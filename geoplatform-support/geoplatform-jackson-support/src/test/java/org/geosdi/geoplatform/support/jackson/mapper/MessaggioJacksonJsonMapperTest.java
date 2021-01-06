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
import org.geosdi.geoplatform.support.jackson.mapper.xml.MessaggioJacksonXmlMapperTest;
import org.geosdi.geoplatform.support.jackson.model.Messaggio;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

import static java.io.File.separator;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Stream.of;
import static org.geosdi.geoplatform.support.jackson.property.GPJacksonSupportEnum.*;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@TestMethodOrder(OrderAnnotation.class)
public class MessaggioJacksonJsonMapperTest {

    private static final Logger logger = LoggerFactory.getLogger(MessaggioJacksonJsonMapperTest.class);
    //
    private static final GPJacksonMapper<Messaggio> GP_JACKSON_MAPPER = new GPBaseJacksonMapper<>(Messaggio.class,
            new GPJacksonSupport(UNWRAP_ROOT_VALUE_DISABLE, FAIL_ON_UNKNOW_PROPERTIES_DISABLE,
                    ACCEPT_SINGLE_VALUE_AS_ARRAY_ENABLE, WRAP_ROOT_VALUE_DISABLE, INDENT_OUTPUT_ENABLE));

    @Test
    @Order(value = 0)
    public void writeMessaggioAsJsonStringTest() throws Exception {
        logger.info("@@@@@@@@@@@@@@@@MESSAGGIO_AS_JSON_STRING : \n{}\n", GP_JACKSON_MAPPER
                .writeAsString(MessaggioJacksonXmlMapperTest::toMessaggio));
    }

    @Test
    @Order(value = 1)
    public void writeMessaggioAsJsonFileTest() throws Exception {
        GP_JACKSON_MAPPER.write(new File("./target/Messaggio.json"), MessaggioJacksonXmlMapperTest::toMessaggio);
    }

    @Test
    @Order(value = 2)
    public void readMessaggioFromJsonFileTest() throws Exception {
        logger.info("###################MESSAGGIO_FROM_JSON_FILE : {}\n", GP_JACKSON_MAPPER.read(new File(of("src", "test",
                "resources", "Messaggio.json").collect(joining(separator)))));
    }
}