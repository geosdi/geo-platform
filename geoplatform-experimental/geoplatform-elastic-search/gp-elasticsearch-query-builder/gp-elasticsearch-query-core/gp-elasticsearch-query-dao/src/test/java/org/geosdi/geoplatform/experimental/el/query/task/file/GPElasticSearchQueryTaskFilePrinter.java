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
public class GPElasticSearchQueryTaskFilePrinter<V extends GPElasticSearchQuery>
        extends GPElasticSearchQueryTaskLoggerPrinter<V> {

    private final String fileName;

    public GPElasticSearchQueryTaskFilePrinter(V theValue, GPBaseMapper<V> theMapper,
            AtomicInteger theCounter, String theFileName) {
        super(theValue, theMapper, theCounter);
        this.fileName = theFileName;
    }

    /**
     * @param value
     * @param counter
     * @throws Exception
     */
    @Override
    protected void print(V value, AtomicInteger counter) throws Exception {
        mapper.write(new File("./target/" + fileName + "-"
                + counter.incrementAndGet() + ".json"), value);
    }
}
