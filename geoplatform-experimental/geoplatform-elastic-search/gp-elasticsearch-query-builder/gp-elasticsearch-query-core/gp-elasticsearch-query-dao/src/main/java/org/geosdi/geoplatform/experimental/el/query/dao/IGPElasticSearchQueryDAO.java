package org.geosdi.geoplatform.experimental.el.query.dao;

import org.geosdi.geoplatform.experimental.el.dao.GPElasticSearchDAO;
import org.geosdi.geoplatform.experimental.el.query.model.GPElasticSearchQuery;
import org.joda.time.DateTime;

import javax.annotation.Nullable;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public interface IGPElasticSearchQueryDAO<Q extends GPElasticSearchQuery> extends GPElasticSearchDAO.GPElasticSearchBaseDAO<Q> {

    /**
     * @param queryName
     * @param from
     * @param size
     * @return {@link org.geosdi.geoplatform.experimental.el.dao.GPPageableElasticSearchDAO.IPageResult<Q>}
     * @throws Exception
     */
    IPageResult<Q> findByQueryName(String queryName, @Nullable Integer from,
            @Nullable Integer size) throws Exception;

    /**
     * @param queryDescription
     * @param from
     * @param size
     * @return {@link org.geosdi.geoplatform.experimental.el.dao.GPPageableElasticSearchDAO.IPageResult<Q>}
     * @throws Exception
     */
    IPageResult<Q> findByQueryDescription(String queryDescription, @Nullable Integer from,
            @Nullable Integer size) throws Exception;

    /**
     * @param fromDate
     * @param toDate
     * @param from
     * @param size
     * @return {@link org.geosdi.geoplatform.experimental.el.dao.GPPageableElasticSearchDAO.IPageResult<Q>}
     * @throws Exception
     */
    IPageResult<Q> findByQueryCreationDate(DateTime fromDate, DateTime toDate,
            @Nullable Integer from, @Nullable Integer size) throws Exception;

    /**
     * @param from
     * @param size
     * @return {@link org.geosdi.geoplatform.experimental.el.dao.GPPageableElasticSearchDAO.IPageResult<Q>}
     * @throws Exception
     */
    IPageResult<Q> findQuery(@Nullable Integer from, @Nullable Integer size) throws Exception;
}
