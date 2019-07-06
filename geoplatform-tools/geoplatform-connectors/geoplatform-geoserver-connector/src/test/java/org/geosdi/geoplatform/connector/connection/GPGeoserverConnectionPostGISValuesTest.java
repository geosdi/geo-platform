package org.geosdi.geoplatform.connector.connection;

import org.geosdi.geoplatform.connector.geoserver.model.connection.key.IGPGeoserverConnectionKey;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

import static org.geosdi.geoplatform.connector.geoserver.model.connection.key.db.postgis.GPGeoserverConnectionPostGISValues.defaultValues;
import static org.geosdi.geoplatform.connector.geoserver.model.connection.key.db.postgis.GPGeoserverConnectionPostGISValues.requiredValues;
import static org.junit.runners.MethodSorters.NAME_ASCENDING;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@FixMethodOrder(value = NAME_ASCENDING)
public class GPGeoserverConnectionPostGISValuesTest {

    private static final Logger logger = LoggerFactory.getLogger(GPGeoserverConnectionPostGISValuesTest.class);

    @Test
    public void a_postgisRequiredValuesTest() throws Exception {
        List<IGPGeoserverConnectionKey> requiredValues = requiredValues();
        Assert.assertTrue(requiredValues.size() == 4);
        logger.info("@@@@@@@@@@@@@@@@@@@@@@@@POSTGIS_REQUIRED_VALUES : {}\n", requiredValues);
    }

    @Test
    public void b_postgisDefaultValuesTest() throws Exception {
        List<IGPGeoserverConnectionKey> defaultValues = defaultValues();
        Assert.assertTrue(defaultValues.size() == 23);
        logger.info("@@@@@@@@@@@@@@@@@@@@@@@@@POSTGIS_DEFAULT_VALUES : {}\n", defaultValues);
    }
}