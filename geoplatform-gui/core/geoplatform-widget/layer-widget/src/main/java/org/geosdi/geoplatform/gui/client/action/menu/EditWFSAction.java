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
package org.geosdi.geoplatform.gui.client.action.menu;

import com.extjs.gxt.ui.client.event.MenuEvent;
import com.extjs.gxt.ui.client.store.TreeStore;
import com.google.gwt.user.client.rpc.AsyncCallback;
import java.util.List;
import org.geosdi.geoplatform.gui.action.menu.MenuBaseAction;
import org.geosdi.geoplatform.gui.client.LayerResources;
import org.geosdi.geoplatform.gui.client.model.FolderTreeNode;
import org.geosdi.geoplatform.gui.client.model.RasterTreeNode;
import org.geosdi.geoplatform.gui.client.model.VectorTreeNode;
import org.geosdi.geoplatform.gui.client.model.visitor.VisitorAddElement;
import org.geosdi.geoplatform.gui.client.model.visitor.VisitorDeleteElement;
import org.geosdi.geoplatform.gui.client.service.LayerRemote;
import org.geosdi.geoplatform.gui.client.widget.SearchStatus;
import org.geosdi.geoplatform.gui.client.widget.tree.GPTreePanel;
import org.geosdi.geoplatform.gui.configuration.map.client.layer.GPLayerType;
import org.geosdi.geoplatform.gui.impl.view.LayoutManager;
import org.geosdi.geoplatform.gui.model.tree.GPBeanTreeModel;
import org.geosdi.geoplatform.gui.model.tree.GPLayerTreeModel;
import org.geosdi.geoplatform.gui.responce.AttributeDTO;
import org.geosdi.geoplatform.gui.responce.LayerSchemaDTO;

/**
 *
 * @author Vincenzo Monteverde <vincenzo.monteverde@geosdi.org>
 */
public class EditWFSAction extends MenuBaseAction {

    private GPTreePanel<GPBeanTreeModel> treePanel;
    private VisitorDeleteElement deleteVisitor = new VisitorDeleteElement();
    private VisitorAddElement visitorAdd = new VisitorAddElement();

    public EditWFSAction(GPTreePanel<GPBeanTreeModel> treePanel) {
        super("Edit WFS Mode", LayerResources.ICONS.vector());
        this.treePanel = treePanel;
    }

    @Override
    public void componentSelected(MenuEvent e) {
        final GPLayerTreeModel item = (GPLayerTreeModel) this.treePanel.getSelectionModel().getSelectedItem();

        LayoutManager.getInstance().getStatusMap().setStatus(
                "Checking if " + item.getName() + " is a feauture.",
                SearchStatus.EnumSearchStatus.STATUS_SEARCH.toString());

        this.executeDescribeFeatureTypeRequest(item);
    }

    private void executeDescribeFeatureTypeRequest(final GPLayerTreeModel layer) {
        LayerRemote.Util.getInstance().describeFeatureType(
                layer.getDataSource(), layer.getName(),
                new AsyncCallback<LayerSchemaDTO>() {
                    @Override
                    public void onFailure(Throwable caught) {
                        String errorMessage = "Error on WFS DescribeFeatureType request";

                        LayoutManager.getInstance().getStatusMap().setStatus(
                                errorMessage + " for " + layer.getName() + " layer.",
                                SearchStatus.EnumSearchStatus.STATUS_SEARCH_ERROR.toString());
                    }

                    @Override
                    public void onSuccess(LayerSchemaDTO result) {
                        if (result == null) {
                            String alertMessage = "The Layer " + layer.getName()
                                    + " isn't a feauture";
                            LayoutManager.getInstance().getStatusMap().setStatus(
                                    alertMessage + ".",
                                    SearchStatus.EnumSearchStatus.STATUS_SEARCH_ERROR.toString());
                            return;
                        }

                        if (layer instanceof RasterTreeNode) {
                            RasterTreeNode raster = (RasterTreeNode) layer;
                            FolderTreeNode parent = (FolderTreeNode) raster.getParent();
                            int layerIndex = parent.indexOf(raster);
                            VectorTreeNode vector = createVectorLayer(result, raster);
                            vector.setParent(parent);
                            System.out.println("\n*** " + vector);

                            // TODO Re-test swap raster to vector: if fail delete GPTreeStore
//                        treePanel.swapModelInstance(item, vector);
//                        treePanel.refreshIcon(vector);
                            
                            TreeStore<GPBeanTreeModel> store = treePanel.getStore();

                            deleteVisitor.deleteElement(raster, parent, layerIndex);
                            store.remove(raster);

                            store.insert(parent, vector, layerIndex, false);
                            visitorAdd.insertElement(vector, parent, layerIndex);

                            treePanel.setExpanded(parent, true);

                            LayoutManager.getInstance().getStatusMap().setStatus(
                                    "The Layer " + vector.getName() + " is a WFS layer.",
                                    SearchStatus.EnumSearchStatus.STATUS_SEARCH.toString());
                        }else{
                            // TODO Manage swap Vector to Raster
                            System.out.println("\n######### TODO Manage swap Vector to Raster #########");
                        }
                    }
                });
    }

    private VectorTreeNode createVectorLayer(LayerSchemaDTO schema, RasterTreeNode raster) {
        VectorTreeNode vector = this.convertRasterToVector(raster);

        vector.setFeatureNameSpace(schema.getTargetNamespace());
        List<AttributeDTO> attributeList = schema.getAttributes();
        for (AttributeDTO attribute : attributeList) {
            if ("the_geom".equals(attribute.getName())) {
                String value = attribute.getValue().toUpperCase();
                vector.setLayerType(GPLayerType.valueOf(value));
                break;
            }
        }
        return vector;
    }

    private VectorTreeNode convertRasterToVector(RasterTreeNode raster) {
        // TODO Re-test swap raster to vector: if fail delete constructor
        // with UUID argument and protected setUUID in super class
        VectorTreeNode vector = new VectorTreeNode(raster.getUUID());
        vector.setAbstractText(raster.getAbstractText());
        vector.setAlias(raster.getAlias());
        vector.setBbox(raster.getBbox());
        vector.setChecked(raster.isChecked());
        vector.setCqlFilter(raster.getCqlFilter());
        vector.setCrs(raster.getCrs());
        vector.setDataSource(raster.getDataSource());
        vector.setId(raster.getId());
        vector.setLabel(raster.getLabel());
        vector.setName(raster.getName());
        vector.setTimeFilter(raster.getTimeFilter());
        vector.setTitle(raster.getTitle());
        vector.setzIndex(raster.getzIndex());
        return vector;
    }
}
