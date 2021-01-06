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
package org.geosdi.geoplatform.gui.featureinfo.widget;

import com.extjs.gxt.ui.client.Style.Scroll;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.google.gwt.core.client.GWT;
import org.geosdi.geoplatform.gui.client.i18n.FeatureInfoModuleConstants;
import org.geosdi.geoplatform.gui.client.widget.GeoPlatformWindow;
import org.geosdi.geoplatform.gui.configuration.map.puregwt.MapHandlerManager;
import org.geosdi.geoplatform.gui.configuration.message.GeoPlatformMessage;
import org.geosdi.geoplatform.gui.featureinfo.cache.FeatureInfoFlyWeight;
import org.geosdi.geoplatform.gui.featureinfo.cache.IGPFeatureInfoElement;
import org.geosdi.geoplatform.gui.impl.map.GeoPlatformMap;
import org.geosdi.geoplatform.gui.puregwt.featureinfo.GPFeatureInfoHandler;
import org.geosdi.geoplatform.gui.shared.util.GPSharedUtils;
import org.gwtopenmaps.openlayers.client.Map;
import org.gwtopenmaps.openlayers.client.layer.Layer;

/**
 *
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class GPFeatureInfoWidget extends GeoPlatformWindow implements
        GPFeatureInfoHandler {

    private ContentPanel mainPanel;
    private final FeatureInfoCaller featureCaller;
    private final GeoPlatformMap mapWidget;

    public GPFeatureInfoWidget(GeoPlatformMap theMapWidget) {
        super(false);
        this.mapWidget = theMapWidget;
        this.featureCaller = new GPFeatureInfoCaller(this.mapWidget.getMap());
        MapHandlerManager.addHandler(GPFeatureInfoHandler.TYPE, this);
    }

    @Override
    public void addComponent() {
        this.mainPanel = new ContentPanel();
        this.mainPanel.setHeaderVisible(false);
        add(this.mainPanel);
    }

    @Override
    public void initSize() {
        super.setMinHeight(400);
        super.setWidth(400);
    }

    @Override
    public void setWindowProperties() {
        super.setClosable(true);
        super.setScrollMode(Scroll.AUTO);
        super.setResizable(Boolean.TRUE);
        super.setPlain(true);
        super.setModal(true);
        super.setPosition(240,60);
        super.setHeadingHtml(FeatureInfoModuleConstants.INSTANCE.GPFeatureInfoWidget_headingText());
    }

    @Override
    public void removeLayer(Layer layer) {
        if (FeatureInfoFlyWeight.getInstance().contains(layer)) {
            Map map = this.mapWidget.getMap();
            IGPFeatureInfoElement featureInfoElement = FeatureInfoFlyWeight.getInstance().get(layer);
            featureInfoElement.getElementControl().deactivate();
            map.removeControl(featureInfoElement.getElementControl());
            FeatureInfoFlyWeight.getInstance().remove(layer);
        }
    }

    @Override
    public void refreshFeatures(Layer layer) {
        FeatureInfoFlyWeight.getInstance().refreshFeatures(layer);
        IGPFeatureInfoElement element = FeatureInfoFlyWeight.getInstance().get(layer);
        Map map = this.mapWidget.getMap();
        map.addControl(element.getElementControl());
        if (this.featureCaller.isActivated()) {
            element.getElementControl().activate();
        }
    }

    @Override
    public void addLayer(Layer layer) {
        if (!FeatureInfoFlyWeight.getInstance().contains(layer)) {
            IGPFeatureInfoElement element = FeatureInfoFlyWeight.getInstance().get(layer);
            Map map = this.mapWidget.getMap();
            map.addControl(element.getElementControl());
            if (this.featureCaller.isActivated()) {
                element.getElementControl().activate();
            }
        }
    }

    @Override
    public void activateHandler() {
//        System.out.println("On activate handler");
        this.featureCaller.activateFeatureInfoControl();
    }

    @Override
    public void deactivateHandler() {
        this.featureCaller.deactivateFeatureInfoControl();
    }

    @Override
    public void cleanFeatureInfoCache() {
        this.featureCaller.cleanFeatureInfoCache();
    }

    @Override
    public void reset() {
        this.mainPanel.removeAll();
    }

    /**
     * @TODO Think a way to have more control when no Layers are on the map, to
     * show a message.
     *
     */
    @Override
    public void showInfoWidget() {
//        System.out.println("Showing the info widget");
        for (IGPFeatureInfoElement featureInfoElement : GPSharedUtils.safeCollection(
                FeatureInfoFlyWeight.getInstance().getCollection())) {
            if (featureInfoElement.isActive()) {
                this.mainPanel.add(featureInfoElement.getElementPanel());
            }
        }
        if (this.mainPanel.getItemCount() > 0) {
            super.show();
            super.setHeight(this.mainPanel.getHeight() < 600 ? this.mainPanel.getHeight() +50 : 600);
        } else {
            GeoPlatformMessage.alertMessage(FeatureInfoModuleConstants.INSTANCE.
                    GPFeatureInfoWidget_alertNoLayerTitleText(),
                    FeatureInfoModuleConstants.INSTANCE.GPFeatureInfoWidget_alertNoLayerBodyText());
        }
    }

    @Override
    protected void afterShow() {
        super.afterShow();
        super.layout(Boolean.TRUE);
    }

}
