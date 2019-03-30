package org.geosdi.geoplatform.gml.api.parser.base.geometry.sextante.responsability.geojson;

import org.geojson.GeoJsonObject;
import org.geosdi.geoplatform.gml.api.AbstractGeometry;
import org.geosdi.geoplatform.gml.api.PropertyType;
import org.geosdi.geoplatform.gml.api.parser.base.geometry.sextante.responsability.ISextanteGeometryHandler;
import org.geosdi.geoplatform.gml.api.parser.exception.ParserException;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public abstract class SextanteGeometryGeoJsonHandler implements ISextanteGeometryGeoJsonHandler {

    protected ISextanteGeometryHandler successor;

    /**
     * @param gmlGeometry
     * @return {@link GeoJsonObject}
     * @throws ParserException
     */
    protected GeoJsonObject forwardParseGeometryAsGeoJson(AbstractGeometry gmlGeometry) throws ParserException {
        if (successor != null) {
            return successor.parseGeometryAsGeoJson(gmlGeometry);
        }
        throw new ParserException("There is no Ring in this Chain to Parse this GML Geometry : " + gmlGeometry);
    }

    /**
     * @param propertyType
     * @return {@link GeoJsonObject}
     * @throws ParserException
     */
    protected GeoJsonObject forwardParseGeometryAsGeoJson(PropertyType propertyType) throws ParserException {
        if (successor != null) {
            return successor.parseGeometryAsGeoJson(propertyType);
        }
        throw new ParserException("There is no Ring in this Chain to parse GML PropertyType : " + propertyType);
    }

    /**
     * @param gmlGeometry
     * @return {@link Boolean}
     */
    protected abstract boolean isCompatibleGeometry(Object gmlGeometry);

    /**
     * @param propertyType
     * @return {@link Boolean}
     */
    protected abstract boolean isCompatibleProperty(Object propertyType);

    /**
     * @param theSuccessor the successor to set
     */
    @Override
    public void setSuccessor(ISextanteGeometryHandler theSuccessor) {
        this.successor = theSuccessor;
    }
}