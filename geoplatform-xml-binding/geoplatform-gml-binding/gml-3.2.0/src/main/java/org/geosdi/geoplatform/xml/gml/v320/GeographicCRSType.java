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
 * <p>Classe Java per GeographicCRSType complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="GeographicCRSType">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.opengis.net/gml}AbstractCRSType">
 *       &lt;sequence>
 *         &lt;element ref="{http://www.opengis.net/gml}usesEllipsoidalCS"/>
 *         &lt;element ref="{http://www.opengis.net/gml}usesGeodeticDatum"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "GeographicCRSType", propOrder = {
    "usesEllipsoidalCS",
    "usesGeodeticDatum"
})
public class GeographicCRSType
    extends AbstractCRSType
    implements Cloneable, CopyTo, Equals, HashCode, MergeFrom, ToString
{

    @XmlElement(required = true)
    protected EllipsoidalCSPropertyType usesEllipsoidalCS;
    @XmlElement(required = true)
    protected GeodeticDatumPropertyType usesGeodeticDatum;

    /**
     * Recupera il valore della proprietà usesEllipsoidalCS.
     * 
     * @return
     *     possible object is
     *     {@link EllipsoidalCSPropertyType }
     *     
     */
    public EllipsoidalCSPropertyType getUsesEllipsoidalCS() {
        return usesEllipsoidalCS;
    }

    /**
     * Imposta il valore della proprietà usesEllipsoidalCS.
     * 
     * @param value
     *     allowed object is
     *     {@link EllipsoidalCSPropertyType }
     *     
     */
    public void setUsesEllipsoidalCS(EllipsoidalCSPropertyType value) {
        this.usesEllipsoidalCS = value;
    }

    public boolean isSetUsesEllipsoidalCS() {
        return (this.usesEllipsoidalCS!= null);
    }

    /**
     * Recupera il valore della proprietà usesGeodeticDatum.
     * 
     * @return
     *     possible object is
     *     {@link GeodeticDatumPropertyType }
     *     
     */
    public GeodeticDatumPropertyType getUsesGeodeticDatum() {
        return usesGeodeticDatum;
    }

    /**
     * Imposta il valore della proprietà usesGeodeticDatum.
     * 
     * @param value
     *     allowed object is
     *     {@link GeodeticDatumPropertyType }
     *     
     */
    public void setUsesGeodeticDatum(GeodeticDatumPropertyType value) {
        this.usesGeodeticDatum = value;
    }

    public boolean isSetUsesGeodeticDatum() {
        return (this.usesGeodeticDatum!= null);
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
            EllipsoidalCSPropertyType theUsesEllipsoidalCS;
            theUsesEllipsoidalCS = this.getUsesEllipsoidalCS();
            strategy.appendField(locator, this, "usesEllipsoidalCS", buffer, theUsesEllipsoidalCS);
        }
        {
            GeodeticDatumPropertyType theUsesGeodeticDatum;
            theUsesGeodeticDatum = this.getUsesGeodeticDatum();
            strategy.appendField(locator, this, "usesGeodeticDatum", buffer, theUsesGeodeticDatum);
        }
        return buffer;
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof GeographicCRSType)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        if (!super.equals(thisLocator, thatLocator, object, strategy)) {
            return false;
        }
        final GeographicCRSType that = ((GeographicCRSType) object);
        {
            EllipsoidalCSPropertyType lhsUsesEllipsoidalCS;
            lhsUsesEllipsoidalCS = this.getUsesEllipsoidalCS();
            EllipsoidalCSPropertyType rhsUsesEllipsoidalCS;
            rhsUsesEllipsoidalCS = that.getUsesEllipsoidalCS();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "usesEllipsoidalCS", lhsUsesEllipsoidalCS), LocatorUtils.property(thatLocator, "usesEllipsoidalCS", rhsUsesEllipsoidalCS), lhsUsesEllipsoidalCS, rhsUsesEllipsoidalCS)) {
                return false;
            }
        }
        {
            GeodeticDatumPropertyType lhsUsesGeodeticDatum;
            lhsUsesGeodeticDatum = this.getUsesGeodeticDatum();
            GeodeticDatumPropertyType rhsUsesGeodeticDatum;
            rhsUsesGeodeticDatum = that.getUsesGeodeticDatum();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "usesGeodeticDatum", lhsUsesGeodeticDatum), LocatorUtils.property(thatLocator, "usesGeodeticDatum", rhsUsesGeodeticDatum), lhsUsesGeodeticDatum, rhsUsesGeodeticDatum)) {
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
            EllipsoidalCSPropertyType theUsesEllipsoidalCS;
            theUsesEllipsoidalCS = this.getUsesEllipsoidalCS();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "usesEllipsoidalCS", theUsesEllipsoidalCS), currentHashCode, theUsesEllipsoidalCS);
        }
        {
            GeodeticDatumPropertyType theUsesGeodeticDatum;
            theUsesGeodeticDatum = this.getUsesGeodeticDatum();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "usesGeodeticDatum", theUsesGeodeticDatum), currentHashCode, theUsesGeodeticDatum);
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
        if (draftCopy instanceof GeographicCRSType) {
            final GeographicCRSType copy = ((GeographicCRSType) draftCopy);
            if (this.isSetUsesEllipsoidalCS()) {
                EllipsoidalCSPropertyType sourceUsesEllipsoidalCS;
                sourceUsesEllipsoidalCS = this.getUsesEllipsoidalCS();
                EllipsoidalCSPropertyType copyUsesEllipsoidalCS = ((EllipsoidalCSPropertyType) strategy.copy(LocatorUtils.property(locator, "usesEllipsoidalCS", sourceUsesEllipsoidalCS), sourceUsesEllipsoidalCS));
                copy.setUsesEllipsoidalCS(copyUsesEllipsoidalCS);
            } else {
                copy.usesEllipsoidalCS = null;
            }
            if (this.isSetUsesGeodeticDatum()) {
                GeodeticDatumPropertyType sourceUsesGeodeticDatum;
                sourceUsesGeodeticDatum = this.getUsesGeodeticDatum();
                GeodeticDatumPropertyType copyUsesGeodeticDatum = ((GeodeticDatumPropertyType) strategy.copy(LocatorUtils.property(locator, "usesGeodeticDatum", sourceUsesGeodeticDatum), sourceUsesGeodeticDatum));
                copy.setUsesGeodeticDatum(copyUsesGeodeticDatum);
            } else {
                copy.usesGeodeticDatum = null;
            }
        }
        return draftCopy;
    }

    public Object createNewInstance() {
        return new GeographicCRSType();
    }

    public void mergeFrom(Object left, Object right) {
        final MergeStrategy strategy = JAXBMergeStrategy.INSTANCE;
        mergeFrom(null, null, left, right, strategy);
    }

    public void mergeFrom(ObjectLocator leftLocator, ObjectLocator rightLocator, Object left, Object right, MergeStrategy strategy) {
        super.mergeFrom(leftLocator, rightLocator, left, right, strategy);
        if (right instanceof GeographicCRSType) {
            final GeographicCRSType target = this;
            final GeographicCRSType leftObject = ((GeographicCRSType) left);
            final GeographicCRSType rightObject = ((GeographicCRSType) right);
            {
                EllipsoidalCSPropertyType lhsUsesEllipsoidalCS;
                lhsUsesEllipsoidalCS = leftObject.getUsesEllipsoidalCS();
                EllipsoidalCSPropertyType rhsUsesEllipsoidalCS;
                rhsUsesEllipsoidalCS = rightObject.getUsesEllipsoidalCS();
                target.setUsesEllipsoidalCS(((EllipsoidalCSPropertyType) strategy.merge(LocatorUtils.property(leftLocator, "usesEllipsoidalCS", lhsUsesEllipsoidalCS), LocatorUtils.property(rightLocator, "usesEllipsoidalCS", rhsUsesEllipsoidalCS), lhsUsesEllipsoidalCS, rhsUsesEllipsoidalCS)));
            }
            {
                GeodeticDatumPropertyType lhsUsesGeodeticDatum;
                lhsUsesGeodeticDatum = leftObject.getUsesGeodeticDatum();
                GeodeticDatumPropertyType rhsUsesGeodeticDatum;
                rhsUsesGeodeticDatum = rightObject.getUsesGeodeticDatum();
                target.setUsesGeodeticDatum(((GeodeticDatumPropertyType) strategy.merge(LocatorUtils.property(leftLocator, "usesGeodeticDatum", lhsUsesGeodeticDatum), LocatorUtils.property(rightLocator, "usesGeodeticDatum", rhsUsesGeodeticDatum), lhsUsesGeodeticDatum, rhsUsesGeodeticDatum)));
            }
        }
    }

    public GeographicCRSType withUsesEllipsoidalCS(EllipsoidalCSPropertyType value) {
        setUsesEllipsoidalCS(value);
        return this;
    }

    public GeographicCRSType withUsesGeodeticDatum(GeodeticDatumPropertyType value) {
        setUsesGeodeticDatum(value);
        return this;
    }

    @Override
    public GeographicCRSType withDomainOfValidity(DomainOfValidityElement... values) {
        if (values!= null) {
            for (DomainOfValidityElement value: values) {
                getDomainOfValidity().add(value);
            }
        }
        return this;
    }

    @Override
    public GeographicCRSType withDomainOfValidity(Collection<DomainOfValidityElement> values) {
        if (values!= null) {
            getDomainOfValidity().addAll(values);
        }
        return this;
    }

    @Override
    public GeographicCRSType withScope(String... values) {
        if (values!= null) {
            for (String value: values) {
                getScope().add(value);
            }
        }
        return this;
    }

    @Override
    public GeographicCRSType withScope(Collection<String> values) {
        if (values!= null) {
            getScope().addAll(values);
        }
        return this;
    }

    @Override
    public GeographicCRSType withDomainOfValidity(List<DomainOfValidityElement> value) {
        setDomainOfValidity(value);
        return this;
    }

    @Override
    public GeographicCRSType withScope(List<String> value) {
        setScope(value);
        return this;
    }

    @Override
    public GeographicCRSType withRemarks(String value) {
        setRemarks(value);
        return this;
    }

    @Override
    public GeographicCRSType withMetaDataProperty(MetaDataPropertyType... values) {
        if (values!= null) {
            for (MetaDataPropertyType value: values) {
                getMetaDataProperty().add(value);
            }
        }
        return this;
    }

    @Override
    public GeographicCRSType withMetaDataProperty(Collection<MetaDataPropertyType> values) {
        if (values!= null) {
            getMetaDataProperty().addAll(values);
        }
        return this;
    }

    @Override
    public GeographicCRSType withDescription(StringOrRefType value) {
        setDescription(value);
        return this;
    }

    @Override
    public GeographicCRSType withDescriptionReference(ReferenceType value) {
        setDescriptionReference(value);
        return this;
    }

    @Override
    public GeographicCRSType withIdentifier(CodeWithAuthorityType value) {
        setIdentifier(value);
        return this;
    }

    @Override
    public GeographicCRSType withName(CodeType... values) {
        if (values!= null) {
            for (CodeType value: values) {
                getName().add(value);
            }
        }
        return this;
    }

    @Override
    public GeographicCRSType withName(Collection<CodeType> values) {
        if (values!= null) {
            getName().addAll(values);
        }
        return this;
    }

    @Override
    public GeographicCRSType withId(String value) {
        setId(value);
        return this;
    }

    @Override
    public GeographicCRSType withMetaDataProperty(List<MetaDataPropertyType> value) {
        setMetaDataProperty(value);
        return this;
    }

    @Override
    public GeographicCRSType withName(List<CodeType> value) {
        setName(value);
        return this;
    }

}
