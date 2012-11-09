/*
 *  geo-platform
 *  Rich webgis framework
 *  http://geo-platform.org
 * ====================================================================
 *
 * Copyright (C) 2008-2012 geoSDI Group (CNR IMAA - Potenza - ITALY).
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
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.util.Margins;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.Text;
import com.extjs.gxt.ui.client.widget.Window;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.button.ToggleButton;
import com.extjs.gxt.ui.client.widget.form.FieldSet;
import com.extjs.gxt.ui.client.widget.layout.BorderLayout;
import com.extjs.gxt.ui.client.widget.layout.BorderLayoutData;
import com.extjs.gxt.ui.client.widget.treepanel.TreePanel;
import com.google.common.collect.Lists;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Image;
import java.util.ArrayList;
import java.util.List;
import org.geosdi.geoplatform.gui.client.BasicWidgetResources;
import org.geosdi.geoplatform.gui.client.event.epsgcheck.IUploadEPSGCheckHandler;
import org.geosdi.geoplatform.gui.client.event.epsgcheck.UploadEPSGCheckEvent;
import org.geosdi.geoplatform.gui.client.event.shapepreview.IFeaturePreviewHandler;
import org.geosdi.geoplatform.gui.client.event.timeout.GPPublishShapePreviewEvent;
import org.geosdi.geoplatform.gui.client.event.timeout.IGPPublishShapePreviewHandler;
import org.geosdi.geoplatform.gui.client.model.PreviewLayer;
import org.geosdi.geoplatform.gui.client.model.PreviewLayerList;
import org.geosdi.geoplatform.gui.client.service.PublisherRemote;
import org.geosdi.geoplatform.gui.client.widget.SearchStatus.EnumSearchStatus;
import org.geosdi.geoplatform.gui.client.widget.fileupload.GPExtensions;
import org.geosdi.geoplatform.gui.client.widget.fileupload.GPFileUploader;
import org.geosdi.geoplatform.gui.client.widget.tree.store.puregwt.event.AddRasterFromPublisherEvent;
import org.geosdi.geoplatform.gui.configuration.message.GeoPlatformMessage;
import org.geosdi.geoplatform.gui.impl.map.event.GPLoginEvent;
import org.geosdi.geoplatform.gui.impl.view.LayoutManager;
import org.geosdi.geoplatform.gui.model.tree.AbstractFolderTreeNode;
import org.geosdi.geoplatform.gui.puregwt.GPHandlerManager;
import org.geosdi.geoplatform.gui.puregwt.layers.LayerHandlerManager;
import org.geosdi.geoplatform.gui.puregwt.session.TimeoutHandlerManager;
import org.geosdi.geoplatform.gui.shared.GPLayerType;
import org.geosdi.geoplatform.gui.utility.GPReloadURLException;
import org.geosdi.geoplatform.gui.utility.GPSessionTimeout;
import org.gwtopenmaps.openlayers.client.Bounds;
import org.gwtopenmaps.openlayers.client.Projection;
import org.gwtopenmaps.openlayers.client.layer.WMS;
import org.gwtopenmaps.openlayers.client.layer.WMSOptions;
import org.gwtopenmaps.openlayers.client.layer.WMSParams;

/**
 * @author Nazzareno Sileno - CNR IMAA geoSDI Group
 * @email nazzareno.sileno@geosdi.org
 */
