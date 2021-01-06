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
package org.geosdi.geoplatform.experimental.el.search.operation.responsibility;

import org.geosdi.geoplatform.experimental.el.dao.ElasticSearchDAO;
import org.geosdi.geoplatform.experimental.el.search.operation.OperationByPage;
import org.geosdi.geoplatform.experimental.el.search.operation.OperationByPage.IOperationByPageResult;
import org.geosdi.geoplatform.experimental.el.search.strategy.GPStrategyRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.google.common.base.Preconditions.checkArgument;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
abstract class GPAbstractOperationHandler<SearchDAO extends ElasticSearchDAO>
        implements GPElasticSearchOperationHandler<SearchDAO> {

    protected final Logger logger = LoggerFactory.getLogger(this.getClass());
    protected final GPStrategyRepository strategyRepository;
    protected GPAbstractOperationHandler<SearchDAO> successor;

    public GPAbstractOperationHandler(GPStrategyRepository thsStrategyRepository) {
        checkArgument(thsStrategyRepository != null, "The Parameter theStrategyRepository must not be null");
        this.strategyRepository = thsStrategyRepository;
    }

    /**
     * @param page
     * @param searchDAO
     * @param <Result>
     * @param <Page>
     * @return {@link Result}
     * @throws Exception
     */
    protected <Result extends IOperationByPageResult, Page extends OperationByPage> Result forwardOperation(Page page,
            SearchDAO searchDAO) throws Exception {
        if (successor != null) {
            return successor.operation(page, searchDAO);
        }
        logger.error("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@There are no Successor to manage Delete Operation for " +
                "DeletePage : {}\n", page);
        throw new IllegalStateException("No Elements in Chain for manage Delete Operation.");
    }

    /**
     * @param page
     * @param <Page>
     * @return {@link Boolean}
     */
    protected abstract <Page extends OperationByPage> Boolean canDoOperation(Page page);

    /**
     * @param page
     * @param searchDAO
     * @param <Result>
     * @param <Page>
     * @return {@link Result}
     * @throws Exception
     */
    protected abstract <Result extends IOperationByPageResult, Page extends OperationByPage> Result internalOperation(Page page,
            SearchDAO searchDAO) throws Exception;

    /**
     * @param theSuccessor
     */
    @Override
    public <Successor extends GPElasticSearchOperationHandler<SearchDAO>> void setSuccessor(Successor theSuccessor) {
        this.successor = (GPAbstractOperationHandler<SearchDAO>) theSuccessor;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "{" +
                " deleteType = " + this.getOperationType() +
                "}";
    }
}
