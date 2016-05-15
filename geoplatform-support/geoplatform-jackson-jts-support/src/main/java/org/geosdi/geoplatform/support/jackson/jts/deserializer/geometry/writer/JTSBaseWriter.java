package org.geosdi.geoplatform.support.jackson.jts.deserializer.geometry.writer;

import com.vividsolutions.jts.geom.Geometry;
import org.geojson.GeoJsonObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public abstract class JTSBaseWriter<GEOJSON extends GeoJsonObject, JTS extends Geometry>
        implements JTSGeometryWriter<GEOJSON, JTS> {

    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * @param geojson
     * @return {@link JTS}
     * @throws Exception
     */
    @Override
    public JTS writeGeometry(GEOJSON geojson) throws Exception {
        return buildJTSGeometry(geojson);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName();
    }
}
