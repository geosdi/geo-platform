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

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
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
 * <p>Classe Java per ConventionalUnitType complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="ConventionalUnitType">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.opengis.net/gml}UnitDefinitionType">
 *       &lt;sequence>
 *         &lt;choice>
 *           &lt;element ref="{http://www.opengis.net/gml}conversionToPreferredUnit"/>
 *           &lt;element ref="{http://www.opengis.net/gml}roughConversionToPreferredUnit"/>
 *         &lt;/choice>
 *         &lt;element ref="{http://www.opengis.net/gml}derivationUnitTerm" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
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

    protected ConversionToPreferredUnitType conversionToPreferredUnit;
    protected ConversionToPreferredUnitType roughConversionToPreferredUnit;
    protected List<DerivationUnitTermType> derivationUnitTerm;

    /**
     * Recupera il valore della proprietà conversionToPreferredUnit.
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
     */
    public void setConversionToPreferredUnit(ConversionToPreferredUnitType value) {
        this.conversionToPreferredUnit = value;
    }

    public boolean isSetConversionToPreferredUnit() {
        return (this.conversionToPreferredUnit!= null);
    }

    /**
     * Recupera il valore della proprietà roughConversionToPreferredUnit.
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
     */
    public void setRoughConversionToPreferredUnit(ConversionToPreferredUnitType value) {
        this.roughConversionToPreferredUnit = value;
    }

    public boolean isSetRoughConversionToPreferredUnit() {
        return (this.roughConversionToPreferredUnit!= null);
    }

    /**
     * Gets the value of the derivationUnitTerm property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the derivationUnitTerm property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDerivationUnitTerm().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DerivationUnitTermType }
     * 
     * 
     */
    public List<DerivationUnitTermType> getDerivationUnitTerm() {
        if (derivationUnitTerm == null) {
            derivationUnitTerm = new ArrayList<DerivationUnitTermType>();
        }
        return this.derivationUnitTerm;
    }

    public boolean isSetDerivationUnitTerm() {
        return ((this.derivationUnitTerm!= null)&&(!this.derivationUnitTerm.isEmpty()));
    }

    public void unsetDerivationUnitTerm() {
        this.derivationUnitTerm = null;
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
            ConversionToPreferredUnitType theConversionToPreferredUnit;
            theConversionToPreferredUnit = this.getConversionToPreferredUnit();
            strategy.appendField(locator, this, "conversionToPreferredUnit", buffer, theConversionToPreferredUnit);
        }
        {
            ConversionToPreferredUnitType theRoughConversionToPreferredUnit;
            theRoughConversionToPreferredUnit = this.getRoughConversionToPreferredUnit();
            strategy.appendField(locator, this, "roughConversionToPreferredUnit", buffer, theRoughConversionToPreferredUnit);
        }
        {
            List<DerivationUnitTermType> theDerivationUnitTerm;
            theDerivationUnitTerm = this.getDerivationUnitTerm();
            strategy.appendField(locator, this, "derivationUnitTerm", buffer, theDerivationUnitTerm);
        }
        return buffer;
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof ConventionalUnitType)) {
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
            if (!strategy.equals(LocatorUtils.property(thisLocator, "conversionToPreferredUnit", lhsConversionToPreferredUnit), LocatorUtils.property(thatLocator, "conversionToPreferredUnit", rhsConversionToPreferredUnit), lhsConversionToPreferredUnit, rhsConversionToPreferredUnit)) {
                return false;
            }
        }
        {
            ConversionToPreferredUnitType lhsRoughConversionToPreferredUnit;
            lhsRoughConversionToPreferredUnit = this.getRoughConversionToPreferredUnit();
            ConversionToPreferredUnitType rhsRoughConversionToPreferredUnit;
            rhsRoughConversionToPreferredUnit = that.getRoughConversionToPreferredUnit();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "roughConversionToPreferredUnit", lhsRoughConversionToPreferredUnit), LocatorUtils.property(thatLocator, "roughConversionToPreferredUnit", rhsRoughConversionToPreferredUnit), lhsRoughConversionToPreferredUnit, rhsRoughConversionToPreferredUnit)) {
                return false;
            }
        }
        {
            List<DerivationUnitTermType> lhsDerivationUnitTerm;
            lhsDerivationUnitTerm = this.getDerivationUnitTerm();
            List<DerivationUnitTermType> rhsDerivationUnitTerm;
            rhsDerivationUnitTerm = that.getDerivationUnitTerm();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "derivationUnitTerm", lhsDerivationUnitTerm), LocatorUtils.property(thatLocator, "derivationUnitTerm", rhsDerivationUnitTerm), lhsDerivationUnitTerm, rhsDerivationUnitTerm)) {
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
            ConversionToPreferredUnitType theConversionToPreferredUnit;
            theConversionToPreferredUnit = this.getConversionToPreferredUnit();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "conversionToPreferredUnit", theConversionToPreferredUnit), currentHashCode, theConversionToPreferredUnit);
        }
        {
            ConversionToPreferredUnitType theRoughConversionToPreferredUnit;
            theRoughConversionToPreferredUnit = this.getRoughConversionToPreferredUnit();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "roughConversionToPreferredUnit", theRoughConversionToPreferredUnit), currentHashCode, theRoughConversionToPreferredUnit);
        }
        {
            List<DerivationUnitTermType> theDerivationUnitTerm;
            theDerivationUnitTerm = this.getDerivationUnitTerm();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "derivationUnitTerm", theDerivationUnitTerm), currentHashCode, theDerivationUnitTerm);
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
        if (draftCopy instanceof ConventionalUnitType) {
            final ConventionalUnitType copy = ((ConventionalUnitType) draftCopy);
            if (this.isSetConversionToPreferredUnit()) {
                ConversionToPreferredUnitType sourceConversionToPreferredUnit;
                sourceConversionToPreferredUnit = this.getConversionToPreferredUnit();
                ConversionToPreferredUnitType copyConversionToPreferredUnit = ((ConversionToPreferredUnitType) strategy.copy(LocatorUtils.property(locator, "conversionToPreferredUnit", sourceConversionToPreferredUnit), sourceConversionToPreferredUnit));
                copy.setConversionToPreferredUnit(copyConversionToPreferredUnit);
            } else {
                copy.conversionToPreferredUnit = null;
            }
            if (this.isSetRoughConversionToPreferredUnit()) {
                ConversionToPreferredUnitType sourceRoughConversionToPreferredUnit;
                sourceRoughConversionToPreferredUnit = this.getRoughConversionToPreferredUnit();
                ConversionToPreferredUnitType copyRoughConversionToPreferredUnit = ((ConversionToPreferredUnitType) strategy.copy(LocatorUtils.property(locator, "roughConversionToPreferredUnit", sourceRoughConversionToPreferredUnit), sourceRoughConversionToPreferredUnit));
                copy.setRoughConversionToPreferredUnit(copyRoughConversionToPreferredUnit);
            } else {
                copy.roughConversionToPreferredUnit = null;
            }
            if (this.isSetDerivationUnitTerm()) {
                List<DerivationUnitTermType> sourceDerivationUnitTerm;
                sourceDerivationUnitTerm = this.getDerivationUnitTerm();
                @SuppressWarnings("unchecked")
                List<DerivationUnitTermType> copyDerivationUnitTerm = ((List<DerivationUnitTermType> ) strategy.copy(LocatorUtils.property(locator, "derivationUnitTerm", sourceDerivationUnitTerm), sourceDerivationUnitTerm));
                copy.unsetDerivationUnitTerm();
                List<DerivationUnitTermType> uniqueDerivationUnitTerml = copy.getDerivationUnitTerm();
                uniqueDerivationUnitTerml.addAll(copyDerivationUnitTerm);
            } else {
                copy.unsetDerivationUnitTerm();
            }
        }
        return draftCopy;
    }

    public Object createNewInstance() {
        return new ConventionalUnitType();
    }

    public void mergeFrom(Object left, Object right) {
        final MergeStrategy strategy = JAXBMergeStrategy.INSTANCE;
        mergeFrom(null, null, left, right, strategy);
    }

    public void mergeFrom(ObjectLocator leftLocator, ObjectLocator rightLocator, Object left, Object right, MergeStrategy strategy) {
        super.mergeFrom(leftLocator, rightLocator, left, right, strategy);
        if (right instanceof ConventionalUnitType) {
            final ConventionalUnitType target = this;
            final ConventionalUnitType leftObject = ((ConventionalUnitType) left);
            final ConventionalUnitType rightObject = ((ConventionalUnitType) right);
            {
                ConversionToPreferredUnitType lhsConversionToPreferredUnit;
                lhsConversionToPreferredUnit = leftObject.getConversionToPreferredUnit();
                ConversionToPreferredUnitType rhsConversionToPreferredUnit;
                rhsConversionToPreferredUnit = rightObject.getConversionToPreferredUnit();
                target.setConversionToPreferredUnit(((ConversionToPreferredUnitType) strategy.merge(LocatorUtils.property(leftLocator, "conversionToPreferredUnit", lhsConversionToPreferredUnit), LocatorUtils.property(rightLocator, "conversionToPreferredUnit", rhsConversionToPreferredUnit), lhsConversionToPreferredUnit, rhsConversionToPreferredUnit)));
            }
            {
                ConversionToPreferredUnitType lhsRoughConversionToPreferredUnit;
                lhsRoughConversionToPreferredUnit = leftObject.getRoughConversionToPreferredUnit();
                ConversionToPreferredUnitType rhsRoughConversionToPreferredUnit;
                rhsRoughConversionToPreferredUnit = rightObject.getRoughConversionToPreferredUnit();
                target.setRoughConversionToPreferredUnit(((ConversionToPreferredUnitType) strategy.merge(LocatorUtils.property(leftLocator, "roughConversionToPreferredUnit", lhsRoughConversionToPreferredUnit), LocatorUtils.property(rightLocator, "roughConversionToPreferredUnit", rhsRoughConversionToPreferredUnit), lhsRoughConversionToPreferredUnit, rhsRoughConversionToPreferredUnit)));
            }
            {
                List<DerivationUnitTermType> lhsDerivationUnitTerm;
                lhsDerivationUnitTerm = leftObject.getDerivationUnitTerm();
                List<DerivationUnitTermType> rhsDerivationUnitTerm;
                rhsDerivationUnitTerm = rightObject.getDerivationUnitTerm();
                target.unsetDerivationUnitTerm();
                List<DerivationUnitTermType> uniqueDerivationUnitTerml = target.getDerivationUnitTerm();
                uniqueDerivationUnitTerml.addAll(((List<DerivationUnitTermType> ) strategy.merge(LocatorUtils.property(leftLocator, "derivationUnitTerm", lhsDerivationUnitTerm), LocatorUtils.property(rightLocator, "derivationUnitTerm", rhsDerivationUnitTerm), lhsDerivationUnitTerm, rhsDerivationUnitTerm)));
            }
        }
    }

    public void setDerivationUnitTerm(List<DerivationUnitTermType> value) {
        List<DerivationUnitTermType> draftl = this.getDerivationUnitTerm();
        draftl.addAll(value);
    }

    public ConventionalUnitType withConversionToPreferredUnit(ConversionToPreferredUnitType value) {
        setConversionToPreferredUnit(value);
        return this;
    }

    public ConventionalUnitType withRoughConversionToPreferredUnit(ConversionToPreferredUnitType value) {
        setRoughConversionToPreferredUnit(value);
        return this;
    }

    public ConventionalUnitType withDerivationUnitTerm(DerivationUnitTermType... values) {
        if (values!= null) {
            for (DerivationUnitTermType value: values) {
                getDerivationUnitTerm().add(value);
            }
        }
        return this;
    }

    public ConventionalUnitType withDerivationUnitTerm(Collection<DerivationUnitTermType> values) {
        if (values!= null) {
            getDerivationUnitTerm().addAll(values);
        }
        return this;
    }

    public ConventionalUnitType withDerivationUnitTerm(List<DerivationUnitTermType> value) {
        setDerivationUnitTerm(value);
        return this;
    }

    @Override
    public ConventionalUnitType withQuantityType(StringOrRefType value) {
        setQuantityType(value);
        return this;
    }

    @Override
    public ConventionalUnitType withQuantityTypeReference(ReferenceType value) {
        setQuantityTypeReference(value);
        return this;
    }

    @Override
    public ConventionalUnitType withCatalogSymbol(CodeType value) {
        setCatalogSymbol(value);
        return this;
    }

    @Override
    public ConventionalUnitType withRemarks(String value) {
        setRemarks(value);
        return this;
    }

    @Override
    public ConventionalUnitType withMetaDataProperty(MetaDataPropertyType... values) {
        if (values!= null) {
            for (MetaDataPropertyType value: values) {
                getMetaDataProperty().add(value);
            }
        }
        return this;
    }

    @Override
    public ConventionalUnitType withMetaDataProperty(Collection<MetaDataPropertyType> values) {
        if (values!= null) {
            getMetaDataProperty().addAll(values);
        }
        return this;
    }

    @Override
    public ConventionalUnitType withDescription(StringOrRefType value) {
        setDescription(value);
        return this;
    }

    @Override
    public ConventionalUnitType withDescriptionReference(ReferenceType value) {
        setDescriptionReference(value);
        return this;
    }

    @Override
    public ConventionalUnitType withIdentifier(CodeWithAuthorityType value) {
        setIdentifier(value);
        return this;
    }

    @Override
    public ConventionalUnitType withName(CodeType... values) {
        if (values!= null) {
            for (CodeType value: values) {
                getName().add(value);
            }
        }
        return this;
    }

    @Override
    public ConventionalUnitType withName(Collection<CodeType> values) {
        if (values!= null) {
            getName().addAll(values);
        }
        return this;
    }

    @Override
    public ConventionalUnitType withId(String value) {
        setId(value);
        return this;
    }

    @Override
    public ConventionalUnitType withMetaDataProperty(List<MetaDataPropertyType> value) {
        setMetaDataProperty(value);
        return this;
    }

    @Override
    public ConventionalUnitType withName(List<CodeType> value) {
        setName(value);
        return this;
    }

}
