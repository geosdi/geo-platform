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

import org.geosdi.geoplatform.gml.impl.v311.jaxb.context.factory.GMLContextFactoryV311;
import org.geosdi.geoplatform.gml.impl.v311.jaxb.context.factory.GMLContextType;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.util.concurrent.TimeUnit;

/**
 *
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@FixMethodOrder(value = MethodSorters.NAME_ASCENDING)
public class GMLSextanteComparisonParserTest extends AbstractGMLComparisonTest {

    @Test
    public void gmlPointSextantePoolTest() throws Exception {
        logger.info("gmlPointSextantePoolTest : Executed {} threads in {} s \n",
                super.defineNumThreads(),
                TimeUnit.MILLISECONDS.toSeconds(executeMultiThreadsTasks(
                        GMLContextFactoryV311.createJAXBContext(GMLContextType.POOLED),
                        GMLTaskType.POINT_POOLED)));
    }

    @Test
    public void gmlPointSextanteSimpleTest() throws Exception {
        logger.info("gmlPointSextanteSimpleTest : Executed {} threads in {} s \n",
                super.defineNumThreads(),
                TimeUnit.MILLISECONDS.toSeconds(executeMultiThreadsTasks(
                        GMLContextFactoryV311.createJAXBContext(GMLContextType.SIMPLE),
                        GMLTaskType.POINT_SIMPLE)));
    }

    @Test
    public void gmlLineStringSextantePoolTest() throws Exception {
        logger.info("gmlLineStringSextantePoolTest : Executed {} threads in {} s \n",
                super.defineNumThreads(),
                TimeUnit.MILLISECONDS.toSeconds(executeMultiThreadsTasks(
                        GMLContextFactoryV311.createJAXBContext(GMLContextType.POOLED),
                        GMLTaskType.LINE_STRING_POOLED)));
    }

    @Test
    public void gmlLineStringSextanteSimpleTest() throws Exception {
        logger.info("gmlLineStringSextanteSimpleTest : Executed {} threads in {} s \n",
                super.defineNumThreads(),
                TimeUnit.MILLISECONDS.toSeconds(executeMultiThreadsTasks(
                        GMLContextFactoryV311.createJAXBContext(GMLContextType.SIMPLE),
                        GMLTaskType.LINE_STRING_SIMPE)));
    }

    @Test
    public void gmlLinearRingSextantePoolTest() throws Exception {
        logger.info("gmlLinearRingSextantePoolTest : Executed {} threads in {} s \n",
                super.defineNumThreads(),
                TimeUnit.MILLISECONDS.toSeconds(executeMultiThreadsTasks(
                        GMLContextFactoryV311.createJAXBContext(GMLContextType.POOLED),
                        GMLTaskType.LINEAR_RING_POOLED)));
    }

    @Test
    public void gmlLinearRingSextanteSimpleTest() throws Exception {
        logger.info("gmlLinearRingSextanteSimpleTest : Executed {} threads in {} s \n",
                super.defineNumThreads(),
                TimeUnit.MILLISECONDS.toSeconds(executeMultiThreadsTasks(
                        GMLContextFactoryV311.createJAXBContext(GMLContextType.SIMPLE),
                        GMLTaskType.LINEAR_RING_SIMPLE)));
    }

    @Test
    public void gmlPolygonSextantePoolTest() throws Exception {
        logger.info("gmlPolygonSextantePoolTest : Executed {} threads in {} s \n",
                super.defineNumThreads(),
                TimeUnit.MILLISECONDS.toSeconds(executeMultiThreadsTasks(
                        GMLContextFactoryV311.createJAXBContext(GMLContextType.POOLED),
                        GMLTaskType.POLYGON_POOLED)));
    }

    @Test
    public void gmlPolygonSextanteSimpleTest() throws Exception {
        logger.info("gmlPolygonSextanteSimpleTest : Executed {} threads in {} s \n",
                super.defineNumThreads(),
                TimeUnit.MILLISECONDS.toSeconds(executeMultiThreadsTasks(
                        GMLContextFactoryV311.createJAXBContext(GMLContextType.SIMPLE),
                        GMLTaskType.POLYGON_SIMPE)));
    }

    @Test
    public void gmlMultiPointSextantePoolTest() throws Exception {
        logger.info("gmlMultiPointSextantePoolTest : Executed {} threads in {} s \n",
                super.defineNumThreads(),
                TimeUnit.MILLISECONDS.toSeconds(executeMultiThreadsTasks(
                        GMLContextFactoryV311.createJAXBContext(GMLContextType.POOLED),
                        GMLTaskType.MULTI_POINT_POOLED)));
    }

    @Test
    public void gmlMultiPointSextanteSimpleTest() throws Exception {
        logger.info("gmlMultiPointSextanteSimpleTest : Executed {} threads in {} s \n",
                super.defineNumThreads(),
                TimeUnit.MILLISECONDS.toSeconds(executeMultiThreadsTasks(
                        GMLContextFactoryV311.createJAXBContext(GMLContextType.SIMPLE),
                        GMLTaskType.MULTI_POINT_SIMPLE)));
    }

    @Test
    public void gmlMultiLineStringSextantePoolTest() throws Exception {
        logger.info("gmlMultiLineStringSextantePoolTest : Executed {} threads in {} s \n",
                super.defineNumThreads(),
                TimeUnit.MILLISECONDS.toSeconds(executeMultiThreadsTasks(
                        GMLContextFactoryV311.createJAXBContext(GMLContextType.POOLED),
                        GMLTaskType.MULTI_LINE_STRING_POOLED)));
    }

    @Test
    public void gmlMultiLineStringSextanteSimpleTest() throws Exception {
        logger.info("gmlMultiLineStringSextanteSimpleTest : Executed {} threads in {} s \n",
                super.defineNumThreads(),
                TimeUnit.MILLISECONDS.toSeconds(executeMultiThreadsTasks(
                        GMLContextFactoryV311.createJAXBContext(GMLContextType.SIMPLE),
                        GMLTaskType.MULTI_LINE_STRING_SIMPLE)));
    }

    @Test
    public void gmlMultiPolygonSextantePoolTest() throws Exception {
        logger.info("gmlMultiPolygonSextantePoolTest : Executed {} threads in {} s \n",
                super.defineNumThreads(),
                TimeUnit.MILLISECONDS.toSeconds(executeMultiThreadsTasks(
                        GMLContextFactoryV311.createJAXBContext(GMLContextType.POOLED),
                        GMLTaskType.MULTI_POLYGON_POOLED)));
    }

    @Test
    public void gmlMultiPolygonSextanteSimpleTest() throws Exception {
        logger.info("gmlMultiPolygonSextanteSimpleTest : Executed {} threads in {} s \n",
                super.defineNumThreads(),
                TimeUnit.MILLISECONDS.toSeconds(executeMultiThreadsTasks(
                        GMLContextFactoryV311.createJAXBContext(GMLContextType.SIMPLE),
                        GMLTaskType.MULTI_POLYGON_SIMPLE)));
    }

    @Test
    public void gmlMultiCurveSextantePoolTest() throws Exception {
        logger.info("gmlMultiCurveSextantePoolTest : Executed {} threads in {} s \n",
                super.defineNumThreads(),
                TimeUnit.MILLISECONDS.toSeconds(executeMultiThreadsTasks(
                        GMLContextFactoryV311.createJAXBContext(GMLContextType.POOLED),
                        GMLTaskType.MULTI_CURVE_POOLED)));
    }

    @Test
    public void gmlMultiCurveSextanteSimpleTest() throws Exception {
        logger.info("gmlMultiCurveSextanteSimpleTest : Executed {} threads in {} s \n",
                super.defineNumThreads(),
                TimeUnit.MILLISECONDS.toSeconds(executeMultiThreadsTasks(
                        GMLContextFactoryV311.createJAXBContext(GMLContextType.SIMPLE),
                        GMLTaskType.MULTI_CURVE_SIMPLE)));
    }

    @Test
    public void gmlMultiSurfaceSextantePoolTest() throws Exception {
        logger.info("gmlMultiSurfaceSextantePoolTest : Executed {} threads in {} s \n",
                super.defineNumThreads(),
                TimeUnit.MILLISECONDS.toSeconds(executeMultiThreadsTasks(
                        GMLContextFactoryV311.createJAXBContext(GMLContextType.POOLED),
                        GMLTaskType.MULTI_SURFACE_POOLED)));
    }

    @Test
    public void gmlMultiSurfaceSextanteSimpleTest() throws Exception {
        logger.info("gmlMultiSurfaceSextanteSimpleTest : Executed {} threads in {} s \n",
                super.defineNumThreads(),
                TimeUnit.MILLISECONDS.toSeconds(executeMultiThreadsTasks(
                        GMLContextFactoryV311.createJAXBContext(GMLContextType.SIMPLE),
                        GMLTaskType.MULTI_SURFACE_SIMPLE)));
    }

    @Test
    public void gmlGeometryCollectionSextantePoolTest() throws Exception {
        logger.info("gmlGeometryCollectionSextantePoolTest : Executed {} threads in {} s \n",
                super.defineNumThreads(),
                TimeUnit.MILLISECONDS.toSeconds(executeMultiThreadsTasks(
                        GMLContextFactoryV311.createJAXBContext(GMLContextType.POOLED),
                        GMLTaskType.GEOMETRY_COLLECTION_POOLED)));
    }

    @Test
    public void gmlGeometryCollectionSimpleTest() throws Exception {
        logger.info("gmlGeometryCollectionSimpleTest : Executed {} threads in {} s \n",
                super.defineNumThreads(),
                TimeUnit.MILLISECONDS.toSeconds(executeMultiThreadsTasks(
                        GMLContextFactoryV311.createJAXBContext(GMLContextType.SIMPLE),
                        GMLTaskType.GEOMETRY_COLLECTION_SIMPLE)));
    }

    @Test
    public void totalCountTestsExecuted() {
        logger.info("#####################Executed : {} Tasks\n", count.get());
    }
}
