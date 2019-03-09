//
// Questo file è stato generato dall'architettura JavaTM per XML Binding (JAXB) Reference Implementation, v2.3.0 
// Vedere <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a> 
// Qualsiasi modifica a questo file andrà persa durante la ricompilazione dello schema di origine. 
// Generato il: 2019.03.07 alle 09:04:14 AM CET 
//


package org.geosdi.geoplatform.xml.filter.v110;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per ComparisonOperatorType.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * <p>
 * <pre>
 * &lt;simpleType name="ComparisonOperatorType"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="LessThan"/&gt;
 *     &lt;enumeration value="GreaterThan"/&gt;
 *     &lt;enumeration value="LessThanEqualTo"/&gt;
 *     &lt;enumeration value="GreaterThanEqualTo"/&gt;
 *     &lt;enumeration value="EqualTo"/&gt;
 *     &lt;enumeration value="NotEqualTo"/&gt;
 *     &lt;enumeration value="Like"/&gt;
 *     &lt;enumeration value="Between"/&gt;
 *     &lt;enumeration value="NullCheck"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "ComparisonOperatorType")
@XmlEnum
public enum ComparisonOperatorType {

    @XmlEnumValue("LessThan")
    LESS_THAN("LessThan"),
    @XmlEnumValue("GreaterThan")
    GREATER_THAN("GreaterThan"),
    @XmlEnumValue("LessThanEqualTo")
    LESS_THAN_EQUAL_TO("LessThanEqualTo"),
    @XmlEnumValue("GreaterThanEqualTo")
    GREATER_THAN_EQUAL_TO("GreaterThanEqualTo"),
    @XmlEnumValue("EqualTo")
    EQUAL_TO("EqualTo"),
    @XmlEnumValue("NotEqualTo")
    NOT_EQUAL_TO("NotEqualTo"),
    @XmlEnumValue("Like")
    LIKE("Like"),
    @XmlEnumValue("Between")
    BETWEEN("Between"),
    @XmlEnumValue("NullCheck")
    NULL_CHECK("NullCheck");
    private final String value;

    ComparisonOperatorType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static ComparisonOperatorType fromValue(String v) {
        for (ComparisonOperatorType c: ComparisonOperatorType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
