/*
 *  geo-platform
 *  Rich webgis framework
 *  http://geo-platform.org
 * ====================================================================
 *
 * Copyright (C) 2008-2012 geoSDI Group (CNR IMAA - Potenza - ITALY).
 *
 * This program is free software: you can redistribute it and/or modify it 
 * under the terms of the GNU General Public License as published by 
 * the Free Software Foundation, either version 3 of the License, or 
 * (at your option) any later version. This program is distributed in the 
 * hope that it will be useful, but WITHOUT ANY WARRANTY; without 
 * even the implied warranty of MERCHANTABILITY or FITNESS FOR 
 * A PARTICULAR PURPOSE. See the GNU General Public License 
 * for more details. You should have received a copy of the GNU General 
 * Public License along with this program. If not, see http://www.gnu.org/licenses/ 
 *
 * ====================================================================
 *
 * Linking this library statically or dynamically with other modules is 
 * making a combined work based on this library. Thus, the terms and 
 * conditions of the GNU General Public License cover the whole combination. 
 * 
 * As a special exception, the copyright holders of this library give you permission 
 * to link this library with independent modules to produce an executable, regardless 
 * of the license terms of these independent modules, and to copy and distribute 
 * the resulting executable under terms of your choice, provided that you also meet, 
 * for each linked independent module, the terms and conditions of the license of 
 * that module. An independent module is a module which is not derived from or 
 * based on this library. If you modify this library, you may extend this exception 
 * to your version of the library, but you are not obligated to do so. If you do not 
 * wish to do so, delete this exception statement from your version. 
 *
 */
package org.geosdi.geoplatform.xml.gml.v311;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>Java class for DirectionPropertyType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="DirectionPropertyType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;choice>
 *         &lt;element ref="{http://www.opengis.net/gml}DirectionVector"/>
 *         &lt;element ref="{http://www.opengis.net/gml}CompassPoint"/>
 *         &lt;element name="DirectionKeyword" type="{http://www.opengis.net/gml}CodeType"/>
 *         &lt;element name="DirectionString" type="{http://www.opengis.net/gml}StringOrRefType"/>
 *       &lt;/choice>
 *       &lt;attGroup ref="{http://www.opengis.net/gml}AssociationAttributeGroup"/>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DirectionPropertyType", propOrder = {
    "directionVector",
    "compassPoint",
    "directionKeyword",
    "directionString"
})
public class DirectionPropertyType {

    @XmlElement(name = "DirectionVector")
    protected DirectionVectorType directionVector;
    @XmlElement(name = "CompassPoint")
    protected CompassPointEnumeration compassPoint;
    @XmlElement(name = "DirectionKeyword")
    protected CodeType directionKeyword;
    @XmlElement(name = "DirectionString")
    protected StringOrRefType directionString;
    @XmlAttribute(name = "remoteSchema",
                  namespace = "http://www.opengis.net/gml")
    @XmlSchemaType(name = "anyURI")
    protected String remoteSchema;
    @XmlAttribute(name = "type", namespace = "http://www.w3.org/1999/xlink")
    protected String type;
    @XmlAttribute(name = "href", namespace = "http://www.w3.org/1999/xlink")
    @XmlSchemaType(name = "anyURI")
    protected String href;
    @XmlAttribute(name = "role", namespace = "http://www.w3.org/1999/xlink")
    @XmlSchemaType(name = "anyURI")
    protected String role;
    @XmlAttribute(name = "arcrole", namespace = "http://www.w3.org/1999/xlink")
    @XmlSchemaType(name = "anyURI")
    protected String arcrole;
    @XmlAttribute(name = "title", namespace = "http://www.w3.org/1999/xlink")
    protected String title;
    @XmlAttribute(name = "show", namespace = "http://www.w3.org/1999/xlink")
    protected String show;
    @XmlAttribute(name = "actuate", namespace = "http://www.w3.org/1999/xlink")
    protected String actuate;

