package org.geosdi.geoplatform.support.jackson.jts.adapter;

import org.geosdi.geoplatform.support.jackson.jts.adapter.coordinate.GPJTSCoordinateAdapter;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.locationtech.jts.geom.*;
import org.locationtech.jts.io.WKTReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.runners.MethodSorters.NAME_ASCENDING;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@FixMethodOrder(value = NAME_ASCENDING)
public class JTSLocationtechGeometryAdapterTest {

    private static final Logger logger = LoggerFactory.getLogger(JTSLocationtechGeometryAdapterTest.class);
    //
    private static GeometryFactory geometryFactory = new GeometryFactory();
    private static WKTReader reader = new WKTReader(geometryFactory);

    @Test
    public void a_adaptJTSPointTest() throws Exception {
        Coordinate ptc = new Coordinate(10, 20);
        Point point = geometryFactory.createPoint(ptc);
        point.setSRID(4326);
        GPJTSGeometryAdapter pointAdapter = JTSGeometryAdapter.adapt(point);
        Assert.assertTrue(pointAdapter instanceof JTSPointAdapter);
        Assert.assertTrue(pointAdapter.getSRID() == 4326);
        GPJTSCoordinateAdapter coordinateAdapter = pointAdapter.getCoordinate();
        logger.info("########################COORDINATE[x : {} - y : {}]\n", coordinateAdapter.x(), coordinateAdapter.y());
    }

    @Test
    public void b_adaptJTSLineStringTest() throws Exception {
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
        GPJTSGeometryAdapter lineStringAdapter = JTSGeometryAdapter.adapt(lineString);
        Assert.assertTrue(lineStringAdapter instanceof JTSLineStringAdapter);
        Assert.assertTrue(lineStringAdapter.getSRID() == 4326);
        Assert.assertTrue(lineStringAdapter.getCoordinates().length == 8);
    }

    @Test
    public void c_adaptJTSLinearRingTest() throws Exception {
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
        GPJTSGeometryAdapter linearRingAdapter = JTSGeometryAdapter.adapt(linearRing);
        Assert.assertTrue(linearRingAdapter instanceof JTSLinearRingAdapter);
        Assert.assertTrue(linearRingAdapter.getSRID() == 4326);
        Assert.assertTrue(linearRingAdapter.getCoordinates().length == 10);
    }

    @Test
    public void d_adapterJTSPolygonTest() throws Exception {
        Geometry polygon = reader.read("POLYGON ((35 10, 10 20, 15 40,"
                + " 45 45, 35 10), (20 30, 35 35, 30 20, 20 30))");
        polygon.setSRID(4326);
        GPJTSGeometryAdapter geometryAdapter = JTSGeometryAdapter.adapt(polygon);
        Assert.assertTrue(geometryAdapter instanceof JTSPolygonAdapter);
        Assert.assertTrue(geometryAdapter.getSRID() == 4326);
        JTSPolygonAdapter polygonAdapter = (JTSPolygonAdapter) geometryAdapter;
        Assert.assertTrue(polygonAdapter.getNumInteriorRing() == 1);
        Assert.assertTrue(polygonAdapter.getInteriorRingN(0) instanceof JTSLineStringAdapter);
    }

    @Test
    public void e_adaptJTSMultiPointTest() throws Exception {
        Geometry multiPoint = reader.read("MULTIPOINT ((10 40), (40 30), "
                + "(20 20), (30 10))");
        multiPoint.setSRID(4326);
        GPJTSGeometryAdapter geometryAdapter = JTSGeometryAdapter.adapt(multiPoint);
        Assert.assertTrue(geometryAdapter instanceof JTSMultiPointAdapter);
        Assert.assertTrue(geometryAdapter.getSRID() == 4326);
        JTSMultiPointAdapter multiPointAdapter = (JTSMultiPointAdapter) geometryAdapter;
        Assert.assertTrue(multiPointAdapter.getNumGeometries() == 4);
        Assert.assertTrue(multiPointAdapter.getGeometryN(0) instanceof JTSPointAdapter);
        Assert.assertTrue(multiPointAdapter.getGeometryN(1) instanceof JTSPointAdapter);
        Assert.assertTrue(multiPointAdapter.getGeometryN(2) instanceof JTSPointAdapter);
        Assert.assertTrue(multiPointAdapter.getGeometryN(3) instanceof JTSPointAdapter);
    }

    @Test
    public void f_adaptJTSMultiLineStringTest() throws Exception {
        Geometry multiLineString = reader.read("MULTILINESTRING ((10 10, 20 20, 10 40), "
                + "(40 40, 30 30, 40 20, 30 10))");
        multiLineString.setSRID(4326);
        GPJTSGeometryAdapter geometryAdapter = JTSGeometryAdapter.adapt(multiLineString);
        Assert.assertTrue(geometryAdapter instanceof JTSMultiLinestringAdapter);
        Assert.assertTrue(geometryAdapter.getSRID() == 4326);
        JTSMultiLinestringAdapter multiLinestringAdapter = (JTSMultiLinestringAdapter) geometryAdapter;
        Assert.assertTrue(multiLinestringAdapter.getNumGeometries() == 2);
        Assert.assertTrue(multiLinestringAdapter.getGeometryN(0) instanceof JTSLineStringAdapter);
        Assert.assertTrue(multiLinestringAdapter.getGeometryN(1) instanceof JTSLineStringAdapter);
    }

