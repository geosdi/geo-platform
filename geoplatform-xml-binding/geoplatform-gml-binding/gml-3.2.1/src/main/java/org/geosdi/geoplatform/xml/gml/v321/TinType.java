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
// Generato il: 2026.11.02 alle 03:48:04 PM CET
//

package org.geosdi.geoplatform.xml.gml.v321;

import java.util.ArrayList;
import java.util.List;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlElements;
import jakarta.xml.bind.annotation.XmlType;
import org.jvnet.jaxb.lang.JAXBToStringStrategy;
import org.jvnet.jaxb.lang.ToString;
import org.jvnet.jaxb.lang.ToStringStrategy;
import org.jvnet.jaxb.locator.ObjectLocator;


/**
 * <p>Classe Java per TinType complex type.</p>
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.</p>
 * 
 * <pre>{@code
 * <complexType name="TinType">
 *   <complexContent>
 *     <extension base="{http://www.opengis.net/gml/3.2}SurfaceType">
 *       <sequence>
 *         <element name="stopLines" type="{http://www.opengis.net/gml/3.2}LineStringSegmentArrayPropertyType" maxOccurs="unbounded" minOccurs="0"/>
 *         <element name="breakLines" type="{http://www.opengis.net/gml/3.2}LineStringSegmentArrayPropertyType" maxOccurs="unbounded" minOccurs="0"/>
 *         <element name="maxLength" type="{http://www.opengis.net/gml/3.2}LengthType"/>
 *         <element name="controlPoint">
 *           <complexType>
 *             <complexContent>
 *               <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 <choice>
 *                   <element ref="{http://www.opengis.net/gml/3.2}posList"/>
 *                   <group ref="{http://www.opengis.net/gml/3.2}geometricPositionGroup" maxOccurs="unbounded" minOccurs="3"/>
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
    extends SurfaceType
    implements ToString
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
     *         <element ref="{http://www.opengis.net/gml/3.2}posList"/>
     *         <group ref="{http://www.opengis.net/gml/3.2}geometricPositionGroup" maxOccurs="unbounded" minOccurs="3"/>
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
    public static class ControlPoint implements ToString
    {

        protected DirectPositionListType posList;
        /**
         * GML supports two different ways to specify a geometric position: either by a direct position (a data type) or a point (a geometric object).
         * pos elements are positions that are "owned" by the geometric primitive encapsulating this geometric position.
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
         * pos elements are positions that are "owned" by the geometric primitive encapsulating this geometric position.
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

    }

}
