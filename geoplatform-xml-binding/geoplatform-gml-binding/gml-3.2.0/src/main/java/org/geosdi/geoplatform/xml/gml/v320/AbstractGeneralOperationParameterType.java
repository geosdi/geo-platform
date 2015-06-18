//
// Questo file è stato generato dall'architettura JavaTM per XML Binding (JAXB) Reference Implementation, v2.2.7 
// Vedere <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Qualsiasi modifica a questo file andrà persa durante la ricompilazione dello schema di origine. 
// Generato il: 2015.06.18 alle 12:52:42 AM CEST 
//


package org.geosdi.geoplatform.xml.gml.v320;

import java.math.BigInteger;
import java.util.Collection;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
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
 * <p>Classe Java per AbstractGeneralOperationParameterType complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="AbstractGeneralOperationParameterType">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.opengis.net/gml}IdentifiedObjectType">
 *       &lt;sequence>
 *         &lt;element ref="{http://www.opengis.net/gml}minimumOccurs" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AbstractGeneralOperationParameterType", propOrder = {
    "minimumOccurs"
})
@XmlSeeAlso({
    OperationParameterType.class,
    OperationParameterGroupType.class
})
public abstract class AbstractGeneralOperationParameterType
    extends IdentifiedObjectType
    implements Cloneable, CopyTo, Equals, HashCode, MergeFrom, ToString
{

    @XmlSchemaType(name = "nonNegativeInteger")
    protected BigInteger minimumOccurs;

    /**
     * Recupera il valore della proprietà minimumOccurs.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getMinimumOccurs() {
        return minimumOccurs;
    }

    /**
     * Imposta il valore della proprietà minimumOccurs.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setMinimumOccurs(BigInteger value) {
        this.minimumOccurs = value;
    }

    public boolean isSetMinimumOccurs() {
        return (this.minimumOccurs!= null);
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
            BigInteger theMinimumOccurs;
            theMinimumOccurs = this.getMinimumOccurs();
            strategy.appendField(locator, this, "minimumOccurs", buffer, theMinimumOccurs);
        }
        return buffer;
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof AbstractGeneralOperationParameterType)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        if (!super.equals(thisLocator, thatLocator, object, strategy)) {
            return false;
        }
        final AbstractGeneralOperationParameterType that = ((AbstractGeneralOperationParameterType) object);
        {
            BigInteger lhsMinimumOccurs;
            lhsMinimumOccurs = this.getMinimumOccurs();
            BigInteger rhsMinimumOccurs;
            rhsMinimumOccurs = that.getMinimumOccurs();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "minimumOccurs", lhsMinimumOccurs), LocatorUtils.property(thatLocator, "minimumOccurs", rhsMinimumOccurs), lhsMinimumOccurs, rhsMinimumOccurs)) {
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
            BigInteger theMinimumOccurs;
            theMinimumOccurs = this.getMinimumOccurs();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "minimumOccurs", theMinimumOccurs), currentHashCode, theMinimumOccurs);
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
        if (target instanceof AbstractGeneralOperationParameterType) {
            final AbstractGeneralOperationParameterType copy = ((AbstractGeneralOperationParameterType) target);
            if (this.isSetMinimumOccurs()) {
                BigInteger sourceMinimumOccurs;
                sourceMinimumOccurs = this.getMinimumOccurs();
                BigInteger copyMinimumOccurs = ((BigInteger) strategy.copy(LocatorUtils.property(locator, "minimumOccurs", sourceMinimumOccurs), sourceMinimumOccurs));
                copy.setMinimumOccurs(copyMinimumOccurs);
            } else {
                copy.minimumOccurs = null;
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
        if (right instanceof AbstractGeneralOperationParameterType) {
            final AbstractGeneralOperationParameterType target = this;
            final AbstractGeneralOperationParameterType leftObject = ((AbstractGeneralOperationParameterType) left);
            final AbstractGeneralOperationParameterType rightObject = ((AbstractGeneralOperationParameterType) right);
            {
                BigInteger lhsMinimumOccurs;
                lhsMinimumOccurs = leftObject.getMinimumOccurs();
                BigInteger rhsMinimumOccurs;
                rhsMinimumOccurs = rightObject.getMinimumOccurs();
                target.setMinimumOccurs(((BigInteger) strategy.merge(LocatorUtils.property(leftLocator, "minimumOccurs", lhsMinimumOccurs), LocatorUtils.property(rightLocator, "minimumOccurs", rhsMinimumOccurs), lhsMinimumOccurs, rhsMinimumOccurs)));
            }
        }
    }

    public AbstractGeneralOperationParameterType withMinimumOccurs(BigInteger value) {
        setMinimumOccurs(value);
        return this;
    }

    @Override
    public AbstractGeneralOperationParameterType withRemarks(String value) {
        setRemarks(value);
        return this;
    }

    @Override
    public AbstractGeneralOperationParameterType withMetaDataProperty(MetaDataPropertyType... values) {
        if (values!= null) {
            for (MetaDataPropertyType value: values) {
                getMetaDataProperty().add(value);
            }
        }
        return this;
    }

    @Override
    public AbstractGeneralOperationParameterType withMetaDataProperty(Collection<MetaDataPropertyType> values) {
        if (values!= null) {
            getMetaDataProperty().addAll(values);
        }
        return this;
    }

    @Override
    public AbstractGeneralOperationParameterType withDescription(StringOrRefType value) {
        setDescription(value);
        return this;
    }

    @Override
    public AbstractGeneralOperationParameterType withDescriptionReference(ReferenceType value) {
        setDescriptionReference(value);
        return this;
    }

    @Override
    public AbstractGeneralOperationParameterType withIdentifier(CodeWithAuthorityType value) {
        setIdentifier(value);
        return this;
    }

    @Override
    public AbstractGeneralOperationParameterType withName(CodeType... values) {
        if (values!= null) {
            for (CodeType value: values) {
                getName().add(value);
            }
        }
        return this;
    }

    @Override
    public AbstractGeneralOperationParameterType withName(Collection<CodeType> values) {
        if (values!= null) {
            getName().addAll(values);
        }
        return this;
    }

    @Override
    public AbstractGeneralOperationParameterType withId(String value) {
        setId(value);
        return this;
    }

    @Override
    public AbstractGeneralOperationParameterType withMetaDataProperty(List<MetaDataPropertyType> value) {
        setMetaDataProperty(value);
        return this;
    }

    @Override
    public AbstractGeneralOperationParameterType withName(List<CodeType> value) {
        setName(value);
        return this;
    }

}
