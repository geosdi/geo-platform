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

import org.geosdi.geoplatform.support.jackson.model.Attivita;
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
import static org.geosdi.geoplatform.support.jackson.mapper.xml.MaterialeJacksonXmlMapperTest.toMateriale;
import static org.geosdi.geoplatform.support.jackson.mapper.xml.PrestazioneJacksonXmlMapperTest.toPrestazioni;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@TestMethodOrder(OrderAnnotation.class)
public class AttivitaJacksonXmlMapperTest {

    private static final Logger logger = LoggerFactory.getLogger(AttivitaJacksonXmlMapperTest.class);
    //
    private static final GPJacksonXmlMapper<Attivita> GP_JACKSON_XML_MAPPER = new GPBaseJacksonXmlMapper<>(Attivita.class,
            new GPJacksonXmlSupport());

    @Test
    @Order(value = 0)
    public void unmarshallAttivitaFromXmlStringTest() throws Exception {
        logger.info("##################Attivita_FROM_XML_STRING : {}\n", GP_JACKSON_XML_MAPPER.read(new StringReader("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n"
                + "<ATTIVITA>\n"
                + "    <TRATTA_PICCHETTO>001-002</TRATTA_PICCHETTO>\n"
                + "    <PROGRESSIVO>0010</PROGRESSIVO>\n"
                + "    <DESCRIZIONE>XM7 Posa cavo interrato Linee BT KM</DESCRIZIONE>\n"
                + "    <CODICE>XM7</CODICE>\n"
                + "    <WBE>GEDQ1N06QSR463</WBE>\n"
                + "    <UNITA_MISURA>KM</UNITA_MISURA>\n"
                + "    <TIPOLOGIA_IMPIANTO>463R</TIPOLOGIA_IMPIANTO>\n"
                + "    <QTA_PROGETTO>0</QTA_PROGETTO>\n"
                + "    <IMPORTO_PRESUNTO>2839.6</IMPORTO_PRESUNTO>\n"
                + "    <PRESTAZIONE>\n"
                + "        <CODICE>EP1005</CODICE>\n"
                + "        <DESCRIZIONE>EXT - CANALIZ. TIPO B IN STRADA ASFALT. O CEMENTATA</DESCRIZIONE>\n"
                + "        <QTA_PROGETTO>80.000</QTA_PROGETTO>\n"
                + "        <UNITA_MISURA>M</UNITA_MISURA>\n"
                + "        <VALORE_ZMED>7020</VALORE_ZMED>\n"
                + "    </PRESTAZIONE>\n"
                + "    <PRESTAZIONE>\n"
                + "        <CODICE>EP1006</CODICE>\n"
                + "        <DESCRIZIONE>EXT - CANALIZ. TIPO B IN AREE PAVIMENTATE</DESCRIZIONE>\n"
                + "        <QTA_PROGETTO>20.000</QTA_PROGETTO>\n"
                + "        <UNITA_MISURA>M</UNITA_MISURA>\n"
                + "        <VALORE_ZMED>7020</VALORE_ZMED>\n"
                + "    </PRESTAZIONE>\n"
                + "    <PRESTAZIONE>\n"
                + "        <CODICE>EP1011</CODICE>\n"
                + "        <DESCRIZIONE>EXT - FORN. E POSA TUBO ACC. O CANAL. LAM. ZINCATA</DESCRIZIONE>\n"
                + "        <QTA_PROGETTO>6.000</QTA_PROGETTO>\n"
                + "        <UNITA_MISURA>M</UNITA_MISURA>\n"
                + "        <VALORE_ZMED>7020</VALORE_ZMED>\n"
                + "    </PRESTAZIONE>\n"
                + "    <PRESTAZIONE>\n"
                + "        <CODICE>EP3007</CODICE>\n"
                + "        <DESCRIZIONE>EXT - POSA CAVO BT INTERRATO SEZ. 50 MMQ.</DESCRIZIONE>\n"
                + "        <QTA_PROGETTO>86.000</QTA_PROGETTO>\n"
                + "        <UNITA_MISURA>M</UNITA_MISURA>\n"
                + "        <VALORE_ZMED>7020</VALORE_ZMED>\n"
                + "    </PRESTAZIONE>\n"
                + "    <MATERIALE>\n"
                + "        <CODICE>330656</CODICE>\n"
                + "        <QTA_PROGETTO>86.000</QTA_PROGETTO>\n"
                + "        <SEGNO>+</SEGNO>\n"
                + "    </MATERIALE>\n"
                + "</ATTIVITA>")));
    }

    @Test
    @Order(value = 1)
    public void unmarshallAttivitaFromFileTest() throws Exception {
        logger.info("#######################Attivita_FROM_FILE : {}\n", GP_JACKSON_XML_MAPPER
                .read(new File(of("src", "test", "resources", "attivita.xml").collect(joining(separator)))));
    }

    @Test
    public void marshallAttivitaAsStringTest() throws Exception {
        logger.info("@@@@@@@@@@@@@@@@@@@Attivita_AS_STRING_TEST : \n{}\n", GP_JACKSON_XML_MAPPER
                .writeAsString(AttivitaJacksonXmlMapperTest::toAttivita));
    }

    /**
     * @return {@link Attivita}
     */
    public static Attivita toAttivita() {
        return new Attivita() {
            {
                super.setTrattaPicchetto("TRATTA_PICCHETTO_TEST");
                super.setProgressivo(0);
                super.setCodice(randomUUID().toString());
                super.setWbe("WBE_TEST");
                super.setUnitaMisura("UNITA_MISURA_TEST");
                super.setTipologiaImpianto("TIPOLOGIA_IMPIANTO_TEST");
                super.setQtaProgetto("QTA_PROGETTO_TEST");
                super.setUnitaMisura("M");
                super.setPrestazioni(toPrestazioni(50));
                super.setMateriale(toMateriale());
            }
        };
    }

    /**
     * @param numbers
     * @return {@link List<Attivita>}
     */
    static List<Attivita> toAttivita(int numbers) {
        checkArgument((numbers > 0), "The Number of Attivita must be greather than 0.");
        return iterate(0, n -> n + 1)
                .limit(numbers)
                .boxed()
                .map(AttivitaJacksonXmlMapperTest::toAttivita)
                .collect(toList());
    }

    /**
     * @param theValue
     * @return {@link Attivita}
     */
    static Attivita toAttivita(@Nonnull(when = NEVER) Integer theValue) {
        checkArgument(theValue != null, "The Parameter value must not be null");
        return new Attivita() {
            {
                super.setTrattaPicchetto("TRATTA_PICCHETTO_TEST#" + theValue.toString());
                super.setProgressivo(theValue);
                super.setCodice(theValue.toString());
                super.setWbe("WBE_TEST#" + theValue.toString());
                super.setUnitaMisura("UNITA_MISURA_TEST#" + theValue.toString());
                super.setTipologiaImpianto("TIPOLOGIA_IMPIANTO_TEST#" + theValue.toString());
                super.setQtaProgetto("QTA_PROGETTO_TEST#" + theValue.toString());
                super.setUnitaMisura(theValue % 2 == 0 ? "M" : "KM");
                super.setPrestazioni(toPrestazioni(theValue + 1));
                super.setMateriale(toMateriale());
            }
        };
    }
}