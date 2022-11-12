/**
 * geo-platform
 * Rich webgis framework
 * http://geo-platform.org
 * ====================================================================
 * <p>
 * Copyright (C) 2008-2022 geoSDI Group (CNR IMAA - Potenza - ITALY).
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
package org.geosdi.geoplatform.connector.jackson.workspace;

import org.geosdi.geoplatform.connector.geoserver.model.workspace.GPGeoserverLinkWorkspace;
import org.geosdi.geoplatform.connector.geoserver.model.workspace.GPGeoserverWorkspace;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.StringReader;

import static org.geosdi.geoplatform.connector.jackson.GPGeoserverJacksonTest.jacksonSupport;
import static org.geosdi.geoplatform.connector.jackson.GPGeoserverJacksonTest.jacksonXmlSupport;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class GPGeoserverWorkspaceJacksonTest {

    private static final Logger logger = LoggerFactory.getLogger(GPGeoserverWorkspaceJacksonTest.class);

    @Test
    public void a_marshallGPGeoserverWorkspaceAsJsonStringTest() throws Exception {
        GPGeoserverWorkspace workspace = new GPGeoserverWorkspace("test", "value");
        logger.info("@@@@@@@@@@@@@@@@@@@@@@@GP_GEOSERVER_WORKSPACE : \n{}\n", jacksonSupport.getDefaultMapper().writeValueAsString(workspace));
    }

    @Test
    public void b_unmarshallGPGeoserverWorkspaceFromJsonStringTest() throws Exception {
        GPGeoserverWorkspace workspace = jacksonSupport.getDefaultMapper().readValue(new StringReader("{\n"
                + "  \"workspace\" : {\n"
                + "    \"name\" : \"test\",\n"
                + "    \"href\" : \"value\"\n"
                + "  }\n"
                + "}"), GPGeoserverWorkspace.class);
        logger.info("#####################GP_GEOSERVER_WORKSPACE : {}\n", workspace);
    }

    @Test
    public void c_marshallGPGeoserverWorkspaceAsXmlStringTest() throws Exception {
        GPGeoserverWorkspace workspace = new GPGeoserverWorkspace("test", "value");
        logger.info("@@@@@@@@@@@@@@@@@@@@@@@GP_GEOSERVER_WORKSPACE : \n{}\n", jacksonXmlSupport.getDefaultMapper().writeValueAsString(workspace));
    }

    @Test
    public void d_unmarshallGPGeoserverWorkspaceFromXmlStringTest() throws Exception {
        GPGeoserverWorkspace workspace = jacksonXmlSupport.getDefaultMapper().readValue(new StringReader("<workspace>\n"
                + "  <name>test</name>\n"
                + "  <href>value</href>\n"
                + "</workspace>"), GPGeoserverWorkspace.class);
        logger.info("#####################GP_GEOSERVER_WORKSPACE : {}\n", workspace);
    }

    @Test
    public void e_marshallGPGeoserverLinkWorkspaceAsJsonStringTest() throws Exception {
        GPGeoserverLinkWorkspace workspace = new GPGeoserverLinkWorkspace("test", "value");
        logger.info("@@@@@@@@@@@@@@@@@@@@@@@GP_LINK_GEOSERVER_WORKSPACE : \n{}\n", jacksonSupport.getDefaultMapper().writeValueAsString(workspace));
    }

    @Test
    public void f_unmarshallGPGeoserverLinkWorkspaceFromJsonStringTest() throws Exception {
        GPGeoserverLinkWorkspace workspace = jacksonSupport.getDefaultMapper().readValue(new StringReader("{\n"
                + "  \"workspace\" : {\n"
                + "    \"name\" : \"test\",\n"
                + "    \"link\" : \"value\"\n"
                + "  }\n"
                + "}"), GPGeoserverLinkWorkspace.class);
        logger.info("#####################GP_GEOSERVER_LINK_WORKSPACE : {}\n", workspace);
    }

    @Test
    public void g_marshallGPGeoserverLinkWorkspaceAsXmlStringTest() throws Exception {
        GPGeoserverLinkWorkspace workspace = new GPGeoserverLinkWorkspace("test", "value");
        logger.info("@@@@@@@@@@@@@@@@@@@@@@@GP_LINK_GEOSERVER_WORKSPACE : \n{}\n", jacksonXmlSupport.getDefaultMapper().writeValueAsString(workspace));
    }

    @Test
    public void h_unmarshallGPGeoserverLinkWorkspaceFromXmlStringTest() throws Exception {
        GPGeoserverLinkWorkspace workspace = jacksonXmlSupport.getDefaultMapper().readValue(new StringReader("<workspace>\n"
                + "  <name>test</name>\n"
                + "  <link>value</link>\n"
                + "</workspace>"), GPGeoserverLinkWorkspace.class);
        logger.info("#####################GP_GEOSERVER_LINK_WORKSPACE : {}\n", workspace);
    }
}