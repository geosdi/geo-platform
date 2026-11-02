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
import jakarta.xml.bind.annotation.XmlSchemaType;
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
 * <p>Classe Java per EnvelopeType complex type.</p>
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.</p>
 * 
 * <pre>{@code
 * <complexType name="EnvelopeType">
 *   <complexContent>
 *     <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       <choice>
 *         <sequence>
 *           <element name="lowerCorner" type="{http://www.opengis.net/gml}DirectPositionType"/>
 *           <element name="upperCorner" type="{http://www.opengis.net/gml}DirectPositionType"/>
 *         </sequence>
 *         <element ref="{http://www.opengis.net/gml}pos" maxOccurs="2" minOccurs="2"/>
 *         <element ref="{http://www.opengis.net/gml}coordinates"/>
 *       </choice>
 *       <attGroup ref="{http://www.opengis.net/gml}SRSReferenceGroup"/>
 *     </restriction>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "EnvelopeType", propOrder = {
    "lowerCorner",
    "upperCorner",
    "pos",
    "coordinates"
})
@XmlSeeAlso({
    EnvelopeWithTimePeriodType.class
})
public class EnvelopeType implements Cloneable, CopyTo, Equals, HashCode, MergeFrom, ToString
{

    protected DirectPositionType lowerCorner;
    protected DirectPositionType upperCorner;
    protected List<DirectPositionType> pos;
    protected CoordinatesType coordinates;
    @XmlAttribute(name = "srsName")
    @XmlSchemaType(name = "anyURI")
    protected String srsName;
    @XmlAttribute(name = "srsDimension")
    @XmlSchemaType(name = "positiveInteger")
    protected BigInteger srsDimension;
    @XmlAttribute(name = "axisLabels")
    protected List<String> axisLabels;
    @XmlAttribute(name = "uomLabels")
    protected List<String> uomLabels;

    /**
     * Recupera il valore della proprietà lowerCorner.
     * 
     * @return
     *     possible object is
     *     {@link DirectPositionType }
     *     
     */
    public DirectPositionType getLowerCorner() {
        return lowerCorner;
    }

    /**
     * Imposta il valore della proprietà lowerCorner.
     * 
     * @param value
     *     allowed object is
     *     {@link DirectPositionType }
     *     
     */
    public void setLowerCorner(DirectPositionType value) {
        this.lowerCorner = value;
    }

    public boolean isSetLowerCorner() {
        return (this.lowerCorner!= null);
    }

    /**
     * Recupera il valore della proprietà upperCorner.
     * 
     * @return
     *     possible object is
     *     {@link DirectPositionType }
     *     
     */
    public DirectPositionType getUpperCorner() {
        return upperCorner;
    }

    /**
     * Imposta il valore della proprietà upperCorner.
     * 
     * @param value
     *     allowed object is
     *     {@link DirectPositionType }
     *     
     */
    public void setUpperCorner(DirectPositionType value) {
        this.upperCorner = value;
    }

    public boolean isSetUpperCorner() {
        return (this.upperCorner!= null);
    }

    /**
     * Gets the value of the pos property.
     * 
     * <p>This accessor method returns a reference to the live list,
     * not a snapshot. Therefore, any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a {@code set} method for the pos property.</p>
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * </p>
     * <pre>
     * getPos().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DirectPositionType }
     * </p>
     * 
     * 
     * @return
     *     The value of the pos property.
     */
    public List<DirectPositionType> getPos() {
        if (pos == null) {
            pos = new ArrayList<>();
        }
        return this.pos;
    }

    public boolean isSetPos() {
        return ((this.pos!= null)&&(!this.pos.isEmpty()));
    }

    public void unsetPos() {
        this.pos = null;
    }

    /**
     * Recupera il valore della proprietà coordinates.
     * 
     * @return
     *     possible object is
     *     {@link CoordinatesType }
     *     
     */
    public CoordinatesType getCoordinates() {
        return coordinates;
    }

    /**
     * Imposta il valore della proprietà coordinates.
     * 
     * @param value
     *     allowed object is
     *     {@link CoordinatesType }
     *     
     */
    public void setCoordinates(CoordinatesType value) {
        this.coordinates = value;
    }

    public boolean isSetCoordinates() {
        return (this.coordinates!= null);
    }

