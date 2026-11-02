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
 * <p>Classe Java per VerticalCRSType complex type.</p>
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.</p>
 * 
 * <pre>{@code
 * <complexType name="VerticalCRSType">
 *   <complexContent>
 *     <extension base="{http://www.opengis.net/gml}AbstractCRSType">
 *       <sequence>
 *         <element ref="{http://www.opengis.net/gml}verticalCS"/>
 *         <element ref="{http://www.opengis.net/gml}verticalDatum"/>
 *       </sequence>
 *     </extension>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "VerticalCRSType", propOrder = {
    "verticalCS",
    "verticalDatum"
})
public class VerticalCRSType
    extends AbstractCRSType
    implements Cloneable, CopyTo, Equals, HashCode, MergeFrom, ToString
{

    /**
     * gml:verticalCS is an association role to the vertical coordinate system used by this CRS.
     * 
     */
    @XmlElementRef(name = "verticalCS", namespace = "http://www.opengis.net/gml", type = JAXBElement.class)
    protected JAXBElement<VerticalCSPropertyType> verticalCS;
    /**
     * gml:verticalDatum is an association role to the vertical datum used by this CRS.
     * 
     */
    @XmlElementRef(name = "verticalDatum", namespace = "http://www.opengis.net/gml", type = JAXBElement.class)
    protected JAXBElement<VerticalDatumPropertyType> verticalDatum;

    /**
     * gml:verticalCS is an association role to the vertical coordinate system used by this CRS.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link VerticalCSPropertyType }{@code >}
     *     {@link JAXBElement }{@code <}{@link VerticalCSPropertyType }{@code >}
     *     
     */
    public JAXBElement<VerticalCSPropertyType> getVerticalCS() {
        return verticalCS;
    }

    /**
     * Imposta il valore della proprietà verticalCS.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link VerticalCSPropertyType }{@code >}
     *     {@link JAXBElement }{@code <}{@link VerticalCSPropertyType }{@code >}
     *     
     * @see #getVerticalCS()
     */
    public void setVerticalCS(JAXBElement<VerticalCSPropertyType> value) {
        this.verticalCS = value;
    }

    public boolean isSetVerticalCS() {
        return (this.verticalCS!= null);
    }

    /**
     * gml:verticalDatum is an association role to the vertical datum used by this CRS.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link VerticalDatumPropertyType }{@code >}
     *     {@link JAXBElement }{@code <}{@link VerticalDatumPropertyType }{@code >}
     *     
     */
    public JAXBElement<VerticalDatumPropertyType> getVerticalDatum() {
        return verticalDatum;
    }

    /**
     * Imposta il valore della proprietà verticalDatum.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link VerticalDatumPropertyType }{@code >}
     *     {@link JAXBElement }{@code <}{@link VerticalDatumPropertyType }{@code >}
     *     
     * @see #getVerticalDatum()
     */
    public void setVerticalDatum(JAXBElement<VerticalDatumPropertyType> value) {
        this.verticalDatum = value;
    }

    public boolean isSetVerticalDatum() {
        return (this.verticalDatum!= null);
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
            JAXBElement<VerticalCSPropertyType> theVerticalCS;
            theVerticalCS = this.getVerticalCS();
            strategy.appendField(locator, this, "verticalCS", buffer, theVerticalCS, this.isSetVerticalCS());
        }
        {
            JAXBElement<VerticalDatumPropertyType> theVerticalDatum;
            theVerticalDatum = this.getVerticalDatum();
            strategy.appendField(locator, this, "verticalDatum", buffer, theVerticalDatum, this.isSetVerticalDatum());
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
        final VerticalCRSType that = ((VerticalCRSType) object);
        {
            JAXBElement<VerticalCSPropertyType> lhsVerticalCS;
            lhsVerticalCS = this.getVerticalCS();
            JAXBElement<VerticalCSPropertyType> rhsVerticalCS;
            rhsVerticalCS = that.getVerticalCS();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "verticalCS", lhsVerticalCS), LocatorUtils.property(thatLocator, "verticalCS", rhsVerticalCS), lhsVerticalCS, rhsVerticalCS, this.isSetVerticalCS(), that.isSetVerticalCS())) {
                return false;
            }
        }
        {
            JAXBElement<VerticalDatumPropertyType> lhsVerticalDatum;
            lhsVerticalDatum = this.getVerticalDatum();
            JAXBElement<VerticalDatumPropertyType> rhsVerticalDatum;
            rhsVerticalDatum = that.getVerticalDatum();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "verticalDatum", lhsVerticalDatum), LocatorUtils.property(thatLocator, "verticalDatum", rhsVerticalDatum), lhsVerticalDatum, rhsVerticalDatum, this.isSetVerticalDatum(), that.isSetVerticalDatum())) {
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
            JAXBElement<VerticalCSPropertyType> theVerticalCS;
            theVerticalCS = this.getVerticalCS();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "verticalCS", theVerticalCS), currentHashCode, theVerticalCS, this.isSetVerticalCS());
        }
        {
            JAXBElement<VerticalDatumPropertyType> theVerticalDatum;
            theVerticalDatum = this.getVerticalDatum();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "verticalDatum", theVerticalDatum), currentHashCode, theVerticalDatum, this.isSetVerticalDatum());
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
        if (draftCopy instanceof VerticalCRSType) {
            final VerticalCRSType copy = ((VerticalCRSType) draftCopy);
            {
                java.lang.Boolean verticalCSShouldBeCopiedAndSet = strategy.shouldBeCopiedAndSet(locator, this.isSetVerticalCS());
                if (verticalCSShouldBeCopiedAndSet == java.lang.Boolean.TRUE) {
                    JAXBElement<VerticalCSPropertyType> sourceVerticalCS;
                    sourceVerticalCS = this.getVerticalCS();
                    @SuppressWarnings("unchecked")
                    JAXBElement<VerticalCSPropertyType> copyVerticalCS = ((JAXBElement<VerticalCSPropertyType> ) strategy.copy(LocatorUtils.property(locator, "verticalCS", sourceVerticalCS), sourceVerticalCS, this.isSetVerticalCS()));
                    copy.setVerticalCS(copyVerticalCS);
                } else {
                    if (verticalCSShouldBeCopiedAndSet == java.lang.Boolean.FALSE) {
                        copy.verticalCS = null;
                    }
                }
            }
            {
                java.lang.Boolean verticalDatumShouldBeCopiedAndSet = strategy.shouldBeCopiedAndSet(locator, this.isSetVerticalDatum());
                if (verticalDatumShouldBeCopiedAndSet == java.lang.Boolean.TRUE) {
                    JAXBElement<VerticalDatumPropertyType> sourceVerticalDatum;
                    sourceVerticalDatum = this.getVerticalDatum();
                    @SuppressWarnings("unchecked")
                    JAXBElement<VerticalDatumPropertyType> copyVerticalDatum = ((JAXBElement<VerticalDatumPropertyType> ) strategy.copy(LocatorUtils.property(locator, "verticalDatum", sourceVerticalDatum), sourceVerticalDatum, this.isSetVerticalDatum()));
                    copy.setVerticalDatum(copyVerticalDatum);
                } else {
                    if (verticalDatumShouldBeCopiedAndSet == java.lang.Boolean.FALSE) {
                        copy.verticalDatum = null;
                    }
                }
            }
        }
        return draftCopy;
    }

    @Override
    public Object createNewInstance() {
        return new VerticalCRSType();
    }

    @Override
    public void mergeFrom(Object left, Object right) {
        final MergeStrategy strategy = JAXBMergeStrategy.getInstance();
        mergeFrom(null, null, left, right, strategy);
    }

    @Override
    public void mergeFrom(ObjectLocator leftLocator, ObjectLocator rightLocator, Object left, Object right, MergeStrategy strategy) {
        super.mergeFrom(leftLocator, rightLocator, left, right, strategy);
        if (right instanceof VerticalCRSType) {
            final VerticalCRSType target = this;
            final VerticalCRSType leftObject = ((VerticalCRSType) left);
            final VerticalCRSType rightObject = ((VerticalCRSType) right);
            {
                java.lang.Boolean verticalCSShouldBeMergedAndSet = strategy.shouldBeMergedAndSet(leftLocator, rightLocator, leftObject.isSetVerticalCS(), rightObject.isSetVerticalCS());
                if (verticalCSShouldBeMergedAndSet == java.lang.Boolean.TRUE) {
                    JAXBElement<VerticalCSPropertyType> lhsVerticalCS;
                    lhsVerticalCS = leftObject.getVerticalCS();
                    JAXBElement<VerticalCSPropertyType> rhsVerticalCS;
                    rhsVerticalCS = rightObject.getVerticalCS();
                    JAXBElement<VerticalCSPropertyType> mergedVerticalCS = ((JAXBElement<VerticalCSPropertyType> ) strategy.merge(LocatorUtils.property(leftLocator, "verticalCS", lhsVerticalCS), LocatorUtils.property(rightLocator, "verticalCS", rhsVerticalCS), lhsVerticalCS, rhsVerticalCS, leftObject.isSetVerticalCS(), rightObject.isSetVerticalCS()));
                    target.setVerticalCS(mergedVerticalCS);
                } else {
                    if (verticalCSShouldBeMergedAndSet == java.lang.Boolean.FALSE) {
                        target.verticalCS = null;
                    }
                }
            }
            {
                java.lang.Boolean verticalDatumShouldBeMergedAndSet = strategy.shouldBeMergedAndSet(leftLocator, rightLocator, leftObject.isSetVerticalDatum(), rightObject.isSetVerticalDatum());
                if (verticalDatumShouldBeMergedAndSet == java.lang.Boolean.TRUE) {
                    JAXBElement<VerticalDatumPropertyType> lhsVerticalDatum;
                    lhsVerticalDatum = leftObject.getVerticalDatum();
                    JAXBElement<VerticalDatumPropertyType> rhsVerticalDatum;
                    rhsVerticalDatum = rightObject.getVerticalDatum();
                    JAXBElement<VerticalDatumPropertyType> mergedVerticalDatum = ((JAXBElement<VerticalDatumPropertyType> ) strategy.merge(LocatorUtils.property(leftLocator, "verticalDatum", lhsVerticalDatum), LocatorUtils.property(rightLocator, "verticalDatum", rhsVerticalDatum), lhsVerticalDatum, rhsVerticalDatum, leftObject.isSetVerticalDatum(), rightObject.isSetVerticalDatum()));
                    target.setVerticalDatum(mergedVerticalDatum);
                } else {
                    if (verticalDatumShouldBeMergedAndSet == java.lang.Boolean.FALSE) {
                        target.verticalDatum = null;
                    }
                }
            }
        }
    }

}
