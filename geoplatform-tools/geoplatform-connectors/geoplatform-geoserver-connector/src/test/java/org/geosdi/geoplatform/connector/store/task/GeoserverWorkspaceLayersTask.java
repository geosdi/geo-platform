package org.geosdi.geoplatform.connector.store.task;

import org.geosdi.geoplatform.connector.geoserver.request.layers.GeoserverLoadWorkspaceLayersRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Nonnull;

import static com.google.common.base.Preconditions.checkArgument;
import static javax.annotation.meta.When.NEVER;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class GeoserverWorkspaceLayersTask extends Thread {

    private static final Logger logger = LoggerFactory.getLogger(GeoserverWorkspaceLayersTask.class);
    //
    private final GeoserverLoadWorkspaceLayersRequest loadWorkspaceLayersRequest;
    private final String workspaceName;

    /**
     * Allocates a new {@code Thread} object. This constructor has the same
     * effect as {@linkplain Thread#Thread(ThreadGroup, Runnable, String) Thread}
     * {@code (null, null, gname)}, where {@code gname} is a newly generated
     * name. Automatically generated names are of the form
     * {@code "Thread-"+}<i>n</i>, where <i>n</i> is an integer.
     */
    public GeoserverWorkspaceLayersTask(@Nonnull(when = NEVER) GeoserverLoadWorkspaceLayersRequest theLoadWorkspaceLayersRequest,
            @Nonnull(when = NEVER) String workspaceName) {
        checkArgument(theLoadWorkspaceLayersRequest != null, "The Parameter laodWorkspaceLayersRequest must not be null.");
        checkArgument((workspaceName != null) && !(workspaceName.trim().isEmpty()),
                "The Parameter workspaceName must not be null or an empty string.");
        this.loadWorkspaceLayersRequest = theLoadWorkspaceLayersRequest;
        this.workspaceName = workspaceName;
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
            this.loadWorkspaceLayersRequest.withWorkspaceName(this.workspaceName);
            logger.debug("------------------------------> Loading Layers for Workspace : {}\n", this.workspaceName);
            logger.debug("#############################{} produces ---------> \n{}\n", currentThread().getName()
                    .concat(" - ").concat(this.workspaceName), this.loadWorkspaceLayersRequest.getResponse());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}