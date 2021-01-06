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
package org.geosdi.geoplatform.gui.client.widget;

import com.extjs.gxt.ui.client.Registry;
import com.extjs.gxt.ui.client.Style;
import com.extjs.gxt.ui.client.event.*;
import com.extjs.gxt.ui.client.util.Margins;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.Text;
import com.extjs.gxt.ui.client.widget.VerticalPanel;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.FieldSet;
import com.extjs.gxt.ui.client.widget.form.FormPanel;
import com.extjs.gxt.ui.client.widget.layout.*;
import com.google.common.collect.Lists;
import com.google.gwt.user.client.ui.AbstractImagePrototype;
import com.google.gwt.user.client.ui.Image;
import org.geosdi.geoplatform.gui.action.button.GPSecureButton;
import org.geosdi.geoplatform.gui.client.BasicWidgetResources;
import org.geosdi.geoplatform.gui.client.event.epsgcheck.IUploadEPSGCheckHandler;
import org.geosdi.geoplatform.gui.client.event.epsgcheck.UploadEPSGCheckEvent;
import org.geosdi.geoplatform.gui.client.event.shapepreview.IFeaturePreviewHandler;
import org.geosdi.geoplatform.gui.client.event.timeout.IGPPublishShapePreviewHandler;
import org.geosdi.geoplatform.gui.client.i18n.PublisherWidgetConstants;
import org.geosdi.geoplatform.gui.client.i18n.PublisherWidgetMessages;
import org.geosdi.geoplatform.gui.client.i18n.buttons.ButtonsConstants;
import org.geosdi.geoplatform.gui.client.model.GPWorkspace;
import org.geosdi.geoplatform.gui.client.model.PreviewLayer;
import org.geosdi.geoplatform.gui.client.model.PreviewLayerList;
import org.geosdi.geoplatform.gui.client.widget.fileupload.GPExtensions;
import org.geosdi.geoplatform.gui.client.widget.fileupload.GPFileUploader;
import org.geosdi.geoplatform.gui.configuration.action.GeoPlatformSecureAction;
import org.geosdi.geoplatform.gui.configuration.message.GeoPlatformMessage;
import org.geosdi.geoplatform.gui.configuration.users.options.member.UserSessionEnum;
import org.geosdi.geoplatform.gui.global.security.IGPAccountDetail;
import org.geosdi.geoplatform.gui.puregwt.GPHandlerManager;
import org.geosdi.geoplatform.gui.puregwt.session.TimeoutHandlerManager;
import org.geosdi.geoplatform.gui.shared.GPLayerType;
import org.geosdi.geoplatform.gui.shared.GPTrustedLevel;
import org.geosdi.geoplatform.gui.shared.util.GPSharedUtils;
import org.gwtopenmaps.openlayers.client.Bounds;
import org.gwtopenmaps.openlayers.client.Projection;
import org.gwtopenmaps.openlayers.client.layer.WMS;
import org.gwtopenmaps.openlayers.client.layer.WMSOptions;
import org.gwtopenmaps.openlayers.client.layer.WMSParams;

import java.util.List;

/**
 * @author Nazzareno Sileno - CNR IMAA geoSDI Group
 * @email nazzareno.sileno@geosdi.org
 */
