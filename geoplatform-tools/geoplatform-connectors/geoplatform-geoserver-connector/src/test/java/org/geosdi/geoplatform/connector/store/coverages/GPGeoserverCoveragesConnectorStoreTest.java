package org.geosdi.geoplatform.connector.store.coverages;

import org.geosdi.geoplatform.connector.geoserver.request.workspaces.coverages.GeoserverLoadCoverageRequest;
import org.geosdi.geoplatform.connector.geoserver.request.workspaces.coverages.GeoserverLoadCoveragesRequest;
import org.geosdi.geoplatform.connector.store.GPBaseGeoserverConnectorStoreTest;
import org.junit.FixMethodOrder;
import org.junit.Test;

import static org.junit.runners.MethodSorters.NAME_ASCENDING;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@FixMethodOrder(NAME_ASCENDING)
public class GPGeoserverCoveragesConnectorStoreTest extends GPBaseGeoserverConnectorStoreTest {

    @Test
    public void a_loadWorkspaceCoveragesTest() throws Exception {
        GeoserverLoadCoveragesRequest loadCoveragesRequest = geoserverConnectorStore.loadWorkspaceCoveragesRequest();
        loadCoveragesRequest.withWorkspace("topp");
        logger.info("########################WORKSPACE_COVERAGES_RESPONSE : {}\n", loadCoveragesRequest.getResponse());
    }

    @Test
    public void b_loadAllCoveragesTest() throws Exception {
        GeoserverLoadCoveragesRequest loadCoveragesRequest = geoserverConnectorStore.loadWorkspaceCoveragesRequest();
        loadCoveragesRequest.withWorkspace("topp").withQueryList("ALL");
        logger.info("########################ALL_COVERAGES_RESPONSE : {}\n", loadCoveragesRequest.getResponse());
    }

    @Test
    public void c_loadCoverageTest() throws Exception {
        GeoserverLoadCoverageRequest loadCoverageRequest = geoserverConnectorStore.loadWorkspaceCoverageRequest();
        loadCoverageRequest.withWorkspace("sf").withCoverage("sfdem");
        logger.info("#######################LOAD_WORKSPACE_COVERAGE_RESPONSE : {}\n", loadCoverageRequest.getResponse());
    }
}