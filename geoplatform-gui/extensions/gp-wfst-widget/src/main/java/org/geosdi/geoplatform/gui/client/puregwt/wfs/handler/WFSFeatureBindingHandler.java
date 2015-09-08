package org.geosdi.geoplatform.gui.client.puregwt.wfs.handler;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;
import org.gwtopenmaps.openlayers.client.feature.VectorFeature;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public interface WFSFeatureBindingHandler extends EventHandler {

    GwtEvent.Type<WFSFeatureBindingHandler> TYPE = new GwtEvent.Type<WFSFeatureBindingHandler>();

    /**
     * <p>Bind {@link VectorFeature} Feature showing it
     * on {@link org.gwtopenmaps.openlayers.client.layer.Vector} Layer
     * </p>
     *
     * @param vectorFeature
     */
    void bindVectoreFeature(VectorFeature vectorFeature);

    /**
     * <p>Internal class to have an {@link GwtEvent} to dispatch the {@link VectorFeature} Feature</p>
     */
    class WFSFeatureBindingEvent extends GwtEvent<WFSFeatureBindingHandler> {

        private final VectorFeature vectorfeature;

        public WFSFeatureBindingEvent(VectorFeature theVectorfeature) {
            assert (theVectorfeature != null) : "The VectoreFeature passed must not be null.";
            this.vectorfeature = theVectorfeature;
        }

        @Override
        public Type<WFSFeatureBindingHandler> getAssociatedType() {
            return TYPE;
        }

        @Override
        protected void dispatch(WFSFeatureBindingHandler wfsFeatureBindingHandler) {
            wfsFeatureBindingHandler.bindVectoreFeature(vectorfeature);
        }
    }
}
