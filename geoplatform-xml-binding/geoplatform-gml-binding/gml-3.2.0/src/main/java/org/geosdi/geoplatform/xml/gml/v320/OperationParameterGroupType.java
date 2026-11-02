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

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import jakarta.xml.bind.JAXBElement;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElementRef;
import jakarta.xml.bind.annotation.XmlSchemaType;
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
 * <p>Classe Java per OperationParameterGroupType complex type.</p>
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.</p>
 * 
 * <pre>{@code
 * <complexType name="OperationParameterGroupType">
 *   <complexContent>
 *     <extension base="{http://www.opengis.net/gml}AbstractGeneralOperationParameterType">
 *       <sequence>
 *         <element ref="{http://www.opengis.net/gml}maximumOccurs" minOccurs="0"/>
 *         <element ref="{http://www.opengis.net/gml}generalOperationParameter" maxOccurs="unbounded" minOccurs="2"/>
 *       </sequence>
 *     </extension>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "OperationParameterGroupType", propOrder = {
    "maximumOccurs",
    "generalOperationParameter"
})
public class OperationParameterGroupType
    extends AbstractGeneralOperationParameterType
    implements Cloneable, CopyTo, Equals, HashCode, MergeFrom, ToString
{

    /**
     * gml:maximumOccurs is the maximum number of times that values for this parameter group may be included. If this attribute is omitted, the maximum number shall be one.
     * 
     */
    @XmlSchemaType(name = "positiveInteger")
    protected BigInteger maximumOccurs;
    /**
     * gml:generalOperationParameter is an association to an operation parameter or parameter group.
     * 
     */
    @XmlElementRef(name = "generalOperationParameter", namespace = "http://www.opengis.net/gml", type = JAXBElement.class)
    protected List<JAXBElement<AbstractGeneralOperationParameterPropertyType>> generalOperationParameter;

    /**
     * gml:maximumOccurs is the maximum number of times that values for this parameter group may be included. If this attribute is omitted, the maximum number shall be one.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getMaximumOccurs() {
        return maximumOccurs;
    }

    /**
     * Imposta il valore della proprietà maximumOccurs.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     * @see #getMaximumOccurs()
     */
    public void setMaximumOccurs(BigInteger value) {
        this.maximumOccurs = value;
    }

    public boolean isSetMaximumOccurs() {
        return (this.maximumOccurs!= null);
    }

    /**
     * gml:generalOperationParameter is an association to an operation parameter or parameter group.
     * 
     * Gets the value of the generalOperationParameter property.
     * 
     * <p>This accessor method returns a reference to the live list,
     * not a snapshot. Therefore, any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a {@code set} method for the generalOperationParameter property.</p>
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * </p>
     * <pre>
     * getGeneralOperationParameter().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link JAXBElement }{@code <}{@link AbstractGeneralOperationParameterPropertyType }{@code >}
     * {@link JAXBElement }{@code <}{@link AbstractGeneralOperationParameterPropertyType }{@code >}
     * </p>
     * 
     * 
     * @return
     *     The value of the generalOperationParameter property.
     */
    public List<JAXBElement<AbstractGeneralOperationParameterPropertyType>> getGeneralOperationParameter() {
        if (generalOperationParameter == null) {
            generalOperationParameter = new ArrayList<>();
        }
        return this.generalOperationParameter;
    }

    public boolean isSetGeneralOperationParameter() {
        return ((this.generalOperationParameter!= null)&&(!this.generalOperationParameter.isEmpty()));
    }

    public void unsetGeneralOperationParameter() {
        this.generalOperationParameter = null;
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
            BigInteger theMaximumOccurs;
            theMaximumOccurs = this.getMaximumOccurs();
            strategy.appendField(locator, this, "maximumOccurs", buffer, theMaximumOccurs, this.isSetMaximumOccurs());
        }
        {
            List<JAXBElement<AbstractGeneralOperationParameterPropertyType>> theGeneralOperationParameter;
            theGeneralOperationParameter = (this.isSetGeneralOperationParameter()?this.getGeneralOperationParameter():null);
            strategy.appendField(locator, this, "generalOperationParameter", buffer, theGeneralOperationParameter, this.isSetGeneralOperationParameter());
        }
        return buffer;
    }

    public void setGeneralOperationParameter(List<JAXBElement<AbstractGeneralOperationParameterPropertyType>> value) {
        this.generalOperationParameter = null;
        if (value!= null) {
            List<JAXBElement<AbstractGeneralOperationParameterPropertyType>> draftl = this.getGeneralOperationParameter();
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
        final OperationParameterGroupType that = ((OperationParameterGroupType) object);
        {
            BigInteger lhsMaximumOccurs;
            lhsMaximumOccurs = this.getMaximumOccurs();
            BigInteger rhsMaximumOccurs;
            rhsMaximumOccurs = that.getMaximumOccurs();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "maximumOccurs", lhsMaximumOccurs), LocatorUtils.property(thatLocator, "maximumOccurs", rhsMaximumOccurs), lhsMaximumOccurs, rhsMaximumOccurs, this.isSetMaximumOccurs(), that.isSetMaximumOccurs())) {
                return false;
            }
        }
        {
            List<JAXBElement<AbstractGeneralOperationParameterPropertyType>> lhsGeneralOperationParameter;
            lhsGeneralOperationParameter = (this.isSetGeneralOperationParameter()?this.getGeneralOperationParameter():null);
            List<JAXBElement<AbstractGeneralOperationParameterPropertyType>> rhsGeneralOperationParameter;
            rhsGeneralOperationParameter = (that.isSetGeneralOperationParameter()?that.getGeneralOperationParameter():null);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "generalOperationParameter", lhsGeneralOperationParameter), LocatorUtils.property(thatLocator, "generalOperationParameter", rhsGeneralOperationParameter), lhsGeneralOperationParameter, rhsGeneralOperationParameter, this.isSetGeneralOperationParameter(), that.isSetGeneralOperationParameter())) {
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
            BigInteger theMaximumOccurs;
            theMaximumOccurs = this.getMaximumOccurs();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "maximumOccurs", theMaximumOccurs), currentHashCode, theMaximumOccurs, this.isSetMaximumOccurs());
        }
        {
            List<JAXBElement<AbstractGeneralOperationParameterPropertyType>> theGeneralOperationParameter;
            theGeneralOperationParameter = (this.isSetGeneralOperationParameter()?this.getGeneralOperationParameter():null);
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "generalOperationParameter", theGeneralOperationParameter), currentHashCode, theGeneralOperationParameter, this.isSetGeneralOperationParameter());
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
        if (draftCopy instanceof OperationParameterGroupType) {
            final OperationParameterGroupType copy = ((OperationParameterGroupType) draftCopy);
            {
                java.lang.Boolean maximumOccursShouldBeCopiedAndSet = strategy.shouldBeCopiedAndSet(locator, this.isSetMaximumOccurs());
                if (maximumOccursShouldBeCopiedAndSet == java.lang.Boolean.TRUE) {
                    BigInteger sourceMaximumOccurs;
                    sourceMaximumOccurs = this.getMaximumOccurs();
                    BigInteger copyMaximumOccurs = ((BigInteger) strategy.copy(LocatorUtils.property(locator, "maximumOccurs", sourceMaximumOccurs), sourceMaximumOccurs, this.isSetMaximumOccurs()));
                    copy.setMaximumOccurs(copyMaximumOccurs);
                } else {
                    if (maximumOccursShouldBeCopiedAndSet == java.lang.Boolean.FALSE) {
                        copy.maximumOccurs = null;
                    }
                }
            }
            {
                java.lang.Boolean generalOperationParameterShouldBeCopiedAndSet = strategy.shouldBeCopiedAndSet(locator, this.isSetGeneralOperationParameter());
                if (generalOperationParameterShouldBeCopiedAndSet == java.lang.Boolean.TRUE) {
                    List<JAXBElement<AbstractGeneralOperationParameterPropertyType>> sourceGeneralOperationParameter;
                    sourceGeneralOperationParameter = (this.isSetGeneralOperationParameter()?this.getGeneralOperationParameter():null);
                    @SuppressWarnings("unchecked")
                    List<JAXBElement<AbstractGeneralOperationParameterPropertyType>> copyGeneralOperationParameter = ((List<JAXBElement<AbstractGeneralOperationParameterPropertyType>> ) strategy.copy(LocatorUtils.property(locator, "generalOperationParameter", sourceGeneralOperationParameter), sourceGeneralOperationParameter, this.isSetGeneralOperationParameter()));
                    copy.setGeneralOperationParameter(copyGeneralOperationParameter);
                } else {
                    if (generalOperationParameterShouldBeCopiedAndSet == java.lang.Boolean.FALSE) {
                        copy.unsetGeneralOperationParameter();
                    }
                }
            }
        }
        return draftCopy;
    }

    @Override
    public Object createNewInstance() {
        return new OperationParameterGroupType();
    }

    @Override
    public void mergeFrom(Object left, Object right) {
        final MergeStrategy strategy = JAXBMergeStrategy.getInstance();
        mergeFrom(null, null, left, right, strategy);
    }

    @Override
    public void mergeFrom(ObjectLocator leftLocator, ObjectLocator rightLocator, Object left, Object right, MergeStrategy strategy) {
        super.mergeFrom(leftLocator, rightLocator, left, right, strategy);
        if (right instanceof OperationParameterGroupType) {
            final OperationParameterGroupType target = this;
            final OperationParameterGroupType leftObject = ((OperationParameterGroupType) left);
            final OperationParameterGroupType rightObject = ((OperationParameterGroupType) right);
            {
                java.lang.Boolean maximumOccursShouldBeMergedAndSet = strategy.shouldBeMergedAndSet(leftLocator, rightLocator, leftObject.isSetMaximumOccurs(), rightObject.isSetMaximumOccurs());
                if (maximumOccursShouldBeMergedAndSet == java.lang.Boolean.TRUE) {
                    BigInteger lhsMaximumOccurs;
                    lhsMaximumOccurs = leftObject.getMaximumOccurs();
                    BigInteger rhsMaximumOccurs;
                    rhsMaximumOccurs = rightObject.getMaximumOccurs();
                    BigInteger mergedMaximumOccurs = ((BigInteger) strategy.merge(LocatorUtils.property(leftLocator, "maximumOccurs", lhsMaximumOccurs), LocatorUtils.property(rightLocator, "maximumOccurs", rhsMaximumOccurs), lhsMaximumOccurs, rhsMaximumOccurs, leftObject.isSetMaximumOccurs(), rightObject.isSetMaximumOccurs()));
                    target.setMaximumOccurs(mergedMaximumOccurs);
                } else {
                    if (maximumOccursShouldBeMergedAndSet == java.lang.Boolean.FALSE) {
                        target.maximumOccurs = null;
                    }
                }
            }
            {
                java.lang.Boolean generalOperationParameterShouldBeMergedAndSet = strategy.shouldBeMergedAndSet(leftLocator, rightLocator, leftObject.isSetGeneralOperationParameter(), rightObject.isSetGeneralOperationParameter());
                if (generalOperationParameterShouldBeMergedAndSet == java.lang.Boolean.TRUE) {
                    List<JAXBElement<AbstractGeneralOperationParameterPropertyType>> lhsGeneralOperationParameter;
                    lhsGeneralOperationParameter = (leftObject.isSetGeneralOperationParameter()?leftObject.getGeneralOperationParameter():null);
                    List<JAXBElement<AbstractGeneralOperationParameterPropertyType>> rhsGeneralOperationParameter;
                    rhsGeneralOperationParameter = (rightObject.isSetGeneralOperationParameter()?rightObject.getGeneralOperationParameter():null);
                    List<JAXBElement<AbstractGeneralOperationParameterPropertyType>> mergedGeneralOperationParameter = ((List<JAXBElement<AbstractGeneralOperationParameterPropertyType>> ) strategy.merge(LocatorUtils.property(leftLocator, "generalOperationParameter", lhsGeneralOperationParameter), LocatorUtils.property(rightLocator, "generalOperationParameter", rhsGeneralOperationParameter), lhsGeneralOperationParameter, rhsGeneralOperationParameter, leftObject.isSetGeneralOperationParameter(), rightObject.isSetGeneralOperationParameter()));
                    target.setGeneralOperationParameter(mergedGeneralOperationParameter);
                } else {
                    if (generalOperationParameterShouldBeMergedAndSet == java.lang.Boolean.FALSE) {
                        target.unsetGeneralOperationParameter();
                    }
                }
            }
        }
    }

}
