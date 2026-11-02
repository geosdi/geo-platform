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
import jakarta.xml.bind.annotation.XmlSeeAlso;
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
 * <p>Classe Java per AbstractFeatureCollectionType complex type.</p>
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.</p>
 * 
 * <pre>{@code
 * <complexType name="AbstractFeatureCollectionType">
 *   <complexContent>
 *     <extension base="{http://www.opengis.net/gml}AbstractFeatureType">
 *       <sequence>
 *         <element ref="{http://www.opengis.net/gml}featureMember" maxOccurs="unbounded" minOccurs="0"/>
 *         <element ref="{http://www.opengis.net/gml}featureMembers" minOccurs="0"/>
 *       </sequence>
 *     </extension>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AbstractFeatureCollectionType", propOrder = {
    "featureMember",
    "featureMembers"
})
@XmlSeeAlso({
    FeatureCollectionType.class
})
public abstract class AbstractFeatureCollectionType
    extends AbstractFeatureType
    implements Cloneable, CopyTo, Equals, HashCode, MergeFrom, ToString
{

    protected List<FeaturePropertyType> featureMember;
    protected FeatureArrayPropertyType featureMembers;

    /**
     * Gets the value of the featureMember property.
     * 
     * <p>This accessor method returns a reference to the live list,
     * not a snapshot. Therefore, any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a {@code set} method for the featureMember property.</p>
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * </p>
     * <pre>
     * getFeatureMember().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link FeaturePropertyType }
     * </p>
     * 
     * 
     * @return
     *     The value of the featureMember property.
     */
    public List<FeaturePropertyType> getFeatureMember() {
        if (featureMember == null) {
            featureMember = new ArrayList<>();
        }
        return this.featureMember;
    }

    public boolean isSetFeatureMember() {
        return ((this.featureMember!= null)&&(!this.featureMember.isEmpty()));
    }

    public void unsetFeatureMember() {
        this.featureMember = null;
    }

    /**
     * Recupera il valore della proprietà featureMembers.
     * 
     * @return
     *     possible object is
     *     {@link FeatureArrayPropertyType }
     *     
     */
    public FeatureArrayPropertyType getFeatureMembers() {
        return featureMembers;
    }

    /**
     * Imposta il valore della proprietà featureMembers.
     * 
     * @param value
     *     allowed object is
     *     {@link FeatureArrayPropertyType }
     *     
     */
    public void setFeatureMembers(FeatureArrayPropertyType value) {
        this.featureMembers = value;
    }

    public boolean isSetFeatureMembers() {
        return (this.featureMembers!= null);
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
            List<FeaturePropertyType> theFeatureMember;
            theFeatureMember = (this.isSetFeatureMember()?this.getFeatureMember():null);
            strategy.appendField(locator, this, "featureMember", buffer, theFeatureMember, this.isSetFeatureMember());
        }
        {
            FeatureArrayPropertyType theFeatureMembers;
            theFeatureMembers = this.getFeatureMembers();
            strategy.appendField(locator, this, "featureMembers", buffer, theFeatureMembers, this.isSetFeatureMembers());
        }
        return buffer;
    }

    public void setFeatureMember(List<FeaturePropertyType> value) {
        this.featureMember = null;
        if (value!= null) {
            List<FeaturePropertyType> draftl = this.getFeatureMember();
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
        final AbstractFeatureCollectionType that = ((AbstractFeatureCollectionType) object);
        {
            List<FeaturePropertyType> lhsFeatureMember;
            lhsFeatureMember = (this.isSetFeatureMember()?this.getFeatureMember():null);
            List<FeaturePropertyType> rhsFeatureMember;
            rhsFeatureMember = (that.isSetFeatureMember()?that.getFeatureMember():null);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "featureMember", lhsFeatureMember), LocatorUtils.property(thatLocator, "featureMember", rhsFeatureMember), lhsFeatureMember, rhsFeatureMember, this.isSetFeatureMember(), that.isSetFeatureMember())) {
                return false;
            }
        }
        {
            FeatureArrayPropertyType lhsFeatureMembers;
            lhsFeatureMembers = this.getFeatureMembers();
            FeatureArrayPropertyType rhsFeatureMembers;
            rhsFeatureMembers = that.getFeatureMembers();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "featureMembers", lhsFeatureMembers), LocatorUtils.property(thatLocator, "featureMembers", rhsFeatureMembers), lhsFeatureMembers, rhsFeatureMembers, this.isSetFeatureMembers(), that.isSetFeatureMembers())) {
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
            List<FeaturePropertyType> theFeatureMember;
            theFeatureMember = (this.isSetFeatureMember()?this.getFeatureMember():null);
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "featureMember", theFeatureMember), currentHashCode, theFeatureMember, this.isSetFeatureMember());
        }
        {
            FeatureArrayPropertyType theFeatureMembers;
            theFeatureMembers = this.getFeatureMembers();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "featureMembers", theFeatureMembers), currentHashCode, theFeatureMembers, this.isSetFeatureMembers());
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
        if (null == target) {
            throw new IllegalArgumentException("Target argument must not be null for abstract copyable classes.");
        }
        super.copyTo(locator, target, strategy);
        if (target instanceof AbstractFeatureCollectionType) {
            final AbstractFeatureCollectionType copy = ((AbstractFeatureCollectionType) target);
            {
                java.lang.Boolean featureMemberShouldBeCopiedAndSet = strategy.shouldBeCopiedAndSet(locator, this.isSetFeatureMember());
                if (featureMemberShouldBeCopiedAndSet == java.lang.Boolean.TRUE) {
                    List<FeaturePropertyType> sourceFeatureMember;
                    sourceFeatureMember = (this.isSetFeatureMember()?this.getFeatureMember():null);
                    @SuppressWarnings("unchecked")
                    List<FeaturePropertyType> copyFeatureMember = ((List<FeaturePropertyType> ) strategy.copy(LocatorUtils.property(locator, "featureMember", sourceFeatureMember), sourceFeatureMember, this.isSetFeatureMember()));
                    copy.setFeatureMember(copyFeatureMember);
                } else {
                    if (featureMemberShouldBeCopiedAndSet == java.lang.Boolean.FALSE) {
                        copy.unsetFeatureMember();
                    }
                }
            }
            {
                java.lang.Boolean featureMembersShouldBeCopiedAndSet = strategy.shouldBeCopiedAndSet(locator, this.isSetFeatureMembers());
                if (featureMembersShouldBeCopiedAndSet == java.lang.Boolean.TRUE) {
                    FeatureArrayPropertyType sourceFeatureMembers;
                    sourceFeatureMembers = this.getFeatureMembers();
                    FeatureArrayPropertyType copyFeatureMembers = ((FeatureArrayPropertyType) strategy.copy(LocatorUtils.property(locator, "featureMembers", sourceFeatureMembers), sourceFeatureMembers, this.isSetFeatureMembers()));
                    copy.setFeatureMembers(copyFeatureMembers);
                } else {
                    if (featureMembersShouldBeCopiedAndSet == java.lang.Boolean.FALSE) {
                        copy.featureMembers = null;
                    }
                }
            }
        }
        return target;
    }

    @Override
    public void mergeFrom(Object left, Object right) {
        final MergeStrategy strategy = JAXBMergeStrategy.getInstance();
        mergeFrom(null, null, left, right, strategy);
    }

    @Override
    public void mergeFrom(ObjectLocator leftLocator, ObjectLocator rightLocator, Object left, Object right, MergeStrategy strategy) {
        super.mergeFrom(leftLocator, rightLocator, left, right, strategy);
        if (right instanceof AbstractFeatureCollectionType) {
            final AbstractFeatureCollectionType target = this;
            final AbstractFeatureCollectionType leftObject = ((AbstractFeatureCollectionType) left);
            final AbstractFeatureCollectionType rightObject = ((AbstractFeatureCollectionType) right);
            {
                java.lang.Boolean featureMemberShouldBeMergedAndSet = strategy.shouldBeMergedAndSet(leftLocator, rightLocator, leftObject.isSetFeatureMember(), rightObject.isSetFeatureMember());
                if (featureMemberShouldBeMergedAndSet == java.lang.Boolean.TRUE) {
                    List<FeaturePropertyType> lhsFeatureMember;
                    lhsFeatureMember = (leftObject.isSetFeatureMember()?leftObject.getFeatureMember():null);
                    List<FeaturePropertyType> rhsFeatureMember;
                    rhsFeatureMember = (rightObject.isSetFeatureMember()?rightObject.getFeatureMember():null);
                    List<FeaturePropertyType> mergedFeatureMember = ((List<FeaturePropertyType> ) strategy.merge(LocatorUtils.property(leftLocator, "featureMember", lhsFeatureMember), LocatorUtils.property(rightLocator, "featureMember", rhsFeatureMember), lhsFeatureMember, rhsFeatureMember, leftObject.isSetFeatureMember(), rightObject.isSetFeatureMember()));
                    target.setFeatureMember(mergedFeatureMember);
                } else {
                    if (featureMemberShouldBeMergedAndSet == java.lang.Boolean.FALSE) {
                        target.unsetFeatureMember();
                    }
                }
            }
            {
                java.lang.Boolean featureMembersShouldBeMergedAndSet = strategy.shouldBeMergedAndSet(leftLocator, rightLocator, leftObject.isSetFeatureMembers(), rightObject.isSetFeatureMembers());
                if (featureMembersShouldBeMergedAndSet == java.lang.Boolean.TRUE) {
                    FeatureArrayPropertyType lhsFeatureMembers;
                    lhsFeatureMembers = leftObject.getFeatureMembers();
                    FeatureArrayPropertyType rhsFeatureMembers;
                    rhsFeatureMembers = rightObject.getFeatureMembers();
                    FeatureArrayPropertyType mergedFeatureMembers = ((FeatureArrayPropertyType) strategy.merge(LocatorUtils.property(leftLocator, "featureMembers", lhsFeatureMembers), LocatorUtils.property(rightLocator, "featureMembers", rhsFeatureMembers), lhsFeatureMembers, rhsFeatureMembers, leftObject.isSetFeatureMembers(), rightObject.isSetFeatureMembers()));
                    target.setFeatureMembers(mergedFeatureMembers);
                } else {
                    if (featureMembersShouldBeMergedAndSet == java.lang.Boolean.FALSE) {
                        target.featureMembers = null;
                    }
                }
            }
        }
    }

}
