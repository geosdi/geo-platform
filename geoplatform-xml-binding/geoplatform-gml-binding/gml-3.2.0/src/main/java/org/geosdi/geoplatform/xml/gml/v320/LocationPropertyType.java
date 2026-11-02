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
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlElementRef;
import jakarta.xml.bind.annotation.XmlList;
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
 * <p>Classe Java per LocationPropertyType complex type.</p>
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.</p>
 * 
 * <pre>{@code
 * <complexType name="LocationPropertyType">
 *   <complexContent>
 *     <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       <sequence>
 *         <choice>
 *           <element ref="{http://www.opengis.net/gml}AbstractGeometry"/>
 *           <element ref="{http://www.opengis.net/gml}LocationKeyWord"/>
 *           <element ref="{http://www.opengis.net/gml}LocationString"/>
 *           <element ref="{http://www.opengis.net/gml}Null"/>
 *         </choice>
 *       </sequence>
 *       <attGroup ref="{http://www.opengis.net/gml}AssociationAttributeGroup"/>
 *     </restriction>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "LocationPropertyType", propOrder = {
    "abstractGeometry",
    "locationKeyWord",
    "locationString",
    "_null"
})
@XmlSeeAlso({
    PriorityLocationPropertyType.class
})
public class LocationPropertyType implements Cloneable, CopyTo, Equals, HashCode, MergeFrom, ToString
{

    /**
     * The AbstractGeometry element is the abstract head of the substitution group for all geometry elements of GML. This includes pre-defined and user-defined geometry elements. Any geometry element shall be a direct or indirect extension/restriction of AbstractGeometryType and shall be directly or indirectly in the substitution group of AbstractGeometry.
     * 
     */
    @XmlElementRef(name = "AbstractGeometry", namespace = "http://www.opengis.net/gml", type = JAXBElement.class, required = false)
    protected JAXBElement<? extends AbstractGeometryType> abstractGeometry;
    @XmlElement(name = "LocationKeyWord")
    protected CodeType locationKeyWord;
    @XmlElement(name = "LocationString")
    protected StringOrRefType locationString;
    @XmlList
    @XmlElement(name = "Null")
    protected List<String> _null;
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

    /**
     * The AbstractGeometry element is the abstract head of the substitution group for all geometry elements of GML. This includes pre-defined and user-defined geometry elements. Any geometry element shall be a direct or indirect extension/restriction of AbstractGeometryType and shall be directly or indirectly in the substitution group of AbstractGeometry.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link AbstractCurveType }{@code >}
     *     {@link JAXBElement }{@code <}{@link AbstractGeometricAggregateType }{@code >}
     *     {@link JAXBElement }{@code <}{@link AbstractGeometricPrimitiveType }{@code >}
     *     {@link JAXBElement }{@code <}{@link AbstractGeometryType }{@code >}
     *     {@link JAXBElement }{@code <}{@link AbstractGeometryType }{@code >}
     *     {@link JAXBElement }{@code <}{@link AbstractSolidType }{@code >}
     *     {@link JAXBElement }{@code <}{@link AbstractSurfaceType }{@code >}
     *     {@link JAXBElement }{@code <}{@link CompositeCurveType }{@code >}
     *     {@link JAXBElement }{@code <}{@link CompositeSolidType }{@code >}
     *     {@link JAXBElement }{@code <}{@link CompositeSurfaceType }{@code >}
     *     {@link JAXBElement }{@code <}{@link CurveType }{@code >}
     *     {@link JAXBElement }{@code <}{@link GeometricComplexType }{@code >}
     *     {@link JAXBElement }{@code <}{@link GridType }{@code >}
     *     {@link JAXBElement }{@code <}{@link LineStringType }{@code >}
     *     {@link JAXBElement }{@code <}{@link MultiCurveType }{@code >}
     *     {@link JAXBElement }{@code <}{@link MultiGeometryType }{@code >}
     *     {@link JAXBElement }{@code <}{@link MultiPointType }{@code >}
     *     {@link JAXBElement }{@code <}{@link MultiSolidType }{@code >}
     *     {@link JAXBElement }{@code <}{@link MultiSurfaceType }{@code >}
     *     {@link JAXBElement }{@code <}{@link OrientableCurveType }{@code >}
     *     {@link JAXBElement }{@code <}{@link OrientableSurfaceType }{@code >}
     *     {@link JAXBElement }{@code <}{@link PointType }{@code >}
     *     {@link JAXBElement }{@code <}{@link PolygonType }{@code >}
     *     {@link JAXBElement }{@code <}{@link PolyhedralSurfaceType }{@code >}
     *     {@link JAXBElement }{@code <}{@link RectifiedGridType }{@code >}
     *     {@link JAXBElement }{@code <}{@link SolidType }{@code >}
     *     {@link JAXBElement }{@code <}{@link SurfaceType }{@code >}
     *     {@link JAXBElement }{@code <}{@link TinType }{@code >}
     *     {@link JAXBElement }{@code <}{@link TriangulatedSurfaceType }{@code >}
     *     
     */
    public JAXBElement<? extends AbstractGeometryType> getAbstractGeometry() {
        return abstractGeometry;
    }

