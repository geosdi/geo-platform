package org.geosdi.geoplatform.gml.api.parser.base.geometry.collection.responsability;

import org.geosdi.geoplatform.gml.api.AbstractGeometricAggregate;
import org.geosdi.geoplatform.gml.api.parser.base.geometry.collection.responsability.geojson.IGeometryCollectionGeoJsonHandler;
import org.geosdi.geoplatform.gml.api.parser.exception.ParserException;
import org.locationtech.jts.geom.GeometryCollection;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public interface IGeometryCollectionHandler extends IGeometryCollectionGeoJsonHandler {

    /**
     * @param gmlGeometry
     * @return {@link GeometryCollection}
     * @throws ParserException
     */
    GeometryCollection parseGeometry(AbstractGeometricAggregate gmlGeometry) throws ParserException;
}