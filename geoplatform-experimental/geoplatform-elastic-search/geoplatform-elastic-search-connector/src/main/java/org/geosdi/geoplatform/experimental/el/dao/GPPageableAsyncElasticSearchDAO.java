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
package org.geosdi.geoplatform.experimental.el.dao;

import net.jcip.annotations.Immutable;
import org.elasticsearch.search.sort.SortOrder;
import org.geosdi.geoplatform.experimental.el.api.model.Document;
import org.geosdi.geoplatform.experimental.el.search.bool.IBooleanSearch;
import org.geosdi.geoplatform.experimental.el.search.operation.OperationByPage;
import org.geosdi.geoplatform.experimental.el.search.strategy.IGPOperationAsyncType.OperationAsyncType;

import javax.annotation.Nonnull;
import java.util.concurrent.CompletableFuture;

import static com.google.common.base.Preconditions.checkNotNull;
import static javax.annotation.meta.When.NEVER;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public interface GPPageableAsyncElasticSearchDAO<D extends Document> extends GPPageableElasticSearchDAO<D> {

    /**
     * @param page
     * @param <P>
     * @return {@link org.elasticsearch.action.bulk.BulkResponse}
     * @throws Exception
     */
    <Result extends OperationByPage.IOperationByPageResult, P extends PageAsync> Result operationByPage(P page) throws Exception;

    /**
     * @param page
     * @param <P>
     * @return {@link CompletableFuture<org.elasticsearch.action.bulk.BulkResponse>}
     * @throws Exception
     */
    <Result extends OperationByPage.IOperationByPageResult, P extends PageAsync> CompletableFuture<Result> operationByPageAsync(P page) throws Exception;

    /**
     *
     */
    @Immutable
    class PageAsync extends GPPageableElasticSearchDAO.MultiFieldsSearch {

        private final OperationAsyncType operationType;
        private GPElasticSearchUpdateHandler updateHandler;

        /**
         * @param theField
         * @param theSortOrder
         * @param theFrom
         * @param theSize
         * @param theOperationType
         * @param theQueryList
         */
        protected PageAsync(String theField, SortOrder theSortOrder, int theFrom, int theSize, @Nonnull(when = NEVER) OperationAsyncType theOperationType, IBooleanSearch... theQueryList) {
            super(theField, theSortOrder, theFrom, theSize, theQueryList);
            checkNotNull("The Operation Type must not be null", theOperationType != null);
            this.operationType = theOperationType;
        }

        /**
         * <p>
         * The Parameter {@link GPElasticSearchUpdateHandler} is optional and must be specified only
         * if {@link OperationAsyncType} type is {@link OperationAsyncType#UPDATE}.
         * </p>
         *
         * @param theFrom
         * @param theSize
         * @param theOperationType
         * @param theUpdateHandler
         */
        protected PageAsync(String theField, SortOrder theSortOrder, int theFrom, int theSize,
                         @Nonnull(when = NEVER) OperationAsyncType theOperationType, GPElasticSearchUpdateHandler theUpdateHandler,
                         IBooleanSearch... theQueryList) {
            this(theField, theSortOrder, theFrom, theSize, theOperationType, theQueryList);
            this.updateHandler = theUpdateHandler;
        }

        /**
         * @return {@link OperationAsyncType}
         */
        public OperationAsyncType getOperationType() {
            return operationType;
        }

        /**
         * @return {@link GPElasticSearchUpdateHandler}
         */
        public GPElasticSearchUpdateHandler getUpdateHandler() {
            return updateHandler;
        }
    }
}
