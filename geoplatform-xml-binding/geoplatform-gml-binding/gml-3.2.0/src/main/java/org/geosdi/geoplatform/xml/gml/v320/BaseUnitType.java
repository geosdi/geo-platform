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
 * <p>Classe Java per BaseUnitType complex type.</p>
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.</p>
 * 
 * <pre>{@code
 * <complexType name="BaseUnitType">
 *   <complexContent>
 *     <extension base="{http://www.opengis.net/gml}UnitDefinitionType">
 *       <sequence>
 *         <element name="unitsSystem" type="{http://www.opengis.net/gml}ReferenceType"/>
 *       </sequence>
 *     </extension>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "BaseUnitType", propOrder = {
    "unitsSystem"
})
public class BaseUnitType
    extends UnitDefinitionType
    implements Cloneable, CopyTo, Equals, HashCode, MergeFrom, ToString
{

    @XmlElement(required = true)
    protected ReferenceType unitsSystem;

    /**
     * Recupera il valore della proprietà unitsSystem.
     * 
     * @return
     *     possible object is
     *     {@link ReferenceType }
     *     
     */
    public ReferenceType getUnitsSystem() {
        return unitsSystem;
    }

    /**
     * Imposta il valore della proprietà unitsSystem.
     * 
     * @param value
     *     allowed object is
     *     {@link ReferenceType }
     *     
     */
    public void setUnitsSystem(ReferenceType value) {
        this.unitsSystem = value;
    }

    public boolean isSetUnitsSystem() {
        return (this.unitsSystem!= null);
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
            ReferenceType theUnitsSystem;
            theUnitsSystem = this.getUnitsSystem();
            strategy.appendField(locator, this, "unitsSystem", buffer, theUnitsSystem, this.isSetUnitsSystem());
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
        final BaseUnitType that = ((BaseUnitType) object);
        {
            ReferenceType lhsUnitsSystem;
            lhsUnitsSystem = this.getUnitsSystem();
            ReferenceType rhsUnitsSystem;
            rhsUnitsSystem = that.getUnitsSystem();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "unitsSystem", lhsUnitsSystem), LocatorUtils.property(thatLocator, "unitsSystem", rhsUnitsSystem), lhsUnitsSystem, rhsUnitsSystem, this.isSetUnitsSystem(), that.isSetUnitsSystem())) {
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
            ReferenceType theUnitsSystem;
            theUnitsSystem = this.getUnitsSystem();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "unitsSystem", theUnitsSystem), currentHashCode, theUnitsSystem, this.isSetUnitsSystem());
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
        if (draftCopy instanceof BaseUnitType) {
            final BaseUnitType copy = ((BaseUnitType) draftCopy);
            {
                java.lang.Boolean unitsSystemShouldBeCopiedAndSet = strategy.shouldBeCopiedAndSet(locator, this.isSetUnitsSystem());
                if (unitsSystemShouldBeCopiedAndSet == java.lang.Boolean.TRUE) {
                    ReferenceType sourceUnitsSystem;
                    sourceUnitsSystem = this.getUnitsSystem();
                    ReferenceType copyUnitsSystem = ((ReferenceType) strategy.copy(LocatorUtils.property(locator, "unitsSystem", sourceUnitsSystem), sourceUnitsSystem, this.isSetUnitsSystem()));
                    copy.setUnitsSystem(copyUnitsSystem);
                } else {
                    if (unitsSystemShouldBeCopiedAndSet == java.lang.Boolean.FALSE) {
                        copy.unitsSystem = null;
                    }
                }
            }
        }
        return draftCopy;
    }

    @Override
    public Object createNewInstance() {
        return new BaseUnitType();
    }

    @Override
    public void mergeFrom(Object left, Object right) {
        final MergeStrategy strategy = JAXBMergeStrategy.getInstance();
        mergeFrom(null, null, left, right, strategy);
    }

    @Override
    public void mergeFrom(ObjectLocator leftLocator, ObjectLocator rightLocator, Object left, Object right, MergeStrategy strategy) {
        super.mergeFrom(leftLocator, rightLocator, left, right, strategy);
        if (right instanceof BaseUnitType) {
            final BaseUnitType target = this;
            final BaseUnitType leftObject = ((BaseUnitType) left);
            final BaseUnitType rightObject = ((BaseUnitType) right);
            {
                java.lang.Boolean unitsSystemShouldBeMergedAndSet = strategy.shouldBeMergedAndSet(leftLocator, rightLocator, leftObject.isSetUnitsSystem(), rightObject.isSetUnitsSystem());
                if (unitsSystemShouldBeMergedAndSet == java.lang.Boolean.TRUE) {
                    ReferenceType lhsUnitsSystem;
                    lhsUnitsSystem = leftObject.getUnitsSystem();
                    ReferenceType rhsUnitsSystem;
                    rhsUnitsSystem = rightObject.getUnitsSystem();
                    ReferenceType mergedUnitsSystem = ((ReferenceType) strategy.merge(LocatorUtils.property(leftLocator, "unitsSystem", lhsUnitsSystem), LocatorUtils.property(rightLocator, "unitsSystem", rhsUnitsSystem), lhsUnitsSystem, rhsUnitsSystem, leftObject.isSetUnitsSystem(), rightObject.isSetUnitsSystem()));
                    target.setUnitsSystem(mergedUnitsSystem);
                } else {
                    if (unitsSystemShouldBeMergedAndSet == java.lang.Boolean.FALSE) {
                        target.unitsSystem = null;
                    }
                }
            }
        }
    }

}
