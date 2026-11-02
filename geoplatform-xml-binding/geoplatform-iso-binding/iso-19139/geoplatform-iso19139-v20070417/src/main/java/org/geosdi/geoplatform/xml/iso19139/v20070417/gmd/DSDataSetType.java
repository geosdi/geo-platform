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
// Generato il: 2026.11.02 alle 03:48:01 PM CET
//

package org.geosdi.geoplatform.xml.iso19139.v20070417.gmd;

import java.util.ArrayList;
import java.util.List;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlSeeAlso;
import jakarta.xml.bind.annotation.XmlType;
import org.geosdi.geoplatform.xml.iso19139.v20070417.gco.AbstractObjectType;
import org.geosdi.geoplatform.xml.iso19139.v20070417.gmx.MXDataSetType;
import org.jvnet.jaxb.lang.JAXBToStringStrategy;
import org.jvnet.jaxb.lang.ToString;
import org.jvnet.jaxb.lang.ToStringStrategy;
import org.jvnet.jaxb.locator.ObjectLocator;


/**
 * Identifiable collection of data
 * 
 * <p>Classe Java per DS_DataSet_Type complex type.</p>
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.</p>
 * 
 * <pre>{@code
 * <complexType name="DS_DataSet_Type">
 *   <complexContent>
 *     <extension base="{http://www.isotc211.org/2005/gco}AbstractObject_Type">
 *       <sequence>
 *         <element name="has" type="{http://www.isotc211.org/2005/gmd}MD_Metadata_PropertyType" maxOccurs="unbounded"/>
 *         <element name="partOf" type="{http://www.isotc211.org/2005/gmd}DS_Aggregate_PropertyType" maxOccurs="unbounded" minOccurs="0"/>
 *       </sequence>
 *     </extension>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DS_DataSet_Type", propOrder = {
    "has",
    "partOf"
})
@XmlSeeAlso({
    MXDataSetType.class
})
public class DSDataSetType
    extends AbstractObjectType
    implements ToString
{

    @XmlElement(required = true)
    protected List<MDMetadataPropertyType> has;
    protected List<DSAggregatePropertyType> partOf;

    /**
     * Gets the value of the has property.
     * 
     * <p>This accessor method returns a reference to the live list,
     * not a snapshot. Therefore, any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a {@code set} method for the has property.</p>
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * </p>
     * <pre>
     * getHas().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link MDMetadataPropertyType }
     * </p>
     * 
     * 
     * @return
     *     The value of the has property.
     */
    public List<MDMetadataPropertyType> getHas() {
        if (has == null) {
            has = new ArrayList<>();
        }
        return this.has;
    }

    public boolean isSetHas() {
        return ((this.has!= null)&&(!this.has.isEmpty()));
    }

    public void unsetHas() {
        this.has = null;
    }

    /**
     * Gets the value of the partOf property.
     * 
     * <p>This accessor method returns a reference to the live list,
     * not a snapshot. Therefore, any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a {@code set} method for the partOf property.</p>
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * </p>
     * <pre>
     * getPartOf().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DSAggregatePropertyType }
     * </p>
     * 
     * 
     * @return
     *     The value of the partOf property.
     */
    public List<DSAggregatePropertyType> getPartOf() {
        if (partOf == null) {
            partOf = new ArrayList<>();
        }
        return this.partOf;
    }

    public boolean isSetPartOf() {
        return ((this.partOf!= null)&&(!this.partOf.isEmpty()));
    }

    public void unsetPartOf() {
        this.partOf = null;
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
            List<MDMetadataPropertyType> theHas;
            theHas = (this.isSetHas()?this.getHas():null);
            strategy.appendField(locator, this, "has", buffer, theHas, this.isSetHas());
        }
        {
            List<DSAggregatePropertyType> thePartOf;
            thePartOf = (this.isSetPartOf()?this.getPartOf():null);
            strategy.appendField(locator, this, "partOf", buffer, thePartOf, this.isSetPartOf());
        }
        return buffer;
    }

    public void setHas(List<MDMetadataPropertyType> value) {
        this.has = null;
        if (value!= null) {
            List<MDMetadataPropertyType> draftl = this.getHas();
            draftl.addAll(value);
        }
    }

    public void setPartOf(List<DSAggregatePropertyType> value) {
        this.partOf = null;
        if (value!= null) {
            List<DSAggregatePropertyType> draftl = this.getPartOf();
            draftl.addAll(value);
        }
    }

}
