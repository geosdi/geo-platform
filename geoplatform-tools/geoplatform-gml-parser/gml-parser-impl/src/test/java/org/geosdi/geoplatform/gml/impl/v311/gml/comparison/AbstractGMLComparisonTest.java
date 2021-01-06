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
package org.geosdi.geoplatform.gml.impl.v311.gml.comparison;

import org.geosdi.geoplatform.gml.api.jaxb.context.GMLJAXBContext;
import org.geosdi.geoplatform.gml.api.jaxb.context.GMLUnmarshaller;
import org.geosdi.geoplatform.gml.impl.v311.jaxb.context.pool.GMLJAXBContextPooledV311;
import org.junit.BeforeClass;
import org.locationtech.jts.geom.Geometry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

import static java.io.File.separator;
import static java.util.stream.Collectors.joining;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public abstract class AbstractGMLComparisonTest {

    enum GMLTaskType {
        POINT_SIMPLE,
        POINT_POOLED,
        LINE_STRING_SIMPE,
        LINE_STRING_POOLED,
        LINEAR_RING_SIMPLE,
        LINEAR_RING_POOLED,
        POLYGON_SIMPE,
        POLYGON_POOLED,
        MULTI_POINT_SIMPLE,
        MULTI_POINT_POOLED,
        MULTI_LINE_STRING_SIMPLE,
        MULTI_LINE_STRING_POOLED,
        MULTI_POLYGON_SIMPLE,
        MULTI_POLYGON_POOLED,
        MULTI_CURVE_SIMPLE,
        MULTI_CURVE_POOLED,
        MULTI_SURFACE_SIMPLE,
        MULTI_SURFACE_POOLED,
        GEOMETRY_COLLECTION_SIMPLE,
        GEOMETRY_COLLECTION_POOLED;
    }

    private static final ThreadFactory GMLComparisonThreadFactory = new ThreadFactory() {

        private final AtomicInteger threadID = new AtomicInteger(0);

        @Override
        public Thread newThread(Runnable r) {
            Thread thread = Executors.privilegedThreadFactory().newThread(r);
            thread.setName("GMLComparisonThread - " + threadID.getAndIncrement());
            thread.setDaemon(Boolean.TRUE);
            return thread;
        }
    };

    protected static final Logger logger = LoggerFactory.getLogger(AbstractGMLComparisonTest.class);
    //
    private static File pointFile;
    private static File lineStringFile;
    private static File linearRingFile;
    private static File polygonFile;
    private static File multiPointFile;
    private static File multiLineStringFile;
    private static File multiPolygonFile;
    private static File multiCurveFile;
    private static File multiSurfaceFile;
    private static File geometryCollectionFile;
    protected static final AtomicInteger count = new AtomicInteger(0);

    @BeforeClass
    public static void loadFile() throws Exception {
        String basePath = Stream.of(new File(".").getCanonicalPath(), "src", "test", "resources")
                .collect(joining(separator, "", separator));
        String pointfileString = basePath.concat("Point.xml");
        pointFile = new File(pointfileString);
        lineStringFile = new File(basePath.concat("LineString.xml"));
        linearRingFile = new File(basePath.concat("LinearRing.xml"));
        polygonFile = new File(basePath.concat("Polygon.xml"));
        multiPointFile = new File(basePath.concat("MultiPoint.xml"));
        multiLineStringFile = new File(basePath.concat("MultiLineString.xml"));
        multiPolygonFile = new File(basePath.concat("MultiPolygon.xml"));
        multiCurveFile = new File(basePath.concat("MultiCurve.xml"));
        multiSurfaceFile = new File(basePath.concat("MultiSurface.xml"));
        geometryCollectionFile = new File(basePath.concat("GeometryCollection.xml"));
    }

    protected int defineNumThreads() {
        return 150;
    }

    protected long executeMultiThreadsTasks(GMLJAXBContext jaxbContext, GMLTaskType gmlTaskType)
            throws Exception {
        long time = 0;

        int numThreads = defineNumThreads();

        ExecutorService executor = Executors.newFixedThreadPool(numThreads,
                GMLComparisonThreadFactory);

        List<GMLPointSextanteTask> tasks = new ArrayList<>(numThreads);
        fillTasksList(jaxbContext, tasks, gmlTaskType, numThreads);

        List<Future<Long>> results = executor.invokeAll(tasks);
        executor.shutdown();

        boolean flag = executor.awaitTermination(1, TimeUnit.MINUTES);

        if (flag) {
            for (Future<Long> future : results) {
                time += future.get();
            }
        } else {
            throw new InterruptedException("Some Threads are not executed.");
        }

        return time;
    }

    private void fillTasksList(GMLJAXBContext jaxbContext, List<GMLPointSextanteTask> tasks,
            GMLTaskType gmlTaskType, int numThreads) {
        switch (gmlTaskType) {
            case POINT_SIMPLE:
                for (int i = 0; i < numThreads; i++)
                    tasks.add(new GMLPointSextanteTask(jaxbContext));
                break;
            case POINT_POOLED:
                for (int i = 0; i < numThreads; i++)
                    tasks.add(new GMLPointPooledSextanteTask(jaxbContext));
                break;
            case LINE_STRING_SIMPE:
                for (int i = 0; i < numThreads; i++)
                    tasks.add(new GMLLineStringSextanteTask(jaxbContext));
                break;
            case LINE_STRING_POOLED:
                for (int i = 0; i < numThreads; i++)
                    tasks.add(new GMLLineStringPooledSextanteTask(jaxbContext));
                break;
            case LINEAR_RING_SIMPLE:
                for (int i = 0; i < numThreads; i++)
                    tasks.add(new GMLLinearRingSextanteTask(jaxbContext));
                break;
            case LINEAR_RING_POOLED:
                for (int i = 0; i < numThreads; i++)
                    tasks.add(new GMLLineStringPooledSextanteTask(jaxbContext));
                break;
            case POLYGON_SIMPE:
                for (int i = 0; i < numThreads; i++)
                    tasks.add(new GMLPolygonSextanteTask(jaxbContext));
                break;
            case POLYGON_POOLED:
                for (int i = 0; i < numThreads; i++)
                    tasks.add(new GMLPolygonPooledSextanteTask(jaxbContext));
                break;
            case MULTI_POINT_SIMPLE:
                for (int i = 0; i < numThreads; i++)
                    tasks.add(new GMLMultiPointSextanteTask(jaxbContext));
                break;
            case MULTI_POINT_POOLED:
                for (int i = 0; i < numThreads; i++)
                    tasks.add(new GMLMultiPointPooledSextanteTask(jaxbContext));
                break;
            case MULTI_LINE_STRING_SIMPLE:
                for (int i = 0; i < numThreads; i++)
                    tasks.add(new GMLMultiLineStringSextanteTask(jaxbContext));
                break;
            case MULTI_LINE_STRING_POOLED:
                for (int i = 0; i < numThreads; i++)
                    tasks.add(new GMLMultiLineStringPooledSextanteTask(jaxbContext));
                break;
            case MULTI_POLYGON_SIMPLE:
                for (int i = 0; i < numThreads; i++)
                    tasks.add(new GMLMultiPolygonSextanteTask(jaxbContext));
                break;
            case MULTI_POLYGON_POOLED:
                for (int i = 0; i < numThreads; i++)
                    tasks.add(new GMLMultiPolygonPooledSextanteTask(jaxbContext));
                break;
            case MULTI_CURVE_SIMPLE:
                for (int i = 0; i < numThreads; i++)
                    tasks.add(new GMLMultiCurveSextanteTask(jaxbContext));
                break;
            case MULTI_CURVE_POOLED:
                for (int i = 0; i < numThreads; i++)
                    tasks.add(new GMLMultiCurvePooledSextanteTask(jaxbContext));
                break;
            case MULTI_SURFACE_SIMPLE:
                for (int i = 0; i < numThreads; i++)
                    tasks.add(new GMLMultiSurfaceSextanteTask(jaxbContext));
                break;
            case MULTI_SURFACE_POOLED:
                for (int i = 0; i < numThreads; i++)
                    tasks.add(new GMLMultiSurfacePooledSextanteTask(jaxbContext));
                break;
            case GEOMETRY_COLLECTION_SIMPLE:
                for (int i = 0; i < numThreads; i++)
                    tasks.add(new GMLGeometryCollectionSextanteTask(jaxbContext));
                break;
            case GEOMETRY_COLLECTION_POOLED:
                for (int i = 0; i < numThreads; i++)
                    tasks.add(new GMLGeometryCollectionPooledSextanteTask(jaxbContext));
                break;
        }
    }


    private class GMLPointSextanteTask implements Callable<Long> {

        final GMLJAXBContext jaxbContext;

        public GMLPointSextanteTask(GMLJAXBContext theJaxbContext) {
            this.jaxbContext = theJaxbContext;
        }

        @Override
        public Long call() throws Exception {
            long start = System.currentTimeMillis();

            GMLUnmarshaller unmarshaller = jaxbContext.acquireUnmarshaller();
            Geometry geometry = (Geometry) unmarshaller.unmarshal(pointFile);

            StringWriter writer = new StringWriter();
            jaxbContext.acquireMarshaller().marshal(geometry, writer);
            logger.debug("\n{}\n", writer);
            count.incrementAndGet();

            return System.currentTimeMillis() - start;
        }
    }

    private class GMLPointPooledSextanteTask extends GMLPointSextanteTask {

        public GMLPointPooledSextanteTask(GMLJAXBContext theJaxbContext) {
            super(theJaxbContext);
        }

        @Override
        public Long call() throws Exception {
            long start = System.currentTimeMillis();

            Geometry geometry = (Geometry) ((GMLJAXBContextPooledV311) jaxbContext).unmarshal(pointFile);
            StringWriter writer = new StringWriter();
            ((GMLJAXBContextPooledV311) jaxbContext).marshal(geometry, writer);
            logger.debug("\n{}\n", writer);
            count.incrementAndGet();

            return System.currentTimeMillis() - start;
        }
    }

    private class GMLLineStringSextanteTask extends GMLPointSextanteTask {

        public GMLLineStringSextanteTask(GMLJAXBContext theJaxbContext) {
            super(theJaxbContext);
        }

        @Override
        public Long call() throws Exception {
            long start = System.currentTimeMillis();

            GMLUnmarshaller unmarshaller = jaxbContext.acquireUnmarshaller();
            Geometry geometry = (Geometry) unmarshaller.unmarshal(lineStringFile);

            StringWriter writer = new StringWriter();
            jaxbContext.acquireMarshaller().marshal(geometry, writer);
            logger.debug("\n{}\n", writer);
            count.incrementAndGet();

            return System.currentTimeMillis() - start;
        }
    }

    private class GMLLineStringPooledSextanteTask extends GMLPointSextanteTask {

        public GMLLineStringPooledSextanteTask(GMLJAXBContext theJaxbContext) {
            super(theJaxbContext);
        }

        @Override
        public Long call() throws Exception {
            long start = System.currentTimeMillis();

            Geometry geometry = (Geometry) ((GMLJAXBContextPooledV311) jaxbContext).unmarshal(lineStringFile);
            StringWriter writer = new StringWriter();
            ((GMLJAXBContextPooledV311) jaxbContext).marshal(geometry, writer);
            logger.debug("\n{}\n", writer);
            count.incrementAndGet();

            return System.currentTimeMillis() - start;
        }
    }

    private class GMLLinearRingSextanteTask extends GMLPointSextanteTask {

        public GMLLinearRingSextanteTask(GMLJAXBContext theJaxbContext) {
            super(theJaxbContext);
        }

        @Override
        public Long call() throws Exception {
            long start = System.currentTimeMillis();

            GMLUnmarshaller unmarshaller = jaxbContext.acquireUnmarshaller();
            Geometry geometry = (Geometry) unmarshaller.unmarshal(linearRingFile);

            StringWriter writer = new StringWriter();
            jaxbContext.acquireMarshaller().marshal(geometry, writer);
            logger.debug("\n{}\n", writer);
            count.incrementAndGet();

            return System.currentTimeMillis() - start;
        }
    }

    private class GMLLinearRingPooledSextanteTask extends GMLPointSextanteTask {

        public GMLLinearRingPooledSextanteTask(GMLJAXBContext theJaxbContext) {
            super(theJaxbContext);
        }

        @Override
        public Long call() throws Exception {
            long start = System.currentTimeMillis();

            Geometry geometry = (Geometry) ((GMLJAXBContextPooledV311) jaxbContext).unmarshal(linearRingFile);
            StringWriter writer = new StringWriter();
            ((GMLJAXBContextPooledV311) jaxbContext).marshal(geometry, writer);
            logger.debug("\n{}\n", writer);
            count.incrementAndGet();

            return System.currentTimeMillis() - start;
        }
    }

    private class GMLPolygonSextanteTask extends GMLPointSextanteTask {

        public GMLPolygonSextanteTask(GMLJAXBContext theJaxbContext) {
            super(theJaxbContext);
        }

        @Override
        public Long call() throws Exception {
            long start = System.currentTimeMillis();

            GMLUnmarshaller unmarshaller = jaxbContext.acquireUnmarshaller();
            Geometry geometry = (Geometry) unmarshaller.unmarshal(polygonFile);

            StringWriter writer = new StringWriter();
            jaxbContext.acquireMarshaller().marshal(geometry, writer);
            logger.debug("\n{}\n", writer);
            count.incrementAndGet();

            return System.currentTimeMillis() - start;
        }
    }

    private class GMLPolygonPooledSextanteTask extends GMLPointSextanteTask {

        public GMLPolygonPooledSextanteTask(GMLJAXBContext theJaxbContext) {
            super(theJaxbContext);
        }

        @Override
        public Long call() throws Exception {
            long start = System.currentTimeMillis();

            Geometry geometry = (Geometry) ((GMLJAXBContextPooledV311) jaxbContext).unmarshal(polygonFile);
            StringWriter writer = new StringWriter();
            ((GMLJAXBContextPooledV311) jaxbContext).marshal(geometry, writer);
            logger.debug("\n{}\n", writer);
            count.incrementAndGet();

            return System.currentTimeMillis() - start;
        }
    }

    private class GMLMultiPointSextanteTask extends GMLPointSextanteTask {

        public GMLMultiPointSextanteTask(GMLJAXBContext theJaxbContext) {
            super(theJaxbContext);
        }

        @Override
        public Long call() throws Exception {
            long start = System.currentTimeMillis();

            GMLUnmarshaller unmarshaller = jaxbContext.acquireUnmarshaller();
            Geometry geometry = (Geometry) unmarshaller.unmarshal(multiPointFile);

            StringWriter writer = new StringWriter();
            jaxbContext.acquireMarshaller().marshal(geometry, writer);
            logger.debug("\n{}\n", writer);
            count.incrementAndGet();

            return System.currentTimeMillis() - start;
        }
    }

    private class GMLMultiPointPooledSextanteTask extends GMLPointSextanteTask {

        public GMLMultiPointPooledSextanteTask(GMLJAXBContext theJaxbContext) {
            super(theJaxbContext);
        }

        @Override
        public Long call() throws Exception {
            long start = System.currentTimeMillis();

            Geometry geometry = (Geometry) ((GMLJAXBContextPooledV311) jaxbContext).unmarshal(multiPointFile);
            StringWriter writer = new StringWriter();
            ((GMLJAXBContextPooledV311) jaxbContext).marshal(geometry, writer);
            logger.debug("\n{}\n", writer);
            count.incrementAndGet();

            return System.currentTimeMillis() - start;
        }
    }

    private class GMLMultiLineStringSextanteTask extends GMLPointSextanteTask {

        public GMLMultiLineStringSextanteTask(GMLJAXBContext theJaxbContext) {
            super(theJaxbContext);
        }

        @Override
        public Long call() throws Exception {
            long start = System.currentTimeMillis();

            GMLUnmarshaller unmarshaller = jaxbContext.acquireUnmarshaller();
            Geometry geometry = (Geometry) unmarshaller.unmarshal(multiLineStringFile);

            StringWriter writer = new StringWriter();
            jaxbContext.acquireMarshaller().marshal(geometry, writer);
            logger.debug("\n{}\n", writer);
            count.incrementAndGet();

            return System.currentTimeMillis() - start;
        }
    }

    private class GMLMultiLineStringPooledSextanteTask extends GMLPointSextanteTask {

        public GMLMultiLineStringPooledSextanteTask(GMLJAXBContext theJaxbContext) {
            super(theJaxbContext);
        }

        @Override
        public Long call() throws Exception {
            long start = System.currentTimeMillis();

            Geometry geometry = (Geometry) ((GMLJAXBContextPooledV311) jaxbContext).unmarshal(multiLineStringFile);
            StringWriter writer = new StringWriter();
            ((GMLJAXBContextPooledV311) jaxbContext).marshal(geometry, writer);
            logger.debug("\n{}\n", writer);
            count.incrementAndGet();

            return System.currentTimeMillis() - start;
        }
    }

    private class GMLMultiPolygonSextanteTask extends GMLPointSextanteTask {

        public GMLMultiPolygonSextanteTask(GMLJAXBContext theJaxbContext) {
            super(theJaxbContext);
        }

        @Override
        public Long call() throws Exception {
            long start = System.currentTimeMillis();

            GMLUnmarshaller unmarshaller = jaxbContext.acquireUnmarshaller();
            Geometry geometry = (Geometry) unmarshaller.unmarshal(multiPolygonFile);

            StringWriter writer = new StringWriter();
            jaxbContext.acquireMarshaller().marshal(geometry, writer);
            logger.debug("\n{}\n", writer);
            count.incrementAndGet();

            return System.currentTimeMillis() - start;
        }
    }

    private class GMLMultiPolygonPooledSextanteTask extends GMLPointSextanteTask {

        public GMLMultiPolygonPooledSextanteTask(GMLJAXBContext theJaxbContext) {
            super(theJaxbContext);
        }

        @Override
        public Long call() throws Exception {
            long start = System.currentTimeMillis();

            Geometry geometry = (Geometry) ((GMLJAXBContextPooledV311) jaxbContext).unmarshal(multiPolygonFile);
            StringWriter writer = new StringWriter();
            ((GMLJAXBContextPooledV311) jaxbContext).marshal(geometry, writer);
            logger.debug("\n{}\n", writer);
            count.incrementAndGet();

            return System.currentTimeMillis() - start;
        }
    }

    private class GMLMultiCurveSextanteTask extends GMLPointSextanteTask {

        public GMLMultiCurveSextanteTask(GMLJAXBContext theJaxbContext) {
            super(theJaxbContext);
        }

        @Override
        public Long call() throws Exception {
            long start = System.currentTimeMillis();

            GMLUnmarshaller unmarshaller = jaxbContext.acquireUnmarshaller();
            Geometry geometry = (Geometry) unmarshaller.unmarshal(multiCurveFile);

            StringWriter writer = new StringWriter();
            jaxbContext.acquireMarshaller().marshal(geometry, writer);
            logger.debug("\n{}\n", writer);
            count.incrementAndGet();

            return System.currentTimeMillis() - start;
        }
    }

    private class GMLMultiCurvePooledSextanteTask extends GMLPointSextanteTask {

        public GMLMultiCurvePooledSextanteTask(GMLJAXBContext theJaxbContext) {
            super(theJaxbContext);
        }

        @Override
        public Long call() throws Exception {
            long start = System.currentTimeMillis();

            Geometry geometry = (Geometry) ((GMLJAXBContextPooledV311) jaxbContext).unmarshal(multiCurveFile);
            StringWriter writer = new StringWriter();
            ((GMLJAXBContextPooledV311) jaxbContext).marshal(geometry, writer);
            logger.debug("\n{}\n", writer);
            count.incrementAndGet();

            return System.currentTimeMillis() - start;
        }
    }

    private class GMLMultiSurfaceSextanteTask extends GMLPointSextanteTask {

        public GMLMultiSurfaceSextanteTask(GMLJAXBContext theJaxbContext) {
            super(theJaxbContext);
        }

        @Override
        public Long call() throws Exception {
            long start = System.currentTimeMillis();

            GMLUnmarshaller unmarshaller = jaxbContext.acquireUnmarshaller();
            Geometry geometry = (Geometry) unmarshaller.unmarshal(multiSurfaceFile);

            StringWriter writer = new StringWriter();
            jaxbContext.acquireMarshaller().marshal(geometry, writer);
            logger.debug("\n{}\n", writer);
            count.incrementAndGet();

            return System.currentTimeMillis() - start;
        }
    }

    private class GMLMultiSurfacePooledSextanteTask extends GMLPointSextanteTask {

        public GMLMultiSurfacePooledSextanteTask(GMLJAXBContext theJaxbContext) {
            super(theJaxbContext);
        }

        @Override
        public Long call() throws Exception {
            long start = System.currentTimeMillis();

            Geometry geometry = (Geometry) ((GMLJAXBContextPooledV311) jaxbContext).unmarshal(multiSurfaceFile);
            StringWriter writer = new StringWriter();
            ((GMLJAXBContextPooledV311) jaxbContext).marshal(geometry, writer);
            logger.debug("\n{}\n", writer);
            count.incrementAndGet();

            return System.currentTimeMillis() - start;
        }
    }

    private class GMLGeometryCollectionSextanteTask extends GMLPointSextanteTask {

        public GMLGeometryCollectionSextanteTask(GMLJAXBContext theJaxbContext) {
            super(theJaxbContext);
        }

        @Override
        public Long call() throws Exception {
            long start = System.currentTimeMillis();

            GMLUnmarshaller unmarshaller = jaxbContext.acquireUnmarshaller();
            Geometry geometry = (Geometry) unmarshaller.unmarshal(geometryCollectionFile);

            StringWriter writer = new StringWriter();
            jaxbContext.acquireMarshaller().marshal(geometry, writer);
            logger.debug("\n{}\n", writer);
            count.incrementAndGet();

            return System.currentTimeMillis() - start;
        }
    }

    private class GMLGeometryCollectionPooledSextanteTask extends GMLPointSextanteTask {

        public GMLGeometryCollectionPooledSextanteTask(GMLJAXBContext theJaxbContext) {
            super(theJaxbContext);
        }

        @Override
        public Long call() throws Exception {
            long start = System.currentTimeMillis();

            Geometry geometry = (Geometry) ((GMLJAXBContextPooledV311) jaxbContext).unmarshal(geometryCollectionFile);
            StringWriter writer = new StringWriter();
            ((GMLJAXBContextPooledV311) jaxbContext).marshal(geometry, writer);
            logger.debug("\n{}\n", writer);
            count.incrementAndGet();

            return System.currentTimeMillis() - start;
        }
    }
}
