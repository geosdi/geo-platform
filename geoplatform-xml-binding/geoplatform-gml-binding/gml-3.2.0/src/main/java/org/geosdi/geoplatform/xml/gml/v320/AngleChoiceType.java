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
 * <p>Classe Java per AngleChoiceType complex type.</p>
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.</p>
 * 
 * <pre>{@code
 * <complexType name="AngleChoiceType">
 *   <complexContent>
 *     <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       <choice>
 *         <element ref="{http://www.opengis.net/gml}angle"/>
 *         <element ref="{http://www.opengis.net/gml}dmsAngle"/>
 *       </choice>
 *     </restriction>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AngleChoiceType", propOrder = {
    "angle",
    "dmsAngle"
})
public class AngleChoiceType implements Cloneable, CopyTo, Equals, HashCode, MergeFrom, ToString
{

    /**
     * The gml:angle property element is used to record the value of an angle quantity as a single number, with its units.
     * 
     */
    protected AngleType angle;
    protected DMSAngleType dmsAngle;

    /**
     * The gml:angle property element is used to record the value of an angle quantity as a single number, with its units.
     * 
     * @return
     *     possible object is
     *     {@link AngleType }
     *     
     */
    public AngleType getAngle() {
        return angle;
    }

    /**
     * Imposta il valore della proprietà angle.
     * 
     * @param value
     *     allowed object is
     *     {@link AngleType }
     *     
     * @see #getAngle()
     */
    public void setAngle(AngleType value) {
        this.angle = value;
    }

    public boolean isSetAngle() {
        return (this.angle!= null);
    }

    /**
     * Recupera il valore della proprietà dmsAngle.
     * 
     * @return
     *     possible object is
     *     {@link DMSAngleType }
     *     
     */
    public DMSAngleType getDmsAngle() {
        return dmsAngle;
    }

    /**
     * Imposta il valore della proprietà dmsAngle.
     * 
     * @param value
     *     allowed object is
     *     {@link DMSAngleType }
     *     
     */
    public void setDmsAngle(DMSAngleType value) {
        this.dmsAngle = value;
    }