    public DirectionPropertyType() {
    }

    /**
     * Gets the value of the directionVector property.
     * 
     * @return
     *     possible object is
     *     {@link DirectionVectorType }
     *     
     */
    public DirectionVectorType getDirectionVector() {
        return directionVector;
    }

    /**
     * Sets the value of the directionVector property.
     * 
     * @param value
     *     allowed object is
     *     {@link DirectionVectorType }
     *     
     */
    public void setDirectionVector(DirectionVectorType value) {
        this.directionVector = value;
    }

    /**
     * Gets the value of the compassPoint property.
     * 
     * @return
     *     possible object is
     *     {@link CompassPointEnumeration }
     *     
     */
    public CompassPointEnumeration getCompassPoint() {
        return compassPoint;
    }

    /**
     * Sets the value of the compassPoint property.
     * 
     * @param value
     *     allowed object is
     *     {@link CompassPointEnumeration }
     *     
     */
    public void setCompassPoint(CompassPointEnumeration value) {
        this.compassPoint = value;
    }

    /**
     * Gets the value of the directionKeyword property.
     * 
     * @return
     *     possible object is
     *     {@link CodeType }
     *     
     */
    public CodeType getDirectionKeyword() {
        return directionKeyword;
    }

    /**
     * Sets the value of the directionKeyword property.
     * 
     * @param value
     *     allowed object is
     *     {@link CodeType }
     *     
     */
    public void setDirectionKeyword(CodeType value) {
        this.directionKeyword = value;
    }

    /**
     * Gets the value of the directionString property.
     * 
     * @return
     *     possible object is
     *     {@link StringOrRefType }
     *     
     */
    public StringOrRefType getDirectionString() {
        return directionString;
    }

    /**
     * Sets the value of the directionString property.
     * 
     * @param value
     *     allowed object is
     *     {@link StringOrRefType }
     *     
     */
    public void setDirectionString(StringOrRefType value) {
        this.directionString = value;
    }

    /**
     * Gets the value of the remoteSchema property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRemoteSchema() {
        return remoteSchema;
    }

    /**
     * Sets the value of the remoteSchema property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRemoteSchema(String value) {
        this.remoteSchema = value;
    }

    /**
     * Gets the value of the type property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getType() {
        if (type == null) {
            return "simple";
        } else {
            return type;
        }
    }

    /**
     * Sets the value of the type property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setType(String value) {
        this.type = value;
    }

    /**
     * Gets the value of the href property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHref() {
        return href;
    }

    /**
     * Sets the value of the href property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHref(String value) {
        this.href = value;
    }

    /**
     * Gets the value of the role property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRole() {
        return role;
    }

    /**
     * Sets the value of the role property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRole(String value) {
        this.role = value;
    }

    /**
     * Gets the value of the arcrole property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getArcrole() {
        return arcrole;
    }

    /**
     * Sets the value of the arcrole property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setArcrole(String value) {
        this.arcrole = value;
    }

    /**
     * Gets the value of the title property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets the value of the title property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTitle(String value) {
        this.title = value;
    }

    /**
     * Gets the value of the show property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getShow() {
        return show;
    }

    /**
     * Sets the value of the show property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setShow(String value) {
        this.show = value;
    }

    /**
     * Gets the value of the actuate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getActuate() {
        return actuate;
    }

    /**
     * Sets the value of the actuate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setActuate(String value) {
        this.actuate = value;
    }

    @Override
    public String toString() {
        return "DirectionPropertyType{ " + "directionVector = "
                + directionVector + ", compassPoint = " + compassPoint
                + ", directionKeyword = " + directionKeyword
                + ", directionString = " + directionString
                + ", remoteSchema = " + remoteSchema
                + ", type = " + type + ", href = " + href
                + ", role = " + role + ", arcrole = " + arcrole
                + ", title = " + title + ", show = "
                + show + ", actuate = " + actuate + '}';
    }
}
