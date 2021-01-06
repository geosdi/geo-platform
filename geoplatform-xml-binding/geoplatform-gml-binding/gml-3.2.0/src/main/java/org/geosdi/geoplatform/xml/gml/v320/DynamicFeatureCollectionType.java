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

import java.util.Collection;
import java.util.List;
import javax.xml.bind.JAXBElement;
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
 * <p>Classe Java per DynamicFeatureCollectionType complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="DynamicFeatureCollectionType">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.opengis.net/gml}DynamicFeatureType">
 *       &lt;sequence>
 *         &lt;element ref="{http://www.opengis.net/gml}dynamicMembers"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DynamicFeatureCollectionType", propOrder = {
    "dynamicMembers"
})
public class DynamicFeatureCollectionType
    extends DynamicFeatureType
    implements Cloneable, CopyTo, Equals, HashCode, MergeFrom, ToString
{

    @XmlElement(required = true)
    protected DynamicFeatureMemberType dynamicMembers;

    /**
     * Recupera il valore della proprietà dynamicMembers.
     * 
     * @return
     *     possible object is
     *     {@link DynamicFeatureMemberType }
     *     
     */
    public DynamicFeatureMemberType getDynamicMembers() {
        return dynamicMembers;
    }

    /**
     * Imposta il valore della proprietà dynamicMembers.
     * 
     * @param value
     *     allowed object is
     *     {@link DynamicFeatureMemberType }
     *     
     */
    public void setDynamicMembers(DynamicFeatureMemberType value) {
        this.dynamicMembers = value;
    }

    public boolean isSetDynamicMembers() {
        return (this.dynamicMembers!= null);
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
            DynamicFeatureMemberType theDynamicMembers;
            theDynamicMembers = this.getDynamicMembers();
            strategy.appendField(locator, this, "dynamicMembers", buffer, theDynamicMembers);
        }
        return buffer;
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof DynamicFeatureCollectionType)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        if (!super.equals(thisLocator, thatLocator, object, strategy)) {
            return false;
        }
        final DynamicFeatureCollectionType that = ((DynamicFeatureCollectionType) object);
        {
            DynamicFeatureMemberType lhsDynamicMembers;
            lhsDynamicMembers = this.getDynamicMembers();
            DynamicFeatureMemberType rhsDynamicMembers;
            rhsDynamicMembers = that.getDynamicMembers();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "dynamicMembers", lhsDynamicMembers), LocatorUtils.property(thatLocator, "dynamicMembers", rhsDynamicMembers), lhsDynamicMembers, rhsDynamicMembers)) {
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
            DynamicFeatureMemberType theDynamicMembers;
            theDynamicMembers = this.getDynamicMembers();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "dynamicMembers", theDynamicMembers), currentHashCode, theDynamicMembers);
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
        if (draftCopy instanceof DynamicFeatureCollectionType) {
            final DynamicFeatureCollectionType copy = ((DynamicFeatureCollectionType) draftCopy);
            if (this.isSetDynamicMembers()) {
                DynamicFeatureMemberType sourceDynamicMembers;
                sourceDynamicMembers = this.getDynamicMembers();
                DynamicFeatureMemberType copyDynamicMembers = ((DynamicFeatureMemberType) strategy.copy(LocatorUtils.property(locator, "dynamicMembers", sourceDynamicMembers), sourceDynamicMembers));
                copy.setDynamicMembers(copyDynamicMembers);
            } else {
                copy.dynamicMembers = null;
            }
        }
        return draftCopy;
    }

    public Object createNewInstance() {
        return new DynamicFeatureCollectionType();
    }

    public void mergeFrom(Object left, Object right) {
        final MergeStrategy strategy = JAXBMergeStrategy.INSTANCE;
        mergeFrom(null, null, left, right, strategy);
    }

    public void mergeFrom(ObjectLocator leftLocator, ObjectLocator rightLocator, Object left, Object right, MergeStrategy strategy) {
        super.mergeFrom(leftLocator, rightLocator, left, right, strategy);
        if (right instanceof DynamicFeatureCollectionType) {
            final DynamicFeatureCollectionType target = this;
            final DynamicFeatureCollectionType leftObject = ((DynamicFeatureCollectionType) left);
            final DynamicFeatureCollectionType rightObject = ((DynamicFeatureCollectionType) right);
            {
                DynamicFeatureMemberType lhsDynamicMembers;
                lhsDynamicMembers = leftObject.getDynamicMembers();
                DynamicFeatureMemberType rhsDynamicMembers;
                rhsDynamicMembers = rightObject.getDynamicMembers();
                target.setDynamicMembers(((DynamicFeatureMemberType) strategy.merge(LocatorUtils.property(leftLocator, "dynamicMembers", lhsDynamicMembers), LocatorUtils.property(rightLocator, "dynamicMembers", rhsDynamicMembers), lhsDynamicMembers, rhsDynamicMembers)));
            }
        }
    }

    public DynamicFeatureCollectionType withDynamicMembers(DynamicFeatureMemberType value) {
        setDynamicMembers(value);
        return this;
    }

    @Override
    public DynamicFeatureCollectionType withValidTime(TimePrimitivePropertyType value) {
        setValidTime(value);
        return this;
    }

    @Override
    public DynamicFeatureCollectionType withHistory(JAXBElement<HistoryPropertyType> value) {
        setHistory(value);
        return this;
    }

    @Override
    public DynamicFeatureCollectionType withDataSource(StringOrRefType value) {
        setDataSource(value);
        return this;
    }

    @Override
    public DynamicFeatureCollectionType withDataSourceReference(ReferenceType value) {
        setDataSourceReference(value);
        return this;
    }

    @Override
    public DynamicFeatureCollectionType withBoundedBy(BoundingShapeType value) {
        setBoundedBy(value);
        return this;
    }

    @Override
    public DynamicFeatureCollectionType withLocation(JAXBElement<? extends LocationPropertyType> value) {
        setLocation(value);
        return this;
    }

    @Override
    public DynamicFeatureCollectionType withMetaDataProperty(MetaDataPropertyType... values) {
        if (values!= null) {
            for (MetaDataPropertyType value: values) {
                getMetaDataProperty().add(value);
            }
        }
        return this;
    }

    @Override
    public DynamicFeatureCollectionType withMetaDataProperty(Collection<MetaDataPropertyType> values) {
        if (values!= null) {
            getMetaDataProperty().addAll(values);
        }
        return this;
    }

    @Override
    public DynamicFeatureCollectionType withDescription(StringOrRefType value) {
        setDescription(value);
        return this;
    }

    @Override
    public DynamicFeatureCollectionType withDescriptionReference(ReferenceType value) {
        setDescriptionReference(value);
        return this;
    }

    @Override
    public DynamicFeatureCollectionType withIdentifier(CodeWithAuthorityType value) {
        setIdentifier(value);
        return this;
    }

    @Override
    public DynamicFeatureCollectionType withName(CodeType... values) {
        if (values!= null) {
            for (CodeType value: values) {
                getName().add(value);
            }
        }
        return this;
    }

    @Override
    public DynamicFeatureCollectionType withName(Collection<CodeType> values) {
        if (values!= null) {
            getName().addAll(values);
        }
        return this;
    }

    @Override
    public DynamicFeatureCollectionType withId(String value) {
        setId(value);
        return this;
    }

    @Override
    public DynamicFeatureCollectionType withMetaDataProperty(List<MetaDataPropertyType> value) {
        setMetaDataProperty(value);
        return this;
    }

    @Override
    public DynamicFeatureCollectionType withName(List<CodeType> value) {
        setName(value);
        return this;
    }

}
