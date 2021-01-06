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
package org.geosdi.geoplatform.support.jackson.jts.serializer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vividsolutions.jts.geom.*;
import com.vividsolutions.jts.io.WKTReader;
import org.geosdi.geoplatform.support.jackson.jts.GPJacksonJTSSupport;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

import static org.junit.runners.MethodSorters.NAME_ASCENDING;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@FixMethodOrder(value = NAME_ASCENDING)
public class GeoJsonVividisolutionsSerializerTest {

    private static final Logger logger = LoggerFactory.getLogger(GeoJsonVividisolutionsSerializerTest.class);
    //
    private static final ObjectMapper mapper = new GPJacksonJTSSupport().getDefaultMapper();
    private static GeometryFactory geometryFactory = new GeometryFactory();
    private static WKTReader reader = new WKTReader(geometryFactory);

    @Test
    public void a_pointSerializerTest() throws Exception {
        Coordinate ptc = new Coordinate(10, 20);
        Point point = geometryFactory.createPoint(ptc);
        point.setSRID(4326);
        String geoJsonPoint = mapper.writeValueAsString(point);
        logger.info(":::::::::::::::::::::::GEO_JSON_POINT : \n{}\n", geoJsonPoint);
        org.geojson.Point p = mapper.readValue(geoJsonPoint, org.geojson.Point.class);
        mapper.writeValue(new File("./target/Point.json"), p);
    }

    @Test
    public void b_lineStringSerializerTest() throws Exception {
        Coordinate[] lsc = new Coordinate[8];
        lsc[0] = new Coordinate(5.0d, 5.0d);
        lsc[1] = new Coordinate(6.0d, 5.0d);
        lsc[2] = new Coordinate(6.0d, 6.0d);
        lsc[3] = new Coordinate(7.0d, 6.0d);
        lsc[4] = new Coordinate(7.0d, 7.0d);
        lsc[5] = new Coordinate(8.0d, 7.0d);
        lsc[6] = new Coordinate(8.0d, 8.0d);
        lsc[7] = new Coordinate(9.0d, 9.0d);
        LineString lineString = geometryFactory.createLineString(lsc);
        lineString.setSRID(4326);
        String lineStringGeoJsonString = mapper.writeValueAsString(lineString);
        logger.info(":::::::::::::::::::::::GEO_JSON_LINE_STRING : \n{}\n", lineStringGeoJsonString);
        org.geojson.LineString l = mapper.readValue(lineStringGeoJsonString, org.geojson.LineString.class);
        mapper.writeValue(new File("./target/LineString.json"), l);
    }

    @Test
    public void c_linearRingSerializerTest() throws Exception {
        Coordinate[] lrc = new Coordinate[10];
        lrc[0] = new Coordinate(7, 7);
        lrc[1] = new Coordinate(6, 9);
        lrc[2] = new Coordinate(6, 11);
        lrc[3] = new Coordinate(7, 12);
        lrc[4] = new Coordinate(9, 11);
        lrc[5] = new Coordinate(11, 12);
        lrc[6] = new Coordinate(13, 11);
        lrc[7] = new Coordinate(13, 9);
        lrc[8] = new Coordinate(11, 7);
        lrc[9] = new Coordinate(7, 7);
        LinearRing linearRing = geometryFactory.createLinearRing(lrc);
        linearRing.setSRID(4326);
        String linearRingGeoJsonString = mapper.writeValueAsString(linearRing);
        logger.info(":::::::::::::::::::::::GEO_JSON_LINE_STRING : \n{}\n", linearRingGeoJsonString);
        org.geojson.LineString l = mapper.readValue(linearRingGeoJsonString, org.geojson.LineString.class);
        mapper.writeValue(new File("./target/LinearRing.json"), l);
    }

