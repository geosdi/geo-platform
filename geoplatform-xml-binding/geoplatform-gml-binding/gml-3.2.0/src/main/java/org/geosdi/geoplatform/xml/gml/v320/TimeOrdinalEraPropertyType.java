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
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlSchemaType;
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
 * gml:TimeOrdinalEraPropertyType provides for associating a gml:TimeOrdinalEra with an object.
 * 
 * <p>Classe Java per TimeOrdinalEraPropertyType complex type.</p>
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.</p>
 * 
 * <pre>{@code
 * <complexType name="TimeOrdinalEraPropertyType">
 *   <complexContent>
 *     <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       <sequence minOccurs="0">
 *         <element ref="{http://www.opengis.net/gml}TimeOrdinalEra"/>
 *       </sequence>
 *       <attGroup ref="{http://www.opengis.net/gml}AssociationAttributeGroup"/>
 *       <attGroup ref="{http://www.opengis.net/gml}OwnershipAttributeGroup"/>
 *     </restriction>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TimeOrdinalEraPropertyType", propOrder = {
    "timeOrdinalEra"
})
public class TimeOrdinalEraPropertyType implements Cloneable, CopyTo, Equals, HashCode, MergeFrom, ToString
{

    /**
     * Its content model follows the pattern of gml:TimeEdge, inheriting standard properties from gml:DefinitionType, and adding gml:start, gml:end and gml:extent properties, a set of gml:member properties which indicate ordered gml:TimeOrdinalEra elements, and a gml:group property which points to the parent era.
     * The recursive inclusion of gml:TimeOrdinalEra elements allow the construction of an arbitrary depth hierarchical ordinal reference schema, such that an ordinal era at a given level of the hierarchy includes a sequence of shorter, coterminous ordinal eras.
     * 
     */
    @XmlElement(name = "TimeOrdinalEra")
    protected TimeOrdinalEraType timeOrdinalEra;
    @XmlAttribute(name = "nilReason")
    protected List<String> nilReason;
    @XmlAttribute(name = "remoteSchema", namespace = "http://www.opengis.net/gml")
    @XmlSchemaType(name = "anyURI")
    protected String remoteSchema;
    @XmlAttribute(name = "type", namespace = "http://www.w3.org/1999/xlink")
    public static final String TYPE = "simple";
    @XmlAttribute(name = "href", namespace = "http://www.w3.org/1999/xlink")
    @XmlSchemaType(name = "anyURI")
    protected String href;
    @XmlAttribute(name = "role", namespace = "http://www.w3.org/1999/xlink")
    @XmlSchemaType(name = "anyURI")
    protected String role;
    @XmlAttribute(name = "arcrole", namespace = "http://www.w3.org/1999/xlink")
    @XmlSchemaType(name = "anyURI")
    protected String arcrole;
    @XmlAttribute(name = "title", namespace = "http://www.w3.org/1999/xlink")
    protected String title;
    /**
     * The 'show' attribute is used to communicate the desired presentation 
     *         of the ending resource on traversal from the starting resource; it's 
     *         value should be treated as follows: 
     *         new - load ending resource in a new window, frame, pane, or other 
     *               presentation context
     *         replace - load the resource in the same window, frame, pane, or 
     *                   other presentation context
     *         embed - load ending resource in place of the presentation of the 
     *                 starting resource
     *         other - behavior is unconstrained; examine other markup in the 
     *                 link for hints 
     *         none - behavior is unconstrained
     * 
     */
    @XmlAttribute(name = "show", namespace = "http://www.w3.org/1999/xlink")
    protected String show;
    /**
     * The 'actuate' attribute is used to communicate the desired timing 
     *         of traversal from the starting resource to the ending resource; 
     *         it's value should be treated as follows:
     *         onLoad - traverse to the ending resource immediately on loading 
     *                  the starting resource 
     *         onRequest - traverse from the starting resource to the ending 
     *                     resource only on a post-loading event triggered for 
     *                     this purpose 
     *         other - behavior is unconstrained; examine other markup in link 
     *                 for hints 
     *         none - behavior is unconstrained
     * 
     */
    @XmlAttribute(name = "actuate", namespace = "http://www.w3.org/1999/xlink")
    protected String actuate;
    @XmlAttribute(name = "owns")
    protected java.lang.Boolean owns;

