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
import jakarta.xml.bind.JAXBElement;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElementRef;
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
 * <p>Classe Java per FeatureArrayPropertyType complex type.</p>
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.</p>
 * 
 * <pre>{@code
 * <complexType name="FeatureArrayPropertyType">
 *   <complexContent>
 *     <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       <sequence maxOccurs="unbounded" minOccurs="0">
 *         <element ref="{http://www.opengis.net/gml}AbstractFeature"/>
 *       </sequence>
 *     </restriction>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "FeatureArrayPropertyType", propOrder = {
    "abstractFeature"
})
public class FeatureArrayPropertyType implements Cloneable, CopyTo, Equals, HashCode, MergeFrom, ToString
{

    @XmlElementRef(name = "AbstractFeature", namespace = "http://www.opengis.net/gml", type = JAXBElement.class, required = false)
    protected List<JAXBElement<? extends AbstractFeatureType>> abstractFeature;

    /**
     * Gets the value of the abstractFeature property.
     * 
     * <p>This accessor method returns a reference to the live list,
     * not a snapshot. Therefore, any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a {@code set} method for the abstractFeature property.</p>
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * </p>
     * <pre>
     * getAbstractFeature().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link JAXBElement }{@code <}{@link AbstractContinuousCoverageType }{@code >}
     * {@link JAXBElement }{@code <}{@link AbstractCoverageType }{@code >}
     * {@link JAXBElement }{@code <}{@link AbstractDiscreteCoverageType }{@code >}
     * {@link JAXBElement }{@code <}{@link AbstractFeatureCollectionType }{@code >}
     * {@link JAXBElement }{@code <}{@link AbstractFeatureType }{@code >}
     * {@link JAXBElement }{@code <}{@link DirectedObservationAtDistanceType }{@code >}
     * {@link JAXBElement }{@code <}{@link DirectedObservationType }{@code >}
     * {@link JAXBElement }{@code <}{@link DynamicFeatureCollectionType }{@code >}
     * {@link JAXBElement }{@code <}{@link DynamicFeatureType }{@code >}
     * {@link JAXBElement }{@code <}{@link FeatureCollectionType }{@code >}
     * {@link JAXBElement }{@code <}{@link GridCoverageType }{@code >}
     * {@link JAXBElement }{@code <}{@link MultiCurveCoverageType }{@code >}
     * {@link JAXBElement }{@code <}{@link MultiPointCoverageType }{@code >}
     * {@link JAXBElement }{@code <}{@link MultiSolidCoverageType }{@code >}
     * {@link JAXBElement }{@code <}{@link MultiSurfaceCoverageType }{@code >}
     * {@link JAXBElement }{@code <}{@link ObservationType }{@code >}
     * {@link JAXBElement }{@code <}{@link RectifiedGridCoverageType }{@code >}
     * </p>
     * 
     * 
     * @return
     *     The value of the abstractFeature property.
     */
    public List<JAXBElement<? extends AbstractFeatureType>> getAbstractFeature() {
        if (abstractFeature == null) {
            abstractFeature = new ArrayList<>();
        }
        return this.abstractFeature;
    }

    public boolean isSetAbstractFeature() {
        return ((this.abstractFeature!= null)&&(!this.abstractFeature.isEmpty()));
    }

