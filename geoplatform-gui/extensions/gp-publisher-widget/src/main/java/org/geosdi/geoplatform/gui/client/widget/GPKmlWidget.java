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

import org.geosdi.geoplatform.gui.configuration.GPSecureStringTextField;
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
import com.extjs.gxt.ui.client.widget.form.FormPanel.LabelAlign;
import com.extjs.gxt.ui.client.widget.form.Validator;
import com.extjs.gxt.ui.client.widget.layout.BorderLayout;
import com.extjs.gxt.ui.client.widget.layout.BorderLayoutData;
import com.extjs.gxt.ui.client.widget.layout.FormLayout;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.http.client.URL;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.HasRpcToken;
import com.google.gwt.user.client.rpc.RpcTokenException;
import com.google.gwt.user.client.rpc.XsrfToken;
import com.google.gwt.user.client.rpc.XsrfTokenServiceAsync;
import com.google.gwt.user.client.ui.AbstractImagePrototype;
import com.google.gwt.user.client.ui.Image;
import org.geosdi.geoplatform.gui.client.BasicWidgetResources;
import org.geosdi.geoplatform.gui.client.PublisherResources;
import org.geosdi.geoplatform.gui.client.event.kml.IUploadKMLPreviewHandler;
import org.geosdi.geoplatform.gui.client.event.timeout.GPKmlPreviewEvent;
import org.geosdi.geoplatform.gui.client.event.timeout.IGPKmlPreviewHandler;
import org.geosdi.geoplatform.gui.client.i18n.PublisherWidgetConstants;
import org.geosdi.geoplatform.gui.client.i18n.buttons.ButtonsConstants;
import org.geosdi.geoplatform.gui.client.i18n.kml.KMLErrorMessageConstants;
import org.geosdi.geoplatform.gui.client.i18n.windows.WindowsConstants;
import org.geosdi.geoplatform.gui.client.service.PublisherRemote;
import org.geosdi.geoplatform.gui.client.service.PublisherRemoteAsync;
import org.geosdi.geoplatform.gui.client.widget.SearchStatus.EnumSearchStatus;
import org.geosdi.geoplatform.gui.configuration.message.GeoPlatformMessage;
import org.geosdi.geoplatform.gui.utility.GPSessionTimeout;
import org.geosdi.geoplatform.gui.impl.map.event.GPLoginEvent;
import org.geosdi.geoplatform.gui.impl.view.LayoutManager;
import org.geosdi.geoplatform.gui.puregwt.GPHandlerManager;
import org.geosdi.geoplatform.gui.puregwt.session.TimeoutHandlerManager;
import org.geosdi.geoplatform.gui.regex.GPRegEx;
import org.geosdi.geoplatform.gui.service.gwt.xsrf.GPXsrfTokenService;
import org.gwtopenmaps.openlayers.client.Bounds;

/**
 * @author Vincenzo Monteverde <vincenzo.monteverde@geosdi.org>
 */
