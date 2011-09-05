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
import com.google.gwt.user.client.rpc.AsyncCallback;
import java.util.ArrayList;
import java.util.List;
import org.geosdi.geoplatform.gui.action.ISave;
import org.geosdi.geoplatform.gui.client.BasicWidgetResources;
import org.geosdi.geoplatform.gui.client.LayerResources;
import org.geosdi.geoplatform.gui.client.model.RasterTreeNode;
import org.geosdi.geoplatform.gui.client.model.memento.GPLayerSaveCache;
import org.geosdi.geoplatform.gui.client.model.memento.MementoBuilder;
import org.geosdi.geoplatform.gui.client.model.memento.MementoSaveAddedLayers;
import org.geosdi.geoplatform.gui.client.model.memento.puregwt.event.PeekCacheEvent;
import org.geosdi.geoplatform.gui.client.model.visitor.VisitorAddElement;
import org.geosdi.geoplatform.gui.client.service.LayerRemote;
import org.geosdi.geoplatform.gui.client.util.UtilityLayerModule;
import org.geosdi.geoplatform.gui.client.widget.SaveStatus;
import org.geosdi.geoplatform.gui.client.widget.SaveStatus.EnumSaveStatus;
import org.geosdi.geoplatform.gui.client.widget.SearchStatus.EnumSearchStatus;
import org.geosdi.geoplatform.gui.client.widget.expander.GPLayerExpander;
import org.geosdi.geoplatform.gui.client.widget.tree.form.GPTreeFormWidget;
import org.geosdi.geoplatform.gui.configuration.message.GeoPlatformMessage;
import org.geosdi.geoplatform.gui.exception.GPSessionTimeout;
import org.geosdi.geoplatform.gui.impl.map.event.GPLoginEvent;
import org.geosdi.geoplatform.gui.impl.view.LayoutManager;
import org.geosdi.geoplatform.gui.model.tree.GPBeanTreeModel;
import org.geosdi.geoplatform.gui.puregwt.GPHandlerManager;
import org.geosdi.geoplatform.gui.puregwt.layers.LayerHandlerManager;
import org.geosdi.geoplatform.gui.puregwt.progressbar.layers.event.DisplayLayersProgressBarEvent;

/**
 *
 * @author Vincenzo Monteverde
 * @email vincenzo.monteverde@geosdi.org - OpenPGP key ID 0xB25F4B38
 */
