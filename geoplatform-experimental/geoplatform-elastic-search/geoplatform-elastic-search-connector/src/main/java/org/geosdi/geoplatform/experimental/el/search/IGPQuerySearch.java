package org.geosdi.geoplatform.experimental.el.search;

import org.elasticsearch.index.query.QueryBuilder;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public interface IGPQuerySearch {

    /**
     * @return {@link String}
     */
    String getField();

    /**
     * @return {@link QueryBuilder}
     */
    QueryBuilder buildQuery();
}
