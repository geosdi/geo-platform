package org.geosdi.geoplatform.xml.wmc.v110.ol;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public enum ExtensionType {

    MAX_EXTENT("ol:maxExtent"),
    TILE_SIZE("ol:tileSize"),
    TRANSPARENT("ol:transparent"),
    NUM_ZOOM_LEVELS("ol:numZoomLevels"),
    UNITS("ol:units"),
    BASE_LAYER("ol:isBaseLayer"),
    DISPLAY_IN_LAYER_SWITCHER("ol:displayInLayerSwitcher"),
    SINGLE_TILE("ol:singleTile");

    private final String value;

    ExtensionType(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return this.value;
    }
}
