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

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import jakarta.xml.bind.JAXBElement;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElementRef;
import jakarta.xml.bind.annotation.XmlSchemaType;
import jakarta.xml.bind.annotation.XmlType;
import org.jvnet.jaxb.lang.JAXBToStringStrategy;
import org.jvnet.jaxb.lang.ToString;
import org.jvnet.jaxb.lang.ToStringStrategy;
import org.jvnet.jaxb.locator.ObjectLocator;


/**
 * <p>Classe Java per OperationMethodType complex type.</p>
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.</p>
 * 
 * <pre>{@code
 * <complexType name="OperationMethodType">
 *   <complexContent>
 *     <extension base="{http://www.opengis.net/gml/3.2}IdentifiedObjectType">
 *       <sequence>
 *         <choice>
 *           <element ref="{http://www.opengis.net/gml/3.2}formulaCitation"/>
 *           <element ref="{http://www.opengis.net/gml/3.2}formula"/>
 *         </choice>
 *         <element ref="{http://www.opengis.net/gml/3.2}sourceDimensions" minOccurs="0"/>
 *         <element ref="{http://www.opengis.net/gml/3.2}targetDimensions" minOccurs="0"/>
 *         <element ref="{http://www.opengis.net/gml/3.2}parameter" maxOccurs="unbounded" minOccurs="0"/>
 *       </sequence>
 *     </extension>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "OperationMethodType", propOrder = {
    "formulaCitation",
    "formula",
    "sourceDimensions",
    "targetDimensions",
    "parameter"
})
public class OperationMethodType
    extends IdentifiedObjectType
    implements ToString
{

    /**
     * gml:formulaCitation provides a reference to a publication giving the formula(s) or procedure used by an coordinate operation method.
     * 
     */
    protected FormulaCitationElement formulaCitation;
    /**
     * gml:formula Formula(s) or procedure used by an operation method. The use of the codespace attribite has been deprecated. The property value shall be a character string.
     * 
     */
    @XmlElementRef(name = "formula", namespace = "http://www.opengis.net/gml/3.2", type = JAXBElement.class, required = false)
    protected JAXBElement<CodeType> formula;
    /**
     * gml:sourceDimensions is the number of dimensions in the source CRS of this operation method.
     * 
     */
    @XmlSchemaType(name = "positiveInteger")
    protected BigInteger sourceDimensions;
    /**
     * gml:targetDimensions is the number of dimensions in the target CRS of this operation method.
     * 
     */
    @XmlSchemaType(name = "positiveInteger")
    protected BigInteger targetDimensions;
    /**
     * gml:parameter is an association to an operation parameter or parameter group.
     * 
     */
    @XmlElementRef(name = "parameter", namespace = "http://www.opengis.net/gml/3.2", type = JAXBElement.class, required = false)
    protected List<JAXBElement<AbstractGeneralOperationParameterPropertyType>> parameter;

    /**
     * gml:formulaCitation provides a reference to a publication giving the formula(s) or procedure used by an coordinate operation method.
     * 
     * @return
     *     possible object is
     *     {@link FormulaCitationElement }
     *     
     */
    public FormulaCitationElement getFormulaCitation() {
        return formulaCitation;
    }

    /**
     * Imposta il valore della proprietà formulaCitation.
     * 
     * @param value
     *     allowed object is
     *     {@link FormulaCitationElement }
     *     
     * @see #getFormulaCitation()
     */
    public void setFormulaCitation(FormulaCitationElement value) {
        this.formulaCitation = value;
    }

    public boolean isSetFormulaCitation() {
        return (this.formulaCitation!= null);
    }

    /**
     * gml:formula Formula(s) or procedure used by an operation method. The use of the codespace attribite has been deprecated. The property value shall be a character string.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link CodeType }{@code >}
     *     {@link JAXBElement }{@code <}{@link CodeType }{@code >}
     *     
     */
    public JAXBElement<CodeType> getFormula() {
        return formula;
    }

    /**
     * Imposta il valore della proprietà formula.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link CodeType }{@code >}
     *     {@link JAXBElement }{@code <}{@link CodeType }{@code >}
     *     
     * @see #getFormula()
     */
    public void setFormula(JAXBElement<CodeType> value) {
        this.formula = value;
    }

    public boolean isSetFormula() {
        return (this.formula!= null);
    }

    /**
     * gml:sourceDimensions is the number of dimensions in the source CRS of this operation method.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getSourceDimensions() {
        return sourceDimensions;
    }

    /**
     * Imposta il valore della proprietà sourceDimensions.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     * @see #getSourceDimensions()
     */
    public void setSourceDimensions(BigInteger value) {
        this.sourceDimensions = value;
    }

    public boolean isSetSourceDimensions() {
        return (this.sourceDimensions!= null);
    }

    /**
     * gml:targetDimensions is the number of dimensions in the target CRS of this operation method.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getTargetDimensions() {
        return targetDimensions;
    }

    /**
     * Imposta il valore della proprietà targetDimensions.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     * @see #getTargetDimensions()
     */
    public void setTargetDimensions(BigInteger value) {
        this.targetDimensions = value;
    }

    public boolean isSetTargetDimensions() {
        return (this.targetDimensions!= null);
    }

    /**
     * gml:parameter is an association to an operation parameter or parameter group.
     * 
     * Gets the value of the parameter property.
     * 
     * <p>This accessor method returns a reference to the live list,
     * not a snapshot. Therefore, any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a {@code set} method for the parameter property.</p>
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * </p>
     * <pre>
     * getParameter().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link JAXBElement }{@code <}{@link AbstractGeneralOperationParameterPropertyType }{@code >}
     * {@link JAXBElement }{@code <}{@link AbstractGeneralOperationParameterPropertyType }{@code >}
     * {@link JAXBElement }{@code <}{@link AbstractGeneralOperationParameterPropertyType }{@code >}
     * {@link JAXBElement }{@code <}{@link AbstractGeneralOperationParameterPropertyType }{@code >}
     * </p>
     * 
     * 
     * @return
     *     The value of the parameter property.
     */
    public List<JAXBElement<AbstractGeneralOperationParameterPropertyType>> getParameter() {
        if (parameter == null) {
            parameter = new ArrayList<>();
        }
        return this.parameter;
    }

    public boolean isSetParameter() {
        return ((this.parameter!= null)&&(!this.parameter.isEmpty()));
    }

    public void unsetParameter() {
        this.parameter = null;
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
            FormulaCitationElement theFormulaCitation;
            theFormulaCitation = this.getFormulaCitation();
            strategy.appendField(locator, this, "formulaCitation", buffer, theFormulaCitation, this.isSetFormulaCitation());
        }
        {
            JAXBElement<CodeType> theFormula;
            theFormula = this.getFormula();
            strategy.appendField(locator, this, "formula", buffer, theFormula, this.isSetFormula());
        }
        {
            BigInteger theSourceDimensions;
            theSourceDimensions = this.getSourceDimensions();
            strategy.appendField(locator, this, "sourceDimensions", buffer, theSourceDimensions, this.isSetSourceDimensions());
        }
        {
            BigInteger theTargetDimensions;
            theTargetDimensions = this.getTargetDimensions();
            strategy.appendField(locator, this, "targetDimensions", buffer, theTargetDimensions, this.isSetTargetDimensions());
        }
        {
            List<JAXBElement<AbstractGeneralOperationParameterPropertyType>> theParameter;
            theParameter = (this.isSetParameter()?this.getParameter():null);
            strategy.appendField(locator, this, "parameter", buffer, theParameter, this.isSetParameter());
        }
        return buffer;
    }

    public void setParameter(List<JAXBElement<AbstractGeneralOperationParameterPropertyType>> value) {
        this.parameter = null;
        if (value!= null) {
            List<JAXBElement<AbstractGeneralOperationParameterPropertyType>> draftl = this.getParameter();
            draftl.addAll(value);
        }
    }

}
