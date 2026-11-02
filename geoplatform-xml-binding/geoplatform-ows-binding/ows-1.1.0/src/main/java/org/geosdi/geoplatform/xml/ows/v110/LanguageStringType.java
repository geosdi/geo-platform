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

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlType;
import jakarta.xml.bind.annotation.XmlValue;
import org.jvnet.jaxb.lang.JAXBToStringStrategy;
import org.jvnet.jaxb.lang.ToString;
import org.jvnet.jaxb.lang.ToStringStrategy;
import org.jvnet.jaxb.locator.ObjectLocator;


/**
 * Text string with the language of the string identified as recommended in the XML 1.0 W3C Recommendation, section 2.12.
 * 
 * <p>Classe Java per LanguageStringType complex type.</p>
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.</p>
 * 
 * <pre>{@code
 * <complexType name="LanguageStringType">
 *   <simpleContent>
 *     <extension base="<http://www.w3.org/2001/XMLSchema>string">
 *       <attribute ref="{http://www.w3.org/XML/1998/namespace}lang"/>
 *     </extension>
 *   </simpleContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "LanguageStringType", propOrder = {
    "value"
})
public class LanguageStringType implements ToString
{

    @XmlValue
    protected String value;
    /**
     * &lt;pre&gt;
     * &amp;lt;?xml version="1.0" encoding="UTF-8"?&amp;gt;&amp;lt;div xmlns="http://www.w3.org/1999/xhtml" xmlns:xs="http://www.w3.org/2001/XMLSchema"&amp;gt;&amp;lt;h3&amp;gt;lang (as an attribute name)&amp;lt;/h3&amp;gt;&amp;lt;p&amp;gt;
     *        denotes an attribute whose value
     *        is a language code for the natural language of the content of
     *        any element; its value is inherited.  This name is reserved
     *        by virtue of its definition in the XML specification.&amp;lt;/p&amp;gt;&amp;lt;/div&amp;gt;
     * &lt;/pre&gt;
     * 
     * &lt;pre&gt;
     * &amp;lt;?xml version="1.0" encoding="UTF-8"?&amp;gt;&amp;lt;div xmlns="http://www.w3.org/1999/xhtml" xmlns:xs="http://www.w3.org/2001/XMLSchema"&amp;gt;&amp;lt;h4&amp;gt;Notes&amp;lt;/h4&amp;gt;&amp;lt;p&amp;gt;
     *       Attempting to install the relevant ISO 2- and 3-letter
     *       codes as the enumerated possible values is probably never
     *       going to be a realistic possibility.  
     *      &amp;lt;/p&amp;gt;&amp;lt;p&amp;gt;
     *       See BCP 47 at &amp;lt;a href="http://www.rfc-editor.org/rfc/bcp/bcp47.txt"&amp;gt;
     *        http://www.rfc-editor.org/rfc/bcp/bcp47.txt&amp;lt;/a&amp;gt;
     *       and the IANA language subtag registry at
     *       &amp;lt;a href="http://www.iana.org/assignments/language-subtag-registry"&amp;gt;
     *        http://www.iana.org/assignments/language-subtag-registry&amp;lt;/a&amp;gt;
     *       for further information.
     *      &amp;lt;/p&amp;gt;&amp;lt;p&amp;gt;
     *       The union allows for the 'un-declaration' of xml:lang with
     *       the empty string.
     *      &amp;lt;/p&amp;gt;&amp;lt;/div&amp;gt;
     * &lt;/pre&gt;
     * 
     */
    @XmlAttribute(name = "lang", namespace = "http://www.w3.org/XML/1998/namespace")
    protected String lang;

    /**
     * Recupera il valore della proprietà value.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getValue() {
        return value;
    }

    /**
     * Imposta il valore della proprietà value.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setValue(String value) {
        this.value = value;
    }

    /**
     * &lt;pre&gt;
     * &amp;lt;?xml version="1.0" encoding="UTF-8"?&amp;gt;&amp;lt;div xmlns="http://www.w3.org/1999/xhtml" xmlns:xs="http://www.w3.org/2001/XMLSchema"&amp;gt;&amp;lt;h3&amp;gt;lang (as an attribute name)&amp;lt;/h3&amp;gt;&amp;lt;p&amp;gt;
     *        denotes an attribute whose value
     *        is a language code for the natural language of the content of
     *        any element; its value is inherited.  This name is reserved
     *        by virtue of its definition in the XML specification.&amp;lt;/p&amp;gt;&amp;lt;/div&amp;gt;
     * &lt;/pre&gt;
     * 
     * &lt;pre&gt;
     * &amp;lt;?xml version="1.0" encoding="UTF-8"?&amp;gt;&amp;lt;div xmlns="http://www.w3.org/1999/xhtml" xmlns:xs="http://www.w3.org/2001/XMLSchema"&amp;gt;&amp;lt;h4&amp;gt;Notes&amp;lt;/h4&amp;gt;&amp;lt;p&amp;gt;
     *       Attempting to install the relevant ISO 2- and 3-letter
     *       codes as the enumerated possible values is probably never
     *       going to be a realistic possibility.  
     *      &amp;lt;/p&amp;gt;&amp;lt;p&amp;gt;
     *       See BCP 47 at &amp;lt;a href="http://www.rfc-editor.org/rfc/bcp/bcp47.txt"&amp;gt;
     *        http://www.rfc-editor.org/rfc/bcp/bcp47.txt&amp;lt;/a&amp;gt;
     *       and the IANA language subtag registry at
     *       &amp;lt;a href="http://www.iana.org/assignments/language-subtag-registry"&amp;gt;
     *        http://www.iana.org/assignments/language-subtag-registry&amp;lt;/a&amp;gt;
     *       for further information.
     *      &amp;lt;/p&amp;gt;&amp;lt;p&amp;gt;
     *       The union allows for the 'un-declaration' of xml:lang with
     *       the empty string.
     *      &amp;lt;/p&amp;gt;&amp;lt;/div&amp;gt;
     * &lt;/pre&gt;
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLang() {
        return lang;
    }

    /**
     * Imposta il valore della proprietà lang.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     * @see #getLang()
     */
    public void setLang(String value) {
        this.lang = value;
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
            String theValue;
            theValue = this.getValue();
            strategy.appendField(locator, this, "value", buffer, theValue, (this.value!= null));
        }
        {
            String theLang;
            theLang = this.getLang();
            strategy.appendField(locator, this, "lang", buffer, theLang, (this.lang!= null));
        }
        return buffer;
    }

}
