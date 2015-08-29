//
// Questo file è stato generato dall'architettura JavaTM per XML Binding (JAXB) Reference Implementation, v2.2.7 
// Vedere <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Qualsiasi modifica a questo file andrà persa durante la ricompilazione dello schema di origine. 
// Generato il: 2015.08.25 alle 10:30:08 PM CEST 
//


package org.geosdi.geoplatform.xml.gml.v212;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per NullType.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * <p>
 * <pre>
 * &lt;simpleType name="NullType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="inapplicable"/>
 *     &lt;enumeration value="unknown"/>
 *     &lt;enumeration value="unavailable"/>
 *     &lt;enumeration value="missing"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "NullType")
@XmlEnum
public enum NullType {

    @XmlEnumValue("inapplicable")
    INAPPLICABLE("inapplicable"),
    @XmlEnumValue("unknown")
    UNKNOWN("unknown"),
    @XmlEnumValue("unavailable")
    UNAVAILABLE("unavailable"),
    @XmlEnumValue("missing")
    MISSING("missing");
    private final String value;

    NullType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static NullType fromValue(String v) {
        for (NullType c: NullType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