public class GPKmlWidget extends GeoPlatformWindow
        implements IUploadKMLPreviewHandler, IGPKmlPreviewHandler {

    private static final XsrfTokenServiceAsync xsrf = GPXsrfTokenService.Util.getInstance();
    private static final PublisherRemoteAsync publisherRemote = PublisherRemote.Util.getInstance();
    //
    private ContentPanel centralPanel;
    private PreviewWidget previewWidget;
    private FieldSet southPanel;
    private Image centralImage;
    private Bounds bounds;
    private final GPKmlPreviewEvent publishShapePreviewEvent = new GPKmlPreviewEvent();
//    private List<PreviewLayer> layerList = new ArrayList<PreviewLayer>(); // KMLPreviewLayer
    //
    private GPSecureStringTextField urlText;
    private Button buttonPreview;
    //
    private String urlEncoding;
    private String suggestion;

    public GPKmlWidget(boolean lazy) {
        super(lazy);
        GPHandlerManager.addHandler(IUploadKMLPreviewHandler.TYPE, this);
        TimeoutHandlerManager.addHandler(IGPKmlPreviewHandler.TYPE, this);
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
        super.setHeadingHtml(
                PublisherWidgetConstants.INSTANCE.GPKmlWidget_headingText());
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
    public void kmlPreview() {
        buttonPreview.fireEvent(Events.Select);
    }

    @Override
    public void showKMLPreview(String jsonString) {
        System.out.println(
                "%%% Method showLayerPreview() must be implemented...");
//        StringBuilder layerProblems = new StringBuilder();
//        PreviewLayerList previewLayers = PreviewLayerList.JSON.read(jsonString);
//        for (PreviewLayer previewLayer : previewLayers.getPreviewLayers()) {
//            if (!previewLayer.getMessage().contains("Some problems")) {
//                previewLayer.setLayerType(GPLayerType.WMS);
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
//        wmsParams.setTransparent(true);
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
//
//        WMSOptions wmsOption = new WMSOptions();
//        wmsOption.setMaxExtent(layerBounds);    
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
        this.reset();
        super.hide();
    }

    private void addCentralPanel() {
        previewWidget = new PreviewWidget();
        centralPanel = new ContentPanel();
        centralPanel.setHeaderVisible(false);
        BorderLayoutData centerData = new BorderLayoutData(LayoutRegion.CENTER);
        centerData.setMargins(new Margins(5, 5, 0, 5));
        centralImage = new Image(BasicWidgetResources.ICONS.geo_platform_logo());
        centralPanel.add(centralImage);
        super.add(centralPanel, centerData);
    }

    private void addSouthPanel() {
        southPanel = new FieldSet();
        southPanel.setHeadingHtml(
                PublisherWidgetConstants.INSTANCE.GPKmlWidget_southPanelHeadingText());

        FormLayout layout = new FormLayout();
        layout.setLabelAlign(LabelAlign.TOP);
        layout.setLabelSeparator("");
        layout.setDefaultWidth(510);
        southPanel.setLayout(layout);

        urlText = new GPSecureStringTextField();
        urlText.setFieldLabel(PublisherWidgetConstants.INSTANCE.GPKmlWidget_urlLabelText());
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

        BorderLayoutData southData = new BorderLayoutData(LayoutRegion.SOUTH,
                100);
        southData.setMargins(new Margins(5, 20, 5, 20));
        super.add(southPanel, southData);

        super.setFocusWidget(urlText);
    }

    private void addFooterButton() {
        buttonPreview = new Button(ButtonsConstants.INSTANCE.previewText(),
                AbstractImagePrototype.create(PublisherResources.ICONS.previewKML()), // GEarth
                new SelectionListener<ButtonEvent>() {

                    @Override
                    public void componentSelected(ButtonEvent ce) {
                        execute();
                    }
                });
        buttonPreview.setEnabled(false);

        Button buttonCancel = new Button(ButtonsConstants.INSTANCE.cancelText(),
                AbstractImagePrototype.create(BasicWidgetResources.ICONS.cancel()));
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
                    urlText.clearInvalid();
                    verifyUrl(false);
                } else {
                    buttonPreview.disable();
                    urlText.forceInvalid(suggestion);
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
                        urlText.clearInvalid();
                        verifyUrl(false);
                    } else {
                        buttonPreview.disable();
                        urlText.forceInvalid(suggestion);
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
//        System.out.println("*** URL encoding:\n" + GPRegEx.printPrettyURL(urlEncoding));

        if (!urlEncoding.startsWith("http://")) {
            suggestion = KMLErrorMessageConstants.INSTANCE.suggestionURLStartText();
        } else if (GPRegEx.RE_FUSION_TABLES_URL.test(urlEncoding)) { // URL Fusion Tables
            if (!GPRegEx.RE_FUSION_TABLES_QS_QUERY.test(urlEncoding)) {
                suggestion = KMLErrorMessageConstants.INSTANCE.suggestionCheckFieldQueryText();
            } else if (!GPRegEx.RE_FUSION_TABLES_QS_O.test(urlEncoding)) {
                suggestion = KMLErrorMessageConstants.INSTANCE.suggestionCheckFieldOText();
            } else if (!GPRegEx.RE_FUSION_TABLES_QS_G.test(urlEncoding)) {
                suggestion = KMLErrorMessageConstants.INSTANCE.suggestionCheckFieldGText();
            }
        } else if (!urlEncoding.toLowerCase().endsWith(".kml")) { // URL direct KML
            suggestion = KMLErrorMessageConstants.INSTANCE.suggestionURLMustReferKMLText();
        }

        if (suggestion == null) { // No suggestion -> NO syntax error
            check = true;
            suggestion = KMLErrorMessageConstants.INSTANCE.suggestionURLOKText();;
        }
//        System.out.println("*** Suggestion = " + suggestion + "\n### ### ###");

        return check;
    }

    private void verifyUrl(final boolean runExecute) {
        xsrf.getNewXsrfToken(new AsyncCallback<XsrfToken>() {

            @Override
            public void onFailure(Throwable caught) {
                try {
                    throw caught;
                } catch (RpcTokenException e) {
                    // Can be thrown for several reasons:
                    //   - duplicate session cookie, which may be a sign of a cookie
                    //     overwrite attack
                    //   - XSRF token cannot be generated because session cookie isn't
                    //     present
                } catch (Throwable e) {
                    // unexpected
                }
            }

            @Override
            public void onSuccess(XsrfToken token) {
                ((HasRpcToken) publisherRemote).setRpcToken(token);
                publisherRemote.kmlPreview(urlEncoding,
                        new AsyncCallback<Boolean>() {

                            @Override
                            public void onFailure(Throwable caught) {
                                if (caught.getCause() instanceof GPSessionTimeout) {
                                    GPHandlerManager.fireEvent(new GPLoginEvent(
                                                    publishShapePreviewEvent));
                                } else {
                                    GeoPlatformMessage.errorMessage(
                                            PublisherWidgetConstants.INSTANCE.GPKmlWidget_errorCheckingKMLURLText(),
                                            WindowsConstants.INSTANCE.errorMakingConnectionBodyText());
                                    LayoutManager.getInstance().getStatusMap().setStatus(
                                            PublisherWidgetConstants.INSTANCE.GPKmlWidget_statusErrorPreviewText(),
                                            EnumSearchStatus.STATUS_NO_SEARCH.toString());
                                    System.out.println(
                                            "Error checking the KML URL: " + caught.toString()
                                            + " data: " + caught.getMessage());
                                }
                            }

                            @Override
                            public void onSuccess(Boolean result) {
                                if (result == null) { // DEL
                                    buttonPreview.enable(); // DEL
                                    return; // DEL
                                } // DEL

                                if (result) {
                                    buttonPreview.enable();
                                    if (runExecute) { // Iff the enter key is pressed
                                        execute();
                                    }
                                } else {
                                    buttonPreview.disable();
                                }
                            }
                        });
            }
        });
    }

    private void execute() {
        System.out.println("%%% Method execute() must be implemented...");
        GeoPlatformMessage.alertMessage(
                WindowsConstants.INSTANCE.warningTitleText(),
                WindowsConstants.INSTANCE.functionNotYetImplementedText());
    }
}
