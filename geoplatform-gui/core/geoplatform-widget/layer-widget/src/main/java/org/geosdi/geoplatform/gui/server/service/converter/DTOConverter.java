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
package org.geosdi.geoplatform.gui.server.service.converter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import org.geosdi.geoplatform.gui.client.model.FolderTreeNode;
import org.geosdi.geoplatform.gui.client.model.RasterTreeNode;
import org.geosdi.geoplatform.gui.client.model.VectorTreeNode;
import org.geosdi.geoplatform.gui.model.tree.GPBeanTreeModel;
import org.geosdi.geoplatform.responce.FolderDTO;
import org.geosdi.geoplatform.responce.RasterLayerDTO;
import org.geosdi.geoplatform.responce.VectorLayerDTO;
import org.geosdi.geoplatform.responce.collection.TreeFolderElements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 *
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email  giuseppe.lascaleia@geosdi.org
 *
 * Simple Class to convert Web-Services beans model in DTO beans Client
 * 
 */
@Component(value = "dtoConverter")
public class DTOConverter {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public List<FolderTreeNode> convertOnlyFolder(Collection<FolderDTO> gpFolders) {
        if(gpFolders == null) return null;
        List<FolderTreeNode> foldersClient = new ArrayList<FolderTreeNode>();
        for (FolderDTO folderDTO : gpFolders) {
            foldersClient.add(this.convertFolderElement(folderDTO));
        }
        return foldersClient;
    }
    
    public List<GPBeanTreeModel> convertFolderElements(TreeFolderElements folderElements){
        List<GPBeanTreeModel> clientFolderElements = new ArrayList<GPBeanTreeModel>();
        Object element;
        Iterator iterator = folderElements.iterator();
        while(iterator.hasNext()){
            element = iterator.next();
            if(element instanceof RasterLayerDTO){
                clientFolderElements.add(this.convertRasterElement((RasterLayerDTO)element));
            } else if(element instanceof VectorLayerDTO){
                clientFolderElements.add(this.convertVectorElement((VectorLayerDTO)element));
            } else if (element instanceof FolderDTO){
                clientFolderElements.add(this.convertFolderElement((FolderDTO)element));
            }
        }
        
        return clientFolderElements;
    }

    private RasterTreeNode convertRasterElement(RasterLayerDTO rasterDTO) {
        RasterTreeNode raster = new RasterTreeNode();
        raster.setId(rasterDTO.getId());
        raster.setLabel(rasterDTO.getName());
        raster.setCrs(rasterDTO.getSrs());
        raster.setDataSource(rasterDTO.getSrs());
        raster.setzIndex(rasterDTO.getPosition());
        return raster;
    }

    private VectorTreeNode convertVectorElement(VectorLayerDTO vectorDTO) {
        VectorTreeNode vector = new VectorTreeNode();
        vector.setId(vectorDTO.getId());
        vector.setCrs(vectorDTO.getSrs());
        vector.setDataSource(vectorDTO.getUrlServer());
        vector.setzIndex(vectorDTO.getPosition());
        return vector;
    }

    private FolderTreeNode convertFolderElement(FolderDTO folderDTO) {
        FolderTreeNode folder = new FolderTreeNode(folderDTO.getName());
        folder.setId(folderDTO.getId());
        folder.setzIndex(folderDTO.getPosition());
        folder.setHasChildrens(!folderDTO.isEmpty());
        return folder;
    }
    
    
}
