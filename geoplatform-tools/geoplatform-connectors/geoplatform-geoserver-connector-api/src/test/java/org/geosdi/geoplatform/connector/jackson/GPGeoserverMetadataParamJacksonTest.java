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
package org.geosdi.geoplatform.connector.jackson;

import org.geosdi.geoplatform.connector.geoserver.model.metadata.GPGeoserverMetadataParam;
import org.geosdi.geoplatform.support.jackson.mapper.xml.GPBaseJacksonXmlMapper;
import org.geosdi.geoplatform.support.jackson.mapper.xml.GPJacksonXmlMapper;
import org.geosdi.geoplatform.support.jackson.xml.GPJacksonXmlSupport;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

import static java.io.File.separator;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Stream.of;
import static org.junit.runners.MethodSorters.NAME_ASCENDING;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@FixMethodOrder(value = NAME_ASCENDING)
public class GPGeoserverMetadataParamJacksonTest {

    private static final Logger logger = LoggerFactory.getLogger(GPGeoserverMetadataParamJacksonTest.class);
    //
    private static final GPJacksonXmlMapper<GPGeoserverMetadataParam> GP_JACKSON_XML_MAPPER = new GPBaseJacksonXmlMapper<>(GPGeoserverMetadataParam.class,
            new GPJacksonXmlSupport());

    @Test
    public void a_marshalMetadataParamAsXmlStringTest() throws Exception {
        logger.info("@@@@@@@@@@@@@@@@@@@@GP_GEOSERVER_METADATA_PARAM_AS_STRING : \n{}\n", GP_JACKSON_XML_MAPPER
                .writeAsString(GPGeoserverMetadataParamJacksonTest::toMetadataParam));
    }

    @Test
    public void b_marshallGPGeoserverMetadataParamAsFileTest() throws Exception {
        GP_JACKSON_XML_MAPPER.write(new File(of(".", "target", "GPGeoserverMetadataParam.xml")
                .collect(joining(separator))), GPGeoserverMetadataParamJacksonTest::toMetadataParam);
    }

    /**
     * @return {@link GPGeoserverMetadataParam}
     */
    public static GPGeoserverMetadataParam toMetadataParam() {
        return new GPGeoserverMetadataParam("key_test", "value_test");
    }
}