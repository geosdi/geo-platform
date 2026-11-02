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

package org.geosdi.geoplatform.xml.wps.v100;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlSchemaType;
import jakarta.xml.bind.annotation.XmlType;
import org.jvnet.jaxb.lang.JAXBToStringStrategy;
import org.jvnet.jaxb.lang.ToString;
import org.jvnet.jaxb.lang.ToStringStrategy;
import org.jvnet.jaxb.locator.ObjectLocator;


/**
 * Listing of the Coordinate Reference System (CRS) support for this process input or output.
 * 
 * <p>Classe Java per SupportedCRSsType complex type.</p>
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.</p>
 * 
 * <pre>{@code
 * <complexType name="SupportedCRSsType">
 *   <complexContent>
 *     <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       <sequence>
 *         <element name="Default">
 *           <complexType>
 *             <complexContent>
 *               <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 <sequence>
 *                   <element name="CRS" type="{http://www.w3.org/2001/XMLSchema}anyURI"/>
 *                 </sequence>
 *               </restriction>
 *             </complexContent>
 *           </complexType>
 *         </element>
 *         <element name="Supported" type="{http://www.opengis.net/wps/1.0.0}CRSsType"/>
 *       </sequence>
 *     </restriction>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SupportedCRSsType", propOrder = {
    "_default",
    "supported"
})
public class SupportedCRSsType implements ToString
{

    /**
     * Identifies the default CRS that will be used unless the Execute operation request specifies another supported CRS.
     * 
     */
    @XmlElement(name = "Default", namespace = "", required = true)
    protected SupportedCRSsType.Default _default;
    /**
     * Unordered list of references to all of the CRSs supported for this Input/Output. The default CRS shall be included in this list.
     * 
     */
    @XmlElement(name = "Supported", namespace = "", required = true)
    protected CRSsType supported;

    /**
     * Identifies the default CRS that will be used unless the Execute operation request specifies another supported CRS.
     * 
     * @return
     *     possible object is
     *     {@link SupportedCRSsType.Default }
     *     
     */
    public SupportedCRSsType.Default getDefault() {
        return _default;
    }

    /**
     * Imposta il valore della proprietà default.
     * 
     * @param value
     *     allowed object is
     *     {@link SupportedCRSsType.Default }
     *     
     * @see #getDefault()
     */
    public void setDefault(SupportedCRSsType.Default value) {
        this._default = value;
    }

    public boolean isSetDefault() {
        return (this._default!= null);
    }

    /**
     * Unordered list of references to all of the CRSs supported for this Input/Output. The default CRS shall be included in this list.
     * 
     * @return
     *     possible object is
     *     {@link CRSsType }
     *     
     */
    public CRSsType getSupported() {
        return supported;
    }

    /**
     * Imposta il valore della proprietà supported.
     * 
     * @param value
     *     allowed object is
     *     {@link CRSsType }
     *     
     * @see #getSupported()
     */
    public void setSupported(CRSsType value) {
        this.supported = value;
    }

    public boolean isSetSupported() {
        return (this.supported!= null);
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
            SupportedCRSsType.Default theDefault;
            theDefault = this.getDefault();
            strategy.appendField(locator, this, "_default", buffer, theDefault, this.isSetDefault());
        }
        {
            CRSsType theSupported;
            theSupported = this.getSupported();
            strategy.appendField(locator, this, "supported", buffer, theSupported, this.isSetSupported());
        }
        return buffer;
    }


    /**
     * <p>Classe Java per anonymous complex type.</p>
     * 
     * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.</p>
     * 
     * <pre>{@code
     * <complexType>
     *   <complexContent>
     *     <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       <sequence>
     *         <element name="CRS" type="{http://www.w3.org/2001/XMLSchema}anyURI"/>
     *       </sequence>
     *     </restriction>
     *   </complexContent>
     * </complexType>
     * }</pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "crs"
    })
    public static class Default implements ToString
    {

        /**
         * Reference to the default CRS supported for this Input/Output
         * 
         */
        @XmlElement(name = "CRS", namespace = "", required = true)
        @XmlSchemaType(name = "anyURI")
        protected String crs;

        /**
         * Reference to the default CRS supported for this Input/Output
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getCRS() {
            return crs;
        }

        /**
         * Imposta il valore della proprietà crs.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         * @see #getCRS()
         */
        public void setCRS(String value) {
            this.crs = value;
        }

        public boolean isSetCRS() {
            return (this.crs!= null);
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
                String theCRS;
                theCRS = this.getCRS();
                strategy.appendField(locator, this, "crs", buffer, theCRS, this.isSetCRS());
            }
            return buffer;
        }

    }

}
