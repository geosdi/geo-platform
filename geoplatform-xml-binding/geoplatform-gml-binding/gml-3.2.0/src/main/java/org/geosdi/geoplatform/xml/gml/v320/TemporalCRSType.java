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
 * <p>Classe Java per TemporalCRSType complex type.</p>
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.</p>
 * 
 * <pre>{@code
 * <complexType name="TemporalCRSType">
 *   <complexContent>
 *     <extension base="{http://www.opengis.net/gml}AbstractCRSType">
 *       <sequence>
 *         <choice>
 *           <element ref="{http://www.opengis.net/gml}timeCS"/>
 *           <element ref="{http://www.opengis.net/gml}usesTemporalCS"/>
 *         </choice>
 *         <element ref="{http://www.opengis.net/gml}temporalDatum"/>
 *       </sequence>
 *     </extension>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TemporalCRSType", propOrder = {
    "timeCS",
    "usesTemporalCS",
    "temporalDatum"
})
public class TemporalCRSType
    extends AbstractCRSType
    implements Cloneable, CopyTo, Equals, HashCode, MergeFrom, ToString
{

    /**
     * gml:timeCS is an association role to the time coordinate system used by this CRS.
     * 
     */
    @XmlElementRef(name = "timeCS", namespace = "http://www.opengis.net/gml", type = JAXBElement.class, required = false)
    protected JAXBElement<TimeCSPropertyType> timeCS;
    protected TemporalCSPropertyType usesTemporalCS;
    /**
     * gml:temporalDatum is an association role to the temporal datum used by this CRS.
     * 
     */
    @XmlElementRef(name = "temporalDatum", namespace = "http://www.opengis.net/gml", type = JAXBElement.class)
    protected JAXBElement<TemporalDatumPropertyType> temporalDatum;

    /**
     * gml:timeCS is an association role to the time coordinate system used by this CRS.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link TimeCSPropertyType }{@code >}
     *     {@link JAXBElement }{@code <}{@link TimeCSPropertyType }{@code >}
     *     
     */
    public JAXBElement<TimeCSPropertyType> getTimeCS() {
        return timeCS;
    }

    /**
     * Imposta il valore della proprietà timeCS.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link TimeCSPropertyType }{@code >}
     *     {@link JAXBElement }{@code <}{@link TimeCSPropertyType }{@code >}
     *     
     * @see #getTimeCS()
     */
    public void setTimeCS(JAXBElement<TimeCSPropertyType> value) {
        this.timeCS = value;
    }

    public boolean isSetTimeCS() {
        return (this.timeCS!= null);
    }

    /**
     * Recupera il valore della proprietà usesTemporalCS.
     * 
     * @return
     *     possible object is
     *     {@link TemporalCSPropertyType }
     *     
     */
    public TemporalCSPropertyType getUsesTemporalCS() {
        return usesTemporalCS;
    }

    /**
     * Imposta il valore della proprietà usesTemporalCS.
     * 
     * @param value
     *     allowed object is
     *     {@link TemporalCSPropertyType }
     *     
     */
    public void setUsesTemporalCS(TemporalCSPropertyType value) {
        this.usesTemporalCS = value;
    }

    public boolean isSetUsesTemporalCS() {
        return (this.usesTemporalCS!= null);
    }

    /**
     * gml:temporalDatum is an association role to the temporal datum used by this CRS.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link TemporalDatumPropertyType }{@code >}
     *     {@link JAXBElement }{@code <}{@link TemporalDatumPropertyType }{@code >}
     *     
     */
    public JAXBElement<TemporalDatumPropertyType> getTemporalDatum() {
        return temporalDatum;
    }

    /**
     * Imposta il valore della proprietà temporalDatum.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link TemporalDatumPropertyType }{@code >}
     *     {@link JAXBElement }{@code <}{@link TemporalDatumPropertyType }{@code >}
     *     
     * @see #getTemporalDatum()
     */
    public void setTemporalDatum(JAXBElement<TemporalDatumPropertyType> value) {
        this.temporalDatum = value;
    }

    public boolean isSetTemporalDatum() {
        return (this.temporalDatum!= null);
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
            JAXBElement<TimeCSPropertyType> theTimeCS;
            theTimeCS = this.getTimeCS();
            strategy.appendField(locator, this, "timeCS", buffer, theTimeCS, this.isSetTimeCS());
        }
        {
            TemporalCSPropertyType theUsesTemporalCS;
            theUsesTemporalCS = this.getUsesTemporalCS();
            strategy.appendField(locator, this, "usesTemporalCS", buffer, theUsesTemporalCS, this.isSetUsesTemporalCS());
        }
        {
            JAXBElement<TemporalDatumPropertyType> theTemporalDatum;
            theTemporalDatum = this.getTemporalDatum();
            strategy.appendField(locator, this, "temporalDatum", buffer, theTemporalDatum, this.isSetTemporalDatum());
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
        final TemporalCRSType that = ((TemporalCRSType) object);
        {
            JAXBElement<TimeCSPropertyType> lhsTimeCS;
            lhsTimeCS = this.getTimeCS();
            JAXBElement<TimeCSPropertyType> rhsTimeCS;
            rhsTimeCS = that.getTimeCS();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "timeCS", lhsTimeCS), LocatorUtils.property(thatLocator, "timeCS", rhsTimeCS), lhsTimeCS, rhsTimeCS, this.isSetTimeCS(), that.isSetTimeCS())) {
                return false;
            }
        }
        {
            TemporalCSPropertyType lhsUsesTemporalCS;
            lhsUsesTemporalCS = this.getUsesTemporalCS();
            TemporalCSPropertyType rhsUsesTemporalCS;
            rhsUsesTemporalCS = that.getUsesTemporalCS();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "usesTemporalCS", lhsUsesTemporalCS), LocatorUtils.property(thatLocator, "usesTemporalCS", rhsUsesTemporalCS), lhsUsesTemporalCS, rhsUsesTemporalCS, this.isSetUsesTemporalCS(), that.isSetUsesTemporalCS())) {
                return false;
            }
        }
        {
            JAXBElement<TemporalDatumPropertyType> lhsTemporalDatum;
            lhsTemporalDatum = this.getTemporalDatum();
            JAXBElement<TemporalDatumPropertyType> rhsTemporalDatum;
            rhsTemporalDatum = that.getTemporalDatum();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "temporalDatum", lhsTemporalDatum), LocatorUtils.property(thatLocator, "temporalDatum", rhsTemporalDatum), lhsTemporalDatum, rhsTemporalDatum, this.isSetTemporalDatum(), that.isSetTemporalDatum())) {
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
            JAXBElement<TimeCSPropertyType> theTimeCS;
            theTimeCS = this.getTimeCS();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "timeCS", theTimeCS), currentHashCode, theTimeCS, this.isSetTimeCS());
        }
        {
            TemporalCSPropertyType theUsesTemporalCS;
            theUsesTemporalCS = this.getUsesTemporalCS();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "usesTemporalCS", theUsesTemporalCS), currentHashCode, theUsesTemporalCS, this.isSetUsesTemporalCS());
        }
        {
            JAXBElement<TemporalDatumPropertyType> theTemporalDatum;
            theTemporalDatum = this.getTemporalDatum();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "temporalDatum", theTemporalDatum), currentHashCode, theTemporalDatum, this.isSetTemporalDatum());
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
        if (draftCopy instanceof TemporalCRSType) {
            final TemporalCRSType copy = ((TemporalCRSType) draftCopy);
            {
                java.lang.Boolean timeCSShouldBeCopiedAndSet = strategy.shouldBeCopiedAndSet(locator, this.isSetTimeCS());
                if (timeCSShouldBeCopiedAndSet == java.lang.Boolean.TRUE) {
                    JAXBElement<TimeCSPropertyType> sourceTimeCS;
                    sourceTimeCS = this.getTimeCS();
                    @SuppressWarnings("unchecked")
                    JAXBElement<TimeCSPropertyType> copyTimeCS = ((JAXBElement<TimeCSPropertyType> ) strategy.copy(LocatorUtils.property(locator, "timeCS", sourceTimeCS), sourceTimeCS, this.isSetTimeCS()));
                    copy.setTimeCS(copyTimeCS);
                } else {
                    if (timeCSShouldBeCopiedAndSet == java.lang.Boolean.FALSE) {
                        copy.timeCS = null;
                    }
                }
            }
            {
                java.lang.Boolean usesTemporalCSShouldBeCopiedAndSet = strategy.shouldBeCopiedAndSet(locator, this.isSetUsesTemporalCS());
                if (usesTemporalCSShouldBeCopiedAndSet == java.lang.Boolean.TRUE) {
                    TemporalCSPropertyType sourceUsesTemporalCS;
                    sourceUsesTemporalCS = this.getUsesTemporalCS();
                    TemporalCSPropertyType copyUsesTemporalCS = ((TemporalCSPropertyType) strategy.copy(LocatorUtils.property(locator, "usesTemporalCS", sourceUsesTemporalCS), sourceUsesTemporalCS, this.isSetUsesTemporalCS()));
                    copy.setUsesTemporalCS(copyUsesTemporalCS);
                } else {
                    if (usesTemporalCSShouldBeCopiedAndSet == java.lang.Boolean.FALSE) {
                        copy.usesTemporalCS = null;
                    }
                }
            }
            {
                java.lang.Boolean temporalDatumShouldBeCopiedAndSet = strategy.shouldBeCopiedAndSet(locator, this.isSetTemporalDatum());
                if (temporalDatumShouldBeCopiedAndSet == java.lang.Boolean.TRUE) {
                    JAXBElement<TemporalDatumPropertyType> sourceTemporalDatum;
                    sourceTemporalDatum = this.getTemporalDatum();
                    @SuppressWarnings("unchecked")
                    JAXBElement<TemporalDatumPropertyType> copyTemporalDatum = ((JAXBElement<TemporalDatumPropertyType> ) strategy.copy(LocatorUtils.property(locator, "temporalDatum", sourceTemporalDatum), sourceTemporalDatum, this.isSetTemporalDatum()));
                    copy.setTemporalDatum(copyTemporalDatum);
                } else {
                    if (temporalDatumShouldBeCopiedAndSet == java.lang.Boolean.FALSE) {
                        copy.temporalDatum = null;
                    }
                }
            }
        }
        return draftCopy;
    }

    @Override
    public Object createNewInstance() {
        return new TemporalCRSType();
    }

    @Override
    public void mergeFrom(Object left, Object right) {
        final MergeStrategy strategy = JAXBMergeStrategy.getInstance();
        mergeFrom(null, null, left, right, strategy);
    }

    @Override
    public void mergeFrom(ObjectLocator leftLocator, ObjectLocator rightLocator, Object left, Object right, MergeStrategy strategy) {
        super.mergeFrom(leftLocator, rightLocator, left, right, strategy);
        if (right instanceof TemporalCRSType) {
            final TemporalCRSType target = this;
            final TemporalCRSType leftObject = ((TemporalCRSType) left);
            final TemporalCRSType rightObject = ((TemporalCRSType) right);
            {
                java.lang.Boolean timeCSShouldBeMergedAndSet = strategy.shouldBeMergedAndSet(leftLocator, rightLocator, leftObject.isSetTimeCS(), rightObject.isSetTimeCS());
                if (timeCSShouldBeMergedAndSet == java.lang.Boolean.TRUE) {
                    JAXBElement<TimeCSPropertyType> lhsTimeCS;
                    lhsTimeCS = leftObject.getTimeCS();
                    JAXBElement<TimeCSPropertyType> rhsTimeCS;
                    rhsTimeCS = rightObject.getTimeCS();
                    JAXBElement<TimeCSPropertyType> mergedTimeCS = ((JAXBElement<TimeCSPropertyType> ) strategy.merge(LocatorUtils.property(leftLocator, "timeCS", lhsTimeCS), LocatorUtils.property(rightLocator, "timeCS", rhsTimeCS), lhsTimeCS, rhsTimeCS, leftObject.isSetTimeCS(), rightObject.isSetTimeCS()));
                    target.setTimeCS(mergedTimeCS);
                } else {
                    if (timeCSShouldBeMergedAndSet == java.lang.Boolean.FALSE) {
                        target.timeCS = null;
                    }
                }
            }
            {
                java.lang.Boolean usesTemporalCSShouldBeMergedAndSet = strategy.shouldBeMergedAndSet(leftLocator, rightLocator, leftObject.isSetUsesTemporalCS(), rightObject.isSetUsesTemporalCS());
                if (usesTemporalCSShouldBeMergedAndSet == java.lang.Boolean.TRUE) {
                    TemporalCSPropertyType lhsUsesTemporalCS;
                    lhsUsesTemporalCS = leftObject.getUsesTemporalCS();
                    TemporalCSPropertyType rhsUsesTemporalCS;
                    rhsUsesTemporalCS = rightObject.getUsesTemporalCS();
                    TemporalCSPropertyType mergedUsesTemporalCS = ((TemporalCSPropertyType) strategy.merge(LocatorUtils.property(leftLocator, "usesTemporalCS", lhsUsesTemporalCS), LocatorUtils.property(rightLocator, "usesTemporalCS", rhsUsesTemporalCS), lhsUsesTemporalCS, rhsUsesTemporalCS, leftObject.isSetUsesTemporalCS(), rightObject.isSetUsesTemporalCS()));
                    target.setUsesTemporalCS(mergedUsesTemporalCS);
                } else {
                    if (usesTemporalCSShouldBeMergedAndSet == java.lang.Boolean.FALSE) {
                        target.usesTemporalCS = null;
                    }
                }
            }
            {
                java.lang.Boolean temporalDatumShouldBeMergedAndSet = strategy.shouldBeMergedAndSet(leftLocator, rightLocator, leftObject.isSetTemporalDatum(), rightObject.isSetTemporalDatum());
                if (temporalDatumShouldBeMergedAndSet == java.lang.Boolean.TRUE) {
                    JAXBElement<TemporalDatumPropertyType> lhsTemporalDatum;
                    lhsTemporalDatum = leftObject.getTemporalDatum();
                    JAXBElement<TemporalDatumPropertyType> rhsTemporalDatum;
                    rhsTemporalDatum = rightObject.getTemporalDatum();
                    JAXBElement<TemporalDatumPropertyType> mergedTemporalDatum = ((JAXBElement<TemporalDatumPropertyType> ) strategy.merge(LocatorUtils.property(leftLocator, "temporalDatum", lhsTemporalDatum), LocatorUtils.property(rightLocator, "temporalDatum", rhsTemporalDatum), lhsTemporalDatum, rhsTemporalDatum, leftObject.isSetTemporalDatum(), rightObject.isSetTemporalDatum()));
                    target.setTemporalDatum(mergedTemporalDatum);
                } else {
                    if (temporalDatumShouldBeMergedAndSet == java.lang.Boolean.FALSE) {
                        target.temporalDatum = null;
                    }
                }
            }
        }
    }

}
