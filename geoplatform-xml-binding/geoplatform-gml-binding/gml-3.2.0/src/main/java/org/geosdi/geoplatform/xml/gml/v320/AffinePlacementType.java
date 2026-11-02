/*
 *
 *    geo-platform
 *    Rich webgis framework
 *    http://geo-platform.org
 *   ====================================================================
 *
 *   Copyright (C) 2008-2026 geoSDI Group (CNR IMAA - Potenza - ITALY).
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
// Questo file è stato generato dall'Eclipse Implementation of JAXB, v4.0.9 
// Vedere https://eclipse-ee4j.github.io/jaxb-ri 
// Qualsiasi modifica a questo file andrà persa durante la ricompilazione dello schema di origine.
// Generato il: 2026.11.02 alle 03:38:19 PM CET
//

package org.geosdi.geoplatform.xml.gml.v320;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlSchemaType;
import jakarta.xml.bind.annotation.XmlType;
import org.jvnet.jaxb.lang.CopyStrategy;
import org.jvnet.jaxb.lang.CopyTo;
import org.jvnet.jaxb.lang.Equals;
import org.jvnet.jaxb.lang.EqualsStrategy;
import org.jvnet.jaxb.lang.HashCode;
import org.jvnet.jaxb.lang.HashCodeStrategy;
import org.jvnet.jaxb.lang.JAXBCopyStrategy;
import org.jvnet.jaxb.lang.JAXBEqualsStrategy;
import org.jvnet.jaxb.lang.JAXBHashCodeStrategy;
import org.jvnet.jaxb.lang.JAXBMergeStrategy;
import org.jvnet.jaxb.lang.JAXBToStringStrategy;
import org.jvnet.jaxb.lang.MergeFrom;
import org.jvnet.jaxb.lang.MergeStrategy;
import org.jvnet.jaxb.lang.ToString;
import org.jvnet.jaxb.lang.ToStringStrategy;
import org.jvnet.jaxb.locator.ObjectLocator;
import org.jvnet.jaxb.locator.util.LocatorUtils;


/**
 * <p>Classe Java per AffinePlacementType complex type.</p>
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.</p>
 * 
 * <pre>{@code
 * <complexType name="AffinePlacementType">
 *   <complexContent>
 *     <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       <sequence>
 *         <element name="location" type="{http://www.opengis.net/gml}DirectPositionType"/>
 *         <element name="refDirection" type="{http://www.opengis.net/gml}VectorType" maxOccurs="unbounded"/>
 *         <element name="inDimension" type="{http://www.w3.org/2001/XMLSchema}positiveInteger"/>
 *         <element name="outDimension" type="{http://www.w3.org/2001/XMLSchema}positiveInteger"/>
 *       </sequence>
 *     </restriction>
 *   </complexContent>
 * </complexType>
 * }</pre>
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
     * <p>This accessor method returns a reference to the live list,
     * not a snapshot. Therefore, any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a {@code set} method for the refDirection property.</p>
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * </p>
     * <pre>
     * getRefDirection().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link VectorType }
     * </p>
     * 
     * 
     * @return
     *     The value of the refDirection property.
     */
    public List<VectorType> getRefDirection() {
        if (refDirection == null) {
            refDirection = new ArrayList<>();
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

    @Override
    public String toString() {
        final ToStringStrategy strategy = JAXBToStringStrategy.getInstance();
        final StringBuilder buffer = new StringBuilder();
        append(null, buffer, strategy);
        return buffer.toString();
    }

    @Override
    public StringBuilder append(ObjectLocator locator, StringBuilder buffer, ToStringStrategy strategy) {
        strategy.appendStart(locator, this, buffer);
        appendFields(locator, buffer, strategy);
        strategy.appendEnd(locator, this, buffer);
        return buffer;
    }

    @Override
    public StringBuilder appendFields(ObjectLocator locator, StringBuilder buffer, ToStringStrategy strategy) {
        {
            DirectPositionType theLocation;
            theLocation = this.getLocation();
            strategy.appendField(locator, this, "location", buffer, theLocation, this.isSetLocation());
        }
        {
            List<VectorType> theRefDirection;
            theRefDirection = (this.isSetRefDirection()?this.getRefDirection():null);
            strategy.appendField(locator, this, "refDirection", buffer, theRefDirection, this.isSetRefDirection());
        }
        {
            BigInteger theInDimension;
            theInDimension = this.getInDimension();
            strategy.appendField(locator, this, "inDimension", buffer, theInDimension, this.isSetInDimension());
        }
        {
            BigInteger theOutDimension;
            theOutDimension = this.getOutDimension();
            strategy.appendField(locator, this, "outDimension", buffer, theOutDimension, this.isSetOutDimension());
        }
        return buffer;
    }

    public void setRefDirection(List<VectorType> value) {
        this.refDirection = null;
        if (value!= null) {
            List<VectorType> draftl = this.getRefDirection();
            draftl.addAll(value);
        }
    }

    @Override
    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if ((object == null)||(this.getClass()!= object.getClass())) {
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
            if (!strategy.equals(LocatorUtils.property(thisLocator, "location", lhsLocation), LocatorUtils.property(thatLocator, "location", rhsLocation), lhsLocation, rhsLocation, this.isSetLocation(), that.isSetLocation())) {
                return false;
            }
        }
        {
            List<VectorType> lhsRefDirection;
            lhsRefDirection = (this.isSetRefDirection()?this.getRefDirection():null);
            List<VectorType> rhsRefDirection;
            rhsRefDirection = (that.isSetRefDirection()?that.getRefDirection():null);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "refDirection", lhsRefDirection), LocatorUtils.property(thatLocator, "refDirection", rhsRefDirection), lhsRefDirection, rhsRefDirection, this.isSetRefDirection(), that.isSetRefDirection())) {
                return false;
            }
        }
        {
            BigInteger lhsInDimension;
            lhsInDimension = this.getInDimension();
            BigInteger rhsInDimension;
            rhsInDimension = that.getInDimension();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "inDimension", lhsInDimension), LocatorUtils.property(thatLocator, "inDimension", rhsInDimension), lhsInDimension, rhsInDimension, this.isSetInDimension(), that.isSetInDimension())) {
                return false;
            }
        }
        {
            BigInteger lhsOutDimension;
            lhsOutDimension = this.getOutDimension();
            BigInteger rhsOutDimension;
            rhsOutDimension = that.getOutDimension();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "outDimension", lhsOutDimension), LocatorUtils.property(thatLocator, "outDimension", rhsOutDimension), lhsOutDimension, rhsOutDimension, this.isSetOutDimension(), that.isSetOutDimension())) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean equals(Object object) {
        final EqualsStrategy strategy = JAXBEqualsStrategy.getInstance();
        return equals(null, null, object, strategy);
    }

    @Override
    public int hashCode(ObjectLocator locator, HashCodeStrategy strategy) {
        int currentHashCode = 1;
        {
            DirectPositionType theLocation;
            theLocation = this.getLocation();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "location", theLocation), currentHashCode, theLocation, this.isSetLocation());
        }
        {
            List<VectorType> theRefDirection;
            theRefDirection = (this.isSetRefDirection()?this.getRefDirection():null);
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "refDirection", theRefDirection), currentHashCode, theRefDirection, this.isSetRefDirection());
        }
        {
            BigInteger theInDimension;
            theInDimension = this.getInDimension();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "inDimension", theInDimension), currentHashCode, theInDimension, this.isSetInDimension());
        }
        {
            BigInteger theOutDimension;
            theOutDimension = this.getOutDimension();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "outDimension", theOutDimension), currentHashCode, theOutDimension, this.isSetOutDimension());
        }
        return currentHashCode;
    }

    @Override
    public int hashCode() {
        final HashCodeStrategy strategy = JAXBHashCodeStrategy.getInstance();
        return this.hashCode(null, strategy);
    }

    @Override
    public Object clone() {
        return copyTo(createNewInstance());
    }

    @Override
    public Object copyTo(Object target) {
        final CopyStrategy strategy = JAXBCopyStrategy.getInstance();
        return copyTo(null, target, strategy);
    }

    @Override
    public Object copyTo(ObjectLocator locator, Object target, CopyStrategy strategy) {
        final Object draftCopy = ((target == null)?createNewInstance():target);
        if (draftCopy instanceof AffinePlacementType) {
            final AffinePlacementType copy = ((AffinePlacementType) draftCopy);
            {
                java.lang.Boolean locationShouldBeCopiedAndSet = strategy.shouldBeCopiedAndSet(locator, this.isSetLocation());
                if (locationShouldBeCopiedAndSet == java.lang.Boolean.TRUE) {
                    DirectPositionType sourceLocation;
                    sourceLocation = this.getLocation();
                    DirectPositionType copyLocation = ((DirectPositionType) strategy.copy(LocatorUtils.property(locator, "location", sourceLocation), sourceLocation, this.isSetLocation()));
                    copy.setLocation(copyLocation);
                } else {
                    if (locationShouldBeCopiedAndSet == java.lang.Boolean.FALSE) {
                        copy.location = null;
                    }
                }
            }
            {
                java.lang.Boolean refDirectionShouldBeCopiedAndSet = strategy.shouldBeCopiedAndSet(locator, this.isSetRefDirection());
                if (refDirectionShouldBeCopiedAndSet == java.lang.Boolean.TRUE) {
                    List<VectorType> sourceRefDirection;
                    sourceRefDirection = (this.isSetRefDirection()?this.getRefDirection():null);
                    @SuppressWarnings("unchecked")
                    List<VectorType> copyRefDirection = ((List<VectorType> ) strategy.copy(LocatorUtils.property(locator, "refDirection", sourceRefDirection), sourceRefDirection, this.isSetRefDirection()));
                    copy.setRefDirection(copyRefDirection);
                } else {
                    if (refDirectionShouldBeCopiedAndSet == java.lang.Boolean.FALSE) {
                        copy.unsetRefDirection();
                    }
                }
            }
            {
                java.lang.Boolean inDimensionShouldBeCopiedAndSet = strategy.shouldBeCopiedAndSet(locator, this.isSetInDimension());
                if (inDimensionShouldBeCopiedAndSet == java.lang.Boolean.TRUE) {
                    BigInteger sourceInDimension;
                    sourceInDimension = this.getInDimension();
                    BigInteger copyInDimension = ((BigInteger) strategy.copy(LocatorUtils.property(locator, "inDimension", sourceInDimension), sourceInDimension, this.isSetInDimension()));
                    copy.setInDimension(copyInDimension);
                } else {
                    if (inDimensionShouldBeCopiedAndSet == java.lang.Boolean.FALSE) {
                        copy.inDimension = null;
                    }
                }
            }
            {
                java.lang.Boolean outDimensionShouldBeCopiedAndSet = strategy.shouldBeCopiedAndSet(locator, this.isSetOutDimension());
                if (outDimensionShouldBeCopiedAndSet == java.lang.Boolean.TRUE) {
                    BigInteger sourceOutDimension;
                    sourceOutDimension = this.getOutDimension();
                    BigInteger copyOutDimension = ((BigInteger) strategy.copy(LocatorUtils.property(locator, "outDimension", sourceOutDimension), sourceOutDimension, this.isSetOutDimension()));
                    copy.setOutDimension(copyOutDimension);
                } else {
                    if (outDimensionShouldBeCopiedAndSet == java.lang.Boolean.FALSE) {
                        copy.outDimension = null;
                    }
                }
            }
        }
        return draftCopy;
    }

    @Override
    public Object createNewInstance() {
        return new AffinePlacementType();
    }

    @Override
    public void mergeFrom(Object left, Object right) {
        final MergeStrategy strategy = JAXBMergeStrategy.getInstance();
        mergeFrom(null, null, left, right, strategy);
    }

    @Override
    public void mergeFrom(ObjectLocator leftLocator, ObjectLocator rightLocator, Object left, Object right, MergeStrategy strategy) {
        if (right instanceof AffinePlacementType) {
            final AffinePlacementType target = this;
            final AffinePlacementType leftObject = ((AffinePlacementType) left);
            final AffinePlacementType rightObject = ((AffinePlacementType) right);
            {
                java.lang.Boolean locationShouldBeMergedAndSet = strategy.shouldBeMergedAndSet(leftLocator, rightLocator, leftObject.isSetLocation(), rightObject.isSetLocation());
                if (locationShouldBeMergedAndSet == java.lang.Boolean.TRUE) {
                    DirectPositionType lhsLocation;
                    lhsLocation = leftObject.getLocation();
                    DirectPositionType rhsLocation;
                    rhsLocation = rightObject.getLocation();
                    DirectPositionType mergedLocation = ((DirectPositionType) strategy.merge(LocatorUtils.property(leftLocator, "location", lhsLocation), LocatorUtils.property(rightLocator, "location", rhsLocation), lhsLocation, rhsLocation, leftObject.isSetLocation(), rightObject.isSetLocation()));
                    target.setLocation(mergedLocation);
                } else {
                    if (locationShouldBeMergedAndSet == java.lang.Boolean.FALSE) {
                        target.location = null;
                    }
                }
            }
            {
                java.lang.Boolean refDirectionShouldBeMergedAndSet = strategy.shouldBeMergedAndSet(leftLocator, rightLocator, leftObject.isSetRefDirection(), rightObject.isSetRefDirection());
                if (refDirectionShouldBeMergedAndSet == java.lang.Boolean.TRUE) {
                    List<VectorType> lhsRefDirection;
                    lhsRefDirection = (leftObject.isSetRefDirection()?leftObject.getRefDirection():null);
                    List<VectorType> rhsRefDirection;
                    rhsRefDirection = (rightObject.isSetRefDirection()?rightObject.getRefDirection():null);
                    List<VectorType> mergedRefDirection = ((List<VectorType> ) strategy.merge(LocatorUtils.property(leftLocator, "refDirection", lhsRefDirection), LocatorUtils.property(rightLocator, "refDirection", rhsRefDirection), lhsRefDirection, rhsRefDirection, leftObject.isSetRefDirection(), rightObject.isSetRefDirection()));
                    target.setRefDirection(mergedRefDirection);
                } else {
                    if (refDirectionShouldBeMergedAndSet == java.lang.Boolean.FALSE) {
                        target.unsetRefDirection();
                    }
                }
            }
            {
                java.lang.Boolean inDimensionShouldBeMergedAndSet = strategy.shouldBeMergedAndSet(leftLocator, rightLocator, leftObject.isSetInDimension(), rightObject.isSetInDimension());
                if (inDimensionShouldBeMergedAndSet == java.lang.Boolean.TRUE) {
                    BigInteger lhsInDimension;
                    lhsInDimension = leftObject.getInDimension();
                    BigInteger rhsInDimension;
                    rhsInDimension = rightObject.getInDimension();
                    BigInteger mergedInDimension = ((BigInteger) strategy.merge(LocatorUtils.property(leftLocator, "inDimension", lhsInDimension), LocatorUtils.property(rightLocator, "inDimension", rhsInDimension), lhsInDimension, rhsInDimension, leftObject.isSetInDimension(), rightObject.isSetInDimension()));
                    target.setInDimension(mergedInDimension);
                } else {
                    if (inDimensionShouldBeMergedAndSet == java.lang.Boolean.FALSE) {
                        target.inDimension = null;
                    }
                }
            }
            {
                java.lang.Boolean outDimensionShouldBeMergedAndSet = strategy.shouldBeMergedAndSet(leftLocator, rightLocator, leftObject.isSetOutDimension(), rightObject.isSetOutDimension());
                if (outDimensionShouldBeMergedAndSet == java.lang.Boolean.TRUE) {
                    BigInteger lhsOutDimension;
                    lhsOutDimension = leftObject.getOutDimension();
                    BigInteger rhsOutDimension;
                    rhsOutDimension = rightObject.getOutDimension();
                    BigInteger mergedOutDimension = ((BigInteger) strategy.merge(LocatorUtils.property(leftLocator, "outDimension", lhsOutDimension), LocatorUtils.property(rightLocator, "outDimension", rhsOutDimension), lhsOutDimension, rhsOutDimension, leftObject.isSetOutDimension(), rightObject.isSetOutDimension()));
                    target.setOutDimension(mergedOutDimension);
                } else {
                    if (outDimensionShouldBeMergedAndSet == java.lang.Boolean.FALSE) {
                        target.outDimension = null;
                    }
                }
            }
        }
    }

}
