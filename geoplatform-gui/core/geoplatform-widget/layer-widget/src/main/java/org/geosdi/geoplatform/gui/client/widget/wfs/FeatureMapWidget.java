/*
 *  geo-platform
 *  Rich webgis framework
 *  http://geo-platform.org
 * ====================================================================
 *
 * Copyright (C) 2008-2013 geoSDI Group (CNR IMAA - Potenza - ITALY).
 *
 * This program is free software: you can redistribute it and/or modify it 
 * under the terms of the GNU General Public License as published by 
 * the Free Software Foundation, either version 3 of the License, or 
 * (at your option) any later version. This program is distributed in the 
 * hope that it will be useful, but WITHOUT ANY WARRANTY; without 
 * even the implied warranty of MERCHANTABILITY or FITNESS FOR 
 * A PARTICULAR PURPOSE. See the GNU General Public License 
 * for more details. You should have received a copy of the GNU General 
 * Public License along with this program. If not, see http://www.gnu.org/licenses/ 
 *
 * ====================================================================
 *
 * Linking this library statically or dynamically with other modules is 
 * making a combined work based on this library. Thus, the terms and 
 * conditions of the GNU General Public License cover the whole combination. 
 * 
 * As a special exception, the copyright holders of this library give you permission 
 * to link this library with independent modules to produce an executable, regardless 
 * of the license terms of these independent modules, and to copy and distribute 
 * the resulting executable under terms of your choice, provided that you also meet, 
 * for each linked independent module, the terms and conditions of the license of 
 * that module. An independent module is a module which is not derived from or 
 * based on this library. If you modify this library, you may extend this exception 
 * to your version of the library, but you are not obligated to do so. If you do not 
 * wish to do so, delete this exception statement from your version. 
 *
 */
package org.geosdi.geoplatform.gui.client.widget.wfs;

import com.google.gwt.user.client.Timer;
import javax.inject.Inject;
import org.geosdi.geoplatform.gui.client.widget.GeoPlatformContentPanel;
import org.geosdi.geoplatform.gui.client.widget.wfs.builder.FeatureMapLayerBuilder;
import org.geosdi.geoplatform.gui.client.widget.wfs.builder.GetFeatureControlBuilder;
import org.geosdi.geoplatform.gui.client.widget.wfs.event.FeatureStatusBarEvent;
import org.geosdi.geoplatform.gui.client.widget.wfs.feature.handler.FeatureSelectHandler;
import org.geosdi.geoplatform.gui.client.widget.wfs.feature.handler.FeatureUnSelectHandler;
import org.geosdi.geoplatform.gui.client.widget.wfs.statusbar.FeatureStatusBar.FeatureStatusBarType;
import org.geosdi.geoplatform.gui.impl.map.control.feature.GetFeatureModel;
import org.geosdi.geoplatform.gui.model.GPLayerBean;
import org.geosdi.geoplatform.gui.model.GPVectorBean;
import org.geosdi.geoplatform.gui.puregwt.GPEventBus;
import org.geosdi.geoplatform.gui.responce.LayerSchemaDTO;
import org.gwtopenmaps.openlayers.client.Bounds;
import org.gwtopenmaps.openlayers.client.LonLat;
import org.gwtopenmaps.openlayers.client.MapWidget;
import org.gwtopenmaps.openlayers.client.control.GetFeature;
import org.gwtopenmaps.openlayers.client.layer.Layer;
import org.gwtopenmaps.openlayers.client.layer.Vector;
import org.gwtopenmaps.openlayers.client.layer.WMS;

/**
 *
 * @author Vincenzo Monteverde <vincenzo.monteverde@geosdi.org>
 */
