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
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
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
 * <p>Classe Java per ArcByCenterPointType complex type.</p>
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.</p>
 * 
 * <pre>{@code
 * <complexType name="ArcByCenterPointType">
 *   <complexContent>
 *     <extension base="{http://www.opengis.net/gml}AbstractCurveSegmentType">
 *       <sequence>
 *         <choice>
 *           <choice>
 *             <element ref="{http://www.opengis.net/gml}pos"/>
 *             <element ref="{http://www.opengis.net/gml}pointProperty"/>
 *             <element ref="{http://www.opengis.net/gml}pointRep"/>
 *           </choice>
 *           <element ref="{http://www.opengis.net/gml}posList"/>
 *           <element ref="{http://www.opengis.net/gml}coordinates"/>
 *         </choice>
 *         <element name="radius" type="{http://www.opengis.net/gml}LengthType"/>
 *         <element name="startAngle" type="{http://www.opengis.net/gml}AngleType" minOccurs="0"/>
 *         <element name="endAngle" type="{http://www.opengis.net/gml}AngleType" minOccurs="0"/>
 *       </sequence>
 *       <attribute name="interpolation" type="{http://www.opengis.net/gml}CurveInterpolationType" fixed="circularArcCenterPointWithRadius" />
 *       <attribute name="numArc" use="required" type="{http://www.w3.org/2001/XMLSchema}integer" fixed="1" />
 *     </extension>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArcByCenterPointType", propOrder = {
    "pos",
    "pointProperty",
    "pointRep",
    "posList",
    "coordinates",
    "radius",
    "startAngle",
    "endAngle"
})
@XmlSeeAlso({
    CircleByCenterPointType.class
})
public class ArcByCenterPointType
    extends AbstractCurveSegmentType
    implements Cloneable, CopyTo, Equals, HashCode, MergeFrom, ToString
{

    protected DirectPositionType pos;
    /**
     * This property element either references a point via the XLink-attributes or contains the point element. pointProperty is the predefined property which may be used by GML Application Schemas whenever a GML feature has a property with a value that is substitutable for Point.
     * 
     */
    protected PointPropertyType pointProperty;
    protected PointPropertyType pointRep;
    protected DirectPositionListType posList;
    protected CoordinatesType coordinates;
    @XmlElement(required = true)
    protected LengthType radius;
    protected AngleType startAngle;
    protected AngleType endAngle;
    @XmlAttribute(name = "interpolation")
    public static final CurveInterpolationType INTERPOLATION = CurveInterpolationType.CIRCULAR_ARC_CENTER_POINT_WITH_RADIUS;
    @XmlAttribute(name = "numArc", required = true)
    public static final BigInteger NUM_ARC = new BigInteger("1");

    /**
     * Recupera il valore della proprietà pos.
     * 
     * @return
     *     possible object is
     *     {@link DirectPositionType }
     *     
     */
    public DirectPositionType getPos() {
        return pos;
    }

    /**
     * Imposta il valore della proprietà pos.
     * 
     * @param value
     *     allowed object is
     *     {@link DirectPositionType }
     *     
     */
    public void setPos(DirectPositionType value) {
        this.pos = value;
    }

    public boolean isSetPos() {
        return (this.pos!= null);
    }

    /**
     * This property element either references a point via the XLink-attributes or contains the point element. pointProperty is the predefined property which may be used by GML Application Schemas whenever a GML feature has a property with a value that is substitutable for Point.
     * 
     * @return
     *     possible object is
     *     {@link PointPropertyType }
     *     
     */
    public PointPropertyType getPointProperty() {
        return pointProperty;
    }

    /**
     * Imposta il valore della proprietà pointProperty.
     * 
     * @param value
     *     allowed object is
     *     {@link PointPropertyType }
     *     
     * @see #getPointProperty()
     */
    public void setPointProperty(PointPropertyType value) {
        this.pointProperty = value;
    }

    public boolean isSetPointProperty() {
        return (this.pointProperty!= null);
    }

    /**
     * Recupera il valore della proprietà pointRep.
     * 
     * @return
     *     possible object is
     *     {@link PointPropertyType }
     *     
     */
    public PointPropertyType getPointRep() {
        return pointRep;
    }

    /**
     * Imposta il valore della proprietà pointRep.
     * 
     * @param value
     *     allowed object is
     *     {@link PointPropertyType }
     *     
     */
    public void setPointRep(PointPropertyType value) {
        this.pointRep = value;
    }

    public boolean isSetPointRep() {
        return (this.pointRep!= null);
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
     * Recupera il valore della proprietà radius.
     * 
     * @return
     *     possible object is
     *     {@link LengthType }
     *     
     */
    public LengthType getRadius() {
        return radius;
    }

    /**
     * Imposta il valore della proprietà radius.
     * 
     * @param value
     *     allowed object is
     *     {@link LengthType }
     *     
     */
    public void setRadius(LengthType value) {
        this.radius = value;
    }

    public boolean isSetRadius() {
        return (this.radius!= null);
    }

    /**
     * Recupera il valore della proprietà startAngle.
     * 
     * @return
     *     possible object is
     *     {@link AngleType }
     *     
     */
    public AngleType getStartAngle() {
        return startAngle;
    }

    /**
     * Imposta il valore della proprietà startAngle.
     * 
     * @param value
     *     allowed object is
     *     {@link AngleType }
     *     
     */
    public void setStartAngle(AngleType value) {
        this.startAngle = value;
    }

    public boolean isSetStartAngle() {
        return (this.startAngle!= null);
    }

    /**
     * Recupera il valore della proprietà endAngle.
     * 
     * @return
     *     possible object is
     *     {@link AngleType }
     *     
     */
    public AngleType getEndAngle() {
        return endAngle;
    }

    /**
     * Imposta il valore della proprietà endAngle.
     * 
     * @param value
     *     allowed object is
     *     {@link AngleType }
     *     
     */
    public void setEndAngle(AngleType value) {
        this.endAngle = value;
    }

    public boolean isSetEndAngle() {
        return (this.endAngle!= null);
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
            DirectPositionType thePos;
            thePos = this.getPos();
            strategy.appendField(locator, this, "pos", buffer, thePos, this.isSetPos());
        }
        {
            PointPropertyType thePointProperty;
            thePointProperty = this.getPointProperty();
            strategy.appendField(locator, this, "pointProperty", buffer, thePointProperty, this.isSetPointProperty());
        }
        {
            PointPropertyType thePointRep;
            thePointRep = this.getPointRep();
            strategy.appendField(locator, this, "pointRep", buffer, thePointRep, this.isSetPointRep());
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
            LengthType theRadius;
            theRadius = this.getRadius();
            strategy.appendField(locator, this, "radius", buffer, theRadius, this.isSetRadius());
        }
        {
            AngleType theStartAngle;
            theStartAngle = this.getStartAngle();
            strategy.appendField(locator, this, "startAngle", buffer, theStartAngle, this.isSetStartAngle());
        }
        {
            AngleType theEndAngle;
            theEndAngle = this.getEndAngle();
            strategy.appendField(locator, this, "endAngle", buffer, theEndAngle, this.isSetEndAngle());
        }
        {
            CurveInterpolationType theINTERPOLATION;
            theINTERPOLATION = ArcByCenterPointType.INTERPOLATION;
            strategy.appendField(locator, this, "interpolation", buffer, theINTERPOLATION, true);
        }
        {
            BigInteger theNUM_ARC;
            theNUM_ARC = ArcByCenterPointType.NUM_ARC;
            strategy.appendField(locator, this, "numARC", buffer, theNUM_ARC, true);
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
        final ArcByCenterPointType that = ((ArcByCenterPointType) object);
        {
            DirectPositionType lhsPos;
            lhsPos = this.getPos();
            DirectPositionType rhsPos;
            rhsPos = that.getPos();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "pos", lhsPos), LocatorUtils.property(thatLocator, "pos", rhsPos), lhsPos, rhsPos, this.isSetPos(), that.isSetPos())) {
                return false;
            }
        }
        {
            PointPropertyType lhsPointProperty;
            lhsPointProperty = this.getPointProperty();
            PointPropertyType rhsPointProperty;
            rhsPointProperty = that.getPointProperty();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "pointProperty", lhsPointProperty), LocatorUtils.property(thatLocator, "pointProperty", rhsPointProperty), lhsPointProperty, rhsPointProperty, this.isSetPointProperty(), that.isSetPointProperty())) {
                return false;
            }
        }
        {
            PointPropertyType lhsPointRep;
            lhsPointRep = this.getPointRep();
            PointPropertyType rhsPointRep;
            rhsPointRep = that.getPointRep();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "pointRep", lhsPointRep), LocatorUtils.property(thatLocator, "pointRep", rhsPointRep), lhsPointRep, rhsPointRep, this.isSetPointRep(), that.isSetPointRep())) {
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
            LengthType lhsRadius;
            lhsRadius = this.getRadius();
            LengthType rhsRadius;
            rhsRadius = that.getRadius();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "radius", lhsRadius), LocatorUtils.property(thatLocator, "radius", rhsRadius), lhsRadius, rhsRadius, this.isSetRadius(), that.isSetRadius())) {
                return false;
            }
        }
        {
            AngleType lhsStartAngle;
            lhsStartAngle = this.getStartAngle();
            AngleType rhsStartAngle;
            rhsStartAngle = that.getStartAngle();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "startAngle", lhsStartAngle), LocatorUtils.property(thatLocator, "startAngle", rhsStartAngle), lhsStartAngle, rhsStartAngle, this.isSetStartAngle(), that.isSetStartAngle())) {
                return false;
            }
        }
        {
            AngleType lhsEndAngle;
            lhsEndAngle = this.getEndAngle();
            AngleType rhsEndAngle;
            rhsEndAngle = that.getEndAngle();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "endAngle", lhsEndAngle), LocatorUtils.property(thatLocator, "endAngle", rhsEndAngle), lhsEndAngle, rhsEndAngle, this.isSetEndAngle(), that.isSetEndAngle())) {
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
            DirectPositionType thePos;
            thePos = this.getPos();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "pos", thePos), currentHashCode, thePos, this.isSetPos());
        }
        {
            PointPropertyType thePointProperty;
            thePointProperty = this.getPointProperty();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "pointProperty", thePointProperty), currentHashCode, thePointProperty, this.isSetPointProperty());
        }
        {
            PointPropertyType thePointRep;
            thePointRep = this.getPointRep();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "pointRep", thePointRep), currentHashCode, thePointRep, this.isSetPointRep());
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
            LengthType theRadius;
            theRadius = this.getRadius();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "radius", theRadius), currentHashCode, theRadius, this.isSetRadius());
        }
        {
            AngleType theStartAngle;
            theStartAngle = this.getStartAngle();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "startAngle", theStartAngle), currentHashCode, theStartAngle, this.isSetStartAngle());
        }
        {
            AngleType theEndAngle;
            theEndAngle = this.getEndAngle();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "endAngle", theEndAngle), currentHashCode, theEndAngle, this.isSetEndAngle());
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
        if (draftCopy instanceof ArcByCenterPointType) {
            final ArcByCenterPointType copy = ((ArcByCenterPointType) draftCopy);
            {
                java.lang.Boolean posShouldBeCopiedAndSet = strategy.shouldBeCopiedAndSet(locator, this.isSetPos());
                if (posShouldBeCopiedAndSet == java.lang.Boolean.TRUE) {
                    DirectPositionType sourcePos;
                    sourcePos = this.getPos();
                    DirectPositionType copyPos = ((DirectPositionType) strategy.copy(LocatorUtils.property(locator, "pos", sourcePos), sourcePos, this.isSetPos()));
                    copy.setPos(copyPos);
                } else {
                    if (posShouldBeCopiedAndSet == java.lang.Boolean.FALSE) {
                        copy.pos = null;
                    }
                }
            }
            {
                java.lang.Boolean pointPropertyShouldBeCopiedAndSet = strategy.shouldBeCopiedAndSet(locator, this.isSetPointProperty());
                if (pointPropertyShouldBeCopiedAndSet == java.lang.Boolean.TRUE) {
                    PointPropertyType sourcePointProperty;
                    sourcePointProperty = this.getPointProperty();
                    PointPropertyType copyPointProperty = ((PointPropertyType) strategy.copy(LocatorUtils.property(locator, "pointProperty", sourcePointProperty), sourcePointProperty, this.isSetPointProperty()));
                    copy.setPointProperty(copyPointProperty);
                } else {
                    if (pointPropertyShouldBeCopiedAndSet == java.lang.Boolean.FALSE) {
                        copy.pointProperty = null;
                    }
                }
            }
            {
                java.lang.Boolean pointRepShouldBeCopiedAndSet = strategy.shouldBeCopiedAndSet(locator, this.isSetPointRep());
                if (pointRepShouldBeCopiedAndSet == java.lang.Boolean.TRUE) {
                    PointPropertyType sourcePointRep;
                    sourcePointRep = this.getPointRep();
                    PointPropertyType copyPointRep = ((PointPropertyType) strategy.copy(LocatorUtils.property(locator, "pointRep", sourcePointRep), sourcePointRep, this.isSetPointRep()));
                    copy.setPointRep(copyPointRep);
                } else {
                    if (pointRepShouldBeCopiedAndSet == java.lang.Boolean.FALSE) {
                        copy.pointRep = null;
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
                java.lang.Boolean radiusShouldBeCopiedAndSet = strategy.shouldBeCopiedAndSet(locator, this.isSetRadius());
                if (radiusShouldBeCopiedAndSet == java.lang.Boolean.TRUE) {
                    LengthType sourceRadius;
                    sourceRadius = this.getRadius();
                    LengthType copyRadius = ((LengthType) strategy.copy(LocatorUtils.property(locator, "radius", sourceRadius), sourceRadius, this.isSetRadius()));
                    copy.setRadius(copyRadius);
                } else {
                    if (radiusShouldBeCopiedAndSet == java.lang.Boolean.FALSE) {
                        copy.radius = null;
                    }
                }
            }
            {
                java.lang.Boolean startAngleShouldBeCopiedAndSet = strategy.shouldBeCopiedAndSet(locator, this.isSetStartAngle());
                if (startAngleShouldBeCopiedAndSet == java.lang.Boolean.TRUE) {
                    AngleType sourceStartAngle;
                    sourceStartAngle = this.getStartAngle();
                    AngleType copyStartAngle = ((AngleType) strategy.copy(LocatorUtils.property(locator, "startAngle", sourceStartAngle), sourceStartAngle, this.isSetStartAngle()));
                    copy.setStartAngle(copyStartAngle);
                } else {
                    if (startAngleShouldBeCopiedAndSet == java.lang.Boolean.FALSE) {
                        copy.startAngle = null;
                    }
                }
            }
            {
                java.lang.Boolean endAngleShouldBeCopiedAndSet = strategy.shouldBeCopiedAndSet(locator, this.isSetEndAngle());
                if (endAngleShouldBeCopiedAndSet == java.lang.Boolean.TRUE) {
                    AngleType sourceEndAngle;
                    sourceEndAngle = this.getEndAngle();
                    AngleType copyEndAngle = ((AngleType) strategy.copy(LocatorUtils.property(locator, "endAngle", sourceEndAngle), sourceEndAngle, this.isSetEndAngle()));
                    copy.setEndAngle(copyEndAngle);
                } else {
                    if (endAngleShouldBeCopiedAndSet == java.lang.Boolean.FALSE) {
                        copy.endAngle = null;
                    }
                }
            }
        }
        return draftCopy;
    }

    @Override
    public Object createNewInstance() {
        return new ArcByCenterPointType();
    }

    @Override
    public void mergeFrom(Object left, Object right) {
        final MergeStrategy strategy = JAXBMergeStrategy.getInstance();
        mergeFrom(null, null, left, right, strategy);
    }

    @Override
    public void mergeFrom(ObjectLocator leftLocator, ObjectLocator rightLocator, Object left, Object right, MergeStrategy strategy) {
        super.mergeFrom(leftLocator, rightLocator, left, right, strategy);
        if (right instanceof ArcByCenterPointType) {
            final ArcByCenterPointType target = this;
            final ArcByCenterPointType leftObject = ((ArcByCenterPointType) left);
            final ArcByCenterPointType rightObject = ((ArcByCenterPointType) right);
            {
                java.lang.Boolean posShouldBeMergedAndSet = strategy.shouldBeMergedAndSet(leftLocator, rightLocator, leftObject.isSetPos(), rightObject.isSetPos());
                if (posShouldBeMergedAndSet == java.lang.Boolean.TRUE) {
                    DirectPositionType lhsPos;
                    lhsPos = leftObject.getPos();
                    DirectPositionType rhsPos;
                    rhsPos = rightObject.getPos();
                    DirectPositionType mergedPos = ((DirectPositionType) strategy.merge(LocatorUtils.property(leftLocator, "pos", lhsPos), LocatorUtils.property(rightLocator, "pos", rhsPos), lhsPos, rhsPos, leftObject.isSetPos(), rightObject.isSetPos()));
                    target.setPos(mergedPos);
                } else {
                    if (posShouldBeMergedAndSet == java.lang.Boolean.FALSE) {
                        target.pos = null;
                    }
                }
            }
            {
                java.lang.Boolean pointPropertyShouldBeMergedAndSet = strategy.shouldBeMergedAndSet(leftLocator, rightLocator, leftObject.isSetPointProperty(), rightObject.isSetPointProperty());
                if (pointPropertyShouldBeMergedAndSet == java.lang.Boolean.TRUE) {
                    PointPropertyType lhsPointProperty;
                    lhsPointProperty = leftObject.getPointProperty();
                    PointPropertyType rhsPointProperty;
                    rhsPointProperty = rightObject.getPointProperty();
                    PointPropertyType mergedPointProperty = ((PointPropertyType) strategy.merge(LocatorUtils.property(leftLocator, "pointProperty", lhsPointProperty), LocatorUtils.property(rightLocator, "pointProperty", rhsPointProperty), lhsPointProperty, rhsPointProperty, leftObject.isSetPointProperty(), rightObject.isSetPointProperty()));
                    target.setPointProperty(mergedPointProperty);
                } else {
                    if (pointPropertyShouldBeMergedAndSet == java.lang.Boolean.FALSE) {
                        target.pointProperty = null;
                    }
                }
            }
            {
                java.lang.Boolean pointRepShouldBeMergedAndSet = strategy.shouldBeMergedAndSet(leftLocator, rightLocator, leftObject.isSetPointRep(), rightObject.isSetPointRep());
                if (pointRepShouldBeMergedAndSet == java.lang.Boolean.TRUE) {
                    PointPropertyType lhsPointRep;
                    lhsPointRep = leftObject.getPointRep();
                    PointPropertyType rhsPointRep;
                    rhsPointRep = rightObject.getPointRep();
                    PointPropertyType mergedPointRep = ((PointPropertyType) strategy.merge(LocatorUtils.property(leftLocator, "pointRep", lhsPointRep), LocatorUtils.property(rightLocator, "pointRep", rhsPointRep), lhsPointRep, rhsPointRep, leftObject.isSetPointRep(), rightObject.isSetPointRep()));
                    target.setPointRep(mergedPointRep);
                } else {
                    if (pointRepShouldBeMergedAndSet == java.lang.Boolean.FALSE) {
                        target.pointRep = null;
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
                java.lang.Boolean radiusShouldBeMergedAndSet = strategy.shouldBeMergedAndSet(leftLocator, rightLocator, leftObject.isSetRadius(), rightObject.isSetRadius());
                if (radiusShouldBeMergedAndSet == java.lang.Boolean.TRUE) {
                    LengthType lhsRadius;
                    lhsRadius = leftObject.getRadius();
                    LengthType rhsRadius;
                    rhsRadius = rightObject.getRadius();
                    LengthType mergedRadius = ((LengthType) strategy.merge(LocatorUtils.property(leftLocator, "radius", lhsRadius), LocatorUtils.property(rightLocator, "radius", rhsRadius), lhsRadius, rhsRadius, leftObject.isSetRadius(), rightObject.isSetRadius()));
                    target.setRadius(mergedRadius);
                } else {
                    if (radiusShouldBeMergedAndSet == java.lang.Boolean.FALSE) {
                        target.radius = null;
                    }
                }
            }
            {
                java.lang.Boolean startAngleShouldBeMergedAndSet = strategy.shouldBeMergedAndSet(leftLocator, rightLocator, leftObject.isSetStartAngle(), rightObject.isSetStartAngle());
                if (startAngleShouldBeMergedAndSet == java.lang.Boolean.TRUE) {
                    AngleType lhsStartAngle;
                    lhsStartAngle = leftObject.getStartAngle();
                    AngleType rhsStartAngle;
                    rhsStartAngle = rightObject.getStartAngle();
                    AngleType mergedStartAngle = ((AngleType) strategy.merge(LocatorUtils.property(leftLocator, "startAngle", lhsStartAngle), LocatorUtils.property(rightLocator, "startAngle", rhsStartAngle), lhsStartAngle, rhsStartAngle, leftObject.isSetStartAngle(), rightObject.isSetStartAngle()));
                    target.setStartAngle(mergedStartAngle);
                } else {
                    if (startAngleShouldBeMergedAndSet == java.lang.Boolean.FALSE) {
                        target.startAngle = null;
                    }
                }
            }
            {
                java.lang.Boolean endAngleShouldBeMergedAndSet = strategy.shouldBeMergedAndSet(leftLocator, rightLocator, leftObject.isSetEndAngle(), rightObject.isSetEndAngle());
                if (endAngleShouldBeMergedAndSet == java.lang.Boolean.TRUE) {
                    AngleType lhsEndAngle;
                    lhsEndAngle = leftObject.getEndAngle();
                    AngleType rhsEndAngle;
                    rhsEndAngle = rightObject.getEndAngle();
                    AngleType mergedEndAngle = ((AngleType) strategy.merge(LocatorUtils.property(leftLocator, "endAngle", lhsEndAngle), LocatorUtils.property(rightLocator, "endAngle", rhsEndAngle), lhsEndAngle, rhsEndAngle, leftObject.isSetEndAngle(), rightObject.isSetEndAngle()));
                    target.setEndAngle(mergedEndAngle);
                } else {
                    if (endAngleShouldBeMergedAndSet == java.lang.Boolean.FALSE) {
                        target.endAngle = null;
                    }
                }
            }
        }
    }

}
