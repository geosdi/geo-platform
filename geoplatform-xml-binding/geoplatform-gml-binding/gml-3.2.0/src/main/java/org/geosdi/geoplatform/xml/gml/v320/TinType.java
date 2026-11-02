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
import jakarta.xml.bind.annotation.XmlElements;
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
 * <p>Classe Java per TinType complex type.</p>
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.</p>
 * 
 * <pre>{@code
 * <complexType name="TinType">
 *   <complexContent>
 *     <extension base="{http://www.opengis.net/gml}TriangulatedSurfaceType">
 *       <sequence>
 *         <element name="stopLines" type="{http://www.opengis.net/gml}LineStringSegmentArrayPropertyType" maxOccurs="unbounded" minOccurs="0"/>
 *         <element name="breakLines" type="{http://www.opengis.net/gml}LineStringSegmentArrayPropertyType" maxOccurs="unbounded" minOccurs="0"/>
 *         <element name="maxLength" type="{http://www.opengis.net/gml}LengthType"/>
 *         <element name="controlPoint">
 *           <complexType>
 *             <complexContent>
 *               <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 <choice>
 *                   <element ref="{http://www.opengis.net/gml}posList"/>
 *                   <group ref="{http://www.opengis.net/gml}geometricPositionGroup" maxOccurs="unbounded" minOccurs="3"/>
 *                 </choice>
 *               </restriction>
 *             </complexContent>
 *           </complexType>
 *         </element>
 *       </sequence>
 *     </extension>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TinType", propOrder = {
    "stopLines",
    "breakLines",
    "maxLength",
    "controlPoint"
})
public class TinType
    extends TriangulatedSurfaceType
    implements Cloneable, CopyTo, Equals, HashCode, MergeFrom, ToString
{

    protected List<LineStringSegmentArrayPropertyType> stopLines;
    protected List<LineStringSegmentArrayPropertyType> breakLines;
    @XmlElement(required = true)
    protected LengthType maxLength;
    @XmlElement(required = true)
    protected TinType.ControlPoint controlPoint;

    /**
     * Gets the value of the stopLines property.
     * 
     * <p>This accessor method returns a reference to the live list,
     * not a snapshot. Therefore, any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a {@code set} method for the stopLines property.</p>
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * </p>
     * <pre>
     * getStopLines().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link LineStringSegmentArrayPropertyType }
     * </p>
     * 
     * 
     * @return
     *     The value of the stopLines property.
     */
    public List<LineStringSegmentArrayPropertyType> getStopLines() {
        if (stopLines == null) {
            stopLines = new ArrayList<>();
        }
        return this.stopLines;
    }

    public boolean isSetStopLines() {
        return ((this.stopLines!= null)&&(!this.stopLines.isEmpty()));
    }

    public void unsetStopLines() {
        this.stopLines = null;
    }

    /**
     * Gets the value of the breakLines property.
     * 
     * <p>This accessor method returns a reference to the live list,
     * not a snapshot. Therefore, any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a {@code set} method for the breakLines property.</p>
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * </p>
     * <pre>
     * getBreakLines().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link LineStringSegmentArrayPropertyType }
     * </p>
     * 
     * 
     * @return
     *     The value of the breakLines property.
     */
    public List<LineStringSegmentArrayPropertyType> getBreakLines() {
        if (breakLines == null) {
            breakLines = new ArrayList<>();
        }
        return this.breakLines;
    }

    public boolean isSetBreakLines() {
        return ((this.breakLines!= null)&&(!this.breakLines.isEmpty()));
    }

    public void unsetBreakLines() {
        this.breakLines = null;
    }

    /**
     * Recupera il valore della proprietà maxLength.
     * 
     * @return
     *     possible object is
     *     {@link LengthType }
     *     
     */
    public LengthType getMaxLength() {
        return maxLength;
    }

    /**
     * Imposta il valore della proprietà maxLength.
     * 
     * @param value
     *     allowed object is
     *     {@link LengthType }
     *     
     */
    public void setMaxLength(LengthType value) {
        this.maxLength = value;
    }

    public boolean isSetMaxLength() {
        return (this.maxLength!= null);
    }

    /**
     * Recupera il valore della proprietà controlPoint.
     * 
     * @return
     *     possible object is
     *     {@link TinType.ControlPoint }
     *     
     */
    public TinType.ControlPoint getControlPoint() {
        return controlPoint;
    }

    /**
     * Imposta il valore della proprietà controlPoint.
     * 
     * @param value
     *     allowed object is
     *     {@link TinType.ControlPoint }
     *     
     */
    public void setControlPoint(TinType.ControlPoint value) {
        this.controlPoint = value;
    }

    public boolean isSetControlPoint() {
        return (this.controlPoint!= null);
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
            List<LineStringSegmentArrayPropertyType> theStopLines;
            theStopLines = (this.isSetStopLines()?this.getStopLines():null);
            strategy.appendField(locator, this, "stopLines", buffer, theStopLines, this.isSetStopLines());
        }
        {
            List<LineStringSegmentArrayPropertyType> theBreakLines;
            theBreakLines = (this.isSetBreakLines()?this.getBreakLines():null);
            strategy.appendField(locator, this, "breakLines", buffer, theBreakLines, this.isSetBreakLines());
        }
        {
            LengthType theMaxLength;
            theMaxLength = this.getMaxLength();
            strategy.appendField(locator, this, "maxLength", buffer, theMaxLength, this.isSetMaxLength());
        }
        {
            TinType.ControlPoint theControlPoint;
            theControlPoint = this.getControlPoint();
            strategy.appendField(locator, this, "controlPoint", buffer, theControlPoint, this.isSetControlPoint());
        }
        return buffer;
    }

    public void setStopLines(List<LineStringSegmentArrayPropertyType> value) {
        this.stopLines = null;
        if (value!= null) {
            List<LineStringSegmentArrayPropertyType> draftl = this.getStopLines();
            draftl.addAll(value);
        }
    }

    public void setBreakLines(List<LineStringSegmentArrayPropertyType> value) {
        this.breakLines = null;
        if (value!= null) {
            List<LineStringSegmentArrayPropertyType> draftl = this.getBreakLines();
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
        final TinType that = ((TinType) object);
        {
            List<LineStringSegmentArrayPropertyType> lhsStopLines;
            lhsStopLines = (this.isSetStopLines()?this.getStopLines():null);
            List<LineStringSegmentArrayPropertyType> rhsStopLines;
            rhsStopLines = (that.isSetStopLines()?that.getStopLines():null);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "stopLines", lhsStopLines), LocatorUtils.property(thatLocator, "stopLines", rhsStopLines), lhsStopLines, rhsStopLines, this.isSetStopLines(), that.isSetStopLines())) {
                return false;
            }
        }
        {
            List<LineStringSegmentArrayPropertyType> lhsBreakLines;
            lhsBreakLines = (this.isSetBreakLines()?this.getBreakLines():null);
            List<LineStringSegmentArrayPropertyType> rhsBreakLines;
            rhsBreakLines = (that.isSetBreakLines()?that.getBreakLines():null);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "breakLines", lhsBreakLines), LocatorUtils.property(thatLocator, "breakLines", rhsBreakLines), lhsBreakLines, rhsBreakLines, this.isSetBreakLines(), that.isSetBreakLines())) {
                return false;
            }
        }
        {
            LengthType lhsMaxLength;
            lhsMaxLength = this.getMaxLength();
            LengthType rhsMaxLength;
            rhsMaxLength = that.getMaxLength();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "maxLength", lhsMaxLength), LocatorUtils.property(thatLocator, "maxLength", rhsMaxLength), lhsMaxLength, rhsMaxLength, this.isSetMaxLength(), that.isSetMaxLength())) {
                return false;
            }
        }
        {
            TinType.ControlPoint lhsControlPoint;
            lhsControlPoint = this.getControlPoint();
            TinType.ControlPoint rhsControlPoint;
            rhsControlPoint = that.getControlPoint();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "controlPoint", lhsControlPoint), LocatorUtils.property(thatLocator, "controlPoint", rhsControlPoint), lhsControlPoint, rhsControlPoint, this.isSetControlPoint(), that.isSetControlPoint())) {
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
            List<LineStringSegmentArrayPropertyType> theStopLines;
            theStopLines = (this.isSetStopLines()?this.getStopLines():null);
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "stopLines", theStopLines), currentHashCode, theStopLines, this.isSetStopLines());
        }
        {
            List<LineStringSegmentArrayPropertyType> theBreakLines;
            theBreakLines = (this.isSetBreakLines()?this.getBreakLines():null);
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "breakLines", theBreakLines), currentHashCode, theBreakLines, this.isSetBreakLines());
        }
        {
            LengthType theMaxLength;
            theMaxLength = this.getMaxLength();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "maxLength", theMaxLength), currentHashCode, theMaxLength, this.isSetMaxLength());
        }
        {
            TinType.ControlPoint theControlPoint;
            theControlPoint = this.getControlPoint();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "controlPoint", theControlPoint), currentHashCode, theControlPoint, this.isSetControlPoint());
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
        if (draftCopy instanceof TinType) {
            final TinType copy = ((TinType) draftCopy);
            {
                java.lang.Boolean stopLinesShouldBeCopiedAndSet = strategy.shouldBeCopiedAndSet(locator, this.isSetStopLines());
                if (stopLinesShouldBeCopiedAndSet == java.lang.Boolean.TRUE) {
                    List<LineStringSegmentArrayPropertyType> sourceStopLines;
                    sourceStopLines = (this.isSetStopLines()?this.getStopLines():null);
                    @SuppressWarnings("unchecked")
                    List<LineStringSegmentArrayPropertyType> copyStopLines = ((List<LineStringSegmentArrayPropertyType> ) strategy.copy(LocatorUtils.property(locator, "stopLines", sourceStopLines), sourceStopLines, this.isSetStopLines()));
                    copy.setStopLines(copyStopLines);
                } else {
                    if (stopLinesShouldBeCopiedAndSet == java.lang.Boolean.FALSE) {
                        copy.unsetStopLines();
                    }
                }
            }
            {
                java.lang.Boolean breakLinesShouldBeCopiedAndSet = strategy.shouldBeCopiedAndSet(locator, this.isSetBreakLines());
                if (breakLinesShouldBeCopiedAndSet == java.lang.Boolean.TRUE) {
                    List<LineStringSegmentArrayPropertyType> sourceBreakLines;
                    sourceBreakLines = (this.isSetBreakLines()?this.getBreakLines():null);
                    @SuppressWarnings("unchecked")
                    List<LineStringSegmentArrayPropertyType> copyBreakLines = ((List<LineStringSegmentArrayPropertyType> ) strategy.copy(LocatorUtils.property(locator, "breakLines", sourceBreakLines), sourceBreakLines, this.isSetBreakLines()));
                    copy.setBreakLines(copyBreakLines);
                } else {
                    if (breakLinesShouldBeCopiedAndSet == java.lang.Boolean.FALSE) {
                        copy.unsetBreakLines();
                    }
                }
            }
            {
                java.lang.Boolean maxLengthShouldBeCopiedAndSet = strategy.shouldBeCopiedAndSet(locator, this.isSetMaxLength());
                if (maxLengthShouldBeCopiedAndSet == java.lang.Boolean.TRUE) {
                    LengthType sourceMaxLength;
                    sourceMaxLength = this.getMaxLength();
                    LengthType copyMaxLength = ((LengthType) strategy.copy(LocatorUtils.property(locator, "maxLength", sourceMaxLength), sourceMaxLength, this.isSetMaxLength()));
                    copy.setMaxLength(copyMaxLength);
                } else {
                    if (maxLengthShouldBeCopiedAndSet == java.lang.Boolean.FALSE) {
                        copy.maxLength = null;
                    }
                }
            }
            {
                java.lang.Boolean controlPointShouldBeCopiedAndSet = strategy.shouldBeCopiedAndSet(locator, this.isSetControlPoint());
                if (controlPointShouldBeCopiedAndSet == java.lang.Boolean.TRUE) {
                    TinType.ControlPoint sourceControlPoint;
                    sourceControlPoint = this.getControlPoint();
                    TinType.ControlPoint copyControlPoint = ((TinType.ControlPoint) strategy.copy(LocatorUtils.property(locator, "controlPoint", sourceControlPoint), sourceControlPoint, this.isSetControlPoint()));
                    copy.setControlPoint(copyControlPoint);
                } else {
                    if (controlPointShouldBeCopiedAndSet == java.lang.Boolean.FALSE) {
                        copy.controlPoint = null;
                    }
                }
            }
        }
        return draftCopy;
    }

    @Override
    public Object createNewInstance() {
        return new TinType();
    }

    @Override
    public void mergeFrom(Object left, Object right) {
        final MergeStrategy strategy = JAXBMergeStrategy.getInstance();
        mergeFrom(null, null, left, right, strategy);
    }

    @Override
    public void mergeFrom(ObjectLocator leftLocator, ObjectLocator rightLocator, Object left, Object right, MergeStrategy strategy) {
        super.mergeFrom(leftLocator, rightLocator, left, right, strategy);
        if (right instanceof TinType) {
            final TinType target = this;
            final TinType leftObject = ((TinType) left);
            final TinType rightObject = ((TinType) right);
            {
                java.lang.Boolean stopLinesShouldBeMergedAndSet = strategy.shouldBeMergedAndSet(leftLocator, rightLocator, leftObject.isSetStopLines(), rightObject.isSetStopLines());
                if (stopLinesShouldBeMergedAndSet == java.lang.Boolean.TRUE) {
                    List<LineStringSegmentArrayPropertyType> lhsStopLines;
                    lhsStopLines = (leftObject.isSetStopLines()?leftObject.getStopLines():null);
                    List<LineStringSegmentArrayPropertyType> rhsStopLines;
                    rhsStopLines = (rightObject.isSetStopLines()?rightObject.getStopLines():null);
                    List<LineStringSegmentArrayPropertyType> mergedStopLines = ((List<LineStringSegmentArrayPropertyType> ) strategy.merge(LocatorUtils.property(leftLocator, "stopLines", lhsStopLines), LocatorUtils.property(rightLocator, "stopLines", rhsStopLines), lhsStopLines, rhsStopLines, leftObject.isSetStopLines(), rightObject.isSetStopLines()));
                    target.setStopLines(mergedStopLines);
                } else {
                    if (stopLinesShouldBeMergedAndSet == java.lang.Boolean.FALSE) {
                        target.unsetStopLines();
                    }
                }
            }
            {
                java.lang.Boolean breakLinesShouldBeMergedAndSet = strategy.shouldBeMergedAndSet(leftLocator, rightLocator, leftObject.isSetBreakLines(), rightObject.isSetBreakLines());
                if (breakLinesShouldBeMergedAndSet == java.lang.Boolean.TRUE) {
                    List<LineStringSegmentArrayPropertyType> lhsBreakLines;
                    lhsBreakLines = (leftObject.isSetBreakLines()?leftObject.getBreakLines():null);
                    List<LineStringSegmentArrayPropertyType> rhsBreakLines;
                    rhsBreakLines = (rightObject.isSetBreakLines()?rightObject.getBreakLines():null);
                    List<LineStringSegmentArrayPropertyType> mergedBreakLines = ((List<LineStringSegmentArrayPropertyType> ) strategy.merge(LocatorUtils.property(leftLocator, "breakLines", lhsBreakLines), LocatorUtils.property(rightLocator, "breakLines", rhsBreakLines), lhsBreakLines, rhsBreakLines, leftObject.isSetBreakLines(), rightObject.isSetBreakLines()));
                    target.setBreakLines(mergedBreakLines);
                } else {
                    if (breakLinesShouldBeMergedAndSet == java.lang.Boolean.FALSE) {
                        target.unsetBreakLines();
                    }
                }
            }
            {
                java.lang.Boolean maxLengthShouldBeMergedAndSet = strategy.shouldBeMergedAndSet(leftLocator, rightLocator, leftObject.isSetMaxLength(), rightObject.isSetMaxLength());
                if (maxLengthShouldBeMergedAndSet == java.lang.Boolean.TRUE) {
                    LengthType lhsMaxLength;
                    lhsMaxLength = leftObject.getMaxLength();
                    LengthType rhsMaxLength;
                    rhsMaxLength = rightObject.getMaxLength();
                    LengthType mergedMaxLength = ((LengthType) strategy.merge(LocatorUtils.property(leftLocator, "maxLength", lhsMaxLength), LocatorUtils.property(rightLocator, "maxLength", rhsMaxLength), lhsMaxLength, rhsMaxLength, leftObject.isSetMaxLength(), rightObject.isSetMaxLength()));
                    target.setMaxLength(mergedMaxLength);
                } else {
                    if (maxLengthShouldBeMergedAndSet == java.lang.Boolean.FALSE) {
                        target.maxLength = null;
                    }
                }
            }
            {
                java.lang.Boolean controlPointShouldBeMergedAndSet = strategy.shouldBeMergedAndSet(leftLocator, rightLocator, leftObject.isSetControlPoint(), rightObject.isSetControlPoint());
                if (controlPointShouldBeMergedAndSet == java.lang.Boolean.TRUE) {
                    TinType.ControlPoint lhsControlPoint;
                    lhsControlPoint = leftObject.getControlPoint();
                    TinType.ControlPoint rhsControlPoint;
                    rhsControlPoint = rightObject.getControlPoint();
                    TinType.ControlPoint mergedControlPoint = ((TinType.ControlPoint) strategy.merge(LocatorUtils.property(leftLocator, "controlPoint", lhsControlPoint), LocatorUtils.property(rightLocator, "controlPoint", rhsControlPoint), lhsControlPoint, rhsControlPoint, leftObject.isSetControlPoint(), rightObject.isSetControlPoint()));
                    target.setControlPoint(mergedControlPoint);
                } else {
                    if (controlPointShouldBeMergedAndSet == java.lang.Boolean.FALSE) {
                        target.controlPoint = null;
                    }
                }
            }
        }
    }


    /**
     * <p>Classe Java per anonymous complex type.</p>
     * 
     * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.</p>
     * 
     * <pre>{@code
     * <complexType>
     *   <complexContent>
     *     <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       <choice>
     *         <element ref="{http://www.opengis.net/gml}posList"/>
     *         <group ref="{http://www.opengis.net/gml}geometricPositionGroup" maxOccurs="unbounded" minOccurs="3"/>
     *       </choice>
     *     </restriction>
     *   </complexContent>
     * </complexType>
     * }</pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "posList",
        "geometricPositionGroup"
    })
    public static class ControlPoint implements Cloneable, CopyTo, Equals, HashCode, MergeFrom, ToString
    {

        protected DirectPositionListType posList;
        /**
         * GML supports two different ways to specify a geometric position: either by a direct position (a data type) or a point (a geometric object).
         * pos elements are positions that are “owned” by the geometric primitive encapsulating this geometric position.
         * pointProperty elements contain a point that may be referenced from other geometry elements or reference another point defined elsewhere (reuse of existing points).
         * 
         */
        @XmlElements({
            @XmlElement(name = "pos", type = DirectPositionType.class),
            @XmlElement(name = "pointProperty", type = PointPropertyType.class)
        })
        protected List<Object> geometricPositionGroup;

        /**
         * Recupera il valore della proprietà posList.
         * 
         * @return
         *     possible object is
         *     {@link DirectPositionListType }
         *     
         */
        public DirectPositionListType getPosList() {
            return posList;
        }

        /**
         * Imposta il valore della proprietà posList.
         * 
         * @param value
         *     allowed object is
         *     {@link DirectPositionListType }
         *     
         */
        public void setPosList(DirectPositionListType value) {
            this.posList = value;
        }

        public boolean isSetPosList() {
            return (this.posList!= null);
        }

        /**
         * GML supports two different ways to specify a geometric position: either by a direct position (a data type) or a point (a geometric object).
         * pos elements are positions that are “owned” by the geometric primitive encapsulating this geometric position.
         * pointProperty elements contain a point that may be referenced from other geometry elements or reference another point defined elsewhere (reuse of existing points).
         * 
         * Gets the value of the geometricPositionGroup property.
         * 
         * <p>This accessor method returns a reference to the live list,
         * not a snapshot. Therefore, any modification you make to the
         * returned list will be present inside the Jakarta XML Binding object.
         * This is why there is not a {@code set} method for the geometricPositionGroup property.</p>
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * </p>
         * <pre>
         * getGeometricPositionGroup().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link DirectPositionType }
         * {@link PointPropertyType }
         * </p>
         * 
         * 
         * @return
         *     The value of the geometricPositionGroup property.
         */
        public List<Object> getGeometricPositionGroup() {
            if (geometricPositionGroup == null) {
                geometricPositionGroup = new ArrayList<>();
            }
            return this.geometricPositionGroup;
        }

        public boolean isSetGeometricPositionGroup() {
            return ((this.geometricPositionGroup!= null)&&(!this.geometricPositionGroup.isEmpty()));
        }

        public void unsetGeometricPositionGroup() {
            this.geometricPositionGroup = null;
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
                DirectPositionListType thePosList;
                thePosList = this.getPosList();
                strategy.appendField(locator, this, "posList", buffer, thePosList, this.isSetPosList());
            }
            {
                List<Object> theGeometricPositionGroup;
                theGeometricPositionGroup = (this.isSetGeometricPositionGroup()?this.getGeometricPositionGroup():null);
                strategy.appendField(locator, this, "geometricPositionGroup", buffer, theGeometricPositionGroup, this.isSetGeometricPositionGroup());
            }
            return buffer;
        }

        public void setGeometricPositionGroup(List<Object> value) {
            this.geometricPositionGroup = null;
            if (value!= null) {
                List<Object> draftl = this.getGeometricPositionGroup();
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
            final TinType.ControlPoint that = ((TinType.ControlPoint) object);
            {
                DirectPositionListType lhsPosList;
                lhsPosList = this.getPosList();
                DirectPositionListType rhsPosList;
                rhsPosList = that.getPosList();
                if (!strategy.equals(LocatorUtils.property(thisLocator, "posList", lhsPosList), LocatorUtils.property(thatLocator, "posList", rhsPosList), lhsPosList, rhsPosList, this.isSetPosList(), that.isSetPosList())) {
                    return false;
                }
            }
            {
                List<Object> lhsGeometricPositionGroup;
                lhsGeometricPositionGroup = (this.isSetGeometricPositionGroup()?this.getGeometricPositionGroup():null);
                List<Object> rhsGeometricPositionGroup;
                rhsGeometricPositionGroup = (that.isSetGeometricPositionGroup()?that.getGeometricPositionGroup():null);
                if (!strategy.equals(LocatorUtils.property(thisLocator, "geometricPositionGroup", lhsGeometricPositionGroup), LocatorUtils.property(thatLocator, "geometricPositionGroup", rhsGeometricPositionGroup), lhsGeometricPositionGroup, rhsGeometricPositionGroup, this.isSetGeometricPositionGroup(), that.isSetGeometricPositionGroup())) {
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
                DirectPositionListType thePosList;
                thePosList = this.getPosList();
                currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "posList", thePosList), currentHashCode, thePosList, this.isSetPosList());
            }
            {
                List<Object> theGeometricPositionGroup;
                theGeometricPositionGroup = (this.isSetGeometricPositionGroup()?this.getGeometricPositionGroup():null);
                currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "geometricPositionGroup", theGeometricPositionGroup), currentHashCode, theGeometricPositionGroup, this.isSetGeometricPositionGroup());
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
            if (draftCopy instanceof TinType.ControlPoint) {
                final TinType.ControlPoint copy = ((TinType.ControlPoint) draftCopy);
                {
                    java.lang.Boolean posListShouldBeCopiedAndSet = strategy.shouldBeCopiedAndSet(locator, this.isSetPosList());
                    if (posListShouldBeCopiedAndSet == java.lang.Boolean.TRUE) {
                        DirectPositionListType sourcePosList;
                        sourcePosList = this.getPosList();
                        DirectPositionListType copyPosList = ((DirectPositionListType) strategy.copy(LocatorUtils.property(locator, "posList", sourcePosList), sourcePosList, this.isSetPosList()));
                        copy.setPosList(copyPosList);
                    } else {
                        if (posListShouldBeCopiedAndSet == java.lang.Boolean.FALSE) {
                            copy.posList = null;
                        }
                    }
                }
                {
                    java.lang.Boolean geometricPositionGroupShouldBeCopiedAndSet = strategy.shouldBeCopiedAndSet(locator, this.isSetGeometricPositionGroup());
                    if (geometricPositionGroupShouldBeCopiedAndSet == java.lang.Boolean.TRUE) {
                        List<Object> sourceGeometricPositionGroup;
                        sourceGeometricPositionGroup = (this.isSetGeometricPositionGroup()?this.getGeometricPositionGroup():null);
                        @SuppressWarnings("unchecked")
                        List<Object> copyGeometricPositionGroup = ((List<Object> ) strategy.copy(LocatorUtils.property(locator, "geometricPositionGroup", sourceGeometricPositionGroup), sourceGeometricPositionGroup, this.isSetGeometricPositionGroup()));
                        copy.setGeometricPositionGroup(copyGeometricPositionGroup);
                    } else {
                        if (geometricPositionGroupShouldBeCopiedAndSet == java.lang.Boolean.FALSE) {
                            copy.unsetGeometricPositionGroup();
                        }
                    }
                }
            }
            return draftCopy;
        }

        @Override
        public Object createNewInstance() {
            return new TinType.ControlPoint();
        }

        @Override
        public void mergeFrom(Object left, Object right) {
            final MergeStrategy strategy = JAXBMergeStrategy.getInstance();
            mergeFrom(null, null, left, right, strategy);
        }

        @Override
        public void mergeFrom(ObjectLocator leftLocator, ObjectLocator rightLocator, Object left, Object right, MergeStrategy strategy) {
            if (right instanceof TinType.ControlPoint) {
                final TinType.ControlPoint target = this;
                final TinType.ControlPoint leftObject = ((TinType.ControlPoint) left);
                final TinType.ControlPoint rightObject = ((TinType.ControlPoint) right);
                {
                    java.lang.Boolean posListShouldBeMergedAndSet = strategy.shouldBeMergedAndSet(leftLocator, rightLocator, leftObject.isSetPosList(), rightObject.isSetPosList());
                    if (posListShouldBeMergedAndSet == java.lang.Boolean.TRUE) {
                        DirectPositionListType lhsPosList;
                        lhsPosList = leftObject.getPosList();
                        DirectPositionListType rhsPosList;
                        rhsPosList = rightObject.getPosList();
                        DirectPositionListType mergedPosList = ((DirectPositionListType) strategy.merge(LocatorUtils.property(leftLocator, "posList", lhsPosList), LocatorUtils.property(rightLocator, "posList", rhsPosList), lhsPosList, rhsPosList, leftObject.isSetPosList(), rightObject.isSetPosList()));
                        target.setPosList(mergedPosList);
                    } else {
                        if (posListShouldBeMergedAndSet == java.lang.Boolean.FALSE) {
                            target.posList = null;
                        }
                    }
                }
                {
                    java.lang.Boolean geometricPositionGroupShouldBeMergedAndSet = strategy.shouldBeMergedAndSet(leftLocator, rightLocator, leftObject.isSetGeometricPositionGroup(), rightObject.isSetGeometricPositionGroup());
                    if (geometricPositionGroupShouldBeMergedAndSet == java.lang.Boolean.TRUE) {
                        List<Object> lhsGeometricPositionGroup;
                        lhsGeometricPositionGroup = (leftObject.isSetGeometricPositionGroup()?leftObject.getGeometricPositionGroup():null);
                        List<Object> rhsGeometricPositionGroup;
                        rhsGeometricPositionGroup = (rightObject.isSetGeometricPositionGroup()?rightObject.getGeometricPositionGroup():null);
                        List<Object> mergedGeometricPositionGroup = ((List<Object> ) strategy.merge(LocatorUtils.property(leftLocator, "geometricPositionGroup", lhsGeometricPositionGroup), LocatorUtils.property(rightLocator, "geometricPositionGroup", rhsGeometricPositionGroup), lhsGeometricPositionGroup, rhsGeometricPositionGroup, leftObject.isSetGeometricPositionGroup(), rightObject.isSetGeometricPositionGroup()));
                        target.setGeometricPositionGroup(mergedGeometricPositionGroup);
                    } else {
                        if (geometricPositionGroupShouldBeMergedAndSet == java.lang.Boolean.FALSE) {
                            target.unsetGeometricPositionGroup();
                        }
                    }
                }
            }
        }

    }

}
