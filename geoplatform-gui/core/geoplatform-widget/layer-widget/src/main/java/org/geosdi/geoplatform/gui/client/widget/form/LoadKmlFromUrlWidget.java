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
package org.geosdi.geoplatform.gui.client.widget.form;

import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.event.BaseEvent;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.ComponentEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.KeyListener;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.FieldSet;
import com.extjs.gxt.ui.client.widget.layout.FormLayout;
import com.extjs.gxt.ui.client.widget.treepanel.TreePanel;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.http.client.URL;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.HasRpcToken;
import com.google.gwt.user.client.rpc.RpcTokenException;
import com.google.gwt.user.client.rpc.XsrfToken;
import com.google.gwt.user.client.rpc.XsrfTokenServiceAsync;
import com.google.gwt.user.client.ui.AbstractImagePrototype;
import org.geosdi.geoplatform.gui.action.ISave;
import org.geosdi.geoplatform.gui.client.BasicWidgetResources;
import org.geosdi.geoplatform.gui.client.LayerResources;
import org.geosdi.geoplatform.gui.client.i18n.LayerModuleConstants;
import org.geosdi.geoplatform.gui.client.i18n.buttons.ButtonsConstants;
import org.geosdi.geoplatform.gui.client.i18n.kml.KMLErrorMessageConstants;
import org.geosdi.geoplatform.gui.client.i18n.windows.WindowsConstants;
import org.geosdi.geoplatform.gui.client.model.memento.save.bean.MementoSaveAddedLayers;
import org.geosdi.geoplatform.gui.client.model.memento.save.MementoSaveOperations;
import org.geosdi.geoplatform.gui.client.service.LayerRemote;
import org.geosdi.geoplatform.gui.client.service.LayerRemoteAsync;
import org.geosdi.geoplatform.gui.client.widget.SearchStatus.EnumSearchStatus;
import org.geosdi.geoplatform.gui.client.widget.expander.GPLayerExpander;
import org.geosdi.geoplatform.gui.client.widget.fileupload.GPExtensions;
import org.geosdi.geoplatform.gui.client.widget.form.KmlUrlStatus.EnumKmlUrlStatus;
import org.geosdi.geoplatform.gui.client.widget.tree.form.GPTreeFormWidget;
import org.geosdi.geoplatform.gui.configuration.GPSecureStringTextField;
import org.geosdi.geoplatform.gui.configuration.message.GeoPlatformMessage;
import org.geosdi.geoplatform.gui.impl.view.LayoutManager;
import org.geosdi.geoplatform.gui.model.tree.GPBeanTreeModel;
import org.geosdi.geoplatform.gui.model.tree.GPLayerTreeModel;
import org.geosdi.geoplatform.gui.regex.GPRegEx;
import org.geosdi.geoplatform.gui.service.gwt.xsrf.GPXsrfTokenService;

