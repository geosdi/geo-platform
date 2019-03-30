package org.geosdi.geoplatform.gml.api.parser.base.geometry.sextante.responsability;

import org.geosdi.geoplatform.gml.api.AbstractGeometry;
import org.geosdi.geoplatform.gml.api.PropertyType;
import org.geosdi.geoplatform.gml.api.parser.base.geometry.sextante.responsability.geojson.ISextanteGeometryGeoJsonHandler;
import org.geosdi.geoplatform.gml.api.parser.exception.ParserException;
import org.locationtech.jts.geom.Geometry;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public interface ISextanteGeometryHandler extends ISextanteGeometryGeoJsonHandler {

    /**
     * @param gmlGeometry
     * @return {@link Geometry}
     * @throws ParserException
     */
    Geometry parseGeometry(AbstractGeometry gmlGeometry) throws ParserException;

    /**
     * @param propertyType
     * @return {@link Geometry}
     * @throws ParserException
     */
    Geometry parseGeometry(PropertyType propertyType) throws ParserException;
}
