<?xml version="1.0" encoding="UTF-8"?>
<%--
 Copyright (c) 2011, 2014 IBM Corporation.

 All rights reserved. This program and the accompanying materials
 are made available under the terms of the Eclipse Public License v1.0
 and Eclipse Distribution License v. 1.0 which accompanies this distribution.
 
 The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 and the Eclipse Distribution License is available at
 http://www.eclipse.org/org/documents/edl-v10.php.
 
 Contributors:
 
    Sam Padgett		 - initial API and implementation
    Michael Fiedler	 - adapted for OSLC4J
    Sam Padgett		 - fix rootservices about URI
--%>
<%@ page contentType="application/rdf+xml" language="java"
	pageEncoding="UTF-8"%>
<%
String baseUri = (String) request.getAttribute("baseUri");
String catalogUri = (String) request.getAttribute("catalogUri");
String oauthDomain = (String) request.getAttribute("oauthDomain");
String about = (String) request.getAttribute("about");
%>

<rdf:Description rdf:about="<%=about%>"
	xmlns:iotp="http://jazz.net/ns/dm/iotp#"
	xmlns:oslc="http://open-services.net/ns/core#"
    xmlns:rqm="http://jazz.net/xmlns/prod/jazz/rqm/qm/1.0/"
    xmlns:qm_rqm="http://jazz.net/ns/qm/rqm#"
	xmlns:oslc_config="http://open-services.net/ns/config#"
	xmlns:trs="http://open-services.net/ns/core/trs#"
	xmlns:dc="http://purl.org/dc/terms/"
	xmlns:jfs="http://jazz.net/xmlns/prod/jazz/jfs/1.0/"
	xmlns:rdf="http://www.w3.org/1999/02/22-rdf-syntax-ns#">

	<dc:title>OSLC Testing Adapter Root Services</dc:title>

	<!-- Service Providers - ServiceProviderCatalog URLs for each domain -->
  	<oslc_qm:qmServiceProviders
	      xmlns:oslc_qm="http://open-services.net/xmlns/qm/1.0/"
	      rdf:resource="<%=catalogUri%>" />

	<!-- OAuth URLs for establishing server-to-server connections -->
	<jfs:oauthRealmName>Jazz</jfs:oauthRealmName>
	<jfs:oauthDomain><%=oauthDomain%></jfs:oauthDomain>
	<jfs:oauthRequestConsumerKeyUrl
		rdf:resource="<%=baseUri + "/services/oauth/requestKey"%>" />
	<jfs:oauthApprovalModuleUrl
		rdf:resource="<%=baseUri + "/services/oauth/approveKey"%>" />
	<jfs:oauthRequestTokenUrl
		rdf:resource="<%=baseUri + "/services/oauth/requestToken"%>" />
	<jfs:oauthUserAuthorizationUrl
		rdf:resource="<%=baseUri + "/services/oauth/authorize"%>" />
	<jfs:oauthAccessTokenUrl
		rdf:resource="<%=baseUri + "/services/oauth/accessToken"%>" />

</rdf:Description>
