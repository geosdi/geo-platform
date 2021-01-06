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
 * <p>Classe Java per ObservationType complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="ObservationType">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.opengis.net/gml}AbstractFeatureType">
 *       &lt;sequence>
 *         &lt;element ref="{http://www.opengis.net/gml}validTime"/>
 *         &lt;element ref="{http://www.opengis.net/gml}using" minOccurs="0"/>
 *         &lt;element ref="{http://www.opengis.net/gml}target" minOccurs="0"/>
 *         &lt;element ref="{http://www.opengis.net/gml}resultOf"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ObservationType", propOrder = {
    "validTime",
    "using",
    "target",
    "resultOf"
})
@XmlSeeAlso({
    DirectedObservationType.class
})
public class ObservationType
    extends AbstractFeatureType
    implements Cloneable, CopyTo, Equals, HashCode, MergeFrom, ToString
{

    @XmlElement(required = true)
    protected TimePrimitivePropertyType validTime;
    protected ProcedurePropertyType using;
    @XmlElementRef(name = "target", namespace = "http://www.opengis.net/gml", type = JAXBElement.class, required = false)
    protected JAXBElement<TargetPropertyType> target;
    @XmlElement(required = true)
    protected ResultType resultOf;

    /**
     * Recupera il valore della proprietà validTime.
     * 
     * @return
     *     possible object is
     *     {@link TimePrimitivePropertyType }
     *     
     */
    public TimePrimitivePropertyType getValidTime() {
        return validTime;
    }

    /**
     * Imposta il valore della proprietà validTime.
     * 
     * @param value
     *     allowed object is
     *     {@link TimePrimitivePropertyType }
     *     
     */
    public void setValidTime(TimePrimitivePropertyType value) {
        this.validTime = value;
    }

    public boolean isSetValidTime() {
        return (this.validTime!= null);
    }

    /**
     * Recupera il valore della proprietà using.
     * 
     * @return
     *     possible object is
     *     {@link ProcedurePropertyType }
     *     
     */
    public ProcedurePropertyType getUsing() {
        return using;
    }

    /**
     * Imposta il valore della proprietà using.
     * 
     * @param value
     *     allowed object is
     *     {@link ProcedurePropertyType }
     *     
     */
    public void setUsing(ProcedurePropertyType value) {
        this.using = value;
    }

    public boolean isSetUsing() {
        return (this.using!= null);
    }

    /**
     * Recupera il valore della proprietà target.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link TargetPropertyType }{@code >}
     *     {@link JAXBElement }{@code <}{@link TargetPropertyType }{@code >}
     *     
     */
    public JAXBElement<TargetPropertyType> getTarget() {
        return target;
    }

    /**
     * Imposta il valore della proprietà target.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link TargetPropertyType }{@code >}
     *     {@link JAXBElement }{@code <}{@link TargetPropertyType }{@code >}
     *     
     */
    public void setTarget(JAXBElement<TargetPropertyType> value) {
        this.target = value;
    }

    public boolean isSetTarget() {
        return (this.target!= null);
    }

    /**
     * Recupera il valore della proprietà resultOf.
     * 
     * @return
     *     possible object is
     *     {@link ResultType }
     *     
     */
    public ResultType getResultOf() {
        return resultOf;
    }

    /**
     * Imposta il valore della proprietà resultOf.
     * 
     * @param value
     *     allowed object is
     *     {@link ResultType }
     *     
     */
    public void setResultOf(ResultType value) {
        this.resultOf = value;
    }

    public boolean isSetResultOf() {
        return (this.resultOf!= null);
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
            TimePrimitivePropertyType theValidTime;
            theValidTime = this.getValidTime();
            strategy.appendField(locator, this, "validTime", buffer, theValidTime);
        }
        {
            ProcedurePropertyType theUsing;
            theUsing = this.getUsing();
            strategy.appendField(locator, this, "using", buffer, theUsing);
        }
        {
            JAXBElement<TargetPropertyType> theTarget;
            theTarget = this.getTarget();
            strategy.appendField(locator, this, "target", buffer, theTarget);
        }
        {
            ResultType theResultOf;
            theResultOf = this.getResultOf();
            strategy.appendField(locator, this, "resultOf", buffer, theResultOf);
        }
        return buffer;
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof ObservationType)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        if (!super.equals(thisLocator, thatLocator, object, strategy)) {
            return false;
        }
        final ObservationType that = ((ObservationType) object);
        {
            TimePrimitivePropertyType lhsValidTime;
            lhsValidTime = this.getValidTime();
            TimePrimitivePropertyType rhsValidTime;
            rhsValidTime = that.getValidTime();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "validTime", lhsValidTime), LocatorUtils.property(thatLocator, "validTime", rhsValidTime), lhsValidTime, rhsValidTime)) {
                return false;
            }
        }
        {
            ProcedurePropertyType lhsUsing;
            lhsUsing = this.getUsing();
            ProcedurePropertyType rhsUsing;
            rhsUsing = that.getUsing();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "using", lhsUsing), LocatorUtils.property(thatLocator, "using", rhsUsing), lhsUsing, rhsUsing)) {
                return false;
            }
        }
        {
            JAXBElement<TargetPropertyType> lhsTarget;
            lhsTarget = this.getTarget();
            JAXBElement<TargetPropertyType> rhsTarget;
            rhsTarget = that.getTarget();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "target", lhsTarget), LocatorUtils.property(thatLocator, "target", rhsTarget), lhsTarget, rhsTarget)) {
                return false;
            }
        }
        {
            ResultType lhsResultOf;
            lhsResultOf = this.getResultOf();
            ResultType rhsResultOf;
            rhsResultOf = that.getResultOf();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "resultOf", lhsResultOf), LocatorUtils.property(thatLocator, "resultOf", rhsResultOf), lhsResultOf, rhsResultOf)) {
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
            TimePrimitivePropertyType theValidTime;
            theValidTime = this.getValidTime();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "validTime", theValidTime), currentHashCode, theValidTime);
        }
        {
            ProcedurePropertyType theUsing;
            theUsing = this.getUsing();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "using", theUsing), currentHashCode, theUsing);
        }
        {
            JAXBElement<TargetPropertyType> theTarget;
            theTarget = this.getTarget();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "target", theTarget), currentHashCode, theTarget);
        }
        {
            ResultType theResultOf;
            theResultOf = this.getResultOf();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "resultOf", theResultOf), currentHashCode, theResultOf);
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
        if (draftCopy instanceof ObservationType) {
            final ObservationType copy = ((ObservationType) draftCopy);
            if (this.isSetValidTime()) {
                TimePrimitivePropertyType sourceValidTime;
                sourceValidTime = this.getValidTime();
                TimePrimitivePropertyType copyValidTime = ((TimePrimitivePropertyType) strategy.copy(LocatorUtils.property(locator, "validTime", sourceValidTime), sourceValidTime));
                copy.setValidTime(copyValidTime);
            } else {
                copy.validTime = null;
            }
            if (this.isSetUsing()) {
                ProcedurePropertyType sourceUsing;
                sourceUsing = this.getUsing();
                ProcedurePropertyType copyUsing = ((ProcedurePropertyType) strategy.copy(LocatorUtils.property(locator, "using", sourceUsing), sourceUsing));
                copy.setUsing(copyUsing);
            } else {
                copy.using = null;
            }
            if (this.isSetTarget()) {
                JAXBElement<TargetPropertyType> sourceTarget;
                sourceTarget = this.getTarget();
                @SuppressWarnings("unchecked")
                JAXBElement<TargetPropertyType> copyTarget = ((JAXBElement<TargetPropertyType> ) strategy.copy(LocatorUtils.property(locator, "target", sourceTarget), sourceTarget));
                copy.setTarget(copyTarget);
            } else {
                copy.target = null;
            }
            if (this.isSetResultOf()) {
                ResultType sourceResultOf;
                sourceResultOf = this.getResultOf();
                ResultType copyResultOf = ((ResultType) strategy.copy(LocatorUtils.property(locator, "resultOf", sourceResultOf), sourceResultOf));
                copy.setResultOf(copyResultOf);
            } else {
                copy.resultOf = null;
            }
        }
        return draftCopy;
    }

    public Object createNewInstance() {
        return new ObservationType();
    }

    public void mergeFrom(Object left, Object right) {
        final MergeStrategy strategy = JAXBMergeStrategy.INSTANCE;
        mergeFrom(null, null, left, right, strategy);
    }

    public void mergeFrom(ObjectLocator leftLocator, ObjectLocator rightLocator, Object left, Object right, MergeStrategy strategy) {
        super.mergeFrom(leftLocator, rightLocator, left, right, strategy);
        if (right instanceof ObservationType) {
            final ObservationType target = this;
            final ObservationType leftObject = ((ObservationType) left);
            final ObservationType rightObject = ((ObservationType) right);
            {
                TimePrimitivePropertyType lhsValidTime;
                lhsValidTime = leftObject.getValidTime();
                TimePrimitivePropertyType rhsValidTime;
                rhsValidTime = rightObject.getValidTime();
                target.setValidTime(((TimePrimitivePropertyType) strategy.merge(LocatorUtils.property(leftLocator, "validTime", lhsValidTime), LocatorUtils.property(rightLocator, "validTime", rhsValidTime), lhsValidTime, rhsValidTime)));
            }
            {
                ProcedurePropertyType lhsUsing;
                lhsUsing = leftObject.getUsing();
                ProcedurePropertyType rhsUsing;
                rhsUsing = rightObject.getUsing();
                target.setUsing(((ProcedurePropertyType) strategy.merge(LocatorUtils.property(leftLocator, "using", lhsUsing), LocatorUtils.property(rightLocator, "using", rhsUsing), lhsUsing, rhsUsing)));
            }
            {
                JAXBElement<TargetPropertyType> lhsTarget;
                lhsTarget = leftObject.getTarget();
                JAXBElement<TargetPropertyType> rhsTarget;
                rhsTarget = rightObject.getTarget();
                target.setTarget(((JAXBElement<TargetPropertyType> ) strategy.merge(LocatorUtils.property(leftLocator, "target", lhsTarget), LocatorUtils.property(rightLocator, "target", rhsTarget), lhsTarget, rhsTarget)));
            }
            {
                ResultType lhsResultOf;
                lhsResultOf = leftObject.getResultOf();
                ResultType rhsResultOf;
                rhsResultOf = rightObject.getResultOf();
                target.setResultOf(((ResultType) strategy.merge(LocatorUtils.property(leftLocator, "resultOf", lhsResultOf), LocatorUtils.property(rightLocator, "resultOf", rhsResultOf), lhsResultOf, rhsResultOf)));
            }
        }
    }

    public ObservationType withValidTime(TimePrimitivePropertyType value) {
        setValidTime(value);
        return this;
    }

    public ObservationType withUsing(ProcedurePropertyType value) {
        setUsing(value);
        return this;
    }

    public ObservationType withTarget(JAXBElement<TargetPropertyType> value) {
        setTarget(value);
        return this;
    }

    public ObservationType withResultOf(ResultType value) {
        setResultOf(value);
        return this;
    }

    @Override
    public ObservationType withBoundedBy(BoundingShapeType value) {
        setBoundedBy(value);
        return this;
    }

    @Override
    public ObservationType withLocation(JAXBElement<? extends LocationPropertyType> value) {
        setLocation(value);
        return this;
    }

    @Override
    public ObservationType withMetaDataProperty(MetaDataPropertyType... values) {
        if (values!= null) {
            for (MetaDataPropertyType value: values) {
                getMetaDataProperty().add(value);
            }
        }
        return this;
    }

    @Override
    public ObservationType withMetaDataProperty(Collection<MetaDataPropertyType> values) {
        if (values!= null) {
            getMetaDataProperty().addAll(values);
        }
        return this;
    }

    @Override
    public ObservationType withDescription(StringOrRefType value) {
        setDescription(value);
        return this;
    }

    @Override
    public ObservationType withDescriptionReference(ReferenceType value) {
        setDescriptionReference(value);
        return this;
    }

    @Override
    public ObservationType withIdentifier(CodeWithAuthorityType value) {
        setIdentifier(value);
        return this;
    }

    @Override
    public ObservationType withName(CodeType... values) {
        if (values!= null) {
            for (CodeType value: values) {
                getName().add(value);
            }
        }
        return this;
    }

    @Override
    public ObservationType withName(Collection<CodeType> values) {
        if (values!= null) {
            getName().addAll(values);
        }
        return this;
    }

    @Override
    public ObservationType withId(String value) {
        setId(value);
        return this;
    }

    @Override
    public ObservationType withMetaDataProperty(List<MetaDataPropertyType> value) {
        setMetaDataProperty(value);
        return this;
    }

    @Override
    public ObservationType withName(List<CodeType> value) {
        setName(value);
        return this;
    }

}
