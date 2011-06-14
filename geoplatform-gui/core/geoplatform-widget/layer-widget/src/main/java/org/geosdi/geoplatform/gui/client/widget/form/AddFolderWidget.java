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
package org.geosdi.geoplatform.gui.client.widget.form;

import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.ComponentEvent;
import com.extjs.gxt.ui.client.event.KeyListener;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.FieldSet;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.extjs.gxt.ui.client.widget.layout.FormLayout;
import com.extjs.gxt.ui.client.widget.treepanel.TreePanel;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.user.client.rpc.AsyncCallback;
import org.geosdi.geoplatform.gui.action.ISave;
import org.geosdi.geoplatform.gui.client.BasicWidgetResources;
import org.geosdi.geoplatform.gui.client.LayerResources;
import org.geosdi.geoplatform.gui.client.model.FolderTreeNode;
import org.geosdi.geoplatform.gui.client.model.memento.GPLayerSaveCache;
import org.geosdi.geoplatform.gui.client.model.memento.MementoBuilder;
import org.geosdi.geoplatform.gui.client.model.memento.MementoFolder;
import org.geosdi.geoplatform.gui.client.model.memento.MementoSaveAddedFolder;
import org.geosdi.geoplatform.gui.client.model.memento.puregwt.event.PeekCacheEvent;
import org.geosdi.geoplatform.gui.client.model.visitor.VisitorAddElement;
import org.geosdi.geoplatform.gui.client.service.LayerRemote;
import org.geosdi.geoplatform.gui.client.widget.SaveStatus;
import org.geosdi.geoplatform.gui.client.widget.SaveStatus.EnumSaveStatus;
import org.geosdi.geoplatform.gui.client.widget.SearchStatus.EnumSearchStatus;
import org.geosdi.geoplatform.gui.client.widget.expander.GPLayerExpander;
import org.geosdi.geoplatform.gui.client.widget.tree.GPTreePanel;
import org.geosdi.geoplatform.gui.client.widget.tree.form.GPTreeFormWidget;
import org.geosdi.geoplatform.gui.configuration.message.GeoPlatformMessage;
import org.geosdi.geoplatform.gui.impl.view.LayoutManager;
import org.geosdi.geoplatform.gui.model.tree.GPBeanTreeModel;
import org.geosdi.geoplatform.gui.puregwt.layers.LayerHandlerManager;
import org.geosdi.geoplatform.gui.puregwt.progressbar.layers.event.DisplayLayersProgressBarEvent;

/**
 *
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email  giuseppe.lascaleia@geosdi.org
 */
