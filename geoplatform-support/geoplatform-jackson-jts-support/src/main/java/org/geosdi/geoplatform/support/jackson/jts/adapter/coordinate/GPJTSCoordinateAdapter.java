package org.geosdi.geoplatform.support.jackson.jts.adapter.coordinate;

import org.locationtech.jts.geom.Coordinate;

import javax.annotation.Nonnull;
import java.io.Serializable;
import java.util.Arrays;

import static com.google.common.base.Preconditions.checkArgument;
import static javax.annotation.meta.When.NEVER;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public abstract class GPJTSCoordinateAdapter implements Serializable {

    protected final Coordinate locationtechCoordinate;
    protected final com.vividsolutions.jts.geom.Coordinate vividsolutionsCoordinate;

    /**
     * @param locationtechCoordinate
     * @param vividsolutionsCoordinate
     */
    GPJTSCoordinateAdapter(Coordinate locationtechCoordinate, com.vividsolutions.jts.geom.Coordinate vividsolutionsCoordinate) {
        this.locationtechCoordinate = locationtechCoordinate;
        this.vividsolutionsCoordinate = vividsolutionsCoordinate;
    }

    /**
     * @param theLocationtechCoordinate
     * @return {@link GPJTSCoordinateAdapter}
     */
    public static GPJTSCoordinateAdapter adapt(@Nonnull(when = NEVER) Coordinate theLocationtechCoordinate) {
        checkArgument(theLocationtechCoordinate != null, "The Parameter locationtechCoordinate must not be null.");
        return new JTSCoordinateAdapter(theLocationtechCoordinate, null);
    }

    /**
     * @param theVividsolutionsCoordinate
     * @return {@link GPJTSCoordinateAdapter}
     */
    public static GPJTSCoordinateAdapter adapt(@Nonnull(when = NEVER) com.vividsolutions.jts.geom.Coordinate theVividsolutionsCoordinate) {
        checkArgument(theVividsolutionsCoordinate != null, "The Parameter vividsolutionsCoordinate must not be null.");
        return new JTSCoordinateAdapter(null, theVividsolutionsCoordinate);
    }

    /**
     * @param theLocationtechCoordinates
     * @return {@link GPJTSCoordinateAdapter[]}
     */
    public static GPJTSCoordinateAdapter[] adapt(@Nonnull(when = NEVER) Coordinate[] theLocationtechCoordinates) {
        checkArgument(theLocationtechCoordinates != null, "The Parameter locationtechCoordinates must not be null.");
        return Arrays.stream(theLocationtechCoordinates)
                .map(coordinate -> new JTSCoordinateAdapter(coordinate, null))
                .toArray(n -> new JTSCoordinateAdapter[n]);
    }

    /**
     * @param theVividsolutionsCoordinates
     * @return {@link GPJTSCoordinateAdapter[]}
     */
    public static GPJTSCoordinateAdapter[] adapt(@Nonnull(when = NEVER) com.vividsolutions.jts.geom.Coordinate[] theVividsolutionsCoordinates) {
        checkArgument(theVividsolutionsCoordinates != null, "The Parameter vividsolutionsCoordinates must not be null.");
        return Arrays.stream(theVividsolutionsCoordinates)
                .map(coordinate -> new JTSCoordinateAdapter(null, coordinate))
                .toArray(n -> new JTSCoordinateAdapter[n]);
    }

    /**
     * @return {@link Double}
     */
    public abstract double x();

    /**
     * @return {@link Double}
     */
    public abstract double y();

    /**
     * @return {@link Double}
     */
    public abstract double z();
}