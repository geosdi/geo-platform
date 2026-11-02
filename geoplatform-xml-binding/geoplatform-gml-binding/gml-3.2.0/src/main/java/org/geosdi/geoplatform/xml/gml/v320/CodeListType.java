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
import jakarta.xml.bind.annotation.XmlSchemaType;
import jakarta.xml.bind.annotation.XmlType;
import jakarta.xml.bind.annotation.XmlValue;
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
 * gml:CodeListType provides for lists of terms. The values in an instance element shall all be valid according to the rules of the dictionary, classification scheme, or authority identified by the value of its codeSpace attribute.
 * 
 * <p>Classe Java per CodeListType complex type.</p>
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.</p>
 * 
 * <pre>{@code
 * <complexType name="CodeListType">
 *   <simpleContent>
 *     <extension base="<http://www.opengis.net/gml>NameList">
 *       <attribute name="codeSpace" type="{http://www.w3.org/2001/XMLSchema}anyURI" />
 *     </extension>
 *   </simpleContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CodeListType", propOrder = {
    "value"
})
public class CodeListType implements Cloneable, CopyTo, Equals, HashCode, MergeFrom, ToString
{

    /**
     * A type for a list of values of the respective simple type.
     * 
     */
    @XmlValue
    protected List<String> value;
    @XmlAttribute(name = "codeSpace")
    @XmlSchemaType(name = "anyURI")
    protected String codeSpace;

    /**
     * A type for a list of values of the respective simple type.
     * 
     * Gets the value of the value property.
     * 
     * <p>This accessor method returns a reference to the live list,
     * not a snapshot. Therefore, any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a {@code set} method for the value property.</p>
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * </p>
     * <pre>
     * getValue().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * </p>
     * 
     * 
     * @return
     *     The value of the value property.
     */
    public List<String> getValue() {
        if (value == null) {
            value = new ArrayList<>();
        }
        return this.value;
    }

    public boolean isSetValue() {
        return ((this.value!= null)&&(!this.value.isEmpty()));
    }

    public void unsetValue() {
        this.value = null;
    }

