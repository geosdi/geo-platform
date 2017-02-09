package org.geosdi.geoplatform.gui.client.command.layer.basic;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class LoadRootElementsRequest extends LoadProjectRequest {

    private static final long serialVersionUID = 8499478028397604217L;

    public LoadRootElementsRequest() {
    }

    @Override
    public String getCommandName() {
        return "command.layer.basic.LoadRootElementsCommand";
    }
}
