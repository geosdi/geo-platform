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
 * <p>Classe Java per TopoComplexType complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="TopoComplexType">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.opengis.net/gml}AbstractTopologyType">
 *       &lt;sequence>
 *         &lt;element ref="{http://www.opengis.net/gml}maximalComplex"/>
 *         &lt;element ref="{http://www.opengis.net/gml}superComplex" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element ref="{http://www.opengis.net/gml}subComplex" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element ref="{http://www.opengis.net/gml}topoPrimitiveMember" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element ref="{http://www.opengis.net/gml}topoPrimitiveMembers" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attGroup ref="{http://www.opengis.net/gml}AggregationAttributeGroup"/>
 *       &lt;attribute name="isMaximal" type="{http://www.w3.org/2001/XMLSchema}boolean" default="false" />
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
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

    @XmlElement(required = true)
    protected TopoComplexMemberType maximalComplex;
    protected List<TopoComplexMemberType> superComplex;
    protected List<TopoComplexMemberType> subComplex;
    protected List<TopoPrimitiveMemberType> topoPrimitiveMember;
    protected TopoPrimitiveArrayAssociationType topoPrimitiveMembers;
    @XmlAttribute(name = "isMaximal")
    protected java.lang.Boolean isMaximal;
    @XmlAttribute(name = "aggregationType")
    protected AggregationType aggregationType;

    /**
     * Recupera il valore della proprietà maximalComplex.
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
     */
    public void setMaximalComplex(TopoComplexMemberType value) {
        this.maximalComplex = value;
    }

    public boolean isSetMaximalComplex() {
        return (this.maximalComplex!= null);
    }

    /**
     * Gets the value of the superComplex property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the superComplex property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSuperComplex().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link TopoComplexMemberType }
     * 
     * 
     */
    public List<TopoComplexMemberType> getSuperComplex() {
        if (superComplex == null) {
            superComplex = new ArrayList<TopoComplexMemberType>();
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
     * Gets the value of the subComplex property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the subComplex property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSubComplex().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link TopoComplexMemberType }
     * 
     * 
     */
    public List<TopoComplexMemberType> getSubComplex() {
        if (subComplex == null) {
            subComplex = new ArrayList<TopoComplexMemberType>();
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
     * Gets the value of the topoPrimitiveMember property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the topoPrimitiveMember property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getTopoPrimitiveMember().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link TopoPrimitiveMemberType }
     * 
     * 
     */
    public List<TopoPrimitiveMemberType> getTopoPrimitiveMember() {
        if (topoPrimitiveMember == null) {
            topoPrimitiveMember = new ArrayList<TopoPrimitiveMemberType>();
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
     * Recupera il valore della proprietà topoPrimitiveMembers.
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
            TopoComplexMemberType theMaximalComplex;
            theMaximalComplex = this.getMaximalComplex();
            strategy.appendField(locator, this, "maximalComplex", buffer, theMaximalComplex);
        }
        {
            List<TopoComplexMemberType> theSuperComplex;
            theSuperComplex = this.getSuperComplex();
            strategy.appendField(locator, this, "superComplex", buffer, theSuperComplex);
        }
        {
            List<TopoComplexMemberType> theSubComplex;
            theSubComplex = this.getSubComplex();
            strategy.appendField(locator, this, "subComplex", buffer, theSubComplex);
        }
        {
            List<TopoPrimitiveMemberType> theTopoPrimitiveMember;
            theTopoPrimitiveMember = this.getTopoPrimitiveMember();
            strategy.appendField(locator, this, "topoPrimitiveMember", buffer, theTopoPrimitiveMember);
        }
        {
            TopoPrimitiveArrayAssociationType theTopoPrimitiveMembers;
            theTopoPrimitiveMembers = this.getTopoPrimitiveMembers();
            strategy.appendField(locator, this, "topoPrimitiveMembers", buffer, theTopoPrimitiveMembers);
        }
        {
            boolean theIsMaximal;
            theIsMaximal = this.isIsMaximal();
            strategy.appendField(locator, this, "isMaximal", buffer, theIsMaximal);
        }
        {
            AggregationType theAggregationType;
            theAggregationType = this.getAggregationType();
            strategy.appendField(locator, this, "aggregationType", buffer, theAggregationType);
        }
        return buffer;
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof TopoComplexType)) {
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
            if (!strategy.equals(LocatorUtils.property(thisLocator, "maximalComplex", lhsMaximalComplex), LocatorUtils.property(thatLocator, "maximalComplex", rhsMaximalComplex), lhsMaximalComplex, rhsMaximalComplex)) {
                return false;
            }
        }
        {
            List<TopoComplexMemberType> lhsSuperComplex;
            lhsSuperComplex = this.getSuperComplex();
            List<TopoComplexMemberType> rhsSuperComplex;
            rhsSuperComplex = that.getSuperComplex();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "superComplex", lhsSuperComplex), LocatorUtils.property(thatLocator, "superComplex", rhsSuperComplex), lhsSuperComplex, rhsSuperComplex)) {
                return false;
            }
        }
        {
            List<TopoComplexMemberType> lhsSubComplex;
            lhsSubComplex = this.getSubComplex();
            List<TopoComplexMemberType> rhsSubComplex;
            rhsSubComplex = that.getSubComplex();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "subComplex", lhsSubComplex), LocatorUtils.property(thatLocator, "subComplex", rhsSubComplex), lhsSubComplex, rhsSubComplex)) {
                return false;
            }
        }
        {
            List<TopoPrimitiveMemberType> lhsTopoPrimitiveMember;
            lhsTopoPrimitiveMember = this.getTopoPrimitiveMember();
            List<TopoPrimitiveMemberType> rhsTopoPrimitiveMember;
            rhsTopoPrimitiveMember = that.getTopoPrimitiveMember();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "topoPrimitiveMember", lhsTopoPrimitiveMember), LocatorUtils.property(thatLocator, "topoPrimitiveMember", rhsTopoPrimitiveMember), lhsTopoPrimitiveMember, rhsTopoPrimitiveMember)) {
                return false;
            }
        }
        {
            TopoPrimitiveArrayAssociationType lhsTopoPrimitiveMembers;
            lhsTopoPrimitiveMembers = this.getTopoPrimitiveMembers();
            TopoPrimitiveArrayAssociationType rhsTopoPrimitiveMembers;
            rhsTopoPrimitiveMembers = that.getTopoPrimitiveMembers();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "topoPrimitiveMembers", lhsTopoPrimitiveMembers), LocatorUtils.property(thatLocator, "topoPrimitiveMembers", rhsTopoPrimitiveMembers), lhsTopoPrimitiveMembers, rhsTopoPrimitiveMembers)) {
                return false;
            }
        }
        {
            boolean lhsIsMaximal;
            lhsIsMaximal = this.isIsMaximal();
            boolean rhsIsMaximal;
            rhsIsMaximal = that.isIsMaximal();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "isMaximal", lhsIsMaximal), LocatorUtils.property(thatLocator, "isMaximal", rhsIsMaximal), lhsIsMaximal, rhsIsMaximal)) {
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
            TopoComplexMemberType theMaximalComplex;
            theMaximalComplex = this.getMaximalComplex();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "maximalComplex", theMaximalComplex), currentHashCode, theMaximalComplex);
        }
        {
            List<TopoComplexMemberType> theSuperComplex;
            theSuperComplex = this.getSuperComplex();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "superComplex", theSuperComplex), currentHashCode, theSuperComplex);
        }
        {
            List<TopoComplexMemberType> theSubComplex;
            theSubComplex = this.getSubComplex();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "subComplex", theSubComplex), currentHashCode, theSubComplex);
        }
        {
            List<TopoPrimitiveMemberType> theTopoPrimitiveMember;
            theTopoPrimitiveMember = this.getTopoPrimitiveMember();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "topoPrimitiveMember", theTopoPrimitiveMember), currentHashCode, theTopoPrimitiveMember);
        }
        {
            TopoPrimitiveArrayAssociationType theTopoPrimitiveMembers;
            theTopoPrimitiveMembers = this.getTopoPrimitiveMembers();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "topoPrimitiveMembers", theTopoPrimitiveMembers), currentHashCode, theTopoPrimitiveMembers);
        }
        {
            boolean theIsMaximal;
            theIsMaximal = this.isIsMaximal();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "isMaximal", theIsMaximal), currentHashCode, theIsMaximal);
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
        if (draftCopy instanceof TopoComplexType) {
            final TopoComplexType copy = ((TopoComplexType) draftCopy);
            if (this.isSetMaximalComplex()) {
                TopoComplexMemberType sourceMaximalComplex;
                sourceMaximalComplex = this.getMaximalComplex();
                TopoComplexMemberType copyMaximalComplex = ((TopoComplexMemberType) strategy.copy(LocatorUtils.property(locator, "maximalComplex", sourceMaximalComplex), sourceMaximalComplex));
                copy.setMaximalComplex(copyMaximalComplex);
            } else {
                copy.maximalComplex = null;
            }
            if (this.isSetSuperComplex()) {
                List<TopoComplexMemberType> sourceSuperComplex;
                sourceSuperComplex = this.getSuperComplex();
                @SuppressWarnings("unchecked")
                List<TopoComplexMemberType> copySuperComplex = ((List<TopoComplexMemberType> ) strategy.copy(LocatorUtils.property(locator, "superComplex", sourceSuperComplex), sourceSuperComplex));
                copy.unsetSuperComplex();
                List<TopoComplexMemberType> uniqueSuperComplexl = copy.getSuperComplex();
                uniqueSuperComplexl.addAll(copySuperComplex);
            } else {
                copy.unsetSuperComplex();
            }
            if (this.isSetSubComplex()) {
                List<TopoComplexMemberType> sourceSubComplex;
                sourceSubComplex = this.getSubComplex();
                @SuppressWarnings("unchecked")
                List<TopoComplexMemberType> copySubComplex = ((List<TopoComplexMemberType> ) strategy.copy(LocatorUtils.property(locator, "subComplex", sourceSubComplex), sourceSubComplex));
                copy.unsetSubComplex();
                List<TopoComplexMemberType> uniqueSubComplexl = copy.getSubComplex();
                uniqueSubComplexl.addAll(copySubComplex);
            } else {
                copy.unsetSubComplex();
            }
            if (this.isSetTopoPrimitiveMember()) {
                List<TopoPrimitiveMemberType> sourceTopoPrimitiveMember;
                sourceTopoPrimitiveMember = this.getTopoPrimitiveMember();
                @SuppressWarnings("unchecked")
                List<TopoPrimitiveMemberType> copyTopoPrimitiveMember = ((List<TopoPrimitiveMemberType> ) strategy.copy(LocatorUtils.property(locator, "topoPrimitiveMember", sourceTopoPrimitiveMember), sourceTopoPrimitiveMember));
                copy.unsetTopoPrimitiveMember();
                List<TopoPrimitiveMemberType> uniqueTopoPrimitiveMemberl = copy.getTopoPrimitiveMember();
                uniqueTopoPrimitiveMemberl.addAll(copyTopoPrimitiveMember);
            } else {
                copy.unsetTopoPrimitiveMember();
            }
            if (this.isSetTopoPrimitiveMembers()) {
                TopoPrimitiveArrayAssociationType sourceTopoPrimitiveMembers;
                sourceTopoPrimitiveMembers = this.getTopoPrimitiveMembers();
                TopoPrimitiveArrayAssociationType copyTopoPrimitiveMembers = ((TopoPrimitiveArrayAssociationType) strategy.copy(LocatorUtils.property(locator, "topoPrimitiveMembers", sourceTopoPrimitiveMembers), sourceTopoPrimitiveMembers));
                copy.setTopoPrimitiveMembers(copyTopoPrimitiveMembers);
            } else {
                copy.topoPrimitiveMembers = null;
            }
            if (this.isSetIsMaximal()) {
                boolean sourceIsMaximal;
                sourceIsMaximal = this.isIsMaximal();
                boolean copyIsMaximal = strategy.copy(LocatorUtils.property(locator, "isMaximal", sourceIsMaximal), sourceIsMaximal);
                copy.setIsMaximal(copyIsMaximal);
            } else {
                copy.unsetIsMaximal();
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
        return new TopoComplexType();
    }

    public void mergeFrom(Object left, Object right) {
        final MergeStrategy strategy = JAXBMergeStrategy.INSTANCE;
        mergeFrom(null, null, left, right, strategy);
    }

    public void mergeFrom(ObjectLocator leftLocator, ObjectLocator rightLocator, Object left, Object right, MergeStrategy strategy) {
        super.mergeFrom(leftLocator, rightLocator, left, right, strategy);
        if (right instanceof TopoComplexType) {
            final TopoComplexType target = this;
            final TopoComplexType leftObject = ((TopoComplexType) left);
            final TopoComplexType rightObject = ((TopoComplexType) right);
            {
                TopoComplexMemberType lhsMaximalComplex;
                lhsMaximalComplex = leftObject.getMaximalComplex();
                TopoComplexMemberType rhsMaximalComplex;
                rhsMaximalComplex = rightObject.getMaximalComplex();
                target.setMaximalComplex(((TopoComplexMemberType) strategy.merge(LocatorUtils.property(leftLocator, "maximalComplex", lhsMaximalComplex), LocatorUtils.property(rightLocator, "maximalComplex", rhsMaximalComplex), lhsMaximalComplex, rhsMaximalComplex)));
            }
            {
                List<TopoComplexMemberType> lhsSuperComplex;
                lhsSuperComplex = leftObject.getSuperComplex();
                List<TopoComplexMemberType> rhsSuperComplex;
                rhsSuperComplex = rightObject.getSuperComplex();
                target.unsetSuperComplex();
                List<TopoComplexMemberType> uniqueSuperComplexl = target.getSuperComplex();
                uniqueSuperComplexl.addAll(((List<TopoComplexMemberType> ) strategy.merge(LocatorUtils.property(leftLocator, "superComplex", lhsSuperComplex), LocatorUtils.property(rightLocator, "superComplex", rhsSuperComplex), lhsSuperComplex, rhsSuperComplex)));
            }
            {
                List<TopoComplexMemberType> lhsSubComplex;
                lhsSubComplex = leftObject.getSubComplex();
                List<TopoComplexMemberType> rhsSubComplex;
                rhsSubComplex = rightObject.getSubComplex();
                target.unsetSubComplex();
                List<TopoComplexMemberType> uniqueSubComplexl = target.getSubComplex();
                uniqueSubComplexl.addAll(((List<TopoComplexMemberType> ) strategy.merge(LocatorUtils.property(leftLocator, "subComplex", lhsSubComplex), LocatorUtils.property(rightLocator, "subComplex", rhsSubComplex), lhsSubComplex, rhsSubComplex)));
            }
            {
                List<TopoPrimitiveMemberType> lhsTopoPrimitiveMember;
                lhsTopoPrimitiveMember = leftObject.getTopoPrimitiveMember();
                List<TopoPrimitiveMemberType> rhsTopoPrimitiveMember;
                rhsTopoPrimitiveMember = rightObject.getTopoPrimitiveMember();
                target.unsetTopoPrimitiveMember();
                List<TopoPrimitiveMemberType> uniqueTopoPrimitiveMemberl = target.getTopoPrimitiveMember();
                uniqueTopoPrimitiveMemberl.addAll(((List<TopoPrimitiveMemberType> ) strategy.merge(LocatorUtils.property(leftLocator, "topoPrimitiveMember", lhsTopoPrimitiveMember), LocatorUtils.property(rightLocator, "topoPrimitiveMember", rhsTopoPrimitiveMember), lhsTopoPrimitiveMember, rhsTopoPrimitiveMember)));
            }
            {
                TopoPrimitiveArrayAssociationType lhsTopoPrimitiveMembers;
                lhsTopoPrimitiveMembers = leftObject.getTopoPrimitiveMembers();
                TopoPrimitiveArrayAssociationType rhsTopoPrimitiveMembers;
                rhsTopoPrimitiveMembers = rightObject.getTopoPrimitiveMembers();
                target.setTopoPrimitiveMembers(((TopoPrimitiveArrayAssociationType) strategy.merge(LocatorUtils.property(leftLocator, "topoPrimitiveMembers", lhsTopoPrimitiveMembers), LocatorUtils.property(rightLocator, "topoPrimitiveMembers", rhsTopoPrimitiveMembers), lhsTopoPrimitiveMembers, rhsTopoPrimitiveMembers)));
            }
            {
                boolean lhsIsMaximal;
                lhsIsMaximal = leftObject.isIsMaximal();
                boolean rhsIsMaximal;
                rhsIsMaximal = rightObject.isIsMaximal();
                target.setIsMaximal(((boolean) strategy.merge(LocatorUtils.property(leftLocator, "isMaximal", lhsIsMaximal), LocatorUtils.property(rightLocator, "isMaximal", rhsIsMaximal), lhsIsMaximal, rhsIsMaximal)));
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

    public void setSuperComplex(List<TopoComplexMemberType> value) {
        List<TopoComplexMemberType> draftl = this.getSuperComplex();
        draftl.addAll(value);
    }

    public void setSubComplex(List<TopoComplexMemberType> value) {
        List<TopoComplexMemberType> draftl = this.getSubComplex();
        draftl.addAll(value);
    }

    public void setTopoPrimitiveMember(List<TopoPrimitiveMemberType> value) {
        List<TopoPrimitiveMemberType> draftl = this.getTopoPrimitiveMember();
        draftl.addAll(value);
    }

    public TopoComplexType withMaximalComplex(TopoComplexMemberType value) {
        setMaximalComplex(value);
        return this;
    }

    public TopoComplexType withSuperComplex(TopoComplexMemberType... values) {
        if (values!= null) {
            for (TopoComplexMemberType value: values) {
                getSuperComplex().add(value);
            }
        }
        return this;
    }

    public TopoComplexType withSuperComplex(Collection<TopoComplexMemberType> values) {
        if (values!= null) {
            getSuperComplex().addAll(values);
        }
        return this;
    }

    public TopoComplexType withSubComplex(TopoComplexMemberType... values) {
        if (values!= null) {
            for (TopoComplexMemberType value: values) {
                getSubComplex().add(value);
            }
        }
        return this;
    }

    public TopoComplexType withSubComplex(Collection<TopoComplexMemberType> values) {
        if (values!= null) {
            getSubComplex().addAll(values);
        }
        return this;
    }

    public TopoComplexType withTopoPrimitiveMember(TopoPrimitiveMemberType... values) {
        if (values!= null) {
            for (TopoPrimitiveMemberType value: values) {
                getTopoPrimitiveMember().add(value);
            }
        }
        return this;
    }

    public TopoComplexType withTopoPrimitiveMember(Collection<TopoPrimitiveMemberType> values) {
        if (values!= null) {
            getTopoPrimitiveMember().addAll(values);
        }
        return this;
    }

    public TopoComplexType withTopoPrimitiveMembers(TopoPrimitiveArrayAssociationType value) {
        setTopoPrimitiveMembers(value);
        return this;
    }

    public TopoComplexType withIsMaximal(boolean value) {
        setIsMaximal(value);
        return this;
    }

    public TopoComplexType withAggregationType(AggregationType value) {
        setAggregationType(value);
        return this;
    }

    public TopoComplexType withSuperComplex(List<TopoComplexMemberType> value) {
        setSuperComplex(value);
        return this;
    }

    public TopoComplexType withSubComplex(List<TopoComplexMemberType> value) {
        setSubComplex(value);
        return this;
    }

    public TopoComplexType withTopoPrimitiveMember(List<TopoPrimitiveMemberType> value) {
        setTopoPrimitiveMember(value);
        return this;
    }

    @Override
    public TopoComplexType withMetaDataProperty(MetaDataPropertyType... values) {
        if (values!= null) {
            for (MetaDataPropertyType value: values) {
                getMetaDataProperty().add(value);
            }
        }
        return this;
    }

    @Override
    public TopoComplexType withMetaDataProperty(Collection<MetaDataPropertyType> values) {
        if (values!= null) {
            getMetaDataProperty().addAll(values);
        }
        return this;
    }

    @Override
    public TopoComplexType withDescription(StringOrRefType value) {
        setDescription(value);
        return this;
    }

    @Override
    public TopoComplexType withDescriptionReference(ReferenceType value) {
        setDescriptionReference(value);
        return this;
    }

    @Override
    public TopoComplexType withIdentifier(CodeWithAuthorityType value) {
        setIdentifier(value);
        return this;
    }

    @Override
    public TopoComplexType withName(CodeType... values) {
        if (values!= null) {
            for (CodeType value: values) {
                getName().add(value);
            }
        }
        return this;
    }

    @Override
    public TopoComplexType withName(Collection<CodeType> values) {
        if (values!= null) {
            getName().addAll(values);
        }
        return this;
    }

    @Override
    public TopoComplexType withId(String value) {
        setId(value);
        return this;
    }

    @Override
    public TopoComplexType withMetaDataProperty(List<MetaDataPropertyType> value) {
        setMetaDataProperty(value);
        return this;
    }

    @Override
    public TopoComplexType withName(List<CodeType> value) {
        setName(value);
        return this;
    }

}
