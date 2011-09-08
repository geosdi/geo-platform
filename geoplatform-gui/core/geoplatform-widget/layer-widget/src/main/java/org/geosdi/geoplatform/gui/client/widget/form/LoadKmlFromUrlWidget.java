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
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.extjs.gxt.ui.client.widget.layout.FormLayout;
import com.extjs.gxt.ui.client.widget.treepanel.TreePanel;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.http.client.URL;
import com.google.gwt.user.client.rpc.AsyncCallback;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.geosdi.geoplatform.gui.action.ISave;
import org.geosdi.geoplatform.gui.client.BasicWidgetResources;
import org.geosdi.geoplatform.gui.client.LayerResources;
import org.geosdi.geoplatform.gui.client.model.memento.AbstractMementoLayer;
import org.geosdi.geoplatform.gui.client.model.memento.GPLayerSaveCache;
import org.geosdi.geoplatform.gui.client.model.memento.MementoSaveAddedLayers;
import org.geosdi.geoplatform.gui.client.model.memento.puregwt.event.PeekCacheEvent;
import org.geosdi.geoplatform.gui.client.model.visitor.VisitorAddElement;
import org.geosdi.geoplatform.gui.client.service.LayerRemote;
import org.geosdi.geoplatform.gui.client.widget.SaveStatus.EnumSaveStatus;
import org.geosdi.geoplatform.gui.client.widget.SearchStatus.EnumSearchStatus;
import org.geosdi.geoplatform.gui.client.widget.expander.GPLayerExpander;
import org.geosdi.geoplatform.gui.client.widget.form.KmlUrlStatus.EnumKmlUrlStatus;
import org.geosdi.geoplatform.gui.client.widget.tree.form.GPTreeFormWidget;
import org.geosdi.geoplatform.gui.configuration.message.GeoPlatformMessage;
import org.geosdi.geoplatform.gui.exception.GPSessionTimeout;
import org.geosdi.geoplatform.gui.impl.map.event.GPLoginEvent;
import org.geosdi.geoplatform.gui.impl.view.LayoutManager;
import org.geosdi.geoplatform.gui.model.tree.GPBeanTreeModel;
import org.geosdi.geoplatform.gui.model.tree.GPLayerTreeModel;
import org.geosdi.geoplatform.gui.puregwt.GPHandlerManager;
import org.geosdi.geoplatform.gui.puregwt.layers.LayerHandlerManager;
import org.geosdi.geoplatform.gui.puregwt.progressbar.layers.event.DisplayLayersProgressBarEvent;
import org.geosdi.geoplatform.gui.server.gwt.LayerRemoteImpl;

/**
 *
 * @author Vincenzo Monteverde
 * @email vincenzo.monteverde@geosdi.org - OpenPGP key ID 0xB25F4B38
 */
