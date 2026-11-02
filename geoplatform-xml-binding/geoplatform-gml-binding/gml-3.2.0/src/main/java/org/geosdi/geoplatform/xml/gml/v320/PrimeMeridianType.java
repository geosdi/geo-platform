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
 * <p>Classe Java per PrimeMeridianType complex type.</p>
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.</p>
 * 
 * <pre>{@code
 * <complexType name="PrimeMeridianType">
 *   <complexContent>
 *     <extension base="{http://www.opengis.net/gml}IdentifiedObjectType">
 *       <sequence>
 *         <element ref="{http://www.opengis.net/gml}greenwichLongitude"/>
 *       </sequence>
 *     </extension>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PrimeMeridianType", propOrder = {
    "greenwichLongitude"
})
public class PrimeMeridianType
    extends IdentifiedObjectType
    implements Cloneable, CopyTo, Equals, HashCode, MergeFrom, ToString
{

    /**
     * gml:greenwichLongitude is the longitude of the prime meridian measured from the Greenwich meridian, positive eastward. If the value of the prime meridian “name” is "Greenwich" then the value of greenwichLongitude shall be 0 degrees.
     * 
     */
    @XmlElement(required = true)
    protected AngleType greenwichLongitude;

    /**
     * gml:greenwichLongitude is the longitude of the prime meridian measured from the Greenwich meridian, positive eastward. If the value of the prime meridian “name” is "Greenwich" then the value of greenwichLongitude shall be 0 degrees.
     * 
     * @return
     *     possible object is
     *     {@link AngleType }
     *     
     */
    public AngleType getGreenwichLongitude() {
        return greenwichLongitude;
    }

    /**
     * Imposta il valore della proprietà greenwichLongitude.
     * 
     * @param value
     *     allowed object is
     *     {@link AngleType }
     *     
     * @see #getGreenwichLongitude()
     */
    public void setGreenwichLongitude(AngleType value) {
        this.greenwichLongitude = value;
    }

    public boolean isSetGreenwichLongitude() {
        return (this.greenwichLongitude!= null);
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
            AngleType theGreenwichLongitude;
            theGreenwichLongitude = this.getGreenwichLongitude();
            strategy.appendField(locator, this, "greenwichLongitude", buffer, theGreenwichLongitude, this.isSetGreenwichLongitude());
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
        final PrimeMeridianType that = ((PrimeMeridianType) object);
        {
            AngleType lhsGreenwichLongitude;
            lhsGreenwichLongitude = this.getGreenwichLongitude();
            AngleType rhsGreenwichLongitude;
            rhsGreenwichLongitude = that.getGreenwichLongitude();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "greenwichLongitude", lhsGreenwichLongitude), LocatorUtils.property(thatLocator, "greenwichLongitude", rhsGreenwichLongitude), lhsGreenwichLongitude, rhsGreenwichLongitude, this.isSetGreenwichLongitude(), that.isSetGreenwichLongitude())) {
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
            AngleType theGreenwichLongitude;
            theGreenwichLongitude = this.getGreenwichLongitude();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "greenwichLongitude", theGreenwichLongitude), currentHashCode, theGreenwichLongitude, this.isSetGreenwichLongitude());
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
        if (draftCopy instanceof PrimeMeridianType) {
            final PrimeMeridianType copy = ((PrimeMeridianType) draftCopy);
            {
                java.lang.Boolean greenwichLongitudeShouldBeCopiedAndSet = strategy.shouldBeCopiedAndSet(locator, this.isSetGreenwichLongitude());
                if (greenwichLongitudeShouldBeCopiedAndSet == java.lang.Boolean.TRUE) {
                    AngleType sourceGreenwichLongitude;
                    sourceGreenwichLongitude = this.getGreenwichLongitude();
                    AngleType copyGreenwichLongitude = ((AngleType) strategy.copy(LocatorUtils.property(locator, "greenwichLongitude", sourceGreenwichLongitude), sourceGreenwichLongitude, this.isSetGreenwichLongitude()));
                    copy.setGreenwichLongitude(copyGreenwichLongitude);
                } else {
                    if (greenwichLongitudeShouldBeCopiedAndSet == java.lang.Boolean.FALSE) {
                        copy.greenwichLongitude = null;
                    }
                }
            }
        }
        return draftCopy;
    }

    @Override
    public Object createNewInstance() {
        return new PrimeMeridianType();
    }

    @Override
    public void mergeFrom(Object left, Object right) {
        final MergeStrategy strategy = JAXBMergeStrategy.getInstance();
        mergeFrom(null, null, left, right, strategy);
    }

    @Override
    public void mergeFrom(ObjectLocator leftLocator, ObjectLocator rightLocator, Object left, Object right, MergeStrategy strategy) {
        super.mergeFrom(leftLocator, rightLocator, left, right, strategy);
        if (right instanceof PrimeMeridianType) {
            final PrimeMeridianType target = this;
            final PrimeMeridianType leftObject = ((PrimeMeridianType) left);
            final PrimeMeridianType rightObject = ((PrimeMeridianType) right);
            {
                java.lang.Boolean greenwichLongitudeShouldBeMergedAndSet = strategy.shouldBeMergedAndSet(leftLocator, rightLocator, leftObject.isSetGreenwichLongitude(), rightObject.isSetGreenwichLongitude());
                if (greenwichLongitudeShouldBeMergedAndSet == java.lang.Boolean.TRUE) {
                    AngleType lhsGreenwichLongitude;
                    lhsGreenwichLongitude = leftObject.getGreenwichLongitude();
                    AngleType rhsGreenwichLongitude;
                    rhsGreenwichLongitude = rightObject.getGreenwichLongitude();
                    AngleType mergedGreenwichLongitude = ((AngleType) strategy.merge(LocatorUtils.property(leftLocator, "greenwichLongitude", lhsGreenwichLongitude), LocatorUtils.property(rightLocator, "greenwichLongitude", rhsGreenwichLongitude), lhsGreenwichLongitude, rhsGreenwichLongitude, leftObject.isSetGreenwichLongitude(), rightObject.isSetGreenwichLongitude()));
                    target.setGreenwichLongitude(mergedGreenwichLongitude);
                } else {
                    if (greenwichLongitudeShouldBeMergedAndSet == java.lang.Boolean.FALSE) {
                        target.greenwichLongitude = null;
                    }
                }
            }
        }
    }

}
