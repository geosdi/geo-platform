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
 * <p>Classe Java per EllipsoidType complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="EllipsoidType">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.opengis.net/gml}IdentifiedObjectType">
 *       &lt;sequence>
 *         &lt;element ref="{http://www.opengis.net/gml}semiMajorAxis"/>
 *         &lt;element ref="{http://www.opengis.net/gml}secondDefiningParameter"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "EllipsoidType", propOrder = {
    "semiMajorAxis",
    "secondDefiningParameter"
})
public class EllipsoidType
    extends IdentifiedObjectType
    implements Cloneable, CopyTo, Equals, HashCode, MergeFrom, ToString
{

    @XmlElement(required = true)
    protected MeasureType semiMajorAxis;
    @XmlElement(required = true)
    protected SecondDefiningParameterPropertyElement secondDefiningParameter;

    /**
     * Recupera il valore della proprietà semiMajorAxis.
     * 
     * @return
     *     possible object is
     *     {@link MeasureType }
     *     
     */
    public MeasureType getSemiMajorAxis() {
        return semiMajorAxis;
    }

    /**
     * Imposta il valore della proprietà semiMajorAxis.
     * 
     * @param value
     *     allowed object is
     *     {@link MeasureType }
     *     
     */
    public void setSemiMajorAxis(MeasureType value) {
        this.semiMajorAxis = value;
    }

    public boolean isSetSemiMajorAxis() {
        return (this.semiMajorAxis!= null);
    }

    /**
     * Recupera il valore della proprietà secondDefiningParameter.
     * 
     * @return
     *     possible object is
     *     {@link SecondDefiningParameterPropertyElement }
     *     
     */
    public SecondDefiningParameterPropertyElement getSecondDefiningParameter() {
        return secondDefiningParameter;
    }

    /**
     * Imposta il valore della proprietà secondDefiningParameter.
     * 
     * @param value
     *     allowed object is
     *     {@link SecondDefiningParameterPropertyElement }
     *     
     */
    public void setSecondDefiningParameter(SecondDefiningParameterPropertyElement value) {
        this.secondDefiningParameter = value;
    }

    public boolean isSetSecondDefiningParameter() {
        return (this.secondDefiningParameter!= null);
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
            MeasureType theSemiMajorAxis;
            theSemiMajorAxis = this.getSemiMajorAxis();
            strategy.appendField(locator, this, "semiMajorAxis", buffer, theSemiMajorAxis);
        }
        {
            SecondDefiningParameterPropertyElement theSecondDefiningParameter;
            theSecondDefiningParameter = this.getSecondDefiningParameter();
            strategy.appendField(locator, this, "secondDefiningParameter", buffer, theSecondDefiningParameter);
        }
        return buffer;
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof EllipsoidType)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        if (!super.equals(thisLocator, thatLocator, object, strategy)) {
            return false;
        }
        final EllipsoidType that = ((EllipsoidType) object);
        {
            MeasureType lhsSemiMajorAxis;
            lhsSemiMajorAxis = this.getSemiMajorAxis();
            MeasureType rhsSemiMajorAxis;
            rhsSemiMajorAxis = that.getSemiMajorAxis();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "semiMajorAxis", lhsSemiMajorAxis), LocatorUtils.property(thatLocator, "semiMajorAxis", rhsSemiMajorAxis), lhsSemiMajorAxis, rhsSemiMajorAxis)) {
                return false;
            }
        }
        {
            SecondDefiningParameterPropertyElement lhsSecondDefiningParameter;
            lhsSecondDefiningParameter = this.getSecondDefiningParameter();
            SecondDefiningParameterPropertyElement rhsSecondDefiningParameter;
            rhsSecondDefiningParameter = that.getSecondDefiningParameter();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "secondDefiningParameter", lhsSecondDefiningParameter), LocatorUtils.property(thatLocator, "secondDefiningParameter", rhsSecondDefiningParameter), lhsSecondDefiningParameter, rhsSecondDefiningParameter)) {
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
            MeasureType theSemiMajorAxis;
            theSemiMajorAxis = this.getSemiMajorAxis();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "semiMajorAxis", theSemiMajorAxis), currentHashCode, theSemiMajorAxis);
        }
        {
            SecondDefiningParameterPropertyElement theSecondDefiningParameter;
            theSecondDefiningParameter = this.getSecondDefiningParameter();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "secondDefiningParameter", theSecondDefiningParameter), currentHashCode, theSecondDefiningParameter);
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
        if (draftCopy instanceof EllipsoidType) {
            final EllipsoidType copy = ((EllipsoidType) draftCopy);
            if (this.isSetSemiMajorAxis()) {
                MeasureType sourceSemiMajorAxis;
                sourceSemiMajorAxis = this.getSemiMajorAxis();
                MeasureType copySemiMajorAxis = ((MeasureType) strategy.copy(LocatorUtils.property(locator, "semiMajorAxis", sourceSemiMajorAxis), sourceSemiMajorAxis));
                copy.setSemiMajorAxis(copySemiMajorAxis);
            } else {
                copy.semiMajorAxis = null;
            }
            if (this.isSetSecondDefiningParameter()) {
                SecondDefiningParameterPropertyElement sourceSecondDefiningParameter;
                sourceSecondDefiningParameter = this.getSecondDefiningParameter();
                SecondDefiningParameterPropertyElement copySecondDefiningParameter = ((SecondDefiningParameterPropertyElement) strategy.copy(LocatorUtils.property(locator, "secondDefiningParameter", sourceSecondDefiningParameter), sourceSecondDefiningParameter));
                copy.setSecondDefiningParameter(copySecondDefiningParameter);
            } else {
                copy.secondDefiningParameter = null;
            }
        }
        return draftCopy;
    }

    public Object createNewInstance() {
        return new EllipsoidType();
    }

    public void mergeFrom(Object left, Object right) {
        final MergeStrategy strategy = JAXBMergeStrategy.INSTANCE;
        mergeFrom(null, null, left, right, strategy);
    }

    public void mergeFrom(ObjectLocator leftLocator, ObjectLocator rightLocator, Object left, Object right, MergeStrategy strategy) {
        super.mergeFrom(leftLocator, rightLocator, left, right, strategy);
        if (right instanceof EllipsoidType) {
            final EllipsoidType target = this;
            final EllipsoidType leftObject = ((EllipsoidType) left);
            final EllipsoidType rightObject = ((EllipsoidType) right);
            {
                MeasureType lhsSemiMajorAxis;
                lhsSemiMajorAxis = leftObject.getSemiMajorAxis();
                MeasureType rhsSemiMajorAxis;
                rhsSemiMajorAxis = rightObject.getSemiMajorAxis();
                target.setSemiMajorAxis(((MeasureType) strategy.merge(LocatorUtils.property(leftLocator, "semiMajorAxis", lhsSemiMajorAxis), LocatorUtils.property(rightLocator, "semiMajorAxis", rhsSemiMajorAxis), lhsSemiMajorAxis, rhsSemiMajorAxis)));
            }
            {
                SecondDefiningParameterPropertyElement lhsSecondDefiningParameter;
                lhsSecondDefiningParameter = leftObject.getSecondDefiningParameter();
                SecondDefiningParameterPropertyElement rhsSecondDefiningParameter;
                rhsSecondDefiningParameter = rightObject.getSecondDefiningParameter();
                target.setSecondDefiningParameter(((SecondDefiningParameterPropertyElement) strategy.merge(LocatorUtils.property(leftLocator, "secondDefiningParameter", lhsSecondDefiningParameter), LocatorUtils.property(rightLocator, "secondDefiningParameter", rhsSecondDefiningParameter), lhsSecondDefiningParameter, rhsSecondDefiningParameter)));
            }
        }
    }

    public EllipsoidType withSemiMajorAxis(MeasureType value) {
        setSemiMajorAxis(value);
        return this;
    }

    public EllipsoidType withSecondDefiningParameter(SecondDefiningParameterPropertyElement value) {
        setSecondDefiningParameter(value);
        return this;
    }

    @Override
    public EllipsoidType withRemarks(String value) {
        setRemarks(value);
        return this;
    }

    @Override
    public EllipsoidType withMetaDataProperty(MetaDataPropertyType... values) {
        if (values!= null) {
            for (MetaDataPropertyType value: values) {
                getMetaDataProperty().add(value);
            }
        }
        return this;
    }

    @Override
    public EllipsoidType withMetaDataProperty(Collection<MetaDataPropertyType> values) {
        if (values!= null) {
            getMetaDataProperty().addAll(values);
        }
        return this;
    }

    @Override
    public EllipsoidType withDescription(StringOrRefType value) {
        setDescription(value);
        return this;
    }

    @Override
    public EllipsoidType withDescriptionReference(ReferenceType value) {
        setDescriptionReference(value);
        return this;
    }

    @Override
    public EllipsoidType withIdentifier(CodeWithAuthorityType value) {
        setIdentifier(value);
        return this;
    }

    @Override
    public EllipsoidType withName(CodeType... values) {
        if (values!= null) {
            for (CodeType value: values) {
                getName().add(value);
            }
        }
        return this;
    }

    @Override
    public EllipsoidType withName(Collection<CodeType> values) {
        if (values!= null) {
            getName().addAll(values);
        }
        return this;
    }

    @Override
    public EllipsoidType withId(String value) {
        setId(value);
        return this;
    }

    @Override
    public EllipsoidType withMetaDataProperty(List<MetaDataPropertyType> value) {
        setMetaDataProperty(value);
        return this;
    }

    @Override
    public EllipsoidType withName(List<CodeType> value) {
        setName(value);
        return this;
    }

}
