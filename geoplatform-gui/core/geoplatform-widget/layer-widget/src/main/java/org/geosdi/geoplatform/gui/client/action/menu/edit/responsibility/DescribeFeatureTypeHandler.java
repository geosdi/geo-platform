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
package org.geosdi.geoplatform.gui.client.action.menu.edit.responsibility;

import com.google.gwt.user.client.rpc.AsyncCallback;
import org.geosdi.geoplatform.gui.client.model.VectorTreeNode;
import org.geosdi.geoplatform.gui.client.service.LayerRemote;
import org.geosdi.geoplatform.gui.client.widget.SearchStatus;
import org.geosdi.geoplatform.gui.client.widget.wfs.FeatureWidget;
import org.geosdi.geoplatform.gui.configuration.message.GeoPlatformMessage;
import org.geosdi.geoplatform.gui.impl.view.LayoutManager;
import org.geosdi.geoplatform.gui.model.tree.GPLayerTreeModel;
import org.geosdi.geoplatform.gui.responce.LayerSchemaDTO;
import org.geosdi.geoplatform.gui.shared.GPLayerType;

/**
 * <p>This Handler will make a Describe FeatureType Request to determine whether
 * the Layer is a Vector or not</p>
 *
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class DescribeFeatureTypeHandler extends LayerTypeHandler {

    private FeatureWidget featureWidget;

    public DescribeFeatureTypeHandler(FeatureWidget theFeatureWidget) {
        this.featureWidget = theFeatureWidget;
    }

    @Override
    public void layerType(GPLayerTreeModel layer) {
        if (layer.getLayerType() == GPLayerType.WMS) {
            System.out.println(
                    "DescribeFeatureTypeHandler @@@@@@@@@@@@@@@@@@@@@@@@");
            executeDescribeFeatureTypeRequest(layer);
        } else {
            super.forwardLayerType(layer);
        }
    }

    private void executeDescribeFeatureTypeRequest(final GPLayerTreeModel layer) {
        LayerRemote.Util.getInstance().describeFeatureType(
                layer.getDataSource(), layer.getName(),
                new AsyncCallback<LayerSchemaDTO>() {
                    @Override
                    public void onFailure(Throwable caught) {
                        String errorMessage = "Error on WFS DescribeFeatureType request";

                        GeoPlatformMessage.errorMessage(
                                "DescribeFetureType Service Error",
                                errorMessage + " - " + caught.getMessage());

                        LayoutManager.getInstance().getStatusMap().setStatus(
                                errorMessage + " for " + layer.getName() + " layer.",
                                SearchStatus.EnumSearchStatus.STATUS_SEARCH_ERROR.toString());
                    }

                    @Override
                    public void onSuccess(LayerSchemaDTO result) {
                        if (result == null) {
                            String alertMessage = "The Layer " + layer.getName()
                                    + " isn't a Vector.";
                            GeoPlatformMessage.alertMessage(
                                    "DescribeFeatureType Service",
                                                            alertMessage);

                            LayoutManager.getInstance().getStatusMap().setStatus(
                                    alertMessage,
                                    SearchStatus.EnumSearchStatus.STATUS_SEARCH_ERROR.toString());
                            layer.setLayerType(GPLayerType.RASTER);
                        } else {
                            String geometry = result.getGeometry();
                            String geometryType = geometry.substring(geometry.lastIndexOf(".") + 1);
                            layer.setLayerType(GPLayerType.valueOf(geometryType.toUpperCase()));

                            LayoutManager.getInstance().getStatusMap().setStatus(
                                    "The Layer " + layer.getName() + " is a WFS layer of "
                                    + geometryType + " geometry type.",
                                    SearchStatus.EnumSearchStatus.STATUS_SEARCH.toString());

                            if (layer instanceof VectorTreeNode) {
                                ((VectorTreeNode) layer).setFeatureNameSpace(
                                        result.getTargetNamespace());
                                // Open the Window
                                featureWidget.show();
                            } else {
                                featureWidget.show();
                            }

                            //TODO here must be opened the widget to manage Edit Mode
                        }

                    }
                });
    }
}
