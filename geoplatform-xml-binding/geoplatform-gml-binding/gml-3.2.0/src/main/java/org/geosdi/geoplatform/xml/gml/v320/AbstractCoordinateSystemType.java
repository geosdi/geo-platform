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

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
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
 * <p>Classe Java per AbstractCoordinateSystemType complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="AbstractCoordinateSystemType">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.opengis.net/gml}IdentifiedObjectType">
 *       &lt;sequence>
 *         &lt;element ref="{http://www.opengis.net/gml}axis" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *       &lt;attGroup ref="{http://www.opengis.net/gml}AggregationAttributeGroup"/>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AbstractCoordinateSystemType", propOrder = {
    "axis"
})
@XmlSeeAlso({
    TimeCSType.class,
    ObliqueCartesianCSType.class,
    VerticalCSType.class,
    LinearCSType.class,
    SphericalCSType.class,
    EllipsoidalCSType.class,
    CartesianCSType.class,
    CylindricalCSType.class,
    AffineCSType.class,
    TemporalCSType.class,
    UserDefinedCSType.class,
    PolarCSType.class
})
public abstract class AbstractCoordinateSystemType
    extends IdentifiedObjectType
    implements Cloneable, CopyTo, Equals, HashCode, MergeFrom, ToString
{

    @XmlElementRef(name = "axis", namespace = "http://www.opengis.net/gml", type = JAXBElement.class)
    protected List<JAXBElement<CoordinateSystemAxisPropertyType>> axis;
    @XmlAttribute(name = "aggregationType")
    protected AggregationType aggregationType;

    /**
     * Gets the value of the axis property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the axis property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAxis().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link JAXBElement }{@code <}{@link CoordinateSystemAxisPropertyType }{@code >}
     * {@link JAXBElement }{@code <}{@link CoordinateSystemAxisPropertyType }{@code >}
     * 
     * 
     */
    public List<JAXBElement<CoordinateSystemAxisPropertyType>> getAxis() {
        if (axis == null) {
            axis = new ArrayList<JAXBElement<CoordinateSystemAxisPropertyType>>();
        }
        return this.axis;
    }

    public boolean isSetAxis() {
        return ((this.axis!= null)&&(!this.axis.isEmpty()));
    }

    public void unsetAxis() {
        this.axis = null;
    }

    /**
     * Recupera il valore della proprietà aggregationType.
     * 
     * @return
     *     possible object is
     *     {@link AggregationType }
     *     
     */
    public AggregationType getAggregationType() {
        return aggregationType;
    }

    /**
     * Imposta il valore della proprietà aggregationType.
     * 
     * @param value
     *     allowed object is
     *     {@link AggregationType }
     *     
     */
    public void setAggregationType(AggregationType value) {
        this.aggregationType = value;
    }

    public boolean isSetAggregationType() {
        return (this.aggregationType!= null);
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
            List<JAXBElement<CoordinateSystemAxisPropertyType>> theAxis;
            theAxis = this.getAxis();
            strategy.appendField(locator, this, "axis", buffer, theAxis);
        }
        {
            AggregationType theAggregationType;
            theAggregationType = this.getAggregationType();
            strategy.appendField(locator, this, "aggregationType", buffer, theAggregationType);
        }
        return buffer;
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof AbstractCoordinateSystemType)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        if (!super.equals(thisLocator, thatLocator, object, strategy)) {
            return false;
        }
        final AbstractCoordinateSystemType that = ((AbstractCoordinateSystemType) object);
        {
            List<JAXBElement<CoordinateSystemAxisPropertyType>> lhsAxis;
            lhsAxis = this.getAxis();
            List<JAXBElement<CoordinateSystemAxisPropertyType>> rhsAxis;
            rhsAxis = that.getAxis();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "axis", lhsAxis), LocatorUtils.property(thatLocator, "axis", rhsAxis), lhsAxis, rhsAxis)) {
                return false;
            }
        }
        {
            AggregationType lhsAggregationType;
            lhsAggregationType = this.getAggregationType();
            AggregationType rhsAggregationType;
            rhsAggregationType = that.getAggregationType();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "aggregationType", lhsAggregationType), LocatorUtils.property(thatLocator, "aggregationType", rhsAggregationType), lhsAggregationType, rhsAggregationType)) {
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
            List<JAXBElement<CoordinateSystemAxisPropertyType>> theAxis;
            theAxis = this.getAxis();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "axis", theAxis), currentHashCode, theAxis);
        }
        {
            AggregationType theAggregationType;
            theAggregationType = this.getAggregationType();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "aggregationType", theAggregationType), currentHashCode, theAggregationType);
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
        if (target instanceof AbstractCoordinateSystemType) {
            final AbstractCoordinateSystemType copy = ((AbstractCoordinateSystemType) target);
            if (this.isSetAxis()) {
                List<JAXBElement<CoordinateSystemAxisPropertyType>> sourceAxis;
                sourceAxis = this.getAxis();
                @SuppressWarnings("unchecked")
                List<JAXBElement<CoordinateSystemAxisPropertyType>> copyAxis = ((List<JAXBElement<CoordinateSystemAxisPropertyType>> ) strategy.copy(LocatorUtils.property(locator, "axis", sourceAxis), sourceAxis));
                copy.unsetAxis();
                List<JAXBElement<CoordinateSystemAxisPropertyType>> uniqueAxisl = copy.getAxis();
                uniqueAxisl.addAll(copyAxis);
            } else {
                copy.unsetAxis();
            }
            if (this.isSetAggregationType()) {
                AggregationType sourceAggregationType;
                sourceAggregationType = this.getAggregationType();
                AggregationType copyAggregationType = ((AggregationType) strategy.copy(LocatorUtils.property(locator, "aggregationType", sourceAggregationType), sourceAggregationType));
                copy.setAggregationType(copyAggregationType);
            } else {
                copy.aggregationType = null;
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
        if (right instanceof AbstractCoordinateSystemType) {
            final AbstractCoordinateSystemType target = this;
            final AbstractCoordinateSystemType leftObject = ((AbstractCoordinateSystemType) left);
            final AbstractCoordinateSystemType rightObject = ((AbstractCoordinateSystemType) right);
            {
                List<JAXBElement<CoordinateSystemAxisPropertyType>> lhsAxis;
                lhsAxis = leftObject.getAxis();
                List<JAXBElement<CoordinateSystemAxisPropertyType>> rhsAxis;
                rhsAxis = rightObject.getAxis();
                target.unsetAxis();
                List<JAXBElement<CoordinateSystemAxisPropertyType>> uniqueAxisl = target.getAxis();
                uniqueAxisl.addAll(((List<JAXBElement<CoordinateSystemAxisPropertyType>> ) strategy.merge(LocatorUtils.property(leftLocator, "axis", lhsAxis), LocatorUtils.property(rightLocator, "axis", rhsAxis), lhsAxis, rhsAxis)));
            }
            {
                AggregationType lhsAggregationType;
                lhsAggregationType = leftObject.getAggregationType();
                AggregationType rhsAggregationType;
                rhsAggregationType = rightObject.getAggregationType();
                target.setAggregationType(((AggregationType) strategy.merge(LocatorUtils.property(leftLocator, "aggregationType", lhsAggregationType), LocatorUtils.property(rightLocator, "aggregationType", rhsAggregationType), lhsAggregationType, rhsAggregationType)));
            }
        }
    }

    public void setAxis(List<JAXBElement<CoordinateSystemAxisPropertyType>> value) {
        List<JAXBElement<CoordinateSystemAxisPropertyType>> draftl = this.getAxis();
        draftl.addAll(value);
    }

    public AbstractCoordinateSystemType withAxis(JAXBElement<CoordinateSystemAxisPropertyType> ... values) {
        if (values!= null) {
            for (JAXBElement<CoordinateSystemAxisPropertyType> value: values) {
                getAxis().add(value);
            }
        }
        return this;
    }

    public AbstractCoordinateSystemType withAxis(Collection<JAXBElement<CoordinateSystemAxisPropertyType>> values) {
        if (values!= null) {
            getAxis().addAll(values);
        }
        return this;
    }

    public AbstractCoordinateSystemType withAggregationType(AggregationType value) {
        setAggregationType(value);
        return this;
    }

    public AbstractCoordinateSystemType withAxis(List<JAXBElement<CoordinateSystemAxisPropertyType>> value) {
        setAxis(value);
        return this;
    }

    @Override
    public AbstractCoordinateSystemType withRemarks(String value) {
        setRemarks(value);
        return this;
    }

    @Override
    public AbstractCoordinateSystemType withMetaDataProperty(MetaDataPropertyType... values) {
        if (values!= null) {
            for (MetaDataPropertyType value: values) {
                getMetaDataProperty().add(value);
            }
        }
        return this;
    }

    @Override
    public AbstractCoordinateSystemType withMetaDataProperty(Collection<MetaDataPropertyType> values) {
        if (values!= null) {
            getMetaDataProperty().addAll(values);
        }
        return this;
    }

    @Override
    public AbstractCoordinateSystemType withDescription(StringOrRefType value) {
        setDescription(value);
        return this;
    }

    @Override
    public AbstractCoordinateSystemType withDescriptionReference(ReferenceType value) {
        setDescriptionReference(value);
        return this;
    }

    @Override
    public AbstractCoordinateSystemType withIdentifier(CodeWithAuthorityType value) {
        setIdentifier(value);
        return this;
    }

    @Override
    public AbstractCoordinateSystemType withName(CodeType... values) {
        if (values!= null) {
            for (CodeType value: values) {
                getName().add(value);
            }
        }
        return this;
    }

    @Override
    public AbstractCoordinateSystemType withName(Collection<CodeType> values) {
        if (values!= null) {
            getName().addAll(values);
        }
        return this;
    }

    @Override
    public AbstractCoordinateSystemType withId(String value) {
        setId(value);
        return this;
    }

    @Override
    public AbstractCoordinateSystemType withMetaDataProperty(List<MetaDataPropertyType> value) {
        setMetaDataProperty(value);
        return this;
    }

    @Override
    public AbstractCoordinateSystemType withName(List<CodeType> value) {
        setName(value);
        return this;
    }

}
