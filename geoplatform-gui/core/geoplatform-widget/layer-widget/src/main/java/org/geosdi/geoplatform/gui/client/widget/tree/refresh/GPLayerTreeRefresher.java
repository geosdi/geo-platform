package org.geosdi.geoplatform.gui.client.widget.tree.refresh;

import com.google.inject.Inject;
import org.geosdi.geoplatform.gui.client.command.layer.basic.LoadRootElementsRequest;
import org.geosdi.geoplatform.gui.client.command.layer.basic.LoadRootElementsResponse;
import org.geosdi.geoplatform.gui.client.model.GPRootTreeNode;
import org.geosdi.geoplatform.gui.client.model.projects.GPShortClientProject;
import org.geosdi.geoplatform.gui.client.puregwt.refresh.GPCompositeRefreshHandler;
import org.geosdi.geoplatform.gui.command.api.GPClientCommand;
import org.geosdi.geoplatform.gui.command.api.GPClientCommandExecutor;
import org.geosdi.geoplatform.gui.configuration.message.GeoPlatformMessage;

import javax.inject.Singleton;
import java.util.logging.Logger;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@Singleton
public class GPLayerTreeRefresher implements GPCompositeRefreshHandler {

    private static final Logger logger = Logger.getLogger("GPLayerTreeRefresher");
    //
    private final LoadRootElementsRequest loadRootElementsRequest = new LoadRootElementsRequest();
    private final GPRootTreeNode root;

    @Inject
    public GPLayerTreeRefresher(GPRootTreeNode theRoot) {
        this.root = theRoot;
    }

    @Override
    public void refreshRootElements() {
        this.loadRootElementsRequest.setProjectId(this.root.getId());
        GPClientCommandExecutor.executeCommand(new GPClientCommand<LoadRootElementsResponse>() {

            {
                super.setCommandRequest(loadRootElementsRequest);
            }

            @Override
            public void onCommandSuccess(LoadRootElementsResponse response) {
                GPShortClientProject gpShortClientProject = response.getResult();
                root.setProjectElements(gpShortClientProject.getNumberOfElements());
                root.setProjectVersion(gpShortClientProject.getVersion());
            }

            @Override
            public void onCommandFailure(Throwable exception) {
                GeoPlatformMessage.errorMessage("Error", exception.getMessage());
            }
        });
    }
}
