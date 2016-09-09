package org.geosdi.geoplatform.experimental.el.search.delete;

import com.google.common.base.Preconditions;
import net.jcip.annotations.Immutable;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.geosdi.geoplatform.experimental.el.dao.GPPageableElasticSearchDAO;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public interface DeleteByPage {

    /**
     * @param builder
     * @param <Builder>
     * @return
     * @throws Exception
     */
    <Builder extends SearchRequestBuilder> Builder buildPage(Builder builder)
            throws Exception;

    /**
     *
     */
    interface IDeleteByPageResult {

        /**
         * <p>The total Number of Elements deleted</p>
         *
         * @return {@link Long}
         */
        Long getElementsDeleted();

        /**
         * @return {@link Long}
         */
        Long getExecutionTime();
    }

    /**
     *
     */
    @Immutable
    class DeleteByPageSearch implements DeleteByPage {

        private final GPPageableElasticSearchDAO.Page page;

        protected DeleteByPageSearch(GPPageableElasticSearchDAO.Page thePage) {
            Preconditions.checkNotNull(thePage, "The Parameter Page must not be null.");
            this.page = thePage;
        }

        /**
         * @param builder
         * @return
         * @throws Exception
         */
        @Override
        public <Builder extends SearchRequestBuilder> Builder buildPage(Builder builder) throws Exception {
            return this.page.buildPage(builder);
        }
    }

    /**
     *
     */
    @Immutable
    class DeleteByPageResult implements IDeleteByPageResult {

        private final Long elementsDeleted;
        private final Long executionTime;

        public DeleteByPageResult(Long theElementsDeleted, Long theExecutionTime) {
            this.elementsDeleted = theElementsDeleted;
            this.executionTime = theExecutionTime;
        }

        /**
         * @return {@link Long}
         */
        @Override
        public Long getElementsDeleted() {
            return this.elementsDeleted;
        }

        /**
         * @return {@link Long}
         */
        @Override
        public Long getExecutionTime() {
            return this.executionTime;
        }

        @Override
        public String toString() {
            return getClass().getSimpleName() + " {" +
                    " elementsDeleted = " + elementsDeleted +
                    ", executionTime = " + executionTime +
                    '}';
        }
    }
}