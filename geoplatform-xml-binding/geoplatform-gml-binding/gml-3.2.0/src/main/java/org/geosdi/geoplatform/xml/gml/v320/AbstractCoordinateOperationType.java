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
 * <p>Classe Java per AbstractCoordinateOperationType complex type.</p>
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.</p>
 * 
 * <pre>{@code
 * <complexType name="AbstractCoordinateOperationType">
 *   <complexContent>
 *     <extension base="{http://www.opengis.net/gml}IdentifiedObjectType">
 *       <sequence>
 *         <element ref="{http://www.opengis.net/gml}domainOfValidity" minOccurs="0"/>
 *         <element ref="{http://www.opengis.net/gml}scope" maxOccurs="unbounded"/>
 *         <element ref="{http://www.opengis.net/gml}operationVersion" minOccurs="0"/>
 *         <element ref="{http://www.opengis.net/gml}coordinateOperationAccuracy" maxOccurs="unbounded" minOccurs="0"/>
 *         <element ref="{http://www.opengis.net/gml}sourceCRS" minOccurs="0"/>
 *         <element ref="{http://www.opengis.net/gml}targetCRS" minOccurs="0"/>
 *       </sequence>
 *     </extension>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AbstractCoordinateOperationType", propOrder = {
    "domainOfValidity",
    "scope",
    "operationVersion",
    "coordinateOperationAccuracy",
    "sourceCRS",
    "targetCRS"
})
@XmlSeeAlso({
    ConcatenatedOperationType.class,
    PassThroughOperationType.class,
    AbstractGeneralConversionType.class,
    AbstractGeneralTransformationType.class
})
public abstract class AbstractCoordinateOperationType
    extends IdentifiedObjectType
    implements Cloneable, CopyTo, Equals, HashCode, MergeFrom, ToString
{

    /**
     * The gml:domainOfValidity property implements an association role to an EX_Extent object as encoded in ISO/TS 19139, either referencing or containing the definition of that extent.
     * 
     */
    protected DomainOfValidityElement domainOfValidity;
    /**
     * The gml:scope property provides a description of the usage, or limitations of usage, for which this CRS-related object is valid. If unknown, enter "not known".
     * 
     */
    @XmlElement(required = true)
    protected List<String> scope;
    /**
     * gml:operationVersion is the version of the coordinate transformation (i.e., instantiation due to the stochastic nature of the parameters). Mandatory when describing a transformation, and should not be supplied for a conversion.
     * 
     */
    protected String operationVersion;
    /**
     * gml:coordinateOperationAccuracy is an association role to a DQ_PositionalAccuracy object as encoded in ISO/TS 19139, either referencing or containing the definition of that positional accuracy. That object contains an estimate of the impact of this coordinate operation on point accuracy. That is, it gives position error estimates for the target coordinates of this coordinate operation, assuming no errors in the source coordinates.
     * 
     */
    protected List<CoordinateOperationAccuracyElement> coordinateOperationAccuracy;
    /**
     * gml:sourceCRS is an association role to the source CRS (coordinate reference system) of this coordinate operation.
     * 
     */
    protected CRSPropertyType sourceCRS;
    /**
     * gml:targetCRS is an association role to the target CRS (coordinate reference system) of this coordinate operation.
     * 
     */
    protected CRSPropertyType targetCRS;

    /**
     * The gml:domainOfValidity property implements an association role to an EX_Extent object as encoded in ISO/TS 19139, either referencing or containing the definition of that extent.
     * 
     * @return
     *     possible object is
     *     {@link DomainOfValidityElement }
     *     
     */
    public DomainOfValidityElement getDomainOfValidity() {
        return domainOfValidity;
    }

    /**
     * Imposta il valore della proprietà domainOfValidity.
     * 
     * @param value
     *     allowed object is
     *     {@link DomainOfValidityElement }
     *     
     * @see #getDomainOfValidity()
     */
    public void setDomainOfValidity(DomainOfValidityElement value) {
        this.domainOfValidity = value;
    }

    public boolean isSetDomainOfValidity() {
        return (this.domainOfValidity!= null);
    }

    /**
     * The gml:scope property provides a description of the usage, or limitations of usage, for which this CRS-related object is valid. If unknown, enter "not known".
     * 
     * Gets the value of the scope property.
     * 
     * <p>This accessor method returns a reference to the live list,
     * not a snapshot. Therefore, any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a {@code set} method for the scope property.</p>
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * </p>
     * <pre>
     * getScope().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * </p>
     * 
     * 
     * @return
     *     The value of the scope property.
     */
    public List<String> getScope() {
        if (scope == null) {
            scope = new ArrayList<>();
        }
        return this.scope;
    }

    public boolean isSetScope() {
        return ((this.scope!= null)&&(!this.scope.isEmpty()));
    }

    public void unsetScope() {
        this.scope = null;
    }

    /**
     * gml:operationVersion is the version of the coordinate transformation (i.e., instantiation due to the stochastic nature of the parameters). Mandatory when describing a transformation, and should not be supplied for a conversion.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOperationVersion() {
        return operationVersion;
    }

    /**
     * Imposta il valore della proprietà operationVersion.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     * @see #getOperationVersion()
     */
    public void setOperationVersion(String value) {
        this.operationVersion = value;
    }

    public boolean isSetOperationVersion() {
        return (this.operationVersion!= null);
    }

    /**
     * gml:coordinateOperationAccuracy is an association role to a DQ_PositionalAccuracy object as encoded in ISO/TS 19139, either referencing or containing the definition of that positional accuracy. That object contains an estimate of the impact of this coordinate operation on point accuracy. That is, it gives position error estimates for the target coordinates of this coordinate operation, assuming no errors in the source coordinates.
     * 
     * Gets the value of the coordinateOperationAccuracy property.
     * 
     * <p>This accessor method returns a reference to the live list,
     * not a snapshot. Therefore, any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a {@code set} method for the coordinateOperationAccuracy property.</p>
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * </p>
     * <pre>
     * getCoordinateOperationAccuracy().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CoordinateOperationAccuracyElement }
     * </p>
     * 
     * 
     * @return
     *     The value of the coordinateOperationAccuracy property.
     */
    public List<CoordinateOperationAccuracyElement> getCoordinateOperationAccuracy() {
        if (coordinateOperationAccuracy == null) {
            coordinateOperationAccuracy = new ArrayList<>();
        }
        return this.coordinateOperationAccuracy;
    }

    public boolean isSetCoordinateOperationAccuracy() {
        return ((this.coordinateOperationAccuracy!= null)&&(!this.coordinateOperationAccuracy.isEmpty()));
    }

    public void unsetCoordinateOperationAccuracy() {
        this.coordinateOperationAccuracy = null;
    }

    /**
     * gml:sourceCRS is an association role to the source CRS (coordinate reference system) of this coordinate operation.
     * 
     * @return
     *     possible object is
     *     {@link CRSPropertyType }
     *     
     */
    public CRSPropertyType getSourceCRS() {
        return sourceCRS;
    }

    /**
     * Imposta il valore della proprietà sourceCRS.
     * 
     * @param value
     *     allowed object is
     *     {@link CRSPropertyType }
     *     
     * @see #getSourceCRS()
     */
    public void setSourceCRS(CRSPropertyType value) {
        this.sourceCRS = value;
    }

    public boolean isSetSourceCRS() {
        return (this.sourceCRS!= null);
    }

    /**
     * gml:targetCRS is an association role to the target CRS (coordinate reference system) of this coordinate operation.
     * 
     * @return
     *     possible object is
     *     {@link CRSPropertyType }
     *     
     */
    public CRSPropertyType getTargetCRS() {
        return targetCRS;
    }

    /**
     * Imposta il valore della proprietà targetCRS.
     * 
     * @param value
     *     allowed object is
     *     {@link CRSPropertyType }
     *     
     * @see #getTargetCRS()
     */
    public void setTargetCRS(CRSPropertyType value) {
        this.targetCRS = value;
    }

    public boolean isSetTargetCRS() {
        return (this.targetCRS!= null);
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
            DomainOfValidityElement theDomainOfValidity;
            theDomainOfValidity = this.getDomainOfValidity();
            strategy.appendField(locator, this, "domainOfValidity", buffer, theDomainOfValidity, this.isSetDomainOfValidity());
        }
        {
            List<String> theScope;
            theScope = (this.isSetScope()?this.getScope():null);
            strategy.appendField(locator, this, "scope", buffer, theScope, this.isSetScope());
        }
        {
            String theOperationVersion;
            theOperationVersion = this.getOperationVersion();
            strategy.appendField(locator, this, "operationVersion", buffer, theOperationVersion, this.isSetOperationVersion());
        }
        {
            List<CoordinateOperationAccuracyElement> theCoordinateOperationAccuracy;
            theCoordinateOperationAccuracy = (this.isSetCoordinateOperationAccuracy()?this.getCoordinateOperationAccuracy():null);
            strategy.appendField(locator, this, "coordinateOperationAccuracy", buffer, theCoordinateOperationAccuracy, this.isSetCoordinateOperationAccuracy());
        }
        {
            CRSPropertyType theSourceCRS;
            theSourceCRS = this.getSourceCRS();
            strategy.appendField(locator, this, "sourceCRS", buffer, theSourceCRS, this.isSetSourceCRS());
        }
        {
            CRSPropertyType theTargetCRS;
            theTargetCRS = this.getTargetCRS();
            strategy.appendField(locator, this, "targetCRS", buffer, theTargetCRS, this.isSetTargetCRS());
        }
        return buffer;
    }

    public void setScope(List<String> value) {
        this.scope = null;
        if (value!= null) {
            List<String> draftl = this.getScope();
            draftl.addAll(value);
        }
    }

    public void setCoordinateOperationAccuracy(List<CoordinateOperationAccuracyElement> value) {
        this.coordinateOperationAccuracy = null;
        if (value!= null) {
            List<CoordinateOperationAccuracyElement> draftl = this.getCoordinateOperationAccuracy();
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
        final AbstractCoordinateOperationType that = ((AbstractCoordinateOperationType) object);
        {
            DomainOfValidityElement lhsDomainOfValidity;
            lhsDomainOfValidity = this.getDomainOfValidity();
            DomainOfValidityElement rhsDomainOfValidity;
            rhsDomainOfValidity = that.getDomainOfValidity();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "domainOfValidity", lhsDomainOfValidity), LocatorUtils.property(thatLocator, "domainOfValidity", rhsDomainOfValidity), lhsDomainOfValidity, rhsDomainOfValidity, this.isSetDomainOfValidity(), that.isSetDomainOfValidity())) {
                return false;
            }
        }
        {
            List<String> lhsScope;
            lhsScope = (this.isSetScope()?this.getScope():null);
            List<String> rhsScope;
            rhsScope = (that.isSetScope()?that.getScope():null);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "scope", lhsScope), LocatorUtils.property(thatLocator, "scope", rhsScope), lhsScope, rhsScope, this.isSetScope(), that.isSetScope())) {
                return false;
            }
        }
        {
            String lhsOperationVersion;
            lhsOperationVersion = this.getOperationVersion();
            String rhsOperationVersion;
            rhsOperationVersion = that.getOperationVersion();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "operationVersion", lhsOperationVersion), LocatorUtils.property(thatLocator, "operationVersion", rhsOperationVersion), lhsOperationVersion, rhsOperationVersion, this.isSetOperationVersion(), that.isSetOperationVersion())) {
                return false;
            }
        }
        {
            List<CoordinateOperationAccuracyElement> lhsCoordinateOperationAccuracy;
            lhsCoordinateOperationAccuracy = (this.isSetCoordinateOperationAccuracy()?this.getCoordinateOperationAccuracy():null);
            List<CoordinateOperationAccuracyElement> rhsCoordinateOperationAccuracy;
            rhsCoordinateOperationAccuracy = (that.isSetCoordinateOperationAccuracy()?that.getCoordinateOperationAccuracy():null);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "coordinateOperationAccuracy", lhsCoordinateOperationAccuracy), LocatorUtils.property(thatLocator, "coordinateOperationAccuracy", rhsCoordinateOperationAccuracy), lhsCoordinateOperationAccuracy, rhsCoordinateOperationAccuracy, this.isSetCoordinateOperationAccuracy(), that.isSetCoordinateOperationAccuracy())) {
                return false;
            }
        }
        {
            CRSPropertyType lhsSourceCRS;
            lhsSourceCRS = this.getSourceCRS();
            CRSPropertyType rhsSourceCRS;
            rhsSourceCRS = that.getSourceCRS();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "sourceCRS", lhsSourceCRS), LocatorUtils.property(thatLocator, "sourceCRS", rhsSourceCRS), lhsSourceCRS, rhsSourceCRS, this.isSetSourceCRS(), that.isSetSourceCRS())) {
                return false;
            }
        }
        {
            CRSPropertyType lhsTargetCRS;
            lhsTargetCRS = this.getTargetCRS();
            CRSPropertyType rhsTargetCRS;
            rhsTargetCRS = that.getTargetCRS();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "targetCRS", lhsTargetCRS), LocatorUtils.property(thatLocator, "targetCRS", rhsTargetCRS), lhsTargetCRS, rhsTargetCRS, this.isSetTargetCRS(), that.isSetTargetCRS())) {
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
            DomainOfValidityElement theDomainOfValidity;
            theDomainOfValidity = this.getDomainOfValidity();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "domainOfValidity", theDomainOfValidity), currentHashCode, theDomainOfValidity, this.isSetDomainOfValidity());
        }
        {
            List<String> theScope;
            theScope = (this.isSetScope()?this.getScope():null);
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "scope", theScope), currentHashCode, theScope, this.isSetScope());
        }
        {
            String theOperationVersion;
            theOperationVersion = this.getOperationVersion();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "operationVersion", theOperationVersion), currentHashCode, theOperationVersion, this.isSetOperationVersion());
        }
        {
            List<CoordinateOperationAccuracyElement> theCoordinateOperationAccuracy;
            theCoordinateOperationAccuracy = (this.isSetCoordinateOperationAccuracy()?this.getCoordinateOperationAccuracy():null);
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "coordinateOperationAccuracy", theCoordinateOperationAccuracy), currentHashCode, theCoordinateOperationAccuracy, this.isSetCoordinateOperationAccuracy());
        }
        {
            CRSPropertyType theSourceCRS;
            theSourceCRS = this.getSourceCRS();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "sourceCRS", theSourceCRS), currentHashCode, theSourceCRS, this.isSetSourceCRS());
        }
        {
            CRSPropertyType theTargetCRS;
            theTargetCRS = this.getTargetCRS();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "targetCRS", theTargetCRS), currentHashCode, theTargetCRS, this.isSetTargetCRS());
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
        if (null == target) {
            throw new IllegalArgumentException("Target argument must not be null for abstract copyable classes.");
        }
        super.copyTo(locator, target, strategy);
        if (target instanceof AbstractCoordinateOperationType) {
            final AbstractCoordinateOperationType copy = ((AbstractCoordinateOperationType) target);
            {
                java.lang.Boolean domainOfValidityShouldBeCopiedAndSet = strategy.shouldBeCopiedAndSet(locator, this.isSetDomainOfValidity());
                if (domainOfValidityShouldBeCopiedAndSet == java.lang.Boolean.TRUE) {
                    DomainOfValidityElement sourceDomainOfValidity;
                    sourceDomainOfValidity = this.getDomainOfValidity();
                    DomainOfValidityElement copyDomainOfValidity = ((DomainOfValidityElement) strategy.copy(LocatorUtils.property(locator, "domainOfValidity", sourceDomainOfValidity), sourceDomainOfValidity, this.isSetDomainOfValidity()));
                    copy.setDomainOfValidity(copyDomainOfValidity);
                } else {
                    if (domainOfValidityShouldBeCopiedAndSet == java.lang.Boolean.FALSE) {
                        copy.domainOfValidity = null;
                    }
                }
            }
            {
                java.lang.Boolean scopeShouldBeCopiedAndSet = strategy.shouldBeCopiedAndSet(locator, this.isSetScope());
                if (scopeShouldBeCopiedAndSet == java.lang.Boolean.TRUE) {
                    List<String> sourceScope;
                    sourceScope = (this.isSetScope()?this.getScope():null);
                    @SuppressWarnings("unchecked")
                    List<String> copyScope = ((List<String> ) strategy.copy(LocatorUtils.property(locator, "scope", sourceScope), sourceScope, this.isSetScope()));
                    copy.setScope(copyScope);
                } else {
                    if (scopeShouldBeCopiedAndSet == java.lang.Boolean.FALSE) {
                        copy.unsetScope();
                    }
                }
            }
            {
                java.lang.Boolean operationVersionShouldBeCopiedAndSet = strategy.shouldBeCopiedAndSet(locator, this.isSetOperationVersion());
                if (operationVersionShouldBeCopiedAndSet == java.lang.Boolean.TRUE) {
                    String sourceOperationVersion;
                    sourceOperationVersion = this.getOperationVersion();
                    String copyOperationVersion = ((String) strategy.copy(LocatorUtils.property(locator, "operationVersion", sourceOperationVersion), sourceOperationVersion, this.isSetOperationVersion()));
                    copy.setOperationVersion(copyOperationVersion);
                } else {
                    if (operationVersionShouldBeCopiedAndSet == java.lang.Boolean.FALSE) {
                        copy.operationVersion = null;
                    }
                }
            }
            {
                java.lang.Boolean coordinateOperationAccuracyShouldBeCopiedAndSet = strategy.shouldBeCopiedAndSet(locator, this.isSetCoordinateOperationAccuracy());
                if (coordinateOperationAccuracyShouldBeCopiedAndSet == java.lang.Boolean.TRUE) {
                    List<CoordinateOperationAccuracyElement> sourceCoordinateOperationAccuracy;
                    sourceCoordinateOperationAccuracy = (this.isSetCoordinateOperationAccuracy()?this.getCoordinateOperationAccuracy():null);
                    @SuppressWarnings("unchecked")
                    List<CoordinateOperationAccuracyElement> copyCoordinateOperationAccuracy = ((List<CoordinateOperationAccuracyElement> ) strategy.copy(LocatorUtils.property(locator, "coordinateOperationAccuracy", sourceCoordinateOperationAccuracy), sourceCoordinateOperationAccuracy, this.isSetCoordinateOperationAccuracy()));
                    copy.setCoordinateOperationAccuracy(copyCoordinateOperationAccuracy);
                } else {
                    if (coordinateOperationAccuracyShouldBeCopiedAndSet == java.lang.Boolean.FALSE) {
                        copy.unsetCoordinateOperationAccuracy();
                    }
                }
            }
            {
                java.lang.Boolean sourceCRSShouldBeCopiedAndSet = strategy.shouldBeCopiedAndSet(locator, this.isSetSourceCRS());
                if (sourceCRSShouldBeCopiedAndSet == java.lang.Boolean.TRUE) {
                    CRSPropertyType sourceSourceCRS;
                    sourceSourceCRS = this.getSourceCRS();
                    CRSPropertyType copySourceCRS = ((CRSPropertyType) strategy.copy(LocatorUtils.property(locator, "sourceCRS", sourceSourceCRS), sourceSourceCRS, this.isSetSourceCRS()));
                    copy.setSourceCRS(copySourceCRS);
                } else {
                    if (sourceCRSShouldBeCopiedAndSet == java.lang.Boolean.FALSE) {
                        copy.sourceCRS = null;
                    }
                }
            }
            {
                java.lang.Boolean targetCRSShouldBeCopiedAndSet = strategy.shouldBeCopiedAndSet(locator, this.isSetTargetCRS());
                if (targetCRSShouldBeCopiedAndSet == java.lang.Boolean.TRUE) {
                    CRSPropertyType sourceTargetCRS;
                    sourceTargetCRS = this.getTargetCRS();
                    CRSPropertyType copyTargetCRS = ((CRSPropertyType) strategy.copy(LocatorUtils.property(locator, "targetCRS", sourceTargetCRS), sourceTargetCRS, this.isSetTargetCRS()));
                    copy.setTargetCRS(copyTargetCRS);
                } else {
                    if (targetCRSShouldBeCopiedAndSet == java.lang.Boolean.FALSE) {
                        copy.targetCRS = null;
                    }
                }
            }
        }
        return target;
    }

    @Override
    public void mergeFrom(Object left, Object right) {
        final MergeStrategy strategy = JAXBMergeStrategy.getInstance();
        mergeFrom(null, null, left, right, strategy);
    }

    @Override
    public void mergeFrom(ObjectLocator leftLocator, ObjectLocator rightLocator, Object left, Object right, MergeStrategy strategy) {
        super.mergeFrom(leftLocator, rightLocator, left, right, strategy);
        if (right instanceof AbstractCoordinateOperationType) {
            final AbstractCoordinateOperationType target = this;
            final AbstractCoordinateOperationType leftObject = ((AbstractCoordinateOperationType) left);
            final AbstractCoordinateOperationType rightObject = ((AbstractCoordinateOperationType) right);
            {
                java.lang.Boolean domainOfValidityShouldBeMergedAndSet = strategy.shouldBeMergedAndSet(leftLocator, rightLocator, leftObject.isSetDomainOfValidity(), rightObject.isSetDomainOfValidity());
                if (domainOfValidityShouldBeMergedAndSet == java.lang.Boolean.TRUE) {
                    DomainOfValidityElement lhsDomainOfValidity;
                    lhsDomainOfValidity = leftObject.getDomainOfValidity();
                    DomainOfValidityElement rhsDomainOfValidity;
                    rhsDomainOfValidity = rightObject.getDomainOfValidity();
                    DomainOfValidityElement mergedDomainOfValidity = ((DomainOfValidityElement) strategy.merge(LocatorUtils.property(leftLocator, "domainOfValidity", lhsDomainOfValidity), LocatorUtils.property(rightLocator, "domainOfValidity", rhsDomainOfValidity), lhsDomainOfValidity, rhsDomainOfValidity, leftObject.isSetDomainOfValidity(), rightObject.isSetDomainOfValidity()));
                    target.setDomainOfValidity(mergedDomainOfValidity);
                } else {
                    if (domainOfValidityShouldBeMergedAndSet == java.lang.Boolean.FALSE) {
                        target.domainOfValidity = null;
                    }
                }
            }
            {
                java.lang.Boolean scopeShouldBeMergedAndSet = strategy.shouldBeMergedAndSet(leftLocator, rightLocator, leftObject.isSetScope(), rightObject.isSetScope());
                if (scopeShouldBeMergedAndSet == java.lang.Boolean.TRUE) {
                    List<String> lhsScope;
                    lhsScope = (leftObject.isSetScope()?leftObject.getScope():null);
                    List<String> rhsScope;
                    rhsScope = (rightObject.isSetScope()?rightObject.getScope():null);
                    List<String> mergedScope = ((List<String> ) strategy.merge(LocatorUtils.property(leftLocator, "scope", lhsScope), LocatorUtils.property(rightLocator, "scope", rhsScope), lhsScope, rhsScope, leftObject.isSetScope(), rightObject.isSetScope()));
                    target.setScope(mergedScope);
                } else {
                    if (scopeShouldBeMergedAndSet == java.lang.Boolean.FALSE) {
                        target.unsetScope();
                    }
                }
            }
            {
                java.lang.Boolean operationVersionShouldBeMergedAndSet = strategy.shouldBeMergedAndSet(leftLocator, rightLocator, leftObject.isSetOperationVersion(), rightObject.isSetOperationVersion());
                if (operationVersionShouldBeMergedAndSet == java.lang.Boolean.TRUE) {
                    String lhsOperationVersion;
                    lhsOperationVersion = leftObject.getOperationVersion();
                    String rhsOperationVersion;
                    rhsOperationVersion = rightObject.getOperationVersion();
                    String mergedOperationVersion = ((String) strategy.merge(LocatorUtils.property(leftLocator, "operationVersion", lhsOperationVersion), LocatorUtils.property(rightLocator, "operationVersion", rhsOperationVersion), lhsOperationVersion, rhsOperationVersion, leftObject.isSetOperationVersion(), rightObject.isSetOperationVersion()));
                    target.setOperationVersion(mergedOperationVersion);
                } else {
                    if (operationVersionShouldBeMergedAndSet == java.lang.Boolean.FALSE) {
                        target.operationVersion = null;
                    }
                }
            }
            {
                java.lang.Boolean coordinateOperationAccuracyShouldBeMergedAndSet = strategy.shouldBeMergedAndSet(leftLocator, rightLocator, leftObject.isSetCoordinateOperationAccuracy(), rightObject.isSetCoordinateOperationAccuracy());
                if (coordinateOperationAccuracyShouldBeMergedAndSet == java.lang.Boolean.TRUE) {
                    List<CoordinateOperationAccuracyElement> lhsCoordinateOperationAccuracy;
                    lhsCoordinateOperationAccuracy = (leftObject.isSetCoordinateOperationAccuracy()?leftObject.getCoordinateOperationAccuracy():null);
                    List<CoordinateOperationAccuracyElement> rhsCoordinateOperationAccuracy;
                    rhsCoordinateOperationAccuracy = (rightObject.isSetCoordinateOperationAccuracy()?rightObject.getCoordinateOperationAccuracy():null);
                    List<CoordinateOperationAccuracyElement> mergedCoordinateOperationAccuracy = ((List<CoordinateOperationAccuracyElement> ) strategy.merge(LocatorUtils.property(leftLocator, "coordinateOperationAccuracy", lhsCoordinateOperationAccuracy), LocatorUtils.property(rightLocator, "coordinateOperationAccuracy", rhsCoordinateOperationAccuracy), lhsCoordinateOperationAccuracy, rhsCoordinateOperationAccuracy, leftObject.isSetCoordinateOperationAccuracy(), rightObject.isSetCoordinateOperationAccuracy()));
                    target.setCoordinateOperationAccuracy(mergedCoordinateOperationAccuracy);
                } else {
                    if (coordinateOperationAccuracyShouldBeMergedAndSet == java.lang.Boolean.FALSE) {
                        target.unsetCoordinateOperationAccuracy();
                    }
                }
            }
            {
                java.lang.Boolean sourceCRSShouldBeMergedAndSet = strategy.shouldBeMergedAndSet(leftLocator, rightLocator, leftObject.isSetSourceCRS(), rightObject.isSetSourceCRS());
                if (sourceCRSShouldBeMergedAndSet == java.lang.Boolean.TRUE) {
                    CRSPropertyType lhsSourceCRS;
                    lhsSourceCRS = leftObject.getSourceCRS();
                    CRSPropertyType rhsSourceCRS;
                    rhsSourceCRS = rightObject.getSourceCRS();
                    CRSPropertyType mergedSourceCRS = ((CRSPropertyType) strategy.merge(LocatorUtils.property(leftLocator, "sourceCRS", lhsSourceCRS), LocatorUtils.property(rightLocator, "sourceCRS", rhsSourceCRS), lhsSourceCRS, rhsSourceCRS, leftObject.isSetSourceCRS(), rightObject.isSetSourceCRS()));
                    target.setSourceCRS(mergedSourceCRS);
                } else {
                    if (sourceCRSShouldBeMergedAndSet == java.lang.Boolean.FALSE) {
                        target.sourceCRS = null;
                    }
                }
            }
            {
                java.lang.Boolean targetCRSShouldBeMergedAndSet = strategy.shouldBeMergedAndSet(leftLocator, rightLocator, leftObject.isSetTargetCRS(), rightObject.isSetTargetCRS());
                if (targetCRSShouldBeMergedAndSet == java.lang.Boolean.TRUE) {
                    CRSPropertyType lhsTargetCRS;
                    lhsTargetCRS = leftObject.getTargetCRS();
                    CRSPropertyType rhsTargetCRS;
                    rhsTargetCRS = rightObject.getTargetCRS();
                    CRSPropertyType mergedTargetCRS = ((CRSPropertyType) strategy.merge(LocatorUtils.property(leftLocator, "targetCRS", lhsTargetCRS), LocatorUtils.property(rightLocator, "targetCRS", rhsTargetCRS), lhsTargetCRS, rhsTargetCRS, leftObject.isSetTargetCRS(), rightObject.isSetTargetCRS()));
                    target.setTargetCRS(mergedTargetCRS);
                } else {
                    if (targetCRSShouldBeMergedAndSet == java.lang.Boolean.FALSE) {
                        target.targetCRS = null;
                    }
                }
            }
        }
    }

}
