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