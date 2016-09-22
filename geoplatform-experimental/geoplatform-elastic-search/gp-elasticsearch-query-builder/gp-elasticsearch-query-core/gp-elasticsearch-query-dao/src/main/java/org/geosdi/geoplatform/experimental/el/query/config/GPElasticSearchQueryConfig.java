package org.geosdi.geoplatform.experimental.el.query.config;

import org.geosdi.geoplatform.experimental.el.api.mapper.GPBaseMapper;
import org.geosdi.geoplatform.experimental.el.query.dao.IGPElasticSearchQueryDAO;
import org.geosdi.geoplatform.experimental.el.query.model.GPElasticSearchQuery;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public interface GPElasticSearchQueryConfig {

    /**
     * @param <Mapper>
     * @return {@link Mapper}
     */
    <Q extends GPElasticSearchQuery, Mapper extends GPBaseMapper<Q>> Mapper configureElasticSearchQueryMapper();

    /**
     * @param <QueryDAO>
     * @return {@link QueryDAO}
     */
    <QueryDAO extends IGPElasticSearchQueryDAO> QueryDAO configureElasticSearchQueryDAO();
}
