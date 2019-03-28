package org.geosdi.geoplatform.gml.api.parser.base.geometry.curve;

import org.geojson.LngLatAlt;
import org.geosdi.geoplatform.gml.api.AbstractCurve;
import org.geosdi.geoplatform.gml.api.Curve;
import org.geosdi.geoplatform.gml.api.CurveProperty;
import org.geosdi.geoplatform.gml.api.parser.base.AbstractGMLBaseParser;
import org.geosdi.geoplatform.gml.api.parser.base.AbstractGMLBaseSRSParser;
import org.geosdi.geoplatform.gml.api.parser.base.coordinate.CoordinateBaseParser;
import org.geosdi.geoplatform.gml.api.parser.base.geometry.curve.responsibility.AbstractCurveHandler;
import org.geosdi.geoplatform.gml.api.parser.base.geometry.curve.responsibility.CoordCurveHandler;
import org.geosdi.geoplatform.gml.api.parser.base.parameter.GMLBaseParametersRepo;
import org.geosdi.geoplatform.gml.api.parser.exception.ParserException;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.LineString;
import org.locationtech.jts.geom.MultiLineString;

import javax.annotation.Nonnull;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;
import static javax.annotation.meta.When.NEVER;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
abstract class GMLCurveParser extends AbstractGMLBaseParser<AbstractCurve, CurveProperty, MultiLineString, org.geojson.MultiLineString> {

    protected CoordinateBaseParser coordinateParser = GMLBaseParametersRepo.getDefaultCoordinateBaseParser();
    protected AbstractCurveHandler coordCurveHandler = new CoordCurveHandler();

    GMLCurveParser(@Nonnull(when = NEVER) GeometryFactory theGeometryFactory, @Nonnull(when = NEVER) AbstractGMLBaseSRSParser theSrsParser) {
        super(theGeometryFactory, theSrsParser);
    }

    /**
     * @param theGMLlGeometry
     * @return {@link MultiLineString}
     * @throws ParserException
     */
    @Override
    protected MultiLineString canParseGeometry(AbstractCurve theGMLlGeometry) throws ParserException {
        checkArgument(theGMLlGeometry instanceof Curve, "For The moment only Curve can be parsed.");
        Curve gmlGeometry = (Curve) theGMLlGeometry;
        Collection<LineString> lines = this.parseGeometry(gmlGeometry);
        MultiLineString multiLineString = geometryFactory.createMultiLineString(lines.stream().toArray(s -> new LineString[s]));
        this.srsParser.parseSRS(gmlGeometry, multiLineString);
        return multiLineString;
    }

    /**
     * @param propertyType
     * @return {@link MultiLineString}
     * @throws ParserException
     */
    @Override
    public MultiLineString parseGeometry(CurveProperty propertyType) throws ParserException {
        checkNotNull(propertyType, "The CurveProperty must not be null.");
        if (propertyType.isSetCurve()) {
            return super.parseGeometry(propertyType.getAbstractCurve());
        }
        throw new ParserException("There is no Curve Geometry to Parse.");
    }

    /**
     * @param theGMLlGeometry
     * @return {@link org.geojson.MultiLineString}
     * @throws ParserException
     */
    @Override
    protected org.geojson.MultiLineString canParseGeometryAsGeoJson(AbstractCurve theGMLlGeometry) throws ParserException {
        checkArgument(theGMLlGeometry instanceof Curve, "For The moment only Curve can be parsed.");
        Curve gmlGeometry = (Curve) theGMLlGeometry;
        Collection<org.geojson.LineString> lines = this.parseGeometryAsGeoJson(gmlGeometry);
        List<LngLatAlt> lngLatAlts = lines.stream()
                .filter(Objects::nonNull)
                .map(lineString -> lineString.getCoordinates())
                .filter(Objects::nonNull)
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
        org.geojson.MultiLineString multiLineString = new org.geojson.MultiLineString(lngLatAlts);
        multiLineString.setCrs(this.srsParser.parseSRS(gmlGeometry));
        return multiLineString;
    }

    /**
     * @param propertyType
     * @return {@link org.geojson.MultiLineString}
     * @throws ParserException
     */
    @Override
    public org.geojson.MultiLineString parseGeometryAsGeoJson(CurveProperty propertyType) throws ParserException {
        checkNotNull(propertyType, "The CurveProperty must not be null.");
        if (propertyType.isSetCurve()) {
            return super.parseGeometryAsGeoJson(propertyType.getAbstractCurve());
        }
        throw new ParserException("There is no Curve Geometry to Parse.");
    }

    /**
     * @param gmlGeometry
     * @return {@link Collection<LineString>}
     * @throws ParserException
     */
    public abstract Collection<LineString> parseGeometry(@Nonnull(when = NEVER) Curve gmlGeometry) throws ParserException;

    /**
     * @param gmlGeometry
     * @return {@link Collection<org.geojson.LineString>}
     * @throws ParserException
     */
    public abstract Collection<org.geojson.LineString> parseGeometryAsGeoJson(@Nonnull(when = NEVER) Curve gmlGeometry) throws ParserException;
}