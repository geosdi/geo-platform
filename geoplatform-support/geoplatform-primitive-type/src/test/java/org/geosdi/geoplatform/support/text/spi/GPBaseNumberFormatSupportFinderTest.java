package org.geosdi.geoplatform.support.text.spi;

import org.geosdi.geoplatform.support.text.spi.finder.GPBaseNumberFormatSupportFinder;
import org.geosdi.geoplatform.support.text.spi.finder.GPNumberFormatSupportFinder;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class GPBaseNumberFormatSupportFinderTest {

    private static final Logger logger = LoggerFactory.getLogger(GPBaseNumberFormatSupportFinderTest.class);
    //
    private static final GPNumberFormatSupportFinder finder = new GPBaseNumberFormatSupportFinder();

    @Test
    public void printNumberFormatSPISupportTest() throws Exception {
        GPNumberFormatSPISupport numberFormatSPISupport = finder.findNumberFormatSupport();
        Assert.assertTrue(numberFormatSPISupport.getNumberFormatSPISupportName().equals(GPBaseNumberFormatSupport.class.getSimpleName()));
    }
}