    @Test
    public void h_adaptJTSMultiPolygonTest() throws Exception {
        Geometry multiPolygon = reader.read("MULTIPOLYGON (((40 40, 20 45,"
                + " 45 30, 40 40)), ((20 35, 45 20, 30 5, "
                + "10 10, 10 30, 20 35), (30 20, 20 25, 20 15, 30 20)))");
        multiPolygon.setSRID(4326);
        GPJTSGeometryAdapter geometryAdapter = JTSGeometryAdapter.adapt(multiPolygon);
        Assert.assertTrue(geometryAdapter instanceof JTSMultiPolygonAdapter);
        Assert.assertTrue(geometryAdapter.getSRID() == 4326);
        JTSMultiPolygonAdapter multiPolygonAdapter = (JTSMultiPolygonAdapter) geometryAdapter;
        Assert.assertTrue(multiPolygonAdapter.getNumGeometries() == 2);
        Assert.assertTrue(multiPolygonAdapter.getGeometryN(0) instanceof JTSPolygonAdapter);
        Assert.assertTrue(multiPolygonAdapter.getGeometryN(1) instanceof JTSPolygonAdapter);
    }

    @Test
    public void i_adaptJTSGeometryCollectionTest() throws Exception {
        Geometry geometryCollection = reader.read("GEOMETRYCOLLECTION(POINT(0 0), "
                + "POINT(1 0), POINT(1 1), POINT(0 1), LINESTRING(4 6,7 10), "
                + "POLYGON ((35 10, 10 20, 15 40, 45 45, 35 10),"
                + "(20 30, 35 35, 30 20, 20 30)), LINEARRING (7 7, 6 9, 6 11,"
                + " 7 12, 9 11, 11 12, 13 11, 13 9, 11 7, 7 7))");
        geometryCollection.setSRID(4326);
        GPJTSGeometryAdapter geometryAdapter = JTSGeometryAdapter.adapt(geometryCollection);
        Assert.assertTrue(geometryAdapter instanceof JTSGeometryCollectionAdapter);
        Assert.assertTrue(geometryAdapter.getSRID() == 4326);
        JTSGeometryCollectionAdapter geometryCollectionAdapter = (JTSGeometryCollectionAdapter) geometryAdapter;
        Assert.assertTrue(geometryCollectionAdapter.getNumGeometries() == 7);
        Assert.assertTrue(geometryCollectionAdapter.getGeometryN(0) instanceof JTSPointAdapter);
        Assert.assertTrue(geometryCollectionAdapter.getGeometryN(1) instanceof JTSPointAdapter);
        Assert.assertTrue(geometryCollectionAdapter.getGeometryN(2) instanceof JTSPointAdapter);
        Assert.assertTrue(geometryCollectionAdapter.getGeometryN(3) instanceof JTSPointAdapter);
        Assert.assertTrue(geometryCollectionAdapter.getGeometryN(4) instanceof JTSLineStringAdapter);
        Assert.assertTrue(geometryCollectionAdapter.getGeometryN(5) instanceof JTSPolygonAdapter);
        Assert.assertTrue(geometryCollectionAdapter.getGeometryN(6) instanceof JTSLinearRingAdapter);
    }

    @Test
    public void i_adaptJTSGeometryCollectionComplexTest() throws Exception {
        Geometry geometryCollection = reader.read("GEOMETRYCOLLECTION(POINT(0 0), "
                + "POINT(1 0), POINT(1 1), POINT(0 1), LINESTRING(4 6,7 10), "
                + "POLYGON ((35 10, 10 20, 15 40, 45 45, 35 10),"
                + "(20 30, 35 35, 30 20, 20 30)), LINEARRING (7 7, 6 9, 6 11,"
                + " 7 12, 9 11, 11 12, 13 11, 13 9, 11 7, 7 7))");
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
        GeometryCollection geometryCollectionComplex = new GeometryCollection(new Geometry[]{point,
                linearRing, lineString, polygon, multiPoint, multiPolygon, geometryCollection}, geometryFactory);
        geometryCollectionComplex.setSRID(4326);
        GPJTSGeometryAdapter geometryAdapter = JTSGeometryAdapter.adapt(geometryCollectionComplex);
        Assert.assertTrue(geometryAdapter instanceof JTSGeometryCollectionAdapter);
        Assert.assertTrue(geometryAdapter.getSRID() == 4326);
        Assert.assertTrue(geometryAdapter.getNumGeometries() == 7);
        Assert.assertTrue(geometryAdapter.getGeometryN(0) instanceof JTSPointAdapter);
        Assert.assertTrue(geometryAdapter.getGeometryN(1) instanceof JTSLinearRingAdapter);
        Assert.assertTrue(geometryAdapter.getGeometryN(2) instanceof JTSLineStringAdapter);
        Assert.assertTrue(geometryAdapter.getGeometryN(3) instanceof JTSPolygonAdapter);
        Assert.assertTrue(geometryAdapter.getGeometryN(4) instanceof JTSMultiPointAdapter);
        Assert.assertTrue(geometryAdapter.getGeometryN(5) instanceof JTSMultiPolygonAdapter);
        Assert.assertTrue(geometryAdapter.getGeometryN(6) instanceof JTSGeometryCollectionAdapter);
    }
}