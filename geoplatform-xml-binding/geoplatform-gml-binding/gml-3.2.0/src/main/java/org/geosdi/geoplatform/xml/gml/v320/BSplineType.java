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
import jakarta.xml.bind.JAXBElement;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlElementRef;
import jakarta.xml.bind.annotation.XmlElementRefs;
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
 * <p>Classe Java per BSplineType complex type.</p>
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.</p>
 * 
 * <pre>{@code
 * <complexType name="BSplineType">
 *   <complexContent>
 *     <extension base="{http://www.opengis.net/gml}AbstractCurveSegmentType">
 *       <sequence>
 *         <choice>
 *           <choice maxOccurs="unbounded" minOccurs="0">
 *             <element ref="{http://www.opengis.net/gml}pos"/>
 *             <element ref="{http://www.opengis.net/gml}pointProperty"/>
 *             <element ref="{http://www.opengis.net/gml}pointRep"/>
 *           </choice>
 *           <element ref="{http://www.opengis.net/gml}posList"/>
 *           <element ref="{http://www.opengis.net/gml}coordinates"/>
 *         </choice>
 *         <element name="degree" type="{http://www.w3.org/2001/XMLSchema}nonNegativeInteger"/>
 *         <element name="knot" type="{http://www.opengis.net/gml}KnotPropertyType" maxOccurs="unbounded" minOccurs="2"/>
 *       </sequence>
 *       <attribute name="interpolation" type="{http://www.opengis.net/gml}CurveInterpolationType" default="polynomialSpline" />
 *       <attribute name="isPolynomial" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       <attribute name="knotType" type="{http://www.opengis.net/gml}KnotTypesType" />
 *     </extension>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "BSplineType", propOrder = {
    "posOrPointPropertyOrPointRep",
    "posList",
    "coordinates",
    "degree",
    "knot"
})
@XmlSeeAlso({
    BezierType.class
})
public class BSplineType
    extends AbstractCurveSegmentType
    implements Cloneable, CopyTo, Equals, HashCode, MergeFrom, ToString
{

    @XmlElementRefs({
        @XmlElementRef(name = "pos", namespace = "http://www.opengis.net/gml", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "pointProperty", namespace = "http://www.opengis.net/gml", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "pointRep", namespace = "http://www.opengis.net/gml", type = JAXBElement.class, required = false)
    })
    protected List<JAXBElement<?>> posOrPointPropertyOrPointRep;
    protected DirectPositionListType posList;
    protected CoordinatesType coordinates;
    @XmlElement(required = true)
    @XmlSchemaType(name = "nonNegativeInteger")
    protected BigInteger degree;
    @XmlElement(required = true)
    protected List<KnotPropertyType> knot;
    @XmlAttribute(name = "interpolation")
    protected CurveInterpolationType interpolation;
    @XmlAttribute(name = "isPolynomial")
    protected java.lang.Boolean isPolynomial;
    @XmlAttribute(name = "knotType")
    protected KnotTypesType knotType;

    /**
     * Gets the value of the posOrPointPropertyOrPointRep property.
     * 
     * <p>This accessor method returns a reference to the live list,
     * not a snapshot. Therefore, any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a {@code set} method for the posOrPointPropertyOrPointRep property.</p>
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * </p>
     * <pre>
     * getPosOrPointPropertyOrPointRep().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link JAXBElement }{@code <}{@link DirectPositionType }{@code >}
     * {@link JAXBElement }{@code <}{@link PointPropertyType }{@code >}
     * {@link JAXBElement }{@code <}{@link PointPropertyType }{@code >}
     * </p>
     * 
     * 
     * @return
     *     The value of the posOrPointPropertyOrPointRep property.
     */
    public List<JAXBElement<?>> getPosOrPointPropertyOrPointRep() {
        if (posOrPointPropertyOrPointRep == null) {
            posOrPointPropertyOrPointRep = new ArrayList<>();
        }
        return this.posOrPointPropertyOrPointRep;
    }

    public boolean isSetPosOrPointPropertyOrPointRep() {
        return ((this.posOrPointPropertyOrPointRep!= null)&&(!this.posOrPointPropertyOrPointRep.isEmpty()));
    }

    public void unsetPosOrPointPropertyOrPointRep() {
        this.posOrPointPropertyOrPointRep = null;
    }

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
     * Recupera il valore della proprietà degree.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getDegree() {
        return degree;
    }

    /**
     * Imposta il valore della proprietà degree.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setDegree(BigInteger value) {
        this.degree = value;
    }

    public boolean isSetDegree() {
        return (this.degree!= null);
    }

    /**
     * Gets the value of the knot property.
     * 
     * <p>This accessor method returns a reference to the live list,
     * not a snapshot. Therefore, any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a {@code set} method for the knot property.</p>
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * </p>
     * <pre>
     * getKnot().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link KnotPropertyType }
     * </p>
     * 
     * 
     * @return
     *     The value of the knot property.
     */
    public List<KnotPropertyType> getKnot() {
        if (knot == null) {
            knot = new ArrayList<>();
        }
        return this.knot;
    }

    public boolean isSetKnot() {
        return ((this.knot!= null)&&(!this.knot.isEmpty()));
    }

    public void unsetKnot() {
        this.knot = null;
    }

    /**
     * Recupera il valore della proprietà interpolation.
     * 
     * @return
     *     possible object is
     *     {@link CurveInterpolationType }
     *     
     */
    public CurveInterpolationType getInterpolation() {
        if (interpolation == null) {
            return CurveInterpolationType.POLYNOMIAL_SPLINE;
        } else {
            return interpolation;
        }
    }

    /**
     * Imposta il valore della proprietà interpolation.
     * 
     * @param value
     *     allowed object is
     *     {@link CurveInterpolationType }
     *     
     */
    public void setInterpolation(CurveInterpolationType value) {
        this.interpolation = value;
    }

    public boolean isSetInterpolation() {
        return (this.interpolation!= null);
    }

    /**
     * Recupera il valore della proprietà isPolynomial.
     * 
     * @return
     *     possible object is
     *     {@link java.lang.Boolean }
     *     
     */
    public boolean isIsPolynomial() {
        return isPolynomial;
    }

    /**
     * Imposta il valore della proprietà isPolynomial.
     * 
     * @param value
     *     allowed object is
     *     {@link java.lang.Boolean }
     *     
     */
    public void setIsPolynomial(boolean value) {
        this.isPolynomial = value;
    }

    public boolean isSetIsPolynomial() {
        return (this.isPolynomial!= null);
    }

    public void unsetIsPolynomial() {
        this.isPolynomial = null;
    }

    /**
     * Recupera il valore della proprietà knotType.
     * 
     * @return
     *     possible object is
     *     {@link KnotTypesType }
     *     
     */
    public KnotTypesType getKnotType() {
        return knotType;
    }

    /**
     * Imposta il valore della proprietà knotType.
     * 
     * @param value
     *     allowed object is
     *     {@link KnotTypesType }
     *     
     */
    public void setKnotType(KnotTypesType value) {
        this.knotType = value;
    }

    public boolean isSetKnotType() {
        return (this.knotType!= null);
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
            List<JAXBElement<?>> thePosOrPointPropertyOrPointRep;
            thePosOrPointPropertyOrPointRep = (this.isSetPosOrPointPropertyOrPointRep()?this.getPosOrPointPropertyOrPointRep():null);
            strategy.appendField(locator, this, "posOrPointPropertyOrPointRep", buffer, thePosOrPointPropertyOrPointRep, this.isSetPosOrPointPropertyOrPointRep());
        }
        {
            DirectPositionListType thePosList;
            thePosList = this.getPosList();
            strategy.appendField(locator, this, "posList", buffer, thePosList, this.isSetPosList());
        }
        {
            CoordinatesType theCoordinates;
            theCoordinates = this.getCoordinates();
            strategy.appendField(locator, this, "coordinates", buffer, theCoordinates, this.isSetCoordinates());
        }
        {
            BigInteger theDegree;
            theDegree = this.getDegree();
            strategy.appendField(locator, this, "degree", buffer, theDegree, this.isSetDegree());
        }
        {
            List<KnotPropertyType> theKnot;
            theKnot = (this.isSetKnot()?this.getKnot():null);
            strategy.appendField(locator, this, "knot", buffer, theKnot, this.isSetKnot());
        }
        {
            CurveInterpolationType theInterpolation;
            theInterpolation = this.getInterpolation();
            strategy.appendField(locator, this, "interpolation", buffer, theInterpolation, this.isSetInterpolation());
        }
        {
            boolean theIsPolynomial;
            theIsPolynomial = (this.isSetIsPolynomial()?this.isIsPolynomial():false);
            strategy.appendField(locator, this, "isPolynomial", buffer, theIsPolynomial, this.isSetIsPolynomial());
        }
        {
            KnotTypesType theKnotType;
            theKnotType = this.getKnotType();
            strategy.appendField(locator, this, "knotType", buffer, theKnotType, this.isSetKnotType());
        }
        return buffer;
    }

    public void setPosOrPointPropertyOrPointRep(List<JAXBElement<?>> value) {
        this.posOrPointPropertyOrPointRep = null;
        if (value!= null) {
            List<JAXBElement<?>> draftl = this.getPosOrPointPropertyOrPointRep();
            draftl.addAll(value);
        }
    }

    public void setKnot(List<KnotPropertyType> value) {
        this.knot = null;
        if (value!= null) {
            List<KnotPropertyType> draftl = this.getKnot();
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
        final BSplineType that = ((BSplineType) object);
        {
            List<JAXBElement<?>> lhsPosOrPointPropertyOrPointRep;
            lhsPosOrPointPropertyOrPointRep = (this.isSetPosOrPointPropertyOrPointRep()?this.getPosOrPointPropertyOrPointRep():null);
            List<JAXBElement<?>> rhsPosOrPointPropertyOrPointRep;
            rhsPosOrPointPropertyOrPointRep = (that.isSetPosOrPointPropertyOrPointRep()?that.getPosOrPointPropertyOrPointRep():null);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "posOrPointPropertyOrPointRep", lhsPosOrPointPropertyOrPointRep), LocatorUtils.property(thatLocator, "posOrPointPropertyOrPointRep", rhsPosOrPointPropertyOrPointRep), lhsPosOrPointPropertyOrPointRep, rhsPosOrPointPropertyOrPointRep, this.isSetPosOrPointPropertyOrPointRep(), that.isSetPosOrPointPropertyOrPointRep())) {
                return false;
            }
        }
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
            CoordinatesType lhsCoordinates;
            lhsCoordinates = this.getCoordinates();
            CoordinatesType rhsCoordinates;
            rhsCoordinates = that.getCoordinates();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "coordinates", lhsCoordinates), LocatorUtils.property(thatLocator, "coordinates", rhsCoordinates), lhsCoordinates, rhsCoordinates, this.isSetCoordinates(), that.isSetCoordinates())) {
                return false;
            }
        }
        {
            BigInteger lhsDegree;
            lhsDegree = this.getDegree();
            BigInteger rhsDegree;
            rhsDegree = that.getDegree();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "degree", lhsDegree), LocatorUtils.property(thatLocator, "degree", rhsDegree), lhsDegree, rhsDegree, this.isSetDegree(), that.isSetDegree())) {
                return false;
            }
        }
        {
            List<KnotPropertyType> lhsKnot;
            lhsKnot = (this.isSetKnot()?this.getKnot():null);
            List<KnotPropertyType> rhsKnot;
            rhsKnot = (that.isSetKnot()?that.getKnot():null);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "knot", lhsKnot), LocatorUtils.property(thatLocator, "knot", rhsKnot), lhsKnot, rhsKnot, this.isSetKnot(), that.isSetKnot())) {
                return false;
            }
        }
        {
            CurveInterpolationType lhsInterpolation;
            lhsInterpolation = this.getInterpolation();
            CurveInterpolationType rhsInterpolation;
            rhsInterpolation = that.getInterpolation();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "interpolation", lhsInterpolation), LocatorUtils.property(thatLocator, "interpolation", rhsInterpolation), lhsInterpolation, rhsInterpolation, this.isSetInterpolation(), that.isSetInterpolation())) {
                return false;
            }
        }
        {
            boolean lhsIsPolynomial;
            lhsIsPolynomial = (this.isSetIsPolynomial()?this.isIsPolynomial():false);
            boolean rhsIsPolynomial;
            rhsIsPolynomial = (that.isSetIsPolynomial()?that.isIsPolynomial():false);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "isPolynomial", lhsIsPolynomial), LocatorUtils.property(thatLocator, "isPolynomial", rhsIsPolynomial), lhsIsPolynomial, rhsIsPolynomial, this.isSetIsPolynomial(), that.isSetIsPolynomial())) {
                return false;
            }
        }
        {
            KnotTypesType lhsKnotType;
            lhsKnotType = this.getKnotType();
            KnotTypesType rhsKnotType;
            rhsKnotType = that.getKnotType();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "knotType", lhsKnotType), LocatorUtils.property(thatLocator, "knotType", rhsKnotType), lhsKnotType, rhsKnotType, this.isSetKnotType(), that.isSetKnotType())) {
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
            List<JAXBElement<?>> thePosOrPointPropertyOrPointRep;
            thePosOrPointPropertyOrPointRep = (this.isSetPosOrPointPropertyOrPointRep()?this.getPosOrPointPropertyOrPointRep():null);
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "posOrPointPropertyOrPointRep", thePosOrPointPropertyOrPointRep), currentHashCode, thePosOrPointPropertyOrPointRep, this.isSetPosOrPointPropertyOrPointRep());
        }
        {
            DirectPositionListType thePosList;
            thePosList = this.getPosList();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "posList", thePosList), currentHashCode, thePosList, this.isSetPosList());
        }
        {
            CoordinatesType theCoordinates;
            theCoordinates = this.getCoordinates();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "coordinates", theCoordinates), currentHashCode, theCoordinates, this.isSetCoordinates());
        }
        {
            BigInteger theDegree;
            theDegree = this.getDegree();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "degree", theDegree), currentHashCode, theDegree, this.isSetDegree());
        }
        {
            List<KnotPropertyType> theKnot;
            theKnot = (this.isSetKnot()?this.getKnot():null);
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "knot", theKnot), currentHashCode, theKnot, this.isSetKnot());
        }
        {
            CurveInterpolationType theInterpolation;
            theInterpolation = this.getInterpolation();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "interpolation", theInterpolation), currentHashCode, theInterpolation, this.isSetInterpolation());
        }
        {
            boolean theIsPolynomial;
            theIsPolynomial = (this.isSetIsPolynomial()?this.isIsPolynomial():false);
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "isPolynomial", theIsPolynomial), currentHashCode, theIsPolynomial, this.isSetIsPolynomial());
        }
        {
            KnotTypesType theKnotType;
            theKnotType = this.getKnotType();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "knotType", theKnotType), currentHashCode, theKnotType, this.isSetKnotType());
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
        if (draftCopy instanceof BSplineType) {
            final BSplineType copy = ((BSplineType) draftCopy);
            {
                java.lang.Boolean posOrPointPropertyOrPointRepShouldBeCopiedAndSet = strategy.shouldBeCopiedAndSet(locator, this.isSetPosOrPointPropertyOrPointRep());
                if (posOrPointPropertyOrPointRepShouldBeCopiedAndSet == java.lang.Boolean.TRUE) {
                    List<JAXBElement<?>> sourcePosOrPointPropertyOrPointRep;
                    sourcePosOrPointPropertyOrPointRep = (this.isSetPosOrPointPropertyOrPointRep()?this.getPosOrPointPropertyOrPointRep():null);
                    @SuppressWarnings("unchecked")
                    List<JAXBElement<?>> copyPosOrPointPropertyOrPointRep = ((List<JAXBElement<?>> ) strategy.copy(LocatorUtils.property(locator, "posOrPointPropertyOrPointRep", sourcePosOrPointPropertyOrPointRep), sourcePosOrPointPropertyOrPointRep, this.isSetPosOrPointPropertyOrPointRep()));
                    copy.setPosOrPointPropertyOrPointRep(copyPosOrPointPropertyOrPointRep);
                } else {
                    if (posOrPointPropertyOrPointRepShouldBeCopiedAndSet == java.lang.Boolean.FALSE) {
                        copy.unsetPosOrPointPropertyOrPointRep();
                    }
                }
            }
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
                java.lang.Boolean degreeShouldBeCopiedAndSet = strategy.shouldBeCopiedAndSet(locator, this.isSetDegree());
                if (degreeShouldBeCopiedAndSet == java.lang.Boolean.TRUE) {
                    BigInteger sourceDegree;
                    sourceDegree = this.getDegree();
                    BigInteger copyDegree = ((BigInteger) strategy.copy(LocatorUtils.property(locator, "degree", sourceDegree), sourceDegree, this.isSetDegree()));
                    copy.setDegree(copyDegree);
                } else {
                    if (degreeShouldBeCopiedAndSet == java.lang.Boolean.FALSE) {
                        copy.degree = null;
                    }
                }
            }
            {
                java.lang.Boolean knotShouldBeCopiedAndSet = strategy.shouldBeCopiedAndSet(locator, this.isSetKnot());
                if (knotShouldBeCopiedAndSet == java.lang.Boolean.TRUE) {
                    List<KnotPropertyType> sourceKnot;
                    sourceKnot = (this.isSetKnot()?this.getKnot():null);
                    @SuppressWarnings("unchecked")
                    List<KnotPropertyType> copyKnot = ((List<KnotPropertyType> ) strategy.copy(LocatorUtils.property(locator, "knot", sourceKnot), sourceKnot, this.isSetKnot()));
                    copy.setKnot(copyKnot);
                } else {
                    if (knotShouldBeCopiedAndSet == java.lang.Boolean.FALSE) {
                        copy.unsetKnot();
                    }
                }
            }
            {
                java.lang.Boolean interpolationShouldBeCopiedAndSet = strategy.shouldBeCopiedAndSet(locator, this.isSetInterpolation());
                if (interpolationShouldBeCopiedAndSet == java.lang.Boolean.TRUE) {
                    CurveInterpolationType sourceInterpolation;
                    sourceInterpolation = this.getInterpolation();
                    CurveInterpolationType copyInterpolation = ((CurveInterpolationType) strategy.copy(LocatorUtils.property(locator, "interpolation", sourceInterpolation), sourceInterpolation, this.isSetInterpolation()));
                    copy.setInterpolation(copyInterpolation);
                } else {
                    if (interpolationShouldBeCopiedAndSet == java.lang.Boolean.FALSE) {
                        copy.interpolation = null;
                    }
                }
            }
            {
                java.lang.Boolean isPolynomialShouldBeCopiedAndSet = strategy.shouldBeCopiedAndSet(locator, this.isSetIsPolynomial());
                if (isPolynomialShouldBeCopiedAndSet == java.lang.Boolean.TRUE) {
                    boolean sourceIsPolynomial;
                    sourceIsPolynomial = (this.isSetIsPolynomial()?this.isIsPolynomial():false);
                    boolean copyIsPolynomial = strategy.copy(LocatorUtils.property(locator, "isPolynomial", sourceIsPolynomial), sourceIsPolynomial, this.isSetIsPolynomial());
                    copy.setIsPolynomial(copyIsPolynomial);
                } else {
                    if (isPolynomialShouldBeCopiedAndSet == java.lang.Boolean.FALSE) {
                        copy.unsetIsPolynomial();
                    }
                }
            }
            {
                java.lang.Boolean knotTypeShouldBeCopiedAndSet = strategy.shouldBeCopiedAndSet(locator, this.isSetKnotType());
                if (knotTypeShouldBeCopiedAndSet == java.lang.Boolean.TRUE) {
                    KnotTypesType sourceKnotType;
                    sourceKnotType = this.getKnotType();
                    KnotTypesType copyKnotType = ((KnotTypesType) strategy.copy(LocatorUtils.property(locator, "knotType", sourceKnotType), sourceKnotType, this.isSetKnotType()));
                    copy.setKnotType(copyKnotType);
                } else {
                    if (knotTypeShouldBeCopiedAndSet == java.lang.Boolean.FALSE) {
                        copy.knotType = null;
                    }
                }
            }
        }
        return draftCopy;
    }

    @Override
    public Object createNewInstance() {
        return new BSplineType();
    }

    @Override
    public void mergeFrom(Object left, Object right) {
        final MergeStrategy strategy = JAXBMergeStrategy.getInstance();
        mergeFrom(null, null, left, right, strategy);
    }

    @Override
    public void mergeFrom(ObjectLocator leftLocator, ObjectLocator rightLocator, Object left, Object right, MergeStrategy strategy) {
        super.mergeFrom(leftLocator, rightLocator, left, right, strategy);
        if (right instanceof BSplineType) {
            final BSplineType target = this;
            final BSplineType leftObject = ((BSplineType) left);
            final BSplineType rightObject = ((BSplineType) right);
            {
                java.lang.Boolean posOrPointPropertyOrPointRepShouldBeMergedAndSet = strategy.shouldBeMergedAndSet(leftLocator, rightLocator, leftObject.isSetPosOrPointPropertyOrPointRep(), rightObject.isSetPosOrPointPropertyOrPointRep());
                if (posOrPointPropertyOrPointRepShouldBeMergedAndSet == java.lang.Boolean.TRUE) {
                    List<JAXBElement<?>> lhsPosOrPointPropertyOrPointRep;
                    lhsPosOrPointPropertyOrPointRep = (leftObject.isSetPosOrPointPropertyOrPointRep()?leftObject.getPosOrPointPropertyOrPointRep():null);
                    List<JAXBElement<?>> rhsPosOrPointPropertyOrPointRep;
                    rhsPosOrPointPropertyOrPointRep = (rightObject.isSetPosOrPointPropertyOrPointRep()?rightObject.getPosOrPointPropertyOrPointRep():null);
                    List<JAXBElement<?>> mergedPosOrPointPropertyOrPointRep = ((List<JAXBElement<?>> ) strategy.merge(LocatorUtils.property(leftLocator, "posOrPointPropertyOrPointRep", lhsPosOrPointPropertyOrPointRep), LocatorUtils.property(rightLocator, "posOrPointPropertyOrPointRep", rhsPosOrPointPropertyOrPointRep), lhsPosOrPointPropertyOrPointRep, rhsPosOrPointPropertyOrPointRep, leftObject.isSetPosOrPointPropertyOrPointRep(), rightObject.isSetPosOrPointPropertyOrPointRep()));
                    target.setPosOrPointPropertyOrPointRep(mergedPosOrPointPropertyOrPointRep);
                } else {
                    if (posOrPointPropertyOrPointRepShouldBeMergedAndSet == java.lang.Boolean.FALSE) {
                        target.unsetPosOrPointPropertyOrPointRep();
                    }
                }
            }
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
                java.lang.Boolean degreeShouldBeMergedAndSet = strategy.shouldBeMergedAndSet(leftLocator, rightLocator, leftObject.isSetDegree(), rightObject.isSetDegree());
                if (degreeShouldBeMergedAndSet == java.lang.Boolean.TRUE) {
                    BigInteger lhsDegree;
                    lhsDegree = leftObject.getDegree();
                    BigInteger rhsDegree;
                    rhsDegree = rightObject.getDegree();
                    BigInteger mergedDegree = ((BigInteger) strategy.merge(LocatorUtils.property(leftLocator, "degree", lhsDegree), LocatorUtils.property(rightLocator, "degree", rhsDegree), lhsDegree, rhsDegree, leftObject.isSetDegree(), rightObject.isSetDegree()));
                    target.setDegree(mergedDegree);
                } else {
                    if (degreeShouldBeMergedAndSet == java.lang.Boolean.FALSE) {
                        target.degree = null;
                    }
                }
            }
            {
                java.lang.Boolean knotShouldBeMergedAndSet = strategy.shouldBeMergedAndSet(leftLocator, rightLocator, leftObject.isSetKnot(), rightObject.isSetKnot());
                if (knotShouldBeMergedAndSet == java.lang.Boolean.TRUE) {
                    List<KnotPropertyType> lhsKnot;
                    lhsKnot = (leftObject.isSetKnot()?leftObject.getKnot():null);
                    List<KnotPropertyType> rhsKnot;
                    rhsKnot = (rightObject.isSetKnot()?rightObject.getKnot():null);
                    List<KnotPropertyType> mergedKnot = ((List<KnotPropertyType> ) strategy.merge(LocatorUtils.property(leftLocator, "knot", lhsKnot), LocatorUtils.property(rightLocator, "knot", rhsKnot), lhsKnot, rhsKnot, leftObject.isSetKnot(), rightObject.isSetKnot()));
                    target.setKnot(mergedKnot);
                } else {
                    if (knotShouldBeMergedAndSet == java.lang.Boolean.FALSE) {
                        target.unsetKnot();
                    }
                }
            }
            {
                java.lang.Boolean interpolationShouldBeMergedAndSet = strategy.shouldBeMergedAndSet(leftLocator, rightLocator, leftObject.isSetInterpolation(), rightObject.isSetInterpolation());
                if (interpolationShouldBeMergedAndSet == java.lang.Boolean.TRUE) {
                    CurveInterpolationType lhsInterpolation;
                    lhsInterpolation = leftObject.getInterpolation();
                    CurveInterpolationType rhsInterpolation;
                    rhsInterpolation = rightObject.getInterpolation();
                    CurveInterpolationType mergedInterpolation = ((CurveInterpolationType) strategy.merge(LocatorUtils.property(leftLocator, "interpolation", lhsInterpolation), LocatorUtils.property(rightLocator, "interpolation", rhsInterpolation), lhsInterpolation, rhsInterpolation, leftObject.isSetInterpolation(), rightObject.isSetInterpolation()));
                    target.setInterpolation(mergedInterpolation);
                } else {
                    if (interpolationShouldBeMergedAndSet == java.lang.Boolean.FALSE) {
                        target.interpolation = null;
                    }
                }
            }
            {
                java.lang.Boolean isPolynomialShouldBeMergedAndSet = strategy.shouldBeMergedAndSet(leftLocator, rightLocator, leftObject.isSetIsPolynomial(), rightObject.isSetIsPolynomial());
                if (isPolynomialShouldBeMergedAndSet == java.lang.Boolean.TRUE) {
                    boolean lhsIsPolynomial;
                    lhsIsPolynomial = (leftObject.isSetIsPolynomial()?leftObject.isIsPolynomial():false);
                    boolean rhsIsPolynomial;
                    rhsIsPolynomial = (rightObject.isSetIsPolynomial()?rightObject.isIsPolynomial():false);
                    boolean mergedIsPolynomial = ((boolean) strategy.merge(LocatorUtils.property(leftLocator, "isPolynomial", lhsIsPolynomial), LocatorUtils.property(rightLocator, "isPolynomial", rhsIsPolynomial), lhsIsPolynomial, rhsIsPolynomial, leftObject.isSetIsPolynomial(), rightObject.isSetIsPolynomial()));
                    target.setIsPolynomial(mergedIsPolynomial);
                } else {
                    if (isPolynomialShouldBeMergedAndSet == java.lang.Boolean.FALSE) {
                        target.unsetIsPolynomial();
                    }
                }
            }
            {
                java.lang.Boolean knotTypeShouldBeMergedAndSet = strategy.shouldBeMergedAndSet(leftLocator, rightLocator, leftObject.isSetKnotType(), rightObject.isSetKnotType());
                if (knotTypeShouldBeMergedAndSet == java.lang.Boolean.TRUE) {
                    KnotTypesType lhsKnotType;
                    lhsKnotType = leftObject.getKnotType();
                    KnotTypesType rhsKnotType;
                    rhsKnotType = rightObject.getKnotType();
                    KnotTypesType mergedKnotType = ((KnotTypesType) strategy.merge(LocatorUtils.property(leftLocator, "knotType", lhsKnotType), LocatorUtils.property(rightLocator, "knotType", rhsKnotType), lhsKnotType, rhsKnotType, leftObject.isSetKnotType(), rightObject.isSetKnotType()));
                    target.setKnotType(mergedKnotType);
                } else {
                    if (knotTypeShouldBeMergedAndSet == java.lang.Boolean.FALSE) {
                        target.knotType = null;
                    }
                }
            }
        }
    }

}
