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
import org.geosdi.geoplatform.xml.iso19139.v20070417.gco.CharacterStringPropertyType;
import org.geosdi.geoplatform.xml.iso19139.v20070417.gco.DateTimePropertyType;
import org.jvnet.jaxb.lang.JAXBToStringStrategy;
import org.jvnet.jaxb.lang.ToString;
import org.jvnet.jaxb.lang.ToStringStrategy;
import org.jvnet.jaxb.locator.ObjectLocator;


/**
 * <p>Classe Java per AbstractDQ_Element_Type complex type.</p>
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.</p>
 * 
 * <pre>{@code
 * <complexType name="AbstractDQ_Element_Type">
 *   <complexContent>
 *     <extension base="{http://www.isotc211.org/2005/gco}AbstractObject_Type">
 *       <sequence>
 *         <element name="nameOfMeasure" type="{http://www.isotc211.org/2005/gco}CharacterString_PropertyType" maxOccurs="unbounded" minOccurs="0"/>
 *         <element name="measureIdentification" type="{http://www.isotc211.org/2005/gmd}MD_Identifier_PropertyType" minOccurs="0"/>
 *         <element name="measureDescription" type="{http://www.isotc211.org/2005/gco}CharacterString_PropertyType" minOccurs="0"/>
 *         <element name="evaluationMethodType" type="{http://www.isotc211.org/2005/gmd}DQ_EvaluationMethodTypeCode_PropertyType" minOccurs="0"/>
 *         <element name="evaluationMethodDescription" type="{http://www.isotc211.org/2005/gco}CharacterString_PropertyType" minOccurs="0"/>
 *         <element name="evaluationProcedure" type="{http://www.isotc211.org/2005/gmd}CI_Citation_PropertyType" minOccurs="0"/>
 *         <element name="dateTime" type="{http://www.isotc211.org/2005/gco}DateTime_PropertyType" maxOccurs="unbounded" minOccurs="0"/>
 *         <element name="result" type="{http://www.isotc211.org/2005/gmd}DQ_Result_PropertyType" maxOccurs="2"/>
 *       </sequence>
 *     </extension>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AbstractDQ_Element_Type", propOrder = {
    "nameOfMeasure",
    "measureIdentification",
    "measureDescription",
    "evaluationMethodType",
    "evaluationMethodDescription",
    "evaluationProcedure",
    "dateTime",
    "result"
})
@XmlSeeAlso({
    AbstractDQTemporalAccuracyType.class,
    AbstractDQThematicAccuracyType.class,
    AbstractDQPositionalAccuracyType.class,
    AbstractDQLogicalConsistencyType.class,
    AbstractDQCompletenessType.class
})
public abstract class AbstractDQElementType
    extends AbstractObjectType
    implements ToString
{

    protected List<CharacterStringPropertyType> nameOfMeasure;
    protected MDIdentifierPropertyType measureIdentification;
    protected CharacterStringPropertyType measureDescription;
    protected DQEvaluationMethodTypeCodePropertyType evaluationMethodType;
    protected CharacterStringPropertyType evaluationMethodDescription;
    protected CICitationPropertyType evaluationProcedure;
    protected List<DateTimePropertyType> dateTime;
    @XmlElement(required = true)
    protected List<DQResultPropertyType> result;

    /**
     * Gets the value of the nameOfMeasure property.
     * 
     * <p>This accessor method returns a reference to the live list,
     * not a snapshot. Therefore, any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a {@code set} method for the nameOfMeasure property.</p>
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * </p>
     * <pre>
     * getNameOfMeasure().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CharacterStringPropertyType }
     * </p>
     * 
     * 
     * @return
     *     The value of the nameOfMeasure property.
     */
    public List<CharacterStringPropertyType> getNameOfMeasure() {
        if (nameOfMeasure == null) {
            nameOfMeasure = new ArrayList<>();
        }
        return this.nameOfMeasure;
    }

    public boolean isSetNameOfMeasure() {
        return ((this.nameOfMeasure!= null)&&(!this.nameOfMeasure.isEmpty()));
    }

    public void unsetNameOfMeasure() {
        this.nameOfMeasure = null;
    }

    /**
     * Recupera il valore della proprietà measureIdentification.
     * 
     * @return
     *     possible object is
     *     {@link MDIdentifierPropertyType }
     *     
     */
    public MDIdentifierPropertyType getMeasureIdentification() {
        return measureIdentification;
    }

    /**
     * Imposta il valore della proprietà measureIdentification.
     * 
     * @param value
     *     allowed object is
     *     {@link MDIdentifierPropertyType }
     *     
     */
    public void setMeasureIdentification(MDIdentifierPropertyType value) {
        this.measureIdentification = value;
    }

    public boolean isSetMeasureIdentification() {
        return (this.measureIdentification!= null);
    }

    /**
     * Recupera il valore della proprietà measureDescription.
     * 
     * @return
     *     possible object is
     *     {@link CharacterStringPropertyType }
     *     
     */
    public CharacterStringPropertyType getMeasureDescription() {
        return measureDescription;
    }

    /**
     * Imposta il valore della proprietà measureDescription.
     * 
     * @param value
     *     allowed object is
     *     {@link CharacterStringPropertyType }
     *     
     */
    public void setMeasureDescription(CharacterStringPropertyType value) {
        this.measureDescription = value;
    }

    public boolean isSetMeasureDescription() {
        return (this.measureDescription!= null);
    }

    /**
     * Recupera il valore della proprietà evaluationMethodType.
     * 
     * @return
     *     possible object is
     *     {@link DQEvaluationMethodTypeCodePropertyType }
     *     
     */
    public DQEvaluationMethodTypeCodePropertyType getEvaluationMethodType() {
        return evaluationMethodType;
    }

    /**
     * Imposta il valore della proprietà evaluationMethodType.
     * 
     * @param value
     *     allowed object is
     *     {@link DQEvaluationMethodTypeCodePropertyType }
     *     
     */
    public void setEvaluationMethodType(DQEvaluationMethodTypeCodePropertyType value) {
        this.evaluationMethodType = value;
    }

    public boolean isSetEvaluationMethodType() {
        return (this.evaluationMethodType!= null);
    }

    /**
     * Recupera il valore della proprietà evaluationMethodDescription.
     * 
     * @return
     *     possible object is
     *     {@link CharacterStringPropertyType }
     *     
     */
    public CharacterStringPropertyType getEvaluationMethodDescription() {
        return evaluationMethodDescription;
    }

    /**
     * Imposta il valore della proprietà evaluationMethodDescription.
     * 
     * @param value
     *     allowed object is
     *     {@link CharacterStringPropertyType }
     *     
     */
    public void setEvaluationMethodDescription(CharacterStringPropertyType value) {
        this.evaluationMethodDescription = value;
    }

    public boolean isSetEvaluationMethodDescription() {
        return (this.evaluationMethodDescription!= null);
    }

    /**
     * Recupera il valore della proprietà evaluationProcedure.
     * 
     * @return
     *     possible object is
     *     {@link CICitationPropertyType }
     *     
     */
    public CICitationPropertyType getEvaluationProcedure() {
        return evaluationProcedure;
    }

    /**
     * Imposta il valore della proprietà evaluationProcedure.
     * 
     * @param value
     *     allowed object is
     *     {@link CICitationPropertyType }
     *     
     */
    public void setEvaluationProcedure(CICitationPropertyType value) {
        this.evaluationProcedure = value;
    }

    public boolean isSetEvaluationProcedure() {
        return (this.evaluationProcedure!= null);
    }

    /**
     * Gets the value of the dateTime property.
     * 
     * <p>This accessor method returns a reference to the live list,
     * not a snapshot. Therefore, any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a {@code set} method for the dateTime property.</p>
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * </p>
     * <pre>
     * getDateTime().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DateTimePropertyType }
     * </p>
     * 
     * 
     * @return
     *     The value of the dateTime property.
     */
    public List<DateTimePropertyType> getDateTime() {
        if (dateTime == null) {
            dateTime = new ArrayList<>();
        }
        return this.dateTime;
    }

    public boolean isSetDateTime() {
        return ((this.dateTime!= null)&&(!this.dateTime.isEmpty()));
    }

    public void unsetDateTime() {
        this.dateTime = null;
    }

    /**
     * Gets the value of the result property.
     * 
     * <p>This accessor method returns a reference to the live list,
     * not a snapshot. Therefore, any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a {@code set} method for the result property.</p>
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * </p>
     * <pre>
     * getResult().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DQResultPropertyType }
     * </p>
     * 
     * 
     * @return
     *     The value of the result property.
     */
    public List<DQResultPropertyType> getResult() {
        if (result == null) {
            result = new ArrayList<>();
        }
        return this.result;
    }

    public boolean isSetResult() {
        return ((this.result!= null)&&(!this.result.isEmpty()));
    }

    public void unsetResult() {
        this.result = null;
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
            List<CharacterStringPropertyType> theNameOfMeasure;
            theNameOfMeasure = (this.isSetNameOfMeasure()?this.getNameOfMeasure():null);
            strategy.appendField(locator, this, "nameOfMeasure", buffer, theNameOfMeasure, this.isSetNameOfMeasure());
        }
        {
            MDIdentifierPropertyType theMeasureIdentification;
            theMeasureIdentification = this.getMeasureIdentification();
            strategy.appendField(locator, this, "measureIdentification", buffer, theMeasureIdentification, this.isSetMeasureIdentification());
        }
        {
            CharacterStringPropertyType theMeasureDescription;
            theMeasureDescription = this.getMeasureDescription();
            strategy.appendField(locator, this, "measureDescription", buffer, theMeasureDescription, this.isSetMeasureDescription());
        }
        {
            DQEvaluationMethodTypeCodePropertyType theEvaluationMethodType;
            theEvaluationMethodType = this.getEvaluationMethodType();
            strategy.appendField(locator, this, "evaluationMethodType", buffer, theEvaluationMethodType, this.isSetEvaluationMethodType());
        }
        {
            CharacterStringPropertyType theEvaluationMethodDescription;
            theEvaluationMethodDescription = this.getEvaluationMethodDescription();
            strategy.appendField(locator, this, "evaluationMethodDescription", buffer, theEvaluationMethodDescription, this.isSetEvaluationMethodDescription());
        }
        {
            CICitationPropertyType theEvaluationProcedure;
            theEvaluationProcedure = this.getEvaluationProcedure();
            strategy.appendField(locator, this, "evaluationProcedure", buffer, theEvaluationProcedure, this.isSetEvaluationProcedure());
        }
        {
            List<DateTimePropertyType> theDateTime;
            theDateTime = (this.isSetDateTime()?this.getDateTime():null);
            strategy.appendField(locator, this, "dateTime", buffer, theDateTime, this.isSetDateTime());
        }
        {
            List<DQResultPropertyType> theResult;
            theResult = (this.isSetResult()?this.getResult():null);
            strategy.appendField(locator, this, "result", buffer, theResult, this.isSetResult());
        }
        return buffer;
    }

    public void setNameOfMeasure(List<CharacterStringPropertyType> value) {
        this.nameOfMeasure = null;
        if (value!= null) {
            List<CharacterStringPropertyType> draftl = this.getNameOfMeasure();
            draftl.addAll(value);
        }
    }

    public void setDateTime(List<DateTimePropertyType> value) {
        this.dateTime = null;
        if (value!= null) {
            List<DateTimePropertyType> draftl = this.getDateTime();
            draftl.addAll(value);
        }
    }

    public void setResult(List<DQResultPropertyType> value) {
        this.result = null;
        if (value!= null) {
            List<DQResultPropertyType> draftl = this.getResult();
            draftl.addAll(value);
        }
    }

}
