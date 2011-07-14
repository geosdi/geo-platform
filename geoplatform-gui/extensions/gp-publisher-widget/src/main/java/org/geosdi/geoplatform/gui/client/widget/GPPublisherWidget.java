/*
 *  geo-platform
 *  Rich webgis framework
 *  http://geo-platform.org
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
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.event.WindowEvent;
import com.extjs.gxt.ui.client.util.Margins;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.FieldSet;
import com.extjs.gxt.ui.client.widget.layout.BorderLayout;
import com.extjs.gxt.ui.client.widget.layout.BorderLayoutData;
import com.extjs.gxt.ui.client.widget.treepanel.TreePanel;
import com.google.gwt.user.client.ui.Image;
import org.geosdi.geoplatform.gui.client.BasicWidgetResources;
import org.geosdi.geoplatform.gui.client.event.IUploadPreviewHandler;
import org.geosdi.geoplatform.gui.client.model.WMSPreview;
import org.geosdi.geoplatform.gui.client.widget.fileupload.GPExtensions;
import org.geosdi.geoplatform.gui.client.widget.fileupload.GPFileUploader;
import org.geosdi.geoplatform.gui.puregwt.GPHandlerManager;
import org.gwtopenmaps.openlayers.client.Bounds;
import org.gwtopenmaps.openlayers.client.Projection;
import org.gwtopenmaps.openlayers.client.layer.WMS;
import org.gwtopenmaps.openlayers.client.layer.WMSOptions;
import org.gwtopenmaps.openlayers.client.layer.WMSParams;

/**
 * @author Nazzareno Sileno - CNR IMAA geoSDI Group
 * @email nazzareno.sileno@geosdi.org
 */
public class GPPublisherWidget extends GeoPlatformWindow implements IUploadPreviewHandler {

    private TreePanel tree;
    private boolean mapInitialized;
    private ContentPanel centralPanel;
    private ShapePreviewWidget shpPreviewWidget;
    private GPFileUploader fileUploader;
    private FieldSet southPanel;
    private Image centralImage;
    private Bounds bbox;

    public GPPublisherWidget(boolean lazy, TreePanel theTree) {
        super(lazy);
        this.tree = theTree;
        GPHandlerManager.addHandler(IUploadPreviewHandler.TYPE, this);
    }

    @Override
    public void setWindowProperties() {
        super.setHeading("Shape Files Uploader");
        setResizable(false);
        setLayout(new BorderLayout());
        setModal(false);
        setCollapsible(true);
        setPlain(true);
        this.addListener(Events.Show, new Listener<WindowEvent>() {

            @Override
            public void handleEvent(WindowEvent be) {
                if (mapInitialized) {
                    shpPreviewWidget.getMapPreview().getMap().zoomToMaxExtent();
                    shpPreviewWidget.getMapPreview().getMap().updateSize();
                }
            }
        });
    }

    @Override
    public void showLayerPreview(String jsonString) {
        this.centralPanel.removeAll();
        WMS previewLayer = this.generateLayer(jsonString);
        this.shpPreviewWidget.getMapPreview().getMap().addLayer(previewLayer);
        this.centralPanel.add(this.shpPreviewWidget.getMapPreview());
        shpPreviewWidget.getMapPreview().getMap().zoomToExtent(bbox);
        shpPreviewWidget.getMapPreview().getMap().updateSize();
        this.centralPanel.layout();
        System.out.println("Funziona la preview");
    }

