package org.geosdi.geoplatform.gui.client.command.layer.basic;

import org.geosdi.geoplatform.gui.client.model.projects.GPShortClientProject;
import org.geosdi.geoplatform.gui.command.api.GPCommandResponse;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class LoadRootElementsResponse implements GPCommandResponse<GPShortClientProject> {

    private static final long serialVersionUID = 3693886445473671386L;
    //
    private GPShortClientProject result;

    public LoadRootElementsResponse() {
    }

    public LoadRootElementsResponse(GPShortClientProject theResult) {
        this.result = theResult;
    }

    @Override
    public GPShortClientProject getResult() {
        return this.result;
    }

    @Override
    public String toString() {
        return getClass().getName() +
                "{" + "result = " + result + '}';
    }
}