public class LoadKmlFromUrlWidget extends GPTreeFormWidget<GPLayerTreeModel>
        implements ISave<MementoSaveAddedLayers> {

    private TreePanel<GPBeanTreeModel> tree;
    private TextField<String> urlText;
    private Button save;
    private Button cancel;
    private VisitorAddElement addVisitor;
    private GPBeanTreeModel parentDestination;
    private GPLayerExpander expander;
    private PeekCacheEvent peekCacheEvent = new PeekCacheEvent();
    //
    private Map<String, String> fieldQueryString = new HashMap<String, String>();
    private String urlEncoding = "";
    private String suggestion = "";

    /**
     *@param theTree 
     * 
     */
    public LoadKmlFromUrlWidget(TreePanel<GPBeanTreeModel> theTree) {
        super(true);
        this.tree = theTree;
        this.addVisitor = new VisitorAddElement();
        this.expander = new GPLayerExpander(this, theTree);
    }

    public void showForm() {
        if (!isInitialized()) {
            super.init();
        }
        this.expander.checkNodeState();
    }

    @Override
    public void initSize() {
        setHeading("Add KML from direct URL");
        setSize(330, 170);
    }

    @Override
    public void initSizeFormPanel() {
        this.formPanel.setHeaderVisible(false);
        this.formPanel.setSize(280, 120);
    }

    @Override
    public void addComponentToForm() {
        this.fieldSet = new FieldSet();
        this.fieldSet.setHeading("Load KML from URL");

        FormLayout layout = new FormLayout();
        layout.setLabelWidth(40);
        fieldSet.setLayout(layout);

        this.urlText = new TextField<String>();
        this.urlText.setFieldLabel("URL");

        this.AddListenerToUrlText();

        this.fieldSet.add(this.urlText);

        this.formPanel.add(this.fieldSet);

        this.saveStatus = new KmlUrlStatus();
        this.saveStatus.setAutoWidth(true);

        this.formPanel.getButtonBar().add(this.saveStatus);

        this.formPanel.setButtonAlign(HorizontalAlignment.RIGHT);

        this.save = new Button("Add", LayerResources.ICONS.addRasterLayer(),
                new SelectionListener<ButtonEvent>() {

                    @Override
                    public void componentSelected(ButtonEvent ce) {
                        execute();
                    }
                });

        save.setEnabled(false);

        this.formPanel.addButton(save);

        this.cancel = new Button("Cancel", BasicWidgetResources.ICONS.cancel(),
                new SelectionListener<ButtonEvent>() {

                    @Override
                    public void componentSelected(ButtonEvent ce) {
                        clearComponents();
                    }
                });

        this.formPanel.addButton(cancel);

        setFocusWidget(this.urlText);
    }

    private void AddListenerToUrlText() {
        this.urlText.addListener(Events.OnPaste, new Listener() {

            @Override
            public void handleEvent(BaseEvent be) {
                if (checkUrl()) {
                    verifyUrl(false);
                } else {
                    save.disable();
                    setStatus(EnumKmlUrlStatus.STATUS_NO_CHECKED.getValue(),
                            EnumKmlUrlStatus.STATUS_MESSAGE_NOT_CHECKED.getValue());
//                            suggestion);
                }
            }
        });

        this.urlText.addKeyListener(new KeyListener() {

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
        this.save.disable();
        this.urlText.clear();
        this.saveStatus.clearStatus("");
        setFocusWidget(this.urlText);
    }

    /**
     * @return the tree
     */
    public TreePanel<GPBeanTreeModel> getTree() {
        return tree;
    }

    @Override
    public void execute() {
        this.saveStatus.setBusy("Adding KML from URL");
        this.parentDestination = this.getTree().getSelectionModel().getSelectedItem();
//        assert (this.getTree().isExpanded(parentDestination)) : "AddFolderWidget on execute: the parent folder must be expanded before the add operation";    

        this.retrieveDataFromQueryString(); // Set the fieldMap

//        List<GPBeanTreeModel> rasterList = this.createRasterList();

//        this.getTree().getStore().insert(parentDestination, rasterList, 0, true);

//        this.addVisitor.insertLayerElements(rasterList, parentDestination);

//        MementoSaveAddedLayers mementoSaveLayer = new MementoSaveAddedLayers(this);
//        mementoSaveLayer.setAddedLayers(MementoBuilder.generateMementoLayerList(rasterList));
//        mementoSaveLayer.setDescendantMap(this.addVisitor.getFolderDescendantMap());
//        GPLayerSaveCache.getInstance().add(mementoSaveLayer);

        clearComponents();
        LayoutManager.getInstance().getStatusMap().setStatus(
                "Added KML on tree succesfully.",
                EnumSearchStatus.STATUS_SEARCH.toString());
    }

    @Override
    public void executeSave(final MementoSaveAddedLayers memento) {
        //Warning: The following conversion is absolutely necessary!
        memento.convertMementoToWs();

        LayerRemote.Util.getInstance().saveAddedLayersAndTreeModifications(memento,
                new AsyncCallback<ArrayList<Long>>() {

                    @Override
                    public void onFailure(Throwable caught) {
                        if (caught.getCause() instanceof GPSessionTimeout) {
                            GPHandlerManager.fireEvent(new GPLoginEvent(peekCacheEvent));
                        } else {
                            LayerHandlerManager.fireEvent(new DisplayLayersProgressBarEvent(false));
                            setStatus(EnumSaveStatus.STATUS_SAVE_ERROR.getValue(),
                                    EnumSaveStatus.STATUS_MESSAGE_SAVE_ERROR.getValue());
                            GeoPlatformMessage.errorMessage("Save KML Error",
                                    "Problems on saving the new tree state after KML creation");
                        }
                    }

                    @Override
                    public void onSuccess(ArrayList<Long> result) {
                        GPLayerSaveCache.getInstance().remove(memento);
                        LayoutManager.getInstance().getStatusMap().setStatus(
                                "KML saved successfully.",
                                EnumSearchStatus.STATUS_SEARCH.toString());
                        //Warning: What happens when I delete a raster before save it???
//                        memento.getRefBaseElement().setId(result.get(0)); // TODO
//                        LayerHandlerManager.fireEvent(peekCacheEvent);                     
                        List<AbstractMementoLayer> listMementoLayers = memento.getAddedLayers();
                        for (int i = 0; i < listMementoLayers.size(); i++) {
                            listMementoLayers.get(i).getRefBaseElement().setId(result.get(i));
                        }
                        LayerHandlerManager.fireEvent(peekCacheEvent);
                    }
                });
    }

    private void retrieveDataFromQueryString() {
        String queryString = urlEncoding.substring(urlEncoding.indexOf("?") + 1);
//        System.out.println("*** Query String: " + queryString + "\n");

        String[] pairs = queryString.split("&");
        for (String pair : pairs) {
            int ind = pair.indexOf("=");
            System.out.println("# " + pair);

            String field = pair.substring(0, ind);
            String fieldValue = pair.substring(ind + 1);

            fieldQueryString.put(field, fieldValue);
        }
    }

    // TODO
    // Check &field=fieldValue
    // Check required fields in query string
    private boolean checkUrl() {
        boolean check = false;

        String url = this.urlText.getValue();
//        System.out.println("*** URL:\n" + url + "\n");        
        if (url == null) { // If it is added the listener on Events.OnPaste, sometimes url is NULL
            System.out.println("URL is NULL");
            return false;
        }

        url = url.replaceAll("[ ]+", ""); // Delete all spaces
//        System.out.println("*** URL clean:\n" + url + "\n");

        this.urlEncoding = URL.decodeQueryString(url); // Encoding into ASCII
//        System.out.println("*** URL encoding:\n" + urlEncoding + "\n");

        if (!urlEncoding.startsWith("http://")) {
            suggestion = "URL must be start with \"http://\"";
//        } else if (!url.contains("/kml?")) { // TODO DEL ?
//            suggestion = "URL must contain \"/kml?\"";
        }
        System.out.println("*** Suggestion = " + suggestion);

        return check;
    }

    private void verifyUrl(final boolean runExecute) {
        LayerRemoteImpl.Util.getInstance().checkKmlUrl(this.urlEncoding, new AsyncCallback<Boolean>() {

            @Override
            public void onFailure(Throwable caught) {
                save.disable();
                GeoPlatformMessage.errorMessage("Error checking URL",
                        "An error occurred while making the requested connection.\n"
                        + "Verify network connections and try again.\n"
                        + "If the problem persists contact your system administrator.");
                LayoutManager.getInstance().getStatusMap().setStatus(
                        "Error checking the WMS URL.",
                        EnumSearchStatus.STATUS_NO_SEARCH.toString());
                System.out.println("Error checking the WMS URL: " + caught.toString()
                        + " data: " + caught.getMessage());
            }

            @Override
            public void onSuccess(Boolean result) {
                if (result) {
                    save.enable();
                    setStatus(EnumKmlUrlStatus.STATUS_CHECKED.getValue(),
                            EnumKmlUrlStatus.STATUS_MESSAGE_CHECKED.getValue());
                    if (runExecute) { // Iff the enter key is pressed
                        execute();
                    }
                } else {
                    save.disable();
                    setStatus(EnumKmlUrlStatus.STATUS_CHECK_ERROR.getValue(),
                            EnumKmlUrlStatus.STATUS_MESSAGE_CHECK_ERROR.getValue());
                }
            }
        });
    }
}