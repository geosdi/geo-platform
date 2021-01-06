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
package org.geosdi.geoplatform.gui.client.action.menu.legend;

import com.extjs.gxt.ui.client.event.MenuEvent;
import com.extjs.gxt.ui.client.widget.treepanel.TreePanel;
import com.google.gwt.user.client.ui.AbstractImagePrototype;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.Image;
import org.geosdi.geoplatform.gui.action.menu.MenuBaseAction;
import org.geosdi.geoplatform.gui.client.LayerResources;
import org.geosdi.geoplatform.gui.client.model.RasterTreeNode;
import org.geosdi.geoplatform.gui.model.tree.GPBeanTreeModel;
import org.geosdi.geoplatform.gui.utility.GSAuthKeyManager;

/**
 *
 * @author Francesco Izzi - CNR IMAA geoSDI Group
 * @mail francesco.izzi@geosdi.org
 */
public class GetLegendGraphicsBox extends MenuBaseAction {

    private TreePanel treePanel;
    
     protected static final String GET_LEGEND_REQUEST = "?REQUEST=GetLegendGraphic"
            + "&VERSION=1.0.0&FORMAT=image/png&LAYER=";

    public GetLegendGraphicsBox(TreePanel treePanel) {
        super("GetLegend", AbstractImagePrototype.create(
                LayerResources.ICONS.exportToTIFF()));
        this.treePanel = treePanel;
    }

    @Override
    public void componentSelected(MenuEvent ce) {
        GPBeanTreeModel item = (GPBeanTreeModel) this.treePanel.getSelectionModel().getSelectedItem();
        DialogBox legendDialog = new DialogBox();
        legendDialog.setModal(false);
        if (item instanceof RasterTreeNode) {
            String dataSource = ((RasterTreeNode) item).getDataSource();
            legendDialog.setText(((RasterTreeNode) item).getName());
            legendDialog.setAnimationEnabled(true);
            legendDialog.setAutoHideEnabled(true);
            Image image;
            

            if (dataSource.contains("gwc/service/wms")) {
                dataSource = dataSource.replaceAll("gwc/service/wms", "wms");
            } else if (!(dataSource.startsWith("http://ows"))
                    && (dataSource.contains("/ows"))) {
                dataSource = dataSource.replaceAll("/ows", "/wms");
            } else {
                dataSource = dataSource.replaceAll("/wfs", "/wms");
            }
            StringBuilder imageURL = new StringBuilder();
            imageURL.append(dataSource).append(GET_LEGEND_REQUEST).append(((RasterTreeNode) item).getName())
                    .append("&scale=5000&service=WMS");
            if (((RasterTreeNode) item).getStyles() != null && ((RasterTreeNode) item).getStyles().size() > 0) {
                imageURL.append("&STYLE=").append(((RasterTreeNode) item).getStyles().get(0).getStyleString());
            }
            String authkeyTuple = GSAuthKeyManager.getAuthKeyTuple();
            if (!authkeyTuple.equals("")) {
                imageURL.append('&').append(authkeyTuple);
            }
            image = new Image(imageURL.toString());
            legendDialog.add(image);
            
            legendDialog.show();
             
            legendDialog.setPopupPosition(ce.getMenu().getPosition(true).x, ce.getMenu().getPosition(true).y);
            
        }

    }

}
