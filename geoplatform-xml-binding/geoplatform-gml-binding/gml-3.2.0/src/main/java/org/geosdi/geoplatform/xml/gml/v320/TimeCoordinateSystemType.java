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
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
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
 * <p>Classe Java per TimeCoordinateSystemType complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="TimeCoordinateSystemType">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.opengis.net/gml}TimeReferenceSystemType">
 *       &lt;sequence>
 *         &lt;choice>
 *           &lt;element name="originPosition" type="{http://www.opengis.net/gml}TimePositionType"/>
 *           &lt;element name="origin" type="{http://www.opengis.net/gml}TimeInstantPropertyType"/>
 *         &lt;/choice>
 *         &lt;element name="interval" type="{http://www.opengis.net/gml}TimeIntervalLengthType"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TimeCoordinateSystemType", propOrder = {
    "originPosition",
    "origin",
    "interval"
})
public class TimeCoordinateSystemType
    extends TimeReferenceSystemType
    implements Cloneable, CopyTo, Equals, HashCode, MergeFrom, ToString
{

    protected TimePositionType originPosition;
    protected TimeInstantPropertyType origin;
    @XmlElement(required = true)
    protected TimeIntervalLengthType interval;

    /**
     * Recupera il valore della proprietà originPosition.
     * 
     * @return
     *     possible object is
     *     {@link TimePositionType }
     *     
     */
    public TimePositionType getOriginPosition() {
        return originPosition;
    }

    /**
     * Imposta il valore della proprietà originPosition.
     * 
     * @param value
     *     allowed object is
     *     {@link TimePositionType }
     *     
     */
    public void setOriginPosition(TimePositionType value) {
        this.originPosition = value;
    }

    public boolean isSetOriginPosition() {
        return (this.originPosition!= null);
    }

    /**
     * Recupera il valore della proprietà origin.
     * 
     * @return
     *     possible object is
     *     {@link TimeInstantPropertyType }
     *     
     */
    public TimeInstantPropertyType getOrigin() {
        return origin;
    }

    /**
     * Imposta il valore della proprietà origin.
     * 
     * @param value
     *     allowed object is
     *     {@link TimeInstantPropertyType }
     *     
     */
    public void setOrigin(TimeInstantPropertyType value) {
        this.origin = value;
    }

    public boolean isSetOrigin() {
        return (this.origin!= null);
    }

    /**
     * Recupera il valore della proprietà interval.
     * 
     * @return
     *     possible object is
     *     {@link TimeIntervalLengthType }
     *     
     */
    public TimeIntervalLengthType getInterval() {
        return interval;
    }

    /**
     * Imposta il valore della proprietà interval.
     * 
     * @param value
     *     allowed object is
     *     {@link TimeIntervalLengthType }
     *     
     */
    public void setInterval(TimeIntervalLengthType value) {
        this.interval = value;
    }

    public boolean isSetInterval() {
        return (this.interval!= null);
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
            TimePositionType theOriginPosition;
            theOriginPosition = this.getOriginPosition();
            strategy.appendField(locator, this, "originPosition", buffer, theOriginPosition);
        }
        {
            TimeInstantPropertyType theOrigin;
            theOrigin = this.getOrigin();
            strategy.appendField(locator, this, "origin", buffer, theOrigin);
        }
        {
            TimeIntervalLengthType theInterval;
            theInterval = this.getInterval();
            strategy.appendField(locator, this, "interval", buffer, theInterval);
        }
        return buffer;
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof TimeCoordinateSystemType)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        if (!super.equals(thisLocator, thatLocator, object, strategy)) {
            return false;
        }
        final TimeCoordinateSystemType that = ((TimeCoordinateSystemType) object);
        {
            TimePositionType lhsOriginPosition;
            lhsOriginPosition = this.getOriginPosition();
            TimePositionType rhsOriginPosition;
            rhsOriginPosition = that.getOriginPosition();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "originPosition", lhsOriginPosition), LocatorUtils.property(thatLocator, "originPosition", rhsOriginPosition), lhsOriginPosition, rhsOriginPosition)) {
                return false;
            }
        }
        {
            TimeInstantPropertyType lhsOrigin;
            lhsOrigin = this.getOrigin();
            TimeInstantPropertyType rhsOrigin;
            rhsOrigin = that.getOrigin();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "origin", lhsOrigin), LocatorUtils.property(thatLocator, "origin", rhsOrigin), lhsOrigin, rhsOrigin)) {
                return false;
            }
        }
        {
            TimeIntervalLengthType lhsInterval;
            lhsInterval = this.getInterval();
            TimeIntervalLengthType rhsInterval;
            rhsInterval = that.getInterval();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "interval", lhsInterval), LocatorUtils.property(thatLocator, "interval", rhsInterval), lhsInterval, rhsInterval)) {
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
            TimePositionType theOriginPosition;
            theOriginPosition = this.getOriginPosition();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "originPosition", theOriginPosition), currentHashCode, theOriginPosition);
        }
        {
            TimeInstantPropertyType theOrigin;
            theOrigin = this.getOrigin();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "origin", theOrigin), currentHashCode, theOrigin);
        }
        {
            TimeIntervalLengthType theInterval;
            theInterval = this.getInterval();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "interval", theInterval), currentHashCode, theInterval);
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
        if (draftCopy instanceof TimeCoordinateSystemType) {
            final TimeCoordinateSystemType copy = ((TimeCoordinateSystemType) draftCopy);
            if (this.isSetOriginPosition()) {
                TimePositionType sourceOriginPosition;
                sourceOriginPosition = this.getOriginPosition();
                TimePositionType copyOriginPosition = ((TimePositionType) strategy.copy(LocatorUtils.property(locator, "originPosition", sourceOriginPosition), sourceOriginPosition));
                copy.setOriginPosition(copyOriginPosition);
            } else {
                copy.originPosition = null;
            }
            if (this.isSetOrigin()) {
                TimeInstantPropertyType sourceOrigin;
                sourceOrigin = this.getOrigin();
                TimeInstantPropertyType copyOrigin = ((TimeInstantPropertyType) strategy.copy(LocatorUtils.property(locator, "origin", sourceOrigin), sourceOrigin));
                copy.setOrigin(copyOrigin);
            } else {
                copy.origin = null;
            }
            if (this.isSetInterval()) {
                TimeIntervalLengthType sourceInterval;
                sourceInterval = this.getInterval();
                TimeIntervalLengthType copyInterval = ((TimeIntervalLengthType) strategy.copy(LocatorUtils.property(locator, "interval", sourceInterval), sourceInterval));
                copy.setInterval(copyInterval);
            } else {
                copy.interval = null;
            }
        }
        return draftCopy;
    }

    public Object createNewInstance() {
        return new TimeCoordinateSystemType();
    }

    public void mergeFrom(Object left, Object right) {
        final MergeStrategy strategy = JAXBMergeStrategy.INSTANCE;
        mergeFrom(null, null, left, right, strategy);
    }

    public void mergeFrom(ObjectLocator leftLocator, ObjectLocator rightLocator, Object left, Object right, MergeStrategy strategy) {
        super.mergeFrom(leftLocator, rightLocator, left, right, strategy);
        if (right instanceof TimeCoordinateSystemType) {
            final TimeCoordinateSystemType target = this;
            final TimeCoordinateSystemType leftObject = ((TimeCoordinateSystemType) left);
            final TimeCoordinateSystemType rightObject = ((TimeCoordinateSystemType) right);
            {
                TimePositionType lhsOriginPosition;
                lhsOriginPosition = leftObject.getOriginPosition();
                TimePositionType rhsOriginPosition;
                rhsOriginPosition = rightObject.getOriginPosition();
                target.setOriginPosition(((TimePositionType) strategy.merge(LocatorUtils.property(leftLocator, "originPosition", lhsOriginPosition), LocatorUtils.property(rightLocator, "originPosition", rhsOriginPosition), lhsOriginPosition, rhsOriginPosition)));
            }
            {
                TimeInstantPropertyType lhsOrigin;
                lhsOrigin = leftObject.getOrigin();
                TimeInstantPropertyType rhsOrigin;
                rhsOrigin = rightObject.getOrigin();
                target.setOrigin(((TimeInstantPropertyType) strategy.merge(LocatorUtils.property(leftLocator, "origin", lhsOrigin), LocatorUtils.property(rightLocator, "origin", rhsOrigin), lhsOrigin, rhsOrigin)));
            }
            {
                TimeIntervalLengthType lhsInterval;
                lhsInterval = leftObject.getInterval();
                TimeIntervalLengthType rhsInterval;
                rhsInterval = rightObject.getInterval();
                target.setInterval(((TimeIntervalLengthType) strategy.merge(LocatorUtils.property(leftLocator, "interval", lhsInterval), LocatorUtils.property(rightLocator, "interval", rhsInterval), lhsInterval, rhsInterval)));
            }
        }
    }

    public TimeCoordinateSystemType withOriginPosition(TimePositionType value) {
        setOriginPosition(value);
        return this;
    }

    public TimeCoordinateSystemType withOrigin(TimeInstantPropertyType value) {
        setOrigin(value);
        return this;
    }

    public TimeCoordinateSystemType withInterval(TimeIntervalLengthType value) {
        setInterval(value);
        return this;
    }

    @Override
    public TimeCoordinateSystemType withDomainOfValidity(String value) {
        setDomainOfValidity(value);
        return this;
    }

    @Override
    public TimeCoordinateSystemType withRemarks(String value) {
        setRemarks(value);
        return this;
    }

    @Override
    public TimeCoordinateSystemType withMetaDataProperty(MetaDataPropertyType... values) {
        if (values!= null) {
            for (MetaDataPropertyType value: values) {
                getMetaDataProperty().add(value);
            }
        }
        return this;
    }

    @Override
    public TimeCoordinateSystemType withMetaDataProperty(Collection<MetaDataPropertyType> values) {
        if (values!= null) {
            getMetaDataProperty().addAll(values);
        }
        return this;
    }

    @Override
    public TimeCoordinateSystemType withDescription(StringOrRefType value) {
        setDescription(value);
        return this;
    }

    @Override
    public TimeCoordinateSystemType withDescriptionReference(ReferenceType value) {
        setDescriptionReference(value);
        return this;
    }

    @Override
    public TimeCoordinateSystemType withIdentifier(CodeWithAuthorityType value) {
        setIdentifier(value);
        return this;
    }

    @Override
    public TimeCoordinateSystemType withName(CodeType... values) {
        if (values!= null) {
            for (CodeType value: values) {
                getName().add(value);
            }
        }
        return this;
    }

    @Override
    public TimeCoordinateSystemType withName(Collection<CodeType> values) {
        if (values!= null) {
            getName().addAll(values);
        }
        return this;
    }

    @Override
    public TimeCoordinateSystemType withId(String value) {
        setId(value);
        return this;
    }

    @Override
    public TimeCoordinateSystemType withMetaDataProperty(List<MetaDataPropertyType> value) {
        setMetaDataProperty(value);
        return this;
    }

    @Override
    public TimeCoordinateSystemType withName(List<CodeType> value) {
        setName(value);
        return this;
    }

}
