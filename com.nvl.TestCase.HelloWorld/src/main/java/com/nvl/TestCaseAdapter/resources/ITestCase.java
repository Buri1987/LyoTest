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
 *
 * This file is generated by Lyo Designer (https://www.eclipse.org/lyo/)
 */
// End of user code

package com.nvl.TestCaseAdapter.resources;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.text.SimpleDateFormat;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.Iterator;

import org.eclipse.lyo.oslc4j.core.annotation.OslcAllowedValue;
import org.eclipse.lyo.oslc4j.core.annotation.OslcDescription;
import org.eclipse.lyo.oslc4j.core.annotation.OslcMemberProperty;
import org.eclipse.lyo.oslc4j.core.annotation.OslcName;
import org.eclipse.lyo.oslc4j.core.annotation.OslcNamespace;
import org.eclipse.lyo.oslc4j.core.annotation.OslcOccurs;
import org.eclipse.lyo.oslc4j.core.annotation.OslcPropertyDefinition;
import org.eclipse.lyo.oslc4j.core.annotation.OslcRdfCollectionType;
import org.eclipse.lyo.oslc4j.core.annotation.OslcRange;
import org.eclipse.lyo.oslc4j.core.annotation.OslcReadOnly;
import org.eclipse.lyo.oslc4j.core.annotation.OslcRepresentation;
import org.eclipse.lyo.oslc4j.core.annotation.OslcResourceShape;
import org.eclipse.lyo.oslc4j.core.annotation.OslcTitle;
import org.eclipse.lyo.oslc4j.core.annotation.OslcValueType;
import org.eclipse.lyo.oslc4j.core.model.AbstractResource;
import org.eclipse.lyo.oslc4j.core.model.Link;
import org.eclipse.lyo.oslc4j.core.model.Occurs;
import org.eclipse.lyo.oslc4j.core.model.OslcConstants;
import org.eclipse.lyo.oslc4j.core.model.Representation;
import org.eclipse.lyo.oslc4j.core.model.ValueType;

import com.nvl.TestCaseAdapter.resources.Oslc_qmDomainConstants;
import com.nvl.TestCaseAdapter.resources.DctermsDomainConstants;
import com.nvl.TestCaseAdapter.resources.NvlQmDomainConstants;
import com.nvl.TestCaseAdapter.resources.FoafDomainConstants;
import com.nvl.TestCaseAdapter.resources.Oslc_qmDomainConstants;
import com.nvl.TestCaseAdapter.resources.RdfDomainConstants;


// Start of user code imports
// End of user code

@OslcNamespace(Oslc_qmDomainConstants.TESTCASE_NAMESPACE)
@OslcName(Oslc_qmDomainConstants.TESTCASE_LOCALNAME)
@OslcResourceShape(title = "Descriptio", description = "Description (Test Case) defines the criteria which determine whether a system exhibits the correct behavior under a specific set of circumstances. ", describes = Oslc_qmDomainConstants.TESTCASE_TYPE)
public interface ITestCase
{

    public void addRdfObject(final Link rdfObject );

    @OslcName("identifier")
    @OslcPropertyDefinition(DctermsDomainConstants.DUBLIN_CORE_NAMSPACE + "identifier")
    @OslcDescription("A unique identifier for a resource. Assigned by the service provider when a resource is created. Not intended for end-user display.")
    @OslcOccurs(Occurs.ExactlyOne)
    @OslcValueType(ValueType.String)
    @OslcReadOnly(false)
    @OslcTitle("id")
    public String getIdentifier();

    @OslcName("description")
    @OslcPropertyDefinition(DctermsDomainConstants.DUBLIN_CORE_NAMSPACE + "description")
    @OslcOccurs(Occurs.ExactlyOne)
    @OslcValueType(ValueType.String)
    @OslcReadOnly(false)
    public String getDescription();

    @OslcName("created")
    @OslcPropertyDefinition(Oslc_qmDomainConstants.QUALITY_MANAGEMENT_NAMSPACE + "created")
    @OslcDescription("Timestamp of resource creation (reference: Dublin Core).")
    @OslcOccurs(Occurs.ExactlyOne)
    @OslcValueType(ValueType.DateTime)
    @OslcReadOnly(false)
    @OslcTitle("created")
    public Date getCreated();

    @OslcName("dokumentenNummer")
    @OslcPropertyDefinition(NvlQmDomainConstants.NVL_QM_NAMSPACE + "dokumentenNummer")
    @OslcDescription("Entspricht der eindeutogen Dokumentennummer eines Dokuments aus dem PS-Systems.")
    @OslcOccurs(Occurs.ExactlyOne)
    @OslcValueType(ValueType.String)
    @OslcReadOnly(false)
    @OslcTitle("docNumber")
    public String getDokumentenNummer();

    @OslcName("testLevel")
    @OslcPropertyDefinition(NvlQmDomainConstants.NVL_QM_NAMSPACE + "testLevel")
    @OslcOccurs(Occurs.ExactlyOne)
    @OslcValueType(ValueType.String)
    @OslcReadOnly(false)
    @OslcTitle("TestLevel")
    public String getTestLevel();

    @OslcName("createdby")
    @OslcPropertyDefinition(Oslc_qmDomainConstants.QUALITY_MANAGEMENT_NAMSPACE + "createdby")
    @OslcOccurs(Occurs.ExactlyOne)
    @OslcValueType(ValueType.String)
    @OslcRange({FoafDomainConstants.PERSON_TYPE})
    @OslcReadOnly(false)
    public String getCreatedby();

    @OslcName("owner")
    @OslcPropertyDefinition(Oslc_qmDomainConstants.QUALITY_MANAGEMENT_NAMSPACE + "owner")
    @OslcOccurs(Occurs.ExactlyOne)
    @OslcValueType(ValueType.String)
    @OslcRange({FoafDomainConstants.PERSON_TYPE})
    @OslcReadOnly(false)
    public String getOwner();

    @OslcName("title")
    @OslcPropertyDefinition(DctermsDomainConstants.DUBLIN_CORE_NAMSPACE + "title")
    @OslcOccurs(Occurs.ExactlyOne)
    @OslcValueType(ValueType.String)
    @OslcReadOnly(false)
    public String getTitle();

    @OslcName("object")
    @OslcPropertyDefinition(RdfDomainConstants.RDF_NAMSPACE + "object")
    @OslcOccurs(Occurs.ZeroOrMany)
    @OslcValueType(ValueType.Resource)
    @OslcReadOnly(false)
    public Set<Link> getRdfObject();


    public void setIdentifier(final String identifier );
    public void setDescription(final String description );
    public void setCreated(final Date created );
    public void setDokumentenNummer(final String dokumentenNummer );
    public void setTestLevel(final String testLevel );
    public void setCreatedby(final String createdby );
    public void setOwner(final String owner );
    public void setTitle(final String title );
    public void setRdfObject(final Set<Link> rdfObject );
}