public class AddFolderWidget extends GPTreeFormWidget<FolderTreeNode>
        implements ISave<MementoSaveAddedFolder> {

    private TreePanel<GPBeanTreeModel> tree;
    private TextField<String> folderText;
    private Button save;
    private Button cancel;
    private VisitorAddElement addVisitor;
    private GPBeanTreeModel parentDestination;
    private GPLayerExpander expander;
    private PeekCacheEvent peekCacheEvent = new PeekCacheEvent();

    /**
     *@param theTree 
     * 
     */
    public AddFolderWidget(TreePanel<GPBeanTreeModel> theTree) {
        super(true);
        this.tree = theTree;
        this.addVisitor = new VisitorAddElement();
        this.expander = new GPLayerExpander(this);
    }

    @Override
    public void addComponentToForm() {
        this.fieldSet = new FieldSet();
        this.fieldSet.setHeading("Folder Name");

        FormLayout layout = new FormLayout();
        layout.setLabelWidth(40);
        fieldSet.setLayout(layout);

        this.folderText = new TextField<String>();
        this.folderText.setFieldLabel("Folder");

        this.folderText.addKeyListener(new KeyListener() {

            @Override
            public void componentKeyUp(ComponentEvent event) {
                if (folderText.getValue() == null) {
                    if ((event.getKeyCode() == KeyCodes.KEY_BACKSPACE)
                            || (event.getKeyCode() == KeyCodes.KEY_DELETE)) {
                        reset();
                    }
                } else {
                    if (folderText.getValue().length() > 3) {
                        save.enable();
                    } else {
                        save.disable();
                    }
                }
            }

            @Override
            public void componentKeyPress(ComponentEvent event) {
                if ((event.getKeyCode() == 13) && (folderText.getValue() != null)
                        && (folderText.getValue().length() > 3)) {
                    execute();
                }
            }
        });

        this.fieldSet.add(this.folderText);

        this.formPanel.add(this.fieldSet);

        this.saveStatus = new SaveStatus();
        this.saveStatus.setAutoWidth(true);

        this.formPanel.getButtonBar().add(this.saveStatus);

        formPanel.setButtonAlign(HorizontalAlignment.RIGHT);

        save = new Button("Create", LayerResources.ICONS.addFolder(),
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
        
        setFocusWidget(this.folderText);
    }

    @Override
    public void initSize() {
        setHeading("Add Folder");
        setSize(330, 170);
    }

    @Override
    public void initSizeFormPanel() {
        this.formPanel.setHeaderVisible(false);
        this.formPanel.setSize(280, 120);
    }

    @Override
    public void execute() {
        this.saveStatus.setBusy("Adding Folder");
        this.parentDestination = this.getTree().getSelectionModel().getSelectedItem();
//        assert (this.getTree().isExpanded(parentDestination)) : "AddFolderWidget on execute: the parent folder must be expanded before the add operation";
        this.entity = new FolderTreeNode(this.folderText.getValue());
        this.getTree().getStore().insert(parentDestination, this.entity, 0, true);

        this.addVisitor.insertElement(this.entity, parentDestination, 0);

        MementoSaveAddedFolder mementoSaveAdd = new MementoSaveAddedFolder(this);
        mementoSaveAdd.setAddedFolder(MementoBuilder.buildSaveFolderMemento(
                this.entity));
        mementoSaveAdd.setDescendantMap(this.addVisitor.getFolderDescendantMap());

        GPLayerSaveCache.getInstance().add(mementoSaveAdd);

        clearComponents();
        LayoutManager.get().getStatusMap().setStatus(
                "Added folder on tree succesfully.",
                EnumSearchStatus.STATUS_SEARCH.toString());
    }

    @Override
    public void reset() {
        this.save.disable();
        this.folderText.clear();
        this.saveStatus.clearStatus("");
        setFocusWidget(this.folderText);
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
    public void executeSave(final MementoSaveAddedFolder memento) {
        //Warning: The following conversion is absolutely necessary!
        memento.convertMementoToWs();

        LayerRemote.Util.getInstance().saveAddedFolderAndTreeModifications(memento,
                new AsyncCallback<Long>() {

                    @Override
                    public void onFailure(Throwable caught) {
                        LayerHandlerManager.fireEvent(new DisplayLayersProgressBarEvent(false));
                        setSaveStatus(EnumSaveStatus.STATUS_SAVE_ERROR,
                                EnumSaveStatus.STATUS_MESSAGE_SAVE_ERROR);
                        GeoPlatformMessage.errorMessage("Save Folder Error",
                                "Problems on saving the new tree state after folder creation");
                    }

                    @Override
                    public void onSuccess(Long result) {
                        GPLayerSaveCache.getInstance().remove(memento);
                        LayoutManager.get().getStatusMap().setStatus(
                                "Folders saveded successfully.",
                                EnumSearchStatus.STATUS_SEARCH.toString());
                        //Warning: What happens when I delete a folder before save it???
                        MementoFolder mementoAdded = memento.getAddedFolder();
                        mementoAdded.getRefBaseElement().setId(result);
                        mementoAdded.getRefBaseElement().setLoaded(true);
                        LayerHandlerManager.fireEvent(peekCacheEvent);
                    }
                });
    }
}
