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
 * <p>Classe Java per SpatialOperatorNameType.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * <p>
 * <pre>
 * &lt;simpleType name="SpatialOperatorNameType"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="BBOX"/&gt;
 *     &lt;enumeration value="Equals"/&gt;
 *     &lt;enumeration value="Disjoint"/&gt;
 *     &lt;enumeration value="Intersects"/&gt;
 *     &lt;enumeration value="Touches"/&gt;
 *     &lt;enumeration value="Crosses"/&gt;
 *     &lt;enumeration value="Within"/&gt;
 *     &lt;enumeration value="Contains"/&gt;
 *     &lt;enumeration value="Overlaps"/&gt;
 *     &lt;enumeration value="Beyond"/&gt;
 *     &lt;enumeration value="DWithin"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "SpatialOperatorNameType")
@XmlEnum
public enum SpatialOperatorNameType {

    BBOX("BBOX"),
    @XmlEnumValue("Equals")
    EQUALS("Equals"),
    @XmlEnumValue("Disjoint")
    DISJOINT("Disjoint"),
    @XmlEnumValue("Intersects")
    INTERSECTS("Intersects"),
    @XmlEnumValue("Touches")
    TOUCHES("Touches"),
    @XmlEnumValue("Crosses")
    CROSSES("Crosses"),
    @XmlEnumValue("Within")
    WITHIN("Within"),
    @XmlEnumValue("Contains")
    CONTAINS("Contains"),
    @XmlEnumValue("Overlaps")
    OVERLAPS("Overlaps"),
    @XmlEnumValue("Beyond")
    BEYOND("Beyond"),
    @XmlEnumValue("DWithin")
    D_WITHIN("DWithin");
    private final String value;

    SpatialOperatorNameType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static SpatialOperatorNameType fromValue(String v) {
        for (SpatialOperatorNameType c: SpatialOperatorNameType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
