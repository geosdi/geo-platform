package org.geosdi.geoplatform.experimental.el.query.rest.service;

import org.geosdi.geoplatform.experimental.el.query.dao.IGPElasticSearchQueryDAO;
import org.geosdi.geoplatform.experimental.el.query.model.GPElasticSearchQuery;
import org.geosdi.geoplatform.experimental.el.query.rest.delegare.IGPElasticSearchQueryDelegate;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public interface GPElasticSearchQueryServiceSupport<QUERY extends GPElasticSearchQuery,
        QUERY_DAO extends IGPElasticSearchQueryDAO<QUERY>,
        QUERY_DELEGATE extends IGPElasticSearchQueryDelegate<QUERY, QUERY_DAO>>
        extends IGPElasticSearchQueryService{

    /**
     * @param theQueryDelegate
     */
    void setQueryDelegate(QUERY_DELEGATE theQueryDelegate);
}
