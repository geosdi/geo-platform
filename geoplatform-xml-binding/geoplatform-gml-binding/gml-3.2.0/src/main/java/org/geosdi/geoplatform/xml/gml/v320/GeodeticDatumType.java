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
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;
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
 * <p>Classe Java per GeodeticDatumType complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="GeodeticDatumType">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.opengis.net/gml}AbstractDatumType">
 *       &lt;sequence>
 *         &lt;element ref="{http://www.opengis.net/gml}primeMeridian"/>
 *         &lt;element ref="{http://www.opengis.net/gml}ellipsoid"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "GeodeticDatumType", propOrder = {
    "primeMeridian",
    "ellipsoid"
})
public class GeodeticDatumType
    extends AbstractDatumType
    implements Cloneable, CopyTo, Equals, HashCode, MergeFrom, ToString
{

    @XmlElementRef(name = "primeMeridian", namespace = "http://www.opengis.net/gml", type = JAXBElement.class)
    protected JAXBElement<PrimeMeridianPropertyType> primeMeridian;
    @XmlElementRef(name = "ellipsoid", namespace = "http://www.opengis.net/gml", type = JAXBElement.class)
    protected JAXBElement<EllipsoidPropertyType> ellipsoid;

    /**
     * Recupera il valore della proprietà primeMeridian.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link PrimeMeridianPropertyType }{@code >}
     *     {@link JAXBElement }{@code <}{@link PrimeMeridianPropertyType }{@code >}
     *     
     */
    public JAXBElement<PrimeMeridianPropertyType> getPrimeMeridian() {
        return primeMeridian;
    }

    /**
     * Imposta il valore della proprietà primeMeridian.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link PrimeMeridianPropertyType }{@code >}
     *     {@link JAXBElement }{@code <}{@link PrimeMeridianPropertyType }{@code >}
     *     
     */
    public void setPrimeMeridian(JAXBElement<PrimeMeridianPropertyType> value) {
        this.primeMeridian = value;
    }

    public boolean isSetPrimeMeridian() {
        return (this.primeMeridian!= null);
    }

    /**
     * Recupera il valore della proprietà ellipsoid.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link EllipsoidPropertyType }{@code >}
     *     {@link JAXBElement }{@code <}{@link EllipsoidPropertyType }{@code >}
     *     
     */
    public JAXBElement<EllipsoidPropertyType> getEllipsoid() {
        return ellipsoid;
    }

    /**
     * Imposta il valore della proprietà ellipsoid.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link EllipsoidPropertyType }{@code >}
     *     {@link JAXBElement }{@code <}{@link EllipsoidPropertyType }{@code >}
     *     
     */
    public void setEllipsoid(JAXBElement<EllipsoidPropertyType> value) {
        this.ellipsoid = value;
    }

    public boolean isSetEllipsoid() {
        return (this.ellipsoid!= null);
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
            JAXBElement<PrimeMeridianPropertyType> thePrimeMeridian;
            thePrimeMeridian = this.getPrimeMeridian();
            strategy.appendField(locator, this, "primeMeridian", buffer, thePrimeMeridian);
        }
        {
            JAXBElement<EllipsoidPropertyType> theEllipsoid;
            theEllipsoid = this.getEllipsoid();
            strategy.appendField(locator, this, "ellipsoid", buffer, theEllipsoid);
        }
        return buffer;
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof GeodeticDatumType)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        if (!super.equals(thisLocator, thatLocator, object, strategy)) {
            return false;
        }
        final GeodeticDatumType that = ((GeodeticDatumType) object);
        {
            JAXBElement<PrimeMeridianPropertyType> lhsPrimeMeridian;
            lhsPrimeMeridian = this.getPrimeMeridian();
            JAXBElement<PrimeMeridianPropertyType> rhsPrimeMeridian;
            rhsPrimeMeridian = that.getPrimeMeridian();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "primeMeridian", lhsPrimeMeridian), LocatorUtils.property(thatLocator, "primeMeridian", rhsPrimeMeridian), lhsPrimeMeridian, rhsPrimeMeridian)) {
                return false;
            }
        }
        {
            JAXBElement<EllipsoidPropertyType> lhsEllipsoid;
            lhsEllipsoid = this.getEllipsoid();
            JAXBElement<EllipsoidPropertyType> rhsEllipsoid;
            rhsEllipsoid = that.getEllipsoid();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "ellipsoid", lhsEllipsoid), LocatorUtils.property(thatLocator, "ellipsoid", rhsEllipsoid), lhsEllipsoid, rhsEllipsoid)) {
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
            JAXBElement<PrimeMeridianPropertyType> thePrimeMeridian;
            thePrimeMeridian = this.getPrimeMeridian();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "primeMeridian", thePrimeMeridian), currentHashCode, thePrimeMeridian);
        }
        {
            JAXBElement<EllipsoidPropertyType> theEllipsoid;
            theEllipsoid = this.getEllipsoid();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "ellipsoid", theEllipsoid), currentHashCode, theEllipsoid);
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
        if (draftCopy instanceof GeodeticDatumType) {
            final GeodeticDatumType copy = ((GeodeticDatumType) draftCopy);
            if (this.isSetPrimeMeridian()) {
                JAXBElement<PrimeMeridianPropertyType> sourcePrimeMeridian;
                sourcePrimeMeridian = this.getPrimeMeridian();
                @SuppressWarnings("unchecked")
                JAXBElement<PrimeMeridianPropertyType> copyPrimeMeridian = ((JAXBElement<PrimeMeridianPropertyType> ) strategy.copy(LocatorUtils.property(locator, "primeMeridian", sourcePrimeMeridian), sourcePrimeMeridian));
                copy.setPrimeMeridian(copyPrimeMeridian);
            } else {
                copy.primeMeridian = null;
            }
            if (this.isSetEllipsoid()) {
                JAXBElement<EllipsoidPropertyType> sourceEllipsoid;
                sourceEllipsoid = this.getEllipsoid();
                @SuppressWarnings("unchecked")
                JAXBElement<EllipsoidPropertyType> copyEllipsoid = ((JAXBElement<EllipsoidPropertyType> ) strategy.copy(LocatorUtils.property(locator, "ellipsoid", sourceEllipsoid), sourceEllipsoid));
                copy.setEllipsoid(copyEllipsoid);
            } else {
                copy.ellipsoid = null;
            }
        }
        return draftCopy;
    }

    public Object createNewInstance() {
        return new GeodeticDatumType();
    }

    public void mergeFrom(Object left, Object right) {
        final MergeStrategy strategy = JAXBMergeStrategy.INSTANCE;
        mergeFrom(null, null, left, right, strategy);
    }

    public void mergeFrom(ObjectLocator leftLocator, ObjectLocator rightLocator, Object left, Object right, MergeStrategy strategy) {
        super.mergeFrom(leftLocator, rightLocator, left, right, strategy);
        if (right instanceof GeodeticDatumType) {
            final GeodeticDatumType target = this;
            final GeodeticDatumType leftObject = ((GeodeticDatumType) left);
            final GeodeticDatumType rightObject = ((GeodeticDatumType) right);
            {
                JAXBElement<PrimeMeridianPropertyType> lhsPrimeMeridian;
                lhsPrimeMeridian = leftObject.getPrimeMeridian();
                JAXBElement<PrimeMeridianPropertyType> rhsPrimeMeridian;
                rhsPrimeMeridian = rightObject.getPrimeMeridian();
                target.setPrimeMeridian(((JAXBElement<PrimeMeridianPropertyType> ) strategy.merge(LocatorUtils.property(leftLocator, "primeMeridian", lhsPrimeMeridian), LocatorUtils.property(rightLocator, "primeMeridian", rhsPrimeMeridian), lhsPrimeMeridian, rhsPrimeMeridian)));
            }
            {
                JAXBElement<EllipsoidPropertyType> lhsEllipsoid;
                lhsEllipsoid = leftObject.getEllipsoid();
                JAXBElement<EllipsoidPropertyType> rhsEllipsoid;
                rhsEllipsoid = rightObject.getEllipsoid();
                target.setEllipsoid(((JAXBElement<EllipsoidPropertyType> ) strategy.merge(LocatorUtils.property(leftLocator, "ellipsoid", lhsEllipsoid), LocatorUtils.property(rightLocator, "ellipsoid", rhsEllipsoid), lhsEllipsoid, rhsEllipsoid)));
            }
        }
    }

    public GeodeticDatumType withPrimeMeridian(JAXBElement<PrimeMeridianPropertyType> value) {
        setPrimeMeridian(value);
        return this;
    }

    public GeodeticDatumType withEllipsoid(JAXBElement<EllipsoidPropertyType> value) {
        setEllipsoid(value);
        return this;
    }

    @Override
    public GeodeticDatumType withDomainOfValidity(DomainOfValidityElement value) {
        setDomainOfValidity(value);
        return this;
    }

    @Override
    public GeodeticDatumType withScope(String... values) {
        if (values!= null) {
            for (String value: values) {
                getScope().add(value);
            }
        }
        return this;
    }

    @Override
    public GeodeticDatumType withScope(Collection<String> values) {
        if (values!= null) {
            getScope().addAll(values);
        }
        return this;
    }

    @Override
    public GeodeticDatumType withAnchorDefinition(JAXBElement<CodeType> value) {
        setAnchorDefinition(value);
        return this;
    }

    @Override
    public GeodeticDatumType withRealizationEpoch(XMLGregorianCalendar value) {
        setRealizationEpoch(value);
        return this;
    }

    @Override
    public GeodeticDatumType withScope(List<String> value) {
        setScope(value);
        return this;
    }

    @Override
    public GeodeticDatumType withRemarks(String value) {
        setRemarks(value);
        return this;
    }

    @Override
    public GeodeticDatumType withMetaDataProperty(MetaDataPropertyType... values) {
        if (values!= null) {
            for (MetaDataPropertyType value: values) {
                getMetaDataProperty().add(value);
            }
        }
        return this;
    }

    @Override
    public GeodeticDatumType withMetaDataProperty(Collection<MetaDataPropertyType> values) {
        if (values!= null) {
            getMetaDataProperty().addAll(values);
        }
        return this;
    }

    @Override
    public GeodeticDatumType withDescription(StringOrRefType value) {
        setDescription(value);
        return this;
    }

    @Override
    public GeodeticDatumType withDescriptionReference(ReferenceType value) {
        setDescriptionReference(value);
        return this;
    }

    @Override
    public GeodeticDatumType withIdentifier(CodeWithAuthorityType value) {
        setIdentifier(value);
        return this;
    }

    @Override
    public GeodeticDatumType withName(CodeType... values) {
        if (values!= null) {
            for (CodeType value: values) {
                getName().add(value);
            }
        }
        return this;
    }

    @Override
    public GeodeticDatumType withName(Collection<CodeType> values) {
        if (values!= null) {
            getName().addAll(values);
        }
        return this;
    }

    @Override
    public GeodeticDatumType withId(String value) {
        setId(value);
        return this;
    }

    @Override
    public GeodeticDatumType withMetaDataProperty(List<MetaDataPropertyType> value) {
        setMetaDataProperty(value);
        return this;
    }

    @Override
    public GeodeticDatumType withName(List<CodeType> value) {
        setName(value);
        return this;
    }

}
