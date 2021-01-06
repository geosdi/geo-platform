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
package org.geosdi.geoplatform.gui.client.widget.store.executor;

import com.google.common.collect.Lists;
import org.geosdi.geoplatform.gui.client.config.MementoModuleInjector;
import org.geosdi.geoplatform.gui.client.i18n.LayerModuleConstants;
import org.geosdi.geoplatform.gui.client.i18n.LayerModuleMessages;
import org.geosdi.geoplatform.gui.client.model.RasterTreeNode;
import org.geosdi.geoplatform.gui.client.model.memento.save.IMementoSave;
import org.geosdi.geoplatform.gui.client.model.memento.save.storage.AbstractMementoOriginalProperties;
import org.geosdi.geoplatform.gui.client.widget.tree.GPTreePanel;
import org.geosdi.geoplatform.gui.client.widget.tree.store.GPTreeStoreOperations;
import org.geosdi.geoplatform.gui.configuration.message.GeoPlatformMessage;
import org.geosdi.geoplatform.gui.model.GPLayerBean;
import org.geosdi.geoplatform.gui.model.tree.GPBeanTreeModel;
import org.geosdi.geoplatform.gui.shared.util.GPSharedUtils;

import java.util.List;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public abstract class LayerTreeStoreExecutor extends BaseTreeStoreExecutor {

    public LayerTreeStoreExecutor(GPTreePanel<GPBeanTreeModel> thetree) {
        super(thetree);
    }

    @Override
    protected String buildAlias(String originalName, GPBeanTreeModel parentDestination) {
        final String COPY_STRING = " - " + LayerModuleConstants.INSTANCE.GPTreeStoreWidget_copyStringText() + " (";
        int suffix = 1;
        int copyIndex = originalName.indexOf(COPY_STRING);
        String modifiedName;
        if (copyIndex != -1) { String intValue = originalName.substring(copyIndex + COPY_STRING.length(), originalName.lastIndexOf(')'));
            suffix = Integer.parseInt(intValue) + 1;
            modifiedName = originalName.substring(0, originalName.lastIndexOf('(') + 1) + suffix + ')';
        } else {
            modifiedName = originalName + COPY_STRING + suffix + ")";
        }
        return this.recursivelySearchAlias(parentDestination.getChildren(), modifiedName, suffix);
    }

    @Override
    protected void addRasterLayers(List<? extends GPLayerBean> layers, GPTreeStoreOperations sourceLayer) {
        if (layers == null || layers.isEmpty()) { // TODO assert
            return;
        }

        //TODO: Check the utility of this code
        //this.changeProgressBarMessage("Loading " + layers.size() + " Raster Layers into the Store");
        GPBeanTreeModel parentDestination = this.tree.getSelectionModel().getSelectedItem();
        // TODO The parent is always expanded?
        System.out.println("*** Parent expanded: " + super.tree.isExpanded(parentDestination));
        if (!super.tree.isExpanded(parentDestination)) {
            super.tree.setExpanded(parentDestination, true);
        }

        List<GPBeanTreeModel> layerList = Lists.<GPBeanTreeModel>newArrayList();
        StringBuilder existingLayers = new StringBuilder();
        List<AbstractMementoOriginalProperties> duplicatedMementoList = Lists.<AbstractMementoOriginalProperties>newArrayList();
        for (GPLayerBean layer : layers) {
            boolean duplicatedLayer = this.checkDuplicatedLayer(layer, parentDestination);
            if (duplicatedLayer) {
                String aliasForCopiedLayer = this.generateUnduplicateAliasForLayer(layer, parentDestination);
                RasterTreeNode rasterDuplicated = this.duplicateRaster(layer);
                duplicatedMementoList.add(this.manageDuplicateRasterForSave(rasterDuplicated, aliasForCopiedLayer));
                layerList.add(rasterDuplicated);
                existingLayers.append(layer.getLabel()).append("\n");
            } else {
                this.manageAddLayerFromSource(layerList, layer, sourceLayer);
            }
        }

        this.manageLayersInsertion(layerList, parentDestination, layers.get(0).getDataSource());
        this.putMementoLayersDuplicationInCacheToSave(duplicatedMementoList);
        this.createAlertMessage(existingLayers);
    }

    protected void createAlertMessage(StringBuilder existingLayers) {
        if (existingLayers.length() != 0) {
            GeoPlatformMessage.alertMessage(LayerModuleConstants.INSTANCE.GPTreeStoreWidget_renameAlertTitleText(),
                    LayerModuleMessages.INSTANCE.GPTreeStoreWidget_renameAlertBodyMessage(existingLayers.toString()));
        }
    }

    protected AbstractMementoOriginalProperties manageDuplicateRasterForSave(RasterTreeNode raster, String alias) {
        IMementoSave mementoSave = MementoModuleInjector.MainInjector.getInstance().getMementoSave();
        AbstractMementoOriginalProperties memento = mementoSave.copyOriginalProperties(raster);
        raster.setAlias(alias);
        return memento;
    }

    private void putMementoLayersDuplicationInCacheToSave(List<AbstractMementoOriginalProperties> duplicatedMementoList) {
        IMementoSave mementoSave = MementoModuleInjector.MainInjector.getInstance().getMementoSave();
        for (AbstractMementoOriginalProperties memento : GPSharedUtils.safeList(duplicatedMementoList)) {
            mementoSave.putOriginalPropertiesInCache(memento);
        }
    }
}