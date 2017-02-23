package org.geosdi.geoplatform.gui.server.command.layer.basic;

import org.geosdi.geoplatform.gui.client.command.layer.basic.LoadRootElementsRequest;
import org.geosdi.geoplatform.gui.client.command.layer.basic.LoadRootElementsResponse;
import org.geosdi.geoplatform.gui.client.model.projects.GPShortClientProject;
import org.geosdi.geoplatform.gui.command.server.GPCommand;
import org.geosdi.geoplatform.gui.server.ILayerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@Lazy(true)
@Component(value = "command.layer.basic.LoadRootElementsCommand")
public class LoadRootElementsCommand implements GPCommand<LoadRootElementsRequest, LoadRootElementsResponse> {

    private static final Logger logger = LoggerFactory.getLogger(LoadRootElementsCommand.class);
    //
    @Autowired
    private ILayerService layerService;

    /**
     * @param request
     * @param httpServletRequest
     * @return
     */
    @Override
    public LoadRootElementsResponse execute(LoadRootElementsRequest request, HttpServletRequest httpServletRequest) {
        logger.debug("##################### Executing {} Command with Request : {}",
                this.getClass().getSimpleName(), request);
        GPShortClientProject gpShortClientProject = this.layerService.loadRootElements(request.getProjectId(), httpServletRequest);
        logger.debug("#################### Found {} ", gpShortClientProject);
        return new LoadRootElementsResponse(gpShortClientProject);
    }
}
