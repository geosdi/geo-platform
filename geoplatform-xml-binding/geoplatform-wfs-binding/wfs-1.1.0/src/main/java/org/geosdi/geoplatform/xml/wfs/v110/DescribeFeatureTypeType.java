/**
 * geo-platform
 * Rich webgis framework
 * http://geo-platform.org
 * ====================================================================
 * <p>
 * Copyright (C) 2008-2017 geoSDI Group (CNR IMAA - Potenza - ITALY).
 * <p>
 * This program is free software: you can redistribute it and/or modify it
 * under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version. This program is distributed in the
 * hope that it will be useful, but WITHOUT ANY WARRANTY; without
 * even the implied warranty of MERCHANTABILITY or FITNESS FOR
 * A PARTICULAR PURPOSE. See the GNU General Public License
 * for more details. You should have received a copy of the GNU General
 * Public License along with this program. If not, see http://www.gnu.org/licenses/
 * <p>
 * ====================================================================
 * <p>
 * Linking this library statically or dynamically with other modules is
 * making a combined work based on this library. Thus, the terms and
 * conditions of the GNU General Public License cover the whole combination.
 * <p>
 * As a special exception, the copyright holders of this library give you permission
 * to link this library with independent modules to produce an executable, regardless
 * of the license terms of these independent modules, and to copy and distribute
 * the resulting executable under terms of your choice, provided that you also meet,
 * for each linked independent module, the terms and conditions of the license of
 * that module. An independent module is a module which is not derived from or
 * based on this library. If you modify this library, you may extend this exception
 * to your version of the library, but you are not obligated to do so. If you do not
 * wish to do so, delete this exception statement from your version.
 */
//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.5-b10 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2012.10.26 at 03:16:42 PM CEST 
//
package org.geosdi.geoplatform.xml.wfs.v110;

import org.jvnet.jaxb2_commons.lang.JAXBToStringStrategy;
import org.jvnet.jaxb2_commons.lang.ToString;
import org.jvnet.jaxb2_commons.lang.ToStringStrategy;
import org.jvnet.jaxb2_commons.locator.ObjectLocator;

import javax.xml.bind.annotation.*;
import javax.xml.namespace.QName;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * The DescribeFeatureType operation allows a client application to request that
 * a Web Feature Service describe one or more feature types. A Web Feature
 * Service must be able to generate feature descriptions as valid GML3
 * application schemas.
 *
 * The schemas generated by the DescribeFeatureType operation can be used by a
 * client application to validate the output.
 *
 * Feature instances within the WFS interface must be specified using GML3. The
 * schema of feature instances specified within the WFS interface must validate
 * against the feature schemas generated by the DescribeFeatureType request.
 *
 *
 * <p>Java class for DescribeFeatureTypeType complex type.
 *
 * <p>The following schema fragment specifies the expected content contained
 * within this class.
 *
 * <pre>
 * &lt;complexType name="DescribeFeatureTypeType">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.opengis.net/wfs}BaseRequestType">
 *       &lt;sequence>
 *         &lt;element name="TypeName" type="{http://www.w3.org/2001/XMLSchema}QName" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="outputFormat" type="{http://www.w3.org/2001/XMLSchema}string" default="text/xml; subtype=gml/3.1.1" />
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlRootElement(name = "DescribeFeatureType")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DescribeFeatureRequestType", propOrder = {
        "typeName"
})
public class DescribeFeatureTypeType extends BaseRequestType implements ToString {

    @XmlElement(name = "TypeName")
    protected List<QName> typeName;
    @XmlAttribute(name = "outputFormat")
    protected String outputFormat;

    public DescribeFeatureTypeType() {
    }

    /**
     * Gets the value of the typeName property.
     *
     * <p> This accessor method returns a reference to the live list, not a
     * snapshot. Therefore any modification you make to the returned list will
     * be present inside the JAXB object. This is why there is not a
     * <CODE>set</CODE> method for the typeName property.
     *
     * <p> For example, to add a new item, do as follows:
     * <pre>
     *    getTypeName().add(newItem);
     * </pre>
     *
     *
     * <p> Objects of the following type(s) are allowed in the list
     * {@link QName }
     *
     *
     */
    public List<QName> getTypeName() {
        if (typeName == null) {
            typeName = new ArrayList<QName>();
        }
        return this.typeName;
    }

    /**
     * Gets the value of the outputFormat property.
     *
     * @return possible object is {@link String }
     *
     */
    public String getOutputFormat() {
        if (outputFormat == null) {
            return "text/xml; subtype=gml/3.1.1";
        } else {
            return outputFormat;
        }
    }

    /**
     * Sets the value of the outputFormat property.
     *
     * @param value allowed object is {@link String }
     *
     */
    public void setOutputFormat(String value) {
        this.outputFormat = value;
    }

    public String toString() {
        final ToStringStrategy strategy = JAXBToStringStrategy.INSTANCE;
        final StringBuilder buffer = new StringBuilder();
        append(null, buffer, strategy);
        return buffer.toString();
    }

    public StringBuilder append(ObjectLocator locator,
            StringBuilder buffer,
            ToStringStrategy strategy) {
        strategy.appendStart(locator, this, buffer);
        appendFields(locator, buffer, strategy);
        strategy.appendEnd(locator, this, buffer);
        return buffer;
    }

    public StringBuilder appendFields(ObjectLocator locator,
            StringBuilder buffer,
            ToStringStrategy strategy) {
        super.appendFields(locator, buffer, strategy);
        {
            List<QName> theTypeName;
            theTypeName = (((this.typeName != null) && (!this.typeName.isEmpty())) ? this.getTypeName() : null);
            strategy.appendField(locator, this, "typeName", buffer, theTypeName);
        }
        {
            String theOutputFormat;
            theOutputFormat = this.getOutputFormat();
            strategy.appendField(locator, this, "outputFormat", buffer,
                    theOutputFormat);
        }
        return buffer;
    }

    public void setTypeName(List<QName> value) {
        this.typeName = null;
        List<QName> draftl = this.getTypeName();
        draftl.addAll(value);
    }
}
