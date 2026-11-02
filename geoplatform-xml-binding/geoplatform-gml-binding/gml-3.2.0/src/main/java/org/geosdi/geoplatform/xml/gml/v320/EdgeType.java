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
 * <p>Classe Java per EdgeType complex type.</p>
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.</p>
 * 
 * <pre>{@code
 * <complexType name="EdgeType">
 *   <complexContent>
 *     <extension base="{http://www.opengis.net/gml}AbstractTopoPrimitiveType">
 *       <sequence>
 *         <element ref="{http://www.opengis.net/gml}directedNode" maxOccurs="2" minOccurs="2"/>
 *         <element ref="{http://www.opengis.net/gml}directedFace" maxOccurs="unbounded" minOccurs="0"/>
 *         <element ref="{http://www.opengis.net/gml}curveProperty" minOccurs="0"/>
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
@XmlType(name = "EdgeType", propOrder = {
    "directedNode",
    "directedFace",
    "curveProperty"
})
public class EdgeType
    extends AbstractTopoPrimitiveType
    implements Cloneable, CopyTo, Equals, HashCode, MergeFrom, ToString
{

    /**
     * A gml:directedNode property element describes the boundary of topology edges and is used in the support of topological point features via the gml:TopoPoint expression, see below. The orientation attribute of type gml:SignType expresses the sense in which the included node is used: start (“-“) or end (“+”) node.
     * 
     */
    @XmlElement(required = true)
    protected List<DirectedNodePropertyType> directedNode;
    /**
     * The gml:directedFace property element describes the boundary of topology solids, in the coBoundary of topology edges and is used in the support of surface features via the gml:TopoSurface expression, see below. The orientation attribute of type gml:SignType expresses the sense in which the included face is used i.e. inward or outward with respect to the surface normal in any geometric realisation.
     * 
     */
    protected List<DirectedFacePropertyType> directedFace;
    /**
     * This property element either references a curve via the XLink-attributes or contains the curve element. curveProperty is the predefined property which may be used by GML Application Schemas whenever a GML feature has a property with a value that is substitutable for AbstractCurve.
     * 
     */
    protected CurvePropertyType curveProperty;
    @XmlAttribute(name = "aggregationType")
    protected AggregationType aggregationType;

    /**
     * A gml:directedNode property element describes the boundary of topology edges and is used in the support of topological point features via the gml:TopoPoint expression, see below. The orientation attribute of type gml:SignType expresses the sense in which the included node is used: start (“-“) or end (“+”) node.
     * 
     * Gets the value of the directedNode property.
     * 
     * <p>This accessor method returns a reference to the live list,
     * not a snapshot. Therefore, any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a {@code set} method for the directedNode property.</p>
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * </p>
     * <pre>
     * getDirectedNode().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DirectedNodePropertyType }
     * </p>
     * 
     * 
     * @return
     *     The value of the directedNode property.
     */
    public List<DirectedNodePropertyType> getDirectedNode() {
        if (directedNode == null) {
            directedNode = new ArrayList<>();
        }
        return this.directedNode;
    }

    public boolean isSetDirectedNode() {
        return ((this.directedNode!= null)&&(!this.directedNode.isEmpty()));
    }

    public void unsetDirectedNode() {
        this.directedNode = null;
    }

    /**
     * The gml:directedFace property element describes the boundary of topology solids, in the coBoundary of topology edges and is used in the support of surface features via the gml:TopoSurface expression, see below. The orientation attribute of type gml:SignType expresses the sense in which the included face is used i.e. inward or outward with respect to the surface normal in any geometric realisation.
     * 
     * Gets the value of the directedFace property.
     * 
     * <p>This accessor method returns a reference to the live list,
     * not a snapshot. Therefore, any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a {@code set} method for the directedFace property.</p>
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * </p>
     * <pre>
     * getDirectedFace().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DirectedFacePropertyType }
     * </p>
     * 
     * 
     * @return
     *     The value of the directedFace property.
     */
    public List<DirectedFacePropertyType> getDirectedFace() {
        if (directedFace == null) {
            directedFace = new ArrayList<>();
        }
        return this.directedFace;
    }

    public boolean isSetDirectedFace() {
        return ((this.directedFace!= null)&&(!this.directedFace.isEmpty()));
    }

    public void unsetDirectedFace() {
        this.directedFace = null;
    }

    /**
     * This property element either references a curve via the XLink-attributes or contains the curve element. curveProperty is the predefined property which may be used by GML Application Schemas whenever a GML feature has a property with a value that is substitutable for AbstractCurve.
     * 
     * @return
     *     possible object is
     *     {@link CurvePropertyType }
     *     
     */
    public CurvePropertyType getCurveProperty() {
        return curveProperty;
    }

    /**
     * Imposta il valore della proprietà curveProperty.
     * 
     * @param value
     *     allowed object is
     *     {@link CurvePropertyType }
     *     
     * @see #getCurveProperty()
     */
    public void setCurveProperty(CurvePropertyType value) {
        this.curveProperty = value;
    }

    public boolean isSetCurveProperty() {
        return (this.curveProperty!= null);
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
            List<DirectedNodePropertyType> theDirectedNode;
            theDirectedNode = (this.isSetDirectedNode()?this.getDirectedNode():null);
            strategy.appendField(locator, this, "directedNode", buffer, theDirectedNode, this.isSetDirectedNode());
        }
        {
            List<DirectedFacePropertyType> theDirectedFace;
            theDirectedFace = (this.isSetDirectedFace()?this.getDirectedFace():null);
            strategy.appendField(locator, this, "directedFace", buffer, theDirectedFace, this.isSetDirectedFace());
        }
        {
            CurvePropertyType theCurveProperty;
            theCurveProperty = this.getCurveProperty();
            strategy.appendField(locator, this, "curveProperty", buffer, theCurveProperty, this.isSetCurveProperty());
        }
        {
            AggregationType theAggregationType;
            theAggregationType = this.getAggregationType();
            strategy.appendField(locator, this, "aggregationType", buffer, theAggregationType, this.isSetAggregationType());
        }
        return buffer;
    }

    public void setDirectedNode(List<DirectedNodePropertyType> value) {
        this.directedNode = null;
        if (value!= null) {
            List<DirectedNodePropertyType> draftl = this.getDirectedNode();
            draftl.addAll(value);
        }
    }

    public void setDirectedFace(List<DirectedFacePropertyType> value) {
        this.directedFace = null;
        if (value!= null) {
            List<DirectedFacePropertyType> draftl = this.getDirectedFace();
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
        final EdgeType that = ((EdgeType) object);
        {
            List<DirectedNodePropertyType> lhsDirectedNode;
            lhsDirectedNode = (this.isSetDirectedNode()?this.getDirectedNode():null);
            List<DirectedNodePropertyType> rhsDirectedNode;
            rhsDirectedNode = (that.isSetDirectedNode()?that.getDirectedNode():null);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "directedNode", lhsDirectedNode), LocatorUtils.property(thatLocator, "directedNode", rhsDirectedNode), lhsDirectedNode, rhsDirectedNode, this.isSetDirectedNode(), that.isSetDirectedNode())) {
                return false;
            }
        }
        {
            List<DirectedFacePropertyType> lhsDirectedFace;
            lhsDirectedFace = (this.isSetDirectedFace()?this.getDirectedFace():null);
            List<DirectedFacePropertyType> rhsDirectedFace;
            rhsDirectedFace = (that.isSetDirectedFace()?that.getDirectedFace():null);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "directedFace", lhsDirectedFace), LocatorUtils.property(thatLocator, "directedFace", rhsDirectedFace), lhsDirectedFace, rhsDirectedFace, this.isSetDirectedFace(), that.isSetDirectedFace())) {
                return false;
            }
        }
        {
            CurvePropertyType lhsCurveProperty;
            lhsCurveProperty = this.getCurveProperty();
            CurvePropertyType rhsCurveProperty;
            rhsCurveProperty = that.getCurveProperty();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "curveProperty", lhsCurveProperty), LocatorUtils.property(thatLocator, "curveProperty", rhsCurveProperty), lhsCurveProperty, rhsCurveProperty, this.isSetCurveProperty(), that.isSetCurveProperty())) {
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
            List<DirectedNodePropertyType> theDirectedNode;
            theDirectedNode = (this.isSetDirectedNode()?this.getDirectedNode():null);
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "directedNode", theDirectedNode), currentHashCode, theDirectedNode, this.isSetDirectedNode());
        }
        {
            List<DirectedFacePropertyType> theDirectedFace;
            theDirectedFace = (this.isSetDirectedFace()?this.getDirectedFace():null);
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "directedFace", theDirectedFace), currentHashCode, theDirectedFace, this.isSetDirectedFace());
        }
        {
            CurvePropertyType theCurveProperty;
            theCurveProperty = this.getCurveProperty();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "curveProperty", theCurveProperty), currentHashCode, theCurveProperty, this.isSetCurveProperty());
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
        if (draftCopy instanceof EdgeType) {
            final EdgeType copy = ((EdgeType) draftCopy);
            {
                java.lang.Boolean directedNodeShouldBeCopiedAndSet = strategy.shouldBeCopiedAndSet(locator, this.isSetDirectedNode());
                if (directedNodeShouldBeCopiedAndSet == java.lang.Boolean.TRUE) {
                    List<DirectedNodePropertyType> sourceDirectedNode;
                    sourceDirectedNode = (this.isSetDirectedNode()?this.getDirectedNode():null);
                    @SuppressWarnings("unchecked")
                    List<DirectedNodePropertyType> copyDirectedNode = ((List<DirectedNodePropertyType> ) strategy.copy(LocatorUtils.property(locator, "directedNode", sourceDirectedNode), sourceDirectedNode, this.isSetDirectedNode()));
                    copy.setDirectedNode(copyDirectedNode);
                } else {
                    if (directedNodeShouldBeCopiedAndSet == java.lang.Boolean.FALSE) {
                        copy.unsetDirectedNode();
                    }
                }
            }
            {
                java.lang.Boolean directedFaceShouldBeCopiedAndSet = strategy.shouldBeCopiedAndSet(locator, this.isSetDirectedFace());
                if (directedFaceShouldBeCopiedAndSet == java.lang.Boolean.TRUE) {
                    List<DirectedFacePropertyType> sourceDirectedFace;
                    sourceDirectedFace = (this.isSetDirectedFace()?this.getDirectedFace():null);
                    @SuppressWarnings("unchecked")
                    List<DirectedFacePropertyType> copyDirectedFace = ((List<DirectedFacePropertyType> ) strategy.copy(LocatorUtils.property(locator, "directedFace", sourceDirectedFace), sourceDirectedFace, this.isSetDirectedFace()));
                    copy.setDirectedFace(copyDirectedFace);
                } else {
                    if (directedFaceShouldBeCopiedAndSet == java.lang.Boolean.FALSE) {
                        copy.unsetDirectedFace();
                    }
                }
            }
            {
                java.lang.Boolean curvePropertyShouldBeCopiedAndSet = strategy.shouldBeCopiedAndSet(locator, this.isSetCurveProperty());
                if (curvePropertyShouldBeCopiedAndSet == java.lang.Boolean.TRUE) {
                    CurvePropertyType sourceCurveProperty;
                    sourceCurveProperty = this.getCurveProperty();
                    CurvePropertyType copyCurveProperty = ((CurvePropertyType) strategy.copy(LocatorUtils.property(locator, "curveProperty", sourceCurveProperty), sourceCurveProperty, this.isSetCurveProperty()));
                    copy.setCurveProperty(copyCurveProperty);
                } else {
                    if (curvePropertyShouldBeCopiedAndSet == java.lang.Boolean.FALSE) {
                        copy.curveProperty = null;
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
        return new EdgeType();
    }

    @Override
    public void mergeFrom(Object left, Object right) {
        final MergeStrategy strategy = JAXBMergeStrategy.getInstance();
        mergeFrom(null, null, left, right, strategy);
    }

    @Override
    public void mergeFrom(ObjectLocator leftLocator, ObjectLocator rightLocator, Object left, Object right, MergeStrategy strategy) {
        super.mergeFrom(leftLocator, rightLocator, left, right, strategy);
        if (right instanceof EdgeType) {
            final EdgeType target = this;
            final EdgeType leftObject = ((EdgeType) left);
            final EdgeType rightObject = ((EdgeType) right);
            {
                java.lang.Boolean directedNodeShouldBeMergedAndSet = strategy.shouldBeMergedAndSet(leftLocator, rightLocator, leftObject.isSetDirectedNode(), rightObject.isSetDirectedNode());
                if (directedNodeShouldBeMergedAndSet == java.lang.Boolean.TRUE) {
                    List<DirectedNodePropertyType> lhsDirectedNode;
                    lhsDirectedNode = (leftObject.isSetDirectedNode()?leftObject.getDirectedNode():null);
                    List<DirectedNodePropertyType> rhsDirectedNode;
                    rhsDirectedNode = (rightObject.isSetDirectedNode()?rightObject.getDirectedNode():null);
                    List<DirectedNodePropertyType> mergedDirectedNode = ((List<DirectedNodePropertyType> ) strategy.merge(LocatorUtils.property(leftLocator, "directedNode", lhsDirectedNode), LocatorUtils.property(rightLocator, "directedNode", rhsDirectedNode), lhsDirectedNode, rhsDirectedNode, leftObject.isSetDirectedNode(), rightObject.isSetDirectedNode()));
                    target.setDirectedNode(mergedDirectedNode);
                } else {
                    if (directedNodeShouldBeMergedAndSet == java.lang.Boolean.FALSE) {
                        target.unsetDirectedNode();
                    }
                }
            }
            {
                java.lang.Boolean directedFaceShouldBeMergedAndSet = strategy.shouldBeMergedAndSet(leftLocator, rightLocator, leftObject.isSetDirectedFace(), rightObject.isSetDirectedFace());
                if (directedFaceShouldBeMergedAndSet == java.lang.Boolean.TRUE) {
                    List<DirectedFacePropertyType> lhsDirectedFace;
                    lhsDirectedFace = (leftObject.isSetDirectedFace()?leftObject.getDirectedFace():null);
                    List<DirectedFacePropertyType> rhsDirectedFace;
                    rhsDirectedFace = (rightObject.isSetDirectedFace()?rightObject.getDirectedFace():null);
                    List<DirectedFacePropertyType> mergedDirectedFace = ((List<DirectedFacePropertyType> ) strategy.merge(LocatorUtils.property(leftLocator, "directedFace", lhsDirectedFace), LocatorUtils.property(rightLocator, "directedFace", rhsDirectedFace), lhsDirectedFace, rhsDirectedFace, leftObject.isSetDirectedFace(), rightObject.isSetDirectedFace()));
                    target.setDirectedFace(mergedDirectedFace);
                } else {
                    if (directedFaceShouldBeMergedAndSet == java.lang.Boolean.FALSE) {
                        target.unsetDirectedFace();
                    }
                }
            }
            {
                java.lang.Boolean curvePropertyShouldBeMergedAndSet = strategy.shouldBeMergedAndSet(leftLocator, rightLocator, leftObject.isSetCurveProperty(), rightObject.isSetCurveProperty());
                if (curvePropertyShouldBeMergedAndSet == java.lang.Boolean.TRUE) {
                    CurvePropertyType lhsCurveProperty;
                    lhsCurveProperty = leftObject.getCurveProperty();
                    CurvePropertyType rhsCurveProperty;
                    rhsCurveProperty = rightObject.getCurveProperty();
                    CurvePropertyType mergedCurveProperty = ((CurvePropertyType) strategy.merge(LocatorUtils.property(leftLocator, "curveProperty", lhsCurveProperty), LocatorUtils.property(rightLocator, "curveProperty", rhsCurveProperty), lhsCurveProperty, rhsCurveProperty, leftObject.isSetCurveProperty(), rightObject.isSetCurveProperty()));
                    target.setCurveProperty(mergedCurveProperty);
                } else {
                    if (curvePropertyShouldBeMergedAndSet == java.lang.Boolean.FALSE) {
                        target.curveProperty = null;
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
