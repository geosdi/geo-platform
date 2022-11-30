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
}