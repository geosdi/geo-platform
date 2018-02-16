package org.geosdi.geoplatform.experimental.el.search.strategy;

import org.geosdi.geoplatform.experimental.el.dao.ElasticSearchDAO;
import org.geosdi.geoplatform.experimental.el.search.operation.OperationByPage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Vito Salvia - CNR IMAA geoSDI Group
 * @email vito.salvia@gmail.com
 */
public interface IGPOperationAsyncStrategy {

    /**
     * @param <Result>
     * @param page
     * @param
     * @return {@link OperationByPage.IOperationByPageResult}
     */
    <Result extends OperationByPage.IOperationByPageResult, Page extends OperationByPage> Result singleOperation(Page page, ElasticSearchDAO searchDAO) throws Exception;

    /**
     * @param <Result>
     * @param page
     * @param
     * @return {@link OperationByPage.IOperationByPageResult}
     */
    <Result extends OperationByPage.IOperationByPageResult, Page extends OperationByPage> Result parallerOperation(Page page, ElasticSearchDAO searchDAO) throws Exception;

    /**
     * @return
     */
    IGPOperationAsyncType.OperationAsyncEnum getStrateyType();

    abstract class AbstractOperationAsyncStrategy implements IGPOperationAsyncStrategy {

        public static final Integer PAGE_SIZE_LIMIT = 500;
        protected final Logger logger = LoggerFactory.getLogger(this.getClass());

        protected Integer calculateNumberOfTasks(int totalElements) {
            int numberTasks = totalElements / PAGE_SIZE_LIMIT;
            int pageRimaing = PAGE_SIZE_LIMIT - (numberTasks * PAGE_SIZE_LIMIT);
            if (pageRimaing > 0)
                numberTasks += 1;
            return numberTasks;
        }


    }

}
