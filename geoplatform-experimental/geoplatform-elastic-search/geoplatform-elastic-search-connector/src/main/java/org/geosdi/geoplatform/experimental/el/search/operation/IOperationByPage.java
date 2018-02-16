package org.geosdi.geoplatform.experimental.el.search.operation;

import org.geosdi.geoplatform.experimental.el.api.model.Document;

/**
 * @author Vito Salvia - CNR IMAA geoSDI Group
 * @email vito.salvia@gmail.com
 */
@FunctionalInterface
public interface IOperationByPage<D extends Document> {

    /**
     *
     * @param value
     * @return {@link D}
     */
    D update(D value);

}
