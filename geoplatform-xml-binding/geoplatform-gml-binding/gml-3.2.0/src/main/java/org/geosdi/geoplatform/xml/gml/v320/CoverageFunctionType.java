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
 * <p>Classe Java per CoverageFunctionType complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="CoverageFunctionType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;choice>
 *         &lt;element ref="{http://www.opengis.net/gml}MappingRule"/>
 *         &lt;element ref="{http://www.opengis.net/gml}CoverageMappingRule"/>
 *         &lt;element ref="{http://www.opengis.net/gml}GridFunction"/>
 *       &lt;/choice>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CoverageFunctionType", propOrder = {
    "mappingRule",
    "coverageMappingRule",
    "gridFunction"
})
public class CoverageFunctionType implements Cloneable, CopyTo, Equals, HashCode, MergeFrom, ToString
{

    @XmlElement(name = "MappingRule")
    protected StringOrRefType mappingRule;
    @XmlElement(name = "CoverageMappingRule")
    protected MappingRuleType coverageMappingRule;
    @XmlElement(name = "GridFunction")
    protected GridFunctionType gridFunction;

    /**
     * Recupera il valore della proprietà mappingRule.
     * 
     * @return
     *     possible object is
     *     {@link StringOrRefType }
     *     
     */
    public StringOrRefType getMappingRule() {
        return mappingRule;
    }

    /**
     * Imposta il valore della proprietà mappingRule.
     * 
     * @param value
     *     allowed object is
     *     {@link StringOrRefType }
     *     
     */
    public void setMappingRule(StringOrRefType value) {
        this.mappingRule = value;
    }

    public boolean isSetMappingRule() {
        return (this.mappingRule!= null);
    }

    /**
     * Recupera il valore della proprietà coverageMappingRule.
     * 
     * @return
     *     possible object is
     *     {@link MappingRuleType }
     *     
     */
    public MappingRuleType getCoverageMappingRule() {
        return coverageMappingRule;
    }

    /**
     * Imposta il valore della proprietà coverageMappingRule.
     * 
     * @param value
     *     allowed object is
     *     {@link MappingRuleType }
     *     
     */
    public void setCoverageMappingRule(MappingRuleType value) {
        this.coverageMappingRule = value;
    }

    public boolean isSetCoverageMappingRule() {
        return (this.coverageMappingRule!= null);
    }

    /**
     * Recupera il valore della proprietà gridFunction.
     * 
     * @return
     *     possible object is
     *     {@link GridFunctionType }
     *     
     */
    public GridFunctionType getGridFunction() {
        return gridFunction;
    }

    /**
     * Imposta il valore della proprietà gridFunction.
     * 
     * @param value
     *     allowed object is
     *     {@link GridFunctionType }
     *     
     */
    public void setGridFunction(GridFunctionType value) {
        this.gridFunction = value;
    }

