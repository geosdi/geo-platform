/*
 *  geo-platform
 *  Rich webgis framework
 *  http://geo-platform.org
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
package org.geosdi.geoplatform.gui.client.model.memento;

import java.util.ArrayList;
import java.util.List;
import org.geosdi.geoplatform.gui.client.model.FolderTreeNode;
import org.geosdi.geoplatform.gui.client.model.RasterTreeNode;
import org.geosdi.geoplatform.gui.client.model.VectorTreeNode;
import org.geosdi.geoplatform.gui.model.tree.GPBeanTreeModel;
import org.geosdi.geoplatform.gui.model.tree.GPLayerTreeModel;

/**
 * @author Nazzareno Sileno - CNR IMAA geoSDI Group
 * @email nazzareno.sileno@geosdi.org
 */
public class MementoBuilder {

    public static MementoFolder buildSaveFolderMemento(FolderTreeNode folder) {
        MementoFolder memento = new MementoFolder();
        if (folder.getId() != 0L) {
            memento.setIdBaseElement(folder.getId());
        } else {
            memento.setRefBaseElement(folder);
            folder.getObservable().addObserver(memento);
        }
        memento.setFolderName(folder.getLabel());
        memento.setRefParent((folder.getParent() instanceof FolderTreeNode) ? (FolderTreeNode) folder.getParent() : null);
        memento.setChecked(folder.isChecked());
        memento.setNumberOfDescendants(folder.getNumberOfDescendants());
        memento.setzIndex(folder.getzIndex());
        return memento;
    }

    public static List<AbstractMementoLayer> generateMementoLayerList(List<GPBeanTreeModel> layers) {
        List<AbstractMementoLayer> mementoLayerList = new ArrayList<AbstractMementoLayer>();
        for (GPBeanTreeModel beanModel : layers) {
            GPLayerTreeModel layer = null;
            AbstractMementoLayer memento = null;

            if (beanModel instanceof RasterTreeNode) {
                layer = ((RasterTreeNode) beanModel);
                memento = new MementoRaster();
            } else if (beanModel instanceof VectorTreeNode) {
                layer = ((VectorTreeNode) beanModel);
                memento = new MementoVector();
            }
            MementoBuilder.convertToMementoLayerFromLayerModel(memento, layer);

            mementoLayerList.add(memento);
        }
        System.out.println("Memento layer list size: " + mementoLayerList.size());
        return mementoLayerList;
    }

    private static void convertToMementoLayerFromLayerModel(AbstractMementoLayer memento, GPLayerTreeModel layer) {
        memento.setRefBaseElement(layer);
        if (layer.getId() != 0L) {
            memento.setIdBaseElement(layer.getId());
        } else {
            layer.getObservable().addObserver(memento);
        }
        memento.setAbstractText(layer.getAbstractText());
        memento.setDataSource(layer.getDataSource());
        memento.setChecked(layer.isChecked());
        memento.setLayerName(layer.getName());
        memento.setLayerType(layer.getLayerType());
        memento.setSrs(layer.getCrs());
        memento.setTitle(layer.getTitle());
        memento.setAlias(layer.getAlias());
        memento.setzIndex(layer.getzIndex());
        // Bbox
        memento.setLowerLeftX(layer.getBbox().getLowerLeftX());
        memento.setLowerLeftY(layer.getBbox().getLowerLeftY());
        memento.setUpperRightX(layer.getBbox().getUpperRightX());
        memento.setUpperRightY(layer.getBbox().getUpperRightY());
        // Parent folder
        FolderTreeNode refParent = (FolderTreeNode) layer.getParent();
        memento.setRefParent(refParent);
    }

    public static AbstractMementoSave generateTypeOfSaveMemento(GPBeanTreeModel element) {
        AbstractMementoSave memento = null;
        if (element instanceof FolderTreeNode) {
            memento = new MementoFolder();
        } else if (element instanceof VectorTreeNode) {
            memento = new MementoVector();
        } else if (element instanceof RasterTreeNode) {
            memento = new MementoRaster();
        }
        return memento;
    }
}
