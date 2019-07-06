package org.geosdi.geoplatform.connector.connection;

import org.geosdi.geoplatform.connector.geoserver.model.connection.key.IGPGeoserverConnectionKey;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

import static org.geosdi.geoplatform.connector.geoserver.model.connection.key.db.geopkg.GPGeoserverConnectionGeoPackageValues.defaultValues;
import static org.geosdi.geoplatform.connector.geoserver.model.connection.key.db.geopkg.GPGeoserverConnectionGeoPackageValues.requiredValues;
import static org.junit.runners.MethodSorters.NAME_ASCENDING;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@FixMethodOrder(value = NAME_ASCENDING)
public class GPGeoserverConnectionGeoPackageValuesTest {

    private static final Logger logger = LoggerFactory.getLogger(GPGeoserverConnectionGeoPackageValuesTest.class);

    @Test
    public void a_geopackageRequiredValuesTest() throws Exception {
        List<IGPGeoserverConnectionKey> requiredValues = requiredValues();
        Assert.assertTrue(requiredValues.size() == 2);
        logger.info("#######################GEOPACKAGE_REQUIRED_VALUES : {}\n", requiredValues);
    }

    @Test
    public void b_geopackageDefaultValuesTest() throws Exception {
        List<IGPGeoserverConnectionKey> defaultValues = defaultValues();
        Assert.assertTrue(defaultValues.size() == 12);
        logger.info("@@@@@@@@@@@@@@@@@@@@@@@GEOPACKAGE_DEFAULT_VALUES : {}\n", defaultValues);
    }
}