    /**
     * Its content model follows the pattern of gml:TimeEdge, inheriting standard properties from gml:DefinitionType, and adding gml:start, gml:end and gml:extent properties, a set of gml:member properties which indicate ordered gml:TimeOrdinalEra elements, and a gml:group property which points to the parent era.
     * The recursive inclusion of gml:TimeOrdinalEra elements allow the construction of an arbitrary depth hierarchical ordinal reference schema, such that an ordinal era at a given level of the hierarchy includes a sequence of shorter, coterminous ordinal eras.
     * 
     * @return
     *     possible object is
     *     {@link TimeOrdinalEraType }
     *     
     */
    public TimeOrdinalEraType getTimeOrdinalEra() {
        return timeOrdinalEra;
    }

    /**
     * Imposta il valore della proprietà timeOrdinalEra.
     * 
     * @param value
     *     allowed object is
     *     {@link TimeOrdinalEraType }
     *     
     * @see #getTimeOrdinalEra()
     */
    public void setTimeOrdinalEra(TimeOrdinalEraType value) {
        this.timeOrdinalEra = value;
    }

    public boolean isSetTimeOrdinalEra() {
        return (this.timeOrdinalEra!= null);
    }

    /**
     * Gets the value of the nilReason property.
     * 
     * <p>This accessor method returns a reference to the live list,
     * not a snapshot. Therefore, any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a {@code set} method for the nilReason property.</p>
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * </p>
     * <pre>
     * getNilReason().add(newItem);
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
     *     The value of the nilReason property.
     */
    public List<String> getNilReason() {
        if (nilReason == null) {
            nilReason = new ArrayList<>();
        }
        return this.nilReason;
    }

    public boolean isSetNilReason() {
        return ((this.nilReason!= null)&&(!this.nilReason.isEmpty()));
    }

    public void unsetNilReason() {
        this.nilReason = null;
    }

    /**
     * Recupera il valore della proprietà remoteSchema.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRemoteSchema() {
        return remoteSchema;
    }

    /**
     * Imposta il valore della proprietà remoteSchema.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRemoteSchema(String value) {
        this.remoteSchema = value;
    }

    public boolean isSetRemoteSchema() {
        return (this.remoteSchema!= null);
    }

    /**
     * Recupera il valore della proprietà href.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHref() {
        return href;
    }

    /**
     * Imposta il valore della proprietà href.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHref(String value) {
        this.href = value;
    }

    public boolean isSetHref() {
        return (this.href!= null);
    }

    /**
     * Recupera il valore della proprietà role.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRole() {
        return role;
    }

    /**
     * Imposta il valore della proprietà role.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRole(String value) {
        this.role = value;
    }

    public boolean isSetRole() {
        return (this.role!= null);
    }

    /**
     * Recupera il valore della proprietà arcrole.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getArcrole() {
        return arcrole;
    }

    /**
     * Imposta il valore della proprietà arcrole.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setArcrole(String value) {
        this.arcrole = value;
    }

    public boolean isSetArcrole() {
        return (this.arcrole!= null);
    }

    /**
     * Recupera il valore della proprietà title.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTitle() {
        return title;
    }

    /**
     * Imposta il valore della proprietà title.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTitle(String value) {
        this.title = value;
    }

    public boolean isSetTitle() {
        return (this.title!= null);
    }

    /**
     * The 'show' attribute is used to communicate the desired presentation 
     *         of the ending resource on traversal from the starting resource; it's 
     *         value should be treated as follows: 
     *         new - load ending resource in a new window, frame, pane, or other 
     *               presentation context
     *         replace - load the resource in the same window, frame, pane, or 
     *                   other presentation context
     *         embed - load ending resource in place of the presentation of the 
     *                 starting resource
     *         other - behavior is unconstrained; examine other markup in the 
     *                 link for hints 
     *         none - behavior is unconstrained
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getShow() {
        return show;
    }

    /**
     * Imposta il valore della proprietà show.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     * @see #getShow()
     */
    public void setShow(String value) {
        this.show = value;
    }

    public boolean isSetShow() {
        return (this.show!= null);
    }

    /**
     * The 'actuate' attribute is used to communicate the desired timing 
     *         of traversal from the starting resource to the ending resource; 
     *         it's value should be treated as follows:
     *         onLoad - traverse to the ending resource immediately on loading 
     *                  the starting resource 
     *         onRequest - traverse from the starting resource to the ending 
     *                     resource only on a post-loading event triggered for 
     *                     this purpose 
     *         other - behavior is unconstrained; examine other markup in link 
     *                 for hints 
     *         none - behavior is unconstrained
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getActuate() {
        return actuate;
    }

    /**
     * Imposta il valore della proprietà actuate.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     * @see #getActuate()
     */
    public void setActuate(String value) {
        this.actuate = value;
    }

    public boolean isSetActuate() {
        return (this.actuate!= null);
    }

