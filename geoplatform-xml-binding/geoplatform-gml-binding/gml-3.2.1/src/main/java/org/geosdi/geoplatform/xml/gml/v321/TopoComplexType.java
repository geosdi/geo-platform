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
// Generato il: 2026.11.02 alle 03:48:04 PM CET
//

package org.geosdi.geoplatform.xml.gml.v321;

import java.util.ArrayList;
import java.util.List;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;
import org.jvnet.jaxb.lang.JAXBToStringStrategy;
import org.jvnet.jaxb.lang.ToString;
import org.jvnet.jaxb.lang.ToStringStrategy;
import org.jvnet.jaxb.locator.ObjectLocator;


/**
 * <p>Classe Java per TopoComplexType complex type.</p>
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.</p>
 * 
 * <pre>{@code
 * <complexType name="TopoComplexType">
 *   <complexContent>
 *     <extension base="{http://www.opengis.net/gml/3.2}AbstractTopologyType">
 *       <sequence>
 *         <element ref="{http://www.opengis.net/gml/3.2}maximalComplex"/>
 *         <element ref="{http://www.opengis.net/gml/3.2}superComplex" maxOccurs="unbounded" minOccurs="0"/>
 *         <element ref="{http://www.opengis.net/gml/3.2}subComplex" maxOccurs="unbounded" minOccurs="0"/>
 *         <element ref="{http://www.opengis.net/gml/3.2}topoPrimitiveMember" maxOccurs="unbounded" minOccurs="0"/>
 *         <element ref="{http://www.opengis.net/gml/3.2}topoPrimitiveMembers" minOccurs="0"/>
 *       </sequence>
 *       <attGroup ref="{http://www.opengis.net/gml/3.2}AggregationAttributeGroup"/>
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
    implements ToString
{

    /**
     * The property elements gml:subComplex, gml:superComplex and gml:maximalComplex provide an encoding for relationships between topology complexes as described for gml:TopoComplex above.
     * 
     */
    @XmlElement(required = true)
    protected TopoComplexPropertyType maximalComplex;
    /**
     * The property elements gml:subComplex, gml:superComplex and gml:maximalComplex provide an encoding for relationships between topology complexes as described for gml:TopoComplex above.
     * 
     */
    protected List<TopoComplexPropertyType> superComplex;
    /**
     * The property elements gml:subComplex, gml:superComplex and gml:maximalComplex provide an encoding for relationships between topology complexes as described for gml:TopoComplex above.
     * 
     */
    protected List<TopoComplexPropertyType> subComplex;
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
     *     {@link TopoComplexPropertyType }
     *     
     */
    public TopoComplexPropertyType getMaximalComplex() {
        return maximalComplex;
    }

    /**
     * Imposta il valore della proprietà maximalComplex.
     * 
     * @param value
     *     allowed object is
     *     {@link TopoComplexPropertyType }
     *     
     * @see #getMaximalComplex()
     */
    public void setMaximalComplex(TopoComplexPropertyType value) {
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
     * {@link TopoComplexPropertyType }
     * </p>
     * 
     * 
     * @return
     *     The value of the superComplex property.
     */
    public List<TopoComplexPropertyType> getSuperComplex() {
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
     * {@link TopoComplexPropertyType }
     * </p>
     * 
     * 
     * @return
     *     The value of the subComplex property.
     */
    public List<TopoComplexPropertyType> getSubComplex() {
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
            TopoComplexPropertyType theMaximalComplex;
            theMaximalComplex = this.getMaximalComplex();
            strategy.appendField(locator, this, "maximalComplex", buffer, theMaximalComplex, this.isSetMaximalComplex());
        }
        {
            List<TopoComplexPropertyType> theSuperComplex;
            theSuperComplex = (this.isSetSuperComplex()?this.getSuperComplex():null);
            strategy.appendField(locator, this, "superComplex", buffer, theSuperComplex, this.isSetSuperComplex());
        }
        {
            List<TopoComplexPropertyType> theSubComplex;
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

    public void setSuperComplex(List<TopoComplexPropertyType> value) {
        this.superComplex = null;
        if (value!= null) {
            List<TopoComplexPropertyType> draftl = this.getSuperComplex();
            draftl.addAll(value);
        }
    }

    public void setSubComplex(List<TopoComplexPropertyType> value) {
        this.subComplex = null;
        if (value!= null) {
            List<TopoComplexPropertyType> draftl = this.getSubComplex();
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

}
