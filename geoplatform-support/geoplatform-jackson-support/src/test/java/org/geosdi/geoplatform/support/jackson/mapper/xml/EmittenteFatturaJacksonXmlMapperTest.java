/**
 * geo-platform
 * Rich webgis framework
 * http://geo-platform.org
 * ====================================================================
 * <p>
 * Copyright (C) 2008-2020 geoSDI Group (CNR IMAA - Potenza - ITALY).
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
package org.geosdi.geoplatform.support.jackson.mapper.xml;

import org.geosdi.geoplatform.support.jackson.model.EmittenteFattura;
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
import static java.util.stream.Collectors.joining;
import static java.util.stream.Stream.of;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@TestMethodOrder(OrderAnnotation.class)
public class EmittenteFatturaJacksonXmlMapperTest {

    private static final Logger logger = LoggerFactory.getLogger(EmittenteFatturaJacksonXmlMapperTest.class);
    //
    private static final GPJacksonXmlMapper<EmittenteFattura> GP_JACKSON_XML_MAPPER = new GPBaseJacksonXmlMapper<>(EmittenteFattura.class,
            new GPJacksonXmlSupport());

    @Test
    @Order(value = 0)
    public void unmarshallEmittenteFatturaFromXmlStringTest() throws Exception {
        logger.info("##################EMITTENTE_FATTURA_FROM_XML_STRING : {}\n", GP_JACKSON_XML_MAPPER.read(new StringReader("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n"
                + "<EMITTENTE_FATTURA>\n"
                + "    <PARTITA_IVA>7865656445</PARTITA_IVA>\n"
                + "    <DENOMINAZIONE>Azienda Test</DENOMINAZIONE>\n"
                + "    <INDIRIZZO>Zona Test</INDIRIZZO>\n"
                + "    <CAP>16121</CAP>\n"
                + "    <COMUNE>Genova</COMUNE>\n"
                + "    <PROVINCIA>GE</PROVINCIA>\n"
                + "    <NAZIONE>IT</NAZIONE>\n"
                + "</EMITTENTE_FATTURA>")));
    }

    @Test
    @Order(value = 1)
    public void unmarshallEmittenteFatturaFromFileTest() throws Exception {
        logger.info("#######################EMITTENTE_FATTURA_FROM_FILE : {}\n", GP_JACKSON_XML_MAPPER
                .read(new File(of("src", "test", "resources", "emittente_fattura.xml").collect(joining(separator)))));
    }

    @Test
    @Order(value = 2)
    public void marshallEmittenteFatturaAsStringTest() throws Exception {
        logger.info("@@@@@@@@@@@@@@@@@@@EMITTENTE_FATTURA_AS_STRING_TEST : \n{}\n", GP_JACKSON_XML_MAPPER
                .writeAsString(EmittenteFatturaJacksonXmlMapperTest::toEmittenteFattura));
    }

    /**
     * @return {@link EmittenteFattura}
     */
    public static EmittenteFattura toEmittenteFattura() {
        return new EmittenteFattura() {
            {
                super.setPartitaIva("PARTITA_IVA_TEST");
                super.setDenomizione("DENOMINAZIONE_TEST");
                super.setIndirizzo("INDIRIZZO_TEST");
                super.setCap("CAP_TEST");
                super.setComune("COMUNE_TEST");
                super.setProvincia("PROVINCIA_TEST");
                super.setNazione("NAZIONE_TEST");
            }
        };
    }
}