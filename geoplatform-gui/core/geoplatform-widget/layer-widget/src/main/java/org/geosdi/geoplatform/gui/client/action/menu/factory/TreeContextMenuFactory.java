/*
 *  geo-platform
 *  Rich webgis framework
 *  http://geo-platform.org
 * ====================================================================
 *
 * Copyright (C) 2008-2013 geoSDI Group (CNR IMAA - Potenza - ITALY).
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
package org.geosdi.geoplatform.gui.client.action.menu.factory;

import com.extjs.gxt.ui.client.data.ModelData;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.form.ComboBox;
import com.extjs.gxt.ui.client.widget.menu.Menu;
import com.extjs.gxt.ui.client.widget.menu.MenuItem;
import javax.inject.Inject;
import org.geosdi.geoplatform.gui.client.BasicWidgetResources;
import org.geosdi.geoplatform.gui.client.LayerResources;
import org.geosdi.geoplatform.gui.client.action.menu.AddFolderMenuAction;
import org.geosdi.geoplatform.gui.client.action.menu.CopyLayerAction;
import org.geosdi.geoplatform.gui.client.action.menu.CreateFolderViewportAction;
import org.geosdi.geoplatform.gui.client.action.menu.CreateLayerViewportAction;
import org.geosdi.geoplatform.gui.client.action.menu.DeleteElementsMenuAction;
import org.geosdi.geoplatform.gui.client.action.menu.PasteLayerAction;
import org.geosdi.geoplatform.gui.client.action.menu.RefreshLayerAction;
import org.geosdi.geoplatform.gui.client.action.menu.ShareProjectMenuAction;
import org.geosdi.geoplatform.gui.client.action.menu.ShowFolderRenameAction;
import org.geosdi.geoplatform.gui.client.action.menu.ShowLayerPropertiesAction;
import org.geosdi.geoplatform.gui.client.action.menu.ZoomToLayerExtentAction;
import org.geosdi.geoplatform.gui.client.model.LayerRefreshTimeValue;
import org.geosdi.geoplatform.gui.client.widget.contextmenu.GPCQLFilterMenu;
import org.geosdi.geoplatform.gui.client.widget.contextmenu.GPExportMenu;
import org.geosdi.geoplatform.gui.client.widget.contextmenu.GPTimeFilterMenu;
import org.geosdi.geoplatform.gui.client.widget.tree.GPTreePanel;
import org.geosdi.geoplatform.gui.model.tree.GPBeanTreeModel;

/**
 * @TODO : Think of a way to make more efficient generation of the menu tree
 * from the configuration file
 *
 * @author Nazzareno Sileno - CNR IMAA geoSDI Group
 * @email nazzareno.sileno@geosdi.org
 */
public class TreeContextMenuFactory {

    @Inject
    static ShareProjectMenuAction shareProjectMenuAction;
    //
    private static Menu rootContextMenu;
    private static Menu folderContextMenu;
    private static Menu layerContextMenu;
    private static Menu multipleSelectionContextMenu;
    private static PasteLayerAction pasteAction;
    private static MenuItem pasteMenuItem;
    private static MenuItem copyMultiLayers;
    private static GPTreePanel<GPBeanTreeModel> treePanel;
    private static ComboBox refreshTimeComboBox;

    public static void setTreePanel(GPTreePanel<GPBeanTreeModel> treePanel) {
        TreeContextMenuFactory.treePanel = treePanel;
        pasteAction = new PasteLayerAction(treePanel);
        pasteMenuItem = new MenuItem("Paste in Folder");
        pasteMenuItem.setIcon(LayerResources.ICONS.paste());
        pasteMenuItem.setEnabled(Boolean.FALSE);
        pasteMenuItem.addSelectionListener(pasteAction);
    }

    public static Menu getMultiSelectionMenu(boolean isOnlyLayers) {
        if (multipleSelectionContextMenu == null) {
            multipleSelectionContextMenu = new Menu();
            DeleteElementsMenuAction deleteElementsMenuAction = new DeleteElementsMenuAction(
                    treePanel);
            MenuItem deleteElements = new MenuItem();
            deleteElements.setText(deleteElementsMenuAction.getTitle());
            deleteElements.setIcon(deleteElementsMenuAction.getImage());
            deleteElements.addSelectionListener(deleteElementsMenuAction);
            multipleSelectionContextMenu.add(deleteElements);
            copyMultiLayers = new MenuItem("Copy Layers");
            copyMultiLayers.setIcon(LayerResources.ICONS.copy());
            copyMultiLayers.addSelectionListener(
                    new CopyLayerAction(treePanel,
                    pasteAction, pasteMenuItem));
        }
        if (isOnlyLayers) {
            multipleSelectionContextMenu.add(copyMultiLayers);
        } else if (multipleSelectionContextMenu.getItems().contains(
                copyMultiLayers)) {
            multipleSelectionContextMenu.remove(copyMultiLayers);
        }
        return multipleSelectionContextMenu;
    }

