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
 * <p>Classe Java per EngineeringCRSType complex type.</p>
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.</p>
 * 
 * <pre>{@code
 * <complexType name="EngineeringCRSType">
 *   <complexContent>
 *     <extension base="{http://www.opengis.net/gml}AbstractCRSType">
 *       <sequence>
 *         <element ref="{http://www.opengis.net/gml}coordinateSystem"/>
 *         <element ref="{http://www.opengis.net/gml}engineeringDatum"/>
 *       </sequence>
 *     </extension>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "EngineeringCRSType", propOrder = {
    "coordinateSystem",
    "engineeringDatum"
})
public class EngineeringCRSType
    extends AbstractCRSType
    implements Cloneable, CopyTo, Equals, HashCode, MergeFrom, ToString
{

    /**
     * An association role to the coordinate system used by this CRS.
     * 
     */
    @XmlElementRef(name = "coordinateSystem", namespace = "http://www.opengis.net/gml", type = JAXBElement.class)
    protected JAXBElement<CoordinateSystemPropertyType> coordinateSystem;
    /**
     * gml:engineeringDatum is an association role to the engineering datum used by this CRS.
     * 
     */
    @XmlElementRef(name = "engineeringDatum", namespace = "http://www.opengis.net/gml", type = JAXBElement.class)
    protected JAXBElement<EngineeringDatumPropertyType> engineeringDatum;

    /**
     * An association role to the coordinate system used by this CRS.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link CoordinateSystemPropertyType }{@code >}
     *     {@link JAXBElement }{@code <}{@link CoordinateSystemPropertyType }{@code >}
     *     
     */
    public JAXBElement<CoordinateSystemPropertyType> getCoordinateSystem() {
        return coordinateSystem;
    }

    /**
     * Imposta il valore della proprietà coordinateSystem.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link CoordinateSystemPropertyType }{@code >}
     *     {@link JAXBElement }{@code <}{@link CoordinateSystemPropertyType }{@code >}
     *     
     * @see #getCoordinateSystem()
     */
    public void setCoordinateSystem(JAXBElement<CoordinateSystemPropertyType> value) {
        this.coordinateSystem = value;
    }

    public boolean isSetCoordinateSystem() {
        return (this.coordinateSystem!= null);
    }

    /**
     * gml:engineeringDatum is an association role to the engineering datum used by this CRS.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link EngineeringDatumPropertyType }{@code >}
     *     {@link JAXBElement }{@code <}{@link EngineeringDatumPropertyType }{@code >}
     *     
     */
    public JAXBElement<EngineeringDatumPropertyType> getEngineeringDatum() {
        return engineeringDatum;
    }

    /**
     * Imposta il valore della proprietà engineeringDatum.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link EngineeringDatumPropertyType }{@code >}
     *     {@link JAXBElement }{@code <}{@link EngineeringDatumPropertyType }{@code >}
     *     
     * @see #getEngineeringDatum()
     */
    public void setEngineeringDatum(JAXBElement<EngineeringDatumPropertyType> value) {
        this.engineeringDatum = value;
    }

    public boolean isSetEngineeringDatum() {
        return (this.engineeringDatum!= null);
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
            JAXBElement<CoordinateSystemPropertyType> theCoordinateSystem;
            theCoordinateSystem = this.getCoordinateSystem();
            strategy.appendField(locator, this, "coordinateSystem", buffer, theCoordinateSystem, this.isSetCoordinateSystem());
        }
        {
            JAXBElement<EngineeringDatumPropertyType> theEngineeringDatum;
            theEngineeringDatum = this.getEngineeringDatum();
            strategy.appendField(locator, this, "engineeringDatum", buffer, theEngineeringDatum, this.isSetEngineeringDatum());
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
        final EngineeringCRSType that = ((EngineeringCRSType) object);
        {
            JAXBElement<CoordinateSystemPropertyType> lhsCoordinateSystem;
            lhsCoordinateSystem = this.getCoordinateSystem();
            JAXBElement<CoordinateSystemPropertyType> rhsCoordinateSystem;
            rhsCoordinateSystem = that.getCoordinateSystem();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "coordinateSystem", lhsCoordinateSystem), LocatorUtils.property(thatLocator, "coordinateSystem", rhsCoordinateSystem), lhsCoordinateSystem, rhsCoordinateSystem, this.isSetCoordinateSystem(), that.isSetCoordinateSystem())) {
                return false;
            }
        }
        {
            JAXBElement<EngineeringDatumPropertyType> lhsEngineeringDatum;
            lhsEngineeringDatum = this.getEngineeringDatum();
            JAXBElement<EngineeringDatumPropertyType> rhsEngineeringDatum;
            rhsEngineeringDatum = that.getEngineeringDatum();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "engineeringDatum", lhsEngineeringDatum), LocatorUtils.property(thatLocator, "engineeringDatum", rhsEngineeringDatum), lhsEngineeringDatum, rhsEngineeringDatum, this.isSetEngineeringDatum(), that.isSetEngineeringDatum())) {
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
            JAXBElement<CoordinateSystemPropertyType> theCoordinateSystem;
            theCoordinateSystem = this.getCoordinateSystem();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "coordinateSystem", theCoordinateSystem), currentHashCode, theCoordinateSystem, this.isSetCoordinateSystem());
        }
        {
            JAXBElement<EngineeringDatumPropertyType> theEngineeringDatum;
            theEngineeringDatum = this.getEngineeringDatum();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "engineeringDatum", theEngineeringDatum), currentHashCode, theEngineeringDatum, this.isSetEngineeringDatum());
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
        if (draftCopy instanceof EngineeringCRSType) {
            final EngineeringCRSType copy = ((EngineeringCRSType) draftCopy);
            {
                java.lang.Boolean coordinateSystemShouldBeCopiedAndSet = strategy.shouldBeCopiedAndSet(locator, this.isSetCoordinateSystem());
                if (coordinateSystemShouldBeCopiedAndSet == java.lang.Boolean.TRUE) {
                    JAXBElement<CoordinateSystemPropertyType> sourceCoordinateSystem;
                    sourceCoordinateSystem = this.getCoordinateSystem();
                    @SuppressWarnings("unchecked")
                    JAXBElement<CoordinateSystemPropertyType> copyCoordinateSystem = ((JAXBElement<CoordinateSystemPropertyType> ) strategy.copy(LocatorUtils.property(locator, "coordinateSystem", sourceCoordinateSystem), sourceCoordinateSystem, this.isSetCoordinateSystem()));
                    copy.setCoordinateSystem(copyCoordinateSystem);
                } else {
                    if (coordinateSystemShouldBeCopiedAndSet == java.lang.Boolean.FALSE) {
                        copy.coordinateSystem = null;
                    }
                }
            }
            {
                java.lang.Boolean engineeringDatumShouldBeCopiedAndSet = strategy.shouldBeCopiedAndSet(locator, this.isSetEngineeringDatum());
                if (engineeringDatumShouldBeCopiedAndSet == java.lang.Boolean.TRUE) {
                    JAXBElement<EngineeringDatumPropertyType> sourceEngineeringDatum;
                    sourceEngineeringDatum = this.getEngineeringDatum();
                    @SuppressWarnings("unchecked")
                    JAXBElement<EngineeringDatumPropertyType> copyEngineeringDatum = ((JAXBElement<EngineeringDatumPropertyType> ) strategy.copy(LocatorUtils.property(locator, "engineeringDatum", sourceEngineeringDatum), sourceEngineeringDatum, this.isSetEngineeringDatum()));
                    copy.setEngineeringDatum(copyEngineeringDatum);
                } else {
                    if (engineeringDatumShouldBeCopiedAndSet == java.lang.Boolean.FALSE) {
                        copy.engineeringDatum = null;
                    }
                }
            }
        }
        return draftCopy;
    }

    @Override
    public Object createNewInstance() {
        return new EngineeringCRSType();
    }

    @Override
    public void mergeFrom(Object left, Object right) {
        final MergeStrategy strategy = JAXBMergeStrategy.getInstance();
        mergeFrom(null, null, left, right, strategy);
    }

    @Override
    public void mergeFrom(ObjectLocator leftLocator, ObjectLocator rightLocator, Object left, Object right, MergeStrategy strategy) {
        super.mergeFrom(leftLocator, rightLocator, left, right, strategy);
        if (right instanceof EngineeringCRSType) {
            final EngineeringCRSType target = this;
            final EngineeringCRSType leftObject = ((EngineeringCRSType) left);
            final EngineeringCRSType rightObject = ((EngineeringCRSType) right);
            {
                java.lang.Boolean coordinateSystemShouldBeMergedAndSet = strategy.shouldBeMergedAndSet(leftLocator, rightLocator, leftObject.isSetCoordinateSystem(), rightObject.isSetCoordinateSystem());
                if (coordinateSystemShouldBeMergedAndSet == java.lang.Boolean.TRUE) {
                    JAXBElement<CoordinateSystemPropertyType> lhsCoordinateSystem;
                    lhsCoordinateSystem = leftObject.getCoordinateSystem();
                    JAXBElement<CoordinateSystemPropertyType> rhsCoordinateSystem;
                    rhsCoordinateSystem = rightObject.getCoordinateSystem();
                    JAXBElement<CoordinateSystemPropertyType> mergedCoordinateSystem = ((JAXBElement<CoordinateSystemPropertyType> ) strategy.merge(LocatorUtils.property(leftLocator, "coordinateSystem", lhsCoordinateSystem), LocatorUtils.property(rightLocator, "coordinateSystem", rhsCoordinateSystem), lhsCoordinateSystem, rhsCoordinateSystem, leftObject.isSetCoordinateSystem(), rightObject.isSetCoordinateSystem()));
                    target.setCoordinateSystem(mergedCoordinateSystem);
                } else {
                    if (coordinateSystemShouldBeMergedAndSet == java.lang.Boolean.FALSE) {
                        target.coordinateSystem = null;
                    }
                }
            }
            {
                java.lang.Boolean engineeringDatumShouldBeMergedAndSet = strategy.shouldBeMergedAndSet(leftLocator, rightLocator, leftObject.isSetEngineeringDatum(), rightObject.isSetEngineeringDatum());
                if (engineeringDatumShouldBeMergedAndSet == java.lang.Boolean.TRUE) {
                    JAXBElement<EngineeringDatumPropertyType> lhsEngineeringDatum;
                    lhsEngineeringDatum = leftObject.getEngineeringDatum();
                    JAXBElement<EngineeringDatumPropertyType> rhsEngineeringDatum;
                    rhsEngineeringDatum = rightObject.getEngineeringDatum();
                    JAXBElement<EngineeringDatumPropertyType> mergedEngineeringDatum = ((JAXBElement<EngineeringDatumPropertyType> ) strategy.merge(LocatorUtils.property(leftLocator, "engineeringDatum", lhsEngineeringDatum), LocatorUtils.property(rightLocator, "engineeringDatum", rhsEngineeringDatum), lhsEngineeringDatum, rhsEngineeringDatum, leftObject.isSetEngineeringDatum(), rightObject.isSetEngineeringDatum()));
                    target.setEngineeringDatum(mergedEngineeringDatum);
                } else {
                    if (engineeringDatumShouldBeMergedAndSet == java.lang.Boolean.FALSE) {
                        target.engineeringDatum = null;
                    }
                }
            }
        }
    }

}
