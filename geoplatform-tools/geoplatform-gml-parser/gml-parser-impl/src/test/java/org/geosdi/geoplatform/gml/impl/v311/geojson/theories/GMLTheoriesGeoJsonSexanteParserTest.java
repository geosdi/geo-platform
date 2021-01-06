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
package org.geosdi.geoplatform.gml.impl.v311.geojson.theories;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.geosdi.geoplatform.gml.api.jaxb.context.GMLJAXBContext;
import org.geosdi.geoplatform.gml.api.parser.base.geometry.sextante.GMLBaseSextanteParser;
import org.geosdi.geoplatform.gml.api.parser.base.parameter.GMLBaseParametersRepo;
import org.geosdi.geoplatform.gml.impl.v311.jaxb.context.factory.GMLContextFactoryV311;
import org.geosdi.geoplatform.support.jackson.GPJacksonSupport;
import org.geosdi.geoplatform.xml.gml.v311.AbstractGeometryType;
import org.junit.BeforeClass;
import org.junit.experimental.theories.DataPoints;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.bind.JAXBElement;
import java.io.File;
import java.io.IOException;

import static java.io.File.separator;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Stream.of;
import static org.geosdi.geoplatform.gml.impl.v311.jaxb.context.factory.GMLContextType.SIMPLE;
import static org.geosdi.geoplatform.support.jackson.property.GPJacksonSupportEnum.*;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@RunWith(Theories.class)
public class GMLTheoriesGeoJsonSexanteParserTest {

    private static final Logger logger = LoggerFactory.getLogger(GMLTheoriesGeoJsonSexanteParserTest.class);
    //
    private static final GMLJAXBContext gmlJAXBContext = GMLContextFactoryV311.createJAXBContext(SIMPLE);
    private static final ObjectMapper mapper = new GPJacksonSupport(UNWRAP_ROOT_VALUE_DISABLE,
            FAIL_ON_UNKNOW_PROPERTIES_DISABLE, ACCEPT_SINGLE_VALUE_AS_ARRAY_ENABLE, WRAP_ROOT_VALUE_DISABLE,
            INDENT_OUTPUT_ENABLE).getDefaultMapper();
    private static final GMLBaseSextanteParser sextanteParser = GMLBaseParametersRepo.getDefaultSextanteParser();
    private static String dirFiles;

    @BeforeClass
    public static void buildDirFiles() throws IOException {
        dirFiles = of(new File(".").getCanonicalPath(), "src", "test", "resources")
                .collect(joining(separator, "", separator));
    }

    @DataPoints
    public static String[] data() {
        return new String[]{"MultiCurve.xml", "Point.xml", "GeometryCollection.xml", "LineString.xml", "LinearRing.xml",
                "MultiLineString.xml", "MultiPoint.xml", "MultiPolygon.xml", "Polygon.xml", "Polygon_1.xml",
                "MultiSurface.xml", "MultiLineString_srsDimension3.xml", "Curve.xml"
        };
    }

    @Theory
    public void geoJsonGeometryParserTest(String file) throws Exception {
        JAXBElement<AbstractGeometryType> jaxbElement = (JAXBElement<AbstractGeometryType>) gmlJAXBContext.acquireDefaultUnmarshaller().unmarshal(new File(dirFiles.concat(file)));
        AbstractGeometryType abstractGeometryType = jaxbElement.getValue();
        logger.debug("#######################################GML Geometry : {} -- GeoJson Geometry \n\n{}\n\n for File : {}\n",
                abstractGeometryType.getClass().getSimpleName(), mapper.writeValueAsString(sextanteParser.parseGeometryAsGeoJson(abstractGeometryType)), file);
    }
}