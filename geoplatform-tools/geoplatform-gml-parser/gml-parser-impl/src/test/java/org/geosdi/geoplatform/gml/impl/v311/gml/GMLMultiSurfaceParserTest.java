/*
 *
 *    geo-platform
 *    Rich webgis framework
 *    http://geo-platform.org
 *   ====================================================================
 *
 *   Copyright (C) 2008-2024 geoSDI Group (CNR IMAA - Potenza - ITALY).
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
package org.geosdi.geoplatform.gml.impl.v311.gml;

import org.geosdi.geoplatform.gml.api.parser.base.geometry.multi.surface.GMLBaseMultiSurfaceParser;
import org.geosdi.geoplatform.gml.api.parser.base.parameter.GMLBaseParametersRepo;
import org.geosdi.geoplatform.jaxb.GPJAXBContextBuilder;
import org.geosdi.geoplatform.xml.gml.v311.MultiSurfaceType;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

import static java.io.File.separator;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Stream.of;
import static org.junit.runners.MethodSorters.NAME_ASCENDING;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@FixMethodOrder(value = NAME_ASCENDING)
public class GMLMultiSurfaceParserTest {

    private static final Logger logger = LoggerFactory.getLogger(GMLMultiSurfaceParserTest.class);
    //
    private static final GMLBaseMultiSurfaceParser multiSurfaceParser = GMLBaseParametersRepo.getDefaultMultiSurfaceParser();

    @Test
    public void a_multiSurfaceJTSParserTest() throws Exception {
        MultiSurfaceType multiSurfaceType = GPJAXBContextBuilder.newInstance().unmarshal(new File(of(new File(".").getCanonicalPath(),
                "src", "test", "resources", "MultiSurface.xml")
                .collect(joining(separator))), MultiSurfaceType.class);
        logger.info("##########################MULTI_POLYGON_JTS : {}\n", multiSurfaceParser.parseGeometry(multiSurfaceType));
    }

    @Test
    public void b_multiSurfaceJTSParserTest() throws Exception {
        MultiSurfaceType multiSurfaceType = GPJAXBContextBuilder.newInstance().unmarshal(new File(of(new File(".").getCanonicalPath(),
                "src", "test", "resources", "MultiSurface1.xml")
                .collect(joining(separator))), MultiSurfaceType.class);
        logger.info("##########################MULTI_POLYGON_JTS_1 : {}\n", multiSurfaceParser.parseGeometry(multiSurfaceType));
    }

    @Test
    public void c_multiSurfaceJTSParserTest() throws Exception {
        MultiSurfaceType multiSurfaceType = GPJAXBContextBuilder.newInstance().unmarshal(new File(of(new File(".").getCanonicalPath(),
                "src", "test", "resources", "MultiSurface2.xml")
                .collect(joining(separator))), MultiSurfaceType.class);
        logger.info("##########################MULTI_POLYGON_JTS_2 : {}\n", multiSurfaceParser.parseGeometry(multiSurfaceType));
    }

    @Test
    public void d_multiSurfaceJTSParserTest() throws Exception {
        MultiSurfaceType multiSurfaceType = GPJAXBContextBuilder.newInstance().unmarshal(new File(of(new File(".").getCanonicalPath(),
                "src", "test", "resources", "MultiSurface3.xml")
                .collect(joining(separator))), MultiSurfaceType.class);
        logger.info("##########################MULTI_POLYGON_JTS_2 : {}\n", multiSurfaceParser.parseGeometry(multiSurfaceType));
    }
}