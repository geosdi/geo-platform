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

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
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
 * <p>Classe Java per MultiPointType complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="MultiPointType">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.opengis.net/gml}AbstractGeometricAggregateType">
 *       &lt;sequence>
 *         &lt;element ref="{http://www.opengis.net/gml}pointMember" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element ref="{http://www.opengis.net/gml}pointMembers" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "MultiPointType", propOrder = {
    "pointMember",
    "pointMembers"
})
public class MultiPointType
    extends AbstractGeometricAggregateType
    implements Cloneable, CopyTo, Equals, HashCode, MergeFrom, ToString
{

    protected List<PointPropertyType> pointMember;
    protected PointArrayPropertyType pointMembers;

    /**
     * Gets the value of the pointMember property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the pointMember property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPointMember().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link PointPropertyType }
     * 
     * 
     */
    public List<PointPropertyType> getPointMember() {
        if (pointMember == null) {
            pointMember = new ArrayList<PointPropertyType>();
        }
        return this.pointMember;
    }

    public boolean isSetPointMember() {
        return ((this.pointMember!= null)&&(!this.pointMember.isEmpty()));
    }

    public void unsetPointMember() {
        this.pointMember = null;
    }

    /**
     * Recupera il valore della proprietà pointMembers.
     * 
     * @return
     *     possible object is
     *     {@link PointArrayPropertyType }
     *     
     */
    public PointArrayPropertyType getPointMembers() {
        return pointMembers;
    }

    /**
     * Imposta il valore della proprietà pointMembers.
     * 
     * @param value
     *     allowed object is
     *     {@link PointArrayPropertyType }
     *     
     */
    public void setPointMembers(PointArrayPropertyType value) {
        this.pointMembers = value;
    }

    public boolean isSetPointMembers() {
        return (this.pointMembers!= null);
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
            List<PointPropertyType> thePointMember;
            thePointMember = this.getPointMember();
            strategy.appendField(locator, this, "pointMember", buffer, thePointMember);
        }
        {
            PointArrayPropertyType thePointMembers;
            thePointMembers = this.getPointMembers();
            strategy.appendField(locator, this, "pointMembers", buffer, thePointMembers);
        }
        return buffer;
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof MultiPointType)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        if (!super.equals(thisLocator, thatLocator, object, strategy)) {
            return false;
        }
        final MultiPointType that = ((MultiPointType) object);
        {
            List<PointPropertyType> lhsPointMember;
            lhsPointMember = this.getPointMember();
            List<PointPropertyType> rhsPointMember;
            rhsPointMember = that.getPointMember();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "pointMember", lhsPointMember), LocatorUtils.property(thatLocator, "pointMember", rhsPointMember), lhsPointMember, rhsPointMember)) {
                return false;
            }
        }
        {
            PointArrayPropertyType lhsPointMembers;
            lhsPointMembers = this.getPointMembers();
            PointArrayPropertyType rhsPointMembers;
            rhsPointMembers = that.getPointMembers();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "pointMembers", lhsPointMembers), LocatorUtils.property(thatLocator, "pointMembers", rhsPointMembers), lhsPointMembers, rhsPointMembers)) {
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
            List<PointPropertyType> thePointMember;
            thePointMember = this.getPointMember();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "pointMember", thePointMember), currentHashCode, thePointMember);
        }
        {
            PointArrayPropertyType thePointMembers;
            thePointMembers = this.getPointMembers();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "pointMembers", thePointMembers), currentHashCode, thePointMembers);
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
        if (draftCopy instanceof MultiPointType) {
            final MultiPointType copy = ((MultiPointType) draftCopy);
            if (this.isSetPointMember()) {
                List<PointPropertyType> sourcePointMember;
                sourcePointMember = this.getPointMember();
                @SuppressWarnings("unchecked")
                List<PointPropertyType> copyPointMember = ((List<PointPropertyType> ) strategy.copy(LocatorUtils.property(locator, "pointMember", sourcePointMember), sourcePointMember));
                copy.unsetPointMember();
                List<PointPropertyType> uniquePointMemberl = copy.getPointMember();
                uniquePointMemberl.addAll(copyPointMember);
            } else {
                copy.unsetPointMember();
            }
            if (this.isSetPointMembers()) {
                PointArrayPropertyType sourcePointMembers;
                sourcePointMembers = this.getPointMembers();
                PointArrayPropertyType copyPointMembers = ((PointArrayPropertyType) strategy.copy(LocatorUtils.property(locator, "pointMembers", sourcePointMembers), sourcePointMembers));
                copy.setPointMembers(copyPointMembers);
            } else {
                copy.pointMembers = null;
            }
        }
        return draftCopy;
    }

    public Object createNewInstance() {
        return new MultiPointType();
    }

    public void mergeFrom(Object left, Object right) {
        final MergeStrategy strategy = JAXBMergeStrategy.INSTANCE;
        mergeFrom(null, null, left, right, strategy);
    }

    public void mergeFrom(ObjectLocator leftLocator, ObjectLocator rightLocator, Object left, Object right, MergeStrategy strategy) {
        super.mergeFrom(leftLocator, rightLocator, left, right, strategy);
        if (right instanceof MultiPointType) {
            final MultiPointType target = this;
            final MultiPointType leftObject = ((MultiPointType) left);
            final MultiPointType rightObject = ((MultiPointType) right);
            {
                List<PointPropertyType> lhsPointMember;
                lhsPointMember = leftObject.getPointMember();
                List<PointPropertyType> rhsPointMember;
                rhsPointMember = rightObject.getPointMember();
                target.unsetPointMember();
                List<PointPropertyType> uniquePointMemberl = target.getPointMember();
                uniquePointMemberl.addAll(((List<PointPropertyType> ) strategy.merge(LocatorUtils.property(leftLocator, "pointMember", lhsPointMember), LocatorUtils.property(rightLocator, "pointMember", rhsPointMember), lhsPointMember, rhsPointMember)));
            }
            {
                PointArrayPropertyType lhsPointMembers;
                lhsPointMembers = leftObject.getPointMembers();
                PointArrayPropertyType rhsPointMembers;
                rhsPointMembers = rightObject.getPointMembers();
                target.setPointMembers(((PointArrayPropertyType) strategy.merge(LocatorUtils.property(leftLocator, "pointMembers", lhsPointMembers), LocatorUtils.property(rightLocator, "pointMembers", rhsPointMembers), lhsPointMembers, rhsPointMembers)));
            }
        }
    }

    public void setPointMember(List<PointPropertyType> value) {
        List<PointPropertyType> draftl = this.getPointMember();
        draftl.addAll(value);
    }

    public MultiPointType withPointMember(PointPropertyType... values) {
        if (values!= null) {
            for (PointPropertyType value: values) {
                getPointMember().add(value);
            }
        }
        return this;
    }

    public MultiPointType withPointMember(Collection<PointPropertyType> values) {
        if (values!= null) {
            getPointMember().addAll(values);
        }
        return this;
    }

    public MultiPointType withPointMembers(PointArrayPropertyType value) {
        setPointMembers(value);
        return this;
    }

    public MultiPointType withPointMember(List<PointPropertyType> value) {
        setPointMember(value);
        return this;
    }

    @Override
    public MultiPointType withAggregationType(AggregationType value) {
        setAggregationType(value);
        return this;
    }

    @Override
    public MultiPointType withSrsName(String value) {
        setSrsName(value);
        return this;
    }

    @Override
    public MultiPointType withSrsDimension(BigInteger value) {
        setSrsDimension(value);
        return this;
    }

    @Override
    public MultiPointType withAxisLabels(String... values) {
        if (values!= null) {
            for (String value: values) {
                getAxisLabels().add(value);
            }
        }
        return this;
    }

    @Override
    public MultiPointType withAxisLabels(Collection<String> values) {
        if (values!= null) {
            getAxisLabels().addAll(values);
        }
        return this;
    }

    @Override
    public MultiPointType withUomLabels(String... values) {
        if (values!= null) {
            for (String value: values) {
                getUomLabels().add(value);
            }
        }
        return this;
    }

    @Override
    public MultiPointType withUomLabels(Collection<String> values) {
        if (values!= null) {
            getUomLabels().addAll(values);
        }
        return this;
    }

    @Override
    public MultiPointType withAxisLabels(List<String> value) {
        setAxisLabels(value);
        return this;
    }

    @Override
    public MultiPointType withUomLabels(List<String> value) {
        setUomLabels(value);
        return this;
    }

    @Override
    public MultiPointType withMetaDataProperty(MetaDataPropertyType... values) {
        if (values!= null) {
            for (MetaDataPropertyType value: values) {
                getMetaDataProperty().add(value);
            }
        }
        return this;
    }

    @Override
    public MultiPointType withMetaDataProperty(Collection<MetaDataPropertyType> values) {
        if (values!= null) {
            getMetaDataProperty().addAll(values);
        }
        return this;
    }

    @Override
    public MultiPointType withDescription(StringOrRefType value) {
        setDescription(value);
        return this;
    }

    @Override
    public MultiPointType withDescriptionReference(ReferenceType value) {
        setDescriptionReference(value);
        return this;
    }

    @Override
    public MultiPointType withIdentifier(CodeWithAuthorityType value) {
        setIdentifier(value);
        return this;
    }

    @Override
    public MultiPointType withName(CodeType... values) {
        if (values!= null) {
            for (CodeType value: values) {
                getName().add(value);
            }
        }
        return this;
    }

    @Override
    public MultiPointType withName(Collection<CodeType> values) {
        if (values!= null) {
            getName().addAll(values);
        }
        return this;
    }

    @Override
    public MultiPointType withId(String value) {
        setId(value);
        return this;
    }

    @Override
    public MultiPointType withMetaDataProperty(List<MetaDataPropertyType> value) {
        setMetaDataProperty(value);
        return this;
    }

    @Override
    public MultiPointType withName(List<CodeType> value) {
        setName(value);
        return this;
    }

}
