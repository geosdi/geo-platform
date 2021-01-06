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
import javax.xml.bind.annotation.XmlElementRef;
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
 * <p>Classe Java per DynamicFeatureType complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="DynamicFeatureType">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.opengis.net/gml}AbstractFeatureType">
 *       &lt;group ref="{http://www.opengis.net/gml}dynamicProperties"/>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DynamicFeatureType", propOrder = {
    "validTime",
    "history",
    "dataSource",
    "dataSourceReference"
})
@XmlSeeAlso({
    DynamicFeatureCollectionType.class
})
public class DynamicFeatureType
    extends AbstractFeatureType
    implements Cloneable, CopyTo, Equals, HashCode, MergeFrom, ToString
{

    protected TimePrimitivePropertyType validTime;
    @XmlElementRef(name = "history", namespace = "http://www.opengis.net/gml", type = JAXBElement.class, required = false)
    protected JAXBElement<HistoryPropertyType> history;
    protected StringOrRefType dataSource;
    protected ReferenceType dataSourceReference;

    /**
     * Recupera il valore della proprietà validTime.
     * 
     * @return
     *     possible object is
     *     {@link TimePrimitivePropertyType }
     *     
     */
    public TimePrimitivePropertyType getValidTime() {
        return validTime;
    }

    /**
     * Imposta il valore della proprietà validTime.
     * 
     * @param value
     *     allowed object is
     *     {@link TimePrimitivePropertyType }
     *     
     */
    public void setValidTime(TimePrimitivePropertyType value) {
        this.validTime = value;
    }

    public boolean isSetValidTime() {
        return (this.validTime!= null);
    }

    /**
     * Recupera il valore della proprietà history.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link HistoryPropertyType }{@code >}
     *     {@link JAXBElement }{@code <}{@link HistoryPropertyType }{@code >}
     *     
     */
    public JAXBElement<HistoryPropertyType> getHistory() {
        return history;
    }

    /**
     * Imposta il valore della proprietà history.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link HistoryPropertyType }{@code >}
     *     {@link JAXBElement }{@code <}{@link HistoryPropertyType }{@code >}
     *     
     */
    public void setHistory(JAXBElement<HistoryPropertyType> value) {
        this.history = value;
    }

    public boolean isSetHistory() {
        return (this.history!= null);
    }

    /**
     * Recupera il valore della proprietà dataSource.
     * 
     * @return
     *     possible object is
     *     {@link StringOrRefType }
     *     
     */
    public StringOrRefType getDataSource() {
        return dataSource;
    }

    /**
     * Imposta il valore della proprietà dataSource.
     * 
     * @param value
     *     allowed object is
     *     {@link StringOrRefType }
     *     
     */
    public void setDataSource(StringOrRefType value) {
        this.dataSource = value;
    }

    public boolean isSetDataSource() {
        return (this.dataSource!= null);
    }

    /**
     * Recupera il valore della proprietà dataSourceReference.
     * 
     * @return
     *     possible object is
     *     {@link ReferenceType }
     *     
     */
    public ReferenceType getDataSourceReference() {
        return dataSourceReference;
    }

    /**
     * Imposta il valore della proprietà dataSourceReference.
     * 
     * @param value
     *     allowed object is
     *     {@link ReferenceType }
     *     
     */
    public void setDataSourceReference(ReferenceType value) {
        this.dataSourceReference = value;
    }

    public boolean isSetDataSourceReference() {
        return (this.dataSourceReference!= null);
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
            TimePrimitivePropertyType theValidTime;
            theValidTime = this.getValidTime();
            strategy.appendField(locator, this, "validTime", buffer, theValidTime);
        }
        {
            JAXBElement<HistoryPropertyType> theHistory;
            theHistory = this.getHistory();
            strategy.appendField(locator, this, "history", buffer, theHistory);
        }
        {
            StringOrRefType theDataSource;
            theDataSource = this.getDataSource();
            strategy.appendField(locator, this, "dataSource", buffer, theDataSource);
        }
        {
            ReferenceType theDataSourceReference;
            theDataSourceReference = this.getDataSourceReference();
            strategy.appendField(locator, this, "dataSourceReference", buffer, theDataSourceReference);
        }
        return buffer;
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof DynamicFeatureType)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        if (!super.equals(thisLocator, thatLocator, object, strategy)) {
            return false;
        }
        final DynamicFeatureType that = ((DynamicFeatureType) object);
        {
            TimePrimitivePropertyType lhsValidTime;
            lhsValidTime = this.getValidTime();
            TimePrimitivePropertyType rhsValidTime;
            rhsValidTime = that.getValidTime();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "validTime", lhsValidTime), LocatorUtils.property(thatLocator, "validTime", rhsValidTime), lhsValidTime, rhsValidTime)) {
                return false;
            }
        }
        {
            JAXBElement<HistoryPropertyType> lhsHistory;
            lhsHistory = this.getHistory();
            JAXBElement<HistoryPropertyType> rhsHistory;
            rhsHistory = that.getHistory();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "history", lhsHistory), LocatorUtils.property(thatLocator, "history", rhsHistory), lhsHistory, rhsHistory)) {
                return false;
            }
        }
        {
            StringOrRefType lhsDataSource;
            lhsDataSource = this.getDataSource();
            StringOrRefType rhsDataSource;
            rhsDataSource = that.getDataSource();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "dataSource", lhsDataSource), LocatorUtils.property(thatLocator, "dataSource", rhsDataSource), lhsDataSource, rhsDataSource)) {
                return false;
            }
        }
        {
            ReferenceType lhsDataSourceReference;
            lhsDataSourceReference = this.getDataSourceReference();
            ReferenceType rhsDataSourceReference;
            rhsDataSourceReference = that.getDataSourceReference();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "dataSourceReference", lhsDataSourceReference), LocatorUtils.property(thatLocator, "dataSourceReference", rhsDataSourceReference), lhsDataSourceReference, rhsDataSourceReference)) {
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
            TimePrimitivePropertyType theValidTime;
            theValidTime = this.getValidTime();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "validTime", theValidTime), currentHashCode, theValidTime);
        }
        {
            JAXBElement<HistoryPropertyType> theHistory;
            theHistory = this.getHistory();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "history", theHistory), currentHashCode, theHistory);
        }
        {
            StringOrRefType theDataSource;
            theDataSource = this.getDataSource();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "dataSource", theDataSource), currentHashCode, theDataSource);
        }
        {
            ReferenceType theDataSourceReference;
            theDataSourceReference = this.getDataSourceReference();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "dataSourceReference", theDataSourceReference), currentHashCode, theDataSourceReference);
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
        if (draftCopy instanceof DynamicFeatureType) {
            final DynamicFeatureType copy = ((DynamicFeatureType) draftCopy);
            if (this.isSetValidTime()) {
                TimePrimitivePropertyType sourceValidTime;
                sourceValidTime = this.getValidTime();
                TimePrimitivePropertyType copyValidTime = ((TimePrimitivePropertyType) strategy.copy(LocatorUtils.property(locator, "validTime", sourceValidTime), sourceValidTime));
                copy.setValidTime(copyValidTime);
            } else {
                copy.validTime = null;
            }
            if (this.isSetHistory()) {
                JAXBElement<HistoryPropertyType> sourceHistory;
                sourceHistory = this.getHistory();
                @SuppressWarnings("unchecked")
                JAXBElement<HistoryPropertyType> copyHistory = ((JAXBElement<HistoryPropertyType> ) strategy.copy(LocatorUtils.property(locator, "history", sourceHistory), sourceHistory));
                copy.setHistory(copyHistory);
            } else {
                copy.history = null;
            }
            if (this.isSetDataSource()) {
                StringOrRefType sourceDataSource;
                sourceDataSource = this.getDataSource();
                StringOrRefType copyDataSource = ((StringOrRefType) strategy.copy(LocatorUtils.property(locator, "dataSource", sourceDataSource), sourceDataSource));
                copy.setDataSource(copyDataSource);
            } else {
                copy.dataSource = null;
            }
            if (this.isSetDataSourceReference()) {
                ReferenceType sourceDataSourceReference;
                sourceDataSourceReference = this.getDataSourceReference();
                ReferenceType copyDataSourceReference = ((ReferenceType) strategy.copy(LocatorUtils.property(locator, "dataSourceReference", sourceDataSourceReference), sourceDataSourceReference));
                copy.setDataSourceReference(copyDataSourceReference);
            } else {
                copy.dataSourceReference = null;
            }
        }
        return draftCopy;
    }

    public Object createNewInstance() {
        return new DynamicFeatureType();
    }

    public void mergeFrom(Object left, Object right) {
        final MergeStrategy strategy = JAXBMergeStrategy.INSTANCE;
        mergeFrom(null, null, left, right, strategy);
    }

    public void mergeFrom(ObjectLocator leftLocator, ObjectLocator rightLocator, Object left, Object right, MergeStrategy strategy) {
        super.mergeFrom(leftLocator, rightLocator, left, right, strategy);
        if (right instanceof DynamicFeatureType) {
            final DynamicFeatureType target = this;
            final DynamicFeatureType leftObject = ((DynamicFeatureType) left);
            final DynamicFeatureType rightObject = ((DynamicFeatureType) right);
            {
                TimePrimitivePropertyType lhsValidTime;
                lhsValidTime = leftObject.getValidTime();
                TimePrimitivePropertyType rhsValidTime;
                rhsValidTime = rightObject.getValidTime();
                target.setValidTime(((TimePrimitivePropertyType) strategy.merge(LocatorUtils.property(leftLocator, "validTime", lhsValidTime), LocatorUtils.property(rightLocator, "validTime", rhsValidTime), lhsValidTime, rhsValidTime)));
            }
            {
                JAXBElement<HistoryPropertyType> lhsHistory;
                lhsHistory = leftObject.getHistory();
                JAXBElement<HistoryPropertyType> rhsHistory;
                rhsHistory = rightObject.getHistory();
                target.setHistory(((JAXBElement<HistoryPropertyType> ) strategy.merge(LocatorUtils.property(leftLocator, "history", lhsHistory), LocatorUtils.property(rightLocator, "history", rhsHistory), lhsHistory, rhsHistory)));
            }
            {
                StringOrRefType lhsDataSource;
                lhsDataSource = leftObject.getDataSource();
                StringOrRefType rhsDataSource;
                rhsDataSource = rightObject.getDataSource();
                target.setDataSource(((StringOrRefType) strategy.merge(LocatorUtils.property(leftLocator, "dataSource", lhsDataSource), LocatorUtils.property(rightLocator, "dataSource", rhsDataSource), lhsDataSource, rhsDataSource)));
            }
            {
                ReferenceType lhsDataSourceReference;
                lhsDataSourceReference = leftObject.getDataSourceReference();
                ReferenceType rhsDataSourceReference;
                rhsDataSourceReference = rightObject.getDataSourceReference();
                target.setDataSourceReference(((ReferenceType) strategy.merge(LocatorUtils.property(leftLocator, "dataSourceReference", lhsDataSourceReference), LocatorUtils.property(rightLocator, "dataSourceReference", rhsDataSourceReference), lhsDataSourceReference, rhsDataSourceReference)));
            }
        }
    }

    public DynamicFeatureType withValidTime(TimePrimitivePropertyType value) {
        setValidTime(value);
        return this;
    }

    public DynamicFeatureType withHistory(JAXBElement<HistoryPropertyType> value) {
        setHistory(value);
        return this;
    }

    public DynamicFeatureType withDataSource(StringOrRefType value) {
        setDataSource(value);
        return this;
    }

    public DynamicFeatureType withDataSourceReference(ReferenceType value) {
        setDataSourceReference(value);
        return this;
    }

    @Override
    public DynamicFeatureType withBoundedBy(BoundingShapeType value) {
        setBoundedBy(value);
        return this;
    }

    @Override
    public DynamicFeatureType withLocation(JAXBElement<? extends LocationPropertyType> value) {
        setLocation(value);
        return this;
    }

    @Override
    public DynamicFeatureType withMetaDataProperty(MetaDataPropertyType... values) {
        if (values!= null) {
            for (MetaDataPropertyType value: values) {
                getMetaDataProperty().add(value);
            }
        }
        return this;
    }

    @Override
    public DynamicFeatureType withMetaDataProperty(Collection<MetaDataPropertyType> values) {
        if (values!= null) {
            getMetaDataProperty().addAll(values);
        }
        return this;
    }

    @Override
    public DynamicFeatureType withDescription(StringOrRefType value) {
        setDescription(value);
        return this;
    }

    @Override
    public DynamicFeatureType withDescriptionReference(ReferenceType value) {
        setDescriptionReference(value);
        return this;
    }

    @Override
    public DynamicFeatureType withIdentifier(CodeWithAuthorityType value) {
        setIdentifier(value);
        return this;
    }

    @Override
    public DynamicFeatureType withName(CodeType... values) {
        if (values!= null) {
            for (CodeType value: values) {
                getName().add(value);
            }
        }
        return this;
    }

    @Override
    public DynamicFeatureType withName(Collection<CodeType> values) {
        if (values!= null) {
            getName().addAll(values);
        }
        return this;
    }

    @Override
    public DynamicFeatureType withId(String value) {
        setId(value);
        return this;
    }

    @Override
    public DynamicFeatureType withMetaDataProperty(List<MetaDataPropertyType> value) {
        setMetaDataProperty(value);
        return this;
    }

    @Override
    public DynamicFeatureType withName(List<CodeType> value) {
        setName(value);
        return this;
    }

}
