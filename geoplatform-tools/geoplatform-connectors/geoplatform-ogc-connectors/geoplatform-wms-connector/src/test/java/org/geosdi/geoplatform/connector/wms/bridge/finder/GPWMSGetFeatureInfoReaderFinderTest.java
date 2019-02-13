package org.geosdi.geoplatform.connector.wms.bridge.finder;

import org.geosdi.geoplatform.connector.bridge.finder.GPWMSGetFeatureInfoReaderFinder;
import org.geosdi.geoplatform.connector.bridge.implementor.GPWMSGetFeatureInfoReader;
import org.geosdi.geoplatform.support.bridge.finder.GPImplementorFinder;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Set;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class GPWMSGetFeatureInfoReaderFinderTest {

    private static final Logger logger = LoggerFactory.getLogger(GPWMSGetFeatureInfoReaderFinderTest.class);
    //
    private static final GPImplementorFinder<GPWMSGetFeatureInfoReader<?>> finder = new GPWMSGetFeatureInfoReaderFinder<>();

    @Test
    public void a_getAllReaderImplementorsTest() throws Exception {
        Set<GPWMSGetFeatureInfoReader<?>> readerImplementors = finder.getAllImplementors();
        logger.info("##########################READER_IMPLEMENTORS : {}\n", readerImplementors);
    }

    @Test
    public void b_firstReload() throws Exception {
        finder.reload();
        logger.info("###########################{}\n", finder.getValidImplementors().size());
    }

    @Test
    public void c_getAllReaderImplementors1Test() throws Exception {
        Set<GPWMSGetFeatureInfoReader<?>> readerImplementors = finder.getAllImplementors();
        logger.info("##########################READER_IMPLEMENTORS : {}\n", readerImplementors.size());

    }

    @Test
    public void d_secondReload() throws Exception {
        finder.reload();
        logger.info("###########################{}\n", finder.getValidImplementors().size());
    }

    @Test
    public void getAllReaderImplementors2Test() throws Exception {
        Set<GPWMSGetFeatureInfoReader<?>> readerImplementors = finder.getAllImplementors();
        logger.info("##########################READER_IMPLEMENTORS : {}\n", readerImplementors.size());
    }
}