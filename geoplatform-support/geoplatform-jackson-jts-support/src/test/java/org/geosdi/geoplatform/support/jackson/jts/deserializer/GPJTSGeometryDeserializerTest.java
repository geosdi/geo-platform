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
package org.geosdi.geoplatform.support.jackson.jts.deserializer;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.geosdi.geoplatform.support.jackson.jts.GPJacksonJTSSupport;
import org.junit.Test;
import org.locationtech.jts.geom.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class GPJTSGeometryDeserializerTest {

    private static final Logger logger = LoggerFactory.getLogger(GPJTSGeometryDeserializerTest.class);
    //
    private static final ObjectMapper mapper = new GPJacksonJTSSupport().getDefaultMapper();

    @Test
    public void jtsPointDeserializerTest() throws Exception {
        logger.info(":::::::::::::::::::::::::::::JTS_POINT : {}\n", mapper.readValue("{\n" +
                "  \"type\" : \"Point\",\n" +
                "  \"crs\" : {\n" +
                "    \"type\" : \"name\",\n" +
                "    \"properties\" : {\n" +
                "      \"name\" : \"EPSG:4326\"\n" +
                "    }\n" +
                "  },\n" +
                "  \"coordinates\" : [ 10.0, 20.0 ]\n" +
                "}", Point.class));
    }

    @Test
    public void jtsLineStringDeserializerTest() throws Exception {
        logger.info(":::::::::::::::::::::::::::::JTS_LINE_STRING : {}\n", mapper.readValue("{\n" +
                "  \"type\" : \"LineString\",\n" +
                "  \"crs\" : {\n" +
                "    \"type\" : \"name\",\n" +
                "    \"properties\" : {\n" +
                "      \"name\" : \"EPSG:4326\"\n" +
                "    }\n" +
                "  },\n" +
                "  \"coordinates\" : [ [ 5.0, 5.0 ], [ 6.0, 5.0 ], [ 6.0, 6.0 ], [ 7.0, 6.0 ], [ 7.0, 7.0 ], [ 8.0, 7.0 ], [ 8.0, 8.0 ], [ 9.0, 9.0 ] ]\n" +
                "}", LineString.class));
    }

    @Test
    public void jtsLinearRingDeserializerTest() throws Exception {
        logger.info(":::::::::::::::::::::::::::JTS_LINEAR_RING : {}", mapper.readValue("{\n" +
                "  \"type\" : \"LineString\",\n" +
                "  \"crs\" : {\n" +
                "    \"type\" : \"name\",\n" +
                "    \"properties\" : {\n" +
                "      \"name\" : \"EPSG:4326\"\n" +
                "    }\n" +
                "  },\n" +
                "  \"coordinates\" : [ [ 7.0, 7.0 ], [ 6.0, 9.0 ], [ 6.0, 11.0 ], [ 7.0, 12.0 ], [ 9.0, 11.0 ], [ 11.0, 12.0 ], [ 13.0, 11.0 ], [ 13.0, 9.0 ], [ 11.0, 7.0 ], [ 7.0, 7.0 ] ]\n" +
                "}", LinearRing.class));
    }

    @Test
    public void jtsPolygonDeserializerTest() throws Exception {
        logger.info("::::::::::::::::::::::::JTS_POLYGON : {}", mapper.readValue("{\n" +
                "  \"type\" : \"Polygon\",\n" +
                "  \"crs\" : {\n" +
                "    \"type\" : \"name\",\n" +
                "    \"properties\" : {\n" +
                "      \"name\" : \"EPSG:4326\"\n" +
                "    }\n" +
                "  },\n" +
                "  \"coordinates\" : [ [ [ 35.0, 10.0 ], [ 10.0, 20.0 ], [ 15.0, 40.0 ], [ 45.0, 45.0 ], [ 35.0, 10.0 ] ], [ [ 20.0, 30.0 ], [ 35.0, 35.0 ], [ 30.0, 20.0 ], [ 20.0, 30.0 ] ] ]\n" +
                "}", Polygon.class));
    }

    @Test
    public void jtsMultiPointDeserializerTest() throws Exception {
        logger.info(":::::::::::::::::::::::::::JTS_MULTI_POINT : {}", mapper.readValue("{\n" +
                "  \"type\" : \"MultiPoint\",\n" +
                "  \"crs\" : {\n" +
                "    \"type\" : \"name\",\n" +
                "    \"properties\" : {\n" +
                "      \"name\" : \"EPSG:4326\"\n" +
                "    }\n" +
                "  },\n" +
                "  \"coordinates\" : [ [ 10.0, 40.0 ], [ 40.0, 30.0 ], [ 20.0, 20.0 ], [ 30.0, 10.0 ] ]\n" +
                "}", MultiPoint.class));
    }

    @Test
    public void jtsMultiLineStringDeserializerTest() throws Exception {
        logger.info("::::::::::::::::::::::::::::::::JTS_MULTI_LINESTRING : {}", mapper.readValue("{\n" +
                "  \"type\" : \"MultiLineString\",\n" +
                "  \"crs\" : {\n" +
                "    \"type\" : \"name\",\n" +
                "    \"properties\" : {\n" +
                "      \"name\" : \"EPSG:4326\"\n" +
                "    }\n" +
                "  },\n" +
                "  \"coordinates\" : [ [ [ 10.0, 10.0 ], [ 20.0, 20.0 ], [ 10.0, 40.0 ] ], [ [ 40.0, 40.0 ], [ 30.0, 30.0 ], [ 40.0, 20.0 ], [ 30.0, 10.0 ] ] ]\n" +
                "}", MultiLineString.class));
    }

    @Test
    public void jtsMultiPolygonDeserializerTest() throws Exception {
        logger.info(":::::::::::::::::::::::::::::JTS_MULTI_POLYGON : {}", mapper.readValue("{\n" +
                "  \"type\" : \"MultiPolygon\",\n" +
                "  \"crs\" : {\n" +
                "    \"type\" : \"name\",\n" +
                "    \"properties\" : {\n" +
                "      \"name\" : \"EPSG:4326\"\n" +
                "    }\n" +
                "  },\n" +
                "  \"coordinates\" : [ [ [ [ 40.0, 40.0 ], [ 20.0, 45.0 ], [ 45.0, 30.0 ], [ 40.0, 40.0 ] ] ], [ [ [ 20.0, 35.0 ], [ 45.0, 20.0 ], [ 30.0, 5.0 ], [ 10.0, 10.0 ], [ 10.0, 30.0 ], [ 20.0, 35.0 ] ], [ [ 30.0, 20.0 ], [ 20.0, 25.0 ], [ 20.0, 15.0 ], [ 30.0, 20.0 ] ] ] ]\n" +
                "}", MultiPolygon.class));
    }

    @Test
    public void jtsGeometryCollectionDeserializerTest() throws Exception {
        logger.info(":::::::::::::::::::::::::::::::::::JTS_GEOMETRY_COLLECTION : {}", mapper.readValue("{\n" +
                "  \"type\" : \"GeometryCollection\",\n" +
                "  \"crs\" : {\n" +
                "    \"type\" : \"name\",\n" +
                "    \"properties\" : {\n" +
                "      \"name\" : \"EPSG:4326\"\n" +
                "    }\n" +
                "  },\n" +
                "  \"geometries\" : [ {\n" +
                "    \"type\" : \"Point\",\n" +
                "    \"coordinates\" : [ 10.0, 20.0 ]\n" +
                "  }, {\n" +
                "    \"type\" : \"LineString\",\n" +
                "    \"coordinates\" : [ [ 7.0, 7.0 ], [ 6.0, 9.0 ], [ 6.0, 11.0 ], [ 7.0, 12.0 ], [ 9.0, 11.0 ], [ 11.0, 12.0 ], [ 13.0, 11.0 ], [ 13.0, 9.0 ], [ 11.0, 7.0 ], [ 7.0, 7.0 ] ]\n" +
                "  }, {\n" +
                "    \"type\" : \"LineString\",\n" +
                "    \"coordinates\" : [ [ 5.0, 5.0 ], [ 6.0, 5.0 ], [ 6.0, 6.0 ], [ 7.0, 6.0 ], [ 7.0, 7.0 ], [ 8.0, 7.0 ], [ 8.0, 8.0 ], [ 9.0, 9.0 ] ]\n" +
                "  }, {\n" +
                "    \"type\" : \"Polygon\",\n" +
                "    \"coordinates\" : [ [ [ 35.0, 10.0 ], [ 10.0, 20.0 ], [ 15.0, 40.0 ], [ 45.0, 45.0 ], [ 35.0, 10.0 ] ], [ [ 20.0, 30.0 ], [ 35.0, 35.0 ], [ 30.0, 20.0 ], [ 20.0, 30.0 ] ] ]\n" +
                "  }, {\n" +
                "    \"type\" : \"MultiPoint\",\n" +
                "    \"coordinates\" : [ [ 10.0, 40.0 ], [ 40.0, 30.0 ], [ 20.0, 20.0 ], [ 30.0, 10.0 ] ]\n" +
                "  }, {\n" +
                "    \"type\" : \"MultiPolygon\",\n" +
                "    \"coordinates\" : [ [ [ [ 40.0, 40.0 ], [ 20.0, 45.0 ], [ 45.0, 30.0 ], [ 40.0, 40.0 ] ] ], [ [ [ 20.0, 35.0 ], [ 45.0, 20.0 ], [ 30.0, 5.0 ], [ 10.0, 10.0 ], [ 10.0, 30.0 ], [ 20.0, 35.0 ] ], [ [ 30.0, 20.0 ], [ 20.0, 25.0 ], [ 20.0, 15.0 ], [ 30.0, 20.0 ] ] ] ]\n" +
                "  } ]\n" +
                "}", GeometryCollection.class));
    }
}