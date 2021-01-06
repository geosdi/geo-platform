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
import javax.xml.bind.annotation.XmlSeeAlso;
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
 * <p>Classe Java per AbstractTopoPrimitiveType complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="AbstractTopoPrimitiveType">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.opengis.net/gml}AbstractTopologyType">
 *       &lt;sequence>
 *         &lt;element ref="{http://www.opengis.net/gml}isolated" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element ref="{http://www.opengis.net/gml}container" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AbstractTopoPrimitiveType", propOrder = {
    "isolated",
    "container"
})
@XmlSeeAlso({
    EdgeType.class,
    NodeType.class,
    TopoSolidType.class,
    FaceType.class
})
public abstract class AbstractTopoPrimitiveType
    extends AbstractTopologyType
    implements Cloneable, CopyTo, Equals, HashCode, MergeFrom, ToString
{

    protected List<IsolatedPropertyType> isolated;
    protected ContainerPropertyType container;

    /**
     * Gets the value of the isolated property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the isolated property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getIsolated().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link IsolatedPropertyType }
     * 
     * 
     */
    public List<IsolatedPropertyType> getIsolated() {
        if (isolated == null) {
            isolated = new ArrayList<IsolatedPropertyType>();
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
     * Recupera il valore della proprietà container.
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
     */
    public void setContainer(ContainerPropertyType value) {
        this.container = value;
    }

    public boolean isSetContainer() {
        return (this.container!= null);
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
            List<IsolatedPropertyType> theIsolated;
            theIsolated = this.getIsolated();
            strategy.appendField(locator, this, "isolated", buffer, theIsolated);
        }
        {
            ContainerPropertyType theContainer;
            theContainer = this.getContainer();
            strategy.appendField(locator, this, "container", buffer, theContainer);
        }
        return buffer;
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof AbstractTopoPrimitiveType)) {
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
            lhsIsolated = this.getIsolated();
            List<IsolatedPropertyType> rhsIsolated;
            rhsIsolated = that.getIsolated();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "isolated", lhsIsolated), LocatorUtils.property(thatLocator, "isolated", rhsIsolated), lhsIsolated, rhsIsolated)) {
                return false;
            }
        }
        {
            ContainerPropertyType lhsContainer;
            lhsContainer = this.getContainer();
            ContainerPropertyType rhsContainer;
            rhsContainer = that.getContainer();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "container", lhsContainer), LocatorUtils.property(thatLocator, "container", rhsContainer), lhsContainer, rhsContainer)) {
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
            List<IsolatedPropertyType> theIsolated;
            theIsolated = this.getIsolated();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "isolated", theIsolated), currentHashCode, theIsolated);
        }
        {
            ContainerPropertyType theContainer;
            theContainer = this.getContainer();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "container", theContainer), currentHashCode, theContainer);
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
        if (target instanceof AbstractTopoPrimitiveType) {
            final AbstractTopoPrimitiveType copy = ((AbstractTopoPrimitiveType) target);
            if (this.isSetIsolated()) {
                List<IsolatedPropertyType> sourceIsolated;
                sourceIsolated = this.getIsolated();
                @SuppressWarnings("unchecked")
                List<IsolatedPropertyType> copyIsolated = ((List<IsolatedPropertyType> ) strategy.copy(LocatorUtils.property(locator, "isolated", sourceIsolated), sourceIsolated));
                copy.unsetIsolated();
                List<IsolatedPropertyType> uniqueIsolatedl = copy.getIsolated();
                uniqueIsolatedl.addAll(copyIsolated);
            } else {
                copy.unsetIsolated();
            }
            if (this.isSetContainer()) {
                ContainerPropertyType sourceContainer;
                sourceContainer = this.getContainer();
                ContainerPropertyType copyContainer = ((ContainerPropertyType) strategy.copy(LocatorUtils.property(locator, "container", sourceContainer), sourceContainer));
                copy.setContainer(copyContainer);
            } else {
                copy.container = null;
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
        if (right instanceof AbstractTopoPrimitiveType) {
            final AbstractTopoPrimitiveType target = this;
            final AbstractTopoPrimitiveType leftObject = ((AbstractTopoPrimitiveType) left);
            final AbstractTopoPrimitiveType rightObject = ((AbstractTopoPrimitiveType) right);
            {
                List<IsolatedPropertyType> lhsIsolated;
                lhsIsolated = leftObject.getIsolated();
                List<IsolatedPropertyType> rhsIsolated;
                rhsIsolated = rightObject.getIsolated();
                target.unsetIsolated();
                List<IsolatedPropertyType> uniqueIsolatedl = target.getIsolated();
                uniqueIsolatedl.addAll(((List<IsolatedPropertyType> ) strategy.merge(LocatorUtils.property(leftLocator, "isolated", lhsIsolated), LocatorUtils.property(rightLocator, "isolated", rhsIsolated), lhsIsolated, rhsIsolated)));
            }
            {
                ContainerPropertyType lhsContainer;
                lhsContainer = leftObject.getContainer();
                ContainerPropertyType rhsContainer;
                rhsContainer = rightObject.getContainer();
                target.setContainer(((ContainerPropertyType) strategy.merge(LocatorUtils.property(leftLocator, "container", lhsContainer), LocatorUtils.property(rightLocator, "container", rhsContainer), lhsContainer, rhsContainer)));
            }
        }
    }

    public void setIsolated(List<IsolatedPropertyType> value) {
        List<IsolatedPropertyType> draftl = this.getIsolated();
        draftl.addAll(value);
    }

    public AbstractTopoPrimitiveType withIsolated(IsolatedPropertyType... values) {
        if (values!= null) {
            for (IsolatedPropertyType value: values) {
                getIsolated().add(value);
            }
        }
        return this;
    }

    public AbstractTopoPrimitiveType withIsolated(Collection<IsolatedPropertyType> values) {
        if (values!= null) {
            getIsolated().addAll(values);
        }
        return this;
    }

    public AbstractTopoPrimitiveType withContainer(ContainerPropertyType value) {
        setContainer(value);
        return this;
    }

    public AbstractTopoPrimitiveType withIsolated(List<IsolatedPropertyType> value) {
        setIsolated(value);
        return this;
    }

    @Override
    public AbstractTopoPrimitiveType withMetaDataProperty(MetaDataPropertyType... values) {
        if (values!= null) {
            for (MetaDataPropertyType value: values) {
                getMetaDataProperty().add(value);
            }
        }
        return this;
    }

    @Override
    public AbstractTopoPrimitiveType withMetaDataProperty(Collection<MetaDataPropertyType> values) {
        if (values!= null) {
            getMetaDataProperty().addAll(values);
        }
        return this;
    }

    @Override
    public AbstractTopoPrimitiveType withDescription(StringOrRefType value) {
        setDescription(value);
        return this;
    }

    @Override
    public AbstractTopoPrimitiveType withDescriptionReference(ReferenceType value) {
        setDescriptionReference(value);
        return this;
    }

    @Override
    public AbstractTopoPrimitiveType withIdentifier(CodeWithAuthorityType value) {
        setIdentifier(value);
        return this;
    }

    @Override
    public AbstractTopoPrimitiveType withName(CodeType... values) {
        if (values!= null) {
            for (CodeType value: values) {
                getName().add(value);
            }
        }
        return this;
    }

    @Override
    public AbstractTopoPrimitiveType withName(Collection<CodeType> values) {
        if (values!= null) {
            getName().addAll(values);
        }
        return this;
    }

    @Override
    public AbstractTopoPrimitiveType withId(String value) {
        setId(value);
        return this;
    }

    @Override
    public AbstractTopoPrimitiveType withMetaDataProperty(List<MetaDataPropertyType> value) {
        setMetaDataProperty(value);
        return this;
    }

    @Override
    public AbstractTopoPrimitiveType withName(List<CodeType> value) {
        setName(value);
        return this;
    }

}
