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
 * <p>Classe Java per DerivedCRSType complex type.</p>
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.</p>
 * 
 * <pre>{@code
 * <complexType name="DerivedCRSType">
 *   <complexContent>
 *     <extension base="{http://www.opengis.net/gml}AbstractGeneralDerivedCRSType">
 *       <sequence>
 *         <element ref="{http://www.opengis.net/gml}baseCRS"/>
 *         <element ref="{http://www.opengis.net/gml}derivedCRSType"/>
 *         <element ref="{http://www.opengis.net/gml}coordinateSystem"/>
 *       </sequence>
 *     </extension>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DerivedCRSType", propOrder = {
    "baseCRS",
    "derivedCRSType",
    "coordinateSystem"
})
public class DerivedCRSType
    extends AbstractGeneralDerivedCRSType
    implements Cloneable, CopyTo, Equals, HashCode, MergeFrom, ToString
{

    /**
     * gml:baseCRS is an association role to the coordinate reference system used by this derived CRS.
     * 
     */
    @XmlElement(required = true)
    protected SingleCRSPropertyType baseCRS;
    /**
     * The gml:derivedCRSType property describes the type of a derived coordinate reference system. The required codeSpace attribute shall reference a source of information specifying the values and meanings of all the allowed string values for this property.
     * 
     */
    @XmlElement(required = true)
    protected CodeWithAuthorityType derivedCRSType;
    /**
     * An association role to the coordinate system used by this CRS.
     * 
     */
    @XmlElementRef(name = "coordinateSystem", namespace = "http://www.opengis.net/gml", type = JAXBElement.class)
    protected JAXBElement<CoordinateSystemPropertyType> coordinateSystem;

    /**
     * gml:baseCRS is an association role to the coordinate reference system used by this derived CRS.
     * 
     * @return
     *     possible object is
     *     {@link SingleCRSPropertyType }
     *     
     */
    public SingleCRSPropertyType getBaseCRS() {
        return baseCRS;
    }

    /**
     * Imposta il valore della proprietà baseCRS.
     * 
     * @param value
     *     allowed object is
     *     {@link SingleCRSPropertyType }
     *     
     * @see #getBaseCRS()
     */
    public void setBaseCRS(SingleCRSPropertyType value) {
        this.baseCRS = value;
    }

    public boolean isSetBaseCRS() {
        return (this.baseCRS!= null);
    }

    /**
     * The gml:derivedCRSType property describes the type of a derived coordinate reference system. The required codeSpace attribute shall reference a source of information specifying the values and meanings of all the allowed string values for this property.
     * 
     * @return
     *     possible object is
     *     {@link CodeWithAuthorityType }
     *     
     */
    public CodeWithAuthorityType getDerivedCRSType() {
        return derivedCRSType;
    }

    /**
     * Imposta il valore della proprietà derivedCRSType.
     * 
     * @param value
     *     allowed object is
     *     {@link CodeWithAuthorityType }
     *     
     * @see #getDerivedCRSType()
     */
    public void setDerivedCRSType(CodeWithAuthorityType value) {
        this.derivedCRSType = value;
    }

    public boolean isSetDerivedCRSType() {
        return (this.derivedCRSType!= null);
    }

    /**
     * An association role to the coordinate system used by this CRS.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link CoordinateSystemPropertyType }{@code >}
     *     {@link JAXBElement }{@code <}{@link CoordinateSystemPropertyType }{@code >}
     *     
     */
    public JAXBElement<CoordinateSystemPropertyType> getCoordinateSystem() {
        return coordinateSystem;
    }

    /**
     * Imposta il valore della proprietà coordinateSystem.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link CoordinateSystemPropertyType }{@code >}
     *     {@link JAXBElement }{@code <}{@link CoordinateSystemPropertyType }{@code >}
     *     
     * @see #getCoordinateSystem()
     */
    public void setCoordinateSystem(JAXBElement<CoordinateSystemPropertyType> value) {
        this.coordinateSystem = value;
    }

    public boolean isSetCoordinateSystem() {
        return (this.coordinateSystem!= null);
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
            SingleCRSPropertyType theBaseCRS;
            theBaseCRS = this.getBaseCRS();
            strategy.appendField(locator, this, "baseCRS", buffer, theBaseCRS, this.isSetBaseCRS());
        }
        {
            CodeWithAuthorityType theDerivedCRSType;
            theDerivedCRSType = this.getDerivedCRSType();
            strategy.appendField(locator, this, "derivedCRSType", buffer, theDerivedCRSType, this.isSetDerivedCRSType());
        }
        {
            JAXBElement<CoordinateSystemPropertyType> theCoordinateSystem;
            theCoordinateSystem = this.getCoordinateSystem();
            strategy.appendField(locator, this, "coordinateSystem", buffer, theCoordinateSystem, this.isSetCoordinateSystem());
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
        final DerivedCRSType that = ((DerivedCRSType) object);
        {
            SingleCRSPropertyType lhsBaseCRS;
            lhsBaseCRS = this.getBaseCRS();
            SingleCRSPropertyType rhsBaseCRS;
            rhsBaseCRS = that.getBaseCRS();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "baseCRS", lhsBaseCRS), LocatorUtils.property(thatLocator, "baseCRS", rhsBaseCRS), lhsBaseCRS, rhsBaseCRS, this.isSetBaseCRS(), that.isSetBaseCRS())) {
                return false;
            }
        }
        {
            CodeWithAuthorityType lhsDerivedCRSType;
            lhsDerivedCRSType = this.getDerivedCRSType();
            CodeWithAuthorityType rhsDerivedCRSType;
            rhsDerivedCRSType = that.getDerivedCRSType();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "derivedCRSType", lhsDerivedCRSType), LocatorUtils.property(thatLocator, "derivedCRSType", rhsDerivedCRSType), lhsDerivedCRSType, rhsDerivedCRSType, this.isSetDerivedCRSType(), that.isSetDerivedCRSType())) {
                return false;
            }
        }
        {
            JAXBElement<CoordinateSystemPropertyType> lhsCoordinateSystem;
            lhsCoordinateSystem = this.getCoordinateSystem();
            JAXBElement<CoordinateSystemPropertyType> rhsCoordinateSystem;
            rhsCoordinateSystem = that.getCoordinateSystem();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "coordinateSystem", lhsCoordinateSystem), LocatorUtils.property(thatLocator, "coordinateSystem", rhsCoordinateSystem), lhsCoordinateSystem, rhsCoordinateSystem, this.isSetCoordinateSystem(), that.isSetCoordinateSystem())) {
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
            SingleCRSPropertyType theBaseCRS;
            theBaseCRS = this.getBaseCRS();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "baseCRS", theBaseCRS), currentHashCode, theBaseCRS, this.isSetBaseCRS());
        }
        {
            CodeWithAuthorityType theDerivedCRSType;
            theDerivedCRSType = this.getDerivedCRSType();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "derivedCRSType", theDerivedCRSType), currentHashCode, theDerivedCRSType, this.isSetDerivedCRSType());
        }
        {
            JAXBElement<CoordinateSystemPropertyType> theCoordinateSystem;
            theCoordinateSystem = this.getCoordinateSystem();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "coordinateSystem", theCoordinateSystem), currentHashCode, theCoordinateSystem, this.isSetCoordinateSystem());
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
        if (draftCopy instanceof DerivedCRSType) {
            final DerivedCRSType copy = ((DerivedCRSType) draftCopy);
            {
                java.lang.Boolean baseCRSShouldBeCopiedAndSet = strategy.shouldBeCopiedAndSet(locator, this.isSetBaseCRS());
                if (baseCRSShouldBeCopiedAndSet == java.lang.Boolean.TRUE) {
                    SingleCRSPropertyType sourceBaseCRS;
                    sourceBaseCRS = this.getBaseCRS();
                    SingleCRSPropertyType copyBaseCRS = ((SingleCRSPropertyType) strategy.copy(LocatorUtils.property(locator, "baseCRS", sourceBaseCRS), sourceBaseCRS, this.isSetBaseCRS()));
                    copy.setBaseCRS(copyBaseCRS);
                } else {
                    if (baseCRSShouldBeCopiedAndSet == java.lang.Boolean.FALSE) {
                        copy.baseCRS = null;
                    }
                }
            }
            {
                java.lang.Boolean derivedCRSTypeShouldBeCopiedAndSet = strategy.shouldBeCopiedAndSet(locator, this.isSetDerivedCRSType());
                if (derivedCRSTypeShouldBeCopiedAndSet == java.lang.Boolean.TRUE) {
                    CodeWithAuthorityType sourceDerivedCRSType;
                    sourceDerivedCRSType = this.getDerivedCRSType();
                    CodeWithAuthorityType copyDerivedCRSType = ((CodeWithAuthorityType) strategy.copy(LocatorUtils.property(locator, "derivedCRSType", sourceDerivedCRSType), sourceDerivedCRSType, this.isSetDerivedCRSType()));
                    copy.setDerivedCRSType(copyDerivedCRSType);
                } else {
                    if (derivedCRSTypeShouldBeCopiedAndSet == java.lang.Boolean.FALSE) {
                        copy.derivedCRSType = null;
                    }
                }
            }
            {
                java.lang.Boolean coordinateSystemShouldBeCopiedAndSet = strategy.shouldBeCopiedAndSet(locator, this.isSetCoordinateSystem());
                if (coordinateSystemShouldBeCopiedAndSet == java.lang.Boolean.TRUE) {
                    JAXBElement<CoordinateSystemPropertyType> sourceCoordinateSystem;
                    sourceCoordinateSystem = this.getCoordinateSystem();
                    @SuppressWarnings("unchecked")
                    JAXBElement<CoordinateSystemPropertyType> copyCoordinateSystem = ((JAXBElement<CoordinateSystemPropertyType> ) strategy.copy(LocatorUtils.property(locator, "coordinateSystem", sourceCoordinateSystem), sourceCoordinateSystem, this.isSetCoordinateSystem()));
                    copy.setCoordinateSystem(copyCoordinateSystem);
                } else {
                    if (coordinateSystemShouldBeCopiedAndSet == java.lang.Boolean.FALSE) {
                        copy.coordinateSystem = null;
                    }
                }
            }
        }
        return draftCopy;
    }

    @Override
    public Object createNewInstance() {
        return new DerivedCRSType();
    }

    @Override
    public void mergeFrom(Object left, Object right) {
        final MergeStrategy strategy = JAXBMergeStrategy.getInstance();
        mergeFrom(null, null, left, right, strategy);
    }

    @Override
    public void mergeFrom(ObjectLocator leftLocator, ObjectLocator rightLocator, Object left, Object right, MergeStrategy strategy) {
        super.mergeFrom(leftLocator, rightLocator, left, right, strategy);
        if (right instanceof DerivedCRSType) {
            final DerivedCRSType target = this;
            final DerivedCRSType leftObject = ((DerivedCRSType) left);
            final DerivedCRSType rightObject = ((DerivedCRSType) right);
            {
                java.lang.Boolean baseCRSShouldBeMergedAndSet = strategy.shouldBeMergedAndSet(leftLocator, rightLocator, leftObject.isSetBaseCRS(), rightObject.isSetBaseCRS());
                if (baseCRSShouldBeMergedAndSet == java.lang.Boolean.TRUE) {
                    SingleCRSPropertyType lhsBaseCRS;
                    lhsBaseCRS = leftObject.getBaseCRS();
                    SingleCRSPropertyType rhsBaseCRS;
                    rhsBaseCRS = rightObject.getBaseCRS();
                    SingleCRSPropertyType mergedBaseCRS = ((SingleCRSPropertyType) strategy.merge(LocatorUtils.property(leftLocator, "baseCRS", lhsBaseCRS), LocatorUtils.property(rightLocator, "baseCRS", rhsBaseCRS), lhsBaseCRS, rhsBaseCRS, leftObject.isSetBaseCRS(), rightObject.isSetBaseCRS()));
                    target.setBaseCRS(mergedBaseCRS);
                } else {
                    if (baseCRSShouldBeMergedAndSet == java.lang.Boolean.FALSE) {
                        target.baseCRS = null;
                    }
                }
            }
            {
                java.lang.Boolean derivedCRSTypeShouldBeMergedAndSet = strategy.shouldBeMergedAndSet(leftLocator, rightLocator, leftObject.isSetDerivedCRSType(), rightObject.isSetDerivedCRSType());
                if (derivedCRSTypeShouldBeMergedAndSet == java.lang.Boolean.TRUE) {
                    CodeWithAuthorityType lhsDerivedCRSType;
                    lhsDerivedCRSType = leftObject.getDerivedCRSType();
                    CodeWithAuthorityType rhsDerivedCRSType;
                    rhsDerivedCRSType = rightObject.getDerivedCRSType();
                    CodeWithAuthorityType mergedDerivedCRSType = ((CodeWithAuthorityType) strategy.merge(LocatorUtils.property(leftLocator, "derivedCRSType", lhsDerivedCRSType), LocatorUtils.property(rightLocator, "derivedCRSType", rhsDerivedCRSType), lhsDerivedCRSType, rhsDerivedCRSType, leftObject.isSetDerivedCRSType(), rightObject.isSetDerivedCRSType()));
                    target.setDerivedCRSType(mergedDerivedCRSType);
                } else {
                    if (derivedCRSTypeShouldBeMergedAndSet == java.lang.Boolean.FALSE) {
                        target.derivedCRSType = null;
                    }
                }
            }
            {
                java.lang.Boolean coordinateSystemShouldBeMergedAndSet = strategy.shouldBeMergedAndSet(leftLocator, rightLocator, leftObject.isSetCoordinateSystem(), rightObject.isSetCoordinateSystem());
                if (coordinateSystemShouldBeMergedAndSet == java.lang.Boolean.TRUE) {
                    JAXBElement<CoordinateSystemPropertyType> lhsCoordinateSystem;
                    lhsCoordinateSystem = leftObject.getCoordinateSystem();
                    JAXBElement<CoordinateSystemPropertyType> rhsCoordinateSystem;
                    rhsCoordinateSystem = rightObject.getCoordinateSystem();
                    JAXBElement<CoordinateSystemPropertyType> mergedCoordinateSystem = ((JAXBElement<CoordinateSystemPropertyType> ) strategy.merge(LocatorUtils.property(leftLocator, "coordinateSystem", lhsCoordinateSystem), LocatorUtils.property(rightLocator, "coordinateSystem", rhsCoordinateSystem), lhsCoordinateSystem, rhsCoordinateSystem, leftObject.isSetCoordinateSystem(), rightObject.isSetCoordinateSystem()));
                    target.setCoordinateSystem(mergedCoordinateSystem);
                } else {
                    if (coordinateSystemShouldBeMergedAndSet == java.lang.Boolean.FALSE) {
                        target.coordinateSystem = null;
                    }
                }
            }
        }
    }

}
