package org.geosdi.geoplatform.experimental.el.search.builder;

import org.geosdi.geoplatform.experimental.el.dao.GPElasticSearchUpdateHandler;
import org.geosdi.geoplatform.experimental.el.dao.GPPageableAsyncElasticSearchDAO;
import org.geosdi.geoplatform.experimental.el.search.strategy.IGPOperationAsyncType;

/**
 * @author Vito Salvia - CNR IMAA geoSDI Group
 * @email vito.salvia@gmail.com
 */
public interface IGPUpdatePageAsyncBuilder extends IGPPageAsyncBuilder {

    /**
     * @param theUpdatehandler
     * @param <UpdateAsyncBuilder>
     * @return {@link IGPUpdatePageAsyncBuilder}
     */
    <UpdateAsyncBuilder extends IGPUpdatePageAsyncBuilder> UpdateAsyncBuilder withUpdateHandler(GPElasticSearchUpdateHandler theUpdatehandler);

    class GPUpdatePageAsyncBuilder extends GPPageAsyncBuilder implements IGPUpdatePageAsyncBuilder {

        private GPElasticSearchUpdateHandler updateHandler;

        private GPUpdatePageAsyncBuilder() {
            super(IGPOperationAsyncType.OperationAsyncType.UPDATE);
        }

        public static <UpdateAsyncBuilder extends IGPUpdatePageAsyncBuilder> UpdateAsyncBuilder gpDeletePageAsyncBuilder() {
            return (UpdateAsyncBuilder) new GPUpdatePageAsyncBuilder();
        }

        /**
         * @param theUpdatehandler
         * @param <UpdateAsyncBuilder>
         * @return {@link IGPUpdatePageAsyncBuilder}
         */
        @Override
        public <UpdateAsyncBuilder extends IGPUpdatePageAsyncBuilder> UpdateAsyncBuilder withUpdateHandler(GPElasticSearchUpdateHandler theUpdatehandler) {
            this.updateHandler = theUpdatehandler;
            return self();
        }

        /**
         * @return {@link GPPageableAsyncElasticSearchDAO.PageAsync }
         */
        @Override
        public GPPageableAsyncElasticSearchDAO.PageAsync build() {
            return new GPPageableAsyncElasticSearchDAO.PageAsync(this.field, this.sortOrder,
                    this.from, this.size, this.operationAsyncType, this.updateHandler, this.iBooleanSearch);
        }

    }


}
