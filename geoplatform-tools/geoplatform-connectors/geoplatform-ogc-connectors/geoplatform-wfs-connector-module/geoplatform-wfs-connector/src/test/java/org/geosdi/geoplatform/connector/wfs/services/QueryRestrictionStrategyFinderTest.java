package org.geosdi.geoplatform.connector.wfs.services;

import org.geosdi.geoplatform.connector.server.request.v110.query.repository.QueryRestrictionStrategy;
import org.geosdi.geoplatform.connector.server.request.v110.query.repository.QueryRestrictionsStrategyFinder;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Set;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class QueryRestrictionStrategyFinderTest {

    private static final Logger logger = LoggerFactory.getLogger(QueryRestrictionStrategyFinderTest.class);

    @Test
    public void queryRestrictionsAllStrategyTest() {
        Set<QueryRestrictionStrategy> strategies = QueryRestrictionsStrategyFinder.getAllStrategies();

        logger.info("#########################QUERY_RESTRICTIONS_ALL_STRATEGIES : {}\n", strategies);

        Assert.assertEquals(11, strategies.size());
    }

    @Test
    public void queryRestrictionsValidStrategyTest() {
        Set<QueryRestrictionStrategy> strategies = QueryRestrictionsStrategyFinder.getValidStrategies();

        logger.info("#########################QUERY_RESTRICTIONS_VALID_STRATEGIES : {}\n", strategies);

        Assert.assertEquals(10, strategies.size());
    }
}
