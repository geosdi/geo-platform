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
public class Transparent extends ExtensionElement {

    private static final long serialVersionUID = -4362665972968018283L;
    //
    @XmlValue
    private boolean value;

    public Transparent() {
    }

    public boolean isValue() {
        return value;
    }

    public void setValue(boolean value) {
        this.value = value;
    }

    @Override
    public ExtensionType getExtensionType() {
        return ExtensionType.TRANSPARENT;
    }

    @Override
    public String toString() {
        return super.toString() + " {" +
                "value=" + value +
                '}';
    }
}
