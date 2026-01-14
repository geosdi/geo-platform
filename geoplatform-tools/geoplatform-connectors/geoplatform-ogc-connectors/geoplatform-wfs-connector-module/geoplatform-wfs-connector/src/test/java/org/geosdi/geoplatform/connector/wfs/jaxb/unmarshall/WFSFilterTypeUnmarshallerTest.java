/*
 *
 *    geo-platform
 *    Rich webgis framework
 *    http://geo-platform.org
 *   ====================================================================
 *
 *   Copyright (C) 2008-2026 geoSDI Group (CNR IMAA - Potenza - ITALY).
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
package org.geosdi.geoplatform.connector.wfs.jaxb.unmarshall;

import org.geosdi.geoplatform.connector.jaxb.context.pool.WFSJAXBContextPool;
import org.geosdi.geoplatform.xml.filter.v110.FilterType;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import java.io.File;
import java.io.StringWriter;
import java.io.Writer;

import static java.io.File.separator;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Stream.of;
import static org.geosdi.geoplatform.connector.server.request.v110.cql.GPFilterTypeCqlBuilder.filterTypeCqlBuilder;
import static org.junit.Assert.assertTrue;
import static org.junit.runners.MethodSorters.NAME_ASCENDING;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@FixMethodOrder(value = NAME_ASCENDING)
public class WFSFilterTypeUnmarshallerTest {

    private static final Logger logger = LoggerFactory.getLogger(WFSFilterTypeUnmarshallerTest.class);

    static {
        try {
            wfsJAXBContextPool = new WFSJAXBContextPool(JAXBContext.newInstance(FilterType.class));
        } catch (JAXBException ex) {
            logger.error("##############FAILED to Look UP JAXBContext for class : {}",
                    WFSFilterTypeUnmarshallerTest.class.getSimpleName());
        }
    }

    private static WFSJAXBContextPool wfsJAXBContextPool;
    private static File wfsFilterType;

    @BeforeClass
    public static void loadFile() throws Exception {
        String basePath = of(new File(".").getCanonicalPath(), "src", "test", "resources", "unmarshall", "filter")
                .collect(joining(separator, "", separator));
        wfsFilterType = new File(basePath.concat("wfsFilterType.xml"));
    }

    @Test
    public void a_wfsFilterv110PoolTest() throws Exception {
        FilterType filterType = wfsJAXBContextPool.unmarshal(wfsFilterType);
        logger.info("#######################FILTER_TYPE : {}\n", filterType);
        StringWriter writer = new StringWriter();
        wfsJAXBContextPool.marshal(filterType, writer);
        logger.debug("######################FILTER_TYPE-String : \n{}\n", writer);
    }

    @Test
    public void b_wfsFilterTypev100PoolTest() throws Exception {
        FilterType filterType = filterTypeCqlBuilder()
                .withCqlFilter("COMUNE like 'AVIGLIANO' OR PRO_COM = 77014 OR COMUNE like 'T%'")
                .build();
        Writer writer = new StringWriter();
        wfsJAXBContextPool.marshal(filterType, writer);
        logger.info("@@@@@@@@@@@@@@@@@@@@@@@@@FILTER_TYPE_AS_XML_STRING : \n{}\n", writer);
        logger.info("#########################FILTER_TYPE : {}\n", filterType);
    }

    @Test
    public void c_wfsFilterTypev100PoolTest() throws Exception {
        FilterType filterType = filterTypeCqlBuilder()
                .withCqlFilter("This is a test")
                .build();
        Writer writer = new StringWriter();
        wfsJAXBContextPool.marshal(filterType, writer);
        logger.info("@@@@@@@@@@@@@@@@@@@@@@@@@FILTER_TYPE_AS_XML_STRING : \n{}\n", writer);
        logger.info("#########################FILTER_TYPE : {}\n", filterType);
    }

    @Test
    public void d_wfsFilterTypev100PoolTest() throws Exception {
        FilterType filterType = filterTypeCqlBuilder()
                .withCqlFilter("(COMUNE like 'AVIGLIANO'  OR  PRO_COM = 77014 OR COMUNE like 'T%') AND (COMUNE like '%O' OR PRO_COM = 4)")
                .build();
        Writer writer = new StringWriter();
        wfsJAXBContextPool.marshal(filterType, writer);
        logger.info("@@@@@@@@@@@@@@@@@@@@@@@@@FILTER_TYPE_AS_XML_STRING : \n{}\n", writer);
        logger.info("#########################FILTER_TYPE : {}\n", filterType);
    }

    @Test
    public void e_wfsFilterTypev100PoolTest() throws Exception {
        FilterType filterType = filterTypeCqlBuilder()
                .withCqlFilter("COMUNE like 'AVIGLIANO'")
                .build();
        Writer writer = new StringWriter();
        wfsJAXBContextPool.marshal(filterType, writer);
        logger.info("@@@@@@@@@@@@@@@@@@@@@@@@@FILTER_TYPE_AS_XML_STRING : \n{}\n", writer);
        logger.info("#########################FILTER_TYPE : {}\n", filterType);
    }

    @Test
    public void f_wfsFilterTypev100PoolTest() throws Exception {
        FilterType filterType = filterTypeCqlBuilder()
                .withCqlFilter("(COMUNE like 'AVIGLIANO'  OR  PRO_COM = 77014 OR COMUNE like 'T%') " +
                        "AND (COMUNE like '%O' OR PRO_COM = 4) AND (Intersects(shape,POINT(-45.891523 170.467375)))")
                .build();
        Writer writer = new StringWriter();
        wfsJAXBContextPool.marshal(filterType, writer);
        logger.info("@@@@@@@@@@@@@@@@@@@@@@@@@FILTER_TYPE_AS_XML_STRING : \n{}\n", writer);
    }

    @Test
    public void g_wfsFilterTypev100PoolTest() throws Exception {
        FilterType filterType = filterTypeCqlBuilder()
                .withCqlFilter("Intersects(shape,POINT(-45.891523 170.467375))")
                .build();
        assertTrue(filterType.isSetSpatialOps());
        Writer writer = new StringWriter();
        wfsJAXBContextPool.marshal(filterType, writer);
        logger.info("@@@@@@@@@@@@@@@@@@@@@@@@@FILTER_TYPE_AS_XML_STRING : \n{}\n", writer);
    }

    @Test
    public void h_wfsFilterTypev100PoolTest() throws Exception {
        FilterType filterType = filterTypeCqlBuilder()
                .withCqlFilter("Intersects(shape,LINESTRING(-42.45063 171.21188,-42.45859 171.20709))")
                .build();
        assertTrue(filterType.isSetSpatialOps());
        Writer writer = new StringWriter();
        wfsJAXBContextPool.marshal(filterType, writer);
        logger.info("@@@@@@@@@@@@@@@@@@@@@@@@@FILTER_TYPE_AS_XML_STRING : \n{}\n", writer);
    }

    @Test
    public void i_wfsFilterTypev100PoolTest() throws Exception {
        FilterType filterType = filterTypeCqlBuilder()
                .withCqlFilter("Within(shape,POLYGON((-45.874136 170.501371,-45.872947 170.502041," +
                        "-45.872544 170.503536,-45.872999 170.505233,-45.874136 170.505870,-45.875255 170.505187," +
                        "-45.875723 170.503601,-45.875261 170.501969,-45.874136 170.501371)))")
                .build();
        assertTrue(filterType.isSetSpatialOps());
        Writer writer = new StringWriter();
        wfsJAXBContextPool.marshal(filterType, writer);
        logger.info("@@@@@@@@@@@@@@@@@@@@@@@@@FILTER_TYPE_AS_XML_STRING : \n{}\n", writer);
    }

    @Test
    public void l_wfsFilterTypev100PoolTest() throws Exception {
        FilterType filterType = filterTypeCqlBuilder()
                .withCqlFilter("DWithin(GEOMETRY,POINT(5895346 1792630),10000,meters)")
                .build();
        assertTrue(filterType.isSetSpatialOps());
        Writer writer = new StringWriter();
        wfsJAXBContextPool.marshal(filterType, writer);
        logger.info("@@@@@@@@@@@@@@@@@@@@@@@@@FILTER_TYPE_AS_XML_STRING : \n{}\n", writer);
    }

    @Test
    public void m_wfsFilterTypev100PoolTest() throws Exception {
        FilterType filterType = filterTypeCqlBuilder()
                .withCqlFilter("INTERSECTS(geom, ENVELOPE(-100, 49, -90, 50))")
                .build();
        assertTrue(filterType.isSetSpatialOps());
        Writer writer = new StringWriter();
        wfsJAXBContextPool.marshal(filterType, writer);
        logger.info("@@@@@@@@@@@@@@@@@@@@@@@@@FILTER_TYPE_AS_XML_STRING : \n{}\n", writer);
    }
}