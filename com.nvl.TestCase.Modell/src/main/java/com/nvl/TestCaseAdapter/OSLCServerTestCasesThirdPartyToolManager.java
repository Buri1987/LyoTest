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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.ServletContextEvent;
import java.util.List;
import java.util.ArrayList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.eclipse.lyo.oslc4j.core.model.ServiceProvider;
import org.eclipse.lyo.oslc4j.core.model.AbstractResource;
import com.nvl.TestCaseAdapter.servlet.ServiceProviderCatalogSingleton;
import com.nvl.TestCaseAdapter.ServiceProviderInfo;
import com.nvl.TestCaseAdapter.resources.TestCase;



// Start of user code imports
// End of user code

// Start of user code pre_class_code
// End of user code

public class OSLCServerTestCasesThirdPartyToolManager {

    private static final Logger log = LoggerFactory.getLogger(OSLCServerTestCasesThirdPartyToolManager.class);

    
    // Start of user code class_attributes
    // End of user code
    
    
    // Start of user code class_methods
    // End of user code

    public static void contextInitializeServletListener(final ServletContextEvent servletContextEvent)
    {
        
        // Start of user code contextInitializeServletListener
        // TODO Implement code to establish connection to data backbone etc ...
        // End of user code
        
    }

    public static void contextDestroyServletListener(ServletContextEvent servletContextEvent) 
    {
        
        // Start of user code contextDestroyed
        // TODO Implement code to shutdown connections to data backbone etc...
        // End of user code
    }

    public static ServiceProviderInfo[] getServiceProviderInfos(HttpServletRequest httpServletRequest)
    {
        ServiceProviderInfo[] serviceProviderInfos = {};
        
        // Start of user code "ServiceProviderInfo[] getServiceProviderInfos(...)"
        // TODO Implement code to return the set of ServiceProviders
        // End of user code
        return serviceProviderInfos;
    }

    public static List<TestCase> queryTestCases(HttpServletRequest httpServletRequest, String where, String prefix, boolean paging, int page, int limit)
    {
        List<TestCase> resources = null;
        
        
        // Start of user code queryTestCases
        // TODO Implement code to return a set of resources.
        // An empty List should imply that no resources where found.
        // If you encounter problems, consider throwing the runtime exception WebApplicationException(message, cause, final httpStatus)
        // End of user code
        return resources;
    }
    public static List<TestCase> TestCaseSelector(HttpServletRequest httpServletRequest, String terms)   
    {
        List<TestCase> resources = null;
        
        
        // Start of user code TestCaseSelector
        // TODO Implement code to return a set of resources, based on search criteria 
        // An empty List should imply that no resources where found.
        // If you encounter problems, consider throwing the runtime exception WebApplicationException(message, cause, final httpStatus)
        // End of user code
        return resources;
    }
    public static TestCase createTestCase(HttpServletRequest httpServletRequest, final TestCase aResource)
    {
        TestCase newResource = null;
        
        
        // Start of user code createTestCase
        // TODO Implement code to create a resource
        // If you encounter problems, consider throwing the runtime exception WebApplicationException(message, cause, final httpStatus)
        // End of user code
        return newResource;
    }

    public static TestCase createTestCaseFromDialog(HttpServletRequest httpServletRequest, final TestCase aResource)
    {
        TestCase newResource = null;
        
        
        // Start of user code createTestCaseFromDialog
        // TODO Implement code to create a resource
        // If you encounter problems, consider throwing the runtime exception WebApplicationException(message, cause, final httpStatus)
        // End of user code
        return newResource;
    }



    public static TestCase getTestCase(HttpServletRequest httpServletRequest, final String id)
    {
        TestCase aResource = null;
        
        
        // Start of user code getTestCase
        // TODO Implement code to return a resource
        // return 'null' if the resource was not found.
        // If you encounter problems, consider throwing the runtime exception WebApplicationException(message, cause, final httpStatus)
        // End of user code
        return aResource;
    }

    public static Boolean deleteTestCase(HttpServletRequest httpServletRequest, final String id)
    {
        Boolean deleted = false;
        
        // Start of user code deleteTestCase
        // TODO Implement code to delete a resource
        // If you encounter problems, consider throwing the runtime exception WebApplicationException(message, cause, final httpStatus)
        // End of user code
        return deleted;
    }

    public static TestCase updateTestCase(HttpServletRequest httpServletRequest, final TestCase aResource, final String id) {
        TestCase updatedResource = null;
        
        // Start of user code updateTestCase
        // TODO Implement code to update and return a resource
        // If you encounter problems, consider throwing the runtime exception WebApplicationException(message, cause, final httpStatus)
        // End of user code
        return updatedResource;
    }

    public static String getETagFromTestCase(final TestCase aResource)
    {
        String eTag = null;
        // Start of user code getETagFromTestCase
        // TODO Implement code to return an ETag for a particular resource
        // If you encounter problems, consider throwing the runtime exception WebApplicationException(message, cause, final httpStatus)
        // End of user code
        return eTag;
    }

}
