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
 * <p>Classe Java per MultiSurfaceType complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="MultiSurfaceType">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.opengis.net/gml}AbstractGeometricAggregateType">
 *       &lt;sequence>
 *         &lt;element ref="{http://www.opengis.net/gml}surfaceMember" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element ref="{http://www.opengis.net/gml}surfaceMembers" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "MultiSurfaceType", propOrder = {
    "surfaceMember",
    "surfaceMembers"
})
public class MultiSurfaceType
    extends AbstractGeometricAggregateType
    implements Cloneable, CopyTo, Equals, HashCode, MergeFrom, ToString
{

    protected List<SurfacePropertyType> surfaceMember;
    protected SurfaceArrayPropertyType surfaceMembers;

    /**
     * Gets the value of the surfaceMember property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the surfaceMember property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSurfaceMember().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link SurfacePropertyType }
     * 
     * 
     */
    public List<SurfacePropertyType> getSurfaceMember() {
        if (surfaceMember == null) {
            surfaceMember = new ArrayList<SurfacePropertyType>();
        }
        return this.surfaceMember;
    }

    public boolean isSetSurfaceMember() {
        return ((this.surfaceMember!= null)&&(!this.surfaceMember.isEmpty()));
    }

    public void unsetSurfaceMember() {
        this.surfaceMember = null;
    }

    /**
     * Recupera il valore della proprietà surfaceMembers.
     * 
     * @return
     *     possible object is
     *     {@link SurfaceArrayPropertyType }
     *     
     */
    public SurfaceArrayPropertyType getSurfaceMembers() {
        return surfaceMembers;
    }

    /**
     * Imposta il valore della proprietà surfaceMembers.
     * 
     * @param value
     *     allowed object is
     *     {@link SurfaceArrayPropertyType }
     *     
     */
    public void setSurfaceMembers(SurfaceArrayPropertyType value) {
        this.surfaceMembers = value;
    }

    public boolean isSetSurfaceMembers() {
        return (this.surfaceMembers!= null);
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
            List<SurfacePropertyType> theSurfaceMember;
            theSurfaceMember = this.getSurfaceMember();
            strategy.appendField(locator, this, "surfaceMember", buffer, theSurfaceMember);
        }
        {
            SurfaceArrayPropertyType theSurfaceMembers;
            theSurfaceMembers = this.getSurfaceMembers();
            strategy.appendField(locator, this, "surfaceMembers", buffer, theSurfaceMembers);
        }
        return buffer;
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof MultiSurfaceType)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        if (!super.equals(thisLocator, thatLocator, object, strategy)) {
            return false;
        }
        final MultiSurfaceType that = ((MultiSurfaceType) object);
        {
            List<SurfacePropertyType> lhsSurfaceMember;
            lhsSurfaceMember = this.getSurfaceMember();
            List<SurfacePropertyType> rhsSurfaceMember;
            rhsSurfaceMember = that.getSurfaceMember();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "surfaceMember", lhsSurfaceMember), LocatorUtils.property(thatLocator, "surfaceMember", rhsSurfaceMember), lhsSurfaceMember, rhsSurfaceMember)) {
                return false;
            }
        }
        {
            SurfaceArrayPropertyType lhsSurfaceMembers;
            lhsSurfaceMembers = this.getSurfaceMembers();
            SurfaceArrayPropertyType rhsSurfaceMembers;
            rhsSurfaceMembers = that.getSurfaceMembers();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "surfaceMembers", lhsSurfaceMembers), LocatorUtils.property(thatLocator, "surfaceMembers", rhsSurfaceMembers), lhsSurfaceMembers, rhsSurfaceMembers)) {
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
            List<SurfacePropertyType> theSurfaceMember;
            theSurfaceMember = this.getSurfaceMember();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "surfaceMember", theSurfaceMember), currentHashCode, theSurfaceMember);
        }
        {
            SurfaceArrayPropertyType theSurfaceMembers;
            theSurfaceMembers = this.getSurfaceMembers();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "surfaceMembers", theSurfaceMembers), currentHashCode, theSurfaceMembers);
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
        if (draftCopy instanceof MultiSurfaceType) {
            final MultiSurfaceType copy = ((MultiSurfaceType) draftCopy);
            if (this.isSetSurfaceMember()) {
                List<SurfacePropertyType> sourceSurfaceMember;
                sourceSurfaceMember = this.getSurfaceMember();
                @SuppressWarnings("unchecked")
                List<SurfacePropertyType> copySurfaceMember = ((List<SurfacePropertyType> ) strategy.copy(LocatorUtils.property(locator, "surfaceMember", sourceSurfaceMember), sourceSurfaceMember));
                copy.unsetSurfaceMember();
                List<SurfacePropertyType> uniqueSurfaceMemberl = copy.getSurfaceMember();
                uniqueSurfaceMemberl.addAll(copySurfaceMember);
            } else {
                copy.unsetSurfaceMember();
            }
            if (this.isSetSurfaceMembers()) {
                SurfaceArrayPropertyType sourceSurfaceMembers;
                sourceSurfaceMembers = this.getSurfaceMembers();
                SurfaceArrayPropertyType copySurfaceMembers = ((SurfaceArrayPropertyType) strategy.copy(LocatorUtils.property(locator, "surfaceMembers", sourceSurfaceMembers), sourceSurfaceMembers));
                copy.setSurfaceMembers(copySurfaceMembers);
            } else {
                copy.surfaceMembers = null;
            }
        }
        return draftCopy;
    }

    public Object createNewInstance() {
        return new MultiSurfaceType();
    }

    public void mergeFrom(Object left, Object right) {
        final MergeStrategy strategy = JAXBMergeStrategy.INSTANCE;
        mergeFrom(null, null, left, right, strategy);
    }

    public void mergeFrom(ObjectLocator leftLocator, ObjectLocator rightLocator, Object left, Object right, MergeStrategy strategy) {
        super.mergeFrom(leftLocator, rightLocator, left, right, strategy);
        if (right instanceof MultiSurfaceType) {
            final MultiSurfaceType target = this;
            final MultiSurfaceType leftObject = ((MultiSurfaceType) left);
            final MultiSurfaceType rightObject = ((MultiSurfaceType) right);
            {
                List<SurfacePropertyType> lhsSurfaceMember;
                lhsSurfaceMember = leftObject.getSurfaceMember();
                List<SurfacePropertyType> rhsSurfaceMember;
                rhsSurfaceMember = rightObject.getSurfaceMember();
                target.unsetSurfaceMember();
                List<SurfacePropertyType> uniqueSurfaceMemberl = target.getSurfaceMember();
                uniqueSurfaceMemberl.addAll(((List<SurfacePropertyType> ) strategy.merge(LocatorUtils.property(leftLocator, "surfaceMember", lhsSurfaceMember), LocatorUtils.property(rightLocator, "surfaceMember", rhsSurfaceMember), lhsSurfaceMember, rhsSurfaceMember)));
            }
            {
                SurfaceArrayPropertyType lhsSurfaceMembers;
                lhsSurfaceMembers = leftObject.getSurfaceMembers();
                SurfaceArrayPropertyType rhsSurfaceMembers;
                rhsSurfaceMembers = rightObject.getSurfaceMembers();
                target.setSurfaceMembers(((SurfaceArrayPropertyType) strategy.merge(LocatorUtils.property(leftLocator, "surfaceMembers", lhsSurfaceMembers), LocatorUtils.property(rightLocator, "surfaceMembers", rhsSurfaceMembers), lhsSurfaceMembers, rhsSurfaceMembers)));
            }
        }
    }

    public void setSurfaceMember(List<SurfacePropertyType> value) {
        List<SurfacePropertyType> draftl = this.getSurfaceMember();
        draftl.addAll(value);
    }

    public MultiSurfaceType withSurfaceMember(SurfacePropertyType... values) {
        if (values!= null) {
            for (SurfacePropertyType value: values) {
                getSurfaceMember().add(value);
            }
        }
        return this;
    }

    public MultiSurfaceType withSurfaceMember(Collection<SurfacePropertyType> values) {
        if (values!= null) {
            getSurfaceMember().addAll(values);
        }
        return this;
    }

    public MultiSurfaceType withSurfaceMembers(SurfaceArrayPropertyType value) {
        setSurfaceMembers(value);
        return this;
    }

    public MultiSurfaceType withSurfaceMember(List<SurfacePropertyType> value) {
        setSurfaceMember(value);
        return this;
    }

    @Override
    public MultiSurfaceType withAggregationType(AggregationType value) {
        setAggregationType(value);
        return this;
    }

    @Override
    public MultiSurfaceType withSrsName(String value) {
        setSrsName(value);
        return this;
    }

    @Override
    public MultiSurfaceType withSrsDimension(BigInteger value) {
        setSrsDimension(value);
        return this;
    }

    @Override
    public MultiSurfaceType withAxisLabels(String... values) {
        if (values!= null) {
            for (String value: values) {
                getAxisLabels().add(value);
            }
        }
        return this;
    }

    @Override
    public MultiSurfaceType withAxisLabels(Collection<String> values) {
        if (values!= null) {
            getAxisLabels().addAll(values);
        }
        return this;
    }

    @Override
    public MultiSurfaceType withUomLabels(String... values) {
        if (values!= null) {
            for (String value: values) {
                getUomLabels().add(value);
            }
        }
        return this;
    }

    @Override
    public MultiSurfaceType withUomLabels(Collection<String> values) {
        if (values!= null) {
            getUomLabels().addAll(values);
        }
        return this;
    }

    @Override
    public MultiSurfaceType withAxisLabels(List<String> value) {
        setAxisLabels(value);
        return this;
    }

    @Override
    public MultiSurfaceType withUomLabels(List<String> value) {
        setUomLabels(value);
        return this;
    }

    @Override
    public MultiSurfaceType withMetaDataProperty(MetaDataPropertyType... values) {
        if (values!= null) {
            for (MetaDataPropertyType value: values) {
                getMetaDataProperty().add(value);
            }
        }
        return this;
    }

    @Override
    public MultiSurfaceType withMetaDataProperty(Collection<MetaDataPropertyType> values) {
        if (values!= null) {
            getMetaDataProperty().addAll(values);
        }
        return this;
    }

    @Override
    public MultiSurfaceType withDescription(StringOrRefType value) {
        setDescription(value);
        return this;
    }

    @Override
    public MultiSurfaceType withDescriptionReference(ReferenceType value) {
        setDescriptionReference(value);
        return this;
    }

    @Override
    public MultiSurfaceType withIdentifier(CodeWithAuthorityType value) {
        setIdentifier(value);
        return this;
    }

    @Override
    public MultiSurfaceType withName(CodeType... values) {
        if (values!= null) {
            for (CodeType value: values) {
                getName().add(value);
            }
        }
        return this;
    }

    @Override
    public MultiSurfaceType withName(Collection<CodeType> values) {
        if (values!= null) {
            getName().addAll(values);
        }
        return this;
    }

    @Override
    public MultiSurfaceType withId(String value) {
        setId(value);
        return this;
    }

    @Override
    public MultiSurfaceType withMetaDataProperty(List<MetaDataPropertyType> value) {
        setMetaDataProperty(value);
        return this;
    }

    @Override
    public MultiSurfaceType withName(List<CodeType> value) {
        setName(value);
        return this;
    }

}
