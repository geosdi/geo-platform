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
// Generato il: 2026.11.02 alle 03:38:19 PM CET
//

package org.geosdi.geoplatform.xml.gml.v320;

import jakarta.xml.bind.JAXBElement;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElementRef;
import jakarta.xml.bind.annotation.XmlType;
import org.jvnet.jaxb.lang.CopyStrategy;
import org.jvnet.jaxb.lang.CopyTo;
import org.jvnet.jaxb.lang.Equals;
import org.jvnet.jaxb.lang.EqualsStrategy;
import org.jvnet.jaxb.lang.HashCode;
import org.jvnet.jaxb.lang.HashCodeStrategy;
import org.jvnet.jaxb.lang.JAXBCopyStrategy;
import org.jvnet.jaxb.lang.JAXBEqualsStrategy;
import org.jvnet.jaxb.lang.JAXBHashCodeStrategy;
import org.jvnet.jaxb.lang.JAXBMergeStrategy;
import org.jvnet.jaxb.lang.JAXBToStringStrategy;
import org.jvnet.jaxb.lang.MergeFrom;
import org.jvnet.jaxb.lang.MergeStrategy;
import org.jvnet.jaxb.lang.ToString;
import org.jvnet.jaxb.lang.ToStringStrategy;
import org.jvnet.jaxb.locator.ObjectLocator;
import org.jvnet.jaxb.locator.util.LocatorUtils;


