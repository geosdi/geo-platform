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
     * @param theSortOrder
     * @param <AsyncBuiler>
     * @return {@link IGPPageAsyncBuilder}
     */
    <AsyncBuiler extends IGPPageAsyncBuilder> AsyncBuiler withSortOrder(SortOrder theSortOrder);

    /**
     * @param theIBooleanSearch
     * @param <AsyncBuilder>
     * @return {@link IGPPageAsyncBuilder}
     */
    <AsyncBuilder extends IGPPageAsyncBuilder> AsyncBuilder withIBooleanSearch(IBooleanSearch... theIBooleanSearch);

    /**
     * @param theField
     * @param <AsyncBuilder>
     * @return {@link IGPPageAsyncBuilder}
     */
    <AsyncBuilder extends IGPPageAsyncBuilder> AsyncBuilder withField(String theField);

    /**
     * @return {@link GPPageableAsyncElasticSearchDAO.PageAsync}
     */
    GPPageableAsyncElasticSearchDAO.PageAsync build();

    abstract class GPPageAsyncBuilder implements IGPPageAsyncBuilder {

        protected final IGPOperationAsyncType.OperationAsyncType operationAsyncType;
        protected int from;
        protected int size;
        protected SortOrder sortOrder;
        protected IBooleanSearch[] iBooleanSearch;
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
        public <AsyncBuiler extends IGPPageAsyncBuilder> AsyncBuiler withSortOrder(SortOrder theSortOrder) {
            this.sortOrder = theSortOrder;
            return self();
        }

        /**
         * @param theIBooleanSearch
         * @param <AsyncBuilder>
         * @return {@link IGPPageAsyncBuilder}
         */
        @Override
        public <AsyncBuilder extends IGPPageAsyncBuilder> AsyncBuilder withIBooleanSearch(IBooleanSearch... theIBooleanSearch) {
            this.iBooleanSearch = theIBooleanSearch;
            return self();
        }

        /**
         * @param theField
         * @param <AsyncBuilder>
         * @return {@link IGPPageAsyncBuilder}
         */
        @Override
        public <AsyncBuilder extends IGPPageAsyncBuilder> AsyncBuilder withField(String theField) {
            this.field = theField;
            return self();
        }

        /**
         * @return {@link GPPageableAsyncElasticSearchDAO.PageAsync }
         */
        @Override
        public GPPageableAsyncElasticSearchDAO.PageAsync build() {
            return new GPPageableAsyncElasticSearchDAO.PageAsync(this.field, this.sortOrder,
                    this.from, this.size, this.operationAsyncType, this.iBooleanSearch);
        }

        /**
         * @return {@link GPPageAsyncBuilder}
         */
        protected <AsyncBuilder extends IGPPageAsyncBuilder> AsyncBuilder self() {
            return (AsyncBuilder) this;
        }
    }
}
