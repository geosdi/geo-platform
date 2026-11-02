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
 * gml:SurfacePatchArrayPropertyType is a container for a sequence of surface patches.
 * 
 * <p>Classe Java per SurfacePatchArrayPropertyType complex type.</p>
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.</p>
 * 
 * <pre>{@code
 * <complexType name="SurfacePatchArrayPropertyType">
 *   <complexContent>
 *     <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       <sequence maxOccurs="unbounded" minOccurs="0">
 *         <element ref="{http://www.opengis.net/gml}AbstractSurfacePatch"/>
 *       </sequence>
 *     </restriction>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SurfacePatchArrayPropertyType", propOrder = {
    "abstractSurfacePatch"
})
@XmlSeeAlso({
    PolygonPatchArrayPropertyType.class,
    TrianglePatchArrayPropertyType.class
})
public class SurfacePatchArrayPropertyType implements Cloneable, CopyTo, Equals, HashCode, MergeFrom, ToString
{

    @XmlElementRef(name = "AbstractSurfacePatch", namespace = "http://www.opengis.net/gml", type = JAXBElement.class, required = false)
    protected List<JAXBElement<? extends AbstractSurfacePatchType>> abstractSurfacePatch;

    /**
     * Gets the value of the abstractSurfacePatch property.
     * 
     * <p>This accessor method returns a reference to the live list,
     * not a snapshot. Therefore, any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a {@code set} method for the abstractSurfacePatch property.</p>
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * </p>
     * <pre>
     * getAbstractSurfacePatch().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link JAXBElement }{@code <}{@link AbstractGriddedSurfaceType }{@code >}
     * {@link JAXBElement }{@code <}{@link AbstractParametricCurveSurfaceType }{@code >}
     * {@link JAXBElement }{@code <}{@link AbstractSurfacePatchType }{@code >}
     * {@link JAXBElement }{@code <}{@link ConeType }{@code >}
     * {@link JAXBElement }{@code <}{@link CylinderType }{@code >}
     * {@link JAXBElement }{@code <}{@link PolygonPatchType }{@code >}
     * {@link JAXBElement }{@code <}{@link RectangleType }{@code >}
     * {@link JAXBElement }{@code <}{@link SphereType }{@code >}
     * {@link JAXBElement }{@code <}{@link TriangleType }{@code >}
     * </p>
     * 
     * 
     * @return
     *     The value of the abstractSurfacePatch property.
     */
    public List<JAXBElement<? extends AbstractSurfacePatchType>> getAbstractSurfacePatch() {
        if (abstractSurfacePatch == null) {
            abstractSurfacePatch = new ArrayList<>();
        }
        return this.abstractSurfacePatch;
    }

    public boolean isSetAbstractSurfacePatch() {
        return ((this.abstractSurfacePatch!= null)&&(!this.abstractSurfacePatch.isEmpty()));
    }

