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

import jakarta.xml.bind.JAXBElement;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElementRef;
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
 * <p>Classe Java per AbstractGeneralDerivedCRSType complex type.</p>
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.</p>
 * 
 * <pre>{@code
 * <complexType name="AbstractGeneralDerivedCRSType">
 *   <complexContent>
 *     <extension base="{http://www.opengis.net/gml}AbstractCRSType">
 *       <sequence>
 *         <element ref="{http://www.opengis.net/gml}conversion"/>
 *       </sequence>
 *     </extension>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AbstractGeneralDerivedCRSType", propOrder = {
    "conversion"
})
@XmlSeeAlso({
    ProjectedCRSType.class,
    DerivedCRSType.class
})
public abstract class AbstractGeneralDerivedCRSType
    extends AbstractCRSType
    implements Cloneable, CopyTo, Equals, HashCode, MergeFrom, ToString
{

    /**
     * gml:conversion is an association role to the coordinate conversion used to define the derived CRS.
     * 
     */
    @XmlElementRef(name = "conversion", namespace = "http://www.opengis.net/gml", type = JAXBElement.class)
    protected JAXBElement<GeneralConversionPropertyType> conversion;

    /**
     * gml:conversion is an association role to the coordinate conversion used to define the derived CRS.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link GeneralConversionPropertyType }{@code >}
     *     {@link JAXBElement }{@code <}{@link GeneralConversionPropertyType }{@code >}
     *     
     */
    public JAXBElement<GeneralConversionPropertyType> getConversion() {
        return conversion;
    }

    /**
     * Imposta il valore della proprietà conversion.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link GeneralConversionPropertyType }{@code >}
     *     {@link JAXBElement }{@code <}{@link GeneralConversionPropertyType }{@code >}
     *     
     * @see #getConversion()
     */
    public void setConversion(JAXBElement<GeneralConversionPropertyType> value) {
        this.conversion = value;
    }

    public boolean isSetConversion() {
        return (this.conversion!= null);
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
            JAXBElement<GeneralConversionPropertyType> theConversion;
            theConversion = this.getConversion();
            strategy.appendField(locator, this, "conversion", buffer, theConversion, this.isSetConversion());
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
        final AbstractGeneralDerivedCRSType that = ((AbstractGeneralDerivedCRSType) object);
        {
            JAXBElement<GeneralConversionPropertyType> lhsConversion;
            lhsConversion = this.getConversion();
            JAXBElement<GeneralConversionPropertyType> rhsConversion;
            rhsConversion = that.getConversion();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "conversion", lhsConversion), LocatorUtils.property(thatLocator, "conversion", rhsConversion), lhsConversion, rhsConversion, this.isSetConversion(), that.isSetConversion())) {
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
            JAXBElement<GeneralConversionPropertyType> theConversion;
            theConversion = this.getConversion();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "conversion", theConversion), currentHashCode, theConversion, this.isSetConversion());
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
        if (target instanceof AbstractGeneralDerivedCRSType) {
            final AbstractGeneralDerivedCRSType copy = ((AbstractGeneralDerivedCRSType) target);
            {
                java.lang.Boolean conversionShouldBeCopiedAndSet = strategy.shouldBeCopiedAndSet(locator, this.isSetConversion());
                if (conversionShouldBeCopiedAndSet == java.lang.Boolean.TRUE) {
                    JAXBElement<GeneralConversionPropertyType> sourceConversion;
                    sourceConversion = this.getConversion();
                    @SuppressWarnings("unchecked")
                    JAXBElement<GeneralConversionPropertyType> copyConversion = ((JAXBElement<GeneralConversionPropertyType> ) strategy.copy(LocatorUtils.property(locator, "conversion", sourceConversion), sourceConversion, this.isSetConversion()));
                    copy.setConversion(copyConversion);
                } else {
                    if (conversionShouldBeCopiedAndSet == java.lang.Boolean.FALSE) {
                        copy.conversion = null;
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
        if (right instanceof AbstractGeneralDerivedCRSType) {
            final AbstractGeneralDerivedCRSType target = this;
            final AbstractGeneralDerivedCRSType leftObject = ((AbstractGeneralDerivedCRSType) left);
            final AbstractGeneralDerivedCRSType rightObject = ((AbstractGeneralDerivedCRSType) right);
            {
                java.lang.Boolean conversionShouldBeMergedAndSet = strategy.shouldBeMergedAndSet(leftLocator, rightLocator, leftObject.isSetConversion(), rightObject.isSetConversion());
                if (conversionShouldBeMergedAndSet == java.lang.Boolean.TRUE) {
                    JAXBElement<GeneralConversionPropertyType> lhsConversion;
                    lhsConversion = leftObject.getConversion();
                    JAXBElement<GeneralConversionPropertyType> rhsConversion;
                    rhsConversion = rightObject.getConversion();
                    JAXBElement<GeneralConversionPropertyType> mergedConversion = ((JAXBElement<GeneralConversionPropertyType> ) strategy.merge(LocatorUtils.property(leftLocator, "conversion", lhsConversion), LocatorUtils.property(rightLocator, "conversion", rhsConversion), lhsConversion, rhsConversion, leftObject.isSetConversion(), rightObject.isSetConversion()));
                    target.setConversion(mergedConversion);
                } else {
                    if (conversionShouldBeMergedAndSet == java.lang.Boolean.FALSE) {
                        target.conversion = null;
                    }
                }
            }
        }
    }

}
