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

import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.Style.LayoutRegion;
import com.extjs.gxt.ui.client.event.BaseEvent;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.ComponentEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.KeyListener;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.util.Margins;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.Field;
import com.extjs.gxt.ui.client.widget.form.FieldSet;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.extjs.gxt.ui.client.widget.form.Validator;
import com.extjs.gxt.ui.client.widget.layout.BorderLayout;
import com.extjs.gxt.ui.client.widget.layout.BorderLayoutData;
import com.extjs.gxt.ui.client.widget.layout.FormLayout;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.http.client.URL;
import com.google.gwt.user.client.ui.Image;
import java.util.HashMap;
import java.util.Map;
import org.geosdi.geoplatform.gui.client.BasicWidgetResources;
import org.geosdi.geoplatform.gui.client.PublisherResources;
import org.geosdi.geoplatform.gui.client.event.IUploadPreviewHandler;
import org.geosdi.geoplatform.gui.puregwt.GPHandlerManager;
import org.geosdi.geoplatform.gui.server.utility.PublisherFileUtils;
import org.geosdi.geoplatform.gui.regex.GPRegEx;
import org.gwtopenmaps.openlayers.client.Bounds;

/**
 * @author Vincenzo Monteverde
 * @email vincenzo.monteverde@geosdi.org - OpenPGP key ID 0xB25F4B38
 */
