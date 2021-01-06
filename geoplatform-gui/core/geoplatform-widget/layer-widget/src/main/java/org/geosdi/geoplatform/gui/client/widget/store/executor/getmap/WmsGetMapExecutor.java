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
package org.geosdi.geoplatform.gui.client.widget.store.executor.getmap;

import java.util.List;
import org.geosdi.geoplatform.gui.client.config.MementoModuleInjector;
import org.geosdi.geoplatform.gui.client.model.memento.save.IMementoSave;
import org.geosdi.geoplatform.gui.client.model.memento.save.MementoSaveBuilder;
import org.geosdi.geoplatform.gui.client.model.memento.save.bean.MementoSaveAddedLayers;
import org.geosdi.geoplatform.gui.client.widget.store.executor.LayerTreeStoreExecutor;
import org.geosdi.geoplatform.gui.client.widget.tree.GPTreePanel;
import org.geosdi.geoplatform.gui.model.tree.GPBeanTreeModel;

/**
 *
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class WmsGetMapExecutor extends LayerTreeStoreExecutor implements
        IWmsGetMapExecutor {

    public WmsGetMapExecutor(GPTreePanel<GPBeanTreeModel> thetree) {
        super(thetree);
    }

    @Override
    public void addLayersFromWmsGetMap(List<GPBeanTreeModel> layers) {
        GPBeanTreeModel parentDestination = this.tree.getSelectionModel().getSelectedItem();

        this.tree.getStore().insert(parentDestination, layers, 0, true);
        this.visitorAdd.insertLayerElements(layers, parentDestination);

        MementoSaveAddedLayers mementoSaveLayer = new MementoSaveAddedLayers(
                this);
        mementoSaveLayer.setAddedLayers(
                MementoSaveBuilder.generateMementoLayerList(layers));
        mementoSaveLayer.setDescendantMap(
                this.visitorAdd.getFolderDescendantMap());
        IMementoSave mementoSave = MementoModuleInjector.MainInjector.getInstance().getMementoSave();
        mementoSave.add(mementoSaveLayer);
    }

}
