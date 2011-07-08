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
package org.geosdi.geoplatform.gui.client.widget;

import com.extjs.gxt.ui.client.Style.LayoutRegion;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.WindowEvent;
import com.extjs.gxt.ui.client.util.Margins;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.Window;
import com.extjs.gxt.ui.client.widget.form.FieldSet;
import com.extjs.gxt.ui.client.widget.layout.BorderLayout;
import com.extjs.gxt.ui.client.widget.layout.BorderLayoutData;
import com.extjs.gxt.ui.client.widget.treepanel.TreePanel;
import com.google.gwt.user.client.ui.Image;
import org.geosdi.geoplatform.gui.client.BasicWidgetResources;
import org.geosdi.geoplatform.gui.client.widget.fileupload.GPExtensions;
import org.geosdi.geoplatform.gui.client.widget.fileupload.GPFileUploader;

/**
 * @author Nazzareno Sileno - CNR IMAA geoSDI Group
 * @email nazzareno.sileno@geosdi.org
 */

public class GPPublisherWidget extends Window {

    private TreePanel tree;
    private boolean initialized;
    private boolean mapInitialized;
    private ContentPanel centralPanel;
    private ShapePreviewWidget shpPreviewWidget;
    private GPFileUploader fileUploader;
    private FieldSet southPanel;

    public GPPublisherWidget(boolean lazy, TreePanel theTree) {
        if (!lazy) {
            init();
        }
        this.tree = theTree;
    }

    private void init() {
        if (!isInitialized()) {
            initializeWindow();
            initComponents();
            this.initialized = true;
        }
    }

    private void initializeWindow() {
        super.setSize(600, 500);
        super.setHeading("Shape Files Uploader");
        setResizable(false);
        setLayout(new BorderLayout());
        setModal(false);
        setCollapsible(true);
        setPlain(true);
        this.addListener(Events.Show, new Listener<WindowEvent>() {

            @Override
            public void handleEvent(WindowEvent be) {
                if(mapInitialized){
                shpPreviewWidget.getMapPreview().getMap().zoomToMaxExtent();
                shpPreviewWidget.getMapPreview().getMap().updateSize();
                }
            }
        });
    }

    private void initComponents() {
        this.centralPanel = new ContentPanel();
        this.centralPanel.setHeaderVisible(false);
        this.fileUploader = new GPFileUploader("UploadServlet", GPExtensions.shp, GPExtensions.zip,
                GPExtensions.dbf, GPExtensions.prj, GPExtensions.shx);
        BorderLayoutData centerData = new BorderLayoutData(LayoutRegion.CENTER);
        centerData.setMargins(new Margins(5));
        //this.centralPanel.addText("No Preview Available. \nPlease upload a shapefile for the preview.");
        Image image = BasicWidgetResources.ICONS.geo_platform_logo().createImage();
        this.centralPanel.add(image);
        super.add(this.centralPanel, centerData);
        this.southPanel = new FieldSet();
        this.southPanel.add(this.fileUploader.getComponent());
        this.southPanel.setHeading("File uploader");
        this.southPanel.setWidth("350px");
        BorderLayoutData southData = new BorderLayoutData(LayoutRegion.SOUTH, 100);
        southData.setMargins(new Margins(20, 187, 20, 187));
        super.add(this.southPanel, southData);
    }

    private void resetComponents() {
        this.fileUploader.getComponent().reset();
    }

    public void showLayerPreview() {
        this.shpPreviewWidget = new ShapePreviewWidget();
        this.centralPanel.removeAll();
        this.centralPanel.add(this.shpPreviewWidget.getMapPreview());
    }

    @Override
    public void show() {
        if (!isInitialized()) {
            this.init();
        }
        super.show();
    }

    /**
     * @return the initialized
     */
    public boolean isInitialized() {
        return initialized;
    }
}
