package org.geosdi.geoplatform.experimental.el.dao;

import org.geosdi.geoplatform.experimental.el.api.model.Document;

/**
 * @author Vito Salvia - CNR IMAA geoSDI Group
 * @email vito.salvia@gmail.com
 */
@FunctionalInterface
public interface GPElasticSearchUpdateHandler<D extends Document> {

    /**
     * @param entity
     * @return
     */
    D updateEntity(D entity);

}
