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

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
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
 * <p>Classe Java per FaceType complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="FaceType">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.opengis.net/gml}AbstractTopoPrimitiveType">
 *       &lt;sequence>
 *         &lt;element ref="{http://www.opengis.net/gml}directedEdge" maxOccurs="unbounded"/>
 *         &lt;element ref="{http://www.opengis.net/gml}directedTopoSolid" maxOccurs="2" minOccurs="0"/>
 *         &lt;element ref="{http://www.opengis.net/gml}surfaceProperty" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attGroup ref="{http://www.opengis.net/gml}AggregationAttributeGroup"/>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
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

    @XmlElement(required = true)
    protected List<DirectedEdgePropertyType> directedEdge;
    protected List<DirectedTopoSolidPropertyType> directedTopoSolid;
    protected SurfacePropertyType surfaceProperty;
    @XmlAttribute(name = "aggregationType")
    protected AggregationType aggregationType;

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
            directedEdge = new ArrayList<DirectedEdgePropertyType>();
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
     * Gets the value of the directedTopoSolid property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the directedTopoSolid property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDirectedTopoSolid().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DirectedTopoSolidPropertyType }
     * 
     * 
     */
    public List<DirectedTopoSolidPropertyType> getDirectedTopoSolid() {
        if (directedTopoSolid == null) {
            directedTopoSolid = new ArrayList<DirectedTopoSolidPropertyType>();
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
     * Recupera il valore della proprietà surfaceProperty.
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
            List<DirectedEdgePropertyType> theDirectedEdge;
            theDirectedEdge = this.getDirectedEdge();
            strategy.appendField(locator, this, "directedEdge", buffer, theDirectedEdge);
        }
        {
            List<DirectedTopoSolidPropertyType> theDirectedTopoSolid;
            theDirectedTopoSolid = this.getDirectedTopoSolid();
            strategy.appendField(locator, this, "directedTopoSolid", buffer, theDirectedTopoSolid);
        }
        {
            SurfacePropertyType theSurfaceProperty;
            theSurfaceProperty = this.getSurfaceProperty();
            strategy.appendField(locator, this, "surfaceProperty", buffer, theSurfaceProperty);
        }
        {
            AggregationType theAggregationType;
            theAggregationType = this.getAggregationType();
            strategy.appendField(locator, this, "aggregationType", buffer, theAggregationType);
        }
        return buffer;
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof FaceType)) {
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
            lhsDirectedEdge = this.getDirectedEdge();
            List<DirectedEdgePropertyType> rhsDirectedEdge;
            rhsDirectedEdge = that.getDirectedEdge();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "directedEdge", lhsDirectedEdge), LocatorUtils.property(thatLocator, "directedEdge", rhsDirectedEdge), lhsDirectedEdge, rhsDirectedEdge)) {
                return false;
            }
        }
        {
            List<DirectedTopoSolidPropertyType> lhsDirectedTopoSolid;
            lhsDirectedTopoSolid = this.getDirectedTopoSolid();
            List<DirectedTopoSolidPropertyType> rhsDirectedTopoSolid;
            rhsDirectedTopoSolid = that.getDirectedTopoSolid();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "directedTopoSolid", lhsDirectedTopoSolid), LocatorUtils.property(thatLocator, "directedTopoSolid", rhsDirectedTopoSolid), lhsDirectedTopoSolid, rhsDirectedTopoSolid)) {
                return false;
            }
        }
        {
            SurfacePropertyType lhsSurfaceProperty;
            lhsSurfaceProperty = this.getSurfaceProperty();
            SurfacePropertyType rhsSurfaceProperty;
            rhsSurfaceProperty = that.getSurfaceProperty();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "surfaceProperty", lhsSurfaceProperty), LocatorUtils.property(thatLocator, "surfaceProperty", rhsSurfaceProperty), lhsSurfaceProperty, rhsSurfaceProperty)) {
                return false;
            }
        }
        {
            AggregationType lhsAggregationType;
            lhsAggregationType = this.getAggregationType();
            AggregationType rhsAggregationType;
            rhsAggregationType = that.getAggregationType();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "aggregationType", lhsAggregationType), LocatorUtils.property(thatLocator, "aggregationType", rhsAggregationType), lhsAggregationType, rhsAggregationType)) {
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
            List<DirectedEdgePropertyType> theDirectedEdge;
            theDirectedEdge = this.getDirectedEdge();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "directedEdge", theDirectedEdge), currentHashCode, theDirectedEdge);
        }
        {
            List<DirectedTopoSolidPropertyType> theDirectedTopoSolid;
            theDirectedTopoSolid = this.getDirectedTopoSolid();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "directedTopoSolid", theDirectedTopoSolid), currentHashCode, theDirectedTopoSolid);
        }
        {
            SurfacePropertyType theSurfaceProperty;
            theSurfaceProperty = this.getSurfaceProperty();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "surfaceProperty", theSurfaceProperty), currentHashCode, theSurfaceProperty);
        }
        {
            AggregationType theAggregationType;
            theAggregationType = this.getAggregationType();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "aggregationType", theAggregationType), currentHashCode, theAggregationType);
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
        if (draftCopy instanceof FaceType) {
            final FaceType copy = ((FaceType) draftCopy);
            if (this.isSetDirectedEdge()) {
                List<DirectedEdgePropertyType> sourceDirectedEdge;
                sourceDirectedEdge = this.getDirectedEdge();
                @SuppressWarnings("unchecked")
                List<DirectedEdgePropertyType> copyDirectedEdge = ((List<DirectedEdgePropertyType> ) strategy.copy(LocatorUtils.property(locator, "directedEdge", sourceDirectedEdge), sourceDirectedEdge));
                copy.unsetDirectedEdge();
                List<DirectedEdgePropertyType> uniqueDirectedEdgel = copy.getDirectedEdge();
                uniqueDirectedEdgel.addAll(copyDirectedEdge);
            } else {
                copy.unsetDirectedEdge();
            }
            if (this.isSetDirectedTopoSolid()) {
                List<DirectedTopoSolidPropertyType> sourceDirectedTopoSolid;
                sourceDirectedTopoSolid = this.getDirectedTopoSolid();
                @SuppressWarnings("unchecked")
                List<DirectedTopoSolidPropertyType> copyDirectedTopoSolid = ((List<DirectedTopoSolidPropertyType> ) strategy.copy(LocatorUtils.property(locator, "directedTopoSolid", sourceDirectedTopoSolid), sourceDirectedTopoSolid));
                copy.unsetDirectedTopoSolid();
                List<DirectedTopoSolidPropertyType> uniqueDirectedTopoSolidl = copy.getDirectedTopoSolid();
                uniqueDirectedTopoSolidl.addAll(copyDirectedTopoSolid);
            } else {
                copy.unsetDirectedTopoSolid();
            }
            if (this.isSetSurfaceProperty()) {
                SurfacePropertyType sourceSurfaceProperty;
                sourceSurfaceProperty = this.getSurfaceProperty();
                SurfacePropertyType copySurfaceProperty = ((SurfacePropertyType) strategy.copy(LocatorUtils.property(locator, "surfaceProperty", sourceSurfaceProperty), sourceSurfaceProperty));
                copy.setSurfaceProperty(copySurfaceProperty);
            } else {
                copy.surfaceProperty = null;
            }
            if (this.isSetAggregationType()) {
                AggregationType sourceAggregationType;
                sourceAggregationType = this.getAggregationType();
                AggregationType copyAggregationType = ((AggregationType) strategy.copy(LocatorUtils.property(locator, "aggregationType", sourceAggregationType), sourceAggregationType));
                copy.setAggregationType(copyAggregationType);
            } else {
                copy.aggregationType = null;
            }
        }
        return draftCopy;
    }

    public Object createNewInstance() {
        return new FaceType();
    }

    public void mergeFrom(Object left, Object right) {
        final MergeStrategy strategy = JAXBMergeStrategy.INSTANCE;
        mergeFrom(null, null, left, right, strategy);
    }

    public void mergeFrom(ObjectLocator leftLocator, ObjectLocator rightLocator, Object left, Object right, MergeStrategy strategy) {
        super.mergeFrom(leftLocator, rightLocator, left, right, strategy);
        if (right instanceof FaceType) {
            final FaceType target = this;
            final FaceType leftObject = ((FaceType) left);
            final FaceType rightObject = ((FaceType) right);
            {
                List<DirectedEdgePropertyType> lhsDirectedEdge;
                lhsDirectedEdge = leftObject.getDirectedEdge();
                List<DirectedEdgePropertyType> rhsDirectedEdge;
                rhsDirectedEdge = rightObject.getDirectedEdge();
                target.unsetDirectedEdge();
                List<DirectedEdgePropertyType> uniqueDirectedEdgel = target.getDirectedEdge();
                uniqueDirectedEdgel.addAll(((List<DirectedEdgePropertyType> ) strategy.merge(LocatorUtils.property(leftLocator, "directedEdge", lhsDirectedEdge), LocatorUtils.property(rightLocator, "directedEdge", rhsDirectedEdge), lhsDirectedEdge, rhsDirectedEdge)));
            }
            {
                List<DirectedTopoSolidPropertyType> lhsDirectedTopoSolid;
                lhsDirectedTopoSolid = leftObject.getDirectedTopoSolid();
                List<DirectedTopoSolidPropertyType> rhsDirectedTopoSolid;
                rhsDirectedTopoSolid = rightObject.getDirectedTopoSolid();
                target.unsetDirectedTopoSolid();
                List<DirectedTopoSolidPropertyType> uniqueDirectedTopoSolidl = target.getDirectedTopoSolid();
                uniqueDirectedTopoSolidl.addAll(((List<DirectedTopoSolidPropertyType> ) strategy.merge(LocatorUtils.property(leftLocator, "directedTopoSolid", lhsDirectedTopoSolid), LocatorUtils.property(rightLocator, "directedTopoSolid", rhsDirectedTopoSolid), lhsDirectedTopoSolid, rhsDirectedTopoSolid)));
            }
            {
                SurfacePropertyType lhsSurfaceProperty;
                lhsSurfaceProperty = leftObject.getSurfaceProperty();
                SurfacePropertyType rhsSurfaceProperty;
                rhsSurfaceProperty = rightObject.getSurfaceProperty();
                target.setSurfaceProperty(((SurfacePropertyType) strategy.merge(LocatorUtils.property(leftLocator, "surfaceProperty", lhsSurfaceProperty), LocatorUtils.property(rightLocator, "surfaceProperty", rhsSurfaceProperty), lhsSurfaceProperty, rhsSurfaceProperty)));
            }
            {
                AggregationType lhsAggregationType;
                lhsAggregationType = leftObject.getAggregationType();
                AggregationType rhsAggregationType;
                rhsAggregationType = rightObject.getAggregationType();
                target.setAggregationType(((AggregationType) strategy.merge(LocatorUtils.property(leftLocator, "aggregationType", lhsAggregationType), LocatorUtils.property(rightLocator, "aggregationType", rhsAggregationType), lhsAggregationType, rhsAggregationType)));
            }
        }
    }

    public void setDirectedEdge(List<DirectedEdgePropertyType> value) {
        List<DirectedEdgePropertyType> draftl = this.getDirectedEdge();
        draftl.addAll(value);
    }

    public void setDirectedTopoSolid(List<DirectedTopoSolidPropertyType> value) {
        List<DirectedTopoSolidPropertyType> draftl = this.getDirectedTopoSolid();
        draftl.addAll(value);
    }

    public FaceType withDirectedEdge(DirectedEdgePropertyType... values) {
        if (values!= null) {
            for (DirectedEdgePropertyType value: values) {
                getDirectedEdge().add(value);
            }
        }
        return this;
    }

    public FaceType withDirectedEdge(Collection<DirectedEdgePropertyType> values) {
        if (values!= null) {
            getDirectedEdge().addAll(values);
        }
        return this;
    }

    public FaceType withDirectedTopoSolid(DirectedTopoSolidPropertyType... values) {
        if (values!= null) {
            for (DirectedTopoSolidPropertyType value: values) {
                getDirectedTopoSolid().add(value);
            }
        }
        return this;
    }

    public FaceType withDirectedTopoSolid(Collection<DirectedTopoSolidPropertyType> values) {
        if (values!= null) {
            getDirectedTopoSolid().addAll(values);
        }
        return this;
    }

    public FaceType withSurfaceProperty(SurfacePropertyType value) {
        setSurfaceProperty(value);
        return this;
    }

    public FaceType withAggregationType(AggregationType value) {
        setAggregationType(value);
        return this;
    }

    public FaceType withDirectedEdge(List<DirectedEdgePropertyType> value) {
        setDirectedEdge(value);
        return this;
    }

    public FaceType withDirectedTopoSolid(List<DirectedTopoSolidPropertyType> value) {
        setDirectedTopoSolid(value);
        return this;
    }

    @Override
    public FaceType withIsolated(IsolatedPropertyType... values) {
        if (values!= null) {
            for (IsolatedPropertyType value: values) {
                getIsolated().add(value);
            }
        }
        return this;
    }

    @Override
    public FaceType withIsolated(Collection<IsolatedPropertyType> values) {
        if (values!= null) {
            getIsolated().addAll(values);
        }
        return this;
    }

    @Override
    public FaceType withContainer(ContainerPropertyType value) {
        setContainer(value);
        return this;
    }

    @Override
    public FaceType withIsolated(List<IsolatedPropertyType> value) {
        setIsolated(value);
        return this;
    }

    @Override
    public FaceType withMetaDataProperty(MetaDataPropertyType... values) {
        if (values!= null) {
            for (MetaDataPropertyType value: values) {
                getMetaDataProperty().add(value);
            }
        }
        return this;
    }

    @Override
    public FaceType withMetaDataProperty(Collection<MetaDataPropertyType> values) {
        if (values!= null) {
            getMetaDataProperty().addAll(values);
        }
        return this;
    }

    @Override
    public FaceType withDescription(StringOrRefType value) {
        setDescription(value);
        return this;
    }

    @Override
    public FaceType withDescriptionReference(ReferenceType value) {
        setDescriptionReference(value);
        return this;
    }

    @Override
    public FaceType withIdentifier(CodeWithAuthorityType value) {
        setIdentifier(value);
        return this;
    }

    @Override
    public FaceType withName(CodeType... values) {
        if (values!= null) {
            for (CodeType value: values) {
                getName().add(value);
            }
        }
        return this;
    }

    @Override
    public FaceType withName(Collection<CodeType> values) {
        if (values!= null) {
            getName().addAll(values);
        }
        return this;
    }

    @Override
    public FaceType withId(String value) {
        setId(value);
        return this;
    }

    @Override
    public FaceType withMetaDataProperty(List<MetaDataPropertyType> value) {
        setMetaDataProperty(value);
        return this;
    }

    @Override
    public FaceType withName(List<CodeType> value) {
        setName(value);
        return this;
    }

}
