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
 * <p>Classe Java per VerticalCRSType complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="VerticalCRSType">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.opengis.net/gml}AbstractCRSType">
 *       &lt;sequence>
 *         &lt;element ref="{http://www.opengis.net/gml}verticalCS"/>
 *         &lt;element ref="{http://www.opengis.net/gml}verticalDatum"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "VerticalCRSType", propOrder = {
    "verticalCS",
    "verticalDatum"
})
public class VerticalCRSType
    extends AbstractCRSType
    implements Cloneable, CopyTo, Equals, HashCode, MergeFrom, ToString
{

    @XmlElementRef(name = "verticalCS", namespace = "http://www.opengis.net/gml", type = JAXBElement.class)
    protected JAXBElement<VerticalCSPropertyType> verticalCS;
    @XmlElementRef(name = "verticalDatum", namespace = "http://www.opengis.net/gml", type = JAXBElement.class)
    protected JAXBElement<VerticalDatumPropertyType> verticalDatum;

    /**
     * Recupera il valore della proprietà verticalCS.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link VerticalCSPropertyType }{@code >}
     *     {@link JAXBElement }{@code <}{@link VerticalCSPropertyType }{@code >}
     *     
     */
    public JAXBElement<VerticalCSPropertyType> getVerticalCS() {
        return verticalCS;
    }

    /**
     * Imposta il valore della proprietà verticalCS.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link VerticalCSPropertyType }{@code >}
     *     {@link JAXBElement }{@code <}{@link VerticalCSPropertyType }{@code >}
     *     
     */
    public void setVerticalCS(JAXBElement<VerticalCSPropertyType> value) {
        this.verticalCS = value;
    }

    public boolean isSetVerticalCS() {
        return (this.verticalCS!= null);
    }

    /**
     * Recupera il valore della proprietà verticalDatum.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link VerticalDatumPropertyType }{@code >}
     *     {@link JAXBElement }{@code <}{@link VerticalDatumPropertyType }{@code >}
     *     
     */
    public JAXBElement<VerticalDatumPropertyType> getVerticalDatum() {
        return verticalDatum;
    }

    /**
     * Imposta il valore della proprietà verticalDatum.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link VerticalDatumPropertyType }{@code >}
     *     {@link JAXBElement }{@code <}{@link VerticalDatumPropertyType }{@code >}
     *     
     */
    public void setVerticalDatum(JAXBElement<VerticalDatumPropertyType> value) {
        this.verticalDatum = value;
    }

    public boolean isSetVerticalDatum() {
        return (this.verticalDatum!= null);
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
            JAXBElement<VerticalCSPropertyType> theVerticalCS;
            theVerticalCS = this.getVerticalCS();
            strategy.appendField(locator, this, "verticalCS", buffer, theVerticalCS);
        }
        {
            JAXBElement<VerticalDatumPropertyType> theVerticalDatum;
            theVerticalDatum = this.getVerticalDatum();
            strategy.appendField(locator, this, "verticalDatum", buffer, theVerticalDatum);
        }
        return buffer;
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof VerticalCRSType)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        if (!super.equals(thisLocator, thatLocator, object, strategy)) {
            return false;
        }
        final VerticalCRSType that = ((VerticalCRSType) object);
        {
            JAXBElement<VerticalCSPropertyType> lhsVerticalCS;
            lhsVerticalCS = this.getVerticalCS();
            JAXBElement<VerticalCSPropertyType> rhsVerticalCS;
            rhsVerticalCS = that.getVerticalCS();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "verticalCS", lhsVerticalCS), LocatorUtils.property(thatLocator, "verticalCS", rhsVerticalCS), lhsVerticalCS, rhsVerticalCS)) {
                return false;
            }
        }
        {
            JAXBElement<VerticalDatumPropertyType> lhsVerticalDatum;
            lhsVerticalDatum = this.getVerticalDatum();
            JAXBElement<VerticalDatumPropertyType> rhsVerticalDatum;
            rhsVerticalDatum = that.getVerticalDatum();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "verticalDatum", lhsVerticalDatum), LocatorUtils.property(thatLocator, "verticalDatum", rhsVerticalDatum), lhsVerticalDatum, rhsVerticalDatum)) {
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
            JAXBElement<VerticalCSPropertyType> theVerticalCS;
            theVerticalCS = this.getVerticalCS();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "verticalCS", theVerticalCS), currentHashCode, theVerticalCS);
        }
        {
            JAXBElement<VerticalDatumPropertyType> theVerticalDatum;
            theVerticalDatum = this.getVerticalDatum();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "verticalDatum", theVerticalDatum), currentHashCode, theVerticalDatum);
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
        if (draftCopy instanceof VerticalCRSType) {
            final VerticalCRSType copy = ((VerticalCRSType) draftCopy);
            if (this.isSetVerticalCS()) {
                JAXBElement<VerticalCSPropertyType> sourceVerticalCS;
                sourceVerticalCS = this.getVerticalCS();
                @SuppressWarnings("unchecked")
                JAXBElement<VerticalCSPropertyType> copyVerticalCS = ((JAXBElement<VerticalCSPropertyType> ) strategy.copy(LocatorUtils.property(locator, "verticalCS", sourceVerticalCS), sourceVerticalCS));
                copy.setVerticalCS(copyVerticalCS);
            } else {
                copy.verticalCS = null;
            }
            if (this.isSetVerticalDatum()) {
                JAXBElement<VerticalDatumPropertyType> sourceVerticalDatum;
                sourceVerticalDatum = this.getVerticalDatum();
                @SuppressWarnings("unchecked")
                JAXBElement<VerticalDatumPropertyType> copyVerticalDatum = ((JAXBElement<VerticalDatumPropertyType> ) strategy.copy(LocatorUtils.property(locator, "verticalDatum", sourceVerticalDatum), sourceVerticalDatum));
                copy.setVerticalDatum(copyVerticalDatum);
            } else {
                copy.verticalDatum = null;
            }
        }
        return draftCopy;
    }

    public Object createNewInstance() {
        return new VerticalCRSType();
    }

    public void mergeFrom(Object left, Object right) {
        final MergeStrategy strategy = JAXBMergeStrategy.INSTANCE;
        mergeFrom(null, null, left, right, strategy);
    }

    public void mergeFrom(ObjectLocator leftLocator, ObjectLocator rightLocator, Object left, Object right, MergeStrategy strategy) {
        super.mergeFrom(leftLocator, rightLocator, left, right, strategy);
        if (right instanceof VerticalCRSType) {
            final VerticalCRSType target = this;
            final VerticalCRSType leftObject = ((VerticalCRSType) left);
            final VerticalCRSType rightObject = ((VerticalCRSType) right);
            {
                JAXBElement<VerticalCSPropertyType> lhsVerticalCS;
                lhsVerticalCS = leftObject.getVerticalCS();
                JAXBElement<VerticalCSPropertyType> rhsVerticalCS;
                rhsVerticalCS = rightObject.getVerticalCS();
                target.setVerticalCS(((JAXBElement<VerticalCSPropertyType> ) strategy.merge(LocatorUtils.property(leftLocator, "verticalCS", lhsVerticalCS), LocatorUtils.property(rightLocator, "verticalCS", rhsVerticalCS), lhsVerticalCS, rhsVerticalCS)));
            }
            {
                JAXBElement<VerticalDatumPropertyType> lhsVerticalDatum;
                lhsVerticalDatum = leftObject.getVerticalDatum();
                JAXBElement<VerticalDatumPropertyType> rhsVerticalDatum;
                rhsVerticalDatum = rightObject.getVerticalDatum();
                target.setVerticalDatum(((JAXBElement<VerticalDatumPropertyType> ) strategy.merge(LocatorUtils.property(leftLocator, "verticalDatum", lhsVerticalDatum), LocatorUtils.property(rightLocator, "verticalDatum", rhsVerticalDatum), lhsVerticalDatum, rhsVerticalDatum)));
            }
        }
    }

    public VerticalCRSType withVerticalCS(JAXBElement<VerticalCSPropertyType> value) {
        setVerticalCS(value);
        return this;
    }

    public VerticalCRSType withVerticalDatum(JAXBElement<VerticalDatumPropertyType> value) {
        setVerticalDatum(value);
        return this;
    }

    @Override
    public VerticalCRSType withDomainOfValidity(DomainOfValidityElement... values) {
        if (values!= null) {
            for (DomainOfValidityElement value: values) {
                getDomainOfValidity().add(value);
            }
        }
        return this;
    }

    @Override
    public VerticalCRSType withDomainOfValidity(Collection<DomainOfValidityElement> values) {
        if (values!= null) {
            getDomainOfValidity().addAll(values);
        }
        return this;
    }

    @Override
    public VerticalCRSType withScope(String... values) {
        if (values!= null) {
            for (String value: values) {
                getScope().add(value);
            }
        }
        return this;
    }

    @Override
    public VerticalCRSType withScope(Collection<String> values) {
        if (values!= null) {
            getScope().addAll(values);
        }
        return this;
    }

    @Override
    public VerticalCRSType withDomainOfValidity(List<DomainOfValidityElement> value) {
        setDomainOfValidity(value);
        return this;
    }

    @Override
    public VerticalCRSType withScope(List<String> value) {
        setScope(value);
        return this;
    }

    @Override
    public VerticalCRSType withRemarks(String value) {
        setRemarks(value);
        return this;
    }

    @Override
    public VerticalCRSType withMetaDataProperty(MetaDataPropertyType... values) {
        if (values!= null) {
            for (MetaDataPropertyType value: values) {
                getMetaDataProperty().add(value);
            }
        }
        return this;
    }

    @Override
    public VerticalCRSType withMetaDataProperty(Collection<MetaDataPropertyType> values) {
        if (values!= null) {
            getMetaDataProperty().addAll(values);
        }
        return this;
    }

    @Override
    public VerticalCRSType withDescription(StringOrRefType value) {
        setDescription(value);
        return this;
    }

    @Override
    public VerticalCRSType withDescriptionReference(ReferenceType value) {
        setDescriptionReference(value);
        return this;
    }

    @Override
    public VerticalCRSType withIdentifier(CodeWithAuthorityType value) {
        setIdentifier(value);
        return this;
    }

    @Override
    public VerticalCRSType withName(CodeType... values) {
        if (values!= null) {
            for (CodeType value: values) {
                getName().add(value);
            }
        }
        return this;
    }

    @Override
    public VerticalCRSType withName(Collection<CodeType> values) {
        if (values!= null) {
            getName().addAll(values);
        }
        return this;
    }

    @Override
    public VerticalCRSType withId(String value) {
        setId(value);
        return this;
    }

    @Override
    public VerticalCRSType withMetaDataProperty(List<MetaDataPropertyType> value) {
        setMetaDataProperty(value);
        return this;
    }

    @Override
    public VerticalCRSType withName(List<CodeType> value) {
        setName(value);
        return this;
    }

}