    public WMS generateLayer(String jsonString) {
        WMSPreview wmsPreview = WMSPreview.JSON.read(jsonString);
        System.out.println("wmsPreview toString: " + wmsPreview);
        WMSParams wmsParams = new WMSParams();
        wmsParams.setFormat("image/png");
        wmsParams.setLayers(wmsPreview.getLayerName());
        wmsParams.setStyles("");
        wmsParams.setIsTransparent(true);
        
        Double lowerX = wmsPreview.getLowerX();
        Double lowerY = wmsPreview.getLowerY();
        Double upperX = wmsPreview.getUpperX();
        Double upperY = wmsPreview.getUpperY();
        
        this.bbox = new Bounds(lowerX, lowerY, upperX, upperY);

        bbox.transform(new Projection(wmsPreview.getCrs()), new Projection(
                this.shpPreviewWidget.getMapPreview().getMap().getProjection()));

        wmsParams.setMaxExtent(bbox);

        WMSOptions wmsOption = new WMSOptions();
        wmsOption.setIsBaseLayer(false);
        wmsOption.setDisplayInLayerSwitcher(false);

        return new WMS(wmsPreview.getLayerName(), wmsPreview.getUrl(),
                wmsParams, wmsOption);
    }

//    private PreviewLayer generateLayer(Map<String, Object> jsonMap) {
//        String label = (String) jsonMap.get("layerName");
//        String title = (String) jsonMap.get("layerName");
//        String name = (String) jsonMap.get("layerName");
//        String abstractText = (String) jsonMap.get("");
//        String dataSource = (String) jsonMap.get("url");
//        String crs = (String) jsonMap.get("crs");
//        Number lowerX = (Number) jsonMap.get("lowerX");
//        Number lowerY = (Number) jsonMap.get("lowerY");
//        Number upperX = (Number) jsonMap.get("upperX");
//        Number upperY = (Number) jsonMap.get("upperY");
//        BboxClientInfo bbox = new BboxClientInfo(lowerX.doubleValue(), lowerY.doubleValue(), upperX.doubleValue(), upperY.doubleValue());
//        PreviewLayer previewLayer = new PreviewLayer(label, title, name, abstractText, dataSource, crs, bbox, GPLayerType.RASTER);
//
//        //layer.setBbox(new BboxClientInfo(lowerX.doubleValue(), jsonMap.get("lowerX"), jsonMap.get("lowerX"), jsonMap.get("lowerX")));
//        return previewLayer;
//    }
    @Override
    public void reset() {
        this.centralPanel.removeAll();
        this.centralPanel.add(this.centralImage);
        this.centralPanel.layout();
        this.fileUploader.getComponent().reset();
    }

    @Override
    public void addComponent() {
        this.addCentralPanel();
        this.addSouthPanel();
        Button publishButton = new Button("Add on Tree", BasicWidgetResources.ICONS.done());
        publishButton.addSelectionListener(new SelectionListener<ButtonEvent>() {

            @Override
            public void componentSelected(ButtonEvent ce) {
                //TODO: add shape on tree
            }
        });
        super.addButton(publishButton);
        Button resetButton = new Button("Reset", BasicWidgetResources.ICONS.cancel());
        resetButton.addSelectionListener(new SelectionListener<ButtonEvent>() {

            @Override
            public void componentSelected(ButtonEvent ce) {
                reset();
            }
        });
        super.addButton(resetButton);
    }

    private void addCentralPanel() {
        this.shpPreviewWidget = new ShapePreviewWidget();
        this.centralPanel = new ContentPanel();
        this.centralPanel.setHeaderVisible(false);
        BorderLayoutData centerData = new BorderLayoutData(LayoutRegion.CENTER);
        centerData.setMargins(new Margins(5, 5, 0, 5));
        this.centralImage = BasicWidgetResources.ICONS.geo_platform_logo().createImage();
        this.centralPanel.add(this.centralImage);
        super.add(this.centralPanel, centerData);
    }

    private void addSouthPanel() {
        this.fileUploader = new GPFileUploader("UploadServlet", GPExtensions.zip);
        this.southPanel = new FieldSet();
        this.southPanel.setHeight(78);
        this.southPanel.setWidth(522);
        this.southPanel.setLayout(new BorderLayout());
        BorderLayoutData centerFileUploader = new BorderLayoutData(LayoutRegion.CENTER);
        centerFileUploader.setMargins(new Margins(10, 150, 9, 151));
        this.southPanel.add(this.fileUploader.getComponent(), centerFileUploader);
        this.southPanel.setHeading("File uploader");
        this.southPanel.add(this.southPanel);
        BorderLayoutData southData = new BorderLayoutData(LayoutRegion.SOUTH, 100);
        southData.setMargins(new Margins(5, 20, 5, 20));
        super.add(this.southPanel, southData);
    }

    @Override
    public void show() {
        if (!isInitialized()) {
            super.init();
        }
        super.show();
    }

    @Override
    public void initSize() {
        //Warning: changing window size will be necessary change panel's size also.
        super.setSize(600, 500);
    }
}