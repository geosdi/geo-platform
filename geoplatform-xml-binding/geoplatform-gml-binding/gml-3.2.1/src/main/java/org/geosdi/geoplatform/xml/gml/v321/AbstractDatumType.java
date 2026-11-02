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
import javax.xml.datatype.XMLGregorianCalendar;
import jakarta.xml.bind.JAXBElement;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlElementRef;
import jakarta.xml.bind.annotation.XmlSchemaType;
import jakarta.xml.bind.annotation.XmlSeeAlso;
import jakarta.xml.bind.annotation.XmlType;
import org.jvnet.jaxb.lang.JAXBToStringStrategy;
import org.jvnet.jaxb.lang.ToString;
import org.jvnet.jaxb.lang.ToStringStrategy;
import org.jvnet.jaxb.locator.ObjectLocator;


/**
 * <p>Classe Java per AbstractDatumType complex type.</p>
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.</p>
 * 
 * <pre>{@code
 * <complexType name="AbstractDatumType">
 *   <complexContent>
 *     <extension base="{http://www.opengis.net/gml/3.2}IdentifiedObjectType">
 *       <sequence>
 *         <element ref="{http://www.opengis.net/gml/3.2}domainOfValidity" minOccurs="0"/>
 *         <element ref="{http://www.opengis.net/gml/3.2}scope" maxOccurs="unbounded"/>
 *         <element ref="{http://www.opengis.net/gml/3.2}anchorDefinition" minOccurs="0"/>
 *         <element ref="{http://www.opengis.net/gml/3.2}realizationEpoch" minOccurs="0"/>
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
    implements ToString
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
    @XmlElementRef(name = "anchorDefinition", namespace = "http://www.opengis.net/gml/3.2", type = JAXBElement.class, required = false)
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

}
