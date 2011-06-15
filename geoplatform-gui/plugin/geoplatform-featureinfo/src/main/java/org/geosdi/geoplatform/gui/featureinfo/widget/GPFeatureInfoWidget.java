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
package org.geosdi.geoplatform.gui.featureinfo.widget;

import com.extjs.gxt.ui.client.Style.Scroll;
import com.extjs.gxt.ui.client.event.WindowEvent;
import com.extjs.gxt.ui.client.event.WindowListener;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.Window;
import java.util.Iterator;
import org.geosdi.geoplatform.gui.configuration.map.puregwt.MapHandlerManager;
import org.geosdi.geoplatform.gui.configuration.message.GeoPlatformMessage;
import org.geosdi.geoplatform.gui.featureinfo.cache.FeatureInfoFlyWeight;
import org.geosdi.geoplatform.gui.featureinfo.cache.IGPFeatureInfoElement;
import org.geosdi.geoplatform.gui.impl.map.GeoPlatformMap;
import org.geosdi.geoplatform.gui.puregwt.featureinfo.GPFeatureInfoHandler;

/**
 *
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email  giuseppe.lascaleia@geosdi.org
 */
public class GPFeatureInfoWidget extends Window implements GPFeatureInfoHandler {

    private boolean initialized;
    private ContentPanel mainPanel;
    private FeatureInfoFlyWeight featureCache = new FeatureInfoFlyWeight();
    private GeoPlatformMap mapWidget;

    public GPFeatureInfoWidget(GeoPlatformMap theMapWidget) {
        this.initializeWindow();
        this.initMainPanel();
        this.mapWidget = theMapWidget;
        MapHandlerManager.addHandler(GPFeatureInfoHandler.TYPE, this);
    }

    @Override
    public void activateHandler() {
        if (!isInitialized()) {
            loadServers();
        } else {
            activateFeatureInfoControl();
        }
    }

    @Override
    public void deactivateHandler() {
        for (Iterator<IGPFeatureInfoElement> it = featureCache.getCollection().iterator(); it.hasNext();) {
            it.next().getElementControl().deactivate();
        }
    }

    /**
     * 
     */
    private void initializeWindow() {
        super.setClosable(true);
        super.setScrollMode(Scroll.AUTO);
        super.setHeight(200);
        super.setWidth(400);
        super.setResizable(true);
        super.setPlain(true);
        super.setModal(true);

        super.addWindowListener(new WindowListener() {

            @Override
            public void windowHide(WindowEvent we) {
                resetMainPanel();
            }
        });

        super.setMaximizable(false);
        super.setHeading("Get Feature Info");
    }

    private void initMainPanel() {
        this.mainPanel = new ContentPanel();
        this.mainPanel.setHeaderVisible(false);

        add(this.mainPanel);
    }

    public void resetMainPanel() {
        this.mainPanel.removeAll();
    }

    @Override
    public void showInfoWidget() {
        for (Iterator<IGPFeatureInfoElement> it = featureCache.getCollection().iterator(); it.hasNext();) {
            IGPFeatureInfoElement element = it.next();
            System.out.println("ELEMENT *********************** " + element.isActive());
            if (element.isActive()) {
                this.mainPanel.add(element.getElementPanel());
            }
        }

        if (this.mainPanel.getItemCount() > 0) {
            super.show();
        } else {
            GeoPlatformMessage.alertMessage("GeoPlatform Feature Widget",
                    "There are no layers to show Info.");
        }
    }

    public boolean isInitialized() {
        return initialized;
    }

    private void loadServers() {
        // TODO : HERE THE CALL TO THE OGC_SERVICE TO LOAD ALL URL SERVERS FOR USER
        IGPFeatureInfoElement element = this.featureCache.get(
                "http://maps.telespazio.it/dpc/dpc-wms");
        
        this.mapWidget.getMap().addControl(element.getElementControl());
        element.getElementControl().activate();
        

        IGPFeatureInfoElement element1 = this.featureCache.get(
                "http://dpc.geosdi.org/geoserver/wms");
        
        this.mapWidget.getMap().addControl(element1.getElementControl());
        element1.getElementControl().activate();
    }

    private void activateFeatureInfoControl() {
        for (Iterator<IGPFeatureInfoElement> it = featureCache.getCollection().iterator(); it.hasNext();) {
            it.next().getElementControl().activate();
        }
    }
}
