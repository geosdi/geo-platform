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
package org.geosdi.geoplatform.gui.client.model.memento.save;

import com.google.common.collect.Lists;
import com.google.gwt.core.shared.GWT;
import org.geosdi.geoplatform.gui.client.model.memento.save.bean.*;
import org.geosdi.geoplatform.gui.client.model.memento.save.storage.AbstractMementoOriginalProperties;
import org.geosdi.geoplatform.gui.client.model.memento.save.storage.MementoFolderOriginalProperties;
import org.geosdi.geoplatform.gui.client.model.memento.save.storage.MementoLayerOriginalProperties;
import org.geosdi.geoplatform.gui.model.tree.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Nazzareno Sileno - CNR IMAA geoSDI Group
 * @email nazzareno.sileno@geosdi.org
 */
public class MementoSaveBuilder {

    private static SaveLayersPropertiesAction saveLayersPropertiesAction = new SaveLayersPropertiesAction();
    private static SaveFoldersPropertiesAction saveFoldersPropertiesAction = new SaveFoldersPropertiesAction();

    public static MementoFolder buildSaveFolderMemento(AbstractFolderTreeNode folder) {
        MementoFolder memento = new MementoFolder();
        if (folder.getId() != null) {
            memento.setIdBaseElement(folder.getId());
        } else {
            memento.setRefBaseElement(folder);
            folder.getObservable().addObserver(memento);
        }
        memento.setRefParent((folder.getParent() instanceof AbstractFolderTreeNode)
                ? (AbstractFolderTreeNode) folder.getParent() : null);
        memento.setNumberOfDescendants(folder.getNumberOfDescendants());
        memento.setzIndex(folder.getzIndex());
        memento.setFolderName(folder.getLabel());
        return memento;
    }

    public static List<AbstractMementoLayer> generateMementoLayerList(List<? extends GPBeanTreeModel> layers) {
        List<AbstractMementoLayer> mementoLayerList = Lists.<AbstractMementoLayer>newArrayList();
        for (GPBeanTreeModel beanModel : layers) {
            GPLayerTreeModel layer = null;
            AbstractMementoLayer memento = null;
            if (beanModel instanceof AbstractRasterTreeModel) {
                layer = ((AbstractRasterTreeModel) beanModel);
                memento = new MementoRaster();
                if(((AbstractRasterTreeModel) beanModel).isTemporalLayer()) {
                    ((MementoRaster) memento).setDimension(((AbstractRasterTreeModel) beanModel).getDimension());
                    ((MementoRaster) memento).setExtent(((AbstractRasterTreeModel) beanModel).getExtent());
                }
                List<String> stringList = convertStyles(((AbstractRasterTreeModel) beanModel).getStyles());
                ((MementoRaster) memento).setStyles(stringList);
            } else if (beanModel instanceof AbstractVectorTreeModel) {
                layer = ((AbstractVectorTreeModel) beanModel);
                memento = new MementoVector();
            }
            MementoSaveBuilder.convertToMementoLayerFromLayerModel(memento, layer);
            mementoLayerList.add(memento);
        }
        System.out.println("Memento layer list size: " + mementoLayerList.size());
        return mementoLayerList;
    }

    private static List<String> convertStyles(ArrayList<GPStyleStringBeanModel> styles) {
        List<String> stringList = Lists.<String>newArrayList();
        for (GPStyleStringBeanModel gPStyleStringBeanModel : styles) {
            stringList.add(gPStyleStringBeanModel.getStyleString());
            GWT.log("#####################ECCO LO STILE : " + gPStyleStringBeanModel.getStyleString());
        }
        return stringList;
    }

    private static void convertToMementoLayerFromLayerModel(AbstractMementoLayer memento, GPLayerTreeModel layer) {
        memento.setRefBaseElement(layer);
        if (layer.getId() != null) {
            memento.setIdBaseElement(layer.getId());
        } else {
            layer.getObservable().addObserver(memento);
        }
        memento.setAbstractText(layer.getAbstractText());
        memento.setDataSource(layer.getDataSource());
        memento.setLayerName(layer.getName());
        memento.setLayerType(layer.getLayerType());
        memento.setSrs(layer.getCrs());
        memento.setTitle(layer.getTitle());
//        memento.setChecked(layer.isChecked());
//        memento.setAlias(layer.getAlias());
        memento.setzIndex(layer.getzIndex());
        // Bbox
        if (layer.getBbox() != null) {
            memento.setLowerLeftX(layer.getBbox().getLowerLeftX());
            memento.setLowerLeftY(layer.getBbox().getLowerLeftY());
            memento.setUpperRightX(layer.getBbox().getUpperRightX());
            memento.setUpperRightY(layer.getBbox().getUpperRightY());
        }
        // Parent folder
        AbstractFolderTreeNode refParent = (AbstractFolderTreeNode) layer.getParent();
        memento.setRefParent(refParent);
    }

    public static AbstractMementoOriginalProperties generateMementoOriginalProperties(GPBeanTreeModel element) {
        AbstractMementoOriginalProperties memento = null;
        if (element instanceof GPLayerTreeModel) {
            memento = new MementoLayerOriginalProperties(saveLayersPropertiesAction);
        } else if (element instanceof AbstractFolderTreeNode) {
            memento = new MementoFolderOriginalProperties(saveFoldersPropertiesAction);
        } else {
            throw new IllegalArgumentException("The method copyOriginalProperties in MementoSaveBuilder class does not accepts your instance: " + element);
        }
        return memento;
    }

    public static AbstractMementoSave generateTypeOfSaveMemento(GPBeanTreeModel element) {
        AbstractMementoSave memento = null;
        if (element instanceof AbstractFolderTreeNode) {
            memento = new MementoFolder();
        } else if (element instanceof AbstractVectorTreeModel) {
            memento = new MementoVector();
        } else if (element instanceof AbstractRasterTreeModel) {
            memento = new MementoRaster();
        }
        return memento;
    }
}