    public void unsetAbstractFeature() {
        this.abstractFeature = null;
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
            List<JAXBElement<? extends AbstractFeatureType>> theAbstractFeature;
            theAbstractFeature = (this.isSetAbstractFeature()?this.getAbstractFeature():null);
            strategy.appendField(locator, this, "abstractFeature", buffer, theAbstractFeature, this.isSetAbstractFeature());
        }
        return buffer;
    }

    public void setAbstractFeature(List<JAXBElement<? extends AbstractFeatureType>> value) {
        this.abstractFeature = null;
        if (value!= null) {
            List<JAXBElement<? extends AbstractFeatureType>> draftl = this.getAbstractFeature();
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
        final FeatureArrayPropertyType that = ((FeatureArrayPropertyType) object);
        {
            List<JAXBElement<? extends AbstractFeatureType>> lhsAbstractFeature;
            lhsAbstractFeature = (this.isSetAbstractFeature()?this.getAbstractFeature():null);
            List<JAXBElement<? extends AbstractFeatureType>> rhsAbstractFeature;
            rhsAbstractFeature = (that.isSetAbstractFeature()?that.getAbstractFeature():null);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "abstractFeature", lhsAbstractFeature), LocatorUtils.property(thatLocator, "abstractFeature", rhsAbstractFeature), lhsAbstractFeature, rhsAbstractFeature, this.isSetAbstractFeature(), that.isSetAbstractFeature())) {
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
            List<JAXBElement<? extends AbstractFeatureType>> theAbstractFeature;
            theAbstractFeature = (this.isSetAbstractFeature()?this.getAbstractFeature():null);
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "abstractFeature", theAbstractFeature), currentHashCode, theAbstractFeature, this.isSetAbstractFeature());
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
        if (draftCopy instanceof FeatureArrayPropertyType) {
            final FeatureArrayPropertyType copy = ((FeatureArrayPropertyType) draftCopy);
            {
                java.lang.Boolean abstractFeatureShouldBeCopiedAndSet = strategy.shouldBeCopiedAndSet(locator, this.isSetAbstractFeature());
                if (abstractFeatureShouldBeCopiedAndSet == java.lang.Boolean.TRUE) {
                    List<JAXBElement<? extends AbstractFeatureType>> sourceAbstractFeature;
                    sourceAbstractFeature = (this.isSetAbstractFeature()?this.getAbstractFeature():null);
                    @SuppressWarnings("unchecked")
                    List<JAXBElement<? extends AbstractFeatureType>> copyAbstractFeature = ((List<JAXBElement<? extends AbstractFeatureType>> ) strategy.copy(LocatorUtils.property(locator, "abstractFeature", sourceAbstractFeature), sourceAbstractFeature, this.isSetAbstractFeature()));
                    copy.setAbstractFeature(copyAbstractFeature);
                } else {
                    if (abstractFeatureShouldBeCopiedAndSet == java.lang.Boolean.FALSE) {
                        copy.unsetAbstractFeature();
                    }
                }
            }
        }
        return draftCopy;
    }

    @Override
    public Object createNewInstance() {
        return new FeatureArrayPropertyType();
    }

    @Override
    public void mergeFrom(Object left, Object right) {
        final MergeStrategy strategy = JAXBMergeStrategy.getInstance();
        mergeFrom(null, null, left, right, strategy);
    }

    @Override
    public void mergeFrom(ObjectLocator leftLocator, ObjectLocator rightLocator, Object left, Object right, MergeStrategy strategy) {
        if (right instanceof FeatureArrayPropertyType) {
            final FeatureArrayPropertyType target = this;
            final FeatureArrayPropertyType leftObject = ((FeatureArrayPropertyType) left);
            final FeatureArrayPropertyType rightObject = ((FeatureArrayPropertyType) right);
            {
                java.lang.Boolean abstractFeatureShouldBeMergedAndSet = strategy.shouldBeMergedAndSet(leftLocator, rightLocator, leftObject.isSetAbstractFeature(), rightObject.isSetAbstractFeature());
                if (abstractFeatureShouldBeMergedAndSet == java.lang.Boolean.TRUE) {
                    List<JAXBElement<? extends AbstractFeatureType>> lhsAbstractFeature;
                    lhsAbstractFeature = (leftObject.isSetAbstractFeature()?leftObject.getAbstractFeature():null);
                    List<JAXBElement<? extends AbstractFeatureType>> rhsAbstractFeature;
                    rhsAbstractFeature = (rightObject.isSetAbstractFeature()?rightObject.getAbstractFeature():null);
                    List<JAXBElement<? extends AbstractFeatureType>> mergedAbstractFeature = ((List<JAXBElement<? extends AbstractFeatureType>> ) strategy.merge(LocatorUtils.property(leftLocator, "abstractFeature", lhsAbstractFeature), LocatorUtils.property(rightLocator, "abstractFeature", rhsAbstractFeature), lhsAbstractFeature, rhsAbstractFeature, leftObject.isSetAbstractFeature(), rightObject.isSetAbstractFeature()));
                    target.setAbstractFeature(mergedAbstractFeature);
                } else {
                    if (abstractFeatureShouldBeMergedAndSet == java.lang.Boolean.FALSE) {
                        target.unsetAbstractFeature();
                    }
                }
            }
        }
    }

}
