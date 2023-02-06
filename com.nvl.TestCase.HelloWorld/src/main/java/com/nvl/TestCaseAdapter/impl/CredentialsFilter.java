/*******************************************************************************
 * Copyright (c) 2012 IBM Corporation.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompanies this distribution.
 *
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 *
 * Contributors:
 *
 *     Jim Amsden     - initial API and implementation for CE4IoTConnector
 *     
 *******************************************************************************/
package com.nvl.TestCaseAdapter.impl;

import java.io.IOException;
import java.net.URISyntaxException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.Properties;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.eclipse.lyo.server.oauth.consumerstore.FileSystemConsumerStore;
import org.eclipse.lyo.server.oauth.core.Application;
import org.eclipse.lyo.server.oauth.core.AuthenticationException;
import org.eclipse.lyo.server.oauth.core.OAuthConfiguration;
import org.eclipse.lyo.server.oauth.core.OAuthRequest;
import org.eclipse.lyo.server.oauth.core.consumer.LyoOAuthConsumer;
import org.eclipse.lyo.server.oauth.core.token.LRUCache;
import org.eclipse.lyo.server.oauth.core.token.SimpleTokenStrategy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nvl.TestCaseAdapter.OSLCServerTestCasesThirdPartyToolManager;

import net.oauth.OAuth;
import net.oauth.OAuthAccessor;
import net.oauth.OAuthException;
import net.oauth.OAuthMessage;
import net.oauth.OAuthProblemException;
import net.oauth.http.HttpMessage;
import net.oauth.server.OAuthServlet;

/**
 * A JEE filter that is used to create an authentication challenge on access to
 * protected resources. The actual authentication is delegated to the IoT
 * Platform since it actually manages the resources. This ensures the user
 * credentials, privileges and authentication are done by the data sources
 * owning the resources. This filter simply delegates user management to the IoT
 * Platform login.
 * 
 * @author jamsden
 *
 */
public class CredentialsFilter implements Filter {

	public static final String CREDENTIALS_ATTRIBUTE = "com.sample.testing.Credentials";
	private static final String ADMIN_SESSION_ATTRIBUTE = "com.sample.testing.AdminSession";
	public static final String JAZZ_INVALID_EXPIRED_TOKEN_OAUTH_PROBLEM = "invalid_expired_token";
	public static final String OAUTH_REALM = "Jazz";

	private static Logger log = LoggerFactory.getLogger(CredentialsFilter.class);

	private static Properties configProperties = new Properties();

	@Override
	public void destroy() {

	}

