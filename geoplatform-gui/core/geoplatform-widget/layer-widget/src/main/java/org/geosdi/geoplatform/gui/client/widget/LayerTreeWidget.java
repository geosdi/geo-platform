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
import com.extjs.gxt.ui.client.data.BaseTreeLoader;
import org.geosdi.geoplatform.gui.client.listener.GPDNDListener;
import org.geosdi.geoplatform.gui.client.model.GPRootTreeNode;
import org.geosdi.geoplatform.gui.client.widget.tree.GeoPlatformTreeWidget;
import org.geosdi.geoplatform.gui.model.tree.GPBeanTreeModel;

import com.extjs.gxt.ui.client.data.ModelData;
import com.extjs.gxt.ui.client.data.ModelIconProvider;
import com.extjs.gxt.ui.client.data.ModelStringProvider;
import com.extjs.gxt.ui.client.data.RpcProxy;
import com.extjs.gxt.ui.client.data.TreeLoader;
import com.extjs.gxt.ui.client.data.TreeModel;
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
import com.extjs.gxt.ui.client.store.TreeStore;
import com.extjs.gxt.ui.client.store.TreeStoreEvent;
import com.extjs.gxt.ui.client.widget.menu.Menu;
import com.extjs.gxt.ui.client.widget.menu.MenuItem;
import com.extjs.gxt.ui.client.widget.treepanel.TreePanel;
import com.extjs.gxt.ui.client.widget.treepanel.TreePanel.CheckCascade;
import com.extjs.gxt.ui.client.widget.treepanel.TreePanel.TreeNode;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AbstractImagePrototype;
import java.util.List;
import org.geosdi.geoplatform.gui.client.LayerEvents;
import org.geosdi.geoplatform.gui.client.LayerResources;
import org.geosdi.geoplatform.gui.client.action.menu.AddLayerAction;
import org.geosdi.geoplatform.gui.client.model.FolderTreeNode;
import org.geosdi.geoplatform.gui.client.model.visitor.VisitorDisplayHide;
import org.geosdi.geoplatform.gui.client.service.LayerRemote;
import org.geosdi.geoplatform.gui.client.service.LayerRemoteAsync;
import org.geosdi.geoplatform.gui.client.widget.toolbar.mediator.MediatorToolbarTreeAction;
import org.geosdi.geoplatform.gui.server.gwt.LayerRemoteImpl;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 * 
 */
public class LayerTreeWidget extends GeoPlatformTreeWidget<GPBeanTreeModel> {

    private LayerRemoteAsync layerService = LayerRemoteImpl.Util.getInstance();
    private VisitorDisplayHide visitorDisplay = new VisitorDisplayHide(this.tree);
    private MediatorToolbarTreeAction actionMediator;
    private GPRootTreeNode root;
    private GPBeanTreeModel selectedElement;
    TreeLoader<GPBeanTreeModel> loader;
    private boolean initialized;

    /**
     * @Constructor
     */
    public LayerTreeWidget() {
        super();
        this.buildRoot();
        this.enableAsyncLoader();
        this.setTreePanelProperties();
        this.actionMediator = new MediatorToolbarTreeAction();
        //TODO: After toolbar implementation remove this method
        this.addMenuAddElement();
    }

    /*
     * Create Root Composite Element
     *
     */
    private void buildRoot() {
        this.root = new GPRootTreeNode(this.tree);
    }

    /**
     * Build Tree
     */
    public void buildTree() {
        if (!initialized) {
            store.add(root, true);
            tree.setExpanded(root, true, true);

//            //I need to specify mock userID
//            layerService.loadUserFolders("user_0", new AsyncCallback<List<FolderTreeNode>>() {
//
//                @Override
//                public void onFailure(Throwable caught) {
//                    GeoPlatformMessage.errorMessage("Layer-Service",
//                            "Failed to load user folders");
//                    initialized = false;
//                }
//
//                @Override
//                public void onSuccess(List<FolderTreeNode> result) {
//                    root.addElements(result);
//                    store.add(root, true);
//                    initialized = true;
//                }
//            });


//            this.root.modelConverter(GeoPlatformUtils.getInstance().
//                    getGlobalConfiguration().getFolderStore().
//                    getFolders());
//            store.add(root, true);
//            initialized = true;
        }
    }

