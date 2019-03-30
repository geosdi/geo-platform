package org.geosdi.geoplatform.gml.api.parser.base.geometry.sextante.responsability.geojson;

import org.geojson.GeoJsonObject;
import org.geosdi.geoplatform.gml.api.AbstractGeometry;
import org.geosdi.geoplatform.gml.api.PropertyType;
import org.geosdi.geoplatform.gml.api.parser.base.geometry.sextante.responsability.ISextanteGeometryHandler;
import org.geosdi.geoplatform.gml.api.parser.exception.ParserException;

import java.io.Serializable;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public interface ISextanteGeometryGeoJsonHandler extends Serializable {

    /**
     * @param gmlGeometry
     * @return {@link GeoJsonObject}
     * @throws ParserException
     */
    GeoJsonObject parseGeometryAsGeoJson(AbstractGeometry gmlGeometry) throws ParserException;

    /**
     * @param propertyType
     * @return {@link GeoJsonObject}
     * @throws ParserException
     */
    GeoJsonObject parseGeometryAsGeoJson(PropertyType propertyType) throws ParserException;

    /**
     * @param theSuccessor
     */
    void setSuccessor(ISextanteGeometryHandler theSuccessor);
}
