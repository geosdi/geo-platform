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
package org.geosdi.geoplatform.gui.server.service.converter;

import com.google.common.collect.Lists;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.geosdi.geoplatform.core.model.GPBBox;
import org.geosdi.geoplatform.core.model.GPFolder;
import org.geosdi.geoplatform.core.model.GPLayer;
import org.geosdi.geoplatform.core.model.GPLayerType;
import org.geosdi.geoplatform.core.model.GPProject;
import org.geosdi.geoplatform.core.model.GPRasterLayer;
import org.geosdi.geoplatform.core.model.GPVectorLayer;
import org.geosdi.geoplatform.gui.client.model.memento.save.bean.AbstractMementoLayer;
import org.geosdi.geoplatform.gui.client.model.memento.save.bean.MementoFolder;
import org.geosdi.geoplatform.gui.client.model.memento.save.bean.MementoRaster;
import org.geosdi.geoplatform.gui.client.model.memento.save.bean.MementoVector;
import org.geosdi.geoplatform.gui.client.model.memento.save.storage.MementoLayerOriginalProperties;
import org.geosdi.geoplatform.gui.client.model.projects.GPClientProject;
import org.geosdi.geoplatform.gui.configuration.map.client.geometry.BBoxClientInfo;
import org.geosdi.geoplatform.gui.configuration.map.client.layer.ClientRasterInfo;
import org.geosdi.geoplatform.gui.configuration.map.client.layer.ClientVectorInfo;
import org.geosdi.geoplatform.gui.configuration.map.client.layer.GPFolderClientInfo;
import org.geosdi.geoplatform.gui.configuration.map.client.layer.GPLayerClientInfo;
import org.geosdi.geoplatform.gui.configuration.map.client.layer.IGPFolderElements;
import org.geosdi.geoplatform.gui.model.tree.GPStyleStringBeanModel;
import org.geosdi.geoplatform.responce.AccountProjectPropertiesDTO;
import org.geosdi.geoplatform.responce.FolderDTO;
import org.geosdi.geoplatform.responce.RasterLayerDTO;
import org.geosdi.geoplatform.responce.RasterPropertiesDTO;
import org.geosdi.geoplatform.responce.ShortLayerDTO;
import org.geosdi.geoplatform.responce.VectorLayerDTO;
import org.geosdi.geoplatform.responce.collection.GPWebServiceMapData;
import org.geosdi.geoplatform.responce.collection.TreeFolderElements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 *
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 *
 * Simple Class to convert Web-Services beans model in DTO beans Client
 *
 */
@Component(value = "dtoConverter")
public class DTOConverter {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public ArrayList<GPFolderClientInfo> convertOnlyFolder(
            Collection<FolderDTO> folders) {
        ArrayList<GPFolderClientInfo> foldersClient = Lists.newArrayList();
        if (folders != null) {
            for (Iterator<FolderDTO> it = folders.iterator(); it.hasNext();) {
                foldersClient.add(this.convertFolderElement(it.next()));
            }
        }
        return foldersClient;
    }

    public ArrayList<IGPFolderElements> convertFolderElements(
            TreeFolderElements folderElements) {
        ArrayList<IGPFolderElements> clientFolderElements = Lists.newArrayList();
        Object element;
        Iterator iterator = folderElements.iterator();
        while (iterator.hasNext()) {
            element = iterator.next();
            if (element instanceof RasterLayerDTO) {
                clientFolderElements.add(this.convertRasterElement(
                        (RasterLayerDTO) element));
            } else if (element instanceof VectorLayerDTO) {
                clientFolderElements.add(this.convertVectorElement(
                        (VectorLayerDTO) element));
            } else if (element instanceof FolderDTO) {
                clientFolderElements.add(this.convertFolderElement(
                        (FolderDTO) element));
            }
        }
        return clientFolderElements;
    }

