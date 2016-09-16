package org.geosdi.geoplatform.experimental.el.query.task.file;

import org.geosdi.geoplatform.experimental.el.api.mapper.GPBaseMapper;
import org.geosdi.geoplatform.experimental.el.query.model.GPElasticSearchQuery;
import org.geosdi.geoplatform.experimental.el.query.task.logger.GPElasticSearchQueryTaskLoggerPrinter;

import java.io.File;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class GPElasticSearchQueryTaskFilePrinter extends GPElasticSearchQueryTaskLoggerPrinter {

    public GPElasticSearchQueryTaskFilePrinter(GPElasticSearchQuery theValue, GPBaseMapper<GPElasticSearchQuery> theMapper,
            AtomicInteger theCounter) {
        super(theValue, theMapper, theCounter);
    }

    /**
     * @param value
     * @param counter
     * @throws Exception
     */
    @Override
    protected void print(GPElasticSearchQuery value, AtomicInteger counter) throws Exception {
        mapper.write(new File("./target/GPElasticSearchQuery-" + counter.incrementAndGet() + ".json"), value);
    }
}