    /**
     * Recupera il valore della proprietà srsName.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSrsName() {
        return srsName;
    }

    /**
     * Imposta il valore della proprietà srsName.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSrsName(String value) {
        this.srsName = value;
    }

    public boolean isSetSrsName() {
        return (this.srsName!= null);
    }

    /**
     * Recupera il valore della proprietà srsDimension.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getSrsDimension() {
        return srsDimension;
    }

    /**
     * Imposta il valore della proprietà srsDimension.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setSrsDimension(BigInteger value) {
        this.srsDimension = value;
    }

    public boolean isSetSrsDimension() {
        return (this.srsDimension!= null);
    }

    /**
     * Gets the value of the axisLabels property.
     * 
     * <p>This accessor method returns a reference to the live list,
     * not a snapshot. Therefore, any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a {@code set} method for the axisLabels property.</p>
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * </p>
     * <pre>
     * getAxisLabels().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * </p>
     * 
     * 
     * @return
     *     The value of the axisLabels property.
     */
    public List<String> getAxisLabels() {
        if (axisLabels == null) {
            axisLabels = new ArrayList<>();
        }
        return this.axisLabels;
    }

    public boolean isSetAxisLabels() {
        return ((this.axisLabels!= null)&&(!this.axisLabels.isEmpty()));
    }

    public void unsetAxisLabels() {
        this.axisLabels = null;
    }

    /**
     * Gets the value of the uomLabels property.
     * 
     * <p>This accessor method returns a reference to the live list,
     * not a snapshot. Therefore, any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a {@code set} method for the uomLabels property.</p>
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * </p>
     * <pre>
     * getUomLabels().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * </p>
     * 
     * 
     * @return
     *     The value of the uomLabels property.
     */
    public List<String> getUomLabels() {
        if (uomLabels == null) {
            uomLabels = new ArrayList<>();
        }
        return this.uomLabels;
    }

    public boolean isSetUomLabels() {
        return ((this.uomLabels!= null)&&(!this.uomLabels.isEmpty()));
    }

