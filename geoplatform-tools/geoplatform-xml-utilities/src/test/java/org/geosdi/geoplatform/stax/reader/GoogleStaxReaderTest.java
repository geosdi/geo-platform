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
package org.geosdi.geoplatform.stax.reader;

import org.geosdi.geoplatform.stax.reader.demo.GoogleGeocodingAaltoReader;
import org.geosdi.geoplatform.stax.reader.demo.GoogleGeocodingStaxReader;
import org.geosdi.geoplatform.stax.reader.demo.GoogleGeocodingWoodstoxReader;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StopWatch;

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
public class GoogleStaxReaderTest {

    private static final Logger logger = LoggerFactory.getLogger(GoogleStaxReaderTest.class);
    //
    private static final StopWatch stopWatch = new StopWatch("GoogleStaxComparison");
    private static final GoogleGeocodingStaxReader googleStaxReader = new GoogleGeocodingStaxReader();
    private static final GoogleGeocodingWoodstoxReader googleWoodstoxReader = new GoogleGeocodingWoodstoxReader();
    private static final GoogleGeocodingAaltoReader googleAaltoReader = new GoogleGeocodingAaltoReader();
    private static String pathFile;

    @BeforeClass
    public static void beforeClass() throws Exception {
        pathFile = of(new File(".").getCanonicalPath(), "src", "test", "resources", "googleGeocodeExample.xml")
                .collect(joining(separator));
    }

    @AfterClass
    public static void afterClass() throws Exception {
        logger.info("{}\n", stopWatch.prettyPrint());
    }

    @Test
    public void a_staxReadFromFile() throws Exception {
        stopWatch.start("googleStaxReader");
        logger.info("#################GoolgeStaxReader Result from File @@@@@@@@@@@@@@@@@@@@ {}\n", googleStaxReader.read(new File(pathFile)).toString());
        stopWatch.stop();
        stopWatch.start("googleAaltoReader");
        logger.info("#################GoolgeAaltoReader Result from File @@@@@@@@@@@@@@@@@@@@ {}\n", googleAaltoReader.read(new File(pathFile)).toString());
        stopWatch.stop();
        stopWatch.start("googleWoodstoxReader");
        logger.info("#################GoolgeWoodstoxReader Result from File @@@@@@@@@@@@@@@@@@@@ {}\n", googleWoodstoxReader.read(new File(pathFile)).toString());
        stopWatch.stop();

    }

    @Test
    public void b_readFromString() throws Exception {
        stopWatch.start("googleStaxReaderFromString");
        logger.info("Result from String @@@@@@@@@@@@@@@@@@@@@@@@@ {}\n", googleStaxReader.read(fileAsString()));
        stopWatch.stop();
        stopWatch.start("googleAaltoReaderFromString");
        logger.info("Result from String @@@@@@@@@@@@@@@@@@@@@@@@@ {}\n", googleAaltoReader.read(fileAsString()));
        stopWatch.stop();
        stopWatch.start("googleWoodstoxReaderFromString");
        logger.info("Result from String @@@@@@@@@@@@@@@@@@@@@@@@@ {}\n", googleWoodstoxReader.read(fileAsString()));
        stopWatch.stop();
    }

