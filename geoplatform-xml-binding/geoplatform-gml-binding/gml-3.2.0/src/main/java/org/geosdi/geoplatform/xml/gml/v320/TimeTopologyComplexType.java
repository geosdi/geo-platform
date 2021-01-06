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
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
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
 * <p>Classe Java per TimeTopologyComplexType complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="TimeTopologyComplexType">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.opengis.net/gml}AbstractTimeComplexType">
 *       &lt;sequence>
 *         &lt;element name="primitive" type="{http://www.opengis.net/gml}TimeTopologyPrimitivePropertyType" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TimeTopologyComplexType", propOrder = {
    "primitive"
})
public abstract class TimeTopologyComplexType
    extends AbstractTimeComplexType
    implements Cloneable, CopyTo, Equals, HashCode, MergeFrom, ToString
{

    @XmlElement(required = true)
    protected List<TimeTopologyPrimitivePropertyType> primitive;

    /**
     * Gets the value of the primitive property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the primitive property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPrimitive().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link TimeTopologyPrimitivePropertyType }
     * 
     * 
     */
    public List<TimeTopologyPrimitivePropertyType> getPrimitive() {
        if (primitive == null) {
            primitive = new ArrayList<TimeTopologyPrimitivePropertyType>();
        }
        return this.primitive;
    }

    public boolean isSetPrimitive() {
        return ((this.primitive!= null)&&(!this.primitive.isEmpty()));
    }

    public void unsetPrimitive() {
        this.primitive = null;
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
            List<TimeTopologyPrimitivePropertyType> thePrimitive;
            thePrimitive = this.getPrimitive();
            strategy.appendField(locator, this, "primitive", buffer, thePrimitive);
        }
        return buffer;
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof TimeTopologyComplexType)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        if (!super.equals(thisLocator, thatLocator, object, strategy)) {
            return false;
        }
        final TimeTopologyComplexType that = ((TimeTopologyComplexType) object);
        {
            List<TimeTopologyPrimitivePropertyType> lhsPrimitive;
            lhsPrimitive = this.getPrimitive();
            List<TimeTopologyPrimitivePropertyType> rhsPrimitive;
            rhsPrimitive = that.getPrimitive();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "primitive", lhsPrimitive), LocatorUtils.property(thatLocator, "primitive", rhsPrimitive), lhsPrimitive, rhsPrimitive)) {
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
            List<TimeTopologyPrimitivePropertyType> thePrimitive;
            thePrimitive = this.getPrimitive();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "primitive", thePrimitive), currentHashCode, thePrimitive);
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
        if (null == target) {
            throw new IllegalArgumentException("Target argument must not be null for abstract copyable classes.");
        }
        super.copyTo(locator, target, strategy);
        if (target instanceof TimeTopologyComplexType) {
            final TimeTopologyComplexType copy = ((TimeTopologyComplexType) target);
            if (this.isSetPrimitive()) {
                List<TimeTopologyPrimitivePropertyType> sourcePrimitive;
                sourcePrimitive = this.getPrimitive();
                @SuppressWarnings("unchecked")
                List<TimeTopologyPrimitivePropertyType> copyPrimitive = ((List<TimeTopologyPrimitivePropertyType> ) strategy.copy(LocatorUtils.property(locator, "primitive", sourcePrimitive), sourcePrimitive));
                copy.unsetPrimitive();
                List<TimeTopologyPrimitivePropertyType> uniquePrimitivel = copy.getPrimitive();
                uniquePrimitivel.addAll(copyPrimitive);
            } else {
                copy.unsetPrimitive();
            }
        }
        return target;
    }

    public void mergeFrom(Object left, Object right) {
        final MergeStrategy strategy = JAXBMergeStrategy.INSTANCE;
        mergeFrom(null, null, left, right, strategy);
    }

    public void mergeFrom(ObjectLocator leftLocator, ObjectLocator rightLocator, Object left, Object right, MergeStrategy strategy) {
        super.mergeFrom(leftLocator, rightLocator, left, right, strategy);
        if (right instanceof TimeTopologyComplexType) {
            final TimeTopologyComplexType target = this;
            final TimeTopologyComplexType leftObject = ((TimeTopologyComplexType) left);
            final TimeTopologyComplexType rightObject = ((TimeTopologyComplexType) right);
            {
                List<TimeTopologyPrimitivePropertyType> lhsPrimitive;
                lhsPrimitive = leftObject.getPrimitive();
                List<TimeTopologyPrimitivePropertyType> rhsPrimitive;
                rhsPrimitive = rightObject.getPrimitive();
                target.unsetPrimitive();
                List<TimeTopologyPrimitivePropertyType> uniquePrimitivel = target.getPrimitive();
                uniquePrimitivel.addAll(((List<TimeTopologyPrimitivePropertyType> ) strategy.merge(LocatorUtils.property(leftLocator, "primitive", lhsPrimitive), LocatorUtils.property(rightLocator, "primitive", rhsPrimitive), lhsPrimitive, rhsPrimitive)));
            }
        }
    }

    public void setPrimitive(List<TimeTopologyPrimitivePropertyType> value) {
        List<TimeTopologyPrimitivePropertyType> draftl = this.getPrimitive();
        draftl.addAll(value);
    }

    public TimeTopologyComplexType withPrimitive(TimeTopologyPrimitivePropertyType... values) {
        if (values!= null) {
            for (TimeTopologyPrimitivePropertyType value: values) {
                getPrimitive().add(value);
            }
        }
        return this;
    }

    public TimeTopologyComplexType withPrimitive(Collection<TimeTopologyPrimitivePropertyType> values) {
        if (values!= null) {
            getPrimitive().addAll(values);
        }
        return this;
    }

    public TimeTopologyComplexType withPrimitive(List<TimeTopologyPrimitivePropertyType> value) {
        setPrimitive(value);
        return this;
    }

    @Override
    public TimeTopologyComplexType withMetaDataProperty(MetaDataPropertyType... values) {
        if (values!= null) {
            for (MetaDataPropertyType value: values) {
                getMetaDataProperty().add(value);
            }
        }
        return this;
    }

    @Override
    public TimeTopologyComplexType withMetaDataProperty(Collection<MetaDataPropertyType> values) {
        if (values!= null) {
            getMetaDataProperty().addAll(values);
        }
        return this;
    }

    @Override
    public TimeTopologyComplexType withDescription(StringOrRefType value) {
        setDescription(value);
        return this;
    }

    @Override
    public TimeTopologyComplexType withDescriptionReference(ReferenceType value) {
        setDescriptionReference(value);
        return this;
    }

    @Override
    public TimeTopologyComplexType withIdentifier(CodeWithAuthorityType value) {
        setIdentifier(value);
        return this;
    }

    @Override
    public TimeTopologyComplexType withName(CodeType... values) {
        if (values!= null) {
            for (CodeType value: values) {
                getName().add(value);
            }
        }
        return this;
    }

    @Override
    public TimeTopologyComplexType withName(Collection<CodeType> values) {
        if (values!= null) {
            getName().addAll(values);
        }
        return this;
    }

    @Override
    public TimeTopologyComplexType withId(String value) {
        setId(value);
        return this;
    }

    @Override
    public TimeTopologyComplexType withMetaDataProperty(List<MetaDataPropertyType> value) {
        setMetaDataProperty(value);
        return this;
    }

    @Override
    public TimeTopologyComplexType withName(List<CodeType> value) {
        setName(value);
        return this;
    }

}