    /**
     * Imposta il valore della proprietà abstractGeometry.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link AbstractCurveType }{@code >}
     *     {@link JAXBElement }{@code <}{@link AbstractGeometricAggregateType }{@code >}
     *     {@link JAXBElement }{@code <}{@link AbstractGeometricPrimitiveType }{@code >}
     *     {@link JAXBElement }{@code <}{@link AbstractGeometryType }{@code >}
     *     {@link JAXBElement }{@code <}{@link AbstractGeometryType }{@code >}
     *     {@link JAXBElement }{@code <}{@link AbstractSolidType }{@code >}
     *     {@link JAXBElement }{@code <}{@link AbstractSurfaceType }{@code >}
     *     {@link JAXBElement }{@code <}{@link CompositeCurveType }{@code >}
     *     {@link JAXBElement }{@code <}{@link CompositeSolidType }{@code >}
     *     {@link JAXBElement }{@code <}{@link CompositeSurfaceType }{@code >}
     *     {@link JAXBElement }{@code <}{@link CurveType }{@code >}
     *     {@link JAXBElement }{@code <}{@link GeometricComplexType }{@code >}
     *     {@link JAXBElement }{@code <}{@link GridType }{@code >}
     *     {@link JAXBElement }{@code <}{@link LineStringType }{@code >}
     *     {@link JAXBElement }{@code <}{@link MultiCurveType }{@code >}
     *     {@link JAXBElement }{@code <}{@link MultiGeometryType }{@code >}
     *     {@link JAXBElement }{@code <}{@link MultiPointType }{@code >}
     *     {@link JAXBElement }{@code <}{@link MultiSolidType }{@code >}
     *     {@link JAXBElement }{@code <}{@link MultiSurfaceType }{@code >}
     *     {@link JAXBElement }{@code <}{@link OrientableCurveType }{@code >}
     *     {@link JAXBElement }{@code <}{@link OrientableSurfaceType }{@code >}
     *     {@link JAXBElement }{@code <}{@link PointType }{@code >}
     *     {@link JAXBElement }{@code <}{@link PolygonType }{@code >}
     *     {@link JAXBElement }{@code <}{@link PolyhedralSurfaceType }{@code >}
     *     {@link JAXBElement }{@code <}{@link RectifiedGridType }{@code >}
     *     {@link JAXBElement }{@code <}{@link SolidType }{@code >}
     *     {@link JAXBElement }{@code <}{@link SurfaceType }{@code >}
     *     {@link JAXBElement }{@code <}{@link TinType }{@code >}
     *     {@link JAXBElement }{@code <}{@link TriangulatedSurfaceType }{@code >}
     *     
     * @see #getAbstractGeometry()
     */
    public void setAbstractGeometry(JAXBElement<? extends AbstractGeometryType> value) {
        this.abstractGeometry = value;
    }

    public boolean isSetAbstractGeometry() {
        return (this.abstractGeometry!= null);
    }

    /**
     * Recupera il valore della proprietà locationKeyWord.
     * 
     * @return
     *     possible object is
     *     {@link CodeType }
     *     
     */
    public CodeType getLocationKeyWord() {
        return locationKeyWord;
    }

    /**
     * Imposta il valore della proprietà locationKeyWord.
     * 
     * @param value
     *     allowed object is
     *     {@link CodeType }
     *     
     */
    public void setLocationKeyWord(CodeType value) {
        this.locationKeyWord = value;
    }

    public boolean isSetLocationKeyWord() {
        return (this.locationKeyWord!= null);
    }

    /**
     * Recupera il valore della proprietà locationString.
     * 
     * @return
     *     possible object is
     *     {@link StringOrRefType }
     *     
     */
    public StringOrRefType getLocationString() {
        return locationString;
    }

    /**
     * Imposta il valore della proprietà locationString.
     * 
     * @param value
     *     allowed object is
     *     {@link StringOrRefType }
     *     
     */
    public void setLocationString(StringOrRefType value) {
        this.locationString = value;
    }

