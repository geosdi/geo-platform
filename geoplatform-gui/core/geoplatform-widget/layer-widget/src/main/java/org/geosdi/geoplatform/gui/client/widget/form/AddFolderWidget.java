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
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.ComponentEvent;
import com.extjs.gxt.ui.client.event.KeyListener;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.FieldSet;
import com.extjs.gxt.ui.client.widget.layout.FormLayout;
import com.extjs.gxt.ui.client.widget.treepanel.TreePanel;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.user.client.ui.AbstractImagePrototype;
import org.geosdi.geoplatform.gui.action.ISave;
import org.geosdi.geoplatform.gui.client.BasicWidgetResources;
import org.geosdi.geoplatform.gui.client.LayerResources;
import org.geosdi.geoplatform.gui.client.command.memento.toolbar.SaveAddedFolderAndTreeModificationsRequest;
import org.geosdi.geoplatform.gui.client.command.memento.toolbar.SaveAddedFolderAndTreeModificationsResponse;
import org.geosdi.geoplatform.gui.client.config.MementoModuleInjector;
import org.geosdi.geoplatform.gui.client.i18n.LayerModuleConstants;
import org.geosdi.geoplatform.gui.client.i18n.buttons.ButtonsConstants;
import org.geosdi.geoplatform.gui.client.i18n.status.SaveStatusConstants;
import org.geosdi.geoplatform.gui.client.model.FolderTreeNode;
import org.geosdi.geoplatform.gui.client.model.GPRootTreeNode;
import org.geosdi.geoplatform.gui.client.model.memento.save.MementoSaveBuilder;
import org.geosdi.geoplatform.gui.client.model.memento.save.bean.MementoFolder;
import org.geosdi.geoplatform.gui.client.model.memento.save.bean.MementoSaveAddedFolder;
import org.geosdi.geoplatform.gui.client.model.memento.puregwt.event.PeekCacheEvent;
import org.geosdi.geoplatform.gui.client.model.memento.save.IMementoSave;
import org.geosdi.geoplatform.gui.client.model.visitor.VisitorAddElement;
import org.geosdi.geoplatform.gui.client.widget.SaveStatus;
import org.geosdi.geoplatform.gui.client.widget.SaveStatus.EnumSaveStatus;
import org.geosdi.geoplatform.gui.client.widget.SearchStatus.EnumSearchStatus;
import org.geosdi.geoplatform.gui.client.widget.expander.GPLayerExpander;
import org.geosdi.geoplatform.gui.client.widget.tree.form.GPTreeFormWidget;
import org.geosdi.geoplatform.gui.command.api.GPClientCommand;
import org.geosdi.geoplatform.gui.command.api.GPClientCommandExecutor;
import org.geosdi.geoplatform.gui.configuration.GPSecureStringTextField;
import org.geosdi.geoplatform.gui.configuration.message.GeoPlatformMessage;
import org.geosdi.geoplatform.gui.utility.GPSessionTimeout;
import org.geosdi.geoplatform.gui.impl.map.event.GPLoginEvent;
import org.geosdi.geoplatform.gui.impl.view.LayoutManager;
import org.geosdi.geoplatform.gui.model.tree.GPBeanTreeModel;
import org.geosdi.geoplatform.gui.puregwt.GPHandlerManager;
import org.geosdi.geoplatform.gui.puregwt.layers.LayerHandlerManager;
import org.geosdi.geoplatform.gui.puregwt.progressbar.layers.event.DisplayLayersProgressBarEvent;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class AddFolderWidget extends GPTreeFormWidget<FolderTreeNode> implements ISave<MementoSaveAddedFolder> {

    private GPSecureStringTextField folderText;
    private Button save;
    private Button cancel;
    private VisitorAddElement addVisitor;
    private GPBeanTreeModel parentDestination;
    private GPLayerExpander expander;
    private PeekCacheEvent peekCacheEvent = new PeekCacheEvent();
    private SaveAddedFolderAndTreeModificationsRequest request = new SaveAddedFolderAndTreeModificationsRequest();

    /**
     * @param theTree
     */
    public AddFolderWidget(TreePanel<GPBeanTreeModel> theTree) {
        super(theTree, true);
        this.addVisitor = new VisitorAddElement();
        this.expander = new GPLayerExpander(theTree, this);
    }

    @Override
    public void addComponentToForm() {
        this.fieldSet = new FieldSet();
        this.fieldSet.setHeadingHtml(LayerModuleConstants.INSTANCE.
                AddFolderWidget_fieldSetHeadingText());

        FormLayout layout = new FormLayout();
        layout.setLabelWidth(40);
        fieldSet.setLayout(layout);

        this.folderText = new GPSecureStringTextField();
        this.folderText.setFieldLabel(LayerModuleConstants.INSTANCE.
                AddFolderWidget_folderLabelText());

        this.folderText.addKeyListener(new KeyListener() {

            @Override
            public void componentKeyUp(ComponentEvent event) {
                if (folderText.getValue() == null) {
                    if ((event.getKeyCode() == KeyCodes.KEY_BACKSPACE) || (event.getKeyCode() == KeyCodes.KEY_DELETE)) {
                        reset();
                    }
                } else {
                    if (folderText.getValue().length() > 0) {
                        save.enable();
                    } else {
                        save.disable();
                    }
                }
            }

            @Override
            public void componentKeyPress(ComponentEvent event) {
                if ((event.getKeyCode() == KeyCodes.KEY_ENTER) && (folderText.getValue() != null) && (folderText
                        .getValue().length() > 0)) {
                    execute();
                }
            }

        });

        this.fieldSet.add(this.folderText);

        this.getFormPanel().add(this.fieldSet);

        this.saveStatus = new SaveStatus();
        this.saveStatus.setAutoWidth(true);

        this.getFormPanel().getButtonBar().add(this.saveStatus);

        getFormPanel().setButtonAlign(HorizontalAlignment.RIGHT);

        save = new Button(ButtonsConstants.INSTANCE.createText(),
                AbstractImagePrototype.create(LayerResources.ICONS.addFolder()), new SelectionListener<ButtonEvent>() {

            @Override
            public void componentSelected(ButtonEvent ce) {
                execute();
            }

        });

        save.setEnabled(false);

        this.getFormPanel().addButton(save);

        this.cancel = new Button(ButtonsConstants.INSTANCE.cancelText(),
                AbstractImagePrototype.create(BasicWidgetResources.ICONS.cancel()),
                new SelectionListener<ButtonEvent>() {

                    @Override
                    public void componentSelected(ButtonEvent ce) {
                        clearComponents();
                    }

                });

        this.getFormPanel().addButton(cancel);

        setFocusWidget(this.folderText);
    }

    @Override
    public void initSize() {
        setHeadingHtml(LayerModuleConstants.INSTANCE.AddFolderWidget_headingText());
        setSize(330, 170);
    }

    @Override
    public void initSizeFormPanel() {
        this.getFormPanel().setHeaderVisible(false);
        this.getFormPanel().setSize(280, 120);
    }

    @Override
    public void execute() {
        this.saveStatus.setBusy(LayerModuleConstants.INSTANCE.
                AddFolderWidget_statusAddingFolderText());
        this.parentDestination = this.getTree().getSelectionModel().getSelectedItem();
        //        assert (this.getTree().isExpanded(parentDestination)) : "AddFolderWidget on execute: the parent folder must be expanded before the add operation";
        this.entity = new FolderTreeNode(this.folderText.getValue());
        this.entity.setLoaded(true);
        this.getTree().getStore().insert(parentDestination, this.entity, 0, true);

        this.addVisitor.insertElement(this.entity, parentDestination, 0);

        MementoSaveAddedFolder mementoSaveAdd = new MementoSaveAddedFolder(this);
        mementoSaveAdd.setAddedFolder(MementoSaveBuilder.buildSaveFolderMemento(this.entity));
        mementoSaveAdd.setDescendantMap(this.addVisitor.getFolderDescendantMap());

        IMementoSave mementoSave = MementoModuleInjector.MainInjector.getInstance().getMementoSave();
        mementoSave.add(mementoSaveAdd);

        clearComponents();
        LayoutManager.getInstance().getStatusMap()
                .setStatus(LayerModuleConstants.INSTANCE.AddFolderWidget_statusAddedFolderSuccessText(),
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
        GPBeanTreeModel selectedItem = this.tree.getSelectionModel().getSelectedItem();
        if (selectedItem instanceof GPRootTreeNode) {
            this.tree.setExpanded(selectedItem, true, false);
        }
        this.expander.checkNodeState();
    }

    private void clearComponents() {
        super.hide();
    }

    @Override
    public void executeSave(final MementoSaveAddedFolder memento) {
        //Warning: The following conversion is absolutely necessary!
        memento.convertMementoToWs();

        this.request.setMemento(memento);

        GPClientCommandExecutor.executeCommand(new GPClientCommand<SaveAddedFolderAndTreeModificationsResponse>() {

            private static final long serialVersionUID = -2132391639495165283L;

            {
                super.setCommandRequest(request);
            }

            @Override
            public void onCommandSuccess(SaveAddedFolderAndTreeModificationsResponse response) {
                IMementoSave mementoSave = MementoModuleInjector.MainInjector.getInstance().getMementoSave();
                mementoSave.remove(memento);
                LayoutManager.getInstance().getStatusMap()
                        .setStatus(LayerModuleConstants.INSTANCE.AddFolderWidget_statusSaveFolderSuccessText(),
                                EnumSearchStatus.STATUS_SEARCH.toString());
                MementoFolder mementoAdded = memento.getAddedFolder();
                mementoAdded.getRefBaseElement().setId(response.getResult());
                mementoAdded.getRefBaseElement().setLoaded(true);
                LayerHandlerManager.fireEvent(peekCacheEvent);
            }

            @Override
            public void onCommandFailure(Throwable exception) {
                if (exception.getCause() instanceof GPSessionTimeout) {
                    GPHandlerManager.fireEvent(new GPLoginEvent(peekCacheEvent));
                } else {
                    LayerHandlerManager.fireEvent(new DisplayLayersProgressBarEvent(false));
                    setStatus(EnumSaveStatus.STATUS_SAVE_ERROR.getValue(),
                            SaveStatusConstants.INSTANCE.STATUS_MESSAGE_SAVE_ERROR().toString());
                    GeoPlatformMessage.errorMessage(LayerModuleConstants.INSTANCE.
                                    AddFolderWidget_saveFolderErrorTitleText(),
                            LayerModuleConstants.INSTANCE.AddFolderWidget_saveFolderErrorBodyText());
                }
            }

        });
    }

}
