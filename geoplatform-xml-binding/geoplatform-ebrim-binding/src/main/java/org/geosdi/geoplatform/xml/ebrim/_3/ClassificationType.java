/**
 *
 *    geo-platform
 *    Rich webgis framework
 *    http://geo-platform.org
 *   ====================================================================
 *
 *   Copyright (C) 2008-2017 geoSDI Group (CNR IMAA - Potenza - ITALY).
 *
 *   This program is free software: you can redistribute it and/or modify it
 *   under the terms of the GNU General Public License as published by
 *   the Free Software Foundation, either version 3 of the License, or
 *   (at your option) any later version. This program is distributed in the
 *   hope that it will be useful, but WITHOUT ANY WARRANTY; without
 *   even the implied warranty of MERCHANTABILITY or FITNESS FOR
 *   A PARTICULAR PURPOSE. See the GNU General Public License
 *   for more details. You should have received a copy of the GNU General
 *   Public License along with this program. If not, see http://www.gnu.org/licenses/
 *
 *   ====================================================================
 *
 *   Linking this library statically or dynamically with other modules is
 *   making a combined work based on this library. Thus, the terms and
 *   conditions of the GNU General Public License cover the whole combination.
 *
 *   As a special exception, the copyright holders of this library give you permission
 *   to link this library with independent modules to produce an executable, regardless
 *   of the license terms of these independent modules, and to copy and distribute
 *   the resulting executable under terms of your choice, provided that you also meet,
 *   for each linked independent module, the terms and conditions of the license of
 *   that module. An independent module is a module which is not derived from or
 *   based on this library. If you modify this library, you may extend this exception
 *   to your version of the library, but you are not obligated to do so. If you do not
 *   wish to do so, delete this exception statement from your version.
 */
//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.5-b10 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2012.04.16 at 04:57:06 PM CEST 
//


package org.geosdi.geoplatform.xml.ebrim._3;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;
import org.jvnet.jaxb2_commons.lang.JAXBToStringStrategy;
import org.jvnet.jaxb2_commons.lang.ToString;
import org.jvnet.jaxb2_commons.lang.ToStringStrategy;
import org.jvnet.jaxb2_commons.locator.ObjectLocator;


/**
 * 
 *         Classification is the mapping of the same named interface in ebRIM.
 *         It extends RegistryObject.
 *         A Classification specifies references to two registry entrys.
 *         The classifiedObject is id of the Object being classified.
 *         The classificationNode is id of the ClassificationNode classying the object
 *       
 * 
 * <p>Java class for ClassificationType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ClassificationType">
 *   &lt;complexContent>
 *     &lt;extension base="{urn:oasis:names:tc:ebxml-regrep:xsd:rim:3.0}RegistryObjectType">
 *       &lt;attribute name="classificationScheme" type="{urn:oasis:names:tc:ebxml-regrep:xsd:rim:3.0}referenceURI" />
 *       &lt;attribute name="classifiedObject" use="required" type="{urn:oasis:names:tc:ebxml-regrep:xsd:rim:3.0}referenceURI" />
 *       &lt;attribute name="classificationNode" type="{urn:oasis:names:tc:ebxml-regrep:xsd:rim:3.0}referenceURI" />
 *       &lt;attribute name="nodeRepresentation" type="{urn:oasis:names:tc:ebxml-regrep:xsd:rim:3.0}LongName" />
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ClassificationType")
public class ClassificationType
    extends RegistryObjectType
    implements Serializable, ToString
{

    private final static long serialVersionUID = 1100L;
    @XmlAttribute(name = "classificationScheme")
    protected String classificationScheme;
    @XmlAttribute(name = "classifiedObject", required = true)
    protected String classifiedObject;
    @XmlAttribute(name = "classificationNode")
    protected String classificationNode;
    @XmlAttribute(name = "nodeRepresentation")
    protected String nodeRepresentation;

    /**
     * Gets the value of the classificationScheme property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getClassificationScheme() {
        return classificationScheme;
    }

    /**
     * Sets the value of the classificationScheme property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setClassificationScheme(String value) {
        this.classificationScheme = value;
    }

    /**
     * Gets the value of the classifiedObject property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getClassifiedObject() {
        return classifiedObject;
    }

    /**
     * Sets the value of the classifiedObject property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setClassifiedObject(String value) {
        this.classifiedObject = value;
    }

    /**
     * Gets the value of the classificationNode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getClassificationNode() {
        return classificationNode;
    }

    /**
     * Sets the value of the classificationNode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setClassificationNode(String value) {
        this.classificationNode = value;
    }

    /**
     * Gets the value of the nodeRepresentation property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNodeRepresentation() {
        return nodeRepresentation;
    }

    /**
     * Sets the value of the nodeRepresentation property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNodeRepresentation(String value) {
        this.nodeRepresentation = value;
    }

    public String toString() {
        final ToStringStrategy strategy = JAXBToStringStrategy.INSTANCE;
        final StringBuilder buffer = new StringBuilder();
        append(null, buffer, strategy);
        return buffer.toString();
    }

    public StringBuilder append(ObjectLocator locator, StringBuilder buffer, ToStringStrategy strategy) {
        strategy.appendStart(locator, this, buffer);
        appendFields(locator, buffer, strategy);
        strategy.appendEnd(locator, this, buffer);
        return buffer;
    }

    public StringBuilder appendFields(ObjectLocator locator, StringBuilder buffer, ToStringStrategy strategy) {
        super.appendFields(locator, buffer, strategy);
        {
            String theClassificationScheme;
            theClassificationScheme = this.getClassificationScheme();
            strategy.appendField(locator, this, "classificationScheme", buffer, theClassificationScheme);
        }
        {
            String theClassifiedObject;
            theClassifiedObject = this.getClassifiedObject();
            strategy.appendField(locator, this, "classifiedObject", buffer, theClassifiedObject);
        }
        {
            String theClassificationNode;
            theClassificationNode = this.getClassificationNode();
            strategy.appendField(locator, this, "classificationNode", buffer, theClassificationNode);
        }
        {
            String theNodeRepresentation;
            theNodeRepresentation = this.getNodeRepresentation();
            strategy.appendField(locator, this, "nodeRepresentation", buffer, theNodeRepresentation);
        }
        return buffer;
    }

}
