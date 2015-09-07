package org.geosdi.geoplatform.gui.client.puregwt.wfs.event;

import com.google.gwt.event.shared.GwtEvent;
import org.geosdi.geoplatform.gui.client.puregwt.wfs.handler.FeatureAttributesHandler;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class RemoveFeatureDetailEvent extends GwtEvent<FeatureAttributesHandler> {

    private final String featureID;

    public RemoveFeatureDetailEvent(String theFeatureID) {
        this.featureID = theFeatureID;
    }

    @Override
    public Type<FeatureAttributesHandler> getAssociatedType() {
        return FeatureAttributesHandler.TYPE;
    }

    @Override
    protected void dispatch(FeatureAttributesHandler featureAttributesHandler) {
        featureAttributesHandler.removeFeatureDetail(this.featureID);
    }
}
