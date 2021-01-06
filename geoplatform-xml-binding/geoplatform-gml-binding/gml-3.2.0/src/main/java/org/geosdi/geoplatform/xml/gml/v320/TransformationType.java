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
// Questo file è stato generato dall'architettura JavaTM per XML Binding (JAXB) Reference Implementation, v2.2.7 
// Vedere <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Qualsiasi modifica a questo file andrà persa durante la ricompilazione dello schema di origine. 
// Generato il: 2015.06.18 alle 12:52:42 AM CEST 
//


package org.geosdi.geoplatform.xml.gml.v320;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;
import org.jvnet.jaxb2_commons.lang.CopyStrategy;
import org.jvnet.jaxb2_commons.lang.CopyTo;
import org.jvnet.jaxb2_commons.lang.Equals;
import org.jvnet.jaxb2_commons.lang.EqualsStrategy;
import org.jvnet.jaxb2_commons.lang.HashCode;
import org.jvnet.jaxb2_commons.lang.HashCodeStrategy;
import org.jvnet.jaxb2_commons.lang.JAXBCopyStrategy;
import org.jvnet.jaxb2_commons.lang.JAXBEqualsStrategy;
import org.jvnet.jaxb2_commons.lang.JAXBHashCodeStrategy;
import org.jvnet.jaxb2_commons.lang.JAXBMergeStrategy;
import org.jvnet.jaxb2_commons.lang.JAXBToStringStrategy;
import org.jvnet.jaxb2_commons.lang.MergeFrom;
import org.jvnet.jaxb2_commons.lang.MergeStrategy;
import org.jvnet.jaxb2_commons.lang.ToString;
import org.jvnet.jaxb2_commons.lang.ToStringStrategy;
import org.jvnet.jaxb2_commons.locator.ObjectLocator;
import org.jvnet.jaxb2_commons.locator.util.LocatorUtils;


