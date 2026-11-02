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
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlSchemaType;
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
 * <p>Classe Java per AbstractGeneralOperationParameterType complex type.</p>
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.</p>
 * 
 * <pre>{@code
 * <complexType name="AbstractGeneralOperationParameterType">
 *   <complexContent>
 *     <extension base="{http://www.opengis.net/gml}IdentifiedObjectType">
 *       <sequence>
 *         <element ref="{http://www.opengis.net/gml}minimumOccurs" minOccurs="0"/>
 *       </sequence>
 *     </extension>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AbstractGeneralOperationParameterType", propOrder = {
    "minimumOccurs"
})
@XmlSeeAlso({
    OperationParameterType.class,
    OperationParameterGroupType.class
})
public abstract class AbstractGeneralOperationParameterType
    extends IdentifiedObjectType
    implements Cloneable, CopyTo, Equals, HashCode, MergeFrom, ToString
{

    /**
     * gml:minimumOccurs is the minimum number of times that values for this parameter group or parameter are required. If this attribute is omitted, the minimum number shall be one.
     * 
     */
    @XmlSchemaType(name = "nonNegativeInteger")
    protected BigInteger minimumOccurs;

    /**
     * gml:minimumOccurs is the minimum number of times that values for this parameter group or parameter are required. If this attribute is omitted, the minimum number shall be one.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getMinimumOccurs() {
        return minimumOccurs;
    }

    /**
     * Imposta il valore della proprietà minimumOccurs.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     * @see #getMinimumOccurs()
     */
    public void setMinimumOccurs(BigInteger value) {
        this.minimumOccurs = value;
    }

    public boolean isSetMinimumOccurs() {
        return (this.minimumOccurs!= null);
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
            BigInteger theMinimumOccurs;
            theMinimumOccurs = this.getMinimumOccurs();
            strategy.appendField(locator, this, "minimumOccurs", buffer, theMinimumOccurs, this.isSetMinimumOccurs());
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
        if (!super.equals(thisLocator, thatLocator, object, strategy)) {
            return false;
        }
        final AbstractGeneralOperationParameterType that = ((AbstractGeneralOperationParameterType) object);
        {
            BigInteger lhsMinimumOccurs;
            lhsMinimumOccurs = this.getMinimumOccurs();
            BigInteger rhsMinimumOccurs;
            rhsMinimumOccurs = that.getMinimumOccurs();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "minimumOccurs", lhsMinimumOccurs), LocatorUtils.property(thatLocator, "minimumOccurs", rhsMinimumOccurs), lhsMinimumOccurs, rhsMinimumOccurs, this.isSetMinimumOccurs(), that.isSetMinimumOccurs())) {
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
            BigInteger theMinimumOccurs;
            theMinimumOccurs = this.getMinimumOccurs();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "minimumOccurs", theMinimumOccurs), currentHashCode, theMinimumOccurs, this.isSetMinimumOccurs());
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
        if (target instanceof AbstractGeneralOperationParameterType) {
            final AbstractGeneralOperationParameterType copy = ((AbstractGeneralOperationParameterType) target);
            {
                java.lang.Boolean minimumOccursShouldBeCopiedAndSet = strategy.shouldBeCopiedAndSet(locator, this.isSetMinimumOccurs());
                if (minimumOccursShouldBeCopiedAndSet == java.lang.Boolean.TRUE) {
                    BigInteger sourceMinimumOccurs;
                    sourceMinimumOccurs = this.getMinimumOccurs();
                    BigInteger copyMinimumOccurs = ((BigInteger) strategy.copy(LocatorUtils.property(locator, "minimumOccurs", sourceMinimumOccurs), sourceMinimumOccurs, this.isSetMinimumOccurs()));
                    copy.setMinimumOccurs(copyMinimumOccurs);
                } else {
                    if (minimumOccursShouldBeCopiedAndSet == java.lang.Boolean.FALSE) {
                        copy.minimumOccurs = null;
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
        if (right instanceof AbstractGeneralOperationParameterType) {
            final AbstractGeneralOperationParameterType target = this;
            final AbstractGeneralOperationParameterType leftObject = ((AbstractGeneralOperationParameterType) left);
            final AbstractGeneralOperationParameterType rightObject = ((AbstractGeneralOperationParameterType) right);
            {
                java.lang.Boolean minimumOccursShouldBeMergedAndSet = strategy.shouldBeMergedAndSet(leftLocator, rightLocator, leftObject.isSetMinimumOccurs(), rightObject.isSetMinimumOccurs());
                if (minimumOccursShouldBeMergedAndSet == java.lang.Boolean.TRUE) {
                    BigInteger lhsMinimumOccurs;
                    lhsMinimumOccurs = leftObject.getMinimumOccurs();
                    BigInteger rhsMinimumOccurs;
                    rhsMinimumOccurs = rightObject.getMinimumOccurs();
                    BigInteger mergedMinimumOccurs = ((BigInteger) strategy.merge(LocatorUtils.property(leftLocator, "minimumOccurs", lhsMinimumOccurs), LocatorUtils.property(rightLocator, "minimumOccurs", rhsMinimumOccurs), lhsMinimumOccurs, rhsMinimumOccurs, leftObject.isSetMinimumOccurs(), rightObject.isSetMinimumOccurs()));
                    target.setMinimumOccurs(mergedMinimumOccurs);
                } else {
                    if (minimumOccursShouldBeMergedAndSet == java.lang.Boolean.FALSE) {
                        target.minimumOccurs = null;
                    }
                }
            }
        }
    }

}
