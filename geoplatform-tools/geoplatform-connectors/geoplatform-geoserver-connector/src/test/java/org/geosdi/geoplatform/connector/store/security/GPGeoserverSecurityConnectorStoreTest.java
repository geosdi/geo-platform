package org.geosdi.geoplatform.connector.store.security;

import org.geosdi.geoplatform.connector.geoserver.model.security.catalog.GPGeoserverCatalog;
import org.geosdi.geoplatform.connector.geoserver.request.security.GPGeoserverGetMasterPasswordRequest;
import org.geosdi.geoplatform.connector.geoserver.request.security.GPGeoserverUpdateCatalogRequest;
import org.geosdi.geoplatform.connector.geoserver.request.security.catalog.GPGeoserverGetCatalogRequest;
import org.geosdi.geoplatform.connector.store.GPBaseGeoserverConnectorStoreTest;
import org.junit.FixMethodOrder;
import org.junit.Test;

import static org.geosdi.geoplatform.connector.geoserver.model.security.catalog.GPGeoserverCatalogMode.HIDE;
import static org.geosdi.geoplatform.connector.geoserver.model.security.catalog.GPGeoserverCatalogMode.MIXED;
import static org.junit.Assert.assertTrue;
import static org.junit.runners.MethodSorters.NAME_ASCENDING;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@FixMethodOrder(NAME_ASCENDING)
public class GPGeoserverSecurityConnectorStoreTest extends GPBaseGeoserverConnectorStoreTest {

    @Test
    public void a_loadGeoserverMasterPasswordTest() throws Exception {
        GPGeoserverGetMasterPasswordRequest geoserverGetMasterPasswordRequest = geoserverConnectorStore.loadMasterPasswordRequest();
        logger.info("@@@@@@@@@@@@@@@@@@@@@@@@@@@@GEOSERVER_LOAD_MASTER_PASSWORD_RESPONSE : {}\n", geoserverGetMasterPasswordRequest.getResponse());
    }

    @Test
    public void b_loadGeoserverCatalogRequestTest() throws Exception {
        GPGeoserverGetCatalogRequest geoserverGetCatalogRequest = geoserverConnectorStore.loadCatalogRequest();
        GPGeoserverCatalog geoserverCatalog = geoserverGetCatalogRequest.getResponse();
        assertTrue(geoserverCatalog.getCatalogMode() == HIDE);
        logger.info("@@@@@@@@@@@@@@@@@@@@@@@@@@@GEOSERVER_LOAD_CATALOG_RESPONSE : {}\n", geoserverCatalog);
    }

    @Test
    public void c_updateGeoserverCatalogRequestTest() throws Exception {
        GPGeoserverUpdateCatalogRequest updateCatalogRequest = geoserverConnectorStore.updateCatalogRequest();
        updateCatalogRequest.withCatalogMode(MIXED);
        logger.info("@@@@@@@@@@@@@@@@@@@@@@@@@@GEOSERVER_UPDATE_CATALOG_RESPONSE : {}\n", updateCatalogRequest.getResponse());
        GPGeoserverGetCatalogRequest geoserverGetCatalogRequest = geoserverConnectorStore.loadCatalogRequest();
        GPGeoserverCatalog geoserverCatalog = geoserverGetCatalogRequest.getResponse();
        assertTrue(geoserverCatalog.getCatalogMode() == MIXED);
        updateCatalogRequest.withCatalogMode(HIDE);
        logger.info("@@@@@@@@@@@@@@@@@@@@@@@@@@GEOSERVER_UPDATE_CATALOG_RESPONSE : {}\n", updateCatalogRequest.getResponse());
    }
}