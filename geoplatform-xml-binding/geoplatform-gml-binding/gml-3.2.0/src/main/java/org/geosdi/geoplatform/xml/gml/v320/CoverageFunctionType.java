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
 * <p>Classe Java per CoverageFunctionType complex type.</p>
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.</p>
 * 
 * <pre>{@code
 * <complexType name="CoverageFunctionType">
 *   <complexContent>
 *     <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       <choice>
 *         <element ref="{http://www.opengis.net/gml}MappingRule"/>
 *         <element ref="{http://www.opengis.net/gml}CoverageMappingRule"/>
 *         <element ref="{http://www.opengis.net/gml}GridFunction"/>
 *       </choice>
 *     </restriction>
 *   </complexContent>
 * </complexType>
 * }</pre>
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
    /**
     * gml:CoverageMappingRule provides a formal or informal description of the coverage function.
     * The mapping rule may be defined as an in-line string (gml:ruleDefinition) or via a remote reference through xlink:href (gml:ruleReference).  
     * If no rule name is specified, the default is ‘Linear’ with respect to members of the domain in document order.
     * 
     */
    @XmlElement(name = "CoverageMappingRule")
    protected MappingRuleType coverageMappingRule;
    /**
     * gml:GridFunction provides an explicit mapping rule for grid geometries, i.e. the domain shall be a geometry of type grid.  It describes the mapping of grid posts (discrete point grid coverage) or grid cells (discrete surface coverage) to the values in the range set.
     * The gml:startPoint is the index position of a point in the grid that is mapped to the first point in the range set (this is also the index position of the first grid post).  If the gml:startPoint property is omitted the gml:startPoint is assumed to be equal to the value of gml:low in the gml:Grid geometry. Subsequent points in the mapping are determined by the value of the gml:sequenceRule.
     * 
     */
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
     * gml:CoverageMappingRule provides a formal or informal description of the coverage function.
     * The mapping rule may be defined as an in-line string (gml:ruleDefinition) or via a remote reference through xlink:href (gml:ruleReference).  
     * If no rule name is specified, the default is ‘Linear’ with respect to members of the domain in document order.
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
     * @see #getCoverageMappingRule()
     */
    public void setCoverageMappingRule(MappingRuleType value) {
        this.coverageMappingRule = value;
    }

    public boolean isSetCoverageMappingRule() {
        return (this.coverageMappingRule!= null);
    }

    /**
     * gml:GridFunction provides an explicit mapping rule for grid geometries, i.e. the domain shall be a geometry of type grid.  It describes the mapping of grid posts (discrete point grid coverage) or grid cells (discrete surface coverage) to the values in the range set.
     * The gml:startPoint is the index position of a point in the grid that is mapped to the first point in the range set (this is also the index position of the first grid post).  If the gml:startPoint property is omitted the gml:startPoint is assumed to be equal to the value of gml:low in the gml:Grid geometry. Subsequent points in the mapping are determined by the value of the gml:sequenceRule.
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
     * @see #getGridFunction()
     */
    public void setGridFunction(GridFunctionType value) {
        this.gridFunction = value;
    }

    public boolean isSetGridFunction() {
        return (this.gridFunction!= null);
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
        {
            StringOrRefType theMappingRule;
            theMappingRule = this.getMappingRule();
            strategy.appendField(locator, this, "mappingRule", buffer, theMappingRule, this.isSetMappingRule());
        }
        {
            MappingRuleType theCoverageMappingRule;
            theCoverageMappingRule = this.getCoverageMappingRule();
            strategy.appendField(locator, this, "coverageMappingRule", buffer, theCoverageMappingRule, this.isSetCoverageMappingRule());
        }
        {
            GridFunctionType theGridFunction;
            theGridFunction = this.getGridFunction();
            strategy.appendField(locator, this, "gridFunction", buffer, theGridFunction, this.isSetGridFunction());
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
        final CoverageFunctionType that = ((CoverageFunctionType) object);
        {
            StringOrRefType lhsMappingRule;
            lhsMappingRule = this.getMappingRule();
            StringOrRefType rhsMappingRule;
            rhsMappingRule = that.getMappingRule();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "mappingRule", lhsMappingRule), LocatorUtils.property(thatLocator, "mappingRule", rhsMappingRule), lhsMappingRule, rhsMappingRule, this.isSetMappingRule(), that.isSetMappingRule())) {
                return false;
            }
        }
        {
            MappingRuleType lhsCoverageMappingRule;
            lhsCoverageMappingRule = this.getCoverageMappingRule();
            MappingRuleType rhsCoverageMappingRule;
            rhsCoverageMappingRule = that.getCoverageMappingRule();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "coverageMappingRule", lhsCoverageMappingRule), LocatorUtils.property(thatLocator, "coverageMappingRule", rhsCoverageMappingRule), lhsCoverageMappingRule, rhsCoverageMappingRule, this.isSetCoverageMappingRule(), that.isSetCoverageMappingRule())) {
                return false;
            }
        }
        {
            GridFunctionType lhsGridFunction;
            lhsGridFunction = this.getGridFunction();
            GridFunctionType rhsGridFunction;
            rhsGridFunction = that.getGridFunction();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "gridFunction", lhsGridFunction), LocatorUtils.property(thatLocator, "gridFunction", rhsGridFunction), lhsGridFunction, rhsGridFunction, this.isSetGridFunction(), that.isSetGridFunction())) {
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
        int currentHashCode = 1;
        {
            StringOrRefType theMappingRule;
            theMappingRule = this.getMappingRule();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "mappingRule", theMappingRule), currentHashCode, theMappingRule, this.isSetMappingRule());
        }
        {
            MappingRuleType theCoverageMappingRule;
            theCoverageMappingRule = this.getCoverageMappingRule();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "coverageMappingRule", theCoverageMappingRule), currentHashCode, theCoverageMappingRule, this.isSetCoverageMappingRule());
        }
        {
            GridFunctionType theGridFunction;
            theGridFunction = this.getGridFunction();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "gridFunction", theGridFunction), currentHashCode, theGridFunction, this.isSetGridFunction());
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
        if (draftCopy instanceof CoverageFunctionType) {
            final CoverageFunctionType copy = ((CoverageFunctionType) draftCopy);
            {
                java.lang.Boolean mappingRuleShouldBeCopiedAndSet = strategy.shouldBeCopiedAndSet(locator, this.isSetMappingRule());
                if (mappingRuleShouldBeCopiedAndSet == java.lang.Boolean.TRUE) {
                    StringOrRefType sourceMappingRule;
                    sourceMappingRule = this.getMappingRule();
                    StringOrRefType copyMappingRule = ((StringOrRefType) strategy.copy(LocatorUtils.property(locator, "mappingRule", sourceMappingRule), sourceMappingRule, this.isSetMappingRule()));
                    copy.setMappingRule(copyMappingRule);
                } else {
                    if (mappingRuleShouldBeCopiedAndSet == java.lang.Boolean.FALSE) {
                        copy.mappingRule = null;
                    }
                }
            }
            {
                java.lang.Boolean coverageMappingRuleShouldBeCopiedAndSet = strategy.shouldBeCopiedAndSet(locator, this.isSetCoverageMappingRule());
                if (coverageMappingRuleShouldBeCopiedAndSet == java.lang.Boolean.TRUE) {
                    MappingRuleType sourceCoverageMappingRule;
                    sourceCoverageMappingRule = this.getCoverageMappingRule();
                    MappingRuleType copyCoverageMappingRule = ((MappingRuleType) strategy.copy(LocatorUtils.property(locator, "coverageMappingRule", sourceCoverageMappingRule), sourceCoverageMappingRule, this.isSetCoverageMappingRule()));
                    copy.setCoverageMappingRule(copyCoverageMappingRule);
                } else {
                    if (coverageMappingRuleShouldBeCopiedAndSet == java.lang.Boolean.FALSE) {
                        copy.coverageMappingRule = null;
                    }
                }
            }
            {
                java.lang.Boolean gridFunctionShouldBeCopiedAndSet = strategy.shouldBeCopiedAndSet(locator, this.isSetGridFunction());
                if (gridFunctionShouldBeCopiedAndSet == java.lang.Boolean.TRUE) {
                    GridFunctionType sourceGridFunction;
                    sourceGridFunction = this.getGridFunction();
                    GridFunctionType copyGridFunction = ((GridFunctionType) strategy.copy(LocatorUtils.property(locator, "gridFunction", sourceGridFunction), sourceGridFunction, this.isSetGridFunction()));
                    copy.setGridFunction(copyGridFunction);
                } else {
                    if (gridFunctionShouldBeCopiedAndSet == java.lang.Boolean.FALSE) {
                        copy.gridFunction = null;
                    }
                }
            }
        }
        return draftCopy;
    }

    @Override
    public Object createNewInstance() {
        return new CoverageFunctionType();
    }

    @Override
    public void mergeFrom(Object left, Object right) {
        final MergeStrategy strategy = JAXBMergeStrategy.getInstance();
        mergeFrom(null, null, left, right, strategy);
    }

    @Override
    public void mergeFrom(ObjectLocator leftLocator, ObjectLocator rightLocator, Object left, Object right, MergeStrategy strategy) {
        if (right instanceof CoverageFunctionType) {
            final CoverageFunctionType target = this;
            final CoverageFunctionType leftObject = ((CoverageFunctionType) left);
            final CoverageFunctionType rightObject = ((CoverageFunctionType) right);
            {
                java.lang.Boolean mappingRuleShouldBeMergedAndSet = strategy.shouldBeMergedAndSet(leftLocator, rightLocator, leftObject.isSetMappingRule(), rightObject.isSetMappingRule());
                if (mappingRuleShouldBeMergedAndSet == java.lang.Boolean.TRUE) {
                    StringOrRefType lhsMappingRule;
                    lhsMappingRule = leftObject.getMappingRule();
                    StringOrRefType rhsMappingRule;
                    rhsMappingRule = rightObject.getMappingRule();
                    StringOrRefType mergedMappingRule = ((StringOrRefType) strategy.merge(LocatorUtils.property(leftLocator, "mappingRule", lhsMappingRule), LocatorUtils.property(rightLocator, "mappingRule", rhsMappingRule), lhsMappingRule, rhsMappingRule, leftObject.isSetMappingRule(), rightObject.isSetMappingRule()));
                    target.setMappingRule(mergedMappingRule);
                } else {
                    if (mappingRuleShouldBeMergedAndSet == java.lang.Boolean.FALSE) {
                        target.mappingRule = null;
                    }
                }
            }
            {
                java.lang.Boolean coverageMappingRuleShouldBeMergedAndSet = strategy.shouldBeMergedAndSet(leftLocator, rightLocator, leftObject.isSetCoverageMappingRule(), rightObject.isSetCoverageMappingRule());
                if (coverageMappingRuleShouldBeMergedAndSet == java.lang.Boolean.TRUE) {
                    MappingRuleType lhsCoverageMappingRule;
                    lhsCoverageMappingRule = leftObject.getCoverageMappingRule();
                    MappingRuleType rhsCoverageMappingRule;
                    rhsCoverageMappingRule = rightObject.getCoverageMappingRule();
                    MappingRuleType mergedCoverageMappingRule = ((MappingRuleType) strategy.merge(LocatorUtils.property(leftLocator, "coverageMappingRule", lhsCoverageMappingRule), LocatorUtils.property(rightLocator, "coverageMappingRule", rhsCoverageMappingRule), lhsCoverageMappingRule, rhsCoverageMappingRule, leftObject.isSetCoverageMappingRule(), rightObject.isSetCoverageMappingRule()));
                    target.setCoverageMappingRule(mergedCoverageMappingRule);
                } else {
                    if (coverageMappingRuleShouldBeMergedAndSet == java.lang.Boolean.FALSE) {
                        target.coverageMappingRule = null;
                    }
                }
            }
            {
                java.lang.Boolean gridFunctionShouldBeMergedAndSet = strategy.shouldBeMergedAndSet(leftLocator, rightLocator, leftObject.isSetGridFunction(), rightObject.isSetGridFunction());
                if (gridFunctionShouldBeMergedAndSet == java.lang.Boolean.TRUE) {
                    GridFunctionType lhsGridFunction;
                    lhsGridFunction = leftObject.getGridFunction();
                    GridFunctionType rhsGridFunction;
                    rhsGridFunction = rightObject.getGridFunction();
                    GridFunctionType mergedGridFunction = ((GridFunctionType) strategy.merge(LocatorUtils.property(leftLocator, "gridFunction", lhsGridFunction), LocatorUtils.property(rightLocator, "gridFunction", rhsGridFunction), lhsGridFunction, rhsGridFunction, leftObject.isSetGridFunction(), rightObject.isSetGridFunction()));
                    target.setGridFunction(mergedGridFunction);
                } else {
                    if (gridFunctionShouldBeMergedAndSet == java.lang.Boolean.FALSE) {
                        target.gridFunction = null;
                    }
                }
            }
        }
    }

}
