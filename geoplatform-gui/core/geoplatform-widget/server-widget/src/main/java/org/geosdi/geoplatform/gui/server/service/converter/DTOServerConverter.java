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
import org.geosdi.geoplatform.core.model.GeoPlatformServer;
import org.geosdi.geoplatform.gui.client.model.GPLayerGrid;
import org.geosdi.geoplatform.gui.client.model.GPRasterLayerGrid;
import org.geosdi.geoplatform.gui.client.model.GPServerBeanModel;
import org.geosdi.geoplatform.gui.configuration.map.client.geometry.BboxClientInfo;
import org.geosdi.geoplatform.gui.configuration.map.client.layer.GPLayerType;
import org.geosdi.geoplatform.responce.ServerDTO;
import org.geosdi.geoplatform.responce.ShortLayerDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 *
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email  giuseppe.lascaleia@geosdi.org
 */
@Component(value = "dtoServerConverter")
public class DTOServerConverter {

    private final Logger logger = LoggerFactory.getLogger(
            this.getClass().getName());

    /**
     *
     * @param serversWS
     * @return
     */
    public ArrayList<GPServerBeanModel> convertServer(
            Collection<ServerDTO> serversWS) {
        ArrayList<GPServerBeanModel> serversDTO = new ArrayList<GPServerBeanModel>();

        if (serversWS != null) {
            for (ServerDTO gpServer : serversWS) {
                GPServerBeanModel serverDTO = new GPServerBeanModel();
                serverDTO.setId(gpServer.getId());
                serverDTO.setUrlServer(gpServer.getServerUrl());
                serverDTO.setName(gpServer.getName());
                serversDTO.add(serverDTO);
            }
        }

        return serversDTO;
    }

    /**
     * 
     * @param gpServer
     * @return
     */
    public GPServerBeanModel getServerDetail(GeoPlatformServer gpServer) {
        GPServerBeanModel serverDTO = new GPServerBeanModel();
        serverDTO.setId(gpServer.getId());
        serverDTO.setName(gpServer.getName());
        serverDTO.setUrlServer(gpServer.getServerUrl());
        serverDTO.setTitle(gpServer.getTitle());
        serverDTO.setContactOrganization(gpServer.getContactOrganization());
        serverDTO.setContactPerson(gpServer.getContactPerson());
        return serverDTO;
    }

    /**
     * 
     * @param layers
     * 
     * @return
     *        ArrayList<? extends GPLayerBeanModel>
     */
    public ArrayList<? extends GPLayerGrid> convertRasterLayer(
            Collection<ShortLayerDTO> layers) {
        ArrayList<GPRasterLayerGrid> layersDTO = new ArrayList<GPRasterLayerGrid>();

        if (layers != null) {
            for (ShortLayerDTO layer : layers) {
                GPRasterLayerGrid raster = new GPRasterLayerGrid();
                raster.setId(layer.getId());
                raster.setLabel(layer.getTitle());
                raster.setName(layer.getName());
                raster.setAbstractText(layer.getAbstractText());
                raster.setLayerType(GPLayerType.RASTER);

                if (layer.getBbox() != null) {
                    raster.setBbox(new BboxClientInfo(layer.getBbox().getMinX(),
                            layer.getBbox().getMinY(), layer.getBbox().getMaxX(),
                            layer.getBbox().getMaxY()));
                    raster.setCrs(layer.getSrs());
                }
                layersDTO.add(raster);
            }
        }

        return layersDTO;
    }

    /**
     * 
     * @param serverWS
     * 
     * @return 
     *        GPServerBeanModel
     */
    public GPServerBeanModel convertServerWS(ServerDTO serverWS) {
        GPServerBeanModel serverDTO = new GPServerBeanModel();
        serverDTO.setId(serverWS.getId());
        serverDTO.setName(serverWS.getName());
        serverDTO.setUrlServer(serverWS.getServerUrl());
        serverDTO.setLayers(this.convertRasterLayer(serverWS.getLayersDTO()));

        return serverDTO;
    }
}
