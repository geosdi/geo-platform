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
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
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
 * <p>Classe Java per AbstractGriddedSurfaceType complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="AbstractGriddedSurfaceType">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.opengis.net/gml}AbstractParametricCurveSurfaceType">
 *       &lt;sequence>
 *         &lt;group ref="{http://www.opengis.net/gml}PointGrid"/>
 *       &lt;/sequence>
 *       &lt;attribute name="rows" type="{http://www.w3.org/2001/XMLSchema}integer" />
 *       &lt;attribute name="columns" type="{http://www.w3.org/2001/XMLSchema}integer" />
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AbstractGriddedSurfaceType", propOrder = {
    "arows"
})
@XmlSeeAlso({
    CylinderType.class,
    ConeType.class,
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
            AbstractGriddedSurfaceType.Rows theArows;
            theArows = this.getArows();
            strategy.appendField(locator, this, "arows", buffer, theArows);
        }
        {
            BigInteger theRows;
            theRows = this.getRows();
            strategy.appendField(locator, this, "rows", buffer, theRows);
        }
        {
            BigInteger theColumns;
            theColumns = this.getColumns();
            strategy.appendField(locator, this, "columns", buffer, theColumns);
        }
        return buffer;
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof AbstractGriddedSurfaceType)) {
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
            if (!strategy.equals(LocatorUtils.property(thisLocator, "arows", lhsArows), LocatorUtils.property(thatLocator, "arows", rhsArows), lhsArows, rhsArows)) {
                return false;
            }
        }
        {
            BigInteger lhsRows;
            lhsRows = this.getRows();
            BigInteger rhsRows;
            rhsRows = that.getRows();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "rows", lhsRows), LocatorUtils.property(thatLocator, "rows", rhsRows), lhsRows, rhsRows)) {
                return false;
            }
        }
        {
            BigInteger lhsColumns;
            lhsColumns = this.getColumns();
            BigInteger rhsColumns;
            rhsColumns = that.getColumns();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "columns", lhsColumns), LocatorUtils.property(thatLocator, "columns", rhsColumns), lhsColumns, rhsColumns)) {
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
            AbstractGriddedSurfaceType.Rows theArows;
            theArows = this.getArows();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "arows", theArows), currentHashCode, theArows);
        }
        {
            BigInteger theRows;
            theRows = this.getRows();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "rows", theRows), currentHashCode, theRows);
        }
        {
            BigInteger theColumns;
            theColumns = this.getColumns();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "columns", theColumns), currentHashCode, theColumns);
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
        if (null == target) {
            throw new IllegalArgumentException("Target argument must not be null for abstract copyable classes.");
        }
        super.copyTo(locator, target, strategy);
        if (target instanceof AbstractGriddedSurfaceType) {
            final AbstractGriddedSurfaceType copy = ((AbstractGriddedSurfaceType) target);
            if (this.isSetArows()) {
                AbstractGriddedSurfaceType.Rows sourceArows;
                sourceArows = this.getArows();
                AbstractGriddedSurfaceType.Rows copyArows = ((AbstractGriddedSurfaceType.Rows) strategy.copy(LocatorUtils.property(locator, "arows", sourceArows), sourceArows));
                copy.setArows(copyArows);
            } else {
                copy.arows = null;
            }
            if (this.isSetRows()) {
                BigInteger sourceRows;
                sourceRows = this.getRows();
                BigInteger copyRows = ((BigInteger) strategy.copy(LocatorUtils.property(locator, "rows", sourceRows), sourceRows));
                copy.setRows(copyRows);
            } else {
                copy.rows = null;
            }
            if (this.isSetColumns()) {
                BigInteger sourceColumns;
                sourceColumns = this.getColumns();
                BigInteger copyColumns = ((BigInteger) strategy.copy(LocatorUtils.property(locator, "columns", sourceColumns), sourceColumns));
                copy.setColumns(copyColumns);
            } else {
                copy.columns = null;
            }
        }
        return target;
    }

    public void mergeFrom(Object left, Object right) {
        final MergeStrategy strategy = JAXBMergeStrategy.INSTANCE;
        mergeFrom(null, null, left, right, strategy);
    }

    public void mergeFrom(ObjectLocator leftLocator, ObjectLocator rightLocator, Object left, Object right, MergeStrategy strategy) {
        super.mergeFrom(leftLocator, rightLocator, left, right, strategy);
        if (right instanceof AbstractGriddedSurfaceType) {
            final AbstractGriddedSurfaceType target = this;
            final AbstractGriddedSurfaceType leftObject = ((AbstractGriddedSurfaceType) left);
            final AbstractGriddedSurfaceType rightObject = ((AbstractGriddedSurfaceType) right);
            {
                AbstractGriddedSurfaceType.Rows lhsArows;
                lhsArows = leftObject.getArows();
                AbstractGriddedSurfaceType.Rows rhsArows;
                rhsArows = rightObject.getArows();
                target.setArows(((AbstractGriddedSurfaceType.Rows) strategy.merge(LocatorUtils.property(leftLocator, "arows", lhsArows), LocatorUtils.property(rightLocator, "arows", rhsArows), lhsArows, rhsArows)));
            }
            {
                BigInteger lhsRows;
                lhsRows = leftObject.getRows();
                BigInteger rhsRows;
                rhsRows = rightObject.getRows();
                target.setRows(((BigInteger) strategy.merge(LocatorUtils.property(leftLocator, "rows", lhsRows), LocatorUtils.property(rightLocator, "rows", rhsRows), lhsRows, rhsRows)));
            }
            {
                BigInteger lhsColumns;
                lhsColumns = leftObject.getColumns();
                BigInteger rhsColumns;
                rhsColumns = rightObject.getColumns();
                target.setColumns(((BigInteger) strategy.merge(LocatorUtils.property(leftLocator, "columns", lhsColumns), LocatorUtils.property(rightLocator, "columns", rhsColumns), lhsColumns, rhsColumns)));
            }
        }
    }

    public AbstractGriddedSurfaceType withArows(AbstractGriddedSurfaceType.Rows value) {
        setArows(value);
        return this;
    }

    public AbstractGriddedSurfaceType withRows(BigInteger value) {
        setRows(value);
        return this;
    }

    public AbstractGriddedSurfaceType withColumns(BigInteger value) {
        setColumns(value);
        return this;
    }

    @Override
    public AbstractGriddedSurfaceType withAggregationType(AggregationType value) {
        setAggregationType(value);
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
     *       &lt;sequence>
     *         &lt;element name="Row" maxOccurs="unbounded">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;group ref="{http://www.opengis.net/gml}geometricPositionListGroup"/>
     *               &lt;/restriction>
     *             &lt;/complexContent>
     *           &lt;/complexType>
     *         &lt;/element>
     *       &lt;/sequence>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "row"
    })
    public static class Rows
        implements Cloneable, CopyTo, Equals, HashCode, MergeFrom, ToString
    {

        @XmlElement(name = "Row", required = true)
        protected List<AbstractGriddedSurfaceType.Rows.Row> row;

        /**
         * Gets the value of the row property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the row property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getRow().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link AbstractGriddedSurfaceType.Rows.Row }
         * 
         * 
         */
        public List<AbstractGriddedSurfaceType.Rows.Row> getRow() {
            if (row == null) {
                row = new ArrayList<AbstractGriddedSurfaceType.Rows.Row>();
            }
            return this.row;
        }

        public boolean isSetRow() {
            return ((this.row!= null)&&(!this.row.isEmpty()));
        }

        public void unsetRow() {
            this.row = null;
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
                List<AbstractGriddedSurfaceType.Rows.Row> theRow;
                theRow = this.getRow();
                strategy.appendField(locator, this, "row", buffer, theRow);
            }
            return buffer;
        }

        public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
            if (!(object instanceof AbstractGriddedSurfaceType.Rows)) {
                return false;
            }
            if (this == object) {
                return true;
            }
            final AbstractGriddedSurfaceType.Rows that = ((AbstractGriddedSurfaceType.Rows) object);
            {
                List<AbstractGriddedSurfaceType.Rows.Row> lhsRow;
                lhsRow = this.getRow();
                List<AbstractGriddedSurfaceType.Rows.Row> rhsRow;
                rhsRow = that.getRow();
                if (!strategy.equals(LocatorUtils.property(thisLocator, "row", lhsRow), LocatorUtils.property(thatLocator, "row", rhsRow), lhsRow, rhsRow)) {
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
                List<AbstractGriddedSurfaceType.Rows.Row> theRow;
                theRow = this.getRow();
                currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "row", theRow), currentHashCode, theRow);
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
            if (draftCopy instanceof AbstractGriddedSurfaceType.Rows) {
                final AbstractGriddedSurfaceType.Rows copy = ((AbstractGriddedSurfaceType.Rows) draftCopy);
                if (this.isSetRow()) {
                    List<AbstractGriddedSurfaceType.Rows.Row> sourceRow;
                    sourceRow = this.getRow();
                    @SuppressWarnings("unchecked")
                    List<AbstractGriddedSurfaceType.Rows.Row> copyRow = ((List<AbstractGriddedSurfaceType.Rows.Row> ) strategy.copy(LocatorUtils.property(locator, "row", sourceRow), sourceRow));
                    copy.unsetRow();
                    List<AbstractGriddedSurfaceType.Rows.Row> uniqueRowl = copy.getRow();
                    uniqueRowl.addAll(copyRow);
                } else {
                    copy.unsetRow();
                }
            }
            return draftCopy;
        }

        public Object createNewInstance() {
            return new AbstractGriddedSurfaceType.Rows();
        }

        public void mergeFrom(Object left, Object right) {
            final MergeStrategy strategy = JAXBMergeStrategy.INSTANCE;
            mergeFrom(null, null, left, right, strategy);
        }

        public void mergeFrom(ObjectLocator leftLocator, ObjectLocator rightLocator, Object left, Object right, MergeStrategy strategy) {
            if (right instanceof AbstractGriddedSurfaceType.Rows) {
                final AbstractGriddedSurfaceType.Rows target = this;
                final AbstractGriddedSurfaceType.Rows leftObject = ((AbstractGriddedSurfaceType.Rows) left);
                final AbstractGriddedSurfaceType.Rows rightObject = ((AbstractGriddedSurfaceType.Rows) right);
                {
                    List<AbstractGriddedSurfaceType.Rows.Row> lhsRow;
                    lhsRow = leftObject.getRow();
                    List<AbstractGriddedSurfaceType.Rows.Row> rhsRow;
                    rhsRow = rightObject.getRow();
                    target.unsetRow();
                    List<AbstractGriddedSurfaceType.Rows.Row> uniqueRowl = target.getRow();
                    uniqueRowl.addAll(((List<AbstractGriddedSurfaceType.Rows.Row> ) strategy.merge(LocatorUtils.property(leftLocator, "row", lhsRow), LocatorUtils.property(rightLocator, "row", rhsRow), lhsRow, rhsRow)));
                }
            }
        }

        public void setRow(List<AbstractGriddedSurfaceType.Rows.Row> value) {
            List<AbstractGriddedSurfaceType.Rows.Row> draftl = this.getRow();
            draftl.addAll(value);
        }

        public AbstractGriddedSurfaceType.Rows withRow(AbstractGriddedSurfaceType.Rows.Row... values) {
            if (values!= null) {
                for (AbstractGriddedSurfaceType.Rows.Row value: values) {
                    getRow().add(value);
                }
            }
            return this;
        }

        public AbstractGriddedSurfaceType.Rows withRow(Collection<AbstractGriddedSurfaceType.Rows.Row> values) {
            if (values!= null) {
                getRow().addAll(values);
            }
            return this;
        }

        public AbstractGriddedSurfaceType.Rows withRow(List<AbstractGriddedSurfaceType.Rows.Row> value) {
            setRow(value);
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
         *       &lt;group ref="{http://www.opengis.net/gml}geometricPositionListGroup"/>
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
        public static class Row
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
                if (!(object instanceof AbstractGriddedSurfaceType.Rows.Row)) {
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
                if (draftCopy instanceof AbstractGriddedSurfaceType.Rows.Row) {
                    final AbstractGriddedSurfaceType.Rows.Row copy = ((AbstractGriddedSurfaceType.Rows.Row) draftCopy);
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
                return new AbstractGriddedSurfaceType.Rows.Row();
            }

            public void mergeFrom(Object left, Object right) {
                final MergeStrategy strategy = JAXBMergeStrategy.INSTANCE;
                mergeFrom(null, null, left, right, strategy);
            }

            public void mergeFrom(ObjectLocator leftLocator, ObjectLocator rightLocator, Object left, Object right, MergeStrategy strategy) {
                if (right instanceof AbstractGriddedSurfaceType.Rows.Row) {
                    final AbstractGriddedSurfaceType.Rows.Row target = this;
                    final AbstractGriddedSurfaceType.Rows.Row leftObject = ((AbstractGriddedSurfaceType.Rows.Row) left);
                    final AbstractGriddedSurfaceType.Rows.Row rightObject = ((AbstractGriddedSurfaceType.Rows.Row) right);
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

            public AbstractGriddedSurfaceType.Rows.Row withPosList(DirectPositionListType value) {
                setPosList(value);
                return this;
            }

            public AbstractGriddedSurfaceType.Rows.Row withGeometricPositionGroup(Object... values) {
                if (values!= null) {
                    for (Object value: values) {
                        getGeometricPositionGroup().add(value);
                    }
                }
                return this;
            }

            public AbstractGriddedSurfaceType.Rows.Row withGeometricPositionGroup(Collection<Object> values) {
                if (values!= null) {
                    getGeometricPositionGroup().addAll(values);
                }
                return this;
            }

            public AbstractGriddedSurfaceType.Rows.Row withGeometricPositionGroup(List<Object> value) {
                setGeometricPositionGroup(value);
                return this;
            }

        }

    }

}