    public boolean isSetLocationString() {
        return (this.locationString!= null);
    }

    /**
     * Gets the value of the null property.
     * 
     * <p>This accessor method returns a reference to the live list,
     * not a snapshot. Therefore, any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a {@code set} method for the null property.</p>
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * </p>
     * <pre>
     * getNull().add(newItem);
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
     *     The value of the null property.
     */
    public List<String> getNull() {
        if (_null == null) {
            _null = new ArrayList<>();
        }
        return this._null;
    }

    public boolean isSetNull() {
        return ((this._null!= null)&&(!this._null.isEmpty()));
    }

    public void unsetNull() {
        this._null = null;
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
            JAXBElement<? extends AbstractGeometryType> theAbstractGeometry;
            theAbstractGeometry = this.getAbstractGeometry();
            strategy.appendField(locator, this, "abstractGeometry", buffer, theAbstractGeometry, this.isSetAbstractGeometry());
        }
        {
            CodeType theLocationKeyWord;
            theLocationKeyWord = this.getLocationKeyWord();
            strategy.appendField(locator, this, "locationKeyWord", buffer, theLocationKeyWord, this.isSetLocationKeyWord());
        }
        {
            StringOrRefType theLocationString;
            theLocationString = this.getLocationString();
            strategy.appendField(locator, this, "locationString", buffer, theLocationString, this.isSetLocationString());
        }
        {
            List<String> theNull;
            theNull = (this.isSetNull()?this.getNull():null);
            strategy.appendField(locator, this, "_null", buffer, theNull, this.isSetNull());
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
            theTYPE = LocationPropertyType.TYPE;
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
        return buffer;
    }