/**
 * <p>Classe Java per MovingObjectStatusType complex type.</p>
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.</p>
 * 
 * <pre>{@code
 * <complexType name="MovingObjectStatusType">
 *   <complexContent>
 *     <extension base="{http://www.opengis.net/gml}AbstractTimeSliceType">
 *       <sequence>
 *         <choice>
 *           <element name="position" type="{http://www.opengis.net/gml}GeometryPropertyType"/>
 *           <element ref="{http://www.opengis.net/gml}pos"/>
 *           <element ref="{http://www.opengis.net/gml}locationName"/>
 *           <element ref="{http://www.opengis.net/gml}locationReference"/>
 *           <element ref="{http://www.opengis.net/gml}location"/>
 *         </choice>
 *         <element name="speed" type="{http://www.opengis.net/gml}MeasureType" minOccurs="0"/>
 *         <element name="bearing" type="{http://www.opengis.net/gml}DirectionPropertyType" minOccurs="0"/>
 *         <element name="acceleration" type="{http://www.opengis.net/gml}MeasureType" minOccurs="0"/>
 *         <element name="elevation" type="{http://www.opengis.net/gml}MeasureType" minOccurs="0"/>
 *         <element ref="{http://www.opengis.net/gml}status" minOccurs="0"/>
 *         <element ref="{http://www.opengis.net/gml}statusReference" minOccurs="0"/>
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
    implements Cloneable, CopyTo, Equals, HashCode, MergeFrom, ToString
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
    @XmlElementRef(name = "location", namespace = "http://www.opengis.net/gml", type = JAXBElement.class, required = false)
    protected JAXBElement<? extends LocationPropertyType> location;
    protected MeasureType speed;
    protected DirectionPropertyType bearing;
    protected MeasureType acceleration;
    protected MeasureType elevation;
    /**
     * The remote link attributes of the gml:status element have been deprecated along with its current type.
     * 
     */
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
     * The remote link attributes of the gml:status element have been deprecated along with its current type.
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
     * @see #getStatus()
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

    @Override
    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if ((object == null)||(this.getClass()!= object.getClass())) {
            return false;
        }
        if (this == object) {
            return true;
        }
        if (!super.equals(thisLocator, thatLocator, object, strategy)) {
            return false;
        }
        final MovingObjectStatusType that = ((MovingObjectStatusType) object);
        {
            GeometryPropertyType lhsPosition;
            lhsPosition = this.getPosition();
            GeometryPropertyType rhsPosition;
            rhsPosition = that.getPosition();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "position", lhsPosition), LocatorUtils.property(thatLocator, "position", rhsPosition), lhsPosition, rhsPosition, this.isSetPosition(), that.isSetPosition())) {
                return false;
            }
        }
        {
            DirectPositionType lhsPos;
            lhsPos = this.getPos();
            DirectPositionType rhsPos;
            rhsPos = that.getPos();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "pos", lhsPos), LocatorUtils.property(thatLocator, "pos", rhsPos), lhsPos, rhsPos, this.isSetPos(), that.isSetPos())) {
                return false;
            }
        }
        {
            CodeType lhsLocationName;
            lhsLocationName = this.getLocationName();
            CodeType rhsLocationName;
            rhsLocationName = that.getLocationName();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "locationName", lhsLocationName), LocatorUtils.property(thatLocator, "locationName", rhsLocationName), lhsLocationName, rhsLocationName, this.isSetLocationName(), that.isSetLocationName())) {
                return false;
            }
        }
        {
            ReferenceType lhsLocationReference;
            lhsLocationReference = this.getLocationReference();
            ReferenceType rhsLocationReference;
            rhsLocationReference = that.getLocationReference();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "locationReference", lhsLocationReference), LocatorUtils.property(thatLocator, "locationReference", rhsLocationReference), lhsLocationReference, rhsLocationReference, this.isSetLocationReference(), that.isSetLocationReference())) {
                return false;
            }
        }
        {
            JAXBElement<? extends LocationPropertyType> lhsLocation;
            lhsLocation = this.getLocation();
            JAXBElement<? extends LocationPropertyType> rhsLocation;
            rhsLocation = that.getLocation();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "location", lhsLocation), LocatorUtils.property(thatLocator, "location", rhsLocation), lhsLocation, rhsLocation, this.isSetLocation(), that.isSetLocation())) {
                return false;
            }
        }
        {
            MeasureType lhsSpeed;
            lhsSpeed = this.getSpeed();
            MeasureType rhsSpeed;
            rhsSpeed = that.getSpeed();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "speed", lhsSpeed), LocatorUtils.property(thatLocator, "speed", rhsSpeed), lhsSpeed, rhsSpeed, this.isSetSpeed(), that.isSetSpeed())) {
                return false;
            }
        }
        {
            DirectionPropertyType lhsBearing;
            lhsBearing = this.getBearing();
            DirectionPropertyType rhsBearing;
            rhsBearing = that.getBearing();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "bearing", lhsBearing), LocatorUtils.property(thatLocator, "bearing", rhsBearing), lhsBearing, rhsBearing, this.isSetBearing(), that.isSetBearing())) {
                return false;
            }
        }
        {
            MeasureType lhsAcceleration;
            lhsAcceleration = this.getAcceleration();
            MeasureType rhsAcceleration;
            rhsAcceleration = that.getAcceleration();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "acceleration", lhsAcceleration), LocatorUtils.property(thatLocator, "acceleration", rhsAcceleration), lhsAcceleration, rhsAcceleration, this.isSetAcceleration(), that.isSetAcceleration())) {
                return false;
            }
        }
        {
            MeasureType lhsElevation;
            lhsElevation = this.getElevation();
            MeasureType rhsElevation;
            rhsElevation = that.getElevation();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "elevation", lhsElevation), LocatorUtils.property(thatLocator, "elevation", rhsElevation), lhsElevation, rhsElevation, this.isSetElevation(), that.isSetElevation())) {
                return false;
            }
        }
        {
            StringOrRefType lhsStatus;
            lhsStatus = this.getStatus();
            StringOrRefType rhsStatus;
            rhsStatus = that.getStatus();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "status", lhsStatus), LocatorUtils.property(thatLocator, "status", rhsStatus), lhsStatus, rhsStatus, this.isSetStatus(), that.isSetStatus())) {
                return false;
            }
        }
        {
            ReferenceType lhsStatusReference;
            lhsStatusReference = this.getStatusReference();
            ReferenceType rhsStatusReference;
            rhsStatusReference = that.getStatusReference();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "statusReference", lhsStatusReference), LocatorUtils.property(thatLocator, "statusReference", rhsStatusReference), lhsStatusReference, rhsStatusReference, this.isSetStatusReference(), that.isSetStatusReference())) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean equals(Object object) {
        final EqualsStrategy strategy = JAXBEqualsStrategy.getInstance();
        return equals(null, null, object, strategy);
    }

    @Override
    public int hashCode(ObjectLocator locator, HashCodeStrategy strategy) {
        int currentHashCode = super.hashCode(locator, strategy);
        {
            GeometryPropertyType thePosition;
            thePosition = this.getPosition();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "position", thePosition), currentHashCode, thePosition, this.isSetPosition());
        }
        {
            DirectPositionType thePos;
            thePos = this.getPos();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "pos", thePos), currentHashCode, thePos, this.isSetPos());
        }
        {
            CodeType theLocationName;
            theLocationName = this.getLocationName();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "locationName", theLocationName), currentHashCode, theLocationName, this.isSetLocationName());
        }
        {
            ReferenceType theLocationReference;
            theLocationReference = this.getLocationReference();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "locationReference", theLocationReference), currentHashCode, theLocationReference, this.isSetLocationReference());
        }
        {
            JAXBElement<? extends LocationPropertyType> theLocation;
            theLocation = this.getLocation();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "location", theLocation), currentHashCode, theLocation, this.isSetLocation());
        }
        {
            MeasureType theSpeed;
            theSpeed = this.getSpeed();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "speed", theSpeed), currentHashCode, theSpeed, this.isSetSpeed());
        }
        {
            DirectionPropertyType theBearing;
            theBearing = this.getBearing();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "bearing", theBearing), currentHashCode, theBearing, this.isSetBearing());
        }
        {
            MeasureType theAcceleration;
            theAcceleration = this.getAcceleration();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "acceleration", theAcceleration), currentHashCode, theAcceleration, this.isSetAcceleration());
        }
        {
            MeasureType theElevation;
            theElevation = this.getElevation();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "elevation", theElevation), currentHashCode, theElevation, this.isSetElevation());
        }
        {
            StringOrRefType theStatus;
            theStatus = this.getStatus();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "status", theStatus), currentHashCode, theStatus, this.isSetStatus());
        }
        {
            ReferenceType theStatusReference;
            theStatusReference = this.getStatusReference();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "statusReference", theStatusReference), currentHashCode, theStatusReference, this.isSetStatusReference());
        }
        return currentHashCode;
    }

    @Override
    public int hashCode() {
        final HashCodeStrategy strategy = JAXBHashCodeStrategy.getInstance();
        return this.hashCode(null, strategy);
    }

    @Override
    public Object clone() {
        return copyTo(createNewInstance());
    }

    @Override
    public Object copyTo(Object target) {
        final CopyStrategy strategy = JAXBCopyStrategy.getInstance();
        return copyTo(null, target, strategy);
    }

    @Override
    public Object copyTo(ObjectLocator locator, Object target, CopyStrategy strategy) {
        final Object draftCopy = ((target == null)?createNewInstance():target);
        super.copyTo(locator, draftCopy, strategy);
        if (draftCopy instanceof MovingObjectStatusType) {
            final MovingObjectStatusType copy = ((MovingObjectStatusType) draftCopy);
            {
                java.lang.Boolean positionShouldBeCopiedAndSet = strategy.shouldBeCopiedAndSet(locator, this.isSetPosition());
                if (positionShouldBeCopiedAndSet == java.lang.Boolean.TRUE) {
                    GeometryPropertyType sourcePosition;
                    sourcePosition = this.getPosition();
                    GeometryPropertyType copyPosition = ((GeometryPropertyType) strategy.copy(LocatorUtils.property(locator, "position", sourcePosition), sourcePosition, this.isSetPosition()));
                    copy.setPosition(copyPosition);
                } else {
                    if (positionShouldBeCopiedAndSet == java.lang.Boolean.FALSE) {
                        copy.position = null;
                    }
                }
            }
            {
                java.lang.Boolean posShouldBeCopiedAndSet = strategy.shouldBeCopiedAndSet(locator, this.isSetPos());
                if (posShouldBeCopiedAndSet == java.lang.Boolean.TRUE) {
                    DirectPositionType sourcePos;
                    sourcePos = this.getPos();
                    DirectPositionType copyPos = ((DirectPositionType) strategy.copy(LocatorUtils.property(locator, "pos", sourcePos), sourcePos, this.isSetPos()));
                    copy.setPos(copyPos);
                } else {
                    if (posShouldBeCopiedAndSet == java.lang.Boolean.FALSE) {
                        copy.pos = null;
                    }
                }
            }
            {
                java.lang.Boolean locationNameShouldBeCopiedAndSet = strategy.shouldBeCopiedAndSet(locator, this.isSetLocationName());
                if (locationNameShouldBeCopiedAndSet == java.lang.Boolean.TRUE) {
                    CodeType sourceLocationName;
                    sourceLocationName = this.getLocationName();
                    CodeType copyLocationName = ((CodeType) strategy.copy(LocatorUtils.property(locator, "locationName", sourceLocationName), sourceLocationName, this.isSetLocationName()));
                    copy.setLocationName(copyLocationName);
                } else {
                    if (locationNameShouldBeCopiedAndSet == java.lang.Boolean.FALSE) {
                        copy.locationName = null;
                    }
                }
            }
            {
                java.lang.Boolean locationReferenceShouldBeCopiedAndSet = strategy.shouldBeCopiedAndSet(locator, this.isSetLocationReference());
                if (locationReferenceShouldBeCopiedAndSet == java.lang.Boolean.TRUE) {
                    ReferenceType sourceLocationReference;
                    sourceLocationReference = this.getLocationReference();
                    ReferenceType copyLocationReference = ((ReferenceType) strategy.copy(LocatorUtils.property(locator, "locationReference", sourceLocationReference), sourceLocationReference, this.isSetLocationReference()));
                    copy.setLocationReference(copyLocationReference);
                } else {
                    if (locationReferenceShouldBeCopiedAndSet == java.lang.Boolean.FALSE) {
                        copy.locationReference = null;
                    }
                }
            }
            {
                java.lang.Boolean locationShouldBeCopiedAndSet = strategy.shouldBeCopiedAndSet(locator, this.isSetLocation());
                if (locationShouldBeCopiedAndSet == java.lang.Boolean.TRUE) {
                    JAXBElement<? extends LocationPropertyType> sourceLocation;
                    sourceLocation = this.getLocation();
                    @SuppressWarnings("unchecked")
                    JAXBElement<? extends LocationPropertyType> copyLocation = ((JAXBElement<? extends LocationPropertyType> ) strategy.copy(LocatorUtils.property(locator, "location", sourceLocation), sourceLocation, this.isSetLocation()));
                    copy.setLocation(copyLocation);
                } else {
                    if (locationShouldBeCopiedAndSet == java.lang.Boolean.FALSE) {
                        copy.location = null;
                    }
                }
            }
            {
                java.lang.Boolean speedShouldBeCopiedAndSet = strategy.shouldBeCopiedAndSet(locator, this.isSetSpeed());
                if (speedShouldBeCopiedAndSet == java.lang.Boolean.TRUE) {
                    MeasureType sourceSpeed;
                    sourceSpeed = this.getSpeed();
                    MeasureType copySpeed = ((MeasureType) strategy.copy(LocatorUtils.property(locator, "speed", sourceSpeed), sourceSpeed, this.isSetSpeed()));
                    copy.setSpeed(copySpeed);
                } else {
                    if (speedShouldBeCopiedAndSet == java.lang.Boolean.FALSE) {
                        copy.speed = null;
                    }
                }
            }
            {
                java.lang.Boolean bearingShouldBeCopiedAndSet = strategy.shouldBeCopiedAndSet(locator, this.isSetBearing());
                if (bearingShouldBeCopiedAndSet == java.lang.Boolean.TRUE) {
                    DirectionPropertyType sourceBearing;
                    sourceBearing = this.getBearing();
                    DirectionPropertyType copyBearing = ((DirectionPropertyType) strategy.copy(LocatorUtils.property(locator, "bearing", sourceBearing), sourceBearing, this.isSetBearing()));
                    copy.setBearing(copyBearing);
                } else {
                    if (bearingShouldBeCopiedAndSet == java.lang.Boolean.FALSE) {
                        copy.bearing = null;
                    }
                }
            }
            {
                java.lang.Boolean accelerationShouldBeCopiedAndSet = strategy.shouldBeCopiedAndSet(locator, this.isSetAcceleration());
                if (accelerationShouldBeCopiedAndSet == java.lang.Boolean.TRUE) {
                    MeasureType sourceAcceleration;
                    sourceAcceleration = this.getAcceleration();
                    MeasureType copyAcceleration = ((MeasureType) strategy.copy(LocatorUtils.property(locator, "acceleration", sourceAcceleration), sourceAcceleration, this.isSetAcceleration()));
                    copy.setAcceleration(copyAcceleration);
                } else {
                    if (accelerationShouldBeCopiedAndSet == java.lang.Boolean.FALSE) {
                        copy.acceleration = null;
                    }
                }
            }
            {
                java.lang.Boolean elevationShouldBeCopiedAndSet = strategy.shouldBeCopiedAndSet(locator, this.isSetElevation());
                if (elevationShouldBeCopiedAndSet == java.lang.Boolean.TRUE) {
                    MeasureType sourceElevation;
                    sourceElevation = this.getElevation();
                    MeasureType copyElevation = ((MeasureType) strategy.copy(LocatorUtils.property(locator, "elevation", sourceElevation), sourceElevation, this.isSetElevation()));
                    copy.setElevation(copyElevation);
                } else {
                    if (elevationShouldBeCopiedAndSet == java.lang.Boolean.FALSE) {
                        copy.elevation = null;
                    }
                }
            }
            {
                java.lang.Boolean statusShouldBeCopiedAndSet = strategy.shouldBeCopiedAndSet(locator, this.isSetStatus());
                if (statusShouldBeCopiedAndSet == java.lang.Boolean.TRUE) {
                    StringOrRefType sourceStatus;
                    sourceStatus = this.getStatus();
                    StringOrRefType copyStatus = ((StringOrRefType) strategy.copy(LocatorUtils.property(locator, "status", sourceStatus), sourceStatus, this.isSetStatus()));
                    copy.setStatus(copyStatus);
                } else {
                    if (statusShouldBeCopiedAndSet == java.lang.Boolean.FALSE) {
                        copy.status = null;
                    }
                }
            }
            {
                java.lang.Boolean statusReferenceShouldBeCopiedAndSet = strategy.shouldBeCopiedAndSet(locator, this.isSetStatusReference());
                if (statusReferenceShouldBeCopiedAndSet == java.lang.Boolean.TRUE) {
                    ReferenceType sourceStatusReference;
                    sourceStatusReference = this.getStatusReference();
                    ReferenceType copyStatusReference = ((ReferenceType) strategy.copy(LocatorUtils.property(locator, "statusReference", sourceStatusReference), sourceStatusReference, this.isSetStatusReference()));
                    copy.setStatusReference(copyStatusReference);
                } else {
                    if (statusReferenceShouldBeCopiedAndSet == java.lang.Boolean.FALSE) {
                        copy.statusReference = null;
                    }
                }
            }
        }
        return draftCopy;
    }

    @Override
    public Object createNewInstance() {
        return new MovingObjectStatusType();
    }

    @Override
    public void mergeFrom(Object left, Object right) {
        final MergeStrategy strategy = JAXBMergeStrategy.getInstance();
        mergeFrom(null, null, left, right, strategy);
    }

    @Override
    public void mergeFrom(ObjectLocator leftLocator, ObjectLocator rightLocator, Object left, Object right, MergeStrategy strategy) {
        super.mergeFrom(leftLocator, rightLocator, left, right, strategy);
        if (right instanceof MovingObjectStatusType) {
            final MovingObjectStatusType target = this;
            final MovingObjectStatusType leftObject = ((MovingObjectStatusType) left);
            final MovingObjectStatusType rightObject = ((MovingObjectStatusType) right);
            {
                java.lang.Boolean positionShouldBeMergedAndSet = strategy.shouldBeMergedAndSet(leftLocator, rightLocator, leftObject.isSetPosition(), rightObject.isSetPosition());
                if (positionShouldBeMergedAndSet == java.lang.Boolean.TRUE) {
                    GeometryPropertyType lhsPosition;
                    lhsPosition = leftObject.getPosition();
                    GeometryPropertyType rhsPosition;
                    rhsPosition = rightObject.getPosition();
                    GeometryPropertyType mergedPosition = ((GeometryPropertyType) strategy.merge(LocatorUtils.property(leftLocator, "position", lhsPosition), LocatorUtils.property(rightLocator, "position", rhsPosition), lhsPosition, rhsPosition, leftObject.isSetPosition(), rightObject.isSetPosition()));
                    target.setPosition(mergedPosition);
                } else {
                    if (positionShouldBeMergedAndSet == java.lang.Boolean.FALSE) {
                        target.position = null;
                    }
                }
            }
            {
                java.lang.Boolean posShouldBeMergedAndSet = strategy.shouldBeMergedAndSet(leftLocator, rightLocator, leftObject.isSetPos(), rightObject.isSetPos());
                if (posShouldBeMergedAndSet == java.lang.Boolean.TRUE) {
                    DirectPositionType lhsPos;
                    lhsPos = leftObject.getPos();
                    DirectPositionType rhsPos;
                    rhsPos = rightObject.getPos();
                    DirectPositionType mergedPos = ((DirectPositionType) strategy.merge(LocatorUtils.property(leftLocator, "pos", lhsPos), LocatorUtils.property(rightLocator, "pos", rhsPos), lhsPos, rhsPos, leftObject.isSetPos(), rightObject.isSetPos()));
                    target.setPos(mergedPos);
                } else {
                    if (posShouldBeMergedAndSet == java.lang.Boolean.FALSE) {
                        target.pos = null;
                    }
                }
            }
            {
                java.lang.Boolean locationNameShouldBeMergedAndSet = strategy.shouldBeMergedAndSet(leftLocator, rightLocator, leftObject.isSetLocationName(), rightObject.isSetLocationName());
                if (locationNameShouldBeMergedAndSet == java.lang.Boolean.TRUE) {
                    CodeType lhsLocationName;
                    lhsLocationName = leftObject.getLocationName();
                    CodeType rhsLocationName;
                    rhsLocationName = rightObject.getLocationName();
                    CodeType mergedLocationName = ((CodeType) strategy.merge(LocatorUtils.property(leftLocator, "locationName", lhsLocationName), LocatorUtils.property(rightLocator, "locationName", rhsLocationName), lhsLocationName, rhsLocationName, leftObject.isSetLocationName(), rightObject.isSetLocationName()));
                    target.setLocationName(mergedLocationName);
                } else {
                    if (locationNameShouldBeMergedAndSet == java.lang.Boolean.FALSE) {
                        target.locationName = null;
                    }
                }
            }
            {
                java.lang.Boolean locationReferenceShouldBeMergedAndSet = strategy.shouldBeMergedAndSet(leftLocator, rightLocator, leftObject.isSetLocationReference(), rightObject.isSetLocationReference());
                if (locationReferenceShouldBeMergedAndSet == java.lang.Boolean.TRUE) {
                    ReferenceType lhsLocationReference;
                    lhsLocationReference = leftObject.getLocationReference();
                    ReferenceType rhsLocationReference;
                    rhsLocationReference = rightObject.getLocationReference();
                    ReferenceType mergedLocationReference = ((ReferenceType) strategy.merge(LocatorUtils.property(leftLocator, "locationReference", lhsLocationReference), LocatorUtils.property(rightLocator, "locationReference", rhsLocationReference), lhsLocationReference, rhsLocationReference, leftObject.isSetLocationReference(), rightObject.isSetLocationReference()));
                    target.setLocationReference(mergedLocationReference);
                } else {
                    if (locationReferenceShouldBeMergedAndSet == java.lang.Boolean.FALSE) {
                        target.locationReference = null;
                    }
                }
            }
            {
                java.lang.Boolean locationShouldBeMergedAndSet = strategy.shouldBeMergedAndSet(leftLocator, rightLocator, leftObject.isSetLocation(), rightObject.isSetLocation());
                if (locationShouldBeMergedAndSet == java.lang.Boolean.TRUE) {
                    JAXBElement<? extends LocationPropertyType> lhsLocation;
                    lhsLocation = leftObject.getLocation();
                    JAXBElement<? extends LocationPropertyType> rhsLocation;
                    rhsLocation = rightObject.getLocation();
                    JAXBElement<? extends LocationPropertyType> mergedLocation = ((JAXBElement<? extends LocationPropertyType> ) strategy.merge(LocatorUtils.property(leftLocator, "location", lhsLocation), LocatorUtils.property(rightLocator, "location", rhsLocation), lhsLocation, rhsLocation, leftObject.isSetLocation(), rightObject.isSetLocation()));
                    target.setLocation(mergedLocation);
                } else {
                    if (locationShouldBeMergedAndSet == java.lang.Boolean.FALSE) {
                        target.location = null;
                    }
                }
            }
            {
                java.lang.Boolean speedShouldBeMergedAndSet = strategy.shouldBeMergedAndSet(leftLocator, rightLocator, leftObject.isSetSpeed(), rightObject.isSetSpeed());
                if (speedShouldBeMergedAndSet == java.lang.Boolean.TRUE) {
                    MeasureType lhsSpeed;
                    lhsSpeed = leftObject.getSpeed();
                    MeasureType rhsSpeed;
                    rhsSpeed = rightObject.getSpeed();
                    MeasureType mergedSpeed = ((MeasureType) strategy.merge(LocatorUtils.property(leftLocator, "speed", lhsSpeed), LocatorUtils.property(rightLocator, "speed", rhsSpeed), lhsSpeed, rhsSpeed, leftObject.isSetSpeed(), rightObject.isSetSpeed()));
                    target.setSpeed(mergedSpeed);
                } else {
                    if (speedShouldBeMergedAndSet == java.lang.Boolean.FALSE) {
                        target.speed = null;
                    }
                }
            }
            {
                java.lang.Boolean bearingShouldBeMergedAndSet = strategy.shouldBeMergedAndSet(leftLocator, rightLocator, leftObject.isSetBearing(), rightObject.isSetBearing());
                if (bearingShouldBeMergedAndSet == java.lang.Boolean.TRUE) {
                    DirectionPropertyType lhsBearing;
                    lhsBearing = leftObject.getBearing();
                    DirectionPropertyType rhsBearing;
                    rhsBearing = rightObject.getBearing();
                    DirectionPropertyType mergedBearing = ((DirectionPropertyType) strategy.merge(LocatorUtils.property(leftLocator, "bearing", lhsBearing), LocatorUtils.property(rightLocator, "bearing", rhsBearing), lhsBearing, rhsBearing, leftObject.isSetBearing(), rightObject.isSetBearing()));
                    target.setBearing(mergedBearing);
                } else {
                    if (bearingShouldBeMergedAndSet == java.lang.Boolean.FALSE) {
                        target.bearing = null;
                    }
                }
            }
            {
                java.lang.Boolean accelerationShouldBeMergedAndSet = strategy.shouldBeMergedAndSet(leftLocator, rightLocator, leftObject.isSetAcceleration(), rightObject.isSetAcceleration());
                if (accelerationShouldBeMergedAndSet == java.lang.Boolean.TRUE) {
                    MeasureType lhsAcceleration;
                    lhsAcceleration = leftObject.getAcceleration();
                    MeasureType rhsAcceleration;
                    rhsAcceleration = rightObject.getAcceleration();
                    MeasureType mergedAcceleration = ((MeasureType) strategy.merge(LocatorUtils.property(leftLocator, "acceleration", lhsAcceleration), LocatorUtils.property(rightLocator, "acceleration", rhsAcceleration), lhsAcceleration, rhsAcceleration, leftObject.isSetAcceleration(), rightObject.isSetAcceleration()));
                    target.setAcceleration(mergedAcceleration);
                } else {
                    if (accelerationShouldBeMergedAndSet == java.lang.Boolean.FALSE) {
                        target.acceleration = null;
                    }
                }
            }
            {
                java.lang.Boolean elevationShouldBeMergedAndSet = strategy.shouldBeMergedAndSet(leftLocator, rightLocator, leftObject.isSetElevation(), rightObject.isSetElevation());
                if (elevationShouldBeMergedAndSet == java.lang.Boolean.TRUE) {
                    MeasureType lhsElevation;
                    lhsElevation = leftObject.getElevation();
                    MeasureType rhsElevation;
                    rhsElevation = rightObject.getElevation();
                    MeasureType mergedElevation = ((MeasureType) strategy.merge(LocatorUtils.property(leftLocator, "elevation", lhsElevation), LocatorUtils.property(rightLocator, "elevation", rhsElevation), lhsElevation, rhsElevation, leftObject.isSetElevation(), rightObject.isSetElevation()));
                    target.setElevation(mergedElevation);
                } else {
                    if (elevationShouldBeMergedAndSet == java.lang.Boolean.FALSE) {
                        target.elevation = null;
                    }
                }
            }
            {
                java.lang.Boolean statusShouldBeMergedAndSet = strategy.shouldBeMergedAndSet(leftLocator, rightLocator, leftObject.isSetStatus(), rightObject.isSetStatus());
                if (statusShouldBeMergedAndSet == java.lang.Boolean.TRUE) {
                    StringOrRefType lhsStatus;
                    lhsStatus = leftObject.getStatus();
                    StringOrRefType rhsStatus;
                    rhsStatus = rightObject.getStatus();
                    StringOrRefType mergedStatus = ((StringOrRefType) strategy.merge(LocatorUtils.property(leftLocator, "status", lhsStatus), LocatorUtils.property(rightLocator, "status", rhsStatus), lhsStatus, rhsStatus, leftObject.isSetStatus(), rightObject.isSetStatus()));
                    target.setStatus(mergedStatus);
                } else {
                    if (statusShouldBeMergedAndSet == java.lang.Boolean.FALSE) {
                        target.status = null;
                    }
                }
            }
            {
                java.lang.Boolean statusReferenceShouldBeMergedAndSet = strategy.shouldBeMergedAndSet(leftLocator, rightLocator, leftObject.isSetStatusReference(), rightObject.isSetStatusReference());
                if (statusReferenceShouldBeMergedAndSet == java.lang.Boolean.TRUE) {
                    ReferenceType lhsStatusReference;
                    lhsStatusReference = leftObject.getStatusReference();
                    ReferenceType rhsStatusReference;
                    rhsStatusReference = rightObject.getStatusReference();
                    ReferenceType mergedStatusReference = ((ReferenceType) strategy.merge(LocatorUtils.property(leftLocator, "statusReference", lhsStatusReference), LocatorUtils.property(rightLocator, "statusReference", rhsStatusReference), lhsStatusReference, rhsStatusReference, leftObject.isSetStatusReference(), rightObject.isSetStatusReference()));
                    target.setStatusReference(mergedStatusReference);
                } else {
                    if (statusReferenceShouldBeMergedAndSet == java.lang.Boolean.FALSE) {
                        target.statusReference = null;
                    }
                }
            }
        }
    }

}
