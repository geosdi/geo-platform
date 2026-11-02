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

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlElements;
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
 * <p>Classe Java per AbstractGriddedSurfaceType complex type.</p>
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.</p>
 * 
 * <pre>{@code
 * <complexType name="AbstractGriddedSurfaceType">
 *   <complexContent>
 *     <extension base="{http://www.opengis.net/gml}AbstractParametricCurveSurfaceType">
 *       <sequence>
 *         <group ref="{http://www.opengis.net/gml}PointGrid"/>
 *       </sequence>
 *       <attribute name="rows" type="{http://www.w3.org/2001/XMLSchema}integer" />
 *       <attribute name="columns" type="{http://www.w3.org/2001/XMLSchema}integer" />
 *     </extension>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AbstractGriddedSurfaceType", propOrder = {
    "arows"
})
@XmlSeeAlso({
    ConeType.class,
    CylinderType.class,
    SphereType.class
})
public abstract class AbstractGriddedSurfaceType
    extends AbstractParametricCurveSurfaceType
    implements Cloneable, CopyTo, Equals, HashCode, MergeFrom, ToString
{

    @XmlElement(name = "rows", required = true)
    protected AbstractGriddedSurfaceType.Rows arows;
    @XmlAttribute(name = "rows")
    protected BigInteger rows;
    @XmlAttribute(name = "columns")
    protected BigInteger columns;

    /**
     * Recupera il valore della proprietà arows.
     * 
     * @return
     *     possible object is
     *     {@link AbstractGriddedSurfaceType.Rows }
     *     
     */
    public AbstractGriddedSurfaceType.Rows getArows() {
        return arows;
    }

    /**
     * Imposta il valore della proprietà arows.
     * 
     * @param value
     *     allowed object is
     *     {@link AbstractGriddedSurfaceType.Rows }
     *     
     */
    public void setArows(AbstractGriddedSurfaceType.Rows value) {
        this.arows = value;
    }

    public boolean isSetArows() {
        return (this.arows!= null);
    }

    /**
     * Recupera il valore della proprietà rows.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getRows() {
        return rows;
    }

    /**
     * Imposta il valore della proprietà rows.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setRows(BigInteger value) {
        this.rows = value;
    }

    public boolean isSetRows() {
        return (this.rows!= null);
    }

    /**
     * Recupera il valore della proprietà columns.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getColumns() {
        return columns;
    }

    /**
     * Imposta il valore della proprietà columns.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setColumns(BigInteger value) {
        this.columns = value;
    }

    public boolean isSetColumns() {
        return (this.columns!= null);
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
            AbstractGriddedSurfaceType.Rows theArows;
            theArows = this.getArows();
            strategy.appendField(locator, this, "arows", buffer, theArows, this.isSetArows());
        }
        {
            BigInteger theRows;
            theRows = this.getRows();
            strategy.appendField(locator, this, "rows", buffer, theRows, this.isSetRows());
        }
        {
            BigInteger theColumns;
            theColumns = this.getColumns();
            strategy.appendField(locator, this, "columns", buffer, theColumns, this.isSetColumns());
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
        final AbstractGriddedSurfaceType that = ((AbstractGriddedSurfaceType) object);
        {
            AbstractGriddedSurfaceType.Rows lhsArows;
            lhsArows = this.getArows();
            AbstractGriddedSurfaceType.Rows rhsArows;
            rhsArows = that.getArows();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "arows", lhsArows), LocatorUtils.property(thatLocator, "arows", rhsArows), lhsArows, rhsArows, this.isSetArows(), that.isSetArows())) {
                return false;
            }
        }
        {
            BigInteger lhsRows;
            lhsRows = this.getRows();
            BigInteger rhsRows;
            rhsRows = that.getRows();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "rows", lhsRows), LocatorUtils.property(thatLocator, "rows", rhsRows), lhsRows, rhsRows, this.isSetRows(), that.isSetRows())) {
                return false;
            }
        }
        {
            BigInteger lhsColumns;
            lhsColumns = this.getColumns();
            BigInteger rhsColumns;
            rhsColumns = that.getColumns();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "columns", lhsColumns), LocatorUtils.property(thatLocator, "columns", rhsColumns), lhsColumns, rhsColumns, this.isSetColumns(), that.isSetColumns())) {
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
            AbstractGriddedSurfaceType.Rows theArows;
            theArows = this.getArows();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "arows", theArows), currentHashCode, theArows, this.isSetArows());
        }
        {
            BigInteger theRows;
            theRows = this.getRows();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "rows", theRows), currentHashCode, theRows, this.isSetRows());
        }
        {
            BigInteger theColumns;
            theColumns = this.getColumns();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "columns", theColumns), currentHashCode, theColumns, this.isSetColumns());
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
        if (null == target) {
            throw new IllegalArgumentException("Target argument must not be null for abstract copyable classes.");
        }
        super.copyTo(locator, target, strategy);
        if (target instanceof AbstractGriddedSurfaceType) {
            final AbstractGriddedSurfaceType copy = ((AbstractGriddedSurfaceType) target);
            {
                java.lang.Boolean arowsShouldBeCopiedAndSet = strategy.shouldBeCopiedAndSet(locator, this.isSetArows());
                if (arowsShouldBeCopiedAndSet == java.lang.Boolean.TRUE) {
                    AbstractGriddedSurfaceType.Rows sourceArows;
                    sourceArows = this.getArows();
                    AbstractGriddedSurfaceType.Rows copyArows = ((AbstractGriddedSurfaceType.Rows) strategy.copy(LocatorUtils.property(locator, "arows", sourceArows), sourceArows, this.isSetArows()));
                    copy.setArows(copyArows);
                } else {
                    if (arowsShouldBeCopiedAndSet == java.lang.Boolean.FALSE) {
                        copy.arows = null;
                    }
                }
            }
            {
                java.lang.Boolean rowsShouldBeCopiedAndSet = strategy.shouldBeCopiedAndSet(locator, this.isSetRows());
                if (rowsShouldBeCopiedAndSet == java.lang.Boolean.TRUE) {
                    BigInteger sourceRows;
                    sourceRows = this.getRows();
                    BigInteger copyRows = ((BigInteger) strategy.copy(LocatorUtils.property(locator, "rows", sourceRows), sourceRows, this.isSetRows()));
                    copy.setRows(copyRows);
                } else {
                    if (rowsShouldBeCopiedAndSet == java.lang.Boolean.FALSE) {
                        copy.rows = null;
                    }
                }
            }
            {
                java.lang.Boolean columnsShouldBeCopiedAndSet = strategy.shouldBeCopiedAndSet(locator, this.isSetColumns());
                if (columnsShouldBeCopiedAndSet == java.lang.Boolean.TRUE) {
                    BigInteger sourceColumns;
                    sourceColumns = this.getColumns();
                    BigInteger copyColumns = ((BigInteger) strategy.copy(LocatorUtils.property(locator, "columns", sourceColumns), sourceColumns, this.isSetColumns()));
                    copy.setColumns(copyColumns);
                } else {
                    if (columnsShouldBeCopiedAndSet == java.lang.Boolean.FALSE) {
                        copy.columns = null;
                    }
                }
            }
        }
        return target;
    }

    @Override
    public void mergeFrom(Object left, Object right) {
        final MergeStrategy strategy = JAXBMergeStrategy.getInstance();
        mergeFrom(null, null, left, right, strategy);
    }

    @Override
    public void mergeFrom(ObjectLocator leftLocator, ObjectLocator rightLocator, Object left, Object right, MergeStrategy strategy) {
        super.mergeFrom(leftLocator, rightLocator, left, right, strategy);
        if (right instanceof AbstractGriddedSurfaceType) {
            final AbstractGriddedSurfaceType target = this;
            final AbstractGriddedSurfaceType leftObject = ((AbstractGriddedSurfaceType) left);
            final AbstractGriddedSurfaceType rightObject = ((AbstractGriddedSurfaceType) right);
            {
                java.lang.Boolean arowsShouldBeMergedAndSet = strategy.shouldBeMergedAndSet(leftLocator, rightLocator, leftObject.isSetArows(), rightObject.isSetArows());
                if (arowsShouldBeMergedAndSet == java.lang.Boolean.TRUE) {
                    AbstractGriddedSurfaceType.Rows lhsArows;
                    lhsArows = leftObject.getArows();
                    AbstractGriddedSurfaceType.Rows rhsArows;
                    rhsArows = rightObject.getArows();
                    AbstractGriddedSurfaceType.Rows mergedArows = ((AbstractGriddedSurfaceType.Rows) strategy.merge(LocatorUtils.property(leftLocator, "arows", lhsArows), LocatorUtils.property(rightLocator, "arows", rhsArows), lhsArows, rhsArows, leftObject.isSetArows(), rightObject.isSetArows()));
                    target.setArows(mergedArows);
                } else {
                    if (arowsShouldBeMergedAndSet == java.lang.Boolean.FALSE) {
                        target.arows = null;
                    }
                }
            }
            {
                java.lang.Boolean rowsShouldBeMergedAndSet = strategy.shouldBeMergedAndSet(leftLocator, rightLocator, leftObject.isSetRows(), rightObject.isSetRows());
                if (rowsShouldBeMergedAndSet == java.lang.Boolean.TRUE) {
                    BigInteger lhsRows;
                    lhsRows = leftObject.getRows();
                    BigInteger rhsRows;
                    rhsRows = rightObject.getRows();
                    BigInteger mergedRows = ((BigInteger) strategy.merge(LocatorUtils.property(leftLocator, "rows", lhsRows), LocatorUtils.property(rightLocator, "rows", rhsRows), lhsRows, rhsRows, leftObject.isSetRows(), rightObject.isSetRows()));
                    target.setRows(mergedRows);
                } else {
                    if (rowsShouldBeMergedAndSet == java.lang.Boolean.FALSE) {
                        target.rows = null;
                    }
                }
            }
            {
                java.lang.Boolean columnsShouldBeMergedAndSet = strategy.shouldBeMergedAndSet(leftLocator, rightLocator, leftObject.isSetColumns(), rightObject.isSetColumns());
                if (columnsShouldBeMergedAndSet == java.lang.Boolean.TRUE) {
                    BigInteger lhsColumns;
                    lhsColumns = leftObject.getColumns();
                    BigInteger rhsColumns;
                    rhsColumns = rightObject.getColumns();
                    BigInteger mergedColumns = ((BigInteger) strategy.merge(LocatorUtils.property(leftLocator, "columns", lhsColumns), LocatorUtils.property(rightLocator, "columns", rhsColumns), lhsColumns, rhsColumns, leftObject.isSetColumns(), rightObject.isSetColumns()));
                    target.setColumns(mergedColumns);
                } else {
                    if (columnsShouldBeMergedAndSet == java.lang.Boolean.FALSE) {
                        target.columns = null;
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
     *       <sequence>
     *         <element name="Row" maxOccurs="unbounded">
     *           <complexType>
     *             <complexContent>
     *               <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 <group ref="{http://www.opengis.net/gml}geometricPositionListGroup"/>
     *               </restriction>
     *             </complexContent>
     *           </complexType>
     *         </element>
     *       </sequence>
     *     </restriction>
     *   </complexContent>
     * </complexType>
     * }</pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "row"
    })
    public static class Rows implements Cloneable, CopyTo, Equals, HashCode, MergeFrom, ToString
    {

        @XmlElement(name = "Row", required = true)
        protected List<AbstractGriddedSurfaceType.Rows.Row> row;

        /**
         * Gets the value of the row property.
         * 
         * <p>This accessor method returns a reference to the live list,
         * not a snapshot. Therefore, any modification you make to the
         * returned list will be present inside the Jakarta XML Binding object.
         * This is why there is not a {@code set} method for the row property.</p>
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * </p>
         * <pre>
         * getRow().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link AbstractGriddedSurfaceType.Rows.Row }
         * </p>
         * 
         * 
         * @return
         *     The value of the row property.
         */
        public List<AbstractGriddedSurfaceType.Rows.Row> getRow() {
            if (row == null) {
                row = new ArrayList<>();
            }
            return this.row;
        }

        public boolean isSetRow() {
            return ((this.row!= null)&&(!this.row.isEmpty()));
        }

        public void unsetRow() {
            this.row = null;
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
                List<AbstractGriddedSurfaceType.Rows.Row> theRow;
                theRow = (this.isSetRow()?this.getRow():null);
                strategy.appendField(locator, this, "row", buffer, theRow, this.isSetRow());
            }
            return buffer;
        }

        public void setRow(List<AbstractGriddedSurfaceType.Rows.Row> value) {
            this.row = null;
            if (value!= null) {
                List<AbstractGriddedSurfaceType.Rows.Row> draftl = this.getRow();
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
            final AbstractGriddedSurfaceType.Rows that = ((AbstractGriddedSurfaceType.Rows) object);
            {
                List<AbstractGriddedSurfaceType.Rows.Row> lhsRow;
                lhsRow = (this.isSetRow()?this.getRow():null);
                List<AbstractGriddedSurfaceType.Rows.Row> rhsRow;
                rhsRow = (that.isSetRow()?that.getRow():null);
                if (!strategy.equals(LocatorUtils.property(thisLocator, "row", lhsRow), LocatorUtils.property(thatLocator, "row", rhsRow), lhsRow, rhsRow, this.isSetRow(), that.isSetRow())) {
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
                List<AbstractGriddedSurfaceType.Rows.Row> theRow;
                theRow = (this.isSetRow()?this.getRow():null);
                currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "row", theRow), currentHashCode, theRow, this.isSetRow());
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
            if (draftCopy instanceof AbstractGriddedSurfaceType.Rows) {
                final AbstractGriddedSurfaceType.Rows copy = ((AbstractGriddedSurfaceType.Rows) draftCopy);
                {
                    java.lang.Boolean rowShouldBeCopiedAndSet = strategy.shouldBeCopiedAndSet(locator, this.isSetRow());
                    if (rowShouldBeCopiedAndSet == java.lang.Boolean.TRUE) {
                        List<AbstractGriddedSurfaceType.Rows.Row> sourceRow;
                        sourceRow = (this.isSetRow()?this.getRow():null);
                        @SuppressWarnings("unchecked")
                        List<AbstractGriddedSurfaceType.Rows.Row> copyRow = ((List<AbstractGriddedSurfaceType.Rows.Row> ) strategy.copy(LocatorUtils.property(locator, "row", sourceRow), sourceRow, this.isSetRow()));
                        copy.setRow(copyRow);
                    } else {
                        if (rowShouldBeCopiedAndSet == java.lang.Boolean.FALSE) {
                            copy.unsetRow();
                        }
                    }
                }
            }
            return draftCopy;
        }

        @Override
        public Object createNewInstance() {
            return new AbstractGriddedSurfaceType.Rows();
        }

        @Override
        public void mergeFrom(Object left, Object right) {
            final MergeStrategy strategy = JAXBMergeStrategy.getInstance();
            mergeFrom(null, null, left, right, strategy);
        }

        @Override
        public void mergeFrom(ObjectLocator leftLocator, ObjectLocator rightLocator, Object left, Object right, MergeStrategy strategy) {
            if (right instanceof AbstractGriddedSurfaceType.Rows) {
                final AbstractGriddedSurfaceType.Rows target = this;
                final AbstractGriddedSurfaceType.Rows leftObject = ((AbstractGriddedSurfaceType.Rows) left);
                final AbstractGriddedSurfaceType.Rows rightObject = ((AbstractGriddedSurfaceType.Rows) right);
                {
                    java.lang.Boolean rowShouldBeMergedAndSet = strategy.shouldBeMergedAndSet(leftLocator, rightLocator, leftObject.isSetRow(), rightObject.isSetRow());
                    if (rowShouldBeMergedAndSet == java.lang.Boolean.TRUE) {
                        List<AbstractGriddedSurfaceType.Rows.Row> lhsRow;
                        lhsRow = (leftObject.isSetRow()?leftObject.getRow():null);
                        List<AbstractGriddedSurfaceType.Rows.Row> rhsRow;
                        rhsRow = (rightObject.isSetRow()?rightObject.getRow():null);
                        List<AbstractGriddedSurfaceType.Rows.Row> mergedRow = ((List<AbstractGriddedSurfaceType.Rows.Row> ) strategy.merge(LocatorUtils.property(leftLocator, "row", lhsRow), LocatorUtils.property(rightLocator, "row", rhsRow), lhsRow, rhsRow, leftObject.isSetRow(), rightObject.isSetRow()));
                        target.setRow(mergedRow);
                    } else {
                        if (rowShouldBeMergedAndSet == java.lang.Boolean.FALSE) {
                            target.unsetRow();
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
         *       <group ref="{http://www.opengis.net/gml}geometricPositionListGroup"/>
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
        public static class Row implements Cloneable, CopyTo, Equals, HashCode, MergeFrom, ToString
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
                final AbstractGriddedSurfaceType.Rows.Row that = ((AbstractGriddedSurfaceType.Rows.Row) object);
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
                if (draftCopy instanceof AbstractGriddedSurfaceType.Rows.Row) {
                    final AbstractGriddedSurfaceType.Rows.Row copy = ((AbstractGriddedSurfaceType.Rows.Row) draftCopy);
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
                return new AbstractGriddedSurfaceType.Rows.Row();
            }

            @Override
            public void mergeFrom(Object left, Object right) {
                final MergeStrategy strategy = JAXBMergeStrategy.getInstance();
                mergeFrom(null, null, left, right, strategy);
            }

            @Override
            public void mergeFrom(ObjectLocator leftLocator, ObjectLocator rightLocator, Object left, Object right, MergeStrategy strategy) {
                if (right instanceof AbstractGriddedSurfaceType.Rows.Row) {
                    final AbstractGriddedSurfaceType.Rows.Row target = this;
                    final AbstractGriddedSurfaceType.Rows.Row leftObject = ((AbstractGriddedSurfaceType.Rows.Row) left);
                    final AbstractGriddedSurfaceType.Rows.Row rightObject = ((AbstractGriddedSurfaceType.Rows.Row) right);
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

}
