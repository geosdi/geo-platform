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
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSeeAlso;
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
 * <p>Classe Java per AbstractCoordinateOperationType complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="AbstractCoordinateOperationType">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.opengis.net/gml}IdentifiedObjectType">
 *       &lt;sequence>
 *         &lt;element ref="{http://www.opengis.net/gml}domainOfValidity" minOccurs="0"/>
 *         &lt;element ref="{http://www.opengis.net/gml}scope" maxOccurs="unbounded"/>
 *         &lt;element ref="{http://www.opengis.net/gml}operationVersion" minOccurs="0"/>
 *         &lt;element ref="{http://www.opengis.net/gml}coordinateOperationAccuracy" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element ref="{http://www.opengis.net/gml}sourceCRS" minOccurs="0"/>
 *         &lt;element ref="{http://www.opengis.net/gml}targetCRS" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
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
    PassThroughOperationType.class,
    AbstractGeneralTransformationType.class,
    AbstractGeneralConversionType.class,
    ConcatenatedOperationType.class
})
public abstract class AbstractCoordinateOperationType
    extends IdentifiedObjectType
    implements Cloneable, CopyTo, Equals, HashCode, MergeFrom, ToString
{

    protected DomainOfValidityElement domainOfValidity;
    @XmlElement(required = true)
    protected List<String> scope;
    protected String operationVersion;
    protected List<CoordinateOperationAccuracyElement> coordinateOperationAccuracy;
    protected CRSPropertyType sourceCRS;
    protected CRSPropertyType targetCRS;

    /**
     * Recupera il valore della proprietà domainOfValidity.
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
     */
    public void setDomainOfValidity(DomainOfValidityElement value) {
        this.domainOfValidity = value;
    }

    public boolean isSetDomainOfValidity() {
        return (this.domainOfValidity!= null);
    }

    /**
     * Gets the value of the scope property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the scope property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getScope().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getScope() {
        if (scope == null) {
            scope = new ArrayList<String>();
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
     * Recupera il valore della proprietà operationVersion.
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
     */
    public void setOperationVersion(String value) {
        this.operationVersion = value;
    }

    public boolean isSetOperationVersion() {
        return (this.operationVersion!= null);
    }

    /**
     * Gets the value of the coordinateOperationAccuracy property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the coordinateOperationAccuracy property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCoordinateOperationAccuracy().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CoordinateOperationAccuracyElement }
     * 
     * 
     */
    public List<CoordinateOperationAccuracyElement> getCoordinateOperationAccuracy() {
        if (coordinateOperationAccuracy == null) {
            coordinateOperationAccuracy = new ArrayList<CoordinateOperationAccuracyElement>();
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
     * Recupera il valore della proprietà sourceCRS.
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
     */
    public void setSourceCRS(CRSPropertyType value) {
        this.sourceCRS = value;
    }

    public boolean isSetSourceCRS() {
        return (this.sourceCRS!= null);
    }

    /**
     * Recupera il valore della proprietà targetCRS.
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
     */
    public void setTargetCRS(CRSPropertyType value) {
        this.targetCRS = value;
    }

    public boolean isSetTargetCRS() {
        return (this.targetCRS!= null);
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
            DomainOfValidityElement theDomainOfValidity;
            theDomainOfValidity = this.getDomainOfValidity();
            strategy.appendField(locator, this, "domainOfValidity", buffer, theDomainOfValidity);
        }
        {
            List<String> theScope;
            theScope = this.getScope();
            strategy.appendField(locator, this, "scope", buffer, theScope);
        }
        {
            String theOperationVersion;
            theOperationVersion = this.getOperationVersion();
            strategy.appendField(locator, this, "operationVersion", buffer, theOperationVersion);
        }
        {
            List<CoordinateOperationAccuracyElement> theCoordinateOperationAccuracy;
            theCoordinateOperationAccuracy = this.getCoordinateOperationAccuracy();
            strategy.appendField(locator, this, "coordinateOperationAccuracy", buffer, theCoordinateOperationAccuracy);
        }
        {
            CRSPropertyType theSourceCRS;
            theSourceCRS = this.getSourceCRS();
            strategy.appendField(locator, this, "sourceCRS", buffer, theSourceCRS);
        }
        {
            CRSPropertyType theTargetCRS;
            theTargetCRS = this.getTargetCRS();
            strategy.appendField(locator, this, "targetCRS", buffer, theTargetCRS);
        }
        return buffer;
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof AbstractCoordinateOperationType)) {
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
            if (!strategy.equals(LocatorUtils.property(thisLocator, "domainOfValidity", lhsDomainOfValidity), LocatorUtils.property(thatLocator, "domainOfValidity", rhsDomainOfValidity), lhsDomainOfValidity, rhsDomainOfValidity)) {
                return false;
            }
        }
        {
            List<String> lhsScope;
            lhsScope = this.getScope();
            List<String> rhsScope;
            rhsScope = that.getScope();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "scope", lhsScope), LocatorUtils.property(thatLocator, "scope", rhsScope), lhsScope, rhsScope)) {
                return false;
            }
        }
        {
            String lhsOperationVersion;
            lhsOperationVersion = this.getOperationVersion();
            String rhsOperationVersion;
            rhsOperationVersion = that.getOperationVersion();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "operationVersion", lhsOperationVersion), LocatorUtils.property(thatLocator, "operationVersion", rhsOperationVersion), lhsOperationVersion, rhsOperationVersion)) {
                return false;
            }
        }
        {
            List<CoordinateOperationAccuracyElement> lhsCoordinateOperationAccuracy;
            lhsCoordinateOperationAccuracy = this.getCoordinateOperationAccuracy();
            List<CoordinateOperationAccuracyElement> rhsCoordinateOperationAccuracy;
            rhsCoordinateOperationAccuracy = that.getCoordinateOperationAccuracy();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "coordinateOperationAccuracy", lhsCoordinateOperationAccuracy), LocatorUtils.property(thatLocator, "coordinateOperationAccuracy", rhsCoordinateOperationAccuracy), lhsCoordinateOperationAccuracy, rhsCoordinateOperationAccuracy)) {
                return false;
            }
        }
        {
            CRSPropertyType lhsSourceCRS;
            lhsSourceCRS = this.getSourceCRS();
            CRSPropertyType rhsSourceCRS;
            rhsSourceCRS = that.getSourceCRS();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "sourceCRS", lhsSourceCRS), LocatorUtils.property(thatLocator, "sourceCRS", rhsSourceCRS), lhsSourceCRS, rhsSourceCRS)) {
                return false;
            }
        }
        {
            CRSPropertyType lhsTargetCRS;
            lhsTargetCRS = this.getTargetCRS();
            CRSPropertyType rhsTargetCRS;
            rhsTargetCRS = that.getTargetCRS();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "targetCRS", lhsTargetCRS), LocatorUtils.property(thatLocator, "targetCRS", rhsTargetCRS), lhsTargetCRS, rhsTargetCRS)) {
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
            DomainOfValidityElement theDomainOfValidity;
            theDomainOfValidity = this.getDomainOfValidity();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "domainOfValidity", theDomainOfValidity), currentHashCode, theDomainOfValidity);
        }
        {
            List<String> theScope;
            theScope = this.getScope();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "scope", theScope), currentHashCode, theScope);
        }
        {
            String theOperationVersion;
            theOperationVersion = this.getOperationVersion();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "operationVersion", theOperationVersion), currentHashCode, theOperationVersion);
        }
        {
            List<CoordinateOperationAccuracyElement> theCoordinateOperationAccuracy;
            theCoordinateOperationAccuracy = this.getCoordinateOperationAccuracy();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "coordinateOperationAccuracy", theCoordinateOperationAccuracy), currentHashCode, theCoordinateOperationAccuracy);
        }
        {
            CRSPropertyType theSourceCRS;
            theSourceCRS = this.getSourceCRS();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "sourceCRS", theSourceCRS), currentHashCode, theSourceCRS);
        }
        {
            CRSPropertyType theTargetCRS;
            theTargetCRS = this.getTargetCRS();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "targetCRS", theTargetCRS), currentHashCode, theTargetCRS);
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
        if (null == target) {
            throw new IllegalArgumentException("Target argument must not be null for abstract copyable classes.");
        }
        super.copyTo(locator, target, strategy);
        if (target instanceof AbstractCoordinateOperationType) {
            final AbstractCoordinateOperationType copy = ((AbstractCoordinateOperationType) target);
            if (this.isSetDomainOfValidity()) {
                DomainOfValidityElement sourceDomainOfValidity;
                sourceDomainOfValidity = this.getDomainOfValidity();
                DomainOfValidityElement copyDomainOfValidity = ((DomainOfValidityElement) strategy.copy(LocatorUtils.property(locator, "domainOfValidity", sourceDomainOfValidity), sourceDomainOfValidity));
                copy.setDomainOfValidity(copyDomainOfValidity);
            } else {
                copy.domainOfValidity = null;
            }
            if (this.isSetScope()) {
                List<String> sourceScope;
                sourceScope = this.getScope();
                @SuppressWarnings("unchecked")
                List<String> copyScope = ((List<String> ) strategy.copy(LocatorUtils.property(locator, "scope", sourceScope), sourceScope));
                copy.unsetScope();
                List<String> uniqueScopel = copy.getScope();
                uniqueScopel.addAll(copyScope);
            } else {
                copy.unsetScope();
            }
            if (this.isSetOperationVersion()) {
                String sourceOperationVersion;
                sourceOperationVersion = this.getOperationVersion();
                String copyOperationVersion = ((String) strategy.copy(LocatorUtils.property(locator, "operationVersion", sourceOperationVersion), sourceOperationVersion));
                copy.setOperationVersion(copyOperationVersion);
            } else {
                copy.operationVersion = null;
            }
            if (this.isSetCoordinateOperationAccuracy()) {
                List<CoordinateOperationAccuracyElement> sourceCoordinateOperationAccuracy;
                sourceCoordinateOperationAccuracy = this.getCoordinateOperationAccuracy();
                @SuppressWarnings("unchecked")
                List<CoordinateOperationAccuracyElement> copyCoordinateOperationAccuracy = ((List<CoordinateOperationAccuracyElement> ) strategy.copy(LocatorUtils.property(locator, "coordinateOperationAccuracy", sourceCoordinateOperationAccuracy), sourceCoordinateOperationAccuracy));
                copy.unsetCoordinateOperationAccuracy();
                List<CoordinateOperationAccuracyElement> uniqueCoordinateOperationAccuracyl = copy.getCoordinateOperationAccuracy();
                uniqueCoordinateOperationAccuracyl.addAll(copyCoordinateOperationAccuracy);
            } else {
                copy.unsetCoordinateOperationAccuracy();
            }
            if (this.isSetSourceCRS()) {
                CRSPropertyType sourceSourceCRS;
                sourceSourceCRS = this.getSourceCRS();
                CRSPropertyType copySourceCRS = ((CRSPropertyType) strategy.copy(LocatorUtils.property(locator, "sourceCRS", sourceSourceCRS), sourceSourceCRS));
                copy.setSourceCRS(copySourceCRS);
            } else {
                copy.sourceCRS = null;
            }
            if (this.isSetTargetCRS()) {
                CRSPropertyType sourceTargetCRS;
                sourceTargetCRS = this.getTargetCRS();
                CRSPropertyType copyTargetCRS = ((CRSPropertyType) strategy.copy(LocatorUtils.property(locator, "targetCRS", sourceTargetCRS), sourceTargetCRS));
                copy.setTargetCRS(copyTargetCRS);
            } else {
                copy.targetCRS = null;
            }
        }
        return target;
    }

    public void mergeFrom(Object left, Object right) {
        final MergeStrategy strategy = JAXBMergeStrategy.INSTANCE;
        mergeFrom(null, null, left, right, strategy);
    }

    public void mergeFrom(ObjectLocator leftLocator, ObjectLocator rightLocator, Object left, Object right, MergeStrategy strategy) {
        super.mergeFrom(leftLocator, rightLocator, left, right, strategy);
        if (right instanceof AbstractCoordinateOperationType) {
            final AbstractCoordinateOperationType target = this;
            final AbstractCoordinateOperationType leftObject = ((AbstractCoordinateOperationType) left);
            final AbstractCoordinateOperationType rightObject = ((AbstractCoordinateOperationType) right);
            {
                DomainOfValidityElement lhsDomainOfValidity;
                lhsDomainOfValidity = leftObject.getDomainOfValidity();
                DomainOfValidityElement rhsDomainOfValidity;
                rhsDomainOfValidity = rightObject.getDomainOfValidity();
                target.setDomainOfValidity(((DomainOfValidityElement) strategy.merge(LocatorUtils.property(leftLocator, "domainOfValidity", lhsDomainOfValidity), LocatorUtils.property(rightLocator, "domainOfValidity", rhsDomainOfValidity), lhsDomainOfValidity, rhsDomainOfValidity)));
            }
            {
                List<String> lhsScope;
                lhsScope = leftObject.getScope();
                List<String> rhsScope;
                rhsScope = rightObject.getScope();
                target.unsetScope();
                List<String> uniqueScopel = target.getScope();
                uniqueScopel.addAll(((List<String> ) strategy.merge(LocatorUtils.property(leftLocator, "scope", lhsScope), LocatorUtils.property(rightLocator, "scope", rhsScope), lhsScope, rhsScope)));
            }
            {
                String lhsOperationVersion;
                lhsOperationVersion = leftObject.getOperationVersion();
                String rhsOperationVersion;
                rhsOperationVersion = rightObject.getOperationVersion();
                target.setOperationVersion(((String) strategy.merge(LocatorUtils.property(leftLocator, "operationVersion", lhsOperationVersion), LocatorUtils.property(rightLocator, "operationVersion", rhsOperationVersion), lhsOperationVersion, rhsOperationVersion)));
            }
            {
                List<CoordinateOperationAccuracyElement> lhsCoordinateOperationAccuracy;
                lhsCoordinateOperationAccuracy = leftObject.getCoordinateOperationAccuracy();
                List<CoordinateOperationAccuracyElement> rhsCoordinateOperationAccuracy;
                rhsCoordinateOperationAccuracy = rightObject.getCoordinateOperationAccuracy();
                target.unsetCoordinateOperationAccuracy();
                List<CoordinateOperationAccuracyElement> uniqueCoordinateOperationAccuracyl = target.getCoordinateOperationAccuracy();
                uniqueCoordinateOperationAccuracyl.addAll(((List<CoordinateOperationAccuracyElement> ) strategy.merge(LocatorUtils.property(leftLocator, "coordinateOperationAccuracy", lhsCoordinateOperationAccuracy), LocatorUtils.property(rightLocator, "coordinateOperationAccuracy", rhsCoordinateOperationAccuracy), lhsCoordinateOperationAccuracy, rhsCoordinateOperationAccuracy)));
            }
            {
                CRSPropertyType lhsSourceCRS;
                lhsSourceCRS = leftObject.getSourceCRS();
                CRSPropertyType rhsSourceCRS;
                rhsSourceCRS = rightObject.getSourceCRS();
                target.setSourceCRS(((CRSPropertyType) strategy.merge(LocatorUtils.property(leftLocator, "sourceCRS", lhsSourceCRS), LocatorUtils.property(rightLocator, "sourceCRS", rhsSourceCRS), lhsSourceCRS, rhsSourceCRS)));
            }
            {
                CRSPropertyType lhsTargetCRS;
                lhsTargetCRS = leftObject.getTargetCRS();
                CRSPropertyType rhsTargetCRS;
                rhsTargetCRS = rightObject.getTargetCRS();
                target.setTargetCRS(((CRSPropertyType) strategy.merge(LocatorUtils.property(leftLocator, "targetCRS", lhsTargetCRS), LocatorUtils.property(rightLocator, "targetCRS", rhsTargetCRS), lhsTargetCRS, rhsTargetCRS)));
            }
        }
    }

    public void setScope(List<String> value) {
        List<String> draftl = this.getScope();
        draftl.addAll(value);
    }

    public void setCoordinateOperationAccuracy(List<CoordinateOperationAccuracyElement> value) {
        List<CoordinateOperationAccuracyElement> draftl = this.getCoordinateOperationAccuracy();
        draftl.addAll(value);
    }

    public AbstractCoordinateOperationType withDomainOfValidity(DomainOfValidityElement value) {
        setDomainOfValidity(value);
        return this;
    }

    public AbstractCoordinateOperationType withScope(String... values) {
        if (values!= null) {
            for (String value: values) {
                getScope().add(value);
            }
        }
        return this;
    }

    public AbstractCoordinateOperationType withScope(Collection<String> values) {
        if (values!= null) {
            getScope().addAll(values);
        }
        return this;
    }

    public AbstractCoordinateOperationType withOperationVersion(String value) {
        setOperationVersion(value);
        return this;
    }

    public AbstractCoordinateOperationType withCoordinateOperationAccuracy(CoordinateOperationAccuracyElement... values) {
        if (values!= null) {
            for (CoordinateOperationAccuracyElement value: values) {
                getCoordinateOperationAccuracy().add(value);
            }
        }
        return this;
    }

    public AbstractCoordinateOperationType withCoordinateOperationAccuracy(Collection<CoordinateOperationAccuracyElement> values) {
        if (values!= null) {
            getCoordinateOperationAccuracy().addAll(values);
        }
        return this;
    }

    public AbstractCoordinateOperationType withSourceCRS(CRSPropertyType value) {
        setSourceCRS(value);
        return this;
    }

    public AbstractCoordinateOperationType withTargetCRS(CRSPropertyType value) {
        setTargetCRS(value);
        return this;
    }

    public AbstractCoordinateOperationType withScope(List<String> value) {
        setScope(value);
        return this;
    }

    public AbstractCoordinateOperationType withCoordinateOperationAccuracy(List<CoordinateOperationAccuracyElement> value) {
        setCoordinateOperationAccuracy(value);
        return this;
    }

    @Override
    public AbstractCoordinateOperationType withRemarks(String value) {
        setRemarks(value);
        return this;
    }

    @Override
    public AbstractCoordinateOperationType withMetaDataProperty(MetaDataPropertyType... values) {
        if (values!= null) {
            for (MetaDataPropertyType value: values) {
                getMetaDataProperty().add(value);
            }
        }
        return this;
    }

    @Override
    public AbstractCoordinateOperationType withMetaDataProperty(Collection<MetaDataPropertyType> values) {
        if (values!= null) {
            getMetaDataProperty().addAll(values);
        }
        return this;
    }

    @Override
    public AbstractCoordinateOperationType withDescription(StringOrRefType value) {
        setDescription(value);
        return this;
    }

    @Override
    public AbstractCoordinateOperationType withDescriptionReference(ReferenceType value) {
        setDescriptionReference(value);
        return this;
    }

    @Override
    public AbstractCoordinateOperationType withIdentifier(CodeWithAuthorityType value) {
        setIdentifier(value);
        return this;
    }

    @Override
    public AbstractCoordinateOperationType withName(CodeType... values) {
        if (values!= null) {
            for (CodeType value: values) {
                getName().add(value);
            }
        }
        return this;
    }

    @Override
    public AbstractCoordinateOperationType withName(Collection<CodeType> values) {
        if (values!= null) {
            getName().addAll(values);
        }
        return this;
    }

    @Override
    public AbstractCoordinateOperationType withId(String value) {
        setId(value);
        return this;
    }

    @Override
    public AbstractCoordinateOperationType withMetaDataProperty(List<MetaDataPropertyType> value) {
        setMetaDataProperty(value);
        return this;
    }

    @Override
    public AbstractCoordinateOperationType withName(List<CodeType> value) {
        setName(value);
        return this;
    }

}
