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
package org.geosdi.geoplatform.gui.client.listener;

import com.extjs.gxt.ui.client.event.EventType;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.store.Store;
import com.extjs.gxt.ui.client.store.TreeStore;
import com.extjs.gxt.ui.client.store.TreeStoreEvent;
import org.geosdi.geoplatform.gui.client.LayerEvents;
import org.geosdi.geoplatform.gui.client.model.FolderTreeNode;
import org.geosdi.geoplatform.gui.client.model.visitor.VisitorAddElement;
import org.geosdi.geoplatform.gui.model.tree.GPBeanTreeModel;

/**
 * @author Nazzareno Sileno - CNR IMAA geoSDI Group
 * @email  nazzareno.sileno@geosdi.org
 */
public class GPAddListener implements Listener<TreeStoreEvent<GPBeanTreeModel>> {

    private VisitorAddElement visitorAddElement = new VisitorAddElement();
    private boolean activeAddElement;
    private boolean isFolderAdd;
    private GPBeanTreeModel newElement;
    private GPBeanTreeModel parentDestination;
    private int newIndex;

    @Override
    public void handleEvent(TreeStoreEvent<GPBeanTreeModel> be) {
        this.manageAddActivation(be.getType());
        if (be.getParent() != null && Store.Add.equals(be.getType()) && this.isFolderAdd == false) {
            TreeStore<GPBeanTreeModel> treeStore = (TreeStore<GPBeanTreeModel>) be.getSource();
            this.parentDestination = be.getParent();
            this.newIndex = be.getIndex();
            this.newElement = treeStore.getChild(parentDestination, newIndex);
            System.out.println("Changed element: " + newElement.getLabel());
            if (this.newElement instanceof FolderTreeNode) {
                this.isFolderAdd = true;
            }
        } else if (this.activeAddElement && LayerEvents.GP_ADD_ELEMENT.equals(be.getType())) {
            System.out.println("IN fix dell'AddListener");
            System.out.println("Added element: " + newElement.getLabel());
            System.out.println("Parent destinazione: " + parentDestination.getLabel());
            System.out.println("Indice destinazione: " + newIndex);
            visitorAddElement.insertElement(newElement, parentDestination, newIndex);
            this.activeAddElement = false;
            this.isFolderAdd = false;
            //this.checkerVisitor.realignViewState(newElement);
        }
    }

    private void manageAddActivation(EventType eventType) {
        if (LayerEvents.GP_ADD_START.equals(eventType)) {
            this.activeAddElement = true;
            this.isFolderAdd = false;
        }
    }
}
