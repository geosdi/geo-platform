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
 * gml:AbstractGeneralParameterValuePropertyType is a  property type for inline association roles to a parameter value or group of parameter values, always containing the values.
 * 
 * <p>Classe Java per AbstractGeneralParameterValuePropertyType complex type.</p>
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.</p>
 * 
 * <pre>{@code
 * <complexType name="AbstractGeneralParameterValuePropertyType">
 *   <complexContent>
 *     <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       <sequence>
 *         <element ref="{http://www.opengis.net/gml}AbstractGeneralParameterValue"/>
 *       </sequence>
 *     </restriction>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AbstractGeneralParameterValuePropertyType", propOrder = {
    "abstractGeneralParameterValue"
})
public class AbstractGeneralParameterValuePropertyType implements Cloneable, CopyTo, Equals, HashCode, MergeFrom, ToString
{

    /**
     * gml:AbstractGeneralParameterValue is an abstract parameter value or group of parameter values.
     * This abstract complexType is expected to be extended and restricted for well-known operation methods with many instances, in Application Schemas that define operation-method-specialized element names and contents. Specific parameter value elements are directly contained in concrete subtypes, not in this abstract type. All concrete types derived from this type shall extend this type to include one "...Value" element with an appropriate type, which should be one of the element types allowed in the ParameterValueType. In addition, all derived concrete types shall extend this type to include a "operationParameter" property element that references one element substitutable for the "OperationParameter" object element.
     * 
     */
    @XmlElementRef(name = "AbstractGeneralParameterValue", namespace = "http://www.opengis.net/gml", type = JAXBElement.class)
    protected JAXBElement<? extends AbstractGeneralParameterValueType> abstractGeneralParameterValue;

    /**
     * gml:AbstractGeneralParameterValue is an abstract parameter value or group of parameter values.
     * This abstract complexType is expected to be extended and restricted for well-known operation methods with many instances, in Application Schemas that define operation-method-specialized element names and contents. Specific parameter value elements are directly contained in concrete subtypes, not in this abstract type. All concrete types derived from this type shall extend this type to include one "...Value" element with an appropriate type, which should be one of the element types allowed in the ParameterValueType. In addition, all derived concrete types shall extend this type to include a "operationParameter" property element that references one element substitutable for the "OperationParameter" object element.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link AbstractGeneralParameterValueType }{@code >}
     *     {@link JAXBElement }{@code <}{@link ParameterValueGroupType }{@code >}
     *     {@link JAXBElement }{@code <}{@link ParameterValueType }{@code >}
     *     
     */
    public JAXBElement<? extends AbstractGeneralParameterValueType> getAbstractGeneralParameterValue() {
        return abstractGeneralParameterValue;
    }

    /**
     * Imposta il valore della proprietà abstractGeneralParameterValue.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link AbstractGeneralParameterValueType }{@code >}
     *     {@link JAXBElement }{@code <}{@link ParameterValueGroupType }{@code >}
     *     {@link JAXBElement }{@code <}{@link ParameterValueType }{@code >}
     *     
     * @see #getAbstractGeneralParameterValue()
     */
    public void setAbstractGeneralParameterValue(JAXBElement<? extends AbstractGeneralParameterValueType> value) {
        this.abstractGeneralParameterValue = value;
    }

    public boolean isSetAbstractGeneralParameterValue() {
        return (this.abstractGeneralParameterValue!= null);
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
        {
            JAXBElement<? extends AbstractGeneralParameterValueType> theAbstractGeneralParameterValue;
            theAbstractGeneralParameterValue = this.getAbstractGeneralParameterValue();
            strategy.appendField(locator, this, "abstractGeneralParameterValue", buffer, theAbstractGeneralParameterValue, this.isSetAbstractGeneralParameterValue());
        }
        return buffer;
    }

