/*
 *
 *    geo-platform
 *    Rich webgis framework
 *    http://geo-platform.org
 *   ====================================================================
 *
 *   Copyright (C) 2008-2022 geoSDI Group (CNR IMAA - Potenza - ITALY).
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

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.geosdi.geoplatform.connector.geoserver.model.featuretypes.GPGeoserverFeatureTypesStoreInfo;
import org.geosdi.geoplatform.connector.geoserver.model.store.GPGeoserverStoreInfo;
import org.geosdi.geoplatform.connector.geoserver.model.workspace.coverages.store.GPGeoserverCoverageStoreInfo;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.bind.annotation.*;
import java.io.Serializable;
import java.io.StringReader;

import static org.geosdi.geoplatform.connector.geoserver.styles.sld.GeoserverStyleSLDV100Request.JACKSON_JAXB_XML_SUPPORT;
import static org.geosdi.geoplatform.connector.jackson.GPGeoserverJacksonTest.jacksonSupport;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class GPGeoserverStoreJacksonTest {

    private static final Logger logger = LoggerFactory.getLogger(GPGeoserverStoreJacksonTest.class);

    @Test
    public void a_marshallGPGeoserverCoverageStoreInfoAsXmlStringTest() throws Exception {
        GPGeoserverCoverageStoreInfo coverageStoreInfo = new GPGeoserverCoverageStoreInfo();
        coverageStoreInfo.setHref("http://localhost:/geoserver/restng/workspaces/sf/coveragestores/sfdem.json");
        coverageStoreInfo.setName("sf:sfdem");
        logger.info("@@@@@@@@@@@@@@@@@@@@GP_COVERAGE_STORE_INFO : \n{}\n", JACKSON_JAXB_XML_SUPPORT.getDefaultMapper()
                .writeValueAsString(coverageStoreInfo));
    }

    @Test
    public void b_unmarshallGPGeoserverStoreJacksonInfoFromXmlStringTest() throws Exception {
        GPGeoserverStoreJacksonInfo storeJacksonInfo = JACKSON_JAXB_XML_SUPPORT.getDefaultMapper()
                .readValue(new StringReader("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n"
                        + "<storeJacksonInfo>\n"
                        + "    <store class=\"coverageStore\">\n" +
                        "" + "        <href>http://localhost:/geoserver/restng/workspaces/sf/coveragestores/sfdem.json</href>\n"
                        + "        <name>sf:sfdem</name>\n"
                        + "    </store>\n"
                        + "</storeJacksonInfo>"), GPGeoserverStoreJacksonInfo.class);
        logger.info("###################GP_GEOSERVER_STORE_JACKSON_INFO : {}\n", storeJacksonInfo);
    }

    @Test
    public void c_marshallGPGeoserverCoverageStoreInfoAsJsonStringTest() throws Exception {
        GPGeoserverCoverageStoreInfo coverageStoreInfo = new GPGeoserverCoverageStoreInfo();
        coverageStoreInfo.setHref("http://localhost:/geoserver/restng/workspaces/sf/coveragestores/sfdem.json");
        coverageStoreInfo.setName("sf:sfdem");
        logger.info("@@@@@@@@@@@@@@@@@@@@GP_COVERAGE_STORE_INFO : \n{}\n", jacksonSupport.getDefaultMapper()
                .writeValueAsString(coverageStoreInfo));
    }

    @Test
    public void d_unmarshallGPGeoserverCoverageStoreInfoFromJsonStringTest() throws Exception {
        GPGeoserverStoreInfo storeInfo = jacksonSupport.getDefaultMapper().readValue(new StringReader("{\n"
                + "  \"store\" : {\n"
                + "    \"@class\" : \"coverageStore\",\n"
                + "    \"name\" : \"sf:sfdem\",\n"
                + "    \"href\" : \"http://localhost:/geoserver/restng/workspaces/sf/coveragestores/sfdem.json\"\n"
                + "  }\n"
                + "}"), GPGeoserverStoreInfo.class);
        assertNotNull(storeInfo);
        assertTrue(storeInfo instanceof GPGeoserverCoverageStoreInfo);
        logger.info("###################GP_COVERAGE_STORE_INFO : {}\n", storeInfo);
    }

    @Test
    public void e_marshallGPGeoserverFeatureStoreInfoAsXmlStringTest() throws Exception {
        GPGeoserverFeatureTypesStoreInfo featureTypesStoreInfo = new GPGeoserverFeatureTypesStoreInfo();
        featureTypesStoreInfo.setName("tiger:nyc");
        featureTypesStoreInfo.setHref("http://localhost:8080/geoserver/rest/workspaces/tiger/datastores/nyc.xml");
        logger.info("@@@@@@@@@@@@@@@@@@GP_GEOSERVER_FEATURE_TYPES_STORE_INFO : \n{}\n", JACKSON_JAXB_XML_SUPPORT
                .getDefaultMapper().writeValueAsString(featureTypesStoreInfo));
    }

    @Test
    public void f_unmarshallGPGeoserverStoreJacksonInfoFromXmlStringTest() throws Exception {
        GPGeoserverStoreJacksonInfo storeJacksonInfo = JACKSON_JAXB_XML_SUPPORT.getDefaultMapper()
                .readValue(new StringReader("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n"
                        + "<storeJacksonInfo>\n"
                        + "    <store class=\"dataStore\">\n"
                        + "        <href>http://localhost:8080/geoserver/rest/workspaces/tiger/datastores/nyc.xml</href>\n"
                        + "        <name>tiger:nyc</name>\n"
                        + "    </store>\n"
                        + "</storeJacksonInfo>"), GPGeoserverStoreJacksonInfo.class);
        logger.info("###################GP_GEOSERVER_STORE_JACKSON_INFO : {}\n", storeJacksonInfo);
    }

    @Test
    public void g_marshallGPGeoserverFeatureTypesStoreInfoAsJsonStringTest() throws Exception {
        GPGeoserverFeatureTypesStoreInfo featureTypesStoreInfo = new GPGeoserverFeatureTypesStoreInfo();
        featureTypesStoreInfo.setName("tiger:nyc");
        featureTypesStoreInfo.setHref("http://localhost:8080/geoserver/rest/workspaces/tiger/datastores/nyc.xml");
        logger.info("@@@@@@@@@@@@@@@@@@GP_GEOSERVER_FEATURE_TYPES_STORE_INFO : \n{}\n", jacksonSupport
                .getDefaultMapper().writeValueAsString(featureTypesStoreInfo));
    }

    @Test
    public void h_unmarshallGPGeoserverFeatureTypesStoreFromJsonStringTest() throws Exception {
        GPGeoserverStoreInfo storeInfo = jacksonSupport.getDefaultMapper()
                .readValue(new StringReader("{\n"
                        + "  \"store\" : {\n"
                        + "    \"@class\" : \"dataStore\",\n"
                        + "    \"name\" : \"tiger:nyc\",\n"
                        + "    \"href\" : \"http://localhost:8080/geoserver/rest/workspaces/tiger/datastores/nyc.xml\"\n"
                        + "  }\n"
                        + "}"), GPGeoserverStoreInfo.class);
        assertNotNull(storeInfo);
        assertTrue(storeInfo instanceof GPGeoserverFeatureTypesStoreInfo);
        logger.info("###################GP_COVERAGE_STORE_INFO : {}\n", storeInfo);
    }

    @Test
    public void i_marshallGPGeoserverStoreJacksonInfoAsJsonStringTest() throws Exception {
        GPGeoserverFeatureTypesStoreInfo featureTypesStoreInfo = new GPGeoserverFeatureTypesStoreInfo();
        featureTypesStoreInfo.setName("tiger:nyc");
        featureTypesStoreInfo.setHref("http://localhost:8080/geoserver/rest/workspaces/tiger/datastores/nyc.xml");
        GPGeoserverStoreJacksonInfo storeJacksonInfo = new GPGeoserverStoreJacksonInfo();
        storeJacksonInfo.setStoreInfo(featureTypesStoreInfo);
        logger.info("@@@@@@@@@@@@@@@@@@GP_GEOSERVER_STORE_JACKSON_INFO : \n{}\n", jacksonSupport.getDefaultMapper()
                .writeValueAsString(storeJacksonInfo));
    }

    @Test
    public void l_unmarshallGPGeoserverStoreJacksonInfoFromJsonStringTest() throws Exception {
        GPGeoserverStoreJacksonInfo storeJacksonInfo = jacksonSupport.getDefaultMapper().readValue(new StringReader("{\n"
                + "  \"storeJacksonInfo\" : {\n"
                + "    \"storeInfo\" : {\n"
                + "      \"@class\" : \"dataStore\",\n"
                + "      \"name\" : \"tiger:nyc\",\n"
                + "      \"href\" : \"http://localhost:8080/geoserver/rest/workspaces/tiger/datastores/nyc.xml\"\n"
                + "    }\n"
                + "  }\n"
                + "}"), GPGeoserverStoreJacksonInfo.class);
        logger.info("##################GP_GEOSERVER_STORE_JACKSON_INFO : {}\n", storeJacksonInfo);
    }

    @Test
    public void m_marshallGPGeoserverStoreJacksonInfoAsXmlStringTest() throws Exception {
        GPGeoserverCoverageStoreInfo coverageStoreInfo = new GPGeoserverCoverageStoreInfo();
        coverageStoreInfo.setHref("http://localhost:/geoserver/restng/workspaces/sf/coveragestores/sfdem.json");
        coverageStoreInfo.setName("sf:sfdem");
        GPGeoserverStoreJacksonInfo storeJacksonInfo = new GPGeoserverStoreJacksonInfo();
        storeJacksonInfo.setStoreInfo(coverageStoreInfo);
        logger.info("##################GP_GEOSERVER_STORE_JACKSON_INFO : {}\n", JACKSON_JAXB_XML_SUPPORT.getDefaultMapper()
                .writeValueAsString(storeJacksonInfo));
    }

    @Getter
    @Setter
    @ToString
    @XmlRootElement(name = "storeJacksonInfo")
    @XmlAccessorType(XmlAccessType.FIELD)
    static class GPGeoserverStoreJacksonInfo implements Serializable {

        private static final long serialVersionUID = 6479843866581538281L;
        //
        @XmlElementRefs(value = {@XmlElementRef(name = "coverageStore", type = GPGeoserverCoverageStoreInfo.class),
                @XmlElementRef(name = "dataStore", type = GPGeoserverFeatureTypesStoreInfo.class)})
        private GPGeoserverStoreInfo storeInfo;
    }
}