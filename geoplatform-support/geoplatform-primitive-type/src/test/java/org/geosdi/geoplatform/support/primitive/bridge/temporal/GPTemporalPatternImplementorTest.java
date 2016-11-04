package org.geosdi.geoplatform.support.primitive.bridge.temporal;

import org.geosdi.geoplatform.support.primitive.string.responsibility.bridge.GPBaseTemporalImplementorFinder;
import org.geosdi.geoplatform.support.primitive.string.responsibility.bridge.GPTemporalImplementorFinder;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class GPTemporalPatternImplementorTest {

    private static final Logger logger = LoggerFactory.getLogger(GPTemporalPatternImplementorTest.class);
    //
    private static final GPTemporalImplementorFinder TEMPORAL_IMPLEMENTOR_FINDER = new GPBaseTemporalImplementorFinder();

    @Test
    public void temporalPatternTest() {
        logger.info("############################FOUND TEMPORAL_IMPLEMENTOR : {}\n",
                TEMPORAL_IMPLEMENTOR_FINDER.findTemporalImplementor());
    }
}
