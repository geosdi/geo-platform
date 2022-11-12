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
package org.geosdi.geoplatform.connector.jackson.wms.store;

import org.geosdi.geoplatform.connector.geoserver.model.wms.store.GeoserverWMSBaseStore;
import org.geosdi.geoplatform.connector.geoserver.model.wms.store.GeoserverWMSStores;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.StringReader;

import static org.geosdi.geoplatform.connector.jackson.GPGeoserverJacksonTest.emptyJacksonSupport;
import static org.geosdi.geoplatform.connector.jackson.GPGeoserverJacksonTest.jacksonSupport;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class GeoserverWMSStoresJacksonTest {

    private static final Logger logger = LoggerFactory.getLogger(GeoserverWMSStoresJacksonTest.class);

    @Test
    public void a_unmarshallWMSStoresFromJsonStringTest() throws Exception {
        GeoserverWMSStores wmsStores = jacksonSupport.getDefaultMapper().readValue(new StringReader("{\n"
                + "   \"wmsStores\":{\n"
                + "      \"wmsStore\":[\n"
                + "         {\n"
                + "            \"name\":\"altgs\",\n"
                + "            \"href\":\"http://localhost:8080/geoserver/rest/workspaces/cite/wmsstores/altgs.json\"\n"
                + "         }\n"
                + "      ]\n"
                + "   }\n"
                + "}"), GeoserverWMSStores.class);
        logger.info("@@@@@@@@@@@@@@@@@@@@WMS_STORES : \n{}\n", jacksonSupport.getDefaultMapper().writeValueAsString(wmsStores));
    }

    @Test
    public void b_unmarshallWMSBaseStoreFromJsonStringTest() throws Exception {
        GeoserverWMSBaseStore wmsBaseStore = emptyJacksonSupport.getDefaultMapper().readValue(new StringReader("{\n"
                + "   \"name\":\"altgs\",\n"
                + "   \"href\":\"http://localhost:8080/geoserver/rest/workspaces/cite/wmsstores/altgs.json\"\n"
                + "}"), GeoserverWMSBaseStore.class);
        logger.info("##################WMS_BASE_STORE : \n{}\n", emptyJacksonSupport.getDefaultMapper().writeValueAsString(wmsBaseStore));
    }
}