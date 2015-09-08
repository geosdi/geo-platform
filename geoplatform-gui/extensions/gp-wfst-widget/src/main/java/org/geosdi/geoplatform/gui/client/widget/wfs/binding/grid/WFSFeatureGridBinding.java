package org.geosdi.geoplatform.gui.client.widget.wfs.binding.grid;

import com.google.gwt.event.shared.HandlerRegistration;
import org.geosdi.geoplatform.gui.client.model.binder.IFeatureIdBinder;
import org.geosdi.geoplatform.gui.client.model.wfs.FeatureAttributeValuesDetail;
import org.geosdi.geoplatform.gui.client.widget.wfs.binding.WFSFeatureBinding;
import org.geosdi.geoplatform.gui.client.widget.wfs.toolbar.button.WFSToggleButton;
import org.geosdi.geoplatform.gui.puregwt.GPEventBus;
import org.gwtopenmaps.openlayers.client.MapWidget;
import org.gwtopenmaps.openlayers.client.feature.VectorFeature;
import org.gwtopenmaps.openlayers.client.layer.Vector;

import javax.inject.Inject;

import static org.geosdi.geoplatform.gui.client.widget.wfs.map.listener.FeatureSelectListener.enableToggleState;

/**
 * <p>This class is responsible to show {@link VectorFeature} Feature on the Map when
 * a user click on row of {@link com.extjs.gxt.ui.client.widget.grid.EditorGrid<FeatureAttributeValuesDetail>}
 * </p>
 *
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class WFSFeatureGridBinding implements WFSFeatureBinding {

    private final GPEventBus bus;
    private final HandlerRegistration handlerRegistration;
    @Inject
    private Vector vectorLayer;
    @Inject
    private IFeatureIdBinder fidBinder;
    @Inject
    private MapWidget mapWidget;

    @Inject
    public WFSFeatureGridBinding(GPEventBus theBus) {
        this.bus = theBus;

        this.handlerRegistration = addFeatureBindingHandler();
    }

    @Override
    public HandlerRegistration addFeatureBindingHandler() {
        return this.bus.addHandler(TYPE, this);
    }

    @Override
    public void bindVectoreFeature(VectorFeature vectorFeature) {
        assert (vectorFeature != null) : "The VectorFeature to bind must not be null.";

        vectorFeature.toState(VectorFeature.State.Unknown);

        if (vectorLayer.getNumberOfFeatures() > 0) {
            vectorLayer.removeAllFeatures();
        }

        vectorLayer.addFeature(vectorFeature);
        this.fidBinder.setFID(vectorFeature.getFID());
        this.mapWidget.getMap().zoomToExtent(vectorFeature.getGeometry().getBounds());

        WFSToggleButton.fireEnableToggleStateEvent(enableToggleState);
    }
}
