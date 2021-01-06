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
 * gml:GeodeticCRS is a coordinate reference system based on a geodetic datum.
 * 
 * <p>Classe Java per GeodeticCRSType complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="GeodeticCRSType">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.opengis.net/gml}AbstractCRSType">
 *       &lt;sequence>
 *         &lt;choice>
 *           &lt;element ref="{http://www.opengis.net/gml}ellipsoidalCS"/>
 *           &lt;element ref="{http://www.opengis.net/gml}cartesianCS"/>
 *           &lt;element ref="{http://www.opengis.net/gml}sphericalCS"/>
 *         &lt;/choice>
 *         &lt;element ref="{http://www.opengis.net/gml}geodeticDatum"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "GeodeticCRSType", propOrder = {
    "ellipsoidalCS",
    "cartesianCS",
    "sphericalCS",
    "geodeticDatum"
})
public class GeodeticCRSType
    extends AbstractCRSType
    implements Cloneable, CopyTo, Equals, HashCode, MergeFrom, ToString
{

    @XmlElementRef(name = "ellipsoidalCS", namespace = "http://www.opengis.net/gml", type = JAXBElement.class, required = false)
    protected JAXBElement<EllipsoidalCSPropertyType> ellipsoidalCS;
    @XmlElementRef(name = "cartesianCS", namespace = "http://www.opengis.net/gml", type = JAXBElement.class, required = false)
    protected JAXBElement<CartesianCSPropertyType> cartesianCS;
    @XmlElementRef(name = "sphericalCS", namespace = "http://www.opengis.net/gml", type = JAXBElement.class, required = false)
    protected JAXBElement<SphericalCSPropertyType> sphericalCS;
    @XmlElementRef(name = "geodeticDatum", namespace = "http://www.opengis.net/gml", type = JAXBElement.class)
    protected JAXBElement<GeodeticDatumPropertyType> geodeticDatum;

    /**
     * Recupera il valore della proprietà ellipsoidalCS.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link EllipsoidalCSPropertyType }{@code >}
     *     {@link JAXBElement }{@code <}{@link EllipsoidalCSPropertyType }{@code >}
     *     
     */
    public JAXBElement<EllipsoidalCSPropertyType> getEllipsoidalCS() {
        return ellipsoidalCS;
    }

    /**
     * Imposta il valore della proprietà ellipsoidalCS.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link EllipsoidalCSPropertyType }{@code >}
     *     {@link JAXBElement }{@code <}{@link EllipsoidalCSPropertyType }{@code >}
     *     
     */
    public void setEllipsoidalCS(JAXBElement<EllipsoidalCSPropertyType> value) {
        this.ellipsoidalCS = value;
    }

    public boolean isSetEllipsoidalCS() {
        return (this.ellipsoidalCS!= null);
    }

    /**
     * Recupera il valore della proprietà cartesianCS.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link CartesianCSPropertyType }{@code >}
     *     {@link JAXBElement }{@code <}{@link CartesianCSPropertyType }{@code >}
     *     
     */
    public JAXBElement<CartesianCSPropertyType> getCartesianCS() {
        return cartesianCS;
    }

    /**
     * Imposta il valore della proprietà cartesianCS.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link CartesianCSPropertyType }{@code >}
     *     {@link JAXBElement }{@code <}{@link CartesianCSPropertyType }{@code >}
     *     
     */
    public void setCartesianCS(JAXBElement<CartesianCSPropertyType> value) {
        this.cartesianCS = value;
    }

    public boolean isSetCartesianCS() {
        return (this.cartesianCS!= null);
    }

    /**
     * Recupera il valore della proprietà sphericalCS.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link SphericalCSPropertyType }{@code >}
     *     {@link JAXBElement }{@code <}{@link SphericalCSPropertyType }{@code >}
     *     
     */
    public JAXBElement<SphericalCSPropertyType> getSphericalCS() {
        return sphericalCS;
    }

    /**
     * Imposta il valore della proprietà sphericalCS.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link SphericalCSPropertyType }{@code >}
     *     {@link JAXBElement }{@code <}{@link SphericalCSPropertyType }{@code >}
     *     
     */
    public void setSphericalCS(JAXBElement<SphericalCSPropertyType> value) {
        this.sphericalCS = value;
    }

    public boolean isSetSphericalCS() {
        return (this.sphericalCS!= null);
    }

    /**
     * Recupera il valore della proprietà geodeticDatum.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link GeodeticDatumPropertyType }{@code >}
     *     {@link JAXBElement }{@code <}{@link GeodeticDatumPropertyType }{@code >}
     *     
     */
    public JAXBElement<GeodeticDatumPropertyType> getGeodeticDatum() {
        return geodeticDatum;
    }

    /**
     * Imposta il valore della proprietà geodeticDatum.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link GeodeticDatumPropertyType }{@code >}
     *     {@link JAXBElement }{@code <}{@link GeodeticDatumPropertyType }{@code >}
     *     
     */
    public void setGeodeticDatum(JAXBElement<GeodeticDatumPropertyType> value) {
        this.geodeticDatum = value;
    }

    public boolean isSetGeodeticDatum() {
        return (this.geodeticDatum!= null);
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
            JAXBElement<EllipsoidalCSPropertyType> theEllipsoidalCS;
            theEllipsoidalCS = this.getEllipsoidalCS();
            strategy.appendField(locator, this, "ellipsoidalCS", buffer, theEllipsoidalCS);
        }
        {
            JAXBElement<CartesianCSPropertyType> theCartesianCS;
            theCartesianCS = this.getCartesianCS();
            strategy.appendField(locator, this, "cartesianCS", buffer, theCartesianCS);
        }
        {
            JAXBElement<SphericalCSPropertyType> theSphericalCS;
            theSphericalCS = this.getSphericalCS();
            strategy.appendField(locator, this, "sphericalCS", buffer, theSphericalCS);
        }
        {
            JAXBElement<GeodeticDatumPropertyType> theGeodeticDatum;
            theGeodeticDatum = this.getGeodeticDatum();
            strategy.appendField(locator, this, "geodeticDatum", buffer, theGeodeticDatum);
        }
        return buffer;
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof GeodeticCRSType)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        if (!super.equals(thisLocator, thatLocator, object, strategy)) {
            return false;
        }
        final GeodeticCRSType that = ((GeodeticCRSType) object);
        {
            JAXBElement<EllipsoidalCSPropertyType> lhsEllipsoidalCS;
            lhsEllipsoidalCS = this.getEllipsoidalCS();
            JAXBElement<EllipsoidalCSPropertyType> rhsEllipsoidalCS;
            rhsEllipsoidalCS = that.getEllipsoidalCS();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "ellipsoidalCS", lhsEllipsoidalCS), LocatorUtils.property(thatLocator, "ellipsoidalCS", rhsEllipsoidalCS), lhsEllipsoidalCS, rhsEllipsoidalCS)) {
                return false;
            }
        }
        {
            JAXBElement<CartesianCSPropertyType> lhsCartesianCS;
            lhsCartesianCS = this.getCartesianCS();
            JAXBElement<CartesianCSPropertyType> rhsCartesianCS;
            rhsCartesianCS = that.getCartesianCS();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "cartesianCS", lhsCartesianCS), LocatorUtils.property(thatLocator, "cartesianCS", rhsCartesianCS), lhsCartesianCS, rhsCartesianCS)) {
                return false;
            }
        }
        {
            JAXBElement<SphericalCSPropertyType> lhsSphericalCS;
            lhsSphericalCS = this.getSphericalCS();
            JAXBElement<SphericalCSPropertyType> rhsSphericalCS;
            rhsSphericalCS = that.getSphericalCS();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "sphericalCS", lhsSphericalCS), LocatorUtils.property(thatLocator, "sphericalCS", rhsSphericalCS), lhsSphericalCS, rhsSphericalCS)) {
                return false;
            }
        }
        {
            JAXBElement<GeodeticDatumPropertyType> lhsGeodeticDatum;
            lhsGeodeticDatum = this.getGeodeticDatum();
            JAXBElement<GeodeticDatumPropertyType> rhsGeodeticDatum;
            rhsGeodeticDatum = that.getGeodeticDatum();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "geodeticDatum", lhsGeodeticDatum), LocatorUtils.property(thatLocator, "geodeticDatum", rhsGeodeticDatum), lhsGeodeticDatum, rhsGeodeticDatum)) {
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
            JAXBElement<EllipsoidalCSPropertyType> theEllipsoidalCS;
            theEllipsoidalCS = this.getEllipsoidalCS();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "ellipsoidalCS", theEllipsoidalCS), currentHashCode, theEllipsoidalCS);
        }
        {
            JAXBElement<CartesianCSPropertyType> theCartesianCS;
            theCartesianCS = this.getCartesianCS();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "cartesianCS", theCartesianCS), currentHashCode, theCartesianCS);
        }
        {
            JAXBElement<SphericalCSPropertyType> theSphericalCS;
            theSphericalCS = this.getSphericalCS();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "sphericalCS", theSphericalCS), currentHashCode, theSphericalCS);
        }
        {
            JAXBElement<GeodeticDatumPropertyType> theGeodeticDatum;
            theGeodeticDatum = this.getGeodeticDatum();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "geodeticDatum", theGeodeticDatum), currentHashCode, theGeodeticDatum);
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
        if (draftCopy instanceof GeodeticCRSType) {
            final GeodeticCRSType copy = ((GeodeticCRSType) draftCopy);
            if (this.isSetEllipsoidalCS()) {
                JAXBElement<EllipsoidalCSPropertyType> sourceEllipsoidalCS;
                sourceEllipsoidalCS = this.getEllipsoidalCS();
                @SuppressWarnings("unchecked")
                JAXBElement<EllipsoidalCSPropertyType> copyEllipsoidalCS = ((JAXBElement<EllipsoidalCSPropertyType> ) strategy.copy(LocatorUtils.property(locator, "ellipsoidalCS", sourceEllipsoidalCS), sourceEllipsoidalCS));
                copy.setEllipsoidalCS(copyEllipsoidalCS);
            } else {
                copy.ellipsoidalCS = null;
            }
            if (this.isSetCartesianCS()) {
                JAXBElement<CartesianCSPropertyType> sourceCartesianCS;
                sourceCartesianCS = this.getCartesianCS();
                @SuppressWarnings("unchecked")
                JAXBElement<CartesianCSPropertyType> copyCartesianCS = ((JAXBElement<CartesianCSPropertyType> ) strategy.copy(LocatorUtils.property(locator, "cartesianCS", sourceCartesianCS), sourceCartesianCS));
                copy.setCartesianCS(copyCartesianCS);
            } else {
                copy.cartesianCS = null;
            }
            if (this.isSetSphericalCS()) {
                JAXBElement<SphericalCSPropertyType> sourceSphericalCS;
                sourceSphericalCS = this.getSphericalCS();
                @SuppressWarnings("unchecked")
                JAXBElement<SphericalCSPropertyType> copySphericalCS = ((JAXBElement<SphericalCSPropertyType> ) strategy.copy(LocatorUtils.property(locator, "sphericalCS", sourceSphericalCS), sourceSphericalCS));
                copy.setSphericalCS(copySphericalCS);
            } else {
                copy.sphericalCS = null;
            }
            if (this.isSetGeodeticDatum()) {
                JAXBElement<GeodeticDatumPropertyType> sourceGeodeticDatum;
                sourceGeodeticDatum = this.getGeodeticDatum();
                @SuppressWarnings("unchecked")
                JAXBElement<GeodeticDatumPropertyType> copyGeodeticDatum = ((JAXBElement<GeodeticDatumPropertyType> ) strategy.copy(LocatorUtils.property(locator, "geodeticDatum", sourceGeodeticDatum), sourceGeodeticDatum));
                copy.setGeodeticDatum(copyGeodeticDatum);
            } else {
                copy.geodeticDatum = null;
            }
        }
        return draftCopy;
    }

    public Object createNewInstance() {
        return new GeodeticCRSType();
    }

    public void mergeFrom(Object left, Object right) {
        final MergeStrategy strategy = JAXBMergeStrategy.INSTANCE;
        mergeFrom(null, null, left, right, strategy);
    }

    public void mergeFrom(ObjectLocator leftLocator, ObjectLocator rightLocator, Object left, Object right, MergeStrategy strategy) {
        super.mergeFrom(leftLocator, rightLocator, left, right, strategy);
        if (right instanceof GeodeticCRSType) {
            final GeodeticCRSType target = this;
            final GeodeticCRSType leftObject = ((GeodeticCRSType) left);
            final GeodeticCRSType rightObject = ((GeodeticCRSType) right);
            {
                JAXBElement<EllipsoidalCSPropertyType> lhsEllipsoidalCS;
                lhsEllipsoidalCS = leftObject.getEllipsoidalCS();
                JAXBElement<EllipsoidalCSPropertyType> rhsEllipsoidalCS;
                rhsEllipsoidalCS = rightObject.getEllipsoidalCS();
                target.setEllipsoidalCS(((JAXBElement<EllipsoidalCSPropertyType> ) strategy.merge(LocatorUtils.property(leftLocator, "ellipsoidalCS", lhsEllipsoidalCS), LocatorUtils.property(rightLocator, "ellipsoidalCS", rhsEllipsoidalCS), lhsEllipsoidalCS, rhsEllipsoidalCS)));
            }
            {
                JAXBElement<CartesianCSPropertyType> lhsCartesianCS;
                lhsCartesianCS = leftObject.getCartesianCS();
                JAXBElement<CartesianCSPropertyType> rhsCartesianCS;
                rhsCartesianCS = rightObject.getCartesianCS();
                target.setCartesianCS(((JAXBElement<CartesianCSPropertyType> ) strategy.merge(LocatorUtils.property(leftLocator, "cartesianCS", lhsCartesianCS), LocatorUtils.property(rightLocator, "cartesianCS", rhsCartesianCS), lhsCartesianCS, rhsCartesianCS)));
            }
            {
                JAXBElement<SphericalCSPropertyType> lhsSphericalCS;
                lhsSphericalCS = leftObject.getSphericalCS();
                JAXBElement<SphericalCSPropertyType> rhsSphericalCS;
                rhsSphericalCS = rightObject.getSphericalCS();
                target.setSphericalCS(((JAXBElement<SphericalCSPropertyType> ) strategy.merge(LocatorUtils.property(leftLocator, "sphericalCS", lhsSphericalCS), LocatorUtils.property(rightLocator, "sphericalCS", rhsSphericalCS), lhsSphericalCS, rhsSphericalCS)));
            }
            {
                JAXBElement<GeodeticDatumPropertyType> lhsGeodeticDatum;
                lhsGeodeticDatum = leftObject.getGeodeticDatum();
                JAXBElement<GeodeticDatumPropertyType> rhsGeodeticDatum;
                rhsGeodeticDatum = rightObject.getGeodeticDatum();
                target.setGeodeticDatum(((JAXBElement<GeodeticDatumPropertyType> ) strategy.merge(LocatorUtils.property(leftLocator, "geodeticDatum", lhsGeodeticDatum), LocatorUtils.property(rightLocator, "geodeticDatum", rhsGeodeticDatum), lhsGeodeticDatum, rhsGeodeticDatum)));
            }
        }
    }

    public GeodeticCRSType withEllipsoidalCS(JAXBElement<EllipsoidalCSPropertyType> value) {
        setEllipsoidalCS(value);
        return this;
    }

    public GeodeticCRSType withCartesianCS(JAXBElement<CartesianCSPropertyType> value) {
        setCartesianCS(value);
        return this;
    }

    public GeodeticCRSType withSphericalCS(JAXBElement<SphericalCSPropertyType> value) {
        setSphericalCS(value);
        return this;
    }

    public GeodeticCRSType withGeodeticDatum(JAXBElement<GeodeticDatumPropertyType> value) {
        setGeodeticDatum(value);
        return this;
    }

    @Override
    public GeodeticCRSType withDomainOfValidity(DomainOfValidityElement... values) {
        if (values!= null) {
            for (DomainOfValidityElement value: values) {
                getDomainOfValidity().add(value);
            }
        }
        return this;
    }

    @Override
    public GeodeticCRSType withDomainOfValidity(Collection<DomainOfValidityElement> values) {
        if (values!= null) {
            getDomainOfValidity().addAll(values);
        }
        return this;
    }

    @Override
    public GeodeticCRSType withScope(String... values) {
        if (values!= null) {
            for (String value: values) {
                getScope().add(value);
            }
        }
        return this;
    }

    @Override
    public GeodeticCRSType withScope(Collection<String> values) {
        if (values!= null) {
            getScope().addAll(values);
        }
        return this;
    }

    @Override
    public GeodeticCRSType withDomainOfValidity(List<DomainOfValidityElement> value) {
        setDomainOfValidity(value);
        return this;
    }

    @Override
    public GeodeticCRSType withScope(List<String> value) {
        setScope(value);
        return this;
    }

    @Override
    public GeodeticCRSType withRemarks(String value) {
        setRemarks(value);
        return this;
    }

    @Override
    public GeodeticCRSType withMetaDataProperty(MetaDataPropertyType... values) {
        if (values!= null) {
            for (MetaDataPropertyType value: values) {
                getMetaDataProperty().add(value);
            }
        }
        return this;
    }

    @Override
    public GeodeticCRSType withMetaDataProperty(Collection<MetaDataPropertyType> values) {
        if (values!= null) {
            getMetaDataProperty().addAll(values);
        }
        return this;
    }

    @Override
    public GeodeticCRSType withDescription(StringOrRefType value) {
        setDescription(value);
        return this;
    }

    @Override
    public GeodeticCRSType withDescriptionReference(ReferenceType value) {
        setDescriptionReference(value);
        return this;
    }

    @Override
    public GeodeticCRSType withIdentifier(CodeWithAuthorityType value) {
        setIdentifier(value);
        return this;
    }

    @Override
    public GeodeticCRSType withName(CodeType... values) {
        if (values!= null) {
            for (CodeType value: values) {
                getName().add(value);
            }
        }
        return this;
    }

    @Override
    public GeodeticCRSType withName(Collection<CodeType> values) {
        if (values!= null) {
            getName().addAll(values);
        }
        return this;
    }

    @Override
    public GeodeticCRSType withId(String value) {
        setId(value);
        return this;
    }

    @Override
    public GeodeticCRSType withMetaDataProperty(List<MetaDataPropertyType> value) {
        setMetaDataProperty(value);
        return this;
    }

    @Override
    public GeodeticCRSType withName(List<CodeType> value) {
        setName(value);
        return this;
    }

}
