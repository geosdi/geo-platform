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
package org.geosdi.geoplatform.gui.client.widget;

import com.extjs.gxt.ui.client.Style.SelectionMode;
import org.geosdi.geoplatform.gui.client.listener.GPDNDListener;
import org.geosdi.geoplatform.gui.client.model.GPRootTreeNode;
import org.geosdi.geoplatform.gui.client.widget.tree.GeoPlatformTreeWidget;
import org.geosdi.geoplatform.gui.model.tree.GPBeanTreeModel;
import org.geosdi.geoplatform.gui.utility.GeoPlatformUtils;

import com.extjs.gxt.ui.client.data.ModelData;
import com.extjs.gxt.ui.client.data.ModelIconProvider;
import com.extjs.gxt.ui.client.data.ModelStringProvider;
import com.extjs.gxt.ui.client.dnd.DND.Feedback;
import com.extjs.gxt.ui.client.dnd.TreePanelDragSource;
import com.extjs.gxt.ui.client.dnd.TreePanelDropTarget;
import com.extjs.gxt.ui.client.event.BaseEvent;
import com.extjs.gxt.ui.client.event.DNDEvent;
import com.extjs.gxt.ui.client.event.DNDListener;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.SelectionChangedEvent;
import com.extjs.gxt.ui.client.event.SelectionChangedListener;
import com.extjs.gxt.ui.client.event.TreePanelEvent;
import com.extjs.gxt.ui.client.store.Store;
import com.extjs.gxt.ui.client.store.TreeStoreEvent;
import com.extjs.gxt.ui.client.widget.treepanel.TreePanel.CheckCascade;
import com.google.gwt.user.client.ui.AbstractImagePrototype;
import org.geosdi.geoplatform.gui.client.LayerEvents;
import org.geosdi.geoplatform.gui.client.model.visitor.VisitorDisplayHide;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 * 
 */
public class LayerTreeWidget extends GeoPlatformTreeWidget<GPBeanTreeModel> {

    private GPRootTreeNode root;
    private boolean initialized;

    /**
     * @Constructor
     */
    public LayerTreeWidget() {
        super();
        this.buildRoot();
    }

    /*
     * Create Root Composite Element
     *
     */
    private void buildRoot() {
        // TODO Auto-generated method stub
        this.root = new GPRootTreeNode(this.tree);
    }

    /**
     * Build Tree
     */
    public void buildTree() {
        if (!initialized) {
            this.initialized = true;
            this.root.modelConverter(GeoPlatformUtils.getInstance().
                    getGlobalConfiguration().getFolderStore().
                    getFolders());
            this.store.add(this.root, true);
        }
    }

    /**
     * Set Tree Properties
     */
    @Override
    public void setTreePanelProperties() {
        setTreePresenter();
        enableCheckChange();
        enableDDSupport();
    }

    /*
     * Define TreePresenter for both Icon and Label Presentation
     * 
     */
    private void setTreePresenter() {
        this.tree.setIconProvider(new ModelIconProvider<GPBeanTreeModel>() {

            @Override
            public AbstractImagePrototype getIcon(GPBeanTreeModel model) {
                return model.getIcon();
            }
        });

        this.tree.setLabelProvider(new ModelStringProvider<GPBeanTreeModel>() {

            @Override
            public String getStringValue(GPBeanTreeModel model, String property) {
                return model.getLabel();
            }
        });

        this.tree.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

        this.tree.getSelectionModel().addSelectionChangedListener(new SelectionChangedListener<GPBeanTreeModel>() {

            @Override
            public void selectionChanged(SelectionChangedEvent<GPBeanTreeModel> se) {
                if (se.getSelectedItem() != null) {
                    System.out.println("TEST SELECTION CHANGED ************ " + se.getSelectedItem());
                }
            }
        });

        this.setCheckable(true);
        this.setCheckStyle(CheckCascade.NONE);
        this.tree.setAutoHeight(true);
    }

    /*
     * Enable Check Box on Tree
     */
    private void enableCheckChange() {
        this.tree.addCheckListener(null);
        this.tree.addListener(Events.CheckChange,
                new Listener<TreePanelEvent<GPBeanTreeModel>>() {

                    @Override
                    public void handleEvent(TreePanelEvent<GPBeanTreeModel> be) {
                        VisitorDisplayHide visitorDisplay = new VisitorDisplayHide(be.getTreePanel());
                        System.out.println("Mi ha chiamato: " + be.getItem());
                        be.getItem().accept(visitorDisplay);
                        //be.getItem().notifyCheckEvent(be.isChecked());
                    }
                });
    }

    /*
     * Add Support for Drag and Drop
     * 
     */
    private void enableDDSupport() {
        TreePanelDragSource dragSource = new TreePanelDragSource(super.tree);
        dragSource.addDNDListener(new DNDListener() {

            @Override
            public void dragStart(DNDEvent e) {
                ModelData sel = tree.getSelectionModel().getSelectedItem();
                if (sel != null
                        && sel == tree.getStore().getRootItems().get(0)) {
                    e.setCancelled(true);
                    e.getStatus().setStatus(false);
                    return;
                }
                super.dragStart(e);
                //TreeStoreEvent dragStartEvent =  new TreeStoreEvent<GPBeanTreeModel>(tree.getStore());
                //dragStartEvent.setType(null)
                ((TreePanelDragSource) e.getSource()).fireEvent(LayerEvents.GP_DRAG_START, new TreeStoreEvent<GPBeanTreeModel>(tree.getStore()));
            }
        });
        //Listener for launch Drag Lost Events
        Listener listenerDragLost = new Listener() {

            @Override
            public void handleEvent(BaseEvent be) {
                ((TreePanelDragSource) be.getSource()).fireEvent(LayerEvents.GP_DRAG_LOST, new TreeStoreEvent<GPBeanTreeModel>(tree.getStore()));
                System.out.println("DragSource: Ho intercettato il drag cancelled");
            }
        };
        //Intercepting Drag Lost Events
        dragSource.addListener(Events.DragCancel, listenerDragLost);
        dragSource.addListener(Events.DragEnd, listenerDragLost);
        dragSource.addListener(Events.DragFail, listenerDragLost);

        GPDNDListener dropAddListener = new GPDNDListener();
        dragSource.addListener(LayerEvents.GP_DRAG_START, dropAddListener);
        dragSource.addListener(LayerEvents.GP_DRAG_LOST, dropAddListener);

        TreePanelDropTarget dropTarget = new GPTreePanelDropTarget(super.tree);
        dropTarget.setAllowSelfAsSource(true);
        dropTarget.setAllowDropOnLeaf(false);
        dropTarget.setFeedback(Feedback.BOTH);

        dropTarget.addListener(LayerEvents.GP_DROP, dropAddListener);
        //dropTarget.addListener(LayerEvents.GP_DRAG_LOST, dropAddListener);

        dropTarget.addListener(Events.DragCancel, new Listener() {

            @Override
            public void handleEvent(BaseEvent be) {
                System.out.println(this.getClass().getName() + "DropTarget: Ho intercettato il drag cancelled");
            }
        });


        super.store.addListener(Store.Add, dropAddListener);
    }
}
