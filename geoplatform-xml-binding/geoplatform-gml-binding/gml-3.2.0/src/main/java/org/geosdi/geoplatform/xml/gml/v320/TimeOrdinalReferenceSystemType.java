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
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
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
 * <p>Classe Java per TimeOrdinalReferenceSystemType complex type.</p>
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.</p>
 * 
 * <pre>{@code
 * <complexType name="TimeOrdinalReferenceSystemType">
 *   <complexContent>
 *     <extension base="{http://www.opengis.net/gml}TimeReferenceSystemType">
 *       <sequence>
 *         <element name="component" type="{http://www.opengis.net/gml}TimeOrdinalEraPropertyType" maxOccurs="unbounded"/>
 *       </sequence>
 *     </extension>
 *   </complexContent>
 * </complexType>
 * }</pre>
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
     * <p>This accessor method returns a reference to the live list,
     * not a snapshot. Therefore, any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a {@code set} method for the component property.</p>
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * </p>
     * <pre>
     * getComponent().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link TimeOrdinalEraPropertyType }
     * </p>
     * 
     * 
     * @return
     *     The value of the component property.
     */
    public List<TimeOrdinalEraPropertyType> getComponent() {
        if (component == null) {
            component = new ArrayList<>();
        }
        return this.component;
    }

    public boolean isSetComponent() {
        return ((this.component!= null)&&(!this.component.isEmpty()));
    }

    public void unsetComponent() {
        this.component = null;
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
            List<TimeOrdinalEraPropertyType> theComponent;
            theComponent = (this.isSetComponent()?this.getComponent():null);
            strategy.appendField(locator, this, "component", buffer, theComponent, this.isSetComponent());
        }
        return buffer;
    }

    public void setComponent(List<TimeOrdinalEraPropertyType> value) {
        this.component = null;
        if (value!= null) {
            List<TimeOrdinalEraPropertyType> draftl = this.getComponent();
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
        final TimeOrdinalReferenceSystemType that = ((TimeOrdinalReferenceSystemType) object);
        {
            List<TimeOrdinalEraPropertyType> lhsComponent;
            lhsComponent = (this.isSetComponent()?this.getComponent():null);
            List<TimeOrdinalEraPropertyType> rhsComponent;
            rhsComponent = (that.isSetComponent()?that.getComponent():null);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "component", lhsComponent), LocatorUtils.property(thatLocator, "component", rhsComponent), lhsComponent, rhsComponent, this.isSetComponent(), that.isSetComponent())) {
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
            List<TimeOrdinalEraPropertyType> theComponent;
            theComponent = (this.isSetComponent()?this.getComponent():null);
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "component", theComponent), currentHashCode, theComponent, this.isSetComponent());
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
        if (draftCopy instanceof TimeOrdinalReferenceSystemType) {
            final TimeOrdinalReferenceSystemType copy = ((TimeOrdinalReferenceSystemType) draftCopy);
            {
                java.lang.Boolean componentShouldBeCopiedAndSet = strategy.shouldBeCopiedAndSet(locator, this.isSetComponent());
                if (componentShouldBeCopiedAndSet == java.lang.Boolean.TRUE) {
                    List<TimeOrdinalEraPropertyType> sourceComponent;
                    sourceComponent = (this.isSetComponent()?this.getComponent():null);
                    @SuppressWarnings("unchecked")
                    List<TimeOrdinalEraPropertyType> copyComponent = ((List<TimeOrdinalEraPropertyType> ) strategy.copy(LocatorUtils.property(locator, "component", sourceComponent), sourceComponent, this.isSetComponent()));
                    copy.setComponent(copyComponent);
                } else {
                    if (componentShouldBeCopiedAndSet == java.lang.Boolean.FALSE) {
                        copy.unsetComponent();
                    }
                }
            }
        }
        return draftCopy;
    }

    @Override
    public Object createNewInstance() {
        return new TimeOrdinalReferenceSystemType();
    }

    @Override
    public void mergeFrom(Object left, Object right) {
        final MergeStrategy strategy = JAXBMergeStrategy.getInstance();
        mergeFrom(null, null, left, right, strategy);
    }

    @Override
    public void mergeFrom(ObjectLocator leftLocator, ObjectLocator rightLocator, Object left, Object right, MergeStrategy strategy) {
        super.mergeFrom(leftLocator, rightLocator, left, right, strategy);
        if (right instanceof TimeOrdinalReferenceSystemType) {
            final TimeOrdinalReferenceSystemType target = this;
            final TimeOrdinalReferenceSystemType leftObject = ((TimeOrdinalReferenceSystemType) left);
            final TimeOrdinalReferenceSystemType rightObject = ((TimeOrdinalReferenceSystemType) right);
            {
                java.lang.Boolean componentShouldBeMergedAndSet = strategy.shouldBeMergedAndSet(leftLocator, rightLocator, leftObject.isSetComponent(), rightObject.isSetComponent());
                if (componentShouldBeMergedAndSet == java.lang.Boolean.TRUE) {
                    List<TimeOrdinalEraPropertyType> lhsComponent;
                    lhsComponent = (leftObject.isSetComponent()?leftObject.getComponent():null);
                    List<TimeOrdinalEraPropertyType> rhsComponent;
                    rhsComponent = (rightObject.isSetComponent()?rightObject.getComponent():null);
                    List<TimeOrdinalEraPropertyType> mergedComponent = ((List<TimeOrdinalEraPropertyType> ) strategy.merge(LocatorUtils.property(leftLocator, "component", lhsComponent), LocatorUtils.property(rightLocator, "component", rhsComponent), lhsComponent, rhsComponent, leftObject.isSetComponent(), rightObject.isSetComponent()));
                    target.setComponent(mergedComponent);
                } else {
                    if (componentShouldBeMergedAndSet == java.lang.Boolean.FALSE) {
                        target.unsetComponent();
                    }
                }
            }
        }
    }

}
