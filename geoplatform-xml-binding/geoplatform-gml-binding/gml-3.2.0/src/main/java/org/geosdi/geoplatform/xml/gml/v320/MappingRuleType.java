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

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
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
 * <p>Classe Java per MappingRuleType complex type.</p>
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.</p>
 * 
 * <pre>{@code
 * <complexType name="MappingRuleType">
 *   <complexContent>
 *     <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       <choice>
 *         <element name="ruleDefinition" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         <element name="ruleReference" type="{http://www.opengis.net/gml}ReferenceType"/>
 *       </choice>
 *     </restriction>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "MappingRuleType", propOrder = {
    "ruleDefinition",
    "ruleReference"
})
public class MappingRuleType implements Cloneable, CopyTo, Equals, HashCode, MergeFrom, ToString
{

    protected String ruleDefinition;
    protected ReferenceType ruleReference;

    /**
     * Recupera il valore della proprietà ruleDefinition.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRuleDefinition() {
        return ruleDefinition;
    }

    /**
     * Imposta il valore della proprietà ruleDefinition.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRuleDefinition(String value) {
        this.ruleDefinition = value;
    }

    public boolean isSetRuleDefinition() {
        return (this.ruleDefinition!= null);
    }

    /**
     * Recupera il valore della proprietà ruleReference.
     * 
     * @return
     *     possible object is
     *     {@link ReferenceType }
     *     
     */
    public ReferenceType getRuleReference() {
        return ruleReference;
    }

    /**
     * Imposta il valore della proprietà ruleReference.
     * 
     * @param value
     *     allowed object is
     *     {@link ReferenceType }
     *     
     */
    public void setRuleReference(ReferenceType value) {
        this.ruleReference = value;
    }

