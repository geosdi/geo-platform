package org.geosdi.geoplatform.xml.wmc.v110.ol;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class TileSize extends ExtensionElement {

    private static final long serialVersionUID = -8153654626019058299L;
    //
    @XmlAttribute
    private int width;
    @XmlAttribute
    private int height;

    public TileSize() {
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    @Override
    public ExtensionType getExtensionType() {
        return ExtensionType.TILE_SIZE;
    }

    @Override
    public String toString() {
        return super.toString() + " {" +
                "width=" + width +
                ", height=" + height +
                '}';
    }
}
