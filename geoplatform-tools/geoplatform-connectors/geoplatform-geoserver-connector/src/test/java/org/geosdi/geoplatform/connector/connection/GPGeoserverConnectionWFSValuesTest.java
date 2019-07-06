package org.geosdi.geoplatform.connector.connection;

import org.geosdi.geoplatform.connector.geoserver.model.connection.key.IGPGeoserverConnectionKey;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

import static org.geosdi.geoplatform.connector.geoserver.model.connection.key.wfs.GPGeoserverConnectionWFSValues.defaultValues;
import static org.geosdi.geoplatform.connector.geoserver.model.connection.key.wfs.GPGeoserverConnectionWFSValues.requiredValues;
import static org.junit.runners.MethodSorters.NAME_ASCENDING;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@FixMethodOrder(value = NAME_ASCENDING)
public class GPGeoserverConnectionWFSValuesTest {

    private static final Logger logger = LoggerFactory.getLogger(GPGeoserverConnectionWFSValuesTest.class);

    @Test
    public void a_wfsRequiredValuesTest() throws Exception {
        List<IGPGeoserverConnectionKey> requiredValues = requiredValues();
        Assert.assertTrue(requiredValues.size() == 1);
        logger.info("#########################WFS_REQUIRED_VALUES : {}\n", requiredValues);
    }

    @Test
    public void b_wfsDefaultValuesTest() throws Exception {
        List<IGPGeoserverConnectionKey> defaultValues = defaultValues();
        Assert.assertTrue(defaultValues.size() == 14);
        logger.info("#########################WFS_DEFAULT_VALUES : {}\n", defaultValues);
    }
}