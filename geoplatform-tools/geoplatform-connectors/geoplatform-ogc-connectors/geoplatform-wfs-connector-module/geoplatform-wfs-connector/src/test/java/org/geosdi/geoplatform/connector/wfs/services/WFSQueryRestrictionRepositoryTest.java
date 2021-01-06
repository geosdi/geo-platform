/**
 *
 *    geo-platform
 *    Rich webgis framework
 *    http://geo-platform.org
 *   ====================================================================
 *
 *   Copyright (C) 2008-2021 geoSDI Group (CNR IMAA - Potenza - ITALY).
 *
 *   This program is free software: you can redistribute it and/or modify it
 *   under the terms of the GNU General Public License as published by
 *   the Free Software Foundation, either version 3 of the License, or
 *   (at your option) any later version. This program is distributed in the
 *   hope that it will be useful, but WITHOUT ANY WARRANTY; without
 *   even the implied warranty of MERCHANTABILITY or FITNESS FOR
 *   A PARTICULAR PURPOSE. See the GNU General Public License
 *   for more details. You should have received a copy of the GNU General
 *   Public License along with this program. If not, see http://www.gnu.org/licenses/
 *
 *   ====================================================================
 *
 *   Linking this library statically or dynamically with other modules is
 *   making a combined work based on this library. Thus, the terms and
 *   conditions of the GNU General Public License cover the whole combination.
 *
 *   As a special exception, the copyright holders of this library give you permission
 *   to link this library with independent modules to produce an executable, regardless
 *   of the license terms of these independent modules, and to copy and distribute
 *   the resulting executable under terms of your choice, provided that you also meet,
 *   for each linked independent module, the terms and conditions of the license of
 *   that module. An independent module is a module which is not derived from or
 *   based on this library. If you modify this library, you may extend this exception
 *   to your version of the library, but you are not obligated to do so. If you do not
 *   wish to do so, delete this exception statement from your version.
 */
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