    @Test
    public void d_polygonWithHolesSerializerTest() throws Exception {
        Geometry polygon = reader.read("POLYGON ((35 10, 10 20, 15 40,"
                + " 45 45, 35 10), (20 30, 35 35, 30 20, 20 30))");
        polygon.setSRID(4326);
        String polygonGeoJsonString = mapper.writeValueAsString(polygon);
        logger.info(":::::::::::::::::::::::GEO_JSON_POLYGON : \n{}\n", polygonGeoJsonString);
        org.geojson.Polygon p = mapper.readValue(polygonGeoJsonString, org.geojson.Polygon.class);
        mapper.writeValue(new File("./target/Polygon.json"), p);
    }

    @Test
    public void e_polygonWithoutHolesSerializerTest() throws Exception {
        Geometry polygon = reader.read("POLYGON ((35 10, 10 20, 15 40,"
                + " 45 45, 35 10))");
        polygon.setSRID(4326);
        String polygonGeoJsonString = mapper.writeValueAsString(polygon);
        logger.info(":::::::::::::::::::::::GEO_JSON_POLYGON : \n{}\n", polygonGeoJsonString);
        org.geojson.Polygon p = mapper.readValue(polygonGeoJsonString, org.geojson.Polygon.class);
        mapper.writeValue(new File("./target/PolygonWithoutHoles.json"), p);
    }

    @Test
    public void f_multiPointSerializerTest() throws Exception {
        Geometry multiPoint = reader.read("MULTIPOINT ((10 40), (40 30), "
                + "(20 20), (30 10))");
        multiPoint.setSRID(4326);
        String multiPointGeoJsonString = mapper.writeValueAsString(multiPoint);
        logger.info(":::::::::::::::::::::::GEO_JSON_MULTI_POINT : \n{}\n", multiPointGeoJsonString);
        org.geojson.MultiPoint multiPointGeoJson = mapper.readValue(multiPointGeoJsonString, org.geojson.MultiPoint.class);
        mapper.writeValue(new File("./target/MultiPoint.json"), multiPointGeoJson);
    }

    @Test
    public void g_multiLineStringSerializerTest() throws Exception {
        Geometry multiLineString = reader.read("MULTILINESTRING ((10 10, 20 20, 10 40), "
                + "(40 40, 30 30, 40 20, 30 10))");
        multiLineString.setSRID(4326);
        String multiLineStringGeoJsonString = mapper.writeValueAsString(multiLineString);
        logger.info(":::::::::::::::::::::::GEO_JSON_MULTI_LINE_STRING : \n{}\n", multiLineStringGeoJsonString);
        org.geojson.MultiLineString multiLineStringGeoJson = mapper.readValue(multiLineStringGeoJsonString, org.geojson.MultiLineString.class);
        mapper.writeValue(new File("./target/MultiLineString.json"), multiLineStringGeoJson);
    }

    @Test
    public void h_multiPolygonSerializerTest() throws Exception {
        Geometry multiPolygon = reader.read("MULTIPOLYGON (((40 40, 20 45,"
                + " 45 30, 40 40)), ((20 35, 45 20, 30 5, "
                + "10 10, 10 30, 20 35), (30 20, 20 25, 20 15, 30 20)))");
        multiPolygon.setSRID(4326);
        String multiPolygonGeoJsonString = mapper.writeValueAsString(multiPolygon);
        logger.info(":::::::::::::::::::::::GEO_JSON_MULTI_POLYGON : \n{}\n", multiPolygonGeoJsonString);
        org.geojson.MultiPolygon p = mapper.readValue(multiPolygonGeoJsonString, org.geojson.MultiPolygon.class);
        mapper.writeValue(new File("./target/MultiPolygon.json"), p);
    }

    @Test
    public void i_geometryCollectionSerializerTest() throws Exception {
        Geometry geometryCollection = reader.read("GEOMETRYCOLLECTION(POINT(0 0), "
                + "POINT(1 0), POINT(1 1), POINT(0 1), LINESTRING(4 6,7 10), "
                + "POLYGON ((35 10, 10 20, 15 40, 45 45, 35 10),"
                + "(20 30, 35 35, 30 20, 20 30)), LINEARRING (7 7, 6 9, 6 11,"
                + " 7 12, 9 11, 11 12, 13 11, 13 9, 11 7, 7 7))");
        geometryCollection.setSRID(4326);
        String geometryCollectionGeoJsonString = mapper.writeValueAsString(geometryCollection);
        logger.info(":::::::::::::::::::::::GEO_JSON_GEOMETRY_COLLECTION : \n{}\n", geometryCollectionGeoJsonString);
        org.geojson.GeometryCollection p = mapper.readValue(geometryCollectionGeoJsonString, org.geojson.GeometryCollection.class);
        mapper.writeValue(new File("./target/GeometryCollection.json"), p);
    }

