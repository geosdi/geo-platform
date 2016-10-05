package org.geosdi.geoplatform.experimental.el.search.delete;

import com.google.common.base.Preconditions;
import net.jcip.annotations.Immutable;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.common.unit.TimeValue;
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
     * @return {@link Integer}
     */
    Integer getFrom();

    /**
     * @return {@link Integer}
     */
    Integer getSize();

    /**
     * @param <P>
     * @return {@link P}
     */
    <P extends GPPageableElasticSearchDAO.Page> P getPage();

    /**
     *
     */
    interface IDeleteByPageResult {

        /**
         * <p>The total Number of Elements deleted</p>
         *
         * @return {@link Integer}
         */
        Integer getElementsDeleted();

        /**
         * @return {@link TimeValue}
         */
        TimeValue getExecutionTime();
    }

    /**
     *
     */
    @Immutable
    class DeleteByPageSearch implements DeleteByPage {

        private final GPPageableElasticSearchDAO.Page page;

        public DeleteByPageSearch(GPPageableElasticSearchDAO.Page thePage) {
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

        /**
         * @return {@link Integer}
         */
        @Override
        public Integer getFrom() {
            return this.page.getFrom();
        }

        /**
         * @return {@link Integer}
         */
        @Override
        public Integer getSize() {
            return this.page.getSize();
        }

        /**
         * @return {@link P}
         */
        @Override
        public <P extends GPPageableElasticSearchDAO.Page> P getPage() {
            return (P) this.page;
        }
    }

    /**
     *
     */
    @Immutable
    class DeleteByPageSearchDecorator extends DeleteByPageSearch {

        private final Long size;

        public DeleteByPageSearchDecorator(GPPageableElasticSearchDAO.Page thePage, Long size) {
            super(thePage);
            this.size = size;
        }

        /**
         * @return {@link Integer}
         */
        @Override
        public Integer getSize() {
            return this.size.intValue();
        }
    }

    /**
     *
     */
    @Immutable
    class DeleteByPageResult implements IDeleteByPageResult {

        private final Integer elementsDeleted;
        private final TimeValue executionTime;

        public DeleteByPageResult(Integer theElementsDeleted, TimeValue theExecutionTime) {
            this.elementsDeleted = theElementsDeleted;
            this.executionTime = theExecutionTime;
        }

        /**
         * @return {@link Integer}
         */
        @Override
        public Integer getElementsDeleted() {
            return this.elementsDeleted;
        }

        /**
         * @return {@link TimeValue}
         */
        @Override
        public TimeValue getExecutionTime() {
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