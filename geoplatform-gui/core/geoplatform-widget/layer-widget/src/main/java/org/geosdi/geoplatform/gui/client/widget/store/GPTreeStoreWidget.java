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
package org.geosdi.geoplatform.gui.client.widget.store;

import org.geosdi.geoplatform.gui.client.widget.store.executor.capabilities.WmsCapabilitiesExecutor;
import org.geosdi.geoplatform.gui.client.widget.store.executor.catalog.CatalogExecutor;
import org.geosdi.geoplatform.gui.client.widget.store.executor.getmap.WmsGetMapExecutor;
import org.geosdi.geoplatform.gui.client.widget.store.executor.menu.LayerMenuExecutor;
import org.geosdi.geoplatform.gui.client.widget.store.executor.publisher.PublisherExecutor;
import org.geosdi.geoplatform.gui.client.widget.tree.panel.GinTreePanel;
import org.geosdi.geoplatform.gui.client.widget.tree.store.GenericTreeStoreWidget;
import org.geosdi.geoplatform.gui.model.GPLayerBean;
import org.geosdi.geoplatform.gui.model.GPRasterBean;
import org.geosdi.geoplatform.gui.model.GPShortLayerBean;
import org.geosdi.geoplatform.gui.model.GPVectorBean;
import org.geosdi.geoplatform.gui.model.tree.GPBeanTreeModel;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.List;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@Singleton
public class GPTreeStoreWidget extends GenericTreeStoreWidget {

    @Inject
    private WmsCapabilitiesExecutor wmsCapabilitiesExecutor;
    @Inject
    private CatalogExecutor catalogExecutor;
    @Inject
    private WmsGetMapExecutor wmsGetMapExecutor;
    @Inject
    private LayerMenuExecutor layerMenuExecutor;
    @Inject
    private PublisherExecutor publisherExecutor;

    @Inject
    public GPTreeStoreWidget(GinTreePanel theTree) {
        super(theTree.get());
    }

    @Override
    public void addRasterLayersFromCapabilities(List<GPRasterBean> layers) {
        this.wmsCapabilitiesExecutor.addRasterLayersFromCapabilities(layers);
    }

    @Override
    public void addVectorLayersFromCapabilities(List<GPVectorBean> layers) {
        this.wmsCapabilitiesExecutor.addVectorLayersFromCapabilities(layers);
    }

    @Override
    public void addRasterLayersFromPublisher(List<? extends GPLayerBean> layers) {
        this.publisherExecutor.addRasterLayersFromPublisher(layers);
    }

    @Override
    public void addVectorLayersFromPublisher(List<GPVectorBean> layers) {
        this.publisherExecutor.addVectorLayersFromPublisher(layers);
    }

    @Override
    public void addLayersFromCopyMenu(List<? extends GPLayerBean> layers) {
        this.layerMenuExecutor.addLayersFromCopyMenu(layers);
    }

    @Override
    public void addRasterLayersFromCatalog(
            List<? extends GPShortLayerBean> layers) {
        this.catalogExecutor.addRasterLayersFromCatalog(layers);
    }

    @Override
    public void addLayersFromWmsGetMap(List<GPBeanTreeModel> layers) {
        this.wmsGetMapExecutor.addLayersFromWmsGetMap(layers);
    }

}