    public RasterPropertiesDTO convertMementoProperties(MementoLayerOriginalProperties memento) {
        RasterPropertiesDTO dto = new RasterPropertiesDTO();
        dto.setAlias(memento.getName());
        dto.setChecked(memento.isChecked());
        dto.setId(memento.getIdBaseElement());
        dto.setOpacity(memento.getOpacity());
        dto.setCqlFilter(memento.getCqlFilter());
        List<String> styleList = Lists.newArrayList();
        for (GPStyleStringBeanModel beanModel : memento.getStyleList()) {
            styleList.add(beanModel.getStyleString());
        }
        dto.setStyleList(styleList);
        return dto;
    }

    private ClientRasterInfo convertRasterElement(RasterLayerDTO rasterDTO) {
        ClientRasterInfo raster = new ClientRasterInfo();
        this.convertToLayerElementFromLayerDTO(raster, rasterDTO);
        raster.setLayerType(org.geosdi.geoplatform.gui.configuration.map.client.layer.GPLayerType.RASTER);
        raster.setOpacity(rasterDTO.getOpacity());
        ArrayList<GPStyleStringBeanModel> styles = Lists.newArrayList();
        GPStyleStringBeanModel style;
        for (String styleString : rasterDTO.getStyleList()) {
            style = new GPStyleStringBeanModel();
            style.setStyleString(styleString);
            styles.add(style);
        }
        raster.setStyles(styles);
        return raster;
    }

    private ClientVectorInfo convertVectorElement(VectorLayerDTO vectorDTO) {
        ClientVectorInfo vector = new ClientVectorInfo();
        this.convertToLayerElementFromLayerDTO(vector, vectorDTO);
        this.setVectorLayerType(vector, vectorDTO.getLayerType());
        vector.setFeatureType(vectorDTO.getName());
        return vector;
    }

    private void convertToLayerElementFromLayerDTO(GPLayerClientInfo layer, ShortLayerDTO layerDTO) {
        layer.setAbstractText(layerDTO.getAbstractText());
        layer.setBbox(this.convertBbox(layerDTO.getBbox()));
        layer.setChecked(layerDTO.isChecked());
        layer.setCrs(layerDTO.getSrs());
        layer.setDataSource(layerDTO.getUrlServer());
        layer.setId(layerDTO.getId());
        layer.setLayerName(layerDTO.getName());
        layer.setTitle(layerDTO.getTitle());
        layer.setAlias(layerDTO.getAlias());
        layer.setCqlFilter(layerDTO.getCqlFilter());
        // layer.setzIndex(layerDTO.getPosition());
    }

    private GPFolderClientInfo convertFolderElement(FolderDTO folderDTO) {
        GPFolderClientInfo folder = new GPFolderClientInfo();
        folder.setLabel(folderDTO.getName());
        folder.setId(folderDTO.getId());
        // folder.setzIndex(folderDTO.getPosition());
        folder.setNumberOfDescendants(folderDTO.getNumberOfDescendants());
        folder.setChecked(folderDTO.isChecked());
        folder.setExpanded(folderDTO.isExpanded());
        return folder;
    }

    private BBoxClientInfo convertBbox(GPBBox gpBbox) {
        return new BBoxClientInfo(gpBbox.getMinX(), gpBbox.getMinY(),
                gpBbox.getMaxX(), gpBbox.getMaxY());
    }

    private void setVectorLayerType(ClientVectorInfo vector,
            GPLayerType gPLayerType) {
        switch (gPLayerType) {
            case POINT:
                vector.setLayerType(
                        org.geosdi.geoplatform.gui.configuration.map.client.layer.GPLayerType.POINT);
                break;
            case LINESTRING:
                vector.setLayerType(
                        org.geosdi.geoplatform.gui.configuration.map.client.layer.GPLayerType.LINESTRING);
                break;
            case POLYGON:
                vector.setLayerType(
                        org.geosdi.geoplatform.gui.configuration.map.client.layer.GPLayerType.POLYGON);
                break;
            case MULTIPOINT:
                vector.setLayerType(
                        org.geosdi.geoplatform.gui.configuration.map.client.layer.GPLayerType.MULTIPOINT);
                break;
            case MULTILINESTRING:
                vector.setLayerType(
                        org.geosdi.geoplatform.gui.configuration.map.client.layer.GPLayerType.MULTILINESTRING);
                break;
            case MULTIPOLYGON:
                vector.setLayerType(
                        org.geosdi.geoplatform.gui.configuration.map.client.layer.GPLayerType.MULTIPOLYGON);
                break;
        }
    }

