/*
 *  geo-platform
 *  Rich webgis framework
 *  http://geo-plartform.org
 * ====================================================================
 *
 * Copyright (C) 2008-2011 geoSDI Group (CNR IMAA - Potenza - ITALY).
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
package org.geosdi.geoplatform.gui.featureinfo.cache;

import com.extjs.gxt.ui.client.Style.Scroll;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.google.gwt.user.client.ui.HTMLPanel;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import org.geosdi.geoplatform.gui.configuration.map.puregwt.MapHandlerManager;
import org.geosdi.geoplatform.gui.featureinfo.widget.factory.FeatureInfoControlFactory;
import org.geosdi.geoplatform.gui.featureinfo.widget.factory.FeatureInfoPanelFactory;
import org.geosdi.geoplatform.gui.puregwt.featureinfo.event.FeatureInfoShowWidgetEvent;
import org.gwtopenmaps.openlayers.client.control.WMSGetFeatureInfo;
import org.gwtopenmaps.openlayers.client.event.ControlActivateListener;
import org.gwtopenmaps.openlayers.client.event.ControlActivateListener.ControlActivateEvent;
import org.gwtopenmaps.openlayers.client.event.GetFeatureInfoListener;
import org.gwtopenmaps.openlayers.client.event.GetFeatureInfoListener.GetFeatureInfoEvent;
import org.gwtopenmaps.openlayers.client.event.NoGetFeatureInfoListener;
import org.gwtopenmaps.openlayers.client.event.NoGetFeatureInfoListener.NoGetFeatureInfoEvent;

/**
 *
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email  giuseppe.lascaleia@geosdi.org
 */
public class FeatureInfoFlyWeight {

    private static FeatureInfoFlyWeight instance = new FeatureInfoFlyWeight();
    
    private Map<String, IGPFeatureInfoElement> cache = new HashMap<String, IGPFeatureInfoElement>();
    private FeatureInfoShowWidgetEvent event = new FeatureInfoShowWidgetEvent();
    private int count = 0;
    
    private FeatureInfoFlyWeight(){}
    
    public static FeatureInfoFlyWeight getInstance(){
        return instance;
    }

    private void checkLastElement() {
        if (++count == cache.size()) {
            MapHandlerManager.fireEvent(event);
            count = 0;
        }
    }

    public IGPFeatureInfoElement get(String key) {
        IGPFeatureInfoElement element = null;
        if (this.cache.containsKey(key)) {
            element = this.cache.get(key);
        } else {
            element = new GPFeatureInfoElement(key);
            this.cache.put(key, element);
        }
        return element;
    }

    public Collection<IGPFeatureInfoElement> getCollection() {
        return this.cache.values();
    }

    private class GPFeatureInfoElement implements IGPFeatureInfoElement {

        private String urlServer;
        private WMSGetFeatureInfo infoControl;
        private ContentPanel infoPanel;
        private boolean isActive;

        public GPFeatureInfoElement(String theUrlServer) {
            this.urlServer = theUrlServer;
            this.createComponents();
        }

        @Override
        public WMSGetFeatureInfo getElementControl() {
            return this.infoControl;
        }

        @Override
        public ContentPanel getElementPanel() {
            return this.infoPanel;
        }

        @Override
        public boolean isActive() {
            return isActive;
        }

        private void createComponents() {
            this.infoControl = FeatureInfoControlFactory.createControl(urlServer);
            this.infoPanel = FeatureInfoPanelFactory.createPanel(urlServer,
                    urlServer);
            this.infoPanel.setScrollMode(Scroll.AUTOX);
            addFeatureListener();
        }

        private void addFeatureListener() {
            this.infoControl.addGetFeatureListener(new GetFeatureInfoListener() {

                @Override
                public void onGetFeatureInfo(GetFeatureInfoEvent eventObject) {
                    infoPanel.removeAll();
                    infoPanel.add(new HTMLPanel(eventObject.getText()));
                    isActive = true;
                    checkLastElement();
                }
            });

            this.infoControl.addNoGetFeatureListener(new NoGetFeatureInfoListener() {

                @Override
                public void onNoGetFeatureInfo(NoGetFeatureInfoEvent eventObject) {
                    infoPanel.removeAll();
                    isActive = false;
                    checkLastElement();
                }
            });
        }
    }
}
