package org.geosdi.geoplatform.connector.wfs.services;

import org.geosdi.geoplatform.connector.server.request.v110.query.repository.QueryRestrictionRepository;
import org.geosdi.geoplatform.connector.server.request.v110.query.repository.QueryRestrictionStrategy;
import org.geosdi.geoplatform.connector.server.request.v110.query.repository.WFSQueryRestrictionRepository;
import org.geosdi.geoplatform.xml.filter.v110.ComparisonOpsType;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.geosdi.geoplatform.gui.shared.wfs.OperatorType.*;
import static org.junit.runners.MethodSorters.NAME_ASCENDING;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@FixMethodOrder(value = NAME_ASCENDING)
public class WFSQueryRestrictionRepositoryTest {

    private static final Logger logger = LoggerFactory.getLogger(WFSQueryRestrictionRepositoryTest.class);
    //
    private static final WFSQueryRestrictionRepository store = new QueryRestrictionRepository();

    @Test
    public void a_loadQueryRestrictionStrategyAsContainsTest() throws Exception {
        QueryRestrictionStrategy<ComparisonOpsType> containsStrategy = store.getQueryRestrictionStrategy(CONTAINS);
        Assert.assertNotNull(containsStrategy);
        logger.info("################################CONTAINS_STRATEGY : {}\n", containsStrategy);
    }

    @Test
    public void b_loadQueryRestrictionStrategyAsEndsWithTest() throws Exception {
        QueryRestrictionStrategy<ComparisonOpsType> endsWithStrategy = store.getQueryRestrictionStrategy(ENDS_WITH);
        Assert.assertNotNull(endsWithStrategy);
        logger.info("################################ENDS_STRATEGY : {}\n", endsWithStrategy);
    }

    @Test
    public void c_loadQueryRestrictionStrategyAsEqualTest() throws Exception {
        QueryRestrictionStrategy<ComparisonOpsType> equalStrategy = store.getQueryRestrictionStrategy(EQUAL);
        Assert.assertNotNull(equalStrategy);
        logger.info("################################EQUAL_STRATEGY : {}\n", equalStrategy);
    }

    @Test
    public void d_loadQueryRestrictionStrategyAsGreaterTest() throws Exception {
        QueryRestrictionStrategy<ComparisonOpsType> greaterStrategy = store.getQueryRestrictionStrategy(GREATER);
        Assert.assertNotNull(greaterStrategy);
        logger.info("################################GREATER_STRATEGY : {}\n", greaterStrategy);
    }

    @Test
    public void e_loadQueryRestrictionStrategyAsGreaterOrEqualTest() throws Exception {
        QueryRestrictionStrategy<ComparisonOpsType> greaterOrEqualStrategy = store.getQueryRestrictionStrategy(GREATER_OR_EQUAL);
        Assert.assertNotNull(greaterOrEqualStrategy);
        logger.info("################################GREATER_OR_EQUAL_STRATEGY : {}\n", greaterOrEqualStrategy);
    }

    @Test
    public void f_loadQueryRestrictionStrategyAsLessTest() throws Exception {
        QueryRestrictionStrategy<ComparisonOpsType> lessStrategy = store.getQueryRestrictionStrategy(LESS);
        Assert.assertNotNull(lessStrategy);
        logger.info("################################LESS_STRATEGY : {}\n", lessStrategy);
    }

    @Test
    public void g_loadQueryRestrictionStrategyAsLessOrEqualTest() throws Exception {
        QueryRestrictionStrategy<ComparisonOpsType> lessOrEqualStrategy = store.getQueryRestrictionStrategy(LESS_OR_EQUAL);
        Assert.assertNotNull(lessOrEqualStrategy);
        logger.info("################################LESS_OR_EQUAL_STRATEGY : {}\n", lessOrEqualStrategy);
    }

    @Test
    public void h_loadQueryRestrictionStrategyAsStartsWithTest() throws Exception {
        QueryRestrictionStrategy<ComparisonOpsType> startsWithStrategy = store.getQueryRestrictionStrategy(STARTS_WITH);
        Assert.assertNotNull(startsWithStrategy);
        logger.info("################################STARTS_WITH_STRATEGY : {}\n", startsWithStrategy);
    }

    @Test
    public void i_loadQueryRestrictionStrategyAsNotEqualTest() throws Exception {
        QueryRestrictionStrategy<ComparisonOpsType> notEqualStrategy = store.getQueryRestrictionStrategy(NOT_EQUAL);
        Assert.assertNotNull(notEqualStrategy);
        logger.info("################################NOT_EQUAL_STRATEGY : {}\n", notEqualStrategy);
    }

    @Test
    public void l_loadQueryRestrictionStrategyAsLikeTest() throws Exception {
        QueryRestrictionStrategy<ComparisonOpsType> likeStrategy = store.getQueryRestrictionStrategy(LIKE);
        Assert.assertNotNull(likeStrategy);
        logger.info("################################LIKE_STRATEGY : {}\n", likeStrategy);
    }
}