    @Test
    public void l_createComplexGeometryCollectionTest() throws Exception {
        Coordinate ptc = new Coordinate(10, 20);
        Point point = geometryFactory.createPoint(ptc);
        point.setSRID(4326);
        Coordinate[] lsc = new Coordinate[8];
        lsc[0] = new Coordinate(5.0d, 5.0d);
        lsc[1] = new Coordinate(6.0d, 5.0d);
        lsc[2] = new Coordinate(6.0d, 6.0d);
        lsc[3] = new Coordinate(7.0d, 6.0d);
        lsc[4] = new Coordinate(7.0d, 7.0d);
        lsc[5] = new Coordinate(8.0d, 7.0d);
        lsc[6] = new Coordinate(8.0d, 8.0d);
        lsc[7] = new Coordinate(9.0d, 9.0d);
        LineString lineString = geometryFactory.createLineString(lsc);
        lineString.setSRID(4326);
        Coordinate[] lrc = new Coordinate[10];
        lrc[0] = new Coordinate(7, 7);
        lrc[1] = new Coordinate(6, 9);
        lrc[2] = new Coordinate(6, 11);
        lrc[3] = new Coordinate(7, 12);
        lrc[4] = new Coordinate(9, 11);
        lrc[5] = new Coordinate(11, 12);
        lrc[6] = new Coordinate(13, 11);
        lrc[7] = new Coordinate(13, 9);
        lrc[8] = new Coordinate(11, 7);
        lrc[9] = new Coordinate(7, 7);
        LinearRing linearRing = geometryFactory.createLinearRing(lrc);
        linearRing.setSRID(4326);
        Geometry polygon = reader.read("POLYGON ((35 10, 10 20, 15 40,"
                + " 45 45, 35 10), (20 30, 35 35, 30 20, 20 30))");
        polygon.setSRID(4326);
        Geometry multiPoint = reader.read("MULTIPOINT ((10 40), (40 30), "
                + "(20 20), (30 10))");
        multiPoint.setSRID(4326);
        Geometry multiPolygon = reader.read("MULTIPOLYGON (((40 40, 20 45,"
                + " 45 30, 40 40)), ((20 35, 45 20, 30 5, "
                + "10 10, 10 30, 20 35), (30 20, 20 25, 20 15, 30 20)))");
        multiPolygon.setSRID(4326);
        Geometry geometryCollection = reader.read("GEOMETRYCOLLECTION(POINT(0 0), "
                + "POINT(1 0), POINT(1 1), POINT(0 1), LINESTRING(4 6,7 10), "
                + "POLYGON ((35 10, 10 20, 15 40, 45 45, 35 10),"
                + "(20 30, 35 35, 30 20, 20 30)), LINEARRING (7 7, 6 9, 6 11,"
                + " 7 12, 9 11, 11 12, 13 11, 13 9, 11 7, 7 7))");
        geometryCollection.setSRID(4326);
        GeometryCollection geometryCollectionComplex = new GeometryCollection(new Geometry[]{point,
                linearRing, lineString, polygon, multiPoint, multiPolygon, geometryCollection}, geometryFactory);
        geometryCollectionComplex.setSRID(4326);
        String geometryCollectionGeoJsonString = mapper.writeValueAsString(geometryCollectionComplex);
        logger.info(":::::::::::::::::::::::GEO_JSON_GEOMETRY_COLLECTION : \n{}\n", geometryCollectionGeoJsonString);
        org.geojson.GeometryCollection p = mapper.readValue(geometryCollectionGeoJsonString, org.geojson.GeometryCollection.class);
        mapper.writeValue(new File("./target/GeometryCollectionComplex.json"), p);
    }
}