// Start of user code Copyright
/*
 * Copyright (c) 2020 Contributors to the Eclipse Foundation
 *
 * See the NOTICE file(s) distributed with this work for additional
 * information regarding copyright ownership.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Distribution License 1.0 which is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 *
 * SPDX-License-Identifier: BSD-3-Simple
 */
// End of user code

package com.nvl.TestCaseAdapter;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;
import javax.ws.rs.core.UriBuilder;

import org.eclipse.lyo.oslc4j.core.model.Link;
import org.eclipse.lyo.oslc4j.core.OSLC4JUtils;
import com.nvl.TestCaseAdapter.resources.Person;
import com.nvl.TestCaseAdapter.resources.TestCase;

// Start of user code imports
// End of user code

// Start of user code pre_class_code
// End of user code

public class OSLCServerTestCasesThirdPartyToolResourcesFactory {

    // Start of user code class_attributes
    // End of user code
    
    // Start of user code class_methods
    // End of user code

    //methods for TestCase resource
    
    public static TestCase createTestCase(final String id)
    {
        return new TestCase(constructURIForTestCase(id));
    }
    
    public static URI constructURIForTestCase(final String id)
    {
        String basePath = OSLC4JUtils.getServletURI();
        Map<String, Object> pathParameters = new HashMap<String, Object>();
        pathParameters.put("id", id);
        String instanceURI = "testCaseServices/TestCase/{id}";
    
        final UriBuilder builder = UriBuilder.fromUri(basePath);
        return builder.path(instanceURI).buildFromMap(pathParameters);
    }
    
    public static Link constructLinkForTestCase(final String id , final String label)
    {
        return new Link(constructURIForTestCase(id), label);
    }
    
    public static Link constructLinkForTestCase(final String id)
    {
        return new Link(constructURIForTestCase(id));
    }
    

}
