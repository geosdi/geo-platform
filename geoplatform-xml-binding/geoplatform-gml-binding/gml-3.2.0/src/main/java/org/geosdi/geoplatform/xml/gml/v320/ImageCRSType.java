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
 * <p>Classe Java per ImageCRSType complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="ImageCRSType">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.opengis.net/gml}AbstractCRSType">
 *       &lt;sequence>
 *         &lt;choice>
 *           &lt;element ref="{http://www.opengis.net/gml}cartesianCS"/>
 *           &lt;element ref="{http://www.opengis.net/gml}affineCS"/>
 *           &lt;element ref="{http://www.opengis.net/gml}usesObliqueCartesianCS"/>
 *         &lt;/choice>
 *         &lt;element ref="{http://www.opengis.net/gml}imageDatum"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ImageCRSType", propOrder = {
    "cartesianCS",
    "affineCS",
    "usesObliqueCartesianCS",
    "imageDatum"
})
public class ImageCRSType
    extends AbstractCRSType
    implements Cloneable, CopyTo, Equals, HashCode, MergeFrom, ToString
{

    @XmlElementRef(name = "cartesianCS", namespace = "http://www.opengis.net/gml", type = JAXBElement.class, required = false)
    protected JAXBElement<CartesianCSPropertyType> cartesianCS;
    @XmlElementRef(name = "affineCS", namespace = "http://www.opengis.net/gml", type = JAXBElement.class, required = false)
    protected JAXBElement<AffineCSPropertyType> affineCS;
    protected ObliqueCartesianCSPropertyType usesObliqueCartesianCS;
    @XmlElementRef(name = "imageDatum", namespace = "http://www.opengis.net/gml", type = JAXBElement.class)
    protected JAXBElement<ImageDatumPropertyType> imageDatum;

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
     * Recupera il valore della proprietà affineCS.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link AffineCSPropertyType }{@code >}
     *     {@link JAXBElement }{@code <}{@link AffineCSPropertyType }{@code >}
     *     
     */
    public JAXBElement<AffineCSPropertyType> getAffineCS() {
        return affineCS;
    }

    /**
     * Imposta il valore della proprietà affineCS.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link AffineCSPropertyType }{@code >}
     *     {@link JAXBElement }{@code <}{@link AffineCSPropertyType }{@code >}
     *     
     */
    public void setAffineCS(JAXBElement<AffineCSPropertyType> value) {
        this.affineCS = value;
    }

    public boolean isSetAffineCS() {
        return (this.affineCS!= null);
    }

    /**
     * Recupera il valore della proprietà usesObliqueCartesianCS.
     * 
     * @return
     *     possible object is
     *     {@link ObliqueCartesianCSPropertyType }
     *     
     */
    public ObliqueCartesianCSPropertyType getUsesObliqueCartesianCS() {
        return usesObliqueCartesianCS;
    }

    /**
     * Imposta il valore della proprietà usesObliqueCartesianCS.
     * 
     * @param value
     *     allowed object is
     *     {@link ObliqueCartesianCSPropertyType }
     *     
     */
    public void setUsesObliqueCartesianCS(ObliqueCartesianCSPropertyType value) {
        this.usesObliqueCartesianCS = value;
    }

    public boolean isSetUsesObliqueCartesianCS() {
        return (this.usesObliqueCartesianCS!= null);
    }

    /**
     * Recupera il valore della proprietà imageDatum.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link ImageDatumPropertyType }{@code >}
     *     {@link JAXBElement }{@code <}{@link ImageDatumPropertyType }{@code >}
     *     
     */
    public JAXBElement<ImageDatumPropertyType> getImageDatum() {
        return imageDatum;
    }

    /**
     * Imposta il valore della proprietà imageDatum.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link ImageDatumPropertyType }{@code >}
     *     {@link JAXBElement }{@code <}{@link ImageDatumPropertyType }{@code >}
     *     
     */
    public void setImageDatum(JAXBElement<ImageDatumPropertyType> value) {
        this.imageDatum = value;
    }

    public boolean isSetImageDatum() {
        return (this.imageDatum!= null);
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
            JAXBElement<CartesianCSPropertyType> theCartesianCS;
            theCartesianCS = this.getCartesianCS();
            strategy.appendField(locator, this, "cartesianCS", buffer, theCartesianCS);
        }
        {
            JAXBElement<AffineCSPropertyType> theAffineCS;
            theAffineCS = this.getAffineCS();
            strategy.appendField(locator, this, "affineCS", buffer, theAffineCS);
        }
        {
            ObliqueCartesianCSPropertyType theUsesObliqueCartesianCS;
            theUsesObliqueCartesianCS = this.getUsesObliqueCartesianCS();
            strategy.appendField(locator, this, "usesObliqueCartesianCS", buffer, theUsesObliqueCartesianCS);
        }
        {
            JAXBElement<ImageDatumPropertyType> theImageDatum;
            theImageDatum = this.getImageDatum();
            strategy.appendField(locator, this, "imageDatum", buffer, theImageDatum);
        }
        return buffer;
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof ImageCRSType)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        if (!super.equals(thisLocator, thatLocator, object, strategy)) {
            return false;
        }
        final ImageCRSType that = ((ImageCRSType) object);
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
            JAXBElement<AffineCSPropertyType> lhsAffineCS;
            lhsAffineCS = this.getAffineCS();
            JAXBElement<AffineCSPropertyType> rhsAffineCS;
            rhsAffineCS = that.getAffineCS();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "affineCS", lhsAffineCS), LocatorUtils.property(thatLocator, "affineCS", rhsAffineCS), lhsAffineCS, rhsAffineCS)) {
                return false;
            }
        }
        {
            ObliqueCartesianCSPropertyType lhsUsesObliqueCartesianCS;
            lhsUsesObliqueCartesianCS = this.getUsesObliqueCartesianCS();
            ObliqueCartesianCSPropertyType rhsUsesObliqueCartesianCS;
            rhsUsesObliqueCartesianCS = that.getUsesObliqueCartesianCS();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "usesObliqueCartesianCS", lhsUsesObliqueCartesianCS), LocatorUtils.property(thatLocator, "usesObliqueCartesianCS", rhsUsesObliqueCartesianCS), lhsUsesObliqueCartesianCS, rhsUsesObliqueCartesianCS)) {
                return false;
            }
        }
        {
            JAXBElement<ImageDatumPropertyType> lhsImageDatum;
            lhsImageDatum = this.getImageDatum();
            JAXBElement<ImageDatumPropertyType> rhsImageDatum;
            rhsImageDatum = that.getImageDatum();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "imageDatum", lhsImageDatum), LocatorUtils.property(thatLocator, "imageDatum", rhsImageDatum), lhsImageDatum, rhsImageDatum)) {
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
            JAXBElement<CartesianCSPropertyType> theCartesianCS;
            theCartesianCS = this.getCartesianCS();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "cartesianCS", theCartesianCS), currentHashCode, theCartesianCS);
        }
        {
            JAXBElement<AffineCSPropertyType> theAffineCS;
            theAffineCS = this.getAffineCS();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "affineCS", theAffineCS), currentHashCode, theAffineCS);
        }
        {
            ObliqueCartesianCSPropertyType theUsesObliqueCartesianCS;
            theUsesObliqueCartesianCS = this.getUsesObliqueCartesianCS();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "usesObliqueCartesianCS", theUsesObliqueCartesianCS), currentHashCode, theUsesObliqueCartesianCS);
        }
        {
            JAXBElement<ImageDatumPropertyType> theImageDatum;
            theImageDatum = this.getImageDatum();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "imageDatum", theImageDatum), currentHashCode, theImageDatum);
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
        if (draftCopy instanceof ImageCRSType) {
            final ImageCRSType copy = ((ImageCRSType) draftCopy);
            if (this.isSetCartesianCS()) {
                JAXBElement<CartesianCSPropertyType> sourceCartesianCS;
                sourceCartesianCS = this.getCartesianCS();
                @SuppressWarnings("unchecked")
                JAXBElement<CartesianCSPropertyType> copyCartesianCS = ((JAXBElement<CartesianCSPropertyType> ) strategy.copy(LocatorUtils.property(locator, "cartesianCS", sourceCartesianCS), sourceCartesianCS));
                copy.setCartesianCS(copyCartesianCS);
            } else {
                copy.cartesianCS = null;
            }
            if (this.isSetAffineCS()) {
                JAXBElement<AffineCSPropertyType> sourceAffineCS;
                sourceAffineCS = this.getAffineCS();
                @SuppressWarnings("unchecked")
                JAXBElement<AffineCSPropertyType> copyAffineCS = ((JAXBElement<AffineCSPropertyType> ) strategy.copy(LocatorUtils.property(locator, "affineCS", sourceAffineCS), sourceAffineCS));
                copy.setAffineCS(copyAffineCS);
            } else {
                copy.affineCS = null;
            }
            if (this.isSetUsesObliqueCartesianCS()) {
                ObliqueCartesianCSPropertyType sourceUsesObliqueCartesianCS;
                sourceUsesObliqueCartesianCS = this.getUsesObliqueCartesianCS();
                ObliqueCartesianCSPropertyType copyUsesObliqueCartesianCS = ((ObliqueCartesianCSPropertyType) strategy.copy(LocatorUtils.property(locator, "usesObliqueCartesianCS", sourceUsesObliqueCartesianCS), sourceUsesObliqueCartesianCS));
                copy.setUsesObliqueCartesianCS(copyUsesObliqueCartesianCS);
            } else {
                copy.usesObliqueCartesianCS = null;
            }
            if (this.isSetImageDatum()) {
                JAXBElement<ImageDatumPropertyType> sourceImageDatum;
                sourceImageDatum = this.getImageDatum();
                @SuppressWarnings("unchecked")
                JAXBElement<ImageDatumPropertyType> copyImageDatum = ((JAXBElement<ImageDatumPropertyType> ) strategy.copy(LocatorUtils.property(locator, "imageDatum", sourceImageDatum), sourceImageDatum));
                copy.setImageDatum(copyImageDatum);
            } else {
                copy.imageDatum = null;
            }
        }
        return draftCopy;
    }

    public Object createNewInstance() {
        return new ImageCRSType();
    }

    public void mergeFrom(Object left, Object right) {
        final MergeStrategy strategy = JAXBMergeStrategy.INSTANCE;
        mergeFrom(null, null, left, right, strategy);
    }

    public void mergeFrom(ObjectLocator leftLocator, ObjectLocator rightLocator, Object left, Object right, MergeStrategy strategy) {
        super.mergeFrom(leftLocator, rightLocator, left, right, strategy);
        if (right instanceof ImageCRSType) {
            final ImageCRSType target = this;
            final ImageCRSType leftObject = ((ImageCRSType) left);
            final ImageCRSType rightObject = ((ImageCRSType) right);
            {
                JAXBElement<CartesianCSPropertyType> lhsCartesianCS;
                lhsCartesianCS = leftObject.getCartesianCS();
                JAXBElement<CartesianCSPropertyType> rhsCartesianCS;
                rhsCartesianCS = rightObject.getCartesianCS();
                target.setCartesianCS(((JAXBElement<CartesianCSPropertyType> ) strategy.merge(LocatorUtils.property(leftLocator, "cartesianCS", lhsCartesianCS), LocatorUtils.property(rightLocator, "cartesianCS", rhsCartesianCS), lhsCartesianCS, rhsCartesianCS)));
            }
            {
                JAXBElement<AffineCSPropertyType> lhsAffineCS;
                lhsAffineCS = leftObject.getAffineCS();
                JAXBElement<AffineCSPropertyType> rhsAffineCS;
                rhsAffineCS = rightObject.getAffineCS();
                target.setAffineCS(((JAXBElement<AffineCSPropertyType> ) strategy.merge(LocatorUtils.property(leftLocator, "affineCS", lhsAffineCS), LocatorUtils.property(rightLocator, "affineCS", rhsAffineCS), lhsAffineCS, rhsAffineCS)));
            }
            {
                ObliqueCartesianCSPropertyType lhsUsesObliqueCartesianCS;
                lhsUsesObliqueCartesianCS = leftObject.getUsesObliqueCartesianCS();
                ObliqueCartesianCSPropertyType rhsUsesObliqueCartesianCS;
                rhsUsesObliqueCartesianCS = rightObject.getUsesObliqueCartesianCS();
                target.setUsesObliqueCartesianCS(((ObliqueCartesianCSPropertyType) strategy.merge(LocatorUtils.property(leftLocator, "usesObliqueCartesianCS", lhsUsesObliqueCartesianCS), LocatorUtils.property(rightLocator, "usesObliqueCartesianCS", rhsUsesObliqueCartesianCS), lhsUsesObliqueCartesianCS, rhsUsesObliqueCartesianCS)));
            }
            {
                JAXBElement<ImageDatumPropertyType> lhsImageDatum;
                lhsImageDatum = leftObject.getImageDatum();
                JAXBElement<ImageDatumPropertyType> rhsImageDatum;
                rhsImageDatum = rightObject.getImageDatum();
                target.setImageDatum(((JAXBElement<ImageDatumPropertyType> ) strategy.merge(LocatorUtils.property(leftLocator, "imageDatum", lhsImageDatum), LocatorUtils.property(rightLocator, "imageDatum", rhsImageDatum), lhsImageDatum, rhsImageDatum)));
            }
        }
    }

    public ImageCRSType withCartesianCS(JAXBElement<CartesianCSPropertyType> value) {
        setCartesianCS(value);
        return this;
    }

    public ImageCRSType withAffineCS(JAXBElement<AffineCSPropertyType> value) {
        setAffineCS(value);
        return this;
    }

    public ImageCRSType withUsesObliqueCartesianCS(ObliqueCartesianCSPropertyType value) {
        setUsesObliqueCartesianCS(value);
        return this;
    }

    public ImageCRSType withImageDatum(JAXBElement<ImageDatumPropertyType> value) {
        setImageDatum(value);
        return this;
    }

    @Override
    public ImageCRSType withDomainOfValidity(DomainOfValidityElement... values) {
        if (values!= null) {
            for (DomainOfValidityElement value: values) {
                getDomainOfValidity().add(value);
            }
        }
        return this;
    }

    @Override
    public ImageCRSType withDomainOfValidity(Collection<DomainOfValidityElement> values) {
        if (values!= null) {
            getDomainOfValidity().addAll(values);
        }
        return this;
    }

    @Override
    public ImageCRSType withScope(String... values) {
        if (values!= null) {
            for (String value: values) {
                getScope().add(value);
            }
        }
        return this;
    }

    @Override
    public ImageCRSType withScope(Collection<String> values) {
        if (values!= null) {
            getScope().addAll(values);
        }
        return this;
    }

    @Override
    public ImageCRSType withDomainOfValidity(List<DomainOfValidityElement> value) {
        setDomainOfValidity(value);
        return this;
    }

    @Override
    public ImageCRSType withScope(List<String> value) {
        setScope(value);
        return this;
    }

    @Override
    public ImageCRSType withRemarks(String value) {
        setRemarks(value);
        return this;
    }

    @Override
    public ImageCRSType withMetaDataProperty(MetaDataPropertyType... values) {
        if (values!= null) {
            for (MetaDataPropertyType value: values) {
                getMetaDataProperty().add(value);
            }
        }
        return this;
    }

    @Override
    public ImageCRSType withMetaDataProperty(Collection<MetaDataPropertyType> values) {
        if (values!= null) {
            getMetaDataProperty().addAll(values);
        }
        return this;
    }

    @Override
    public ImageCRSType withDescription(StringOrRefType value) {
        setDescription(value);
        return this;
    }

    @Override
    public ImageCRSType withDescriptionReference(ReferenceType value) {
        setDescriptionReference(value);
        return this;
    }

    @Override
    public ImageCRSType withIdentifier(CodeWithAuthorityType value) {
        setIdentifier(value);
        return this;
    }

    @Override
    public ImageCRSType withName(CodeType... values) {
        if (values!= null) {
            for (CodeType value: values) {
                getName().add(value);
            }
        }
        return this;
    }

    @Override
    public ImageCRSType withName(Collection<CodeType> values) {
        if (values!= null) {
            getName().addAll(values);
        }
        return this;
    }

    @Override
    public ImageCRSType withId(String value) {
        setId(value);
        return this;
    }

    @Override
    public ImageCRSType withMetaDataProperty(List<MetaDataPropertyType> value) {
        setMetaDataProperty(value);
        return this;
    }

    @Override
    public ImageCRSType withName(List<CodeType> value) {
        setName(value);
        return this;
    }

}
