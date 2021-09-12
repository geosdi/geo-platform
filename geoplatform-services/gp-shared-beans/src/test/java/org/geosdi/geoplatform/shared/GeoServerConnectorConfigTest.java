package org.geosdi.geoplatform.shared;

import org.geosdi.geoplatform.connector.store.GPGeoserverConnectorStore;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author Vito Salvia - CNR IMAA geoSDI Group
 * @email vito.salvia@gmail.com
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext-GeoServerConnector-Test.xml"})
public class GeoServerConnectorConfigTest {

    @Autowired
    private GPGeoserverConnectorStore geoserverConnectorStore;

    @Before
    public void setUp() throws Exception {
        Assert.assertNotNull(this.geoserverConnectorStore);
    }

    @Test
    public void testConnection() {

    }

}
