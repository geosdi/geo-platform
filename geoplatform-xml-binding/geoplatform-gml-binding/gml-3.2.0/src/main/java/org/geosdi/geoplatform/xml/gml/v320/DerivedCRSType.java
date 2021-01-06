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
import javax.xml.bind.annotation.XmlElement;
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
 * <p>Classe Java per DerivedCRSType complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="DerivedCRSType">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.opengis.net/gml}AbstractGeneralDerivedCRSType">
 *       &lt;sequence>
 *         &lt;element ref="{http://www.opengis.net/gml}baseCRS"/>
 *         &lt;element ref="{http://www.opengis.net/gml}derivedCRSType"/>
 *         &lt;element ref="{http://www.opengis.net/gml}coordinateSystem"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DerivedCRSType", propOrder = {
    "baseCRS",
    "derivedCRSType",
    "coordinateSystem"
})
public class DerivedCRSType
    extends AbstractGeneralDerivedCRSType
    implements Cloneable, CopyTo, Equals, HashCode, MergeFrom, ToString
{

    @XmlElement(required = true)
    protected SingleCRSPropertyType baseCRS;
    @XmlElement(required = true)
    protected CodeWithAuthorityType derivedCRSType;
    @XmlElementRef(name = "coordinateSystem", namespace = "http://www.opengis.net/gml", type = JAXBElement.class)
    protected JAXBElement<CoordinateSystemPropertyType> coordinateSystem;

    /**
     * Recupera il valore della proprietà baseCRS.
     * 
     * @return
     *     possible object is
     *     {@link SingleCRSPropertyType }
     *     
     */
    public SingleCRSPropertyType getBaseCRS() {
        return baseCRS;
    }

    /**
     * Imposta il valore della proprietà baseCRS.
     * 
     * @param value
     *     allowed object is
     *     {@link SingleCRSPropertyType }
     *     
     */
    public void setBaseCRS(SingleCRSPropertyType value) {
        this.baseCRS = value;
    }

    public boolean isSetBaseCRS() {
        return (this.baseCRS!= null);
    }

    /**
     * Recupera il valore della proprietà derivedCRSType.
     * 
     * @return
     *     possible object is
     *     {@link CodeWithAuthorityType }
     *     
     */
    public CodeWithAuthorityType getDerivedCRSType() {
        return derivedCRSType;
    }

    /**
     * Imposta il valore della proprietà derivedCRSType.
     * 
     * @param value
     *     allowed object is
     *     {@link CodeWithAuthorityType }
     *     
     */
    public void setDerivedCRSType(CodeWithAuthorityType value) {
        this.derivedCRSType = value;
    }

    public boolean isSetDerivedCRSType() {
        return (this.derivedCRSType!= null);
    }

    /**
     * Recupera il valore della proprietà coordinateSystem.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link CoordinateSystemPropertyType }{@code >}
     *     {@link JAXBElement }{@code <}{@link CoordinateSystemPropertyType }{@code >}
     *     
     */
    public JAXBElement<CoordinateSystemPropertyType> getCoordinateSystem() {
        return coordinateSystem;
    }

    /**
     * Imposta il valore della proprietà coordinateSystem.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link CoordinateSystemPropertyType }{@code >}
     *     {@link JAXBElement }{@code <}{@link CoordinateSystemPropertyType }{@code >}
     *     
     */
    public void setCoordinateSystem(JAXBElement<CoordinateSystemPropertyType> value) {
        this.coordinateSystem = value;
    }

    public boolean isSetCoordinateSystem() {
        return (this.coordinateSystem!= null);
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
            SingleCRSPropertyType theBaseCRS;
            theBaseCRS = this.getBaseCRS();
            strategy.appendField(locator, this, "baseCRS", buffer, theBaseCRS);
        }
        {
            CodeWithAuthorityType theDerivedCRSType;
            theDerivedCRSType = this.getDerivedCRSType();
            strategy.appendField(locator, this, "derivedCRSType", buffer, theDerivedCRSType);
        }
        {
            JAXBElement<CoordinateSystemPropertyType> theCoordinateSystem;
            theCoordinateSystem = this.getCoordinateSystem();
            strategy.appendField(locator, this, "coordinateSystem", buffer, theCoordinateSystem);
        }
        return buffer;
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof DerivedCRSType)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        if (!super.equals(thisLocator, thatLocator, object, strategy)) {
            return false;
        }
        final DerivedCRSType that = ((DerivedCRSType) object);
        {
            SingleCRSPropertyType lhsBaseCRS;
            lhsBaseCRS = this.getBaseCRS();
            SingleCRSPropertyType rhsBaseCRS;
            rhsBaseCRS = that.getBaseCRS();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "baseCRS", lhsBaseCRS), LocatorUtils.property(thatLocator, "baseCRS", rhsBaseCRS), lhsBaseCRS, rhsBaseCRS)) {
                return false;
            }
        }
        {
            CodeWithAuthorityType lhsDerivedCRSType;
            lhsDerivedCRSType = this.getDerivedCRSType();
            CodeWithAuthorityType rhsDerivedCRSType;
            rhsDerivedCRSType = that.getDerivedCRSType();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "derivedCRSType", lhsDerivedCRSType), LocatorUtils.property(thatLocator, "derivedCRSType", rhsDerivedCRSType), lhsDerivedCRSType, rhsDerivedCRSType)) {
                return false;
            }
        }
        {
            JAXBElement<CoordinateSystemPropertyType> lhsCoordinateSystem;
            lhsCoordinateSystem = this.getCoordinateSystem();
            JAXBElement<CoordinateSystemPropertyType> rhsCoordinateSystem;
            rhsCoordinateSystem = that.getCoordinateSystem();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "coordinateSystem", lhsCoordinateSystem), LocatorUtils.property(thatLocator, "coordinateSystem", rhsCoordinateSystem), lhsCoordinateSystem, rhsCoordinateSystem)) {
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
            SingleCRSPropertyType theBaseCRS;
            theBaseCRS = this.getBaseCRS();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "baseCRS", theBaseCRS), currentHashCode, theBaseCRS);
        }
        {
            CodeWithAuthorityType theDerivedCRSType;
            theDerivedCRSType = this.getDerivedCRSType();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "derivedCRSType", theDerivedCRSType), currentHashCode, theDerivedCRSType);
        }
        {
            JAXBElement<CoordinateSystemPropertyType> theCoordinateSystem;
            theCoordinateSystem = this.getCoordinateSystem();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "coordinateSystem", theCoordinateSystem), currentHashCode, theCoordinateSystem);
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
        if (draftCopy instanceof DerivedCRSType) {
            final DerivedCRSType copy = ((DerivedCRSType) draftCopy);
            if (this.isSetBaseCRS()) {
                SingleCRSPropertyType sourceBaseCRS;
                sourceBaseCRS = this.getBaseCRS();
                SingleCRSPropertyType copyBaseCRS = ((SingleCRSPropertyType) strategy.copy(LocatorUtils.property(locator, "baseCRS", sourceBaseCRS), sourceBaseCRS));
                copy.setBaseCRS(copyBaseCRS);
            } else {
                copy.baseCRS = null;
            }
            if (this.isSetDerivedCRSType()) {
                CodeWithAuthorityType sourceDerivedCRSType;
                sourceDerivedCRSType = this.getDerivedCRSType();
                CodeWithAuthorityType copyDerivedCRSType = ((CodeWithAuthorityType) strategy.copy(LocatorUtils.property(locator, "derivedCRSType", sourceDerivedCRSType), sourceDerivedCRSType));
                copy.setDerivedCRSType(copyDerivedCRSType);
            } else {
                copy.derivedCRSType = null;
            }
            if (this.isSetCoordinateSystem()) {
                JAXBElement<CoordinateSystemPropertyType> sourceCoordinateSystem;
                sourceCoordinateSystem = this.getCoordinateSystem();
                @SuppressWarnings("unchecked")
                JAXBElement<CoordinateSystemPropertyType> copyCoordinateSystem = ((JAXBElement<CoordinateSystemPropertyType> ) strategy.copy(LocatorUtils.property(locator, "coordinateSystem", sourceCoordinateSystem), sourceCoordinateSystem));
                copy.setCoordinateSystem(copyCoordinateSystem);
            } else {
                copy.coordinateSystem = null;
            }
        }
        return draftCopy;
    }

    public Object createNewInstance() {
        return new DerivedCRSType();
    }

    public void mergeFrom(Object left, Object right) {
        final MergeStrategy strategy = JAXBMergeStrategy.INSTANCE;
        mergeFrom(null, null, left, right, strategy);
    }

    public void mergeFrom(ObjectLocator leftLocator, ObjectLocator rightLocator, Object left, Object right, MergeStrategy strategy) {
        super.mergeFrom(leftLocator, rightLocator, left, right, strategy);
        if (right instanceof DerivedCRSType) {
            final DerivedCRSType target = this;
            final DerivedCRSType leftObject = ((DerivedCRSType) left);
            final DerivedCRSType rightObject = ((DerivedCRSType) right);
            {
                SingleCRSPropertyType lhsBaseCRS;
                lhsBaseCRS = leftObject.getBaseCRS();
                SingleCRSPropertyType rhsBaseCRS;
                rhsBaseCRS = rightObject.getBaseCRS();
                target.setBaseCRS(((SingleCRSPropertyType) strategy.merge(LocatorUtils.property(leftLocator, "baseCRS", lhsBaseCRS), LocatorUtils.property(rightLocator, "baseCRS", rhsBaseCRS), lhsBaseCRS, rhsBaseCRS)));
            }
            {
                CodeWithAuthorityType lhsDerivedCRSType;
                lhsDerivedCRSType = leftObject.getDerivedCRSType();
                CodeWithAuthorityType rhsDerivedCRSType;
                rhsDerivedCRSType = rightObject.getDerivedCRSType();
                target.setDerivedCRSType(((CodeWithAuthorityType) strategy.merge(LocatorUtils.property(leftLocator, "derivedCRSType", lhsDerivedCRSType), LocatorUtils.property(rightLocator, "derivedCRSType", rhsDerivedCRSType), lhsDerivedCRSType, rhsDerivedCRSType)));
            }
            {
                JAXBElement<CoordinateSystemPropertyType> lhsCoordinateSystem;
                lhsCoordinateSystem = leftObject.getCoordinateSystem();
                JAXBElement<CoordinateSystemPropertyType> rhsCoordinateSystem;
                rhsCoordinateSystem = rightObject.getCoordinateSystem();
                target.setCoordinateSystem(((JAXBElement<CoordinateSystemPropertyType> ) strategy.merge(LocatorUtils.property(leftLocator, "coordinateSystem", lhsCoordinateSystem), LocatorUtils.property(rightLocator, "coordinateSystem", rhsCoordinateSystem), lhsCoordinateSystem, rhsCoordinateSystem)));
            }
        }
    }

    public DerivedCRSType withBaseCRS(SingleCRSPropertyType value) {
        setBaseCRS(value);
        return this;
    }

    public DerivedCRSType withDerivedCRSType(CodeWithAuthorityType value) {
        setDerivedCRSType(value);
        return this;
    }

    public DerivedCRSType withCoordinateSystem(JAXBElement<CoordinateSystemPropertyType> value) {
        setCoordinateSystem(value);
        return this;
    }

    @Override
    public DerivedCRSType withConversion(JAXBElement<GeneralConversionPropertyType> value) {
        setConversion(value);
        return this;
    }

    @Override
    public DerivedCRSType withDomainOfValidity(DomainOfValidityElement... values) {
        if (values!= null) {
            for (DomainOfValidityElement value: values) {
                getDomainOfValidity().add(value);
            }
        }
        return this;
    }

    @Override
    public DerivedCRSType withDomainOfValidity(Collection<DomainOfValidityElement> values) {
        if (values!= null) {
            getDomainOfValidity().addAll(values);
        }
        return this;
    }

    @Override
    public DerivedCRSType withScope(String... values) {
        if (values!= null) {
            for (String value: values) {
                getScope().add(value);
            }
        }
        return this;
    }

    @Override
    public DerivedCRSType withScope(Collection<String> values) {
        if (values!= null) {
            getScope().addAll(values);
        }
        return this;
    }

    @Override
    public DerivedCRSType withDomainOfValidity(List<DomainOfValidityElement> value) {
        setDomainOfValidity(value);
        return this;
    }

    @Override
    public DerivedCRSType withScope(List<String> value) {
        setScope(value);
        return this;
    }

    @Override
    public DerivedCRSType withRemarks(String value) {
        setRemarks(value);
        return this;
    }

    @Override
    public DerivedCRSType withMetaDataProperty(MetaDataPropertyType... values) {
        if (values!= null) {
            for (MetaDataPropertyType value: values) {
                getMetaDataProperty().add(value);
            }
        }
        return this;
    }

    @Override
    public DerivedCRSType withMetaDataProperty(Collection<MetaDataPropertyType> values) {
        if (values!= null) {
            getMetaDataProperty().addAll(values);
        }
        return this;
    }

    @Override
    public DerivedCRSType withDescription(StringOrRefType value) {
        setDescription(value);
        return this;
    }

    @Override
    public DerivedCRSType withDescriptionReference(ReferenceType value) {
        setDescriptionReference(value);
        return this;
    }

    @Override
    public DerivedCRSType withIdentifier(CodeWithAuthorityType value) {
        setIdentifier(value);
        return this;
    }

    @Override
    public DerivedCRSType withName(CodeType... values) {
        if (values!= null) {
            for (CodeType value: values) {
                getName().add(value);
            }
        }
        return this;
    }

    @Override
    public DerivedCRSType withName(Collection<CodeType> values) {
        if (values!= null) {
            getName().addAll(values);
        }
        return this;
    }

    @Override
    public DerivedCRSType withId(String value) {
        setId(value);
        return this;
    }

    @Override
    public DerivedCRSType withMetaDataProperty(List<MetaDataPropertyType> value) {
        setMetaDataProperty(value);
        return this;
    }

    @Override
    public DerivedCRSType withName(List<CodeType> value) {
        setName(value);
        return this;
    }

}
