package org.geosdi.geoplatform.connector.wfs;

import org.geosdi.geoplatform.connector.WFSVersion;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.geosdi.geoplatform.connector.server.request.v110.query.responsibility.ILogicOperatorHandler.QUERY_RESTRICTION_REPOSITORY;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class WFSVersionTest {

    private static final Logger logger = LoggerFactory.getLogger(WFSVersionTest.class);

    @Test
    public void v110VersionTest() {
        logger.info("############################FOUND VERSION : {}\n", WFSVersion.fromString("1.1.0"));
    }

    @Test
    public void v200VersionTest() {
        logger.info("############################FOUND VERSION : {}\n", WFSVersion.fromString("2.0.0"));
    }

    @Test
    public void wfsIncorrectVersionTest() {
        Assert.assertTrue(WFSVersion.fromString("1.0.0").equals(WFSVersion.V110));
    }

    @Test
    public void simpleTEst() {
        logger.info("#########################{}\n", QUERY_RESTRICTION_REPOSITORY);
    }
}