    @Override
    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if ((object == null)||(this.getClass()!= object.getClass())) {
            return false;
        }
        if (this == object) {
            return true;
        }
        final AbstractGeneralParameterValuePropertyType that = ((AbstractGeneralParameterValuePropertyType) object);
        {
            JAXBElement<? extends AbstractGeneralParameterValueType> lhsAbstractGeneralParameterValue;
            lhsAbstractGeneralParameterValue = this.getAbstractGeneralParameterValue();
            JAXBElement<? extends AbstractGeneralParameterValueType> rhsAbstractGeneralParameterValue;
            rhsAbstractGeneralParameterValue = that.getAbstractGeneralParameterValue();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "abstractGeneralParameterValue", lhsAbstractGeneralParameterValue), LocatorUtils.property(thatLocator, "abstractGeneralParameterValue", rhsAbstractGeneralParameterValue), lhsAbstractGeneralParameterValue, rhsAbstractGeneralParameterValue, this.isSetAbstractGeneralParameterValue(), that.isSetAbstractGeneralParameterValue())) {
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
        int currentHashCode = 1;
        {
            JAXBElement<? extends AbstractGeneralParameterValueType> theAbstractGeneralParameterValue;
            theAbstractGeneralParameterValue = this.getAbstractGeneralParameterValue();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "abstractGeneralParameterValue", theAbstractGeneralParameterValue), currentHashCode, theAbstractGeneralParameterValue, this.isSetAbstractGeneralParameterValue());
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
        if (draftCopy instanceof AbstractGeneralParameterValuePropertyType) {
            final AbstractGeneralParameterValuePropertyType copy = ((AbstractGeneralParameterValuePropertyType) draftCopy);
            {
                java.lang.Boolean abstractGeneralParameterValueShouldBeCopiedAndSet = strategy.shouldBeCopiedAndSet(locator, this.isSetAbstractGeneralParameterValue());
                if (abstractGeneralParameterValueShouldBeCopiedAndSet == java.lang.Boolean.TRUE) {
                    JAXBElement<? extends AbstractGeneralParameterValueType> sourceAbstractGeneralParameterValue;
                    sourceAbstractGeneralParameterValue = this.getAbstractGeneralParameterValue();
                    @SuppressWarnings("unchecked")
                    JAXBElement<? extends AbstractGeneralParameterValueType> copyAbstractGeneralParameterValue = ((JAXBElement<? extends AbstractGeneralParameterValueType> ) strategy.copy(LocatorUtils.property(locator, "abstractGeneralParameterValue", sourceAbstractGeneralParameterValue), sourceAbstractGeneralParameterValue, this.isSetAbstractGeneralParameterValue()));
                    copy.setAbstractGeneralParameterValue(copyAbstractGeneralParameterValue);
                } else {
                    if (abstractGeneralParameterValueShouldBeCopiedAndSet == java.lang.Boolean.FALSE) {
                        copy.abstractGeneralParameterValue = null;
                    }
                }
            }
        }
        return draftCopy;
    }

    @Override
    public Object createNewInstance() {
        return new AbstractGeneralParameterValuePropertyType();
    }

    @Override
    public void mergeFrom(Object left, Object right) {
        final MergeStrategy strategy = JAXBMergeStrategy.getInstance();
        mergeFrom(null, null, left, right, strategy);
    }

    @Override
    public void mergeFrom(ObjectLocator leftLocator, ObjectLocator rightLocator, Object left, Object right, MergeStrategy strategy) {
        if (right instanceof AbstractGeneralParameterValuePropertyType) {
            final AbstractGeneralParameterValuePropertyType target = this;
            final AbstractGeneralParameterValuePropertyType leftObject = ((AbstractGeneralParameterValuePropertyType) left);
            final AbstractGeneralParameterValuePropertyType rightObject = ((AbstractGeneralParameterValuePropertyType) right);
            {
                java.lang.Boolean abstractGeneralParameterValueShouldBeMergedAndSet = strategy.shouldBeMergedAndSet(leftLocator, rightLocator, leftObject.isSetAbstractGeneralParameterValue(), rightObject.isSetAbstractGeneralParameterValue());
                if (abstractGeneralParameterValueShouldBeMergedAndSet == java.lang.Boolean.TRUE) {
                    JAXBElement<? extends AbstractGeneralParameterValueType> lhsAbstractGeneralParameterValue;
                    lhsAbstractGeneralParameterValue = leftObject.getAbstractGeneralParameterValue();
                    JAXBElement<? extends AbstractGeneralParameterValueType> rhsAbstractGeneralParameterValue;
                    rhsAbstractGeneralParameterValue = rightObject.getAbstractGeneralParameterValue();
                    JAXBElement<? extends AbstractGeneralParameterValueType> mergedAbstractGeneralParameterValue = ((JAXBElement<? extends AbstractGeneralParameterValueType> ) strategy.merge(LocatorUtils.property(leftLocator, "abstractGeneralParameterValue", lhsAbstractGeneralParameterValue), LocatorUtils.property(rightLocator, "abstractGeneralParameterValue", rhsAbstractGeneralParameterValue), lhsAbstractGeneralParameterValue, rhsAbstractGeneralParameterValue, leftObject.isSetAbstractGeneralParameterValue(), rightObject.isSetAbstractGeneralParameterValue()));
                    target.setAbstractGeneralParameterValue(mergedAbstractGeneralParameterValue);
                } else {
                    if (abstractGeneralParameterValueShouldBeMergedAndSet == java.lang.Boolean.FALSE) {
                        target.abstractGeneralParameterValue = null;
                    }
                }
            }
        }
    }

}