    public boolean isSetRuleReference() {
        return (this.ruleReference!= null);
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
            String theRuleDefinition;
            theRuleDefinition = this.getRuleDefinition();
            strategy.appendField(locator, this, "ruleDefinition", buffer, theRuleDefinition, this.isSetRuleDefinition());
        }
        {
            ReferenceType theRuleReference;
            theRuleReference = this.getRuleReference();
            strategy.appendField(locator, this, "ruleReference", buffer, theRuleReference, this.isSetRuleReference());
        }
        return buffer;
    }

    @Override
    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if ((object == null)||(this.getClass()!= object.getClass())) {
            return false;
        }
        if (this == object) {
            return true;
        }
        final MappingRuleType that = ((MappingRuleType) object);
        {
            String lhsRuleDefinition;
            lhsRuleDefinition = this.getRuleDefinition();
            String rhsRuleDefinition;
            rhsRuleDefinition = that.getRuleDefinition();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "ruleDefinition", lhsRuleDefinition), LocatorUtils.property(thatLocator, "ruleDefinition", rhsRuleDefinition), lhsRuleDefinition, rhsRuleDefinition, this.isSetRuleDefinition(), that.isSetRuleDefinition())) {
                return false;
            }
        }
        {
            ReferenceType lhsRuleReference;
            lhsRuleReference = this.getRuleReference();
            ReferenceType rhsRuleReference;
            rhsRuleReference = that.getRuleReference();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "ruleReference", lhsRuleReference), LocatorUtils.property(thatLocator, "ruleReference", rhsRuleReference), lhsRuleReference, rhsRuleReference, this.isSetRuleReference(), that.isSetRuleReference())) {
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
            String theRuleDefinition;
            theRuleDefinition = this.getRuleDefinition();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "ruleDefinition", theRuleDefinition), currentHashCode, theRuleDefinition, this.isSetRuleDefinition());
        }
        {
            ReferenceType theRuleReference;
            theRuleReference = this.getRuleReference();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "ruleReference", theRuleReference), currentHashCode, theRuleReference, this.isSetRuleReference());
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
        if (draftCopy instanceof MappingRuleType) {
            final MappingRuleType copy = ((MappingRuleType) draftCopy);
            {
                java.lang.Boolean ruleDefinitionShouldBeCopiedAndSet = strategy.shouldBeCopiedAndSet(locator, this.isSetRuleDefinition());
                if (ruleDefinitionShouldBeCopiedAndSet == java.lang.Boolean.TRUE) {
                    String sourceRuleDefinition;
                    sourceRuleDefinition = this.getRuleDefinition();
                    String copyRuleDefinition = ((String) strategy.copy(LocatorUtils.property(locator, "ruleDefinition", sourceRuleDefinition), sourceRuleDefinition, this.isSetRuleDefinition()));
                    copy.setRuleDefinition(copyRuleDefinition);
                } else {
                    if (ruleDefinitionShouldBeCopiedAndSet == java.lang.Boolean.FALSE) {
                        copy.ruleDefinition = null;
                    }
                }
            }
            {
                java.lang.Boolean ruleReferenceShouldBeCopiedAndSet = strategy.shouldBeCopiedAndSet(locator, this.isSetRuleReference());
                if (ruleReferenceShouldBeCopiedAndSet == java.lang.Boolean.TRUE) {
                    ReferenceType sourceRuleReference;
                    sourceRuleReference = this.getRuleReference();
                    ReferenceType copyRuleReference = ((ReferenceType) strategy.copy(LocatorUtils.property(locator, "ruleReference", sourceRuleReference), sourceRuleReference, this.isSetRuleReference()));
                    copy.setRuleReference(copyRuleReference);
                } else {
                    if (ruleReferenceShouldBeCopiedAndSet == java.lang.Boolean.FALSE) {
                        copy.ruleReference = null;
                    }
                }
            }
        }
        return draftCopy;
    }

    @Override
    public Object createNewInstance() {
        return new MappingRuleType();
    }

    @Override
    public void mergeFrom(Object left, Object right) {
        final MergeStrategy strategy = JAXBMergeStrategy.getInstance();
        mergeFrom(null, null, left, right, strategy);
    }

    @Override
    public void mergeFrom(ObjectLocator leftLocator, ObjectLocator rightLocator, Object left, Object right, MergeStrategy strategy) {
        if (right instanceof MappingRuleType) {
            final MappingRuleType target = this;
            final MappingRuleType leftObject = ((MappingRuleType) left);
            final MappingRuleType rightObject = ((MappingRuleType) right);
            {
                java.lang.Boolean ruleDefinitionShouldBeMergedAndSet = strategy.shouldBeMergedAndSet(leftLocator, rightLocator, leftObject.isSetRuleDefinition(), rightObject.isSetRuleDefinition());
                if (ruleDefinitionShouldBeMergedAndSet == java.lang.Boolean.TRUE) {
                    String lhsRuleDefinition;
                    lhsRuleDefinition = leftObject.getRuleDefinition();
                    String rhsRuleDefinition;
                    rhsRuleDefinition = rightObject.getRuleDefinition();
                    String mergedRuleDefinition = ((String) strategy.merge(LocatorUtils.property(leftLocator, "ruleDefinition", lhsRuleDefinition), LocatorUtils.property(rightLocator, "ruleDefinition", rhsRuleDefinition), lhsRuleDefinition, rhsRuleDefinition, leftObject.isSetRuleDefinition(), rightObject.isSetRuleDefinition()));
                    target.setRuleDefinition(mergedRuleDefinition);
                } else {
                    if (ruleDefinitionShouldBeMergedAndSet == java.lang.Boolean.FALSE) {
                        target.ruleDefinition = null;
                    }
                }
            }
            {
                java.lang.Boolean ruleReferenceShouldBeMergedAndSet = strategy.shouldBeMergedAndSet(leftLocator, rightLocator, leftObject.isSetRuleReference(), rightObject.isSetRuleReference());
                if (ruleReferenceShouldBeMergedAndSet == java.lang.Boolean.TRUE) {
                    ReferenceType lhsRuleReference;
                    lhsRuleReference = leftObject.getRuleReference();
                    ReferenceType rhsRuleReference;
                    rhsRuleReference = rightObject.getRuleReference();
                    ReferenceType mergedRuleReference = ((ReferenceType) strategy.merge(LocatorUtils.property(leftLocator, "ruleReference", lhsRuleReference), LocatorUtils.property(rightLocator, "ruleReference", rhsRuleReference), lhsRuleReference, rhsRuleReference, leftObject.isSetRuleReference(), rightObject.isSetRuleReference()));
                    target.setRuleReference(mergedRuleReference);
                } else {
                    if (ruleReferenceShouldBeMergedAndSet == java.lang.Boolean.FALSE) {
                        target.ruleReference = null;
                    }
                }
            }
        }
    }

}
