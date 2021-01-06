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

import org.geosdi.geoplatform.support.jackson.model.Prestazione;
import org.geosdi.geoplatform.support.jackson.xml.GPJacksonXmlSupport;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Nonnull;
import java.io.File;
import java.io.StringReader;
import java.util.List;

import static com.google.common.base.Preconditions.checkArgument;
import static java.io.File.separator;
import static java.util.UUID.randomUUID;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;
import static java.util.stream.IntStream.iterate;
import static java.util.stream.Stream.of;
import static javax.annotation.meta.When.NEVER;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@TestMethodOrder(OrderAnnotation.class)
public class PrestazioneJacksonXmlMapperTest {

    private static final Logger logger = LoggerFactory.getLogger(PrestazioneJacksonXmlMapperTest.class);
    //
    private static final GPJacksonXmlMapper<Prestazione> GP_JACKSON_XML_MAPPER = new GPBaseJacksonXmlMapper<>(Prestazione.class,
            new GPJacksonXmlSupport());

    @Test
    @Order(value = 0)
    public void unmarshallPrestazioneFromXmlStringTest() throws Exception {
        logger.info("##################Prestazione_FROM_XML_STRING : {}\n", GP_JACKSON_XML_MAPPER.read(new StringReader("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n"
                + "<PRESTAZIONE>\n"
                + "    <CODICE>EP3007</CODICE>\n"
                + "    <DESCRIZIONE>EXT - POSA CAVO BT INTERRATO SEZ. 50 MMQ.</DESCRIZIONE>\n"
                + "    <QTA_PROGETTO>86.000</QTA_PROGETTO>\n"
                + "    <UNITA_MISURA>M</UNITA_MISURA>\n"
                + "    <VALORE_ZMED>7020</VALORE_ZMED>\n"
                + "</PRESTAZIONE>")));
    }

    @Test
    @Order(value = 1)
    public void unmarshallPrestazioneFromFileTest() throws Exception {
        logger.info("#######################Prestazione_FROM_FILE : {}\n", GP_JACKSON_XML_MAPPER
                .read(new File(of("src", "test", "resources", "prestazione.xml").collect(joining(separator)))));
    }

    @Test
    public void marshallPrestazioneAsStringTest() throws Exception {
        logger.info("@@@@@@@@@@@@@@@@@@@Prestazione_AS_STRING_TEST : \n{}\n", GP_JACKSON_XML_MAPPER
                .writeAsString(PrestazioneJacksonXmlMapperTest::toPrestazione));
    }

    /**
     * @return {@link Prestazione}
     */
    public static Prestazione toPrestazione() {
        return new Prestazione() {
            {
                super.setCodice(randomUUID().toString());
                super.setDescrizione("DESCRIZIONE_TEST");
                super.setQtaProgetto("86.000");
                super.setUnitaMisura("M");
                super.setValoreZmed(7020);
            }
        };
    }

    /**
     * @param numbers
     * @return {@link List<Prestazione>}
     */
    static List<Prestazione> toPrestazioni(int numbers) {
        checkArgument((numbers > 0), "The Number of Prestazioni must be greather than 0.");
        return iterate(0, n -> n + 1)
                .limit(numbers)
                .boxed()
                .map(PrestazioneJacksonXmlMapperTest::toPrestazione)
                .collect(toList());
    }

    /**
     * @param theValue
     * @return {@link Prestazione}
     */
    static Prestazione toPrestazione(@Nonnull(when = NEVER) Integer theValue) {
        checkArgument(theValue != null, "The Parameter value must not be null");
        return new Prestazione() {
            {
                super.setCodice("CODICE_TEST#" + theValue.toString());
                super.setDescrizione("DESCRIZIONE_TEST#" + theValue.toString());
                super.setQtaProgetto("QTA_TEST#" + theValue.toString());
                super.setUnitaMisura("UNITA_MISURA_TEST#" + theValue.toString());
                super.setValoreZmed(theValue);
            }
        };
    }
}