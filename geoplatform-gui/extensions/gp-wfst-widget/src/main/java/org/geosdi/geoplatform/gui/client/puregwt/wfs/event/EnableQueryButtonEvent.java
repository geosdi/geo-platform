package org.geosdi.geoplatform.gui.client.puregwt.wfs.event;

import com.google.gwt.event.shared.GwtEvent;
import org.geosdi.geoplatform.gui.client.puregwt.wfs.handler.FeatureSelectionWidgetHandler;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class EnableQueryButtonEvent extends GwtEvent<FeatureSelectionWidgetHandler> {

    private final Boolean enable;

    public EnableQueryButtonEvent(Boolean theEnable) {
        this.enable = theEnable;
    }

    @Override
    public Type<FeatureSelectionWidgetHandler> getAssociatedType() {
        return FeatureSelectionWidgetHandler.TYPE;
    }

    @Override
    protected void dispatch(FeatureSelectionWidgetHandler featureSelectionWidgetHandler) {
        featureSelectionWidgetHandler.queryEnabled(this.enable);
    }
}
