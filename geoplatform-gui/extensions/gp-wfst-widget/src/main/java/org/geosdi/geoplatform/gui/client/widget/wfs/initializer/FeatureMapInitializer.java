/**
 *
 *    geo-platform
 *    Rich webgis framework
 *    http://geo-platform.org
 *   ====================================================================
 *
 *   Copyright (C) 2008-2021 geoSDI Group (CNR IMAA - Potenza - ITALY).
 *
 *   This program is free software: you can redistribute it and/or modify it
 *   under the terms of the GNU General Public License as published by
 *   the Free Software Foundation, either version 3 of the License, or
 *   (at your option) any later version. This program is distributed in the
 *   hope that it will be useful, but WITHOUT ANY WARRANTY; without
 *   even the implied warranty of MERCHANTABILITY or FITNESS FOR
 *   A PARTICULAR PURPOSE. See the GNU General Public License
 *   for more details. You should have received a copy of the GNU General
 *   Public License along with this program. If not, see http://www.gnu.org/licenses/
 *
 *   ====================================================================
 *
 *   Linking this library statically or dynamically with other modules is
 *   making a combined work based on this library. Thus, the terms and
 *   conditions of the GNU General Public License cover the whole combination.
 *
 *   As a special exception, the copyright holders of this library give you permission
 *   to link this library with independent modules to produce an executable, regardless
 *   of the license terms of these independent modules, and to copy and distribute
 *   the resulting executable under terms of your choice, provided that you also meet,
 *   for each linked independent module, the terms and conditions of the license of
 *   that module. An independent module is a module which is not derived from or
 *   based on this library. If you modify this library, you may extend this exception
 *   to your version of the library, but you are not obligated to do so. If you do not
 *   wish to do so, delete this exception statement from your version.
 */
package org.geosdi.geoplatform.gui.client.widget.wfs.initializer;

import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.Timer;
import org.geosdi.geoplatform.gui.client.model.binder.ILayerSchemaBinder;
import org.geosdi.geoplatform.gui.client.puregwt.observer.event.ResetToolbarObserverEvent;
import org.geosdi.geoplatform.gui.client.puregwt.wfs.event.FeatureStatusBarEvent;
import org.geosdi.geoplatform.gui.client.puregwt.wfs.event.InjectGetFeatureModelEvent;
import org.geosdi.geoplatform.gui.client.widget.wfs.builder.FeatureMapLayerBuilder;
import org.geosdi.geoplatform.gui.client.widget.wfs.map.control.getfeature.WFSGetFeatureControl;
import org.geosdi.geoplatform.gui.client.widget.wfs.toolbar.button.WFSButtonKeyProvider;
import org.geosdi.geoplatform.gui.client.widget.wfs.toolbar.button.WFSToggleButton;
import org.geosdi.geoplatform.gui.model.GPLayerBean;
import org.geosdi.geoplatform.gui.puregwt.GPEventBus;
import org.gwtopenmaps.openlayers.client.Bounds;
import org.gwtopenmaps.openlayers.client.LonLat;
import org.gwtopenmaps.openlayers.client.MapWidget;
import org.gwtopenmaps.openlayers.client.layer.Layer;
import org.gwtopenmaps.openlayers.client.layer.Vector;
import org.gwtopenmaps.openlayers.client.layer.WMS;

import javax.inject.Inject;
import java.util.List;
import java.util.logging.Logger;

import static org.geosdi.geoplatform.gui.client.widget.wfs.statusbar.FeatureStatusBar.FeatureStatusBarType.STATUS_OK;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class FeatureMapInitializer implements IFeatureMapInitializer {

    private static final Logger logger = Logger.getLogger("FeatureMapInitializer");
    private static final ResetToolbarObserverEvent resetToolbarObserver = new ResetToolbarObserverEvent();
    private static final int WMS_Z_INDEX = 1000;
    private static final int VECTOR_LAYER_Z_INDEX = 1001;
    private final GPEventBus bus;
    //
    @Inject
    private MapWidget mapWidget;
    @Inject
    private FeatureMapLayerBuilder mapLayerBuilder;
    @Inject
    private Vector vectorLayer;
    @Inject
    private LonLat italyLonLat;
    @Inject
    private ILayerSchemaBinder layerSchemaBinder;
    @Inject
    private InjectGetFeatureModelEvent injectGetFeatureModelEvent;
    private Layer wms;
    private List<GPLayerBean> layers;
    private List<Layer> wmsLayers;
    private Bounds bb;

    /**
     * @param theBus
     */
    @Inject
    public FeatureMapInitializer(GPEventBus theBus) {
        this.bus = theBus;
        addFeatureMapInitializerHandler();
    }

    @Override
    public void bindLayerSchema() {
        final GPLayerBean layer = layerSchemaBinder.getSelectedLayer();
        this.wms = this.mapLayerBuilder.buildLayer(layer);
        this.injectGetFeatureModelEvent.setWms(wms);
        WFSGetFeatureControl.fireInjectGetFeatureModelEvent(injectGetFeatureModelEvent);

        Timer t = new Timer() {

            @Override
            public void run() {
                loadLayerOnMap();
                notifyStatus();
            }

        };

        t.schedule(1000);
    }

    @Override
    public void resetMapWidget() {
        this.bus.fireEvent(resetToolbarObserver);
        this.vectorLayer.destroyFeatures();

        if ((this.wmsLayers != null) && !(this.wmsLayers.isEmpty())) {
            for (Layer layer : this.wmsLayers) {
                this.mapWidget.getMap().removeLayer(layer);
            }
            this.layers = null;
            this.wmsLayers = null;
        }

        if (wms != null) {
            this.mapWidget.getMap().removeLayer(wms);
        }

        this.mapWidget.getMap().removeLayer(vectorLayer);
        this.initMapWidget();
    }

    @Override
    public void initMapWidget() {
        this.mapWidget.getMap().setCenter(italyLonLat, 4);
    }

    @Override
    public final HandlerRegistration addFeatureMapInitializerHandler() {
        return this.bus.addHandler(TYPE, this);
    }

    @Override
    public void redrawWMSLayer() {
        ((WMS) this.wms).redraw(true);
    }

    @Override
    public void bindLayers(List<GPLayerBean> theLayers) {
        this.layers = theLayers;
    }

    protected void loadLayerOnMap() {
        this.mapWidget.getMap().addLayer(wms);
        this.mapWidget.getMap().addLayer(vectorLayer);
        this.wms.setZIndex(WMS_Z_INDEX);
        this.vectorLayer.setZIndex(VECTOR_LAYER_Z_INDEX);
        ((WMS) wms).redraw(true);
        this.bb = ((WMS) this.wms).getOptions().getMaxExtent();
        this.mapWidget.getMap().zoomToExtent(this.bb);
        WFSToggleButton.fireToggleStateEvent(WFSButtonKeyProvider.GET_FEATURE.name());
    }

    public void zoomOnBounds(){
        this.mapWidget.getMap().zoomToExtent(this.bb);
    }

    protected void notifyStatus() {
        this.bus.fireEvent(new FeatureStatusBarEvent("WFS Layer loaded", STATUS_OK));
    }
}
