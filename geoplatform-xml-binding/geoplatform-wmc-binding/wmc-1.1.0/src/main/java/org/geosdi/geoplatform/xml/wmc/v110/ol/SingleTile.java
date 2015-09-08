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
public class SingleTile extends ExtensionElement {

    private static final long serialVersionUID = 6409717519966760836L;
    //
    @XmlValue
    private boolean value;

    public SingleTile() {
    }

    public boolean isValue() {
        return value;
    }

    public void setValue(boolean value) {
        this.value = value;
    }

    @Override
    public ExtensionType getExtensionType() {
        return ExtensionType.SINGLE_TILE;
    }

    @Override
    public String toString() {
        return super.toString() + " {" +
                "value = " + value +
                '}';
    }
}
