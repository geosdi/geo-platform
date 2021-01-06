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
// Questo file è stato generato dall'architettura JavaTM per XML Binding (JAXB) Reference Implementation, v2.3.0 
// Vedere <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a> 
// Qualsiasi modifica a questo file andrà persa durante la ricompilazione dello schema di origine. 
// Generato il: 2019.03.07 alle 09:04:14 AM CET 
//


package org.geosdi.geoplatform.xml.filter.v110;

import org.jvnet.jaxb2_commons.lang.JAXBToStringStrategy;
import org.jvnet.jaxb2_commons.lang.ToString2;
import org.jvnet.jaxb2_commons.lang.ToStringStrategy2;
import org.jvnet.jaxb2_commons.locator.ObjectLocator;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;


/**
 * <p>Classe Java per FilterType complex type.
 *
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 *
 * <pre>
 * &lt;complexType name="FilterType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;choice&gt;
 *         &lt;element ref="{http://www.opengis.net/ogc}spatialOps"/&gt;
 *         &lt;element ref="{http://www.opengis.net/ogc}comparisonOps"/&gt;
 *         &lt;element ref="{http://www.opengis.net/ogc}logicOps"/&gt;
 *         &lt;element ref="{http://www.opengis.net/ogc}_Id" maxOccurs="unbounded"/&gt;
 *       &lt;/choice&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "FilterType", propOrder = {
        "spatialOps",
        "comparisonOps",
        "logicOps",
        "id"
})
@XmlRootElement(name = "Filter")
public class FilterType implements ToString2 {

    @XmlElementRef(name = "spatialOps", namespace = "http://www.opengis.net/ogc", type = JAXBElement.class, required = false)
    protected JAXBElement<? extends SpatialOpsType> spatialOps;
    @XmlElementRef(name = "comparisonOps", namespace = "http://www.opengis.net/ogc", type = JAXBElement.class, required = false)
    protected JAXBElement<? extends ComparisonOpsType> comparisonOps;
    @XmlElementRef(name = "logicOps", namespace = "http://www.opengis.net/ogc", type = JAXBElement.class, required = false)
    protected JAXBElement<? extends LogicOpsType> logicOps;
    @XmlElementRef(name = "_Id", namespace = "http://www.opengis.net/ogc", type = JAXBElement.class, required = false)
    protected List<JAXBElement<? extends AbstractIdType>> id;

    /**
     * Recupera il valore della proprietà spatialOps.
     *
     * @return possible object is
     * {@link JAXBElement }{@code <}{@link DistanceBufferType }{@code >}
     * {@link JAXBElement }{@code <}{@link BinarySpatialOpType }{@code >}
     * {@link JAXBElement }{@code <}{@link BinarySpatialOpType }{@code >}
     * {@link JAXBElement }{@code <}{@link BinarySpatialOpType }{@code >}
     * {@link JAXBElement }{@code <}{@link BBOXType }{@code >}
     * {@link JAXBElement }{@code <}{@link BinarySpatialOpType }{@code >}
     * {@link JAXBElement }{@code <}{@link BinarySpatialOpType }{@code >}
     * {@link JAXBElement }{@code <}{@link BinarySpatialOpType }{@code >}
     * {@link JAXBElement }{@code <}{@link BinarySpatialOpType }{@code >}
     * {@link JAXBElement }{@code <}{@link BinarySpatialOpType }{@code >}
     * {@link JAXBElement }{@code <}{@link DistanceBufferType }{@code >}
     * {@link JAXBElement }{@code <}{@link SpatialOpsType }{@code >}
     */
    public JAXBElement<? extends SpatialOpsType> getSpatialOps() {
        return spatialOps;
    }

    /**
     * Imposta il valore della proprietà spatialOps.
     *
     * @param value allowed object is
     *              {@link JAXBElement }{@code <}{@link DistanceBufferType }{@code >}
     *              {@link JAXBElement }{@code <}{@link BinarySpatialOpType }{@code >}
     *              {@link JAXBElement }{@code <}{@link BinarySpatialOpType }{@code >}
     *              {@link JAXBElement }{@code <}{@link BinarySpatialOpType }{@code >}
     *              {@link JAXBElement }{@code <}{@link BBOXType }{@code >}
     *              {@link JAXBElement }{@code <}{@link BinarySpatialOpType }{@code >}
     *              {@link JAXBElement }{@code <}{@link BinarySpatialOpType }{@code >}
     *              {@link JAXBElement }{@code <}{@link BinarySpatialOpType }{@code >}
     *              {@link JAXBElement }{@code <}{@link BinarySpatialOpType }{@code >}
     *              {@link JAXBElement }{@code <}{@link BinarySpatialOpType }{@code >}
     *              {@link JAXBElement }{@code <}{@link DistanceBufferType }{@code >}
     *              {@link JAXBElement }{@code <}{@link SpatialOpsType }{@code >}
     */
    public void setSpatialOps(JAXBElement<? extends SpatialOpsType> value) {
        this.spatialOps = value;
    }

    public boolean isSetSpatialOps() {
        return (this.spatialOps != null);
    }

    /**
     * Recupera il valore della proprietà comparisonOps.
     *
     * @return possible object is
     * {@link JAXBElement }{@code <}{@link PropertyIsLikeType }{@code >}
     * {@link JAXBElement }{@code <}{@link BinaryComparisonOpType }{@code >}
     * {@link JAXBElement }{@code <}{@link PropertyIsBetweenType }{@code >}
     * {@link JAXBElement }{@code <}{@link BinaryComparisonOpType }{@code >}
     * {@link JAXBElement }{@code <}{@link BinaryComparisonOpType }{@code >}
     * {@link JAXBElement }{@code <}{@link PropertyIsNullType }{@code >}
     * {@link JAXBElement }{@code <}{@link BinaryComparisonOpType }{@code >}
     * {@link JAXBElement }{@code <}{@link BinaryComparisonOpType }{@code >}
     * {@link JAXBElement }{@code <}{@link BinaryComparisonOpType }{@code >}
     * {@link JAXBElement }{@code <}{@link ComparisonOpsType }{@code >}
     */
    public JAXBElement<? extends ComparisonOpsType> getComparisonOps() {
        return comparisonOps;
    }

    /**
     * Imposta il valore della proprietà comparisonOps.
     *
     * @param value allowed object is
     *              {@link JAXBElement }{@code <}{@link PropertyIsLikeType }{@code >}
     *              {@link JAXBElement }{@code <}{@link BinaryComparisonOpType }{@code >}
     *              {@link JAXBElement }{@code <}{@link PropertyIsBetweenType }{@code >}
     *              {@link JAXBElement }{@code <}{@link BinaryComparisonOpType }{@code >}
     *              {@link JAXBElement }{@code <}{@link BinaryComparisonOpType }{@code >}
     *              {@link JAXBElement }{@code <}{@link PropertyIsNullType }{@code >}
     *              {@link JAXBElement }{@code <}{@link BinaryComparisonOpType }{@code >}
     *              {@link JAXBElement }{@code <}{@link BinaryComparisonOpType }{@code >}
     *              {@link JAXBElement }{@code <}{@link BinaryComparisonOpType }{@code >}
     *              {@link JAXBElement }{@code <}{@link ComparisonOpsType }{@code >}
     */
    public void setComparisonOps(JAXBElement<? extends ComparisonOpsType> value) {
        this.comparisonOps = value;
    }

    public boolean isSetComparisonOps() {
        return (this.comparisonOps != null);
    }

    /**
     * Recupera il valore della proprietà logicOps.
     *
     * @return possible object is
     * {@link JAXBElement }{@code <}{@link BinaryLogicOpType }{@code >}
     * {@link JAXBElement }{@code <}{@link BinaryLogicOpType }{@code >}
     * {@link JAXBElement }{@code <}{@link UnaryLogicOpType }{@code >}
     * {@link JAXBElement }{@code <}{@link LogicOpsType }{@code >}
     */
    public JAXBElement<? extends LogicOpsType> getLogicOps() {
        return logicOps;
    }

    /**
     * Imposta il valore della proprietà logicOps.
     *
     * @param value allowed object is
     *              {@link JAXBElement }{@code <}{@link BinaryLogicOpType }{@code >}
     *              {@link JAXBElement }{@code <}{@link BinaryLogicOpType }{@code >}
     *              {@link JAXBElement }{@code <}{@link UnaryLogicOpType }{@code >}
     *              {@link JAXBElement }{@code <}{@link LogicOpsType }{@code >}
     */
    public void setLogicOps(JAXBElement<? extends LogicOpsType> value) {
        this.logicOps = value;
    }

    public boolean isSetLogicOps() {
        return (this.logicOps != null);
    }

    /**
     * Gets the value of the id property.
     *
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the id property.
     *
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getId().add(newItem);
     * </pre>
     *
     *
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link JAXBElement }{@code <}{@link FeatureIdType }{@code >}
     * {@link JAXBElement }{@code <}{@link GmlObjectIdType }{@code >}
     * {@link JAXBElement }{@code <}{@link AbstractIdType }{@code >}
     */
    public List<JAXBElement<? extends AbstractIdType>> getId() {
        if (id == null) {
            id = new ArrayList<JAXBElement<? extends AbstractIdType>>();
        }
        return this.id;
    }

    public boolean isSetId() {
        return ((this.id != null) && (!this.id.isEmpty()));
    }

    public void unsetId() {
        this.id = null;
    }

    public String toString() {
        final ToStringStrategy2 strategy = JAXBToStringStrategy.INSTANCE2;
        final StringBuilder buffer = new StringBuilder();
        append(null, buffer, strategy);
        return buffer.toString();
    }

    public StringBuilder append(ObjectLocator locator, StringBuilder buffer, ToStringStrategy2 strategy) {
        strategy.appendStart(locator, this, buffer);
        appendFields(locator, buffer, strategy);
        strategy.appendEnd(locator, this, buffer);
        return buffer;
    }

    public StringBuilder appendFields(ObjectLocator locator, StringBuilder buffer, ToStringStrategy2 strategy) {
        {
            JAXBElement<? extends SpatialOpsType> theSpatialOps;
            theSpatialOps = this.getSpatialOps();
            strategy.appendField(locator, this, "spatialOps", buffer, theSpatialOps, this.isSetSpatialOps());
        }
        {
            JAXBElement<? extends ComparisonOpsType> theComparisonOps;
            theComparisonOps = this.getComparisonOps();
            strategy.appendField(locator, this, "comparisonOps", buffer, theComparisonOps, this.isSetComparisonOps());
        }
        {
            JAXBElement<? extends LogicOpsType> theLogicOps;
            theLogicOps = this.getLogicOps();
            strategy.appendField(locator, this, "logicOps", buffer, theLogicOps, this.isSetLogicOps());
        }
        {
            List<JAXBElement<? extends AbstractIdType>> theId;
            theId = (this.isSetId() ? this.getId() : null);
            strategy.appendField(locator, this, "id", buffer, theId, this.isSetId());
        }
        return buffer;
    }

    public void setId(List<JAXBElement<? extends AbstractIdType>> value) {
        this.id = null;
        if (value != null) {
            List<JAXBElement<? extends AbstractIdType>> draftl = this.getId();
            draftl.addAll(value);
        }
    }

}
