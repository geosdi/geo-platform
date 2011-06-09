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
package org.geosdi.geoplatform.gui.client.widget.store;

import com.google.gwt.user.client.rpc.AsyncCallback;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.geosdi.geoplatform.gui.action.ISave;
import org.geosdi.geoplatform.gui.client.model.GPRasterLayerGrid;
import org.geosdi.geoplatform.gui.client.model.RasterTreeNode;
import org.geosdi.geoplatform.gui.client.model.memento.AbstractMementoLayer;
import org.geosdi.geoplatform.gui.client.model.memento.GPLayerSaveCache;
import org.geosdi.geoplatform.gui.client.model.memento.MementoBuilder;
import org.geosdi.geoplatform.gui.client.model.memento.MementoSaveAddedLayers;
import org.geosdi.geoplatform.gui.client.model.memento.puregwt.event.PeekCacheEvent;
import org.geosdi.geoplatform.gui.client.model.visitor.VisitorAddElement;
import org.geosdi.geoplatform.gui.client.model.visitor.VisitorDisplayHide;
import org.geosdi.geoplatform.gui.client.service.LayerRemote;
import org.geosdi.geoplatform.gui.client.widget.SearchStatus.EnumSearchStatus;
import org.geosdi.geoplatform.gui.client.widget.tree.GPTreePanel;
import org.geosdi.geoplatform.gui.client.widget.tree.store.GenericTreeStoreWidget;
import org.geosdi.geoplatform.gui.configuration.message.GeoPlatformMessage;
import org.geosdi.geoplatform.gui.impl.view.LayoutManager;
import org.geosdi.geoplatform.gui.model.GPLayerBean;
import org.geosdi.geoplatform.gui.model.tree.GPBeanTreeModel;
import org.geosdi.geoplatform.gui.puregwt.grid.event.DeselectGridElementEvent;
import org.geosdi.geoplatform.gui.puregwt.layers.LayerHandlerManager;
import org.geosdi.geoplatform.gui.puregwt.progressbar.layers.event.DisplayLayersProgressBarEvent;
import org.geosdi.geoplatform.gui.puregwt.progressbar.layers.event.LayersProgressTextEvent;

/**
 *
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email  giuseppe.lascaleia@geosdi.org
 */
public class GPTreeStoreWidget extends GenericTreeStoreWidget implements ISave<MementoSaveAddedLayers> {

    private LayersProgressTextEvent layersTextEvent = new LayersProgressTextEvent();
    private DeselectGridElementEvent deselectEvent = new DeselectGridElementEvent();
    private VisitorAddElement visitorAdd = new VisitorAddElement();
    private VisitorDisplayHide visitorDispayLayer;
    private PeekCacheEvent peekCacheEvent = new PeekCacheEvent();

    /*
     * @param theTree 
     */
    public GPTreeStoreWidget(GPTreePanel<GPBeanTreeModel> theTree) {
        super(theTree);
        this.visitorDispayLayer = new VisitorDisplayHide(theTree);
    }

    @Override
    public void addRasterLayers(List<? extends GPLayerBean> layers) {
        this.changeProgressBarMessage(
                "Loading " + layers.size() + " Raster Layers into the Store");
        GPBeanTreeModel parentDestination = this.tree.getSelectionModel().getSelectedItem();
        List<GPBeanTreeModel> layerList = new ArrayList<GPBeanTreeModel>();
        for (Iterator it = layers.iterator(); it.hasNext();) {
            layerList.add(this.convertGPRasterBeanModelToRasterTreeNode(
                    (GPRasterLayerGrid) it.next()));
        }
        this.tree.getStore().insert(parentDestination, layerList, 0, true);
        this.visitorAdd.insertLayerElements(layerList, parentDestination);
        this.visitorDispayLayer.realignViewState(parentDestination);
        MementoSaveAddedLayers mementoSaveLayer = new MementoSaveAddedLayers(this);
        mementoSaveLayer.setAddedLayers(MementoBuilder.generateMementoLayerList(layerList));
        mementoSaveLayer.setDescendantMap(this.visitorAdd.getFolderDescendantMap());
        GPLayerSaveCache.getInstance().add(mementoSaveLayer);


        LayerHandlerManager.fireEvent(deselectEvent);
    }

    private RasterTreeNode convertGPRasterBeanModelToRasterTreeNode(GPRasterLayerGrid rasterBean) {
        RasterTreeNode raster = new RasterTreeNode();
        raster.setAbstractText(rasterBean.getAbstractText());
        raster.setBbox(rasterBean.getBbox());
        raster.setChecked(true);
        raster.setCrs(rasterBean.getCrs());
        raster.setDataSource(rasterBean.getDataSource());
        System.out.println("Data Source: " + raster.getDataSource());
        raster.setLabel(rasterBean.getLabel());
        raster.setLayerType(rasterBean.getLayerType());
        raster.setName(rasterBean.getName());
        raster.setStyles(rasterBean.getStyles());
        raster.setzIndex(rasterBean.getzIndex());
        return raster;
    }

    @Override
    public void addVectorLayers(List<? extends GPLayerBean> layers) {
        this.changeProgressBarMessage("Load Vector Layers in the Store");
        System.out.println("ADD VECTORS *********************** " + layers);
    }

    private void changeProgressBarMessage(String message) {
        layersTextEvent.setMessage(message);
        LayerHandlerManager.fireEvent(layersTextEvent);
    }

    @Override
    public void executeSave(final MementoSaveAddedLayers memento) {
        //Warning: The following conversion is absolutely necessary!
        memento.convertMementoToWs();

        LayerRemote.Util.getInstance().saveAddedLayersAndTreeModifications(
                memento,
                new AsyncCallback<ArrayList<Long>>() {

                    @Override
                    public void onFailure(Throwable caught) {
                        LayerHandlerManager.fireEvent(new DisplayLayersProgressBarEvent(
                                false));
                        GeoPlatformMessage.errorMessage("Save Layers Error",
                                "Problems on saving the new tree state after layers creation");
                    }

                    @Override
                    public void onSuccess(ArrayList<Long> result) {
                        GPLayerSaveCache.getInstance().remove(memento);
                        LayoutManager.get().getStatusMap().setStatus(
                                "Layers saveded successfully.",
                                EnumSearchStatus.STATUS_SEARCH.toString());
                        List<AbstractMementoLayer> listMementoLayers = memento.getAddedLayers();
                        for (int i = 0; i < listMementoLayers.size(); i++) {
                            listMementoLayers.get(i).getRefBaseElement().setId(i);
                        }
                        LayerHandlerManager.fireEvent(peekCacheEvent);
                    }
                });
    }
}