    public void setNull(List<String> value) {
        this._null = null;
        if (value!= null) {
            List<String> draftl = this.getNull();
            draftl.addAll(value);
        }
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
        final LocationPropertyType that = ((LocationPropertyType) object);
        {
            JAXBElement<? extends AbstractGeometryType> lhsAbstractGeometry;
            lhsAbstractGeometry = this.getAbstractGeometry();
            JAXBElement<? extends AbstractGeometryType> rhsAbstractGeometry;
            rhsAbstractGeometry = that.getAbstractGeometry();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "abstractGeometry", lhsAbstractGeometry), LocatorUtils.property(thatLocator, "abstractGeometry", rhsAbstractGeometry), lhsAbstractGeometry, rhsAbstractGeometry, this.isSetAbstractGeometry(), that.isSetAbstractGeometry())) {
                return false;
            }
        }
        {
            CodeType lhsLocationKeyWord;
            lhsLocationKeyWord = this.getLocationKeyWord();
            CodeType rhsLocationKeyWord;
            rhsLocationKeyWord = that.getLocationKeyWord();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "locationKeyWord", lhsLocationKeyWord), LocatorUtils.property(thatLocator, "locationKeyWord", rhsLocationKeyWord), lhsLocationKeyWord, rhsLocationKeyWord, this.isSetLocationKeyWord(), that.isSetLocationKeyWord())) {
                return false;
            }
        }
        {
            StringOrRefType lhsLocationString;
            lhsLocationString = this.getLocationString();
            StringOrRefType rhsLocationString;
            rhsLocationString = that.getLocationString();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "locationString", lhsLocationString), LocatorUtils.property(thatLocator, "locationString", rhsLocationString), lhsLocationString, rhsLocationString, this.isSetLocationString(), that.isSetLocationString())) {
                return false;
            }
        }
        {
            List<String> lhsNull;
            lhsNull = (this.isSetNull()?this.getNull():null);
            List<String> rhsNull;
            rhsNull = (that.isSetNull()?that.getNull():null);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "_null", lhsNull), LocatorUtils.property(thatLocator, "_null", rhsNull), lhsNull, rhsNull, this.isSetNull(), that.isSetNull())) {
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
            JAXBElement<? extends AbstractGeometryType> theAbstractGeometry;
            theAbstractGeometry = this.getAbstractGeometry();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "abstractGeometry", theAbstractGeometry), currentHashCode, theAbstractGeometry, this.isSetAbstractGeometry());
        }
        {
            CodeType theLocationKeyWord;
            theLocationKeyWord = this.getLocationKeyWord();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "locationKeyWord", theLocationKeyWord), currentHashCode, theLocationKeyWord, this.isSetLocationKeyWord());
        }
        {
            StringOrRefType theLocationString;
            theLocationString = this.getLocationString();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "locationString", theLocationString), currentHashCode, theLocationString, this.isSetLocationString());
        }
        {
            List<String> theNull;
            theNull = (this.isSetNull()?this.getNull():null);
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "_null", theNull), currentHashCode, theNull, this.isSetNull());
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
        if (draftCopy instanceof LocationPropertyType) {
            final LocationPropertyType copy = ((LocationPropertyType) draftCopy);
            {
                java.lang.Boolean abstractGeometryShouldBeCopiedAndSet = strategy.shouldBeCopiedAndSet(locator, this.isSetAbstractGeometry());
                if (abstractGeometryShouldBeCopiedAndSet == java.lang.Boolean.TRUE) {
                    JAXBElement<? extends AbstractGeometryType> sourceAbstractGeometry;
                    sourceAbstractGeometry = this.getAbstractGeometry();
                    @SuppressWarnings("unchecked")
                    JAXBElement<? extends AbstractGeometryType> copyAbstractGeometry = ((JAXBElement<? extends AbstractGeometryType> ) strategy.copy(LocatorUtils.property(locator, "abstractGeometry", sourceAbstractGeometry), sourceAbstractGeometry, this.isSetAbstractGeometry()));
                    copy.setAbstractGeometry(copyAbstractGeometry);
                } else {
                    if (abstractGeometryShouldBeCopiedAndSet == java.lang.Boolean.FALSE) {
                        copy.abstractGeometry = null;
                    }
                }
            }
            {
                java.lang.Boolean locationKeyWordShouldBeCopiedAndSet = strategy.shouldBeCopiedAndSet(locator, this.isSetLocationKeyWord());
                if (locationKeyWordShouldBeCopiedAndSet == java.lang.Boolean.TRUE) {
                    CodeType sourceLocationKeyWord;
                    sourceLocationKeyWord = this.getLocationKeyWord();
                    CodeType copyLocationKeyWord = ((CodeType) strategy.copy(LocatorUtils.property(locator, "locationKeyWord", sourceLocationKeyWord), sourceLocationKeyWord, this.isSetLocationKeyWord()));
                    copy.setLocationKeyWord(copyLocationKeyWord);
                } else {
                    if (locationKeyWordShouldBeCopiedAndSet == java.lang.Boolean.FALSE) {
                        copy.locationKeyWord = null;
                    }
                }
            }
            {
                java.lang.Boolean locationStringShouldBeCopiedAndSet = strategy.shouldBeCopiedAndSet(locator, this.isSetLocationString());
                if (locationStringShouldBeCopiedAndSet == java.lang.Boolean.TRUE) {
                    StringOrRefType sourceLocationString;
                    sourceLocationString = this.getLocationString();
                    StringOrRefType copyLocationString = ((StringOrRefType) strategy.copy(LocatorUtils.property(locator, "locationString", sourceLocationString), sourceLocationString, this.isSetLocationString()));
                    copy.setLocationString(copyLocationString);
                } else {
                    if (locationStringShouldBeCopiedAndSet == java.lang.Boolean.FALSE) {
                        copy.locationString = null;
                    }
                }
            }
            {
                java.lang.Boolean _nullShouldBeCopiedAndSet = strategy.shouldBeCopiedAndSet(locator, this.isSetNull());
                if (_nullShouldBeCopiedAndSet == java.lang.Boolean.TRUE) {
                    List<String> sourceNull;
                    sourceNull = (this.isSetNull()?this.getNull():null);
                    @SuppressWarnings("unchecked")
                    List<String> copyNull = ((List<String> ) strategy.copy(LocatorUtils.property(locator, "_null", sourceNull), sourceNull, this.isSetNull()));
                    copy.setNull(copyNull);
                } else {
                    if (_nullShouldBeCopiedAndSet == java.lang.Boolean.FALSE) {
                        copy.unsetNull();
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
        }
        return draftCopy;
    }

    @Override
    public Object createNewInstance() {
        return new LocationPropertyType();
    }

    @Override
    public void mergeFrom(Object left, Object right) {
        final MergeStrategy strategy = JAXBMergeStrategy.getInstance();
        mergeFrom(null, null, left, right, strategy);
    }

    @Override
    public void mergeFrom(ObjectLocator leftLocator, ObjectLocator rightLocator, Object left, Object right, MergeStrategy strategy) {
        if (right instanceof LocationPropertyType) {
            final LocationPropertyType target = this;
            final LocationPropertyType leftObject = ((LocationPropertyType) left);
            final LocationPropertyType rightObject = ((LocationPropertyType) right);
            {
                java.lang.Boolean abstractGeometryShouldBeMergedAndSet = strategy.shouldBeMergedAndSet(leftLocator, rightLocator, leftObject.isSetAbstractGeometry(), rightObject.isSetAbstractGeometry());
                if (abstractGeometryShouldBeMergedAndSet == java.lang.Boolean.TRUE) {
                    JAXBElement<? extends AbstractGeometryType> lhsAbstractGeometry;
                    lhsAbstractGeometry = leftObject.getAbstractGeometry();
                    JAXBElement<? extends AbstractGeometryType> rhsAbstractGeometry;
                    rhsAbstractGeometry = rightObject.getAbstractGeometry();
                    JAXBElement<? extends AbstractGeometryType> mergedAbstractGeometry = ((JAXBElement<? extends AbstractGeometryType> ) strategy.merge(LocatorUtils.property(leftLocator, "abstractGeometry", lhsAbstractGeometry), LocatorUtils.property(rightLocator, "abstractGeometry", rhsAbstractGeometry), lhsAbstractGeometry, rhsAbstractGeometry, leftObject.isSetAbstractGeometry(), rightObject.isSetAbstractGeometry()));
                    target.setAbstractGeometry(mergedAbstractGeometry);
                } else {
                    if (abstractGeometryShouldBeMergedAndSet == java.lang.Boolean.FALSE) {
                        target.abstractGeometry = null;
                    }
                }
            }
            {
                java.lang.Boolean locationKeyWordShouldBeMergedAndSet = strategy.shouldBeMergedAndSet(leftLocator, rightLocator, leftObject.isSetLocationKeyWord(), rightObject.isSetLocationKeyWord());
                if (locationKeyWordShouldBeMergedAndSet == java.lang.Boolean.TRUE) {
                    CodeType lhsLocationKeyWord;
                    lhsLocationKeyWord = leftObject.getLocationKeyWord();
                    CodeType rhsLocationKeyWord;
                    rhsLocationKeyWord = rightObject.getLocationKeyWord();
                    CodeType mergedLocationKeyWord = ((CodeType) strategy.merge(LocatorUtils.property(leftLocator, "locationKeyWord", lhsLocationKeyWord), LocatorUtils.property(rightLocator, "locationKeyWord", rhsLocationKeyWord), lhsLocationKeyWord, rhsLocationKeyWord, leftObject.isSetLocationKeyWord(), rightObject.isSetLocationKeyWord()));
                    target.setLocationKeyWord(mergedLocationKeyWord);
                } else {
                    if (locationKeyWordShouldBeMergedAndSet == java.lang.Boolean.FALSE) {
                        target.locationKeyWord = null;
                    }
                }
            }
            {
                java.lang.Boolean locationStringShouldBeMergedAndSet = strategy.shouldBeMergedAndSet(leftLocator, rightLocator, leftObject.isSetLocationString(), rightObject.isSetLocationString());
                if (locationStringShouldBeMergedAndSet == java.lang.Boolean.TRUE) {
                    StringOrRefType lhsLocationString;
                    lhsLocationString = leftObject.getLocationString();
                    StringOrRefType rhsLocationString;
                    rhsLocationString = rightObject.getLocationString();
                    StringOrRefType mergedLocationString = ((StringOrRefType) strategy.merge(LocatorUtils.property(leftLocator, "locationString", lhsLocationString), LocatorUtils.property(rightLocator, "locationString", rhsLocationString), lhsLocationString, rhsLocationString, leftObject.isSetLocationString(), rightObject.isSetLocationString()));
                    target.setLocationString(mergedLocationString);
                } else {
                    if (locationStringShouldBeMergedAndSet == java.lang.Boolean.FALSE) {
                        target.locationString = null;
                    }
                }
            }
            {
                java.lang.Boolean _nullShouldBeMergedAndSet = strategy.shouldBeMergedAndSet(leftLocator, rightLocator, leftObject.isSetNull(), rightObject.isSetNull());
                if (_nullShouldBeMergedAndSet == java.lang.Boolean.TRUE) {
                    List<String> lhsNull;
                    lhsNull = (leftObject.isSetNull()?leftObject.getNull():null);
                    List<String> rhsNull;
                    rhsNull = (rightObject.isSetNull()?rightObject.getNull():null);
                    List<String> mergedNull = ((List<String> ) strategy.merge(LocatorUtils.property(leftLocator, "_null", lhsNull), LocatorUtils.property(rightLocator, "_null", rhsNull), lhsNull, rhsNull, leftObject.isSetNull(), rightObject.isSetNull()));
                    target.setNull(mergedNull);
                } else {
                    if (_nullShouldBeMergedAndSet == java.lang.Boolean.FALSE) {
                        target.unsetNull();
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
        }
    }

}
