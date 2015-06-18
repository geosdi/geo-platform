//
// Questo file è stato generato dall'architettura JavaTM per XML Binding (JAXB) Reference Implementation, v2.2.7 
// Vedere <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Qualsiasi modifica a questo file andrà persa durante la ricompilazione dello schema di origine. 
// Generato il: 2015.06.18 alle 12:52:42 AM CEST 
//


package org.geosdi.geoplatform.xml.gml.v320;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per IncrementOrder.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * <p>
 * <pre>
 * &lt;simpleType name="IncrementOrder">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="+x+y"/>
 *     &lt;enumeration value="+y+x"/>
 *     &lt;enumeration value="+x-y"/>
 *     &lt;enumeration value="-x-y"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "IncrementOrder")
@XmlEnum
public enum IncrementOrder {

    @XmlEnumValue("+x+y")
    VALUE_1("+x+y"),
    @XmlEnumValue("+y+x")
    VALUE_2("+y+x"),
    @XmlEnumValue("+x-y")
    VALUE_3("+x-y"),
    @XmlEnumValue("-x-y")
    VALUE_4("-x-y");
    private final String value;

    IncrementOrder(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static IncrementOrder fromValue(String v) {
        for (IncrementOrder c: IncrementOrder.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
