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
 * <p>Classe Java per AbstractCRSType complex type.</p>
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.</p>
 * 
 * <pre>{@code
 * <complexType name="AbstractCRSType">
 *   <complexContent>
 *     <extension base="{http://www.opengis.net/gml}IdentifiedObjectType">
 *       <sequence>
 *         <element ref="{http://www.opengis.net/gml}domainOfValidity" maxOccurs="unbounded" minOccurs="0"/>
 *         <element ref="{http://www.opengis.net/gml}scope" maxOccurs="unbounded"/>
 *       </sequence>
 *     </extension>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AbstractCRSType", propOrder = {
    "domainOfValidity",
    "scope"
})
@XmlSeeAlso({
    CompoundCRSType.class,
    GeodeticCRSType.class,
    VerticalCRSType.class,
    AbstractGeneralDerivedCRSType.class,
    EngineeringCRSType.class,
    ImageCRSType.class,
    TemporalCRSType.class,
    GeographicCRSType.class,
    GeocentricCRSType.class
})
public abstract class AbstractCRSType
    extends IdentifiedObjectType
    implements Cloneable, CopyTo, Equals, HashCode, MergeFrom, ToString
{

    /**
     * The gml:domainOfValidity property implements an association role to an EX_Extent object as encoded in ISO/TS 19139, either referencing or containing the definition of that extent.
     * 
     */
    protected List<DomainOfValidityElement> domainOfValidity;
    /**
     * The gml:scope property provides a description of the usage, or limitations of usage, for which this CRS-related object is valid. If unknown, enter "not known".
     * 
     */
    @XmlElement(required = true)
    protected List<String> scope;

    /**
     * The gml:domainOfValidity property implements an association role to an EX_Extent object as encoded in ISO/TS 19139, either referencing or containing the definition of that extent.
     * 
     * Gets the value of the domainOfValidity property.
     * 
     * <p>This accessor method returns a reference to the live list,
     * not a snapshot. Therefore, any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a {@code set} method for the domainOfValidity property.</p>
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * </p>
     * <pre>
     * getDomainOfValidity().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DomainOfValidityElement }
     * </p>
     * 
     * 
     * @return
     *     The value of the domainOfValidity property.
     */
    public List<DomainOfValidityElement> getDomainOfValidity() {
        if (domainOfValidity == null) {
            domainOfValidity = new ArrayList<>();
        }
        return this.domainOfValidity;
    }

    public boolean isSetDomainOfValidity() {
        return ((this.domainOfValidity!= null)&&(!this.domainOfValidity.isEmpty()));
    }

    public void unsetDomainOfValidity() {
        this.domainOfValidity = null;
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
            List<DomainOfValidityElement> theDomainOfValidity;
            theDomainOfValidity = (this.isSetDomainOfValidity()?this.getDomainOfValidity():null);
            strategy.appendField(locator, this, "domainOfValidity", buffer, theDomainOfValidity, this.isSetDomainOfValidity());
        }
        {
            List<String> theScope;
            theScope = (this.isSetScope()?this.getScope():null);
            strategy.appendField(locator, this, "scope", buffer, theScope, this.isSetScope());
        }
        return buffer;
    }

    public void setDomainOfValidity(List<DomainOfValidityElement> value) {
        this.domainOfValidity = null;
        if (value!= null) {
            List<DomainOfValidityElement> draftl = this.getDomainOfValidity();
            draftl.addAll(value);
        }
    }

    public void setScope(List<String> value) {
        this.scope = null;
        if (value!= null) {
            List<String> draftl = this.getScope();
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
        final AbstractCRSType that = ((AbstractCRSType) object);
        {
            List<DomainOfValidityElement> lhsDomainOfValidity;
            lhsDomainOfValidity = (this.isSetDomainOfValidity()?this.getDomainOfValidity():null);
            List<DomainOfValidityElement> rhsDomainOfValidity;
            rhsDomainOfValidity = (that.isSetDomainOfValidity()?that.getDomainOfValidity():null);
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
            List<DomainOfValidityElement> theDomainOfValidity;
            theDomainOfValidity = (this.isSetDomainOfValidity()?this.getDomainOfValidity():null);
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "domainOfValidity", theDomainOfValidity), currentHashCode, theDomainOfValidity, this.isSetDomainOfValidity());
        }
        {
            List<String> theScope;
            theScope = (this.isSetScope()?this.getScope():null);
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "scope", theScope), currentHashCode, theScope, this.isSetScope());
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
        if (target instanceof AbstractCRSType) {
            final AbstractCRSType copy = ((AbstractCRSType) target);
            {
                java.lang.Boolean domainOfValidityShouldBeCopiedAndSet = strategy.shouldBeCopiedAndSet(locator, this.isSetDomainOfValidity());
                if (domainOfValidityShouldBeCopiedAndSet == java.lang.Boolean.TRUE) {
                    List<DomainOfValidityElement> sourceDomainOfValidity;
                    sourceDomainOfValidity = (this.isSetDomainOfValidity()?this.getDomainOfValidity():null);
                    @SuppressWarnings("unchecked")
                    List<DomainOfValidityElement> copyDomainOfValidity = ((List<DomainOfValidityElement> ) strategy.copy(LocatorUtils.property(locator, "domainOfValidity", sourceDomainOfValidity), sourceDomainOfValidity, this.isSetDomainOfValidity()));
                    copy.setDomainOfValidity(copyDomainOfValidity);
                } else {
                    if (domainOfValidityShouldBeCopiedAndSet == java.lang.Boolean.FALSE) {
                        copy.unsetDomainOfValidity();
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
        if (right instanceof AbstractCRSType) {
            final AbstractCRSType target = this;
            final AbstractCRSType leftObject = ((AbstractCRSType) left);
            final AbstractCRSType rightObject = ((AbstractCRSType) right);
            {
                java.lang.Boolean domainOfValidityShouldBeMergedAndSet = strategy.shouldBeMergedAndSet(leftLocator, rightLocator, leftObject.isSetDomainOfValidity(), rightObject.isSetDomainOfValidity());
                if (domainOfValidityShouldBeMergedAndSet == java.lang.Boolean.TRUE) {
                    List<DomainOfValidityElement> lhsDomainOfValidity;
                    lhsDomainOfValidity = (leftObject.isSetDomainOfValidity()?leftObject.getDomainOfValidity():null);
                    List<DomainOfValidityElement> rhsDomainOfValidity;
                    rhsDomainOfValidity = (rightObject.isSetDomainOfValidity()?rightObject.getDomainOfValidity():null);
                    List<DomainOfValidityElement> mergedDomainOfValidity = ((List<DomainOfValidityElement> ) strategy.merge(LocatorUtils.property(leftLocator, "domainOfValidity", lhsDomainOfValidity), LocatorUtils.property(rightLocator, "domainOfValidity", rhsDomainOfValidity), lhsDomainOfValidity, rhsDomainOfValidity, leftObject.isSetDomainOfValidity(), rightObject.isSetDomainOfValidity()));
                    target.setDomainOfValidity(mergedDomainOfValidity);
                } else {
                    if (domainOfValidityShouldBeMergedAndSet == java.lang.Boolean.FALSE) {
                        target.unsetDomainOfValidity();
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
        }
    }

}
