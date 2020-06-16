package org.geosdi.geoplatform.gui.client.widget.time.panel.strategy.time;

import org.geosdi.geoplatform.gui.client.puregwt.layer.event.GPSingleLayerEvent;
import org.geosdi.geoplatform.gui.puregwt.properties.WidgetPropertiesHandlerManager;

/**
 * @author Vito Salvia - CNR IMAA geoSDI Group
 * @email vito.salvia@gmail.com
 */
public class SingleLayerTimeOperation implements IStrategyLayerOperation.ILayerTimeOperationStrategy {

    private final static GPSingleLayerEvent GP_SINGLE_LAYER_EVENT = new GPSingleLayerEvent();

    /**
     *
     */
    @Override
    public void getApplyOperation() {
        WidgetPropertiesHandlerManager.fireEvent(GP_SINGLE_LAYER_EVENT);

    }
}
