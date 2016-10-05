package org.geosdi.geoplatform.experimental.el.search.delete.responsibility;

import org.geosdi.geoplatform.experimental.el.dao.ElasticSearchDAO;
import org.geosdi.geoplatform.experimental.el.search.delete.DeleteByPage;
import org.geosdi.geoplatform.experimental.el.search.delete.DeleteByPage.IDeleteByPageResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
abstract class GPAbstractDeleteHandler<SearchDAO extends ElasticSearchDAO>
        implements GPElasticSearchDeleteHandler<SearchDAO> {

    protected final Logger logger = LoggerFactory.getLogger(this.getClass());
    protected GPAbstractDeleteHandler<SearchDAO> successor;

    /**
     * @param page
     * @param searchDAO
     * @param <Result>
     * @param <Page>
     * @return {@link Result}
     * @throws Exception
     */
    protected <Result extends IDeleteByPageResult, Page extends DeleteByPage> Result forwardDelete(Page page,
            SearchDAO searchDAO) throws Exception {
        if (successor != null) {
            return successor.delete(page, searchDAO);
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
    protected abstract <Page extends DeleteByPage> Boolean canDelete(Page page);

    /**
     * @param page
     * @param searchDAO
     * @param <Result>
     * @param <Page>
     * @return {@link Result}
     * @throws Exception
     */
    protected abstract <Result extends IDeleteByPageResult, Page extends DeleteByPage> Result internalDelete(Page page,
            SearchDAO searchDAO) throws Exception;

    /**
     * @param theSuccessor
     */
    @Override
    public <Successor extends GPElasticSearchDeleteHandler<SearchDAO>> void setSuccessor(Successor theSuccessor) {
        this.successor = (GPAbstractDeleteHandler<SearchDAO>) theSuccessor;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "{" +
                " deleteType = " + this.getDeleteType() +
                "}";
    }
}
