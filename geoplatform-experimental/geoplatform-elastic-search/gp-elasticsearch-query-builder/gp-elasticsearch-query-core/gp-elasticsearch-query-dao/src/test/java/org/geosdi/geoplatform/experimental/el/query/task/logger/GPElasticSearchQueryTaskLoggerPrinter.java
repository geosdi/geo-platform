package org.geosdi.geoplatform.experimental.el.query.task.logger;

import org.geosdi.geoplatform.experimental.el.api.mapper.GPBaseMapper;
import org.geosdi.geoplatform.experimental.el.query.model.GPElasticSearchQuery;
import org.geosdi.geoplatform.experimental.el.query.task.GPElasticSearchQueryTaskPrinter;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class GPElasticSearchQueryTaskLoggerPrinter extends GPElasticSearchQueryTaskPrinter<GPElasticSearchQuery> {

    public GPElasticSearchQueryTaskLoggerPrinter(GPElasticSearchQuery theValue, GPBaseMapper<GPElasticSearchQuery> theMapper,
            AtomicInteger theCounter) {
        super(theValue, theMapper, theCounter);
    }
}
