//
// Questo file è stato generato dall'architettura JavaTM per XML Binding (JAXB) Reference Implementation, v2.2.11 
// Vedere <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Qualsiasi modifica a questo file andrà persa durante la ricompilazione dello schema di origine. 
// Generato il: 2017.11.30 alle 03:34:58 PM CET 
//


package org.geosdi.geoplatform.xml.xlink.v100;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per typeType.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * <p>
 * <pre>
 * &lt;simpleType name="typeType"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}token"&gt;
 *     &lt;enumeration value="simple"/&gt;
 *     &lt;enumeration value="extended"/&gt;
 *     &lt;enumeration value="title"/&gt;
 *     &lt;enumeration value="resource"/&gt;
 *     &lt;enumeration value="locator"/&gt;
 *     &lt;enumeration value="arc"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "typeType")
@XmlEnum
public enum TypeType {

    @XmlEnumValue("simple")
    SIMPLE("simple"),
    @XmlEnumValue("extended")
    EXTENDED("extended"),
    @XmlEnumValue("title")
    TITLE("title"),
    @XmlEnumValue("resource")
    RESOURCE("resource"),
    @XmlEnumValue("locator")
    LOCATOR("locator"),
    @XmlEnumValue("arc")
    ARC("arc");
    private final String value;

    TypeType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static TypeType fromValue(String v) {
        for (TypeType c: TypeType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
