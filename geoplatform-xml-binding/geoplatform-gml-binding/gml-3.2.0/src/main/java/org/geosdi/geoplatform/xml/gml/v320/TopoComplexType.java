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
 * <p>Classe Java per TopoComplexType complex type.</p>
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.</p>
 * 
 * <pre>{@code
 * <complexType name="TopoComplexType">
 *   <complexContent>
 *     <extension base="{http://www.opengis.net/gml}AbstractTopologyType">
 *       <sequence>
 *         <element ref="{http://www.opengis.net/gml}maximalComplex"/>
 *         <element ref="{http://www.opengis.net/gml}superComplex" maxOccurs="unbounded" minOccurs="0"/>
 *         <element ref="{http://www.opengis.net/gml}subComplex" maxOccurs="unbounded" minOccurs="0"/>
 *         <element ref="{http://www.opengis.net/gml}topoPrimitiveMember" maxOccurs="unbounded" minOccurs="0"/>
 *         <element ref="{http://www.opengis.net/gml}topoPrimitiveMembers" minOccurs="0"/>
 *       </sequence>
 *       <attGroup ref="{http://www.opengis.net/gml}AggregationAttributeGroup"/>
 *       <attribute name="isMaximal" type="{http://www.w3.org/2001/XMLSchema}boolean" default="false" />
 *     </extension>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TopoComplexType", propOrder = {
    "maximalComplex",
    "superComplex",
    "subComplex",
    "topoPrimitiveMember",
    "topoPrimitiveMembers"
})
public class TopoComplexType
    extends AbstractTopologyType
    implements Cloneable, CopyTo, Equals, HashCode, MergeFrom, ToString
{

    /**
     * The property elements gml:subComplex, gml:superComplex and gml:maximalComplex provide an encoding for relationships between topology complexes as described for gml:TopoComplex above.
     * 
     */
    @XmlElement(required = true)
    protected TopoComplexMemberType maximalComplex;
    /**
     * The property elements gml:subComplex, gml:superComplex and gml:maximalComplex provide an encoding for relationships between topology complexes as described for gml:TopoComplex above.
     * 
     */
    protected List<TopoComplexMemberType> superComplex;
    /**
     * The property elements gml:subComplex, gml:superComplex and gml:maximalComplex provide an encoding for relationships between topology complexes as described for gml:TopoComplex above.
     * 
     */
    protected List<TopoComplexMemberType> subComplex;
    /**
     * The gml:topoPrimitiveMember property element encodes for the relationship between a topology complex and a single topology primitive.
     * 
     */
    protected List<TopoPrimitiveMemberType> topoPrimitiveMember;
    /**
     * The gml:topoPrimitiveMembers property element encodes the relationship between a topology complex and an arbitrary number of topology primitives.
     * 
     */
    protected TopoPrimitiveArrayAssociationType topoPrimitiveMembers;
    @XmlAttribute(name = "isMaximal")
    protected java.lang.Boolean isMaximal;
    @XmlAttribute(name = "aggregationType")
    protected AggregationType aggregationType;

    /**
     * The property elements gml:subComplex, gml:superComplex and gml:maximalComplex provide an encoding for relationships between topology complexes as described for gml:TopoComplex above.
     * 
     * @return
     *     possible object is
     *     {@link TopoComplexMemberType }
     *     
     */
    public TopoComplexMemberType getMaximalComplex() {
        return maximalComplex;
    }

    /**
     * Imposta il valore della proprietà maximalComplex.
     * 
     * @param value
     *     allowed object is
     *     {@link TopoComplexMemberType }
     *     
     * @see #getMaximalComplex()
     */
    public void setMaximalComplex(TopoComplexMemberType value) {
        this.maximalComplex = value;
    }

    public boolean isSetMaximalComplex() {
        return (this.maximalComplex!= null);
    }

    /**
     * The property elements gml:subComplex, gml:superComplex and gml:maximalComplex provide an encoding for relationships between topology complexes as described for gml:TopoComplex above.
     * 
     * Gets the value of the superComplex property.
     * 
     * <p>This accessor method returns a reference to the live list,
     * not a snapshot. Therefore, any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a {@code set} method for the superComplex property.</p>
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * </p>
     * <pre>
     * getSuperComplex().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link TopoComplexMemberType }
     * </p>
     * 
     * 
     * @return
     *     The value of the superComplex property.
     */
    public List<TopoComplexMemberType> getSuperComplex() {
        if (superComplex == null) {
            superComplex = new ArrayList<>();
        }
        return this.superComplex;
    }

    public boolean isSetSuperComplex() {
        return ((this.superComplex!= null)&&(!this.superComplex.isEmpty()));
    }

    public void unsetSuperComplex() {
        this.superComplex = null;
    }

    /**
     * The property elements gml:subComplex, gml:superComplex and gml:maximalComplex provide an encoding for relationships between topology complexes as described for gml:TopoComplex above.
     * 
     * Gets the value of the subComplex property.
     * 
     * <p>This accessor method returns a reference to the live list,
     * not a snapshot. Therefore, any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a {@code set} method for the subComplex property.</p>
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * </p>
     * <pre>
     * getSubComplex().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link TopoComplexMemberType }
     * </p>
     * 
     * 
     * @return
     *     The value of the subComplex property.
     */
    public List<TopoComplexMemberType> getSubComplex() {
        if (subComplex == null) {
            subComplex = new ArrayList<>();
        }
        return this.subComplex;
    }

    public boolean isSetSubComplex() {
        return ((this.subComplex!= null)&&(!this.subComplex.isEmpty()));
    }

    public void unsetSubComplex() {
        this.subComplex = null;
    }

    /**
     * The gml:topoPrimitiveMember property element encodes for the relationship between a topology complex and a single topology primitive.
     * 
     * Gets the value of the topoPrimitiveMember property.
     * 
     * <p>This accessor method returns a reference to the live list,
     * not a snapshot. Therefore, any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a {@code set} method for the topoPrimitiveMember property.</p>
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * </p>
     * <pre>
     * getTopoPrimitiveMember().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link TopoPrimitiveMemberType }
     * </p>
     * 
     * 
     * @return
     *     The value of the topoPrimitiveMember property.
     */
    public List<TopoPrimitiveMemberType> getTopoPrimitiveMember() {
        if (topoPrimitiveMember == null) {
            topoPrimitiveMember = new ArrayList<>();
        }
        return this.topoPrimitiveMember;
    }

    public boolean isSetTopoPrimitiveMember() {
        return ((this.topoPrimitiveMember!= null)&&(!this.topoPrimitiveMember.isEmpty()));
    }

    public void unsetTopoPrimitiveMember() {
        this.topoPrimitiveMember = null;
    }

    /**
     * The gml:topoPrimitiveMembers property element encodes the relationship between a topology complex and an arbitrary number of topology primitives.
     * 
     * @return
     *     possible object is
     *     {@link TopoPrimitiveArrayAssociationType }
     *     
     */
    public TopoPrimitiveArrayAssociationType getTopoPrimitiveMembers() {
        return topoPrimitiveMembers;
    }

    /**
     * Imposta il valore della proprietà topoPrimitiveMembers.
     * 
     * @param value
     *     allowed object is
     *     {@link TopoPrimitiveArrayAssociationType }
     *     
     * @see #getTopoPrimitiveMembers()
     */
    public void setTopoPrimitiveMembers(TopoPrimitiveArrayAssociationType value) {
        this.topoPrimitiveMembers = value;
    }

    public boolean isSetTopoPrimitiveMembers() {
        return (this.topoPrimitiveMembers!= null);
    }

    /**
     * Recupera il valore della proprietà isMaximal.
     * 
     * @return
     *     possible object is
     *     {@link java.lang.Boolean }
     *     
     */
    public boolean isIsMaximal() {
        if (isMaximal == null) {
            return false;
        } else {
            return isMaximal;
        }
    }

    /**
     * Imposta il valore della proprietà isMaximal.
     * 
     * @param value
     *     allowed object is
     *     {@link java.lang.Boolean }
     *     
     */
    public void setIsMaximal(boolean value) {
        this.isMaximal = value;
    }

    public boolean isSetIsMaximal() {
        return (this.isMaximal!= null);
    }

    public void unsetIsMaximal() {
        this.isMaximal = null;
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
            TopoComplexMemberType theMaximalComplex;
            theMaximalComplex = this.getMaximalComplex();
            strategy.appendField(locator, this, "maximalComplex", buffer, theMaximalComplex, this.isSetMaximalComplex());
        }
        {
            List<TopoComplexMemberType> theSuperComplex;
            theSuperComplex = (this.isSetSuperComplex()?this.getSuperComplex():null);
            strategy.appendField(locator, this, "superComplex", buffer, theSuperComplex, this.isSetSuperComplex());
        }
        {
            List<TopoComplexMemberType> theSubComplex;
            theSubComplex = (this.isSetSubComplex()?this.getSubComplex():null);
            strategy.appendField(locator, this, "subComplex", buffer, theSubComplex, this.isSetSubComplex());
        }
        {
            List<TopoPrimitiveMemberType> theTopoPrimitiveMember;
            theTopoPrimitiveMember = (this.isSetTopoPrimitiveMember()?this.getTopoPrimitiveMember():null);
            strategy.appendField(locator, this, "topoPrimitiveMember", buffer, theTopoPrimitiveMember, this.isSetTopoPrimitiveMember());
        }
        {
            TopoPrimitiveArrayAssociationType theTopoPrimitiveMembers;
            theTopoPrimitiveMembers = this.getTopoPrimitiveMembers();
            strategy.appendField(locator, this, "topoPrimitiveMembers", buffer, theTopoPrimitiveMembers, this.isSetTopoPrimitiveMembers());
        }
        {
            boolean theIsMaximal;
            theIsMaximal = (this.isSetIsMaximal()?this.isIsMaximal():false);
            strategy.appendField(locator, this, "isMaximal", buffer, theIsMaximal, this.isSetIsMaximal());
        }
        {
            AggregationType theAggregationType;
            theAggregationType = this.getAggregationType();
            strategy.appendField(locator, this, "aggregationType", buffer, theAggregationType, this.isSetAggregationType());
        }
        return buffer;
    }

    public void setSuperComplex(List<TopoComplexMemberType> value) {
        this.superComplex = null;
        if (value!= null) {
            List<TopoComplexMemberType> draftl = this.getSuperComplex();
            draftl.addAll(value);
        }
    }

    public void setSubComplex(List<TopoComplexMemberType> value) {
        this.subComplex = null;
        if (value!= null) {
            List<TopoComplexMemberType> draftl = this.getSubComplex();
            draftl.addAll(value);
        }
    }

    public void setTopoPrimitiveMember(List<TopoPrimitiveMemberType> value) {
        this.topoPrimitiveMember = null;
        if (value!= null) {
            List<TopoPrimitiveMemberType> draftl = this.getTopoPrimitiveMember();
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
        final TopoComplexType that = ((TopoComplexType) object);
        {
            TopoComplexMemberType lhsMaximalComplex;
            lhsMaximalComplex = this.getMaximalComplex();
            TopoComplexMemberType rhsMaximalComplex;
            rhsMaximalComplex = that.getMaximalComplex();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "maximalComplex", lhsMaximalComplex), LocatorUtils.property(thatLocator, "maximalComplex", rhsMaximalComplex), lhsMaximalComplex, rhsMaximalComplex, this.isSetMaximalComplex(), that.isSetMaximalComplex())) {
                return false;
            }
        }
        {
            List<TopoComplexMemberType> lhsSuperComplex;
            lhsSuperComplex = (this.isSetSuperComplex()?this.getSuperComplex():null);
            List<TopoComplexMemberType> rhsSuperComplex;
            rhsSuperComplex = (that.isSetSuperComplex()?that.getSuperComplex():null);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "superComplex", lhsSuperComplex), LocatorUtils.property(thatLocator, "superComplex", rhsSuperComplex), lhsSuperComplex, rhsSuperComplex, this.isSetSuperComplex(), that.isSetSuperComplex())) {
                return false;
            }
        }
        {
            List<TopoComplexMemberType> lhsSubComplex;
            lhsSubComplex = (this.isSetSubComplex()?this.getSubComplex():null);
            List<TopoComplexMemberType> rhsSubComplex;
            rhsSubComplex = (that.isSetSubComplex()?that.getSubComplex():null);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "subComplex", lhsSubComplex), LocatorUtils.property(thatLocator, "subComplex", rhsSubComplex), lhsSubComplex, rhsSubComplex, this.isSetSubComplex(), that.isSetSubComplex())) {
                return false;
            }
        }
        {
            List<TopoPrimitiveMemberType> lhsTopoPrimitiveMember;
            lhsTopoPrimitiveMember = (this.isSetTopoPrimitiveMember()?this.getTopoPrimitiveMember():null);
            List<TopoPrimitiveMemberType> rhsTopoPrimitiveMember;
            rhsTopoPrimitiveMember = (that.isSetTopoPrimitiveMember()?that.getTopoPrimitiveMember():null);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "topoPrimitiveMember", lhsTopoPrimitiveMember), LocatorUtils.property(thatLocator, "topoPrimitiveMember", rhsTopoPrimitiveMember), lhsTopoPrimitiveMember, rhsTopoPrimitiveMember, this.isSetTopoPrimitiveMember(), that.isSetTopoPrimitiveMember())) {
                return false;
            }
        }
        {
            TopoPrimitiveArrayAssociationType lhsTopoPrimitiveMembers;
            lhsTopoPrimitiveMembers = this.getTopoPrimitiveMembers();
            TopoPrimitiveArrayAssociationType rhsTopoPrimitiveMembers;
            rhsTopoPrimitiveMembers = that.getTopoPrimitiveMembers();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "topoPrimitiveMembers", lhsTopoPrimitiveMembers), LocatorUtils.property(thatLocator, "topoPrimitiveMembers", rhsTopoPrimitiveMembers), lhsTopoPrimitiveMembers, rhsTopoPrimitiveMembers, this.isSetTopoPrimitiveMembers(), that.isSetTopoPrimitiveMembers())) {
                return false;
            }
        }
        {
            boolean lhsIsMaximal;
            lhsIsMaximal = (this.isSetIsMaximal()?this.isIsMaximal():false);
            boolean rhsIsMaximal;
            rhsIsMaximal = (that.isSetIsMaximal()?that.isIsMaximal():false);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "isMaximal", lhsIsMaximal), LocatorUtils.property(thatLocator, "isMaximal", rhsIsMaximal), lhsIsMaximal, rhsIsMaximal, this.isSetIsMaximal(), that.isSetIsMaximal())) {
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
            TopoComplexMemberType theMaximalComplex;
            theMaximalComplex = this.getMaximalComplex();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "maximalComplex", theMaximalComplex), currentHashCode, theMaximalComplex, this.isSetMaximalComplex());
        }
        {
            List<TopoComplexMemberType> theSuperComplex;
            theSuperComplex = (this.isSetSuperComplex()?this.getSuperComplex():null);
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "superComplex", theSuperComplex), currentHashCode, theSuperComplex, this.isSetSuperComplex());
        }
        {
            List<TopoComplexMemberType> theSubComplex;
            theSubComplex = (this.isSetSubComplex()?this.getSubComplex():null);
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "subComplex", theSubComplex), currentHashCode, theSubComplex, this.isSetSubComplex());
        }
        {
            List<TopoPrimitiveMemberType> theTopoPrimitiveMember;
            theTopoPrimitiveMember = (this.isSetTopoPrimitiveMember()?this.getTopoPrimitiveMember():null);
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "topoPrimitiveMember", theTopoPrimitiveMember), currentHashCode, theTopoPrimitiveMember, this.isSetTopoPrimitiveMember());
        }
        {
            TopoPrimitiveArrayAssociationType theTopoPrimitiveMembers;
            theTopoPrimitiveMembers = this.getTopoPrimitiveMembers();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "topoPrimitiveMembers", theTopoPrimitiveMembers), currentHashCode, theTopoPrimitiveMembers, this.isSetTopoPrimitiveMembers());
        }
        {
            boolean theIsMaximal;
            theIsMaximal = (this.isSetIsMaximal()?this.isIsMaximal():false);
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "isMaximal", theIsMaximal), currentHashCode, theIsMaximal, this.isSetIsMaximal());
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
        if (draftCopy instanceof TopoComplexType) {
            final TopoComplexType copy = ((TopoComplexType) draftCopy);
            {
                java.lang.Boolean maximalComplexShouldBeCopiedAndSet = strategy.shouldBeCopiedAndSet(locator, this.isSetMaximalComplex());
                if (maximalComplexShouldBeCopiedAndSet == java.lang.Boolean.TRUE) {
                    TopoComplexMemberType sourceMaximalComplex;
                    sourceMaximalComplex = this.getMaximalComplex();
                    TopoComplexMemberType copyMaximalComplex = ((TopoComplexMemberType) strategy.copy(LocatorUtils.property(locator, "maximalComplex", sourceMaximalComplex), sourceMaximalComplex, this.isSetMaximalComplex()));
                    copy.setMaximalComplex(copyMaximalComplex);
                } else {
                    if (maximalComplexShouldBeCopiedAndSet == java.lang.Boolean.FALSE) {
                        copy.maximalComplex = null;
                    }
                }
            }
            {
                java.lang.Boolean superComplexShouldBeCopiedAndSet = strategy.shouldBeCopiedAndSet(locator, this.isSetSuperComplex());
                if (superComplexShouldBeCopiedAndSet == java.lang.Boolean.TRUE) {
                    List<TopoComplexMemberType> sourceSuperComplex;
                    sourceSuperComplex = (this.isSetSuperComplex()?this.getSuperComplex():null);
                    @SuppressWarnings("unchecked")
                    List<TopoComplexMemberType> copySuperComplex = ((List<TopoComplexMemberType> ) strategy.copy(LocatorUtils.property(locator, "superComplex", sourceSuperComplex), sourceSuperComplex, this.isSetSuperComplex()));
                    copy.setSuperComplex(copySuperComplex);
                } else {
                    if (superComplexShouldBeCopiedAndSet == java.lang.Boolean.FALSE) {
                        copy.unsetSuperComplex();
                    }
                }
            }
            {
                java.lang.Boolean subComplexShouldBeCopiedAndSet = strategy.shouldBeCopiedAndSet(locator, this.isSetSubComplex());
                if (subComplexShouldBeCopiedAndSet == java.lang.Boolean.TRUE) {
                    List<TopoComplexMemberType> sourceSubComplex;
                    sourceSubComplex = (this.isSetSubComplex()?this.getSubComplex():null);
                    @SuppressWarnings("unchecked")
                    List<TopoComplexMemberType> copySubComplex = ((List<TopoComplexMemberType> ) strategy.copy(LocatorUtils.property(locator, "subComplex", sourceSubComplex), sourceSubComplex, this.isSetSubComplex()));
                    copy.setSubComplex(copySubComplex);
                } else {
                    if (subComplexShouldBeCopiedAndSet == java.lang.Boolean.FALSE) {
                        copy.unsetSubComplex();
                    }
                }
            }
            {
                java.lang.Boolean topoPrimitiveMemberShouldBeCopiedAndSet = strategy.shouldBeCopiedAndSet(locator, this.isSetTopoPrimitiveMember());
                if (topoPrimitiveMemberShouldBeCopiedAndSet == java.lang.Boolean.TRUE) {
                    List<TopoPrimitiveMemberType> sourceTopoPrimitiveMember;
                    sourceTopoPrimitiveMember = (this.isSetTopoPrimitiveMember()?this.getTopoPrimitiveMember():null);
                    @SuppressWarnings("unchecked")
                    List<TopoPrimitiveMemberType> copyTopoPrimitiveMember = ((List<TopoPrimitiveMemberType> ) strategy.copy(LocatorUtils.property(locator, "topoPrimitiveMember", sourceTopoPrimitiveMember), sourceTopoPrimitiveMember, this.isSetTopoPrimitiveMember()));
                    copy.setTopoPrimitiveMember(copyTopoPrimitiveMember);
                } else {
                    if (topoPrimitiveMemberShouldBeCopiedAndSet == java.lang.Boolean.FALSE) {
                        copy.unsetTopoPrimitiveMember();
                    }
                }
            }
            {
                java.lang.Boolean topoPrimitiveMembersShouldBeCopiedAndSet = strategy.shouldBeCopiedAndSet(locator, this.isSetTopoPrimitiveMembers());
                if (topoPrimitiveMembersShouldBeCopiedAndSet == java.lang.Boolean.TRUE) {
                    TopoPrimitiveArrayAssociationType sourceTopoPrimitiveMembers;
                    sourceTopoPrimitiveMembers = this.getTopoPrimitiveMembers();
                    TopoPrimitiveArrayAssociationType copyTopoPrimitiveMembers = ((TopoPrimitiveArrayAssociationType) strategy.copy(LocatorUtils.property(locator, "topoPrimitiveMembers", sourceTopoPrimitiveMembers), sourceTopoPrimitiveMembers, this.isSetTopoPrimitiveMembers()));
                    copy.setTopoPrimitiveMembers(copyTopoPrimitiveMembers);
                } else {
                    if (topoPrimitiveMembersShouldBeCopiedAndSet == java.lang.Boolean.FALSE) {
                        copy.topoPrimitiveMembers = null;
                    }
                }
            }
            {
                java.lang.Boolean isMaximalShouldBeCopiedAndSet = strategy.shouldBeCopiedAndSet(locator, this.isSetIsMaximal());
                if (isMaximalShouldBeCopiedAndSet == java.lang.Boolean.TRUE) {
                    boolean sourceIsMaximal;
                    sourceIsMaximal = (this.isSetIsMaximal()?this.isIsMaximal():false);
                    boolean copyIsMaximal = strategy.copy(LocatorUtils.property(locator, "isMaximal", sourceIsMaximal), sourceIsMaximal, this.isSetIsMaximal());
                    copy.setIsMaximal(copyIsMaximal);
                } else {
                    if (isMaximalShouldBeCopiedAndSet == java.lang.Boolean.FALSE) {
                        copy.unsetIsMaximal();
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
        return new TopoComplexType();
    }

    @Override
    public void mergeFrom(Object left, Object right) {
        final MergeStrategy strategy = JAXBMergeStrategy.getInstance();
        mergeFrom(null, null, left, right, strategy);
    }

    @Override
    public void mergeFrom(ObjectLocator leftLocator, ObjectLocator rightLocator, Object left, Object right, MergeStrategy strategy) {
        super.mergeFrom(leftLocator, rightLocator, left, right, strategy);
        if (right instanceof TopoComplexType) {
            final TopoComplexType target = this;
            final TopoComplexType leftObject = ((TopoComplexType) left);
            final TopoComplexType rightObject = ((TopoComplexType) right);
            {
                java.lang.Boolean maximalComplexShouldBeMergedAndSet = strategy.shouldBeMergedAndSet(leftLocator, rightLocator, leftObject.isSetMaximalComplex(), rightObject.isSetMaximalComplex());
                if (maximalComplexShouldBeMergedAndSet == java.lang.Boolean.TRUE) {
                    TopoComplexMemberType lhsMaximalComplex;
                    lhsMaximalComplex = leftObject.getMaximalComplex();
                    TopoComplexMemberType rhsMaximalComplex;
                    rhsMaximalComplex = rightObject.getMaximalComplex();
                    TopoComplexMemberType mergedMaximalComplex = ((TopoComplexMemberType) strategy.merge(LocatorUtils.property(leftLocator, "maximalComplex", lhsMaximalComplex), LocatorUtils.property(rightLocator, "maximalComplex", rhsMaximalComplex), lhsMaximalComplex, rhsMaximalComplex, leftObject.isSetMaximalComplex(), rightObject.isSetMaximalComplex()));
                    target.setMaximalComplex(mergedMaximalComplex);
                } else {
                    if (maximalComplexShouldBeMergedAndSet == java.lang.Boolean.FALSE) {
                        target.maximalComplex = null;
                    }
                }
            }
            {
                java.lang.Boolean superComplexShouldBeMergedAndSet = strategy.shouldBeMergedAndSet(leftLocator, rightLocator, leftObject.isSetSuperComplex(), rightObject.isSetSuperComplex());
                if (superComplexShouldBeMergedAndSet == java.lang.Boolean.TRUE) {
                    List<TopoComplexMemberType> lhsSuperComplex;
                    lhsSuperComplex = (leftObject.isSetSuperComplex()?leftObject.getSuperComplex():null);
                    List<TopoComplexMemberType> rhsSuperComplex;
                    rhsSuperComplex = (rightObject.isSetSuperComplex()?rightObject.getSuperComplex():null);
                    List<TopoComplexMemberType> mergedSuperComplex = ((List<TopoComplexMemberType> ) strategy.merge(LocatorUtils.property(leftLocator, "superComplex", lhsSuperComplex), LocatorUtils.property(rightLocator, "superComplex", rhsSuperComplex), lhsSuperComplex, rhsSuperComplex, leftObject.isSetSuperComplex(), rightObject.isSetSuperComplex()));
                    target.setSuperComplex(mergedSuperComplex);
                } else {
                    if (superComplexShouldBeMergedAndSet == java.lang.Boolean.FALSE) {
                        target.unsetSuperComplex();
                    }
                }
            }
            {
                java.lang.Boolean subComplexShouldBeMergedAndSet = strategy.shouldBeMergedAndSet(leftLocator, rightLocator, leftObject.isSetSubComplex(), rightObject.isSetSubComplex());
                if (subComplexShouldBeMergedAndSet == java.lang.Boolean.TRUE) {
                    List<TopoComplexMemberType> lhsSubComplex;
                    lhsSubComplex = (leftObject.isSetSubComplex()?leftObject.getSubComplex():null);
                    List<TopoComplexMemberType> rhsSubComplex;
                    rhsSubComplex = (rightObject.isSetSubComplex()?rightObject.getSubComplex():null);
                    List<TopoComplexMemberType> mergedSubComplex = ((List<TopoComplexMemberType> ) strategy.merge(LocatorUtils.property(leftLocator, "subComplex", lhsSubComplex), LocatorUtils.property(rightLocator, "subComplex", rhsSubComplex), lhsSubComplex, rhsSubComplex, leftObject.isSetSubComplex(), rightObject.isSetSubComplex()));
                    target.setSubComplex(mergedSubComplex);
                } else {
                    if (subComplexShouldBeMergedAndSet == java.lang.Boolean.FALSE) {
                        target.unsetSubComplex();
                    }
                }
            }
            {
                java.lang.Boolean topoPrimitiveMemberShouldBeMergedAndSet = strategy.shouldBeMergedAndSet(leftLocator, rightLocator, leftObject.isSetTopoPrimitiveMember(), rightObject.isSetTopoPrimitiveMember());
                if (topoPrimitiveMemberShouldBeMergedAndSet == java.lang.Boolean.TRUE) {
                    List<TopoPrimitiveMemberType> lhsTopoPrimitiveMember;
                    lhsTopoPrimitiveMember = (leftObject.isSetTopoPrimitiveMember()?leftObject.getTopoPrimitiveMember():null);
                    List<TopoPrimitiveMemberType> rhsTopoPrimitiveMember;
                    rhsTopoPrimitiveMember = (rightObject.isSetTopoPrimitiveMember()?rightObject.getTopoPrimitiveMember():null);
                    List<TopoPrimitiveMemberType> mergedTopoPrimitiveMember = ((List<TopoPrimitiveMemberType> ) strategy.merge(LocatorUtils.property(leftLocator, "topoPrimitiveMember", lhsTopoPrimitiveMember), LocatorUtils.property(rightLocator, "topoPrimitiveMember", rhsTopoPrimitiveMember), lhsTopoPrimitiveMember, rhsTopoPrimitiveMember, leftObject.isSetTopoPrimitiveMember(), rightObject.isSetTopoPrimitiveMember()));
                    target.setTopoPrimitiveMember(mergedTopoPrimitiveMember);
                } else {
                    if (topoPrimitiveMemberShouldBeMergedAndSet == java.lang.Boolean.FALSE) {
                        target.unsetTopoPrimitiveMember();
                    }
                }
            }
            {
                java.lang.Boolean topoPrimitiveMembersShouldBeMergedAndSet = strategy.shouldBeMergedAndSet(leftLocator, rightLocator, leftObject.isSetTopoPrimitiveMembers(), rightObject.isSetTopoPrimitiveMembers());
                if (topoPrimitiveMembersShouldBeMergedAndSet == java.lang.Boolean.TRUE) {
                    TopoPrimitiveArrayAssociationType lhsTopoPrimitiveMembers;
                    lhsTopoPrimitiveMembers = leftObject.getTopoPrimitiveMembers();
                    TopoPrimitiveArrayAssociationType rhsTopoPrimitiveMembers;
                    rhsTopoPrimitiveMembers = rightObject.getTopoPrimitiveMembers();
                    TopoPrimitiveArrayAssociationType mergedTopoPrimitiveMembers = ((TopoPrimitiveArrayAssociationType) strategy.merge(LocatorUtils.property(leftLocator, "topoPrimitiveMembers", lhsTopoPrimitiveMembers), LocatorUtils.property(rightLocator, "topoPrimitiveMembers", rhsTopoPrimitiveMembers), lhsTopoPrimitiveMembers, rhsTopoPrimitiveMembers, leftObject.isSetTopoPrimitiveMembers(), rightObject.isSetTopoPrimitiveMembers()));
                    target.setTopoPrimitiveMembers(mergedTopoPrimitiveMembers);
                } else {
                    if (topoPrimitiveMembersShouldBeMergedAndSet == java.lang.Boolean.FALSE) {
                        target.topoPrimitiveMembers = null;
                    }
                }
            }
            {
                java.lang.Boolean isMaximalShouldBeMergedAndSet = strategy.shouldBeMergedAndSet(leftLocator, rightLocator, leftObject.isSetIsMaximal(), rightObject.isSetIsMaximal());
                if (isMaximalShouldBeMergedAndSet == java.lang.Boolean.TRUE) {
                    boolean lhsIsMaximal;
                    lhsIsMaximal = (leftObject.isSetIsMaximal()?leftObject.isIsMaximal():false);
                    boolean rhsIsMaximal;
                    rhsIsMaximal = (rightObject.isSetIsMaximal()?rightObject.isIsMaximal():false);
                    boolean mergedIsMaximal = ((boolean) strategy.merge(LocatorUtils.property(leftLocator, "isMaximal", lhsIsMaximal), LocatorUtils.property(rightLocator, "isMaximal", rhsIsMaximal), lhsIsMaximal, rhsIsMaximal, leftObject.isSetIsMaximal(), rightObject.isSetIsMaximal()));
                    target.setIsMaximal(mergedIsMaximal);
                } else {
                    if (isMaximalShouldBeMergedAndSet == java.lang.Boolean.FALSE) {
                        target.unsetIsMaximal();
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
