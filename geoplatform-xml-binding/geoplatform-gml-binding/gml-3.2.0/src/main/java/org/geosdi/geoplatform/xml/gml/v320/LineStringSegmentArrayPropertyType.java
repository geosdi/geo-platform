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
 * gml:LineStringSegmentArrayPropertyType provides a container for line strings.
 * 
 * <p>Classe Java per LineStringSegmentArrayPropertyType complex type.</p>
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.</p>
 * 
 * <pre>{@code
 * <complexType name="LineStringSegmentArrayPropertyType">
 *   <complexContent>
 *     <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       <sequence maxOccurs="unbounded" minOccurs="0">
 *         <element ref="{http://www.opengis.net/gml}LineStringSegment"/>
 *       </sequence>
 *     </restriction>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "LineStringSegmentArrayPropertyType", propOrder = {
    "lineStringSegment"
})
public class LineStringSegmentArrayPropertyType implements Cloneable, CopyTo, Equals, HashCode, MergeFrom, ToString
{

    @XmlElement(name = "LineStringSegment")
    protected List<LineStringSegmentType> lineStringSegment;

    /**
     * Gets the value of the lineStringSegment property.
     * 
     * <p>This accessor method returns a reference to the live list,
     * not a snapshot. Therefore, any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a {@code set} method for the lineStringSegment property.</p>
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * </p>
     * <pre>
     * getLineStringSegment().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link LineStringSegmentType }
     * </p>
     * 
     * 
     * @return
     *     The value of the lineStringSegment property.
     */
    public List<LineStringSegmentType> getLineStringSegment() {
        if (lineStringSegment == null) {
            lineStringSegment = new ArrayList<>();
        }
        return this.lineStringSegment;
    }

    public boolean isSetLineStringSegment() {
        return ((this.lineStringSegment!= null)&&(!this.lineStringSegment.isEmpty()));
    }

    public void unsetLineStringSegment() {
        this.lineStringSegment = null;
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
            List<LineStringSegmentType> theLineStringSegment;
            theLineStringSegment = (this.isSetLineStringSegment()?this.getLineStringSegment():null);
            strategy.appendField(locator, this, "lineStringSegment", buffer, theLineStringSegment, this.isSetLineStringSegment());
        }
        return buffer;
    }

    public void setLineStringSegment(List<LineStringSegmentType> value) {
        this.lineStringSegment = null;
        if (value!= null) {
            List<LineStringSegmentType> draftl = this.getLineStringSegment();
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
        final LineStringSegmentArrayPropertyType that = ((LineStringSegmentArrayPropertyType) object);
        {
            List<LineStringSegmentType> lhsLineStringSegment;
            lhsLineStringSegment = (this.isSetLineStringSegment()?this.getLineStringSegment():null);
            List<LineStringSegmentType> rhsLineStringSegment;
            rhsLineStringSegment = (that.isSetLineStringSegment()?that.getLineStringSegment():null);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "lineStringSegment", lhsLineStringSegment), LocatorUtils.property(thatLocator, "lineStringSegment", rhsLineStringSegment), lhsLineStringSegment, rhsLineStringSegment, this.isSetLineStringSegment(), that.isSetLineStringSegment())) {
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
            List<LineStringSegmentType> theLineStringSegment;
            theLineStringSegment = (this.isSetLineStringSegment()?this.getLineStringSegment():null);
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "lineStringSegment", theLineStringSegment), currentHashCode, theLineStringSegment, this.isSetLineStringSegment());
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
        if (draftCopy instanceof LineStringSegmentArrayPropertyType) {
            final LineStringSegmentArrayPropertyType copy = ((LineStringSegmentArrayPropertyType) draftCopy);
            {
                java.lang.Boolean lineStringSegmentShouldBeCopiedAndSet = strategy.shouldBeCopiedAndSet(locator, this.isSetLineStringSegment());
                if (lineStringSegmentShouldBeCopiedAndSet == java.lang.Boolean.TRUE) {
                    List<LineStringSegmentType> sourceLineStringSegment;
                    sourceLineStringSegment = (this.isSetLineStringSegment()?this.getLineStringSegment():null);
                    @SuppressWarnings("unchecked")
                    List<LineStringSegmentType> copyLineStringSegment = ((List<LineStringSegmentType> ) strategy.copy(LocatorUtils.property(locator, "lineStringSegment", sourceLineStringSegment), sourceLineStringSegment, this.isSetLineStringSegment()));
                    copy.setLineStringSegment(copyLineStringSegment);
                } else {
                    if (lineStringSegmentShouldBeCopiedAndSet == java.lang.Boolean.FALSE) {
                        copy.unsetLineStringSegment();
                    }
                }
            }
        }
        return draftCopy;
    }

    @Override
    public Object createNewInstance() {
        return new LineStringSegmentArrayPropertyType();
    }

    @Override
    public void mergeFrom(Object left, Object right) {
        final MergeStrategy strategy = JAXBMergeStrategy.getInstance();
        mergeFrom(null, null, left, right, strategy);
    }

    @Override
    public void mergeFrom(ObjectLocator leftLocator, ObjectLocator rightLocator, Object left, Object right, MergeStrategy strategy) {
        if (right instanceof LineStringSegmentArrayPropertyType) {
            final LineStringSegmentArrayPropertyType target = this;
            final LineStringSegmentArrayPropertyType leftObject = ((LineStringSegmentArrayPropertyType) left);
            final LineStringSegmentArrayPropertyType rightObject = ((LineStringSegmentArrayPropertyType) right);
            {
                java.lang.Boolean lineStringSegmentShouldBeMergedAndSet = strategy.shouldBeMergedAndSet(leftLocator, rightLocator, leftObject.isSetLineStringSegment(), rightObject.isSetLineStringSegment());
                if (lineStringSegmentShouldBeMergedAndSet == java.lang.Boolean.TRUE) {
                    List<LineStringSegmentType> lhsLineStringSegment;
                    lhsLineStringSegment = (leftObject.isSetLineStringSegment()?leftObject.getLineStringSegment():null);
                    List<LineStringSegmentType> rhsLineStringSegment;
                    rhsLineStringSegment = (rightObject.isSetLineStringSegment()?rightObject.getLineStringSegment():null);
                    List<LineStringSegmentType> mergedLineStringSegment = ((List<LineStringSegmentType> ) strategy.merge(LocatorUtils.property(leftLocator, "lineStringSegment", lhsLineStringSegment), LocatorUtils.property(rightLocator, "lineStringSegment", rhsLineStringSegment), lhsLineStringSegment, rhsLineStringSegment, leftObject.isSetLineStringSegment(), rightObject.isSetLineStringSegment()));
                    target.setLineStringSegment(mergedLineStringSegment);
                } else {
                    if (lineStringSegmentShouldBeMergedAndSet == java.lang.Boolean.FALSE) {
                        target.unsetLineStringSegment();
                    }
                }
            }
        }
    }

}
