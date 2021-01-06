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
import java.util.Collection;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
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
 * <p>Classe Java per OrientableCurveType complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="OrientableCurveType">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.opengis.net/gml}AbstractCurveType">
 *       &lt;sequence>
 *         &lt;element ref="{http://www.opengis.net/gml}baseCurve"/>
 *       &lt;/sequence>
 *       &lt;attribute name="orientation" type="{http://www.opengis.net/gml}SignType" default="+" />
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "OrientableCurveType", propOrder = {
    "baseCurve"
})
public class OrientableCurveType
    extends AbstractCurveType
    implements Cloneable, CopyTo, Equals, HashCode, MergeFrom, ToString
{

    @XmlElement(required = true)
    protected CurvePropertyType baseCurve;
    @XmlAttribute(name = "orientation")
    protected SignType orientation;

    /**
     * Recupera il valore della proprietà baseCurve.
     * 
     * @return
     *     possible object is
     *     {@link CurvePropertyType }
     *     
     */
    public CurvePropertyType getBaseCurve() {
        return baseCurve;
    }

    /**
     * Imposta il valore della proprietà baseCurve.
     * 
     * @param value
     *     allowed object is
     *     {@link CurvePropertyType }
     *     
     */
    public void setBaseCurve(CurvePropertyType value) {
        this.baseCurve = value;
    }

    public boolean isSetBaseCurve() {
        return (this.baseCurve!= null);
    }

    /**
     * Recupera il valore della proprietà orientation.
     * 
     * @return
     *     possible object is
     *     {@link SignType }
     *     
     */
    public SignType getOrientation() {
        if (orientation == null) {
            return SignType.VALUE_2;
        } else {
            return orientation;
        }
    }

    /**
     * Imposta il valore della proprietà orientation.
     * 
     * @param value
     *     allowed object is
     *     {@link SignType }
     *     
     */
    public void setOrientation(SignType value) {
        this.orientation = value;
    }

    public boolean isSetOrientation() {
        return (this.orientation!= null);
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
            CurvePropertyType theBaseCurve;
            theBaseCurve = this.getBaseCurve();
            strategy.appendField(locator, this, "baseCurve", buffer, theBaseCurve);
        }
        {
            SignType theOrientation;
            theOrientation = this.getOrientation();
            strategy.appendField(locator, this, "orientation", buffer, theOrientation);
        }
        return buffer;
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof OrientableCurveType)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        if (!super.equals(thisLocator, thatLocator, object, strategy)) {
            return false;
        }
        final OrientableCurveType that = ((OrientableCurveType) object);
        {
            CurvePropertyType lhsBaseCurve;
            lhsBaseCurve = this.getBaseCurve();
            CurvePropertyType rhsBaseCurve;
            rhsBaseCurve = that.getBaseCurve();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "baseCurve", lhsBaseCurve), LocatorUtils.property(thatLocator, "baseCurve", rhsBaseCurve), lhsBaseCurve, rhsBaseCurve)) {
                return false;
            }
        }
        {
            SignType lhsOrientation;
            lhsOrientation = this.getOrientation();
            SignType rhsOrientation;
            rhsOrientation = that.getOrientation();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "orientation", lhsOrientation), LocatorUtils.property(thatLocator, "orientation", rhsOrientation), lhsOrientation, rhsOrientation)) {
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
            CurvePropertyType theBaseCurve;
            theBaseCurve = this.getBaseCurve();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "baseCurve", theBaseCurve), currentHashCode, theBaseCurve);
        }
        {
            SignType theOrientation;
            theOrientation = this.getOrientation();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "orientation", theOrientation), currentHashCode, theOrientation);
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
        if (draftCopy instanceof OrientableCurveType) {
            final OrientableCurveType copy = ((OrientableCurveType) draftCopy);
            if (this.isSetBaseCurve()) {
                CurvePropertyType sourceBaseCurve;
                sourceBaseCurve = this.getBaseCurve();
                CurvePropertyType copyBaseCurve = ((CurvePropertyType) strategy.copy(LocatorUtils.property(locator, "baseCurve", sourceBaseCurve), sourceBaseCurve));
                copy.setBaseCurve(copyBaseCurve);
            } else {
                copy.baseCurve = null;
            }
            if (this.isSetOrientation()) {
                SignType sourceOrientation;
                sourceOrientation = this.getOrientation();
                SignType copyOrientation = ((SignType) strategy.copy(LocatorUtils.property(locator, "orientation", sourceOrientation), sourceOrientation));
                copy.setOrientation(copyOrientation);
            } else {
                copy.orientation = null;
            }
        }
        return draftCopy;
    }

    public Object createNewInstance() {
        return new OrientableCurveType();
    }

    public void mergeFrom(Object left, Object right) {
        final MergeStrategy strategy = JAXBMergeStrategy.INSTANCE;
        mergeFrom(null, null, left, right, strategy);
    }

    public void mergeFrom(ObjectLocator leftLocator, ObjectLocator rightLocator, Object left, Object right, MergeStrategy strategy) {
        super.mergeFrom(leftLocator, rightLocator, left, right, strategy);
        if (right instanceof OrientableCurveType) {
            final OrientableCurveType target = this;
            final OrientableCurveType leftObject = ((OrientableCurveType) left);
            final OrientableCurveType rightObject = ((OrientableCurveType) right);
            {
                CurvePropertyType lhsBaseCurve;
                lhsBaseCurve = leftObject.getBaseCurve();
                CurvePropertyType rhsBaseCurve;
                rhsBaseCurve = rightObject.getBaseCurve();
                target.setBaseCurve(((CurvePropertyType) strategy.merge(LocatorUtils.property(leftLocator, "baseCurve", lhsBaseCurve), LocatorUtils.property(rightLocator, "baseCurve", rhsBaseCurve), lhsBaseCurve, rhsBaseCurve)));
            }
            {
                SignType lhsOrientation;
                lhsOrientation = leftObject.getOrientation();
                SignType rhsOrientation;
                rhsOrientation = rightObject.getOrientation();
                target.setOrientation(((SignType) strategy.merge(LocatorUtils.property(leftLocator, "orientation", lhsOrientation), LocatorUtils.property(rightLocator, "orientation", rhsOrientation), lhsOrientation, rhsOrientation)));
            }
        }
    }

    public OrientableCurveType withBaseCurve(CurvePropertyType value) {
        setBaseCurve(value);
        return this;
    }

    public OrientableCurveType withOrientation(SignType value) {
        setOrientation(value);
        return this;
    }

    @Override
    public OrientableCurveType withSrsName(String value) {
        setSrsName(value);
        return this;
    }

    @Override
    public OrientableCurveType withSrsDimension(BigInteger value) {
        setSrsDimension(value);
        return this;
    }

    @Override
    public OrientableCurveType withAxisLabels(String... values) {
        if (values!= null) {
            for (String value: values) {
                getAxisLabels().add(value);
            }
        }
        return this;
    }

    @Override
    public OrientableCurveType withAxisLabels(Collection<String> values) {
        if (values!= null) {
            getAxisLabels().addAll(values);
        }
        return this;
    }

    @Override
    public OrientableCurveType withUomLabels(String... values) {
        if (values!= null) {
            for (String value: values) {
                getUomLabels().add(value);
            }
        }
        return this;
    }

    @Override
    public OrientableCurveType withUomLabels(Collection<String> values) {
        if (values!= null) {
            getUomLabels().addAll(values);
        }
        return this;
    }

    @Override
    public OrientableCurveType withAxisLabels(List<String> value) {
        setAxisLabels(value);
        return this;
    }

    @Override
    public OrientableCurveType withUomLabels(List<String> value) {
        setUomLabels(value);
        return this;
    }

    @Override
    public OrientableCurveType withMetaDataProperty(MetaDataPropertyType... values) {
        if (values!= null) {
            for (MetaDataPropertyType value: values) {
                getMetaDataProperty().add(value);
            }
        }
        return this;
    }

    @Override
    public OrientableCurveType withMetaDataProperty(Collection<MetaDataPropertyType> values) {
        if (values!= null) {
            getMetaDataProperty().addAll(values);
        }
        return this;
    }

    @Override
    public OrientableCurveType withDescription(StringOrRefType value) {
        setDescription(value);
        return this;
    }

    @Override
    public OrientableCurveType withDescriptionReference(ReferenceType value) {
        setDescriptionReference(value);
        return this;
    }

    @Override
    public OrientableCurveType withIdentifier(CodeWithAuthorityType value) {
        setIdentifier(value);
        return this;
    }

    @Override
    public OrientableCurveType withName(CodeType... values) {
        if (values!= null) {
            for (CodeType value: values) {
                getName().add(value);
            }
        }
        return this;
    }

    @Override
    public OrientableCurveType withName(Collection<CodeType> values) {
        if (values!= null) {
            getName().addAll(values);
        }
        return this;
    }

    @Override
    public OrientableCurveType withId(String value) {
        setId(value);
        return this;
    }

    @Override
    public OrientableCurveType withMetaDataProperty(List<MetaDataPropertyType> value) {
        setMetaDataProperty(value);
        return this;
    }

    @Override
    public OrientableCurveType withName(List<CodeType> value) {
        setName(value);
        return this;
    }

}