    public GPFolder convertMementoFolder(MementoFolder memento) {
        GPFolder gpFolder = new GPFolder();
        gpFolder.setId(memento.getIdBaseElement());
        gpFolder.setName(memento.getFolderName());
        gpFolder.setNumberOfDescendants(memento.getNumberOfDescendants());
//        if (memento.getIdParent() != null) {
//            GPFolder parent = new GPFolder();
//            parent.setId(memento.getIdParent());
//            gpFolder.setParent(parent);
//        }
        gpFolder.setPosition(memento.getzIndex());
        /*
         * TODO: Once implemented shared function you must set this property
         * gpFolder.setShared(true);
         */
        return gpFolder;
    }

    public GPWebServiceMapData<Long, Integer> convertDescendantMap(
            Map<Long, Integer> descendantMap) {
        GPWebServiceMapData<Long, Integer> wsMap = new GPWebServiceMapData<Long, Integer>();
        wsMap.setDescendantsMap(descendantMap);
        System.out.println("Size descendants map: " + descendantMap.size());
        return wsMap;
    }

    public ArrayList<GPLayer> convertMementoLayers(List<AbstractMementoLayer> addedLayers) {
        ArrayList<GPLayer> layersList = Lists.newArrayList();
        GPFolder folder = new GPFolder();
        for (AbstractMementoLayer memento : addedLayers) {
            GPLayer layer = null;
            if (memento instanceof MementoRaster) {
//                MementoRaster mementoRaster = (MementoRaster) memento;
                layer = new GPRasterLayer();
                layer.setLayerType(GPLayerType.RASTER);
                ((GPRasterLayer) layer).setStyles(((MementoRaster) memento).getStyles());
                // layer.setLayerInfo();???
            } else if (memento instanceof MementoVector) {
//                MementoVector mementoVector = (MementoVector) memento;
                layer = new GPVectorLayer();
                // layer.setGeometry()???
            }
            this.convertToLayerElementFromMementoLayer(layer, memento);
            folder.setId(memento.getIdFolderParent());
            layer.setFolder(folder);
            layersList.add(layer);
        }
        return layersList;
    }

    public GPProject convertToGProject(GPClientProject clientProject) {
        GPProject project = new GPProject();
        project.setName(clientProject.getName());
        project.setShared(false);
        return project;
    }

    public AccountProjectPropertiesDTO convertToAccountProjectPropertiesDTO(Long accountID,
            GPClientProject project) {
        AccountProjectPropertiesDTO dto = new AccountProjectPropertiesDTO();
        dto.setAccountID(accountID);
        dto.setProjectID(project.getId());
        dto.setProjectName(project.getName());
        dto.setDefaultProject(project.isDefaultProject());
        return dto;
    }

    private void convertToLayerElementFromMementoLayer(GPLayer layer, AbstractMementoLayer memento) {
        layer.setAbstractText(memento.getAbstractText());
        layer.setBbox(new GPBBox(memento.getLowerLeftX(), memento.getLowerLeftY(),
                memento.getUpperRightX(), memento.getUpperRightY()));
        layer.setSrs(memento.getSrs());
        layer.setUrlServer(memento.getDataSource());
        layer.setId(memento.getIdBaseElement());
        layer.setName(memento.getLayerName());
        layer.setTitle(memento.getTitle());
        layer.setPosition(memento.getzIndex());
//        layer.setChecked(memento.isChecked());
//        layer.setAlias(memento.getAlias());
//        layer.setShared(mementoLayer.isShared());
    }
}
