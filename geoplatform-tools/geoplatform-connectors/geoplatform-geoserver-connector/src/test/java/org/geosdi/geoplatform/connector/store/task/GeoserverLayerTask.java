package org.geosdi.geoplatform.connector.store.task;

import org.geosdi.geoplatform.connector.geoserver.request.layers.GPGeoserverLayerRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class GeoserverLayerTask extends Thread {

    private static final Logger logger = LoggerFactory.getLogger(GeoserverLayerTask.class);
    //
    private final AtomicInteger counter = new AtomicInteger(0);
    private final GPGeoserverLayerRequest layerRequest;
    private final String layerName;

    /**
     * Allocates a new {@code Thread} object. This constructor has the same
     * effect as {@linkplain Thread#Thread(ThreadGroup, Runnable, String) Thread}
     * {@code (null, null, gname)}, where {@code gname} is a newly generated
     * name. Automatically generated names are of the form
     * {@code "Thread-"+}<i>n</i>, where <i>n</i> is an integer.
     */
    public GeoserverLayerTask(GPGeoserverLayerRequest theLayerRequest, String theLayerName) {
        this.layerRequest = theLayerRequest;
        this.layerName = theLayerName;
    }

    /**
     * If this thread was constructed using a separate
     * <code>Runnable</code> run object, then that
     * <code>Runnable</code> object's <code>run</code> method is called;
     * otherwise, this method does nothing and returns.
     * <p>
     * Subclasses of <code>Thread</code> should override this method.
     *
     * @see #start()
     * @see #stop()
     * @see Thread#Thread(ThreadGroup, Runnable, String)
     */
    @Override
    public void run() {
        try {
            this.layerRequest.setName(this.layerName);
            logger.debug("#############################{} produces ---------> \n{}\n", currentThread().getName()
                    .concat(" - ").concat("" + this.counter.getAndIncrement()), this.layerRequest.getResponseAsString());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
