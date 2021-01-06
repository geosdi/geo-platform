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
 * gml:AbstractGeneralParameterValuePropertyType is a  property type for inline association roles to a parameter value or group of parameter values, always containing the values.
 * 
 * <p>Classe Java per AbstractGeneralParameterValuePropertyType complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="AbstractGeneralParameterValuePropertyType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element ref="{http://www.opengis.net/gml}AbstractGeneralParameterValue"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AbstractGeneralParameterValuePropertyType", propOrder = {
    "abstractGeneralParameterValue"
})
public class AbstractGeneralParameterValuePropertyType
    implements Cloneable, CopyTo, Equals, HashCode, MergeFrom, ToString
{

    @XmlElementRef(name = "AbstractGeneralParameterValue", namespace = "http://www.opengis.net/gml", type = JAXBElement.class)
    protected JAXBElement<? extends AbstractGeneralParameterValueType> abstractGeneralParameterValue;

    /**
     * Recupera il valore della proprietà abstractGeneralParameterValue.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link ParameterValueType }{@code >}
     *     {@link JAXBElement }{@code <}{@link ParameterValueGroupType }{@code >}
     *     {@link JAXBElement }{@code <}{@link AbstractGeneralParameterValueType }{@code >}
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
     *     {@link JAXBElement }{@code <}{@link ParameterValueType }{@code >}
     *     {@link JAXBElement }{@code <}{@link ParameterValueGroupType }{@code >}
     *     {@link JAXBElement }{@code <}{@link AbstractGeneralParameterValueType }{@code >}
     *     
     */
    public void setAbstractGeneralParameterValue(JAXBElement<? extends AbstractGeneralParameterValueType> value) {
        this.abstractGeneralParameterValue = value;
    }

    public boolean isSetAbstractGeneralParameterValue() {
        return (this.abstractGeneralParameterValue!= null);
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
        {
            JAXBElement<? extends AbstractGeneralParameterValueType> theAbstractGeneralParameterValue;
            theAbstractGeneralParameterValue = this.getAbstractGeneralParameterValue();
            strategy.appendField(locator, this, "abstractGeneralParameterValue", buffer, theAbstractGeneralParameterValue);
        }
        return buffer;
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof AbstractGeneralParameterValuePropertyType)) {
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
            if (!strategy.equals(LocatorUtils.property(thisLocator, "abstractGeneralParameterValue", lhsAbstractGeneralParameterValue), LocatorUtils.property(thatLocator, "abstractGeneralParameterValue", rhsAbstractGeneralParameterValue), lhsAbstractGeneralParameterValue, rhsAbstractGeneralParameterValue)) {
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
        int currentHashCode = 1;
        {
            JAXBElement<? extends AbstractGeneralParameterValueType> theAbstractGeneralParameterValue;
            theAbstractGeneralParameterValue = this.getAbstractGeneralParameterValue();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "abstractGeneralParameterValue", theAbstractGeneralParameterValue), currentHashCode, theAbstractGeneralParameterValue);
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
        if (draftCopy instanceof AbstractGeneralParameterValuePropertyType) {
            final AbstractGeneralParameterValuePropertyType copy = ((AbstractGeneralParameterValuePropertyType) draftCopy);
            if (this.isSetAbstractGeneralParameterValue()) {
                JAXBElement<? extends AbstractGeneralParameterValueType> sourceAbstractGeneralParameterValue;
                sourceAbstractGeneralParameterValue = this.getAbstractGeneralParameterValue();
                @SuppressWarnings("unchecked")
                JAXBElement<? extends AbstractGeneralParameterValueType> copyAbstractGeneralParameterValue = ((JAXBElement<? extends AbstractGeneralParameterValueType> ) strategy.copy(LocatorUtils.property(locator, "abstractGeneralParameterValue", sourceAbstractGeneralParameterValue), sourceAbstractGeneralParameterValue));
                copy.setAbstractGeneralParameterValue(copyAbstractGeneralParameterValue);
            } else {
                copy.abstractGeneralParameterValue = null;
            }
        }
        return draftCopy;
    }

    public Object createNewInstance() {
        return new AbstractGeneralParameterValuePropertyType();
    }

    public void mergeFrom(Object left, Object right) {
        final MergeStrategy strategy = JAXBMergeStrategy.INSTANCE;
        mergeFrom(null, null, left, right, strategy);
    }

    public void mergeFrom(ObjectLocator leftLocator, ObjectLocator rightLocator, Object left, Object right, MergeStrategy strategy) {
        if (right instanceof AbstractGeneralParameterValuePropertyType) {
            final AbstractGeneralParameterValuePropertyType target = this;
            final AbstractGeneralParameterValuePropertyType leftObject = ((AbstractGeneralParameterValuePropertyType) left);
            final AbstractGeneralParameterValuePropertyType rightObject = ((AbstractGeneralParameterValuePropertyType) right);
            {
                JAXBElement<? extends AbstractGeneralParameterValueType> lhsAbstractGeneralParameterValue;
                lhsAbstractGeneralParameterValue = leftObject.getAbstractGeneralParameterValue();
                JAXBElement<? extends AbstractGeneralParameterValueType> rhsAbstractGeneralParameterValue;
                rhsAbstractGeneralParameterValue = rightObject.getAbstractGeneralParameterValue();
                target.setAbstractGeneralParameterValue(((JAXBElement<? extends AbstractGeneralParameterValueType> ) strategy.merge(LocatorUtils.property(leftLocator, "abstractGeneralParameterValue", lhsAbstractGeneralParameterValue), LocatorUtils.property(rightLocator, "abstractGeneralParameterValue", rhsAbstractGeneralParameterValue), lhsAbstractGeneralParameterValue, rhsAbstractGeneralParameterValue)));
            }
        }
    }

    public AbstractGeneralParameterValuePropertyType withAbstractGeneralParameterValue(JAXBElement<? extends AbstractGeneralParameterValueType> value) {
        setAbstractGeneralParameterValue(value);
        return this;
    }

}
