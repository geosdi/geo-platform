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
package org.geosdi.geoplatform.gui.client.listener;

import com.extjs.gxt.ui.client.event.EventType;
import org.geosdi.geoplatform.gui.client.model.visitor.VisitorPosition;
import org.geosdi.geoplatform.gui.model.tree.GPBeanTreeModel;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.store.Store;
import com.extjs.gxt.ui.client.store.TreeStore;
import com.extjs.gxt.ui.client.store.TreeStoreEvent;
import org.geosdi.geoplatform.gui.action.ISave;
import org.geosdi.geoplatform.gui.client.command.memento.dnd.chain.GPSaveTreeElementManager;
import org.geosdi.geoplatform.gui.client.command.memento.dnd.chain.SaveTreeElementManager;
import org.geosdi.geoplatform.gui.client.config.MementoModuleInjector;
import org.geosdi.geoplatform.gui.model.tree.LayerEvents;
import org.geosdi.geoplatform.gui.client.model.FolderTreeNode;
import org.geosdi.geoplatform.gui.client.model.memento.save.bean.MementoSaveDragDrop;
import org.geosdi.geoplatform.gui.client.model.memento.save.IMementoSave;
import org.geosdi.geoplatform.gui.client.model.visitor.VisitorDisplayHide;

public class GPDNDListener implements Listener<TreeStoreEvent<GPBeanTreeModel>>,
        ISave<MementoSaveDragDrop> {

    private VisitorDisplayHide checkerVisitor;
    private VisitorPosition visitor = new VisitorPosition();
    private GPBeanTreeModel changedElement;
    private GPBeanTreeModel parentDestination;
    private int newIndex;
    private boolean isActiveDrop = false;
    private boolean isFolderDrop = false;
    private GPSaveTreeElementManager saveTreeElement = new SaveTreeElementManager();

    public GPDNDListener(VisitorDisplayHide visitorDisplayHide) {
        this.checkerVisitor = visitorDisplayHide;
    }


    /*We need to manager three kinds of DND Events:
     *  LayerEvents.GP_DRAG_START
     *  LayerEvents.GP_DRAG_LOST
     *  LayerEvents.GP_DROP
     * and the Store.Add event, in this way we can manage the visit on tree
     */
    @SuppressWarnings("unchecked")
    @Override
    public void handleEvent(TreeStoreEvent<GPBeanTreeModel> be) {
        this.manageDropActivation(be.getType());
        if (be.getParent() != null && Store.Add.equals(be.getType()) && this.isFolderDrop == false) {
            TreeStore<GPBeanTreeModel> treeStore = (TreeStore<GPBeanTreeModel>) be.getSource();
            this.parentDestination = be.getParent();
            this.newIndex = be.getIndex();
            this.changedElement = treeStore.getChild(parentDestination, newIndex);
            if (this.changedElement instanceof FolderTreeNode) {
                this.isFolderDrop = true;
            }
        } else if (this.isActiveDrop && LayerEvents.GP_DROP.equals(be.getType())) {
            this.visitor.fixPosition(changedElement, parentDestination, newIndex);
            MementoSaveDragDrop mementoSaveDND = new MementoSaveDragDrop(this);
            mementoSaveDND.setRefBaseElement(changedElement);
            mementoSaveDND.setNewZIndex(changedElement.getzIndex());
            mementoSaveDND.setRefNewParent(
                    (parentDestination instanceof FolderTreeNode)
                    ? (FolderTreeNode) parentDestination : null);
            mementoSaveDND.setDescendantMap(
                    this.visitor.getFolderDescendantMap());
            IMementoSave mementoSave = MementoModuleInjector.MainInjector.getInstance().getMementoSave();
            mementoSave.add(mementoSaveDND);
            this.isActiveDrop = false;
            this.isFolderDrop = false;
            this.checkerVisitor.realignViewState(changedElement);
        }
    }

    private void manageDropActivation(EventType eventType) {
        if (LayerEvents.GP_DRAG_START.equals(eventType)) {
            this.isActiveDrop = true;
            this.isFolderDrop = false;
        } else if (LayerEvents.GP_DRAG_LOST.equals(eventType)) {
            this.isActiveDrop = false;
        }
    }

    @Override
    public void executeSave(final MementoSaveDragDrop memento) {
        this.saveTreeElement.bind(memento);
    }

}
