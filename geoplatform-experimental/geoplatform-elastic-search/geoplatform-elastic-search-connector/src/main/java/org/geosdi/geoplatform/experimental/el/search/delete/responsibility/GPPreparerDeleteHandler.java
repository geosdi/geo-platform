package org.geosdi.geoplatform.experimental.el.search.delete.responsibility;

import com.google.common.base.Preconditions;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.geosdi.geoplatform.experimental.el.dao.ElasticSearchDAO;
import org.geosdi.geoplatform.experimental.el.search.delete.DeleteByPage;
import org.geosdi.geoplatform.experimental.el.search.delete.DeleteByPage.DeleteByPageSearchDecorator;
import org.geosdi.geoplatform.experimental.el.search.delete.DeleteByPage.IDeleteByPageResult;

import static org.geosdi.geoplatform.experimental.el.search.delete.responsibility.GPElasticSearchDeleteHandler.GPElasticSearchDeleteHandlerType.PREPARER_DELETE_TYPE;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
class GPPreparerDeleteHandler extends GPAbstractDeleteHandler<ElasticSearchDAO> {

    public GPPreparerDeleteHandler() {
        super.setSuccessor(new GPSingleDeleteHandler());
    }

    /**
     * @param page
     * @param searchDAO
     * @return {@link Result}
     * @throws Exception
     */
    @Override
    public <Result extends IDeleteByPageResult, Page extends DeleteByPage> Result delete(Page page,
            ElasticSearchDAO searchDAO) throws Exception {
        return (canDelete(page) ? internalDelete(page, searchDAO) : super.forwardDelete(page, searchDAO));
    }

    /**
     * @param page
     * @param searchDAO
     * @return {@link Result}
     * @throws Exception
     */
    @Override
    protected final <Result extends IDeleteByPageResult, Page extends DeleteByPage> Result internalDelete(Page page,
            ElasticSearchDAO searchDAO) throws Exception {
        Preconditions.checkNotNull(page, "Parameter Page must not be null.");
        Preconditions.checkNotNull(searchDAO, "Parameter SearchDAO must not be null.");

        SearchRequestBuilder builder = page.buildPage(searchDAO.client()
                .prepareSearch(searchDAO.getIndexName()).setTypes(searchDAO.getIndexType()));
        Long totalElementsToDelete = builder.execute().get().getHits().getTotalHits();
        return super.forwardDelete(new DeleteByPageSearchDecorator(page.getPage(), totalElementsToDelete),
                searchDAO);
    }

    /**
     * @param page
     * @return {@link Boolean}
     */
    @Override
    protected <Page extends DeleteByPage> Boolean canDelete(Page page) {
        return ((page.getSize() != null) && (page.getSize() == 0));
    }

    /**
     * @return {@link TYPE}
     */
    @Override
    public <TYPE extends IGPElasticSearchDeleteHandlerType> TYPE getDeleteType() {
        return (TYPE) PREPARER_DELETE_TYPE;
    }
}
