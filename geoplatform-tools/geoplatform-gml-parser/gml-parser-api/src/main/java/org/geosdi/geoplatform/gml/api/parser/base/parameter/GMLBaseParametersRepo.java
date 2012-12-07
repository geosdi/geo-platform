/*
 *  geo-platform
 *  Rich webgis framework
 *  http://geo-platform.org
 * ====================================================================
 *
 * Copyright (C) 2008-2012 geoSDI Group (CNR IMAA - Potenza - ITALY).
 *
 * This program is free software: you can redistribute it and/or modify it 
 * under the terms of the GNU General Public License as published by 
 * the Free Software Foundation, either version 3 of the License, or 
 * (at your option) any later version. This program is distributed in the 
 * hope that it will be useful, but WITHOUT ANY WARRANTY; without 
 * even the implied warranty of MERCHANTABILITY or FITNESS FOR 
 * A PARTICULAR PURPOSE. See the GNU General Public License 
 * for more details. You should have received a copy of the GNU General 
 * Public License along with this program. If not, see http://www.gnu.org/licenses/ 
 *
 * ====================================================================
 *
 * Linking this library statically or dynamically with other modules is 
 * making a combined work based on this library. Thus, the terms and 
 * conditions of the GNU General Public License cover the whole combination. 
 * 
 * As a special exception, the copyright holders of this library give you permission 
 * to link this library with independent modules to produce an executable, regardless 
 * of the license terms of these independent modules, and to copy and distribute 
 * the resulting executable under terms of your choice, provided that you also meet, 
 * for each linked independent module, the terms and conditions of the license of 
 * that module. An independent module is a module which is not derived from or 
 * based on this library. If you modify this library, you may extend this exception 
 * to your version of the library, but you are not obligated to do so. If you do not 
 * wish to do so, delete this exception statement from your version. 
 *
 */
package org.geosdi.geoplatform.gml.api.parser.base.parameter;

import com.google.common.collect.Maps;
import com.vividsolutions.jts.geom.GeometryFactory;
import java.util.EnumMap;
import org.geosdi.geoplatform.gml.api.parser.base.DefaultSRSBaseParser;
import org.geosdi.geoplatform.gml.api.parser.base.coordinate.CoordinateBaseParser;
import org.geosdi.geoplatform.gml.api.parser.base.geometry.line.GMLBaseLineStringParser;
import org.geosdi.geoplatform.gml.api.parser.base.geometry.linerarring.GMLBaseLinearRingParser;
import org.geosdi.geoplatform.gml.api.parser.base.geometry.multi.geometry.GMLBaseMultiGeometryParser;
import org.geosdi.geoplatform.gml.api.parser.base.geometry.multi.line.GMLBaseMultiLineStringParser;
import org.geosdi.geoplatform.gml.api.parser.base.geometry.multi.point.GMLBaseMultiPointParser;
import org.geosdi.geoplatform.gml.api.parser.base.geometry.multi.polygon.GMLBaseMultiPolygonParser;
import org.geosdi.geoplatform.gml.api.parser.base.geometry.multi.surface.GMLBaseMultiSurfaceParser;
import org.geosdi.geoplatform.gml.api.parser.base.geometry.point.GMLBasePointParser;
import org.geosdi.geoplatform.gml.api.parser.base.geometry.polygon.GMLBasePolygonParser;
import org.geosdi.geoplatform.gml.api.parser.base.geometry.sextante.GMLBaseSextanteParser;

