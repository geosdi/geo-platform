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
import javax.xml.datatype.XMLGregorianCalendar;
import jakarta.xml.bind.JAXBElement;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlElementRef;
import jakarta.xml.bind.annotation.XmlSchemaType;
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
 * <p>Classe Java per AbstractDatumType complex type.</p>
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.</p>
 * 
 * <pre>{@code
 * <complexType name="AbstractDatumType">
 *   <complexContent>
 *     <extension base="{http://www.opengis.net/gml}IdentifiedObjectType">
 *       <sequence>
 *         <element ref="{http://www.opengis.net/gml}domainOfValidity" minOccurs="0"/>
 *         <element ref="{http://www.opengis.net/gml}scope" maxOccurs="unbounded"/>
 *         <element ref="{http://www.opengis.net/gml}anchorDefinition" minOccurs="0"/>
 *         <element ref="{http://www.opengis.net/gml}realizationEpoch" minOccurs="0"/>
 *       </sequence>
 *     </extension>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AbstractDatumType", propOrder = {
    "domainOfValidity",
    "scope",
    "anchorDefinition",
    "realizationEpoch"
})
@XmlSeeAlso({
    GeodeticDatumType.class,
    EngineeringDatumType.class,
    ImageDatumType.class,
    VerticalDatumType.class,
    TemporalDatumBaseType.class
})
public abstract class AbstractDatumType
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
     * gml:anchorDefinition is a description, possibly including coordinates, of the definition used to anchor the datum to the Earth. Also known as the "origin", especially for engineering and image datums. The codeSpace attribute may be used to reference a source of more detailed on this point or surface, or on a set of such descriptions.
     * -	For a geodetic datum, this point is also known as the fundamental point, which is traditionally the point where the relationship between geoid and ellipsoid is defined. In some cases, the "fundamental point" may consist of a number of points. In those cases, the parameters defining the geoid/ellipsoid relationship have been averaged for these points, and the averages adopted as the datum definition.
     * -	For an engineering datum, the anchor definition may be a physical point, or it may be a point with defined coordinates in another CRS.may
     * -	For an image datum, the anchor definition is usually either the centre of the image or the corner of the image.
     * -	For a temporal datum, this attribute is not defined. Instead of the anchor definition, a temporal datum carries a separate time origin of type DateTime.
     * 
     */
    @XmlElementRef(name = "anchorDefinition", namespace = "http://www.opengis.net/gml", type = JAXBElement.class, required = false)
    protected JAXBElement<CodeType> anchorDefinition;
    /**
     * gml:realizationEpoch is the time after which this datum definition is valid. See ISO 19111 Table 32 for details.
     * 
     */
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar realizationEpoch;

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
     * gml:anchorDefinition is a description, possibly including coordinates, of the definition used to anchor the datum to the Earth. Also known as the "origin", especially for engineering and image datums. The codeSpace attribute may be used to reference a source of more detailed on this point or surface, or on a set of such descriptions.
     * -	For a geodetic datum, this point is also known as the fundamental point, which is traditionally the point where the relationship between geoid and ellipsoid is defined. In some cases, the "fundamental point" may consist of a number of points. In those cases, the parameters defining the geoid/ellipsoid relationship have been averaged for these points, and the averages adopted as the datum definition.
     * -	For an engineering datum, the anchor definition may be a physical point, or it may be a point with defined coordinates in another CRS.may
     * -	For an image datum, the anchor definition is usually either the centre of the image or the corner of the image.
     * -	For a temporal datum, this attribute is not defined. Instead of the anchor definition, a temporal datum carries a separate time origin of type DateTime.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link CodeType }{@code >}
     *     {@link JAXBElement }{@code <}{@link CodeType }{@code >}
     *     
     */
    public JAXBElement<CodeType> getAnchorDefinition() {
        return anchorDefinition;
    }

    /**
     * Imposta il valore della proprietà anchorDefinition.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link CodeType }{@code >}
     *     {@link JAXBElement }{@code <}{@link CodeType }{@code >}
     *     
     * @see #getAnchorDefinition()
     */
    public void setAnchorDefinition(JAXBElement<CodeType> value) {
        this.anchorDefinition = value;
    }

    public boolean isSetAnchorDefinition() {
        return (this.anchorDefinition!= null);
    }

    /**
     * gml:realizationEpoch is the time after which this datum definition is valid. See ISO 19111 Table 32 for details.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getRealizationEpoch() {
        return realizationEpoch;
    }

    /**
     * Imposta il valore della proprietà realizationEpoch.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     * @see #getRealizationEpoch()
     */
    public void setRealizationEpoch(XMLGregorianCalendar value) {
        this.realizationEpoch = value;
    }

    public boolean isSetRealizationEpoch() {
        return (this.realizationEpoch!= null);
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
            JAXBElement<CodeType> theAnchorDefinition;
            theAnchorDefinition = this.getAnchorDefinition();
            strategy.appendField(locator, this, "anchorDefinition", buffer, theAnchorDefinition, this.isSetAnchorDefinition());
        }
        {
            XMLGregorianCalendar theRealizationEpoch;
            theRealizationEpoch = this.getRealizationEpoch();
            strategy.appendField(locator, this, "realizationEpoch", buffer, theRealizationEpoch, this.isSetRealizationEpoch());
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
        final AbstractDatumType that = ((AbstractDatumType) object);
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
            JAXBElement<CodeType> lhsAnchorDefinition;
            lhsAnchorDefinition = this.getAnchorDefinition();
            JAXBElement<CodeType> rhsAnchorDefinition;
            rhsAnchorDefinition = that.getAnchorDefinition();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "anchorDefinition", lhsAnchorDefinition), LocatorUtils.property(thatLocator, "anchorDefinition", rhsAnchorDefinition), lhsAnchorDefinition, rhsAnchorDefinition, this.isSetAnchorDefinition(), that.isSetAnchorDefinition())) {
                return false;
            }
        }
        {
            XMLGregorianCalendar lhsRealizationEpoch;
            lhsRealizationEpoch = this.getRealizationEpoch();
            XMLGregorianCalendar rhsRealizationEpoch;
            rhsRealizationEpoch = that.getRealizationEpoch();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "realizationEpoch", lhsRealizationEpoch), LocatorUtils.property(thatLocator, "realizationEpoch", rhsRealizationEpoch), lhsRealizationEpoch, rhsRealizationEpoch, this.isSetRealizationEpoch(), that.isSetRealizationEpoch())) {
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
            JAXBElement<CodeType> theAnchorDefinition;
            theAnchorDefinition = this.getAnchorDefinition();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "anchorDefinition", theAnchorDefinition), currentHashCode, theAnchorDefinition, this.isSetAnchorDefinition());
        }
        {
            XMLGregorianCalendar theRealizationEpoch;
            theRealizationEpoch = this.getRealizationEpoch();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "realizationEpoch", theRealizationEpoch), currentHashCode, theRealizationEpoch, this.isSetRealizationEpoch());
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
        if (target instanceof AbstractDatumType) {
            final AbstractDatumType copy = ((AbstractDatumType) target);
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
                java.lang.Boolean anchorDefinitionShouldBeCopiedAndSet = strategy.shouldBeCopiedAndSet(locator, this.isSetAnchorDefinition());
                if (anchorDefinitionShouldBeCopiedAndSet == java.lang.Boolean.TRUE) {
                    JAXBElement<CodeType> sourceAnchorDefinition;
                    sourceAnchorDefinition = this.getAnchorDefinition();
                    @SuppressWarnings("unchecked")
                    JAXBElement<CodeType> copyAnchorDefinition = ((JAXBElement<CodeType> ) strategy.copy(LocatorUtils.property(locator, "anchorDefinition", sourceAnchorDefinition), sourceAnchorDefinition, this.isSetAnchorDefinition()));
                    copy.setAnchorDefinition(copyAnchorDefinition);
                } else {
                    if (anchorDefinitionShouldBeCopiedAndSet == java.lang.Boolean.FALSE) {
                        copy.anchorDefinition = null;
                    }
                }
            }
            {
                java.lang.Boolean realizationEpochShouldBeCopiedAndSet = strategy.shouldBeCopiedAndSet(locator, this.isSetRealizationEpoch());
                if (realizationEpochShouldBeCopiedAndSet == java.lang.Boolean.TRUE) {
                    XMLGregorianCalendar sourceRealizationEpoch;
                    sourceRealizationEpoch = this.getRealizationEpoch();
                    XMLGregorianCalendar copyRealizationEpoch = ((XMLGregorianCalendar) strategy.copy(LocatorUtils.property(locator, "realizationEpoch", sourceRealizationEpoch), sourceRealizationEpoch, this.isSetRealizationEpoch()));
                    copy.setRealizationEpoch(copyRealizationEpoch);
                } else {
                    if (realizationEpochShouldBeCopiedAndSet == java.lang.Boolean.FALSE) {
                        copy.realizationEpoch = null;
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
        if (right instanceof AbstractDatumType) {
            final AbstractDatumType target = this;
            final AbstractDatumType leftObject = ((AbstractDatumType) left);
            final AbstractDatumType rightObject = ((AbstractDatumType) right);
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
                java.lang.Boolean anchorDefinitionShouldBeMergedAndSet = strategy.shouldBeMergedAndSet(leftLocator, rightLocator, leftObject.isSetAnchorDefinition(), rightObject.isSetAnchorDefinition());
                if (anchorDefinitionShouldBeMergedAndSet == java.lang.Boolean.TRUE) {
                    JAXBElement<CodeType> lhsAnchorDefinition;
                    lhsAnchorDefinition = leftObject.getAnchorDefinition();
                    JAXBElement<CodeType> rhsAnchorDefinition;
                    rhsAnchorDefinition = rightObject.getAnchorDefinition();
                    JAXBElement<CodeType> mergedAnchorDefinition = ((JAXBElement<CodeType> ) strategy.merge(LocatorUtils.property(leftLocator, "anchorDefinition", lhsAnchorDefinition), LocatorUtils.property(rightLocator, "anchorDefinition", rhsAnchorDefinition), lhsAnchorDefinition, rhsAnchorDefinition, leftObject.isSetAnchorDefinition(), rightObject.isSetAnchorDefinition()));
                    target.setAnchorDefinition(mergedAnchorDefinition);
                } else {
                    if (anchorDefinitionShouldBeMergedAndSet == java.lang.Boolean.FALSE) {
                        target.anchorDefinition = null;
                    }
                }
            }
            {
                java.lang.Boolean realizationEpochShouldBeMergedAndSet = strategy.shouldBeMergedAndSet(leftLocator, rightLocator, leftObject.isSetRealizationEpoch(), rightObject.isSetRealizationEpoch());
                if (realizationEpochShouldBeMergedAndSet == java.lang.Boolean.TRUE) {
                    XMLGregorianCalendar lhsRealizationEpoch;
                    lhsRealizationEpoch = leftObject.getRealizationEpoch();
                    XMLGregorianCalendar rhsRealizationEpoch;
                    rhsRealizationEpoch = rightObject.getRealizationEpoch();
                    XMLGregorianCalendar mergedRealizationEpoch = ((XMLGregorianCalendar) strategy.merge(LocatorUtils.property(leftLocator, "realizationEpoch", lhsRealizationEpoch), LocatorUtils.property(rightLocator, "realizationEpoch", rhsRealizationEpoch), lhsRealizationEpoch, rhsRealizationEpoch, leftObject.isSetRealizationEpoch(), rightObject.isSetRealizationEpoch()));
                    target.setRealizationEpoch(mergedRealizationEpoch);
                } else {
                    if (realizationEpochShouldBeMergedAndSet == java.lang.Boolean.FALSE) {
                        target.realizationEpoch = null;
                    }
                }
            }
        }
    }

}
