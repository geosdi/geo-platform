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
// Generato il: 2026.11.02 alle 03:38:19 PM CET
//

package org.geosdi.geoplatform.xml.gml.v320;

import java.util.ArrayList;
import java.util.List;
import jakarta.xml.bind.JAXBElement;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElementRef;
import jakarta.xml.bind.annotation.XmlType;
import org.jvnet.jaxb.lang.CopyStrategy;
import org.jvnet.jaxb.lang.CopyTo;
import org.jvnet.jaxb.lang.Equals;
import org.jvnet.jaxb.lang.EqualsStrategy;
import org.jvnet.jaxb.lang.HashCode;
import org.jvnet.jaxb.lang.HashCodeStrategy;
import org.jvnet.jaxb.lang.JAXBCopyStrategy;
import org.jvnet.jaxb.lang.JAXBEqualsStrategy;
import org.jvnet.jaxb.lang.JAXBHashCodeStrategy;
import org.jvnet.jaxb.lang.JAXBMergeStrategy;
import org.jvnet.jaxb.lang.JAXBToStringStrategy;
import org.jvnet.jaxb.lang.MergeFrom;
import org.jvnet.jaxb.lang.MergeStrategy;
import org.jvnet.jaxb.lang.ToString;
import org.jvnet.jaxb.lang.ToStringStrategy;
import org.jvnet.jaxb.locator.ObjectLocator;
import org.jvnet.jaxb.locator.util.LocatorUtils;


