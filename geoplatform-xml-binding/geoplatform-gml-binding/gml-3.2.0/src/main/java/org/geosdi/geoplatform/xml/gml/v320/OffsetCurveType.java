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
 * <p>Classe Java per OffsetCurveType complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="OffsetCurveType">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.opengis.net/gml}AbstractCurveSegmentType">
 *       &lt;sequence>
 *         &lt;element name="offsetBase" type="{http://www.opengis.net/gml}CurvePropertyType"/>
 *         &lt;element name="distance" type="{http://www.opengis.net/gml}LengthType"/>
 *         &lt;element name="refDirection" type="{http://www.opengis.net/gml}VectorType" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "OffsetCurveType", propOrder = {
    "offsetBase",
    "distance",
    "refDirection"
})
public class OffsetCurveType
    extends AbstractCurveSegmentType
    implements Cloneable, CopyTo, Equals, HashCode, MergeFrom, ToString
{

    @XmlElement(required = true)
    protected CurvePropertyType offsetBase;
    @XmlElement(required = true)
    protected LengthType distance;
    protected VectorType refDirection;

    /**
     * Recupera il valore della proprietà offsetBase.
     * 
     * @return
     *     possible object is
     *     {@link CurvePropertyType }
     *     
     */
    public CurvePropertyType getOffsetBase() {
        return offsetBase;
    }

    /**
     * Imposta il valore della proprietà offsetBase.
     * 
     * @param value
     *     allowed object is
     *     {@link CurvePropertyType }
     *     
     */
    public void setOffsetBase(CurvePropertyType value) {
        this.offsetBase = value;
    }

    public boolean isSetOffsetBase() {
        return (this.offsetBase!= null);
    }

    /**
     * Recupera il valore della proprietà distance.
     * 
     * @return
     *     possible object is
     *     {@link LengthType }
     *     
     */
    public LengthType getDistance() {
        return distance;
    }

    /**
     * Imposta il valore della proprietà distance.
     * 
     * @param value
     *     allowed object is
     *     {@link LengthType }
     *     
     */
    public void setDistance(LengthType value) {
        this.distance = value;
    }

    public boolean isSetDistance() {
        return (this.distance!= null);
    }

    /**
     * Recupera il valore della proprietà refDirection.
     * 
     * @return
     *     possible object is
     *     {@link VectorType }
     *     
     */
    public VectorType getRefDirection() {
        return refDirection;
    }

    /**
     * Imposta il valore della proprietà refDirection.
     * 
     * @param value
     *     allowed object is
     *     {@link VectorType }
     *     
     */
    public void setRefDirection(VectorType value) {
        this.refDirection = value;
    }

    public boolean isSetRefDirection() {
        return (this.refDirection!= null);
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
            CurvePropertyType theOffsetBase;
            theOffsetBase = this.getOffsetBase();
            strategy.appendField(locator, this, "offsetBase", buffer, theOffsetBase);
        }
        {
            LengthType theDistance;
            theDistance = this.getDistance();
            strategy.appendField(locator, this, "distance", buffer, theDistance);
        }
        {
            VectorType theRefDirection;
            theRefDirection = this.getRefDirection();
            strategy.appendField(locator, this, "refDirection", buffer, theRefDirection);
        }
        return buffer;
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof OffsetCurveType)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        if (!super.equals(thisLocator, thatLocator, object, strategy)) {
            return false;
        }
        final OffsetCurveType that = ((OffsetCurveType) object);
        {
            CurvePropertyType lhsOffsetBase;
            lhsOffsetBase = this.getOffsetBase();
            CurvePropertyType rhsOffsetBase;
            rhsOffsetBase = that.getOffsetBase();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "offsetBase", lhsOffsetBase), LocatorUtils.property(thatLocator, "offsetBase", rhsOffsetBase), lhsOffsetBase, rhsOffsetBase)) {
                return false;
            }
        }
        {
            LengthType lhsDistance;
            lhsDistance = this.getDistance();
            LengthType rhsDistance;
            rhsDistance = that.getDistance();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "distance", lhsDistance), LocatorUtils.property(thatLocator, "distance", rhsDistance), lhsDistance, rhsDistance)) {
                return false;
            }
        }
        {
            VectorType lhsRefDirection;
            lhsRefDirection = this.getRefDirection();
            VectorType rhsRefDirection;
            rhsRefDirection = that.getRefDirection();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "refDirection", lhsRefDirection), LocatorUtils.property(thatLocator, "refDirection", rhsRefDirection), lhsRefDirection, rhsRefDirection)) {
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
            CurvePropertyType theOffsetBase;
            theOffsetBase = this.getOffsetBase();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "offsetBase", theOffsetBase), currentHashCode, theOffsetBase);
        }
        {
            LengthType theDistance;
            theDistance = this.getDistance();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "distance", theDistance), currentHashCode, theDistance);
        }
        {
            VectorType theRefDirection;
            theRefDirection = this.getRefDirection();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "refDirection", theRefDirection), currentHashCode, theRefDirection);
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
        if (draftCopy instanceof OffsetCurveType) {
            final OffsetCurveType copy = ((OffsetCurveType) draftCopy);
            if (this.isSetOffsetBase()) {
                CurvePropertyType sourceOffsetBase;
                sourceOffsetBase = this.getOffsetBase();
                CurvePropertyType copyOffsetBase = ((CurvePropertyType) strategy.copy(LocatorUtils.property(locator, "offsetBase", sourceOffsetBase), sourceOffsetBase));
                copy.setOffsetBase(copyOffsetBase);
            } else {
                copy.offsetBase = null;
            }
            if (this.isSetDistance()) {
                LengthType sourceDistance;
                sourceDistance = this.getDistance();
                LengthType copyDistance = ((LengthType) strategy.copy(LocatorUtils.property(locator, "distance", sourceDistance), sourceDistance));
                copy.setDistance(copyDistance);
            } else {
                copy.distance = null;
            }
            if (this.isSetRefDirection()) {
                VectorType sourceRefDirection;
                sourceRefDirection = this.getRefDirection();
                VectorType copyRefDirection = ((VectorType) strategy.copy(LocatorUtils.property(locator, "refDirection", sourceRefDirection), sourceRefDirection));
                copy.setRefDirection(copyRefDirection);
            } else {
                copy.refDirection = null;
            }
        }
        return draftCopy;
    }

    public Object createNewInstance() {
        return new OffsetCurveType();
    }

    public void mergeFrom(Object left, Object right) {
        final MergeStrategy strategy = JAXBMergeStrategy.INSTANCE;
        mergeFrom(null, null, left, right, strategy);
    }

    public void mergeFrom(ObjectLocator leftLocator, ObjectLocator rightLocator, Object left, Object right, MergeStrategy strategy) {
        super.mergeFrom(leftLocator, rightLocator, left, right, strategy);
        if (right instanceof OffsetCurveType) {
            final OffsetCurveType target = this;
            final OffsetCurveType leftObject = ((OffsetCurveType) left);
            final OffsetCurveType rightObject = ((OffsetCurveType) right);
            {
                CurvePropertyType lhsOffsetBase;
                lhsOffsetBase = leftObject.getOffsetBase();
                CurvePropertyType rhsOffsetBase;
                rhsOffsetBase = rightObject.getOffsetBase();
                target.setOffsetBase(((CurvePropertyType) strategy.merge(LocatorUtils.property(leftLocator, "offsetBase", lhsOffsetBase), LocatorUtils.property(rightLocator, "offsetBase", rhsOffsetBase), lhsOffsetBase, rhsOffsetBase)));
            }
            {
                LengthType lhsDistance;
                lhsDistance = leftObject.getDistance();
                LengthType rhsDistance;
                rhsDistance = rightObject.getDistance();
                target.setDistance(((LengthType) strategy.merge(LocatorUtils.property(leftLocator, "distance", lhsDistance), LocatorUtils.property(rightLocator, "distance", rhsDistance), lhsDistance, rhsDistance)));
            }
            {
                VectorType lhsRefDirection;
                lhsRefDirection = leftObject.getRefDirection();
                VectorType rhsRefDirection;
                rhsRefDirection = rightObject.getRefDirection();
                target.setRefDirection(((VectorType) strategy.merge(LocatorUtils.property(leftLocator, "refDirection", lhsRefDirection), LocatorUtils.property(rightLocator, "refDirection", rhsRefDirection), lhsRefDirection, rhsRefDirection)));
            }
        }
    }

    public OffsetCurveType withOffsetBase(CurvePropertyType value) {
        setOffsetBase(value);
        return this;
    }

    public OffsetCurveType withDistance(LengthType value) {
        setDistance(value);
        return this;
    }

    public OffsetCurveType withRefDirection(VectorType value) {
        setRefDirection(value);
        return this;
    }

    @Override
    public OffsetCurveType withNumDerivativesAtStart(BigInteger value) {
        setNumDerivativesAtStart(value);
        return this;
    }

    @Override
    public OffsetCurveType withNumDerivativesAtEnd(BigInteger value) {
        setNumDerivativesAtEnd(value);
        return this;
    }

    @Override
    public OffsetCurveType withNumDerivativeInterior(BigInteger value) {
        setNumDerivativeInterior(value);
        return this;
    }

}