    public static Menu getRootContextMenu() {
        if (rootContextMenu == null) {
            rootContextMenu = new Menu();
            AddFolderMenuAction addFolderAction = new AddFolderMenuAction(
                    treePanel);
            MenuItem addFolder = new MenuItem();
            addFolder.setText(addFolderAction.getTitle());
            addFolder.setIcon(addFolderAction.getImage());
            addFolder.addSelectionListener(addFolderAction);
            rootContextMenu.add(addFolder);
            MenuItem shareProject = new MenuItem();
            shareProject.setText(shareProjectMenuAction.getTitle());
            shareProject.setIcon(shareProjectMenuAction.getImage());
            shareProject.addSelectionListener(shareProjectMenuAction);
            shareProject.setEnabled(shareProjectMenuAction.isEnabled());
            rootContextMenu.add(shareProject);
        }
        return rootContextMenu;
    }

    public static Menu getLayerContextMenu() {
        if (layerContextMenu == null) {
            layerContextMenu = new Menu();
            // add zoom to max extent
            MenuItem zoomToMaxExtend = new MenuItem();
            zoomToMaxExtend.setText("Zoom to layer extend");
            zoomToMaxExtend.setIcon(LayerResources.ICONS.zoomToMaxExtend());
            zoomToMaxExtend.addSelectionListener(new ZoomToLayerExtentAction(
                    treePanel));
            layerContextMenu.add(zoomToMaxExtend);

            MenuItem exportMenuItem = new MenuItem();
            exportMenuItem.setText("Export");
            exportMenuItem.setSubMenu(new GPExportMenu(treePanel));
            layerContextMenu.add(exportMenuItem);

            MenuItem cqlFilterMenuItem = new MenuItem();
            cqlFilterMenuItem.setText("CQL Filter");
            cqlFilterMenuItem.setSubMenu(new GPCQLFilterMenu(treePanel));
            layerContextMenu.add(cqlFilterMenuItem);

            MenuItem timeFilterMenuItem = new MenuItem();
            timeFilterMenuItem.setText("TIME Filter");
            timeFilterMenuItem.setSubMenu(new GPTimeFilterMenu(treePanel));
            layerContextMenu.add(timeFilterMenuItem);

            MenuItem layerProperties = new MenuItem();
            layerProperties.setText("Layer Properties");
            layerProperties.setIcon(LayerResources.ICONS.layerProperties());
            layerProperties.addSelectionListener(new ShowLayerPropertiesAction(
                    treePanel));

            MenuItem copyMenuItem = new MenuItem("Copy Layer");
            copyMenuItem.setIcon(LayerResources.ICONS.copy());
            copyMenuItem.addSelectionListener(new CopyLayerAction(treePanel,
                    pasteAction,
                    pasteMenuItem));

            layerContextMenu.add(copyMenuItem);

            MenuItem createViewportLayerMenu = new MenuItem();
            createViewportLayerMenu.setText("Create Viewport");
            createViewportLayerMenu.setIcon(
                    BasicWidgetResources.ICONS.viewport());
            createViewportLayerMenu.addSelectionListener(new CreateLayerViewportAction(
                    treePanel));
            layerContextMenu.add(createViewportLayerMenu);

            // TODO ADD EditWFS dynamically
//            EditWFSAction editFeatureAction = new EditWFSAction(treePanel);
//            MenuItem editFeature = new MenuItem();
//            editFeature.setText(editFeatureAction.getTitle());
//            editFeature.setIcon(editFeatureAction.getImage());
//            editFeature.addSelectionListener(editFeatureAction);
//            layerContextMenu.add(editFeature);

            refreshTimeComboBox = new ComboBox() {
                @Override
                protected void onSelect(ModelData model, int index) {
                    super.onSelect(model, index);
                    refreshTimeComboBox.clearSelections();
                    refreshTimeComboBox.getParent().setVisible(false);
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
            refreshTimeComboBox.setDisplayField(
                    LayerRefreshTimeValue.REFRESH_TIME_KEY);
//        refreshMenuItem.setIcon(LayerResources.ICONS.layerRefresh());
            refreshTimeComboBox.addSelectionChangedListener(new RefreshLayerAction(
                    treePanel));
            layerContextMenu.add(refreshTimeComboBox);

            layerContextMenu.add(layerProperties);
        }
        return layerContextMenu;
    }

    public static Menu getFolderContextMenu() {
        if (folderContextMenu == null) {
            folderContextMenu = new Menu();
            folderContextMenu.add(pasteMenuItem);

            MenuItem folderRename = new MenuItem();
            folderRename.setText("Rename Folder");
            folderRename.setIcon(LayerResources.ICONS.editFolder());
            folderRename.addSelectionListener(new ShowFolderRenameAction(
                    treePanel));
            folderContextMenu.add(folderRename);

            MenuItem createViewportFolderMenu = new MenuItem();
            createViewportFolderMenu.setText("Create Viewport");
            createViewportFolderMenu.setIcon(
                    BasicWidgetResources.ICONS.viewport());
            createViewportFolderMenu.addSelectionListener(new CreateFolderViewportAction(
                    treePanel));
            folderContextMenu.add(createViewportFolderMenu);
        }
        return folderContextMenu;
    }
}