public class GPKmlWidget extends GeoPlatformWindow
        implements IUploadPreviewHandler {

    private ContentPanel centralPanel;
    private PreviewWidget previewWidget;
    private FieldSet southPanel;
    private Image centralImage;
    private Bounds bounds;
//    private List<PreviewLayer> layerList = new ArrayList<PreviewLayer>(); // KMLPreviewLayer
    //
    private TextField<String> urlText;
    private Button buttonPreview;
    //
    private String urlEncoding;
    private String suggestion;

    public GPKmlWidget(boolean lazy) {
        super(lazy);
        GPHandlerManager.addHandler(IUploadPreviewHandler.TYPE, this);
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
        super.setSize(600, 500);
    }

    @Override
    public void setWindowProperties() {
        super.setHeading("Preview KML file from URL");
        super.setResizable(false);
        super.setLayout(new BorderLayout());
        super.setModal(false);
        super.setCollapsible(true);
        super.setPlain(true);
    }

    @Override
    public void reset() {
        centralPanel.removeAll();
        centralPanel.add(centralImage);
        centralPanel.layout();
        previewWidget.getMapPreview().getMap().removeOverlayLayers();
        bounds = null;
//        layerList.clear();
    }

    @Override
    public void showLayerPreview(String jsonString) {
//        StringBuilder layerProblems = new StringBuilder();
//        PreviewLayerList previewLayers = PreviewLayerList.JSON.read(jsonString);
//        for (PreviewLayer previewLayer : previewLayers.getPreviewLayers()) {
//            if (!previewLayer.getMessage().contains("Some problems")) {
//                previewLayer.setLayerType(GPLayerType.RASTER);
//                layerList.add(previewLayer);
//                WMS wmsLayer = this.generateLayer(previewLayer);
//                shpPreviewWidget.getMapPreview().getMap().addLayer(wmsLayer);
//            } else {
//                layerProblems.append(previewLayer.getTitle()).append("\n");
//            }
//        }
//        if (this.shpPreviewWidget.getMapPreview().getMap().getNumLayers() > 1) {
//            centralPanel.removeAll();
//            centralPanel.add(this.shpPreviewWidget.getMapPreview());
//            shpPreviewWidget.getMapPreview().getMap().zoomToExtent(bounds);
//            shpPreviewWidget.getMapPreview().getMap().updateSize();
//            centralPanel.layout();
//            publishButton.enable();
//        }
//        if (layerProblems.length() != 0) {
//            GeoPlatformMessage.alertMessage("Upload Shape Error",
//                    "Some problems occurred with following layers:\n" + layerProblems);
//        }
    }

//    public WMS generateLayer(PreviewLayer previewLayer) {
//        WMSParams wmsParams = new WMSParams();
//        wmsParams.setFormat("image/png");
//        wmsParams.setLayers(previewLayer.getName());
//        wmsParams.setStyles("");
//        wmsParams.setIsTransparent(true);
//
//        Double lowerX = previewLayer.getLowerX();
//        Double lowerY = previewLayer.getLowerY();
//        Double upperX = previewLayer.getUpperX();
//        Double upperY = previewLayer.getUpperY();
//
//        Bounds layerBounds = new Bounds(lowerX, lowerY, upperX, upperY);
//
//        layerBounds.transform(new Projection(previewLayer.getCrs()), new Projection(
//                shpPreviewWidget.getMapPreview().getMap().getProjection()));
//        wmsParams.setMaxExtent(layerBounds);
//
//        WMSOptions wmsOption = new WMSOptions();
//        wmsOption.setIsBaseLayer(false);
//        wmsOption.setDisplayInLayerSwitcher(true);
//        this.updateMapBounds(layerBounds);
//        return new WMS(previewLayer.getName(), previewLayer.getDataSource(),
//                wmsParams, wmsOption);
//    }
//
//    private void (Bounds layerBounds) {
//        if (bounds == null) {
//            bounds = layerBounds;
//        } else {
//            bounds.extend(layerBounds);
//        }
//    }
    @Override
    public void addComponent() {
        this.addCentralPanel();
        this.addSouthPanel();
        this.addFooterButton();
    }

    private void clearComponents() {
        reset();
        super.hide();
    }

    private void addCentralPanel() {
        previewWidget = new PreviewWidget();
        centralPanel = new ContentPanel();
        centralPanel.setHeaderVisible(false);
        BorderLayoutData centerData = new BorderLayoutData(LayoutRegion.CENTER);
        centerData.setMargins(new Margins(5, 5, 0, 5));
        centralImage = BasicWidgetResources.ICONS.geo_platform_logo().createImage();
        centralPanel.add(centralImage);
        super.add(centralPanel, centerData);
    }

    private void addSouthPanel() {
        southPanel = new FieldSet();
        southPanel.setHeading("Insert KML URL for preview");
//        southPanel.setHeight(40);
//        southPanel.setWidth(522);
//        southPanel.setLayout(new BorderLayout());

        FormLayout layout = new FormLayout();
        layout.setLabelWidth(50);
        southPanel.setLayout(layout);

        urlText = new TextField<String>();
        urlText.setFieldLabel("URL");
        urlText.setEmptyText("Insert a KML URL");
        urlText.setWidth(500);
        urlText.setValidator(new Validator() {

            @Override
            public String validate(Field<?> field, String value) {
                if (checkUrl()) {
                    return null;
                }
                return suggestion;
            }
        });
        this.addListenerToUrlText();
        southPanel.add(urlText);

        BorderLayoutData southData = new BorderLayoutData(LayoutRegion.SOUTH, 100);
        southData.setMargins(new Margins(5, 20, 5, 20));
        super.add(southPanel, southData);

        setFocusWidget(this.urlText);

//        this.saveStatus = new KmlUrlStatus();
//        this.saveStatus.setAutoWidth(true);
//
//        super.getButtonBar().add(this.saveStatus);
    }

    private void addFooterButton() {
        buttonPreview = new Button("Preview", PublisherResources.ICONS.previewKML(), // GEarth
                new SelectionListener<ButtonEvent>() {

            @Override
            public void componentSelected(ButtonEvent ce) {
//                        execute();
                System.out.println("*** PREVIEW ***");
            }
        });
        buttonPreview.setEnabled(false);

        Button buttonCancel = new Button("Cancel", BasicWidgetResources.ICONS.cancel());
        buttonCancel.addSelectionListener(new SelectionListener<ButtonEvent>() {

            @Override
            public void componentSelected(ButtonEvent ce) {
                clearComponents();
            }
        });

        super.addButton(buttonPreview);
        super.addButton(buttonCancel);

        super.setButtonAlign(HorizontalAlignment.RIGHT);
    }

    private void addListenerToUrlText() {
        urlText.addListener(Events.OnPaste, new Listener() {

            @Override
            public void handleEvent(BaseEvent be) {
                if (checkUrl()) {
                    verifyUrl(false);
                } else {
                    buttonPreview.disable();
//                    setStatus(EnumKmlUrlStatus.STATUS_NO_CHECKED.getValue(),
//                            EnumKmlUrlStatus.STATUS_MESSAGE_NOT_CHECKED.getValue());
//                            suggestion);
                }
            }
        });

        urlText.addKeyListener(new KeyListener() {

            @Override
            public void componentKeyUp(ComponentEvent event) {
                if (urlText.getValue() == null) {
                    if ((event.getKeyCode() == KeyCodes.KEY_BACKSPACE)
                            || (event.getKeyCode() == KeyCodes.KEY_DELETE)) {
                        reset();
                    }
                } else {
                    if (checkUrl()) {
                        verifyUrl(false);
                    } else {
//                        setStatus(EnumKmlUrlStatus.STATUS_NO_CHECKED.getValue(),
//                                EnumKmlUrlStatus.STATUS_MESSAGE_NOT_CHECKED.getValue());
//                            suggestion);
                    }
                }
            }

            @Override
            public void componentKeyPress(ComponentEvent event) {
                if ((event.getKeyCode() == KeyCodes.KEY_ENTER)
                        && checkUrl()) {
                    verifyUrl(true);
                }
            }
        });
    }

    private boolean checkUrl() {
        boolean check = false;
        suggestion = null;

        String url = urlText.getValue();
//        System.out.println("*** URL:\n" + url + "\n");        
        if (url == null) { // If it is added the listener on Events.OnPaste, sometimes url is NULL
            System.out.println("URL is NULL");
            return check;
        }
        url = url.replaceAll("[ ]+", ""); // Delete all spaces

        urlEncoding = URL.decodeQueryString(url); // Encoding into ASCII
        System.out.println("*** URL encoding:\n" + GPRegEx.printPrettyURL(urlEncoding));

        if (!urlEncoding.startsWith("http://")) {
            suggestion = "URL must be start with \"http://\"";
        } else if (GPRegEx.RE_FUSION_TABLES_URL.test(urlEncoding)) { // URL Fusion Tables
            if (!GPRegEx.RE_FUSION_TABLES_QS_QUERY.test(urlEncoding)) {
                suggestion = "Query String of Fusion Tables URL is incorrect: check field \"query\"";
            } else if (!GPRegEx.RE_FUSION_TABLES_QS_O.test(urlEncoding)) {
                suggestion = "Query String of Fusion Tables URL is incorrect: check field \"o\"";
            } else if (!GPRegEx.RE_FUSION_TABLES_QS_G.test(urlEncoding)) {
                suggestion = "Query String of Fusion Tables URL is incorrect: check field \"g\"";
            }
        } else if (!urlEncoding.toLowerCase().endsWith(".kml")) { // URL direct KML
            suggestion = "URL must refer to KML file";
        }

        if (suggestion == null) { // No suggestion -> NO syntax error
            check = true;
            suggestion = "URL syntax is OK";
        }
        System.out.println("*** Suggestion = " + suggestion + "\n### ### ###");

        return check;
    }

    private void verifyUrl(final boolean runExecute) {
//        LayerRemoteImpl.Util.getInstance().checkKmlUrl(urlEncoding, new AsyncCallback<Boolean>() {
//
//            @Override
//            public void onFailure(Throwable caught) {
//                buttonAdd.disable();
//                GeoPlatformMessage.errorMessage("Error checking URL",
//                        "An error occurred while making the requested connection.\n"
//                        + "Verify network connections and try again.\n"
//                        + "If the problem persists contact your system administrator.");
//                LayoutManager.getInstance().getStatusMap().setStatus(
//                        "Error checking the WMS URL.",
//                        EnumSearchStatus.STATUS_NO_SEARCH.toString());
//                System.out.println("Error checking the WMS URL: " + caught.toString()
//                        + " data: " + caught.getMessage());
//            }
//
//            @Override
//            public void onSuccess(Boolean result) {
//                if (result) {
//                    buttonAdd.enable();
//                    setStatus(EnumKmlUrlStatus.STATUS_CHECKED.getValue(),
//                            EnumKmlUrlStatus.STATUS_MESSAGE_CHECKED.getValue());
//                    if (runExecute) { // Iff the enter key is pressed
//                        execute();
//                    }
//                } else {
//                    buttonAdd.disable();
//                    setStatus(EnumKmlUrlStatus.STATUS_CHECK_ERROR.getValue(),
//                            EnumKmlUrlStatus.STATUS_MESSAGE_CHECK_ERROR.getValue());
//                }
//            }
//        });
    }
}
