/**
 *
 *    geo-platform
 *    Rich webgis framework
 *    http://geo-platform.org
 *   ====================================================================
 *
 *   Copyright (C) 2008-2015 geoSDI Group (CNR IMAA - Potenza - ITALY).
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
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Image;
import java.util.List;
import org.geosdi.geoplatform.gui.client.BasicWidgetResources;
import org.geosdi.geoplatform.gui.client.command.publish.basic.PublishLayerPreviewRequest;
import org.geosdi.geoplatform.gui.client.command.publish.basic.PublishLayerPreviewResponse;
import org.geosdi.geoplatform.gui.client.event.epsgcheck.IUploadEPSGCheckHandler;
import org.geosdi.geoplatform.gui.client.event.epsgcheck.UploadEPSGCheckEvent;
import org.geosdi.geoplatform.gui.client.event.shapepreview.IFeaturePreviewHandler;
import org.geosdi.geoplatform.gui.client.event.timeout.GPPublishShapePreviewEvent;
import org.geosdi.geoplatform.gui.client.event.timeout.IGPPublishShapePreviewHandler;
import org.geosdi.geoplatform.gui.client.i18n.PublisherWidgetConstants;
import org.geosdi.geoplatform.gui.client.i18n.PublisherWidgetMessages;
import org.geosdi.geoplatform.gui.client.i18n.buttons.ButtonsConstants;
import org.geosdi.geoplatform.gui.client.i18n.windows.WindowsConstants;
import org.geosdi.geoplatform.gui.client.model.PreviewLayer;
import org.geosdi.geoplatform.gui.client.model.PreviewLayerList;
import org.geosdi.geoplatform.gui.client.widget.SearchStatus.EnumSearchStatus;
import org.geosdi.geoplatform.gui.client.widget.fileupload.GPExtensions;
import org.geosdi.geoplatform.gui.client.widget.fileupload.GPFileUploader;
import org.geosdi.geoplatform.gui.client.widget.tree.store.puregwt.event.AddRasterFromPublisherEvent;
import org.geosdi.geoplatform.gui.command.api.ClientCommandDispatcher;
import org.geosdi.geoplatform.gui.command.api.GPClientCommand;
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
        implements IFeaturePreviewHandler, IGPPublishShapePreviewHandler,
        IUploadEPSGCheckHandler {

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
    private GPPublishShapePreviewEvent publishShapePreviewEvent =
            new GPPublishShapePreviewEvent();
    private List<PreviewLayer> layerList = Lists.<PreviewLayer>newArrayList();
    private Button publishButton;
    private HTML htmlPanel;
    private Window htmlWindow = new Window();
    private Text uploadMessage = new Text(PublisherWidgetConstants.INSTANCE.
            GPPublisherWidget_uploadMessageText() + ":");
    private ToggleButton toggleButtonClusterReload;
    private UploadEPSGCheckEvent epsgCheckEvent = new UploadEPSGCheckEvent();
    private EPSGTablePanel epsgTable;
    private PublishLayerPreviewRequest publishLayerRequest = GWT.
            <PublishLayerPreviewRequest>create(PublishLayerPreviewRequest.class);

    public GPPublisherWidget(boolean lazy, TreePanel theTree) {
        super(lazy);
        this.tree = theTree;
        GPHandlerManager.addHandler(IFeaturePreviewHandler.TYPE, this);
        GPHandlerManager.addHandler(IUploadEPSGCheckHandler.TYPE, this);
        TimeoutHandlerManager.addHandler(IGPPublishShapePreviewHandler.TYPE,
                this);
    }

    @Override
    public void setWindowProperties() {
        super.setHeadingHtml(PublisherWidgetConstants.INSTANCE.
                GPPublisherWidget_headingText());
        super.setResizable(false);
        super.setLayout(new BorderLayout());
        super.setModal(false);
        super.setCollapsible(true);
        super.setPlain(true);
        this.htmlWindow.setHeadingHtml(PublisherWidgetConstants.INSTANCE.
                GPPublisherWidget_htmlWindowHeadingText());
        this.epsgTable = new EPSGTablePanel();
    }

    @Override
    public void showEPSGTable(String jsonString) {
        StringBuilder layerProblems = new StringBuilder();
        List<PreviewLayer> epsgLayerList = Lists.<PreviewLayer>newArrayList();
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
            GeoPlatformMessage.alertMessage(PublisherWidgetConstants.INSTANCE.
                    GPPublisherWidget_errorUploadShapeTitleText(),
                    PublisherWidgetMessages.INSTANCE.
                    GPPublisherWidget_errorUploadShapeBodyMessage(layerProblems.toString()));
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

        layerBounds.transform(new Projection(previewLayer.getCrs()),
                new Projection(
                shpPreviewWidget.getMapPreview().getMap().getProjection()));

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
        publishButton = new Button(ButtonsConstants.INSTANCE.addOnTreeText(),
                BasicWidgetResources.ICONS.done());
        publishButton.addSelectionListener(
                new SelectionListener<ButtonEvent>() {
            @Override
            public void componentSelected(ButtonEvent ce) {
                if (tree.getSelectionModel().getSelectedItem() instanceof AbstractFolderTreeNode) {
                    //expander.checkNodeState();
                    List<String> layersName = Lists.<String>newArrayList();
                    for (PreviewLayer layer : layerList) {
                        layersName.add(layer.getName());
                    }
                    boolean reloadCluster = toggleButtonClusterReload.
                            isPressed();

                    publishLayerRequest.setLayerList(layersName);
                    publishLayerRequest.setReloadCluster(reloadCluster);

                    ClientCommandDispatcher.getInstance().execute(
                            new GPClientCommand<PublishLayerPreviewResponse>() {
                        private static final long serialVersionUID = -7646291858544319457L;

                        {
                            super.setCommandRequest(publishLayerRequest);
                        }

                        @Override
                        public void onCommandSuccess(
                                PublishLayerPreviewResponse response) {
                            LayerHandlerManager.fireEvent(
                                    new AddRasterFromPublisherEvent(
                                    layerList));
                            reset();
                            System.out.println("Response: " + response);
                            if (toggleButtonClusterReload.isPressed()) {
                                if ((response != null) && (response.getResult() != null)) {
                                    htmlPanel = new HTML(response.getResult());
                                } else {
                                    htmlPanel = new HTML(
                                            PublisherWidgetConstants.INSTANCE.
                                            GPPublisherWidget_htmlPanelNoResultReloadText());
                                }
                                htmlWindow.removeAll();
                                htmlWindow.add(htmlPanel);
                                htmlWindow.show();
                            }
                            LayoutManager.getInstance().getStatusMap().
                                    setStatus(
                                    PublisherWidgetConstants.INSTANCE.
                                    GPPublisherWidget_statusShapePublishedSuccesfullyText(),
                                    EnumSearchStatus.STATUS_SEARCH.
                                    toString());
                        }

                        @Override
                        public void onCommandFailure(Throwable exception) {
                            if (exception.getCause() instanceof GPSessionTimeout) {
                                GPHandlerManager.fireEvent(new GPLoginEvent(
                                        publishShapePreviewEvent));
                            } else if (exception.getCause() instanceof GPReloadURLException) {
                                GeoPlatformMessage.errorMessage(
                                        PublisherWidgetConstants.INSTANCE.
                                        GPPublisherWidget_errorReloadClusterTitleText(),
                                        PublisherWidgetMessages.INSTANCE.
                                        GPPublisherWidget_errorReloadClusterBodyMessage(
                                        exception.getCause().getMessage()));
                                LayoutManager.getInstance().getStatusMap().
                                        setStatus(PublisherWidgetConstants.INSTANCE.
                                        statusErrorShapePublishingText(),
                                        EnumSearchStatus.STATUS_NO_SEARCH.
                                        toString());
                                System.out.println(
                                        "Error Publishing previewed shape: " + exception.
                                        toString()
                                        + " data: " + exception.getMessage());
                            } else {
                                GeoPlatformMessage.errorMessage(
                                        PublisherWidgetConstants.INSTANCE.errorPublishingText(),
                                        WindowsConstants.INSTANCE.errorMakingConnectionBodyText());
                                LayoutManager.getInstance().getStatusMap().
                                        setStatus(
                                        PublisherWidgetConstants.INSTANCE.
                                        statusErrorShapePublishingText(),
                                        EnumSearchStatus.STATUS_NO_SEARCH.
                                        toString());
                                System.out.println(
                                        "Error Publishing previewed shape: " + exception.
                                        toString()
                                        + " data: " + exception.getMessage());
                            }
                        }
                    });
                } else {
                    GeoPlatformMessage.alertMessage(
                            PublisherWidgetConstants.INSTANCE.GPPublisherWidget_shapePreviewTitleText(),
                            WindowsConstants.INSTANCE.warningLayerInToFolderText());
                }
            }
        });
        publishButton.disable();
        super.addButton(publishButton);
        Button resetButton = new Button(ButtonsConstants.INSTANCE.resetText(),
                BasicWidgetResources.ICONS.cancel());
        resetButton.addSelectionListener(
                new SelectionListener<ButtonEvent>() {
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
        centralImage = BasicWidgetResources.ICONS.geo_platform_logo().
                createImage();
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

        BorderLayoutData uploadMessageOnTop = new BorderLayoutData(
                LayoutRegion.NORTH);
        uploadMessageOnTop.setMargins(new Margins(3, 150, 0, 151));
        uploadMessageOnTop.setSize(13);
        southPanel.add(uploadMessage, uploadMessageOnTop);

        BorderLayoutData centerFileUploader = new BorderLayoutData(
                LayoutRegion.CENTER);
        centerFileUploader.setMargins(new Margins(1, 150, 9, 151));

        BorderLayoutData toggleButtonPosition = new BorderLayoutData(
                LayoutRegion.EAST, 105);
        toggleButtonPosition.setMargins(new Margins(22, 10, 5, 1));

        this.toggleButtonClusterReload = new ToggleButton(
                PublisherWidgetConstants.INSTANCE.
                GPPublisherWidget_toggleButtonClusterReloadText());
        this.toggleButtonClusterReload.setTitle(
                PublisherWidgetConstants.INSTANCE.
                GPPublisherWidget_toggleButtonClusterReloadTooltipText());
        southPanel.add(fileUploader.getComponent(), centerFileUploader);
        southPanel.add(this.toggleButtonClusterReload, toggleButtonPosition);
        southPanel.setHeadingHtml(PublisherWidgetConstants.INSTANCE.
                GPPublisherWidget_southPanelHeadingText());
        BorderLayoutData southData = new BorderLayoutData(LayoutRegion.SOUTH,
                100);
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
        super.setSize(GPPublisherWidget.PUBLISHER_WIDGET_WIDTH,
                GPPublisherWidget.PUBLISHER_WIDGET_HEIGHT);
    }
}