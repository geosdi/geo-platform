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

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlSeeAlso;
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
 * <p>Classe Java per AbstractTimeSliceType complex type.</p>
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.</p>
 * 
 * <pre>{@code
 * <complexType name="AbstractTimeSliceType">
 *   <complexContent>
 *     <extension base="{http://www.opengis.net/gml}AbstractGMLType">
 *       <sequence>
 *         <element ref="{http://www.opengis.net/gml}validTime"/>
 *         <element ref="{http://www.opengis.net/gml}dataSource" minOccurs="0"/>
 *       </sequence>
 *     </extension>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AbstractTimeSliceType", propOrder = {
    "validTime",
    "dataSource"
})
@XmlSeeAlso({
    MovingObjectStatusType.class
})
public abstract class AbstractTimeSliceType
    extends AbstractGMLType
    implements Cloneable, CopyTo, Equals, HashCode, MergeFrom, ToString
{

    /**
     * gml:validTime is a convenience property element.
     * 
     */
    @XmlElement(required = true)
    protected TimePrimitivePropertyType validTime;
    /**
     * Evidence is represented by a simple gml:dataSource or gml:dataSourceReference property that indicates the source of the temporal data. The remote link attributes of the gml:dataSource element have been deprecated along with its current type.
     * 
     */
    protected StringOrRefType dataSource;

    /**
     * gml:validTime is a convenience property element.
     * 
     * @return
     *     possible object is
     *     {@link TimePrimitivePropertyType }
     *     
     */
    public TimePrimitivePropertyType getValidTime() {
        return validTime;
    }

    /**
     * Imposta il valore della proprietà validTime.
     * 
     * @param value
     *     allowed object is
     *     {@link TimePrimitivePropertyType }
     *     
     * @see #getValidTime()
     */
    public void setValidTime(TimePrimitivePropertyType value) {
        this.validTime = value;
    }

    public boolean isSetValidTime() {
        return (this.validTime!= null);
    }

    /**
     * Evidence is represented by a simple gml:dataSource or gml:dataSourceReference property that indicates the source of the temporal data. The remote link attributes of the gml:dataSource element have been deprecated along with its current type.
     * 
     * @return
     *     possible object is
     *     {@link StringOrRefType }
     *     
     */
    public StringOrRefType getDataSource() {
        return dataSource;
    }

    /**
     * Imposta il valore della proprietà dataSource.
     * 
     * @param value
     *     allowed object is
     *     {@link StringOrRefType }
     *     
     * @see #getDataSource()
     */
    public void setDataSource(StringOrRefType value) {
        this.dataSource = value;
    }

    public boolean isSetDataSource() {
        return (this.dataSource!= null);
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
            TimePrimitivePropertyType theValidTime;
            theValidTime = this.getValidTime();
            strategy.appendField(locator, this, "validTime", buffer, theValidTime, this.isSetValidTime());
        }
        {
            StringOrRefType theDataSource;
            theDataSource = this.getDataSource();
            strategy.appendField(locator, this, "dataSource", buffer, theDataSource, this.isSetDataSource());
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
        final AbstractTimeSliceType that = ((AbstractTimeSliceType) object);
        {
            TimePrimitivePropertyType lhsValidTime;
            lhsValidTime = this.getValidTime();
            TimePrimitivePropertyType rhsValidTime;
            rhsValidTime = that.getValidTime();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "validTime", lhsValidTime), LocatorUtils.property(thatLocator, "validTime", rhsValidTime), lhsValidTime, rhsValidTime, this.isSetValidTime(), that.isSetValidTime())) {
                return false;
            }
        }
        {
            StringOrRefType lhsDataSource;
            lhsDataSource = this.getDataSource();
            StringOrRefType rhsDataSource;
            rhsDataSource = that.getDataSource();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "dataSource", lhsDataSource), LocatorUtils.property(thatLocator, "dataSource", rhsDataSource), lhsDataSource, rhsDataSource, this.isSetDataSource(), that.isSetDataSource())) {
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
            TimePrimitivePropertyType theValidTime;
            theValidTime = this.getValidTime();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "validTime", theValidTime), currentHashCode, theValidTime, this.isSetValidTime());
        }
        {
            StringOrRefType theDataSource;
            theDataSource = this.getDataSource();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "dataSource", theDataSource), currentHashCode, theDataSource, this.isSetDataSource());
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
        if (null == target) {
            throw new IllegalArgumentException("Target argument must not be null for abstract copyable classes.");
        }
        super.copyTo(locator, target, strategy);
        if (target instanceof AbstractTimeSliceType) {
            final AbstractTimeSliceType copy = ((AbstractTimeSliceType) target);
            {
                java.lang.Boolean validTimeShouldBeCopiedAndSet = strategy.shouldBeCopiedAndSet(locator, this.isSetValidTime());
                if (validTimeShouldBeCopiedAndSet == java.lang.Boolean.TRUE) {
                    TimePrimitivePropertyType sourceValidTime;
                    sourceValidTime = this.getValidTime();
                    TimePrimitivePropertyType copyValidTime = ((TimePrimitivePropertyType) strategy.copy(LocatorUtils.property(locator, "validTime", sourceValidTime), sourceValidTime, this.isSetValidTime()));
                    copy.setValidTime(copyValidTime);
                } else {
                    if (validTimeShouldBeCopiedAndSet == java.lang.Boolean.FALSE) {
                        copy.validTime = null;
                    }
                }
            }
            {
                java.lang.Boolean dataSourceShouldBeCopiedAndSet = strategy.shouldBeCopiedAndSet(locator, this.isSetDataSource());
                if (dataSourceShouldBeCopiedAndSet == java.lang.Boolean.TRUE) {
                    StringOrRefType sourceDataSource;
                    sourceDataSource = this.getDataSource();
                    StringOrRefType copyDataSource = ((StringOrRefType) strategy.copy(LocatorUtils.property(locator, "dataSource", sourceDataSource), sourceDataSource, this.isSetDataSource()));
                    copy.setDataSource(copyDataSource);
                } else {
                    if (dataSourceShouldBeCopiedAndSet == java.lang.Boolean.FALSE) {
                        copy.dataSource = null;
                    }
                }
            }
        }
        return target;
    }

    @Override
    public void mergeFrom(Object left, Object right) {
        final MergeStrategy strategy = JAXBMergeStrategy.getInstance();
        mergeFrom(null, null, left, right, strategy);
    }

    @Override
    public void mergeFrom(ObjectLocator leftLocator, ObjectLocator rightLocator, Object left, Object right, MergeStrategy strategy) {
        super.mergeFrom(leftLocator, rightLocator, left, right, strategy);
        if (right instanceof AbstractTimeSliceType) {
            final AbstractTimeSliceType target = this;
            final AbstractTimeSliceType leftObject = ((AbstractTimeSliceType) left);
            final AbstractTimeSliceType rightObject = ((AbstractTimeSliceType) right);
            {
                java.lang.Boolean validTimeShouldBeMergedAndSet = strategy.shouldBeMergedAndSet(leftLocator, rightLocator, leftObject.isSetValidTime(), rightObject.isSetValidTime());
                if (validTimeShouldBeMergedAndSet == java.lang.Boolean.TRUE) {
                    TimePrimitivePropertyType lhsValidTime;
                    lhsValidTime = leftObject.getValidTime();
                    TimePrimitivePropertyType rhsValidTime;
                    rhsValidTime = rightObject.getValidTime();
                    TimePrimitivePropertyType mergedValidTime = ((TimePrimitivePropertyType) strategy.merge(LocatorUtils.property(leftLocator, "validTime", lhsValidTime), LocatorUtils.property(rightLocator, "validTime", rhsValidTime), lhsValidTime, rhsValidTime, leftObject.isSetValidTime(), rightObject.isSetValidTime()));
                    target.setValidTime(mergedValidTime);
                } else {
                    if (validTimeShouldBeMergedAndSet == java.lang.Boolean.FALSE) {
                        target.validTime = null;
                    }
                }
            }
            {
                java.lang.Boolean dataSourceShouldBeMergedAndSet = strategy.shouldBeMergedAndSet(leftLocator, rightLocator, leftObject.isSetDataSource(), rightObject.isSetDataSource());
                if (dataSourceShouldBeMergedAndSet == java.lang.Boolean.TRUE) {
                    StringOrRefType lhsDataSource;
                    lhsDataSource = leftObject.getDataSource();
                    StringOrRefType rhsDataSource;
                    rhsDataSource = rightObject.getDataSource();
                    StringOrRefType mergedDataSource = ((StringOrRefType) strategy.merge(LocatorUtils.property(leftLocator, "dataSource", lhsDataSource), LocatorUtils.property(rightLocator, "dataSource", rhsDataSource), lhsDataSource, rhsDataSource, leftObject.isSetDataSource(), rightObject.isSetDataSource()));
                    target.setDataSource(mergedDataSource);
                } else {
                    if (dataSourceShouldBeMergedAndSet == java.lang.Boolean.FALSE) {
                        target.dataSource = null;
                    }
                }
            }
        }
    }

}
