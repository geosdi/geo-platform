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
import org.geosdi.geoplatform.gml.api.parser.base.coordinate.GMLCoordinateBaseParser;
import org.geosdi.geoplatform.gml.api.parser.base.geometry.line.GMLBaseLineStringParser;
import org.geosdi.geoplatform.gml.api.parser.base.geometry.linerarring.GMLBaseLinearRingParser;
import org.geosdi.geoplatform.gml.api.parser.base.geometry.multi.line.GMLBaseMultiLineStringParser;
import org.geosdi.geoplatform.gml.api.parser.base.geometry.multi.point.GMLBaseMultiPointParser;
import org.geosdi.geoplatform.gml.api.parser.base.geometry.multi.polygon.GMLBaseMultiPolygonParser;
import org.geosdi.geoplatform.gml.api.parser.base.geometry.point.GMLBasePointParser;
import org.geosdi.geoplatform.gml.api.parser.base.geometry.polygon.GMLBasePolygonParser;

/**
 *
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class GMLBaseParametersRepo {

    private final static EnumMap<BaseParameterEnum, BaseParameterValue> parameters = Maps.newEnumMap(
            BaseParameterEnum.class);

    private GMLBaseParametersRepo() {
    }
    //
    private static final BaseParameterValue<String[]> defaultSRSParameterFormat;

    static {
        defaultSRSParameterFormat = parameters.put(
                BaseParameterEnum.DEFAULT_SRS_PARAMETER_FORMAT,
                new SRSFormatParameterPatterns());
    }
    //
    private static final BaseParameterValue<GeometryFactory> defaultGeometryFactory;

    static {
        defaultGeometryFactory = parameters.put(
                BaseParameterEnum.DEFAULT_GEOMETRY_FACTORY,
                new GeometryFactoryParameter());
    }
    //
    private static final BaseParameterValue<DefaultSRSBaseParser> defaultSRSParser;

    static {
        defaultSRSParser = parameters.put(BaseParameterEnum.DEFAULT_SRS_PARSER,
                new BaseParameterValue<DefaultSRSBaseParser>() {
                    @Override
                    public DefaultSRSBaseParser getValue() {
                        return new DefaultSRSBaseParser(
                                defaultSRSParameterFormat.getValue());
                    }
                });
    }
    //
    private static final BaseParameterValue<CoordinateBaseParser> defaultCoordinateParser;

    static {
        defaultCoordinateParser = parameters.put(
                BaseParameterEnum.DEFAULT_COORDINATE_PARSER,
                new BaseParameterValue<CoordinateBaseParser>() {
                    @Override
                    public CoordinateBaseParser getValue() {
                        return new GMLCoordinateBaseParser();
                    }
                });
    }
    //
    private static final BaseParameterValue<GMLBasePointParser> defaultPointParser;

    static {
        defaultPointParser = parameters.put(
                BaseParameterEnum.DEFAULT_POINT_PARSER,
                new BaseParameterValue<GMLBasePointParser>() {
                    @Override
                    public GMLBasePointParser getValue() {
                        return new GMLBasePointParser(
                                defaultGeometryFactory.getValue(),
                                defaultSRSParser.getValue(),
                                defaultCoordinateParser.getValue());
                    }
                });
    }
    //
    private static final BaseParameterValue<GMLBaseLineStringParser> defaultLineStringParser;

    static {
        defaultLineStringParser = parameters.put(
                BaseParameterEnum.DEFAULT_LINE_STRING_PARSER,
                new BaseParameterValue<GMLBaseLineStringParser>() {
                    @Override
                    public GMLBaseLineStringParser getValue() {
                        return new GMLBaseLineStringParser(
                                defaultGeometryFactory.getValue(),
                                defaultSRSParser.getValue(),
                                defaultCoordinateParser.getValue(),
                                defaultPointParser.getValue());
                    }
                });
    }
    //
    private static final BaseParameterValue<GMLBaseLinearRingParser> defaultLinearRingParser;

    static {
        defaultLinearRingParser = parameters.put(
                BaseParameterEnum.DEFAULT_LINEAR_RING_PARSER,
                new BaseParameterValue<GMLBaseLinearRingParser>() {
                    @Override
                    public GMLBaseLinearRingParser getValue() {
                        return new GMLBaseLinearRingParser(
                                defaultCoordinateParser.getValue(),
                                defaultPointParser.getValue(),
                                defaultGeometryFactory.getValue(),
                                defaultSRSParser.getValue());
                    }
                });
    }
    //
    private static final BaseParameterValue<GMLBasePolygonParser> defaultPolygonParser;

    static {
        defaultPolygonParser = parameters.put(
                BaseParameterEnum.DEFAULT_POLYGON_PARSER,
                new BaseParameterValue<GMLBasePolygonParser>() {
                    @Override
                    public GMLBasePolygonParser getValue() {
                        return new GMLBasePolygonParser(
                                defaultLinearRingParser.getValue(),
                                defaultGeometryFactory.getValue(),
                                defaultSRSParser.getValue());
                    }
                });
    }
    //
    private static final BaseParameterValue<GMLBaseMultiPointParser> defaultMultiPointParser;

    static {
        defaultMultiPointParser = parameters.put(
                BaseParameterEnum.DEFAULT_MULTI_POINT_PARSER,
                new BaseParameterValue<GMLBaseMultiPointParser>() {
                    @Override
                    public GMLBaseMultiPointParser getValue() {
                        return new GMLBaseMultiPointParser(
                                defaultGeometryFactory.getValue(),
                                defaultSRSParser.getValue(),
                                defaultPointParser.getValue());
                    }
                });
    }
    //
    private static final BaseParameterValue<GMLBaseMultiLineStringParser> defaultMultiLineStringParser;

    static {
        defaultMultiLineStringParser = parameters.put(
                BaseParameterEnum.DEFAULT_MULTI_LINE_STRING_PARSER,
                new BaseParameterValue<GMLBaseMultiLineStringParser>() {
                    @Override
                    public GMLBaseMultiLineStringParser getValue() {
                        return new GMLBaseMultiLineStringParser(
                                defaultGeometryFactory.getValue(),
                                defaultSRSParser.getValue(),
                                defaultLineStringParser.getValue());
                    }
                });
    }
    //
    private static final BaseParameterValue<GMLBaseMultiPolygonParser> defaultMultiPolygonParser;

    static {
        defaultMultiPolygonParser = parameters.put(
                BaseParameterEnum.DEFAULT_MULTI_POLYGON_PARSER,
                new BaseParameterValue<GMLBaseMultiPolygonParser>() {
                    @Override
                    public GMLBaseMultiPolygonParser getValue() {
                        return new GMLBaseMultiPolygonParser(
                                defaultGeometryFactory.getValue(),
                                defaultSRSParser.getValue(),
                                defaultPolygonParser.getValue());
                    }
                });
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

    public static BaseParameterValue getRepoParamater(BaseParameterEnum key) {
        return parameters.get(key);
    }
}
