package org.geosdi.geoplatform.support.jackson.jts.adapter.coordinate;

import org.locationtech.jts.geom.Coordinate;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class JTSCoordinateAdapter extends GPJTSCoordinateAdapter {

    /**
     * @param locationtechCoordinate
     * @param vividsolutionsCoordinate
     */
    JTSCoordinateAdapter(Coordinate locationtechCoordinate, com.vividsolutions.jts.geom.Coordinate vividsolutionsCoordinate) {
        super(locationtechCoordinate, vividsolutionsCoordinate);
    }

    /**
     * @return {@link Double}
     */
    @Override
    public double x() {
        return (this.locationtechCoordinate != null) ? this.locationtechCoordinate.x : this.vividsolutionsCoordinate.x;
    }

    /**
     * @return {@link Double}
     */
    @Override
    public double y() {
        return (this.locationtechCoordinate != null) ? this.locationtechCoordinate.y : this.vividsolutionsCoordinate.y;
    }

    /**
     * @return {@link Double}
     */
    @Override
    public double z() {
        return (this.locationtechCoordinate != null) ? this.locationtechCoordinate.getZ() : this.vividsolutionsCoordinate.z;
    }
}