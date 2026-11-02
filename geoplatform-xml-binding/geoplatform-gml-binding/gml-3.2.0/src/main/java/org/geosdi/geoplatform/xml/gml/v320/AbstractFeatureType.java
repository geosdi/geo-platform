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
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlElementRef;
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
 * The basic feature model is given by the gml:AbstractFeatureType.
 * The content model for gml:AbstractFeatureType adds two specific properties suitable for geographic features to the content model defined in gml:AbstractGMLType. 
 * The value of the gml:boundedBy property describes an envelope that encloses the entire feature instance, and is primarily useful for supporting rapid searching for features that occur in a particular location. 
 * The value of the gml:location property describes the extent, position or relative location of the feature.
 * 
 * <p>Classe Java per AbstractFeatureType complex type.</p>
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.</p>
 * 
 * <pre>{@code
 * <complexType name="AbstractFeatureType">
 *   <complexContent>
 *     <extension base="{http://www.opengis.net/gml}AbstractGMLType">
 *       <sequence>
 *         <element ref="{http://www.opengis.net/gml}boundedBy" minOccurs="0"/>
 *         <element ref="{http://www.opengis.net/gml}location" minOccurs="0"/>
 *       </sequence>
 *     </extension>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AbstractFeatureType", propOrder = {
    "boundedBy",
    "location"
})
@XmlSeeAlso({
    AbstractFeatureCollectionType.class,
    DynamicFeatureType.class,
    AbstractCoverageType.class,
    ObservationType.class,
    BoundedFeatureType.class
})
public abstract class AbstractFeatureType
    extends AbstractGMLType
    implements Cloneable, CopyTo, Equals, HashCode, MergeFrom, ToString
{

    /**
     * This property describes the minimum bounding box or rectangle that encloses the entire feature.
     * 
     */
    @XmlElement(nillable = true)
    protected BoundingShapeType boundedBy;
    @XmlElementRef(name = "location", namespace = "http://www.opengis.net/gml", type = JAXBElement.class, required = false)
    protected JAXBElement<? extends LocationPropertyType> location;

    /**
     * This property describes the minimum bounding box or rectangle that encloses the entire feature.
     * 
     * @return
     *     possible object is
     *     {@link BoundingShapeType }
     *     
     */
    public BoundingShapeType getBoundedBy() {
        return boundedBy;
    }

    /**
     * Imposta il valore della proprietà boundedBy.
     * 
     * @param value
     *     allowed object is
     *     {@link BoundingShapeType }
     *     
     * @see #getBoundedBy()
     */
    public void setBoundedBy(BoundingShapeType value) {
        this.boundedBy = value;
    }

    public boolean isSetBoundedBy() {
        return (this.boundedBy!= null);
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
            BoundingShapeType theBoundedBy;
            theBoundedBy = this.getBoundedBy();
            strategy.appendField(locator, this, "boundedBy", buffer, theBoundedBy, this.isSetBoundedBy());
        }
        {
            JAXBElement<? extends LocationPropertyType> theLocation;
            theLocation = this.getLocation();
            strategy.appendField(locator, this, "location", buffer, theLocation, this.isSetLocation());
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
        final AbstractFeatureType that = ((AbstractFeatureType) object);
        {
            BoundingShapeType lhsBoundedBy;
            lhsBoundedBy = this.getBoundedBy();
            BoundingShapeType rhsBoundedBy;
            rhsBoundedBy = that.getBoundedBy();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "boundedBy", lhsBoundedBy), LocatorUtils.property(thatLocator, "boundedBy", rhsBoundedBy), lhsBoundedBy, rhsBoundedBy, this.isSetBoundedBy(), that.isSetBoundedBy())) {
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
            BoundingShapeType theBoundedBy;
            theBoundedBy = this.getBoundedBy();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "boundedBy", theBoundedBy), currentHashCode, theBoundedBy, this.isSetBoundedBy());
        }
        {
            JAXBElement<? extends LocationPropertyType> theLocation;
            theLocation = this.getLocation();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "location", theLocation), currentHashCode, theLocation, this.isSetLocation());
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
        if (target instanceof AbstractFeatureType) {
            final AbstractFeatureType copy = ((AbstractFeatureType) target);
            {
                java.lang.Boolean boundedByShouldBeCopiedAndSet = strategy.shouldBeCopiedAndSet(locator, this.isSetBoundedBy());
                if (boundedByShouldBeCopiedAndSet == java.lang.Boolean.TRUE) {
                    BoundingShapeType sourceBoundedBy;
                    sourceBoundedBy = this.getBoundedBy();
                    BoundingShapeType copyBoundedBy = ((BoundingShapeType) strategy.copy(LocatorUtils.property(locator, "boundedBy", sourceBoundedBy), sourceBoundedBy, this.isSetBoundedBy()));
                    copy.setBoundedBy(copyBoundedBy);
                } else {
                    if (boundedByShouldBeCopiedAndSet == java.lang.Boolean.FALSE) {
                        copy.boundedBy = null;
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
        if (right instanceof AbstractFeatureType) {
            final AbstractFeatureType target = this;
            final AbstractFeatureType leftObject = ((AbstractFeatureType) left);
            final AbstractFeatureType rightObject = ((AbstractFeatureType) right);
            {
                java.lang.Boolean boundedByShouldBeMergedAndSet = strategy.shouldBeMergedAndSet(leftLocator, rightLocator, leftObject.isSetBoundedBy(), rightObject.isSetBoundedBy());
                if (boundedByShouldBeMergedAndSet == java.lang.Boolean.TRUE) {
                    BoundingShapeType lhsBoundedBy;
                    lhsBoundedBy = leftObject.getBoundedBy();
                    BoundingShapeType rhsBoundedBy;
                    rhsBoundedBy = rightObject.getBoundedBy();
                    BoundingShapeType mergedBoundedBy = ((BoundingShapeType) strategy.merge(LocatorUtils.property(leftLocator, "boundedBy", lhsBoundedBy), LocatorUtils.property(rightLocator, "boundedBy", rhsBoundedBy), lhsBoundedBy, rhsBoundedBy, leftObject.isSetBoundedBy(), rightObject.isSetBoundedBy()));
                    target.setBoundedBy(mergedBoundedBy);
                } else {
                    if (boundedByShouldBeMergedAndSet == java.lang.Boolean.FALSE) {
                        target.boundedBy = null;
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
        }
    }

}
