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

import org.geosdi.geoplatform.gui.client.LayerResources;
import org.geosdi.geoplatform.gui.client.action.menu.CopyLayerAction;
import org.geosdi.geoplatform.gui.client.action.menu.ExportoToCSV;
import org.geosdi.geoplatform.gui.client.action.menu.ExportoToGML2;
import org.geosdi.geoplatform.gui.client.action.menu.ExportoToGML3_1;
import org.geosdi.geoplatform.gui.client.action.menu.ExportoToGML3_2;
import org.geosdi.geoplatform.gui.client.action.menu.ExportoToGeoJSON;
import org.geosdi.geoplatform.gui.client.action.menu.ExportoToGeoRSS;
import org.geosdi.geoplatform.gui.client.action.menu.ExportoToKML;
import org.geosdi.geoplatform.gui.client.action.menu.ExportoToPDF;
import org.geosdi.geoplatform.gui.client.action.menu.ExportoToShpZip;
import org.geosdi.geoplatform.gui.client.action.menu.ExportoToTIFF;
import org.geosdi.geoplatform.gui.client.action.menu.PasteLayerAction;
import org.geosdi.geoplatform.gui.client.action.menu.ShowFolderRenameAction;
import org.geosdi.geoplatform.gui.client.action.menu.ShowLayerPropertiesAction;
import org.geosdi.geoplatform.gui.client.action.menu.ZoomToLayerExtentAction;
import org.geosdi.geoplatform.gui.client.model.FolderTreeNode;
import org.geosdi.geoplatform.gui.client.widget.tree.GPTreePanel;
import org.geosdi.geoplatform.gui.model.tree.GPBeanTreeModel;
import org.geosdi.geoplatform.gui.model.tree.GPLayerTreeModel;

import com.extjs.gxt.ui.client.event.BaseEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.widget.menu.Menu;
import com.extjs.gxt.ui.client.widget.menu.MenuItem;

/**
 * @author Nazzareno Sileno - CNR IMAA geoSDI Group
 * @email nazzareno.sileno@geosdi.org
 */
public class GPDynamicTreeContextMenu extends Menu {

    private GPTreePanel<GPBeanTreeModel> tree;
    private Menu folderContextMenu = new Menu();
    private Menu layerContextMenu = new Menu();
    private Menu exportMenu = new Menu();


    public GPDynamicTreeContextMenu(GPTreePanel tree) {
        this.tree = tree;
        this.buildMenu();
    }

    private void buildMenu() {
        // add zoom to max extent
        MenuItem zoomToMaxExtend = new MenuItem();
        zoomToMaxExtend.setText("Zoom to layer extend");
        zoomToMaxExtend.setIcon(LayerResources.ICONS.zoomToMaxExtend());
        zoomToMaxExtend.addSelectionListener(new ZoomToLayerExtentAction(tree));
        layerContextMenu.add(zoomToMaxExtend);
        
        MenuItem export = new MenuItem();
        export.setText("Export");
        
        MenuItem exportToKML = new MenuItem();
        exportToKML.setText("Export To KML");
        exportToKML.setIcon(LayerResources.ICONS.exportToKML());
        exportToKML.addSelectionListener(new ExportoToKML(tree));
        exportMenu.add(exportToKML);

        MenuItem exportToPDF = new MenuItem();
        exportToPDF.setText("Export To PDF");
        exportToPDF.setIcon(LayerResources.ICONS.exportToPDF());
        exportToPDF.addSelectionListener(new ExportoToPDF(tree));
        exportMenu.add(exportToPDF);

        MenuItem exportToTIFF = new MenuItem();
        exportToTIFF.setText("Export To TIFF");
        exportToTIFF.setIcon(LayerResources.ICONS.exportToTIFF());
        exportToTIFF.addSelectionListener(new ExportoToTIFF(tree));
        exportMenu.add(exportToTIFF);


        MenuItem exportToShpZip = new MenuItem();
        exportToShpZip.setText("Export To Shp-Zip");
        exportToShpZip.setIcon(LayerResources.ICONS.exportToShpZip());
        exportToShpZip.addSelectionListener(new ExportoToShpZip(tree));
        exportMenu.add(exportToShpZip);

        MenuItem layerProperties = new MenuItem();
        layerProperties.setText("Layer Properties");
        layerProperties.setIcon(LayerResources.ICONS.layerProperties());
        layerProperties.addSelectionListener(new ShowLayerPropertiesAction(tree));

        MenuItem exportToGML2 = new MenuItem();
        exportToGML2.setText("Export To GML 2");
        exportToGML2.setIcon(LayerResources.ICONS.exportToGML());
        exportToGML2.addSelectionListener(new ExportoToGML2(tree));
        exportMenu.add(exportToGML2);
        
        MenuItem exportToGML3_1 = new MenuItem();
        exportToGML3_1.setText("Export To GML 3.1");
        exportToGML3_1.setIcon(LayerResources.ICONS.exportToGML());
        exportToGML3_1.addSelectionListener(new ExportoToGML3_1(tree));
        exportMenu.add(exportToGML3_1);
        
        MenuItem exportToGML3_2 = new MenuItem();
        exportToGML3_2.setText("Export To GML 3.2");
        exportToGML3_2.setIcon(LayerResources.ICONS.exportToGML());
        exportToGML3_2.addSelectionListener(new ExportoToGML3_2(tree));
        exportMenu.add(exportToGML3_2);
        
        MenuItem exportToCSV = new MenuItem();
        exportToCSV.setText("Export To CSV");
        exportToCSV.setIcon(LayerResources.ICONS.exportToCSV());
        exportToCSV.addSelectionListener(new ExportoToCSV(tree));
        exportMenu.add(exportToCSV);
        
        MenuItem exportToGeoJSON = new MenuItem();
        exportToGeoJSON.setText("Export To GeoJSON");
        exportToGeoJSON.setIcon(LayerResources.ICONS.exportToJSON());
        exportToGeoJSON.addSelectionListener(new ExportoToGeoJSON(tree));
        exportMenu.add(exportToGeoJSON);
        
        MenuItem exportToRSS = new MenuItem();
        exportToRSS.setText("Export To RSS");
        exportToRSS.setIcon(LayerResources.ICONS.exportToRSS());
        exportToRSS.addSelectionListener(new ExportoToGeoRSS(tree));
        exportMenu.add(exportToRSS);

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
        layerContextMenu.add(layerProperties);
        
        
        export.setSubMenu(exportMenu);
        layerContextMenu.add(export);

        this.tree.setContextMenu(this.layerContextMenu);

        this.tree.addListener(Events.OnContextMenu, new Listener() {

            private Menu emptyMenu = new Menu();

            @Override
            public void handleEvent(BaseEvent be) {
                GPBeanTreeModel selectedItem = tree.getSelectionModel().getSelectedItem();
                if (selectedItem instanceof FolderTreeNode) {
                    tree.setContextMenu(folderContextMenu);
                } else if (selectedItem instanceof GPLayerTreeModel) {
                    tree.setContextMenu(layerContextMenu);
                } else {
                    tree.setContextMenu(emptyMenu);
                }
            }
        });
    }
}
