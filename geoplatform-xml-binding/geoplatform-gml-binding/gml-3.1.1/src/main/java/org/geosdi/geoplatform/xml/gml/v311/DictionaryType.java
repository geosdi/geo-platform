/*
 *
 *    geo-platform
 *    Rich webgis framework
 *    http://geo-platform.org
 *   ====================================================================
 *
 *   Copyright (C) 2008-2025 geoSDI Group (CNR IMAA - Potenza - ITALY).
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
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.5-b10 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2012.04.17 at 10:27:36 PM CEST 
//


package org.geosdi.geoplatform.xml.gml.v311;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlElementRefs;
import javax.xml.bind.annotation.XmlType;
import org.jvnet.jaxb2_commons.lang.JAXBToStringStrategy;
import org.jvnet.jaxb2_commons.lang.ToString;
import org.jvnet.jaxb2_commons.lang.ToStringStrategy;
import org.jvnet.jaxb2_commons.locator.ObjectLocator;


/**
 * A non-abstract bag that is specialized for use as a dictionary which contains a set of definitions. These definitions are referenced from other places, in the same and different XML documents. In this restricted type, the inherited optional "description" element can be used for a description of this dictionary. The inherited optional "name" element can be used for the name(s) of this dictionary. The inherited "metaDataProperty" elements can be used to reference or contain more information about this dictionary. The inherited required gml:id attribute allows the dictionary to be referenced using this handle. 
 * 
 * <p>Java class for DictionaryType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="DictionaryType">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.opengis.net/gml}DefinitionType">
 *       &lt;sequence maxOccurs="unbounded" minOccurs="0">
 *         &lt;choice>
 *           &lt;element ref="{http://www.opengis.net/gml}dictionaryEntry"/>
 *           &lt;element ref="{http://www.opengis.net/gml}indirectEntry"/>
 *         &lt;/choice>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DictionaryType", propOrder = {
    "dictionaryEntryOrIndirectEntry"
})
public class DictionaryType
    extends DefinitionType
    implements ToString
{

    @XmlElementRefs({
        @XmlElementRef(name = "indirectEntry", namespace = "http://www.opengis.net/gml", type = JAXBElement.class),
        @XmlElementRef(name = "dictionaryEntry", namespace = "http://www.opengis.net/gml", type = JAXBElement.class)
    })
    protected List<JAXBElement<?>> dictionaryEntryOrIndirectEntry;

    /**
     * Gets the value of the dictionaryEntryOrIndirectEntry property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the dictionaryEntryOrIndirectEntry property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDictionaryEntryOrIndirectEntry().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link JAXBElement }{@code <}{@link DictionaryEntryType }{@code >}
     * {@link JAXBElement }{@code <}{@link DictionaryEntryType }{@code >}
     * {@link JAXBElement }{@code <}{@link IndirectEntryType }{@code >}
     * 
     * 
     */
    public List<JAXBElement<?>> getDictionaryEntryOrIndirectEntry() {
        if (dictionaryEntryOrIndirectEntry == null) {
            dictionaryEntryOrIndirectEntry = new ArrayList<JAXBElement<?>>();
        }
        return this.dictionaryEntryOrIndirectEntry;
    }

    public boolean isSetDictionaryEntryOrIndirectEntry() {
        return ((this.dictionaryEntryOrIndirectEntry!= null)&&(!this.dictionaryEntryOrIndirectEntry.isEmpty()));
    }

    public void unsetDictionaryEntryOrIndirectEntry() {
        this.dictionaryEntryOrIndirectEntry = null;
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
            List<JAXBElement<?>> theDictionaryEntryOrIndirectEntry;
            theDictionaryEntryOrIndirectEntry = (this.isSetDictionaryEntryOrIndirectEntry()?this.getDictionaryEntryOrIndirectEntry():null);
            strategy.appendField(locator, this, "dictionaryEntryOrIndirectEntry", buffer, theDictionaryEntryOrIndirectEntry);
        }
        return buffer;
    }

    public void setDictionaryEntryOrIndirectEntry(List<JAXBElement<?>> value) {
        this.dictionaryEntryOrIndirectEntry = null;
        List<JAXBElement<?>> draftl = this.getDictionaryEntryOrIndirectEntry();
        draftl.addAll(value);
    }

}