public class FeatureMapWidget extends GeoPlatformContentPanel implements
        IFeatureMapWidget {

    private MapWidget mapWidget;
    private FeatureMapLayerBuilder mapLayerBuilder;
    private Vector vectorLayer;
    private Layer wms;
    private GetFeature controlFeature;
    private GetFeatureControlBuilder featureControlBuilder;
    private FeatureSelectHandler selectFeature;
    private FeatureUnSelectHandler unSelectFeature;
    private LonLat italyLonLat;
    private GPEventBus bus;

    @Inject
    public FeatureMapWidget(MapWidget mapWidget,
            FeatureMapLayerBuilder theMapLayerBuilder,
            Vector theVectorLayer,
            FeatureSelectHandler theSelectFeature,
            FeatureUnSelectHandler theUnSelectFeature,
            GetFeatureControlBuilder theFeatureControlBuilder,
            LonLat theItalyLonLat,
            GPEventBus bus) {
        super(true);
        this.mapWidget = mapWidget;
        this.mapLayerBuilder = theMapLayerBuilder;
        this.vectorLayer = theVectorLayer;
        this.selectFeature = theSelectFeature;
        this.unSelectFeature = theUnSelectFeature;
        this.featureControlBuilder = theFeatureControlBuilder;
        this.italyLonLat = theItalyLonLat;
        this.bus = bus;
    }

    @Override
    public void addComponent() {
        this.initMapWidget();

        super.add(this.mapWidget);
    }

    @Override
    public void initSize() {
    }

    @Override
    public void setPanelProperties() {
        super.setHeaderVisible(false);
    }

    private void resetMapWidget() {
        // REMOVE CONTROL GETFEATURE
        this.controlFeature.deactivate();
        this.mapWidget.getMap().removeControl(controlFeature);

        this.vectorLayer.destroyFeatures();

        if (wms != null) {
            this.mapWidget.getMap().removeLayer(wms);
        }

        this.mapWidget.getMap().removeLayer(vectorLayer);

        this.initMapWidget();
    }

    private void initMapWidget() {
        this.mapWidget.getMap().setCenter(italyLonLat, 4);
    }

    @Override
    public void reset() {
        this.resetMapWidget();
    }

    @Override
    public void bind(final GPLayerBean layer,
            final LayerSchemaDTO schema) {

        this.wms = this.mapLayerBuilder.buildLayer(layer);

        this.controlFeature = this.featureControlBuilder.buildControl(new GetFeatureModel() {
            @Override
            public String getFeatureNameSpace() {
                return layer instanceof GPVectorBean ? ((GPVectorBean) layer).getFeatureNameSpace()
                        : schema.getTargetNamespace();
            }

            @Override
            public String getFeatureType() {
                int pos = layer.getName().indexOf(":");

                return pos > 0 ? layer.getName().substring(pos + 1,
                                                           layer.getName().length()) : layer.getName();
            }

            @Override
            public String getSrsName() {
                return layer.getCrs();
            }

            @Override
            public String getGeometryName() {
                return layer instanceof GPVectorBean ? ((GPVectorBean) layer).getGeometryName()
                        : schema.getGeometry().getName();
            }

            @Override
            public WMS getWMSLayer() {
                return (WMS) wms;
            }
        });

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
    public void updateSize() {
        this.mapWidget.getMap().updateSize();
    }

    private void loadLayerOnMap() {
        this.mapWidget.getMap().addLayer(wms);
        this.mapWidget.getMap().addLayer(vectorLayer);

        this.mapWidget.getMap().addControl(controlFeature);

        controlFeature.getEvents().register("featureselected", this.wms,
                                            this.selectFeature);

        controlFeature.getEvents().register("featureunselected", this.wms,
                                            this.unSelectFeature);

        Bounds bb = ((WMS) this.wms).getOptions().getMaxExtent();

        this.mapWidget.getMap().zoomToExtent(bb);

        this.controlFeature.activate();
    }

    private void notifyStatus() {
        this.bus.fireEvent(
                new FeatureStatusBarEvent("WFS Layer loaded",
                                          FeatureStatusBarType.STATUS_OK));
    }
}