/**
 *
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class GMLBaseParametersRepo {

    static {
        lookUpAllParameters();
    }
    //
    private static EnumMap<BaseParameterEnum, BaseParameterValue> parameters;

    private GMLBaseParametersRepo() {
    }

    private static void lookUpAllParameters() {
        parameters = Maps.newEnumMap(BaseParameterEnum.class);

        final BaseParameterValue<String[]> defaultSRSParameterFormat = new SRSFormatParameterPatterns();
        parameters.put(BaseParameterEnum.DEFAULT_SRS_PARAMETER_FORMAT,
                defaultSRSParameterFormat);

        final BaseParameterValue<GeometryFactory> defaultGeometryFactory = new GeometryFactoryParameter();
        parameters.put(BaseParameterEnum.DEFAULT_GEOMETRY_FACTORY,
                defaultGeometryFactory);

        final BaseParameterValue<DefaultSRSBaseParser> defaultSRSParser = new DefaultSRSParserParameter(
                defaultSRSParameterFormat.getValue());
        parameters.put(BaseParameterEnum.DEFAULT_SRS_PARSER, defaultSRSParser);

        final BaseParameterValue<CoordinateBaseParser> defaultCoordinateParser = new CoordinateParserParameter();
        parameters.put(BaseParameterEnum.DEFAULT_COORDINATE_PARSER,
                defaultCoordinateParser);

        final BaseParameterValue<GMLBasePointParser> defaultPointParser = new PointParserParameter(
                defaultGeometryFactory, defaultSRSParser,
                defaultCoordinateParser);
        parameters.put(BaseParameterEnum.DEFAULT_POINT_PARSER,
                defaultPointParser);

        final BaseParameterValue<GMLBaseLineStringParser> defaultLineStringParser = new LineStringParserParameter(
                defaultGeometryFactory, defaultSRSParser,
                defaultCoordinateParser,
                defaultPointParser);
        parameters.put(BaseParameterEnum.DEFAULT_LINE_STRING_PARSER,
                defaultLineStringParser);

        final BaseParameterValue<GMLBaseLinearRingParser> defaultLinearRingParser = new LinearRingParserParameter(
                defaultGeometryFactory, defaultSRSParser,
                defaultCoordinateParser,
                defaultPointParser);
        parameters.put(BaseParameterEnum.DEFAULT_LINEAR_RING_PARSER,
                defaultLinearRingParser);

        final BaseParameterValue<GMLBasePolygonParser> defaultPolygonParser = new PolygonParserParameter(
                defaultGeometryFactory, defaultSRSParser,
                defaultLinearRingParser);
        parameters.put(BaseParameterEnum.DEFAULT_POLYGON_PARSER,
                defaultPolygonParser);

        final BaseParameterValue<GMLBaseMultiPointParser> defaultMultiPointParser = new MultiPointParserParameter(
                defaultGeometryFactory, defaultSRSParser,
                defaultPointParser);
        parameters.put(BaseParameterEnum.DEFAULT_MULTI_POINT_PARSER,
                defaultMultiPointParser);

        final BaseParameterValue<GMLBaseMultiLineStringParser> defaultMultiLineStringParser = new MultiLineStringParserParameter(
                defaultGeometryFactory, defaultSRSParser,
                defaultLineStringParser);
        parameters.put(BaseParameterEnum.DEFAULT_MULTI_LINE_STRING_PARSER,
                defaultMultiLineStringParser);

        final BaseParameterValue<GMLBaseMultiPolygonParser> defaultMultiPolygonParser = new MultiPolygonParserParameter(
                defaultGeometryFactory, defaultSRSParser,
                defaultPolygonParser);
        parameters.put(
                BaseParameterEnum.DEFAULT_MULTI_POLYGON_PARSER,
                defaultMultiPolygonParser);

        final BaseParameterValue<GMLBaseMultiSurfaceParser> defaultMultiSurfaceParser = new MultiSurfaceParserParameter(
                defaultGeometryFactory, defaultSRSParser,
                defaultPolygonParser);
        parameters.put(BaseParameterEnum.DEFAULT_MULTI_SURFACE_PARSER,
                defaultMultiSurfaceParser);

        final BaseParameterValue<GMLBaseSextanteParser> defaultSextanteParser = new SextanteParserParameter();
        parameters.put(BaseParameterEnum.DEFAULT_SEXTANTE_PARSER,
                defaultSextanteParser);

        final BaseParameterValue<GMLBaseMultiGeometryParser> defaultMultiGeometryParser = new MultiGeometryParserParameter();
        parameters.put(BaseParameterEnum.DEFAULT_MULTI_GEOMETRY_PARSER,
                defaultMultiGeometryParser);
    }

    public static GeometryFactory getDefaultGeometryFactory() {
        return (GeometryFactory) parameters.get(
                BaseParameterEnum.DEFAULT_GEOMETRY_FACTORY).getValue();
    }

    public static DefaultSRSBaseParser getDefaultSRSParser() {
        return (DefaultSRSBaseParser) parameters.get(
                BaseParameterEnum.DEFAULT_SRS_PARSER).getValue();
    }

    public static GMLBasePointParser getDefaultPointParser() {
        return (GMLBasePointParser) parameters.get(
                BaseParameterEnum.DEFAULT_POINT_PARSER).getValue();
    }

    public static GMLBaseLineStringParser getDefaultLineStringParser() {
        return (GMLBaseLineStringParser) parameters.get(
                BaseParameterEnum.DEFAULT_LINE_STRING_PARSER).getValue();
    }

    public static GMLBaseLinearRingParser getDefaultLinearRingParser() {
        return ((BaseParameterValue<GMLBaseLinearRingParser>) parameters.get(
                BaseParameterEnum.DEFAULT_LINEAR_RING_PARSER)).getValue();
    }

    public static GMLBasePolygonParser getDefaultPolygonParser() {
        return ((BaseParameterValue<GMLBasePolygonParser>) parameters.get(
                BaseParameterEnum.DEFAULT_POLYGON_PARSER)).getValue();
    }

    public static GMLBaseMultiPointParser getDefaultMultiPointParser() {
        return ((BaseParameterValue<GMLBaseMultiPointParser>) parameters.get(
                BaseParameterEnum.DEFAULT_MULTI_POINT_PARSER)).getValue();
    }

    public static GMLBaseMultiLineStringParser getDefaultMultiLineStringParser() {
        return ((BaseParameterValue<GMLBaseMultiLineStringParser>) parameters.get(
                BaseParameterEnum.DEFAULT_MULTI_LINE_STRING_PARSER)).getValue();
    }

    public static GMLBaseMultiPolygonParser getDefaultMultiPolygonParser() {
        return ((BaseParameterValue<GMLBaseMultiPolygonParser>) parameters.get(
                BaseParameterEnum.DEFAULT_MULTI_POLYGON_PARSER)).getValue();
    }

    public static GMLBaseMultiSurfaceParser getDefaultMultiSurfaceParser() {
        return ((BaseParameterValue<GMLBaseMultiSurfaceParser>) parameters.get(
                BaseParameterEnum.DEFAULT_MULTI_SURFACE_PARSER)).getValue();
    }

    public static GMLBaseSextanteParser getDefaultSextanteParser() {
        return ((BaseParameterValue<GMLBaseSextanteParser>) parameters.get(
                BaseParameterEnum.DEFAULT_SEXTANTE_PARSER)).getValue();
    }

    public static GMLBaseMultiGeometryParser getDefaultMultiGeometryParser() {
        return ((BaseParameterValue<GMLBaseMultiGeometryParser>) parameters.get(
                BaseParameterEnum.DEFAULT_MULTI_GEOMETRY_PARSER)).getValue();
    }

    public static BaseParameterValue getRepoParamater(BaseParameterEnum key) {
        return parameters.get(key);
    }
}
