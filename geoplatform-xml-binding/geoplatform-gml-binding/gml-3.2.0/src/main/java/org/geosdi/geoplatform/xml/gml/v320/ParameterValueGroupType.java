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
 * <p>Classe Java per ParameterValueGroupType complex type.</p>
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.</p>
 * 
 * <pre>{@code
 * <complexType name="ParameterValueGroupType">
 *   <complexContent>
 *     <extension base="{http://www.opengis.net/gml}AbstractGeneralParameterValueType">
 *       <sequence>
 *         <element ref="{http://www.opengis.net/gml}parameterValue" maxOccurs="unbounded" minOccurs="2"/>
 *         <element ref="{http://www.opengis.net/gml}group"/>
 *       </sequence>
 *     </extension>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ParameterValueGroupType", propOrder = {
    "parameterValue",
    "group"
})
public class ParameterValueGroupType
    extends AbstractGeneralParameterValueType
    implements Cloneable, CopyTo, Equals, HashCode, MergeFrom, ToString
{

    /**
     * gml:parameterValue is a composition association to a parameter value or group of parameter values used by a coordinate operation.
     * 
     */
    @XmlElementRef(name = "parameterValue", namespace = "http://www.opengis.net/gml", type = JAXBElement.class)
    protected List<JAXBElement<AbstractGeneralParameterValuePropertyType>> parameterValue;
    /**
     * gml:group is an association role to the operation parameter group for which this element provides parameter values.
     * 
     */
    @XmlElementRef(name = "group", namespace = "http://www.opengis.net/gml", type = JAXBElement.class)
    protected JAXBElement<OperationParameterGroupPropertyType> group;

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

    /**
     * gml:group is an association role to the operation parameter group for which this element provides parameter values.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link OperationParameterGroupPropertyType }{@code >}
     *     {@link JAXBElement }{@code <}{@link OperationParameterGroupPropertyType }{@code >}
     *     
     */
    public JAXBElement<OperationParameterGroupPropertyType> getGroup() {
        return group;
    }

    /**
     * Imposta il valore della proprietà group.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link OperationParameterGroupPropertyType }{@code >}
     *     {@link JAXBElement }{@code <}{@link OperationParameterGroupPropertyType }{@code >}
     *     
     * @see #getGroup()
     */
    public void setGroup(JAXBElement<OperationParameterGroupPropertyType> value) {
        this.group = value;
    }

    public boolean isSetGroup() {
        return (this.group!= null);
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
            List<JAXBElement<AbstractGeneralParameterValuePropertyType>> theParameterValue;
            theParameterValue = (this.isSetParameterValue()?this.getParameterValue():null);
            strategy.appendField(locator, this, "parameterValue", buffer, theParameterValue, this.isSetParameterValue());
        }
        {
            JAXBElement<OperationParameterGroupPropertyType> theGroup;
            theGroup = this.getGroup();
            strategy.appendField(locator, this, "group", buffer, theGroup, this.isSetGroup());
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
        final ParameterValueGroupType that = ((ParameterValueGroupType) object);
        {
            List<JAXBElement<AbstractGeneralParameterValuePropertyType>> lhsParameterValue;
            lhsParameterValue = (this.isSetParameterValue()?this.getParameterValue():null);
            List<JAXBElement<AbstractGeneralParameterValuePropertyType>> rhsParameterValue;
            rhsParameterValue = (that.isSetParameterValue()?that.getParameterValue():null);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "parameterValue", lhsParameterValue), LocatorUtils.property(thatLocator, "parameterValue", rhsParameterValue), lhsParameterValue, rhsParameterValue, this.isSetParameterValue(), that.isSetParameterValue())) {
                return false;
            }
        }
        {
            JAXBElement<OperationParameterGroupPropertyType> lhsGroup;
            lhsGroup = this.getGroup();
            JAXBElement<OperationParameterGroupPropertyType> rhsGroup;
            rhsGroup = that.getGroup();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "group", lhsGroup), LocatorUtils.property(thatLocator, "group", rhsGroup), lhsGroup, rhsGroup, this.isSetGroup(), that.isSetGroup())) {
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
            List<JAXBElement<AbstractGeneralParameterValuePropertyType>> theParameterValue;
            theParameterValue = (this.isSetParameterValue()?this.getParameterValue():null);
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "parameterValue", theParameterValue), currentHashCode, theParameterValue, this.isSetParameterValue());
        }
        {
            JAXBElement<OperationParameterGroupPropertyType> theGroup;
            theGroup = this.getGroup();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "group", theGroup), currentHashCode, theGroup, this.isSetGroup());
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
        if (draftCopy instanceof ParameterValueGroupType) {
            final ParameterValueGroupType copy = ((ParameterValueGroupType) draftCopy);
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
            {
                java.lang.Boolean groupShouldBeCopiedAndSet = strategy.shouldBeCopiedAndSet(locator, this.isSetGroup());
                if (groupShouldBeCopiedAndSet == java.lang.Boolean.TRUE) {
                    JAXBElement<OperationParameterGroupPropertyType> sourceGroup;
                    sourceGroup = this.getGroup();
                    @SuppressWarnings("unchecked")
                    JAXBElement<OperationParameterGroupPropertyType> copyGroup = ((JAXBElement<OperationParameterGroupPropertyType> ) strategy.copy(LocatorUtils.property(locator, "group", sourceGroup), sourceGroup, this.isSetGroup()));
                    copy.setGroup(copyGroup);
                } else {
                    if (groupShouldBeCopiedAndSet == java.lang.Boolean.FALSE) {
                        copy.group = null;
                    }
                }
            }
        }
        return draftCopy;
    }

    @Override
    public Object createNewInstance() {
        return new ParameterValueGroupType();
    }

    @Override
    public void mergeFrom(Object left, Object right) {
        final MergeStrategy strategy = JAXBMergeStrategy.getInstance();
        mergeFrom(null, null, left, right, strategy);
    }

    @Override
    public void mergeFrom(ObjectLocator leftLocator, ObjectLocator rightLocator, Object left, Object right, MergeStrategy strategy) {
        super.mergeFrom(leftLocator, rightLocator, left, right, strategy);
        if (right instanceof ParameterValueGroupType) {
            final ParameterValueGroupType target = this;
            final ParameterValueGroupType leftObject = ((ParameterValueGroupType) left);
            final ParameterValueGroupType rightObject = ((ParameterValueGroupType) right);
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
            {
                java.lang.Boolean groupShouldBeMergedAndSet = strategy.shouldBeMergedAndSet(leftLocator, rightLocator, leftObject.isSetGroup(), rightObject.isSetGroup());
                if (groupShouldBeMergedAndSet == java.lang.Boolean.TRUE) {
                    JAXBElement<OperationParameterGroupPropertyType> lhsGroup;
                    lhsGroup = leftObject.getGroup();
                    JAXBElement<OperationParameterGroupPropertyType> rhsGroup;
                    rhsGroup = rightObject.getGroup();
                    JAXBElement<OperationParameterGroupPropertyType> mergedGroup = ((JAXBElement<OperationParameterGroupPropertyType> ) strategy.merge(LocatorUtils.property(leftLocator, "group", lhsGroup), LocatorUtils.property(rightLocator, "group", rhsGroup), lhsGroup, rhsGroup, leftObject.isSetGroup(), rightObject.isSetGroup()));
                    target.setGroup(mergedGroup);
                } else {
                    if (groupShouldBeMergedAndSet == java.lang.Boolean.FALSE) {
                        target.group = null;
                    }
                }
            }
        }
    }

}
