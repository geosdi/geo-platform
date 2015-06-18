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
 * <p>Classe Java per BaseUnitType complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="BaseUnitType">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.opengis.net/gml}UnitDefinitionType">
 *       &lt;sequence>
 *         &lt;element name="unitsSystem" type="{http://www.opengis.net/gml}ReferenceType"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "BaseUnitType", propOrder = {
    "unitsSystem"
})
public class BaseUnitType
    extends UnitDefinitionType
    implements Cloneable, CopyTo, Equals, HashCode, MergeFrom, ToString
{

    @XmlElement(required = true)
    protected ReferenceType unitsSystem;

    /**
     * Recupera il valore della proprietà unitsSystem.
     * 
     * @return
     *     possible object is
     *     {@link ReferenceType }
     *     
     */
    public ReferenceType getUnitsSystem() {
        return unitsSystem;
    }

    /**
     * Imposta il valore della proprietà unitsSystem.
     * 
     * @param value
     *     allowed object is
     *     {@link ReferenceType }
     *     
     */
    public void setUnitsSystem(ReferenceType value) {
        this.unitsSystem = value;
    }

    public boolean isSetUnitsSystem() {
        return (this.unitsSystem!= null);
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
            ReferenceType theUnitsSystem;
            theUnitsSystem = this.getUnitsSystem();
            strategy.appendField(locator, this, "unitsSystem", buffer, theUnitsSystem);
        }
        return buffer;
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof BaseUnitType)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        if (!super.equals(thisLocator, thatLocator, object, strategy)) {
            return false;
        }
        final BaseUnitType that = ((BaseUnitType) object);
        {
            ReferenceType lhsUnitsSystem;
            lhsUnitsSystem = this.getUnitsSystem();
            ReferenceType rhsUnitsSystem;
            rhsUnitsSystem = that.getUnitsSystem();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "unitsSystem", lhsUnitsSystem), LocatorUtils.property(thatLocator, "unitsSystem", rhsUnitsSystem), lhsUnitsSystem, rhsUnitsSystem)) {
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
            ReferenceType theUnitsSystem;
            theUnitsSystem = this.getUnitsSystem();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "unitsSystem", theUnitsSystem), currentHashCode, theUnitsSystem);
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
        if (draftCopy instanceof BaseUnitType) {
            final BaseUnitType copy = ((BaseUnitType) draftCopy);
            if (this.isSetUnitsSystem()) {
                ReferenceType sourceUnitsSystem;
                sourceUnitsSystem = this.getUnitsSystem();
                ReferenceType copyUnitsSystem = ((ReferenceType) strategy.copy(LocatorUtils.property(locator, "unitsSystem", sourceUnitsSystem), sourceUnitsSystem));
                copy.setUnitsSystem(copyUnitsSystem);
            } else {
                copy.unitsSystem = null;
            }
        }
        return draftCopy;
    }

    public Object createNewInstance() {
        return new BaseUnitType();
    }

    public void mergeFrom(Object left, Object right) {
        final MergeStrategy strategy = JAXBMergeStrategy.INSTANCE;
        mergeFrom(null, null, left, right, strategy);
    }

    public void mergeFrom(ObjectLocator leftLocator, ObjectLocator rightLocator, Object left, Object right, MergeStrategy strategy) {
        super.mergeFrom(leftLocator, rightLocator, left, right, strategy);
        if (right instanceof BaseUnitType) {
            final BaseUnitType target = this;
            final BaseUnitType leftObject = ((BaseUnitType) left);
            final BaseUnitType rightObject = ((BaseUnitType) right);
            {
                ReferenceType lhsUnitsSystem;
                lhsUnitsSystem = leftObject.getUnitsSystem();
                ReferenceType rhsUnitsSystem;
                rhsUnitsSystem = rightObject.getUnitsSystem();
                target.setUnitsSystem(((ReferenceType) strategy.merge(LocatorUtils.property(leftLocator, "unitsSystem", lhsUnitsSystem), LocatorUtils.property(rightLocator, "unitsSystem", rhsUnitsSystem), lhsUnitsSystem, rhsUnitsSystem)));
            }
        }
    }

    public BaseUnitType withUnitsSystem(ReferenceType value) {
        setUnitsSystem(value);
        return this;
    }

    @Override
    public BaseUnitType withQuantityType(StringOrRefType value) {
        setQuantityType(value);
        return this;
    }

    @Override
    public BaseUnitType withQuantityTypeReference(ReferenceType value) {
        setQuantityTypeReference(value);
        return this;
    }

    @Override
    public BaseUnitType withCatalogSymbol(CodeType value) {
        setCatalogSymbol(value);
        return this;
    }

    @Override
    public BaseUnitType withRemarks(String value) {
        setRemarks(value);
        return this;
    }

    @Override
    public BaseUnitType withMetaDataProperty(MetaDataPropertyType... values) {
        if (values!= null) {
            for (MetaDataPropertyType value: values) {
                getMetaDataProperty().add(value);
            }
        }
        return this;
    }

    @Override
    public BaseUnitType withMetaDataProperty(Collection<MetaDataPropertyType> values) {
        if (values!= null) {
            getMetaDataProperty().addAll(values);
        }
        return this;
    }

    @Override
    public BaseUnitType withDescription(StringOrRefType value) {
        setDescription(value);
        return this;
    }

    @Override
    public BaseUnitType withDescriptionReference(ReferenceType value) {
        setDescriptionReference(value);
        return this;
    }

    @Override
    public BaseUnitType withIdentifier(CodeWithAuthorityType value) {
        setIdentifier(value);
        return this;
    }

    @Override
    public BaseUnitType withName(CodeType... values) {
        if (values!= null) {
            for (CodeType value: values) {
                getName().add(value);
            }
        }
        return this;
    }

    @Override
    public BaseUnitType withName(Collection<CodeType> values) {
        if (values!= null) {
            getName().addAll(values);
        }
        return this;
    }

    @Override
    public BaseUnitType withId(String value) {
        setId(value);
        return this;
    }

    @Override
    public BaseUnitType withMetaDataProperty(List<MetaDataPropertyType> value) {
        setMetaDataProperty(value);
        return this;
    }

    @Override
    public BaseUnitType withName(List<CodeType> value) {
        setName(value);
        return this;
    }

}
