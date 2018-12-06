package org.geosdi.geoplatform.support.jackson.jts.adapter;

import org.geosdi.geoplatform.support.jackson.jts.adapter.coordinate.GPJTSCoordinateAdapter;

import java.io.Serializable;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public interface GPJTSGeometryAdapter extends Serializable {

    /**
     * @return {@link Integer}
     */
    int getSRID();

    /**
     * @return {@link Integer}
     */
    int getNumGeometries();

    /**
     * @param n
     * @return {@link GPJTSGeometryAdapter}
     */
    GPJTSGeometryAdapter getGeometryN(int n);

    /**
     * @param <Coordinate>
     * @return {@link Coordinate}
     */
    <Coordinate extends GPJTSCoordinateAdapter> Coordinate getCoordinate();

    /**
     * @param <Coordinate>
     * @return {@link Coordinate[]}
     */
    <Coordinate extends GPJTSCoordinateAdapter> Coordinate[] getCoordinates();
}