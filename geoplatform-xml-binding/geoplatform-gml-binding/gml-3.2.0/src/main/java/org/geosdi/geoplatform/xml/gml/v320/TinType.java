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

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
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
 * <p>Classe Java per TinType complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="TinType">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.opengis.net/gml}TriangulatedSurfaceType">
 *       &lt;sequence>
 *         &lt;element name="stopLines" type="{http://www.opengis.net/gml}LineStringSegmentArrayPropertyType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="breakLines" type="{http://www.opengis.net/gml}LineStringSegmentArrayPropertyType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="maxLength" type="{http://www.opengis.net/gml}LengthType"/>
 *         &lt;element name="controlPoint">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;choice>
 *                   &lt;element ref="{http://www.opengis.net/gml}posList"/>
 *                   &lt;group ref="{http://www.opengis.net/gml}geometricPositionGroup" maxOccurs="unbounded" minOccurs="3"/>
 *                 &lt;/choice>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
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
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the stopLines property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getStopLines().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link LineStringSegmentArrayPropertyType }
     * 
     * 
     */
    public List<LineStringSegmentArrayPropertyType> getStopLines() {
        if (stopLines == null) {
            stopLines = new ArrayList<LineStringSegmentArrayPropertyType>();
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
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the breakLines property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getBreakLines().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link LineStringSegmentArrayPropertyType }
     * 
     * 
     */
    public List<LineStringSegmentArrayPropertyType> getBreakLines() {
        if (breakLines == null) {
            breakLines = new ArrayList<LineStringSegmentArrayPropertyType>();
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
        super.appendFields(locator, buffer, strategy);
        {
            List<LineStringSegmentArrayPropertyType> theStopLines;
            theStopLines = this.getStopLines();
            strategy.appendField(locator, this, "stopLines", buffer, theStopLines);
        }
        {
            List<LineStringSegmentArrayPropertyType> theBreakLines;
            theBreakLines = this.getBreakLines();
            strategy.appendField(locator, this, "breakLines", buffer, theBreakLines);
        }
        {
            LengthType theMaxLength;
            theMaxLength = this.getMaxLength();
            strategy.appendField(locator, this, "maxLength", buffer, theMaxLength);
        }
        {
            TinType.ControlPoint theControlPoint;
            theControlPoint = this.getControlPoint();
            strategy.appendField(locator, this, "controlPoint", buffer, theControlPoint);
        }
        return buffer;
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof TinType)) {
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
            lhsStopLines = this.getStopLines();
            List<LineStringSegmentArrayPropertyType> rhsStopLines;
            rhsStopLines = that.getStopLines();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "stopLines", lhsStopLines), LocatorUtils.property(thatLocator, "stopLines", rhsStopLines), lhsStopLines, rhsStopLines)) {
                return false;
            }
        }
        {
            List<LineStringSegmentArrayPropertyType> lhsBreakLines;
            lhsBreakLines = this.getBreakLines();
            List<LineStringSegmentArrayPropertyType> rhsBreakLines;
            rhsBreakLines = that.getBreakLines();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "breakLines", lhsBreakLines), LocatorUtils.property(thatLocator, "breakLines", rhsBreakLines), lhsBreakLines, rhsBreakLines)) {
                return false;
            }
        }
        {
            LengthType lhsMaxLength;
            lhsMaxLength = this.getMaxLength();
            LengthType rhsMaxLength;
            rhsMaxLength = that.getMaxLength();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "maxLength", lhsMaxLength), LocatorUtils.property(thatLocator, "maxLength", rhsMaxLength), lhsMaxLength, rhsMaxLength)) {
                return false;
            }
        }
        {
            TinType.ControlPoint lhsControlPoint;
            lhsControlPoint = this.getControlPoint();
            TinType.ControlPoint rhsControlPoint;
            rhsControlPoint = that.getControlPoint();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "controlPoint", lhsControlPoint), LocatorUtils.property(thatLocator, "controlPoint", rhsControlPoint), lhsControlPoint, rhsControlPoint)) {
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
        int currentHashCode = super.hashCode(locator, strategy);
        {
            List<LineStringSegmentArrayPropertyType> theStopLines;
            theStopLines = this.getStopLines();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "stopLines", theStopLines), currentHashCode, theStopLines);
        }
        {
            List<LineStringSegmentArrayPropertyType> theBreakLines;
            theBreakLines = this.getBreakLines();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "breakLines", theBreakLines), currentHashCode, theBreakLines);
        }
        {
            LengthType theMaxLength;
            theMaxLength = this.getMaxLength();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "maxLength", theMaxLength), currentHashCode, theMaxLength);
        }
        {
            TinType.ControlPoint theControlPoint;
            theControlPoint = this.getControlPoint();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "controlPoint", theControlPoint), currentHashCode, theControlPoint);
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
        super.copyTo(locator, draftCopy, strategy);
        if (draftCopy instanceof TinType) {
            final TinType copy = ((TinType) draftCopy);
            if (this.isSetStopLines()) {
                List<LineStringSegmentArrayPropertyType> sourceStopLines;
                sourceStopLines = this.getStopLines();
                @SuppressWarnings("unchecked")
                List<LineStringSegmentArrayPropertyType> copyStopLines = ((List<LineStringSegmentArrayPropertyType> ) strategy.copy(LocatorUtils.property(locator, "stopLines", sourceStopLines), sourceStopLines));
                copy.unsetStopLines();
                List<LineStringSegmentArrayPropertyType> uniqueStopLinesl = copy.getStopLines();
                uniqueStopLinesl.addAll(copyStopLines);
            } else {
                copy.unsetStopLines();
            }
            if (this.isSetBreakLines()) {
                List<LineStringSegmentArrayPropertyType> sourceBreakLines;
                sourceBreakLines = this.getBreakLines();
                @SuppressWarnings("unchecked")
                List<LineStringSegmentArrayPropertyType> copyBreakLines = ((List<LineStringSegmentArrayPropertyType> ) strategy.copy(LocatorUtils.property(locator, "breakLines", sourceBreakLines), sourceBreakLines));
                copy.unsetBreakLines();
                List<LineStringSegmentArrayPropertyType> uniqueBreakLinesl = copy.getBreakLines();
                uniqueBreakLinesl.addAll(copyBreakLines);
            } else {
                copy.unsetBreakLines();
            }
            if (this.isSetMaxLength()) {
                LengthType sourceMaxLength;
                sourceMaxLength = this.getMaxLength();
                LengthType copyMaxLength = ((LengthType) strategy.copy(LocatorUtils.property(locator, "maxLength", sourceMaxLength), sourceMaxLength));
                copy.setMaxLength(copyMaxLength);
            } else {
                copy.maxLength = null;
            }
            if (this.isSetControlPoint()) {
                TinType.ControlPoint sourceControlPoint;
                sourceControlPoint = this.getControlPoint();
                TinType.ControlPoint copyControlPoint = ((TinType.ControlPoint) strategy.copy(LocatorUtils.property(locator, "controlPoint", sourceControlPoint), sourceControlPoint));
                copy.setControlPoint(copyControlPoint);
            } else {
                copy.controlPoint = null;
            }
        }
        return draftCopy;
    }

    public Object createNewInstance() {
        return new TinType();
    }

    public void mergeFrom(Object left, Object right) {
        final MergeStrategy strategy = JAXBMergeStrategy.INSTANCE;
        mergeFrom(null, null, left, right, strategy);
    }

    public void mergeFrom(ObjectLocator leftLocator, ObjectLocator rightLocator, Object left, Object right, MergeStrategy strategy) {
        super.mergeFrom(leftLocator, rightLocator, left, right, strategy);
        if (right instanceof TinType) {
            final TinType target = this;
            final TinType leftObject = ((TinType) left);
            final TinType rightObject = ((TinType) right);
            {
                List<LineStringSegmentArrayPropertyType> lhsStopLines;
                lhsStopLines = leftObject.getStopLines();
                List<LineStringSegmentArrayPropertyType> rhsStopLines;
                rhsStopLines = rightObject.getStopLines();
                target.unsetStopLines();
                List<LineStringSegmentArrayPropertyType> uniqueStopLinesl = target.getStopLines();
                uniqueStopLinesl.addAll(((List<LineStringSegmentArrayPropertyType> ) strategy.merge(LocatorUtils.property(leftLocator, "stopLines", lhsStopLines), LocatorUtils.property(rightLocator, "stopLines", rhsStopLines), lhsStopLines, rhsStopLines)));
            }
            {
                List<LineStringSegmentArrayPropertyType> lhsBreakLines;
                lhsBreakLines = leftObject.getBreakLines();
                List<LineStringSegmentArrayPropertyType> rhsBreakLines;
                rhsBreakLines = rightObject.getBreakLines();
                target.unsetBreakLines();
                List<LineStringSegmentArrayPropertyType> uniqueBreakLinesl = target.getBreakLines();
                uniqueBreakLinesl.addAll(((List<LineStringSegmentArrayPropertyType> ) strategy.merge(LocatorUtils.property(leftLocator, "breakLines", lhsBreakLines), LocatorUtils.property(rightLocator, "breakLines", rhsBreakLines), lhsBreakLines, rhsBreakLines)));
            }
            {
                LengthType lhsMaxLength;
                lhsMaxLength = leftObject.getMaxLength();
                LengthType rhsMaxLength;
                rhsMaxLength = rightObject.getMaxLength();
                target.setMaxLength(((LengthType) strategy.merge(LocatorUtils.property(leftLocator, "maxLength", lhsMaxLength), LocatorUtils.property(rightLocator, "maxLength", rhsMaxLength), lhsMaxLength, rhsMaxLength)));
            }
            {
                TinType.ControlPoint lhsControlPoint;
                lhsControlPoint = leftObject.getControlPoint();
                TinType.ControlPoint rhsControlPoint;
                rhsControlPoint = rightObject.getControlPoint();
                target.setControlPoint(((TinType.ControlPoint) strategy.merge(LocatorUtils.property(leftLocator, "controlPoint", lhsControlPoint), LocatorUtils.property(rightLocator, "controlPoint", rhsControlPoint), lhsControlPoint, rhsControlPoint)));
            }
        }
    }

    public void setStopLines(List<LineStringSegmentArrayPropertyType> value) {
        List<LineStringSegmentArrayPropertyType> draftl = this.getStopLines();
        draftl.addAll(value);
    }

    public void setBreakLines(List<LineStringSegmentArrayPropertyType> value) {
        List<LineStringSegmentArrayPropertyType> draftl = this.getBreakLines();
        draftl.addAll(value);
    }

    public TinType withStopLines(LineStringSegmentArrayPropertyType... values) {
        if (values!= null) {
            for (LineStringSegmentArrayPropertyType value: values) {
                getStopLines().add(value);
            }
        }
        return this;
    }

    public TinType withStopLines(Collection<LineStringSegmentArrayPropertyType> values) {
        if (values!= null) {
            getStopLines().addAll(values);
        }
        return this;
    }

    public TinType withBreakLines(LineStringSegmentArrayPropertyType... values) {
        if (values!= null) {
            for (LineStringSegmentArrayPropertyType value: values) {
                getBreakLines().add(value);
            }
        }
        return this;
    }

    public TinType withBreakLines(Collection<LineStringSegmentArrayPropertyType> values) {
        if (values!= null) {
            getBreakLines().addAll(values);
        }
        return this;
    }

    public TinType withMaxLength(LengthType value) {
        setMaxLength(value);
        return this;
    }

    public TinType withControlPoint(TinType.ControlPoint value) {
        setControlPoint(value);
        return this;
    }

    public TinType withStopLines(List<LineStringSegmentArrayPropertyType> value) {
        setStopLines(value);
        return this;
    }

    public TinType withBreakLines(List<LineStringSegmentArrayPropertyType> value) {
        setBreakLines(value);
        return this;
    }

    @Override
    public TinType withPatches(JAXBElement<? extends SurfacePatchArrayPropertyType> value) {
        setPatches(value);
        return this;
    }

    @Override
    public TinType withSrsName(String value) {
        setSrsName(value);
        return this;
    }

    @Override
    public TinType withSrsDimension(BigInteger value) {
        setSrsDimension(value);
        return this;
    }

    @Override
    public TinType withAxisLabels(String... values) {
        if (values!= null) {
            for (String value: values) {
                getAxisLabels().add(value);
            }
        }
        return this;
    }

    @Override
    public TinType withAxisLabels(Collection<String> values) {
        if (values!= null) {
            getAxisLabels().addAll(values);
        }
        return this;
    }

    @Override
    public TinType withUomLabels(String... values) {
        if (values!= null) {
            for (String value: values) {
                getUomLabels().add(value);
            }
        }
        return this;
    }

    @Override
    public TinType withUomLabels(Collection<String> values) {
        if (values!= null) {
            getUomLabels().addAll(values);
        }
        return this;
    }

    @Override
    public TinType withAxisLabels(List<String> value) {
        setAxisLabels(value);
        return this;
    }

    @Override
    public TinType withUomLabels(List<String> value) {
        setUomLabels(value);
        return this;
    }

    @Override
    public TinType withMetaDataProperty(MetaDataPropertyType... values) {
        if (values!= null) {
            for (MetaDataPropertyType value: values) {
                getMetaDataProperty().add(value);
            }
        }
        return this;
    }

    @Override
    public TinType withMetaDataProperty(Collection<MetaDataPropertyType> values) {
        if (values!= null) {
            getMetaDataProperty().addAll(values);
        }
        return this;
    }

    @Override
    public TinType withDescription(StringOrRefType value) {
        setDescription(value);
        return this;
    }

    @Override
    public TinType withDescriptionReference(ReferenceType value) {
        setDescriptionReference(value);
        return this;
    }

    @Override
    public TinType withIdentifier(CodeWithAuthorityType value) {
        setIdentifier(value);
        return this;
    }

    @Override
    public TinType withName(CodeType... values) {
        if (values!= null) {
            for (CodeType value: values) {
                getName().add(value);
            }
        }
        return this;
    }

    @Override
    public TinType withName(Collection<CodeType> values) {
        if (values!= null) {
            getName().addAll(values);
        }
        return this;
    }

    @Override
    public TinType withId(String value) {
        setId(value);
        return this;
    }

    @Override
    public TinType withMetaDataProperty(List<MetaDataPropertyType> value) {
        setMetaDataProperty(value);
        return this;
    }

    @Override
    public TinType withName(List<CodeType> value) {
        setName(value);
        return this;
    }


    /**
     * <p>Classe Java per anonymous complex type.
     * 
     * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;choice>
     *         &lt;element ref="{http://www.opengis.net/gml}posList"/>
     *         &lt;group ref="{http://www.opengis.net/gml}geometricPositionGroup" maxOccurs="unbounded" minOccurs="3"/>
     *       &lt;/choice>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "posList",
        "geometricPositionGroup"
    })
    public static class ControlPoint
        implements Cloneable, CopyTo, Equals, HashCode, MergeFrom, ToString
    {

        protected DirectPositionListType posList;
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
         * Gets the value of the geometricPositionGroup property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the geometricPositionGroup property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getGeometricPositionGroup().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link DirectPositionType }
         * {@link PointPropertyType }
         * 
         * 
         */
        public List<Object> getGeometricPositionGroup() {
            if (geometricPositionGroup == null) {
                geometricPositionGroup = new ArrayList<Object>();
            }
            return this.geometricPositionGroup;
        }

        public boolean isSetGeometricPositionGroup() {
            return ((this.geometricPositionGroup!= null)&&(!this.geometricPositionGroup.isEmpty()));
        }

        public void unsetGeometricPositionGroup() {
            this.geometricPositionGroup = null;
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
                DirectPositionListType thePosList;
                thePosList = this.getPosList();
                strategy.appendField(locator, this, "posList", buffer, thePosList);
            }
            {
                List<Object> theGeometricPositionGroup;
                theGeometricPositionGroup = this.getGeometricPositionGroup();
                strategy.appendField(locator, this, "geometricPositionGroup", buffer, theGeometricPositionGroup);
            }
            return buffer;
        }

        public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
            if (!(object instanceof TinType.ControlPoint)) {
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
                if (!strategy.equals(LocatorUtils.property(thisLocator, "posList", lhsPosList), LocatorUtils.property(thatLocator, "posList", rhsPosList), lhsPosList, rhsPosList)) {
                    return false;
                }
            }
            {
                List<Object> lhsGeometricPositionGroup;
                lhsGeometricPositionGroup = this.getGeometricPositionGroup();
                List<Object> rhsGeometricPositionGroup;
                rhsGeometricPositionGroup = that.getGeometricPositionGroup();
                if (!strategy.equals(LocatorUtils.property(thisLocator, "geometricPositionGroup", lhsGeometricPositionGroup), LocatorUtils.property(thatLocator, "geometricPositionGroup", rhsGeometricPositionGroup), lhsGeometricPositionGroup, rhsGeometricPositionGroup)) {
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
                DirectPositionListType thePosList;
                thePosList = this.getPosList();
                currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "posList", thePosList), currentHashCode, thePosList);
            }
            {
                List<Object> theGeometricPositionGroup;
                theGeometricPositionGroup = this.getGeometricPositionGroup();
                currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "geometricPositionGroup", theGeometricPositionGroup), currentHashCode, theGeometricPositionGroup);
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
            if (draftCopy instanceof TinType.ControlPoint) {
                final TinType.ControlPoint copy = ((TinType.ControlPoint) draftCopy);
                if (this.isSetPosList()) {
                    DirectPositionListType sourcePosList;
                    sourcePosList = this.getPosList();
                    DirectPositionListType copyPosList = ((DirectPositionListType) strategy.copy(LocatorUtils.property(locator, "posList", sourcePosList), sourcePosList));
                    copy.setPosList(copyPosList);
                } else {
                    copy.posList = null;
                }
                if (this.isSetGeometricPositionGroup()) {
                    List<Object> sourceGeometricPositionGroup;
                    sourceGeometricPositionGroup = this.getGeometricPositionGroup();
                    @SuppressWarnings("unchecked")
                    List<Object> copyGeometricPositionGroup = ((List<Object> ) strategy.copy(LocatorUtils.property(locator, "geometricPositionGroup", sourceGeometricPositionGroup), sourceGeometricPositionGroup));
                    copy.unsetGeometricPositionGroup();
                    List<Object> uniqueGeometricPositionGroupl = copy.getGeometricPositionGroup();
                    uniqueGeometricPositionGroupl.addAll(copyGeometricPositionGroup);
                } else {
                    copy.unsetGeometricPositionGroup();
                }
            }
            return draftCopy;
        }

        public Object createNewInstance() {
            return new TinType.ControlPoint();
        }

        public void mergeFrom(Object left, Object right) {
            final MergeStrategy strategy = JAXBMergeStrategy.INSTANCE;
            mergeFrom(null, null, left, right, strategy);
        }

        public void mergeFrom(ObjectLocator leftLocator, ObjectLocator rightLocator, Object left, Object right, MergeStrategy strategy) {
            if (right instanceof TinType.ControlPoint) {
                final TinType.ControlPoint target = this;
                final TinType.ControlPoint leftObject = ((TinType.ControlPoint) left);
                final TinType.ControlPoint rightObject = ((TinType.ControlPoint) right);
                {
                    DirectPositionListType lhsPosList;
                    lhsPosList = leftObject.getPosList();
                    DirectPositionListType rhsPosList;
                    rhsPosList = rightObject.getPosList();
                    target.setPosList(((DirectPositionListType) strategy.merge(LocatorUtils.property(leftLocator, "posList", lhsPosList), LocatorUtils.property(rightLocator, "posList", rhsPosList), lhsPosList, rhsPosList)));
                }
                {
                    List<Object> lhsGeometricPositionGroup;
                    lhsGeometricPositionGroup = leftObject.getGeometricPositionGroup();
                    List<Object> rhsGeometricPositionGroup;
                    rhsGeometricPositionGroup = rightObject.getGeometricPositionGroup();
                    target.unsetGeometricPositionGroup();
                    List<Object> uniqueGeometricPositionGroupl = target.getGeometricPositionGroup();
                    uniqueGeometricPositionGroupl.addAll(((List<Object> ) strategy.merge(LocatorUtils.property(leftLocator, "geometricPositionGroup", lhsGeometricPositionGroup), LocatorUtils.property(rightLocator, "geometricPositionGroup", rhsGeometricPositionGroup), lhsGeometricPositionGroup, rhsGeometricPositionGroup)));
                }
            }
        }

        public void setGeometricPositionGroup(List<Object> value) {
            List<Object> draftl = this.getGeometricPositionGroup();
            draftl.addAll(value);
        }

        public TinType.ControlPoint withPosList(DirectPositionListType value) {
            setPosList(value);
            return this;
        }

        public TinType.ControlPoint withGeometricPositionGroup(Object... values) {
            if (values!= null) {
                for (Object value: values) {
                    getGeometricPositionGroup().add(value);
                }
            }
            return this;
        }

        public TinType.ControlPoint withGeometricPositionGroup(Collection<Object> values) {
            if (values!= null) {
                getGeometricPositionGroup().addAll(values);
            }
            return this;
        }

        public TinType.ControlPoint withGeometricPositionGroup(List<Object> value) {
            setGeometricPositionGroup(value);
            return this;
        }

    }

}
