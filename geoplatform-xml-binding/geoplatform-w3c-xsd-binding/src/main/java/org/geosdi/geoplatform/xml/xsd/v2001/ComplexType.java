/*
 *
 *    geo-platform
 *    Rich webgis framework
 *    http://geo-platform.org
 *   ====================================================================
 *
 *   Copyright (C) 2008-2024 geoSDI Group (CNR IMAA - Potenza - ITALY).
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
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.5-b10 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2012.11.02 at 06:49:13 PM CET 
//
package org.geosdi.geoplatform.xml.xsd.v2001;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import org.jvnet.jaxb2_commons.lang.JAXBToStringStrategy;
import org.jvnet.jaxb2_commons.lang.ToString;
import org.jvnet.jaxb2_commons.lang.ToStringStrategy;
import org.jvnet.jaxb2_commons.locator.ObjectLocator;

/**
 * <p>Java class for complexType complex type.
 *
 * <p>The following schema fragment specifies the expected content contained
 * within this class.
 *
 * <pre>
 * &lt;complexType name="complexType">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.w3.org/2001/XMLSchema}annotated">
 *       &lt;group ref="{http://www.w3.org/2001/XMLSchema}complexTypeModel"/>
 *       &lt;attribute name="name" type="{http://www.w3.org/2001/XMLSchema}NCName" />
 *       &lt;attribute name="mixed" type="{http://www.w3.org/2001/XMLSchema}boolean" default="false" />
 *       &lt;attribute name="abstract" type="{http://www.w3.org/2001/XMLSchema}boolean" default="false" />
 *       &lt;attribute name="final" type="{http://www.w3.org/2001/XMLSchema}derivationSet" />
 *       &lt;attribute name="block" type="{http://www.w3.org/2001/XMLSchema}derivationSet" />
 *       &lt;anyAttribute processContents='lax' namespace='##other'/>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "complexType", propOrder = {
    "simpleContent",
    "complexContent",
    "group",
    "all",
    "choice",
    "sequence",
    "attributeOrAttributeGroup",
    "anyAttribute"
})
@XmlSeeAlso({
    TopLevelComplexType.class,
    LocalComplexType.class
})
public abstract class ComplexType
        extends Annotated
        implements ToString {

    protected SimpleContent simpleContent;
    protected ComplexContent complexContent;
    protected GroupRef group;
    protected All all;
    protected ExplicitGroup choice;
    protected ExplicitGroup sequence;
    @XmlElements({
        @XmlElement(name = "attribute", type = Attribute.class),
        @XmlElement(name = "attributeGroup", type = AttributeGroupRef.class)
    })
    protected List<Annotated> attributeOrAttributeGroup;
    protected Wildcard anyAttribute;
    @XmlAttribute(name = "name")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "NCName")
    protected String name;
    @XmlAttribute(name = "mixed")
    protected Boolean mixed;
    @XmlAttribute(name = "abstract")
    protected Boolean _abstract;
    @XmlAttribute(name = "final")
    @XmlSchemaType(name = "derivationSet")
    protected List<String> _final;
    @XmlAttribute(name = "block")
    @XmlSchemaType(name = "derivationSet")
    protected List<String> block;

    /**
     * Gets the value of the simpleContent property.
     *
     * @return possible object is {@link SimpleContent }
     *
     */
    public SimpleContent getSimpleContent() {
        return simpleContent;
    }

    /**
     * Sets the value of the simpleContent property.
     *
     * @param value allowed object is {@link SimpleContent }
     *
     */
    public void setSimpleContent(SimpleContent value) {
        this.simpleContent = value;
    }

    public boolean isSetSimpleContent() {
        return (this.simpleContent != null);
    }

    /**
     * Gets the value of the complexContent property.
     *
     * @return possible object is {@link ComplexContent }
     *
     */
    public ComplexContent getComplexContent() {
        return complexContent;
    }

    /**
     * Sets the value of the complexContent property.
     *
     * @param value allowed object is {@link ComplexContent }
     *
     */
    public void setComplexContent(ComplexContent value) {
        this.complexContent = value;
    }

    public boolean isSetComplexContent() {
        return (this.complexContent != null);
    }

    /**
     * Gets the value of the group property.
     *
     * @return possible object is {@link GroupRef }
     *
     */
    public GroupRef getGroup() {
        return group;
    }

    /**
     * Sets the value of the group property.
     *
     * @param value allowed object is {@link GroupRef }
     *
     */
    public void setGroup(GroupRef value) {
        this.group = value;
    }

    public boolean isSetGroup() {
        return (this.group != null);
    }

    /**
     * Gets the value of the all property.
     *
     * @return possible object is {@link All }
     *
     */
    public All getAll() {
        return all;
    }

    /**
     * Sets the value of the all property.
     *
     * @param value allowed object is {@link All }
     *
     */
    public void setAll(All value) {
        this.all = value;
    }

    public boolean isSetAll() {
        return (this.all != null);
    }

    /**
     * Gets the value of the choice property.
     *
     * @return possible object is {@link ExplicitGroup }
     *
     */
    public ExplicitGroup getChoice() {
        return choice;
    }

    /**
     * Sets the value of the choice property.
     *
     * @param value allowed object is {@link ExplicitGroup }
     *
     */
    public void setChoice(ExplicitGroup value) {
        this.choice = value;
    }

    public boolean isSetChoice() {
        return (this.choice != null);
    }

    /**
     * Gets the value of the sequence property.
     *
     * @return possible object is {@link ExplicitGroup }
     *
     */
    public ExplicitGroup getSequence() {
        return sequence;
    }

    /**
     * Sets the value of the sequence property.
     *
     * @param value allowed object is {@link ExplicitGroup }
     *
     */
    public void setSequence(ExplicitGroup value) {
        this.sequence = value;
    }

    public boolean isSetSequence() {
        return (this.sequence != null);
    }

    /**
     * Gets the value of the attributeOrAttributeGroup property.
     *
     * <p> This accessor method returns a reference to the live list, not a
     * snapshot. Therefore any modification you make to the returned list will
     * be present inside the JAXB object. This is why there is not a
     * <CODE>set</CODE> method for the attributeOrAttributeGroup property.
     *
     * <p> For example, to add a new item, do as follows:
     * <pre>
     *    getAttributeOrAttributeGroup().add(newItem);
     * </pre>
     *
     *
     * <p> Objects of the following type(s) are allowed in the list      {@link Attribute }
     * {@link AttributeGroupRef }
     *
     *
     */
    public List<Annotated> getAttributeOrAttributeGroup() {
        if (attributeOrAttributeGroup == null) {
            attributeOrAttributeGroup = new ArrayList<Annotated>();
        }
        return this.attributeOrAttributeGroup;
    }

    public boolean isSetAttributeOrAttributeGroup() {
        return ((this.attributeOrAttributeGroup != null) && (!this.attributeOrAttributeGroup.isEmpty()));
    }

    public void unsetAttributeOrAttributeGroup() {
        this.attributeOrAttributeGroup = null;
    }

    /**
     * Gets the value of the anyAttribute property.
     *
     * @return possible object is {@link Wildcard }
     *
     */
    public Wildcard getAnyAttribute() {
        return anyAttribute;
    }

    /**
     * Sets the value of the anyAttribute property.
     *
     * @param value allowed object is {@link Wildcard }
     *
     */
    public void setAnyAttribute(Wildcard value) {
        this.anyAttribute = value;
    }

    public boolean isSetAnyAttribute() {
        return (this.anyAttribute != null);
    }

    /**
     * Gets the value of the name property.
     *
     * @return possible object is {@link String }
     *
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the value of the name property.
     *
     * @param value allowed object is {@link String }
     *
     */
    public void setName(String value) {
        this.name = value;
    }

    public boolean isSetName() {
        return (this.name != null);
    }

    /**
     * Gets the value of the mixed property.
     *
     * @return possible object is {@link Boolean }
     *
     */
    public boolean isMixed() {
        if (mixed == null) {
            return false;
        } else {
            return mixed;
        }
    }

    /**
     * Sets the value of the mixed property.
     *
     * @param value allowed object is {@link Boolean }
     *
     */
    public void setMixed(boolean value) {
        this.mixed = value;
    }

    public boolean isSetMixed() {
        return (this.mixed != null);
    }

    public void unsetMixed() {
        this.mixed = null;
    }

    /**
     * Gets the value of the abstract property.
     *
     * @return possible object is {@link Boolean }
     *
     */
    public boolean isAbstract() {
        if (_abstract == null) {
            return false;
        } else {
            return _abstract;
        }
    }

    /**
     * Sets the value of the abstract property.
     *
     * @param value allowed object is {@link Boolean }
     *
     */
    public void setAbstract(boolean value) {
        this._abstract = value;
    }

    public boolean isSetAbstract() {
        return (this._abstract != null);
    }

    public void unsetAbstract() {
        this._abstract = null;
    }

    /**
     * Gets the value of the final property.
     *
     * <p> This accessor method returns a reference to the live list, not a
     * snapshot. Therefore any modification you make to the returned list will
     * be present inside the JAXB object. This is why there is not a
     * <CODE>set</CODE> method for the final property.
     *
     * <p> For example, to add a new item, do as follows:
     * <pre>
     *    getFinal().add(newItem);
     * </pre>
     *
     *
     * <p> Objects of the following type(s) are allowed in the list
     * {@link String }
     *
     *
     */
    public List<String> getFinal() {
        if (_final == null) {
            _final = new ArrayList<String>();
        }
        return this._final;
    }

    public boolean isSetFinal() {
        return ((this._final != null) && (!this._final.isEmpty()));
    }

    public void unsetFinal() {
        this._final = null;
    }

    /**
     * Gets the value of the block property.
     *
     * <p> This accessor method returns a reference to the live list, not a
     * snapshot. Therefore any modification you make to the returned list will
     * be present inside the JAXB object. This is why there is not a
     * <CODE>set</CODE> method for the block property.
     *
     * <p> For example, to add a new item, do as follows:
     * <pre>
     *    getBlock().add(newItem);
     * </pre>
     *
     *
     * <p> Objects of the following type(s) are allowed in the list
     * {@link String }
     *
     *
     */
    public List<String> getBlock() {
        if (block == null) {
            block = new ArrayList<String>();
        }
        return this.block;
    }

    public boolean isSetBlock() {
        return ((this.block != null) && (!this.block.isEmpty()));
    }

    public void unsetBlock() {
        this.block = null;
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
            SimpleContent theSimpleContent;
            theSimpleContent = this.getSimpleContent();
            strategy.appendField(locator, this, "simpleContent", buffer, theSimpleContent);
        }
        {
            ComplexContent theComplexContent;
            theComplexContent = this.getComplexContent();
            strategy.appendField(locator, this, "complexContent", buffer, theComplexContent);
        }
        {
            GroupRef theGroup;
            theGroup = this.getGroup();
            strategy.appendField(locator, this, "group", buffer, theGroup);
        }
        {
            All theAll;
            theAll = this.getAll();
            strategy.appendField(locator, this, "all", buffer, theAll);
        }
        {
            ExplicitGroup theChoice;
            theChoice = this.getChoice();
            strategy.appendField(locator, this, "choice", buffer, theChoice);
        }
        {
            ExplicitGroup theSequence;
            theSequence = this.getSequence();
            strategy.appendField(locator, this, "sequence", buffer, theSequence);
        }
        {
            List<Annotated> theAttributeOrAttributeGroup;
            theAttributeOrAttributeGroup = (this.isSetAttributeOrAttributeGroup() ? this.getAttributeOrAttributeGroup() : null);
            strategy.appendField(locator, this, "attributeOrAttributeGroup", buffer, theAttributeOrAttributeGroup);
        }
        {
            Wildcard theAnyAttribute;
            theAnyAttribute = this.getAnyAttribute();
            strategy.appendField(locator, this, "anyAttribute", buffer, theAnyAttribute);
        }
        {
            String theName;
            theName = this.getName();
            strategy.appendField(locator, this, "name", buffer, theName);
        }
        {
            boolean theMixed;
            theMixed = (this.isSetMixed() ? this.isMixed() : false);
            strategy.appendField(locator, this, "mixed", buffer, theMixed);
        }
        {
            boolean theAbstract;
            theAbstract = (this.isSetAbstract() ? this.isAbstract() : false);
            strategy.appendField(locator, this, "_abstract", buffer, theAbstract);
        }
        {
            List<String> theFinal;
            theFinal = (this.isSetFinal() ? this.getFinal() : null);
            strategy.appendField(locator, this, "_final", buffer, theFinal);
        }
        {
            List<String> theBlock;
            theBlock = (this.isSetBlock() ? this.getBlock() : null);
            strategy.appendField(locator, this, "block", buffer, theBlock);
        }
        return buffer;
    }

    public void setAttributeOrAttributeGroup(List<Annotated> value) {
        this.attributeOrAttributeGroup = null;
        List<Annotated> draftl = this.getAttributeOrAttributeGroup();
        draftl.addAll(value);
    }

    public void setFinal(List<String> value) {
        this._final = null;
        List<String> draftl = this.getFinal();
        draftl.addAll(value);
    }

    public void setBlock(List<String> value) {
        this.block = null;
        List<String> draftl = this.getBlock();
        draftl.addAll(value);
    }
}
