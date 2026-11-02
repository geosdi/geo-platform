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
// Generato il: 2026.11.02 alle 02:51:17 PM CET
//

package org.geosdi.geoplatform.xml.ows.v110;

import java.util.ArrayList;
import java.util.List;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlSeeAlso;
import jakarta.xml.bind.annotation.XmlType;
import org.jvnet.jaxb.lang.JAXBToStringStrategy;
import org.jvnet.jaxb.lang.ToString;
import org.jvnet.jaxb.lang.ToStringStrategy;
import org.jvnet.jaxb.locator.ObjectLocator;


/**
 * Complete reference to a remote or local resource, allowing including metadata about that resource.
 * 
 * <p>Classe Java per ReferenceType complex type.</p>
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.</p>
 * 
 * <pre>{@code
 * <complexType name="ReferenceType">
 *   <complexContent>
 *     <extension base="{http://www.opengis.net/ows/1.1}AbstractReferenceBaseType">
 *       <sequence>
 *         <element ref="{http://www.opengis.net/ows/1.1}Identifier" minOccurs="0"/>
 *         <element ref="{http://www.opengis.net/ows/1.1}Abstract" maxOccurs="unbounded" minOccurs="0"/>
 *         <element name="Format" type="{http://www.opengis.net/ows/1.1}MimeType" minOccurs="0"/>
 *         <element ref="{http://www.opengis.net/ows/1.1}Metadata" maxOccurs="unbounded" minOccurs="0"/>
 *       </sequence>
 *     </extension>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ReferenceType", propOrder = {
    "identifier",
    "_abstract",
    "format",
    "metadata"
})
@XmlSeeAlso({
    ServiceReferenceType.class
})
public class ReferenceType
    extends AbstractReferenceBaseType
    implements ToString
{

    /**
     * Optional unique identifier of the referenced resource.
     * 
     */
    @XmlElement(name = "Identifier")
    protected CodeType identifier;
    /**
     * Brief narrative description of this resource, normally used for display to a human.
     * 
     */
    @XmlElement(name = "Abstract")
    protected List<LanguageStringType> _abstract;
    /**
     * The format of the referenced resource. This element is omitted when the mime type is indicated in the http header of the reference.
     * 
     */
    @XmlElement(name = "Format")
    protected String format;
    /**
     * Optional unordered list of additional metadata about this resource. A list of optional metadata elements for this ReferenceType could be specified in the Implementation Specification for each use of this type in a specific OWS.
     * 
     */
    @XmlElement(name = "Metadata")
    protected List<MetadataType> metadata;

    /**
     * Optional unique identifier of the referenced resource.
     * 
     * @return
     *     possible object is
     *     {@link CodeType }
     *     
     */
    public CodeType getIdentifier() {
        return identifier;
    }

    /**
     * Imposta il valore della proprietà identifier.
     * 
     * @param value
     *     allowed object is
     *     {@link CodeType }
     *     
     * @see #getIdentifier()
     */
    public void setIdentifier(CodeType value) {
        this.identifier = value;
    }

    /**
     * Brief narrative description of this resource, normally used for display to a human.
     * 
     * Gets the value of the abstract property.
     * 
     * <p>This accessor method returns a reference to the live list,
     * not a snapshot. Therefore, any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a {@code set} method for the abstract property.</p>
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * </p>
     * <pre>
     * getAbstract().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link LanguageStringType }
     * </p>
     * 
     * 
     * @return
     *     The value of the abstract property.
     */
    public List<LanguageStringType> getAbstract() {
        if (_abstract == null) {
            _abstract = new ArrayList<>();
        }
        return this._abstract;
    }

    /**
     * The format of the referenced resource. This element is omitted when the mime type is indicated in the http header of the reference.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFormat() {
        return format;
    }

    /**
     * Imposta il valore della proprietà format.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     * @see #getFormat()
     */
    public void setFormat(String value) {
        this.format = value;
    }

    /**
     * Optional unordered list of additional metadata about this resource. A list of optional metadata elements for this ReferenceType could be specified in the Implementation Specification for each use of this type in a specific OWS.
     * 
     * Gets the value of the metadata property.
     * 
     * <p>This accessor method returns a reference to the live list,
     * not a snapshot. Therefore, any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a {@code set} method for the metadata property.</p>
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * </p>
     * <pre>
     * getMetadata().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link MetadataType }
     * </p>
     * 
     * 
     * @return
     *     The value of the metadata property.
     */
    public List<MetadataType> getMetadata() {
        if (metadata == null) {
            metadata = new ArrayList<>();
        }
        return this.metadata;
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
            CodeType theIdentifier;
            theIdentifier = this.getIdentifier();
            strategy.appendField(locator, this, "identifier", buffer, theIdentifier, (this.identifier!= null));
        }
        {
            List<LanguageStringType> theAbstract;
            theAbstract = (((this._abstract!= null)&&(!this._abstract.isEmpty()))?this.getAbstract():null);
            strategy.appendField(locator, this, "_abstract", buffer, theAbstract, ((this._abstract!= null)&&(!this._abstract.isEmpty())));
        }
        {
            String theFormat;
            theFormat = this.getFormat();
            strategy.appendField(locator, this, "format", buffer, theFormat, (this.format!= null));
        }
        {
            List<MetadataType> theMetadata;
            theMetadata = (((this.metadata!= null)&&(!this.metadata.isEmpty()))?this.getMetadata():null);
            strategy.appendField(locator, this, "metadata", buffer, theMetadata, ((this.metadata!= null)&&(!this.metadata.isEmpty())));
        }
        return buffer;
    }

    public void setAbstract(List<LanguageStringType> value) {
        this._abstract = null;
        if (value!= null) {
            List<LanguageStringType> draftl = this.getAbstract();
            draftl.addAll(value);
        }
    }

    public void setMetadata(List<MetadataType> value) {
        this.metadata = null;
        if (value!= null) {
            List<MetadataType> draftl = this.getMetadata();
            draftl.addAll(value);
        }
    }

}
