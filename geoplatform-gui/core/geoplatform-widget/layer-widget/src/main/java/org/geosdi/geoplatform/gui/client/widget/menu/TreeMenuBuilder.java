/*
 *  geo-platform
 *  Rich webgis framework
 *  http://geo-platform.org
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
package org.geosdi.geoplatform.gui.client.widget.menu;

import com.extjs.gxt.ui.client.widget.menu.Menu;
import com.extjs.gxt.ui.client.widget.menu.MenuItem;
import org.geosdi.geoplatform.gui.impl.tree.menu.GeoPlatformMenuTree;
import org.geosdi.geoplatform.gui.model.GPLayerBean;
import org.geosdi.geoplatform.gui.model.tree.AbstractFolderTreeNode;
import org.geosdi.geoplatform.gui.model.tree.AbstractRootTreeNode;

/**
 *
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email  giuseppe.lascaleia@geosdi.org
 */
public class TreeMenuBuilder implements GeoPlatformMenuTree {

    private Menu menu;

    public TreeMenuBuilder() {
        this.menu = new Menu();
    }

    @Override
    public void buildMenu() {
        this.buildRootMenu();
        this.buildFolderMenu();
        this.buildLeafMenu();
    }

    /*
     *Build Menu for Root Composite Element
     * 
     */
    private void buildRootMenu() {
        MenuItem addRootFolder = new MenuItem("Add Folder");
        addRootFolder.setItemId("folderRoot");

        this.menu.add(addRootFolder);
    }

    /*
     * Build Menu for Folder Composite Element
     *
     */
    private void buildFolderMenu() {
        MenuItem addFolder = new MenuItem("Add Folder");
        addFolder.setItemId("folder");

        this.menu.add(addFolder);

        MenuItem deleteFolder = new MenuItem("Delete Folder");
        deleteFolder.setItemId("deleteFolder");

        this.menu.add(deleteFolder);

        MenuItem addLayer = new MenuItem("Add Layer");
        addLayer.setItemId("addLayer");

        this.menu.add(addLayer);
    }

    /*
     * Build Menu for Leaf Composite Element
     *
     */
    private void buildLeafMenu() {
        MenuItem deleteLayer = new MenuItem("Delete Layer");
        deleteLayer.setItemId("deleteLayer");

        this.menu.add(deleteLayer);
    }

    @Override
    public Menu getMenu() {
        return this.menu;
    }

    @Override
    public void show(AbstractRootTreeNode root) {
        menu.getItemByItemId("folderRoot").setEnabled(true);
        menu.getItemByItemId("folder").setEnabled(false);
        menu.getItemByItemId("deleteFolder").setEnabled(false);
        menu.getItemByItemId("addLayer").setEnabled(false);
        menu.getItemByItemId("deleteLayer").setEnabled(false);
    }

    @Override
    public void show(AbstractFolderTreeNode folder) {
        menu.getItemByItemId("folderRoot").setEnabled(false);
        menu.getItemByItemId("folder").setEnabled(true);
        menu.getItemByItemId("deleteFolder").setEnabled(true);
        menu.getItemByItemId("addLayer").setEnabled(true);
        menu.getItemByItemId("deleteLayer").setEnabled(false);
    }

    @Override
    public void showLeaf(GPLayerBean leaf) {
         menu.getItemByItemId("folderRoot").setEnabled(false);
        menu.getItemByItemId("folder").setEnabled(false);
        menu.getItemByItemId("deleteFolder").setEnabled(false);
        menu.getItemByItemId("addLayer").setEnabled(false);
        menu.getItemByItemId("deleteLayer").setEnabled(true);
    }
}