    public boolean isSetDmsAngle() {
        return (this.dmsAngle!= null);
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
            AngleType theAngle;
            theAngle = this.getAngle();
            strategy.appendField(locator, this, "angle", buffer, theAngle, this.isSetAngle());
        }
        {
            DMSAngleType theDmsAngle;
            theDmsAngle = this.getDmsAngle();
            strategy.appendField(locator, this, "dmsAngle", buffer, theDmsAngle, this.isSetDmsAngle());
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
        final AngleChoiceType that = ((AngleChoiceType) object);
        {
            AngleType lhsAngle;
            lhsAngle = this.getAngle();
            AngleType rhsAngle;
            rhsAngle = that.getAngle();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "angle", lhsAngle), LocatorUtils.property(thatLocator, "angle", rhsAngle), lhsAngle, rhsAngle, this.isSetAngle(), that.isSetAngle())) {
                return false;
            }
        }
        {
            DMSAngleType lhsDmsAngle;
            lhsDmsAngle = this.getDmsAngle();
            DMSAngleType rhsDmsAngle;
            rhsDmsAngle = that.getDmsAngle();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "dmsAngle", lhsDmsAngle), LocatorUtils.property(thatLocator, "dmsAngle", rhsDmsAngle), lhsDmsAngle, rhsDmsAngle, this.isSetDmsAngle(), that.isSetDmsAngle())) {
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
            AngleType theAngle;
            theAngle = this.getAngle();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "angle", theAngle), currentHashCode, theAngle, this.isSetAngle());
        }
        {
            DMSAngleType theDmsAngle;
            theDmsAngle = this.getDmsAngle();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "dmsAngle", theDmsAngle), currentHashCode, theDmsAngle, this.isSetDmsAngle());
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
        if (draftCopy instanceof AngleChoiceType) {
            final AngleChoiceType copy = ((AngleChoiceType) draftCopy);
            {
                java.lang.Boolean angleShouldBeCopiedAndSet = strategy.shouldBeCopiedAndSet(locator, this.isSetAngle());
                if (angleShouldBeCopiedAndSet == java.lang.Boolean.TRUE) {
                    AngleType sourceAngle;
                    sourceAngle = this.getAngle();
                    AngleType copyAngle = ((AngleType) strategy.copy(LocatorUtils.property(locator, "angle", sourceAngle), sourceAngle, this.isSetAngle()));
                    copy.setAngle(copyAngle);
                } else {
                    if (angleShouldBeCopiedAndSet == java.lang.Boolean.FALSE) {
                        copy.angle = null;
                    }
                }
            }
            {
                java.lang.Boolean dmsAngleShouldBeCopiedAndSet = strategy.shouldBeCopiedAndSet(locator, this.isSetDmsAngle());
                if (dmsAngleShouldBeCopiedAndSet == java.lang.Boolean.TRUE) {
                    DMSAngleType sourceDmsAngle;
                    sourceDmsAngle = this.getDmsAngle();
                    DMSAngleType copyDmsAngle = ((DMSAngleType) strategy.copy(LocatorUtils.property(locator, "dmsAngle", sourceDmsAngle), sourceDmsAngle, this.isSetDmsAngle()));
                    copy.setDmsAngle(copyDmsAngle);
                } else {
                    if (dmsAngleShouldBeCopiedAndSet == java.lang.Boolean.FALSE) {
                        copy.dmsAngle = null;
                    }
                }
            }
        }
        return draftCopy;
    }

    @Override
    public Object createNewInstance() {
        return new AngleChoiceType();
    }

    @Override
    public void mergeFrom(Object left, Object right) {
        final MergeStrategy strategy = JAXBMergeStrategy.getInstance();
        mergeFrom(null, null, left, right, strategy);
    }

    @Override
    public void mergeFrom(ObjectLocator leftLocator, ObjectLocator rightLocator, Object left, Object right, MergeStrategy strategy) {
        if (right instanceof AngleChoiceType) {
            final AngleChoiceType target = this;
            final AngleChoiceType leftObject = ((AngleChoiceType) left);
            final AngleChoiceType rightObject = ((AngleChoiceType) right);
            {
                java.lang.Boolean angleShouldBeMergedAndSet = strategy.shouldBeMergedAndSet(leftLocator, rightLocator, leftObject.isSetAngle(), rightObject.isSetAngle());
                if (angleShouldBeMergedAndSet == java.lang.Boolean.TRUE) {
                    AngleType lhsAngle;
                    lhsAngle = leftObject.getAngle();
                    AngleType rhsAngle;
                    rhsAngle = rightObject.getAngle();
                    AngleType mergedAngle = ((AngleType) strategy.merge(LocatorUtils.property(leftLocator, "angle", lhsAngle), LocatorUtils.property(rightLocator, "angle", rhsAngle), lhsAngle, rhsAngle, leftObject.isSetAngle(), rightObject.isSetAngle()));
                    target.setAngle(mergedAngle);
                } else {
                    if (angleShouldBeMergedAndSet == java.lang.Boolean.FALSE) {
                        target.angle = null;
                    }
                }
            }
            {
                java.lang.Boolean dmsAngleShouldBeMergedAndSet = strategy.shouldBeMergedAndSet(leftLocator, rightLocator, leftObject.isSetDmsAngle(), rightObject.isSetDmsAngle());
                if (dmsAngleShouldBeMergedAndSet == java.lang.Boolean.TRUE) {
                    DMSAngleType lhsDmsAngle;
                    lhsDmsAngle = leftObject.getDmsAngle();
                    DMSAngleType rhsDmsAngle;
                    rhsDmsAngle = rightObject.getDmsAngle();
                    DMSAngleType mergedDmsAngle = ((DMSAngleType) strategy.merge(LocatorUtils.property(leftLocator, "dmsAngle", lhsDmsAngle), LocatorUtils.property(rightLocator, "dmsAngle", rhsDmsAngle), lhsDmsAngle, rhsDmsAngle, leftObject.isSetDmsAngle(), rightObject.isSetDmsAngle()));
                    target.setDmsAngle(mergedDmsAngle);
                } else {
                    if (dmsAngleShouldBeMergedAndSet == java.lang.Boolean.FALSE) {
                        target.dmsAngle = null;
                    }
                }
            }
        }
    }

}