    public void unsetUomLabels() {
        this.uomLabels = null;
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
            DirectPositionType theLowerCorner;
            theLowerCorner = this.getLowerCorner();
            strategy.appendField(locator, this, "lowerCorner", buffer, theLowerCorner, this.isSetLowerCorner());
        }
        {
            DirectPositionType theUpperCorner;
            theUpperCorner = this.getUpperCorner();
            strategy.appendField(locator, this, "upperCorner", buffer, theUpperCorner, this.isSetUpperCorner());
        }
        {
            List<DirectPositionType> thePos;
            thePos = (this.isSetPos()?this.getPos():null);
            strategy.appendField(locator, this, "pos", buffer, thePos, this.isSetPos());
        }
        {
            CoordinatesType theCoordinates;
            theCoordinates = this.getCoordinates();
            strategy.appendField(locator, this, "coordinates", buffer, theCoordinates, this.isSetCoordinates());
        }
        {
            String theSrsName;
            theSrsName = this.getSrsName();
            strategy.appendField(locator, this, "srsName", buffer, theSrsName, this.isSetSrsName());
        }
        {
            BigInteger theSrsDimension;
            theSrsDimension = this.getSrsDimension();
            strategy.appendField(locator, this, "srsDimension", buffer, theSrsDimension, this.isSetSrsDimension());
        }
        {
            List<String> theAxisLabels;
            theAxisLabels = (this.isSetAxisLabels()?this.getAxisLabels():null);
            strategy.appendField(locator, this, "axisLabels", buffer, theAxisLabels, this.isSetAxisLabels());
        }
        {
            List<String> theUomLabels;
            theUomLabels = (this.isSetUomLabels()?this.getUomLabels():null);
            strategy.appendField(locator, this, "uomLabels", buffer, theUomLabels, this.isSetUomLabels());
        }
        return buffer;
    }

    public void setPos(List<DirectPositionType> value) {
        this.pos = null;
        if (value!= null) {
            List<DirectPositionType> draftl = this.getPos();
            draftl.addAll(value);
        }
    }

    public void setAxisLabels(List<String> value) {
        this.axisLabels = null;
        if (value!= null) {
            List<String> draftl = this.getAxisLabels();
            draftl.addAll(value);
        }
    }

    public void setUomLabels(List<String> value) {
        this.uomLabels = null;
        if (value!= null) {
            List<String> draftl = this.getUomLabels();
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
        final EnvelopeType that = ((EnvelopeType) object);
        {
            DirectPositionType lhsLowerCorner;
            lhsLowerCorner = this.getLowerCorner();
            DirectPositionType rhsLowerCorner;
            rhsLowerCorner = that.getLowerCorner();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "lowerCorner", lhsLowerCorner), LocatorUtils.property(thatLocator, "lowerCorner", rhsLowerCorner), lhsLowerCorner, rhsLowerCorner, this.isSetLowerCorner(), that.isSetLowerCorner())) {
                return false;
            }
        }
        {
            DirectPositionType lhsUpperCorner;
            lhsUpperCorner = this.getUpperCorner();
            DirectPositionType rhsUpperCorner;
            rhsUpperCorner = that.getUpperCorner();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "upperCorner", lhsUpperCorner), LocatorUtils.property(thatLocator, "upperCorner", rhsUpperCorner), lhsUpperCorner, rhsUpperCorner, this.isSetUpperCorner(), that.isSetUpperCorner())) {
                return false;
            }
        }
        {
            List<DirectPositionType> lhsPos;
            lhsPos = (this.isSetPos()?this.getPos():null);
            List<DirectPositionType> rhsPos;
            rhsPos = (that.isSetPos()?that.getPos():null);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "pos", lhsPos), LocatorUtils.property(thatLocator, "pos", rhsPos), lhsPos, rhsPos, this.isSetPos(), that.isSetPos())) {
                return false;
            }
        }
        {
            CoordinatesType lhsCoordinates;
            lhsCoordinates = this.getCoordinates();
            CoordinatesType rhsCoordinates;
            rhsCoordinates = that.getCoordinates();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "coordinates", lhsCoordinates), LocatorUtils.property(thatLocator, "coordinates", rhsCoordinates), lhsCoordinates, rhsCoordinates, this.isSetCoordinates(), that.isSetCoordinates())) {
                return false;
            }
        }
        {
            String lhsSrsName;
            lhsSrsName = this.getSrsName();
            String rhsSrsName;
            rhsSrsName = that.getSrsName();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "srsName", lhsSrsName), LocatorUtils.property(thatLocator, "srsName", rhsSrsName), lhsSrsName, rhsSrsName, this.isSetSrsName(), that.isSetSrsName())) {
                return false;
            }
        }
        {
            BigInteger lhsSrsDimension;
            lhsSrsDimension = this.getSrsDimension();
            BigInteger rhsSrsDimension;
            rhsSrsDimension = that.getSrsDimension();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "srsDimension", lhsSrsDimension), LocatorUtils.property(thatLocator, "srsDimension", rhsSrsDimension), lhsSrsDimension, rhsSrsDimension, this.isSetSrsDimension(), that.isSetSrsDimension())) {
                return false;
            }
        }
        {
            List<String> lhsAxisLabels;
            lhsAxisLabels = (this.isSetAxisLabels()?this.getAxisLabels():null);
            List<String> rhsAxisLabels;
            rhsAxisLabels = (that.isSetAxisLabels()?that.getAxisLabels():null);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "axisLabels", lhsAxisLabels), LocatorUtils.property(thatLocator, "axisLabels", rhsAxisLabels), lhsAxisLabels, rhsAxisLabels, this.isSetAxisLabels(), that.isSetAxisLabels())) {
                return false;
            }
        }
        {
            List<String> lhsUomLabels;
            lhsUomLabels = (this.isSetUomLabels()?this.getUomLabels():null);
            List<String> rhsUomLabels;
            rhsUomLabels = (that.isSetUomLabels()?that.getUomLabels():null);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "uomLabels", lhsUomLabels), LocatorUtils.property(thatLocator, "uomLabels", rhsUomLabels), lhsUomLabels, rhsUomLabels, this.isSetUomLabels(), that.isSetUomLabels())) {
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
            DirectPositionType theLowerCorner;
            theLowerCorner = this.getLowerCorner();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "lowerCorner", theLowerCorner), currentHashCode, theLowerCorner, this.isSetLowerCorner());
        }
        {
            DirectPositionType theUpperCorner;
            theUpperCorner = this.getUpperCorner();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "upperCorner", theUpperCorner), currentHashCode, theUpperCorner, this.isSetUpperCorner());
        }
        {
            List<DirectPositionType> thePos;
            thePos = (this.isSetPos()?this.getPos():null);
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "pos", thePos), currentHashCode, thePos, this.isSetPos());
        }
        {
            CoordinatesType theCoordinates;
            theCoordinates = this.getCoordinates();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "coordinates", theCoordinates), currentHashCode, theCoordinates, this.isSetCoordinates());
        }
        {
            String theSrsName;
            theSrsName = this.getSrsName();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "srsName", theSrsName), currentHashCode, theSrsName, this.isSetSrsName());
        }
        {
            BigInteger theSrsDimension;
            theSrsDimension = this.getSrsDimension();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "srsDimension", theSrsDimension), currentHashCode, theSrsDimension, this.isSetSrsDimension());
        }
        {
            List<String> theAxisLabels;
            theAxisLabels = (this.isSetAxisLabels()?this.getAxisLabels():null);
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "axisLabels", theAxisLabels), currentHashCode, theAxisLabels, this.isSetAxisLabels());
        }
        {
            List<String> theUomLabels;
            theUomLabels = (this.isSetUomLabels()?this.getUomLabels():null);
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "uomLabels", theUomLabels), currentHashCode, theUomLabels, this.isSetUomLabels());
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
        if (draftCopy instanceof EnvelopeType) {
            final EnvelopeType copy = ((EnvelopeType) draftCopy);
            {
                java.lang.Boolean lowerCornerShouldBeCopiedAndSet = strategy.shouldBeCopiedAndSet(locator, this.isSetLowerCorner());
                if (lowerCornerShouldBeCopiedAndSet == java.lang.Boolean.TRUE) {
                    DirectPositionType sourceLowerCorner;
                    sourceLowerCorner = this.getLowerCorner();
                    DirectPositionType copyLowerCorner = ((DirectPositionType) strategy.copy(LocatorUtils.property(locator, "lowerCorner", sourceLowerCorner), sourceLowerCorner, this.isSetLowerCorner()));
                    copy.setLowerCorner(copyLowerCorner);
                } else {
                    if (lowerCornerShouldBeCopiedAndSet == java.lang.Boolean.FALSE) {
                        copy.lowerCorner = null;
                    }
                }
            }
            {
                java.lang.Boolean upperCornerShouldBeCopiedAndSet = strategy.shouldBeCopiedAndSet(locator, this.isSetUpperCorner());
                if (upperCornerShouldBeCopiedAndSet == java.lang.Boolean.TRUE) {
                    DirectPositionType sourceUpperCorner;
                    sourceUpperCorner = this.getUpperCorner();
                    DirectPositionType copyUpperCorner = ((DirectPositionType) strategy.copy(LocatorUtils.property(locator, "upperCorner", sourceUpperCorner), sourceUpperCorner, this.isSetUpperCorner()));
                    copy.setUpperCorner(copyUpperCorner);
                } else {
                    if (upperCornerShouldBeCopiedAndSet == java.lang.Boolean.FALSE) {
                        copy.upperCorner = null;
                    }
                }
            }
            {
                java.lang.Boolean posShouldBeCopiedAndSet = strategy.shouldBeCopiedAndSet(locator, this.isSetPos());
                if (posShouldBeCopiedAndSet == java.lang.Boolean.TRUE) {
                    List<DirectPositionType> sourcePos;
                    sourcePos = (this.isSetPos()?this.getPos():null);
                    @SuppressWarnings("unchecked")
                    List<DirectPositionType> copyPos = ((List<DirectPositionType> ) strategy.copy(LocatorUtils.property(locator, "pos", sourcePos), sourcePos, this.isSetPos()));
                    copy.setPos(copyPos);
                } else {
                    if (posShouldBeCopiedAndSet == java.lang.Boolean.FALSE) {
                        copy.unsetPos();
                    }
                }
            }
            {
                java.lang.Boolean coordinatesShouldBeCopiedAndSet = strategy.shouldBeCopiedAndSet(locator, this.isSetCoordinates());
                if (coordinatesShouldBeCopiedAndSet == java.lang.Boolean.TRUE) {
                    CoordinatesType sourceCoordinates;
                    sourceCoordinates = this.getCoordinates();
                    CoordinatesType copyCoordinates = ((CoordinatesType) strategy.copy(LocatorUtils.property(locator, "coordinates", sourceCoordinates), sourceCoordinates, this.isSetCoordinates()));
                    copy.setCoordinates(copyCoordinates);
                } else {
                    if (coordinatesShouldBeCopiedAndSet == java.lang.Boolean.FALSE) {
                        copy.coordinates = null;
                    }
                }
            }
            {
                java.lang.Boolean srsNameShouldBeCopiedAndSet = strategy.shouldBeCopiedAndSet(locator, this.isSetSrsName());
                if (srsNameShouldBeCopiedAndSet == java.lang.Boolean.TRUE) {
                    String sourceSrsName;
                    sourceSrsName = this.getSrsName();
                    String copySrsName = ((String) strategy.copy(LocatorUtils.property(locator, "srsName", sourceSrsName), sourceSrsName, this.isSetSrsName()));
                    copy.setSrsName(copySrsName);
                } else {
                    if (srsNameShouldBeCopiedAndSet == java.lang.Boolean.FALSE) {
                        copy.srsName = null;
                    }
                }
            }
            {
                java.lang.Boolean srsDimensionShouldBeCopiedAndSet = strategy.shouldBeCopiedAndSet(locator, this.isSetSrsDimension());
                if (srsDimensionShouldBeCopiedAndSet == java.lang.Boolean.TRUE) {
                    BigInteger sourceSrsDimension;
                    sourceSrsDimension = this.getSrsDimension();
                    BigInteger copySrsDimension = ((BigInteger) strategy.copy(LocatorUtils.property(locator, "srsDimension", sourceSrsDimension), sourceSrsDimension, this.isSetSrsDimension()));
                    copy.setSrsDimension(copySrsDimension);
                } else {
                    if (srsDimensionShouldBeCopiedAndSet == java.lang.Boolean.FALSE) {
                        copy.srsDimension = null;
                    }
                }
            }
            {
                java.lang.Boolean axisLabelsShouldBeCopiedAndSet = strategy.shouldBeCopiedAndSet(locator, this.isSetAxisLabels());
                if (axisLabelsShouldBeCopiedAndSet == java.lang.Boolean.TRUE) {
                    List<String> sourceAxisLabels;
                    sourceAxisLabels = (this.isSetAxisLabels()?this.getAxisLabels():null);
                    @SuppressWarnings("unchecked")
                    List<String> copyAxisLabels = ((List<String> ) strategy.copy(LocatorUtils.property(locator, "axisLabels", sourceAxisLabels), sourceAxisLabels, this.isSetAxisLabels()));
                    copy.setAxisLabels(copyAxisLabels);
                } else {
                    if (axisLabelsShouldBeCopiedAndSet == java.lang.Boolean.FALSE) {
                        copy.unsetAxisLabels();
                    }
                }
            }
            {
                java.lang.Boolean uomLabelsShouldBeCopiedAndSet = strategy.shouldBeCopiedAndSet(locator, this.isSetUomLabels());
                if (uomLabelsShouldBeCopiedAndSet == java.lang.Boolean.TRUE) {
                    List<String> sourceUomLabels;
                    sourceUomLabels = (this.isSetUomLabels()?this.getUomLabels():null);
                    @SuppressWarnings("unchecked")
                    List<String> copyUomLabels = ((List<String> ) strategy.copy(LocatorUtils.property(locator, "uomLabels", sourceUomLabels), sourceUomLabels, this.isSetUomLabels()));
                    copy.setUomLabels(copyUomLabels);
                } else {
                    if (uomLabelsShouldBeCopiedAndSet == java.lang.Boolean.FALSE) {
                        copy.unsetUomLabels();
                    }
                }
            }
        }
        return draftCopy;
    }

    @Override
    public Object createNewInstance() {
        return new EnvelopeType();
    }

    @Override
    public void mergeFrom(Object left, Object right) {
        final MergeStrategy strategy = JAXBMergeStrategy.getInstance();
        mergeFrom(null, null, left, right, strategy);
    }

    @Override
    public void mergeFrom(ObjectLocator leftLocator, ObjectLocator rightLocator, Object left, Object right, MergeStrategy strategy) {
        if (right instanceof EnvelopeType) {
            final EnvelopeType target = this;
            final EnvelopeType leftObject = ((EnvelopeType) left);
            final EnvelopeType rightObject = ((EnvelopeType) right);
            {
                java.lang.Boolean lowerCornerShouldBeMergedAndSet = strategy.shouldBeMergedAndSet(leftLocator, rightLocator, leftObject.isSetLowerCorner(), rightObject.isSetLowerCorner());
                if (lowerCornerShouldBeMergedAndSet == java.lang.Boolean.TRUE) {
                    DirectPositionType lhsLowerCorner;
                    lhsLowerCorner = leftObject.getLowerCorner();
                    DirectPositionType rhsLowerCorner;
                    rhsLowerCorner = rightObject.getLowerCorner();
                    DirectPositionType mergedLowerCorner = ((DirectPositionType) strategy.merge(LocatorUtils.property(leftLocator, "lowerCorner", lhsLowerCorner), LocatorUtils.property(rightLocator, "lowerCorner", rhsLowerCorner), lhsLowerCorner, rhsLowerCorner, leftObject.isSetLowerCorner(), rightObject.isSetLowerCorner()));
                    target.setLowerCorner(mergedLowerCorner);
                } else {
                    if (lowerCornerShouldBeMergedAndSet == java.lang.Boolean.FALSE) {
                        target.lowerCorner = null;
                    }
                }
            }
            {
                java.lang.Boolean upperCornerShouldBeMergedAndSet = strategy.shouldBeMergedAndSet(leftLocator, rightLocator, leftObject.isSetUpperCorner(), rightObject.isSetUpperCorner());
                if (upperCornerShouldBeMergedAndSet == java.lang.Boolean.TRUE) {
                    DirectPositionType lhsUpperCorner;
                    lhsUpperCorner = leftObject.getUpperCorner();
                    DirectPositionType rhsUpperCorner;
                    rhsUpperCorner = rightObject.getUpperCorner();
                    DirectPositionType mergedUpperCorner = ((DirectPositionType) strategy.merge(LocatorUtils.property(leftLocator, "upperCorner", lhsUpperCorner), LocatorUtils.property(rightLocator, "upperCorner", rhsUpperCorner), lhsUpperCorner, rhsUpperCorner, leftObject.isSetUpperCorner(), rightObject.isSetUpperCorner()));
                    target.setUpperCorner(mergedUpperCorner);
                } else {
                    if (upperCornerShouldBeMergedAndSet == java.lang.Boolean.FALSE) {
                        target.upperCorner = null;
                    }
                }
            }
            {
                java.lang.Boolean posShouldBeMergedAndSet = strategy.shouldBeMergedAndSet(leftLocator, rightLocator, leftObject.isSetPos(), rightObject.isSetPos());
                if (posShouldBeMergedAndSet == java.lang.Boolean.TRUE) {
                    List<DirectPositionType> lhsPos;
                    lhsPos = (leftObject.isSetPos()?leftObject.getPos():null);
                    List<DirectPositionType> rhsPos;
                    rhsPos = (rightObject.isSetPos()?rightObject.getPos():null);
                    List<DirectPositionType> mergedPos = ((List<DirectPositionType> ) strategy.merge(LocatorUtils.property(leftLocator, "pos", lhsPos), LocatorUtils.property(rightLocator, "pos", rhsPos), lhsPos, rhsPos, leftObject.isSetPos(), rightObject.isSetPos()));
                    target.setPos(mergedPos);
                } else {
                    if (posShouldBeMergedAndSet == java.lang.Boolean.FALSE) {
                        target.unsetPos();
                    }
                }
            }
            {
                java.lang.Boolean coordinatesShouldBeMergedAndSet = strategy.shouldBeMergedAndSet(leftLocator, rightLocator, leftObject.isSetCoordinates(), rightObject.isSetCoordinates());
                if (coordinatesShouldBeMergedAndSet == java.lang.Boolean.TRUE) {
                    CoordinatesType lhsCoordinates;
                    lhsCoordinates = leftObject.getCoordinates();
                    CoordinatesType rhsCoordinates;
                    rhsCoordinates = rightObject.getCoordinates();
                    CoordinatesType mergedCoordinates = ((CoordinatesType) strategy.merge(LocatorUtils.property(leftLocator, "coordinates", lhsCoordinates), LocatorUtils.property(rightLocator, "coordinates", rhsCoordinates), lhsCoordinates, rhsCoordinates, leftObject.isSetCoordinates(), rightObject.isSetCoordinates()));
                    target.setCoordinates(mergedCoordinates);
                } else {
                    if (coordinatesShouldBeMergedAndSet == java.lang.Boolean.FALSE) {
                        target.coordinates = null;
                    }
                }
            }
            {
                java.lang.Boolean srsNameShouldBeMergedAndSet = strategy.shouldBeMergedAndSet(leftLocator, rightLocator, leftObject.isSetSrsName(), rightObject.isSetSrsName());
                if (srsNameShouldBeMergedAndSet == java.lang.Boolean.TRUE) {
                    String lhsSrsName;
                    lhsSrsName = leftObject.getSrsName();
                    String rhsSrsName;
                    rhsSrsName = rightObject.getSrsName();
                    String mergedSrsName = ((String) strategy.merge(LocatorUtils.property(leftLocator, "srsName", lhsSrsName), LocatorUtils.property(rightLocator, "srsName", rhsSrsName), lhsSrsName, rhsSrsName, leftObject.isSetSrsName(), rightObject.isSetSrsName()));
                    target.setSrsName(mergedSrsName);
                } else {
                    if (srsNameShouldBeMergedAndSet == java.lang.Boolean.FALSE) {
                        target.srsName = null;
                    }
                }
            }
            {
                java.lang.Boolean srsDimensionShouldBeMergedAndSet = strategy.shouldBeMergedAndSet(leftLocator, rightLocator, leftObject.isSetSrsDimension(), rightObject.isSetSrsDimension());
                if (srsDimensionShouldBeMergedAndSet == java.lang.Boolean.TRUE) {
                    BigInteger lhsSrsDimension;
                    lhsSrsDimension = leftObject.getSrsDimension();
                    BigInteger rhsSrsDimension;
                    rhsSrsDimension = rightObject.getSrsDimension();
                    BigInteger mergedSrsDimension = ((BigInteger) strategy.merge(LocatorUtils.property(leftLocator, "srsDimension", lhsSrsDimension), LocatorUtils.property(rightLocator, "srsDimension", rhsSrsDimension), lhsSrsDimension, rhsSrsDimension, leftObject.isSetSrsDimension(), rightObject.isSetSrsDimension()));
                    target.setSrsDimension(mergedSrsDimension);
                } else {
                    if (srsDimensionShouldBeMergedAndSet == java.lang.Boolean.FALSE) {
                        target.srsDimension = null;
                    }
                }
            }
            {
                java.lang.Boolean axisLabelsShouldBeMergedAndSet = strategy.shouldBeMergedAndSet(leftLocator, rightLocator, leftObject.isSetAxisLabels(), rightObject.isSetAxisLabels());
                if (axisLabelsShouldBeMergedAndSet == java.lang.Boolean.TRUE) {
                    List<String> lhsAxisLabels;
                    lhsAxisLabels = (leftObject.isSetAxisLabels()?leftObject.getAxisLabels():null);
                    List<String> rhsAxisLabels;
                    rhsAxisLabels = (rightObject.isSetAxisLabels()?rightObject.getAxisLabels():null);
                    List<String> mergedAxisLabels = ((List<String> ) strategy.merge(LocatorUtils.property(leftLocator, "axisLabels", lhsAxisLabels), LocatorUtils.property(rightLocator, "axisLabels", rhsAxisLabels), lhsAxisLabels, rhsAxisLabels, leftObject.isSetAxisLabels(), rightObject.isSetAxisLabels()));
                    target.setAxisLabels(mergedAxisLabels);
                } else {
                    if (axisLabelsShouldBeMergedAndSet == java.lang.Boolean.FALSE) {
                        target.unsetAxisLabels();
                    }
                }
            }
            {
                java.lang.Boolean uomLabelsShouldBeMergedAndSet = strategy.shouldBeMergedAndSet(leftLocator, rightLocator, leftObject.isSetUomLabels(), rightObject.isSetUomLabels());
                if (uomLabelsShouldBeMergedAndSet == java.lang.Boolean.TRUE) {
                    List<String> lhsUomLabels;
                    lhsUomLabels = (leftObject.isSetUomLabels()?leftObject.getUomLabels():null);
                    List<String> rhsUomLabels;
                    rhsUomLabels = (rightObject.isSetUomLabels()?rightObject.getUomLabels():null);
                    List<String> mergedUomLabels = ((List<String> ) strategy.merge(LocatorUtils.property(leftLocator, "uomLabels", lhsUomLabels), LocatorUtils.property(rightLocator, "uomLabels", rhsUomLabels), lhsUomLabels, rhsUomLabels, leftObject.isSetUomLabels(), rightObject.isSetUomLabels()));
                    target.setUomLabels(mergedUomLabels);
                } else {
                    if (uomLabelsShouldBeMergedAndSet == java.lang.Boolean.FALSE) {
                        target.unsetUomLabels();
                    }
                }
            }
        }
    }

}
