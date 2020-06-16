package org.geosdi.geoplatform.gui.client.widget.time.panel.strategy.time;

import org.geosdi.geoplatform.gui.client.puregwt.layer.event.GPMultipleLayerEvent;
import org.geosdi.geoplatform.gui.puregwt.properties.WidgetPropertiesHandlerManager;

/**
 * @author Vito Salvia - CNR IMAA geoSDI Group
 * @email vito.salvia@gmail.com
 */
public class MultipleLayerTimeOperation implements IStrategyLayerOperation.ILayerTimeOperationStrategy {

    private final static GPMultipleLayerEvent GP_MULTIPLE_LAYER_EVENT = new GPMultipleLayerEvent();

    /**
     *
     */
    @Override
    public void getApplyOperation() {
        WidgetPropertiesHandlerManager.fireEvent(GP_MULTIPLE_LAYER_EVENT);

    }
}
