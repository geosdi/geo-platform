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
package org.geosdi.geoplatform.experimental.el.search.builder;

import org.elasticsearch.search.sort.SortOrder;
import org.geosdi.geoplatform.experimental.el.dao.GPPageableAsyncElasticSearchDAO;
import org.geosdi.geoplatform.experimental.el.search.bool.IBooleanSearch;
import org.geosdi.geoplatform.experimental.el.search.strategy.IGPOperationAsyncType;

/**
 * @author Vito Salvia - CNR IMAA geoSDI Group
 * @email vito.salvia@gmail.com
 */
public interface IGPPageAsyncBuilder {

    /**
     * @param theFrom
     * @param <AsyncBuiler>
     * @return {@link IGPPageAsyncBuilder}
     */
    <AsyncBuiler extends IGPPageAsyncBuilder> AsyncBuiler withFrom(int theFrom);

    /**
     * @param theSize
     * @param <AsyncBuilder>
     * @return {@link IGPPageAsyncBuilder}
     */
    <AsyncBuilder extends IGPPageAsyncBuilder> AsyncBuilder withSize(int theSize);

    /**
     * @param theField
     * @param theSortOrder
     * @param <AsyncBuiler>
     * @return {@link IGPPageAsyncBuilder}
     */
    <AsyncBuiler extends IGPPageAsyncBuilder> AsyncBuiler withSortOrder(String theField, SortOrder theSortOrder);

    /**
     * @param theBooleanSearch
     * @param <AsyncBuilder>
     * @return {@link IGPPageAsyncBuilder}
     */
    <AsyncBuilder extends IGPPageAsyncBuilder> AsyncBuilder withIBooleanSearch(IBooleanSearch... theBooleanSearch);

    /**
     * @return {@link GPPageableAsyncElasticSearchDAO.PageAsync}
     */
    GPPageableAsyncElasticSearchDAO.PageAsync build();

    abstract class GPPageAsyncBuilder implements IGPPageAsyncBuilder {

        protected final IGPOperationAsyncType.OperationAsyncType operationAsyncType;
        protected int from;
        protected int size;
        protected SortOrder sortOrder;
        protected IBooleanSearch[] booleanSearch;
        protected String field;

        protected GPPageAsyncBuilder(IGPOperationAsyncType.OperationAsyncType operationAsyncType) {
            this.operationAsyncType = operationAsyncType;
        }

        /**
         * @param theFrom
         * @param <AsyncBuiler>
         * @return {@link IGPPageAsyncBuilder}
         */
        @Override
        public <AsyncBuiler extends IGPPageAsyncBuilder> AsyncBuiler withFrom(int theFrom) {
            this.from = theFrom;
            return self();
        }

        /**
         * @param theSize
         * @param <AsyncBuilder>
         * @return {@link IGPPageAsyncBuilder}
         */
        @Override
        public <AsyncBuilder extends IGPPageAsyncBuilder> AsyncBuilder withSize(int theSize) {
            this.size = theSize;
            return self();
        }

        /**
         * @param theSortOrder
         * @param <AsyncBuiler>
         * @return {@link IGPPageAsyncBuilder}
         */
        @Override
        public <AsyncBuiler extends IGPPageAsyncBuilder> AsyncBuiler withSortOrder(String theField, SortOrder theSortOrder) {
            this.field = theField;
            this.sortOrder = theSortOrder;
            return self();
        }

        /**
         * @param theBooleanSearch
         * @param <AsyncBuilder>
         * @return {@link IGPPageAsyncBuilder}
         */
        @Override
        public <AsyncBuilder extends IGPPageAsyncBuilder> AsyncBuilder withIBooleanSearch(IBooleanSearch... theBooleanSearch) {
            this.booleanSearch = theBooleanSearch;
            return self();
        }

        /**
         * @return {@link GPPageableAsyncElasticSearchDAO.PageAsync }
         */
        @Override
        public abstract GPPageableAsyncElasticSearchDAO.PageAsync build();

        /**
         * @return {@link GPPageAsyncBuilder}
         */
        protected <AsyncBuilder extends IGPPageAsyncBuilder> AsyncBuilder self() {
            return (AsyncBuilder) this;
        }
    }
}