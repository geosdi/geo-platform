package org.geosdi.geoplatform.xml.wmc.v110.ol;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlTransient;
import java.io.Serializable;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@XmlTransient
@XmlAccessorType(XmlAccessType.FIELD)
@XmlSeeAlso(value = {MaxExtent.class, TileSize.class, Transparent.class,
        NumZoomLevels.class, Units.class, BaseLayer.class,
        DisplayInLayerSwitcher.class, SingleTile.class})
public abstract class ExtensionElement implements Serializable {


    private static final long serialVersionUID = 5676144625784315942L;

    public abstract ExtensionType getExtensionType();

    @Override
    public String toString() {
        return getClass().getSimpleName()
                + " - ExtensionType : " + getExtensionType();
    }
}
