package org.geosdi.geoplatform.xml.wmc.v110.ol;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlValue;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Units extends ExtensionElement {

    private static final long serialVersionUID = -1594897942998413426L;
    //
    @XmlValue
    private String value;

    public Units() {
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public ExtensionType getExtensionType() {
        return ExtensionType.UNITS;
    }

    @Override
    public String toString() {
        return super.toString() + " {" +
                "value = " + value +
                '}';
    }
}
