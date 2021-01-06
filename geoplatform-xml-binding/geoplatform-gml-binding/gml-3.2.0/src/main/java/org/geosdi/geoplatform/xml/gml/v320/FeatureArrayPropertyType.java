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
import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
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
 * <p>Classe Java per FeatureArrayPropertyType complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="FeatureArrayPropertyType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence maxOccurs="unbounded" minOccurs="0">
 *         &lt;element ref="{http://www.opengis.net/gml}AbstractFeature"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "FeatureArrayPropertyType", propOrder = {
    "abstractFeature"
})
public class FeatureArrayPropertyType
    implements Cloneable, CopyTo, Equals, HashCode, MergeFrom, ToString
{

    @XmlElementRef(name = "AbstractFeature", namespace = "http://www.opengis.net/gml", type = JAXBElement.class, required = false)
    protected List<JAXBElement<? extends AbstractFeatureType>> abstractFeature;

    /**
     * Gets the value of the abstractFeature property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the abstractFeature property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAbstractFeature().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link JAXBElement }{@code <}{@link DirectedObservationType }{@code >}
     * {@link JAXBElement }{@code <}{@link GridCoverageType }{@code >}
     * {@link JAXBElement }{@code <}{@link MultiSurfaceCoverageType }{@code >}
     * {@link JAXBElement }{@code <}{@link MultiPointCoverageType }{@code >}
     * {@link JAXBElement }{@code <}{@link MultiSolidCoverageType }{@code >}
     * {@link JAXBElement }{@code <}{@link ObservationType }{@code >}
     * {@link JAXBElement }{@code <}{@link RectifiedGridCoverageType }{@code >}
     * {@link JAXBElement }{@code <}{@link AbstractFeatureType }{@code >}
     * {@link JAXBElement }{@code <}{@link AbstractContinuousCoverageType }{@code >}
     * {@link JAXBElement }{@code <}{@link AbstractCoverageType }{@code >}
     * {@link JAXBElement }{@code <}{@link DynamicFeatureCollectionType }{@code >}
     * {@link JAXBElement }{@code <}{@link MultiCurveCoverageType }{@code >}
     * {@link JAXBElement }{@code <}{@link AbstractDiscreteCoverageType }{@code >}
     * {@link JAXBElement }{@code <}{@link FeatureCollectionType }{@code >}
     * {@link JAXBElement }{@code <}{@link DirectedObservationAtDistanceType }{@code >}
     * {@link JAXBElement }{@code <}{@link AbstractFeatureCollectionType }{@code >}
     * {@link JAXBElement }{@code <}{@link DynamicFeatureType }{@code >}
     * 
     * 
     */
    public List<JAXBElement<? extends AbstractFeatureType>> getAbstractFeature() {
        if (abstractFeature == null) {
            abstractFeature = new ArrayList<JAXBElement<? extends AbstractFeatureType>>();
        }
        return this.abstractFeature;
    }

    public boolean isSetAbstractFeature() {
        return ((this.abstractFeature!= null)&&(!this.abstractFeature.isEmpty()));
    }

    public void unsetAbstractFeature() {
        this.abstractFeature = null;
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
            List<JAXBElement<? extends AbstractFeatureType>> theAbstractFeature;
            theAbstractFeature = this.getAbstractFeature();
            strategy.appendField(locator, this, "abstractFeature", buffer, theAbstractFeature);
        }
        return buffer;
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof FeatureArrayPropertyType)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        final FeatureArrayPropertyType that = ((FeatureArrayPropertyType) object);
        {
            List<JAXBElement<? extends AbstractFeatureType>> lhsAbstractFeature;
            lhsAbstractFeature = this.getAbstractFeature();
            List<JAXBElement<? extends AbstractFeatureType>> rhsAbstractFeature;
            rhsAbstractFeature = that.getAbstractFeature();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "abstractFeature", lhsAbstractFeature), LocatorUtils.property(thatLocator, "abstractFeature", rhsAbstractFeature), lhsAbstractFeature, rhsAbstractFeature)) {
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
            List<JAXBElement<? extends AbstractFeatureType>> theAbstractFeature;
            theAbstractFeature = this.getAbstractFeature();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "abstractFeature", theAbstractFeature), currentHashCode, theAbstractFeature);
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
        if (draftCopy instanceof FeatureArrayPropertyType) {
            final FeatureArrayPropertyType copy = ((FeatureArrayPropertyType) draftCopy);
            if (this.isSetAbstractFeature()) {
                List<JAXBElement<? extends AbstractFeatureType>> sourceAbstractFeature;
                sourceAbstractFeature = this.getAbstractFeature();
                @SuppressWarnings("unchecked")
                List<JAXBElement<? extends AbstractFeatureType>> copyAbstractFeature = ((List<JAXBElement<? extends AbstractFeatureType>> ) strategy.copy(LocatorUtils.property(locator, "abstractFeature", sourceAbstractFeature), sourceAbstractFeature));
                copy.unsetAbstractFeature();
                List<JAXBElement<? extends AbstractFeatureType>> uniqueAbstractFeaturel = copy.getAbstractFeature();
                uniqueAbstractFeaturel.addAll(copyAbstractFeature);
            } else {
                copy.unsetAbstractFeature();
            }
        }
        return draftCopy;
    }

    public Object createNewInstance() {
        return new FeatureArrayPropertyType();
    }

    public void mergeFrom(Object left, Object right) {
        final MergeStrategy strategy = JAXBMergeStrategy.INSTANCE;
        mergeFrom(null, null, left, right, strategy);
    }

    public void mergeFrom(ObjectLocator leftLocator, ObjectLocator rightLocator, Object left, Object right, MergeStrategy strategy) {
        if (right instanceof FeatureArrayPropertyType) {
            final FeatureArrayPropertyType target = this;
            final FeatureArrayPropertyType leftObject = ((FeatureArrayPropertyType) left);
            final FeatureArrayPropertyType rightObject = ((FeatureArrayPropertyType) right);
            {
                List<JAXBElement<? extends AbstractFeatureType>> lhsAbstractFeature;
                lhsAbstractFeature = leftObject.getAbstractFeature();
                List<JAXBElement<? extends AbstractFeatureType>> rhsAbstractFeature;
                rhsAbstractFeature = rightObject.getAbstractFeature();
                target.unsetAbstractFeature();
                List<JAXBElement<? extends AbstractFeatureType>> uniqueAbstractFeaturel = target.getAbstractFeature();
                uniqueAbstractFeaturel.addAll(((List<JAXBElement<? extends AbstractFeatureType>> ) strategy.merge(LocatorUtils.property(leftLocator, "abstractFeature", lhsAbstractFeature), LocatorUtils.property(rightLocator, "abstractFeature", rhsAbstractFeature), lhsAbstractFeature, rhsAbstractFeature)));
            }
        }
    }

    public void setAbstractFeature(List<JAXBElement<? extends AbstractFeatureType>> value) {
        List<JAXBElement<? extends AbstractFeatureType>> draftl = this.getAbstractFeature();
        draftl.addAll(value);
    }

    public FeatureArrayPropertyType withAbstractFeature(JAXBElement<? extends AbstractFeatureType> ... values) {
        if (values!= null) {
            for (JAXBElement<? extends AbstractFeatureType> value: values) {
                getAbstractFeature().add(value);
            }
        }
        return this;
    }

    public FeatureArrayPropertyType withAbstractFeature(Collection<JAXBElement<? extends AbstractFeatureType>> values) {
        if (values!= null) {
            getAbstractFeature().addAll(values);
        }
        return this;
    }

    public FeatureArrayPropertyType withAbstractFeature(List<JAXBElement<? extends AbstractFeatureType>> value) {
        setAbstractFeature(value);
        return this;
    }

}
