package org.geosdi.geoplatform.gml.api.parser.base.geometry.collection.responsability.geojson;

import org.geojson.GeoJsonObject;
import org.geojson.GeometryCollection;
import org.geosdi.geoplatform.gml.api.AbstractGeometricAggregate;
import org.geosdi.geoplatform.gml.api.parser.base.geometry.collection.responsability.IGeometryCollectionHandler;
import org.geosdi.geoplatform.gml.api.parser.exception.ParserException;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public abstract class GeometryCollectionGeoJsonHandler implements IGeometryCollectionGeoJsonHandler {

    protected IGeometryCollectionHandler successor;

    /**
     * @param gmlGeometry
     * @return {@link GeometryCollection}
     * @throws ParserException
     */
    protected <GeoJsonGeometry extends GeoJsonObject> GeoJsonGeometry forwardParseGeometryAsGeoJson(AbstractGeometricAggregate gmlGeometry) throws ParserException {
        if (successor != null) {
            return successor.parseGeometryAsGeoJson(gmlGeometry);
        }
        throw new ParserException("There is no Ring in this Chain to parse GML Geometry : " + gmlGeometry);
    }

    /**
     * @param theSuccessor
     */
    @Override
    public void setSuccessor(IGeometryCollectionHandler theSuccessor) {
        this.successor = theSuccessor;
    }
}