    /**
     * Set Tree Properties
     */
    @Override
    public void setTreePanelProperties() {
        this.setTreePresenter();
        this.enableDDSupport();
        this.enableCheckChange();
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
            public void selectionChanged(
                    SelectionChangedEvent<GPBeanTreeModel> se) {
                if (se.getSelectedItem() != null) {
                    actionMediator.elementChanged(se.getSelectedItem());
                } else {
                    actionMediator.disableAllActions();
                }

            }
        });

        this.tree.addListener(Events.OnMouseOver, new Listener<BaseEvent>() {

            @Override
            public void handleEvent(BaseEvent be) {
                System.out.println("Entro in listener per selezione");                
                if (be.getSource() instanceof GPBeanTreeModel) {
                    selectedElement = (GPBeanTreeModel) be.getSource();
                    System.out.println("impostata selezione su: " + selectedElement.getLabel());
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
        this.tree.addListener(Events.CheckChange,
                new Listener<TreePanelEvent<GPBeanTreeModel>>() {

                    @Override
                    public void handleEvent(TreePanelEvent<GPBeanTreeModel> be) {
                        //System.out.println("Events.CheckChange from: " + be.getItem().getLabel());
                        be.getItem().accept(visitorDisplay);
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
                ((TreePanelDragSource) e.getSource()).fireEvent(
                        LayerEvents.GP_DRAG_START, new TreeStoreEvent<GPBeanTreeModel>(
                        tree.getStore()));
            }
        });

        GPDNDListener gpDNDListener = new GPDNDListener(this.visitorDisplay);
        dragSource.addListener(LayerEvents.GP_DRAG_START, gpDNDListener);
        dragSource.addListener(LayerEvents.GP_DRAG_LOST, gpDNDListener);

        //Listener for launch Drag Lost Events
        Listener listenerDragLost = new Listener() {

            @Override
            public void handleEvent(BaseEvent be) {
                ((TreePanelDragSource) be.getSource()).fireEvent(
                        LayerEvents.GP_DRAG_LOST, new TreeStoreEvent<GPBeanTreeModel>(
                        tree.getStore()));
                //System.out.println("DragSource: Ho intercettato il drag cancelled");
            }
        };
        //Intercepting Drag Lost Events
        dragSource.addListener(Events.DragCancel, listenerDragLost);
        dragSource.addListener(Events.DragEnd, listenerDragLost);
        dragSource.addListener(Events.DragFail, listenerDragLost);

        TreePanelDropTarget dropTarget = new GPTreePanelDropTarget(super.tree);
        dropTarget.setAllowSelfAsSource(true);
        dropTarget.setAllowDropOnLeaf(false);
        dropTarget.setFeedback(Feedback.BOTH);

        dropTarget.addListener(LayerEvents.GP_DROP, gpDNDListener);

        super.store.addListener(Store.Add, gpDNDListener);
    }

    private void addMenuAddElement() {
        Menu contextMenu = new Menu();
        MenuItem insert = new MenuItem();
        insert.setText("Add Folder");
        insert.setIcon(LayerResources.ICONS.addFolder());
        insert.addSelectionListener(new AddLayerAction(tree));
        contextMenu.add(insert);
        this.tree.setContextMenu(contextMenu);
    }

    private void enableAsyncLoader() {
        final LayerRemoteAsync service = LayerRemote.Util.getInstance();
        // data proxy  
        RpcProxy<List<GPBeanTreeModel>> proxy = new RpcProxy<List<GPBeanTreeModel>>() {

            @Override
            protected void load(Object loadConfig, AsyncCallback<List<GPBeanTreeModel>> callback) {
                if(loadConfig != null){
                    System.out.println("Vediamooo: " + loadConfig.toString());
                }
                if (initialized == false) {
                    layerService.loadUserFolders("user_0", callback);
                    System.out.println("User folder inizialized");
                    initialized = true;
//                } else if (selectedElement != null && selectedElement instanceof FolderTreeNode) {
//                    service.loadFolderElements(((FolderTreeNode) selectedElement).getId(), callback);
//                    System.out.println("Eseguo dopo inzialized");
                } else if (loadConfig != null && (loadConfig instanceof FolderTreeNode || loadConfig instanceof GPRootTreeNode)){
                    service.loadFolderElements(((FolderTreeNode) loadConfig).getId(), callback);
                }
            }
        };
        // tree loader  
        this.loader = new BaseTreeLoader<GPBeanTreeModel>(proxy) {

            @Override
            public boolean hasChildren(GPBeanTreeModel element) {
                System.out.println("Prova: " + element.getLabel());
                return element instanceof FolderTreeNode || element instanceof GPRootTreeNode;
                //return true;
            }
        };

        // trees store  
        super.store = new TreeStore<GPBeanTreeModel>(loader);
        super.tree = new TreePanel<GPBeanTreeModel>(super.store);
    }
}
