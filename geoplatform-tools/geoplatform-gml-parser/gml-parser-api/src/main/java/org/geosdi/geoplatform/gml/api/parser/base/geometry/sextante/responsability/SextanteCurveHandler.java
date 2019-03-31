package org.geosdi.geoplatform.gml.api.parser.base.geometry.sextante.responsability;

import org.geojson.GeoJsonObject;
import org.geosdi.geoplatform.gml.api.*;
import org.geosdi.geoplatform.gml.api.parser.base.geometry.curve.GMLBaseCurveParser;
import org.geosdi.geoplatform.gml.api.parser.base.parameter.GMLBaseParametersRepo;
import org.geosdi.geoplatform.gml.api.parser.exception.ParserException;
import org.locationtech.jts.geom.Geometry;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
class SextanteCurveHandler extends SextanteGeometryHandler {

    private final GMLBaseCurveParser curveParser = GMLBaseParametersRepo.getDefaultCurveParser();

    SextanteCurveHandler() {
        super.setSuccessor(new SextanteMultiCurveHandler());
    }

    /**
     * @param gmlGeometry
     * @return {@link Geometry}
     * @throws ParserException
     */
    @Override
    public Geometry parseGeometry(AbstractGeometry gmlGeometry) throws ParserException {
        return isCompatibleGeometry(gmlGeometry)
                ? curveParser.parseGeometry((AbstractCurve) gmlGeometry)
                : super.forwardParseGeometry(gmlGeometry);
    }

    /**
     * @param propertyType
     * @return {@link Geometry}
     * @throws ParserException
     */
    @Override
    public Geometry parseGeometry(PropertyType propertyType) throws ParserException {
        return isCompatibleProperty(propertyType)
                ? curveParser.parseGeometry((CurveProperty) propertyType)
                : super.forwardParseGeometry(propertyType);
    }

    /**
     * @param gmlGeometry
     * @return {@link GeoJsonObject}
     * @throws ParserException
     */
    @Override
    public GeoJsonObject parseGeometryAsGeoJson(AbstractGeometry gmlGeometry) throws ParserException {
        return isCompatibleGeometry(gmlGeometry)
                ? curveParser.parseGeometryAsGeoJson((AbstractCurve) gmlGeometry)
                : super.forwardParseGeometryAsGeoJson(gmlGeometry);
    }

    /**
     * @param propertyType
     * @return {@link GeoJsonObject}
     * @throws ParserException
     */
    @Override
    public GeoJsonObject parseGeometryAsGeoJson(PropertyType propertyType) throws ParserException {
        return isCompatibleProperty(propertyType)
                ? curveParser.parseGeometryAsGeoJson((CurveProperty) propertyType)
                : super.forwardParseGeometryAsGeoJson(propertyType);
    }

    /**
     * @param gmlGeometry
     * @return {@link Boolean}
     */
    @Override
    protected boolean isCompatibleGeometry(Object gmlGeometry) {
        return gmlGeometry instanceof Curve;
    }

    /**
     * @param propertyType
     * @return {@link Boolean}
     */
    @Override
    protected boolean isCompatibleProperty(Object propertyType) {
        return propertyType instanceof CurveProperty;
    }
}