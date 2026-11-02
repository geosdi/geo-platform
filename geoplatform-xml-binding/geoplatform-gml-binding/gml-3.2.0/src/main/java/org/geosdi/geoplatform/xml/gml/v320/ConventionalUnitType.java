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

import java.util.ArrayList;
import java.util.List;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
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
 * <p>Classe Java per ConventionalUnitType complex type.</p>
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.</p>
 * 
 * <pre>{@code
 * <complexType name="ConventionalUnitType">
 *   <complexContent>
 *     <extension base="{http://www.opengis.net/gml}UnitDefinitionType">
 *       <sequence>
 *         <choice>
 *           <element ref="{http://www.opengis.net/gml}conversionToPreferredUnit"/>
 *           <element ref="{http://www.opengis.net/gml}roughConversionToPreferredUnit"/>
 *         </choice>
 *         <element ref="{http://www.opengis.net/gml}derivationUnitTerm" maxOccurs="unbounded" minOccurs="0"/>
 *       </sequence>
 *     </extension>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ConventionalUnitType", propOrder = {
    "conversionToPreferredUnit",
    "roughConversionToPreferredUnit",
    "derivationUnitTerm"
})
public class ConventionalUnitType
    extends UnitDefinitionType
    implements Cloneable, CopyTo, Equals, HashCode, MergeFrom, ToString
{

    /**
     * The elements gml:conversionToPreferredUnit and gml:roughConversionToPreferredUnit represent parameters used to convert conventional units to preferred units for this physical quantity type.  A preferred unit is either a Base Unit or a Derived Unit that is selected for all values of one physical quantity type.
     * 
     */
    protected ConversionToPreferredUnitType conversionToPreferredUnit;
    /**
     * The elements gml:conversionToPreferredUnit and gml:roughConversionToPreferredUnit represent parameters used to convert conventional units to preferred units for this physical quantity type.  A preferred unit is either a Base Unit or a Derived Unit that is selected for all values of one physical quantity type.
     * 
     */
    protected ConversionToPreferredUnitType roughConversionToPreferredUnit;
    /**
     * A set of gml:derivationUnitTerm elements describes a derived unit of measure.  Each element carries an integer exponent.  The terms are combined by raising each referenced unit to the power of its exponent and forming the product.
     * This unit term references another unit of measure (uom) and provides an integer exponent applied to that unit in defining the compound unit. The exponent may be positive or negative, but not zero.
     * 
     */
    protected List<DerivationUnitTermType> derivationUnitTerm;

    /**
     * The elements gml:conversionToPreferredUnit and gml:roughConversionToPreferredUnit represent parameters used to convert conventional units to preferred units for this physical quantity type.  A preferred unit is either a Base Unit or a Derived Unit that is selected for all values of one physical quantity type.
     * 
     * @return
     *     possible object is
     *     {@link ConversionToPreferredUnitType }
     *     
     */
    public ConversionToPreferredUnitType getConversionToPreferredUnit() {
        return conversionToPreferredUnit;
    }

    /**
     * Imposta il valore della proprietà conversionToPreferredUnit.
     * 
     * @param value
     *     allowed object is
     *     {@link ConversionToPreferredUnitType }
     *     
     * @see #getConversionToPreferredUnit()
     */
    public void setConversionToPreferredUnit(ConversionToPreferredUnitType value) {
        this.conversionToPreferredUnit = value;
    }

    public boolean isSetConversionToPreferredUnit() {
        return (this.conversionToPreferredUnit!= null);
    }

    /**
     * The elements gml:conversionToPreferredUnit and gml:roughConversionToPreferredUnit represent parameters used to convert conventional units to preferred units for this physical quantity type.  A preferred unit is either a Base Unit or a Derived Unit that is selected for all values of one physical quantity type.
     * 
     * @return
     *     possible object is
     *     {@link ConversionToPreferredUnitType }
     *     
     */
    public ConversionToPreferredUnitType getRoughConversionToPreferredUnit() {
        return roughConversionToPreferredUnit;
    }

    /**
     * Imposta il valore della proprietà roughConversionToPreferredUnit.
     * 
     * @param value
     *     allowed object is
     *     {@link ConversionToPreferredUnitType }
     *     
     * @see #getRoughConversionToPreferredUnit()
     */
    public void setRoughConversionToPreferredUnit(ConversionToPreferredUnitType value) {
        this.roughConversionToPreferredUnit = value;
    }

    public boolean isSetRoughConversionToPreferredUnit() {
        return (this.roughConversionToPreferredUnit!= null);
    }

    /**
     * A set of gml:derivationUnitTerm elements describes a derived unit of measure.  Each element carries an integer exponent.  The terms are combined by raising each referenced unit to the power of its exponent and forming the product.
     * This unit term references another unit of measure (uom) and provides an integer exponent applied to that unit in defining the compound unit. The exponent may be positive or negative, but not zero.
     * 
     * Gets the value of the derivationUnitTerm property.
     * 
     * <p>This accessor method returns a reference to the live list,
     * not a snapshot. Therefore, any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a {@code set} method for the derivationUnitTerm property.</p>
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * </p>
     * <pre>
     * getDerivationUnitTerm().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DerivationUnitTermType }
     * </p>
     * 
     * 
     * @return
     *     The value of the derivationUnitTerm property.
     */
    public List<DerivationUnitTermType> getDerivationUnitTerm() {
        if (derivationUnitTerm == null) {
            derivationUnitTerm = new ArrayList<>();
        }
        return this.derivationUnitTerm;
    }

    public boolean isSetDerivationUnitTerm() {
        return ((this.derivationUnitTerm!= null)&&(!this.derivationUnitTerm.isEmpty()));
    }

    public void unsetDerivationUnitTerm() {
        this.derivationUnitTerm = null;
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
            ConversionToPreferredUnitType theConversionToPreferredUnit;
            theConversionToPreferredUnit = this.getConversionToPreferredUnit();
            strategy.appendField(locator, this, "conversionToPreferredUnit", buffer, theConversionToPreferredUnit, this.isSetConversionToPreferredUnit());
        }
        {
            ConversionToPreferredUnitType theRoughConversionToPreferredUnit;
            theRoughConversionToPreferredUnit = this.getRoughConversionToPreferredUnit();
            strategy.appendField(locator, this, "roughConversionToPreferredUnit", buffer, theRoughConversionToPreferredUnit, this.isSetRoughConversionToPreferredUnit());
        }
        {
            List<DerivationUnitTermType> theDerivationUnitTerm;
            theDerivationUnitTerm = (this.isSetDerivationUnitTerm()?this.getDerivationUnitTerm():null);
            strategy.appendField(locator, this, "derivationUnitTerm", buffer, theDerivationUnitTerm, this.isSetDerivationUnitTerm());
        }
        return buffer;
    }

    public void setDerivationUnitTerm(List<DerivationUnitTermType> value) {
        this.derivationUnitTerm = null;
        if (value!= null) {
            List<DerivationUnitTermType> draftl = this.getDerivationUnitTerm();
            draftl.addAll(value);
        }
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
        final ConventionalUnitType that = ((ConventionalUnitType) object);
        {
            ConversionToPreferredUnitType lhsConversionToPreferredUnit;
            lhsConversionToPreferredUnit = this.getConversionToPreferredUnit();
            ConversionToPreferredUnitType rhsConversionToPreferredUnit;
            rhsConversionToPreferredUnit = that.getConversionToPreferredUnit();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "conversionToPreferredUnit", lhsConversionToPreferredUnit), LocatorUtils.property(thatLocator, "conversionToPreferredUnit", rhsConversionToPreferredUnit), lhsConversionToPreferredUnit, rhsConversionToPreferredUnit, this.isSetConversionToPreferredUnit(), that.isSetConversionToPreferredUnit())) {
                return false;
            }
        }
        {
            ConversionToPreferredUnitType lhsRoughConversionToPreferredUnit;
            lhsRoughConversionToPreferredUnit = this.getRoughConversionToPreferredUnit();
            ConversionToPreferredUnitType rhsRoughConversionToPreferredUnit;
            rhsRoughConversionToPreferredUnit = that.getRoughConversionToPreferredUnit();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "roughConversionToPreferredUnit", lhsRoughConversionToPreferredUnit), LocatorUtils.property(thatLocator, "roughConversionToPreferredUnit", rhsRoughConversionToPreferredUnit), lhsRoughConversionToPreferredUnit, rhsRoughConversionToPreferredUnit, this.isSetRoughConversionToPreferredUnit(), that.isSetRoughConversionToPreferredUnit())) {
                return false;
            }
        }
        {
            List<DerivationUnitTermType> lhsDerivationUnitTerm;
            lhsDerivationUnitTerm = (this.isSetDerivationUnitTerm()?this.getDerivationUnitTerm():null);
            List<DerivationUnitTermType> rhsDerivationUnitTerm;
            rhsDerivationUnitTerm = (that.isSetDerivationUnitTerm()?that.getDerivationUnitTerm():null);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "derivationUnitTerm", lhsDerivationUnitTerm), LocatorUtils.property(thatLocator, "derivationUnitTerm", rhsDerivationUnitTerm), lhsDerivationUnitTerm, rhsDerivationUnitTerm, this.isSetDerivationUnitTerm(), that.isSetDerivationUnitTerm())) {
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
            ConversionToPreferredUnitType theConversionToPreferredUnit;
            theConversionToPreferredUnit = this.getConversionToPreferredUnit();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "conversionToPreferredUnit", theConversionToPreferredUnit), currentHashCode, theConversionToPreferredUnit, this.isSetConversionToPreferredUnit());
        }
        {
            ConversionToPreferredUnitType theRoughConversionToPreferredUnit;
            theRoughConversionToPreferredUnit = this.getRoughConversionToPreferredUnit();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "roughConversionToPreferredUnit", theRoughConversionToPreferredUnit), currentHashCode, theRoughConversionToPreferredUnit, this.isSetRoughConversionToPreferredUnit());
        }
        {
            List<DerivationUnitTermType> theDerivationUnitTerm;
            theDerivationUnitTerm = (this.isSetDerivationUnitTerm()?this.getDerivationUnitTerm():null);
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "derivationUnitTerm", theDerivationUnitTerm), currentHashCode, theDerivationUnitTerm, this.isSetDerivationUnitTerm());
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
        if (draftCopy instanceof ConventionalUnitType) {
            final ConventionalUnitType copy = ((ConventionalUnitType) draftCopy);
            {
                java.lang.Boolean conversionToPreferredUnitShouldBeCopiedAndSet = strategy.shouldBeCopiedAndSet(locator, this.isSetConversionToPreferredUnit());
                if (conversionToPreferredUnitShouldBeCopiedAndSet == java.lang.Boolean.TRUE) {
                    ConversionToPreferredUnitType sourceConversionToPreferredUnit;
                    sourceConversionToPreferredUnit = this.getConversionToPreferredUnit();
                    ConversionToPreferredUnitType copyConversionToPreferredUnit = ((ConversionToPreferredUnitType) strategy.copy(LocatorUtils.property(locator, "conversionToPreferredUnit", sourceConversionToPreferredUnit), sourceConversionToPreferredUnit, this.isSetConversionToPreferredUnit()));
                    copy.setConversionToPreferredUnit(copyConversionToPreferredUnit);
                } else {
                    if (conversionToPreferredUnitShouldBeCopiedAndSet == java.lang.Boolean.FALSE) {
                        copy.conversionToPreferredUnit = null;
                    }
                }
            }
            {
                java.lang.Boolean roughConversionToPreferredUnitShouldBeCopiedAndSet = strategy.shouldBeCopiedAndSet(locator, this.isSetRoughConversionToPreferredUnit());
                if (roughConversionToPreferredUnitShouldBeCopiedAndSet == java.lang.Boolean.TRUE) {
                    ConversionToPreferredUnitType sourceRoughConversionToPreferredUnit;
                    sourceRoughConversionToPreferredUnit = this.getRoughConversionToPreferredUnit();
                    ConversionToPreferredUnitType copyRoughConversionToPreferredUnit = ((ConversionToPreferredUnitType) strategy.copy(LocatorUtils.property(locator, "roughConversionToPreferredUnit", sourceRoughConversionToPreferredUnit), sourceRoughConversionToPreferredUnit, this.isSetRoughConversionToPreferredUnit()));
                    copy.setRoughConversionToPreferredUnit(copyRoughConversionToPreferredUnit);
                } else {
                    if (roughConversionToPreferredUnitShouldBeCopiedAndSet == java.lang.Boolean.FALSE) {
                        copy.roughConversionToPreferredUnit = null;
                    }
                }
            }
            {
                java.lang.Boolean derivationUnitTermShouldBeCopiedAndSet = strategy.shouldBeCopiedAndSet(locator, this.isSetDerivationUnitTerm());
                if (derivationUnitTermShouldBeCopiedAndSet == java.lang.Boolean.TRUE) {
                    List<DerivationUnitTermType> sourceDerivationUnitTerm;
                    sourceDerivationUnitTerm = (this.isSetDerivationUnitTerm()?this.getDerivationUnitTerm():null);
                    @SuppressWarnings("unchecked")
                    List<DerivationUnitTermType> copyDerivationUnitTerm = ((List<DerivationUnitTermType> ) strategy.copy(LocatorUtils.property(locator, "derivationUnitTerm", sourceDerivationUnitTerm), sourceDerivationUnitTerm, this.isSetDerivationUnitTerm()));
                    copy.setDerivationUnitTerm(copyDerivationUnitTerm);
                } else {
                    if (derivationUnitTermShouldBeCopiedAndSet == java.lang.Boolean.FALSE) {
                        copy.unsetDerivationUnitTerm();
                    }
                }
            }
        }
        return draftCopy;
    }

    @Override
    public Object createNewInstance() {
        return new ConventionalUnitType();
    }

    @Override
    public void mergeFrom(Object left, Object right) {
        final MergeStrategy strategy = JAXBMergeStrategy.getInstance();
        mergeFrom(null, null, left, right, strategy);
    }

    @Override
    public void mergeFrom(ObjectLocator leftLocator, ObjectLocator rightLocator, Object left, Object right, MergeStrategy strategy) {
        super.mergeFrom(leftLocator, rightLocator, left, right, strategy);
        if (right instanceof ConventionalUnitType) {
            final ConventionalUnitType target = this;
            final ConventionalUnitType leftObject = ((ConventionalUnitType) left);
            final ConventionalUnitType rightObject = ((ConventionalUnitType) right);
            {
                java.lang.Boolean conversionToPreferredUnitShouldBeMergedAndSet = strategy.shouldBeMergedAndSet(leftLocator, rightLocator, leftObject.isSetConversionToPreferredUnit(), rightObject.isSetConversionToPreferredUnit());
                if (conversionToPreferredUnitShouldBeMergedAndSet == java.lang.Boolean.TRUE) {
                    ConversionToPreferredUnitType lhsConversionToPreferredUnit;
                    lhsConversionToPreferredUnit = leftObject.getConversionToPreferredUnit();
                    ConversionToPreferredUnitType rhsConversionToPreferredUnit;
                    rhsConversionToPreferredUnit = rightObject.getConversionToPreferredUnit();
                    ConversionToPreferredUnitType mergedConversionToPreferredUnit = ((ConversionToPreferredUnitType) strategy.merge(LocatorUtils.property(leftLocator, "conversionToPreferredUnit", lhsConversionToPreferredUnit), LocatorUtils.property(rightLocator, "conversionToPreferredUnit", rhsConversionToPreferredUnit), lhsConversionToPreferredUnit, rhsConversionToPreferredUnit, leftObject.isSetConversionToPreferredUnit(), rightObject.isSetConversionToPreferredUnit()));
                    target.setConversionToPreferredUnit(mergedConversionToPreferredUnit);
                } else {
                    if (conversionToPreferredUnitShouldBeMergedAndSet == java.lang.Boolean.FALSE) {
                        target.conversionToPreferredUnit = null;
                    }
                }
            }
            {
                java.lang.Boolean roughConversionToPreferredUnitShouldBeMergedAndSet = strategy.shouldBeMergedAndSet(leftLocator, rightLocator, leftObject.isSetRoughConversionToPreferredUnit(), rightObject.isSetRoughConversionToPreferredUnit());
                if (roughConversionToPreferredUnitShouldBeMergedAndSet == java.lang.Boolean.TRUE) {
                    ConversionToPreferredUnitType lhsRoughConversionToPreferredUnit;
                    lhsRoughConversionToPreferredUnit = leftObject.getRoughConversionToPreferredUnit();
                    ConversionToPreferredUnitType rhsRoughConversionToPreferredUnit;
                    rhsRoughConversionToPreferredUnit = rightObject.getRoughConversionToPreferredUnit();
                    ConversionToPreferredUnitType mergedRoughConversionToPreferredUnit = ((ConversionToPreferredUnitType) strategy.merge(LocatorUtils.property(leftLocator, "roughConversionToPreferredUnit", lhsRoughConversionToPreferredUnit), LocatorUtils.property(rightLocator, "roughConversionToPreferredUnit", rhsRoughConversionToPreferredUnit), lhsRoughConversionToPreferredUnit, rhsRoughConversionToPreferredUnit, leftObject.isSetRoughConversionToPreferredUnit(), rightObject.isSetRoughConversionToPreferredUnit()));
                    target.setRoughConversionToPreferredUnit(mergedRoughConversionToPreferredUnit);
                } else {
                    if (roughConversionToPreferredUnitShouldBeMergedAndSet == java.lang.Boolean.FALSE) {
                        target.roughConversionToPreferredUnit = null;
                    }
                }
            }
            {
                java.lang.Boolean derivationUnitTermShouldBeMergedAndSet = strategy.shouldBeMergedAndSet(leftLocator, rightLocator, leftObject.isSetDerivationUnitTerm(), rightObject.isSetDerivationUnitTerm());
                if (derivationUnitTermShouldBeMergedAndSet == java.lang.Boolean.TRUE) {
                    List<DerivationUnitTermType> lhsDerivationUnitTerm;
                    lhsDerivationUnitTerm = (leftObject.isSetDerivationUnitTerm()?leftObject.getDerivationUnitTerm():null);
                    List<DerivationUnitTermType> rhsDerivationUnitTerm;
                    rhsDerivationUnitTerm = (rightObject.isSetDerivationUnitTerm()?rightObject.getDerivationUnitTerm():null);
                    List<DerivationUnitTermType> mergedDerivationUnitTerm = ((List<DerivationUnitTermType> ) strategy.merge(LocatorUtils.property(leftLocator, "derivationUnitTerm", lhsDerivationUnitTerm), LocatorUtils.property(rightLocator, "derivationUnitTerm", rhsDerivationUnitTerm), lhsDerivationUnitTerm, rhsDerivationUnitTerm, leftObject.isSetDerivationUnitTerm(), rightObject.isSetDerivationUnitTerm()));
                    target.setDerivationUnitTerm(mergedDerivationUnitTerm);
                } else {
                    if (derivationUnitTermShouldBeMergedAndSet == java.lang.Boolean.FALSE) {
                        target.unsetDerivationUnitTerm();
                    }
                }
            }
        }
    }

}
