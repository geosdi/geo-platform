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
// Generato il: 2026.11.02 alle 03:48:04 PM CET
//

package org.geosdi.geoplatform.xml.gml.v321;

import jakarta.xml.bind.JAXBElement;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElementRef;
import jakarta.xml.bind.annotation.XmlType;
import org.jvnet.jaxb.lang.JAXBToStringStrategy;
import org.jvnet.jaxb.lang.ToString;
import org.jvnet.jaxb.lang.ToStringStrategy;
import org.jvnet.jaxb.locator.ObjectLocator;


/**
 * <p>Classe Java per MovingObjectStatusType complex type.</p>
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.</p>
 * 
 * <pre>{@code
 * <complexType name="MovingObjectStatusType">
 *   <complexContent>
 *     <extension base="{http://www.opengis.net/gml/3.2}AbstractTimeSliceType">
 *       <sequence>
 *         <choice>
 *           <element name="position" type="{http://www.opengis.net/gml/3.2}GeometryPropertyType"/>
 *           <element ref="{http://www.opengis.net/gml/3.2}pos"/>
 *           <element ref="{http://www.opengis.net/gml/3.2}locationName"/>
 *           <element ref="{http://www.opengis.net/gml/3.2}locationReference"/>
 *           <element ref="{http://www.opengis.net/gml/3.2}location"/>
 *         </choice>
 *         <element name="speed" type="{http://www.opengis.net/gml/3.2}MeasureType" minOccurs="0"/>
 *         <element name="bearing" type="{http://www.opengis.net/gml/3.2}DirectionPropertyType" minOccurs="0"/>
 *         <element name="acceleration" type="{http://www.opengis.net/gml/3.2}MeasureType" minOccurs="0"/>
 *         <element name="elevation" type="{http://www.opengis.net/gml/3.2}MeasureType" minOccurs="0"/>
 *         <element ref="{http://www.opengis.net/gml/3.2}status" minOccurs="0"/>
 *         <element ref="{http://www.opengis.net/gml/3.2}statusReference" minOccurs="0"/>
 *       </sequence>
 *     </extension>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "MovingObjectStatusType", propOrder = {
    "position",
    "pos",
    "locationName",
    "locationReference",
    "location",
    "speed",
    "bearing",
    "acceleration",
    "elevation",
    "status",
    "statusReference"
})
public class MovingObjectStatusType
    extends AbstractTimeSliceType
    implements ToString
{

    protected GeometryPropertyType position;
    protected DirectPositionType pos;
    /**
     * The gml:locationName property element is a convenience property where the text value describes the location of the feature. If the location names are selected from a controlled list, then the list shall be identified in the codeSpace attribute.
     * 
     */
    protected CodeType locationName;
    /**
     * The gml:locationReference property element is a convenience property where the text value referenced by the xlink:href attribute describes the location of the feature.
     * 
     */
    protected ReferenceType locationReference;
    @XmlElementRef(name = "location", namespace = "http://www.opengis.net/gml/3.2", type = JAXBElement.class, required = false)
    protected JAXBElement<? extends LocationPropertyType> location;
    protected MeasureType speed;
    protected DirectionPropertyType bearing;
    protected MeasureType acceleration;
    protected MeasureType elevation;
    protected StringOrRefType status;
    protected ReferenceType statusReference;

    /**
     * Recupera il valore della proprietà position.
     * 
     * @return
     *     possible object is
     *     {@link GeometryPropertyType }
     *     
     */
    public GeometryPropertyType getPosition() {
        return position;
    }

    /**
     * Imposta il valore della proprietà position.
     * 
     * @param value
     *     allowed object is
     *     {@link GeometryPropertyType }
     *     
     */
    public void setPosition(GeometryPropertyType value) {
        this.position = value;
    }

    public boolean isSetPosition() {
        return (this.position!= null);
    }

    /**
     * Recupera il valore della proprietà pos.
     * 
     * @return
     *     possible object is
     *     {@link DirectPositionType }
     *     
     */
    public DirectPositionType getPos() {
        return pos;
    }

    /**
     * Imposta il valore della proprietà pos.
     * 
     * @param value
     *     allowed object is
     *     {@link DirectPositionType }
     *     
     */
    public void setPos(DirectPositionType value) {
        this.pos = value;
    }

    public boolean isSetPos() {
        return (this.pos!= null);
    }

    /**
     * The gml:locationName property element is a convenience property where the text value describes the location of the feature. If the location names are selected from a controlled list, then the list shall be identified in the codeSpace attribute.
     * 
     * @return
     *     possible object is
     *     {@link CodeType }
     *     
     */
    public CodeType getLocationName() {
        return locationName;
    }

    /**
     * Imposta il valore della proprietà locationName.
     * 
     * @param value
     *     allowed object is
     *     {@link CodeType }
     *     
     * @see #getLocationName()
     */
    public void setLocationName(CodeType value) {
        this.locationName = value;
    }

    public boolean isSetLocationName() {
        return (this.locationName!= null);
    }

    /**
     * The gml:locationReference property element is a convenience property where the text value referenced by the xlink:href attribute describes the location of the feature.
     * 
     * @return
     *     possible object is
     *     {@link ReferenceType }
     *     
     */
    public ReferenceType getLocationReference() {
        return locationReference;
    }

    /**
     * Imposta il valore della proprietà locationReference.
     * 
     * @param value
     *     allowed object is
     *     {@link ReferenceType }
     *     
     * @see #getLocationReference()
     */
    public void setLocationReference(ReferenceType value) {
        this.locationReference = value;
    }

    public boolean isSetLocationReference() {
        return (this.locationReference!= null);
    }

    /**
     * Recupera il valore della proprietà location.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link LocationPropertyType }{@code >}
     *     {@link JAXBElement }{@code <}{@link PriorityLocationPropertyType }{@code >}
     *     
     */
    public JAXBElement<? extends LocationPropertyType> getLocation() {
        return location;
    }

    /**
     * Imposta il valore della proprietà location.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link LocationPropertyType }{@code >}
     *     {@link JAXBElement }{@code <}{@link PriorityLocationPropertyType }{@code >}
     *     
     */
    public void setLocation(JAXBElement<? extends LocationPropertyType> value) {
        this.location = value;
    }

    public boolean isSetLocation() {
        return (this.location!= null);
    }

    /**
     * Recupera il valore della proprietà speed.
     * 
     * @return
     *     possible object is
     *     {@link MeasureType }
     *     
     */
    public MeasureType getSpeed() {
        return speed;
    }

    /**
     * Imposta il valore della proprietà speed.
     * 
     * @param value
     *     allowed object is
     *     {@link MeasureType }
     *     
     */
    public void setSpeed(MeasureType value) {
        this.speed = value;
    }

    public boolean isSetSpeed() {
        return (this.speed!= null);
    }

    /**
     * Recupera il valore della proprietà bearing.
     * 
     * @return
     *     possible object is
     *     {@link DirectionPropertyType }
     *     
     */
    public DirectionPropertyType getBearing() {
        return bearing;
    }

    /**
     * Imposta il valore della proprietà bearing.
     * 
     * @param value
     *     allowed object is
     *     {@link DirectionPropertyType }
     *     
     */
    public void setBearing(DirectionPropertyType value) {
        this.bearing = value;
    }

    public boolean isSetBearing() {
        return (this.bearing!= null);
    }

    /**
     * Recupera il valore della proprietà acceleration.
     * 
     * @return
     *     possible object is
     *     {@link MeasureType }
     *     
     */
    public MeasureType getAcceleration() {
        return acceleration;
    }

    /**
     * Imposta il valore della proprietà acceleration.
     * 
     * @param value
     *     allowed object is
     *     {@link MeasureType }
     *     
     */
    public void setAcceleration(MeasureType value) {
        this.acceleration = value;
    }

    public boolean isSetAcceleration() {
        return (this.acceleration!= null);
    }

    /**
     * Recupera il valore della proprietà elevation.
     * 
     * @return
     *     possible object is
     *     {@link MeasureType }
     *     
     */
    public MeasureType getElevation() {
        return elevation;
    }

    /**
     * Imposta il valore della proprietà elevation.
     * 
     * @param value
     *     allowed object is
     *     {@link MeasureType }
     *     
     */
    public void setElevation(MeasureType value) {
        this.elevation = value;
    }

    public boolean isSetElevation() {
        return (this.elevation!= null);
    }

    /**
     * Recupera il valore della proprietà status.
     * 
     * @return
     *     possible object is
     *     {@link StringOrRefType }
     *     
     */
    public StringOrRefType getStatus() {
        return status;
    }

    /**
     * Imposta il valore della proprietà status.
     * 
     * @param value
     *     allowed object is
     *     {@link StringOrRefType }
     *     
     */
    public void setStatus(StringOrRefType value) {
        this.status = value;
    }

    public boolean isSetStatus() {
        return (this.status!= null);
    }

    /**
     * Recupera il valore della proprietà statusReference.
     * 
     * @return
     *     possible object is
     *     {@link ReferenceType }
     *     
     */
    public ReferenceType getStatusReference() {
        return statusReference;
    }

    /**
     * Imposta il valore della proprietà statusReference.
     * 
     * @param value
     *     allowed object is
     *     {@link ReferenceType }
     *     
     */
    public void setStatusReference(ReferenceType value) {
        this.statusReference = value;
    }

    public boolean isSetStatusReference() {
        return (this.statusReference!= null);
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
            GeometryPropertyType thePosition;
            thePosition = this.getPosition();
            strategy.appendField(locator, this, "position", buffer, thePosition, this.isSetPosition());
        }
        {
            DirectPositionType thePos;
            thePos = this.getPos();
            strategy.appendField(locator, this, "pos", buffer, thePos, this.isSetPos());
        }
        {
            CodeType theLocationName;
            theLocationName = this.getLocationName();
            strategy.appendField(locator, this, "locationName", buffer, theLocationName, this.isSetLocationName());
        }
        {
            ReferenceType theLocationReference;
            theLocationReference = this.getLocationReference();
            strategy.appendField(locator, this, "locationReference", buffer, theLocationReference, this.isSetLocationReference());
        }
        {
            JAXBElement<? extends LocationPropertyType> theLocation;
            theLocation = this.getLocation();
            strategy.appendField(locator, this, "location", buffer, theLocation, this.isSetLocation());
        }
        {
            MeasureType theSpeed;
            theSpeed = this.getSpeed();
            strategy.appendField(locator, this, "speed", buffer, theSpeed, this.isSetSpeed());
        }
        {
            DirectionPropertyType theBearing;
            theBearing = this.getBearing();
            strategy.appendField(locator, this, "bearing", buffer, theBearing, this.isSetBearing());
        }
        {
            MeasureType theAcceleration;
            theAcceleration = this.getAcceleration();
            strategy.appendField(locator, this, "acceleration", buffer, theAcceleration, this.isSetAcceleration());
        }
        {
            MeasureType theElevation;
            theElevation = this.getElevation();
            strategy.appendField(locator, this, "elevation", buffer, theElevation, this.isSetElevation());
        }
        {
            StringOrRefType theStatus;
            theStatus = this.getStatus();
            strategy.appendField(locator, this, "status", buffer, theStatus, this.isSetStatus());
        }
        {
            ReferenceType theStatusReference;
            theStatusReference = this.getStatusReference();
            strategy.appendField(locator, this, "statusReference", buffer, theStatusReference, this.isSetStatusReference());
        }
        return buffer;
    }

}
