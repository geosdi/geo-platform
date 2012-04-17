/*
 *  geo-platform
 *  Rich webgis framework
 *  http://geo-platform.org
 * ====================================================================
 *
 * Copyright (C) 2008-2012 geoSDI Group (CNR IMAA - Potenza - ITALY).
 *
 * This program is free software: you can redistribute it and/or modify it 
 * under the terms of the GNU General Public License as published by 
 * the Free Software Foundation, either version 3 of the License, or 
 * (at your option) any later version. This program is distributed in the 
 * hope that it will be useful, but WITHOUT ANY WARRANTY; without 
 * even the implied warranty of MERCHANTABILITY or FITNESS FOR 
 * A PARTICULAR PURPOSE. See the GNU General Public License 
 * for more details. You should have received a copy of the GNU General 
 * Public License along with this program. If not, see http://www.gnu.org/licenses/ 
 *
 * ====================================================================
 *
 * Linking this library statically or dynamically with other modules is 
 * making a combined work based on this library. Thus, the terms and 
 * conditions of the GNU General Public License cover the whole combination. 
 * 
 * As a special exception, the copyright holders of this library give you permission 
 * to link this library with independent modules to produce an executable, regardless 
 * of the license terms of these independent modules, and to copy and distribute 
 * the resulting executable under terms of your choice, provided that you also meet, 
 * for each linked independent module, the terms and conditions of the license of 
 * that module. An independent module is a module which is not derived from or 
 * based on this library. If you modify this library, you may extend this exception 
 * to your version of the library, but you are not obligated to do so. If you do not 
 * wish to do so, delete this exception statement from your version. 
 *
 */
package org.geosdi.geoplatform.xml.gml.v311;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * The end Node of each directedEdge of a TopoCurveType
 * is the start Node of the next directedEdge of the TopoCurveType in document order.  The TopoCurve type and element represent a homogeneous topological expression, a list of directed edges, which if realised are isomorphic to a geometric curve primitive. The intended use of TopoCurve is to appear within a line feature instance to express the structural and geometric relationships of this line to other features via the shared edge definitions.
 * 
 * <p>Java class for TopoCurveType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="TopoCurveType">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.opengis.net/gml}AbstractTopologyType">
 *       &lt;sequence>
 *         &lt;element ref="{http://www.opengis.net/gml}directedEdge" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TopoCurveType", propOrder = {
    "directedEdge"
})
public class TopoCurveType extends AbstractTopologyType {

    @XmlElement(required = true)
    private List<DirectedEdgePropertyType> directedEdge;

    public TopoCurveType() {
    }

    /**
     * @param directedEdge the directedEdge to set
     */
    public void setDirectedEdge(List<DirectedEdgePropertyType> directedEdge) {
        this.directedEdge = directedEdge;
    }

    /**
     * Gets the value of the directedEdge property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the directedEdge property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDirectedEdge().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DirectedEdgePropertyType }
     * 
     * 
     */
    public List<DirectedEdgePropertyType> getDirectedEdge() {
        if (directedEdge == null) {
            this.directedEdge = new ArrayList<DirectedEdgePropertyType>();
        }
        return this.directedEdge;
    }

    @Override
    public String toString() {
        return "TopoCurveType{ " + "directedEdge = " + directedEdge + '}';
    }
}
