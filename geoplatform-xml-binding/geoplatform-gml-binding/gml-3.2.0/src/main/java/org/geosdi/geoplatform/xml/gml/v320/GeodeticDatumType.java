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
 * <p>Classe Java per GeodeticDatumType complex type.</p>
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.</p>
 * 
 * <pre>{@code
 * <complexType name="GeodeticDatumType">
 *   <complexContent>
 *     <extension base="{http://www.opengis.net/gml}AbstractDatumType">
 *       <sequence>
 *         <element ref="{http://www.opengis.net/gml}primeMeridian"/>
 *         <element ref="{http://www.opengis.net/gml}ellipsoid"/>
 *       </sequence>
 *     </extension>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "GeodeticDatumType", propOrder = {
    "primeMeridian",
    "ellipsoid"
})
public class GeodeticDatumType
    extends AbstractDatumType
    implements Cloneable, CopyTo, Equals, HashCode, MergeFrom, ToString
{

    /**
     * gml:primeMeridian is an association role to the prime meridian used by this geodetic datum.
     * 
     */
    @XmlElementRef(name = "primeMeridian", namespace = "http://www.opengis.net/gml", type = JAXBElement.class)
    protected JAXBElement<PrimeMeridianPropertyType> primeMeridian;
    /**
     * gml:ellipsoid is an association role to the ellipsoid used by this geodetic datum.
     * 
     */
    @XmlElementRef(name = "ellipsoid", namespace = "http://www.opengis.net/gml", type = JAXBElement.class)
    protected JAXBElement<EllipsoidPropertyType> ellipsoid;

    /**
     * gml:primeMeridian is an association role to the prime meridian used by this geodetic datum.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link PrimeMeridianPropertyType }{@code >}
     *     {@link JAXBElement }{@code <}{@link PrimeMeridianPropertyType }{@code >}
     *     
     */
    public JAXBElement<PrimeMeridianPropertyType> getPrimeMeridian() {
        return primeMeridian;
    }

    /**
     * Imposta il valore della proprietà primeMeridian.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link PrimeMeridianPropertyType }{@code >}
     *     {@link JAXBElement }{@code <}{@link PrimeMeridianPropertyType }{@code >}
     *     
     * @see #getPrimeMeridian()
     */
    public void setPrimeMeridian(JAXBElement<PrimeMeridianPropertyType> value) {
        this.primeMeridian = value;
    }

    public boolean isSetPrimeMeridian() {
        return (this.primeMeridian!= null);
    }

    /**
     * gml:ellipsoid is an association role to the ellipsoid used by this geodetic datum.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link EllipsoidPropertyType }{@code >}
     *     {@link JAXBElement }{@code <}{@link EllipsoidPropertyType }{@code >}
     *     
     */
    public JAXBElement<EllipsoidPropertyType> getEllipsoid() {
        return ellipsoid;
    }

    /**
     * Imposta il valore della proprietà ellipsoid.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link EllipsoidPropertyType }{@code >}
     *     {@link JAXBElement }{@code <}{@link EllipsoidPropertyType }{@code >}
     *     
     * @see #getEllipsoid()
     */
    public void setEllipsoid(JAXBElement<EllipsoidPropertyType> value) {
        this.ellipsoid = value;
    }

    public boolean isSetEllipsoid() {
        return (this.ellipsoid!= null);
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
            JAXBElement<PrimeMeridianPropertyType> thePrimeMeridian;
            thePrimeMeridian = this.getPrimeMeridian();
            strategy.appendField(locator, this, "primeMeridian", buffer, thePrimeMeridian, this.isSetPrimeMeridian());
        }
        {
            JAXBElement<EllipsoidPropertyType> theEllipsoid;
            theEllipsoid = this.getEllipsoid();
            strategy.appendField(locator, this, "ellipsoid", buffer, theEllipsoid, this.isSetEllipsoid());
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
        final GeodeticDatumType that = ((GeodeticDatumType) object);
        {
            JAXBElement<PrimeMeridianPropertyType> lhsPrimeMeridian;
            lhsPrimeMeridian = this.getPrimeMeridian();
            JAXBElement<PrimeMeridianPropertyType> rhsPrimeMeridian;
            rhsPrimeMeridian = that.getPrimeMeridian();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "primeMeridian", lhsPrimeMeridian), LocatorUtils.property(thatLocator, "primeMeridian", rhsPrimeMeridian), lhsPrimeMeridian, rhsPrimeMeridian, this.isSetPrimeMeridian(), that.isSetPrimeMeridian())) {
                return false;
            }
        }
        {
            JAXBElement<EllipsoidPropertyType> lhsEllipsoid;
            lhsEllipsoid = this.getEllipsoid();
            JAXBElement<EllipsoidPropertyType> rhsEllipsoid;
            rhsEllipsoid = that.getEllipsoid();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "ellipsoid", lhsEllipsoid), LocatorUtils.property(thatLocator, "ellipsoid", rhsEllipsoid), lhsEllipsoid, rhsEllipsoid, this.isSetEllipsoid(), that.isSetEllipsoid())) {
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
            JAXBElement<PrimeMeridianPropertyType> thePrimeMeridian;
            thePrimeMeridian = this.getPrimeMeridian();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "primeMeridian", thePrimeMeridian), currentHashCode, thePrimeMeridian, this.isSetPrimeMeridian());
        }
        {
            JAXBElement<EllipsoidPropertyType> theEllipsoid;
            theEllipsoid = this.getEllipsoid();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "ellipsoid", theEllipsoid), currentHashCode, theEllipsoid, this.isSetEllipsoid());
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
        if (draftCopy instanceof GeodeticDatumType) {
            final GeodeticDatumType copy = ((GeodeticDatumType) draftCopy);
            {
                java.lang.Boolean primeMeridianShouldBeCopiedAndSet = strategy.shouldBeCopiedAndSet(locator, this.isSetPrimeMeridian());
                if (primeMeridianShouldBeCopiedAndSet == java.lang.Boolean.TRUE) {
                    JAXBElement<PrimeMeridianPropertyType> sourcePrimeMeridian;
                    sourcePrimeMeridian = this.getPrimeMeridian();
                    @SuppressWarnings("unchecked")
                    JAXBElement<PrimeMeridianPropertyType> copyPrimeMeridian = ((JAXBElement<PrimeMeridianPropertyType> ) strategy.copy(LocatorUtils.property(locator, "primeMeridian", sourcePrimeMeridian), sourcePrimeMeridian, this.isSetPrimeMeridian()));
                    copy.setPrimeMeridian(copyPrimeMeridian);
                } else {
                    if (primeMeridianShouldBeCopiedAndSet == java.lang.Boolean.FALSE) {
                        copy.primeMeridian = null;
                    }
                }
            }
            {
                java.lang.Boolean ellipsoidShouldBeCopiedAndSet = strategy.shouldBeCopiedAndSet(locator, this.isSetEllipsoid());
                if (ellipsoidShouldBeCopiedAndSet == java.lang.Boolean.TRUE) {
                    JAXBElement<EllipsoidPropertyType> sourceEllipsoid;
                    sourceEllipsoid = this.getEllipsoid();
                    @SuppressWarnings("unchecked")
                    JAXBElement<EllipsoidPropertyType> copyEllipsoid = ((JAXBElement<EllipsoidPropertyType> ) strategy.copy(LocatorUtils.property(locator, "ellipsoid", sourceEllipsoid), sourceEllipsoid, this.isSetEllipsoid()));
                    copy.setEllipsoid(copyEllipsoid);
                } else {
                    if (ellipsoidShouldBeCopiedAndSet == java.lang.Boolean.FALSE) {
                        copy.ellipsoid = null;
                    }
                }
            }
        }
        return draftCopy;
    }

    @Override
    public Object createNewInstance() {
        return new GeodeticDatumType();
    }

    @Override
    public void mergeFrom(Object left, Object right) {
        final MergeStrategy strategy = JAXBMergeStrategy.getInstance();
        mergeFrom(null, null, left, right, strategy);
    }

    @Override
    public void mergeFrom(ObjectLocator leftLocator, ObjectLocator rightLocator, Object left, Object right, MergeStrategy strategy) {
        super.mergeFrom(leftLocator, rightLocator, left, right, strategy);
        if (right instanceof GeodeticDatumType) {
            final GeodeticDatumType target = this;
            final GeodeticDatumType leftObject = ((GeodeticDatumType) left);
            final GeodeticDatumType rightObject = ((GeodeticDatumType) right);
            {
                java.lang.Boolean primeMeridianShouldBeMergedAndSet = strategy.shouldBeMergedAndSet(leftLocator, rightLocator, leftObject.isSetPrimeMeridian(), rightObject.isSetPrimeMeridian());
                if (primeMeridianShouldBeMergedAndSet == java.lang.Boolean.TRUE) {
                    JAXBElement<PrimeMeridianPropertyType> lhsPrimeMeridian;
                    lhsPrimeMeridian = leftObject.getPrimeMeridian();
                    JAXBElement<PrimeMeridianPropertyType> rhsPrimeMeridian;
                    rhsPrimeMeridian = rightObject.getPrimeMeridian();
                    JAXBElement<PrimeMeridianPropertyType> mergedPrimeMeridian = ((JAXBElement<PrimeMeridianPropertyType> ) strategy.merge(LocatorUtils.property(leftLocator, "primeMeridian", lhsPrimeMeridian), LocatorUtils.property(rightLocator, "primeMeridian", rhsPrimeMeridian), lhsPrimeMeridian, rhsPrimeMeridian, leftObject.isSetPrimeMeridian(), rightObject.isSetPrimeMeridian()));
                    target.setPrimeMeridian(mergedPrimeMeridian);
                } else {
                    if (primeMeridianShouldBeMergedAndSet == java.lang.Boolean.FALSE) {
                        target.primeMeridian = null;
                    }
                }
            }
            {
                java.lang.Boolean ellipsoidShouldBeMergedAndSet = strategy.shouldBeMergedAndSet(leftLocator, rightLocator, leftObject.isSetEllipsoid(), rightObject.isSetEllipsoid());
                if (ellipsoidShouldBeMergedAndSet == java.lang.Boolean.TRUE) {
                    JAXBElement<EllipsoidPropertyType> lhsEllipsoid;
                    lhsEllipsoid = leftObject.getEllipsoid();
                    JAXBElement<EllipsoidPropertyType> rhsEllipsoid;
                    rhsEllipsoid = rightObject.getEllipsoid();
                    JAXBElement<EllipsoidPropertyType> mergedEllipsoid = ((JAXBElement<EllipsoidPropertyType> ) strategy.merge(LocatorUtils.property(leftLocator, "ellipsoid", lhsEllipsoid), LocatorUtils.property(rightLocator, "ellipsoid", rhsEllipsoid), lhsEllipsoid, rhsEllipsoid, leftObject.isSetEllipsoid(), rightObject.isSetEllipsoid()));
                    target.setEllipsoid(mergedEllipsoid);
                } else {
                    if (ellipsoidShouldBeMergedAndSet == java.lang.Boolean.FALSE) {
                        target.ellipsoid = null;
                    }
                }
            }
        }
    }

}
