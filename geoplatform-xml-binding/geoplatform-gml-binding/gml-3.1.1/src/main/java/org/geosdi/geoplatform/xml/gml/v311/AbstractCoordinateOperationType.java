/*
 *
 *    geo-platform
 *    Rich webgis framework
 *    http://geo-platform.org
 *   ====================================================================
 *
 *   Copyright (C) 2008-2025 geoSDI Group (CNR IMAA - Potenza - ITALY).
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
// Generated on: 2012.04.17 at 10:27:36 PM CEST 
//


package org.geosdi.geoplatform.xml.gml.v311;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;
import org.jvnet.jaxb2_commons.lang.JAXBToStringStrategy;
import org.jvnet.jaxb2_commons.lang.ToString;
import org.jvnet.jaxb2_commons.lang.ToStringStrategy;
import org.jvnet.jaxb2_commons.locator.ObjectLocator;


/**
 * A mathematical operation on coordinates that transforms or converts coordinates to another coordinate reference system. Many but not all coordinate operations (from CRS A to CRS B) also uniquely define the inverse operation (from CRS B to CRS A). In some cases, the operation method algorithm for the inverse operation is the same as for the forward algorithm, but the signs of some operation parameter values must be reversed. In other cases, different algorithms are required for the forward and inverse operations, but the same operation parameter values are used. If (some) entirely different parameter values are needed, a different coordinate operation shall be defined.
 * 
 * <p>Java class for AbstractCoordinateOperationType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="AbstractCoordinateOperationType">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.opengis.net/gml}AbstractCoordinateOperationBaseType">
 *       &lt;sequence>
 *         &lt;element ref="{http://www.opengis.net/gml}coordinateOperationID" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element ref="{http://www.opengis.net/gml}remarks" minOccurs="0"/>
 *         &lt;element ref="{http://www.opengis.net/gml}operationVersion" minOccurs="0"/>
 *         &lt;element ref="{http://www.opengis.net/gml}validArea" minOccurs="0"/>
 *         &lt;element ref="{http://www.opengis.net/gml}scope" minOccurs="0"/>
 *         &lt;element ref="{http://www.opengis.net/gml}_positionalAccuracy" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element ref="{http://www.opengis.net/gml}sourceCRS" minOccurs="0"/>
 *         &lt;element ref="{http://www.opengis.net/gml}targetCRS" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AbstractCoordinateOperationType", propOrder = {
    "coordinateOperationID",
    "remarks",
    "operationVersion",
    "validArea",
    "scope",
    "positionalAccuracy",
    "sourceCRS",
    "targetCRS"
})
@XmlSeeAlso({
    PassThroughOperationType.class,
    AbstractGeneralTransformationType.class,
    AbstractGeneralConversionType.class,
    ConcatenatedOperationType.class
})
public abstract class AbstractCoordinateOperationType
    extends AbstractCoordinateOperationBaseType
    implements ToString
{

    protected List<IdentifierType> coordinateOperationID;
    protected StringOrRefType remarks;
    protected String operationVersion;
    protected ExtentType validArea;
    protected String scope;
    @XmlElementRef(name = "_positionalAccuracy", namespace = "http://www.opengis.net/gml", type = JAXBElement.class)
    protected List<JAXBElement<? extends AbstractPositionalAccuracyType>> positionalAccuracy;
    protected CRSRefType sourceCRS;
    protected CRSRefType targetCRS;

    /**
     * Set of alternative identifications of this coordinate operation. The first coordinateOperationID, if any, is normally the primary identification code, and any others are aliases. Gets the value of the coordinateOperationID property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the coordinateOperationID property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCoordinateOperationID().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link IdentifierType }
     * 
     * 
     */
    public List<IdentifierType> getCoordinateOperationID() {
        if (coordinateOperationID == null) {
            coordinateOperationID = new ArrayList<IdentifierType>();
        }
        return this.coordinateOperationID;
    }

    public boolean isSetCoordinateOperationID() {
        return ((this.coordinateOperationID!= null)&&(!this.coordinateOperationID.isEmpty()));
    }

    public void unsetCoordinateOperationID() {
        this.coordinateOperationID = null;
    }

    /**
     * Comments on or information about this coordinate operation, including source information. 
     * 
     * @return
     *     possible object is
     *     {@link StringOrRefType }
     *     
     */
    public StringOrRefType getRemarks() {
        return remarks;
    }

    /**
     * Sets the value of the remarks property.
     * 
     * @param value
     *     allowed object is
     *     {@link StringOrRefType }
     *     
     */
    public void setRemarks(StringOrRefType value) {
        this.remarks = value;
    }

    public boolean isSetRemarks() {
        return (this.remarks!= null);
    }

    /**
     * Gets the value of the operationVersion property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOperationVersion() {
        return operationVersion;
    }

    /**
     * Sets the value of the operationVersion property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOperationVersion(String value) {
        this.operationVersion = value;
    }

    public boolean isSetOperationVersion() {
        return (this.operationVersion!= null);
    }

    /**
     * Gets the value of the validArea property.
     * 
     * @return
     *     possible object is
     *     {@link ExtentType }
     *     
     */
    public ExtentType getValidArea() {
        return validArea;
    }

    /**
     * Sets the value of the validArea property.
     * 
     * @param value
     *     allowed object is
     *     {@link ExtentType }
     *     
     */
    public void setValidArea(ExtentType value) {
        this.validArea = value;
    }

    public boolean isSetValidArea() {
        return (this.validArea!= null);
    }

    /**
     * Gets the value of the scope property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getScope() {
        return scope;
    }

    /**
     * Sets the value of the scope property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setScope(String value) {
        this.scope = value;
    }

    public boolean isSetScope() {
        return (this.scope!= null);
    }

    /**
     * Unordered set of estimates of the impact of this coordinate operation on point position accuracy. Gives position error estimates for target coordinates of this coordinate operation, assuming no errors in source coordinates. Gets the value of the positionalAccuracy property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the positionalAccuracy property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPositionalAccuracy().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link JAXBElement }{@code <}{@link RelativeInternalPositionalAccuracyType }{@code >}
     * {@link JAXBElement }{@code <}{@link AbsoluteExternalPositionalAccuracyType }{@code >}
     * {@link JAXBElement }{@code <}{@link AbstractPositionalAccuracyType }{@code >}
     * {@link JAXBElement }{@code <}{@link CovarianceMatrixType }{@code >}
     * 
     * 
     */
    public List<JAXBElement<? extends AbstractPositionalAccuracyType>> getPositionalAccuracy() {
        if (positionalAccuracy == null) {
            positionalAccuracy = new ArrayList<JAXBElement<? extends AbstractPositionalAccuracyType>>();
        }
        return this.positionalAccuracy;
    }

    public boolean isSetPositionalAccuracy() {
        return ((this.positionalAccuracy!= null)&&(!this.positionalAccuracy.isEmpty()));
    }

    public void unsetPositionalAccuracy() {
        this.positionalAccuracy = null;
    }

    /**
     * Gets the value of the sourceCRS property.
     * 
     * @return
     *     possible object is
     *     {@link CRSRefType }
     *     
     */
    public CRSRefType getSourceCRS() {
        return sourceCRS;
    }

    /**
     * Sets the value of the sourceCRS property.
     * 
     * @param value
     *     allowed object is
     *     {@link CRSRefType }
     *     
     */
    public void setSourceCRS(CRSRefType value) {
        this.sourceCRS = value;
    }

    public boolean isSetSourceCRS() {
        return (this.sourceCRS!= null);
    }

    /**
     * Gets the value of the targetCRS property.
     * 
     * @return
     *     possible object is
     *     {@link CRSRefType }
     *     
     */
    public CRSRefType getTargetCRS() {
        return targetCRS;
    }

    /**
     * Sets the value of the targetCRS property.
     * 
     * @param value
     *     allowed object is
     *     {@link CRSRefType }
     *     
     */
    public void setTargetCRS(CRSRefType value) {
        this.targetCRS = value;
    }

    public boolean isSetTargetCRS() {
        return (this.targetCRS!= null);
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
            List<IdentifierType> theCoordinateOperationID;
            theCoordinateOperationID = (this.isSetCoordinateOperationID()?this.getCoordinateOperationID():null);
            strategy.appendField(locator, this, "coordinateOperationID", buffer, theCoordinateOperationID);
        }
        {
            StringOrRefType theRemarks;
            theRemarks = this.getRemarks();
            strategy.appendField(locator, this, "remarks", buffer, theRemarks);
        }
        {
            String theOperationVersion;
            theOperationVersion = this.getOperationVersion();
            strategy.appendField(locator, this, "operationVersion", buffer, theOperationVersion);
        }
        {
            ExtentType theValidArea;
            theValidArea = this.getValidArea();
            strategy.appendField(locator, this, "validArea", buffer, theValidArea);
        }
        {
            String theScope;
            theScope = this.getScope();
            strategy.appendField(locator, this, "scope", buffer, theScope);
        }
        {
            List<JAXBElement<? extends AbstractPositionalAccuracyType>> thePositionalAccuracy;
            thePositionalAccuracy = (this.isSetPositionalAccuracy()?this.getPositionalAccuracy():null);
            strategy.appendField(locator, this, "positionalAccuracy", buffer, thePositionalAccuracy);
        }
        {
            CRSRefType theSourceCRS;
            theSourceCRS = this.getSourceCRS();
            strategy.appendField(locator, this, "sourceCRS", buffer, theSourceCRS);
        }
        {
            CRSRefType theTargetCRS;
            theTargetCRS = this.getTargetCRS();
            strategy.appendField(locator, this, "targetCRS", buffer, theTargetCRS);
        }
        return buffer;
    }

    public void setCoordinateOperationID(List<IdentifierType> value) {
        this.coordinateOperationID = null;
        List<IdentifierType> draftl = this.getCoordinateOperationID();
        draftl.addAll(value);
    }

    public void setPositionalAccuracy(List<JAXBElement<? extends AbstractPositionalAccuracyType>> value) {
        this.positionalAccuracy = null;
        List<JAXBElement<? extends AbstractPositionalAccuracyType>> draftl = this.getPositionalAccuracy();
        draftl.addAll(value);
    }

}
