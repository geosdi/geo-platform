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
package org.geosdi.geoplatform.support.jackson.mapper.xml;

import org.geosdi.geoplatform.support.jackson.model.Intestazione;
import org.geosdi.geoplatform.support.jackson.xml.GPJacksonXmlSupport;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.StringReader;

import static java.io.File.separator;
import static java.util.UUID.randomUUID;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Stream.of;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@TestMethodOrder(OrderAnnotation.class)
public class IntestazioneJacksonXmlMapperTest {

    private static final Logger logger = LoggerFactory.getLogger(IntestazioneJacksonXmlMapperTest.class);
    //
    private static final GPJacksonXmlMapper<Intestazione> GP_JACKSON_XML_MAPPER = new GPBaseJacksonXmlMapper<>(Intestazione.class,
            new GPJacksonXmlSupport());

    @Test
    @Order(value = 0)
    public void unmarshallIntestazioneFromXmlStringTest() throws Exception {
        logger.info("##################INTESTAZIONE_FROM_XML_STRING : {}\n", GP_JACKSON_XML_MAPPER.read(new StringReader("<INTESTAZIONE>\n"
                + "        <DA>05779711000</DA>\n"
                + "        <A>7865656445</A>\n"
                + "        <ID/>\n"
                + "        <DATA_INVIO>2007-02-05</DATA_INVIO>\n"
                + "        <TIPO_DOC>LCL</TIPO_DOC>\n"
                + "        <VERSIONE>1.0.0</VERSIONE>\n"
                + "    </INTESTAZIONE>")));
    }

    @Test
    @Order(value = 1)
    public void unmarshallIntestazioneFromFileTest() throws Exception {
        logger.info("#######################INTESTAZIONE_FROM_FILE : {}\n", GP_JACKSON_XML_MAPPER
                .read(new File(of("src", "test", "resources", "intestazione.xml").collect(joining(separator)))));
    }

    @Test
    public void marshallIntestazioneAsStringTest() throws Exception {
        logger.info("@@@@@@@@@@@@@@@@@@@INTESTAZIONE_AS_STRING_TEST : \n{}\n", GP_JACKSON_XML_MAPPER
                .writeAsString(IntestazioneJacksonXmlMapperTest::toIntestazione));
    }

    /**
     * @return {@link Intestazione}
     */
    public static Intestazione toIntestazione() {
        return new Intestazione() {
            {
                super.setDa("DA_TEST");
                super.setA("A_TEST");
                super.setId(randomUUID().toString());
                super.setDataInvio("11-09-2020");
                super.setTipoDoc("LCL");
                super.setVersione("1.0.0");
            }
        };
    }
}