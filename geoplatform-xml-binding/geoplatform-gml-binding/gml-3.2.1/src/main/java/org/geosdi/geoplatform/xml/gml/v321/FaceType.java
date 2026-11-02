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
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;
import org.jvnet.jaxb.lang.JAXBToStringStrategy;
import org.jvnet.jaxb.lang.ToString;
import org.jvnet.jaxb.lang.ToStringStrategy;
import org.jvnet.jaxb.locator.ObjectLocator;


/**
 * <p>Classe Java per FaceType complex type.</p>
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.</p>
 * 
 * <pre>{@code
 * <complexType name="FaceType">
 *   <complexContent>
 *     <extension base="{http://www.opengis.net/gml/3.2}AbstractTopoPrimitiveType">
 *       <sequence>
 *         <element name="isolated" type="{http://www.opengis.net/gml/3.2}NodePropertyType" maxOccurs="unbounded" minOccurs="0"/>
 *         <element ref="{http://www.opengis.net/gml/3.2}directedEdge" maxOccurs="unbounded"/>
 *         <element ref="{http://www.opengis.net/gml/3.2}directedTopoSolid" maxOccurs="2" minOccurs="0"/>
 *         <element ref="{http://www.opengis.net/gml/3.2}surfaceProperty" minOccurs="0"/>
 *       </sequence>
 *       <attGroup ref="{http://www.opengis.net/gml/3.2}AggregationAttributeGroup"/>
 *       <attribute name="universal" type="{http://www.w3.org/2001/XMLSchema}boolean" default="false" />
 *     </extension>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "FaceType", propOrder = {
    "isolated",
    "directedEdge",
    "directedTopoSolid",
    "surfaceProperty"
})
public class FaceType
    extends AbstractTopoPrimitiveType
    implements ToString
{

    protected List<NodePropertyType> isolated;
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
    /**
     * If the topological representation exists an unbounded manifold (e.g. Euclidean plane), a gml:Face must indicate whether it is a universal face or not, to ensure a lossless topology representation as defined by Kuijpers, et. al. (see OGC 05-102 Topology IPR). The optional universal attribute of type boolean is used to indicate this. NOTE The universal face is normally not part of any feature, and is used to represent the unbounded portion of the data set. Its interior boundary (it has no exterior boundary) would normally be considered the exterior boundary of the map represented by the data set.
     * 
     */
    @XmlAttribute(name = "universal")
    protected java.lang.Boolean universal;
    @XmlAttribute(name = "aggregationType")
    protected AggregationType aggregationType;

    /**
     * Gets the value of the isolated property.
     * 
     * <p>This accessor method returns a reference to the live list,
     * not a snapshot. Therefore, any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a {@code set} method for the isolated property.</p>
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * </p>
     * <pre>
     * getIsolated().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link NodePropertyType }
     * </p>
     * 
     * 
     * @return
     *     The value of the isolated property.
     */
    public List<NodePropertyType> getIsolated() {
        if (isolated == null) {
            isolated = new ArrayList<>();
        }
        return this.isolated;
    }

    public boolean isSetIsolated() {
        return ((this.isolated!= null)&&(!this.isolated.isEmpty()));
    }

    public void unsetIsolated() {
        this.isolated = null;
    }

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
     * If the topological representation exists an unbounded manifold (e.g. Euclidean plane), a gml:Face must indicate whether it is a universal face or not, to ensure a lossless topology representation as defined by Kuijpers, et. al. (see OGC 05-102 Topology IPR). The optional universal attribute of type boolean is used to indicate this. NOTE The universal face is normally not part of any feature, and is used to represent the unbounded portion of the data set. Its interior boundary (it has no exterior boundary) would normally be considered the exterior boundary of the map represented by the data set.
     * 
     * @return
     *     possible object is
     *     {@link java.lang.Boolean }
     *     
     */
    public boolean isUniversal() {
        if (universal == null) {
            return false;
        } else {
            return universal;
        }
    }

    /**
     * Imposta il valore della proprietà universal.
     * 
     * @param value
     *     allowed object is
     *     {@link java.lang.Boolean }
     *     
     * @see #isUniversal()
     */
    public void setUniversal(boolean value) {
        this.universal = value;
    }

    public boolean isSetUniversal() {
        return (this.universal!= null);
    }

    public void unsetUniversal() {
        this.universal = null;
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
            List<NodePropertyType> theIsolated;
            theIsolated = (this.isSetIsolated()?this.getIsolated():null);
            strategy.appendField(locator, this, "isolated", buffer, theIsolated, this.isSetIsolated());
        }
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
            boolean theUniversal;
            theUniversal = (this.isSetUniversal()?this.isUniversal():false);
            strategy.appendField(locator, this, "universal", buffer, theUniversal, this.isSetUniversal());
        }
        {
            AggregationType theAggregationType;
            theAggregationType = this.getAggregationType();
            strategy.appendField(locator, this, "aggregationType", buffer, theAggregationType, this.isSetAggregationType());
        }
        return buffer;
    }

    public void setIsolated(List<NodePropertyType> value) {
        this.isolated = null;
        if (value!= null) {
            List<NodePropertyType> draftl = this.getIsolated();
            draftl.addAll(value);
        }
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

}
