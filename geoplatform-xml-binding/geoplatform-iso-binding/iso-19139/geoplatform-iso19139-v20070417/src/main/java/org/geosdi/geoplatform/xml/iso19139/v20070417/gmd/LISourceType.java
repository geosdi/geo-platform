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
import org.jvnet.jaxb.lang.JAXBToStringStrategy;
import org.jvnet.jaxb.lang.ToString;
import org.jvnet.jaxb.lang.ToStringStrategy;
import org.jvnet.jaxb.locator.ObjectLocator;


/**
 * <p>Classe Java per LI_Source_Type complex type.</p>
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.</p>
 * 
 * <pre>{@code
 * <complexType name="LI_Source_Type">
 *   <complexContent>
 *     <extension base="{http://www.isotc211.org/2005/gco}AbstractObject_Type">
 *       <sequence>
 *         <element name="description" type="{http://www.isotc211.org/2005/gco}CharacterString_PropertyType" minOccurs="0"/>
 *         <element name="scaleDenominator" type="{http://www.isotc211.org/2005/gmd}MD_RepresentativeFraction_PropertyType" minOccurs="0"/>
 *         <element name="sourceReferenceSystem" type="{http://www.isotc211.org/2005/gmd}MD_ReferenceSystem_PropertyType" minOccurs="0"/>
 *         <element name="sourceCitation" type="{http://www.isotc211.org/2005/gmd}CI_Citation_PropertyType" minOccurs="0"/>
 *         <element name="sourceExtent" type="{http://www.isotc211.org/2005/gmd}EX_Extent_PropertyType" maxOccurs="unbounded" minOccurs="0"/>
 *         <element name="sourceStep" type="{http://www.isotc211.org/2005/gmd}LI_ProcessStep_PropertyType" maxOccurs="unbounded" minOccurs="0"/>
 *       </sequence>
 *     </extension>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "LI_Source_Type", propOrder = {
    "description",
    "scaleDenominator",
    "sourceReferenceSystem",
    "sourceCitation",
    "sourceExtent",
    "sourceStep"
})
public class LISourceType
    extends AbstractObjectType
    implements ToString
{

    protected CharacterStringPropertyType description;
    protected MDRepresentativeFractionPropertyType scaleDenominator;
    protected MDReferenceSystemPropertyType sourceReferenceSystem;
    protected CICitationPropertyType sourceCitation;
    protected List<EXExtentPropertyType> sourceExtent;
    protected List<LIProcessStepPropertyType> sourceStep;

    /**
     * Recupera il valore della proprietà description.
     * 
     * @return
     *     possible object is
     *     {@link CharacterStringPropertyType }
     *     
     */
    public CharacterStringPropertyType getDescription() {
        return description;
    }

    /**
     * Imposta il valore della proprietà description.
     * 
     * @param value
     *     allowed object is
     *     {@link CharacterStringPropertyType }
     *     
     */
    public void setDescription(CharacterStringPropertyType value) {
        this.description = value;
    }

    public boolean isSetDescription() {
        return (this.description!= null);
    }

    /**
     * Recupera il valore della proprietà scaleDenominator.
     * 
     * @return
     *     possible object is
     *     {@link MDRepresentativeFractionPropertyType }
     *     
     */
    public MDRepresentativeFractionPropertyType getScaleDenominator() {
        return scaleDenominator;
    }

    /**
     * Imposta il valore della proprietà scaleDenominator.
     * 
     * @param value
     *     allowed object is
     *     {@link MDRepresentativeFractionPropertyType }
     *     
     */
    public void setScaleDenominator(MDRepresentativeFractionPropertyType value) {
        this.scaleDenominator = value;
    }

    public boolean isSetScaleDenominator() {
        return (this.scaleDenominator!= null);
    }

    /**
     * Recupera il valore della proprietà sourceReferenceSystem.
     * 
     * @return
     *     possible object is
     *     {@link MDReferenceSystemPropertyType }
     *     
     */
    public MDReferenceSystemPropertyType getSourceReferenceSystem() {
        return sourceReferenceSystem;
    }

    /**
     * Imposta il valore della proprietà sourceReferenceSystem.
     * 
     * @param value
     *     allowed object is
     *     {@link MDReferenceSystemPropertyType }
     *     
     */
    public void setSourceReferenceSystem(MDReferenceSystemPropertyType value) {
        this.sourceReferenceSystem = value;
    }

    public boolean isSetSourceReferenceSystem() {
        return (this.sourceReferenceSystem!= null);
    }

    /**
     * Recupera il valore della proprietà sourceCitation.
     * 
     * @return
     *     possible object is
     *     {@link CICitationPropertyType }
     *     
     */
    public CICitationPropertyType getSourceCitation() {
        return sourceCitation;
    }

    /**
     * Imposta il valore della proprietà sourceCitation.
     * 
     * @param value
     *     allowed object is
     *     {@link CICitationPropertyType }
     *     
     */
    public void setSourceCitation(CICitationPropertyType value) {
        this.sourceCitation = value;
    }

    public boolean isSetSourceCitation() {
        return (this.sourceCitation!= null);
    }

    /**
     * Gets the value of the sourceExtent property.
     * 
     * <p>This accessor method returns a reference to the live list,
     * not a snapshot. Therefore, any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a {@code set} method for the sourceExtent property.</p>
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * </p>
     * <pre>
     * getSourceExtent().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link EXExtentPropertyType }
     * </p>
     * 
     * 
     * @return
     *     The value of the sourceExtent property.
     */
    public List<EXExtentPropertyType> getSourceExtent() {
        if (sourceExtent == null) {
            sourceExtent = new ArrayList<>();
        }
        return this.sourceExtent;
    }

    public boolean isSetSourceExtent() {
        return ((this.sourceExtent!= null)&&(!this.sourceExtent.isEmpty()));
    }

    public void unsetSourceExtent() {
        this.sourceExtent = null;
    }

    /**
     * Gets the value of the sourceStep property.
     * 
     * <p>This accessor method returns a reference to the live list,
     * not a snapshot. Therefore, any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a {@code set} method for the sourceStep property.</p>
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * </p>
     * <pre>
     * getSourceStep().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link LIProcessStepPropertyType }
     * </p>
     * 
     * 
     * @return
     *     The value of the sourceStep property.
     */
    public List<LIProcessStepPropertyType> getSourceStep() {
        if (sourceStep == null) {
            sourceStep = new ArrayList<>();
        }
        return this.sourceStep;
    }

    public boolean isSetSourceStep() {
        return ((this.sourceStep!= null)&&(!this.sourceStep.isEmpty()));
    }

    public void unsetSourceStep() {
        this.sourceStep = null;
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
            CharacterStringPropertyType theDescription;
            theDescription = this.getDescription();
            strategy.appendField(locator, this, "description", buffer, theDescription, this.isSetDescription());
        }
        {
            MDRepresentativeFractionPropertyType theScaleDenominator;
            theScaleDenominator = this.getScaleDenominator();
            strategy.appendField(locator, this, "scaleDenominator", buffer, theScaleDenominator, this.isSetScaleDenominator());
        }
        {
            MDReferenceSystemPropertyType theSourceReferenceSystem;
            theSourceReferenceSystem = this.getSourceReferenceSystem();
            strategy.appendField(locator, this, "sourceReferenceSystem", buffer, theSourceReferenceSystem, this.isSetSourceReferenceSystem());
        }
        {
            CICitationPropertyType theSourceCitation;
            theSourceCitation = this.getSourceCitation();
            strategy.appendField(locator, this, "sourceCitation", buffer, theSourceCitation, this.isSetSourceCitation());
        }
        {
            List<EXExtentPropertyType> theSourceExtent;
            theSourceExtent = (this.isSetSourceExtent()?this.getSourceExtent():null);
            strategy.appendField(locator, this, "sourceExtent", buffer, theSourceExtent, this.isSetSourceExtent());
        }
        {
            List<LIProcessStepPropertyType> theSourceStep;
            theSourceStep = (this.isSetSourceStep()?this.getSourceStep():null);
            strategy.appendField(locator, this, "sourceStep", buffer, theSourceStep, this.isSetSourceStep());
        }
        return buffer;
    }

    public void setSourceExtent(List<EXExtentPropertyType> value) {
        this.sourceExtent = null;
        if (value!= null) {
            List<EXExtentPropertyType> draftl = this.getSourceExtent();
            draftl.addAll(value);
        }
    }

    public void setSourceStep(List<LIProcessStepPropertyType> value) {
        this.sourceStep = null;
        if (value!= null) {
            List<LIProcessStepPropertyType> draftl = this.getSourceStep();
            draftl.addAll(value);
        }
    }

}
