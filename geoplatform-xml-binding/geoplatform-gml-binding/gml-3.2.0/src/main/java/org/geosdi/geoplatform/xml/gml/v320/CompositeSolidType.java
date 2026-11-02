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

import java.util.ArrayList;
import java.util.List;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
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
 * <p>Classe Java per CompositeSolidType complex type.</p>
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.</p>
 * 
 * <pre>{@code
 * <complexType name="CompositeSolidType">
 *   <complexContent>
 *     <extension base="{http://www.opengis.net/gml}AbstractSolidType">
 *       <sequence>
 *         <element ref="{http://www.opengis.net/gml}solidMember" maxOccurs="unbounded"/>
 *       </sequence>
 *       <attGroup ref="{http://www.opengis.net/gml}AggregationAttributeGroup"/>
 *     </extension>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CompositeSolidType", propOrder = {
    "solidMember"
})
public class CompositeSolidType
    extends AbstractSolidType
    implements Cloneable, CopyTo, Equals, HashCode, MergeFrom, ToString
{

    /**
     * This property element either references a solid via the XLink-attributes or contains the solid element. A solid element is any element, which is substitutable for gml:AbstractSolid.
     * 
     */
    @XmlElement(required = true)
    protected List<SolidPropertyType> solidMember;
    @XmlAttribute(name = "aggregationType")
    protected AggregationType aggregationType;

    /**
     * This property element either references a solid via the XLink-attributes or contains the solid element. A solid element is any element, which is substitutable for gml:AbstractSolid.
     * 
     * Gets the value of the solidMember property.
     * 
     * <p>This accessor method returns a reference to the live list,
     * not a snapshot. Therefore, any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a {@code set} method for the solidMember property.</p>
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * </p>
     * <pre>
     * getSolidMember().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link SolidPropertyType }
     * </p>
     * 
     * 
     * @return
     *     The value of the solidMember property.
     */
    public List<SolidPropertyType> getSolidMember() {
        if (solidMember == null) {
            solidMember = new ArrayList<>();
        }
        return this.solidMember;
    }

    public boolean isSetSolidMember() {
        return ((this.solidMember!= null)&&(!this.solidMember.isEmpty()));
    }

    public void unsetSolidMember() {
        this.solidMember = null;
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
        super.appendFields(locator, buffer, strategy);
        {
            List<SolidPropertyType> theSolidMember;
            theSolidMember = (this.isSetSolidMember()?this.getSolidMember():null);
            strategy.appendField(locator, this, "solidMember", buffer, theSolidMember, this.isSetSolidMember());
        }
        {
            AggregationType theAggregationType;
            theAggregationType = this.getAggregationType();
            strategy.appendField(locator, this, "aggregationType", buffer, theAggregationType, this.isSetAggregationType());
        }
        return buffer;
    }

    public void setSolidMember(List<SolidPropertyType> value) {
        this.solidMember = null;
        if (value!= null) {
            List<SolidPropertyType> draftl = this.getSolidMember();
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
        if (!super.equals(thisLocator, thatLocator, object, strategy)) {
            return false;
        }
        final CompositeSolidType that = ((CompositeSolidType) object);
        {
            List<SolidPropertyType> lhsSolidMember;
            lhsSolidMember = (this.isSetSolidMember()?this.getSolidMember():null);
            List<SolidPropertyType> rhsSolidMember;
            rhsSolidMember = (that.isSetSolidMember()?that.getSolidMember():null);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "solidMember", lhsSolidMember), LocatorUtils.property(thatLocator, "solidMember", rhsSolidMember), lhsSolidMember, rhsSolidMember, this.isSetSolidMember(), that.isSetSolidMember())) {
                return false;
            }
        }
        {
            AggregationType lhsAggregationType;
            lhsAggregationType = this.getAggregationType();
            AggregationType rhsAggregationType;
            rhsAggregationType = that.getAggregationType();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "aggregationType", lhsAggregationType), LocatorUtils.property(thatLocator, "aggregationType", rhsAggregationType), lhsAggregationType, rhsAggregationType, this.isSetAggregationType(), that.isSetAggregationType())) {
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
        int currentHashCode = super.hashCode(locator, strategy);
        {
            List<SolidPropertyType> theSolidMember;
            theSolidMember = (this.isSetSolidMember()?this.getSolidMember():null);
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "solidMember", theSolidMember), currentHashCode, theSolidMember, this.isSetSolidMember());
        }
        {
            AggregationType theAggregationType;
            theAggregationType = this.getAggregationType();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "aggregationType", theAggregationType), currentHashCode, theAggregationType, this.isSetAggregationType());
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
        super.copyTo(locator, draftCopy, strategy);
        if (draftCopy instanceof CompositeSolidType) {
            final CompositeSolidType copy = ((CompositeSolidType) draftCopy);
            {
                java.lang.Boolean solidMemberShouldBeCopiedAndSet = strategy.shouldBeCopiedAndSet(locator, this.isSetSolidMember());
                if (solidMemberShouldBeCopiedAndSet == java.lang.Boolean.TRUE) {
                    List<SolidPropertyType> sourceSolidMember;
                    sourceSolidMember = (this.isSetSolidMember()?this.getSolidMember():null);
                    @SuppressWarnings("unchecked")
                    List<SolidPropertyType> copySolidMember = ((List<SolidPropertyType> ) strategy.copy(LocatorUtils.property(locator, "solidMember", sourceSolidMember), sourceSolidMember, this.isSetSolidMember()));
                    copy.setSolidMember(copySolidMember);
                } else {
                    if (solidMemberShouldBeCopiedAndSet == java.lang.Boolean.FALSE) {
                        copy.unsetSolidMember();
                    }
                }
            }
            {
                java.lang.Boolean aggregationTypeShouldBeCopiedAndSet = strategy.shouldBeCopiedAndSet(locator, this.isSetAggregationType());
                if (aggregationTypeShouldBeCopiedAndSet == java.lang.Boolean.TRUE) {
                    AggregationType sourceAggregationType;
                    sourceAggregationType = this.getAggregationType();
                    AggregationType copyAggregationType = ((AggregationType) strategy.copy(LocatorUtils.property(locator, "aggregationType", sourceAggregationType), sourceAggregationType, this.isSetAggregationType()));
                    copy.setAggregationType(copyAggregationType);
                } else {
                    if (aggregationTypeShouldBeCopiedAndSet == java.lang.Boolean.FALSE) {
                        copy.aggregationType = null;
                    }
                }
            }
        }
        return draftCopy;
    }

    @Override
    public Object createNewInstance() {
        return new CompositeSolidType();
    }

    @Override
    public void mergeFrom(Object left, Object right) {
        final MergeStrategy strategy = JAXBMergeStrategy.getInstance();
        mergeFrom(null, null, left, right, strategy);
    }

    @Override
    public void mergeFrom(ObjectLocator leftLocator, ObjectLocator rightLocator, Object left, Object right, MergeStrategy strategy) {
        super.mergeFrom(leftLocator, rightLocator, left, right, strategy);
        if (right instanceof CompositeSolidType) {
            final CompositeSolidType target = this;
            final CompositeSolidType leftObject = ((CompositeSolidType) left);
            final CompositeSolidType rightObject = ((CompositeSolidType) right);
            {
                java.lang.Boolean solidMemberShouldBeMergedAndSet = strategy.shouldBeMergedAndSet(leftLocator, rightLocator, leftObject.isSetSolidMember(), rightObject.isSetSolidMember());
                if (solidMemberShouldBeMergedAndSet == java.lang.Boolean.TRUE) {
                    List<SolidPropertyType> lhsSolidMember;
                    lhsSolidMember = (leftObject.isSetSolidMember()?leftObject.getSolidMember():null);
                    List<SolidPropertyType> rhsSolidMember;
                    rhsSolidMember = (rightObject.isSetSolidMember()?rightObject.getSolidMember():null);
                    List<SolidPropertyType> mergedSolidMember = ((List<SolidPropertyType> ) strategy.merge(LocatorUtils.property(leftLocator, "solidMember", lhsSolidMember), LocatorUtils.property(rightLocator, "solidMember", rhsSolidMember), lhsSolidMember, rhsSolidMember, leftObject.isSetSolidMember(), rightObject.isSetSolidMember()));
                    target.setSolidMember(mergedSolidMember);
                } else {
                    if (solidMemberShouldBeMergedAndSet == java.lang.Boolean.FALSE) {
                        target.unsetSolidMember();
                    }
                }
            }
            {
                java.lang.Boolean aggregationTypeShouldBeMergedAndSet = strategy.shouldBeMergedAndSet(leftLocator, rightLocator, leftObject.isSetAggregationType(), rightObject.isSetAggregationType());
                if (aggregationTypeShouldBeMergedAndSet == java.lang.Boolean.TRUE) {
                    AggregationType lhsAggregationType;
                    lhsAggregationType = leftObject.getAggregationType();
                    AggregationType rhsAggregationType;
                    rhsAggregationType = rightObject.getAggregationType();
                    AggregationType mergedAggregationType = ((AggregationType) strategy.merge(LocatorUtils.property(leftLocator, "aggregationType", lhsAggregationType), LocatorUtils.property(rightLocator, "aggregationType", rhsAggregationType), lhsAggregationType, rhsAggregationType, leftObject.isSetAggregationType(), rightObject.isSetAggregationType()));
                    target.setAggregationType(mergedAggregationType);
                } else {
                    if (aggregationTypeShouldBeMergedAndSet == java.lang.Boolean.FALSE) {
                        target.aggregationType = null;
                    }
                }
            }
        }
    }

}
