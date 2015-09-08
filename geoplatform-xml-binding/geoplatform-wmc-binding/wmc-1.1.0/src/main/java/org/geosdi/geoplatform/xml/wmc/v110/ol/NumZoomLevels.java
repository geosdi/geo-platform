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
public class NumZoomLevels extends ExtensionElement {

    private static final long serialVersionUID = 3003894100959857508L;
    //
    @XmlValue
    private int value;

    public NumZoomLevels() {
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    @Override
    public ExtensionType getExtensionType() {
        return ExtensionType.NUM_ZOOM_LEVELS;
    }

    @Override
    public String toString() {
        return super.toString() + " {" +
                "value=" + value +
                '}';
    }
}