    public void unsetAbstractSurfacePatch() {
        this.abstractSurfacePatch = null;
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
            List<JAXBElement<? extends AbstractSurfacePatchType>> theAbstractSurfacePatch;
            theAbstractSurfacePatch = (this.isSetAbstractSurfacePatch()?this.getAbstractSurfacePatch():null);
            strategy.appendField(locator, this, "abstractSurfacePatch", buffer, theAbstractSurfacePatch, this.isSetAbstractSurfacePatch());
        }
        return buffer;
    }

    public void setAbstractSurfacePatch(List<JAXBElement<? extends AbstractSurfacePatchType>> value) {
        this.abstractSurfacePatch = null;
        if (value!= null) {
            List<JAXBElement<? extends AbstractSurfacePatchType>> draftl = this.getAbstractSurfacePatch();
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
        final SurfacePatchArrayPropertyType that = ((SurfacePatchArrayPropertyType) object);
        {
            List<JAXBElement<? extends AbstractSurfacePatchType>> lhsAbstractSurfacePatch;
            lhsAbstractSurfacePatch = (this.isSetAbstractSurfacePatch()?this.getAbstractSurfacePatch():null);
            List<JAXBElement<? extends AbstractSurfacePatchType>> rhsAbstractSurfacePatch;
            rhsAbstractSurfacePatch = (that.isSetAbstractSurfacePatch()?that.getAbstractSurfacePatch():null);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "abstractSurfacePatch", lhsAbstractSurfacePatch), LocatorUtils.property(thatLocator, "abstractSurfacePatch", rhsAbstractSurfacePatch), lhsAbstractSurfacePatch, rhsAbstractSurfacePatch, this.isSetAbstractSurfacePatch(), that.isSetAbstractSurfacePatch())) {
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
            List<JAXBElement<? extends AbstractSurfacePatchType>> theAbstractSurfacePatch;
            theAbstractSurfacePatch = (this.isSetAbstractSurfacePatch()?this.getAbstractSurfacePatch():null);
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "abstractSurfacePatch", theAbstractSurfacePatch), currentHashCode, theAbstractSurfacePatch, this.isSetAbstractSurfacePatch());
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
        if (draftCopy instanceof SurfacePatchArrayPropertyType) {
            final SurfacePatchArrayPropertyType copy = ((SurfacePatchArrayPropertyType) draftCopy);
            {
                java.lang.Boolean abstractSurfacePatchShouldBeCopiedAndSet = strategy.shouldBeCopiedAndSet(locator, this.isSetAbstractSurfacePatch());
                if (abstractSurfacePatchShouldBeCopiedAndSet == java.lang.Boolean.TRUE) {
                    List<JAXBElement<? extends AbstractSurfacePatchType>> sourceAbstractSurfacePatch;
                    sourceAbstractSurfacePatch = (this.isSetAbstractSurfacePatch()?this.getAbstractSurfacePatch():null);
                    @SuppressWarnings("unchecked")
                    List<JAXBElement<? extends AbstractSurfacePatchType>> copyAbstractSurfacePatch = ((List<JAXBElement<? extends AbstractSurfacePatchType>> ) strategy.copy(LocatorUtils.property(locator, "abstractSurfacePatch", sourceAbstractSurfacePatch), sourceAbstractSurfacePatch, this.isSetAbstractSurfacePatch()));
                    copy.setAbstractSurfacePatch(copyAbstractSurfacePatch);
                } else {
                    if (abstractSurfacePatchShouldBeCopiedAndSet == java.lang.Boolean.FALSE) {
                        copy.unsetAbstractSurfacePatch();
                    }
                }
            }
        }
        return draftCopy;
    }

    @Override
    public Object createNewInstance() {
        return new SurfacePatchArrayPropertyType();
    }

    @Override
    public void mergeFrom(Object left, Object right) {
        final MergeStrategy strategy = JAXBMergeStrategy.getInstance();
        mergeFrom(null, null, left, right, strategy);
    }

    @Override
    public void mergeFrom(ObjectLocator leftLocator, ObjectLocator rightLocator, Object left, Object right, MergeStrategy strategy) {
        if (right instanceof SurfacePatchArrayPropertyType) {
            final SurfacePatchArrayPropertyType target = this;
            final SurfacePatchArrayPropertyType leftObject = ((SurfacePatchArrayPropertyType) left);
            final SurfacePatchArrayPropertyType rightObject = ((SurfacePatchArrayPropertyType) right);
            {
                java.lang.Boolean abstractSurfacePatchShouldBeMergedAndSet = strategy.shouldBeMergedAndSet(leftLocator, rightLocator, leftObject.isSetAbstractSurfacePatch(), rightObject.isSetAbstractSurfacePatch());
                if (abstractSurfacePatchShouldBeMergedAndSet == java.lang.Boolean.TRUE) {
                    List<JAXBElement<? extends AbstractSurfacePatchType>> lhsAbstractSurfacePatch;
                    lhsAbstractSurfacePatch = (leftObject.isSetAbstractSurfacePatch()?leftObject.getAbstractSurfacePatch():null);
                    List<JAXBElement<? extends AbstractSurfacePatchType>> rhsAbstractSurfacePatch;
                    rhsAbstractSurfacePatch = (rightObject.isSetAbstractSurfacePatch()?rightObject.getAbstractSurfacePatch():null);
                    List<JAXBElement<? extends AbstractSurfacePatchType>> mergedAbstractSurfacePatch = ((List<JAXBElement<? extends AbstractSurfacePatchType>> ) strategy.merge(LocatorUtils.property(leftLocator, "abstractSurfacePatch", lhsAbstractSurfacePatch), LocatorUtils.property(rightLocator, "abstractSurfacePatch", rhsAbstractSurfacePatch), lhsAbstractSurfacePatch, rhsAbstractSurfacePatch, leftObject.isSetAbstractSurfacePatch(), rightObject.isSetAbstractSurfacePatch()));
                    target.setAbstractSurfacePatch(mergedAbstractSurfacePatch);
                } else {
                    if (abstractSurfacePatchShouldBeMergedAndSet == java.lang.Boolean.FALSE) {
                        target.unsetAbstractSurfacePatch();
                    }
                }
            }
        }
    }

}
