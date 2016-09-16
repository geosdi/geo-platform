package org.geosdi.geoplatform.experimental.el.query.task;

import org.geosdi.geoplatform.experimental.el.api.mapper.GPBaseMapper;
import org.geosdi.geoplatform.experimental.el.api.model.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class GPElasticSearchQueryTaskPrinter<D extends Document> extends Thread {

    protected static final Logger logger = LoggerFactory.getLogger(GPElasticSearchQueryTaskPrinter.class);
    //
    private final D value;
    protected final GPBaseMapper<D> mapper;
    private final AtomicInteger counter;

    public GPElasticSearchQueryTaskPrinter(D theValue, GPBaseMapper<D> theMapper, AtomicInteger theCounter) {
        this.value = theValue;
        this.mapper = theMapper;
        this.counter = theCounter;
    }

    @Override
    public void run() {
        try {
            print(this.value, this.counter);
        } catch (Exception ex) {
            logger.error("{} --------------------> generate error : {}\n",
                    Thread.currentThread().getName(), ex.getMessage());
            ex.printStackTrace();
        }
    }

    /**
     * @param value
     * @param counter
     * @throws Exception
     */
    protected void print(D value, AtomicInteger counter) throws Exception {
        logger.info("{} ------------------> print : \n{}\n",
                Thread.currentThread().getName(), mapper.writeAsString(value));
        counter.incrementAndGet();
    }
}
