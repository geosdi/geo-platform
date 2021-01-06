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

import org.geosdi.geoplatform.support.jackson.model.Contratto;
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
public class ContrattoJacksonXmlMapperTest {

    private static final Logger logger = LoggerFactory.getLogger(ContrattoJacksonXmlMapperTest.class);
    //
    private static final GPJacksonXmlMapper<Contratto> GP_JACKSON_XML_MAPPER = new GPBaseJacksonXmlMapper<>(Contratto.class,
            new GPJacksonXmlSupport());

    @Test
    @Order(value = 0)
    public void unmarshallContrattoFromXmlStringTest() throws Exception {
        logger.info("##################CONTRATTO_FROM_XML_STRING : {}\n", GP_JACKSON_XML_MAPPER.read(new StringReader("<CONTRATTO>\n"
                + "            <NUMERO>5200002163</NUMERO>\n"
                + "            <DESCRIZIONE/>\n"
                + "            <DATA_EMISSIONE>2006-05-05</DATA_EMISSIONE>\n"
                + "            <VALORE_PUNTOA/>\n"
                + "            <VALORE_PUNTOB/>\n"
                + "            <DATA_INIZIO>2006-07-01</DATA_INIZIO>\n"
                + "            <DATA_FINE>2006-12-30</DATA_FINE>\n"
                + "            <IMPORTO_MASSIMO>246558.02</IMPORTO_MASSIMO>\n"
                + "        </CONTRATTO>")));
    }

    @Test
    @Order(value = 1)
    public void unmarshallContrattoFromFileTest() throws Exception {
        logger.info("#######################CONTRATTO_FROM_FILE : {}\n", GP_JACKSON_XML_MAPPER
                .read(new File(of("src", "test", "resources", "contratto.xml").collect(joining(separator)))));
    }

    @Test
    public void marshallContrattoAsStringTest() throws Exception {
        logger.info("@@@@@@@@@@@@@@@@@@@CONTRATTO_AS_STRING_TEST : \n{}\n", GP_JACKSON_XML_MAPPER
                .writeAsString(ContrattoJacksonXmlMapperTest::toContratto));
    }

    /**
     * @return {@link Contratto}
     */
    public static Contratto toContratto() {
        return new Contratto() {
            {
                super.setNumero(493892843l);
                super.setDescrizione("DESCRIZIONE_TEST");
                super.setDataEmissione("22-04-2004");
                super.setValorePuntoA(88.9);
                super.setValorePuntoB(109.90);
                super.setDataInizio("24-04-2004");
                super.setDataFine("30-09-2005");
                super.setImortoMassimo(89987.89);
            }
        };
    }
}