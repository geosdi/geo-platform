/**
 *
 *    geo-platform
 *    Rich webgis framework
 *    http://geo-platform.org
 *   ====================================================================
 *
 *   Copyright (C) 2008-2021 geoSDI Group (CNR IMAA - Potenza - ITALY).
 *
 *   This program is free software: you can redistribute it and/or modify it
 *   under the terms of the GNU General Public License as published by
 *   the Free Software Foundation, either version 3 of the License, or
 *   (at your option) any later version. This program is distributed in the
 *   hope that it will be useful, but WITHOUT ANY WARRANTY; without
 *   even the implied warranty of MERCHANTABILITY or FITNESS FOR
 *   A PARTICULAR PURPOSE. See the GNU General Public License
 *   for more details. You should have received a copy of the GNU General
 *   Public License along with this program. If not, see http://www.gnu.org/licenses/
 *
 *   ====================================================================
 *
 *   Linking this library statically or dynamically with other modules is
 *   making a combined work based on this library. Thus, the terms and
 *   conditions of the GNU General Public License cover the whole combination.
 *
 *   As a special exception, the copyright holders of this library give you permission
 *   to link this library with independent modules to produce an executable, regardless
 *   of the license terms of these independent modules, and to copy and distribute
 *   the resulting executable under terms of your choice, provided that you also meet,
 *   for each linked independent module, the terms and conditions of the license of
 *   that module. An independent module is a module which is not derived from or
 *   based on this library. If you modify this library, you may extend this exception
 *   to your version of the library, but you are not obligated to do so. If you do not
 *   wish to do so, delete this exception statement from your version.
 */
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