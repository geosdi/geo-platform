/*
 *
 *    geo-platform
 *    Rich webgis framework
 *    http://geo-platform.org
 *   ====================================================================
 *
 *   Copyright (C) 2008-2026 geoSDI Group (CNR IMAA - Potenza - ITALY).
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
// Questo file è stato generato dall'Eclipse Implementation of JAXB, v4.0.9 
// Vedere https://eclipse-ee4j.github.io/jaxb-ri 
// Qualsiasi modifica a questo file andrà persa durante la ricompilazione dello schema di origine.
// Generato il: 2026.11.02 alle 03:48:01 PM CET
//

package org.geosdi.geoplatform.xml.iso19139.v20070417.gmd;

import java.util.ArrayList;
import java.util.List;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;
import org.geosdi.geoplatform.xml.iso19139.v20070417.gco.BooleanPropertyType;
import org.geosdi.geoplatform.xml.iso19139.v20070417.gco.CharacterStringPropertyType;
import org.geosdi.geoplatform.xml.iso19139.v20070417.gss.GMPointPropertyType;
import org.jvnet.jaxb.lang.JAXBToStringStrategy;
import org.jvnet.jaxb.lang.ToString;
import org.jvnet.jaxb.lang.ToStringStrategy;
import org.jvnet.jaxb.locator.ObjectLocator;


/**
 * <p>Classe Java per MD_Georectified_Type complex type.</p>
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.</p>
 * 
 * <pre>{@code
 * <complexType name="MD_Georectified_Type">
 *   <complexContent>
 *     <extension base="{http://www.isotc211.org/2005/gmd}MD_GridSpatialRepresentation_Type">
 *       <sequence>
 *         <element name="checkPointAvailability" type="{http://www.isotc211.org/2005/gco}Boolean_PropertyType"/>
 *         <element name="checkPointDescription" type="{http://www.isotc211.org/2005/gco}CharacterString_PropertyType" minOccurs="0"/>
 *         <element name="cornerPoints" type="{http://www.isotc211.org/2005/gss}GM_Point_PropertyType" maxOccurs="unbounded" minOccurs="0"/>
 *         <element name="centerPoint" type="{http://www.isotc211.org/2005/gss}GM_Point_PropertyType" minOccurs="0"/>
 *         <element name="pointInPixel" type="{http://www.isotc211.org/2005/gmd}MD_PixelOrientationCode_PropertyType"/>
 *         <element name="transformationDimensionDescription" type="{http://www.isotc211.org/2005/gco}CharacterString_PropertyType" minOccurs="0"/>
 *         <element name="transformationDimensionMapping" type="{http://www.isotc211.org/2005/gco}CharacterString_PropertyType" maxOccurs="2" minOccurs="0"/>
 *       </sequence>
 *     </extension>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "MD_Georectified_Type", propOrder = {
    "checkPointAvailability",
    "checkPointDescription",
    "cornerPoints",
    "centerPoint",
    "pointInPixel",
    "transformationDimensionDescription",
    "transformationDimensionMapping"
})
public class MDGeorectifiedType
    extends MDGridSpatialRepresentationType
    implements ToString
{

    @XmlElement(required = true)
    protected BooleanPropertyType checkPointAvailability;
    protected CharacterStringPropertyType checkPointDescription;
    protected List<GMPointPropertyType> cornerPoints;
    protected GMPointPropertyType centerPoint;
    @XmlElement(required = true)
    protected MDPixelOrientationCodePropertyType pointInPixel;
    protected CharacterStringPropertyType transformationDimensionDescription;
    protected List<CharacterStringPropertyType> transformationDimensionMapping;

    /**
     * Recupera il valore della proprietà checkPointAvailability.
     * 
     * @return
     *     possible object is
     *     {@link BooleanPropertyType }
     *     
     */
    public BooleanPropertyType getCheckPointAvailability() {
        return checkPointAvailability;
    }

    /**
     * Imposta il valore della proprietà checkPointAvailability.
     * 
     * @param value
     *     allowed object is
     *     {@link BooleanPropertyType }
     *     
     */
    public void setCheckPointAvailability(BooleanPropertyType value) {
        this.checkPointAvailability = value;
    }

    public boolean isSetCheckPointAvailability() {
        return (this.checkPointAvailability!= null);
    }

    /**
     * Recupera il valore della proprietà checkPointDescription.
     * 
     * @return
     *     possible object is
     *     {@link CharacterStringPropertyType }
     *     
     */
    public CharacterStringPropertyType getCheckPointDescription() {
        return checkPointDescription;
    }

    /**
     * Imposta il valore della proprietà checkPointDescription.
     * 
     * @param value
     *     allowed object is
     *     {@link CharacterStringPropertyType }
     *     
     */
    public void setCheckPointDescription(CharacterStringPropertyType value) {
        this.checkPointDescription = value;
    }

    public boolean isSetCheckPointDescription() {
        return (this.checkPointDescription!= null);
    }

    /**
     * Gets the value of the cornerPoints property.
     * 
     * <p>This accessor method returns a reference to the live list,
     * not a snapshot. Therefore, any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a {@code set} method for the cornerPoints property.</p>
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * </p>
     * <pre>
     * getCornerPoints().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link GMPointPropertyType }
     * </p>
     * 
     * 
     * @return
     *     The value of the cornerPoints property.
     */
    public List<GMPointPropertyType> getCornerPoints() {
        if (cornerPoints == null) {
            cornerPoints = new ArrayList<>();
        }
        return this.cornerPoints;
    }

    public boolean isSetCornerPoints() {
        return ((this.cornerPoints!= null)&&(!this.cornerPoints.isEmpty()));
    }

    public void unsetCornerPoints() {
        this.cornerPoints = null;
    }

    /**
     * Recupera il valore della proprietà centerPoint.
     * 
     * @return
     *     possible object is
     *     {@link GMPointPropertyType }
     *     
     */
    public GMPointPropertyType getCenterPoint() {
        return centerPoint;
    }

    /**
     * Imposta il valore della proprietà centerPoint.
     * 
     * @param value
     *     allowed object is
     *     {@link GMPointPropertyType }
     *     
     */
    public void setCenterPoint(GMPointPropertyType value) {
        this.centerPoint = value;
    }

    public boolean isSetCenterPoint() {
        return (this.centerPoint!= null);
    }

    /**
     * Recupera il valore della proprietà pointInPixel.
     * 
     * @return
     *     possible object is
     *     {@link MDPixelOrientationCodePropertyType }
     *     
     */
    public MDPixelOrientationCodePropertyType getPointInPixel() {
        return pointInPixel;
    }

    /**
     * Imposta il valore della proprietà pointInPixel.
     * 
     * @param value
     *     allowed object is
     *     {@link MDPixelOrientationCodePropertyType }
     *     
     */
    public void setPointInPixel(MDPixelOrientationCodePropertyType value) {
        this.pointInPixel = value;
    }

    public boolean isSetPointInPixel() {
        return (this.pointInPixel!= null);
    }

    /**
     * Recupera il valore della proprietà transformationDimensionDescription.
     * 
     * @return
     *     possible object is
     *     {@link CharacterStringPropertyType }
     *     
     */
    public CharacterStringPropertyType getTransformationDimensionDescription() {
        return transformationDimensionDescription;
    }

    /**
     * Imposta il valore della proprietà transformationDimensionDescription.
     * 
     * @param value
     *     allowed object is
     *     {@link CharacterStringPropertyType }
     *     
     */
    public void setTransformationDimensionDescription(CharacterStringPropertyType value) {
        this.transformationDimensionDescription = value;
    }

    public boolean isSetTransformationDimensionDescription() {
        return (this.transformationDimensionDescription!= null);
    }

    /**
     * Gets the value of the transformationDimensionMapping property.
     * 
     * <p>This accessor method returns a reference to the live list,
     * not a snapshot. Therefore, any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a {@code set} method for the transformationDimensionMapping property.</p>
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * </p>
     * <pre>
     * getTransformationDimensionMapping().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CharacterStringPropertyType }
     * </p>
     * 
     * 
     * @return
     *     The value of the transformationDimensionMapping property.
     */
    public List<CharacterStringPropertyType> getTransformationDimensionMapping() {
        if (transformationDimensionMapping == null) {
            transformationDimensionMapping = new ArrayList<>();
        }
        return this.transformationDimensionMapping;
    }

    public boolean isSetTransformationDimensionMapping() {
        return ((this.transformationDimensionMapping!= null)&&(!this.transformationDimensionMapping.isEmpty()));
    }

    public void unsetTransformationDimensionMapping() {
        this.transformationDimensionMapping = null;
    }

    @Override
    public String toString() {
        final ToStringStrategy strategy = JAXBToStringStrategy.getInstance();
        final StringBuilder buffer = new StringBuilder();
        append(null, buffer, strategy);
        return buffer.toString();
    }

    @Override
    public StringBuilder append(ObjectLocator locator, StringBuilder buffer, ToStringStrategy strategy) {
        strategy.appendStart(locator, this, buffer);
        appendFields(locator, buffer, strategy);
        strategy.appendEnd(locator, this, buffer);
        return buffer;
    }

    @Override
    public StringBuilder appendFields(ObjectLocator locator, StringBuilder buffer, ToStringStrategy strategy) {
        super.appendFields(locator, buffer, strategy);
        {
            BooleanPropertyType theCheckPointAvailability;
            theCheckPointAvailability = this.getCheckPointAvailability();
            strategy.appendField(locator, this, "checkPointAvailability", buffer, theCheckPointAvailability, this.isSetCheckPointAvailability());
        }
        {
            CharacterStringPropertyType theCheckPointDescription;
            theCheckPointDescription = this.getCheckPointDescription();
            strategy.appendField(locator, this, "checkPointDescription", buffer, theCheckPointDescription, this.isSetCheckPointDescription());
        }
        {
            List<GMPointPropertyType> theCornerPoints;
            theCornerPoints = (this.isSetCornerPoints()?this.getCornerPoints():null);
            strategy.appendField(locator, this, "cornerPoints", buffer, theCornerPoints, this.isSetCornerPoints());
        }
        {
            GMPointPropertyType theCenterPoint;
            theCenterPoint = this.getCenterPoint();
            strategy.appendField(locator, this, "centerPoint", buffer, theCenterPoint, this.isSetCenterPoint());
        }
        {
            MDPixelOrientationCodePropertyType thePointInPixel;
            thePointInPixel = this.getPointInPixel();
            strategy.appendField(locator, this, "pointInPixel", buffer, thePointInPixel, this.isSetPointInPixel());
        }
        {
            CharacterStringPropertyType theTransformationDimensionDescription;
            theTransformationDimensionDescription = this.getTransformationDimensionDescription();
            strategy.appendField(locator, this, "transformationDimensionDescription", buffer, theTransformationDimensionDescription, this.isSetTransformationDimensionDescription());
        }
        {
            List<CharacterStringPropertyType> theTransformationDimensionMapping;
            theTransformationDimensionMapping = (this.isSetTransformationDimensionMapping()?this.getTransformationDimensionMapping():null);
            strategy.appendField(locator, this, "transformationDimensionMapping", buffer, theTransformationDimensionMapping, this.isSetTransformationDimensionMapping());
        }
        return buffer;
    }

    public void setCornerPoints(List<GMPointPropertyType> value) {
        this.cornerPoints = null;
        if (value!= null) {
            List<GMPointPropertyType> draftl = this.getCornerPoints();
            draftl.addAll(value);
        }
    }

    public void setTransformationDimensionMapping(List<CharacterStringPropertyType> value) {
        this.transformationDimensionMapping = null;
        if (value!= null) {
            List<CharacterStringPropertyType> draftl = this.getTransformationDimensionMapping();
            draftl.addAll(value);
        }
    }

}
