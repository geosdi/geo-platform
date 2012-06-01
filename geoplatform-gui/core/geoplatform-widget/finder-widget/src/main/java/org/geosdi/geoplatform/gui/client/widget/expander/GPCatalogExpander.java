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
package org.geosdi.geoplatform.gui.client.widget.expander;

import com.extjs.gxt.ui.client.widget.treepanel.TreePanel;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.geosdi.geoplatform.gui.client.model.FullRecord;
import org.geosdi.geoplatform.gui.client.widget.SearchStatus;
import org.geosdi.geoplatform.gui.client.widget.components.search.pagination.RecordsContainer;
import org.geosdi.geoplatform.gui.client.widget.tree.expander.GPTreeExpanderNotifier;
import org.geosdi.geoplatform.gui.client.widget.tree.store.puregwt.event.AddRasterFromCatalogEvent;
import org.geosdi.geoplatform.gui.configuration.map.client.layer.GPLayerType;
import org.geosdi.geoplatform.gui.configuration.message.GeoPlatformMessage;
import org.geosdi.geoplatform.gui.impl.view.LayoutManager;
import org.geosdi.geoplatform.gui.model.GPLayerBean;
import org.geosdi.geoplatform.gui.model.server.GPRasterLayerGrid;
import org.geosdi.geoplatform.gui.model.tree.AbstractFolderTreeNode;
import org.geosdi.geoplatform.gui.model.tree.GPStyleStringBeanModel;
import org.geosdi.geoplatform.gui.puregwt.layers.LayerHandlerManager;
import org.geosdi.geoplatform.gui.puregwt.progressbar.layers.event.DisplayLayersProgressBarEvent;
import org.geosdi.geoplatform.gui.responce.OnlineResourceProtocolType;
import org.geosdi.geoplatform.gui.responce.URIDTO;

/**
 *
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class GPCatalogExpander
        extends GPTreeExpanderNotifier<AbstractFolderTreeNode> {

    private RecordsContainer recordsContainer;
    private DisplayLayersProgressBarEvent displayEvent = new DisplayLayersProgressBarEvent(true);

    public GPCatalogExpander(TreePanel theTree, RecordsContainer recordsContainer) {
        super(theTree);
        this.recordsContainer = recordsContainer;
        this.displayEvent.setMessage("Search Layers");
    }

    @Override
    protected void execute() {
//        LayerHandlerManager.fireEvent(displayEvent); // TODO Display progress bar (why the bar don't disappear?)

        List<FullRecord> records = recordsContainer.getSelectedRecords();
        List<GPLayerBean> layers = this.convert(records);

        LayerHandlerManager.fireEvent(new AddRasterFromCatalogEvent(layers));
    }

    @Override
    protected boolean checkNode() {
        return super.selectedElement.getId() == null;
    }

    @Override
    protected void defineStatusBarCancelMessage() {
        LayoutManager.getInstance().getStatusMap().setStatus(
                "Add layer operation cancelled.",
                SearchStatus.EnumSearchStatus.STATUS_SEARCH_ERROR.toString());
    }

    public void executeActionRequest() {
        if (tree.getSelectionModel().getSelectedItem() instanceof AbstractFolderTreeNode) {
            super.checkNodeState();
        } else {
            GeoPlatformMessage.alertMessage("Catalog Finder",
                    "You can put layers into folders only.\n"
                    + "Please select the correct node.");
        }
    }

    /**
     * TODO Think a better way for this purpose.
     * 
     * Here convert FullRecords to GPLayerBeans, in order to use this list into
     * GPTreeStoreWidget: that convert GPLayerBeans to RasterTreeNode (raster layer of the tree)
     * 
     */
    private List<GPLayerBean> convert(List<FullRecord> recordList) {
        List<GPLayerBean> layerList = new ArrayList<GPLayerBean>(recordList.size());

        for (FullRecord record : recordList) {
            URIDTO uri = this.getURI(record.getUriMap());

            GPLayerBean layer = new GPRasterLayerGrid(); // TODO Use custom raster class as MetadataRasterLayer?

            layer.setName(uri.getName());
            layer.setTitle(uri.getDescription());
            layer.setLabel(this.getLabel(uri.getName()));
            layer.setDataSource(this.getDataSource(uri.getServiceURL()));

            layer.setLayerType(GPLayerType.RASTER);
            layer.setBbox(record.getBBox());
            layer.setCrs(record.getCrs());
            layer.setStyles(new ArrayList<GPStyleStringBeanModel>(0));

            layerList.add(layer);
        }

        return layerList;
    }

    private URIDTO getURI(Map<OnlineResourceProtocolType, URIDTO> uriMap) {
        for (OnlineResourceProtocolType wmsProtocol : OnlineResourceProtocolType.LIST_WMS_GET_MAP_REQUEST) {
            URIDTO uri = uriMap.get(wmsProtocol);
            if (uri != null) {
                return uri;
            }
        }
        throw new IllegalArgumentException("FullRecord must have a URIDTO for WMS GetMap Request");
    }

    private String getLabel(String name) {
        int ind = name.indexOf(":");
        if (ind != -1) {
            return name.substring(ind + 1);
        }
        return name;
    }

    private String getDataSource(String serviceURL) {
        int ind = serviceURL.indexOf("?");
        if (ind != -1) {
            return serviceURL.substring(0, ind);
        }
        return serviceURL;
    }
}
