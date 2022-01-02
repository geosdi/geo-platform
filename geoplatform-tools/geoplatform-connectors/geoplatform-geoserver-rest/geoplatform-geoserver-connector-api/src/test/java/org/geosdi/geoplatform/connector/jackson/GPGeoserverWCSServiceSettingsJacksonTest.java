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

import org.geosdi.geoplatform.connector.geoserver.model.settings.service.version.GPGeoserverGeotoolsVersion;
import org.geosdi.geoplatform.connector.geoserver.model.settings.service.version.GeoserverGeotoolsVersion;
import org.geosdi.geoplatform.connector.geoserver.model.settings.service.wcs.GPGeoserverWCSServiceSettings;
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
public class GPGeoserverWCSServiceSettingsJacksonTest {

    private static final Logger logger = LoggerFactory.getLogger(GPGeoserverWCSServiceSettingsJacksonTest.class);

    @Test
    public void a_marshallGPGeoserverWCSServiceSettingsTest() throws Exception {
        logger.info("@@@@@@@@@@@@@@@@@@@@GP_GEOSERVER_WCS_SERVICE_SETTINGS : \n{}\n", JACKSON_JAXB_XML_SUPPORT.writeAsString(GPGeoserverWCSServiceSettingsJacksonTest::toWCSServiceSettings));
    }

    @Test
    public void b_unmarshallGPGeoserverWCSServiceSettingsFromXmlFileTest() throws Exception {
        GPGeoserverWCSServiceSettings wcsServiceSettings = JACKSON_JAXB_XML_SUPPORT.getDefaultMapper()
                .readValue(new File(of(new File(".").getCanonicalPath(), "src", "test", "resources", "WCSSettings")
                        .collect(joining(separator, "", ".xml"))), GPGeoserverWCSServiceSettings.class);
        logger.info("####################GP_GEOSERVER_WCS_SERVICE_SETTINGS : {}\n", wcsServiceSettings);
    }

    @Test
    public void c_marshallGPGeoserverWCSServiceSettingsAsJsonStringTest() throws Exception {
        logger.info("@@@@@@@@@@@@@@@@@@@@GP_GEOSERVER_WCS_SERVICE_SETTINGS : \n{}\n", jacksonSupport.writeAsString(GPGeoserverWCSServiceSettingsJacksonTest::toWCSServiceSettings));
    }

    @Test
    public void d_unmarshallGPGeoserverWCSServiceSettingsFromJsonFileTest() throws Exception {
        GPGeoserverWCSServiceSettings wcsServiceSettings = jacksonSupport.getDefaultMapper()
                .readValue(new File(of(new File(".").getCanonicalPath(), "src", "test", "resources", "WCSSettings")
                        .collect(joining(separator, "", ".json"))), GPGeoserverWCSServiceSettings.class);
        logger.info("####################GP_GEOSERVER_WCS_SERVICE_SETTINGS : {}\n", wcsServiceSettings);
    }

    /**
     * @return {@link GPGeoserverWCSServiceSettings}
     */
    public static GPGeoserverWCSServiceSettings toWCSServiceSettings() {
        GPGeoserverWCSServiceSettings wcsServiceSettings = new GPGeoserverWCSServiceSettings();
        wcsServiceSettings.setEnabled(TRUE);
        wcsServiceSettings.setName("My GeoServer WMS");
        wcsServiceSettings.setTitle("My GeoServer WMS");
        wcsServiceSettings.setMaintainer("http://geoserver.org/comm");
        wcsServiceSettings.setAbstrct("This is a description of your Web Map Server.");
        wcsServiceSettings.setAccessConstraints("NONE");
        wcsServiceSettings.setFees("NONE");
        GeoserverGeotoolsVersion geotoolsVersion = new GPGeoserverGeotoolsVersion();
        geotoolsVersion.setVersions(IntStream.iterate(0, n -> n + 1)
                .limit(15)
                .boxed()
                .map(GPGeoserverWMSServiceSettingsJacksonTest::toGeoserverVersion)
                .collect(toList()));
        wcsServiceSettings.setVersion(geotoolsVersion);
        wcsServiceSettings.setKeyword(toKeyword(10));
        wcsServiceSettings.setCiteCompliant(TRUE);
        wcsServiceSettings.setOnlineResource("http://geoserver.org");
        wcsServiceSettings.setSchemaBaseURL("http://schemas.opengis.net");
        wcsServiceSettings.setVerbose(FALSE);
        wcsServiceSettings.setMetadataLink(toMetadataLinkSettings());
        wcsServiceSettings.setGmlPrefixing(TRUE);
        wcsServiceSettings.setLatLon(TRUE);
        wcsServiceSettings.setMaxInputMemory(0);
        wcsServiceSettings.setMaxOutputMemory(0);
        return wcsServiceSettings;
    }
}