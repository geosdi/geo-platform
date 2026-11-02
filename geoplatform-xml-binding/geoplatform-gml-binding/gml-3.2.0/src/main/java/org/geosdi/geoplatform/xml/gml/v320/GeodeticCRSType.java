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
 * gml:GeodeticCRS is a coordinate reference system based on a geodetic datum.
 * 
 * <p>Classe Java per GeodeticCRSType complex type.</p>
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.</p>
 * 
 * <pre>{@code
 * <complexType name="GeodeticCRSType">
 *   <complexContent>
 *     <extension base="{http://www.opengis.net/gml}AbstractCRSType">
 *       <sequence>
 *         <choice>
 *           <element ref="{http://www.opengis.net/gml}ellipsoidalCS"/>
 *           <element ref="{http://www.opengis.net/gml}cartesianCS"/>
 *           <element ref="{http://www.opengis.net/gml}sphericalCS"/>
 *         </choice>
 *         <element ref="{http://www.opengis.net/gml}geodeticDatum"/>
 *       </sequence>
 *     </extension>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "GeodeticCRSType", propOrder = {
    "ellipsoidalCS",
    "cartesianCS",
    "sphericalCS",
    "geodeticDatum"
})
public class GeodeticCRSType
    extends AbstractCRSType
    implements Cloneable, CopyTo, Equals, HashCode, MergeFrom, ToString
{

    /**
     * gml:ellipsoidalCS is an association role to the ellipsoidal coordinate system used by this CRS.
     * 
     */
    @XmlElementRef(name = "ellipsoidalCS", namespace = "http://www.opengis.net/gml", type = JAXBElement.class, required = false)
    protected JAXBElement<EllipsoidalCSPropertyType> ellipsoidalCS;
    /**
     * gml:cartesianCS is an association role to the Cartesian coordinate system used by this CRS.
     * 
     */
    @XmlElementRef(name = "cartesianCS", namespace = "http://www.opengis.net/gml", type = JAXBElement.class, required = false)
    protected JAXBElement<CartesianCSPropertyType> cartesianCS;
    /**
     * gml:sphericalCS is an association role to the spherical coordinate system used by this CRS.
     * 
     */
    @XmlElementRef(name = "sphericalCS", namespace = "http://www.opengis.net/gml", type = JAXBElement.class, required = false)
    protected JAXBElement<SphericalCSPropertyType> sphericalCS;
    /**
     * gml:geodeticDatum is an association role to the geodetic datum used by this CRS.
     * 
     */
    @XmlElementRef(name = "geodeticDatum", namespace = "http://www.opengis.net/gml", type = JAXBElement.class)
    protected JAXBElement<GeodeticDatumPropertyType> geodeticDatum;

    /**
     * gml:ellipsoidalCS is an association role to the ellipsoidal coordinate system used by this CRS.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link EllipsoidalCSPropertyType }{@code >}
     *     {@link JAXBElement }{@code <}{@link EllipsoidalCSPropertyType }{@code >}
     *     
     */
    public JAXBElement<EllipsoidalCSPropertyType> getEllipsoidalCS() {
        return ellipsoidalCS;
    }

    /**
     * Imposta il valore della proprietà ellipsoidalCS.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link EllipsoidalCSPropertyType }{@code >}
     *     {@link JAXBElement }{@code <}{@link EllipsoidalCSPropertyType }{@code >}
     *     
     * @see #getEllipsoidalCS()
     */
    public void setEllipsoidalCS(JAXBElement<EllipsoidalCSPropertyType> value) {
        this.ellipsoidalCS = value;
    }

    public boolean isSetEllipsoidalCS() {
        return (this.ellipsoidalCS!= null);
    }

    /**
     * gml:cartesianCS is an association role to the Cartesian coordinate system used by this CRS.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link CartesianCSPropertyType }{@code >}
     *     {@link JAXBElement }{@code <}{@link CartesianCSPropertyType }{@code >}
     *     
     */
    public JAXBElement<CartesianCSPropertyType> getCartesianCS() {
        return cartesianCS;
    }

    /**
     * Imposta il valore della proprietà cartesianCS.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link CartesianCSPropertyType }{@code >}
     *     {@link JAXBElement }{@code <}{@link CartesianCSPropertyType }{@code >}
     *     
     * @see #getCartesianCS()
     */
    public void setCartesianCS(JAXBElement<CartesianCSPropertyType> value) {
        this.cartesianCS = value;
    }

    public boolean isSetCartesianCS() {
        return (this.cartesianCS!= null);
    }

    /**
     * gml:sphericalCS is an association role to the spherical coordinate system used by this CRS.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link SphericalCSPropertyType }{@code >}
     *     {@link JAXBElement }{@code <}{@link SphericalCSPropertyType }{@code >}
     *     
     */
    public JAXBElement<SphericalCSPropertyType> getSphericalCS() {
        return sphericalCS;
    }

    /**
     * Imposta il valore della proprietà sphericalCS.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link SphericalCSPropertyType }{@code >}
     *     {@link JAXBElement }{@code <}{@link SphericalCSPropertyType }{@code >}
     *     
     * @see #getSphericalCS()
     */
    public void setSphericalCS(JAXBElement<SphericalCSPropertyType> value) {
        this.sphericalCS = value;
    }

    public boolean isSetSphericalCS() {
        return (this.sphericalCS!= null);
    }

    /**
     * gml:geodeticDatum is an association role to the geodetic datum used by this CRS.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link GeodeticDatumPropertyType }{@code >}
     *     {@link JAXBElement }{@code <}{@link GeodeticDatumPropertyType }{@code >}
     *     
     */
    public JAXBElement<GeodeticDatumPropertyType> getGeodeticDatum() {
        return geodeticDatum;
    }

    /**
     * Imposta il valore della proprietà geodeticDatum.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link GeodeticDatumPropertyType }{@code >}
     *     {@link JAXBElement }{@code <}{@link GeodeticDatumPropertyType }{@code >}
     *     
     * @see #getGeodeticDatum()
     */
    public void setGeodeticDatum(JAXBElement<GeodeticDatumPropertyType> value) {
        this.geodeticDatum = value;
    }

    public boolean isSetGeodeticDatum() {
        return (this.geodeticDatum!= null);
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
            JAXBElement<EllipsoidalCSPropertyType> theEllipsoidalCS;
            theEllipsoidalCS = this.getEllipsoidalCS();
            strategy.appendField(locator, this, "ellipsoidalCS", buffer, theEllipsoidalCS, this.isSetEllipsoidalCS());
        }
        {
            JAXBElement<CartesianCSPropertyType> theCartesianCS;
            theCartesianCS = this.getCartesianCS();
            strategy.appendField(locator, this, "cartesianCS", buffer, theCartesianCS, this.isSetCartesianCS());
        }
        {
            JAXBElement<SphericalCSPropertyType> theSphericalCS;
            theSphericalCS = this.getSphericalCS();
            strategy.appendField(locator, this, "sphericalCS", buffer, theSphericalCS, this.isSetSphericalCS());
        }
        {
            JAXBElement<GeodeticDatumPropertyType> theGeodeticDatum;
            theGeodeticDatum = this.getGeodeticDatum();
            strategy.appendField(locator, this, "geodeticDatum", buffer, theGeodeticDatum, this.isSetGeodeticDatum());
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
        final GeodeticCRSType that = ((GeodeticCRSType) object);
        {
            JAXBElement<EllipsoidalCSPropertyType> lhsEllipsoidalCS;
            lhsEllipsoidalCS = this.getEllipsoidalCS();
            JAXBElement<EllipsoidalCSPropertyType> rhsEllipsoidalCS;
            rhsEllipsoidalCS = that.getEllipsoidalCS();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "ellipsoidalCS", lhsEllipsoidalCS), LocatorUtils.property(thatLocator, "ellipsoidalCS", rhsEllipsoidalCS), lhsEllipsoidalCS, rhsEllipsoidalCS, this.isSetEllipsoidalCS(), that.isSetEllipsoidalCS())) {
                return false;
            }
        }
        {
            JAXBElement<CartesianCSPropertyType> lhsCartesianCS;
            lhsCartesianCS = this.getCartesianCS();
            JAXBElement<CartesianCSPropertyType> rhsCartesianCS;
            rhsCartesianCS = that.getCartesianCS();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "cartesianCS", lhsCartesianCS), LocatorUtils.property(thatLocator, "cartesianCS", rhsCartesianCS), lhsCartesianCS, rhsCartesianCS, this.isSetCartesianCS(), that.isSetCartesianCS())) {
                return false;
            }
        }
        {
            JAXBElement<SphericalCSPropertyType> lhsSphericalCS;
            lhsSphericalCS = this.getSphericalCS();
            JAXBElement<SphericalCSPropertyType> rhsSphericalCS;
            rhsSphericalCS = that.getSphericalCS();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "sphericalCS", lhsSphericalCS), LocatorUtils.property(thatLocator, "sphericalCS", rhsSphericalCS), lhsSphericalCS, rhsSphericalCS, this.isSetSphericalCS(), that.isSetSphericalCS())) {
                return false;
            }
        }
        {
            JAXBElement<GeodeticDatumPropertyType> lhsGeodeticDatum;
            lhsGeodeticDatum = this.getGeodeticDatum();
            JAXBElement<GeodeticDatumPropertyType> rhsGeodeticDatum;
            rhsGeodeticDatum = that.getGeodeticDatum();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "geodeticDatum", lhsGeodeticDatum), LocatorUtils.property(thatLocator, "geodeticDatum", rhsGeodeticDatum), lhsGeodeticDatum, rhsGeodeticDatum, this.isSetGeodeticDatum(), that.isSetGeodeticDatum())) {
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
            JAXBElement<EllipsoidalCSPropertyType> theEllipsoidalCS;
            theEllipsoidalCS = this.getEllipsoidalCS();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "ellipsoidalCS", theEllipsoidalCS), currentHashCode, theEllipsoidalCS, this.isSetEllipsoidalCS());
        }
        {
            JAXBElement<CartesianCSPropertyType> theCartesianCS;
            theCartesianCS = this.getCartesianCS();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "cartesianCS", theCartesianCS), currentHashCode, theCartesianCS, this.isSetCartesianCS());
        }
        {
            JAXBElement<SphericalCSPropertyType> theSphericalCS;
            theSphericalCS = this.getSphericalCS();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "sphericalCS", theSphericalCS), currentHashCode, theSphericalCS, this.isSetSphericalCS());
        }
        {
            JAXBElement<GeodeticDatumPropertyType> theGeodeticDatum;
            theGeodeticDatum = this.getGeodeticDatum();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "geodeticDatum", theGeodeticDatum), currentHashCode, theGeodeticDatum, this.isSetGeodeticDatum());
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
        if (draftCopy instanceof GeodeticCRSType) {
            final GeodeticCRSType copy = ((GeodeticCRSType) draftCopy);
            {
                java.lang.Boolean ellipsoidalCSShouldBeCopiedAndSet = strategy.shouldBeCopiedAndSet(locator, this.isSetEllipsoidalCS());
                if (ellipsoidalCSShouldBeCopiedAndSet == java.lang.Boolean.TRUE) {
                    JAXBElement<EllipsoidalCSPropertyType> sourceEllipsoidalCS;
                    sourceEllipsoidalCS = this.getEllipsoidalCS();
                    @SuppressWarnings("unchecked")
                    JAXBElement<EllipsoidalCSPropertyType> copyEllipsoidalCS = ((JAXBElement<EllipsoidalCSPropertyType> ) strategy.copy(LocatorUtils.property(locator, "ellipsoidalCS", sourceEllipsoidalCS), sourceEllipsoidalCS, this.isSetEllipsoidalCS()));
                    copy.setEllipsoidalCS(copyEllipsoidalCS);
                } else {
                    if (ellipsoidalCSShouldBeCopiedAndSet == java.lang.Boolean.FALSE) {
                        copy.ellipsoidalCS = null;
                    }
                }
            }
            {
                java.lang.Boolean cartesianCSShouldBeCopiedAndSet = strategy.shouldBeCopiedAndSet(locator, this.isSetCartesianCS());
                if (cartesianCSShouldBeCopiedAndSet == java.lang.Boolean.TRUE) {
                    JAXBElement<CartesianCSPropertyType> sourceCartesianCS;
                    sourceCartesianCS = this.getCartesianCS();
                    @SuppressWarnings("unchecked")
                    JAXBElement<CartesianCSPropertyType> copyCartesianCS = ((JAXBElement<CartesianCSPropertyType> ) strategy.copy(LocatorUtils.property(locator, "cartesianCS", sourceCartesianCS), sourceCartesianCS, this.isSetCartesianCS()));
                    copy.setCartesianCS(copyCartesianCS);
                } else {
                    if (cartesianCSShouldBeCopiedAndSet == java.lang.Boolean.FALSE) {
                        copy.cartesianCS = null;
                    }
                }
            }
            {
                java.lang.Boolean sphericalCSShouldBeCopiedAndSet = strategy.shouldBeCopiedAndSet(locator, this.isSetSphericalCS());
                if (sphericalCSShouldBeCopiedAndSet == java.lang.Boolean.TRUE) {
                    JAXBElement<SphericalCSPropertyType> sourceSphericalCS;
                    sourceSphericalCS = this.getSphericalCS();
                    @SuppressWarnings("unchecked")
                    JAXBElement<SphericalCSPropertyType> copySphericalCS = ((JAXBElement<SphericalCSPropertyType> ) strategy.copy(LocatorUtils.property(locator, "sphericalCS", sourceSphericalCS), sourceSphericalCS, this.isSetSphericalCS()));
                    copy.setSphericalCS(copySphericalCS);
                } else {
                    if (sphericalCSShouldBeCopiedAndSet == java.lang.Boolean.FALSE) {
                        copy.sphericalCS = null;
                    }
                }
            }
            {
                java.lang.Boolean geodeticDatumShouldBeCopiedAndSet = strategy.shouldBeCopiedAndSet(locator, this.isSetGeodeticDatum());
                if (geodeticDatumShouldBeCopiedAndSet == java.lang.Boolean.TRUE) {
                    JAXBElement<GeodeticDatumPropertyType> sourceGeodeticDatum;
                    sourceGeodeticDatum = this.getGeodeticDatum();
                    @SuppressWarnings("unchecked")
                    JAXBElement<GeodeticDatumPropertyType> copyGeodeticDatum = ((JAXBElement<GeodeticDatumPropertyType> ) strategy.copy(LocatorUtils.property(locator, "geodeticDatum", sourceGeodeticDatum), sourceGeodeticDatum, this.isSetGeodeticDatum()));
                    copy.setGeodeticDatum(copyGeodeticDatum);
                } else {
                    if (geodeticDatumShouldBeCopiedAndSet == java.lang.Boolean.FALSE) {
                        copy.geodeticDatum = null;
                    }
                }
            }
        }
        return draftCopy;
    }

    @Override
    public Object createNewInstance() {
        return new GeodeticCRSType();
    }

    @Override
    public void mergeFrom(Object left, Object right) {
        final MergeStrategy strategy = JAXBMergeStrategy.getInstance();
        mergeFrom(null, null, left, right, strategy);
    }

    @Override
    public void mergeFrom(ObjectLocator leftLocator, ObjectLocator rightLocator, Object left, Object right, MergeStrategy strategy) {
        super.mergeFrom(leftLocator, rightLocator, left, right, strategy);
        if (right instanceof GeodeticCRSType) {
            final GeodeticCRSType target = this;
            final GeodeticCRSType leftObject = ((GeodeticCRSType) left);
            final GeodeticCRSType rightObject = ((GeodeticCRSType) right);
            {
                java.lang.Boolean ellipsoidalCSShouldBeMergedAndSet = strategy.shouldBeMergedAndSet(leftLocator, rightLocator, leftObject.isSetEllipsoidalCS(), rightObject.isSetEllipsoidalCS());
                if (ellipsoidalCSShouldBeMergedAndSet == java.lang.Boolean.TRUE) {
                    JAXBElement<EllipsoidalCSPropertyType> lhsEllipsoidalCS;
                    lhsEllipsoidalCS = leftObject.getEllipsoidalCS();
                    JAXBElement<EllipsoidalCSPropertyType> rhsEllipsoidalCS;
                    rhsEllipsoidalCS = rightObject.getEllipsoidalCS();
                    JAXBElement<EllipsoidalCSPropertyType> mergedEllipsoidalCS = ((JAXBElement<EllipsoidalCSPropertyType> ) strategy.merge(LocatorUtils.property(leftLocator, "ellipsoidalCS", lhsEllipsoidalCS), LocatorUtils.property(rightLocator, "ellipsoidalCS", rhsEllipsoidalCS), lhsEllipsoidalCS, rhsEllipsoidalCS, leftObject.isSetEllipsoidalCS(), rightObject.isSetEllipsoidalCS()));
                    target.setEllipsoidalCS(mergedEllipsoidalCS);
                } else {
                    if (ellipsoidalCSShouldBeMergedAndSet == java.lang.Boolean.FALSE) {
                        target.ellipsoidalCS = null;
                    }
                }
            }
            {
                java.lang.Boolean cartesianCSShouldBeMergedAndSet = strategy.shouldBeMergedAndSet(leftLocator, rightLocator, leftObject.isSetCartesianCS(), rightObject.isSetCartesianCS());
                if (cartesianCSShouldBeMergedAndSet == java.lang.Boolean.TRUE) {
                    JAXBElement<CartesianCSPropertyType> lhsCartesianCS;
                    lhsCartesianCS = leftObject.getCartesianCS();
                    JAXBElement<CartesianCSPropertyType> rhsCartesianCS;
                    rhsCartesianCS = rightObject.getCartesianCS();
                    JAXBElement<CartesianCSPropertyType> mergedCartesianCS = ((JAXBElement<CartesianCSPropertyType> ) strategy.merge(LocatorUtils.property(leftLocator, "cartesianCS", lhsCartesianCS), LocatorUtils.property(rightLocator, "cartesianCS", rhsCartesianCS), lhsCartesianCS, rhsCartesianCS, leftObject.isSetCartesianCS(), rightObject.isSetCartesianCS()));
                    target.setCartesianCS(mergedCartesianCS);
                } else {
                    if (cartesianCSShouldBeMergedAndSet == java.lang.Boolean.FALSE) {
                        target.cartesianCS = null;
                    }
                }
            }
            {
                java.lang.Boolean sphericalCSShouldBeMergedAndSet = strategy.shouldBeMergedAndSet(leftLocator, rightLocator, leftObject.isSetSphericalCS(), rightObject.isSetSphericalCS());
                if (sphericalCSShouldBeMergedAndSet == java.lang.Boolean.TRUE) {
                    JAXBElement<SphericalCSPropertyType> lhsSphericalCS;
                    lhsSphericalCS = leftObject.getSphericalCS();
                    JAXBElement<SphericalCSPropertyType> rhsSphericalCS;
                    rhsSphericalCS = rightObject.getSphericalCS();
                    JAXBElement<SphericalCSPropertyType> mergedSphericalCS = ((JAXBElement<SphericalCSPropertyType> ) strategy.merge(LocatorUtils.property(leftLocator, "sphericalCS", lhsSphericalCS), LocatorUtils.property(rightLocator, "sphericalCS", rhsSphericalCS), lhsSphericalCS, rhsSphericalCS, leftObject.isSetSphericalCS(), rightObject.isSetSphericalCS()));
                    target.setSphericalCS(mergedSphericalCS);
                } else {
                    if (sphericalCSShouldBeMergedAndSet == java.lang.Boolean.FALSE) {
                        target.sphericalCS = null;
                    }
                }
            }
            {
                java.lang.Boolean geodeticDatumShouldBeMergedAndSet = strategy.shouldBeMergedAndSet(leftLocator, rightLocator, leftObject.isSetGeodeticDatum(), rightObject.isSetGeodeticDatum());
                if (geodeticDatumShouldBeMergedAndSet == java.lang.Boolean.TRUE) {
                    JAXBElement<GeodeticDatumPropertyType> lhsGeodeticDatum;
                    lhsGeodeticDatum = leftObject.getGeodeticDatum();
                    JAXBElement<GeodeticDatumPropertyType> rhsGeodeticDatum;
                    rhsGeodeticDatum = rightObject.getGeodeticDatum();
                    JAXBElement<GeodeticDatumPropertyType> mergedGeodeticDatum = ((JAXBElement<GeodeticDatumPropertyType> ) strategy.merge(LocatorUtils.property(leftLocator, "geodeticDatum", lhsGeodeticDatum), LocatorUtils.property(rightLocator, "geodeticDatum", rhsGeodeticDatum), lhsGeodeticDatum, rhsGeodeticDatum, leftObject.isSetGeodeticDatum(), rightObject.isSetGeodeticDatum()));
                    target.setGeodeticDatum(mergedGeodeticDatum);
                } else {
                    if (geodeticDatumShouldBeMergedAndSet == java.lang.Boolean.FALSE) {
                        target.geodeticDatum = null;
                    }
                }
            }
        }
    }

}
