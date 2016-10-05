package org.geosdi.geoplatform.experimental.el.search.delete.responsibility;

import org.geosdi.geoplatform.experimental.el.dao.ElasticSearchDAO;
import org.geosdi.geoplatform.experimental.el.search.delete.DeleteByPage;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public interface GPElasticSearchDeleteHandler<SearchDAO extends ElasticSearchDAO> {

    /**
     * @param page
     * @param searchDAO
     * @param <Result>
     * @param <Page>
     * @return {@link Result}
     * @throws Exception
     */
    <Result extends DeleteByPage.IDeleteByPageResult, Page extends DeleteByPage> Result delete(Page page,
            SearchDAO searchDAO) throws Exception;

    /**
     * @param theSuccessor
     * @param <Successor>
     */
    <Successor extends GPElasticSearchDeleteHandler<SearchDAO>> void setSuccessor(Successor theSuccessor);

    /**
     * @param <TYPE>
     * @return {@link TYPE}
     */
    <TYPE extends IGPElasticSearchDeleteHandlerType> TYPE getDeleteType();

    /**
     *
     */
    interface IGPElasticSearchDeleteHandlerType {

        /**
         * @return {@link String}
         */
        String getType();
    }

    /**
     *
     */
    enum GPElasticSearchDeleteHandlerType implements IGPElasticSearchDeleteHandlerType {

        PREPARER_DELETE_TYPE("PREPARER_DELETE_TYPE_HANDLER"),
        NOT_PARALLEL_DELETE_TYPE("NOT_PARALLEL_DELETE_TYPE_HANDLER"),
        PARALLEL_DELETE_TYPE("PARALLEL_DELETE_TYPE_HANDLER");

        private final String value;

        GPElasticSearchDeleteHandlerType(String value) {
            this.value = value;
        }


        /**
         * @return {@link String}
         */
        @Override
        public String getType() {
            return this.value;
        }

        @Override
        public String toString() {
            return this.value;
        }
    }
}
