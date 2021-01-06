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
package org.geosdi.geoplatform.gui.client.widget.tree.properties.dnd;

import com.extjs.gxt.ui.client.data.ModelData;
import com.extjs.gxt.ui.client.dnd.TreePanelDragSource;
import com.extjs.gxt.ui.client.event.BaseEvent;
import com.extjs.gxt.ui.client.event.DNDEvent;
import com.extjs.gxt.ui.client.event.DNDListener;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.store.TreeStoreEvent;
import javax.inject.Inject;
import javax.inject.Singleton;
import org.geosdi.geoplatform.gui.model.tree.LayerEvents;
import org.geosdi.geoplatform.gui.client.listener.GPDNDListener;
import org.geosdi.geoplatform.gui.client.widget.tree.GPTreePanel;
import org.geosdi.geoplatform.gui.client.widget.tree.dnd.GinDragSource;
import org.geosdi.geoplatform.gui.client.widget.tree.dnd.GinGPDNDListener;
import org.geosdi.geoplatform.gui.client.widget.tree.panel.GinTreePanel;
import org.geosdi.geoplatform.gui.model.tree.GPBeanTreeModel;

/**
 *
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@Singleton
public class GPTreeDragSupport implements TreeDragSupport {

    private final TreePanelDragSource dragSource;
    private final GPDNDListener gpDNDListener;
    private final GPTreePanel tree;

    @Inject
    public GPTreeDragSupport(GinDragSource theDragSource,
            GinGPDNDListener theGpDNDListener,
            GinTreePanel theTree) {
        this.dragSource = theDragSource.get();
        this.gpDNDListener = theGpDNDListener.get();
        this.tree = theTree.get();
    }

    @Override
    public void enableDrag() {
        dragSource.addDNDListener(new DNDListener() {
            @Override
            public void dragStart(DNDEvent e) {
                ModelData sel = tree.getSelectionModel().getSelectedItem();
                if (tree.getSelectionModel().getSelectedItems().size() > 1
                        || (sel != null && sel == tree.getStore().getRootItems().get(
                        0))) {
                    e.setCancelled(Boolean.TRUE);
                    e.getStatus().setStatus(Boolean.FALSE);
                } else {
                    super.dragStart(e);
                    ((TreePanelDragSource) e.getSource()).fireEvent(
                            LayerEvents.GP_DRAG_START,
                            new TreeStoreEvent<GPBeanTreeModel>(
                            tree.getStore()));
                }
            }
        });

        dragSource.addListener(LayerEvents.GP_DRAG_START, gpDNDListener);
        dragSource.addListener(LayerEvents.GP_DRAG_LOST, gpDNDListener);

        //Listener for launch Drag Lost Events
        Listener listenerDragLost = new Listener() {
            @Override
            public void handleEvent(BaseEvent be) {
                ((TreePanelDragSource) be.getSource()).fireEvent(
                        LayerEvents.GP_DRAG_LOST,
                        new TreeStoreEvent<GPBeanTreeModel>(
                        tree.getStore()));
                //System.out.println("DragSource: Ho intercettato il drag cancelled");
            }
        };
        //Intercepting Drag Lost Events
        dragSource.addListener(Events.DragCancel, listenerDragLost);
        dragSource.addListener(Events.DragEnd, listenerDragLost);
        dragSource.addListener(Events.DragFail, listenerDragLost);
    }
}
