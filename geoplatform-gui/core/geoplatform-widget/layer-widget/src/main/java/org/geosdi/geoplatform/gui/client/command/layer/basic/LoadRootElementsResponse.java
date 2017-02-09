package org.geosdi.geoplatform.gui.client.command.layer.basic;

import org.geosdi.geoplatform.gui.command.api.GPCommandResponse;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class LoadRootElementsResponse implements GPCommandResponse<Integer> {

    private static final long serialVersionUID = 3693886445473671386L;
    //
    private Integer result;

    public LoadRootElementsResponse() {
    }

    public LoadRootElementsResponse(Integer theResult) {
        this.result = theResult;
    }

    @Override
    public Integer getResult() {
        return this.result;
    }

    @Override
    public String toString() {
        return getClass().getName() +
                "{" + "result = " + result + '}';
    }
}
