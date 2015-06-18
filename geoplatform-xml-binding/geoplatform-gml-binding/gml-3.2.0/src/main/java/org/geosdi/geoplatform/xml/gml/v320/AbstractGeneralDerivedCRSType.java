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
import javax.xml.bind.annotation.XmlSeeAlso;
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
 * <p>Classe Java per AbstractGeneralDerivedCRSType complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="AbstractGeneralDerivedCRSType">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.opengis.net/gml}AbstractCRSType">
 *       &lt;sequence>
 *         &lt;element ref="{http://www.opengis.net/gml}conversion"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AbstractGeneralDerivedCRSType", propOrder = {
    "conversion"
})
@XmlSeeAlso({
    DerivedCRSType.class,
    ProjectedCRSType.class
})
public abstract class AbstractGeneralDerivedCRSType
    extends AbstractCRSType
    implements Cloneable, CopyTo, Equals, HashCode, MergeFrom, ToString
{

    @XmlElementRef(name = "conversion", namespace = "http://www.opengis.net/gml", type = JAXBElement.class)
    protected JAXBElement<GeneralConversionPropertyType> conversion;

    /**
     * Recupera il valore della proprietà conversion.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link GeneralConversionPropertyType }{@code >}
     *     {@link JAXBElement }{@code <}{@link GeneralConversionPropertyType }{@code >}
     *     
     */
    public JAXBElement<GeneralConversionPropertyType> getConversion() {
        return conversion;
    }

    /**
     * Imposta il valore della proprietà conversion.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link GeneralConversionPropertyType }{@code >}
     *     {@link JAXBElement }{@code <}{@link GeneralConversionPropertyType }{@code >}
     *     
     */
    public void setConversion(JAXBElement<GeneralConversionPropertyType> value) {
        this.conversion = value;
    }

    public boolean isSetConversion() {
        return (this.conversion!= null);
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
            JAXBElement<GeneralConversionPropertyType> theConversion;
            theConversion = this.getConversion();
            strategy.appendField(locator, this, "conversion", buffer, theConversion);
        }
        return buffer;
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof AbstractGeneralDerivedCRSType)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        if (!super.equals(thisLocator, thatLocator, object, strategy)) {
            return false;
        }
        final AbstractGeneralDerivedCRSType that = ((AbstractGeneralDerivedCRSType) object);
        {
            JAXBElement<GeneralConversionPropertyType> lhsConversion;
            lhsConversion = this.getConversion();
            JAXBElement<GeneralConversionPropertyType> rhsConversion;
            rhsConversion = that.getConversion();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "conversion", lhsConversion), LocatorUtils.property(thatLocator, "conversion", rhsConversion), lhsConversion, rhsConversion)) {
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
            JAXBElement<GeneralConversionPropertyType> theConversion;
            theConversion = this.getConversion();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "conversion", theConversion), currentHashCode, theConversion);
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
        if (null == target) {
            throw new IllegalArgumentException("Target argument must not be null for abstract copyable classes.");
        }
        super.copyTo(locator, target, strategy);
        if (target instanceof AbstractGeneralDerivedCRSType) {
            final AbstractGeneralDerivedCRSType copy = ((AbstractGeneralDerivedCRSType) target);
            if (this.isSetConversion()) {
                JAXBElement<GeneralConversionPropertyType> sourceConversion;
                sourceConversion = this.getConversion();
                @SuppressWarnings("unchecked")
                JAXBElement<GeneralConversionPropertyType> copyConversion = ((JAXBElement<GeneralConversionPropertyType> ) strategy.copy(LocatorUtils.property(locator, "conversion", sourceConversion), sourceConversion));
                copy.setConversion(copyConversion);
            } else {
                copy.conversion = null;
            }
        }
        return target;
    }

    public void mergeFrom(Object left, Object right) {
        final MergeStrategy strategy = JAXBMergeStrategy.INSTANCE;
        mergeFrom(null, null, left, right, strategy);
    }

    public void mergeFrom(ObjectLocator leftLocator, ObjectLocator rightLocator, Object left, Object right, MergeStrategy strategy) {
        super.mergeFrom(leftLocator, rightLocator, left, right, strategy);
        if (right instanceof AbstractGeneralDerivedCRSType) {
            final AbstractGeneralDerivedCRSType target = this;
            final AbstractGeneralDerivedCRSType leftObject = ((AbstractGeneralDerivedCRSType) left);
            final AbstractGeneralDerivedCRSType rightObject = ((AbstractGeneralDerivedCRSType) right);
            {
                JAXBElement<GeneralConversionPropertyType> lhsConversion;
                lhsConversion = leftObject.getConversion();
                JAXBElement<GeneralConversionPropertyType> rhsConversion;
                rhsConversion = rightObject.getConversion();
                target.setConversion(((JAXBElement<GeneralConversionPropertyType> ) strategy.merge(LocatorUtils.property(leftLocator, "conversion", lhsConversion), LocatorUtils.property(rightLocator, "conversion", rhsConversion), lhsConversion, rhsConversion)));
            }
        }
    }

    public AbstractGeneralDerivedCRSType withConversion(JAXBElement<GeneralConversionPropertyType> value) {
        setConversion(value);
        return this;
    }

    @Override
    public AbstractGeneralDerivedCRSType withDomainOfValidity(DomainOfValidityElement... values) {
        if (values!= null) {
            for (DomainOfValidityElement value: values) {
                getDomainOfValidity().add(value);
            }
        }
        return this;
    }

    @Override
    public AbstractGeneralDerivedCRSType withDomainOfValidity(Collection<DomainOfValidityElement> values) {
        if (values!= null) {
            getDomainOfValidity().addAll(values);
        }
        return this;
    }

    @Override
    public AbstractGeneralDerivedCRSType withScope(String... values) {
        if (values!= null) {
            for (String value: values) {
                getScope().add(value);
            }
        }
        return this;
    }

    @Override
    public AbstractGeneralDerivedCRSType withScope(Collection<String> values) {
        if (values!= null) {
            getScope().addAll(values);
        }
        return this;
    }

    @Override
    public AbstractGeneralDerivedCRSType withDomainOfValidity(List<DomainOfValidityElement> value) {
        setDomainOfValidity(value);
        return this;
    }

    @Override
    public AbstractGeneralDerivedCRSType withScope(List<String> value) {
        setScope(value);
        return this;
    }

    @Override
    public AbstractGeneralDerivedCRSType withRemarks(String value) {
        setRemarks(value);
        return this;
    }

    @Override
    public AbstractGeneralDerivedCRSType withMetaDataProperty(MetaDataPropertyType... values) {
        if (values!= null) {
            for (MetaDataPropertyType value: values) {
                getMetaDataProperty().add(value);
            }
        }
        return this;
    }

    @Override
    public AbstractGeneralDerivedCRSType withMetaDataProperty(Collection<MetaDataPropertyType> values) {
        if (values!= null) {
            getMetaDataProperty().addAll(values);
        }
        return this;
    }

    @Override
    public AbstractGeneralDerivedCRSType withDescription(StringOrRefType value) {
        setDescription(value);
        return this;
    }

    @Override
    public AbstractGeneralDerivedCRSType withDescriptionReference(ReferenceType value) {
        setDescriptionReference(value);
        return this;
    }

    @Override
    public AbstractGeneralDerivedCRSType withIdentifier(CodeWithAuthorityType value) {
        setIdentifier(value);
        return this;
    }

    @Override
    public AbstractGeneralDerivedCRSType withName(CodeType... values) {
        if (values!= null) {
            for (CodeType value: values) {
                getName().add(value);
            }
        }
        return this;
    }

    @Override
    public AbstractGeneralDerivedCRSType withName(Collection<CodeType> values) {
        if (values!= null) {
            getName().addAll(values);
        }
        return this;
    }

    @Override
    public AbstractGeneralDerivedCRSType withId(String value) {
        setId(value);
        return this;
    }

    @Override
    public AbstractGeneralDerivedCRSType withMetaDataProperty(List<MetaDataPropertyType> value) {
        setMetaDataProperty(value);
        return this;
    }

    @Override
    public AbstractGeneralDerivedCRSType withName(List<CodeType> value) {
        setName(value);
        return this;
    }

}