public class GPPublisherWidget extends GeoPlatformWindow
        implements IFeaturePreviewHandler, IGPPublishShapePreviewHandler, IUploadEPSGCheckHandler {

    public final static int PUBLISHER_WIDGET_WIDTH = 600;
    public final static int PUBLISHER_WIDGET_HEIGHT = 500;
    private TreePanel tree;
//    private boolean mapInitialized;
    private ContentPanel centralPanel;
    private PreviewWidget shpPreviewWidget;
    private GPFileUploader fileUploader;
    private FieldSet southPanel;
    private Image centralImage;
    private Bounds bounds;
    private GPPublishShapePreviewEvent publishShapePreviewEvent = new GPPublishShapePreviewEvent();
    private List<PreviewLayer> layerList = Lists.newArrayList();
    private Button publishButton;
    private HTML htmlPanel;
    private Window htmlWindow = new Window();
    private Text uploadMessage = new Text("Select a file to show in preview:");
    private ToggleButton toggleButtonClusterReload;
    private UploadEPSGCheckEvent epsgCheckEvent = new UploadEPSGCheckEvent();
    private EPSGTablePanel epsgTable;

    public GPPublisherWidget(boolean lazy, TreePanel theTree) {
        super(lazy);
        this.tree = theTree;
        GPHandlerManager.addHandler(IFeaturePreviewHandler.TYPE, this);
        GPHandlerManager.addHandler(IUploadEPSGCheckHandler.TYPE, this);
        TimeoutHandlerManager.addHandler(IGPPublishShapePreviewHandler.TYPE, this);
    }

    @Override
    public void setWindowProperties() {
        super.setHeading("Geotiff - Shape Files Uploader");
        super.setResizable(false);
        super.setLayout(new BorderLayout());
        super.setModal(false);
        super.setCollapsible(true);
        super.setPlain(true);
        this.htmlWindow.setHeading("Cluster Reload Result");
        this.epsgTable = new EPSGTablePanel();
//        this.addListener(Events.Show, new Listener<WindowEvent>() {
//
//            @Override
//            public void handleEvent(WindowEvent be) {
//                if (mapInitialized) {
//                    shpPreviewWidget.getMapPreview().getMap().zoomToMaxExtent();
//                    shpPreviewWidget.getMapPreview().getMap().updateSize();
//                }
//            }
//        });
    }

    @Override
    public void showEPSGTable(String jsonString) {
        StringBuilder layerProblems = new StringBuilder();
        List<PreviewLayer> epsgLayerList = Lists.newArrayList();
        PreviewLayerList previewLayers = PreviewLayerList.JSON.read(jsonString);
        for (PreviewLayer previewLayer : previewLayers.getPreviewLayers()) {
            if (previewLayer.getMessage().contains("Some problems")) {
                layerProblems.append(previewLayer.getTitle()).append("\n");
            } else {
                epsgLayerList.add(previewLayer);
            }
        }
        if (epsgLayerList.size() > 0) {
            this.epsgTable.populateStore(epsgLayerList);
            centralPanel.removeAll();
            centralPanel.add(this.epsgTable);//aggiungere tabella epsg
            this.epsgTable.layout();
            centralPanel.layout();
        }
        this.manageLayerProblems(layerProblems);
    }

    private void manageLayerProblems(StringBuilder layerProblems) {
        if (layerProblems.length() != 0) {
            GeoPlatformMessage.alertMessage("Upload Shape Error",
                    "Some problems occurred with following layers:\n" + layerProblems);
        }
    }

    @Override
    public void showLayerPreview(String jsonString) {
        StringBuilder layerProblems = new StringBuilder();
        PreviewLayerList previewLayers = PreviewLayerList.JSON.read(jsonString);
        for (PreviewLayer previewLayer : previewLayers.getPreviewLayers()) {
            if (!previewLayer.getMessage().contains("Some problems")) {
                previewLayer.setLayerType(GPLayerType.WMS);
                layerList.add(previewLayer);
                WMS wmsLayer = this.generateLayer(previewLayer);
                shpPreviewWidget.getMapPreview().getMap().addLayer(wmsLayer);
            } else {
                layerProblems.append(previewLayer.getTitle()).append("\n");
            }
        }
        if (shpPreviewWidget.getMapPreview().getMap().getNumLayers() > 1) {
            centralPanel.removeAll();
            centralPanel.add(shpPreviewWidget.getMapPreview());
            shpPreviewWidget.getMapPreview().getMap().zoomToExtent(bounds);
            shpPreviewWidget.getMapPreview().getMap().updateSize();
            centralPanel.layout();
            publishButton.enable();
        }
        this.manageLayerProblems(layerProblems);
    }

    public WMS generateLayer(PreviewLayer previewLayer) {
        WMSParams wmsParams = new WMSParams();
        wmsParams.setFormat("image/png");
        wmsParams.setLayers(previewLayer.getName());
        wmsParams.setStyles(previewLayer.getStyleName());
        wmsParams.setTransparent(Boolean.TRUE);

        Double lowerX = previewLayer.getLowerX();
        Double lowerY = previewLayer.getLowerY();
        Double upperX = previewLayer.getUpperX();
        Double upperY = previewLayer.getUpperY();

        Bounds layerBounds = new Bounds(lowerX, lowerY, upperX, upperY);

//        System.out.println("layerBounds.toString(): " + layerBounds.toString());

//        System.out.println("layer crs: " + previewLayer.getCrs());

        layerBounds.transform(new Projection(previewLayer.getCrs()), new Projection(
                shpPreviewWidget.getMapPreview().getMap().getProjection()));
//        System.out.println("layer bounds transformated: " + layerBounds);

        WMSOptions wmsOption = new WMSOptions();
        wmsOption.setMaxExtent(layerBounds);
        wmsOption.setIsBaseLayer(false);
        wmsOption.setDisplayInLayerSwitcher(true);
        this.updateMapBounds(layerBounds);
        return new WMS(previewLayer.getName(), previewLayer.getDataSource(),
                wmsParams, wmsOption);
    }

    private void updateMapBounds(Bounds layerBounds) {
        if (bounds == null) {
            bounds = layerBounds;
        } else {
            bounds.extend(layerBounds);
        }
    }

    @Override
    public void reset() {
        centralPanel.removeAll();
        centralPanel.add(this.centralImage);
        centralPanel.layout();
        publishButton.disable();
        fileUploader.getComponent().reset();
        shpPreviewWidget.getMapPreview().getMap().removeOverlayLayers();
        bounds = null;
        layerList.clear();
    }

    @Override
    public void publishShapePreview() {
        publishButton.fireEvent(Events.Select);
    }

    @Override
    public void addComponent() {
        this.addCentralPanel();
        this.addSouthPanel();
        publishButton = new Button("Add on Tree", BasicWidgetResources.ICONS.done());
        publishButton.addSelectionListener(new SelectionListener<ButtonEvent>() {
            @Override
            public void componentSelected(ButtonEvent ce) {
                if (tree.getSelectionModel().getSelectedItem() instanceof AbstractFolderTreeNode) {
                    //expander.checkNodeState();
                    List<String> layersName = new ArrayList<String>();
                    for (PreviewLayer layer : layerList) {
                        layersName.add(layer.getName());
                    }
                    boolean reloadCluster = toggleButtonClusterReload.isPressed();
                    PublisherRemote.Util.getInstance().publishLayerPreview(layersName, reloadCluster,
                            new AsyncCallback<String>() {
                                @Override
                                public void onFailure(Throwable caught) {
                                    if (caught.getCause() instanceof GPSessionTimeout) {
                                        GPHandlerManager.fireEvent(new GPLoginEvent(publishShapePreviewEvent));
                                    } else if (caught.getCause() instanceof GPReloadURLException) {
                                        GeoPlatformMessage.errorMessage("Error Reloading CLuster",
                                                "An error occurred reloading cluster configuration: "
                                                + caught.getCause().getMessage());
                                        LayoutManager.getInstance().getStatusMap().setStatus(
                                                "Error Publishing previewed shape.",
                                                EnumSearchStatus.STATUS_NO_SEARCH.toString());
                                        System.out.println("Error Publishing previewed shape: " + caught.toString()
                                                + " data: " + caught.getMessage());
                                    } else {
                                        GeoPlatformMessage.errorMessage("Error Publishing",
                                                "An error occurred while making the requested connection.\n"
                                                + "Verify network connections and try again."
                                                + "\nIf the problem persists contact your system administrator.");
                                        LayoutManager.getInstance().getStatusMap().setStatus(
                                                "Error Publishing previewed shape.",
                                                EnumSearchStatus.STATUS_NO_SEARCH.toString());
                                        System.out.println("Error Publishing previewed shape: " + caught.toString()
                                                + " data: " + caught.getMessage());
                                    }
                                }

                                @Override
                                public void onSuccess(String result) {
                                    LayerHandlerManager.fireEvent(new AddRasterFromPublisherEvent(layerList));
                                    reset();
                                    System.out.println("Result: " + result);
                                    if (toggleButtonClusterReload.isPressed()) {
                                        if (result != null) {
                                            htmlPanel = new HTML(result);
                                        } else {
                                            htmlPanel = new HTML("Realod without result: may be some problems?");
                                        }
                                        htmlWindow.removeAll();
                                        htmlWindow.add(htmlPanel);
                                        htmlWindow.show();
                                    }
                                    LayoutManager.getInstance().getStatusMap().setStatus(
                                            "Shape\\s published successfully: remember to save the new tree state.",
                                            EnumSearchStatus.STATUS_SEARCH.toString());
                                }
                            });
                } else {
                    GeoPlatformMessage.alertMessage("Shaper Preview",
                            "You can put layers into Folders only.\n"
                            + "Please select the correct node from the tree.");
                }
            }
        });
        publishButton.disable();
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
        shpPreviewWidget = new PreviewWidget();
        centralPanel = new ContentPanel();
        centralPanel.setHeaderVisible(false);
        BorderLayoutData centerData = new BorderLayoutData(LayoutRegion.CENTER);
        centerData.setMargins(new Margins(5, 5, 0, 5));
        centralImage = BasicWidgetResources.ICONS.geo_platform_logo().createImage();
        centralPanel.add(centralImage);
        super.add(centralPanel, centerData);
    }

    private void addSouthPanel() {
        fileUploader = new GPFileUploader("UploadServlet", this.epsgCheckEvent,
                GPExtensions.ZIP, GPExtensions.TIF, GPExtensions.TIFF);
        southPanel = new FieldSet();
        southPanel.setHeight(78);
        southPanel.setWidth(522);
        southPanel.setLayout(new BorderLayout());

        BorderLayoutData uploadMessageOnTop = new BorderLayoutData(LayoutRegion.NORTH);
        uploadMessageOnTop.setMargins(new Margins(3, 150, 0, 151));
        uploadMessageOnTop.setSize(13);
        southPanel.add(uploadMessage, uploadMessageOnTop);

        BorderLayoutData centerFileUploader = new BorderLayoutData(LayoutRegion.CENTER);
        centerFileUploader.setMargins(new Margins(1, 150, 9, 151));

        BorderLayoutData toggleButtonPosition = new BorderLayoutData(LayoutRegion.EAST, 105);
        toggleButtonPosition.setMargins(new Margins(22, 10, 5, 1));

        this.toggleButtonClusterReload = new ToggleButton("Reload Cluster");
        this.toggleButtonClusterReload.setTitle("If enabled: the cluster configuration will be reloaded after layers publishing");
        southPanel.add(fileUploader.getComponent(), centerFileUploader);
        southPanel.add(this.toggleButtonClusterReload, toggleButtonPosition);
        southPanel.setHeading("File uploader");
        BorderLayoutData southData = new BorderLayoutData(LayoutRegion.SOUTH, 100);
        southData.setMargins(new Margins(5, 20, 5, 20));
        super.add(southPanel, southData);
    }

    @Override
    public void show() {
        if (!super.isInitialized()) {
            super.init();
        }
        super.show();
    }

    @Override
    public void initSize() {
        //Warning: changing window size will be necessary change panel's size also.
        super.setSize(GPPublisherWidget.PUBLISHER_WIDGET_WIDTH, GPPublisherWidget.PUBLISHER_WIDGET_HEIGHT);
    }
}