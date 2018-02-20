package org.geosdi.geoplatform.experimental.el.dao;

import org.elasticsearch.search.sort.SortOrder;
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
        public GPPageableAsyncElasticSearchDAO.PageAsync build() {
            return new GPPageableAsyncElasticSearchDAO.PageAsync(this.field, this.sortOrder,
                    this.from, this.size, this.operationAsyncType, this.booleanSearch);
        }

        /**
         * @return {@link GPPageAsyncBuilder}
         */
        protected <AsyncBuilder extends IGPPageAsyncBuilder> AsyncBuilder self() {
            return (AsyncBuilder) this;
        }
    }
}