	/**
	 * Check for OAuth or BasicAuth credentials and challenge if not found.
	 * 
	 * Store the IoTPClient and BluemixClient in the HttpSession for retrieval in
	 * the REST services. The IoTPClient has the LtpaToken2 cookie used to
	 * authenticate the user's access to the IoT Platform REST APIs. This cookie is
	 * retrieved on Watson IoT Platform login. BluemixClient uses bearer token
	 * authorization.
	 */
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain)
			throws IOException, ServletException {

		if (servletRequest instanceof HttpServletRequest && servletResponse instanceof HttpServletResponse) {
			HttpServletRequest request = (HttpServletRequest) servletRequest;
			HttpServletResponse response = (HttpServletResponse) servletResponse;

			boolean isTwoLeggedOAuthRequest = false;

			// To suppress lost form parameters by POST request when creating new test case
			String requestURI = request.getRequestURI().toString();
			if (!requestURI.endsWith("creator")) {

				// Don't protect requests to oauth service.
				// TODO: Consider not protecting requests for oauth service in web.xml
				String pathinfo = request.getPathInfo();
				if (!pathinfo.startsWith("/oauth") && !pathinfo.startsWith("/login")) {

					// First check if this is an OAuth request.
					try {
						try {
							OAuthMessage message = OAuthServlet.getMessage(request, null);
							// test if this is a valid two-legged oauth request
							if ("".equals(message.getToken())) {
								validateTwoLeggedOAuthMessage(message);
								isTwoLeggedOAuthRequest = true;
							}

						} catch (OAuthProblemException e) {
							if (OAuth.Problems.TOKEN_REJECTED.equals(e.getProblem())) {
								throwInvalidExpiredException(e);
							} else {
								throw e;
							}
						}
					} catch (OAuthException e) {
						OAuthServlet.handleException(response, e, OAUTH_REALM);
						return;
					} catch (URISyntaxException e) {
						throw new ServletException(e);
					}

					// This is not an OAuth request
					HttpSession session = request.getSession();

				}
			}
			chain.doFilter(servletRequest, servletResponse);
		}
	}

	private void validateTwoLeggedOAuthMessage(OAuthMessage message)
			throws IOException, OAuthException, URISyntaxException {
		OAuthConfiguration config = OAuthConfiguration.getInstance();
		LyoOAuthConsumer consumer = config.getConsumerStore().getConsumer(message.getConsumerKey());
		if (consumer != null && consumer.isTrusted()) {
			// The request can be a two-legged oauth request because it's a trusted consumer
			// Validate the message with an empty token and an empty secret
			OAuthAccessor accessor = new OAuthAccessor(consumer);
			accessor.requestToken = "";
			accessor.tokenSecret = "";
			config.getValidator().validateMessage(message, accessor);
		} else {
			throw new OAuthProblemException(OAuth.Problems.TOKEN_REJECTED);
		}
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		OAuthConfiguration config = OAuthConfiguration.getInstance();

		// Validates a user's ID and password.
		config.setApplication(new Application() {
			@Override
			public void login(HttpServletRequest request, String id, String password) throws AuthenticationException {
				try {
					// Login to the API Platform
					log.info("Doing application login: {}", id);
					/*
					 * IoTPClient iotpClient = new IoTPClient(id, password); iotpClient.login();
					 * request.setAttribute(IoTPClient.IOTPCLIENT_ATTRIBUTE, iotpClient);
					 * 
					 * Credentials creds = new Credentials(); creds.setUsername(id);
					 * creds.setPassword(password);
					 * request.getSession().setAttribute(CREDENTIALS_ATTRIBUTE, creds);
					 */
					// TODO: determine if the IoT Platform user is an administrator
					request.getSession().setAttribute(ADMIN_SESSION_ATTRIBUTE, Boolean.TRUE);
				} catch (Exception e) {
					log.error("Login failed: {}", e.getCause().getMessage());
					throw new AuthenticationException(e.getCause().getMessage(), e);
				}
			}

			@Override
			public String getName() {
				// Display name for this application.
				return "TestAdapterConnector";
			}

			@Override
			public boolean isAdminSession(HttpServletRequest request) {
//				return Boolean.TRUE.equals(request.getSession().getAttribute(ADMIN_SESSION_ATTRIBUTE));
				return true;
			}

			@Override
			public String getRealm(HttpServletRequest request) {
				return OSLCServerTestCasesThirdPartyToolManager.REALM;
			}

			@Override
			public boolean isAuthenticated(HttpServletRequest request) {
				/*
				 * IoTPClient iotpc = (IoTPClient)
				 * request.getSession().getAttribute(IoTPClient.IOTPCLIENT_ATTRIBUTE); if (iotpc
				 * == null) { return false; }
				 * 
				 * request.setAttribute(IoTPClient.IOTPCLIENT_ATTRIBUTE, iotpc);
				 */
				return true;
			}
		});

		try {
			// For now, hard-code the consumers.
			config.setConsumerStore(new FileSystemConsumerStore("OAuthStore.xml"));
		} catch (Throwable t) {
			log.error("Error initializing the OAuth consumer store: " + t.getMessage());

		}

	}

	/**
	 * Jazz requires a exception with the magic string "invalid_expired_token" to
	 * restart OAuth authentication
	 * 
	 * @param e
	 * @return
	 * @throws OAuthProblemException
	 */
	private void throwInvalidExpiredException(OAuthProblemException e) throws OAuthProblemException {
		OAuthProblemException ope = new OAuthProblemException(JAZZ_INVALID_EXPIRED_TOKEN_OAUTH_PROBLEM);
		ope.setParameter(HttpMessage.STATUS_CODE, HttpServletResponse.SC_UNAUTHORIZED);
		throw ope;
	}

}
