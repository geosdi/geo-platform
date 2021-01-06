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
package org.geosdi.geoplatform.gml.api.parser.base.geometry.linerarring.outerchain;

import org.geojson.LineString;
import org.geojson.LngLatAlt;
import org.geosdi.geoplatform.gml.api.LinearRing;
import org.geosdi.geoplatform.gml.api.parser.base.AbstractGMLBaseSRSParser;
import org.geosdi.geoplatform.gml.api.parser.base.coordinate.CoordinateBaseParser;
import org.geosdi.geoplatform.gml.api.parser.base.geometry.linerarring.internalchain.InternalDirectPosLinerarRingHandler;
import org.geosdi.geoplatform.gml.api.parser.base.geometry.point.GMLBasePointParser;
import org.geosdi.geoplatform.gml.api.parser.base.geometry.responsibility.AbstractGeometryHandler;
import org.geosdi.geoplatform.gml.api.parser.exception.ParserException;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;

import javax.annotation.Nonnull;
import javax.xml.bind.JAXBElement;
import java.util.ArrayList;
import java.util.List;

import static com.google.common.base.Preconditions.checkArgument;
import static javax.annotation.meta.When.NEVER;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class MixedLinearRingGeometryHandler extends AbstractGeometryHandler<LinearRing, org.locationtech.jts.geom.LinearRing, GMLBasePointParser, CoordinateBaseParser, LineString>
        implements MixedLineraRingHandler {

    private final AbstractGMLBaseSRSParser srsParser;
    private InternalDirectPosLinerarRingHandler internalDirectPosHandler;

    /**
     * @param theSRSParser
     */
    public MixedLinearRingGeometryHandler(@Nonnull(when = NEVER) AbstractGMLBaseSRSParser theSRSParser) {
        checkArgument(theSRSParser != null, "The Parameter srsParser must not be null.");
        this.srsParser = theSRSParser;
        super.setSuccessor(new DirectPosLinearRingGeometryHandler(this.srsParser));
        this.internalDirectPosHandler = new InternalDirectPosLinerarRingHandler();
    }

    /**
     * @param geometryFactory
     * @param gmlGeometry
     * @param firstParser
     * @param secondParser
     * @return {@link org.locationtech.jts.geom.LinearRing}
     * @throws ParserException
     */
    @Override
    public org.locationtech.jts.geom.LinearRing buildGeometry(GeometryFactory geometryFactory, LinearRing gmlGeometry,
            GMLBasePointParser firstParser, CoordinateBaseParser secondParser) throws ParserException {
        logger.trace("###########################Called : {}#buildGeometry", this.getClass().getSimpleName());
        return gmlGeometry.isSetPosOrPointPropertyOrPointRep()
                ? buildLineString(geometryFactory, gmlGeometry, firstParser, secondParser)
                : buildGeometry(geometryFactory, gmlGeometry, secondParser);
    }

    /**
     * @param geometryFactory
     * @param gmlGeometry
     * @param parser
     * @return {@link org.locationtech.jts.geom.LinearRing}
     * @throws ParserException
     */
    @Override
    public org.locationtech.jts.geom.LinearRing buildGeometry(GeometryFactory geometryFactory, LinearRing gmlGeometry,
            CoordinateBaseParser parser) throws ParserException {
        logger.trace("###########################Called : {}#buildGeometry", this.getClass().getSimpleName());
        return super.forwardBuildGeometry(geometryFactory, gmlGeometry, parser);
    }

    /**
     * @param geometryFactory
     * @param gmlGeometry
     * @param firstParser
     * @param secondParser
     * @return {@link org.locationtech.jts.geom.LinearRing}
     * @throws ParserException
     */
    @Override
    public org.locationtech.jts.geom.LinearRing buildLineString(GeometryFactory geometryFactory, LinearRing gmlGeometry,
            GMLBasePointParser firstParser, CoordinateBaseParser secondParser) throws ParserException {
        logger.trace("###########################Called : {}#buildLineString", this.getClass().getSimpleName());
        List<Coordinate> coordinates = new ArrayList<>();
        for (JAXBElement<?> element : gmlGeometry.getPosOrPointPropertyOrPointRep()) {
            coordinates.add(this.internalDirectPosHandler.buildGeometry(geometryFactory, element.getValue()).getCoordinate());
        }
        org.locationtech.jts.geom.LinearRing linearRing = geometryFactory.createLinearRing(coordinates.toArray(new Coordinate[coordinates.size()]));
        this.srsParser.parseSRS(gmlGeometry, linearRing);
        return linearRing;
    }

    /**
     * @param gmlGeometry
     * @param firstParser
     * @param secondParser
     * @return {@link LineString}
     * @throws ParserException
     */
    @Override
    public LineString buildGeometryAsGeoJson(LinearRing gmlGeometry, GMLBasePointParser firstParser, CoordinateBaseParser secondParser) throws ParserException {
        logger.trace("###########################Called : {}#buildGeometryAsGeoJson(LinearRing gmlGeometry, GMLBasePointParser firstParser, CoordinateBaseParser secondParser)", this.getClass().getSimpleName());
        return gmlGeometry.isSetPosOrPointPropertyOrPointRep()
                ? buildLineStringAsGeoJson(gmlGeometry, firstParser, secondParser) : buildGeometryAsGeoJson(gmlGeometry, secondParser);
    }

    /**
     * @param gmlGeometry
     * @param firstParser
     * @param secondParser
     * @return {@link LineString}
     * @throws ParserException
     */
    @Override
    public LineString buildLineStringAsGeoJson(LinearRing gmlGeometry, GMLBasePointParser firstParser, CoordinateBaseParser secondParser) throws ParserException {
        logger.trace("###########################Called : {}#buildLineStringAsGeoJson(LinearRing gmlGeometry, GMLBasePointParser firstParser, CoordinateBaseParser secondParser)", this.getClass().getSimpleName());
        List<LngLatAlt> coordinates = new ArrayList<>();
        for (JAXBElement<?> element : gmlGeometry.getPosOrPointPropertyOrPointRep()) {
            coordinates.add(this.internalDirectPosHandler.buildGeometryAsGeoJson(element.getValue()).getCoordinates());
        }
        LineString linearString = new LineString(coordinates.stream().toArray(s -> new LngLatAlt[s]));
        linearString.setCrs(this.srsParser.parseSRS(gmlGeometry));
        return linearString;
    }

    /**
     * @param gmlGeometry
     * @param parser
     * @return {@link LineString}
     * @throws ParserException
     */
    @Override
    public LineString buildGeometryAsGeoJson(LinearRing gmlGeometry, CoordinateBaseParser parser) throws ParserException {
        logger.trace("###########################Called : {}#buildGeometryAsGeoJson", this.getClass().getSimpleName());
        return super.forwardBuildGeometryAsGeoJson(gmlGeometry, parser);
    }
}