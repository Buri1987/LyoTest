package com.nvl.TestCaseAdapter.resources;


import java.net.URI;
import java.util.Collection;
import java.util.Map;

import javax.xml.namespace.QName;

import org.eclipse.lyo.oslc4j.core.annotation.OslcResourceShape;
import org.eclipse.lyo.oslc4j.core.model.IResource;

/**
 * A resource that can hold unknown properties and content. If a setter is not
 * found for a property when reading a resource, it is added as an extended
 * property. These extended properties are preserved when writing the resource
 * back out, for instance on a PUT request. In OSLC, clients MUST preserve
 * unknown content when performing updates of resources.
 * 
 * @see <a href="http://open-services.net/bin/view/Main/OslcCoreSpecification?sortcol=table;up=#Unknown_properties_and_content">OSLC Core 2.0: Unknown properties and content</a>
 */
public interface IExtendedResourceRobert extends IResource
{
	/**
	 * Gets the RDF types of this resource. These types will be added to the
	 * serialization of the resource in addition to the
	 * {@link OslcResourceShape#describes()} annotation.
	 * 
	 * @return the collection of types
	 */
	//public Collection<URI> getTypes();

	/**
	 * Sets the RDF types of this resource. These types will be added to the
	 * serialization of the resource in addition to the
	 * {@link OslcResourceShape#describes()} annotation.
	 * 
	 * @param types
	 *			  the collection of types
	 */
	//public void setTypes(final Collection<URI> types);

	/**
	 * Adds an RDF type to this resource. These types will be added to the
	 * serialization of the resource in addition to the
	 * {@link OslcResourceShape#describes()} annotation.
	 * 
	 * @param type
	 *			  the type URI
	 */
	//public void addType(final URI type);

	/**
	 * Sets extended properties not defined in the bean.
	 * 
	 * @param properties
	 *			  a map of properties where the key is the predicate qualified
	 *			  name and the value is the object of the statement. Values are
	 *			  collections if there are multiple statements for a predicate.
	 */
	public void setExtendedProperties(Map<QName, Object> properties);
	
	/**
	 * Gets back the list of extended properties not defined in this bean.
	 * 
	 * @return the extended properties, a map of properties where the key is the
	 *		   predicate qualified name and the value is the object of the
	 *		   statement
	 */
	public Map<QName, Object> getExtendedProperties();
}
