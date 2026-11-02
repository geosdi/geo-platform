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
import jakarta.xml.bind.annotation.XmlType;
import org.geosdi.geoplatform.xml.iso19139.v20070417.gco.AbstractObjectType;
import org.geosdi.geoplatform.xml.iso19139.v20070417.gco.CharacterStringPropertyType;
import org.geosdi.geoplatform.xml.iso19139.v20070417.gco.IntegerPropertyType;
import org.geosdi.geoplatform.xml.iso19139.v20070417.gco.RealPropertyType;
import org.jvnet.jaxb.lang.JAXBToStringStrategy;
import org.jvnet.jaxb.lang.ToString;
import org.jvnet.jaxb.lang.ToStringStrategy;
import org.jvnet.jaxb.locator.ObjectLocator;


/**
 * Information about the media on which the data can be distributed
 * 
 * <p>Classe Java per MD_Medium_Type complex type.</p>
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.</p>
 * 
 * <pre>{@code
 * <complexType name="MD_Medium_Type">
 *   <complexContent>
 *     <extension base="{http://www.isotc211.org/2005/gco}AbstractObject_Type">
 *       <sequence>
 *         <element name="name" type="{http://www.isotc211.org/2005/gmd}MD_MediumNameCode_PropertyType" minOccurs="0"/>
 *         <element name="density" type="{http://www.isotc211.org/2005/gco}Real_PropertyType" maxOccurs="unbounded" minOccurs="0"/>
 *         <element name="densityUnits" type="{http://www.isotc211.org/2005/gco}CharacterString_PropertyType" minOccurs="0"/>
 *         <element name="volumes" type="{http://www.isotc211.org/2005/gco}Integer_PropertyType" minOccurs="0"/>
 *         <element name="mediumFormat" type="{http://www.isotc211.org/2005/gmd}MD_MediumFormatCode_PropertyType" maxOccurs="unbounded" minOccurs="0"/>
 *         <element name="mediumNote" type="{http://www.isotc211.org/2005/gco}CharacterString_PropertyType" minOccurs="0"/>
 *       </sequence>
 *     </extension>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "MD_Medium_Type", propOrder = {
    "name",
    "density",
    "densityUnits",
    "volumes",
    "mediumFormat",
    "mediumNote"
})
public class MDMediumType
    extends AbstractObjectType
    implements ToString
{

    protected MDMediumNameCodePropertyType name;
    protected List<RealPropertyType> density;
    protected CharacterStringPropertyType densityUnits;
    protected IntegerPropertyType volumes;
    protected List<MDMediumFormatCodePropertyType> mediumFormat;
    protected CharacterStringPropertyType mediumNote;

    /**
     * Recupera il valore della proprietà name.
     * 
     * @return
     *     possible object is
     *     {@link MDMediumNameCodePropertyType }
     *     
     */
    public MDMediumNameCodePropertyType getName() {
        return name;
    }

    /**
     * Imposta il valore della proprietà name.
     * 
     * @param value
     *     allowed object is
     *     {@link MDMediumNameCodePropertyType }
     *     
     */
    public void setName(MDMediumNameCodePropertyType value) {
        this.name = value;
    }

    public boolean isSetName() {
        return (this.name!= null);
    }

    /**
     * Gets the value of the density property.
     * 
     * <p>This accessor method returns a reference to the live list,
     * not a snapshot. Therefore, any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a {@code set} method for the density property.</p>
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * </p>
     * <pre>
     * getDensity().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link RealPropertyType }
     * </p>
     * 
     * 
     * @return
     *     The value of the density property.
     */
    public List<RealPropertyType> getDensity() {
        if (density == null) {
            density = new ArrayList<>();
        }
        return this.density;
    }

    public boolean isSetDensity() {
        return ((this.density!= null)&&(!this.density.isEmpty()));
    }

    public void unsetDensity() {
        this.density = null;
    }

    /**
     * Recupera il valore della proprietà densityUnits.
     * 
     * @return
     *     possible object is
     *     {@link CharacterStringPropertyType }
     *     
     */
    public CharacterStringPropertyType getDensityUnits() {
        return densityUnits;
    }

    /**
     * Imposta il valore della proprietà densityUnits.
     * 
     * @param value
     *     allowed object is
     *     {@link CharacterStringPropertyType }
     *     
     */
    public void setDensityUnits(CharacterStringPropertyType value) {
        this.densityUnits = value;
    }

    public boolean isSetDensityUnits() {
        return (this.densityUnits!= null);
    }

    /**
     * Recupera il valore della proprietà volumes.
     * 
     * @return
     *     possible object is
     *     {@link IntegerPropertyType }
     *     
     */
    public IntegerPropertyType getVolumes() {
        return volumes;
    }

    /**
     * Imposta il valore della proprietà volumes.
     * 
     * @param value
     *     allowed object is
     *     {@link IntegerPropertyType }
     *     
     */
    public void setVolumes(IntegerPropertyType value) {
        this.volumes = value;
    }

    public boolean isSetVolumes() {
        return (this.volumes!= null);
    }

    /**
     * Gets the value of the mediumFormat property.
     * 
     * <p>This accessor method returns a reference to the live list,
     * not a snapshot. Therefore, any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a {@code set} method for the mediumFormat property.</p>
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * </p>
     * <pre>
     * getMediumFormat().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link MDMediumFormatCodePropertyType }
     * </p>
     * 
     * 
     * @return
     *     The value of the mediumFormat property.
     */
    public List<MDMediumFormatCodePropertyType> getMediumFormat() {
        if (mediumFormat == null) {
            mediumFormat = new ArrayList<>();
        }
        return this.mediumFormat;
    }

    public boolean isSetMediumFormat() {
        return ((this.mediumFormat!= null)&&(!this.mediumFormat.isEmpty()));
    }

    public void unsetMediumFormat() {
        this.mediumFormat = null;
    }

    /**
     * Recupera il valore della proprietà mediumNote.
     * 
     * @return
     *     possible object is
     *     {@link CharacterStringPropertyType }
     *     
     */
    public CharacterStringPropertyType getMediumNote() {
        return mediumNote;
    }

    /**
     * Imposta il valore della proprietà mediumNote.
     * 
     * @param value
     *     allowed object is
     *     {@link CharacterStringPropertyType }
     *     
     */
    public void setMediumNote(CharacterStringPropertyType value) {
        this.mediumNote = value;
    }

    public boolean isSetMediumNote() {
        return (this.mediumNote!= null);
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
            MDMediumNameCodePropertyType theName;
            theName = this.getName();
            strategy.appendField(locator, this, "name", buffer, theName, this.isSetName());
        }
        {
            List<RealPropertyType> theDensity;
            theDensity = (this.isSetDensity()?this.getDensity():null);
            strategy.appendField(locator, this, "density", buffer, theDensity, this.isSetDensity());
        }
        {
            CharacterStringPropertyType theDensityUnits;
            theDensityUnits = this.getDensityUnits();
            strategy.appendField(locator, this, "densityUnits", buffer, theDensityUnits, this.isSetDensityUnits());
        }
        {
            IntegerPropertyType theVolumes;
            theVolumes = this.getVolumes();
            strategy.appendField(locator, this, "volumes", buffer, theVolumes, this.isSetVolumes());
        }
        {
            List<MDMediumFormatCodePropertyType> theMediumFormat;
            theMediumFormat = (this.isSetMediumFormat()?this.getMediumFormat():null);
            strategy.appendField(locator, this, "mediumFormat", buffer, theMediumFormat, this.isSetMediumFormat());
        }
        {
            CharacterStringPropertyType theMediumNote;
            theMediumNote = this.getMediumNote();
            strategy.appendField(locator, this, "mediumNote", buffer, theMediumNote, this.isSetMediumNote());
        }
        return buffer;
    }

    public void setDensity(List<RealPropertyType> value) {
        this.density = null;
        if (value!= null) {
            List<RealPropertyType> draftl = this.getDensity();
            draftl.addAll(value);
        }
    }

    public void setMediumFormat(List<MDMediumFormatCodePropertyType> value) {
        this.mediumFormat = null;
        if (value!= null) {
            List<MDMediumFormatCodePropertyType> draftl = this.getMediumFormat();
            draftl.addAll(value);
        }
    }

}
