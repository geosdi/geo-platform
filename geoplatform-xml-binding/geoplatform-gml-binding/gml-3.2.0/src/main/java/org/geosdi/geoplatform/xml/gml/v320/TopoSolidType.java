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
 * <p>Classe Java per TopoSolidType complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="TopoSolidType">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.opengis.net/gml}AbstractTopoPrimitiveType">
 *       &lt;sequence>
 *         &lt;element ref="{http://www.opengis.net/gml}directedFace" maxOccurs="unbounded"/>
 *         &lt;element ref="{http://www.opengis.net/gml}solidProperty" minOccurs="0"/>
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
@XmlType(name = "TopoSolidType", propOrder = {
    "directedFace",
    "solidProperty"
})
public class TopoSolidType
    extends AbstractTopoPrimitiveType
    implements Cloneable, CopyTo, Equals, HashCode, MergeFrom, ToString
{

    @XmlElement(required = true)
    protected List<DirectedFacePropertyType> directedFace;
    protected SolidPropertyType solidProperty;
    @XmlAttribute(name = "aggregationType")
    protected AggregationType aggregationType;

    /**
     * Gets the value of the directedFace property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the directedFace property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDirectedFace().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DirectedFacePropertyType }
     * 
     * 
     */
    public List<DirectedFacePropertyType> getDirectedFace() {
        if (directedFace == null) {
            directedFace = new ArrayList<DirectedFacePropertyType>();
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
     * Recupera il valore della proprietà solidProperty.
     * 
     * @return
     *     possible object is
     *     {@link SolidPropertyType }
     *     
     */
    public SolidPropertyType getSolidProperty() {
        return solidProperty;
    }

    /**
     * Imposta il valore della proprietà solidProperty.
     * 
     * @param value
     *     allowed object is
     *     {@link SolidPropertyType }
     *     
     */
    public void setSolidProperty(SolidPropertyType value) {
        this.solidProperty = value;
    }

    public boolean isSetSolidProperty() {
        return (this.solidProperty!= null);
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
            List<DirectedFacePropertyType> theDirectedFace;
            theDirectedFace = this.getDirectedFace();
            strategy.appendField(locator, this, "directedFace", buffer, theDirectedFace);
        }
        {
            SolidPropertyType theSolidProperty;
            theSolidProperty = this.getSolidProperty();
            strategy.appendField(locator, this, "solidProperty", buffer, theSolidProperty);
        }
        {
            AggregationType theAggregationType;
            theAggregationType = this.getAggregationType();
            strategy.appendField(locator, this, "aggregationType", buffer, theAggregationType);
        }
        return buffer;
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof TopoSolidType)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        if (!super.equals(thisLocator, thatLocator, object, strategy)) {
            return false;
        }
        final TopoSolidType that = ((TopoSolidType) object);
        {
            List<DirectedFacePropertyType> lhsDirectedFace;
            lhsDirectedFace = this.getDirectedFace();
            List<DirectedFacePropertyType> rhsDirectedFace;
            rhsDirectedFace = that.getDirectedFace();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "directedFace", lhsDirectedFace), LocatorUtils.property(thatLocator, "directedFace", rhsDirectedFace), lhsDirectedFace, rhsDirectedFace)) {
                return false;
            }
        }
        {
            SolidPropertyType lhsSolidProperty;
            lhsSolidProperty = this.getSolidProperty();
            SolidPropertyType rhsSolidProperty;
            rhsSolidProperty = that.getSolidProperty();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "solidProperty", lhsSolidProperty), LocatorUtils.property(thatLocator, "solidProperty", rhsSolidProperty), lhsSolidProperty, rhsSolidProperty)) {
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
            List<DirectedFacePropertyType> theDirectedFace;
            theDirectedFace = this.getDirectedFace();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "directedFace", theDirectedFace), currentHashCode, theDirectedFace);
        }
        {
            SolidPropertyType theSolidProperty;
            theSolidProperty = this.getSolidProperty();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "solidProperty", theSolidProperty), currentHashCode, theSolidProperty);
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
        if (draftCopy instanceof TopoSolidType) {
            final TopoSolidType copy = ((TopoSolidType) draftCopy);
            if (this.isSetDirectedFace()) {
                List<DirectedFacePropertyType> sourceDirectedFace;
                sourceDirectedFace = this.getDirectedFace();
                @SuppressWarnings("unchecked")
                List<DirectedFacePropertyType> copyDirectedFace = ((List<DirectedFacePropertyType> ) strategy.copy(LocatorUtils.property(locator, "directedFace", sourceDirectedFace), sourceDirectedFace));
                copy.unsetDirectedFace();
                List<DirectedFacePropertyType> uniqueDirectedFacel = copy.getDirectedFace();
                uniqueDirectedFacel.addAll(copyDirectedFace);
            } else {
                copy.unsetDirectedFace();
            }
            if (this.isSetSolidProperty()) {
                SolidPropertyType sourceSolidProperty;
                sourceSolidProperty = this.getSolidProperty();
                SolidPropertyType copySolidProperty = ((SolidPropertyType) strategy.copy(LocatorUtils.property(locator, "solidProperty", sourceSolidProperty), sourceSolidProperty));
                copy.setSolidProperty(copySolidProperty);
            } else {
                copy.solidProperty = null;
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
        return new TopoSolidType();
    }

    public void mergeFrom(Object left, Object right) {
        final MergeStrategy strategy = JAXBMergeStrategy.INSTANCE;
        mergeFrom(null, null, left, right, strategy);
    }

    public void mergeFrom(ObjectLocator leftLocator, ObjectLocator rightLocator, Object left, Object right, MergeStrategy strategy) {
        super.mergeFrom(leftLocator, rightLocator, left, right, strategy);
        if (right instanceof TopoSolidType) {
            final TopoSolidType target = this;
            final TopoSolidType leftObject = ((TopoSolidType) left);
            final TopoSolidType rightObject = ((TopoSolidType) right);
            {
                List<DirectedFacePropertyType> lhsDirectedFace;
                lhsDirectedFace = leftObject.getDirectedFace();
                List<DirectedFacePropertyType> rhsDirectedFace;
                rhsDirectedFace = rightObject.getDirectedFace();
                target.unsetDirectedFace();
                List<DirectedFacePropertyType> uniqueDirectedFacel = target.getDirectedFace();
                uniqueDirectedFacel.addAll(((List<DirectedFacePropertyType> ) strategy.merge(LocatorUtils.property(leftLocator, "directedFace", lhsDirectedFace), LocatorUtils.property(rightLocator, "directedFace", rhsDirectedFace), lhsDirectedFace, rhsDirectedFace)));
            }
            {
                SolidPropertyType lhsSolidProperty;
                lhsSolidProperty = leftObject.getSolidProperty();
                SolidPropertyType rhsSolidProperty;
                rhsSolidProperty = rightObject.getSolidProperty();
                target.setSolidProperty(((SolidPropertyType) strategy.merge(LocatorUtils.property(leftLocator, "solidProperty", lhsSolidProperty), LocatorUtils.property(rightLocator, "solidProperty", rhsSolidProperty), lhsSolidProperty, rhsSolidProperty)));
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

    public void setDirectedFace(List<DirectedFacePropertyType> value) {
        List<DirectedFacePropertyType> draftl = this.getDirectedFace();
        draftl.addAll(value);
    }

    public TopoSolidType withDirectedFace(DirectedFacePropertyType... values) {
        if (values!= null) {
            for (DirectedFacePropertyType value: values) {
                getDirectedFace().add(value);
            }
        }
        return this;
    }

    public TopoSolidType withDirectedFace(Collection<DirectedFacePropertyType> values) {
        if (values!= null) {
            getDirectedFace().addAll(values);
        }
        return this;
    }

    public TopoSolidType withSolidProperty(SolidPropertyType value) {
        setSolidProperty(value);
        return this;
    }

    public TopoSolidType withAggregationType(AggregationType value) {
        setAggregationType(value);
        return this;
    }

    public TopoSolidType withDirectedFace(List<DirectedFacePropertyType> value) {
        setDirectedFace(value);
        return this;
    }

    @Override
    public TopoSolidType withIsolated(IsolatedPropertyType... values) {
        if (values!= null) {
            for (IsolatedPropertyType value: values) {
                getIsolated().add(value);
            }
        }
        return this;
    }

    @Override
    public TopoSolidType withIsolated(Collection<IsolatedPropertyType> values) {
        if (values!= null) {
            getIsolated().addAll(values);
        }
        return this;
    }

    @Override
    public TopoSolidType withContainer(ContainerPropertyType value) {
        setContainer(value);
        return this;
    }

    @Override
    public TopoSolidType withIsolated(List<IsolatedPropertyType> value) {
        setIsolated(value);
        return this;
    }

    @Override
    public TopoSolidType withMetaDataProperty(MetaDataPropertyType... values) {
        if (values!= null) {
            for (MetaDataPropertyType value: values) {
                getMetaDataProperty().add(value);
            }
        }
        return this;
    }

    @Override
    public TopoSolidType withMetaDataProperty(Collection<MetaDataPropertyType> values) {
        if (values!= null) {
            getMetaDataProperty().addAll(values);
        }
        return this;
    }

    @Override
    public TopoSolidType withDescription(StringOrRefType value) {
        setDescription(value);
        return this;
    }

    @Override
    public TopoSolidType withDescriptionReference(ReferenceType value) {
        setDescriptionReference(value);
        return this;
    }

    @Override
    public TopoSolidType withIdentifier(CodeWithAuthorityType value) {
        setIdentifier(value);
        return this;
    }

    @Override
    public TopoSolidType withName(CodeType... values) {
        if (values!= null) {
            for (CodeType value: values) {
                getName().add(value);
            }
        }
        return this;
    }

    @Override
    public TopoSolidType withName(Collection<CodeType> values) {
        if (values!= null) {
            getName().addAll(values);
        }
        return this;
    }

    @Override
    public TopoSolidType withId(String value) {
        setId(value);
        return this;
    }

    @Override
    public TopoSolidType withMetaDataProperty(List<MetaDataPropertyType> value) {
        setMetaDataProperty(value);
        return this;
    }

    @Override
    public TopoSolidType withName(List<CodeType> value) {
        setName(value);
        return this;
    }

}
