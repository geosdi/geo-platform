package org.geosdi.geoplatform.connector.wms;

import org.geotools.referencing.CRS;
import org.junit.Test;
import org.opengis.referencing.crs.CoordinateReferenceSystem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static java.lang.Boolean.TRUE;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class SimpleTest {

    private static final Logger logger = LoggerFactory.getLogger(SimpleTest.class);

    @Test
    public void simpleTest() throws Exception {
        CoordinateReferenceSystem sourceCRS = CRS.decode("EPSG:32632", TRUE);
        logger.info("################## {}", sourceCRS);
    }
}
