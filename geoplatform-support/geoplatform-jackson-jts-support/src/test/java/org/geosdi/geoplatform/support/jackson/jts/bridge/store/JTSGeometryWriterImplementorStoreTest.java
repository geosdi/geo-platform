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
package org.geosdi.geoplatform.support.jackson.jts.bridge.store;

import org.geojson.*;
import org.geosdi.geoplatform.support.jackson.jts.deserializer.geometry.writer.GeoJsonLinearRing;
import org.geosdi.geoplatform.support.jackson.jts.deserializer.geometry.writer.bridge.implementor.JTSGeometryWriterImplementor;
import org.geosdi.geoplatform.support.jackson.jts.deserializer.geometry.writer.bridge.store.GPJTSGeometryWriterImplementorStore;
import org.geosdi.geoplatform.support.jackson.jts.deserializer.geometry.writer.bridge.store.JTSGeometryWriterImplementorStore;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Set;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class JTSGeometryWriterImplementorStoreTest {

    private static final Logger logger = LoggerFactory.getLogger(JTSGeometryWriterImplementorStoreTest.class);
    //
    private static final GPJTSGeometryWriterImplementorStore store = new JTSGeometryWriterImplementorStore();

    @Test
    public void getAllJTSGeometryWriterImplementorTest() throws Exception {
        Set<JTSGeometryWriterImplementor<?, ?>> implementors = store.getAllImplementors();
        logger.info("######################JTS_GEOMETRY_WRITER_IMPLEMENTORS : {}\n", implementors);
    }

    @Test
    public void jtsPointGeometryWriterTest() throws Exception {
        logger.info("###################Found : {} for key {}\n", store.getImplementorByKey(Point.class), Point.class);
    }

    @Test
    public void jtsLineStringGeometryWriterTest() throws Exception {
        logger.info("###################Found : {} for key {}\n", store.getImplementorByKey(LineString.class), LineString.class);
    }

    @Test
    public void jtsLinearRingGeometryWriterTest() throws Exception {
        logger.info("###################Found : {} for key {}\n", store.getImplementorByKey(GeoJsonLinearRing.class), GeoJsonLinearRing.class);
    }

    @Test
    public void jtsPolygonGeometryWriterTest() throws Exception {
        logger.info("###################Found : {} for key {}\n", store.getImplementorByKey(Polygon.class), Polygon.class);
    }

    @Test
    public void jtsMultiPointGeometryWriterTest() throws Exception {
        logger.info("###################Found : {} for key {}\n", store.getImplementorByKey(MultiPoint.class), MultiPoint.class);
    }

    @Test
    public void jtsMultiLineStringGeometryWriterTest() throws Exception {
        logger.info("###################Found : {} for key {}\n", store.getImplementorByKey(MultiLineString.class), MultiLineString.class);
    }

    @Test
    public void jtsMultiPolygonGeometryWriterTest() throws Exception {
        logger.info("###################Found : {} for key {}\n", store.getImplementorByKey(MultiPolygon.class), MultiPolygon.class);
    }

    @Test
    public void jtsGeometryColletionWriterTest() throws Exception {
        logger.info("###################Found : {} for key {}\n", store.getImplementorByKey(GeometryCollection.class), GeometryCollection.class);
    }

    @Test
    public void jtsGeometryFeatureColletionWriterTest() throws Exception {
        logger.info("###################Found : {} for key {}\n", store.getImplementorByKey(FeatureCollection.class), FeatureCollection.class);
    }

    @Test
    public void jtsGeometryFeatureWriterTest() throws Exception {
        logger.info("###################Found : {} for key {}\n", store.getImplementorByKey(Feature.class), Feature.class);
    }
}