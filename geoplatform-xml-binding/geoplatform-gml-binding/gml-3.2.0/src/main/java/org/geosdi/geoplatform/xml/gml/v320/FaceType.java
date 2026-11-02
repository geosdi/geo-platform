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
import jakarta.xml.bind.annotation.XmlAttribute;
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
 * <p>Classe Java per FaceType complex type.</p>
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.</p>
 * 
 * <pre>{@code
 * <complexType name="FaceType">
 *   <complexContent>
 *     <extension base="{http://www.opengis.net/gml}AbstractTopoPrimitiveType">
 *       <sequence>
 *         <element ref="{http://www.opengis.net/gml}directedEdge" maxOccurs="unbounded"/>
 *         <element ref="{http://www.opengis.net/gml}directedTopoSolid" maxOccurs="2" minOccurs="0"/>
 *         <element ref="{http://www.opengis.net/gml}surfaceProperty" minOccurs="0"/>
 *       </sequence>
 *       <attGroup ref="{http://www.opengis.net/gml}AggregationAttributeGroup"/>
 *     </extension>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "FaceType", propOrder = {
    "directedEdge",
    "directedTopoSolid",
    "surfaceProperty"
})
public class FaceType
    extends AbstractTopoPrimitiveType
    implements Cloneable, CopyTo, Equals, HashCode, MergeFrom, ToString
{

    /**
     * A gml:directedEdge property element describes the boundary of topology faces, the coBoundary of topology nodes and is used in the support of topological line features via the gml:TopoCurve expression, see below. The orientation attribute of type gml:SignType expresses the sense in which the included edge is used, i.e. forward or reverse.
     * 
     */
    @XmlElement(required = true)
    protected List<DirectedEdgePropertyType> directedEdge;
    /**
     * The gml:directedSolid property element describes the coBoundary of topology faces and is used in the support of volume features via the gml:TopoVolume expression, see below. The orientation attribute of type gml:SignType expresses the sense in which the included solid appears in the face coboundary. In the context of a gml:TopoVolume the orientation attribute has no meaning.
     * 
     */
    protected List<DirectedTopoSolidPropertyType> directedTopoSolid;
    /**
     * This property element either references a surface via the XLink-attributes or contains the surface element. surfaceProperty is the predefined property which may be used by GML Application Schemas whenever a GML feature has a property with a value that is substitutable for AbstractSurface.
     * 
     */
    protected SurfacePropertyType surfaceProperty;
    @XmlAttribute(name = "aggregationType")
    protected AggregationType aggregationType;

    /**
     * A gml:directedEdge property element describes the boundary of topology faces, the coBoundary of topology nodes and is used in the support of topological line features via the gml:TopoCurve expression, see below. The orientation attribute of type gml:SignType expresses the sense in which the included edge is used, i.e. forward or reverse.
     * 
     * Gets the value of the directedEdge property.
     * 
     * <p>This accessor method returns a reference to the live list,
     * not a snapshot. Therefore, any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a {@code set} method for the directedEdge property.</p>
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * </p>
     * <pre>
     * getDirectedEdge().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DirectedEdgePropertyType }
     * </p>
     * 
     * 
     * @return
     *     The value of the directedEdge property.
     */
    public List<DirectedEdgePropertyType> getDirectedEdge() {
        if (directedEdge == null) {
            directedEdge = new ArrayList<>();
        }
        return this.directedEdge;
    }

    public boolean isSetDirectedEdge() {
        return ((this.directedEdge!= null)&&(!this.directedEdge.isEmpty()));
    }

    public void unsetDirectedEdge() {
        this.directedEdge = null;
    }

    /**
     * The gml:directedSolid property element describes the coBoundary of topology faces and is used in the support of volume features via the gml:TopoVolume expression, see below. The orientation attribute of type gml:SignType expresses the sense in which the included solid appears in the face coboundary. In the context of a gml:TopoVolume the orientation attribute has no meaning.
     * 
     * Gets the value of the directedTopoSolid property.
     * 
     * <p>This accessor method returns a reference to the live list,
     * not a snapshot. Therefore, any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a {@code set} method for the directedTopoSolid property.</p>
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * </p>
     * <pre>
     * getDirectedTopoSolid().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DirectedTopoSolidPropertyType }
     * </p>
     * 
     * 
     * @return
     *     The value of the directedTopoSolid property.
     */
    public List<DirectedTopoSolidPropertyType> getDirectedTopoSolid() {
        if (directedTopoSolid == null) {
            directedTopoSolid = new ArrayList<>();
        }
        return this.directedTopoSolid;
    }

    public boolean isSetDirectedTopoSolid() {
        return ((this.directedTopoSolid!= null)&&(!this.directedTopoSolid.isEmpty()));
    }

    public void unsetDirectedTopoSolid() {
        this.directedTopoSolid = null;
    }

    /**
     * This property element either references a surface via the XLink-attributes or contains the surface element. surfaceProperty is the predefined property which may be used by GML Application Schemas whenever a GML feature has a property with a value that is substitutable for AbstractSurface.
     * 
     * @return
     *     possible object is
     *     {@link SurfacePropertyType }
     *     
     */
    public SurfacePropertyType getSurfaceProperty() {
        return surfaceProperty;
    }

    /**
     * Imposta il valore della proprietà surfaceProperty.
     * 
     * @param value
     *     allowed object is
     *     {@link SurfacePropertyType }
     *     
     * @see #getSurfaceProperty()
     */
    public void setSurfaceProperty(SurfacePropertyType value) {
        this.surfaceProperty = value;
    }

    public boolean isSetSurfaceProperty() {
        return (this.surfaceProperty!= null);
    }

    /**
     * Recupera il valore della proprietà aggregationType.
     * 
     * @return
     *     possible object is
     *     {@link AggregationType }
     *     
     */
    public AggregationType getAggregationType() {
        return aggregationType;
    }

    /**
     * Imposta il valore della proprietà aggregationType.
     * 
     * @param value
     *     allowed object is
     *     {@link AggregationType }
     *     
     */
    public void setAggregationType(AggregationType value) {
        this.aggregationType = value;
    }

    public boolean isSetAggregationType() {
        return (this.aggregationType!= null);
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
            List<DirectedEdgePropertyType> theDirectedEdge;
            theDirectedEdge = (this.isSetDirectedEdge()?this.getDirectedEdge():null);
            strategy.appendField(locator, this, "directedEdge", buffer, theDirectedEdge, this.isSetDirectedEdge());
        }
        {
            List<DirectedTopoSolidPropertyType> theDirectedTopoSolid;
            theDirectedTopoSolid = (this.isSetDirectedTopoSolid()?this.getDirectedTopoSolid():null);
            strategy.appendField(locator, this, "directedTopoSolid", buffer, theDirectedTopoSolid, this.isSetDirectedTopoSolid());
        }
        {
            SurfacePropertyType theSurfaceProperty;
            theSurfaceProperty = this.getSurfaceProperty();
            strategy.appendField(locator, this, "surfaceProperty", buffer, theSurfaceProperty, this.isSetSurfaceProperty());
        }
        {
            AggregationType theAggregationType;
            theAggregationType = this.getAggregationType();
            strategy.appendField(locator, this, "aggregationType", buffer, theAggregationType, this.isSetAggregationType());
        }
        return buffer;
    }

    public void setDirectedEdge(List<DirectedEdgePropertyType> value) {
        this.directedEdge = null;
        if (value!= null) {
            List<DirectedEdgePropertyType> draftl = this.getDirectedEdge();
            draftl.addAll(value);
        }
    }

    public void setDirectedTopoSolid(List<DirectedTopoSolidPropertyType> value) {
        this.directedTopoSolid = null;
        if (value!= null) {
            List<DirectedTopoSolidPropertyType> draftl = this.getDirectedTopoSolid();
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
        final FaceType that = ((FaceType) object);
        {
            List<DirectedEdgePropertyType> lhsDirectedEdge;
            lhsDirectedEdge = (this.isSetDirectedEdge()?this.getDirectedEdge():null);
            List<DirectedEdgePropertyType> rhsDirectedEdge;
            rhsDirectedEdge = (that.isSetDirectedEdge()?that.getDirectedEdge():null);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "directedEdge", lhsDirectedEdge), LocatorUtils.property(thatLocator, "directedEdge", rhsDirectedEdge), lhsDirectedEdge, rhsDirectedEdge, this.isSetDirectedEdge(), that.isSetDirectedEdge())) {
                return false;
            }
        }
        {
            List<DirectedTopoSolidPropertyType> lhsDirectedTopoSolid;
            lhsDirectedTopoSolid = (this.isSetDirectedTopoSolid()?this.getDirectedTopoSolid():null);
            List<DirectedTopoSolidPropertyType> rhsDirectedTopoSolid;
            rhsDirectedTopoSolid = (that.isSetDirectedTopoSolid()?that.getDirectedTopoSolid():null);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "directedTopoSolid", lhsDirectedTopoSolid), LocatorUtils.property(thatLocator, "directedTopoSolid", rhsDirectedTopoSolid), lhsDirectedTopoSolid, rhsDirectedTopoSolid, this.isSetDirectedTopoSolid(), that.isSetDirectedTopoSolid())) {
                return false;
            }
        }
        {
            SurfacePropertyType lhsSurfaceProperty;
            lhsSurfaceProperty = this.getSurfaceProperty();
            SurfacePropertyType rhsSurfaceProperty;
            rhsSurfaceProperty = that.getSurfaceProperty();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "surfaceProperty", lhsSurfaceProperty), LocatorUtils.property(thatLocator, "surfaceProperty", rhsSurfaceProperty), lhsSurfaceProperty, rhsSurfaceProperty, this.isSetSurfaceProperty(), that.isSetSurfaceProperty())) {
                return false;
            }
        }
        {
            AggregationType lhsAggregationType;
            lhsAggregationType = this.getAggregationType();
            AggregationType rhsAggregationType;
            rhsAggregationType = that.getAggregationType();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "aggregationType", lhsAggregationType), LocatorUtils.property(thatLocator, "aggregationType", rhsAggregationType), lhsAggregationType, rhsAggregationType, this.isSetAggregationType(), that.isSetAggregationType())) {
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
            List<DirectedEdgePropertyType> theDirectedEdge;
            theDirectedEdge = (this.isSetDirectedEdge()?this.getDirectedEdge():null);
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "directedEdge", theDirectedEdge), currentHashCode, theDirectedEdge, this.isSetDirectedEdge());
        }
        {
            List<DirectedTopoSolidPropertyType> theDirectedTopoSolid;
            theDirectedTopoSolid = (this.isSetDirectedTopoSolid()?this.getDirectedTopoSolid():null);
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "directedTopoSolid", theDirectedTopoSolid), currentHashCode, theDirectedTopoSolid, this.isSetDirectedTopoSolid());
        }
        {
            SurfacePropertyType theSurfaceProperty;
            theSurfaceProperty = this.getSurfaceProperty();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "surfaceProperty", theSurfaceProperty), currentHashCode, theSurfaceProperty, this.isSetSurfaceProperty());
        }
        {
            AggregationType theAggregationType;
            theAggregationType = this.getAggregationType();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "aggregationType", theAggregationType), currentHashCode, theAggregationType, this.isSetAggregationType());
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
        if (draftCopy instanceof FaceType) {
            final FaceType copy = ((FaceType) draftCopy);
            {
                java.lang.Boolean directedEdgeShouldBeCopiedAndSet = strategy.shouldBeCopiedAndSet(locator, this.isSetDirectedEdge());
                if (directedEdgeShouldBeCopiedAndSet == java.lang.Boolean.TRUE) {
                    List<DirectedEdgePropertyType> sourceDirectedEdge;
                    sourceDirectedEdge = (this.isSetDirectedEdge()?this.getDirectedEdge():null);
                    @SuppressWarnings("unchecked")
                    List<DirectedEdgePropertyType> copyDirectedEdge = ((List<DirectedEdgePropertyType> ) strategy.copy(LocatorUtils.property(locator, "directedEdge", sourceDirectedEdge), sourceDirectedEdge, this.isSetDirectedEdge()));
                    copy.setDirectedEdge(copyDirectedEdge);
                } else {
                    if (directedEdgeShouldBeCopiedAndSet == java.lang.Boolean.FALSE) {
                        copy.unsetDirectedEdge();
                    }
                }
            }
            {
                java.lang.Boolean directedTopoSolidShouldBeCopiedAndSet = strategy.shouldBeCopiedAndSet(locator, this.isSetDirectedTopoSolid());
                if (directedTopoSolidShouldBeCopiedAndSet == java.lang.Boolean.TRUE) {
                    List<DirectedTopoSolidPropertyType> sourceDirectedTopoSolid;
                    sourceDirectedTopoSolid = (this.isSetDirectedTopoSolid()?this.getDirectedTopoSolid():null);
                    @SuppressWarnings("unchecked")
                    List<DirectedTopoSolidPropertyType> copyDirectedTopoSolid = ((List<DirectedTopoSolidPropertyType> ) strategy.copy(LocatorUtils.property(locator, "directedTopoSolid", sourceDirectedTopoSolid), sourceDirectedTopoSolid, this.isSetDirectedTopoSolid()));
                    copy.setDirectedTopoSolid(copyDirectedTopoSolid);
                } else {
                    if (directedTopoSolidShouldBeCopiedAndSet == java.lang.Boolean.FALSE) {
                        copy.unsetDirectedTopoSolid();
                    }
                }
            }
            {
                java.lang.Boolean surfacePropertyShouldBeCopiedAndSet = strategy.shouldBeCopiedAndSet(locator, this.isSetSurfaceProperty());
                if (surfacePropertyShouldBeCopiedAndSet == java.lang.Boolean.TRUE) {
                    SurfacePropertyType sourceSurfaceProperty;
                    sourceSurfaceProperty = this.getSurfaceProperty();
                    SurfacePropertyType copySurfaceProperty = ((SurfacePropertyType) strategy.copy(LocatorUtils.property(locator, "surfaceProperty", sourceSurfaceProperty), sourceSurfaceProperty, this.isSetSurfaceProperty()));
                    copy.setSurfaceProperty(copySurfaceProperty);
                } else {
                    if (surfacePropertyShouldBeCopiedAndSet == java.lang.Boolean.FALSE) {
                        copy.surfaceProperty = null;
                    }
                }
            }
            {
                java.lang.Boolean aggregationTypeShouldBeCopiedAndSet = strategy.shouldBeCopiedAndSet(locator, this.isSetAggregationType());
                if (aggregationTypeShouldBeCopiedAndSet == java.lang.Boolean.TRUE) {
                    AggregationType sourceAggregationType;
                    sourceAggregationType = this.getAggregationType();
                    AggregationType copyAggregationType = ((AggregationType) strategy.copy(LocatorUtils.property(locator, "aggregationType", sourceAggregationType), sourceAggregationType, this.isSetAggregationType()));
                    copy.setAggregationType(copyAggregationType);
                } else {
                    if (aggregationTypeShouldBeCopiedAndSet == java.lang.Boolean.FALSE) {
                        copy.aggregationType = null;
                    }
                }
            }
        }
        return draftCopy;
    }

    @Override
    public Object createNewInstance() {
        return new FaceType();
    }

    @Override
    public void mergeFrom(Object left, Object right) {
        final MergeStrategy strategy = JAXBMergeStrategy.getInstance();
        mergeFrom(null, null, left, right, strategy);
    }

    @Override
    public void mergeFrom(ObjectLocator leftLocator, ObjectLocator rightLocator, Object left, Object right, MergeStrategy strategy) {
        super.mergeFrom(leftLocator, rightLocator, left, right, strategy);
        if (right instanceof FaceType) {
            final FaceType target = this;
            final FaceType leftObject = ((FaceType) left);
            final FaceType rightObject = ((FaceType) right);
            {
                java.lang.Boolean directedEdgeShouldBeMergedAndSet = strategy.shouldBeMergedAndSet(leftLocator, rightLocator, leftObject.isSetDirectedEdge(), rightObject.isSetDirectedEdge());
                if (directedEdgeShouldBeMergedAndSet == java.lang.Boolean.TRUE) {
                    List<DirectedEdgePropertyType> lhsDirectedEdge;
                    lhsDirectedEdge = (leftObject.isSetDirectedEdge()?leftObject.getDirectedEdge():null);
                    List<DirectedEdgePropertyType> rhsDirectedEdge;
                    rhsDirectedEdge = (rightObject.isSetDirectedEdge()?rightObject.getDirectedEdge():null);
                    List<DirectedEdgePropertyType> mergedDirectedEdge = ((List<DirectedEdgePropertyType> ) strategy.merge(LocatorUtils.property(leftLocator, "directedEdge", lhsDirectedEdge), LocatorUtils.property(rightLocator, "directedEdge", rhsDirectedEdge), lhsDirectedEdge, rhsDirectedEdge, leftObject.isSetDirectedEdge(), rightObject.isSetDirectedEdge()));
                    target.setDirectedEdge(mergedDirectedEdge);
                } else {
                    if (directedEdgeShouldBeMergedAndSet == java.lang.Boolean.FALSE) {
                        target.unsetDirectedEdge();
                    }
                }
            }
            {
                java.lang.Boolean directedTopoSolidShouldBeMergedAndSet = strategy.shouldBeMergedAndSet(leftLocator, rightLocator, leftObject.isSetDirectedTopoSolid(), rightObject.isSetDirectedTopoSolid());
                if (directedTopoSolidShouldBeMergedAndSet == java.lang.Boolean.TRUE) {
                    List<DirectedTopoSolidPropertyType> lhsDirectedTopoSolid;
                    lhsDirectedTopoSolid = (leftObject.isSetDirectedTopoSolid()?leftObject.getDirectedTopoSolid():null);
                    List<DirectedTopoSolidPropertyType> rhsDirectedTopoSolid;
                    rhsDirectedTopoSolid = (rightObject.isSetDirectedTopoSolid()?rightObject.getDirectedTopoSolid():null);
                    List<DirectedTopoSolidPropertyType> mergedDirectedTopoSolid = ((List<DirectedTopoSolidPropertyType> ) strategy.merge(LocatorUtils.property(leftLocator, "directedTopoSolid", lhsDirectedTopoSolid), LocatorUtils.property(rightLocator, "directedTopoSolid", rhsDirectedTopoSolid), lhsDirectedTopoSolid, rhsDirectedTopoSolid, leftObject.isSetDirectedTopoSolid(), rightObject.isSetDirectedTopoSolid()));
                    target.setDirectedTopoSolid(mergedDirectedTopoSolid);
                } else {
                    if (directedTopoSolidShouldBeMergedAndSet == java.lang.Boolean.FALSE) {
                        target.unsetDirectedTopoSolid();
                    }
                }
            }
            {
                java.lang.Boolean surfacePropertyShouldBeMergedAndSet = strategy.shouldBeMergedAndSet(leftLocator, rightLocator, leftObject.isSetSurfaceProperty(), rightObject.isSetSurfaceProperty());
                if (surfacePropertyShouldBeMergedAndSet == java.lang.Boolean.TRUE) {
                    SurfacePropertyType lhsSurfaceProperty;
                    lhsSurfaceProperty = leftObject.getSurfaceProperty();
                    SurfacePropertyType rhsSurfaceProperty;
                    rhsSurfaceProperty = rightObject.getSurfaceProperty();
                    SurfacePropertyType mergedSurfaceProperty = ((SurfacePropertyType) strategy.merge(LocatorUtils.property(leftLocator, "surfaceProperty", lhsSurfaceProperty), LocatorUtils.property(rightLocator, "surfaceProperty", rhsSurfaceProperty), lhsSurfaceProperty, rhsSurfaceProperty, leftObject.isSetSurfaceProperty(), rightObject.isSetSurfaceProperty()));
                    target.setSurfaceProperty(mergedSurfaceProperty);
                } else {
                    if (surfacePropertyShouldBeMergedAndSet == java.lang.Boolean.FALSE) {
                        target.surfaceProperty = null;
                    }
                }
            }
            {
                java.lang.Boolean aggregationTypeShouldBeMergedAndSet = strategy.shouldBeMergedAndSet(leftLocator, rightLocator, leftObject.isSetAggregationType(), rightObject.isSetAggregationType());
                if (aggregationTypeShouldBeMergedAndSet == java.lang.Boolean.TRUE) {
                    AggregationType lhsAggregationType;
                    lhsAggregationType = leftObject.getAggregationType();
                    AggregationType rhsAggregationType;
                    rhsAggregationType = rightObject.getAggregationType();
                    AggregationType mergedAggregationType = ((AggregationType) strategy.merge(LocatorUtils.property(leftLocator, "aggregationType", lhsAggregationType), LocatorUtils.property(rightLocator, "aggregationType", rhsAggregationType), lhsAggregationType, rhsAggregationType, leftObject.isSetAggregationType(), rightObject.isSetAggregationType()));
                    target.setAggregationType(mergedAggregationType);
                } else {
                    if (aggregationTypeShouldBeMergedAndSet == java.lang.Boolean.FALSE) {
                        target.aggregationType = null;
                    }
                }
            }
        }
    }

}
