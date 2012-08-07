/*
 *  geo-platform
 *  Rich webgis framework
 *  http://geo-platform.org
 * ====================================================================
 *
 * Copyright (C) 2008-2012 geoSDI Group (CNR IMAA - Potenza - ITALY).
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
package org.geosdi.geoplatform.gui.client.widget.contextmenu;

import com.extjs.gxt.ui.client.data.ModelData;
import com.extjs.gxt.ui.client.event.BaseEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.form.ComboBox;
import com.extjs.gxt.ui.client.widget.menu.Menu;
import com.extjs.gxt.ui.client.widget.menu.MenuItem;
import org.geosdi.geoplatform.gui.client.BasicWidgetResources;
import org.geosdi.geoplatform.gui.client.LayerResources;
import org.geosdi.geoplatform.gui.client.action.menu.*;
import org.geosdi.geoplatform.gui.client.model.FolderTreeNode;
import org.geosdi.geoplatform.gui.client.model.GPRootTreeNode;
import org.geosdi.geoplatform.gui.client.model.LayerRefreshTimeValue;
import org.geosdi.geoplatform.gui.client.widget.tree.GPTreePanel;
import org.geosdi.geoplatform.gui.model.tree.GPBeanTreeModel;
import org.geosdi.geoplatform.gui.model.tree.GPLayerTreeModel;

/**
 * @author Nazzareno Sileno - CNR IMAA geoSDI Group
 * @email nazzareno.sileno@geosdi.org
 */
public class GPDynamicTreeContextMenu extends Menu {

    private GPTreePanel<GPBeanTreeModel> tree;
    private Menu rootContextMenu = new Menu();
    private Menu folderContextMenu = new Menu();
    private Menu layerContextMenu = new Menu();
    private ComboBox refreshTimeComboBox;

    public GPDynamicTreeContextMenu(GPTreePanel tree) {
        this.tree = tree;
        this.buildMenu();
    }

    private void buildMenu() {
        AddFolderMenuAction addFolderAction = new AddFolderMenuAction(tree);
        MenuItem addFolder = new MenuItem();
        addFolder.setText(addFolderAction.getTitle());
        addFolder.setIcon(addFolderAction.getImage());
        addFolder.addSelectionListener(addFolderAction);
        rootContextMenu.add(addFolder);

        // add zoom to max extent
        MenuItem zoomToMaxExtend = new MenuItem();
        zoomToMaxExtend.setText("Zoom to layer extend");
        zoomToMaxExtend.setIcon(LayerResources.ICONS.zoomToMaxExtend());
        zoomToMaxExtend.addSelectionListener(new ZoomToLayerExtentAction(tree));
        layerContextMenu.add(zoomToMaxExtend);

        MenuItem exportMenuItem = new MenuItem();
        exportMenuItem.setText("Export");
        exportMenuItem.setSubMenu(new GPExportMenu(tree));
        layerContextMenu.add(exportMenuItem);

        MenuItem cqlFilterMenuItem = new MenuItem();
        cqlFilterMenuItem.setText("CQL Filter");
        cqlFilterMenuItem.setSubMenu(new GPCQLFilterMenu(tree));
        layerContextMenu.add(cqlFilterMenuItem);

        MenuItem layerProperties = new MenuItem();
        layerProperties.setText("Layer Properties");
        layerProperties.setIcon(LayerResources.ICONS.layerProperties());
        layerProperties.addSelectionListener(new ShowLayerPropertiesAction(tree));

        MenuItem pasteMenuItem = new MenuItem("Paste in Folder");
        pasteMenuItem.setIcon(LayerResources.ICONS.paste());
        pasteMenuItem.setEnabled(false);
        PasteLayerAction pasteAction = new PasteLayerAction(tree);
        pasteMenuItem.addSelectionListener(pasteAction);
        folderContextMenu.add(pasteMenuItem);

        MenuItem folderRename = new MenuItem();
        folderRename.setText("Rename Folder");
        folderRename.setIcon(LayerResources.ICONS.editFolder());
        folderRename.addSelectionListener(new ShowFolderRenameAction(tree));
        folderContextMenu.add(folderRename);

        MenuItem copyMenuItem = new MenuItem("Copy Layer");
        copyMenuItem.setIcon(LayerResources.ICONS.copy());
        copyMenuItem.addSelectionListener(new CopyLayerAction(tree, pasteAction, pasteMenuItem));

        layerContextMenu.add(copyMenuItem);

        MenuItem createViewportLayerMenu = new MenuItem();
        createViewportLayerMenu.setText("Create Viewport");
        createViewportLayerMenu.setIcon(BasicWidgetResources.ICONS.viewport());
        createViewportLayerMenu.addSelectionListener(new CreateLayerViewportAction(tree));
        layerContextMenu.add(createViewportLayerMenu);

        MenuItem createViewportFolderMenu = new MenuItem();
        createViewportFolderMenu.setText("Create Viewport");
        createViewportFolderMenu.setIcon(BasicWidgetResources.ICONS.viewport());
        createViewportFolderMenu.addSelectionListener(new CreateFolderViewportAction(tree));
        folderContextMenu.add(createViewportFolderMenu);

        refreshTimeComboBox = new ComboBox() {
            @Override
            protected void onSelect(ModelData model, int index) {
                super.onSelect(model, index);
                refreshTimeComboBox.clearSelections();
                layerContextMenu.hide();
            }
        };
        refreshTimeComboBox.setEmptyText("Refresh Time");
        ListStore<LayerRefreshTimeValue> store = new ListStore<LayerRefreshTimeValue>();
        store.add(LayerRefreshTimeValue.getLayerRefreshTimeList());
        refreshTimeComboBox.setStore(store);
        refreshTimeComboBox.setEditable(Boolean.FALSE);
        refreshTimeComboBox.setForceSelection(Boolean.TRUE);
        refreshTimeComboBox.setTypeAhead(Boolean.FALSE);
        refreshTimeComboBox.setUseQueryCache(Boolean.FALSE);
        refreshTimeComboBox.setDisplayField(LayerRefreshTimeValue.REFRESH_TIME_KEY);
//        refreshMenuItem.setIcon(LayerResources.ICONS.layerRefresh());
        refreshTimeComboBox.addSelectionChangedListener(new RefreshLayerAction(tree));
        layerContextMenu.add(refreshTimeComboBox);

        layerContextMenu.add(layerProperties);

        this.tree.setContextMenu(this.layerContextMenu);

        this.tree.addListener(Events.OnContextMenu, new Listener() {
            @Override
            public void handleEvent(BaseEvent be) {
                GPBeanTreeModel selectedItem = tree.getSelectionModel().getSelectedItem();
                if (selectedItem instanceof FolderTreeNode) {
                    tree.setContextMenu(folderContextMenu);
                } else if (selectedItem instanceof GPLayerTreeModel) {
                    tree.setContextMenu(layerContextMenu);
                } else if (selectedItem instanceof GPRootTreeNode) {
                    tree.setContextMenu(rootContextMenu);
                }
            }
        });
    }
}
