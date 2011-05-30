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
import org.geosdi.geoplatform.gui.client.BasicWidgetResources;
import org.geosdi.geoplatform.gui.client.model.FolderTreeNode;
import org.geosdi.geoplatform.gui.client.model.GPRootTreeNode;
import org.geosdi.geoplatform.gui.client.model.visitor.VisitorAddElement;
import org.geosdi.geoplatform.gui.client.service.LayerRemote;
import org.geosdi.geoplatform.gui.client.service.LayerRemoteAsync;
import org.geosdi.geoplatform.gui.client.widget.SaveStatus;
import org.geosdi.geoplatform.gui.client.widget.SaveStatus.EnumSaveStatus;
import org.geosdi.geoplatform.gui.client.widget.tree.form.GPTreeFormWidget;
import org.geosdi.geoplatform.gui.configuration.message.GeoPlatformMessage;
import org.geosdi.geoplatform.gui.model.tree.GPBeanTreeModel;

/**
 *
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email  giuseppe.lascaleia@geosdi.org
 */
public class AddFolderWidget extends GPTreeFormWidget<FolderTreeNode> {

    private LayerRemoteAsync layerService = LayerRemote.Util.getInstance();
    private TreePanel tree;
    private TextField<String> folderText;
    private Button save;
    private Button cancel;
    private VisitorAddElement addVisitor;
    private GPBeanTreeModel parentDestination;

    /**
     *@Constructor
     */
    public AddFolderWidget(TreePanel theTree) {
        super(true);
        this.tree = theTree;
        //this.addVisitor = new VisitorAddElement((GPRootTreeNode)tree.getStore().getRootItems().get(0));
        this.addVisitor = new VisitorAddElement();
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

        save = new Button("Save", BasicWidgetResources.ICONS.save(),
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
        this.saveStatus.setBusy("Saving Folder");
        parentDestination = (GPBeanTreeModel) this.tree.getSelectionModel().getSelectedItem();

        this.entity = new FolderTreeNode(this.folderText.getValue());
        this.tree.getStore().insert(parentDestination, this.entity, 0, true);
        this.addVisitor.insertElement(this.entity, parentDestination, 0);

//        this.tree.getStore().insert(
//                parentDestination, this.entity, 0,
//                true);

        if (parentDestination instanceof GPRootTreeNode) {
            //this.addVisitor.insertElement(this.entity, parentDestination, 0);
            this.saveFolderForUser();
        } else {
            this.saveFolder();
        }
    }

    @Override
    public void reset() {
        this.save.disable();
        this.folderText.clear();
        this.saveStatus.clearStatus("");
    }

    @Override
    public void show() {
        if (!isInitialized()) {
            super.init();
        }
        super.show();
    }

    private void clearComponents() {
        super.hide();
    }

    private void saveFolderForUser() {
        this.layerService.saveFolderForUser(entity.getLabel(),
                entity.getzIndex(), new AsyncCallback<Long>() {

            @Override
            public void onFailure(Throwable caught) {
                setSaveStatus(EnumSaveStatus.STATUS_SAVE_ERROR,
                        EnumSaveStatus.STATUS_MESSAGE_SAVE_ERROR);
                parentDestination.remove(entity);
            }

            @Override
            public void onSuccess(Long result) {
                setSaveStatus(EnumSaveStatus.STATUS_SAVE,
                        EnumSaveStatus.STATUS_MESSAGE_SAVE);
                entity.setId(result);
//                tree.getStore().insert(
//                        parentDestination, entity, 0,
//                        true);
                clearComponents();
            }
        });
    }

    private void saveFolder() {
        this.layerService.saveFolder(
                ((FolderTreeNode) parentDestination).getId(),
                entity.getLabel(), entity.getzIndex(), new AsyncCallback<Long>() {

            @Override
            public void onFailure(Throwable caught) {
                setSaveStatus(EnumSaveStatus.STATUS_SAVE_ERROR,
                        EnumSaveStatus.STATUS_MESSAGE_SAVE_ERROR);
                parentDestination.getParent().remove(parentDestination);
                GeoPlatformMessage.errorMessage("Add Folder Error",
                        "The folder with label : "
                        + ((GPBeanTreeModel) parentDestination.getParent()).getLabel()
                        + " was deleted on the server.");
            }

            @Override
            public void onSuccess(Long result) {
                setSaveStatus(EnumSaveStatus.STATUS_SAVE,
                        EnumSaveStatus.STATUS_MESSAGE_SAVE);
                entity.setId(result);
                if (((FolderTreeNode) parentDestination).isLoaded()) {
                    tree.getStore().insert(
                            parentDestination, entity, 0,
                            true);
                } else {
                    tree.setExpanded(parentDestination, true);
                }
                clearComponents();
            }
        });
    }
}
