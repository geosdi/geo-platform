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
 * <p>Classe Java per TemporalCRSType complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="TemporalCRSType">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.opengis.net/gml}AbstractCRSType">
 *       &lt;sequence>
 *         &lt;choice>
 *           &lt;element ref="{http://www.opengis.net/gml}timeCS"/>
 *           &lt;element ref="{http://www.opengis.net/gml}usesTemporalCS"/>
 *         &lt;/choice>
 *         &lt;element ref="{http://www.opengis.net/gml}temporalDatum"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TemporalCRSType", propOrder = {
    "timeCS",
    "usesTemporalCS",
    "temporalDatum"
})
public class TemporalCRSType
    extends AbstractCRSType
    implements Cloneable, CopyTo, Equals, HashCode, MergeFrom, ToString
{

    @XmlElementRef(name = "timeCS", namespace = "http://www.opengis.net/gml", type = JAXBElement.class, required = false)
    protected JAXBElement<TimeCSPropertyType> timeCS;
    protected TemporalCSPropertyType usesTemporalCS;
    @XmlElementRef(name = "temporalDatum", namespace = "http://www.opengis.net/gml", type = JAXBElement.class)
    protected JAXBElement<TemporalDatumPropertyType> temporalDatum;

    /**
     * Recupera il valore della proprietà timeCS.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link TimeCSPropertyType }{@code >}
     *     {@link JAXBElement }{@code <}{@link TimeCSPropertyType }{@code >}
     *     
     */
    public JAXBElement<TimeCSPropertyType> getTimeCS() {
        return timeCS;
    }

    /**
     * Imposta il valore della proprietà timeCS.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link TimeCSPropertyType }{@code >}
     *     {@link JAXBElement }{@code <}{@link TimeCSPropertyType }{@code >}
     *     
     */
    public void setTimeCS(JAXBElement<TimeCSPropertyType> value) {
        this.timeCS = value;
    }

    public boolean isSetTimeCS() {
        return (this.timeCS!= null);
    }

    /**
     * Recupera il valore della proprietà usesTemporalCS.
     * 
     * @return
     *     possible object is
     *     {@link TemporalCSPropertyType }
     *     
     */
    public TemporalCSPropertyType getUsesTemporalCS() {
        return usesTemporalCS;
    }

    /**
     * Imposta il valore della proprietà usesTemporalCS.
     * 
     * @param value
     *     allowed object is
     *     {@link TemporalCSPropertyType }
     *     
     */
    public void setUsesTemporalCS(TemporalCSPropertyType value) {
        this.usesTemporalCS = value;
    }

    public boolean isSetUsesTemporalCS() {
        return (this.usesTemporalCS!= null);
    }

    /**
     * Recupera il valore della proprietà temporalDatum.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link TemporalDatumPropertyType }{@code >}
     *     {@link JAXBElement }{@code <}{@link TemporalDatumPropertyType }{@code >}
     *     
     */
    public JAXBElement<TemporalDatumPropertyType> getTemporalDatum() {
        return temporalDatum;
    }

    /**
     * Imposta il valore della proprietà temporalDatum.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link TemporalDatumPropertyType }{@code >}
     *     {@link JAXBElement }{@code <}{@link TemporalDatumPropertyType }{@code >}
     *     
     */
    public void setTemporalDatum(JAXBElement<TemporalDatumPropertyType> value) {
        this.temporalDatum = value;
    }

    public boolean isSetTemporalDatum() {
        return (this.temporalDatum!= null);
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
            JAXBElement<TimeCSPropertyType> theTimeCS;
            theTimeCS = this.getTimeCS();
            strategy.appendField(locator, this, "timeCS", buffer, theTimeCS);
        }
        {
            TemporalCSPropertyType theUsesTemporalCS;
            theUsesTemporalCS = this.getUsesTemporalCS();
            strategy.appendField(locator, this, "usesTemporalCS", buffer, theUsesTemporalCS);
        }
        {
            JAXBElement<TemporalDatumPropertyType> theTemporalDatum;
            theTemporalDatum = this.getTemporalDatum();
            strategy.appendField(locator, this, "temporalDatum", buffer, theTemporalDatum);
        }
        return buffer;
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof TemporalCRSType)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        if (!super.equals(thisLocator, thatLocator, object, strategy)) {
            return false;
        }
        final TemporalCRSType that = ((TemporalCRSType) object);
        {
            JAXBElement<TimeCSPropertyType> lhsTimeCS;
            lhsTimeCS = this.getTimeCS();
            JAXBElement<TimeCSPropertyType> rhsTimeCS;
            rhsTimeCS = that.getTimeCS();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "timeCS", lhsTimeCS), LocatorUtils.property(thatLocator, "timeCS", rhsTimeCS), lhsTimeCS, rhsTimeCS)) {
                return false;
            }
        }
        {
            TemporalCSPropertyType lhsUsesTemporalCS;
            lhsUsesTemporalCS = this.getUsesTemporalCS();
            TemporalCSPropertyType rhsUsesTemporalCS;
            rhsUsesTemporalCS = that.getUsesTemporalCS();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "usesTemporalCS", lhsUsesTemporalCS), LocatorUtils.property(thatLocator, "usesTemporalCS", rhsUsesTemporalCS), lhsUsesTemporalCS, rhsUsesTemporalCS)) {
                return false;
            }
        }
        {
            JAXBElement<TemporalDatumPropertyType> lhsTemporalDatum;
            lhsTemporalDatum = this.getTemporalDatum();
            JAXBElement<TemporalDatumPropertyType> rhsTemporalDatum;
            rhsTemporalDatum = that.getTemporalDatum();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "temporalDatum", lhsTemporalDatum), LocatorUtils.property(thatLocator, "temporalDatum", rhsTemporalDatum), lhsTemporalDatum, rhsTemporalDatum)) {
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
            JAXBElement<TimeCSPropertyType> theTimeCS;
            theTimeCS = this.getTimeCS();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "timeCS", theTimeCS), currentHashCode, theTimeCS);
        }
        {
            TemporalCSPropertyType theUsesTemporalCS;
            theUsesTemporalCS = this.getUsesTemporalCS();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "usesTemporalCS", theUsesTemporalCS), currentHashCode, theUsesTemporalCS);
        }
        {
            JAXBElement<TemporalDatumPropertyType> theTemporalDatum;
            theTemporalDatum = this.getTemporalDatum();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "temporalDatum", theTemporalDatum), currentHashCode, theTemporalDatum);
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
        if (draftCopy instanceof TemporalCRSType) {
            final TemporalCRSType copy = ((TemporalCRSType) draftCopy);
            if (this.isSetTimeCS()) {
                JAXBElement<TimeCSPropertyType> sourceTimeCS;
                sourceTimeCS = this.getTimeCS();
                @SuppressWarnings("unchecked")
                JAXBElement<TimeCSPropertyType> copyTimeCS = ((JAXBElement<TimeCSPropertyType> ) strategy.copy(LocatorUtils.property(locator, "timeCS", sourceTimeCS), sourceTimeCS));
                copy.setTimeCS(copyTimeCS);
            } else {
                copy.timeCS = null;
            }
            if (this.isSetUsesTemporalCS()) {
                TemporalCSPropertyType sourceUsesTemporalCS;
                sourceUsesTemporalCS = this.getUsesTemporalCS();
                TemporalCSPropertyType copyUsesTemporalCS = ((TemporalCSPropertyType) strategy.copy(LocatorUtils.property(locator, "usesTemporalCS", sourceUsesTemporalCS), sourceUsesTemporalCS));
                copy.setUsesTemporalCS(copyUsesTemporalCS);
            } else {
                copy.usesTemporalCS = null;
            }
            if (this.isSetTemporalDatum()) {
                JAXBElement<TemporalDatumPropertyType> sourceTemporalDatum;
                sourceTemporalDatum = this.getTemporalDatum();
                @SuppressWarnings("unchecked")
                JAXBElement<TemporalDatumPropertyType> copyTemporalDatum = ((JAXBElement<TemporalDatumPropertyType> ) strategy.copy(LocatorUtils.property(locator, "temporalDatum", sourceTemporalDatum), sourceTemporalDatum));
                copy.setTemporalDatum(copyTemporalDatum);
            } else {
                copy.temporalDatum = null;
            }
        }
        return draftCopy;
    }

    public Object createNewInstance() {
        return new TemporalCRSType();
    }

    public void mergeFrom(Object left, Object right) {
        final MergeStrategy strategy = JAXBMergeStrategy.INSTANCE;
        mergeFrom(null, null, left, right, strategy);
    }

    public void mergeFrom(ObjectLocator leftLocator, ObjectLocator rightLocator, Object left, Object right, MergeStrategy strategy) {
        super.mergeFrom(leftLocator, rightLocator, left, right, strategy);
        if (right instanceof TemporalCRSType) {
            final TemporalCRSType target = this;
            final TemporalCRSType leftObject = ((TemporalCRSType) left);
            final TemporalCRSType rightObject = ((TemporalCRSType) right);
            {
                JAXBElement<TimeCSPropertyType> lhsTimeCS;
                lhsTimeCS = leftObject.getTimeCS();
                JAXBElement<TimeCSPropertyType> rhsTimeCS;
                rhsTimeCS = rightObject.getTimeCS();
                target.setTimeCS(((JAXBElement<TimeCSPropertyType> ) strategy.merge(LocatorUtils.property(leftLocator, "timeCS", lhsTimeCS), LocatorUtils.property(rightLocator, "timeCS", rhsTimeCS), lhsTimeCS, rhsTimeCS)));
            }
            {
                TemporalCSPropertyType lhsUsesTemporalCS;
                lhsUsesTemporalCS = leftObject.getUsesTemporalCS();
                TemporalCSPropertyType rhsUsesTemporalCS;
                rhsUsesTemporalCS = rightObject.getUsesTemporalCS();
                target.setUsesTemporalCS(((TemporalCSPropertyType) strategy.merge(LocatorUtils.property(leftLocator, "usesTemporalCS", lhsUsesTemporalCS), LocatorUtils.property(rightLocator, "usesTemporalCS", rhsUsesTemporalCS), lhsUsesTemporalCS, rhsUsesTemporalCS)));
            }
            {
                JAXBElement<TemporalDatumPropertyType> lhsTemporalDatum;
                lhsTemporalDatum = leftObject.getTemporalDatum();
                JAXBElement<TemporalDatumPropertyType> rhsTemporalDatum;
                rhsTemporalDatum = rightObject.getTemporalDatum();
                target.setTemporalDatum(((JAXBElement<TemporalDatumPropertyType> ) strategy.merge(LocatorUtils.property(leftLocator, "temporalDatum", lhsTemporalDatum), LocatorUtils.property(rightLocator, "temporalDatum", rhsTemporalDatum), lhsTemporalDatum, rhsTemporalDatum)));
            }
        }
    }

    public TemporalCRSType withTimeCS(JAXBElement<TimeCSPropertyType> value) {
        setTimeCS(value);
        return this;
    }

    public TemporalCRSType withUsesTemporalCS(TemporalCSPropertyType value) {
        setUsesTemporalCS(value);
        return this;
    }

    public TemporalCRSType withTemporalDatum(JAXBElement<TemporalDatumPropertyType> value) {
        setTemporalDatum(value);
        return this;
    }

    @Override
    public TemporalCRSType withDomainOfValidity(DomainOfValidityElement... values) {
        if (values!= null) {
            for (DomainOfValidityElement value: values) {
                getDomainOfValidity().add(value);
            }
        }
        return this;
    }

    @Override
    public TemporalCRSType withDomainOfValidity(Collection<DomainOfValidityElement> values) {
        if (values!= null) {
            getDomainOfValidity().addAll(values);
        }
        return this;
    }

    @Override
    public TemporalCRSType withScope(String... values) {
        if (values!= null) {
            for (String value: values) {
                getScope().add(value);
            }
        }
        return this;
    }

    @Override
    public TemporalCRSType withScope(Collection<String> values) {
        if (values!= null) {
            getScope().addAll(values);
        }
        return this;
    }

    @Override
    public TemporalCRSType withDomainOfValidity(List<DomainOfValidityElement> value) {
        setDomainOfValidity(value);
        return this;
    }

    @Override
    public TemporalCRSType withScope(List<String> value) {
        setScope(value);
        return this;
    }

    @Override
    public TemporalCRSType withRemarks(String value) {
        setRemarks(value);
        return this;
    }

    @Override
    public TemporalCRSType withMetaDataProperty(MetaDataPropertyType... values) {
        if (values!= null) {
            for (MetaDataPropertyType value: values) {
                getMetaDataProperty().add(value);
            }
        }
        return this;
    }

    @Override
    public TemporalCRSType withMetaDataProperty(Collection<MetaDataPropertyType> values) {
        if (values!= null) {
            getMetaDataProperty().addAll(values);
        }
        return this;
    }

    @Override
    public TemporalCRSType withDescription(StringOrRefType value) {
        setDescription(value);
        return this;
    }

    @Override
    public TemporalCRSType withDescriptionReference(ReferenceType value) {
        setDescriptionReference(value);
        return this;
    }

    @Override
    public TemporalCRSType withIdentifier(CodeWithAuthorityType value) {
        setIdentifier(value);
        return this;
    }

    @Override
    public TemporalCRSType withName(CodeType... values) {
        if (values!= null) {
            for (CodeType value: values) {
                getName().add(value);
            }
        }
        return this;
    }

    @Override
    public TemporalCRSType withName(Collection<CodeType> values) {
        if (values!= null) {
            getName().addAll(values);
        }
        return this;
    }

    @Override
    public TemporalCRSType withId(String value) {
        setId(value);
        return this;
    }

    @Override
    public TemporalCRSType withMetaDataProperty(List<MetaDataPropertyType> value) {
        setMetaDataProperty(value);
        return this;
    }

    @Override
    public TemporalCRSType withName(List<CodeType> value) {
        setName(value);
        return this;
    }

}
