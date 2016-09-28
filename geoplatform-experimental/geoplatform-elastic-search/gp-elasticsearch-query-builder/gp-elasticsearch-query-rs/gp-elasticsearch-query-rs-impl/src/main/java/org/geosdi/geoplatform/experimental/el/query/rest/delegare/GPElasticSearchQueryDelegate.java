package org.geosdi.geoplatform.experimental.el.query.rest.delegare;

import org.geosdi.geoplatform.experimental.el.dao.GPPageableElasticSearchDAO.IPageResult;
import org.geosdi.geoplatform.experimental.el.query.dao.IGPElasticSearchQueryDAO;
import org.geosdi.geoplatform.experimental.el.query.model.GPElasticSearchQuery;
import org.geosdi.geoplatform.experimental.el.query.rest.response.GPElasticSearchQueryStore;
import org.geosdi.geoplatform.logger.support.annotation.GeoPlatformLog;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class GPElasticSearchQueryDelegate<QUERY extends GPElasticSearchQuery,
        QUERY_DAO extends IGPElasticSearchQueryDAO<QUERY>> implements IGPElasticSearchQueryDelegate<QUERY, QUERY_DAO> {

    @GeoPlatformLog
    protected Logger logger;
    //
    protected QUERY_DAO queryDAO;

    /**
     * @param from
     * @param size
     * @return {@link GPElasticSearchQueryStore <QUERY>}
     * @throws Exception
     */
    @Override
    public GPElasticSearchQueryStore<QUERY> findQuery(Integer from, Integer size) throws Exception {
        logger.debug("###########################{}#findGPElasticSearchQuery is executing with parameters from : {}, " +
                "size : {}\n", getClass().getSimpleName(), from, size);
        IPageResult<QUERY> pageResult = this.queryDAO.findQuery(from, size);
        return new GPElasticSearchQueryStore<>(pageResult.getTotal(), pageResult.getResults());
    }

    /**
     * @param from
     * @param size
     * @param fromDate
     * @param toDate
     * @return {@link GPElasticSearchQueryStore<QUERY>}
     * @throws Exception
     */
    @Override
    public GPElasticSearchQueryStore<QUERY> findQueryByCreationDate(Integer from, Integer size,
            Long fromDate, Long toDate) throws Exception {
        logger.debug("###########################{}#findGPElasticSearchQueryByCreationDate is executing with parameters from : {}, " +
                "size : {}, fromDate : {}, toDate : {}\n", getClass().getSimpleName(), from, size, fromDate, toDate);
        IPageResult<QUERY> pageResult = this.queryDAO.findByQueryCreationDate(new DateTime(fromDate),
                new DateTime(toDate), from, size);
        return new GPElasticSearchQueryStore<>(pageResult.getTotal(), pageResult.getResults());
    }

    /**
     * @param theQueryDAO
     */
    @Autowired
    @Override
    public void setGPElasticSearchQueryDAO(QUERY_DAO theQueryDAO) {
        this.queryDAO = theQueryDAO;
    }
}
