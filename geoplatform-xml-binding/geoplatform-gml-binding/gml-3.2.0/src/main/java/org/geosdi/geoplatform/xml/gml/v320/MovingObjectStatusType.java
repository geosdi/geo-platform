/**
 *
 *    geo-platform
 *    Rich webgis framework
 *    http://geo-platform.org
 *   ====================================================================
 *
 *   Copyright (C) 2008-2021 geoSDI Group (CNR IMAA - Potenza - ITALY).
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
// Questo file è stato generato dall'architettura JavaTM per XML Binding (JAXB) Reference Implementation, v2.2.7 
// Vedere <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Qualsiasi modifica a questo file andrà persa durante la ricompilazione dello schema di origine. 
// Generato il: 2015.06.18 alle 12:52:42 AM CEST 
//


package org.geosdi.geoplatform.xml.gml.v320;

import java.util.Collection;
import java.util.List;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;
import org.jvnet.jaxb2_commons.lang.CopyStrategy;
import org.jvnet.jaxb2_commons.lang.CopyTo;
import org.jvnet.jaxb2_commons.lang.Equals;
import org.jvnet.jaxb2_commons.lang.EqualsStrategy;
import org.jvnet.jaxb2_commons.lang.HashCode;
import org.jvnet.jaxb2_commons.lang.HashCodeStrategy;
import org.jvnet.jaxb2_commons.lang.JAXBCopyStrategy;
import org.jvnet.jaxb2_commons.lang.JAXBEqualsStrategy;
import org.jvnet.jaxb2_commons.lang.JAXBHashCodeStrategy;
import org.jvnet.jaxb2_commons.lang.JAXBMergeStrategy;
import org.jvnet.jaxb2_commons.lang.JAXBToStringStrategy;
import org.jvnet.jaxb2_commons.lang.MergeFrom;
import org.jvnet.jaxb2_commons.lang.MergeStrategy;
import org.jvnet.jaxb2_commons.lang.ToString;
import org.jvnet.jaxb2_commons.lang.ToStringStrategy;
import org.jvnet.jaxb2_commons.locator.ObjectLocator;
import org.jvnet.jaxb2_commons.locator.util.LocatorUtils;


/**
 * <p>Classe Java per MovingObjectStatusType complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="MovingObjectStatusType">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.opengis.net/gml}AbstractTimeSliceType">
 *       &lt;sequence>
 *         &lt;choice>
 *           &lt;element name="position" type="{http://www.opengis.net/gml}GeometryPropertyType"/>
 *           &lt;element ref="{http://www.opengis.net/gml}pos"/>
 *           &lt;element ref="{http://www.opengis.net/gml}locationName"/>
 *           &lt;element ref="{http://www.opengis.net/gml}locationReference"/>
 *           &lt;element ref="{http://www.opengis.net/gml}location"/>
 *         &lt;/choice>
 *         &lt;element name="speed" type="{http://www.opengis.net/gml}MeasureType" minOccurs="0"/>
 *         &lt;element name="bearing" type="{http://www.opengis.net/gml}DirectionPropertyType" minOccurs="0"/>
 *         &lt;element name="acceleration" type="{http://www.opengis.net/gml}MeasureType" minOccurs="0"/>
 *         &lt;element name="elevation" type="{http://www.opengis.net/gml}MeasureType" minOccurs="0"/>
 *         &lt;element ref="{http://www.opengis.net/gml}status" minOccurs="0"/>
 *         &lt;element ref="{http://www.opengis.net/gml}statusReference" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
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
    protected CodeType locationName;
    protected ReferenceType locationReference;
    @XmlElementRef(name = "location", namespace = "http://www.opengis.net/gml", type = JAXBElement.class, required = false)
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
     * Recupera il valore della proprietà locationName.
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
     */
    public void setLocationName(CodeType value) {
        this.locationName = value;
    }

    public boolean isSetLocationName() {
        return (this.locationName!= null);
    }

    /**
     * Recupera il valore della proprietà locationReference.
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
            GeometryPropertyType thePosition;
            thePosition = this.getPosition();
            strategy.appendField(locator, this, "position", buffer, thePosition);
        }
        {
            DirectPositionType thePos;
            thePos = this.getPos();
            strategy.appendField(locator, this, "pos", buffer, thePos);
        }
        {
            CodeType theLocationName;
            theLocationName = this.getLocationName();
            strategy.appendField(locator, this, "locationName", buffer, theLocationName);
        }
        {
            ReferenceType theLocationReference;
            theLocationReference = this.getLocationReference();
            strategy.appendField(locator, this, "locationReference", buffer, theLocationReference);
        }
        {
            JAXBElement<? extends LocationPropertyType> theLocation;
            theLocation = this.getLocation();
            strategy.appendField(locator, this, "location", buffer, theLocation);
        }
        {
            MeasureType theSpeed;
            theSpeed = this.getSpeed();
            strategy.appendField(locator, this, "speed", buffer, theSpeed);
        }
        {
            DirectionPropertyType theBearing;
            theBearing = this.getBearing();
            strategy.appendField(locator, this, "bearing", buffer, theBearing);
        }
        {
            MeasureType theAcceleration;
            theAcceleration = this.getAcceleration();
            strategy.appendField(locator, this, "acceleration", buffer, theAcceleration);
        }
        {
            MeasureType theElevation;
            theElevation = this.getElevation();
            strategy.appendField(locator, this, "elevation", buffer, theElevation);
        }
        {
            StringOrRefType theStatus;
            theStatus = this.getStatus();
            strategy.appendField(locator, this, "status", buffer, theStatus);
        }
        {
            ReferenceType theStatusReference;
            theStatusReference = this.getStatusReference();
            strategy.appendField(locator, this, "statusReference", buffer, theStatusReference);
        }
        return buffer;
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof MovingObjectStatusType)) {
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
            if (!strategy.equals(LocatorUtils.property(thisLocator, "position", lhsPosition), LocatorUtils.property(thatLocator, "position", rhsPosition), lhsPosition, rhsPosition)) {
                return false;
            }
        }
        {
            DirectPositionType lhsPos;
            lhsPos = this.getPos();
            DirectPositionType rhsPos;
            rhsPos = that.getPos();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "pos", lhsPos), LocatorUtils.property(thatLocator, "pos", rhsPos), lhsPos, rhsPos)) {
                return false;
            }
        }
        {
            CodeType lhsLocationName;
            lhsLocationName = this.getLocationName();
            CodeType rhsLocationName;
            rhsLocationName = that.getLocationName();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "locationName", lhsLocationName), LocatorUtils.property(thatLocator, "locationName", rhsLocationName), lhsLocationName, rhsLocationName)) {
                return false;
            }
        }
        {
            ReferenceType lhsLocationReference;
            lhsLocationReference = this.getLocationReference();
            ReferenceType rhsLocationReference;
            rhsLocationReference = that.getLocationReference();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "locationReference", lhsLocationReference), LocatorUtils.property(thatLocator, "locationReference", rhsLocationReference), lhsLocationReference, rhsLocationReference)) {
                return false;
            }
        }
        {
            JAXBElement<? extends LocationPropertyType> lhsLocation;
            lhsLocation = this.getLocation();
            JAXBElement<? extends LocationPropertyType> rhsLocation;
            rhsLocation = that.getLocation();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "location", lhsLocation), LocatorUtils.property(thatLocator, "location", rhsLocation), lhsLocation, rhsLocation)) {
                return false;
            }
        }
        {
            MeasureType lhsSpeed;
            lhsSpeed = this.getSpeed();
            MeasureType rhsSpeed;
            rhsSpeed = that.getSpeed();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "speed", lhsSpeed), LocatorUtils.property(thatLocator, "speed", rhsSpeed), lhsSpeed, rhsSpeed)) {
                return false;
            }
        }
        {
            DirectionPropertyType lhsBearing;
            lhsBearing = this.getBearing();
            DirectionPropertyType rhsBearing;
            rhsBearing = that.getBearing();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "bearing", lhsBearing), LocatorUtils.property(thatLocator, "bearing", rhsBearing), lhsBearing, rhsBearing)) {
                return false;
            }
        }
        {
            MeasureType lhsAcceleration;
            lhsAcceleration = this.getAcceleration();
            MeasureType rhsAcceleration;
            rhsAcceleration = that.getAcceleration();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "acceleration", lhsAcceleration), LocatorUtils.property(thatLocator, "acceleration", rhsAcceleration), lhsAcceleration, rhsAcceleration)) {
                return false;
            }
        }
        {
            MeasureType lhsElevation;
            lhsElevation = this.getElevation();
            MeasureType rhsElevation;
            rhsElevation = that.getElevation();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "elevation", lhsElevation), LocatorUtils.property(thatLocator, "elevation", rhsElevation), lhsElevation, rhsElevation)) {
                return false;
            }
        }
        {
            StringOrRefType lhsStatus;
            lhsStatus = this.getStatus();
            StringOrRefType rhsStatus;
            rhsStatus = that.getStatus();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "status", lhsStatus), LocatorUtils.property(thatLocator, "status", rhsStatus), lhsStatus, rhsStatus)) {
                return false;
            }
        }
        {
            ReferenceType lhsStatusReference;
            lhsStatusReference = this.getStatusReference();
            ReferenceType rhsStatusReference;
            rhsStatusReference = that.getStatusReference();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "statusReference", lhsStatusReference), LocatorUtils.property(thatLocator, "statusReference", rhsStatusReference), lhsStatusReference, rhsStatusReference)) {
                return false;
            }
        }
        return true;
    }

    public boolean equals(Object object) {
        final EqualsStrategy strategy = JAXBEqualsStrategy.INSTANCE;
        return equals(null, null, object, strategy);
    }

    public int hashCode(ObjectLocator locator, HashCodeStrategy strategy) {
        int currentHashCode = super.hashCode(locator, strategy);
        {
            GeometryPropertyType thePosition;
            thePosition = this.getPosition();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "position", thePosition), currentHashCode, thePosition);
        }
        {
            DirectPositionType thePos;
            thePos = this.getPos();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "pos", thePos), currentHashCode, thePos);
        }
        {
            CodeType theLocationName;
            theLocationName = this.getLocationName();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "locationName", theLocationName), currentHashCode, theLocationName);
        }
        {
            ReferenceType theLocationReference;
            theLocationReference = this.getLocationReference();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "locationReference", theLocationReference), currentHashCode, theLocationReference);
        }
        {
            JAXBElement<? extends LocationPropertyType> theLocation;
            theLocation = this.getLocation();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "location", theLocation), currentHashCode, theLocation);
        }
        {
            MeasureType theSpeed;
            theSpeed = this.getSpeed();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "speed", theSpeed), currentHashCode, theSpeed);
        }
        {
            DirectionPropertyType theBearing;
            theBearing = this.getBearing();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "bearing", theBearing), currentHashCode, theBearing);
        }
        {
            MeasureType theAcceleration;
            theAcceleration = this.getAcceleration();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "acceleration", theAcceleration), currentHashCode, theAcceleration);
        }
        {
            MeasureType theElevation;
            theElevation = this.getElevation();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "elevation", theElevation), currentHashCode, theElevation);
        }
        {
            StringOrRefType theStatus;
            theStatus = this.getStatus();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "status", theStatus), currentHashCode, theStatus);
        }
        {
            ReferenceType theStatusReference;
            theStatusReference = this.getStatusReference();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "statusReference", theStatusReference), currentHashCode, theStatusReference);
        }
        return currentHashCode;
    }

    public int hashCode() {
        final HashCodeStrategy strategy = JAXBHashCodeStrategy.INSTANCE;
        return this.hashCode(null, strategy);
    }

    public Object clone() {
        return copyTo(createNewInstance());
    }

    public Object copyTo(Object target) {
        final CopyStrategy strategy = JAXBCopyStrategy.INSTANCE;
        return copyTo(null, target, strategy);
    }

    public Object copyTo(ObjectLocator locator, Object target, CopyStrategy strategy) {
        final Object draftCopy = ((target == null)?createNewInstance():target);
        super.copyTo(locator, draftCopy, strategy);
        if (draftCopy instanceof MovingObjectStatusType) {
            final MovingObjectStatusType copy = ((MovingObjectStatusType) draftCopy);
            if (this.isSetPosition()) {
                GeometryPropertyType sourcePosition;
                sourcePosition = this.getPosition();
                GeometryPropertyType copyPosition = ((GeometryPropertyType) strategy.copy(LocatorUtils.property(locator, "position", sourcePosition), sourcePosition));
                copy.setPosition(copyPosition);
            } else {
                copy.position = null;
            }
            if (this.isSetPos()) {
                DirectPositionType sourcePos;
                sourcePos = this.getPos();
                DirectPositionType copyPos = ((DirectPositionType) strategy.copy(LocatorUtils.property(locator, "pos", sourcePos), sourcePos));
                copy.setPos(copyPos);
            } else {
                copy.pos = null;
            }
            if (this.isSetLocationName()) {
                CodeType sourceLocationName;
                sourceLocationName = this.getLocationName();
                CodeType copyLocationName = ((CodeType) strategy.copy(LocatorUtils.property(locator, "locationName", sourceLocationName), sourceLocationName));
                copy.setLocationName(copyLocationName);
            } else {
                copy.locationName = null;
            }
            if (this.isSetLocationReference()) {
                ReferenceType sourceLocationReference;
                sourceLocationReference = this.getLocationReference();
                ReferenceType copyLocationReference = ((ReferenceType) strategy.copy(LocatorUtils.property(locator, "locationReference", sourceLocationReference), sourceLocationReference));
                copy.setLocationReference(copyLocationReference);
            } else {
                copy.locationReference = null;
            }
            if (this.isSetLocation()) {
                JAXBElement<? extends LocationPropertyType> sourceLocation;
                sourceLocation = this.getLocation();
                @SuppressWarnings("unchecked")
                JAXBElement<? extends LocationPropertyType> copyLocation = ((JAXBElement<? extends LocationPropertyType> ) strategy.copy(LocatorUtils.property(locator, "location", sourceLocation), sourceLocation));
                copy.setLocation(copyLocation);
            } else {
                copy.location = null;
            }
            if (this.isSetSpeed()) {
                MeasureType sourceSpeed;
                sourceSpeed = this.getSpeed();
                MeasureType copySpeed = ((MeasureType) strategy.copy(LocatorUtils.property(locator, "speed", sourceSpeed), sourceSpeed));
                copy.setSpeed(copySpeed);
            } else {
                copy.speed = null;
            }
            if (this.isSetBearing()) {
                DirectionPropertyType sourceBearing;
                sourceBearing = this.getBearing();
                DirectionPropertyType copyBearing = ((DirectionPropertyType) strategy.copy(LocatorUtils.property(locator, "bearing", sourceBearing), sourceBearing));
                copy.setBearing(copyBearing);
            } else {
                copy.bearing = null;
            }
            if (this.isSetAcceleration()) {
                MeasureType sourceAcceleration;
                sourceAcceleration = this.getAcceleration();
                MeasureType copyAcceleration = ((MeasureType) strategy.copy(LocatorUtils.property(locator, "acceleration", sourceAcceleration), sourceAcceleration));
                copy.setAcceleration(copyAcceleration);
            } else {
                copy.acceleration = null;
            }
            if (this.isSetElevation()) {
                MeasureType sourceElevation;
                sourceElevation = this.getElevation();
                MeasureType copyElevation = ((MeasureType) strategy.copy(LocatorUtils.property(locator, "elevation", sourceElevation), sourceElevation));
                copy.setElevation(copyElevation);
            } else {
                copy.elevation = null;
            }
            if (this.isSetStatus()) {
                StringOrRefType sourceStatus;
                sourceStatus = this.getStatus();
                StringOrRefType copyStatus = ((StringOrRefType) strategy.copy(LocatorUtils.property(locator, "status", sourceStatus), sourceStatus));
                copy.setStatus(copyStatus);
            } else {
                copy.status = null;
            }
            if (this.isSetStatusReference()) {
                ReferenceType sourceStatusReference;
                sourceStatusReference = this.getStatusReference();
                ReferenceType copyStatusReference = ((ReferenceType) strategy.copy(LocatorUtils.property(locator, "statusReference", sourceStatusReference), sourceStatusReference));
                copy.setStatusReference(copyStatusReference);
            } else {
                copy.statusReference = null;
            }
        }
        return draftCopy;
    }

    public Object createNewInstance() {
        return new MovingObjectStatusType();
    }

    public void mergeFrom(Object left, Object right) {
        final MergeStrategy strategy = JAXBMergeStrategy.INSTANCE;
        mergeFrom(null, null, left, right, strategy);
    }

    public void mergeFrom(ObjectLocator leftLocator, ObjectLocator rightLocator, Object left, Object right, MergeStrategy strategy) {
        super.mergeFrom(leftLocator, rightLocator, left, right, strategy);
        if (right instanceof MovingObjectStatusType) {
            final MovingObjectStatusType target = this;
            final MovingObjectStatusType leftObject = ((MovingObjectStatusType) left);
            final MovingObjectStatusType rightObject = ((MovingObjectStatusType) right);
            {
                GeometryPropertyType lhsPosition;
                lhsPosition = leftObject.getPosition();
                GeometryPropertyType rhsPosition;
                rhsPosition = rightObject.getPosition();
                target.setPosition(((GeometryPropertyType) strategy.merge(LocatorUtils.property(leftLocator, "position", lhsPosition), LocatorUtils.property(rightLocator, "position", rhsPosition), lhsPosition, rhsPosition)));
            }
            {
                DirectPositionType lhsPos;
                lhsPos = leftObject.getPos();
                DirectPositionType rhsPos;
                rhsPos = rightObject.getPos();
                target.setPos(((DirectPositionType) strategy.merge(LocatorUtils.property(leftLocator, "pos", lhsPos), LocatorUtils.property(rightLocator, "pos", rhsPos), lhsPos, rhsPos)));
            }
            {
                CodeType lhsLocationName;
                lhsLocationName = leftObject.getLocationName();
                CodeType rhsLocationName;
                rhsLocationName = rightObject.getLocationName();
                target.setLocationName(((CodeType) strategy.merge(LocatorUtils.property(leftLocator, "locationName", lhsLocationName), LocatorUtils.property(rightLocator, "locationName", rhsLocationName), lhsLocationName, rhsLocationName)));
            }
            {
                ReferenceType lhsLocationReference;
                lhsLocationReference = leftObject.getLocationReference();
                ReferenceType rhsLocationReference;
                rhsLocationReference = rightObject.getLocationReference();
                target.setLocationReference(((ReferenceType) strategy.merge(LocatorUtils.property(leftLocator, "locationReference", lhsLocationReference), LocatorUtils.property(rightLocator, "locationReference", rhsLocationReference), lhsLocationReference, rhsLocationReference)));
            }
            {
                JAXBElement<? extends LocationPropertyType> lhsLocation;
                lhsLocation = leftObject.getLocation();
                JAXBElement<? extends LocationPropertyType> rhsLocation;
                rhsLocation = rightObject.getLocation();
                target.setLocation(((JAXBElement<? extends LocationPropertyType> ) strategy.merge(LocatorUtils.property(leftLocator, "location", lhsLocation), LocatorUtils.property(rightLocator, "location", rhsLocation), lhsLocation, rhsLocation)));
            }
            {
                MeasureType lhsSpeed;
                lhsSpeed = leftObject.getSpeed();
                MeasureType rhsSpeed;
                rhsSpeed = rightObject.getSpeed();
                target.setSpeed(((MeasureType) strategy.merge(LocatorUtils.property(leftLocator, "speed", lhsSpeed), LocatorUtils.property(rightLocator, "speed", rhsSpeed), lhsSpeed, rhsSpeed)));
            }
            {
                DirectionPropertyType lhsBearing;
                lhsBearing = leftObject.getBearing();
                DirectionPropertyType rhsBearing;
                rhsBearing = rightObject.getBearing();
                target.setBearing(((DirectionPropertyType) strategy.merge(LocatorUtils.property(leftLocator, "bearing", lhsBearing), LocatorUtils.property(rightLocator, "bearing", rhsBearing), lhsBearing, rhsBearing)));
            }
            {
                MeasureType lhsAcceleration;
                lhsAcceleration = leftObject.getAcceleration();
                MeasureType rhsAcceleration;
                rhsAcceleration = rightObject.getAcceleration();
                target.setAcceleration(((MeasureType) strategy.merge(LocatorUtils.property(leftLocator, "acceleration", lhsAcceleration), LocatorUtils.property(rightLocator, "acceleration", rhsAcceleration), lhsAcceleration, rhsAcceleration)));
            }
            {
                MeasureType lhsElevation;
                lhsElevation = leftObject.getElevation();
                MeasureType rhsElevation;
                rhsElevation = rightObject.getElevation();
                target.setElevation(((MeasureType) strategy.merge(LocatorUtils.property(leftLocator, "elevation", lhsElevation), LocatorUtils.property(rightLocator, "elevation", rhsElevation), lhsElevation, rhsElevation)));
            }
            {
                StringOrRefType lhsStatus;
                lhsStatus = leftObject.getStatus();
                StringOrRefType rhsStatus;
                rhsStatus = rightObject.getStatus();
                target.setStatus(((StringOrRefType) strategy.merge(LocatorUtils.property(leftLocator, "status", lhsStatus), LocatorUtils.property(rightLocator, "status", rhsStatus), lhsStatus, rhsStatus)));
            }
            {
                ReferenceType lhsStatusReference;
                lhsStatusReference = leftObject.getStatusReference();
                ReferenceType rhsStatusReference;
                rhsStatusReference = rightObject.getStatusReference();
                target.setStatusReference(((ReferenceType) strategy.merge(LocatorUtils.property(leftLocator, "statusReference", lhsStatusReference), LocatorUtils.property(rightLocator, "statusReference", rhsStatusReference), lhsStatusReference, rhsStatusReference)));
            }
        }
    }

    public MovingObjectStatusType withPosition(GeometryPropertyType value) {
        setPosition(value);
        return this;
    }

    public MovingObjectStatusType withPos(DirectPositionType value) {
        setPos(value);
        return this;
    }

    public MovingObjectStatusType withLocationName(CodeType value) {
        setLocationName(value);
        return this;
    }

    public MovingObjectStatusType withLocationReference(ReferenceType value) {
        setLocationReference(value);
        return this;
    }

    public MovingObjectStatusType withLocation(JAXBElement<? extends LocationPropertyType> value) {
        setLocation(value);
        return this;
    }

    public MovingObjectStatusType withSpeed(MeasureType value) {
        setSpeed(value);
        return this;
    }

    public MovingObjectStatusType withBearing(DirectionPropertyType value) {
        setBearing(value);
        return this;
    }

    public MovingObjectStatusType withAcceleration(MeasureType value) {
        setAcceleration(value);
        return this;
    }

    public MovingObjectStatusType withElevation(MeasureType value) {
        setElevation(value);
        return this;
    }

    public MovingObjectStatusType withStatus(StringOrRefType value) {
        setStatus(value);
        return this;
    }

    public MovingObjectStatusType withStatusReference(ReferenceType value) {
        setStatusReference(value);
        return this;
    }

    @Override
    public MovingObjectStatusType withValidTime(TimePrimitivePropertyType value) {
        setValidTime(value);
        return this;
    }

    @Override
    public MovingObjectStatusType withDataSource(StringOrRefType value) {
        setDataSource(value);
        return this;
    }

    @Override
    public MovingObjectStatusType withMetaDataProperty(MetaDataPropertyType... values) {
        if (values!= null) {
            for (MetaDataPropertyType value: values) {
                getMetaDataProperty().add(value);
            }
        }
        return this;
    }

    @Override
    public MovingObjectStatusType withMetaDataProperty(Collection<MetaDataPropertyType> values) {
        if (values!= null) {
            getMetaDataProperty().addAll(values);
        }
        return this;
    }

    @Override
    public MovingObjectStatusType withDescription(StringOrRefType value) {
        setDescription(value);
        return this;
    }

    @Override
    public MovingObjectStatusType withDescriptionReference(ReferenceType value) {
        setDescriptionReference(value);
        return this;
    }

    @Override
    public MovingObjectStatusType withIdentifier(CodeWithAuthorityType value) {
        setIdentifier(value);
        return this;
    }

    @Override
    public MovingObjectStatusType withName(CodeType... values) {
        if (values!= null) {
            for (CodeType value: values) {
                getName().add(value);
            }
        }
        return this;
    }

    @Override
    public MovingObjectStatusType withName(Collection<CodeType> values) {
        if (values!= null) {
            getName().addAll(values);
        }
        return this;
    }

    @Override
    public MovingObjectStatusType withId(String value) {
        setId(value);
        return this;
    }

    @Override
    public MovingObjectStatusType withMetaDataProperty(List<MetaDataPropertyType> value) {
        setMetaDataProperty(value);
        return this;
    }

    @Override
    public MovingObjectStatusType withName(List<CodeType> value) {
        setName(value);
        return this;
    }

}
