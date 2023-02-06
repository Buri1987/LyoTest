package com.nvl.TestCaseAdapter.resources;
import java.net.URI;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.xml.namespace.QName;

import org.eclipse.lyo.oslc.domains.RdfVocabularyConstants;
import org.eclipse.lyo.oslc4j.core.annotation.OslcDescription;
import org.eclipse.lyo.oslc4j.core.annotation.OslcName;
import org.eclipse.lyo.oslc4j.core.annotation.OslcOccurs;
import org.eclipse.lyo.oslc4j.core.annotation.OslcPropertyDefinition;
import org.eclipse.lyo.oslc4j.core.annotation.OslcReadOnly;
import org.eclipse.lyo.oslc4j.core.annotation.OslcValueType;
import org.eclipse.lyo.oslc4j.core.model.IExtendedResource;
import org.eclipse.lyo.oslc4j.core.model.Occurs;
import org.eclipse.lyo.oslc4j.core.model.ValueType;


public abstract class RobertAbstractResource implements IExtendedResourceRobert {
	private URI about;
	//private Collection<URI> types = new ArrayList<>();
	private Map<QName, Object> extendedProperties = new HashMap<>();

	protected RobertAbstractResource(final URI about) {
		super();

		this.about = about;
	}

	protected RobertAbstractResource() {
		super();
	}

	@Override
	public final URI getAbout() {
		return about;
	}

	@Override
	public final void setAbout(final URI about) {
		this.about = about;
	}

	@Override
	public void setExtendedProperties(final Map<QName, Object> properties)
	{
		this.extendedProperties = properties;
	}

	@Override
	public Map<QName, Object> getExtendedProperties()
	{
		return extendedProperties;
	}
	/*
    @OslcName("type")
    @OslcPropertyDefinition(RdfDomainConstants.RDF_NAMSPACE + "type")
    @OslcDescription("The resource type URIs")
    @OslcOccurs(Occurs.ZeroOrMany)
    @OslcValueType(ValueType.Resource)
    @OslcReadOnly(false)
	@Override
	public Collection<URI> getTypes()
	{
		return types;
	}

	@Override
	public void setTypes(final Collection<URI> type)
	{
		this.types = type;
	}

	@Override
	public void addType(final URI type)
	{
		this.types.add(type);
	}*/
}
