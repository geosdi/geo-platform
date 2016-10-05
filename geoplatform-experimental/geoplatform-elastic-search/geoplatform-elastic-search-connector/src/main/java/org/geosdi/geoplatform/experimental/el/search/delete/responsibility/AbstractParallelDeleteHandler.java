package org.geosdi.geoplatform.experimental.el.search.delete.responsibility;

import org.geosdi.geoplatform.experimental.el.dao.ElasticSearchDAO;
import org.geosdi.geoplatform.experimental.el.search.delete.DeleteByPage;
import org.geosdi.geoplatform.experimental.el.search.delete.DeleteByPage.IDeleteByPageResult;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
abstract class AbstractParallelDeleteHandler extends GPAbstractDeleteHandler<ElasticSearchDAO> {

    protected static final Integer PAGE_SIZE_LIMIT = 500;

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
     * @param totalElements
     * @return {@link Integer}
     */
    protected Integer calculateNumberOfTasks(int totalElements) {
        int numberTasks = totalElements / PAGE_SIZE_LIMIT;
        int pageRimaing = PAGE_SIZE_LIMIT - (numberTasks * PAGE_SIZE_LIMIT);
        if (pageRimaing > 0)
            numberTasks += 1;
        return numberTasks;
    }
}
