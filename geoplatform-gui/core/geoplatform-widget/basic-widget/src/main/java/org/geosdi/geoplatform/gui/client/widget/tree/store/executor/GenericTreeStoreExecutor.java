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
package org.geosdi.geoplatform.gui.client.widget.tree.store.executor;

import com.extjs.gxt.ui.client.data.ModelData;
import org.geosdi.geoplatform.gui.client.widget.tree.GPTreePanel;
import org.geosdi.geoplatform.gui.client.widget.tree.store.GPTreeStoreOperations;
import org.geosdi.geoplatform.gui.model.GPLayerBean;
import org.geosdi.geoplatform.gui.model.memento.IMemento;
import org.geosdi.geoplatform.gui.model.tree.GPBeanTreeModel;
import org.geosdi.geoplatform.gui.model.tree.GPLayerTreeModel;

import java.util.List;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public abstract class GenericTreeStoreExecutor<T extends IMemento> implements GPTreeStoreExecutor<T> {

    protected final GPTreePanel<GPBeanTreeModel> tree;

    public GenericTreeStoreExecutor(GPTreePanel<GPBeanTreeModel> thetree) {
        this.tree = thetree;
    }

    /**
     * @param layer
     * @param parentDestination
     * @return {@link Boolean}
     */
    protected boolean checkDuplicatedLayer(GPLayerBean layer,
            GPBeanTreeModel parentDestination) {
        for (ModelData element : parentDestination.getChildren()) {
            if (element != null && element instanceof GPLayerTreeModel
                    && ((GPLayerTreeModel) element).getTitle().equals(
                    layer.getTitle())) {
                return true;
            }
        }
        return false;
    }

    /**
     * @param layer
     * @param parentDestination
     * @return {@link String}
     */
    protected String generateUnduplicateAliasForLayer(GPLayerBean layer,
            GPBeanTreeModel parentDestination) {
        String originalName;
        if (layer.getAlias() != null) {
            originalName = layer.getAlias();
        } else {
            originalName = layer.getTitle();
        }
        return this.buildAlias(originalName, parentDestination);
    }

    /**
     * @param originalName
     * @param parentDestination
     * @return {@link String}
     */
    protected abstract String buildAlias(String originalName, GPBeanTreeModel parentDestination);

    /**
     * @param layers
     * @param sourceLayer
     */
    protected abstract void addRasterLayers(List<? extends GPLayerBean> layers, GPTreeStoreOperations sourceLayer);
}