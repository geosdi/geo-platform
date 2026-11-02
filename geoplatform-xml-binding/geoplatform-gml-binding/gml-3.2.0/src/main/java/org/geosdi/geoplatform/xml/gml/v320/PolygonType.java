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
 * <p>Classe Java per PolygonType complex type.</p>
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.</p>
 * 
 * <pre>{@code
 * <complexType name="PolygonType">
 *   <complexContent>
 *     <extension base="{http://www.opengis.net/gml}AbstractSurfaceType">
 *       <sequence>
 *         <element ref="{http://www.opengis.net/gml}exterior" minOccurs="0"/>
 *         <element ref="{http://www.opengis.net/gml}interior" maxOccurs="unbounded" minOccurs="0"/>
 *       </sequence>
 *     </extension>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PolygonType", propOrder = {
    "exterior",
    "interior"
})
public class PolygonType
    extends AbstractSurfaceType
    implements Cloneable, CopyTo, Equals, HashCode, MergeFrom, ToString
{

    /**
     * A boundary of a surface consists of a number of rings. In the normal 2D case, one of these rings is distinguished as being the exterior boundary. In a general manifold this is not always possible, in which case all boundaries shall be listed as interior boundaries, and the exterior will be empty.
     * 
     */
    protected AbstractRingPropertyType exterior;
    /**
     * A boundary of a surface consists of a number of rings. The "interior" rings separate the surface / surface patch from the area enclosed by the rings.
     * 
     */
    protected List<AbstractRingPropertyType> interior;

    /**
     * A boundary of a surface consists of a number of rings. In the normal 2D case, one of these rings is distinguished as being the exterior boundary. In a general manifold this is not always possible, in which case all boundaries shall be listed as interior boundaries, and the exterior will be empty.
     * 
     * @return
     *     possible object is
     *     {@link AbstractRingPropertyType }
     *     
     */
    public AbstractRingPropertyType getExterior() {
        return exterior;
    }

    /**
     * Imposta il valore della proprietà exterior.
     * 
     * @param value
     *     allowed object is
     *     {@link AbstractRingPropertyType }
     *     
     * @see #getExterior()
     */
    public void setExterior(AbstractRingPropertyType value) {
        this.exterior = value;
    }

    public boolean isSetExterior() {
        return (this.exterior!= null);
    }

    /**
     * A boundary of a surface consists of a number of rings. The "interior" rings separate the surface / surface patch from the area enclosed by the rings.
     * 
     * Gets the value of the interior property.
     * 
     * <p>This accessor method returns a reference to the live list,
     * not a snapshot. Therefore, any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a {@code set} method for the interior property.</p>
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * </p>
     * <pre>
     * getInterior().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link AbstractRingPropertyType }
     * </p>
     * 
     * 
     * @return
     *     The value of the interior property.
     */
    public List<AbstractRingPropertyType> getInterior() {
        if (interior == null) {
            interior = new ArrayList<>();
        }
        return this.interior;
    }

    public boolean isSetInterior() {
        return ((this.interior!= null)&&(!this.interior.isEmpty()));
    }

    public void unsetInterior() {
        this.interior = null;
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
            AbstractRingPropertyType theExterior;
            theExterior = this.getExterior();
            strategy.appendField(locator, this, "exterior", buffer, theExterior, this.isSetExterior());
        }
        {
            List<AbstractRingPropertyType> theInterior;
            theInterior = (this.isSetInterior()?this.getInterior():null);
            strategy.appendField(locator, this, "interior", buffer, theInterior, this.isSetInterior());
        }
        return buffer;
    }

    public void setInterior(List<AbstractRingPropertyType> value) {
        this.interior = null;
        if (value!= null) {
            List<AbstractRingPropertyType> draftl = this.getInterior();
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
        if (!super.equals(thisLocator, thatLocator, object, strategy)) {
            return false;
        }
        final PolygonType that = ((PolygonType) object);
        {
            AbstractRingPropertyType lhsExterior;
            lhsExterior = this.getExterior();
            AbstractRingPropertyType rhsExterior;
            rhsExterior = that.getExterior();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "exterior", lhsExterior), LocatorUtils.property(thatLocator, "exterior", rhsExterior), lhsExterior, rhsExterior, this.isSetExterior(), that.isSetExterior())) {
                return false;
            }
        }
        {
            List<AbstractRingPropertyType> lhsInterior;
            lhsInterior = (this.isSetInterior()?this.getInterior():null);
            List<AbstractRingPropertyType> rhsInterior;
            rhsInterior = (that.isSetInterior()?that.getInterior():null);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "interior", lhsInterior), LocatorUtils.property(thatLocator, "interior", rhsInterior), lhsInterior, rhsInterior, this.isSetInterior(), that.isSetInterior())) {
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
            AbstractRingPropertyType theExterior;
            theExterior = this.getExterior();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "exterior", theExterior), currentHashCode, theExterior, this.isSetExterior());
        }
        {
            List<AbstractRingPropertyType> theInterior;
            theInterior = (this.isSetInterior()?this.getInterior():null);
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "interior", theInterior), currentHashCode, theInterior, this.isSetInterior());
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
        if (draftCopy instanceof PolygonType) {
            final PolygonType copy = ((PolygonType) draftCopy);
            {
                java.lang.Boolean exteriorShouldBeCopiedAndSet = strategy.shouldBeCopiedAndSet(locator, this.isSetExterior());
                if (exteriorShouldBeCopiedAndSet == java.lang.Boolean.TRUE) {
                    AbstractRingPropertyType sourceExterior;
                    sourceExterior = this.getExterior();
                    AbstractRingPropertyType copyExterior = ((AbstractRingPropertyType) strategy.copy(LocatorUtils.property(locator, "exterior", sourceExterior), sourceExterior, this.isSetExterior()));
                    copy.setExterior(copyExterior);
                } else {
                    if (exteriorShouldBeCopiedAndSet == java.lang.Boolean.FALSE) {
                        copy.exterior = null;
                    }
                }
            }
            {
                java.lang.Boolean interiorShouldBeCopiedAndSet = strategy.shouldBeCopiedAndSet(locator, this.isSetInterior());
                if (interiorShouldBeCopiedAndSet == java.lang.Boolean.TRUE) {
                    List<AbstractRingPropertyType> sourceInterior;
                    sourceInterior = (this.isSetInterior()?this.getInterior():null);
                    @SuppressWarnings("unchecked")
                    List<AbstractRingPropertyType> copyInterior = ((List<AbstractRingPropertyType> ) strategy.copy(LocatorUtils.property(locator, "interior", sourceInterior), sourceInterior, this.isSetInterior()));
                    copy.setInterior(copyInterior);
                } else {
                    if (interiorShouldBeCopiedAndSet == java.lang.Boolean.FALSE) {
                        copy.unsetInterior();
                    }
                }
            }
        }
        return draftCopy;
    }

    @Override
    public Object createNewInstance() {
        return new PolygonType();
    }

    @Override
    public void mergeFrom(Object left, Object right) {
        final MergeStrategy strategy = JAXBMergeStrategy.getInstance();
        mergeFrom(null, null, left, right, strategy);
    }

    @Override
    public void mergeFrom(ObjectLocator leftLocator, ObjectLocator rightLocator, Object left, Object right, MergeStrategy strategy) {
        super.mergeFrom(leftLocator, rightLocator, left, right, strategy);
        if (right instanceof PolygonType) {
            final PolygonType target = this;
            final PolygonType leftObject = ((PolygonType) left);
            final PolygonType rightObject = ((PolygonType) right);
            {
                java.lang.Boolean exteriorShouldBeMergedAndSet = strategy.shouldBeMergedAndSet(leftLocator, rightLocator, leftObject.isSetExterior(), rightObject.isSetExterior());
                if (exteriorShouldBeMergedAndSet == java.lang.Boolean.TRUE) {
                    AbstractRingPropertyType lhsExterior;
                    lhsExterior = leftObject.getExterior();
                    AbstractRingPropertyType rhsExterior;
                    rhsExterior = rightObject.getExterior();
                    AbstractRingPropertyType mergedExterior = ((AbstractRingPropertyType) strategy.merge(LocatorUtils.property(leftLocator, "exterior", lhsExterior), LocatorUtils.property(rightLocator, "exterior", rhsExterior), lhsExterior, rhsExterior, leftObject.isSetExterior(), rightObject.isSetExterior()));
                    target.setExterior(mergedExterior);
                } else {
                    if (exteriorShouldBeMergedAndSet == java.lang.Boolean.FALSE) {
                        target.exterior = null;
                    }
                }
            }
            {
                java.lang.Boolean interiorShouldBeMergedAndSet = strategy.shouldBeMergedAndSet(leftLocator, rightLocator, leftObject.isSetInterior(), rightObject.isSetInterior());
                if (interiorShouldBeMergedAndSet == java.lang.Boolean.TRUE) {
                    List<AbstractRingPropertyType> lhsInterior;
                    lhsInterior = (leftObject.isSetInterior()?leftObject.getInterior():null);
                    List<AbstractRingPropertyType> rhsInterior;
                    rhsInterior = (rightObject.isSetInterior()?rightObject.getInterior():null);
                    List<AbstractRingPropertyType> mergedInterior = ((List<AbstractRingPropertyType> ) strategy.merge(LocatorUtils.property(leftLocator, "interior", lhsInterior), LocatorUtils.property(rightLocator, "interior", rhsInterior), lhsInterior, rhsInterior, leftObject.isSetInterior(), rightObject.isSetInterior()));
                    target.setInterior(mergedInterior);
                } else {
                    if (interiorShouldBeMergedAndSet == java.lang.Boolean.FALSE) {
                        target.unsetInterior();
                    }
                }
            }
        }
    }

}