public abstract class AbstractPublisherWidget extends GeoPlatformWindow
        implements IFeaturePreviewHandler, IGPPublishShapePreviewHandler,
        IUploadEPSGCheckHandler {

    public final static int PUBLISHER_WIDGET_WIDTH = 600;
    public final static int PUBLISHER_WIDGET_HEIGHT = 500;
    //    private boolean mapInitialized;
    private ContentPanel centralPanel;
    private PreviewWidget shpPreviewWidget;
    private GPFileUploader fileUploader;
    private FieldSet southPanel;
    private Image centralImage;
    private Bounds bounds;

    protected List<PreviewLayer> layerList = Lists.<PreviewLayer>newArrayList();
    protected Button finishButton;
    protected WorkspacesComboBox workspaceSimpleComboBox;
    private Text uploadMessage = new Text(PublisherWidgetConstants.INSTANCE.
            GPPublisherWidget_uploadMessageText() + ":");
    private UploadEPSGCheckEvent epsgCheckEvent = new UploadEPSGCheckEvent();
    private EPSGTablePanel epsgTable;
    protected GPSecureButton addWorkSpaceButton;

    public AbstractPublisherWidget(boolean lazy) {
        super(lazy);
        GPHandlerManager.addHandler(IFeaturePreviewHandler.TYPE, this);
        GPHandlerManager.addHandler(IUploadEPSGCheckHandler.TYPE, this);
        TimeoutHandlerManager.addHandler(IGPPublishShapePreviewHandler.TYPE,
                this);
        this.finishButton = this.getFinalizePublishButton();
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
        this.epsgTable = new EPSGTablePanel();
    }

    @Override
    public void showEPSGTable(String jsonString, String workspace) {
        StringBuilder layerProblems = new StringBuilder();
        List<PreviewLayer> epsgLayerList = Lists.<PreviewLayer>newArrayList();
        PreviewLayerList previewLayers = PreviewLayerList.JSON.read(jsonString);
        for (PreviewLayer previewLayer : GPSharedUtils.safeList(previewLayers.getPreviewLayers())) {
            if (previewLayer.getMessage().contains("Some problems")) {
                layerProblems.append(previewLayer.getTitle()).append("\n");
            } else {
                epsgLayerList.add(previewLayer);
            }
        }
        if (epsgLayerList.size() > 0) {
            this.epsgTable.populateStore(epsgLayerList);
            this.epsgTable.setWorkspace(workspace);
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
            finishButton.enable();
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
        finishButton.disable();
        fileUploader.getComponent().reset();
        shpPreviewWidget.getMapPreview().getMap().removeOverlayLayers();
        bounds = null;
        layerList.clear();
        workspaceSimpleComboBox.enable();
        workspaceSimpleComboBox.clear();
    }

    @Override
    public void publishShapePreview() {
        finishButton.fireEvent(Events.Select);
    }

    @Override
    public void addComponent() {
        this.addCentralPanel();
        this.addSouthPanel();
        super.addButton(this.getFinalizePublishButton());
        Button resetButton = new Button(ButtonsConstants.INSTANCE.resetText(),
                AbstractImagePrototype.create(BasicWidgetResources.ICONS.cancel()));
        resetButton.addSelectionListener(
                new SelectionListener<ButtonEvent>() {
                    @Override
                    public void componentSelected(ButtonEvent ce) {
                        reset();
                    }
                });
        super.addButton(resetButton);
    }

    public abstract Button getFinalizePublishButton();

    private void addCentralPanel() {
        shpPreviewWidget = new PreviewWidget();
        centralPanel = new ContentPanel();
        centralPanel.setHeaderVisible(false);
        BorderLayoutData centerData = new BorderLayoutData(Style.LayoutRegion.CENTER);
        centerData.setMargins(new Margins(5, 5, 0, 5));
        centralImage = new Image(BasicWidgetResources.ICONS.geo_platform_logo());
        centralPanel.add(centralImage);
        super.add(centralPanel, centerData);
    }

    private void addSouthPanel() {
        fileUploader = new GPFileUploader("UploadServlet", this.epsgCheckEvent,
                GPExtensions.ZIP, GPExtensions.TIF, GPExtensions.TIFF);
        fileUploader.getButtonSubmit().addListener(Events.BeforeSelect, new Listener<BaseEvent>() {

            @Override
            public void handleEvent(BaseEvent be) {
                epsgCheckEvent.reset();
                workspaceSimpleComboBox.disable();
                String workspaceName = getSelectedWorkspace();
                if (GPSharedUtils.isNotEmpty(workspaceName)) {
                    epsgCheckEvent.setWorkspace(workspaceName);
                    logger.finest("Added param workspace to the servlet URL: " + workspaceName);
                    fileUploader.addParamToServletURL("workspace", workspaceName);
                }
            }
        });
        southPanel = new FieldSet();
        southPanel.setHeight(78);
        southPanel.setWidth(522);
        southPanel.setLayout(new RowLayout(Style.Orientation.HORIZONTAL));

        VerticalPanel uploadPanel = new VerticalPanel();
        uploadPanel.add(uploadMessage);
        uploadPanel.add(fileUploader.getComponent());
        uploadPanel.setWidth(225);

        FormPanel workSpaceSelectionPanel = new FormPanel();
        workSpaceSelectionPanel.setHeaderVisible(false);
        workSpaceSelectionPanel.setBorders(false);
        workSpaceSelectionPanel.setBodyBorder(false);
        workSpaceSelectionPanel.setSize(250, 30);

        this.workspaceSimpleComboBox = new WorkspacesComboBox();
        this.workspaceSimpleComboBox.setFieldLabel(
                PublisherWidgetConstants.INSTANCE.GPPublisherWidget_chooseWorkspaceComboBoxText());
        workSpaceSelectionPanel.add(workspaceSimpleComboBox, new FormData("99%"));
        final AddWorkspaceWidget addWorkspaceWidget = new AddWorkspaceWidget(true);
        this.addWorkSpaceButton = new GPSecureButton("",
                AbstractImagePrototype.create(BasicWidgetResources.ICONS.done()),
                new GeoPlatformSecureAction<ButtonEvent>(GPTrustedLevel.FULL) {

                    @Override
                    public void componentSelected(ButtonEvent e) {
                        addWorkspaceWidget.showForm();
                    }
                });
        workSpaceSelectionPanel.setPadding(0);
        addWorkSpaceButton.setSize(22, 20);
        addWorkSpaceButton.setToolTip(PublisherWidgetConstants.INSTANCE.
                GPPublisherWidget_addWorkspaceButtonTooltip());
        southPanel.add(workSpaceSelectionPanel, new RowData(-1, 1, new Margins(14, 0, 14, 10)));
        southPanel.add(addWorkSpaceButton, new RowData(22, 1, new Margins(13, 0, 28, 0)));
        southPanel.add(uploadPanel, new RowData(1, 1, new Margins(4)));
        southPanel.setHeadingHtml(PublisherWidgetConstants.INSTANCE.
                GPPublisherWidget_southPanelHeadingText());
        BorderLayoutData southData = new BorderLayoutData(Style.LayoutRegion.SOUTH,
                100);
        southData.setMargins(new Margins(5, 20, 5, 20));
        super.add(southPanel, southData);
    }

    protected String getSelectedWorkspace() {
        GPWorkspace gpWorkspace = workspaceSimpleComboBox.getValue();
        String workspaceName = null;
        if (gpWorkspace != null && GPSharedUtils.isNotEmpty(
                gpWorkspace.getWorkspaceName())) {
            workspaceName = gpWorkspace.getWorkspaceName();
        } else if (GPSharedUtils.isNotEmpty(
                workspaceSimpleComboBox.getRawValue())) {
            workspaceName = workspaceSimpleComboBox.getRawValue();
        } else {
            IGPAccountDetail accountDetail = Registry.get(
                    UserSessionEnum.ACCOUNT_DETAIL_IN_SESSION.name());
            workspaceName = accountDetail.getUsername();
        }
        return workspaceName;
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
