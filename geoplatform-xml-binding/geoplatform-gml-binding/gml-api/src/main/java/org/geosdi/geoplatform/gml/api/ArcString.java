package org.geosdi.geoplatform.gml.api;

import java.math.BigInteger;
import java.util.List;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public interface ArcString extends AbstractCurveSegment {

    /**
     * @return {@link Coordinates}
     */
    Coordinates getCoordinates();

    /**
     * @return {@link Boolean}
     */
    boolean isSetCoordinates();

    /**
     * @return {@link DirectPositionList}
     */
    DirectPositionList getPosList();

    /**
     * @return {@link Boolean}
     */
    boolean isSetPosList();

    /**
     * @return {@link List}
     */
    List getPosOrPointPropertyOrPointRep();

    /**
     * @return {@link BigInteger}
     */
    BigInteger getNumArc();

    /**
     * @return {@link CurveInterpolation}
     */
    CurveInterpolation getInterpolation();
}