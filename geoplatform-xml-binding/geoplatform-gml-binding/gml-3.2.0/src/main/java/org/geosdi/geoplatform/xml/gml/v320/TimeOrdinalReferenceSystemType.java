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
 * <p>Classe Java per TimeOrdinalReferenceSystemType complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="TimeOrdinalReferenceSystemType">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.opengis.net/gml}TimeReferenceSystemType">
 *       &lt;sequence>
 *         &lt;element name="component" type="{http://www.opengis.net/gml}TimeOrdinalEraPropertyType" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TimeOrdinalReferenceSystemType", propOrder = {
    "component"
})
public class TimeOrdinalReferenceSystemType
    extends TimeReferenceSystemType
    implements Cloneable, CopyTo, Equals, HashCode, MergeFrom, ToString
{

    @XmlElement(required = true)
    protected List<TimeOrdinalEraPropertyType> component;

    /**
     * Gets the value of the component property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the component property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getComponent().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link TimeOrdinalEraPropertyType }
     * 
     * 
     */
    public List<TimeOrdinalEraPropertyType> getComponent() {
        if (component == null) {
            component = new ArrayList<TimeOrdinalEraPropertyType>();
        }
        return this.component;
    }

    public boolean isSetComponent() {
        return ((this.component!= null)&&(!this.component.isEmpty()));
    }

    public void unsetComponent() {
        this.component = null;
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
            List<TimeOrdinalEraPropertyType> theComponent;
            theComponent = this.getComponent();
            strategy.appendField(locator, this, "component", buffer, theComponent);
        }
        return buffer;
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof TimeOrdinalReferenceSystemType)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        if (!super.equals(thisLocator, thatLocator, object, strategy)) {
            return false;
        }
        final TimeOrdinalReferenceSystemType that = ((TimeOrdinalReferenceSystemType) object);
        {
            List<TimeOrdinalEraPropertyType> lhsComponent;
            lhsComponent = this.getComponent();
            List<TimeOrdinalEraPropertyType> rhsComponent;
            rhsComponent = that.getComponent();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "component", lhsComponent), LocatorUtils.property(thatLocator, "component", rhsComponent), lhsComponent, rhsComponent)) {
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
            List<TimeOrdinalEraPropertyType> theComponent;
            theComponent = this.getComponent();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "component", theComponent), currentHashCode, theComponent);
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
        if (draftCopy instanceof TimeOrdinalReferenceSystemType) {
            final TimeOrdinalReferenceSystemType copy = ((TimeOrdinalReferenceSystemType) draftCopy);
            if (this.isSetComponent()) {
                List<TimeOrdinalEraPropertyType> sourceComponent;
                sourceComponent = this.getComponent();
                @SuppressWarnings("unchecked")
                List<TimeOrdinalEraPropertyType> copyComponent = ((List<TimeOrdinalEraPropertyType> ) strategy.copy(LocatorUtils.property(locator, "component", sourceComponent), sourceComponent));
                copy.unsetComponent();
                List<TimeOrdinalEraPropertyType> uniqueComponentl = copy.getComponent();
                uniqueComponentl.addAll(copyComponent);
            } else {
                copy.unsetComponent();
            }
        }
        return draftCopy;
    }

    public Object createNewInstance() {
        return new TimeOrdinalReferenceSystemType();
    }

    public void mergeFrom(Object left, Object right) {
        final MergeStrategy strategy = JAXBMergeStrategy.INSTANCE;
        mergeFrom(null, null, left, right, strategy);
    }

    public void mergeFrom(ObjectLocator leftLocator, ObjectLocator rightLocator, Object left, Object right, MergeStrategy strategy) {
        super.mergeFrom(leftLocator, rightLocator, left, right, strategy);
        if (right instanceof TimeOrdinalReferenceSystemType) {
            final TimeOrdinalReferenceSystemType target = this;
            final TimeOrdinalReferenceSystemType leftObject = ((TimeOrdinalReferenceSystemType) left);
            final TimeOrdinalReferenceSystemType rightObject = ((TimeOrdinalReferenceSystemType) right);
            {
                List<TimeOrdinalEraPropertyType> lhsComponent;
                lhsComponent = leftObject.getComponent();
                List<TimeOrdinalEraPropertyType> rhsComponent;
                rhsComponent = rightObject.getComponent();
                target.unsetComponent();
                List<TimeOrdinalEraPropertyType> uniqueComponentl = target.getComponent();
                uniqueComponentl.addAll(((List<TimeOrdinalEraPropertyType> ) strategy.merge(LocatorUtils.property(leftLocator, "component", lhsComponent), LocatorUtils.property(rightLocator, "component", rhsComponent), lhsComponent, rhsComponent)));
            }
        }
    }

    public void setComponent(List<TimeOrdinalEraPropertyType> value) {
        List<TimeOrdinalEraPropertyType> draftl = this.getComponent();
        draftl.addAll(value);
    }

    public TimeOrdinalReferenceSystemType withComponent(TimeOrdinalEraPropertyType... values) {
        if (values!= null) {
            for (TimeOrdinalEraPropertyType value: values) {
                getComponent().add(value);
            }
        }
        return this;
    }

    public TimeOrdinalReferenceSystemType withComponent(Collection<TimeOrdinalEraPropertyType> values) {
        if (values!= null) {
            getComponent().addAll(values);
        }
        return this;
    }

    public TimeOrdinalReferenceSystemType withComponent(List<TimeOrdinalEraPropertyType> value) {
        setComponent(value);
        return this;
    }

    @Override
    public TimeOrdinalReferenceSystemType withDomainOfValidity(String value) {
        setDomainOfValidity(value);
        return this;
    }

    @Override
    public TimeOrdinalReferenceSystemType withRemarks(String value) {
        setRemarks(value);
        return this;
    }

    @Override
    public TimeOrdinalReferenceSystemType withMetaDataProperty(MetaDataPropertyType... values) {
        if (values!= null) {
            for (MetaDataPropertyType value: values) {
                getMetaDataProperty().add(value);
            }
        }
        return this;
    }

    @Override
    public TimeOrdinalReferenceSystemType withMetaDataProperty(Collection<MetaDataPropertyType> values) {
        if (values!= null) {
            getMetaDataProperty().addAll(values);
        }
        return this;
    }

    @Override
    public TimeOrdinalReferenceSystemType withDescription(StringOrRefType value) {
        setDescription(value);
        return this;
    }

    @Override
    public TimeOrdinalReferenceSystemType withDescriptionReference(ReferenceType value) {
        setDescriptionReference(value);
        return this;
    }

    @Override
    public TimeOrdinalReferenceSystemType withIdentifier(CodeWithAuthorityType value) {
        setIdentifier(value);
        return this;
    }

    @Override
    public TimeOrdinalReferenceSystemType withName(CodeType... values) {
        if (values!= null) {
            for (CodeType value: values) {
                getName().add(value);
            }
        }
        return this;
    }

    @Override
    public TimeOrdinalReferenceSystemType withName(Collection<CodeType> values) {
        if (values!= null) {
            getName().addAll(values);
        }
        return this;
    }

    @Override
    public TimeOrdinalReferenceSystemType withId(String value) {
        setId(value);
        return this;
    }

    @Override
    public TimeOrdinalReferenceSystemType withMetaDataProperty(List<MetaDataPropertyType> value) {
        setMetaDataProperty(value);
        return this;
    }

    @Override
    public TimeOrdinalReferenceSystemType withName(List<CodeType> value) {
        setName(value);
        return this;
    }

}
