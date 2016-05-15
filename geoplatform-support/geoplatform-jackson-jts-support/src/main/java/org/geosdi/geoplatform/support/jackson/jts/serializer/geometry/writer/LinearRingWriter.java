package org.geosdi.geoplatform.support.jackson.jts.serializer.geometry.writer;

import com.vividsolutions.jts.geom.LinearRing;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class LinearRingWriter extends AbstractLineWriter<LinearRing> {

    /**
     * @return {@link Class<LinearRing>}
     */
    @Override
    public Class<LinearRing> getKey() {
        return LinearRing.class;
    }
}