    /**
     * Recupera il valore della proprietà owns.
     * 
     * @return
     *     possible object is
     *     {@link java.lang.Boolean }
     *     
     */
    public boolean isOwns() {
        if (owns == null) {
            return false;
        } else {
            return owns;
        }
    }

    /**
     * Imposta il valore della proprietà owns.
     * 
     * @param value
     *     allowed object is
     *     {@link java.lang.Boolean }
     *     
     */
    public void setOwns(boolean value) {
        this.owns = value;
    }

    public boolean isSetOwns() {
        return (this.owns!= null);
    }

    public void unsetOwns() {
        this.owns = null;
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
            TimeOrdinalEraType theTimeOrdinalEra;
            theTimeOrdinalEra = this.getTimeOrdinalEra();
            strategy.appendField(locator, this, "timeOrdinalEra", buffer, theTimeOrdinalEra, this.isSetTimeOrdinalEra());
        }
        {
            List<String> theNilReason;
            theNilReason = (this.isSetNilReason()?this.getNilReason():null);
            strategy.appendField(locator, this, "nilReason", buffer, theNilReason, this.isSetNilReason());
        }
        {
            String theRemoteSchema;
            theRemoteSchema = this.getRemoteSchema();
            strategy.appendField(locator, this, "remoteSchema", buffer, theRemoteSchema, this.isSetRemoteSchema());
        }
        {
            String theTYPE;
            theTYPE = TimeOrdinalEraPropertyType.TYPE;
            strategy.appendField(locator, this, "type", buffer, theTYPE, true);
        }
        {
            String theHref;
            theHref = this.getHref();
            strategy.appendField(locator, this, "href", buffer, theHref, this.isSetHref());
        }
        {
            String theRole;
            theRole = this.getRole();
            strategy.appendField(locator, this, "role", buffer, theRole, this.isSetRole());
        }
        {
            String theArcrole;
            theArcrole = this.getArcrole();
            strategy.appendField(locator, this, "arcrole", buffer, theArcrole, this.isSetArcrole());
        }
        {
            String theTitle;
            theTitle = this.getTitle();
            strategy.appendField(locator, this, "title", buffer, theTitle, this.isSetTitle());
        }
        {
            String theShow;
            theShow = this.getShow();
            strategy.appendField(locator, this, "show", buffer, theShow, this.isSetShow());
        }
        {
            String theActuate;
            theActuate = this.getActuate();
            strategy.appendField(locator, this, "actuate", buffer, theActuate, this.isSetActuate());
        }
        {
            boolean theOwns;
            theOwns = (this.isSetOwns()?this.isOwns():false);
            strategy.appendField(locator, this, "owns", buffer, theOwns, this.isSetOwns());
        }
        return buffer;
    }

    public void setNilReason(List<String> value) {
        this.nilReason = null;
        if (value!= null) {
            List<String> draftl = this.getNilReason();
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
        final TimeOrdinalEraPropertyType that = ((TimeOrdinalEraPropertyType) object);
        {
            TimeOrdinalEraType lhsTimeOrdinalEra;
            lhsTimeOrdinalEra = this.getTimeOrdinalEra();
            TimeOrdinalEraType rhsTimeOrdinalEra;
            rhsTimeOrdinalEra = that.getTimeOrdinalEra();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "timeOrdinalEra", lhsTimeOrdinalEra), LocatorUtils.property(thatLocator, "timeOrdinalEra", rhsTimeOrdinalEra), lhsTimeOrdinalEra, rhsTimeOrdinalEra, this.isSetTimeOrdinalEra(), that.isSetTimeOrdinalEra())) {
                return false;
            }
        }
        {
            List<String> lhsNilReason;
            lhsNilReason = (this.isSetNilReason()?this.getNilReason():null);
            List<String> rhsNilReason;
            rhsNilReason = (that.isSetNilReason()?that.getNilReason():null);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "nilReason", lhsNilReason), LocatorUtils.property(thatLocator, "nilReason", rhsNilReason), lhsNilReason, rhsNilReason, this.isSetNilReason(), that.isSetNilReason())) {
                return false;
            }
        }
        {
            String lhsRemoteSchema;
            lhsRemoteSchema = this.getRemoteSchema();
            String rhsRemoteSchema;
            rhsRemoteSchema = that.getRemoteSchema();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "remoteSchema", lhsRemoteSchema), LocatorUtils.property(thatLocator, "remoteSchema", rhsRemoteSchema), lhsRemoteSchema, rhsRemoteSchema, this.isSetRemoteSchema(), that.isSetRemoteSchema())) {
                return false;
            }
        }
        {
            String lhsHref;
            lhsHref = this.getHref();
            String rhsHref;
            rhsHref = that.getHref();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "href", lhsHref), LocatorUtils.property(thatLocator, "href", rhsHref), lhsHref, rhsHref, this.isSetHref(), that.isSetHref())) {
                return false;
            }
        }
        {
            String lhsRole;
            lhsRole = this.getRole();
            String rhsRole;
            rhsRole = that.getRole();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "role", lhsRole), LocatorUtils.property(thatLocator, "role", rhsRole), lhsRole, rhsRole, this.isSetRole(), that.isSetRole())) {
                return false;
            }
        }
        {
            String lhsArcrole;
            lhsArcrole = this.getArcrole();
            String rhsArcrole;
            rhsArcrole = that.getArcrole();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "arcrole", lhsArcrole), LocatorUtils.property(thatLocator, "arcrole", rhsArcrole), lhsArcrole, rhsArcrole, this.isSetArcrole(), that.isSetArcrole())) {
                return false;
            }
        }
        {
            String lhsTitle;
            lhsTitle = this.getTitle();
            String rhsTitle;
            rhsTitle = that.getTitle();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "title", lhsTitle), LocatorUtils.property(thatLocator, "title", rhsTitle), lhsTitle, rhsTitle, this.isSetTitle(), that.isSetTitle())) {
                return false;
            }
        }
        {
            String lhsShow;
            lhsShow = this.getShow();
            String rhsShow;
            rhsShow = that.getShow();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "show", lhsShow), LocatorUtils.property(thatLocator, "show", rhsShow), lhsShow, rhsShow, this.isSetShow(), that.isSetShow())) {
                return false;
            }
        }
        {
            String lhsActuate;
            lhsActuate = this.getActuate();
            String rhsActuate;
            rhsActuate = that.getActuate();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "actuate", lhsActuate), LocatorUtils.property(thatLocator, "actuate", rhsActuate), lhsActuate, rhsActuate, this.isSetActuate(), that.isSetActuate())) {
                return false;
            }
        }
        {
            boolean lhsOwns;
            lhsOwns = (this.isSetOwns()?this.isOwns():false);
            boolean rhsOwns;
            rhsOwns = (that.isSetOwns()?that.isOwns():false);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "owns", lhsOwns), LocatorUtils.property(thatLocator, "owns", rhsOwns), lhsOwns, rhsOwns, this.isSetOwns(), that.isSetOwns())) {
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
            TimeOrdinalEraType theTimeOrdinalEra;
            theTimeOrdinalEra = this.getTimeOrdinalEra();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "timeOrdinalEra", theTimeOrdinalEra), currentHashCode, theTimeOrdinalEra, this.isSetTimeOrdinalEra());
        }
        {
            List<String> theNilReason;
            theNilReason = (this.isSetNilReason()?this.getNilReason():null);
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "nilReason", theNilReason), currentHashCode, theNilReason, this.isSetNilReason());
        }
        {
            String theRemoteSchema;
            theRemoteSchema = this.getRemoteSchema();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "remoteSchema", theRemoteSchema), currentHashCode, theRemoteSchema, this.isSetRemoteSchema());
        }
        {
            String theHref;
            theHref = this.getHref();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "href", theHref), currentHashCode, theHref, this.isSetHref());
        }
        {
            String theRole;
            theRole = this.getRole();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "role", theRole), currentHashCode, theRole, this.isSetRole());
        }
        {
            String theArcrole;
            theArcrole = this.getArcrole();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "arcrole", theArcrole), currentHashCode, theArcrole, this.isSetArcrole());
        }
        {
            String theTitle;
            theTitle = this.getTitle();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "title", theTitle), currentHashCode, theTitle, this.isSetTitle());
        }
        {
            String theShow;
            theShow = this.getShow();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "show", theShow), currentHashCode, theShow, this.isSetShow());
        }
        {
            String theActuate;
            theActuate = this.getActuate();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "actuate", theActuate), currentHashCode, theActuate, this.isSetActuate());
        }
        {
            boolean theOwns;
            theOwns = (this.isSetOwns()?this.isOwns():false);
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "owns", theOwns), currentHashCode, theOwns, this.isSetOwns());
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
        if (draftCopy instanceof TimeOrdinalEraPropertyType) {
            final TimeOrdinalEraPropertyType copy = ((TimeOrdinalEraPropertyType) draftCopy);
            {
                java.lang.Boolean timeOrdinalEraShouldBeCopiedAndSet = strategy.shouldBeCopiedAndSet(locator, this.isSetTimeOrdinalEra());
                if (timeOrdinalEraShouldBeCopiedAndSet == java.lang.Boolean.TRUE) {
                    TimeOrdinalEraType sourceTimeOrdinalEra;
                    sourceTimeOrdinalEra = this.getTimeOrdinalEra();
                    TimeOrdinalEraType copyTimeOrdinalEra = ((TimeOrdinalEraType) strategy.copy(LocatorUtils.property(locator, "timeOrdinalEra", sourceTimeOrdinalEra), sourceTimeOrdinalEra, this.isSetTimeOrdinalEra()));
                    copy.setTimeOrdinalEra(copyTimeOrdinalEra);
                } else {
                    if (timeOrdinalEraShouldBeCopiedAndSet == java.lang.Boolean.FALSE) {
                        copy.timeOrdinalEra = null;
                    }
                }
            }
            {
                java.lang.Boolean nilReasonShouldBeCopiedAndSet = strategy.shouldBeCopiedAndSet(locator, this.isSetNilReason());
                if (nilReasonShouldBeCopiedAndSet == java.lang.Boolean.TRUE) {
                    List<String> sourceNilReason;
                    sourceNilReason = (this.isSetNilReason()?this.getNilReason():null);
                    @SuppressWarnings("unchecked")
                    List<String> copyNilReason = ((List<String> ) strategy.copy(LocatorUtils.property(locator, "nilReason", sourceNilReason), sourceNilReason, this.isSetNilReason()));
                    copy.setNilReason(copyNilReason);
                } else {
                    if (nilReasonShouldBeCopiedAndSet == java.lang.Boolean.FALSE) {
                        copy.unsetNilReason();
                    }
                }
            }
            {
                java.lang.Boolean remoteSchemaShouldBeCopiedAndSet = strategy.shouldBeCopiedAndSet(locator, this.isSetRemoteSchema());
                if (remoteSchemaShouldBeCopiedAndSet == java.lang.Boolean.TRUE) {
                    String sourceRemoteSchema;
                    sourceRemoteSchema = this.getRemoteSchema();
                    String copyRemoteSchema = ((String) strategy.copy(LocatorUtils.property(locator, "remoteSchema", sourceRemoteSchema), sourceRemoteSchema, this.isSetRemoteSchema()));
                    copy.setRemoteSchema(copyRemoteSchema);
                } else {
                    if (remoteSchemaShouldBeCopiedAndSet == java.lang.Boolean.FALSE) {
                        copy.remoteSchema = null;
                    }
                }
            }
            {
                java.lang.Boolean hrefShouldBeCopiedAndSet = strategy.shouldBeCopiedAndSet(locator, this.isSetHref());
                if (hrefShouldBeCopiedAndSet == java.lang.Boolean.TRUE) {
                    String sourceHref;
                    sourceHref = this.getHref();
                    String copyHref = ((String) strategy.copy(LocatorUtils.property(locator, "href", sourceHref), sourceHref, this.isSetHref()));
                    copy.setHref(copyHref);
                } else {
                    if (hrefShouldBeCopiedAndSet == java.lang.Boolean.FALSE) {
                        copy.href = null;
                    }
                }
            }
            {
                java.lang.Boolean roleShouldBeCopiedAndSet = strategy.shouldBeCopiedAndSet(locator, this.isSetRole());
                if (roleShouldBeCopiedAndSet == java.lang.Boolean.TRUE) {
                    String sourceRole;
                    sourceRole = this.getRole();
                    String copyRole = ((String) strategy.copy(LocatorUtils.property(locator, "role", sourceRole), sourceRole, this.isSetRole()));
                    copy.setRole(copyRole);
                } else {
                    if (roleShouldBeCopiedAndSet == java.lang.Boolean.FALSE) {
                        copy.role = null;
                    }
                }
            }
            {
                java.lang.Boolean arcroleShouldBeCopiedAndSet = strategy.shouldBeCopiedAndSet(locator, this.isSetArcrole());
                if (arcroleShouldBeCopiedAndSet == java.lang.Boolean.TRUE) {
                    String sourceArcrole;
                    sourceArcrole = this.getArcrole();
                    String copyArcrole = ((String) strategy.copy(LocatorUtils.property(locator, "arcrole", sourceArcrole), sourceArcrole, this.isSetArcrole()));
                    copy.setArcrole(copyArcrole);
                } else {
                    if (arcroleShouldBeCopiedAndSet == java.lang.Boolean.FALSE) {
                        copy.arcrole = null;
                    }
                }
            }
            {
                java.lang.Boolean titleShouldBeCopiedAndSet = strategy.shouldBeCopiedAndSet(locator, this.isSetTitle());
                if (titleShouldBeCopiedAndSet == java.lang.Boolean.TRUE) {
                    String sourceTitle;
                    sourceTitle = this.getTitle();
                    String copyTitle = ((String) strategy.copy(LocatorUtils.property(locator, "title", sourceTitle), sourceTitle, this.isSetTitle()));
                    copy.setTitle(copyTitle);
                } else {
                    if (titleShouldBeCopiedAndSet == java.lang.Boolean.FALSE) {
                        copy.title = null;
                    }
                }
            }
            {
                java.lang.Boolean showShouldBeCopiedAndSet = strategy.shouldBeCopiedAndSet(locator, this.isSetShow());
                if (showShouldBeCopiedAndSet == java.lang.Boolean.TRUE) {
                    String sourceShow;
                    sourceShow = this.getShow();
                    String copyShow = ((String) strategy.copy(LocatorUtils.property(locator, "show", sourceShow), sourceShow, this.isSetShow()));
                    copy.setShow(copyShow);
                } else {
                    if (showShouldBeCopiedAndSet == java.lang.Boolean.FALSE) {
                        copy.show = null;
                    }
                }
            }
            {
                java.lang.Boolean actuateShouldBeCopiedAndSet = strategy.shouldBeCopiedAndSet(locator, this.isSetActuate());
                if (actuateShouldBeCopiedAndSet == java.lang.Boolean.TRUE) {
                    String sourceActuate;
                    sourceActuate = this.getActuate();
                    String copyActuate = ((String) strategy.copy(LocatorUtils.property(locator, "actuate", sourceActuate), sourceActuate, this.isSetActuate()));
                    copy.setActuate(copyActuate);
                } else {
                    if (actuateShouldBeCopiedAndSet == java.lang.Boolean.FALSE) {
                        copy.actuate = null;
                    }
                }
            }
            {
                java.lang.Boolean ownsShouldBeCopiedAndSet = strategy.shouldBeCopiedAndSet(locator, this.isSetOwns());
                if (ownsShouldBeCopiedAndSet == java.lang.Boolean.TRUE) {
                    boolean sourceOwns;
                    sourceOwns = (this.isSetOwns()?this.isOwns():false);
                    boolean copyOwns = strategy.copy(LocatorUtils.property(locator, "owns", sourceOwns), sourceOwns, this.isSetOwns());
                    copy.setOwns(copyOwns);
                } else {
                    if (ownsShouldBeCopiedAndSet == java.lang.Boolean.FALSE) {
                        copy.unsetOwns();
                    }
                }
            }
        }
        return draftCopy;
    }

    @Override
    public Object createNewInstance() {
        return new TimeOrdinalEraPropertyType();
    }

    @Override
    public void mergeFrom(Object left, Object right) {
        final MergeStrategy strategy = JAXBMergeStrategy.getInstance();
        mergeFrom(null, null, left, right, strategy);
    }

    @Override
    public void mergeFrom(ObjectLocator leftLocator, ObjectLocator rightLocator, Object left, Object right, MergeStrategy strategy) {
        if (right instanceof TimeOrdinalEraPropertyType) {
            final TimeOrdinalEraPropertyType target = this;
            final TimeOrdinalEraPropertyType leftObject = ((TimeOrdinalEraPropertyType) left);
            final TimeOrdinalEraPropertyType rightObject = ((TimeOrdinalEraPropertyType) right);
            {
                java.lang.Boolean timeOrdinalEraShouldBeMergedAndSet = strategy.shouldBeMergedAndSet(leftLocator, rightLocator, leftObject.isSetTimeOrdinalEra(), rightObject.isSetTimeOrdinalEra());
                if (timeOrdinalEraShouldBeMergedAndSet == java.lang.Boolean.TRUE) {
                    TimeOrdinalEraType lhsTimeOrdinalEra;
                    lhsTimeOrdinalEra = leftObject.getTimeOrdinalEra();
                    TimeOrdinalEraType rhsTimeOrdinalEra;
                    rhsTimeOrdinalEra = rightObject.getTimeOrdinalEra();
                    TimeOrdinalEraType mergedTimeOrdinalEra = ((TimeOrdinalEraType) strategy.merge(LocatorUtils.property(leftLocator, "timeOrdinalEra", lhsTimeOrdinalEra), LocatorUtils.property(rightLocator, "timeOrdinalEra", rhsTimeOrdinalEra), lhsTimeOrdinalEra, rhsTimeOrdinalEra, leftObject.isSetTimeOrdinalEra(), rightObject.isSetTimeOrdinalEra()));
                    target.setTimeOrdinalEra(mergedTimeOrdinalEra);
                } else {
                    if (timeOrdinalEraShouldBeMergedAndSet == java.lang.Boolean.FALSE) {
                        target.timeOrdinalEra = null;
                    }
                }
            }
            {
                java.lang.Boolean nilReasonShouldBeMergedAndSet = strategy.shouldBeMergedAndSet(leftLocator, rightLocator, leftObject.isSetNilReason(), rightObject.isSetNilReason());
                if (nilReasonShouldBeMergedAndSet == java.lang.Boolean.TRUE) {
                    List<String> lhsNilReason;
                    lhsNilReason = (leftObject.isSetNilReason()?leftObject.getNilReason():null);
                    List<String> rhsNilReason;
                    rhsNilReason = (rightObject.isSetNilReason()?rightObject.getNilReason():null);
                    List<String> mergedNilReason = ((List<String> ) strategy.merge(LocatorUtils.property(leftLocator, "nilReason", lhsNilReason), LocatorUtils.property(rightLocator, "nilReason", rhsNilReason), lhsNilReason, rhsNilReason, leftObject.isSetNilReason(), rightObject.isSetNilReason()));
                    target.setNilReason(mergedNilReason);
                } else {
                    if (nilReasonShouldBeMergedAndSet == java.lang.Boolean.FALSE) {
                        target.unsetNilReason();
                    }
                }
            }
            {
                java.lang.Boolean remoteSchemaShouldBeMergedAndSet = strategy.shouldBeMergedAndSet(leftLocator, rightLocator, leftObject.isSetRemoteSchema(), rightObject.isSetRemoteSchema());
                if (remoteSchemaShouldBeMergedAndSet == java.lang.Boolean.TRUE) {
                    String lhsRemoteSchema;
                    lhsRemoteSchema = leftObject.getRemoteSchema();
                    String rhsRemoteSchema;
                    rhsRemoteSchema = rightObject.getRemoteSchema();
                    String mergedRemoteSchema = ((String) strategy.merge(LocatorUtils.property(leftLocator, "remoteSchema", lhsRemoteSchema), LocatorUtils.property(rightLocator, "remoteSchema", rhsRemoteSchema), lhsRemoteSchema, rhsRemoteSchema, leftObject.isSetRemoteSchema(), rightObject.isSetRemoteSchema()));
                    target.setRemoteSchema(mergedRemoteSchema);
                } else {
                    if (remoteSchemaShouldBeMergedAndSet == java.lang.Boolean.FALSE) {
                        target.remoteSchema = null;
                    }
                }
            }
            {
                java.lang.Boolean hrefShouldBeMergedAndSet = strategy.shouldBeMergedAndSet(leftLocator, rightLocator, leftObject.isSetHref(), rightObject.isSetHref());
                if (hrefShouldBeMergedAndSet == java.lang.Boolean.TRUE) {
                    String lhsHref;
                    lhsHref = leftObject.getHref();
                    String rhsHref;
                    rhsHref = rightObject.getHref();
                    String mergedHref = ((String) strategy.merge(LocatorUtils.property(leftLocator, "href", lhsHref), LocatorUtils.property(rightLocator, "href", rhsHref), lhsHref, rhsHref, leftObject.isSetHref(), rightObject.isSetHref()));
                    target.setHref(mergedHref);
                } else {
                    if (hrefShouldBeMergedAndSet == java.lang.Boolean.FALSE) {
                        target.href = null;
                    }
                }
            }
            {
                java.lang.Boolean roleShouldBeMergedAndSet = strategy.shouldBeMergedAndSet(leftLocator, rightLocator, leftObject.isSetRole(), rightObject.isSetRole());
                if (roleShouldBeMergedAndSet == java.lang.Boolean.TRUE) {
                    String lhsRole;
                    lhsRole = leftObject.getRole();
                    String rhsRole;
                    rhsRole = rightObject.getRole();
                    String mergedRole = ((String) strategy.merge(LocatorUtils.property(leftLocator, "role", lhsRole), LocatorUtils.property(rightLocator, "role", rhsRole), lhsRole, rhsRole, leftObject.isSetRole(), rightObject.isSetRole()));
                    target.setRole(mergedRole);
                } else {
                    if (roleShouldBeMergedAndSet == java.lang.Boolean.FALSE) {
                        target.role = null;
                    }
                }
            }
            {
                java.lang.Boolean arcroleShouldBeMergedAndSet = strategy.shouldBeMergedAndSet(leftLocator, rightLocator, leftObject.isSetArcrole(), rightObject.isSetArcrole());
                if (arcroleShouldBeMergedAndSet == java.lang.Boolean.TRUE) {
                    String lhsArcrole;
                    lhsArcrole = leftObject.getArcrole();
                    String rhsArcrole;
                    rhsArcrole = rightObject.getArcrole();
                    String mergedArcrole = ((String) strategy.merge(LocatorUtils.property(leftLocator, "arcrole", lhsArcrole), LocatorUtils.property(rightLocator, "arcrole", rhsArcrole), lhsArcrole, rhsArcrole, leftObject.isSetArcrole(), rightObject.isSetArcrole()));
                    target.setArcrole(mergedArcrole);
                } else {
                    if (arcroleShouldBeMergedAndSet == java.lang.Boolean.FALSE) {
                        target.arcrole = null;
                    }
                }
            }
            {
                java.lang.Boolean titleShouldBeMergedAndSet = strategy.shouldBeMergedAndSet(leftLocator, rightLocator, leftObject.isSetTitle(), rightObject.isSetTitle());
                if (titleShouldBeMergedAndSet == java.lang.Boolean.TRUE) {
                    String lhsTitle;
                    lhsTitle = leftObject.getTitle();
                    String rhsTitle;
                    rhsTitle = rightObject.getTitle();
                    String mergedTitle = ((String) strategy.merge(LocatorUtils.property(leftLocator, "title", lhsTitle), LocatorUtils.property(rightLocator, "title", rhsTitle), lhsTitle, rhsTitle, leftObject.isSetTitle(), rightObject.isSetTitle()));
                    target.setTitle(mergedTitle);
                } else {
                    if (titleShouldBeMergedAndSet == java.lang.Boolean.FALSE) {
                        target.title = null;
                    }
                }
            }
            {
                java.lang.Boolean showShouldBeMergedAndSet = strategy.shouldBeMergedAndSet(leftLocator, rightLocator, leftObject.isSetShow(), rightObject.isSetShow());
                if (showShouldBeMergedAndSet == java.lang.Boolean.TRUE) {
                    String lhsShow;
                    lhsShow = leftObject.getShow();
                    String rhsShow;
                    rhsShow = rightObject.getShow();
                    String mergedShow = ((String) strategy.merge(LocatorUtils.property(leftLocator, "show", lhsShow), LocatorUtils.property(rightLocator, "show", rhsShow), lhsShow, rhsShow, leftObject.isSetShow(), rightObject.isSetShow()));
                    target.setShow(mergedShow);
                } else {
                    if (showShouldBeMergedAndSet == java.lang.Boolean.FALSE) {
                        target.show = null;
                    }
                }
            }
            {
                java.lang.Boolean actuateShouldBeMergedAndSet = strategy.shouldBeMergedAndSet(leftLocator, rightLocator, leftObject.isSetActuate(), rightObject.isSetActuate());
                if (actuateShouldBeMergedAndSet == java.lang.Boolean.TRUE) {
                    String lhsActuate;
                    lhsActuate = leftObject.getActuate();
                    String rhsActuate;
                    rhsActuate = rightObject.getActuate();
                    String mergedActuate = ((String) strategy.merge(LocatorUtils.property(leftLocator, "actuate", lhsActuate), LocatorUtils.property(rightLocator, "actuate", rhsActuate), lhsActuate, rhsActuate, leftObject.isSetActuate(), rightObject.isSetActuate()));
                    target.setActuate(mergedActuate);
                } else {
                    if (actuateShouldBeMergedAndSet == java.lang.Boolean.FALSE) {
                        target.actuate = null;
                    }
                }
            }
            {
                java.lang.Boolean ownsShouldBeMergedAndSet = strategy.shouldBeMergedAndSet(leftLocator, rightLocator, leftObject.isSetOwns(), rightObject.isSetOwns());
                if (ownsShouldBeMergedAndSet == java.lang.Boolean.TRUE) {
                    boolean lhsOwns;
                    lhsOwns = (leftObject.isSetOwns()?leftObject.isOwns():false);
                    boolean rhsOwns;
                    rhsOwns = (rightObject.isSetOwns()?rightObject.isOwns():false);
                    boolean mergedOwns = ((boolean) strategy.merge(LocatorUtils.property(leftLocator, "owns", lhsOwns), LocatorUtils.property(rightLocator, "owns", rhsOwns), lhsOwns, rhsOwns, leftObject.isSetOwns(), rightObject.isSetOwns()));
                    target.setOwns(mergedOwns);
                } else {
                    if (ownsShouldBeMergedAndSet == java.lang.Boolean.FALSE) {
                        target.unsetOwns();
                    }
                }
            }
        }
    }

}
