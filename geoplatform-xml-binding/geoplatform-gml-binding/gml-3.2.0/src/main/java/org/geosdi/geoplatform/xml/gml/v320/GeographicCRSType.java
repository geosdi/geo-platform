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
 * <p>Classe Java per GeographicCRSType complex type.</p>
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.</p>
 * 
 * <pre>{@code
 * <complexType name="GeographicCRSType">
 *   <complexContent>
 *     <extension base="{http://www.opengis.net/gml}AbstractCRSType">
 *       <sequence>
 *         <element ref="{http://www.opengis.net/gml}usesEllipsoidalCS"/>
 *         <element ref="{http://www.opengis.net/gml}usesGeodeticDatum"/>
 *       </sequence>
 *     </extension>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "GeographicCRSType", propOrder = {
    "usesEllipsoidalCS",
    "usesGeodeticDatum"
})
public class GeographicCRSType
    extends AbstractCRSType
    implements Cloneable, CopyTo, Equals, HashCode, MergeFrom, ToString
{

    @XmlElement(required = true)
    protected EllipsoidalCSPropertyType usesEllipsoidalCS;
    @XmlElement(required = true)
    protected GeodeticDatumPropertyType usesGeodeticDatum;

    /**
     * Recupera il valore della proprietà usesEllipsoidalCS.
     * 
     * @return
     *     possible object is
     *     {@link EllipsoidalCSPropertyType }
     *     
     */
    public EllipsoidalCSPropertyType getUsesEllipsoidalCS() {
        return usesEllipsoidalCS;
    }

    /**
     * Imposta il valore della proprietà usesEllipsoidalCS.
     * 
     * @param value
     *     allowed object is
     *     {@link EllipsoidalCSPropertyType }
     *     
     */
    public void setUsesEllipsoidalCS(EllipsoidalCSPropertyType value) {
        this.usesEllipsoidalCS = value;
    }

    public boolean isSetUsesEllipsoidalCS() {
        return (this.usesEllipsoidalCS!= null);
    }

    /**
     * Recupera il valore della proprietà usesGeodeticDatum.
     * 
     * @return
     *     possible object is
     *     {@link GeodeticDatumPropertyType }
     *     
     */
    public GeodeticDatumPropertyType getUsesGeodeticDatum() {
        return usesGeodeticDatum;
    }

    /**
     * Imposta il valore della proprietà usesGeodeticDatum.
     * 
     * @param value
     *     allowed object is
     *     {@link GeodeticDatumPropertyType }
     *     
     */
    public void setUsesGeodeticDatum(GeodeticDatumPropertyType value) {
        this.usesGeodeticDatum = value;
    }

    public boolean isSetUsesGeodeticDatum() {
        return (this.usesGeodeticDatum!= null);
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
            EllipsoidalCSPropertyType theUsesEllipsoidalCS;
            theUsesEllipsoidalCS = this.getUsesEllipsoidalCS();
            strategy.appendField(locator, this, "usesEllipsoidalCS", buffer, theUsesEllipsoidalCS, this.isSetUsesEllipsoidalCS());
        }
        {
            GeodeticDatumPropertyType theUsesGeodeticDatum;
            theUsesGeodeticDatum = this.getUsesGeodeticDatum();
            strategy.appendField(locator, this, "usesGeodeticDatum", buffer, theUsesGeodeticDatum, this.isSetUsesGeodeticDatum());
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
        final GeographicCRSType that = ((GeographicCRSType) object);
        {
            EllipsoidalCSPropertyType lhsUsesEllipsoidalCS;
            lhsUsesEllipsoidalCS = this.getUsesEllipsoidalCS();
            EllipsoidalCSPropertyType rhsUsesEllipsoidalCS;
            rhsUsesEllipsoidalCS = that.getUsesEllipsoidalCS();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "usesEllipsoidalCS", lhsUsesEllipsoidalCS), LocatorUtils.property(thatLocator, "usesEllipsoidalCS", rhsUsesEllipsoidalCS), lhsUsesEllipsoidalCS, rhsUsesEllipsoidalCS, this.isSetUsesEllipsoidalCS(), that.isSetUsesEllipsoidalCS())) {
                return false;
            }
        }
        {
            GeodeticDatumPropertyType lhsUsesGeodeticDatum;
            lhsUsesGeodeticDatum = this.getUsesGeodeticDatum();
            GeodeticDatumPropertyType rhsUsesGeodeticDatum;
            rhsUsesGeodeticDatum = that.getUsesGeodeticDatum();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "usesGeodeticDatum", lhsUsesGeodeticDatum), LocatorUtils.property(thatLocator, "usesGeodeticDatum", rhsUsesGeodeticDatum), lhsUsesGeodeticDatum, rhsUsesGeodeticDatum, this.isSetUsesGeodeticDatum(), that.isSetUsesGeodeticDatum())) {
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
            EllipsoidalCSPropertyType theUsesEllipsoidalCS;
            theUsesEllipsoidalCS = this.getUsesEllipsoidalCS();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "usesEllipsoidalCS", theUsesEllipsoidalCS), currentHashCode, theUsesEllipsoidalCS, this.isSetUsesEllipsoidalCS());
        }
        {
            GeodeticDatumPropertyType theUsesGeodeticDatum;
            theUsesGeodeticDatum = this.getUsesGeodeticDatum();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "usesGeodeticDatum", theUsesGeodeticDatum), currentHashCode, theUsesGeodeticDatum, this.isSetUsesGeodeticDatum());
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
        if (draftCopy instanceof GeographicCRSType) {
            final GeographicCRSType copy = ((GeographicCRSType) draftCopy);
            {
                java.lang.Boolean usesEllipsoidalCSShouldBeCopiedAndSet = strategy.shouldBeCopiedAndSet(locator, this.isSetUsesEllipsoidalCS());
                if (usesEllipsoidalCSShouldBeCopiedAndSet == java.lang.Boolean.TRUE) {
                    EllipsoidalCSPropertyType sourceUsesEllipsoidalCS;
                    sourceUsesEllipsoidalCS = this.getUsesEllipsoidalCS();
                    EllipsoidalCSPropertyType copyUsesEllipsoidalCS = ((EllipsoidalCSPropertyType) strategy.copy(LocatorUtils.property(locator, "usesEllipsoidalCS", sourceUsesEllipsoidalCS), sourceUsesEllipsoidalCS, this.isSetUsesEllipsoidalCS()));
                    copy.setUsesEllipsoidalCS(copyUsesEllipsoidalCS);
                } else {
                    if (usesEllipsoidalCSShouldBeCopiedAndSet == java.lang.Boolean.FALSE) {
                        copy.usesEllipsoidalCS = null;
                    }
                }
            }
            {
                java.lang.Boolean usesGeodeticDatumShouldBeCopiedAndSet = strategy.shouldBeCopiedAndSet(locator, this.isSetUsesGeodeticDatum());
                if (usesGeodeticDatumShouldBeCopiedAndSet == java.lang.Boolean.TRUE) {
                    GeodeticDatumPropertyType sourceUsesGeodeticDatum;
                    sourceUsesGeodeticDatum = this.getUsesGeodeticDatum();
                    GeodeticDatumPropertyType copyUsesGeodeticDatum = ((GeodeticDatumPropertyType) strategy.copy(LocatorUtils.property(locator, "usesGeodeticDatum", sourceUsesGeodeticDatum), sourceUsesGeodeticDatum, this.isSetUsesGeodeticDatum()));
                    copy.setUsesGeodeticDatum(copyUsesGeodeticDatum);
                } else {
                    if (usesGeodeticDatumShouldBeCopiedAndSet == java.lang.Boolean.FALSE) {
                        copy.usesGeodeticDatum = null;
                    }
                }
            }
        }
        return draftCopy;
    }

    @Override
    public Object createNewInstance() {
        return new GeographicCRSType();
    }

    @Override
    public void mergeFrom(Object left, Object right) {
        final MergeStrategy strategy = JAXBMergeStrategy.getInstance();
        mergeFrom(null, null, left, right, strategy);
    }

    @Override
    public void mergeFrom(ObjectLocator leftLocator, ObjectLocator rightLocator, Object left, Object right, MergeStrategy strategy) {
        super.mergeFrom(leftLocator, rightLocator, left, right, strategy);
        if (right instanceof GeographicCRSType) {
            final GeographicCRSType target = this;
            final GeographicCRSType leftObject = ((GeographicCRSType) left);
            final GeographicCRSType rightObject = ((GeographicCRSType) right);
            {
                java.lang.Boolean usesEllipsoidalCSShouldBeMergedAndSet = strategy.shouldBeMergedAndSet(leftLocator, rightLocator, leftObject.isSetUsesEllipsoidalCS(), rightObject.isSetUsesEllipsoidalCS());
                if (usesEllipsoidalCSShouldBeMergedAndSet == java.lang.Boolean.TRUE) {
                    EllipsoidalCSPropertyType lhsUsesEllipsoidalCS;
                    lhsUsesEllipsoidalCS = leftObject.getUsesEllipsoidalCS();
                    EllipsoidalCSPropertyType rhsUsesEllipsoidalCS;
                    rhsUsesEllipsoidalCS = rightObject.getUsesEllipsoidalCS();
                    EllipsoidalCSPropertyType mergedUsesEllipsoidalCS = ((EllipsoidalCSPropertyType) strategy.merge(LocatorUtils.property(leftLocator, "usesEllipsoidalCS", lhsUsesEllipsoidalCS), LocatorUtils.property(rightLocator, "usesEllipsoidalCS", rhsUsesEllipsoidalCS), lhsUsesEllipsoidalCS, rhsUsesEllipsoidalCS, leftObject.isSetUsesEllipsoidalCS(), rightObject.isSetUsesEllipsoidalCS()));
                    target.setUsesEllipsoidalCS(mergedUsesEllipsoidalCS);
                } else {
                    if (usesEllipsoidalCSShouldBeMergedAndSet == java.lang.Boolean.FALSE) {
                        target.usesEllipsoidalCS = null;
                    }
                }
            }
            {
                java.lang.Boolean usesGeodeticDatumShouldBeMergedAndSet = strategy.shouldBeMergedAndSet(leftLocator, rightLocator, leftObject.isSetUsesGeodeticDatum(), rightObject.isSetUsesGeodeticDatum());
                if (usesGeodeticDatumShouldBeMergedAndSet == java.lang.Boolean.TRUE) {
                    GeodeticDatumPropertyType lhsUsesGeodeticDatum;
                    lhsUsesGeodeticDatum = leftObject.getUsesGeodeticDatum();
                    GeodeticDatumPropertyType rhsUsesGeodeticDatum;
                    rhsUsesGeodeticDatum = rightObject.getUsesGeodeticDatum();
                    GeodeticDatumPropertyType mergedUsesGeodeticDatum = ((GeodeticDatumPropertyType) strategy.merge(LocatorUtils.property(leftLocator, "usesGeodeticDatum", lhsUsesGeodeticDatum), LocatorUtils.property(rightLocator, "usesGeodeticDatum", rhsUsesGeodeticDatum), lhsUsesGeodeticDatum, rhsUsesGeodeticDatum, leftObject.isSetUsesGeodeticDatum(), rightObject.isSetUsesGeodeticDatum()));
                    target.setUsesGeodeticDatum(mergedUsesGeodeticDatum);
                } else {
                    if (usesGeodeticDatumShouldBeMergedAndSet == java.lang.Boolean.FALSE) {
                        target.usesGeodeticDatum = null;
                    }
                }
            }
        }
    }

}