    public boolean isSetGridFunction() {
        return (this.gridFunction!= null);
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
        {
            StringOrRefType theMappingRule;
            theMappingRule = this.getMappingRule();
            strategy.appendField(locator, this, "mappingRule", buffer, theMappingRule);
        }
        {
            MappingRuleType theCoverageMappingRule;
            theCoverageMappingRule = this.getCoverageMappingRule();
            strategy.appendField(locator, this, "coverageMappingRule", buffer, theCoverageMappingRule);
        }
        {
            GridFunctionType theGridFunction;
            theGridFunction = this.getGridFunction();
            strategy.appendField(locator, this, "gridFunction", buffer, theGridFunction);
        }
        return buffer;
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof CoverageFunctionType)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        final CoverageFunctionType that = ((CoverageFunctionType) object);
        {
            StringOrRefType lhsMappingRule;
            lhsMappingRule = this.getMappingRule();
            StringOrRefType rhsMappingRule;
            rhsMappingRule = that.getMappingRule();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "mappingRule", lhsMappingRule), LocatorUtils.property(thatLocator, "mappingRule", rhsMappingRule), lhsMappingRule, rhsMappingRule)) {
                return false;
            }
        }
        {
            MappingRuleType lhsCoverageMappingRule;
            lhsCoverageMappingRule = this.getCoverageMappingRule();
            MappingRuleType rhsCoverageMappingRule;
            rhsCoverageMappingRule = that.getCoverageMappingRule();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "coverageMappingRule", lhsCoverageMappingRule), LocatorUtils.property(thatLocator, "coverageMappingRule", rhsCoverageMappingRule), lhsCoverageMappingRule, rhsCoverageMappingRule)) {
                return false;
            }
        }
        {
            GridFunctionType lhsGridFunction;
            lhsGridFunction = this.getGridFunction();
            GridFunctionType rhsGridFunction;
            rhsGridFunction = that.getGridFunction();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "gridFunction", lhsGridFunction), LocatorUtils.property(thatLocator, "gridFunction", rhsGridFunction), lhsGridFunction, rhsGridFunction)) {
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
        int currentHashCode = 1;
        {
            StringOrRefType theMappingRule;
            theMappingRule = this.getMappingRule();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "mappingRule", theMappingRule), currentHashCode, theMappingRule);
        }
        {
            MappingRuleType theCoverageMappingRule;
            theCoverageMappingRule = this.getCoverageMappingRule();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "coverageMappingRule", theCoverageMappingRule), currentHashCode, theCoverageMappingRule);
        }
        {
            GridFunctionType theGridFunction;
            theGridFunction = this.getGridFunction();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "gridFunction", theGridFunction), currentHashCode, theGridFunction);
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
        if (draftCopy instanceof CoverageFunctionType) {
            final CoverageFunctionType copy = ((CoverageFunctionType) draftCopy);
            if (this.isSetMappingRule()) {
                StringOrRefType sourceMappingRule;
                sourceMappingRule = this.getMappingRule();
                StringOrRefType copyMappingRule = ((StringOrRefType) strategy.copy(LocatorUtils.property(locator, "mappingRule", sourceMappingRule), sourceMappingRule));
                copy.setMappingRule(copyMappingRule);
            } else {
                copy.mappingRule = null;
            }
            if (this.isSetCoverageMappingRule()) {
                MappingRuleType sourceCoverageMappingRule;
                sourceCoverageMappingRule = this.getCoverageMappingRule();
                MappingRuleType copyCoverageMappingRule = ((MappingRuleType) strategy.copy(LocatorUtils.property(locator, "coverageMappingRule", sourceCoverageMappingRule), sourceCoverageMappingRule));
                copy.setCoverageMappingRule(copyCoverageMappingRule);
            } else {
                copy.coverageMappingRule = null;
            }
            if (this.isSetGridFunction()) {
                GridFunctionType sourceGridFunction;
                sourceGridFunction = this.getGridFunction();
                GridFunctionType copyGridFunction = ((GridFunctionType) strategy.copy(LocatorUtils.property(locator, "gridFunction", sourceGridFunction), sourceGridFunction));
                copy.setGridFunction(copyGridFunction);
            } else {
                copy.gridFunction = null;
            }
        }
        return draftCopy;
    }

    public Object createNewInstance() {
        return new CoverageFunctionType();
    }

    public void mergeFrom(Object left, Object right) {
        final MergeStrategy strategy = JAXBMergeStrategy.INSTANCE;
        mergeFrom(null, null, left, right, strategy);
    }

    public void mergeFrom(ObjectLocator leftLocator, ObjectLocator rightLocator, Object left, Object right, MergeStrategy strategy) {
        if (right instanceof CoverageFunctionType) {
            final CoverageFunctionType target = this;
            final CoverageFunctionType leftObject = ((CoverageFunctionType) left);
            final CoverageFunctionType rightObject = ((CoverageFunctionType) right);
            {
                StringOrRefType lhsMappingRule;
                lhsMappingRule = leftObject.getMappingRule();
                StringOrRefType rhsMappingRule;
                rhsMappingRule = rightObject.getMappingRule();
                target.setMappingRule(((StringOrRefType) strategy.merge(LocatorUtils.property(leftLocator, "mappingRule", lhsMappingRule), LocatorUtils.property(rightLocator, "mappingRule", rhsMappingRule), lhsMappingRule, rhsMappingRule)));
            }
            {
                MappingRuleType lhsCoverageMappingRule;
                lhsCoverageMappingRule = leftObject.getCoverageMappingRule();
                MappingRuleType rhsCoverageMappingRule;
                rhsCoverageMappingRule = rightObject.getCoverageMappingRule();
                target.setCoverageMappingRule(((MappingRuleType) strategy.merge(LocatorUtils.property(leftLocator, "coverageMappingRule", lhsCoverageMappingRule), LocatorUtils.property(rightLocator, "coverageMappingRule", rhsCoverageMappingRule), lhsCoverageMappingRule, rhsCoverageMappingRule)));
            }
            {
                GridFunctionType lhsGridFunction;
                lhsGridFunction = leftObject.getGridFunction();
                GridFunctionType rhsGridFunction;
                rhsGridFunction = rightObject.getGridFunction();
                target.setGridFunction(((GridFunctionType) strategy.merge(LocatorUtils.property(leftLocator, "gridFunction", lhsGridFunction), LocatorUtils.property(rightLocator, "gridFunction", rhsGridFunction), lhsGridFunction, rhsGridFunction)));
            }
        }
    }

    public CoverageFunctionType withMappingRule(StringOrRefType value) {
        setMappingRule(value);
        return this;
    }

    public CoverageFunctionType withCoverageMappingRule(MappingRuleType value) {
        setCoverageMappingRule(value);
        return this;
    }

    public CoverageFunctionType withGridFunction(GridFunctionType value) {
        setGridFunction(value);
        return this;
    }

}
