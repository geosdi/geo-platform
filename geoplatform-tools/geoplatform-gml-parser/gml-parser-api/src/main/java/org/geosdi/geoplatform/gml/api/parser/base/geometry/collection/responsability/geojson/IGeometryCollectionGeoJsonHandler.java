package org.geosdi.geoplatform.gml.api.parser.base.geometry.collection.responsability.geojson;

import org.geojson.GeoJsonObject;
import org.geojson.GeometryCollection;
import org.geosdi.geoplatform.gml.api.AbstractGeometricAggregate;
import org.geosdi.geoplatform.gml.api.parser.base.geometry.collection.responsability.IGeometryCollectionHandler;
import org.geosdi.geoplatform.gml.api.parser.exception.ParserException;

import java.io.Serializable;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public interface IGeometryCollectionGeoJsonHandler extends Serializable {

    /**
     * @param gmlGeometry
     * @return {@link GeometryCollection}
     * @throws ParserException
     */
    <GeoJsonGeometry extends GeoJsonObject> GeoJsonGeometry parseGeometryAsGeoJson(AbstractGeometricAggregate gmlGeometry) throws ParserException;

    /**
     * @param theSuccessor
     */
    void setSuccessor(IGeometryCollectionHandler theSuccessor);
}