    private String fileAsString() {
        return "<GeocodeResponse>\n"
                + "    <status>OK</status>\n"
                + "    <result>\n"
                + "        <type>route</type>\n"
                + "        <formatted_address>Contrada Monte Grosso, 85100 Potenza, Italy</formatted_address>\n"
                + "        <address_component>\n"
                + "            <long_name>Contrada Monte Grosso</long_name>\n"
                + "            <short_name>Contrada Monte Grosso</short_name>\n"
                + "            <type>route</type>\n"
                + "        </address_component>\n"
                + "        <address_component>\n"
                + "            <long_name>Potenza</long_name>\n"
                + "            <short_name>Potenza</short_name>\n"
                + "            <type>locality</type>\n"
                + "            <type>political</type>\n"
                + "        </address_component>\n"
                + "        <address_component>\n"
                + "            <long_name>Potenza</long_name>\n"
                + "            <short_name>PZ</short_name>\n"
                + "            <type>administrative_area_level_2</type>\n"
                + "            <type>political</type>\n"
                + "        </address_component>\n"
                + "        <address_component>\n"
                + "            <long_name>Basilicata</long_name>\n"
                + "            <short_name>Basilicata</short_name>\n"
                + "            <type>administrative_area_level_1</type>\n"
                + "            <type>political</type>\n"
                + "        </address_component>\n"
                + "        <address_component>\n"
                + "            <long_name>Italy</long_name>\n"
                + "            <short_name>IT</short_name>\n"
                + "            <type>country</type>\n"
                + "            <type>political</type>\n"
                + "        </address_component>\n"
                + "        <address_component>\n"
                + "            <long_name>85100</long_name>\n"
                + "            <short_name>85100</short_name>\n"
                + "            <type>postal_code</type>\n"
                + "        </address_component>\n"
                + "        <geometry>\n"
                + "            <location>\n"
                + "                <lat>40.6220103</lat>\n"
                + "                <lng>15.8663895</lng>\n"
                + "            </location>\n"
                + "            <location_type>APPROXIMATE</location_type>\n"
                + "            <viewport>\n"
                + "                <southwest>\n"
                + "                    <lat>40.6205376</lat>\n"
                + "                    <lng>15.8642091</lng>\n"
                + "                </southwest>\n"
                + "                <northeast>\n"
                + "                    <lat>40.6232355</lat>\n"
                + "                    <lng>15.8685939</lng>\n"
                + "                </northeast>\n"
                + "            </viewport>\n"
                + "            <bounds>\n"
                + "                <southwest>\n"
                + "                    <lat>40.6208391</lat>\n"
                + "                    <lng>15.8642091</lng>\n"
                + "                </southwest>\n"
                + "                <northeast>\n"
                + "                    <lat>40.6229340</lat>\n"
                + "                    <lng>15.8685939</lng>\n"
                + "                </northeast>\n"
                + "            </bounds>\n"
                + "        </geometry>\n"
                + "    </result>\n"
                + "    <result>\n"
                + "        <type>postal_code</type>\n"
                + "        <formatted_address>85100 Potenza, Italy</formatted_address>\n"
                + "        <address_component>\n"
                + "            <long_name>85100</long_name>\n"
                + "            <short_name>85100</short_name>\n"
                + "            <type>postal_code</type>\n"
                + "        </address_component>\n"
                + "        <address_component>\n"
                + "            <long_name>Potenza</long_name>\n"
                + "            <short_name>Potenza</short_name>\n"
                + "            <type>locality</type>\n"
                + "            <type>political</type>\n"
                + "        </address_component>\n"
                + "        <address_component>\n"
                + "            <long_name>Potenza</long_name>\n"
                + "            <short_name>PZ</short_name>\n"
                + "            <type>administrative_area_level_2</type>\n"
                + "            <type>political</type>\n"
                + "        </address_component>\n"
                + "        <address_component>\n"
                + "            <long_name>Basilicata</long_name>\n"
                + "            <short_name>Basilicata</short_name>\n"
                + "            <type>administrative_area_level_1</type>\n"
                + "            <type>political</type>\n"
                + "        </address_component>\n"
                + "        <address_component>\n"
                + "            <long_name>Italy</long_name>\n"
                + "            <short_name>IT</short_name>\n"
                + "            <type>country</type>\n"
                + "            <type>political</type>\n"
                + "        </address_component>\n"
                + "        <geometry>\n"
                + "            <location>\n"
                + "                <lat>40.6464034</lat>\n"
                + "                <lng>15.8055471</lng>\n"
                + "            </location>\n"
                + "            <location_type>APPROXIMATE</location_type>\n"
                + "            <viewport>\n"
                + "                <southwest>\n"
                + "                    <lat>40.5790779</lat>\n"
                + "                    <lng>15.7065539</lng>\n"
                + "                </southwest>\n"
                + "                <northeast>\n"
                + "                    <lat>40.7408128</lat>\n"
                + "                    <lng>15.9017078</lng>\n"
                + "                </northeast>\n"
                + "            </viewport>\n"
                + "            <bounds>\n"
                + "                <southwest>\n"
                + "                    <lat>40.5790779</lat>\n"
                + "                    <lng>15.7065539</lng>\n"
                + "                </southwest>\n"
                + "                <northeast>\n"
                + "                    <lat>40.7408128</lat>\n"
                + "                    <lng>15.9017078</lng>\n"
                + "                </northeast>\n"
                + "            </bounds>\n"
                + "        </geometry>\n"
                + "    </result>\n"
                + "    <result>\n"
                + "        <type>locality</type>\n"
                + "        <type>political</type>\n"
                + "        <formatted_address>Potenza, Italy</formatted_address>\n"
                + "        <address_component>\n"
                + "            <long_name>Potenza</long_name>\n"
                + "            <short_name>Potenza</short_name>\n"
                + "            <type>locality</type>\n"
                + "            <type>political</type>\n"
                + "        </address_component>\n"
                + "        <address_component>\n"
                + "            <long_name>Potenza</long_name>\n"
                + "            <short_name>PZ</short_name>\n"
                + "            <type>administrative_area_level_2</type>\n"
                + "            <type>political</type>\n"
                + "        </address_component>\n"
                + "        <address_component>\n"
                + "            <long_name>Basilicata</long_name>\n"
                + "            <short_name>Basilicata</short_name>\n"
                + "            <type>administrative_area_level_1</type>\n"
                + "            <type>political</type>\n"
                + "        </address_component>\n"
                + "        <address_component>\n"
                + "            <long_name>Italy</long_name>\n"
                + "            <short_name>IT</short_name>\n"
                + "            <type>country</type>\n"
                + "            <type>political</type>\n"
                + "        </address_component>\n"
                + "        <geometry>\n"
                + "            <location>\n"
                + "                <lat>40.6372425</lat>\n"
                + "                <lng>15.8022214</lng>\n"
                + "            </location>\n"
                + "            <location_type>APPROXIMATE</location_type>\n"
                + "            <viewport>\n"
                + "                <southwest>\n"
                + "                    <lat>40.5790779</lat>\n"
                + "                    <lng>15.7065539</lng>\n"
                + "                </southwest>\n"
                + "                <northeast>\n"
                + "                    <lat>40.7408128</lat>\n"
                + "                    <lng>15.9017078</lng>\n"
                + "                </northeast>\n"
                + "            </viewport>\n"
                + "            <bounds>\n"
                + "                <southwest>\n"
                + "                    <lat>40.5790779</lat>\n"
                + "                    <lng>15.7065539</lng>\n"
                + "                </southwest>\n"
                + "                <northeast>\n"
                + "                    <lat>40.7408128</lat>\n"
                + "                    <lng>15.9017078</lng>\n"
                + "                </northeast>\n"
                + "            </bounds>\n"
                + "        </geometry>\n"
                + "    </result>\n"
                + "    <result>\n"
                + "        <type>administrative_area_level_2</type>\n"
                + "        <type>political</type>\n"
                + "        <formatted_address>Potenza, Italy</formatted_address>\n"
                + "        <address_component>\n"
                + "            <long_name>Potenza</long_name>\n"
                + "            <short_name>PZ</short_name>\n"
                + "            <type>administrative_area_level_2</type>\n"
                + "            <type>political</type>\n"
                + "        </address_component>\n"
                + "        <address_component>\n"
                + "            <long_name>Basilicata</long_name>\n"
                + "            <short_name>Basilicata</short_name>\n"
                + "            <type>administrative_area_level_1</type>\n"
                + "            <type>political</type>\n"
                + "        </address_component>\n"
                + "        <address_component>\n"
                + "            <long_name>Italy</long_name>\n"
                + "            <short_name>IT</short_name>\n"
                + "            <type>country</type>\n"
                + "            <type>political</type>\n"
                + "        </address_component>\n"
                + "        <geometry>\n"
                + "            <location>\n"
                + "                <lat>40.4182194</lat>\n"
                + "                <lng>15.8760040</lng>\n"
                + "            </location>\n"
                + "            <location_type>APPROXIMATE</location_type>\n"
                + "            <viewport>\n"
                + "                <southwest>\n"
                + "                    <lat>39.8943315</lat>\n"
                + "                    <lng>15.3364212</lng>\n"
                + "                </southwest>\n"
                + "                <northeast>\n"
                + "                    <lat>41.1393551</lat>\n"
                + "                    <lng>16.3998902</lng>\n"
                + "                </northeast>\n"
                + "            </viewport>\n"
                + "            <bounds>\n"
                + "                <southwest>\n"
                + "                    <lat>39.8943315</lat>\n"
                + "                    <lng>15.3364212</lng>\n"
                + "                </southwest>\n"
                + "                <northeast>\n"
                + "                    <lat>41.1393551</lat>\n"
                + "                    <lng>16.3998902</lng>\n"
                + "                </northeast>\n"
                + "            </bounds>\n"
                + "        </geometry>\n"
                + "    </result>\n"
                + "    <result>\n"
                + "        <type>administrative_area_level_1</type>\n"
                + "        <type>political</type>\n"
                + "        <formatted_address>Basilicata, Italy</formatted_address>\n"
                + "        <address_component>\n"
                + "            <long_name>Basilicata</long_name>\n"
                + "            <short_name>Basilicata</short_name>\n"
                + "            <type>administrative_area_level_1</type>\n"
                + "            <type>political</type>\n"
                + "        </address_component>\n"
                + "        <address_component>\n"
                + "            <long_name>Italy</long_name>\n"
                + "            <short_name>IT</short_name>\n"
                + "            <type>country</type>\n"
                + "            <type>political</type>\n"
                + "        </address_component>\n"
                + "        <geometry>\n"
                + "            <location>\n"
                + "                <lat>40.6430766</lat>\n"
                + "                <lng>15.9699878</lng>\n"
                + "            </location>\n"
                + "            <location_type>APPROXIMATE</location_type>\n"
                + "            <viewport>\n"
                + "                <southwest>\n"
                + "                    <lat>39.8943315</lat>\n"
                + "                    <lng>15.3364212</lng>\n"
                + "                </southwest>\n"
                + "                <northeast>\n"
                + "                    <lat>41.1393551</lat>\n"
                + "                    <lng>16.8676688</lng>\n"
                + "                </northeast>\n"
                + "            </viewport>\n"
                + "            <bounds>\n"
                + "                <southwest>\n"
                + "                    <lat>39.8943315</lat>\n"
                + "                    <lng>15.3364212</lng>\n"
                + "                </southwest>\n"
                + "                <northeast>\n"
                + "                    <lat>41.1393551</lat>\n"
                + "                    <lng>16.8676688</lng>\n"
                + "                </northeast>\n"
                + "            </bounds>\n"
                + "        </geometry>\n"
                + "    </result>\n"
                + "    <result>\n"
                + "        <type>country</type>\n"
                + "        <type>political</type>\n"
                + "        <formatted_address>Italy</formatted_address>\n"
                + "        <address_component>\n"
                + "            <long_name>Italy</long_name>\n"
                + "            <short_name>IT</short_name>\n"
                + "            <type>country</type>\n"
                + "            <type>political</type>\n"
                + "        </address_component>\n"
                + "        <geometry>\n"
                + "            <location>\n"
                + "                <lat>41.8719400</lat>\n"
                + "                <lng>12.5673800</lng>\n"
                + "            </location>\n"
                + "            <location_type>APPROXIMATE</location_type>\n"
                + "            <viewport>\n"
                + "                <southwest>\n"
                + "                    <lat>36.6441736</lat>\n"
                + "                    <lng>6.6267200</lng>\n"
                + "                </southwest>\n"
                + "                <northeast>\n"
                + "                    <lat>47.0920000</lat>\n"
                + "                    <lng>18.5203619</lng>\n"
                + "                </northeast>\n"
                + "            </viewport>\n"
                + "            <bounds>\n"
                + "                <southwest>\n"
                + "                    <lat>36.6441736</lat>\n"
                + "                    <lng>6.6267200</lng>\n"
                + "                </southwest>\n"
                + "                <northeast>\n"
                + "                    <lat>47.0920000</lat>\n"
                + "                    <lng>18.5203619</lng>\n"
                + "                </northeast>\n"
                + "            </bounds>\n"
                + "        </geometry>\n"
                + "    </result>\n"
                + "</GeocodeResponse>";
    }
}