public class LoadKmlFromUrlWidget extends GPTreeFormWidget<GPLayerTreeModel>
        implements ISave<MementoSaveAddedLayers> {

    private GPSecureStringTextField urlText;
    private static final XsrfTokenServiceAsync xsrf = GPXsrfTokenService.Util.getInstance();
    private static final LayerRemoteAsync layerRemote = LayerRemote.Util.getInstance();
    //
    private Button buttonAdd;
//    private final VisitorAddElement addVisitor;
    private GPBeanTreeModel parentDestination;
    private final GPLayerExpander expander;
    //
    private String urlEncoding = "";
    private String suggestion = "";

    public LoadKmlFromUrlWidget(TreePanel<GPBeanTreeModel> theTree) {
        super(theTree, true);
//        addVisitor = new VisitorAddElement();
        expander = new GPLayerExpander(theTree, this);
    }

    public void showForm() {
        if (!super.isInitialized()) {
            super.init();
        }
        expander.checkNodeState();
    }

    @Override
    public void initSize() {
        setHeadingHtml(
                LayerModuleConstants.INSTANCE.LoadKmlFromUrlWidget_headingText());
        setSize(330, 170);
    }

    @Override
    public void initSizeFormPanel() {
        super.getFormPanel().setHeaderVisible(false);
        super.getFormPanel().setSize(280, 120);
    }

    @Override
    public void addComponentToForm() {
        super.fieldSet = new FieldSet();
        super.fieldSet.setHeadingHtml(LayerModuleConstants.INSTANCE.
                LoadKmlFromUrlWidget_fieldSetHeadingText());

        FormLayout layout = new FormLayout();
        layout.setLabelWidth(40);
        super.fieldSet.setLayout(layout);

        urlText = new GPSecureStringTextField();
        urlText.setFieldLabel(LayerModuleConstants.INSTANCE.urlLabelText());

        this.addListenerToUrlText();

        super.fieldSet.add(urlText);

        super.getFormPanel().add(super.fieldSet);

        super.saveStatus = new KmlUrlStatus();
        super.saveStatus.setAutoWidth(true);

        super.getFormPanel().getButtonBar().add(super.saveStatus);

        super.getFormPanel().setButtonAlign(HorizontalAlignment.RIGHT);

        buttonAdd = new Button(ButtonsConstants.INSTANCE.addText(),
                AbstractImagePrototype.create(LayerResources.ICONS.addRasterLayer()),
                new SelectionListener<ButtonEvent>() {

                    @Override
                    public void componentSelected(ButtonEvent ce) {
                        execute();
                    }
                });

        buttonAdd.setEnabled(false);

        super.getFormPanel().addButton(buttonAdd);

        Button buttonCancel = new Button(ButtonsConstants.INSTANCE.cancelText(),
                AbstractImagePrototype.create(BasicWidgetResources.ICONS.cancel()),
                new SelectionListener<ButtonEvent>() {

                    @Override
                    public void componentSelected(ButtonEvent ce) {
                        clearComponents();
                    }
                });

        super.getFormPanel().addButton(buttonCancel);

        setFocusWidget(urlText);
    }

    private void addListenerToUrlText() {
        urlText.addListener(Events.OnPaste, new Listener() {

            @Override
            public void handleEvent(BaseEvent be) {
                if (checkUrl()) {
//                    verifyUrl(false); // TODO Decomment
                } else {
                    buttonAdd.disable();
                    setStatus(EnumKmlUrlStatus.STATUS_NO_CHECKED.getValue(),
                            EnumKmlUrlStatus.STATUS_MESSAGE_NOT_CHECKED.getValue());
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
//                        verifyUrl(false); // TODO Decomment
                    } else {
                        setStatus(EnumKmlUrlStatus.STATUS_NO_CHECKED.getValue(),
                                EnumKmlUrlStatus.STATUS_MESSAGE_NOT_CHECKED.getValue());
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

    private void clearComponents() {
        reset();
        super.hide();
    }

    @Override
    public void reset() {
        buttonAdd.disable();
        urlText.clear();
        super.saveStatus.clearStatus("");
        super.setFocusWidget(urlText);
    }

    @Override
    public void execute() {
        super.saveStatus.setBusy(LayerModuleConstants.INSTANCE.
                LoadKmlFromUrlWidget_statusAddingKMLText());
        this.parentDestination = this.getTree().getSelectionModel().getSelectedItem();
//        assert (this.getTree().isExpanded(parentDestination)) : "AddFolderWidget on execute: the parent folder must be expanded before the add operation";    \

//        List<GPBeanTreeModel> rasterList = this.createRasterList();
//        this.getTree().getStore().insert(parentDestination, rasterList, 0, true);
//        this.addVisitor.insertLayerElements(rasterList, parentDestination);
//        MementoSaveAddedLayers mementoSaveLayer = new MementoSaveAddedLayers(this);
//        mementoSaveLayer.setAddedLayers(MementoBuilder.generateMementoLayerList(rasterList));
//        mementoSaveLayer.setDescendantMap(this.addVisitor.getFolderDescendantMap());
//        GPLayerSaveCache.getInstance().add(mementoSaveLayer);
        clearComponents();
        LayoutManager.getInstance().getStatusMap().setStatus(
                LayerModuleConstants.INSTANCE.LoadKmlFromUrlWidget_statusAddedKMLSuccessText(),
                EnumSearchStatus.STATUS_SEARCH.toString());
    }

    @Override
    public void executeSave(final MementoSaveAddedLayers memento) {
        MementoSaveOperations.mementoSaveAddedLayer(memento,
                LayerModuleConstants.INSTANCE.LoadKmlFromUrlWidget_mementoSuccessMessageText(),
                LayerModuleConstants.INSTANCE.LoadKmlFromUrlWidget_mementoFailMessageText());
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
        System.out.println("*** URL encoding:\n" + GPRegEx.printPrettyURL(
                urlEncoding));

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
        } else if (!urlEncoding.toUpperCase().endsWith(
                GPExtensions.KML.toString())) { // URL direct KML
            suggestion = KMLErrorMessageConstants.INSTANCE.suggestionURLMustReferKMLText();
        }

        if (suggestion == null) { // No suggestion -> NO syntax error
            check = true;
            suggestion = KMLErrorMessageConstants.INSTANCE.suggestionURLOKText();
        }
        System.out.println("*** Suggestion = " + suggestion + "\n### ### ###");

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
                ((HasRpcToken) layerRemote).setRpcToken(token);
                layerRemote.checkKmlUrl(urlEncoding,
                        new AsyncCallback<Boolean>() {

                            @Override
                            public void onFailure(Throwable caught) {
                                buttonAdd.disable();
                                GeoPlatformMessage.errorMessage(
                                        LayerModuleConstants.INSTANCE.
                                        errorCheckingURLTitleText(),
                                        WindowsConstants.INSTANCE.errorMakingConnectionBodyText());
                                LayoutManager.getInstance().getStatusMap().setStatus(
                                        LayerModuleConstants.INSTANCE.statusErrorCheckingURLText(),
                                        EnumSearchStatus.STATUS_NO_SEARCH.toString());
                                System.out.println(
                                        "Error checking the WMS URL: " + caught.toString()
                                        + " data: " + caught.getMessage());
                            }

                            @Override
                            public void onSuccess(Boolean result) {
                                if (result) {
                                    buttonAdd.enable();
                                    setStatus(
                                            EnumKmlUrlStatus.STATUS_CHECKED.getValue(),
                                            EnumKmlUrlStatus.STATUS_MESSAGE_CHECKED.getValue());
                                    if (runExecute) { // Iff the enter key is pressed
                                        execute();
                                    }
                                } else {
                                    buttonAdd.disable();
                                    setStatus(
                                            EnumKmlUrlStatus.STATUS_CHECK_ERROR.getValue(),
                                            EnumKmlUrlStatus.STATUS_MESSAGE_CHECK_ERROR.getValue());
                                }
                            }
                        });
            }
        });
    }
}
