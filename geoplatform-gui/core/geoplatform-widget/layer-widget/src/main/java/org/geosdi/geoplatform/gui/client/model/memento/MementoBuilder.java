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
package org.geosdi.geoplatform.gui.client.model.memento;

import java.util.ArrayList;
import java.util.List;
import org.geosdi.geoplatform.gui.client.model.FolderTreeNode;
import org.geosdi.geoplatform.gui.client.model.RasterTreeNode;
import org.geosdi.geoplatform.gui.client.model.VectorTreeNode;
import org.geosdi.geoplatform.gui.configuration.map.client.layer.GPLayerType;
import org.geosdi.geoplatform.gui.model.GPLayerBean;
import org.geosdi.geoplatform.gui.model.tree.GPBeanTreeModel;

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
        memento.setIsChecked(folder.isChecked());
        memento.setNumberOfDescendants(folder.getNumberOfDescendants());
        memento.setzIndex(folder.getzIndex());
        return memento;
    }

    public static List<AbstractMementoLayer> generateMementoLayerList(List<? extends GPLayerBean> layers) {
        List<AbstractMementoLayer> mementoLayerList = new ArrayList<AbstractMementoLayer>();
        for (GPLayerBean layerBean : layers) {
            if (layerBean instanceof RasterTreeNode) {
                RasterTreeNode raster = ((RasterTreeNode) layerBean);
                MementoRaster mementoRaster = new MementoRaster();
                if (layerBean.getId() != 0L) {
                    mementoRaster.setIdBaseElement(layerBean.getId());
                } else {
                    mementoRaster.setRefBaseElement(raster);
                    raster.getObservable().addObserver(mementoRaster);
                }
                mementoRaster.setAbstractText(layerBean.getAbstractText());
                mementoRaster.setDataSource(layerBean.getDataSource());
                mementoRaster.setRefBaseElement(raster);
                mementoRaster.setIsChecked(raster.isChecked());
                mementoRaster.setLayerName(layerBean.getName());
                mementoRaster.setLayerType(GPLayerType.RASTER);
                mementoRaster.setLowerLeftX(layerBean.getBbox().getLowerLeftX());
                mementoRaster.setLowerLeftY(layerBean.getBbox().getLowerLeftY());
                mementoRaster.setSrs(layerBean.getCrs());
                mementoRaster.setTitle(layerBean.getLabel());
                mementoRaster.setzIndex(layerBean.getzIndex());
                mementoRaster.setUpperRightX(layerBean.getBbox().getUpperRightX());
                mementoRaster.setUpperRightY(layerBean.getBbox().getUpperRightY());
                FolderTreeNode refParent = (FolderTreeNode) raster.getParent();
                mementoRaster.setRefParent(refParent);
                mementoLayerList.add(mementoRaster);
            } else if (layerBean instanceof VectorTreeNode) {
                VectorTreeNode vector = ((VectorTreeNode) layerBean);
                MementoVector mementoVector = new MementoVector();
                if (layerBean.getId() != 0L) {
                    mementoVector.setIdBaseElement(layerBean.getId());
                } else {
                    mementoVector.setRefBaseElement(vector);
                    vector.getObservable().addObserver(mementoVector);
                }
                mementoVector.setAbstractText(layerBean.getAbstractText());
                mementoVector.setDataSource(layerBean.getDataSource());
                mementoVector.setRefBaseElement(vector);
                mementoVector.setIsChecked(vector.isChecked());
                mementoVector.setLayerName(layerBean.getName());
                mementoVector.setLayerType(vector.getLayerType());
                mementoVector.setLowerLeftX(layerBean.getBbox().getLowerLeftX());
                mementoVector.setLowerLeftY(layerBean.getBbox().getLowerLeftY());
                mementoVector.setSrs(layerBean.getCrs());
                mementoVector.setTitle(layerBean.getLabel());
                mementoVector.setzIndex(layerBean.getzIndex());
                mementoVector.setUpperRightX(layerBean.getBbox().getUpperRightX());
                mementoVector.setUpperRightY(layerBean.getBbox().getUpperRightY());
                FolderTreeNode refParent = (FolderTreeNode) vector.getParent();
                mementoVector.setRefParent(refParent);
                mementoLayerList.add(mementoVector);
            }
        }
        return mementoLayerList;
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
