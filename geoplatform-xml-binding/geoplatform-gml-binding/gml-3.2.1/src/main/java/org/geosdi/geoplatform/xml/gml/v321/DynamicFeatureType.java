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
import jakarta.xml.bind.annotation.XmlElementRef;
import jakarta.xml.bind.annotation.XmlSeeAlso;
import jakarta.xml.bind.annotation.XmlType;
import org.jvnet.jaxb.lang.JAXBToStringStrategy;
import org.jvnet.jaxb.lang.ToString;
import org.jvnet.jaxb.lang.ToStringStrategy;
import org.jvnet.jaxb.locator.ObjectLocator;


/**
 * <p>Classe Java per DynamicFeatureType complex type.</p>
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.</p>
 * 
 * <pre>{@code
 * <complexType name="DynamicFeatureType">
 *   <complexContent>
 *     <extension base="{http://www.opengis.net/gml/3.2}AbstractFeatureType">
 *       <group ref="{http://www.opengis.net/gml/3.2}dynamicProperties"/>
 *     </extension>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DynamicFeatureType", propOrder = {
    "validTime",
    "history",
    "dataSource",
    "dataSourceReference"
})
@XmlSeeAlso({
    DynamicFeatureCollectionType.class
})
public class DynamicFeatureType
    extends AbstractFeatureType
    implements ToString
{

    /**
     * gml:validTime is a convenience property element.
     * 
     */
    protected TimePrimitivePropertyType validTime;
    /**
     * A generic sequence of events constitute a gml:history of an object.
     * The gml:history element contains a set of elements in the substitution group headed by the abstract element gml:AbstractTimeSlice, representing the time-varying properties of interest. The history property of a dynamic feature associates a feature instance with a sequence of time slices (i.e. change events) that encapsulate the evolution of the feature.
     * 
     */
    @XmlElementRef(name = "history", namespace = "http://www.opengis.net/gml/3.2", type = JAXBElement.class, required = false)
    protected JAXBElement<HistoryPropertyType> history;
    /**
     * Evidence is represented by a simple gml:dataSource or gml:dataSourceReference property that indicates the source of the temporal data. The remote link attributes of the gml:dataSource element have been deprecated along with its current type.
     * 
     */
    protected StringOrRefType dataSource;
    /**
     * Evidence is represented by a simple gml:dataSource or gml:dataSourceReference property that indicates the source of the temporal data.
     * 
     */
    protected ReferenceType dataSourceReference;

    /**
     * gml:validTime is a convenience property element.
     * 
     * @return
     *     possible object is
     *     {@link TimePrimitivePropertyType }
     *     
     */
    public TimePrimitivePropertyType getValidTime() {
        return validTime;
    }

    /**
     * Imposta il valore della proprietà validTime.
     * 
     * @param value
     *     allowed object is
     *     {@link TimePrimitivePropertyType }
     *     
     * @see #getValidTime()
     */
    public void setValidTime(TimePrimitivePropertyType value) {
        this.validTime = value;
    }

    public boolean isSetValidTime() {
        return (this.validTime!= null);
    }

    /**
     * A generic sequence of events constitute a gml:history of an object.
     * The gml:history element contains a set of elements in the substitution group headed by the abstract element gml:AbstractTimeSlice, representing the time-varying properties of interest. The history property of a dynamic feature associates a feature instance with a sequence of time slices (i.e. change events) that encapsulate the evolution of the feature.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link HistoryPropertyType }{@code >}
     *     {@link JAXBElement }{@code <}{@link HistoryPropertyType }{@code >}
     *     
     */
    public JAXBElement<HistoryPropertyType> getHistory() {
        return history;
    }

    /**
     * Imposta il valore della proprietà history.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link HistoryPropertyType }{@code >}
     *     {@link JAXBElement }{@code <}{@link HistoryPropertyType }{@code >}
     *     
     * @see #getHistory()
     */
    public void setHistory(JAXBElement<HistoryPropertyType> value) {
        this.history = value;
    }

    public boolean isSetHistory() {
        return (this.history!= null);
    }

    /**
     * Evidence is represented by a simple gml:dataSource or gml:dataSourceReference property that indicates the source of the temporal data. The remote link attributes of the gml:dataSource element have been deprecated along with its current type.
     * 
     * @return
     *     possible object is
     *     {@link StringOrRefType }
     *     
     */
    public StringOrRefType getDataSource() {
        return dataSource;
    }

    /**
     * Imposta il valore della proprietà dataSource.
     * 
     * @param value
     *     allowed object is
     *     {@link StringOrRefType }
     *     
     * @see #getDataSource()
     */
    public void setDataSource(StringOrRefType value) {
        this.dataSource = value;
    }

    public boolean isSetDataSource() {
        return (this.dataSource!= null);
    }

    /**
     * Evidence is represented by a simple gml:dataSource or gml:dataSourceReference property that indicates the source of the temporal data.
     * 
     * @return
     *     possible object is
     *     {@link ReferenceType }
     *     
     */
    public ReferenceType getDataSourceReference() {
        return dataSourceReference;
    }

    /**
     * Imposta il valore della proprietà dataSourceReference.
     * 
     * @param value
     *     allowed object is
     *     {@link ReferenceType }
     *     
     * @see #getDataSourceReference()
     */
    public void setDataSourceReference(ReferenceType value) {
        this.dataSourceReference = value;
    }

    public boolean isSetDataSourceReference() {
        return (this.dataSourceReference!= null);
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
            TimePrimitivePropertyType theValidTime;
            theValidTime = this.getValidTime();
            strategy.appendField(locator, this, "validTime", buffer, theValidTime, this.isSetValidTime());
        }
        {
            JAXBElement<HistoryPropertyType> theHistory;
            theHistory = this.getHistory();
            strategy.appendField(locator, this, "history", buffer, theHistory, this.isSetHistory());
        }
        {
            StringOrRefType theDataSource;
            theDataSource = this.getDataSource();
            strategy.appendField(locator, this, "dataSource", buffer, theDataSource, this.isSetDataSource());
        }
        {
            ReferenceType theDataSourceReference;
            theDataSourceReference = this.getDataSourceReference();
            strategy.appendField(locator, this, "dataSourceReference", buffer, theDataSourceReference, this.isSetDataSourceReference());
        }
        return buffer;
    }

}
