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
import jakarta.xml.bind.annotation.XmlSeeAlso;
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
 * <p>Classe Java per AbstractTopoPrimitiveType complex type.</p>
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.</p>
 * 
 * <pre>{@code
 * <complexType name="AbstractTopoPrimitiveType">
 *   <complexContent>
 *     <extension base="{http://www.opengis.net/gml}AbstractTopologyType">
 *       <sequence>
 *         <element ref="{http://www.opengis.net/gml}isolated" maxOccurs="unbounded" minOccurs="0"/>
 *         <element ref="{http://www.opengis.net/gml}container" minOccurs="0"/>
 *       </sequence>
 *     </extension>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AbstractTopoPrimitiveType", propOrder = {
    "isolated",
    "container"
})
@XmlSeeAlso({
    NodeType.class,
    EdgeType.class,
    FaceType.class,
    TopoSolidType.class
})
public abstract class AbstractTopoPrimitiveType
    extends AbstractTopologyType
    implements Cloneable, CopyTo, Equals, HashCode, MergeFrom, ToString
{

    /**
     * The gml:isolated property element implements the role of the same name of the ISO 19107 “Isolated In” association (see ISO 19107:2003, 7.3.10.4).
     * 
     */
    protected List<IsolatedPropertyType> isolated;
    /**
     * The gml:container property element implements the role of the same name of the ISO 19107 “Isolated In” association (see ISO 19107:2003, 7.3.10.4).
     * 
     */
    protected ContainerPropertyType container;

    /**
     * The gml:isolated property element implements the role of the same name of the ISO 19107 “Isolated In” association (see ISO 19107:2003, 7.3.10.4).
     * 
     * Gets the value of the isolated property.
     * 
     * <p>This accessor method returns a reference to the live list,
     * not a snapshot. Therefore, any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a {@code set} method for the isolated property.</p>
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * </p>
     * <pre>
     * getIsolated().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link IsolatedPropertyType }
     * </p>
     * 
     * 
     * @return
     *     The value of the isolated property.
     */
    public List<IsolatedPropertyType> getIsolated() {
        if (isolated == null) {
            isolated = new ArrayList<>();
        }
        return this.isolated;
    }

    public boolean isSetIsolated() {
        return ((this.isolated!= null)&&(!this.isolated.isEmpty()));
    }

    public void unsetIsolated() {
        this.isolated = null;
    }

    /**
     * The gml:container property element implements the role of the same name of the ISO 19107 “Isolated In” association (see ISO 19107:2003, 7.3.10.4).
     * 
     * @return
     *     possible object is
     *     {@link ContainerPropertyType }
     *     
     */
    public ContainerPropertyType getContainer() {
        return container;
    }

    /**
     * Imposta il valore della proprietà container.
     * 
     * @param value
     *     allowed object is
     *     {@link ContainerPropertyType }
     *     
     * @see #getContainer()
     */
    public void setContainer(ContainerPropertyType value) {
        this.container = value;
    }

    public boolean isSetContainer() {
        return (this.container!= null);
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
            List<IsolatedPropertyType> theIsolated;
            theIsolated = (this.isSetIsolated()?this.getIsolated():null);
            strategy.appendField(locator, this, "isolated", buffer, theIsolated, this.isSetIsolated());
        }
        {
            ContainerPropertyType theContainer;
            theContainer = this.getContainer();
            strategy.appendField(locator, this, "container", buffer, theContainer, this.isSetContainer());
        }
        return buffer;
    }

    public void setIsolated(List<IsolatedPropertyType> value) {
        this.isolated = null;
        if (value!= null) {
            List<IsolatedPropertyType> draftl = this.getIsolated();
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
        final AbstractTopoPrimitiveType that = ((AbstractTopoPrimitiveType) object);
        {
            List<IsolatedPropertyType> lhsIsolated;
            lhsIsolated = (this.isSetIsolated()?this.getIsolated():null);
            List<IsolatedPropertyType> rhsIsolated;
            rhsIsolated = (that.isSetIsolated()?that.getIsolated():null);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "isolated", lhsIsolated), LocatorUtils.property(thatLocator, "isolated", rhsIsolated), lhsIsolated, rhsIsolated, this.isSetIsolated(), that.isSetIsolated())) {
                return false;
            }
        }
        {
            ContainerPropertyType lhsContainer;
            lhsContainer = this.getContainer();
            ContainerPropertyType rhsContainer;
            rhsContainer = that.getContainer();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "container", lhsContainer), LocatorUtils.property(thatLocator, "container", rhsContainer), lhsContainer, rhsContainer, this.isSetContainer(), that.isSetContainer())) {
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
            List<IsolatedPropertyType> theIsolated;
            theIsolated = (this.isSetIsolated()?this.getIsolated():null);
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "isolated", theIsolated), currentHashCode, theIsolated, this.isSetIsolated());
        }
        {
            ContainerPropertyType theContainer;
            theContainer = this.getContainer();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "container", theContainer), currentHashCode, theContainer, this.isSetContainer());
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
        if (null == target) {
            throw new IllegalArgumentException("Target argument must not be null for abstract copyable classes.");
        }
        super.copyTo(locator, target, strategy);
        if (target instanceof AbstractTopoPrimitiveType) {
            final AbstractTopoPrimitiveType copy = ((AbstractTopoPrimitiveType) target);
            {
                java.lang.Boolean isolatedShouldBeCopiedAndSet = strategy.shouldBeCopiedAndSet(locator, this.isSetIsolated());
                if (isolatedShouldBeCopiedAndSet == java.lang.Boolean.TRUE) {
                    List<IsolatedPropertyType> sourceIsolated;
                    sourceIsolated = (this.isSetIsolated()?this.getIsolated():null);
                    @SuppressWarnings("unchecked")
                    List<IsolatedPropertyType> copyIsolated = ((List<IsolatedPropertyType> ) strategy.copy(LocatorUtils.property(locator, "isolated", sourceIsolated), sourceIsolated, this.isSetIsolated()));
                    copy.setIsolated(copyIsolated);
                } else {
                    if (isolatedShouldBeCopiedAndSet == java.lang.Boolean.FALSE) {
                        copy.unsetIsolated();
                    }
                }
            }
            {
                java.lang.Boolean containerShouldBeCopiedAndSet = strategy.shouldBeCopiedAndSet(locator, this.isSetContainer());
                if (containerShouldBeCopiedAndSet == java.lang.Boolean.TRUE) {
                    ContainerPropertyType sourceContainer;
                    sourceContainer = this.getContainer();
                    ContainerPropertyType copyContainer = ((ContainerPropertyType) strategy.copy(LocatorUtils.property(locator, "container", sourceContainer), sourceContainer, this.isSetContainer()));
                    copy.setContainer(copyContainer);
                } else {
                    if (containerShouldBeCopiedAndSet == java.lang.Boolean.FALSE) {
                        copy.container = null;
                    }
                }
            }
        }
        return target;
    }

    @Override
    public void mergeFrom(Object left, Object right) {
        final MergeStrategy strategy = JAXBMergeStrategy.getInstance();
        mergeFrom(null, null, left, right, strategy);
    }

    @Override
    public void mergeFrom(ObjectLocator leftLocator, ObjectLocator rightLocator, Object left, Object right, MergeStrategy strategy) {
        super.mergeFrom(leftLocator, rightLocator, left, right, strategy);
        if (right instanceof AbstractTopoPrimitiveType) {
            final AbstractTopoPrimitiveType target = this;
            final AbstractTopoPrimitiveType leftObject = ((AbstractTopoPrimitiveType) left);
            final AbstractTopoPrimitiveType rightObject = ((AbstractTopoPrimitiveType) right);
            {
                java.lang.Boolean isolatedShouldBeMergedAndSet = strategy.shouldBeMergedAndSet(leftLocator, rightLocator, leftObject.isSetIsolated(), rightObject.isSetIsolated());
                if (isolatedShouldBeMergedAndSet == java.lang.Boolean.TRUE) {
                    List<IsolatedPropertyType> lhsIsolated;
                    lhsIsolated = (leftObject.isSetIsolated()?leftObject.getIsolated():null);
                    List<IsolatedPropertyType> rhsIsolated;
                    rhsIsolated = (rightObject.isSetIsolated()?rightObject.getIsolated():null);
                    List<IsolatedPropertyType> mergedIsolated = ((List<IsolatedPropertyType> ) strategy.merge(LocatorUtils.property(leftLocator, "isolated", lhsIsolated), LocatorUtils.property(rightLocator, "isolated", rhsIsolated), lhsIsolated, rhsIsolated, leftObject.isSetIsolated(), rightObject.isSetIsolated()));
                    target.setIsolated(mergedIsolated);
                } else {
                    if (isolatedShouldBeMergedAndSet == java.lang.Boolean.FALSE) {
                        target.unsetIsolated();
                    }
                }
            }
            {
                java.lang.Boolean containerShouldBeMergedAndSet = strategy.shouldBeMergedAndSet(leftLocator, rightLocator, leftObject.isSetContainer(), rightObject.isSetContainer());
                if (containerShouldBeMergedAndSet == java.lang.Boolean.TRUE) {
                    ContainerPropertyType lhsContainer;
                    lhsContainer = leftObject.getContainer();
                    ContainerPropertyType rhsContainer;
                    rhsContainer = rightObject.getContainer();
                    ContainerPropertyType mergedContainer = ((ContainerPropertyType) strategy.merge(LocatorUtils.property(leftLocator, "container", lhsContainer), LocatorUtils.property(rightLocator, "container", rhsContainer), lhsContainer, rhsContainer, leftObject.isSetContainer(), rightObject.isSetContainer()));
                    target.setContainer(mergedContainer);
                } else {
                    if (containerShouldBeMergedAndSet == java.lang.Boolean.FALSE) {
                        target.container = null;
                    }
                }
            }
        }
    }

}