public class AddRasterFromUrlWidget extends GPTreeFormWidget<RasterTreeNode>
        implements ISave<MementoSaveAddedLayers> {

    private TreePanel<GPBeanTreeModel> tree;
    private TextField<String> urlText;
    private Button save;
    private Button cancel;
    private Button validate;
    private VisitorAddElement addVisitor;
    private GPBeanTreeModel parentDestination;
    private GPLayerExpander expander;
    private PeekCacheEvent peekCacheEvent = new PeekCacheEvent();

    /**
     *@param theTree 
     * 
     */
    public AddRasterFromUrlWidget(TreePanel<GPBeanTreeModel> theTree) {
        super(true);
        this.tree = theTree;
        this.addVisitor = new VisitorAddElement();
        this.expander = new GPLayerExpander(this, theTree);
    }

    @Override
    public void addComponentToForm() {
        this.fieldSet = new FieldSet();
        this.fieldSet.setHeading("WMS from URL");

        FormLayout layout = new FormLayout();
        layout.setLabelWidth(40);
        fieldSet.setLayout(layout);

        this.urlText = new TextField<String>();
        this.urlText.setFieldLabel("URL");

        this.urlText.addListener(Events.OnPaste, new Listener() {

            @Override
            public void handleEvent(BaseEvent be) {
                if (checkUrl()) {
                    save.enable();
                } else {
                    save.disable();
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
                        save.enable();
                    } else {
                        save.disable();
                    }
                }
            }

            @Override
            public void componentKeyPress(ComponentEvent event) {
                if ((event.getKeyCode() == KeyCodes.KEY_ENTER) && (urlText.getValue() != null)
                        && (checkUrl())) {
                    execute();
                }
            }
        });

        this.fieldSet.add(this.urlText);

        this.formPanel.add(this.fieldSet);

        this.saveStatus = new SaveStatus();
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

    @Override
    public void initSize() {
        setHeading("Add WMS from URL");
        setSize(330, 170);
    }

    @Override
    public void initSizeFormPanel() {
        this.formPanel.setHeaderVisible(false);
        this.formPanel.setSize(280, 120);
    }

    @Override
    public void execute() {
        //Pleease don't remove this comment GWT.getModuleBaseURL() + ""

        this.saveStatus.setBusy("Adding Raster");
        this.parentDestination = this.getTree().getSelectionModel().getSelectedItem();
//        assert (this.getTree().isExpanded(parentDestination)) : "AddFolderWidget on execute: the parent folder must be expanded before the add operation";    

        this.analyzeUrl();
        this.entity = new RasterTreeNode();
        this.getTree().getStore().insert(parentDestination, this.entity, 0, true);

        this.addVisitor.insertElement(this.entity, parentDestination, 0);

        MementoSaveAddedLayers mementoSaveLayer = new MementoSaveAddedLayers(this);
        // TODO Manage a List (URL depends) of layer
        List<GPBeanTreeModel> layerList = new ArrayList<GPBeanTreeModel>();
        layerList.add(entity);
        mementoSaveLayer.setAddedLayers(MementoBuilder.generateMementoLayerList(layerList));
        mementoSaveLayer.setDescendantMap(this.addVisitor.getFolderDescendantMap());
        GPLayerSaveCache.getInstance().add(mementoSaveLayer);

        clearComponents();
        LayoutManager.getInstance().getStatusMap().setStatus(
                "Added raster on tree succesfully.",
                EnumSearchStatus.STATUS_SEARCH.toString());
    }

    @Override
    public void reset() {
        this.save.disable();
        this.urlText.clear();
        this.saveStatus.clearStatus("");
        setFocusWidget(this.urlText);
    }

    public void showForm() {
        if (!isInitialized()) {
            super.init();
        }
        this.expander.checkNodeState();
    }

    private void clearComponents() {
        super.hide();
    }

    /**
     * @return the tree
     */
    public TreePanel<GPBeanTreeModel> getTree() {
        return tree;
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
                            setSaveStatus(EnumSaveStatus.STATUS_SAVE_ERROR,
                                    EnumSaveStatus.STATUS_MESSAGE_SAVE_ERROR);
                            GeoPlatformMessage.errorMessage("Save WMS Error",
                                    "Problems on saving the new tree state after raster creation");
                        }
                    }

                    @Override
                    public void onSuccess(ArrayList<Long> result) {
                        GPLayerSaveCache.getInstance().remove(memento);
                        LayoutManager.getInstance().getStatusMap().setStatus(
                                "WMS saved successfully.",
                                EnumSearchStatus.STATUS_SEARCH.toString());
                        //Warning: What happens when I delete a raster before save it???
                        memento.getRefBaseElement().setId(result.get(0)); // TODO
                        LayerHandlerManager.fireEvent(peekCacheEvent);
                    }
                });
    }

    private void analyzeUrl() {
        String url = this.urlText.getValue();
        String queryString = url.substring(url.indexOf("?"));

        // TODO
    }

    private boolean checkUrl() {
        String url = this.urlText.getValue();
        if (url == null) {
            System.out.println("URL is NULL");
            return false;
        }
//        url = url.replaceAll("[ ]+", ""); // Delete all space character
//        UtilityLayerModule.replace(url, "[ ]+", ""); // Delete all space character

        if (!url.startsWith("http://")) {
            System.out.println("URL must be start with \"http://\"");
            return false;
        }
        if (!url.contains("/wms?")) {
            System.out.println("URL must contain \"/wms?\"");
            return false;
        }
        // Required field in query string
        if (UtilityLayerModule.match(url, GetMap.REQUEST
                + "[ ]*=[ ]*GetMap").length() == 0) {
            System.out.println("Query String must have \"" + GetMap.REQUEST + "=GetMap\"");
            return false;
        }
        if (UtilityLayerModule.match(url, GetMap.VERSION
                + "[ ]*=[ ]*1\\.(0\\.0|1\\.0|1\\.1)").length() == 0) {
            System.out.println("Query String must have \"" + GetMap.VERSION + "=1.0.0, 1.1.0, or 1.1.1\"");
            return false;
        }

        return true;
    }
}
