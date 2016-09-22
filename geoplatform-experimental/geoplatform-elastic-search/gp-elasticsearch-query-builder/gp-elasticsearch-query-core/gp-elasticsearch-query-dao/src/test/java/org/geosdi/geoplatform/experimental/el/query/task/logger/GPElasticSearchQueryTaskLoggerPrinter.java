package org.geosdi.geoplatform.experimental.el.query.task.logger;

import org.geosdi.geoplatform.experimental.el.api.mapper.GPBaseMapper;
import org.geosdi.geoplatform.experimental.el.query.model.GPElasticSearchQuery;
import org.geosdi.geoplatform.experimental.el.query.task.GPElasticSearchQueryTaskPrinter;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class GPElasticSearchQueryTaskLoggerPrinter<V extends GPElasticSearchQuery>
        extends GPElasticSearchQueryTaskPrinter<V> {

    public GPElasticSearchQueryTaskLoggerPrinter(V theValue, GPBaseMapper<V> theMapper,
            AtomicInteger theCounter) {
        super(theValue, theMapper, theCounter);
    }
}
