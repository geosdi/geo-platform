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
import jakarta.xml.bind.annotation.XmlElement;
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
 * <p>Classe Java per DerivedUnitType complex type.</p>
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.</p>
 * 
 * <pre>{@code
 * <complexType name="DerivedUnitType">
 *   <complexContent>
 *     <extension base="{http://www.opengis.net/gml}UnitDefinitionType">
 *       <sequence>
 *         <element ref="{http://www.opengis.net/gml}derivationUnitTerm" maxOccurs="unbounded"/>
 *       </sequence>
 *     </extension>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DerivedUnitType", propOrder = {
    "derivationUnitTerm"
})
public class DerivedUnitType
    extends UnitDefinitionType
    implements Cloneable, CopyTo, Equals, HashCode, MergeFrom, ToString
{

    /**
     * A set of gml:derivationUnitTerm elements describes a derived unit of measure.  Each element carries an integer exponent.  The terms are combined by raising each referenced unit to the power of its exponent and forming the product.
     * This unit term references another unit of measure (uom) and provides an integer exponent applied to that unit in defining the compound unit. The exponent may be positive or negative, but not zero.
     * 
     */
    @XmlElement(required = true)
    protected List<DerivationUnitTermType> derivationUnitTerm;

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
        final DerivedUnitType that = ((DerivedUnitType) object);
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
        if (draftCopy instanceof DerivedUnitType) {
            final DerivedUnitType copy = ((DerivedUnitType) draftCopy);
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
        return new DerivedUnitType();
    }

    @Override
    public void mergeFrom(Object left, Object right) {
        final MergeStrategy strategy = JAXBMergeStrategy.getInstance();
        mergeFrom(null, null, left, right, strategy);
    }

    @Override
    public void mergeFrom(ObjectLocator leftLocator, ObjectLocator rightLocator, Object left, Object right, MergeStrategy strategy) {
        super.mergeFrom(leftLocator, rightLocator, left, right, strategy);
        if (right instanceof DerivedUnitType) {
            final DerivedUnitType target = this;
            final DerivedUnitType leftObject = ((DerivedUnitType) left);
            final DerivedUnitType rightObject = ((DerivedUnitType) right);
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
