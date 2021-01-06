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

import org.geosdi.geoplatform.connector.geoserver.model.security.GPGeoserverMasterPassword;
import org.geosdi.geoplatform.connector.geoserver.model.security.GPGeoserverUpdateMasterPassword;
import org.geosdi.geoplatform.connector.geoserver.model.security.catalog.GPGeoserverCatalog;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;

import static org.geosdi.geoplatform.connector.jackson.GPGeoserverJacksonTest.emptyJacksonSupport;
import static org.junit.runners.MethodSorters.NAME_ASCENDING;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@FixMethodOrder(value = NAME_ASCENDING)
public class GPGeoserverSecurityJacksonTest {

    private static final Logger logger = LoggerFactory.getLogger(GPGeoserverSecurityJacksonTest.class);

    @Test
    public void a_unmarshallGeoserverMasterPasswordTest() throws Exception {
        GPGeoserverMasterPassword geoserverMasterPassword = emptyJacksonSupport
                .getDefaultMapper().readValue(new StringReader("{\"oldMasterPassword\":\"GeoServer\"}"), GPGeoserverMasterPassword.class);
        logger.info("#######################GEOSERVER_MASTER_PASSWORD : {}\n", geoserverMasterPassword);
    }

    @Test
    public void b_unmarshallGeoserverUpdateMasterPasswordTest() throws Exception {
        GPGeoserverUpdateMasterPassword geoserverUpdateMasterPassword = emptyJacksonSupport
                .getDefaultMapper().readValue(new StringReader("{\n" +
                        "  \"oldMasterPassword\": \"oldPassword\",\n" +
                        "  \"newMasterPassword\": \"newPassword\"\n" +
                        "}"), GPGeoserverUpdateMasterPassword.class);
        logger.info("######################GEOSERVER_UPDATE_MASTER_PASSWORD : {}\n", geoserverUpdateMasterPassword);
    }

    @Test
    public void c_unmarshallGPGeoserverCatalogTest() throws Exception {
        GPGeoserverCatalog geoserverCatalog = emptyJacksonSupport
                .getDefaultMapper().readValue(new StringReader("{\"mode\":\"HIDE\"}"), GPGeoserverCatalog.class);
        Writer writer = new StringWriter();
        emptyJacksonSupport.getDefaultMapper().writeValue(writer, geoserverCatalog);
        logger.info("@@@@@@@@@@@@@@@@@@@@@GEOSERVER_CATALOG : \n{}\n", writer);
    }
}