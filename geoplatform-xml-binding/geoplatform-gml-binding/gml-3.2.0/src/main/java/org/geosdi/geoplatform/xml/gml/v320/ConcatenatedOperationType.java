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
 * gml:ConcatenatedOperation is an ordered sequence of two or more coordinate operations. This sequence of operations is constrained by the requirement that the source coordinate reference system of step (n+1) must be the same as the target coordinate reference system of step (n). The source coordinate reference system of the first step and the target coordinate reference system of the last step are the source and target coordinate reference system associated with the concatenated operation. Instead of a forward operation, an inverse operation may be used for one or more of the operation steps mentioned above, if the inverse operation is uniquely defined by the forward operation.
 * The gml:coordOperation property elements are an ordered sequence of associations to the two or more operations used by this concatenated operation. The AggregationAttributeGroup should be used to specify that the coordOperation associations are ordered.
 * 
 * <p>Classe Java per ConcatenatedOperationType complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="ConcatenatedOperationType">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.opengis.net/gml}AbstractCoordinateOperationType">
 *       &lt;sequence>
 *         &lt;element ref="{http://www.opengis.net/gml}coordOperation" maxOccurs="unbounded" minOccurs="2"/>
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
@XmlType(name = "ConcatenatedOperationType", propOrder = {
    "coordOperation"
})
public class ConcatenatedOperationType
    extends AbstractCoordinateOperationType
    implements Cloneable, CopyTo, Equals, HashCode, MergeFrom, ToString
{

    @XmlElementRef(name = "coordOperation", namespace = "http://www.opengis.net/gml", type = JAXBElement.class)
    protected List<JAXBElement<CoordinateOperationPropertyType>> coordOperation;
    @XmlAttribute(name = "aggregationType")
    protected AggregationType aggregationType;

    /**
     * Gets the value of the coordOperation property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the coordOperation property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCoordOperation().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link JAXBElement }{@code <}{@link CoordinateOperationPropertyType }{@code >}
     * {@link JAXBElement }{@code <}{@link CoordinateOperationPropertyType }{@code >}
     * {@link JAXBElement }{@code <}{@link CoordinateOperationPropertyType }{@code >}
     * 
     * 
     */
    public List<JAXBElement<CoordinateOperationPropertyType>> getCoordOperation() {
        if (coordOperation == null) {
            coordOperation = new ArrayList<JAXBElement<CoordinateOperationPropertyType>>();
        }
        return this.coordOperation;
    }

    public boolean isSetCoordOperation() {
        return ((this.coordOperation!= null)&&(!this.coordOperation.isEmpty()));
    }

    public void unsetCoordOperation() {
        this.coordOperation = null;
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
            List<JAXBElement<CoordinateOperationPropertyType>> theCoordOperation;
            theCoordOperation = this.getCoordOperation();
            strategy.appendField(locator, this, "coordOperation", buffer, theCoordOperation);
        }
        {
            AggregationType theAggregationType;
            theAggregationType = this.getAggregationType();
            strategy.appendField(locator, this, "aggregationType", buffer, theAggregationType);
        }
        return buffer;
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof ConcatenatedOperationType)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        if (!super.equals(thisLocator, thatLocator, object, strategy)) {
            return false;
        }
        final ConcatenatedOperationType that = ((ConcatenatedOperationType) object);
        {
            List<JAXBElement<CoordinateOperationPropertyType>> lhsCoordOperation;
            lhsCoordOperation = this.getCoordOperation();
            List<JAXBElement<CoordinateOperationPropertyType>> rhsCoordOperation;
            rhsCoordOperation = that.getCoordOperation();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "coordOperation", lhsCoordOperation), LocatorUtils.property(thatLocator, "coordOperation", rhsCoordOperation), lhsCoordOperation, rhsCoordOperation)) {
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
            List<JAXBElement<CoordinateOperationPropertyType>> theCoordOperation;
            theCoordOperation = this.getCoordOperation();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "coordOperation", theCoordOperation), currentHashCode, theCoordOperation);
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
        final Object draftCopy = ((target == null)?createNewInstance():target);
        super.copyTo(locator, draftCopy, strategy);
        if (draftCopy instanceof ConcatenatedOperationType) {
            final ConcatenatedOperationType copy = ((ConcatenatedOperationType) draftCopy);
            if (this.isSetCoordOperation()) {
                List<JAXBElement<CoordinateOperationPropertyType>> sourceCoordOperation;
                sourceCoordOperation = this.getCoordOperation();
                @SuppressWarnings("unchecked")
                List<JAXBElement<CoordinateOperationPropertyType>> copyCoordOperation = ((List<JAXBElement<CoordinateOperationPropertyType>> ) strategy.copy(LocatorUtils.property(locator, "coordOperation", sourceCoordOperation), sourceCoordOperation));
                copy.unsetCoordOperation();
                List<JAXBElement<CoordinateOperationPropertyType>> uniqueCoordOperationl = copy.getCoordOperation();
                uniqueCoordOperationl.addAll(copyCoordOperation);
            } else {
                copy.unsetCoordOperation();
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
        return draftCopy;
    }

    public Object createNewInstance() {
        return new ConcatenatedOperationType();
    }

    public void mergeFrom(Object left, Object right) {
        final MergeStrategy strategy = JAXBMergeStrategy.INSTANCE;
        mergeFrom(null, null, left, right, strategy);
    }

    public void mergeFrom(ObjectLocator leftLocator, ObjectLocator rightLocator, Object left, Object right, MergeStrategy strategy) {
        super.mergeFrom(leftLocator, rightLocator, left, right, strategy);
        if (right instanceof ConcatenatedOperationType) {
            final ConcatenatedOperationType target = this;
            final ConcatenatedOperationType leftObject = ((ConcatenatedOperationType) left);
            final ConcatenatedOperationType rightObject = ((ConcatenatedOperationType) right);
            {
                List<JAXBElement<CoordinateOperationPropertyType>> lhsCoordOperation;
                lhsCoordOperation = leftObject.getCoordOperation();
                List<JAXBElement<CoordinateOperationPropertyType>> rhsCoordOperation;
                rhsCoordOperation = rightObject.getCoordOperation();
                target.unsetCoordOperation();
                List<JAXBElement<CoordinateOperationPropertyType>> uniqueCoordOperationl = target.getCoordOperation();
                uniqueCoordOperationl.addAll(((List<JAXBElement<CoordinateOperationPropertyType>> ) strategy.merge(LocatorUtils.property(leftLocator, "coordOperation", lhsCoordOperation), LocatorUtils.property(rightLocator, "coordOperation", rhsCoordOperation), lhsCoordOperation, rhsCoordOperation)));
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

    public void setCoordOperation(List<JAXBElement<CoordinateOperationPropertyType>> value) {
        List<JAXBElement<CoordinateOperationPropertyType>> draftl = this.getCoordOperation();
        draftl.addAll(value);
    }

    public ConcatenatedOperationType withCoordOperation(JAXBElement<CoordinateOperationPropertyType> ... values) {
        if (values!= null) {
            for (JAXBElement<CoordinateOperationPropertyType> value: values) {
                getCoordOperation().add(value);
            }
        }
        return this;
    }

    public ConcatenatedOperationType withCoordOperation(Collection<JAXBElement<CoordinateOperationPropertyType>> values) {
        if (values!= null) {
            getCoordOperation().addAll(values);
        }
        return this;
    }

    public ConcatenatedOperationType withAggregationType(AggregationType value) {
        setAggregationType(value);
        return this;
    }

    public ConcatenatedOperationType withCoordOperation(List<JAXBElement<CoordinateOperationPropertyType>> value) {
        setCoordOperation(value);
        return this;
    }

    @Override
    public ConcatenatedOperationType withDomainOfValidity(DomainOfValidityElement value) {
        setDomainOfValidity(value);
        return this;
    }

    @Override
    public ConcatenatedOperationType withScope(String... values) {
        if (values!= null) {
            for (String value: values) {
                getScope().add(value);
            }
        }
        return this;
    }

    @Override
    public ConcatenatedOperationType withScope(Collection<String> values) {
        if (values!= null) {
            getScope().addAll(values);
        }
        return this;
    }

    @Override
    public ConcatenatedOperationType withOperationVersion(String value) {
        setOperationVersion(value);
        return this;
    }

    @Override
    public ConcatenatedOperationType withCoordinateOperationAccuracy(CoordinateOperationAccuracyElement... values) {
        if (values!= null) {
            for (CoordinateOperationAccuracyElement value: values) {
                getCoordinateOperationAccuracy().add(value);
            }
        }
        return this;
    }

    @Override
    public ConcatenatedOperationType withCoordinateOperationAccuracy(Collection<CoordinateOperationAccuracyElement> values) {
        if (values!= null) {
            getCoordinateOperationAccuracy().addAll(values);
        }
        return this;
    }

    @Override
    public ConcatenatedOperationType withSourceCRS(CRSPropertyType value) {
        setSourceCRS(value);
        return this;
    }

    @Override
    public ConcatenatedOperationType withTargetCRS(CRSPropertyType value) {
        setTargetCRS(value);
        return this;
    }

    @Override
    public ConcatenatedOperationType withScope(List<String> value) {
        setScope(value);
        return this;
    }

    @Override
    public ConcatenatedOperationType withCoordinateOperationAccuracy(List<CoordinateOperationAccuracyElement> value) {
        setCoordinateOperationAccuracy(value);
        return this;
    }

    @Override
    public ConcatenatedOperationType withRemarks(String value) {
        setRemarks(value);
        return this;
    }

    @Override
    public ConcatenatedOperationType withMetaDataProperty(MetaDataPropertyType... values) {
        if (values!= null) {
            for (MetaDataPropertyType value: values) {
                getMetaDataProperty().add(value);
            }
        }
        return this;
    }

    @Override
    public ConcatenatedOperationType withMetaDataProperty(Collection<MetaDataPropertyType> values) {
        if (values!= null) {
            getMetaDataProperty().addAll(values);
        }
        return this;
    }

    @Override
    public ConcatenatedOperationType withDescription(StringOrRefType value) {
        setDescription(value);
        return this;
    }

    @Override
    public ConcatenatedOperationType withDescriptionReference(ReferenceType value) {
        setDescriptionReference(value);
        return this;
    }

    @Override
    public ConcatenatedOperationType withIdentifier(CodeWithAuthorityType value) {
        setIdentifier(value);
        return this;
    }

    @Override
    public ConcatenatedOperationType withName(CodeType... values) {
        if (values!= null) {
            for (CodeType value: values) {
                getName().add(value);
            }
        }
        return this;
    }

    @Override
    public ConcatenatedOperationType withName(Collection<CodeType> values) {
        if (values!= null) {
            getName().addAll(values);
        }
        return this;
    }

    @Override
    public ConcatenatedOperationType withId(String value) {
        setId(value);
        return this;
    }

    @Override
    public ConcatenatedOperationType withMetaDataProperty(List<MetaDataPropertyType> value) {
        setMetaDataProperty(value);
        return this;
    }

    @Override
    public ConcatenatedOperationType withName(List<CodeType> value) {
        setName(value);
        return this;
    }

}
