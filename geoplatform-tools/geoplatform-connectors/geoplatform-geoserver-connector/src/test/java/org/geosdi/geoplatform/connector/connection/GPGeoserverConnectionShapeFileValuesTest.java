package org.geosdi.geoplatform.connector.connection;

import org.geosdi.geoplatform.connector.geoserver.model.connection.key.IGPGeoserverConnectionKey;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

import static org.geosdi.geoplatform.connector.geoserver.model.connection.key.file.shape.GPGeoserverConnectionShapeFileValues.defaultValues;
import static org.geosdi.geoplatform.connector.geoserver.model.connection.key.file.shape.GPGeoserverConnectionShapeFileValues.requiredValues;
import static org.junit.runners.MethodSorters.NAME_ASCENDING;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@FixMethodOrder(value = NAME_ASCENDING)
public class GPGeoserverConnectionShapeFileValuesTest {

    private static final Logger logger = LoggerFactory.getLogger(GPGeoserverConnectionShapeFileValuesTest.class);

    @Test
    public void a_shapeFileRequiredValuesTest() throws Exception {
        List<IGPGeoserverConnectionKey> requiredValues = requiredValues();
        Assert.assertTrue(requiredValues.size() == 1);
        logger.info("#########################SHAPE_FILE_REQUIRED_VALUES : {}\n", requiredValues);
    }

    @Test
    public void b_shapeFileDefaultValuesTest() throws Exception {
        List<IGPGeoserverConnectionKey> defaultValues = defaultValues();
        Assert.assertTrue(defaultValues.size() == 6);
        logger.info("#########################SHAPE_FILE_DEFAULT_VALUES : {}\n", defaultValues);
    }
}