/**
 * <p>Classe Java per ConversionType complex type.</p>
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.</p>
 * 
 * <pre>{@code
 * <complexType name="ConversionType">
 *   <complexContent>
 *     <extension base="{http://www.opengis.net/gml}AbstractGeneralConversionType">
 *       <sequence>
 *         <element ref="{http://www.opengis.net/gml}method"/>
 *         <element ref="{http://www.opengis.net/gml}parameterValue" maxOccurs="unbounded" minOccurs="0"/>
 *       </sequence>
 *     </extension>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ConversionType", propOrder = {
    "method",
    "parameterValue"
})
public class ConversionType
    extends AbstractGeneralConversionType
    implements Cloneable, CopyTo, Equals, HashCode, MergeFrom, ToString
{

    /**
     * gml:method is an association role to the operation method used by a coordinate operation.
     * 
     */
    @XmlElementRef(name = "method", namespace = "http://www.opengis.net/gml", type = JAXBElement.class)
    protected JAXBElement<OperationMethodPropertyType> method;
    /**
     * gml:parameterValue is a composition association to a parameter value or group of parameter values used by a coordinate operation.
     * 
     */
    @XmlElementRef(name = "parameterValue", namespace = "http://www.opengis.net/gml", type = JAXBElement.class, required = false)
    protected List<JAXBElement<AbstractGeneralParameterValuePropertyType>> parameterValue;

    /**
     * gml:method is an association role to the operation method used by a coordinate operation.
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
     * @see #getMethod()
     */
    public void setMethod(JAXBElement<OperationMethodPropertyType> value) {
        this.method = value;
    }

    public boolean isSetMethod() {
        return (this.method!= null);
    }

    /**
     * gml:parameterValue is a composition association to a parameter value or group of parameter values used by a coordinate operation.
     * 
     * Gets the value of the parameterValue property.
     * 
     * <p>This accessor method returns a reference to the live list,
     * not a snapshot. Therefore, any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a {@code set} method for the parameterValue property.</p>
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * </p>
     * <pre>
     * getParameterValue().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link JAXBElement }{@code <}{@link AbstractGeneralParameterValuePropertyType }{@code >}
     * {@link JAXBElement }{@code <}{@link AbstractGeneralParameterValuePropertyType }{@code >}
     * {@link JAXBElement }{@code <}{@link AbstractGeneralParameterValuePropertyType }{@code >}
     * </p>
     * 
     * 
     * @return
     *     The value of the parameterValue property.
     */
    public List<JAXBElement<AbstractGeneralParameterValuePropertyType>> getParameterValue() {
        if (parameterValue == null) {
            parameterValue = new ArrayList<>();
        }
        return this.parameterValue;
    }

    public boolean isSetParameterValue() {
        return ((this.parameterValue!= null)&&(!this.parameterValue.isEmpty()));
    }

    public void unsetParameterValue() {
        this.parameterValue = null;
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
            JAXBElement<OperationMethodPropertyType> theMethod;
            theMethod = this.getMethod();
            strategy.appendField(locator, this, "method", buffer, theMethod, this.isSetMethod());
        }
        {
            List<JAXBElement<AbstractGeneralParameterValuePropertyType>> theParameterValue;
            theParameterValue = (this.isSetParameterValue()?this.getParameterValue():null);
            strategy.appendField(locator, this, "parameterValue", buffer, theParameterValue, this.isSetParameterValue());
        }
        return buffer;
    }

    public void setParameterValue(List<JAXBElement<AbstractGeneralParameterValuePropertyType>> value) {
        this.parameterValue = null;
        if (value!= null) {
            List<JAXBElement<AbstractGeneralParameterValuePropertyType>> draftl = this.getParameterValue();
            draftl.addAll(value);
        }
    }

    @Override
    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if ((object == null)||(this.getClass()!= object.getClass())) {
            return false;
        }
        if (this == object) {
            return true;
        }
        if (!super.equals(thisLocator, thatLocator, object, strategy)) {
            return false;
        }
        final ConversionType that = ((ConversionType) object);
        {
            JAXBElement<OperationMethodPropertyType> lhsMethod;
            lhsMethod = this.getMethod();
            JAXBElement<OperationMethodPropertyType> rhsMethod;
            rhsMethod = that.getMethod();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "method", lhsMethod), LocatorUtils.property(thatLocator, "method", rhsMethod), lhsMethod, rhsMethod, this.isSetMethod(), that.isSetMethod())) {
                return false;
            }
        }
        {
            List<JAXBElement<AbstractGeneralParameterValuePropertyType>> lhsParameterValue;
            lhsParameterValue = (this.isSetParameterValue()?this.getParameterValue():null);
            List<JAXBElement<AbstractGeneralParameterValuePropertyType>> rhsParameterValue;
            rhsParameterValue = (that.isSetParameterValue()?that.getParameterValue():null);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "parameterValue", lhsParameterValue), LocatorUtils.property(thatLocator, "parameterValue", rhsParameterValue), lhsParameterValue, rhsParameterValue, this.isSetParameterValue(), that.isSetParameterValue())) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean equals(Object object) {
        final EqualsStrategy strategy = JAXBEqualsStrategy.getInstance();
        return equals(null, null, object, strategy);
    }

    @Override
    public int hashCode(ObjectLocator locator, HashCodeStrategy strategy) {
        int currentHashCode = super.hashCode(locator, strategy);
        {
            JAXBElement<OperationMethodPropertyType> theMethod;
            theMethod = this.getMethod();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "method", theMethod), currentHashCode, theMethod, this.isSetMethod());
        }
        {
            List<JAXBElement<AbstractGeneralParameterValuePropertyType>> theParameterValue;
            theParameterValue = (this.isSetParameterValue()?this.getParameterValue():null);
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "parameterValue", theParameterValue), currentHashCode, theParameterValue, this.isSetParameterValue());
        }
        return currentHashCode;
    }

    @Override
    public int hashCode() {
        final HashCodeStrategy strategy = JAXBHashCodeStrategy.getInstance();
        return this.hashCode(null, strategy);
    }

    @Override
    public Object clone() {
        return copyTo(createNewInstance());
    }

    @Override
    public Object copyTo(Object target) {
        final CopyStrategy strategy = JAXBCopyStrategy.getInstance();
        return copyTo(null, target, strategy);
    }

    @Override
    public Object copyTo(ObjectLocator locator, Object target, CopyStrategy strategy) {
        final Object draftCopy = ((target == null)?createNewInstance():target);
        super.copyTo(locator, draftCopy, strategy);
        if (draftCopy instanceof ConversionType) {
            final ConversionType copy = ((ConversionType) draftCopy);
            {
                java.lang.Boolean methodShouldBeCopiedAndSet = strategy.shouldBeCopiedAndSet(locator, this.isSetMethod());
                if (methodShouldBeCopiedAndSet == java.lang.Boolean.TRUE) {
                    JAXBElement<OperationMethodPropertyType> sourceMethod;
                    sourceMethod = this.getMethod();
                    @SuppressWarnings("unchecked")
                    JAXBElement<OperationMethodPropertyType> copyMethod = ((JAXBElement<OperationMethodPropertyType> ) strategy.copy(LocatorUtils.property(locator, "method", sourceMethod), sourceMethod, this.isSetMethod()));
                    copy.setMethod(copyMethod);
                } else {
                    if (methodShouldBeCopiedAndSet == java.lang.Boolean.FALSE) {
                        copy.method = null;
                    }
                }
            }
            {
                java.lang.Boolean parameterValueShouldBeCopiedAndSet = strategy.shouldBeCopiedAndSet(locator, this.isSetParameterValue());
                if (parameterValueShouldBeCopiedAndSet == java.lang.Boolean.TRUE) {
                    List<JAXBElement<AbstractGeneralParameterValuePropertyType>> sourceParameterValue;
                    sourceParameterValue = (this.isSetParameterValue()?this.getParameterValue():null);
                    @SuppressWarnings("unchecked")
                    List<JAXBElement<AbstractGeneralParameterValuePropertyType>> copyParameterValue = ((List<JAXBElement<AbstractGeneralParameterValuePropertyType>> ) strategy.copy(LocatorUtils.property(locator, "parameterValue", sourceParameterValue), sourceParameterValue, this.isSetParameterValue()));
                    copy.setParameterValue(copyParameterValue);
                } else {
                    if (parameterValueShouldBeCopiedAndSet == java.lang.Boolean.FALSE) {
                        copy.unsetParameterValue();
                    }
                }
            }
        }
        return draftCopy;
    }

    @Override
    public Object createNewInstance() {
        return new ConversionType();
    }

    @Override
    public void mergeFrom(Object left, Object right) {
        final MergeStrategy strategy = JAXBMergeStrategy.getInstance();
        mergeFrom(null, null, left, right, strategy);
    }

    @Override
    public void mergeFrom(ObjectLocator leftLocator, ObjectLocator rightLocator, Object left, Object right, MergeStrategy strategy) {
        super.mergeFrom(leftLocator, rightLocator, left, right, strategy);
        if (right instanceof ConversionType) {
            final ConversionType target = this;
            final ConversionType leftObject = ((ConversionType) left);
            final ConversionType rightObject = ((ConversionType) right);
            {
                java.lang.Boolean methodShouldBeMergedAndSet = strategy.shouldBeMergedAndSet(leftLocator, rightLocator, leftObject.isSetMethod(), rightObject.isSetMethod());
                if (methodShouldBeMergedAndSet == java.lang.Boolean.TRUE) {
                    JAXBElement<OperationMethodPropertyType> lhsMethod;
                    lhsMethod = leftObject.getMethod();
                    JAXBElement<OperationMethodPropertyType> rhsMethod;
                    rhsMethod = rightObject.getMethod();
                    JAXBElement<OperationMethodPropertyType> mergedMethod = ((JAXBElement<OperationMethodPropertyType> ) strategy.merge(LocatorUtils.property(leftLocator, "method", lhsMethod), LocatorUtils.property(rightLocator, "method", rhsMethod), lhsMethod, rhsMethod, leftObject.isSetMethod(), rightObject.isSetMethod()));
                    target.setMethod(mergedMethod);
                } else {
                    if (methodShouldBeMergedAndSet == java.lang.Boolean.FALSE) {
                        target.method = null;
                    }
                }
            }
            {
                java.lang.Boolean parameterValueShouldBeMergedAndSet = strategy.shouldBeMergedAndSet(leftLocator, rightLocator, leftObject.isSetParameterValue(), rightObject.isSetParameterValue());
                if (parameterValueShouldBeMergedAndSet == java.lang.Boolean.TRUE) {
                    List<JAXBElement<AbstractGeneralParameterValuePropertyType>> lhsParameterValue;
                    lhsParameterValue = (leftObject.isSetParameterValue()?leftObject.getParameterValue():null);
                    List<JAXBElement<AbstractGeneralParameterValuePropertyType>> rhsParameterValue;
                    rhsParameterValue = (rightObject.isSetParameterValue()?rightObject.getParameterValue():null);
                    List<JAXBElement<AbstractGeneralParameterValuePropertyType>> mergedParameterValue = ((List<JAXBElement<AbstractGeneralParameterValuePropertyType>> ) strategy.merge(LocatorUtils.property(leftLocator, "parameterValue", lhsParameterValue), LocatorUtils.property(rightLocator, "parameterValue", rhsParameterValue), lhsParameterValue, rhsParameterValue, leftObject.isSetParameterValue(), rightObject.isSetParameterValue()));
                    target.setParameterValue(mergedParameterValue);
                } else {
                    if (parameterValueShouldBeMergedAndSet == java.lang.Boolean.FALSE) {
                        target.unsetParameterValue();
                    }
                }
            }
        }
    }

}
