package org.geosdi.geoplatform.support.jackson.jts;

import org.geojson.Geometry;
import org.geosdi.geoplatform.support.jackson.JacksonSupport;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public interface IGPJacksonJTSSupport extends JacksonSupport {

    /**
     * @param theGeom
     * @return
     * @throws Exception
     */
    Geometry convertJtsGeometryToGeoJson(com.vividsolutions.jts.geom.Geometry theGeom) throws Exception;

    /**
     * @param theGeoJsonGeometry
     * @return {@link com.vividsolutions.jts.geom.Geometry}
     * @throws Exception
     */
    com.vividsolutions.jts.geom.Geometry convertGeoJsonGeometryToJts(Geometry theGeoJsonGeometry) throws Exception;
}
