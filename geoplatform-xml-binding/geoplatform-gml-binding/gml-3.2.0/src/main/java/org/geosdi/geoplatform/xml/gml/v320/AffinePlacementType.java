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
import javax.xml.bind.annotation.XmlElement;
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
 * <p>Classe Java per AffinePlacementType complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="AffinePlacementType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="location" type="{http://www.opengis.net/gml}DirectPositionType"/>
 *         &lt;element name="refDirection" type="{http://www.opengis.net/gml}VectorType" maxOccurs="unbounded"/>
 *         &lt;element name="inDimension" type="{http://www.w3.org/2001/XMLSchema}positiveInteger"/>
 *         &lt;element name="outDimension" type="{http://www.w3.org/2001/XMLSchema}positiveInteger"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AffinePlacementType", propOrder = {
    "location",
    "refDirection",
    "inDimension",
    "outDimension"
})
public class AffinePlacementType implements Cloneable, CopyTo, Equals, HashCode, MergeFrom, ToString
{

    @XmlElement(required = true)
    protected DirectPositionType location;
    @XmlElement(required = true)
    protected List<VectorType> refDirection;
    @XmlElement(required = true)
    @XmlSchemaType(name = "positiveInteger")
    protected BigInteger inDimension;
    @XmlElement(required = true)
    @XmlSchemaType(name = "positiveInteger")
    protected BigInteger outDimension;

    /**
     * Recupera il valore della proprietà location.
     * 
     * @return
     *     possible object is
     *     {@link DirectPositionType }
     *     
     */
    public DirectPositionType getLocation() {
        return location;
    }

    /**
     * Imposta il valore della proprietà location.
     * 
     * @param value
     *     allowed object is
     *     {@link DirectPositionType }
     *     
     */
    public void setLocation(DirectPositionType value) {
        this.location = value;
    }

    public boolean isSetLocation() {
        return (this.location!= null);
    }

    /**
     * Gets the value of the refDirection property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the refDirection property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getRefDirection().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link VectorType }
     * 
     * 
     */
    public List<VectorType> getRefDirection() {
        if (refDirection == null) {
            refDirection = new ArrayList<VectorType>();
        }
        return this.refDirection;
    }

    public boolean isSetRefDirection() {
        return ((this.refDirection!= null)&&(!this.refDirection.isEmpty()));
    }

    public void unsetRefDirection() {
        this.refDirection = null;
    }

