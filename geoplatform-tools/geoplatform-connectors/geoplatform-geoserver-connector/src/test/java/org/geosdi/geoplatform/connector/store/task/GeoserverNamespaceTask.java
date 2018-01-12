package org.geosdi.geoplatform.connector.store.task;

import org.geosdi.geoplatform.connector.geoserver.request.namespaces.GPGeoserverNamespaceRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.atomic.AtomicInteger;

import static com.google.common.base.Preconditions.checkArgument;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class GeoserverNamespaceTask extends Thread {

    private static final Logger logger = LoggerFactory.getLogger(GeoserverNamespaceTask.class);
    //
    private final AtomicInteger counter = new AtomicInteger(0);
    private final GPGeoserverNamespaceRequest namespaceRequest;
    private final String prefix;

    /**
     * Allocates a new {@code Thread} object. This constructor has the same
     * effect as {@linkplain Thread#Thread(ThreadGroup, Runnable, String) Thread}
     * {@code (null, null, gname)}, where {@code gname} is a newly generated
     * name. Automatically generated names are of the form
     * {@code "Thread-"+}<i>n</i>, where <i>n</i> is an integer.
     */
    public GeoserverNamespaceTask(GPGeoserverNamespaceRequest theNamespaceRequest, String thePrefix) {
        checkArgument(theNamespaceRequest != null, "The Parameter NameSpaceRequest must not be null.");
        checkArgument(((thePrefix != null) && !(thePrefix.isEmpty())),
                "The Parameter prefix must not be null or an Empty String.");
        this.namespaceRequest = theNamespaceRequest;
        this.prefix = thePrefix;
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
            this.namespaceRequest.setPrefix(this.prefix);
            logger.debug("#############################{} produces ---------> \n{}\n", currentThread().getName()
                    .concat(" - ").concat("" + this.counter.getAndIncrement()), this.namespaceRequest.getResponseAsString());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}