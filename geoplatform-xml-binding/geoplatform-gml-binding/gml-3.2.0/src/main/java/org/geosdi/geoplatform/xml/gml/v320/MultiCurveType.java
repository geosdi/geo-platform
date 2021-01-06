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
 * <p>Classe Java per MultiCurveType complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="MultiCurveType">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.opengis.net/gml}AbstractGeometricAggregateType">
 *       &lt;sequence>
 *         &lt;element ref="{http://www.opengis.net/gml}curveMember" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element ref="{http://www.opengis.net/gml}curveMembers" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "MultiCurveType", propOrder = {
    "curveMember",
    "curveMembers"
})
public class MultiCurveType
    extends AbstractGeometricAggregateType
    implements Cloneable, CopyTo, Equals, HashCode, MergeFrom, ToString
{

    protected List<CurvePropertyType> curveMember;
    protected CurveArrayPropertyType curveMembers;

    /**
     * Gets the value of the curveMember property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the curveMember property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCurveMember().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CurvePropertyType }
     * 
     * 
     */
    public List<CurvePropertyType> getCurveMember() {
        if (curveMember == null) {
            curveMember = new ArrayList<CurvePropertyType>();
        }
        return this.curveMember;
    }

    public boolean isSetCurveMember() {
        return ((this.curveMember!= null)&&(!this.curveMember.isEmpty()));
    }

    public void unsetCurveMember() {
        this.curveMember = null;
    }

    /**
     * Recupera il valore della proprietà curveMembers.
     * 
     * @return
     *     possible object is
     *     {@link CurveArrayPropertyType }
     *     
     */
    public CurveArrayPropertyType getCurveMembers() {
        return curveMembers;
    }

    /**
     * Imposta il valore della proprietà curveMembers.
     * 
     * @param value
     *     allowed object is
     *     {@link CurveArrayPropertyType }
     *     
     */
    public void setCurveMembers(CurveArrayPropertyType value) {
        this.curveMembers = value;
    }

    public boolean isSetCurveMembers() {
        return (this.curveMembers!= null);
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
            List<CurvePropertyType> theCurveMember;
            theCurveMember = this.getCurveMember();
            strategy.appendField(locator, this, "curveMember", buffer, theCurveMember);
        }
        {
            CurveArrayPropertyType theCurveMembers;
            theCurveMembers = this.getCurveMembers();
            strategy.appendField(locator, this, "curveMembers", buffer, theCurveMembers);
        }
        return buffer;
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof MultiCurveType)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        if (!super.equals(thisLocator, thatLocator, object, strategy)) {
            return false;
        }
        final MultiCurveType that = ((MultiCurveType) object);
        {
            List<CurvePropertyType> lhsCurveMember;
            lhsCurveMember = this.getCurveMember();
            List<CurvePropertyType> rhsCurveMember;
            rhsCurveMember = that.getCurveMember();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "curveMember", lhsCurveMember), LocatorUtils.property(thatLocator, "curveMember", rhsCurveMember), lhsCurveMember, rhsCurveMember)) {
                return false;
            }
        }
        {
            CurveArrayPropertyType lhsCurveMembers;
            lhsCurveMembers = this.getCurveMembers();
            CurveArrayPropertyType rhsCurveMembers;
            rhsCurveMembers = that.getCurveMembers();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "curveMembers", lhsCurveMembers), LocatorUtils.property(thatLocator, "curveMembers", rhsCurveMembers), lhsCurveMembers, rhsCurveMembers)) {
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
            List<CurvePropertyType> theCurveMember;
            theCurveMember = this.getCurveMember();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "curveMember", theCurveMember), currentHashCode, theCurveMember);
        }
        {
            CurveArrayPropertyType theCurveMembers;
            theCurveMembers = this.getCurveMembers();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "curveMembers", theCurveMembers), currentHashCode, theCurveMembers);
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
        if (draftCopy instanceof MultiCurveType) {
            final MultiCurveType copy = ((MultiCurveType) draftCopy);
            if (this.isSetCurveMember()) {
                List<CurvePropertyType> sourceCurveMember;
                sourceCurveMember = this.getCurveMember();
                @SuppressWarnings("unchecked")
                List<CurvePropertyType> copyCurveMember = ((List<CurvePropertyType> ) strategy.copy(LocatorUtils.property(locator, "curveMember", sourceCurveMember), sourceCurveMember));
                copy.unsetCurveMember();
                List<CurvePropertyType> uniqueCurveMemberl = copy.getCurveMember();
                uniqueCurveMemberl.addAll(copyCurveMember);
            } else {
                copy.unsetCurveMember();
            }
            if (this.isSetCurveMembers()) {
                CurveArrayPropertyType sourceCurveMembers;
                sourceCurveMembers = this.getCurveMembers();
                CurveArrayPropertyType copyCurveMembers = ((CurveArrayPropertyType) strategy.copy(LocatorUtils.property(locator, "curveMembers", sourceCurveMembers), sourceCurveMembers));
                copy.setCurveMembers(copyCurveMembers);
            } else {
                copy.curveMembers = null;
            }
        }
        return draftCopy;
    }

    public Object createNewInstance() {
        return new MultiCurveType();
    }

    public void mergeFrom(Object left, Object right) {
        final MergeStrategy strategy = JAXBMergeStrategy.INSTANCE;
        mergeFrom(null, null, left, right, strategy);
    }

    public void mergeFrom(ObjectLocator leftLocator, ObjectLocator rightLocator, Object left, Object right, MergeStrategy strategy) {
        super.mergeFrom(leftLocator, rightLocator, left, right, strategy);
        if (right instanceof MultiCurveType) {
            final MultiCurveType target = this;
            final MultiCurveType leftObject = ((MultiCurveType) left);
            final MultiCurveType rightObject = ((MultiCurveType) right);
            {
                List<CurvePropertyType> lhsCurveMember;
                lhsCurveMember = leftObject.getCurveMember();
                List<CurvePropertyType> rhsCurveMember;
                rhsCurveMember = rightObject.getCurveMember();
                target.unsetCurveMember();
                List<CurvePropertyType> uniqueCurveMemberl = target.getCurveMember();
                uniqueCurveMemberl.addAll(((List<CurvePropertyType> ) strategy.merge(LocatorUtils.property(leftLocator, "curveMember", lhsCurveMember), LocatorUtils.property(rightLocator, "curveMember", rhsCurveMember), lhsCurveMember, rhsCurveMember)));
            }
            {
                CurveArrayPropertyType lhsCurveMembers;
                lhsCurveMembers = leftObject.getCurveMembers();
                CurveArrayPropertyType rhsCurveMembers;
                rhsCurveMembers = rightObject.getCurveMembers();
                target.setCurveMembers(((CurveArrayPropertyType) strategy.merge(LocatorUtils.property(leftLocator, "curveMembers", lhsCurveMembers), LocatorUtils.property(rightLocator, "curveMembers", rhsCurveMembers), lhsCurveMembers, rhsCurveMembers)));
            }
        }
    }

    public void setCurveMember(List<CurvePropertyType> value) {
        List<CurvePropertyType> draftl = this.getCurveMember();
        draftl.addAll(value);
    }

    public MultiCurveType withCurveMember(CurvePropertyType... values) {
        if (values!= null) {
            for (CurvePropertyType value: values) {
                getCurveMember().add(value);
            }
        }
        return this;
    }

    public MultiCurveType withCurveMember(Collection<CurvePropertyType> values) {
        if (values!= null) {
            getCurveMember().addAll(values);
        }
        return this;
    }

    public MultiCurveType withCurveMembers(CurveArrayPropertyType value) {
        setCurveMembers(value);
        return this;
    }

    public MultiCurveType withCurveMember(List<CurvePropertyType> value) {
        setCurveMember(value);
        return this;
    }

    @Override
    public MultiCurveType withAggregationType(AggregationType value) {
        setAggregationType(value);
        return this;
    }

    @Override
    public MultiCurveType withSrsName(String value) {
        setSrsName(value);
        return this;
    }

    @Override
    public MultiCurveType withSrsDimension(BigInteger value) {
        setSrsDimension(value);
        return this;
    }

    @Override
    public MultiCurveType withAxisLabels(String... values) {
        if (values!= null) {
            for (String value: values) {
                getAxisLabels().add(value);
            }
        }
        return this;
    }

    @Override
    public MultiCurveType withAxisLabels(Collection<String> values) {
        if (values!= null) {
            getAxisLabels().addAll(values);
        }
        return this;
    }

    @Override
    public MultiCurveType withUomLabels(String... values) {
        if (values!= null) {
            for (String value: values) {
                getUomLabels().add(value);
            }
        }
        return this;
    }

    @Override
    public MultiCurveType withUomLabels(Collection<String> values) {
        if (values!= null) {
            getUomLabels().addAll(values);
        }
        return this;
    }

    @Override
    public MultiCurveType withAxisLabels(List<String> value) {
        setAxisLabels(value);
        return this;
    }

    @Override
    public MultiCurveType withUomLabels(List<String> value) {
        setUomLabels(value);
        return this;
    }

    @Override
    public MultiCurveType withMetaDataProperty(MetaDataPropertyType... values) {
        if (values!= null) {
            for (MetaDataPropertyType value: values) {
                getMetaDataProperty().add(value);
            }
        }
        return this;
    }

    @Override
    public MultiCurveType withMetaDataProperty(Collection<MetaDataPropertyType> values) {
        if (values!= null) {
            getMetaDataProperty().addAll(values);
        }
        return this;
    }

    @Override
    public MultiCurveType withDescription(StringOrRefType value) {
        setDescription(value);
        return this;
    }

    @Override
    public MultiCurveType withDescriptionReference(ReferenceType value) {
        setDescriptionReference(value);
        return this;
    }

    @Override
    public MultiCurveType withIdentifier(CodeWithAuthorityType value) {
        setIdentifier(value);
        return this;
    }

    @Override
    public MultiCurveType withName(CodeType... values) {
        if (values!= null) {
            for (CodeType value: values) {
                getName().add(value);
            }
        }
        return this;
    }

    @Override
    public MultiCurveType withName(Collection<CodeType> values) {
        if (values!= null) {
            getName().addAll(values);
        }
        return this;
    }

    @Override
    public MultiCurveType withId(String value) {
        setId(value);
        return this;
    }

    @Override
    public MultiCurveType withMetaDataProperty(List<MetaDataPropertyType> value) {
        setMetaDataProperty(value);
        return this;
    }

    @Override
    public MultiCurveType withName(List<CodeType> value) {
        setName(value);
        return this;
    }

}
