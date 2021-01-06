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

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlSchemaType;
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
 * <p>Classe Java per OperationParameterGroupType complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="OperationParameterGroupType">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.opengis.net/gml}AbstractGeneralOperationParameterType">
 *       &lt;sequence>
 *         &lt;element ref="{http://www.opengis.net/gml}maximumOccurs" minOccurs="0"/>
 *         &lt;element ref="{http://www.opengis.net/gml}generalOperationParameter" maxOccurs="unbounded" minOccurs="2"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "OperationParameterGroupType", propOrder = {
    "maximumOccurs",
    "generalOperationParameter"
})
public class OperationParameterGroupType
    extends AbstractGeneralOperationParameterType
    implements Cloneable, CopyTo, Equals, HashCode, MergeFrom, ToString
{

    @XmlSchemaType(name = "positiveInteger")
    protected BigInteger maximumOccurs;
    @XmlElementRef(name = "generalOperationParameter", namespace = "http://www.opengis.net/gml", type = JAXBElement.class)
    protected List<JAXBElement<AbstractGeneralOperationParameterPropertyType>> generalOperationParameter;

    /**
     * Recupera il valore della proprietà maximumOccurs.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getMaximumOccurs() {
        return maximumOccurs;
    }

    /**
     * Imposta il valore della proprietà maximumOccurs.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setMaximumOccurs(BigInteger value) {
        this.maximumOccurs = value;
    }

    public boolean isSetMaximumOccurs() {
        return (this.maximumOccurs!= null);
    }

    /**
     * Gets the value of the generalOperationParameter property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the generalOperationParameter property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getGeneralOperationParameter().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link JAXBElement }{@code <}{@link AbstractGeneralOperationParameterPropertyType }{@code >}
     * {@link JAXBElement }{@code <}{@link AbstractGeneralOperationParameterPropertyType }{@code >}
     * 
     * 
     */
    public List<JAXBElement<AbstractGeneralOperationParameterPropertyType>> getGeneralOperationParameter() {
        if (generalOperationParameter == null) {
            generalOperationParameter = new ArrayList<JAXBElement<AbstractGeneralOperationParameterPropertyType>>();
        }
        return this.generalOperationParameter;
    }

    public boolean isSetGeneralOperationParameter() {
        return ((this.generalOperationParameter!= null)&&(!this.generalOperationParameter.isEmpty()));
    }

    public void unsetGeneralOperationParameter() {
        this.generalOperationParameter = null;
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
            BigInteger theMaximumOccurs;
            theMaximumOccurs = this.getMaximumOccurs();
            strategy.appendField(locator, this, "maximumOccurs", buffer, theMaximumOccurs);
        }
        {
            List<JAXBElement<AbstractGeneralOperationParameterPropertyType>> theGeneralOperationParameter;
            theGeneralOperationParameter = this.getGeneralOperationParameter();
            strategy.appendField(locator, this, "generalOperationParameter", buffer, theGeneralOperationParameter);
        }
        return buffer;
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof OperationParameterGroupType)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        if (!super.equals(thisLocator, thatLocator, object, strategy)) {
            return false;
        }
        final OperationParameterGroupType that = ((OperationParameterGroupType) object);
        {
            BigInteger lhsMaximumOccurs;
            lhsMaximumOccurs = this.getMaximumOccurs();
            BigInteger rhsMaximumOccurs;
            rhsMaximumOccurs = that.getMaximumOccurs();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "maximumOccurs", lhsMaximumOccurs), LocatorUtils.property(thatLocator, "maximumOccurs", rhsMaximumOccurs), lhsMaximumOccurs, rhsMaximumOccurs)) {
                return false;
            }
        }
        {
            List<JAXBElement<AbstractGeneralOperationParameterPropertyType>> lhsGeneralOperationParameter;
            lhsGeneralOperationParameter = this.getGeneralOperationParameter();
            List<JAXBElement<AbstractGeneralOperationParameterPropertyType>> rhsGeneralOperationParameter;
            rhsGeneralOperationParameter = that.getGeneralOperationParameter();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "generalOperationParameter", lhsGeneralOperationParameter), LocatorUtils.property(thatLocator, "generalOperationParameter", rhsGeneralOperationParameter), lhsGeneralOperationParameter, rhsGeneralOperationParameter)) {
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
            BigInteger theMaximumOccurs;
            theMaximumOccurs = this.getMaximumOccurs();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "maximumOccurs", theMaximumOccurs), currentHashCode, theMaximumOccurs);
        }
        {
            List<JAXBElement<AbstractGeneralOperationParameterPropertyType>> theGeneralOperationParameter;
            theGeneralOperationParameter = this.getGeneralOperationParameter();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "generalOperationParameter", theGeneralOperationParameter), currentHashCode, theGeneralOperationParameter);
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
        if (draftCopy instanceof OperationParameterGroupType) {
            final OperationParameterGroupType copy = ((OperationParameterGroupType) draftCopy);
            if (this.isSetMaximumOccurs()) {
                BigInteger sourceMaximumOccurs;
                sourceMaximumOccurs = this.getMaximumOccurs();
                BigInteger copyMaximumOccurs = ((BigInteger) strategy.copy(LocatorUtils.property(locator, "maximumOccurs", sourceMaximumOccurs), sourceMaximumOccurs));
                copy.setMaximumOccurs(copyMaximumOccurs);
            } else {
                copy.maximumOccurs = null;
            }
            if (this.isSetGeneralOperationParameter()) {
                List<JAXBElement<AbstractGeneralOperationParameterPropertyType>> sourceGeneralOperationParameter;
                sourceGeneralOperationParameter = this.getGeneralOperationParameter();
                @SuppressWarnings("unchecked")
                List<JAXBElement<AbstractGeneralOperationParameterPropertyType>> copyGeneralOperationParameter = ((List<JAXBElement<AbstractGeneralOperationParameterPropertyType>> ) strategy.copy(LocatorUtils.property(locator, "generalOperationParameter", sourceGeneralOperationParameter), sourceGeneralOperationParameter));
                copy.unsetGeneralOperationParameter();
                List<JAXBElement<AbstractGeneralOperationParameterPropertyType>> uniqueGeneralOperationParameterl = copy.getGeneralOperationParameter();
                uniqueGeneralOperationParameterl.addAll(copyGeneralOperationParameter);
            } else {
                copy.unsetGeneralOperationParameter();
            }
        }
        return draftCopy;
    }

    public Object createNewInstance() {
        return new OperationParameterGroupType();
    }

    public void mergeFrom(Object left, Object right) {
        final MergeStrategy strategy = JAXBMergeStrategy.INSTANCE;
        mergeFrom(null, null, left, right, strategy);
    }

    public void mergeFrom(ObjectLocator leftLocator, ObjectLocator rightLocator, Object left, Object right, MergeStrategy strategy) {
        super.mergeFrom(leftLocator, rightLocator, left, right, strategy);
        if (right instanceof OperationParameterGroupType) {
            final OperationParameterGroupType target = this;
            final OperationParameterGroupType leftObject = ((OperationParameterGroupType) left);
            final OperationParameterGroupType rightObject = ((OperationParameterGroupType) right);
            {
                BigInteger lhsMaximumOccurs;
                lhsMaximumOccurs = leftObject.getMaximumOccurs();
                BigInteger rhsMaximumOccurs;
                rhsMaximumOccurs = rightObject.getMaximumOccurs();
                target.setMaximumOccurs(((BigInteger) strategy.merge(LocatorUtils.property(leftLocator, "maximumOccurs", lhsMaximumOccurs), LocatorUtils.property(rightLocator, "maximumOccurs", rhsMaximumOccurs), lhsMaximumOccurs, rhsMaximumOccurs)));
            }
            {
                List<JAXBElement<AbstractGeneralOperationParameterPropertyType>> lhsGeneralOperationParameter;
                lhsGeneralOperationParameter = leftObject.getGeneralOperationParameter();
                List<JAXBElement<AbstractGeneralOperationParameterPropertyType>> rhsGeneralOperationParameter;
                rhsGeneralOperationParameter = rightObject.getGeneralOperationParameter();
                target.unsetGeneralOperationParameter();
                List<JAXBElement<AbstractGeneralOperationParameterPropertyType>> uniqueGeneralOperationParameterl = target.getGeneralOperationParameter();
                uniqueGeneralOperationParameterl.addAll(((List<JAXBElement<AbstractGeneralOperationParameterPropertyType>> ) strategy.merge(LocatorUtils.property(leftLocator, "generalOperationParameter", lhsGeneralOperationParameter), LocatorUtils.property(rightLocator, "generalOperationParameter", rhsGeneralOperationParameter), lhsGeneralOperationParameter, rhsGeneralOperationParameter)));
            }
        }
    }

    public void setGeneralOperationParameter(List<JAXBElement<AbstractGeneralOperationParameterPropertyType>> value) {
        List<JAXBElement<AbstractGeneralOperationParameterPropertyType>> draftl = this.getGeneralOperationParameter();
        draftl.addAll(value);
    }

    public OperationParameterGroupType withMaximumOccurs(BigInteger value) {
        setMaximumOccurs(value);
        return this;
    }

    public OperationParameterGroupType withGeneralOperationParameter(JAXBElement<AbstractGeneralOperationParameterPropertyType> ... values) {
        if (values!= null) {
            for (JAXBElement<AbstractGeneralOperationParameterPropertyType> value: values) {
                getGeneralOperationParameter().add(value);
            }
        }
        return this;
    }

    public OperationParameterGroupType withGeneralOperationParameter(Collection<JAXBElement<AbstractGeneralOperationParameterPropertyType>> values) {
        if (values!= null) {
            getGeneralOperationParameter().addAll(values);
        }
        return this;
    }

    public OperationParameterGroupType withGeneralOperationParameter(List<JAXBElement<AbstractGeneralOperationParameterPropertyType>> value) {
        setGeneralOperationParameter(value);
        return this;
    }

    @Override
    public OperationParameterGroupType withMinimumOccurs(BigInteger value) {
        setMinimumOccurs(value);
        return this;
    }

    @Override
    public OperationParameterGroupType withRemarks(String value) {
        setRemarks(value);
        return this;
    }

    @Override
    public OperationParameterGroupType withMetaDataProperty(MetaDataPropertyType... values) {
        if (values!= null) {
            for (MetaDataPropertyType value: values) {
                getMetaDataProperty().add(value);
            }
        }
        return this;
    }

    @Override
    public OperationParameterGroupType withMetaDataProperty(Collection<MetaDataPropertyType> values) {
        if (values!= null) {
            getMetaDataProperty().addAll(values);
        }
        return this;
    }

    @Override
    public OperationParameterGroupType withDescription(StringOrRefType value) {
        setDescription(value);
        return this;
    }

    @Override
    public OperationParameterGroupType withDescriptionReference(ReferenceType value) {
        setDescriptionReference(value);
        return this;
    }

    @Override
    public OperationParameterGroupType withIdentifier(CodeWithAuthorityType value) {
        setIdentifier(value);
        return this;
    }

    @Override
    public OperationParameterGroupType withName(CodeType... values) {
        if (values!= null) {
            for (CodeType value: values) {
                getName().add(value);
            }
        }
        return this;
    }

    @Override
    public OperationParameterGroupType withName(Collection<CodeType> values) {
        if (values!= null) {
            getName().addAll(values);
        }
        return this;
    }

    @Override
    public OperationParameterGroupType withId(String value) {
        setId(value);
        return this;
    }

    @Override
    public OperationParameterGroupType withMetaDataProperty(List<MetaDataPropertyType> value) {
        setMetaDataProperty(value);
        return this;
    }

    @Override
    public OperationParameterGroupType withName(List<CodeType> value) {
        setName(value);
        return this;
    }

}
