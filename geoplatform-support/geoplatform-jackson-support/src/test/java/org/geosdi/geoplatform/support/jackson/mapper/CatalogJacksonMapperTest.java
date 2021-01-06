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
package org.geosdi.geoplatform.support.jackson.mapper;

import org.geosdi.geoplatform.support.jackson.GPJacksonSupport;
import org.geosdi.geoplatform.support.jackson.mapper.xml.CatalogJacksonXmlMapperTest;
import org.geosdi.geoplatform.support.jackson.model.Catalog;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.StringReader;

import static java.io.File.separator;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Stream.of;
import static org.geosdi.geoplatform.support.jackson.mapper.xml.CatalogJacksonXmlMapperTest.catalogBean;
import static org.geosdi.geoplatform.support.jackson.property.GPJacksonSupportEnum.*;
import static org.junit.runners.MethodSorters.NAME_ASCENDING;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@FixMethodOrder(value = NAME_ASCENDING)
public class CatalogJacksonMapperTest {

    private static final Logger logger = LoggerFactory.getLogger(CatalogJacksonMapperTest.class);
    //
    private static final GPJacksonMapper<Catalog> GP_JACKSON_MAPPER = new GPBaseJacksonMapper<>(Catalog.class,
            new GPJacksonSupport(UNWRAP_ROOT_VALUE_DISABLE, FAIL_ON_UNKNOW_PROPERTIES_DISABLE,
                    ACCEPT_SINGLE_VALUE_AS_ARRAY_ENABLE, WRAP_ROOT_VALUE_DISABLE, INDENT_OUTPUT_ENABLE));

    @Test
    public void a_writeCatalogAsJsonStringTest() throws Exception {
        logger.info("\n{}\n", GP_JACKSON_MAPPER.writeAsString(CatalogJacksonXmlMapperTest::catalogBean));
    }

    @Test
    public void b_writeCatalogAsJsonStringTest() throws Exception {
        logger.info("\n{}\n", GP_JACKSON_MAPPER.writeAsString(catalogBean(10)));
    }

    @Test
    public void c_readCatalogFromJsonStringTest() throws Exception {
        Catalog catalog = GP_JACKSON_MAPPER.read(new StringReader("{\n"
                + "  \"CD\" : [ {\n"
                + "    \"TITLE\" : \"TITLE#0\",\n"
                + "    \"ARTIST\" : \"ARTIST#0\",\n"
                + "    \"COUNTRY\" : \"COUNTRY#0\",\n"
                + "    \"COMPANY\" : \"COMPANY#0\",\n"
                + "    \"PRICE\" : 0.0,\n"
                + "    \"YEAR\" : 2020\n"
                + "  }, {\n"
                + "    \"TITLE\" : \"TITLE#1\",\n"
                + "    \"ARTIST\" : \"ARTIST#1\",\n"
                + "    \"COUNTRY\" : \"COUNTRY#1\",\n"
                + "    \"COMPANY\" : \"COMPANY#1\",\n"
                + "    \"PRICE\" : 1.0,\n"
                + "    \"YEAR\" : 2019\n"
                + "  }, {\n"
                + "    \"TITLE\" : \"TITLE#2\",\n"
                + "    \"ARTIST\" : \"ARTIST#2\",\n"
                + "    \"COUNTRY\" : \"COUNTRY#2\",\n"
                + "    \"COMPANY\" : \"COMPANY#2\",\n"
                + "    \"PRICE\" : 2.0,\n"
                + "    \"YEAR\" : 2018\n"
                + "  }, {\n"
                + "    \"TITLE\" : \"TITLE#3\",\n"
                + "    \"ARTIST\" : \"ARTIST#3\",\n"
                + "    \"COUNTRY\" : \"COUNTRY#3\",\n"
                + "    \"COMPANY\" : \"COMPANY#3\",\n"
                + "    \"PRICE\" : 3.0,\n"
                + "    \"YEAR\" : 2017\n"
                + "  }, {\n"
                + "    \"TITLE\" : \"TITLE#4\",\n"
                + "    \"ARTIST\" : \"ARTIST#4\",\n"
                + "    \"COUNTRY\" : \"COUNTRY#4\",\n"
                + "    \"COMPANY\" : \"COMPANY#4\",\n"
                + "    \"PRICE\" : 4.0,\n"
                + "    \"YEAR\" : 2016\n"
                + "  }, {\n"
                + "    \"TITLE\" : \"TITLE#5\",\n"
                + "    \"ARTIST\" : \"ARTIST#5\",\n"
                + "    \"COUNTRY\" : \"COUNTRY#5\",\n"
                + "    \"COMPANY\" : \"COMPANY#5\",\n"
                + "    \"PRICE\" : 5.0,\n"
                + "    \"YEAR\" : 2015\n"
                + "  }, {\n"
                + "    \"TITLE\" : \"TITLE#6\",\n"
                + "    \"ARTIST\" : \"ARTIST#6\",\n"
                + "    \"COUNTRY\" : \"COUNTRY#6\",\n"
                + "    \"COMPANY\" : \"COMPANY#6\",\n"
                + "    \"PRICE\" : 6.0,\n"
                + "    \"YEAR\" : 2014\n"
                + "  }, {\n"
                + "    \"TITLE\" : \"TITLE#7\",\n"
                + "    \"ARTIST\" : \"ARTIST#7\",\n"
                + "    \"COUNTRY\" : \"COUNTRY#7\",\n"
                + "    \"COMPANY\" : \"COMPANY#7\",\n"
                + "    \"PRICE\" : 7.0,\n"
                + "    \"YEAR\" : 2013\n"
                + "  }, {\n"
                + "    \"TITLE\" : \"TITLE#8\",\n"
                + "    \"ARTIST\" : \"ARTIST#8\",\n"
                + "    \"COUNTRY\" : \"COUNTRY#8\",\n"
                + "    \"COMPANY\" : \"COMPANY#8\",\n"
                + "    \"PRICE\" : 8.0,\n"
                + "    \"YEAR\" : 2012\n"
                + "  }, {\n"
                + "    \"TITLE\" : \"TITLE#9\",\n"
                + "    \"ARTIST\" : \"ARTIST#9\",\n"
                + "    \"COUNTRY\" : \"COUNTRY#9\",\n"
                + "    \"COMPANY\" : \"COMPANY#9\",\n"
                + "    \"PRICE\" : 9.0,\n"
                + "    \"YEAR\" : 2011\n"
                + "  } ]\n"
                + "}"));
        logger.info("@@@@@@@@@@@@@@@@@@@@@CATALOG_FROM_XML_STRING : {}\n", catalog);
    }

    @Test
    public void d_writeCatalogAsJsonFileTest() throws Exception {
        GP_JACKSON_MAPPER.write(new File(of(".", "target", "Catalog.json").collect(joining(separator))), CatalogJacksonXmlMapperTest::catalogBean);
    }

    @Test
    public void e_readCatalogFromJsonFileTest() throws Exception {
        logger.info("#####################CATALOG_FROM_FILE : {}\n", GP_JACKSON_MAPPER.read(new File(of(".", "target", "Catalog.json")
                .collect(joining(separator)))));
    }
}