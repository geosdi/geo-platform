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

import jakarta.xml.bind.JAXBElement;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlElementRef;
import jakarta.xml.bind.annotation.XmlSeeAlso;
import jakarta.xml.bind.annotation.XmlType;
import org.jvnet.jaxb.lang.JAXBToStringStrategy;
import org.jvnet.jaxb.lang.ToString;
import org.jvnet.jaxb.lang.ToStringStrategy;
import org.jvnet.jaxb.locator.ObjectLocator;


/**
 * The base type for coverages is gml:AbstractCoverageType. The basic elements of a coverage can be seen in this content model: the coverage contains gml:domainSet and gml:rangeSet properties. The gml:domainSet property describes the domain of the coverage and the gml:rangeSet property describes the range of the coverage.
 * 
 * <p>Classe Java per AbstractCoverageType complex type.</p>
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.</p>
 * 
 * <pre>{@code
 * <complexType name="AbstractCoverageType">
 *   <complexContent>
 *     <extension base="{http://www.opengis.net/gml/3.2}AbstractFeatureType">
 *       <sequence>
 *         <element ref="{http://www.opengis.net/gml/3.2}domainSet"/>
 *         <element ref="{http://www.opengis.net/gml/3.2}rangeSet"/>
 *       </sequence>
 *     </extension>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AbstractCoverageType", propOrder = {
    "domainSet",
    "rangeSet"
})
@XmlSeeAlso({
    DiscreteCoverageType.class,
    AbstractContinuousCoverageType.class
})
public abstract class AbstractCoverageType
    extends AbstractFeatureType
    implements ToString
{

    /**
     * The gml:domainSet property element describes the spatio-temporal region of interest, within which the coverage is defined. Its content model is given by gml:DomainSetType.
     * The value of the domain is thus a choice between a gml:AbstractGeometry and a gml:AbstractTimeObject.  In the instance these abstract elements will normally be substituted by a geometry complex or temporal complex, to represent spatial coverages and time-series, respectively.  
     * The presence of the gml:AssociationAttributeGroup means that domainSet follows the usual GML property model and may use the xlink:href attribute to point to the domain, as an alternative to describing the domain inline. Ownership semantics may be provided using the gml:OwnershipAttributeGroup.
     * 
     */
    @XmlElementRef(name = "domainSet", namespace = "http://www.opengis.net/gml/3.2", type = JAXBElement.class)
    protected JAXBElement<DomainSetType> domainSet;
    /**
     * The gml:rangeSet property element contains the values of the coverage (sometimes called the attribute values).  Its content model is given by gml:RangeSetType.
     * This content model supports a structural description of the range.  The semantic information describing the range set is embedded using a uniform method, as part of the explicit values, or as a template value accompanying the representation using gml:DataBlock and gml:File.
     * The values from each component (or "band") in the range may be encoded within a gml:ValueArray element or a concrete member of the gml:AbstractScalarValueList substitution group . Use of these elements satisfies the value-type homogeneity requirement.
     * 
     */
    @XmlElement(required = true)
    protected RangeSetType rangeSet;

    /**
     * The gml:domainSet property element describes the spatio-temporal region of interest, within which the coverage is defined. Its content model is given by gml:DomainSetType.
     * The value of the domain is thus a choice between a gml:AbstractGeometry and a gml:AbstractTimeObject.  In the instance these abstract elements will normally be substituted by a geometry complex or temporal complex, to represent spatial coverages and time-series, respectively.  
     * The presence of the gml:AssociationAttributeGroup means that domainSet follows the usual GML property model and may use the xlink:href attribute to point to the domain, as an alternative to describing the domain inline. Ownership semantics may be provided using the gml:OwnershipAttributeGroup.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link DomainSetType }{@code >}
     *     {@link JAXBElement }{@code <}{@link DomainSetType }{@code >}
     *     {@link JAXBElement }{@code <}{@link DomainSetType }{@code >}
     *     {@link JAXBElement }{@code <}{@link DomainSetType }{@code >}
     *     {@link JAXBElement }{@code <}{@link DomainSetType }{@code >}
     *     {@link JAXBElement }{@code <}{@link DomainSetType }{@code >}
     *     {@link JAXBElement }{@code <}{@link DomainSetType }{@code >}
     *     
     */
    public JAXBElement<DomainSetType> getDomainSet() {
        return domainSet;
    }

    /**
     * Imposta il valore della proprietà domainSet.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link DomainSetType }{@code >}
     *     {@link JAXBElement }{@code <}{@link DomainSetType }{@code >}
     *     {@link JAXBElement }{@code <}{@link DomainSetType }{@code >}
     *     {@link JAXBElement }{@code <}{@link DomainSetType }{@code >}
     *     {@link JAXBElement }{@code <}{@link DomainSetType }{@code >}
     *     {@link JAXBElement }{@code <}{@link DomainSetType }{@code >}
     *     {@link JAXBElement }{@code <}{@link DomainSetType }{@code >}
     *     
     * @see #getDomainSet()
     */
    public void setDomainSet(JAXBElement<DomainSetType> value) {
        this.domainSet = value;
    }

    public boolean isSetDomainSet() {
        return (this.domainSet!= null);
    }

    /**
     * The gml:rangeSet property element contains the values of the coverage (sometimes called the attribute values).  Its content model is given by gml:RangeSetType.
     * This content model supports a structural description of the range.  The semantic information describing the range set is embedded using a uniform method, as part of the explicit values, or as a template value accompanying the representation using gml:DataBlock and gml:File.
     * The values from each component (or "band") in the range may be encoded within a gml:ValueArray element or a concrete member of the gml:AbstractScalarValueList substitution group . Use of these elements satisfies the value-type homogeneity requirement.
     * 
     * @return
     *     possible object is
     *     {@link RangeSetType }
     *     
     */
    public RangeSetType getRangeSet() {
        return rangeSet;
    }

    /**
     * Imposta il valore della proprietà rangeSet.
     * 
     * @param value
     *     allowed object is
     *     {@link RangeSetType }
     *     
     * @see #getRangeSet()
     */
    public void setRangeSet(RangeSetType value) {
        this.rangeSet = value;
    }

    public boolean isSetRangeSet() {
        return (this.rangeSet!= null);
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
            JAXBElement<DomainSetType> theDomainSet;
            theDomainSet = this.getDomainSet();
            strategy.appendField(locator, this, "domainSet", buffer, theDomainSet, this.isSetDomainSet());
        }
        {
            RangeSetType theRangeSet;
            theRangeSet = this.getRangeSet();
            strategy.appendField(locator, this, "rangeSet", buffer, theRangeSet, this.isSetRangeSet());
        }
        return buffer;
    }

}
