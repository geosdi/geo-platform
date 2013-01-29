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
package org.geosdi.geoplatform.gui.client.widget.viewport;

import com.extjs.gxt.ui.client.Style;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.layout.FlowLayout;
import com.google.gwt.user.client.rpc.AsyncCallback;
import java.util.List;
import org.geosdi.geoplatform.gui.client.service.MapRemote;
import org.geosdi.geoplatform.gui.client.widget.GeoPlatformWindow;
import org.geosdi.geoplatform.gui.client.widget.SearchStatus;
import org.geosdi.geoplatform.gui.configuration.map.client.GPClientViewport;
import org.geosdi.geoplatform.gui.configuration.map.client.GPCoordinateReferenceSystem;
import org.geosdi.geoplatform.gui.configuration.map.client.geometry.BBoxClientInfo;
import org.geosdi.geoplatform.gui.configuration.map.puregwt.MapHandlerManager;
import org.geosdi.geoplatform.gui.configuration.map.puregwt.event.CreateViewportHandler;
import org.geosdi.geoplatform.gui.configuration.message.GeoPlatformMessage;
import org.geosdi.geoplatform.gui.impl.view.LayoutManager;
import org.geosdi.geoplatform.gui.model.GPLayerBean;
import org.gwtopenmaps.openlayers.client.Bounds;
import org.gwtopenmaps.openlayers.client.Map;
import org.gwtopenmaps.openlayers.client.Projection;

/**
 * @author Nazzareno Sileno - CNR IMAA geoSDI Group
 * @email nazzareno.sileno@geosdi.org
 */
public class ViewportWidget extends GeoPlatformWindow implements CreateViewportHandler {

    public static final short VIEWPORT_WIDGET_WIDTH = 690;
    public static final short VIEWPORT_WIDGET_HEIGHT = 430;
    private ContentPanel centralPanel;
    private ViewportGridFieldSet viewportGridFieldSet;
    private Map map;
    private GPClientViewport viewportToAdd;

    public ViewportWidget(boolean lazy, Map map) {
        super(lazy);
        this.map = map;
        this.viewportGridFieldSet = new ViewportGridFieldSet(this.map);
        MapHandlerManager.addHandler(CreateViewportHandler.TYPE, this);
    }

    @Override
    public void addComponent() {
        this.centralPanel = new ContentPanel(new FlowLayout(0));
        this.centralPanel.setHeaderVisible(Boolean.FALSE);
        this.centralPanel.setFrame(Boolean.TRUE);
        this.centralPanel.setSize(VIEWPORT_WIDGET_WIDTH - 15, VIEWPORT_WIDGET_HEIGHT - 20);
        this.centralPanel.add(this.viewportGridFieldSet);
        this.centralPanel.setScrollMode(Style.Scroll.NONE);
        super.add(this.centralPanel);
    }

    @Override
    public void initSize() {
        setSize(VIEWPORT_WIDGET_WIDTH, VIEWPORT_WIDGET_HEIGHT);
    }

    @Override
    public void setWindowProperties() {
        super.setHeading("Viewport Widget");
        super.setScrollMode(Style.Scroll.NONE);
        super.setResizable(Boolean.FALSE);
    }

    @Override
    public void finalizeInitOperations() {
        super.finalizeInitOperations();

    }

    @Override
    public void show() {
        ViewportWidget.super.show();
        this.centralPanel.mask("Loading Viewport...");
        MapRemote.Util.getInstance().loadViewportElements(new AsyncCallback<List<GPClientViewport>>() {
            @Override
            public void onFailure(Throwable caught) {
                GeoPlatformMessage.errorMessage("Error loading",
                        "An error occurred while making the requested connection.\n"
                        + "Verify network connections and try again."
                        + "\nIf the problem persists contact your system administrator.");
                LayoutManager.getInstance().getStatusMap().setStatus(
                        "Error loading the viewport elements.",
                        SearchStatus.EnumSearchStatus.STATUS_NO_SEARCH.toString());
                System.out.println("Error saving loading the viewport elements: " + caught.toString()
                        + " data: " + caught.getMessage());
            }

            @Override
            public void onSuccess(List<GPClientViewport> result) {
                viewportGridFieldSet.setViewportListStore(result);
                ViewportWidget.this.centralPanel.unmask();
                if (viewportToAdd != null) {
                    viewportGridFieldSet.addViewportElement(viewportToAdd);
                    viewportToAdd = null;
                }
            }
        });
    }

    @Override
    public void onCreateViewport(List<GPLayerBean> layerList, String viewportName) {
        Bounds bounds = ViewportUtility.calculateMaxBound(layerList, map);

        Projection currentProjection = new Projection(GPCoordinateReferenceSystem.WGS_84.getCode());
        Projection destinationProjection = new Projection(map.getProjection());
        bounds.transform(currentProjection, destinationProjection);

        double zoom = map.getZoomForExtent(bounds, Boolean.FALSE);

        bounds.transform(destinationProjection, currentProjection);

        BBoxClientInfo bbox = ViewportUtility.generateBBOXFromBounds(bounds);
        this.viewportToAdd = new GPClientViewport(viewportName,
                "Insert description", bbox, zoom, Boolean.FALSE);
        this.show();
    }
}
