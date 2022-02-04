/**
 * geo-platform
 * Rich webgis framework
 * http://geo-platform.org
 * ====================================================================
 * <p>
 * Copyright (C) 2008-2022 geoSDI Group (CNR IMAA - Potenza - ITALY).
 * <p>
 * This program is free software: you can redistribute it and/or modify it
 * under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version. This program is distributed in the
 * hope that it will be useful, but WITHOUT ANY WARRANTY; without
 * even the implied warranty of MERCHANTABILITY or FITNESS FOR
 * A PARTICULAR PURPOSE. See the GNU General Public License
 * for more details. You should have received a copy of the GNU General
 * Public License along with this program. If not, see http://www.gnu.org/licenses/
 * <p>
 * ====================================================================
 * <p>
 * Linking this library statically or dynamically with other modules is
 * making a combined work based on this library. Thus, the terms and
 * conditions of the GNU General Public License cover the whole combination.
 * <p>
 * As a special exception, the copyright holders of this library give you permission
 * to link this library with independent modules to produce an executable, regardless
 * of the license terms of these independent modules, and to copy and distribute
 * the resulting executable under terms of your choice, provided that you also meet,
 * for each linked independent module, the terms and conditions of the license of
 * that module. An independent module is a module which is not derived from or
 * based on this library. If you modify this library, you may extend this exception
 * to your version of the library, but you are not obligated to do so. If you do not
 * wish to do so, delete this exception statement from your version.
 */
package org.geosdi.geoplatform.gml.impl.v311.gml;

import org.geosdi.geoplatform.gml.api.parser.base.geometry.polygon.GMLBasePolygonParser;
import org.geosdi.geoplatform.gml.api.parser.base.parameter.GMLBaseParametersRepo;
import org.geosdi.geoplatform.jaxb.GPJAXBContextBuilder;
import org.geosdi.geoplatform.xml.gml.v311.PolygonType;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.StringReader;

import static java.util.stream.Collectors.joining;
import static java.util.stream.Stream.of;
import static org.junit.runners.MethodSorters.NAME_ASCENDING;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@FixMethodOrder(value = NAME_ASCENDING)
public class GMLPolygonParserTest {

    private static final Logger logger = LoggerFactory.getLogger(GMLPolygonParserTest.class);
    //
    private static final GMLBasePolygonParser polygonParser = GMLBaseParametersRepo.getDefaultPolygonParser();

    @Test
    public void a_polygonJTSParserTest() throws Exception {
        PolygonType polygonType = GPJAXBContextBuilder.newInstance().unmarshal(new File(of(new File(".").getCanonicalPath(),
                "src", "test", "resources", "Polygon_2.xml")
                .collect(joining(File.separator))), PolygonType.class);
        logger.info("##########################POLYGON_STRING_JTS : {}\n", polygonParser.parseGeometry(polygonType));
    }

    @Test
    public void b_polygonJTSParserTest() throws Exception {
        PolygonType polygonType = GPJAXBContextBuilder.newInstance().unmarshal(new File(of(new File(".").getCanonicalPath(),
                "src", "test", "resources", "Polygon_3.xml")
                .collect(joining(File.separator))), PolygonType.class);
        logger.info("##########################POLYGON_STRING_JTS : {}\n", polygonParser.parseGeometry(polygonType));
    }

    @Test
    public void c_polygonJTSParserTest() throws Exception {
        PolygonType polygonType = GPJAXBContextBuilder.newInstance().unmarshal(new StringReader("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n"
                + "<gml:Polygon xmlns:gml=\"http://www.opengis.net/gml\" srsName=\"SDO:\">\n"
                + "   <gml:exterior>\n"
                + "      <gml:LinearRing>\n"
                + "         <gml:posList srsDimension=\"2\">5.0 1.0 8.0 1.0 8.0 6.0 5.0 7.0 5.0 1.0</gml:posList>\n"
                + "      </gml:LinearRing>\n"
                + "   </gml:exterior>\n"
                + "</gml:Polygon>"), PolygonType.class);
        logger.info("##########################POLYGON_STRING_JTS : {}\n", polygonParser.parseGeometry(polygonType));
    }
}