    /**
     * Recupera il valore della proprietà codeSpace.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodeSpace() {
        return codeSpace;
    }

    /**
     * Imposta il valore della proprietà codeSpace.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodeSpace(String value) {
        this.codeSpace = value;
    }

    public boolean isSetCodeSpace() {
        return (this.codeSpace!= null);
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
            List<String> theValue;
            theValue = (this.isSetValue()?this.getValue():null);
            strategy.appendField(locator, this, "value", buffer, theValue, this.isSetValue());
        }
        {
            String theCodeSpace;
            theCodeSpace = this.getCodeSpace();
            strategy.appendField(locator, this, "codeSpace", buffer, theCodeSpace, this.isSetCodeSpace());
        }
        return buffer;
    }

    public void setValue(List<String> value) {
        this.value = null;
        if (value!= null) {
            List<String> draftl = this.getValue();
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
        final CodeListType that = ((CodeListType) object);
        {
            List<String> lhsValue;
            lhsValue = (this.isSetValue()?this.getValue():null);
            List<String> rhsValue;
            rhsValue = (that.isSetValue()?that.getValue():null);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "value", lhsValue), LocatorUtils.property(thatLocator, "value", rhsValue), lhsValue, rhsValue, this.isSetValue(), that.isSetValue())) {
                return false;
            }
        }
        {
            String lhsCodeSpace;
            lhsCodeSpace = this.getCodeSpace();
            String rhsCodeSpace;
            rhsCodeSpace = that.getCodeSpace();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "codeSpace", lhsCodeSpace), LocatorUtils.property(thatLocator, "codeSpace", rhsCodeSpace), lhsCodeSpace, rhsCodeSpace, this.isSetCodeSpace(), that.isSetCodeSpace())) {
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
            List<String> theValue;
            theValue = (this.isSetValue()?this.getValue():null);
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "value", theValue), currentHashCode, theValue, this.isSetValue());
        }
        {
            String theCodeSpace;
            theCodeSpace = this.getCodeSpace();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "codeSpace", theCodeSpace), currentHashCode, theCodeSpace, this.isSetCodeSpace());
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
        if (draftCopy instanceof CodeListType) {
            final CodeListType copy = ((CodeListType) draftCopy);
            {
                java.lang.Boolean valueShouldBeCopiedAndSet = strategy.shouldBeCopiedAndSet(locator, this.isSetValue());
                if (valueShouldBeCopiedAndSet == java.lang.Boolean.TRUE) {
                    List<String> sourceValue;
                    sourceValue = (this.isSetValue()?this.getValue():null);
                    @SuppressWarnings("unchecked")
                    List<String> copyValue = ((List<String> ) strategy.copy(LocatorUtils.property(locator, "value", sourceValue), sourceValue, this.isSetValue()));
                    copy.setValue(copyValue);
                } else {
                    if (valueShouldBeCopiedAndSet == java.lang.Boolean.FALSE) {
                        copy.unsetValue();
                    }
                }
            }
            {
                java.lang.Boolean codeSpaceShouldBeCopiedAndSet = strategy.shouldBeCopiedAndSet(locator, this.isSetCodeSpace());
                if (codeSpaceShouldBeCopiedAndSet == java.lang.Boolean.TRUE) {
                    String sourceCodeSpace;
                    sourceCodeSpace = this.getCodeSpace();
                    String copyCodeSpace = ((String) strategy.copy(LocatorUtils.property(locator, "codeSpace", sourceCodeSpace), sourceCodeSpace, this.isSetCodeSpace()));
                    copy.setCodeSpace(copyCodeSpace);
                } else {
                    if (codeSpaceShouldBeCopiedAndSet == java.lang.Boolean.FALSE) {
                        copy.codeSpace = null;
                    }
                }
            }
        }
        return draftCopy;
    }

    @Override
    public Object createNewInstance() {
        return new CodeListType();
    }

    @Override
    public void mergeFrom(Object left, Object right) {
        final MergeStrategy strategy = JAXBMergeStrategy.getInstance();
        mergeFrom(null, null, left, right, strategy);
    }

    @Override
    public void mergeFrom(ObjectLocator leftLocator, ObjectLocator rightLocator, Object left, Object right, MergeStrategy strategy) {
        if (right instanceof CodeListType) {
            final CodeListType target = this;
            final CodeListType leftObject = ((CodeListType) left);
            final CodeListType rightObject = ((CodeListType) right);
            {
                java.lang.Boolean valueShouldBeMergedAndSet = strategy.shouldBeMergedAndSet(leftLocator, rightLocator, leftObject.isSetValue(), rightObject.isSetValue());
                if (valueShouldBeMergedAndSet == java.lang.Boolean.TRUE) {
                    List<String> lhsValue;
                    lhsValue = (leftObject.isSetValue()?leftObject.getValue():null);
                    List<String> rhsValue;
                    rhsValue = (rightObject.isSetValue()?rightObject.getValue():null);
                    List<String> mergedValue = ((List<String> ) strategy.merge(LocatorUtils.property(leftLocator, "value", lhsValue), LocatorUtils.property(rightLocator, "value", rhsValue), lhsValue, rhsValue, leftObject.isSetValue(), rightObject.isSetValue()));
                    target.setValue(mergedValue);
                } else {
                    if (valueShouldBeMergedAndSet == java.lang.Boolean.FALSE) {
                        target.unsetValue();
                    }
                }
            }
            {
                java.lang.Boolean codeSpaceShouldBeMergedAndSet = strategy.shouldBeMergedAndSet(leftLocator, rightLocator, leftObject.isSetCodeSpace(), rightObject.isSetCodeSpace());
                if (codeSpaceShouldBeMergedAndSet == java.lang.Boolean.TRUE) {
                    String lhsCodeSpace;
                    lhsCodeSpace = leftObject.getCodeSpace();
                    String rhsCodeSpace;
                    rhsCodeSpace = rightObject.getCodeSpace();
                    String mergedCodeSpace = ((String) strategy.merge(LocatorUtils.property(leftLocator, "codeSpace", lhsCodeSpace), LocatorUtils.property(rightLocator, "codeSpace", rhsCodeSpace), lhsCodeSpace, rhsCodeSpace, leftObject.isSetCodeSpace(), rightObject.isSetCodeSpace()));
                    target.setCodeSpace(mergedCodeSpace);
                } else {
                    if (codeSpaceShouldBeMergedAndSet == java.lang.Boolean.FALSE) {
                        target.codeSpace = null;
                    }
                }
            }
        }
    }

}
