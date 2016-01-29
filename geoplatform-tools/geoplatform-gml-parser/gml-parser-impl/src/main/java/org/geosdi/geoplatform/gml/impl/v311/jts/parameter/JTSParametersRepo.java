/**
 *
 *    geo-platform
 *    Rich webgis framework
 *    http://geo-platform.org
 *   ====================================================================
 *
 *   Copyright (C) 2008-2016 geoSDI Group (CNR IMAA - Potenza - ITALY).
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
package org.geosdi.geoplatform.gml.impl.v311.jts.parameter;

import com.google.common.collect.Maps;
import java.util.Collections;
import java.util.Map;
import org.geosdi.geoplatform.gml.api.jaxb.AbstractGMLObjectFactory;
import org.geosdi.geoplatform.gml.api.parameter.ParameterValue;
import org.geosdi.geoplatform.gml.api.parser.jts.DefaultJTSSRSParser;
import org.geosdi.geoplatform.gml.api.parser.jts.coordinate.JTSCoordinateParser;
import org.geosdi.geoplatform.gml.api.parser.jts.geometry.line.JTSLineStringParser;
import org.geosdi.geoplatform.gml.api.parser.jts.geometry.linearring.JTSLinearRingParser;
import org.geosdi.geoplatform.gml.api.parser.jts.geometry.multi.geometry.JTSMultiGeometryParser;
import org.geosdi.geoplatform.gml.api.parser.jts.geometry.multi.line.JTSMultiLineStringParser;
import org.geosdi.geoplatform.gml.api.parser.jts.geometry.multi.point.JTSMultiPointParser;
import org.geosdi.geoplatform.gml.api.parser.jts.geometry.multi.polygon.JTSMultiPolygonParser;
import org.geosdi.geoplatform.gml.api.parser.jts.geometry.point.JTSPointParser;
import org.geosdi.geoplatform.gml.api.parser.jts.geometry.polygon.JTSPolygonParser;
import org.geosdi.geoplatform.gml.api.parser.jts.geometry.sextante.JTSSextanteParser;
import org.geosdi.geoplatform.gml.impl.v311.jts.sextante.JTSSextanteParserV311;

/**
 *
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public final class JTSParametersRepo {

    private static final JTSParametersRepo instance = new JTSParametersRepo();

    static {
        lookUpAllParameters();
    }
    //
    private final Map<JTSParameterEnum, ParameterValue<?>> parameters;

    private JTSParametersRepo() {
        parameters = Maps.newEnumMap(JTSParameterEnum.class);
    }

    private static void lookUpAllParameters() {
        final ParameterValue<AbstractGMLObjectFactory> defaultGmlObjectFactory = new ObjectFactoryParameter();
        instance.parameters.put(JTSParameterEnum.DEFAULT_OBJECT_FACTORY,
                defaultGmlObjectFactory);

        final ParameterValue<DefaultJTSSRSParser> defaultSRSParser = new DefaultJTSSRSParserParameter();
        instance.parameters.put(JTSParameterEnum.DEFAULT_JTS_SRS_PARSER,
                defaultSRSParser);

        final ParameterValue<JTSCoordinateParser> defaultCoordinateParser = new JTSCoordinateParserParameter(
                defaultGmlObjectFactory);
        instance.parameters.put(JTSParameterEnum.DEFAULT_JTS_COORDINATE_PARSER,
                defaultCoordinateParser);

        final ParameterValue<JTSPointParser> defaultPointParser = new JTSPointParserParameter(
                defaultGmlObjectFactory, defaultSRSParser,
                defaultCoordinateParser);
        instance.parameters.put(JTSParameterEnum.DEFAULT_JTS_POINT_PARSER,
                defaultPointParser);

        final ParameterValue<JTSLineStringParser> defaultLineStringParser = new JTSLineStringParserParameter(
                defaultGmlObjectFactory, defaultSRSParser,
                defaultCoordinateParser);
        instance.parameters.put(JTSParameterEnum.DEFAULT_JTS_LINE_STRING_PARSER,
                defaultLineStringParser);

        final ParameterValue<JTSLinearRingParser> defaultLinearRingParser = new JTSLinearRingParserParameter(
                defaultGmlObjectFactory, defaultSRSParser,
                defaultCoordinateParser);
        instance.parameters.put(JTSParameterEnum.DEFAULT_JTS_LINEAR_RING_PARSER,
                defaultLinearRingParser);

        final ParameterValue<JTSPolygonParser> defaultPolygonParser = new JTSPolygonParserParameter(
                defaultGmlObjectFactory, defaultSRSParser,
                defaultLinearRingParser);
        instance.parameters.put(JTSParameterEnum.DEFAULT_JTS_POLYGON_PARSER,
                defaultPolygonParser);

        final ParameterValue<JTSMultiPointParser> defaultMultiPointParser = new JTSMultiPointParserParameter(
                defaultGmlObjectFactory, defaultSRSParser, defaultPointParser);
        instance.parameters.put(JTSParameterEnum.DEFAULT_JTS_MULTI_POINT_PARSER,
                defaultMultiPointParser);

        final ParameterValue<JTSMultiLineStringParser> defaultMultiLineStringParser = new JTSMultiLineParserParameter(
                defaultGmlObjectFactory, defaultSRSParser,
                defaultLineStringParser);
        instance.parameters.put(JTSParameterEnum.DEFAULT_JTS_MULTI_LINE_PARSER,
                defaultMultiLineStringParser);

        final ParameterValue<JTSMultiPolygonParser> defaultMultiPolygonParser = new JTSMultiPolygonParserParameter(
                defaultGmlObjectFactory, defaultSRSParser, defaultPolygonParser);
        instance.parameters.put(
                JTSParameterEnum.DEFAULT_JTS_MULTI_POLYGON_PARSER,
                defaultMultiPolygonParser);

        final ParameterValue<JTSSextanteParserV311> defaultSextanteParser = new JTSSextanteParserParameter();
        instance.parameters.put(JTSParameterEnum.DEFAULT_JTS_SEXTANTE_PARSER,
                defaultSextanteParser);

        final ParameterValue<JTSMultiGeometryParser> defaultMultiGeometryParser = new JTSMultiGeometryParserParameter(
                defaultGmlObjectFactory, defaultSRSParser, defaultSextanteParser);
        instance.parameters.put(
                JTSParameterEnum.DEFAULT_JTS_MULTI_GEOMETRY_PARSER,
                defaultMultiGeometryParser);
    }

    public static AbstractGMLObjectFactory getDefaultGmlObjectFactory() {
        return ((ParameterValue<AbstractGMLObjectFactory>) instance.parameters.get(
                JTSParameterEnum.DEFAULT_OBJECT_FACTORY)).getValue();
    }

    public static DefaultJTSSRSParser getDefaultSRSParser() {
        return ((ParameterValue<DefaultJTSSRSParser>) instance.parameters.get(
                JTSParameterEnum.DEFAULT_JTS_SRS_PARSER)).getValue();
    }

    public static JTSCoordinateParser getDefaultCoordinateParser() {
        return ((ParameterValue<JTSCoordinateParser>) instance.parameters.get(
                JTSParameterEnum.DEFAULT_JTS_COORDINATE_PARSER)).getValue();
    }

    public static JTSPointParser getDefaultPointParser() {
        return ((ParameterValue<JTSPointParser>) instance.parameters.get(
                JTSParameterEnum.DEFAULT_JTS_POINT_PARSER)).getValue();
    }

    public static JTSLineStringParser getDefaultLineStringParser() {
        return ((ParameterValue<JTSLineStringParser>) instance.parameters.get(
                JTSParameterEnum.DEFAULT_JTS_LINE_STRING_PARSER)).getValue();
    }

    public static JTSLinearRingParser getDefaultLinearRingParser() {
        return ((ParameterValue<JTSLinearRingParser>) instance.parameters.get(
                JTSParameterEnum.DEFAULT_JTS_LINEAR_RING_PARSER)).getValue();
    }

    public static JTSPolygonParser getDefaultPolygonParser() {
        return ((ParameterValue<JTSPolygonParser>) instance.parameters.get(
                JTSParameterEnum.DEFAULT_JTS_POLYGON_PARSER)).getValue();
    }

    public static JTSMultiPointParser getDefaultMultiPointParser() {
        return ((ParameterValue<JTSMultiPointParser>) instance.parameters.get(
                JTSParameterEnum.DEFAULT_JTS_MULTI_POINT_PARSER)).getValue();
    }

    public static JTSMultiLineStringParser getDefaultMultiLineParser() {
        return ((ParameterValue<JTSMultiLineStringParser>) instance.parameters.get(
                JTSParameterEnum.DEFAULT_JTS_MULTI_LINE_PARSER)).getValue();
    }

    public static JTSMultiPolygonParser getDefaultMultiPolygonParser() {
        return ((ParameterValue<JTSMultiPolygonParser>) instance.parameters.get(
                JTSParameterEnum.DEFAULT_JTS_MULTI_POLYGON_PARSER)).getValue();
    }

    public static JTSMultiGeometryParser getDefaultMultiGeometryParser() {
        return ((ParameterValue<JTSMultiGeometryParser>) instance.parameters.get(
                JTSParameterEnum.DEFAULT_JTS_MULTI_GEOMETRY_PARSER)).getValue();
    }

    public static JTSSextanteParser getDefaultSextanteParser() {
        return ((ParameterValue<JTSSextanteParser>) instance.parameters.get(
                JTSParameterEnum.DEFAULT_JTS_SEXTANTE_PARSER)).getValue();
    }

    public static ParameterValue<?> getRepoParameter(JTSParameterEnum key) {
        return instance.parameters.get(key);
    }

    public static Map<JTSParameterEnum, ParameterValue<?>> getAllParameters() {
        return Collections.unmodifiableMap(instance.parameters);
    }
}
