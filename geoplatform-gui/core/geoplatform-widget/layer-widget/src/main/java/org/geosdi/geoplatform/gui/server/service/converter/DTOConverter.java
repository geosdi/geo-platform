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
import java.util.Map;
import org.geosdi.geoplatform.core.model.GPBBox;
import org.geosdi.geoplatform.core.model.GPFolder;
import org.geosdi.geoplatform.core.model.GPLayer;
import org.geosdi.geoplatform.core.model.GPLayerType;
import org.geosdi.geoplatform.core.model.GPRasterLayer;
import org.geosdi.geoplatform.core.model.GPUser;
import org.geosdi.geoplatform.core.model.GPVectorLayer;
import org.geosdi.geoplatform.gui.client.model.memento.AbstractMementoLayer;
import org.geosdi.geoplatform.gui.client.model.memento.MementoFolder;
import org.geosdi.geoplatform.gui.client.model.memento.MementoRaster;
import org.geosdi.geoplatform.gui.client.model.memento.MementoVector;
import org.geosdi.geoplatform.gui.configuration.map.client.geometry.BboxClientInfo;
import org.geosdi.geoplatform.gui.configuration.map.client.layer.ClientRasterInfo;
import org.geosdi.geoplatform.gui.configuration.map.client.layer.ClientVectorInfo;
import org.geosdi.geoplatform.gui.configuration.map.client.layer.GPFolderClientInfo;
import org.geosdi.geoplatform.gui.configuration.map.client.layer.IGPFolderElements;
import org.geosdi.geoplatform.responce.FolderDTO;
import org.geosdi.geoplatform.responce.RasterLayerDTO;
import org.geosdi.geoplatform.responce.VectorLayerDTO;
import org.geosdi.geoplatform.responce.collection.GPWebServiceMapData;
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

    public ArrayList<GPFolderClientInfo> convertOnlyFolder(
            Collection<FolderDTO> gpFolders) {
        ArrayList<GPFolderClientInfo> foldersClient = new ArrayList<GPFolderClientInfo>();

        if (gpFolders != null) {
            for (Iterator<FolderDTO> it = gpFolders.iterator(); it.hasNext();) {
                foldersClient.add(this.convertFolderElement(it.next()));
            }
        }

        return foldersClient;
    }

    public ArrayList<IGPFolderElements> convertFolderElements(
            TreeFolderElements folderElements) {
        ArrayList<IGPFolderElements> clientFolderElements = new ArrayList<IGPFolderElements>();
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

    private ClientRasterInfo convertRasterElement(RasterLayerDTO rasterDTO) {
        ClientRasterInfo raster = new ClientRasterInfo();
        raster.setId(rasterDTO.getId());
        raster.setLayerName(rasterDTO.getName());
        raster.setCrs(rasterDTO.getSrs());
        raster.setDataSource(rasterDTO.getUrlServer());
        //raster.setzIndex(rasterDTO.getPosition());
        raster.setBbox(this.convertBbox(rasterDTO.getBbox()));
        raster.setChecked(rasterDTO.isChecked());
        return raster;
    }

    private ClientVectorInfo convertVectorElement(VectorLayerDTO vectorDTO) {
        ClientVectorInfo vector = new ClientVectorInfo();
        vector.setId(vectorDTO.getId());
        this.setVectorLayerType(vector, vectorDTO.getLayerType());
        vector.setFeatureType(vectorDTO.getName());
        vector.setCrs(vectorDTO.getSrs());
        vector.setDataSource(vectorDTO.getUrlServer());
        //vector.setzIndex(vectorDTO.getPosition());
        vector.setBbox(this.convertBbox(vectorDTO.getBbox()));
        vector.setChecked(vectorDTO.isChecked());
        return vector;
    }

    private GPFolderClientInfo convertFolderElement(FolderDTO folderDTO) {
        GPFolderClientInfo folder = new GPFolderClientInfo();
        folder.setLabel(folderDTO.getName());
        folder.setId(folderDTO.getId());
        //folder.setzIndex(folderDTO.getPosition());
        folder.setNumberOfDescendants(folderDTO.getNumberOfChilds());
        folder.setChecked(folderDTO.isChecked());
        return folder;
    }

    private BboxClientInfo convertBbox(GPBBox gpBbox) {
        return new BboxClientInfo(gpBbox.getMinX(), gpBbox.getMinY(),
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
        gpFolder.setName(memento.getFolderName());
        gpFolder.setChecked(memento.isChecked());
        gpFolder.setId(memento.getIdBaseElement());
        gpFolder.setNumberOfDescendants(memento.getNumberOfDescendants());
        if (memento.getIdParent() != 0L) {
            GPFolder parent = new GPFolder();
            parent.setId(memento.getIdParent());
            gpFolder.setParent(parent);
        } else {
            GPUser user = new GPUser();
            user.setUsername("user_test_0");
            gpFolder.setOwner(user);
        }
        gpFolder.setPosition(memento.getzIndex());
        /*TODO: Once implemented shared function you must set this property
        gpFolder.setShared(true);*/
        return gpFolder;
    }

    public GPWebServiceMapData<Long, Integer> convertDescendantMap(
            Map<Long, Integer> descendantMap) {
        GPWebServiceMapData<Long, Integer> wsMap = new GPWebServiceMapData<Long, Integer>();
        wsMap.setDescendantsMap(descendantMap);
        System.out.println(
                "Size della mappa descendants: " + descendantMap.size());
        return wsMap;
    }

    public ArrayList<GPLayer> convertMementoLayers(
            List<AbstractMementoLayer> addedLayers, GPFolder folder) {
        ArrayList<GPLayer> layersList = new ArrayList<GPLayer>();
        for (AbstractMementoLayer layer : addedLayers) {
            if (layer instanceof MementoRaster) {
                MementoRaster memento = (MementoRaster) layer;
                GPRasterLayer raster = new GPRasterLayer();
                raster.setAbstractText(memento.getAbstractText());
                raster.setBbox(new GPBBox(memento.getLowerLeftX(),
                        memento.getLowerLeftY(),
                        memento.getUpperRightX(), memento.getUpperRightY()));
                raster.setChecked(memento.isChecked());
                raster.setFolder(folder);
                raster.setId(memento.getIdBaseElement());
                //raster.setLayerInfo();???
                raster.setName(memento.getLayerName());
                raster.setPosition(memento.getzIndex());
                //raster.setShared();???
                raster.setSrs(memento.getSrs());
                raster.setTitle(memento.getTitle());
                raster.setUrlServer(memento.getDataSource());
                raster.setLayerType(GPLayerType.RASTER);
                layersList.add(raster);
            } else if (layer instanceof MementoVector) {
                GPVectorLayer vector = new GPVectorLayer();
                MementoRaster memento = (MementoRaster) layer;
                vector.setAbstractText(memento.getAbstractText());
                vector.setBbox(new GPBBox(memento.getLowerLeftX(),
                        memento.getLowerLeftY(),
                        memento.getUpperRightX(), memento.getUpperRightY()));
                vector.setChecked(memento.isChecked());
                vector.setFolder(folder);
                vector.setId(memento.getIdBaseElement());
                //vector.setGeometry()???
                vector.setName(memento.getLayerName());
                vector.setPosition(memento.getzIndex());
                //vector.setShared();???
                vector.setSrs(memento.getSrs());
                vector.setTitle(memento.getTitle());
                vector.setUrlServer(memento.getDataSource());
                layersList.add(vector);
            }
        }
        return layersList;
    }
}
