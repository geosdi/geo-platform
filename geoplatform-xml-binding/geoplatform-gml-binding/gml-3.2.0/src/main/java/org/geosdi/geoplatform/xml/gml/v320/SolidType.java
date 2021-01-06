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
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
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
 * <p>Classe Java per SolidType complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="SolidType">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.opengis.net/gml}AbstractSolidType">
 *       &lt;sequence>
 *         &lt;element name="exterior" type="{http://www.opengis.net/gml}ShellPropertyType" minOccurs="0"/>
 *         &lt;element name="interior" type="{http://www.opengis.net/gml}ShellPropertyType" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SolidType", propOrder = {
    "exterior",
    "interior"
})
public class SolidType
    extends AbstractSolidType
    implements Cloneable, CopyTo, Equals, HashCode, MergeFrom, ToString
{

    protected ShellPropertyType exterior;
    protected List<ShellPropertyType> interior;

    /**
     * Recupera il valore della proprietà exterior.
     * 
     * @return
     *     possible object is
     *     {@link ShellPropertyType }
     *     
     */
    public ShellPropertyType getExterior() {
        return exterior;
    }

    /**
     * Imposta il valore della proprietà exterior.
     * 
     * @param value
     *     allowed object is
     *     {@link ShellPropertyType }
     *     
     */
    public void setExterior(ShellPropertyType value) {
        this.exterior = value;
    }

    public boolean isSetExterior() {
        return (this.exterior!= null);
    }

    /**
     * Gets the value of the interior property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the interior property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getInterior().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ShellPropertyType }
     * 
     * 
     */
    public List<ShellPropertyType> getInterior() {
        if (interior == null) {
            interior = new ArrayList<ShellPropertyType>();
        }
        return this.interior;
    }

    public boolean isSetInterior() {
        return ((this.interior!= null)&&(!this.interior.isEmpty()));
    }

    public void unsetInterior() {
        this.interior = null;
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
            ShellPropertyType theExterior;
            theExterior = this.getExterior();
            strategy.appendField(locator, this, "exterior", buffer, theExterior);
        }
        {
            List<ShellPropertyType> theInterior;
            theInterior = this.getInterior();
            strategy.appendField(locator, this, "interior", buffer, theInterior);
        }
        return buffer;
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof SolidType)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        if (!super.equals(thisLocator, thatLocator, object, strategy)) {
            return false;
        }
        final SolidType that = ((SolidType) object);
        {
            ShellPropertyType lhsExterior;
            lhsExterior = this.getExterior();
            ShellPropertyType rhsExterior;
            rhsExterior = that.getExterior();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "exterior", lhsExterior), LocatorUtils.property(thatLocator, "exterior", rhsExterior), lhsExterior, rhsExterior)) {
                return false;
            }
        }
        {
            List<ShellPropertyType> lhsInterior;
            lhsInterior = this.getInterior();
            List<ShellPropertyType> rhsInterior;
            rhsInterior = that.getInterior();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "interior", lhsInterior), LocatorUtils.property(thatLocator, "interior", rhsInterior), lhsInterior, rhsInterior)) {
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
            ShellPropertyType theExterior;
            theExterior = this.getExterior();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "exterior", theExterior), currentHashCode, theExterior);
        }
        {
            List<ShellPropertyType> theInterior;
            theInterior = this.getInterior();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "interior", theInterior), currentHashCode, theInterior);
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
        if (draftCopy instanceof SolidType) {
            final SolidType copy = ((SolidType) draftCopy);
            if (this.isSetExterior()) {
                ShellPropertyType sourceExterior;
                sourceExterior = this.getExterior();
                ShellPropertyType copyExterior = ((ShellPropertyType) strategy.copy(LocatorUtils.property(locator, "exterior", sourceExterior), sourceExterior));
                copy.setExterior(copyExterior);
            } else {
                copy.exterior = null;
            }
            if (this.isSetInterior()) {
                List<ShellPropertyType> sourceInterior;
                sourceInterior = this.getInterior();
                @SuppressWarnings("unchecked")
                List<ShellPropertyType> copyInterior = ((List<ShellPropertyType> ) strategy.copy(LocatorUtils.property(locator, "interior", sourceInterior), sourceInterior));
                copy.unsetInterior();
                List<ShellPropertyType> uniqueInteriorl = copy.getInterior();
                uniqueInteriorl.addAll(copyInterior);
            } else {
                copy.unsetInterior();
            }
        }
        return draftCopy;
    }

    public Object createNewInstance() {
        return new SolidType();
    }

    public void mergeFrom(Object left, Object right) {
        final MergeStrategy strategy = JAXBMergeStrategy.INSTANCE;
        mergeFrom(null, null, left, right, strategy);
    }

    public void mergeFrom(ObjectLocator leftLocator, ObjectLocator rightLocator, Object left, Object right, MergeStrategy strategy) {
        super.mergeFrom(leftLocator, rightLocator, left, right, strategy);
        if (right instanceof SolidType) {
            final SolidType target = this;
            final SolidType leftObject = ((SolidType) left);
            final SolidType rightObject = ((SolidType) right);
            {
                ShellPropertyType lhsExterior;
                lhsExterior = leftObject.getExterior();
                ShellPropertyType rhsExterior;
                rhsExterior = rightObject.getExterior();
                target.setExterior(((ShellPropertyType) strategy.merge(LocatorUtils.property(leftLocator, "exterior", lhsExterior), LocatorUtils.property(rightLocator, "exterior", rhsExterior), lhsExterior, rhsExterior)));
            }
            {
                List<ShellPropertyType> lhsInterior;
                lhsInterior = leftObject.getInterior();
                List<ShellPropertyType> rhsInterior;
                rhsInterior = rightObject.getInterior();
                target.unsetInterior();
                List<ShellPropertyType> uniqueInteriorl = target.getInterior();
                uniqueInteriorl.addAll(((List<ShellPropertyType> ) strategy.merge(LocatorUtils.property(leftLocator, "interior", lhsInterior), LocatorUtils.property(rightLocator, "interior", rhsInterior), lhsInterior, rhsInterior)));
            }
        }
    }

    public void setInterior(List<ShellPropertyType> value) {
        List<ShellPropertyType> draftl = this.getInterior();
        draftl.addAll(value);
    }

    public SolidType withExterior(ShellPropertyType value) {
        setExterior(value);
        return this;
    }

    public SolidType withInterior(ShellPropertyType... values) {
        if (values!= null) {
            for (ShellPropertyType value: values) {
                getInterior().add(value);
            }
        }
        return this;
    }

    public SolidType withInterior(Collection<ShellPropertyType> values) {
        if (values!= null) {
            getInterior().addAll(values);
        }
        return this;
    }

    public SolidType withInterior(List<ShellPropertyType> value) {
        setInterior(value);
        return this;
    }

    @Override
    public SolidType withSrsName(String value) {
        setSrsName(value);
        return this;
    }

    @Override
    public SolidType withSrsDimension(BigInteger value) {
        setSrsDimension(value);
        return this;
    }

    @Override
    public SolidType withAxisLabels(String... values) {
        if (values!= null) {
            for (String value: values) {
                getAxisLabels().add(value);
            }
        }
        return this;
    }

    @Override
    public SolidType withAxisLabels(Collection<String> values) {
        if (values!= null) {
            getAxisLabels().addAll(values);
        }
        return this;
    }

    @Override
    public SolidType withUomLabels(String... values) {
        if (values!= null) {
            for (String value: values) {
                getUomLabels().add(value);
            }
        }
        return this;
    }

    @Override
    public SolidType withUomLabels(Collection<String> values) {
        if (values!= null) {
            getUomLabels().addAll(values);
        }
        return this;
    }

    @Override
    public SolidType withAxisLabels(List<String> value) {
        setAxisLabels(value);
        return this;
    }

    @Override
    public SolidType withUomLabels(List<String> value) {
        setUomLabels(value);
        return this;
    }

    @Override
    public SolidType withMetaDataProperty(MetaDataPropertyType... values) {
        if (values!= null) {
            for (MetaDataPropertyType value: values) {
                getMetaDataProperty().add(value);
            }
        }
        return this;
    }

    @Override
    public SolidType withMetaDataProperty(Collection<MetaDataPropertyType> values) {
        if (values!= null) {
            getMetaDataProperty().addAll(values);
        }
        return this;
    }

    @Override
    public SolidType withDescription(StringOrRefType value) {
        setDescription(value);
        return this;
    }

    @Override
    public SolidType withDescriptionReference(ReferenceType value) {
        setDescriptionReference(value);
        return this;
    }

    @Override
    public SolidType withIdentifier(CodeWithAuthorityType value) {
        setIdentifier(value);
        return this;
    }

    @Override
    public SolidType withName(CodeType... values) {
        if (values!= null) {
            for (CodeType value: values) {
                getName().add(value);
            }
        }
        return this;
    }

    @Override
    public SolidType withName(Collection<CodeType> values) {
        if (values!= null) {
            getName().addAll(values);
        }
        return this;
    }

    @Override
    public SolidType withId(String value) {
        setId(value);
        return this;
    }

    @Override
    public SolidType withMetaDataProperty(List<MetaDataPropertyType> value) {
        setMetaDataProperty(value);
        return this;
    }

    @Override
    public SolidType withName(List<CodeType> value) {
        setName(value);
        return this;
    }

}
