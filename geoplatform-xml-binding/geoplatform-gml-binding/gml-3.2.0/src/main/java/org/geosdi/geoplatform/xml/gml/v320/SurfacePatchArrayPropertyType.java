/**
 *
 *    geo-platform
 *    Rich webgis framework
 *    http://geo-platform.org
 *   ====================================================================
 *
 *   Copyright (C) 2008-2021 geoSDI Group (CNR IMAA - Potenza - ITALY).
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
// Questo file è stato generato dall'architettura JavaTM per XML Binding (JAXB) Reference Implementation, v2.2.7 
// Vedere <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Qualsiasi modifica a questo file andrà persa durante la ricompilazione dello schema di origine. 
// Generato il: 2015.06.18 alle 12:52:42 AM CEST 
//


package org.geosdi.geoplatform.xml.gml.v320;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;
import org.jvnet.jaxb2_commons.lang.CopyStrategy;
import org.jvnet.jaxb2_commons.lang.CopyTo;
import org.jvnet.jaxb2_commons.lang.Equals;
import org.jvnet.jaxb2_commons.lang.EqualsStrategy;
import org.jvnet.jaxb2_commons.lang.HashCode;
import org.jvnet.jaxb2_commons.lang.HashCodeStrategy;
import org.jvnet.jaxb2_commons.lang.JAXBCopyStrategy;
import org.jvnet.jaxb2_commons.lang.JAXBEqualsStrategy;
import org.jvnet.jaxb2_commons.lang.JAXBHashCodeStrategy;
import org.jvnet.jaxb2_commons.lang.JAXBMergeStrategy;
import org.jvnet.jaxb2_commons.lang.JAXBToStringStrategy;
import org.jvnet.jaxb2_commons.lang.MergeFrom;
import org.jvnet.jaxb2_commons.lang.MergeStrategy;
import org.jvnet.jaxb2_commons.lang.ToString;
import org.jvnet.jaxb2_commons.lang.ToStringStrategy;
import org.jvnet.jaxb2_commons.locator.ObjectLocator;
import org.jvnet.jaxb2_commons.locator.util.LocatorUtils;


/**
 * gml:SurfacePatchArrayPropertyType is a container for a sequence of surface patches.
 * 
 * <p>Classe Java per SurfacePatchArrayPropertyType complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="SurfacePatchArrayPropertyType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence maxOccurs="unbounded" minOccurs="0">
 *         &lt;element ref="{http://www.opengis.net/gml}AbstractSurfacePatch"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SurfacePatchArrayPropertyType", propOrder = {
    "abstractSurfacePatch"
})
@XmlSeeAlso({
    TrianglePatchArrayPropertyType.class,
    PolygonPatchArrayPropertyType.class
})
public class SurfacePatchArrayPropertyType implements Cloneable, CopyTo, Equals, HashCode, MergeFrom, ToString
{

    @XmlElementRef(name = "AbstractSurfacePatch", namespace = "http://www.opengis.net/gml", type = JAXBElement.class, required = false)
    protected List<JAXBElement<? extends AbstractSurfacePatchType>> abstractSurfacePatch;

    /**
     * Gets the value of the abstractSurfacePatch property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the abstractSurfacePatch property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAbstractSurfacePatch().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link JAXBElement }{@code <}{@link PolygonPatchType }{@code >}
     * {@link JAXBElement }{@code <}{@link ConeType }{@code >}
     * {@link JAXBElement }{@code <}{@link CylinderType }{@code >}
     * {@link JAXBElement }{@code <}{@link AbstractGriddedSurfaceType }{@code >}
     * {@link JAXBElement }{@code <}{@link AbstractSurfacePatchType }{@code >}
     * {@link JAXBElement }{@code <}{@link AbstractParametricCurveSurfaceType }{@code >}
     * {@link JAXBElement }{@code <}{@link SphereType }{@code >}
     * {@link JAXBElement }{@code <}{@link RectangleType }{@code >}
     * {@link JAXBElement }{@code <}{@link TriangleType }{@code >}
     * 
     * 
     */
    public List<JAXBElement<? extends AbstractSurfacePatchType>> getAbstractSurfacePatch() {
        if (abstractSurfacePatch == null) {
            abstractSurfacePatch = new ArrayList<JAXBElement<? extends AbstractSurfacePatchType>>();
        }
        return this.abstractSurfacePatch;
    }

    public boolean isSetAbstractSurfacePatch() {
        return ((this.abstractSurfacePatch!= null)&&(!this.abstractSurfacePatch.isEmpty()));
    }

    public void unsetAbstractSurfacePatch() {
        this.abstractSurfacePatch = null;
    }

    public String toString() {
        final ToStringStrategy strategy = JAXBToStringStrategy.INSTANCE;
        final StringBuilder buffer = new StringBuilder();
        append(null, buffer, strategy);
        return buffer.toString();
    }

    public StringBuilder append(ObjectLocator locator, StringBuilder buffer, ToStringStrategy strategy) {
        strategy.appendStart(locator, this, buffer);
        appendFields(locator, buffer, strategy);
        strategy.appendEnd(locator, this, buffer);
        return buffer;
    }

    public StringBuilder appendFields(ObjectLocator locator, StringBuilder buffer, ToStringStrategy strategy) {
        {
            List<JAXBElement<? extends AbstractSurfacePatchType>> theAbstractSurfacePatch;
            theAbstractSurfacePatch = this.getAbstractSurfacePatch();
            strategy.appendField(locator, this, "abstractSurfacePatch", buffer, theAbstractSurfacePatch);
        }
        return buffer;
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof SurfacePatchArrayPropertyType)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        final SurfacePatchArrayPropertyType that = ((SurfacePatchArrayPropertyType) object);
        {
            List<JAXBElement<? extends AbstractSurfacePatchType>> lhsAbstractSurfacePatch;
            lhsAbstractSurfacePatch = this.getAbstractSurfacePatch();
            List<JAXBElement<? extends AbstractSurfacePatchType>> rhsAbstractSurfacePatch;
            rhsAbstractSurfacePatch = that.getAbstractSurfacePatch();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "abstractSurfacePatch", lhsAbstractSurfacePatch), LocatorUtils.property(thatLocator, "abstractSurfacePatch", rhsAbstractSurfacePatch), lhsAbstractSurfacePatch, rhsAbstractSurfacePatch)) {
                return false;
            }
        }
        return true;
    }

    public boolean equals(Object object) {
        final EqualsStrategy strategy = JAXBEqualsStrategy.INSTANCE;
        return equals(null, null, object, strategy);
    }

    public int hashCode(ObjectLocator locator, HashCodeStrategy strategy) {
        int currentHashCode = 1;
        {
            List<JAXBElement<? extends AbstractSurfacePatchType>> theAbstractSurfacePatch;
            theAbstractSurfacePatch = this.getAbstractSurfacePatch();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "abstractSurfacePatch", theAbstractSurfacePatch), currentHashCode, theAbstractSurfacePatch);
        }
        return currentHashCode;
    }

    public int hashCode() {
        final HashCodeStrategy strategy = JAXBHashCodeStrategy.INSTANCE;
        return this.hashCode(null, strategy);
    }

    public Object clone() {
        return copyTo(createNewInstance());
    }

    public Object copyTo(Object target) {
        final CopyStrategy strategy = JAXBCopyStrategy.INSTANCE;
        return copyTo(null, target, strategy);
    }

    public Object copyTo(ObjectLocator locator, Object target, CopyStrategy strategy) {
        final Object draftCopy = ((target == null)?createNewInstance():target);
        if (draftCopy instanceof SurfacePatchArrayPropertyType) {
            final SurfacePatchArrayPropertyType copy = ((SurfacePatchArrayPropertyType) draftCopy);
            if (this.isSetAbstractSurfacePatch()) {
                List<JAXBElement<? extends AbstractSurfacePatchType>> sourceAbstractSurfacePatch;
                sourceAbstractSurfacePatch = this.getAbstractSurfacePatch();
                @SuppressWarnings("unchecked")
                List<JAXBElement<? extends AbstractSurfacePatchType>> copyAbstractSurfacePatch = ((List<JAXBElement<? extends AbstractSurfacePatchType>> ) strategy.copy(LocatorUtils.property(locator, "abstractSurfacePatch", sourceAbstractSurfacePatch), sourceAbstractSurfacePatch));
                copy.unsetAbstractSurfacePatch();
                List<JAXBElement<? extends AbstractSurfacePatchType>> uniqueAbstractSurfacePatchl = copy.getAbstractSurfacePatch();
                uniqueAbstractSurfacePatchl.addAll(copyAbstractSurfacePatch);
            } else {
                copy.unsetAbstractSurfacePatch();
            }
        }
        return draftCopy;
    }

    public Object createNewInstance() {
        return new SurfacePatchArrayPropertyType();
    }

    public void mergeFrom(Object left, Object right) {
        final MergeStrategy strategy = JAXBMergeStrategy.INSTANCE;
        mergeFrom(null, null, left, right, strategy);
    }

    public void mergeFrom(ObjectLocator leftLocator, ObjectLocator rightLocator, Object left, Object right, MergeStrategy strategy) {
        if (right instanceof SurfacePatchArrayPropertyType) {
            final SurfacePatchArrayPropertyType target = this;
            final SurfacePatchArrayPropertyType leftObject = ((SurfacePatchArrayPropertyType) left);
            final SurfacePatchArrayPropertyType rightObject = ((SurfacePatchArrayPropertyType) right);
            {
                List<JAXBElement<? extends AbstractSurfacePatchType>> lhsAbstractSurfacePatch;
                lhsAbstractSurfacePatch = leftObject.getAbstractSurfacePatch();
                List<JAXBElement<? extends AbstractSurfacePatchType>> rhsAbstractSurfacePatch;
                rhsAbstractSurfacePatch = rightObject.getAbstractSurfacePatch();
                target.unsetAbstractSurfacePatch();
                List<JAXBElement<? extends AbstractSurfacePatchType>> uniqueAbstractSurfacePatchl = target.getAbstractSurfacePatch();
                uniqueAbstractSurfacePatchl.addAll(((List<JAXBElement<? extends AbstractSurfacePatchType>> ) strategy.merge(LocatorUtils.property(leftLocator, "abstractSurfacePatch", lhsAbstractSurfacePatch), LocatorUtils.property(rightLocator, "abstractSurfacePatch", rhsAbstractSurfacePatch), lhsAbstractSurfacePatch, rhsAbstractSurfacePatch)));
            }
        }
    }

    public void setAbstractSurfacePatch(List<JAXBElement<? extends AbstractSurfacePatchType>> value) {
        List<JAXBElement<? extends AbstractSurfacePatchType>> draftl = this.getAbstractSurfacePatch();
        draftl.addAll(value);
    }

    public SurfacePatchArrayPropertyType withAbstractSurfacePatch(JAXBElement<? extends AbstractSurfacePatchType> ... values) {
        if (values!= null) {
            for (JAXBElement<? extends AbstractSurfacePatchType> value: values) {
                getAbstractSurfacePatch().add(value);
            }
        }
        return this;
    }

    public SurfacePatchArrayPropertyType withAbstractSurfacePatch(Collection<JAXBElement<? extends AbstractSurfacePatchType>> values) {
        if (values!= null) {
            getAbstractSurfacePatch().addAll(values);
        }
        return this;
    }

    public SurfacePatchArrayPropertyType withAbstractSurfacePatch(List<JAXBElement<? extends AbstractSurfacePatchType>> value) {
        setAbstractSurfacePatch(value);
        return this;
    }

}
