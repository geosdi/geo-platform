/*
 *
 *    geo-platform
 *    Rich webgis framework
 *    http://geo-platform.org
 *   ====================================================================
 *
 *   Copyright (C) 2008-2026 geoSDI Group (CNR IMAA - Potenza - ITALY).
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

import org.geosdi.geoplatform.connector.geoserver.model.settings.service.version.GPGeoserverGeotoolsVersion;
import org.geosdi.geoplatform.connector.geoserver.model.settings.service.version.GeoserverGeotoolsVersion;
import org.geosdi.geoplatform.connector.geoserver.model.settings.service.wmts.GPGeoserverWMTSServiceSettings;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.stream.IntStream;

import static java.io.File.separator;
import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Stream.of;
import static org.geosdi.geoplatform.connector.geoserver.styles.sld.GeoserverStyleSLDV100Request.JACKSON_JAXB_XML_SUPPORT;
import static org.geosdi.geoplatform.connector.jackson.GPGeoserverJacksonTest.jacksonSupport;
import static org.geosdi.geoplatform.connector.jackson.GPGeoserverKeywordJacksonTest.toKeyword;
import static org.geosdi.geoplatform.connector.jackson.GPGeoserverWFSServiceSettingsJacksonTest.toMetadataLinkSettings;
import static org.junit.runners.MethodSorters.NAME_ASCENDING;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@FixMethodOrder(value = NAME_ASCENDING)
public class GPGeoserverWMTSServiceSettingsJacksonTest {

    private static final Logger logger = LoggerFactory.getLogger(GPGeoserverWMTSServiceSettingsJacksonTest.class);

    @Test
    public void a_marshallGPGeoserverWMTSServiceSettingsAsXmlStringTest() throws Exception {
        logger.info("@@@@@@@@@@@@@@@@@@@@@GEOSERVER_WMTS_SERVICE_SETTINGS : \n{}\n", JACKSON_JAXB_XML_SUPPORT.writeAsString(GPGeoserverWMTSServiceSettingsJacksonTest::toWMTSServiceSettings));
    }

    @Test
    public void b_unmarshallGPGeoserverWMTSServiceSettingsFromXmlFileTest() throws Exception {
        GPGeoserverWMTSServiceSettings wmtsServiceSettings = JACKSON_JAXB_XML_SUPPORT.getDefaultMapper()
                .readValue(new File(of(new File(".").getCanonicalPath(), "src", "test", "resources", "WMTSSettings")
                        .collect(joining(separator, "", ".xml"))), GPGeoserverWMTSServiceSettings.class);
        logger.info("####################GP_GEOSERVER_WMTS_SERVICE_SETTINGS : {}\n", wmtsServiceSettings);
    }

    @Test
    public void c_marshallGPGeoserverWMTSServiceSettingsAsJsonStringTest() throws Exception {
        logger.info("@@@@@@@@@@@@@@@@@@@@@GEOSERVER_WMTS_SERVICE_SETTINGS : \n{}\n", jacksonSupport.writeAsString(GPGeoserverWMTSServiceSettingsJacksonTest::toWMTSServiceSettings));
    }

    @Test
    public void d_unmarshallGPGeoserverWMTSServiceSettingsFromJsonFileTest() throws Exception {
        GPGeoserverWMTSServiceSettings wmtsServiceSettings = jacksonSupport.getDefaultMapper()
                .readValue(new File(of(new File(".").getCanonicalPath(), "src", "test", "resources", "WMTSSettings")
                        .collect(joining(separator, "", ".json"))), GPGeoserverWMTSServiceSettings.class);
        logger.info("####################GP_GEOSERVER_WMTS_SERVICE_SETTINGS : {}\n", wmtsServiceSettings);
    }

    /**
     * @return {@link GPGeoserverWMTSServiceSettings}
     */
    public static GPGeoserverWMTSServiceSettings toWMTSServiceSettings() {
        GPGeoserverWMTSServiceSettings wmtsServiceSettings = new GPGeoserverWMTSServiceSettings();
        wmtsServiceSettings.setEnabled(TRUE);
        wmtsServiceSettings.setName("My GeoServer WMS");
        wmtsServiceSettings.setTitle("My GeoServer WMS");
        wmtsServiceSettings.setMaintainer("http://geoserver.org/comm");
        wmtsServiceSettings.setAbstrct("This is a description of your Web Map Server.");
        wmtsServiceSettings.setAccessConstraints("NONE");
        wmtsServiceSettings.setFees("NONE");
        GeoserverGeotoolsVersion geotoolsVersion = new GPGeoserverGeotoolsVersion();
        geotoolsVersion.setVersions(IntStream.iterate(0, n -> n + 1)
                .limit(15)
                .boxed()
                .map(GPGeoserverWMSServiceSettingsJacksonTest::toGeoserverVersion)
                .collect(toList()));
        wmtsServiceSettings.setVersion(geotoolsVersion);
        wmtsServiceSettings.setKeyword(toKeyword(10));
        wmtsServiceSettings.setCiteCompliant(TRUE);
        wmtsServiceSettings.setOnlineResource("http://geoserver.org");
        wmtsServiceSettings.setSchemaBaseURL("http://schemas.opengis.net");
        wmtsServiceSettings.setVerbose(FALSE);
        wmtsServiceSettings.setMetadataLink(toMetadataLinkSettings());
        return wmtsServiceSettings;
    }
}