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
// Generato il: 2026.11.02 alle 03:48:04 PM CET
//

package org.geosdi.geoplatform.xml.gml.v321;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;
import org.jvnet.jaxb.lang.JAXBToStringStrategy;
import org.jvnet.jaxb.lang.ToString;
import org.jvnet.jaxb.lang.ToStringStrategy;
import org.jvnet.jaxb.locator.ObjectLocator;


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
 *         <element ref="{http://www.opengis.net/gml/3.2}MappingRule"/>
 *         <element ref="{http://www.opengis.net/gml/3.2}CoverageMappingRule"/>
 *         <element ref="{http://www.opengis.net/gml/3.2}GridFunction"/>
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
public class CoverageFunctionType implements ToString
{

    @XmlElement(name = "MappingRule")
    protected StringOrRefType mappingRule;
    /**
     * gml:CoverageMappingRule provides a formal or informal description of the coverage function.
     * The mapping rule may be defined as an in-line string (gml:ruleDefinition) or via a remote reference through xlink:href (gml:ruleReference).  
     * If no rule name is specified, the default is 'Linear' with respect to members of the domain in document order.
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
     * If no rule name is specified, the default is 'Linear' with respect to members of the domain in document order.
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

}
