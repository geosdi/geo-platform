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
 * <p>Classe Java per KnotTypesType.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * <p>
 * <pre>
 * &lt;simpleType name="KnotTypesType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="uniform"/>
 *     &lt;enumeration value="quasiUniform"/>
 *     &lt;enumeration value="piecewiseBezier"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "KnotTypesType")
@XmlEnum
public enum KnotTypesType {

    @XmlEnumValue("uniform")
    UNIFORM("uniform"),
    @XmlEnumValue("quasiUniform")
    QUASI_UNIFORM("quasiUniform"),
    @XmlEnumValue("piecewiseBezier")
    PIECEWISE_BEZIER("piecewiseBezier");
    private final String value;

    KnotTypesType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static KnotTypesType fromValue(String v) {
        for (KnotTypesType c: KnotTypesType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
