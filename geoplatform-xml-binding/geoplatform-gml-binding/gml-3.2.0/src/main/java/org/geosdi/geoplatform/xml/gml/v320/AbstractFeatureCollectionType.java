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
import javax.xml.bind.JAXBElement;
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
 * <p>Classe Java per AbstractFeatureCollectionType complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="AbstractFeatureCollectionType">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.opengis.net/gml}AbstractFeatureType">
 *       &lt;sequence>
 *         &lt;element ref="{http://www.opengis.net/gml}featureMember" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element ref="{http://www.opengis.net/gml}featureMembers" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AbstractFeatureCollectionType", propOrder = {
    "featureMember",
    "featureMembers"
})
@XmlSeeAlso({
    FeatureCollectionType.class
})
public abstract class AbstractFeatureCollectionType
    extends AbstractFeatureType
    implements Cloneable, CopyTo, Equals, HashCode, MergeFrom, ToString
{

    protected List<FeaturePropertyType> featureMember;
    protected FeatureArrayPropertyType featureMembers;

    /**
     * Gets the value of the featureMember property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the featureMember property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getFeatureMember().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link FeaturePropertyType }
     * 
     * 
     */
    public List<FeaturePropertyType> getFeatureMember() {
        if (featureMember == null) {
            featureMember = new ArrayList<FeaturePropertyType>();
        }
        return this.featureMember;
    }

    public boolean isSetFeatureMember() {
        return ((this.featureMember!= null)&&(!this.featureMember.isEmpty()));
    }

    public void unsetFeatureMember() {
        this.featureMember = null;
    }

    /**
     * Recupera il valore della proprietà featureMembers.
     * 
     * @return
     *     possible object is
     *     {@link FeatureArrayPropertyType }
     *     
     */
    public FeatureArrayPropertyType getFeatureMembers() {
        return featureMembers;
    }

    /**
     * Imposta il valore della proprietà featureMembers.
     * 
     * @param value
     *     allowed object is
     *     {@link FeatureArrayPropertyType }
     *     
     */
    public void setFeatureMembers(FeatureArrayPropertyType value) {
        this.featureMembers = value;
    }

    public boolean isSetFeatureMembers() {
        return (this.featureMembers!= null);
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
            List<FeaturePropertyType> theFeatureMember;
            theFeatureMember = this.getFeatureMember();
            strategy.appendField(locator, this, "featureMember", buffer, theFeatureMember);
        }
        {
            FeatureArrayPropertyType theFeatureMembers;
            theFeatureMembers = this.getFeatureMembers();
            strategy.appendField(locator, this, "featureMembers", buffer, theFeatureMembers);
        }
        return buffer;
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof AbstractFeatureCollectionType)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        if (!super.equals(thisLocator, thatLocator, object, strategy)) {
            return false;
        }
        final AbstractFeatureCollectionType that = ((AbstractFeatureCollectionType) object);
        {
            List<FeaturePropertyType> lhsFeatureMember;
            lhsFeatureMember = this.getFeatureMember();
            List<FeaturePropertyType> rhsFeatureMember;
            rhsFeatureMember = that.getFeatureMember();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "featureMember", lhsFeatureMember), LocatorUtils.property(thatLocator, "featureMember", rhsFeatureMember), lhsFeatureMember, rhsFeatureMember)) {
                return false;
            }
        }
        {
            FeatureArrayPropertyType lhsFeatureMembers;
            lhsFeatureMembers = this.getFeatureMembers();
            FeatureArrayPropertyType rhsFeatureMembers;
            rhsFeatureMembers = that.getFeatureMembers();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "featureMembers", lhsFeatureMembers), LocatorUtils.property(thatLocator, "featureMembers", rhsFeatureMembers), lhsFeatureMembers, rhsFeatureMembers)) {
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
            List<FeaturePropertyType> theFeatureMember;
            theFeatureMember = this.getFeatureMember();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "featureMember", theFeatureMember), currentHashCode, theFeatureMember);
        }
        {
            FeatureArrayPropertyType theFeatureMembers;
            theFeatureMembers = this.getFeatureMembers();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "featureMembers", theFeatureMembers), currentHashCode, theFeatureMembers);
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
        if (target instanceof AbstractFeatureCollectionType) {
            final AbstractFeatureCollectionType copy = ((AbstractFeatureCollectionType) target);
            if (this.isSetFeatureMember()) {
                List<FeaturePropertyType> sourceFeatureMember;
                sourceFeatureMember = this.getFeatureMember();
                @SuppressWarnings("unchecked")
                List<FeaturePropertyType> copyFeatureMember = ((List<FeaturePropertyType> ) strategy.copy(LocatorUtils.property(locator, "featureMember", sourceFeatureMember), sourceFeatureMember));
                copy.unsetFeatureMember();
                List<FeaturePropertyType> uniqueFeatureMemberl = copy.getFeatureMember();
                uniqueFeatureMemberl.addAll(copyFeatureMember);
            } else {
                copy.unsetFeatureMember();
            }
            if (this.isSetFeatureMembers()) {
                FeatureArrayPropertyType sourceFeatureMembers;
                sourceFeatureMembers = this.getFeatureMembers();
                FeatureArrayPropertyType copyFeatureMembers = ((FeatureArrayPropertyType) strategy.copy(LocatorUtils.property(locator, "featureMembers", sourceFeatureMembers), sourceFeatureMembers));
                copy.setFeatureMembers(copyFeatureMembers);
            } else {
                copy.featureMembers = null;
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
        if (right instanceof AbstractFeatureCollectionType) {
            final AbstractFeatureCollectionType target = this;
            final AbstractFeatureCollectionType leftObject = ((AbstractFeatureCollectionType) left);
            final AbstractFeatureCollectionType rightObject = ((AbstractFeatureCollectionType) right);
            {
                List<FeaturePropertyType> lhsFeatureMember;
                lhsFeatureMember = leftObject.getFeatureMember();
                List<FeaturePropertyType> rhsFeatureMember;
                rhsFeatureMember = rightObject.getFeatureMember();
                target.unsetFeatureMember();
                List<FeaturePropertyType> uniqueFeatureMemberl = target.getFeatureMember();
                uniqueFeatureMemberl.addAll(((List<FeaturePropertyType> ) strategy.merge(LocatorUtils.property(leftLocator, "featureMember", lhsFeatureMember), LocatorUtils.property(rightLocator, "featureMember", rhsFeatureMember), lhsFeatureMember, rhsFeatureMember)));
            }
            {
                FeatureArrayPropertyType lhsFeatureMembers;
                lhsFeatureMembers = leftObject.getFeatureMembers();
                FeatureArrayPropertyType rhsFeatureMembers;
                rhsFeatureMembers = rightObject.getFeatureMembers();
                target.setFeatureMembers(((FeatureArrayPropertyType) strategy.merge(LocatorUtils.property(leftLocator, "featureMembers", lhsFeatureMembers), LocatorUtils.property(rightLocator, "featureMembers", rhsFeatureMembers), lhsFeatureMembers, rhsFeatureMembers)));
            }
        }
    }

    public void setFeatureMember(List<FeaturePropertyType> value) {
        List<FeaturePropertyType> draftl = this.getFeatureMember();
        draftl.addAll(value);
    }

    public AbstractFeatureCollectionType withFeatureMember(FeaturePropertyType... values) {
        if (values!= null) {
            for (FeaturePropertyType value: values) {
                getFeatureMember().add(value);
            }
        }
        return this;
    }

    public AbstractFeatureCollectionType withFeatureMember(Collection<FeaturePropertyType> values) {
        if (values!= null) {
            getFeatureMember().addAll(values);
        }
        return this;
    }

    public AbstractFeatureCollectionType withFeatureMembers(FeatureArrayPropertyType value) {
        setFeatureMembers(value);
        return this;
    }

    public AbstractFeatureCollectionType withFeatureMember(List<FeaturePropertyType> value) {
        setFeatureMember(value);
        return this;
    }

    @Override
    public AbstractFeatureCollectionType withBoundedBy(BoundingShapeType value) {
        setBoundedBy(value);
        return this;
    }

    @Override
    public AbstractFeatureCollectionType withLocation(JAXBElement<? extends LocationPropertyType> value) {
        setLocation(value);
        return this;
    }

    @Override
    public AbstractFeatureCollectionType withMetaDataProperty(MetaDataPropertyType... values) {
        if (values!= null) {
            for (MetaDataPropertyType value: values) {
                getMetaDataProperty().add(value);
            }
        }
        return this;
    }

    @Override
    public AbstractFeatureCollectionType withMetaDataProperty(Collection<MetaDataPropertyType> values) {
        if (values!= null) {
            getMetaDataProperty().addAll(values);
        }
        return this;
    }

    @Override
    public AbstractFeatureCollectionType withDescription(StringOrRefType value) {
        setDescription(value);
        return this;
    }

    @Override
    public AbstractFeatureCollectionType withDescriptionReference(ReferenceType value) {
        setDescriptionReference(value);
        return this;
    }

    @Override
    public AbstractFeatureCollectionType withIdentifier(CodeWithAuthorityType value) {
        setIdentifier(value);
        return this;
    }

    @Override
    public AbstractFeatureCollectionType withName(CodeType... values) {
        if (values!= null) {
            for (CodeType value: values) {
                getName().add(value);
            }
        }
        return this;
    }

    @Override
    public AbstractFeatureCollectionType withName(Collection<CodeType> values) {
        if (values!= null) {
            getName().addAll(values);
        }
        return this;
    }

    @Override
    public AbstractFeatureCollectionType withId(String value) {
        setId(value);
        return this;
    }

    @Override
    public AbstractFeatureCollectionType withMetaDataProperty(List<MetaDataPropertyType> value) {
        setMetaDataProperty(value);
        return this;
    }

    @Override
    public AbstractFeatureCollectionType withName(List<CodeType> value) {
        setName(value);
        return this;
    }

}
