package org.geosdi.geoplatform.support.jackson.jts.serializer.geometry;

import com.fasterxml.jackson.core.JsonGenerator;
import com.vividsolutions.jts.geom.Geometry;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public interface IGPGeoJsonGeometryHandler<G extends Geometry> {

    /**
     * @param geometry
     * @param jsonGenerator
     * @throws Exception
     */
    void parseGeometry(Geometry geometry, JsonGenerator jsonGenerator)
            throws Exception;

    /**
     * @return
     */
    Class<G> getCompatibleGeometry();

    /**
     * @param theSuccessor
     */
    <Successor extends IGPGeoJsonGeometryHandler> void setSuccessor(Successor theSuccessor);
}