    /**
     * Recupera il valore della proprietà inDimension.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getInDimension() {
        return inDimension;
    }

    /**
     * Imposta il valore della proprietà inDimension.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setInDimension(BigInteger value) {
        this.inDimension = value;
    }

    public boolean isSetInDimension() {
        return (this.inDimension!= null);
    }

    /**
     * Recupera il valore della proprietà outDimension.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getOutDimension() {
        return outDimension;
    }

    /**
     * Imposta il valore della proprietà outDimension.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setOutDimension(BigInteger value) {
        this.outDimension = value;
    }

    public boolean isSetOutDimension() {
        return (this.outDimension!= null);
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
        {
            DirectPositionType theLocation;
            theLocation = this.getLocation();
            strategy.appendField(locator, this, "location", buffer, theLocation);
        }
        {
            List<VectorType> theRefDirection;
            theRefDirection = this.getRefDirection();
            strategy.appendField(locator, this, "refDirection", buffer, theRefDirection);
        }
        {
            BigInteger theInDimension;
            theInDimension = this.getInDimension();
            strategy.appendField(locator, this, "inDimension", buffer, theInDimension);
        }
        {
            BigInteger theOutDimension;
            theOutDimension = this.getOutDimension();
            strategy.appendField(locator, this, "outDimension", buffer, theOutDimension);
        }
        return buffer;
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof AffinePlacementType)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        final AffinePlacementType that = ((AffinePlacementType) object);
        {
            DirectPositionType lhsLocation;
            lhsLocation = this.getLocation();
            DirectPositionType rhsLocation;
            rhsLocation = that.getLocation();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "location", lhsLocation), LocatorUtils.property(thatLocator, "location", rhsLocation), lhsLocation, rhsLocation)) {
                return false;
            }
        }
        {
            List<VectorType> lhsRefDirection;
            lhsRefDirection = this.getRefDirection();
            List<VectorType> rhsRefDirection;
            rhsRefDirection = that.getRefDirection();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "refDirection", lhsRefDirection), LocatorUtils.property(thatLocator, "refDirection", rhsRefDirection), lhsRefDirection, rhsRefDirection)) {
                return false;
            }
        }
        {
            BigInteger lhsInDimension;
            lhsInDimension = this.getInDimension();
            BigInteger rhsInDimension;
            rhsInDimension = that.getInDimension();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "inDimension", lhsInDimension), LocatorUtils.property(thatLocator, "inDimension", rhsInDimension), lhsInDimension, rhsInDimension)) {
                return false;
            }
        }
        {
            BigInteger lhsOutDimension;
            lhsOutDimension = this.getOutDimension();
            BigInteger rhsOutDimension;
            rhsOutDimension = that.getOutDimension();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "outDimension", lhsOutDimension), LocatorUtils.property(thatLocator, "outDimension", rhsOutDimension), lhsOutDimension, rhsOutDimension)) {
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
        int currentHashCode = 1;
        {
            DirectPositionType theLocation;
            theLocation = this.getLocation();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "location", theLocation), currentHashCode, theLocation);
        }
        {
            List<VectorType> theRefDirection;
            theRefDirection = this.getRefDirection();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "refDirection", theRefDirection), currentHashCode, theRefDirection);
        }
        {
            BigInteger theInDimension;
            theInDimension = this.getInDimension();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "inDimension", theInDimension), currentHashCode, theInDimension);
        }
        {
            BigInteger theOutDimension;
            theOutDimension = this.getOutDimension();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "outDimension", theOutDimension), currentHashCode, theOutDimension);
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
        if (draftCopy instanceof AffinePlacementType) {
            final AffinePlacementType copy = ((AffinePlacementType) draftCopy);
            if (this.isSetLocation()) {
                DirectPositionType sourceLocation;
                sourceLocation = this.getLocation();
                DirectPositionType copyLocation = ((DirectPositionType) strategy.copy(LocatorUtils.property(locator, "location", sourceLocation), sourceLocation));
                copy.setLocation(copyLocation);
            } else {
                copy.location = null;
            }
            if (this.isSetRefDirection()) {
                List<VectorType> sourceRefDirection;
                sourceRefDirection = this.getRefDirection();
                @SuppressWarnings("unchecked")
                List<VectorType> copyRefDirection = ((List<VectorType> ) strategy.copy(LocatorUtils.property(locator, "refDirection", sourceRefDirection), sourceRefDirection));
                copy.unsetRefDirection();
                List<VectorType> uniqueRefDirectionl = copy.getRefDirection();
                uniqueRefDirectionl.addAll(copyRefDirection);
            } else {
                copy.unsetRefDirection();
            }
            if (this.isSetInDimension()) {
                BigInteger sourceInDimension;
                sourceInDimension = this.getInDimension();
                BigInteger copyInDimension = ((BigInteger) strategy.copy(LocatorUtils.property(locator, "inDimension", sourceInDimension), sourceInDimension));
                copy.setInDimension(copyInDimension);
            } else {
                copy.inDimension = null;
            }
            if (this.isSetOutDimension()) {
                BigInteger sourceOutDimension;
                sourceOutDimension = this.getOutDimension();
                BigInteger copyOutDimension = ((BigInteger) strategy.copy(LocatorUtils.property(locator, "outDimension", sourceOutDimension), sourceOutDimension));
                copy.setOutDimension(copyOutDimension);
            } else {
                copy.outDimension = null;
            }
        }
        return draftCopy;
    }

    public Object createNewInstance() {
        return new AffinePlacementType();
    }

    public void mergeFrom(Object left, Object right) {
        final MergeStrategy strategy = JAXBMergeStrategy.INSTANCE;
        mergeFrom(null, null, left, right, strategy);
    }

    public void mergeFrom(ObjectLocator leftLocator, ObjectLocator rightLocator, Object left, Object right, MergeStrategy strategy) {
        if (right instanceof AffinePlacementType) {
            final AffinePlacementType target = this;
            final AffinePlacementType leftObject = ((AffinePlacementType) left);
            final AffinePlacementType rightObject = ((AffinePlacementType) right);
            {
                DirectPositionType lhsLocation;
                lhsLocation = leftObject.getLocation();
                DirectPositionType rhsLocation;
                rhsLocation = rightObject.getLocation();
                target.setLocation(((DirectPositionType) strategy.merge(LocatorUtils.property(leftLocator, "location", lhsLocation), LocatorUtils.property(rightLocator, "location", rhsLocation), lhsLocation, rhsLocation)));
            }
            {
                List<VectorType> lhsRefDirection;
                lhsRefDirection = leftObject.getRefDirection();
                List<VectorType> rhsRefDirection;
                rhsRefDirection = rightObject.getRefDirection();
                target.unsetRefDirection();
                List<VectorType> uniqueRefDirectionl = target.getRefDirection();
                uniqueRefDirectionl.addAll(((List<VectorType> ) strategy.merge(LocatorUtils.property(leftLocator, "refDirection", lhsRefDirection), LocatorUtils.property(rightLocator, "refDirection", rhsRefDirection), lhsRefDirection, rhsRefDirection)));
            }
            {
                BigInteger lhsInDimension;
                lhsInDimension = leftObject.getInDimension();
                BigInteger rhsInDimension;
                rhsInDimension = rightObject.getInDimension();
                target.setInDimension(((BigInteger) strategy.merge(LocatorUtils.property(leftLocator, "inDimension", lhsInDimension), LocatorUtils.property(rightLocator, "inDimension", rhsInDimension), lhsInDimension, rhsInDimension)));
            }
            {
                BigInteger lhsOutDimension;
                lhsOutDimension = leftObject.getOutDimension();
                BigInteger rhsOutDimension;
                rhsOutDimension = rightObject.getOutDimension();
                target.setOutDimension(((BigInteger) strategy.merge(LocatorUtils.property(leftLocator, "outDimension", lhsOutDimension), LocatorUtils.property(rightLocator, "outDimension", rhsOutDimension), lhsOutDimension, rhsOutDimension)));
            }
        }
    }

    public void setRefDirection(List<VectorType> value) {
        List<VectorType> draftl = this.getRefDirection();
        draftl.addAll(value);
    }

    public AffinePlacementType withLocation(DirectPositionType value) {
        setLocation(value);
        return this;
    }

    public AffinePlacementType withRefDirection(VectorType... values) {
        if (values!= null) {
            for (VectorType value: values) {
                getRefDirection().add(value);
            }
        }
        return this;
    }

    public AffinePlacementType withRefDirection(Collection<VectorType> values) {
        if (values!= null) {
            getRefDirection().addAll(values);
        }
        return this;
    }

    public AffinePlacementType withInDimension(BigInteger value) {
        setInDimension(value);
        return this;
    }

    public AffinePlacementType withOutDimension(BigInteger value) {
        setOutDimension(value);
        return this;
    }

    public AffinePlacementType withRefDirection(List<VectorType> value) {
        setRefDirection(value);
        return this;
    }

}