/**
 * <p>Classe Java per TransformationType complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="TransformationType">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.opengis.net/gml}AbstractGeneralTransformationType">
 *       &lt;sequence>
 *         &lt;element ref="{http://www.opengis.net/gml}method"/>
 *         &lt;element ref="{http://www.opengis.net/gml}parameterValue" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TransformationType", propOrder = {
    "method",
    "parameterValue"
})
public class TransformationType
    extends AbstractGeneralTransformationType
    implements Cloneable, CopyTo, Equals, HashCode, MergeFrom, ToString
{

    @XmlElementRef(name = "method", namespace = "http://www.opengis.net/gml", type = JAXBElement.class)
    protected JAXBElement<OperationMethodPropertyType> method;
    @XmlElementRef(name = "parameterValue", namespace = "http://www.opengis.net/gml", type = JAXBElement.class, required = false)
    protected List<JAXBElement<AbstractGeneralParameterValuePropertyType>> parameterValue;

    /**
     * Recupera il valore della proprietà method.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link OperationMethodPropertyType }{@code >}
     *     {@link JAXBElement }{@code <}{@link OperationMethodPropertyType }{@code >}
     *     
     */
    public JAXBElement<OperationMethodPropertyType> getMethod() {
        return method;
    }

    /**
     * Imposta il valore della proprietà method.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link OperationMethodPropertyType }{@code >}
     *     {@link JAXBElement }{@code <}{@link OperationMethodPropertyType }{@code >}
     *     
     */
    public void setMethod(JAXBElement<OperationMethodPropertyType> value) {
        this.method = value;
    }

    public boolean isSetMethod() {
        return (this.method!= null);
    }

    /**
     * Gets the value of the parameterValue property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the parameterValue property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getParameterValue().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link JAXBElement }{@code <}{@link AbstractGeneralParameterValuePropertyType }{@code >}
     * {@link JAXBElement }{@code <}{@link AbstractGeneralParameterValuePropertyType }{@code >}
     * {@link JAXBElement }{@code <}{@link AbstractGeneralParameterValuePropertyType }{@code >}
     * 
     * 
     */
    public List<JAXBElement<AbstractGeneralParameterValuePropertyType>> getParameterValue() {
        if (parameterValue == null) {
            parameterValue = new ArrayList<JAXBElement<AbstractGeneralParameterValuePropertyType>>();
        }
        return this.parameterValue;
    }

    public boolean isSetParameterValue() {
        return ((this.parameterValue!= null)&&(!this.parameterValue.isEmpty()));
    }

    public void unsetParameterValue() {
        this.parameterValue = null;
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
            JAXBElement<OperationMethodPropertyType> theMethod;
            theMethod = this.getMethod();
            strategy.appendField(locator, this, "method", buffer, theMethod);
        }
        {
            List<JAXBElement<AbstractGeneralParameterValuePropertyType>> theParameterValue;
            theParameterValue = this.getParameterValue();
            strategy.appendField(locator, this, "parameterValue", buffer, theParameterValue);
        }
        return buffer;
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof TransformationType)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        if (!super.equals(thisLocator, thatLocator, object, strategy)) {
            return false;
        }
        final TransformationType that = ((TransformationType) object);
        {
            JAXBElement<OperationMethodPropertyType> lhsMethod;
            lhsMethod = this.getMethod();
            JAXBElement<OperationMethodPropertyType> rhsMethod;
            rhsMethod = that.getMethod();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "method", lhsMethod), LocatorUtils.property(thatLocator, "method", rhsMethod), lhsMethod, rhsMethod)) {
                return false;
            }
        }
        {
            List<JAXBElement<AbstractGeneralParameterValuePropertyType>> lhsParameterValue;
            lhsParameterValue = this.getParameterValue();
            List<JAXBElement<AbstractGeneralParameterValuePropertyType>> rhsParameterValue;
            rhsParameterValue = that.getParameterValue();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "parameterValue", lhsParameterValue), LocatorUtils.property(thatLocator, "parameterValue", rhsParameterValue), lhsParameterValue, rhsParameterValue)) {
                return false;
            }
        }
        return true;
    }

    public boolean equals(Object object) {
        final EqualsStrategy strategy = JAXBEqualsStrategy.INSTANCE;
        return equals(null, null, object, strategy);
    }

    public int hashCode(ObjectLocator locator, HashCodeStrategy strategy) {
        int currentHashCode = super.hashCode(locator, strategy);
        {
            JAXBElement<OperationMethodPropertyType> theMethod;
            theMethod = this.getMethod();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "method", theMethod), currentHashCode, theMethod);
        }
        {
            List<JAXBElement<AbstractGeneralParameterValuePropertyType>> theParameterValue;
            theParameterValue = this.getParameterValue();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "parameterValue", theParameterValue), currentHashCode, theParameterValue);
        }
        return currentHashCode;
    }

    public int hashCode() {
        final HashCodeStrategy strategy = JAXBHashCodeStrategy.INSTANCE;
        return this.hashCode(null, strategy);
    }

    public Object clone() {
        return copyTo(createNewInstance());
    }

    public Object copyTo(Object target) {
        final CopyStrategy strategy = JAXBCopyStrategy.INSTANCE;
        return copyTo(null, target, strategy);
    }

    public Object copyTo(ObjectLocator locator, Object target, CopyStrategy strategy) {
        final Object draftCopy = ((target == null)?createNewInstance():target);
        super.copyTo(locator, draftCopy, strategy);
        if (draftCopy instanceof TransformationType) {
            final TransformationType copy = ((TransformationType) draftCopy);
            if (this.isSetMethod()) {
                JAXBElement<OperationMethodPropertyType> sourceMethod;
                sourceMethod = this.getMethod();
                @SuppressWarnings("unchecked")
                JAXBElement<OperationMethodPropertyType> copyMethod = ((JAXBElement<OperationMethodPropertyType> ) strategy.copy(LocatorUtils.property(locator, "method", sourceMethod), sourceMethod));
                copy.setMethod(copyMethod);
            } else {
                copy.method = null;
            }
            if (this.isSetParameterValue()) {
                List<JAXBElement<AbstractGeneralParameterValuePropertyType>> sourceParameterValue;
                sourceParameterValue = this.getParameterValue();
                @SuppressWarnings("unchecked")
                List<JAXBElement<AbstractGeneralParameterValuePropertyType>> copyParameterValue = ((List<JAXBElement<AbstractGeneralParameterValuePropertyType>> ) strategy.copy(LocatorUtils.property(locator, "parameterValue", sourceParameterValue), sourceParameterValue));
                copy.unsetParameterValue();
                List<JAXBElement<AbstractGeneralParameterValuePropertyType>> uniqueParameterValuel = copy.getParameterValue();
                uniqueParameterValuel.addAll(copyParameterValue);
            } else {
                copy.unsetParameterValue();
            }
        }
        return draftCopy;
    }

    public Object createNewInstance() {
        return new TransformationType();
    }

    public void mergeFrom(Object left, Object right) {
        final MergeStrategy strategy = JAXBMergeStrategy.INSTANCE;
        mergeFrom(null, null, left, right, strategy);
    }

    public void mergeFrom(ObjectLocator leftLocator, ObjectLocator rightLocator, Object left, Object right, MergeStrategy strategy) {
        super.mergeFrom(leftLocator, rightLocator, left, right, strategy);
        if (right instanceof TransformationType) {
            final TransformationType target = this;
            final TransformationType leftObject = ((TransformationType) left);
            final TransformationType rightObject = ((TransformationType) right);
            {
                JAXBElement<OperationMethodPropertyType> lhsMethod;
                lhsMethod = leftObject.getMethod();
                JAXBElement<OperationMethodPropertyType> rhsMethod;
                rhsMethod = rightObject.getMethod();
                target.setMethod(((JAXBElement<OperationMethodPropertyType> ) strategy.merge(LocatorUtils.property(leftLocator, "method", lhsMethod), LocatorUtils.property(rightLocator, "method", rhsMethod), lhsMethod, rhsMethod)));
            }
            {
                List<JAXBElement<AbstractGeneralParameterValuePropertyType>> lhsParameterValue;
                lhsParameterValue = leftObject.getParameterValue();
                List<JAXBElement<AbstractGeneralParameterValuePropertyType>> rhsParameterValue;
                rhsParameterValue = rightObject.getParameterValue();
                target.unsetParameterValue();
                List<JAXBElement<AbstractGeneralParameterValuePropertyType>> uniqueParameterValuel = target.getParameterValue();
                uniqueParameterValuel.addAll(((List<JAXBElement<AbstractGeneralParameterValuePropertyType>> ) strategy.merge(LocatorUtils.property(leftLocator, "parameterValue", lhsParameterValue), LocatorUtils.property(rightLocator, "parameterValue", rhsParameterValue), lhsParameterValue, rhsParameterValue)));
            }
        }
    }

    public void setParameterValue(List<JAXBElement<AbstractGeneralParameterValuePropertyType>> value) {
        List<JAXBElement<AbstractGeneralParameterValuePropertyType>> draftl = this.getParameterValue();
        draftl.addAll(value);
    }

    public TransformationType withMethod(JAXBElement<OperationMethodPropertyType> value) {
        setMethod(value);
        return this;
    }

    public TransformationType withParameterValue(JAXBElement<AbstractGeneralParameterValuePropertyType> ... values) {
        if (values!= null) {
            for (JAXBElement<AbstractGeneralParameterValuePropertyType> value: values) {
                getParameterValue().add(value);
            }
        }
        return this;
    }

    public TransformationType withParameterValue(Collection<JAXBElement<AbstractGeneralParameterValuePropertyType>> values) {
        if (values!= null) {
            getParameterValue().addAll(values);
        }
        return this;
    }

    public TransformationType withParameterValue(List<JAXBElement<AbstractGeneralParameterValuePropertyType>> value) {
        setParameterValue(value);
        return this;
    }

    @Override
    public TransformationType withDomainOfValidity(DomainOfValidityElement value) {
        setDomainOfValidity(value);
        return this;
    }

    @Override
    public TransformationType withScope(String... values) {
        if (values!= null) {
            for (String value: values) {
                getScope().add(value);
            }
        }
        return this;
    }

    @Override
    public TransformationType withScope(Collection<String> values) {
        if (values!= null) {
            getScope().addAll(values);
        }
        return this;
    }

    @Override
    public TransformationType withOperationVersion(String value) {
        setOperationVersion(value);
        return this;
    }

    @Override
    public TransformationType withCoordinateOperationAccuracy(CoordinateOperationAccuracyElement... values) {
        if (values!= null) {
            for (CoordinateOperationAccuracyElement value: values) {
                getCoordinateOperationAccuracy().add(value);
            }
        }
        return this;
    }

    @Override
    public TransformationType withCoordinateOperationAccuracy(Collection<CoordinateOperationAccuracyElement> values) {
        if (values!= null) {
            getCoordinateOperationAccuracy().addAll(values);
        }
        return this;
    }

    @Override
    public TransformationType withSourceCRS(CRSPropertyType value) {
        setSourceCRS(value);
        return this;
    }

    @Override
    public TransformationType withTargetCRS(CRSPropertyType value) {
        setTargetCRS(value);
        return this;
    }

    @Override
    public TransformationType withScope(List<String> value) {
        setScope(value);
        return this;
    }

    @Override
    public TransformationType withCoordinateOperationAccuracy(List<CoordinateOperationAccuracyElement> value) {
        setCoordinateOperationAccuracy(value);
        return this;
    }

    @Override
    public TransformationType withRemarks(String value) {
        setRemarks(value);
        return this;
    }

    @Override
    public TransformationType withMetaDataProperty(MetaDataPropertyType... values) {
        if (values!= null) {
            for (MetaDataPropertyType value: values) {
                getMetaDataProperty().add(value);
            }
        }
        return this;
    }

    @Override
    public TransformationType withMetaDataProperty(Collection<MetaDataPropertyType> values) {
        if (values!= null) {
            getMetaDataProperty().addAll(values);
        }
        return this;
    }

    @Override
    public TransformationType withDescription(StringOrRefType value) {
        setDescription(value);
        return this;
    }

    @Override
    public TransformationType withDescriptionReference(ReferenceType value) {
        setDescriptionReference(value);
        return this;
    }

    @Override
    public TransformationType withIdentifier(CodeWithAuthorityType value) {
        setIdentifier(value);
        return this;
    }

    @Override
    public TransformationType withName(CodeType... values) {
        if (values!= null) {
            for (CodeType value: values) {
                getName().add(value);
            }
        }
        return this;
    }

    @Override
    public TransformationType withName(Collection<CodeType> values) {
        if (values!= null) {
            getName().addAll(values);
        }
        return this;
    }

    @Override
    public TransformationType withId(String value) {
        setId(value);
        return this;
    }

    @Override
    public TransformationType withMetaDataProperty(List<MetaDataPropertyType> value) {
        setMetaDataProperty(value);
        return this;
    }

    @Override
    public TransformationType withName(List<CodeType> value) {
        setName(value);
        return this;
    }

}
