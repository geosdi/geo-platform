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
public class BaseLayer extends ExtensionElement {

    private static final long serialVersionUID = 7856214968232465398L;
    //
    @XmlValue
    private boolean value;

    public BaseLayer() {
    }

    public boolean isValue() {
        return value;
    }

    public void setValue(boolean value) {
        this.value = value;
    }

    @Override
    public ExtensionType getExtensionType() {
        return ExtensionType.BASE_LAYER;
    }

    @Override
    public String toString() {
        return super.toString() + " {" +
                "value = " + value +
                '}';
    }
}
