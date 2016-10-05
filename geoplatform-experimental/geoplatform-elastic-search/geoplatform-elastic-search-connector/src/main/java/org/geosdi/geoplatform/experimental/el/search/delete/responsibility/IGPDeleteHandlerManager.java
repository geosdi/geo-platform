package org.geosdi.geoplatform.experimental.el.search.delete.responsibility;

import org.geosdi.geoplatform.experimental.el.dao.ElasticSearchDAO;
import org.geosdi.geoplatform.experimental.el.search.delete.DeleteByPage;
import org.geosdi.geoplatform.experimental.el.search.delete.DeleteByPage.IDeleteByPageResult;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public interface IGPDeleteHandlerManager {

    /**
     * @param page
     * @param searchDAO
     * @param <Result>
     * @param <Page>
     * @return {@link Result}
     * @throws Exception
     */
    <Result extends IDeleteByPageResult, Page extends DeleteByPage> Result delete(Page page,
            ElasticSearchDAO searchDAO) throws Exception;

    /**
     *
     */
    class GPDeleteHandlerManager implements IGPDeleteHandlerManager {

        private final GPAbstractDeleteHandler<ElasticSearchDAO> scrollDeleteHandler = new GPPreparerDeleteHandler();

        /**
         * @param page
         * @param searchDAO
         * @return {@link Result}
         * @throws Exception
         */
        @Override
        public <Result extends IDeleteByPageResult, Page extends DeleteByPage> Result delete(Page page, ElasticSearchDAO searchDAO) throws Exception {
            return this.scrollDeleteHandler.delete(page, searchDAO);
        }
    }
}
