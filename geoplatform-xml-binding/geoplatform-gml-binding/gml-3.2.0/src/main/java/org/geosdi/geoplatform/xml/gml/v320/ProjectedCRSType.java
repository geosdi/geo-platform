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
 * <p>Classe Java per ProjectedCRSType complex type.</p>
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.</p>
 * 
 * <pre>{@code
 * <complexType name="ProjectedCRSType">
 *   <complexContent>
 *     <extension base="{http://www.opengis.net/gml}AbstractGeneralDerivedCRSType">
 *       <sequence>
 *         <choice>
 *           <element ref="{http://www.opengis.net/gml}baseGeodeticCRS"/>
 *           <element ref="{http://www.opengis.net/gml}baseGeographicCRS"/>
 *         </choice>
 *         <element ref="{http://www.opengis.net/gml}cartesianCS"/>
 *       </sequence>
 *     </extension>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ProjectedCRSType", propOrder = {
    "baseGeodeticCRS",
    "baseGeographicCRS",
    "cartesianCS"
})
public class ProjectedCRSType
    extends AbstractGeneralDerivedCRSType
    implements Cloneable, CopyTo, Equals, HashCode, MergeFrom, ToString
{

    /**
     * gml:baseGeodeticCRS is an association role to the geodetic coordinate reference system used by this projected CRS.
     * 
     */
    protected GeodeticCRSPropertyType baseGeodeticCRS;
    protected GeographicCRSPropertyType baseGeographicCRS;
    /**
     * gml:cartesianCS is an association role to the Cartesian coordinate system used by this CRS.
     * 
     */
    @XmlElementRef(name = "cartesianCS", namespace = "http://www.opengis.net/gml", type = JAXBElement.class)
    protected JAXBElement<CartesianCSPropertyType> cartesianCS;

    /**
     * gml:baseGeodeticCRS is an association role to the geodetic coordinate reference system used by this projected CRS.
     * 
     * @return
     *     possible object is
     *     {@link GeodeticCRSPropertyType }
     *     
     */
    public GeodeticCRSPropertyType getBaseGeodeticCRS() {
        return baseGeodeticCRS;
    }

    /**
     * Imposta il valore della proprietà baseGeodeticCRS.
     * 
     * @param value
     *     allowed object is
     *     {@link GeodeticCRSPropertyType }
     *     
     * @see #getBaseGeodeticCRS()
     */
    public void setBaseGeodeticCRS(GeodeticCRSPropertyType value) {
        this.baseGeodeticCRS = value;
    }

    public boolean isSetBaseGeodeticCRS() {
        return (this.baseGeodeticCRS!= null);
    }

    /**
     * Recupera il valore della proprietà baseGeographicCRS.
     * 
     * @return
     *     possible object is
     *     {@link GeographicCRSPropertyType }
     *     
     */
    public GeographicCRSPropertyType getBaseGeographicCRS() {
        return baseGeographicCRS;
    }

    /**
     * Imposta il valore della proprietà baseGeographicCRS.
     * 
     * @param value
     *     allowed object is
     *     {@link GeographicCRSPropertyType }
     *     
     */
    public void setBaseGeographicCRS(GeographicCRSPropertyType value) {
        this.baseGeographicCRS = value;
    }

    public boolean isSetBaseGeographicCRS() {
        return (this.baseGeographicCRS!= null);
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
            GeodeticCRSPropertyType theBaseGeodeticCRS;
            theBaseGeodeticCRS = this.getBaseGeodeticCRS();
            strategy.appendField(locator, this, "baseGeodeticCRS", buffer, theBaseGeodeticCRS, this.isSetBaseGeodeticCRS());
        }
        {
            GeographicCRSPropertyType theBaseGeographicCRS;
            theBaseGeographicCRS = this.getBaseGeographicCRS();
            strategy.appendField(locator, this, "baseGeographicCRS", buffer, theBaseGeographicCRS, this.isSetBaseGeographicCRS());
        }
        {
            JAXBElement<CartesianCSPropertyType> theCartesianCS;
            theCartesianCS = this.getCartesianCS();
            strategy.appendField(locator, this, "cartesianCS", buffer, theCartesianCS, this.isSetCartesianCS());
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
        final ProjectedCRSType that = ((ProjectedCRSType) object);
        {
            GeodeticCRSPropertyType lhsBaseGeodeticCRS;
            lhsBaseGeodeticCRS = this.getBaseGeodeticCRS();
            GeodeticCRSPropertyType rhsBaseGeodeticCRS;
            rhsBaseGeodeticCRS = that.getBaseGeodeticCRS();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "baseGeodeticCRS", lhsBaseGeodeticCRS), LocatorUtils.property(thatLocator, "baseGeodeticCRS", rhsBaseGeodeticCRS), lhsBaseGeodeticCRS, rhsBaseGeodeticCRS, this.isSetBaseGeodeticCRS(), that.isSetBaseGeodeticCRS())) {
                return false;
            }
        }
        {
            GeographicCRSPropertyType lhsBaseGeographicCRS;
            lhsBaseGeographicCRS = this.getBaseGeographicCRS();
            GeographicCRSPropertyType rhsBaseGeographicCRS;
            rhsBaseGeographicCRS = that.getBaseGeographicCRS();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "baseGeographicCRS", lhsBaseGeographicCRS), LocatorUtils.property(thatLocator, "baseGeographicCRS", rhsBaseGeographicCRS), lhsBaseGeographicCRS, rhsBaseGeographicCRS, this.isSetBaseGeographicCRS(), that.isSetBaseGeographicCRS())) {
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
            GeodeticCRSPropertyType theBaseGeodeticCRS;
            theBaseGeodeticCRS = this.getBaseGeodeticCRS();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "baseGeodeticCRS", theBaseGeodeticCRS), currentHashCode, theBaseGeodeticCRS, this.isSetBaseGeodeticCRS());
        }
        {
            GeographicCRSPropertyType theBaseGeographicCRS;
            theBaseGeographicCRS = this.getBaseGeographicCRS();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "baseGeographicCRS", theBaseGeographicCRS), currentHashCode, theBaseGeographicCRS, this.isSetBaseGeographicCRS());
        }
        {
            JAXBElement<CartesianCSPropertyType> theCartesianCS;
            theCartesianCS = this.getCartesianCS();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "cartesianCS", theCartesianCS), currentHashCode, theCartesianCS, this.isSetCartesianCS());
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
        if (draftCopy instanceof ProjectedCRSType) {
            final ProjectedCRSType copy = ((ProjectedCRSType) draftCopy);
            {
                java.lang.Boolean baseGeodeticCRSShouldBeCopiedAndSet = strategy.shouldBeCopiedAndSet(locator, this.isSetBaseGeodeticCRS());
                if (baseGeodeticCRSShouldBeCopiedAndSet == java.lang.Boolean.TRUE) {
                    GeodeticCRSPropertyType sourceBaseGeodeticCRS;
                    sourceBaseGeodeticCRS = this.getBaseGeodeticCRS();
                    GeodeticCRSPropertyType copyBaseGeodeticCRS = ((GeodeticCRSPropertyType) strategy.copy(LocatorUtils.property(locator, "baseGeodeticCRS", sourceBaseGeodeticCRS), sourceBaseGeodeticCRS, this.isSetBaseGeodeticCRS()));
                    copy.setBaseGeodeticCRS(copyBaseGeodeticCRS);
                } else {
                    if (baseGeodeticCRSShouldBeCopiedAndSet == java.lang.Boolean.FALSE) {
                        copy.baseGeodeticCRS = null;
                    }
                }
            }
            {
                java.lang.Boolean baseGeographicCRSShouldBeCopiedAndSet = strategy.shouldBeCopiedAndSet(locator, this.isSetBaseGeographicCRS());
                if (baseGeographicCRSShouldBeCopiedAndSet == java.lang.Boolean.TRUE) {
                    GeographicCRSPropertyType sourceBaseGeographicCRS;
                    sourceBaseGeographicCRS = this.getBaseGeographicCRS();
                    GeographicCRSPropertyType copyBaseGeographicCRS = ((GeographicCRSPropertyType) strategy.copy(LocatorUtils.property(locator, "baseGeographicCRS", sourceBaseGeographicCRS), sourceBaseGeographicCRS, this.isSetBaseGeographicCRS()));
                    copy.setBaseGeographicCRS(copyBaseGeographicCRS);
                } else {
                    if (baseGeographicCRSShouldBeCopiedAndSet == java.lang.Boolean.FALSE) {
                        copy.baseGeographicCRS = null;
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
        }
        return draftCopy;
    }

    @Override
    public Object createNewInstance() {
        return new ProjectedCRSType();
    }

    @Override
    public void mergeFrom(Object left, Object right) {
        final MergeStrategy strategy = JAXBMergeStrategy.getInstance();
        mergeFrom(null, null, left, right, strategy);
    }

    @Override
    public void mergeFrom(ObjectLocator leftLocator, ObjectLocator rightLocator, Object left, Object right, MergeStrategy strategy) {
        super.mergeFrom(leftLocator, rightLocator, left, right, strategy);
        if (right instanceof ProjectedCRSType) {
            final ProjectedCRSType target = this;
            final ProjectedCRSType leftObject = ((ProjectedCRSType) left);
            final ProjectedCRSType rightObject = ((ProjectedCRSType) right);
            {
                java.lang.Boolean baseGeodeticCRSShouldBeMergedAndSet = strategy.shouldBeMergedAndSet(leftLocator, rightLocator, leftObject.isSetBaseGeodeticCRS(), rightObject.isSetBaseGeodeticCRS());
                if (baseGeodeticCRSShouldBeMergedAndSet == java.lang.Boolean.TRUE) {
                    GeodeticCRSPropertyType lhsBaseGeodeticCRS;
                    lhsBaseGeodeticCRS = leftObject.getBaseGeodeticCRS();
                    GeodeticCRSPropertyType rhsBaseGeodeticCRS;
                    rhsBaseGeodeticCRS = rightObject.getBaseGeodeticCRS();
                    GeodeticCRSPropertyType mergedBaseGeodeticCRS = ((GeodeticCRSPropertyType) strategy.merge(LocatorUtils.property(leftLocator, "baseGeodeticCRS", lhsBaseGeodeticCRS), LocatorUtils.property(rightLocator, "baseGeodeticCRS", rhsBaseGeodeticCRS), lhsBaseGeodeticCRS, rhsBaseGeodeticCRS, leftObject.isSetBaseGeodeticCRS(), rightObject.isSetBaseGeodeticCRS()));
                    target.setBaseGeodeticCRS(mergedBaseGeodeticCRS);
                } else {
                    if (baseGeodeticCRSShouldBeMergedAndSet == java.lang.Boolean.FALSE) {
                        target.baseGeodeticCRS = null;
                    }
                }
            }
            {
                java.lang.Boolean baseGeographicCRSShouldBeMergedAndSet = strategy.shouldBeMergedAndSet(leftLocator, rightLocator, leftObject.isSetBaseGeographicCRS(), rightObject.isSetBaseGeographicCRS());
                if (baseGeographicCRSShouldBeMergedAndSet == java.lang.Boolean.TRUE) {
                    GeographicCRSPropertyType lhsBaseGeographicCRS;
                    lhsBaseGeographicCRS = leftObject.getBaseGeographicCRS();
                    GeographicCRSPropertyType rhsBaseGeographicCRS;
                    rhsBaseGeographicCRS = rightObject.getBaseGeographicCRS();
                    GeographicCRSPropertyType mergedBaseGeographicCRS = ((GeographicCRSPropertyType) strategy.merge(LocatorUtils.property(leftLocator, "baseGeographicCRS", lhsBaseGeographicCRS), LocatorUtils.property(rightLocator, "baseGeographicCRS", rhsBaseGeographicCRS), lhsBaseGeographicCRS, rhsBaseGeographicCRS, leftObject.isSetBaseGeographicCRS(), rightObject.isSetBaseGeographicCRS()));
                    target.setBaseGeographicCRS(mergedBaseGeographicCRS);
                } else {
                    if (baseGeographicCRSShouldBeMergedAndSet == java.lang.Boolean.FALSE) {
                        target.baseGeographicCRS = null;
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
        }
    }

}
