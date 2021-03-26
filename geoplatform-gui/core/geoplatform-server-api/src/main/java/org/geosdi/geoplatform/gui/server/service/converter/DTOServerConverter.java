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
package org.geosdi.geoplatform.gui.server.service.converter;

import org.geosdi.geoplatform.core.model.GeoPlatformServer;
import org.geosdi.geoplatform.core.model.temporal.dimension.GPTemporalDimension;
import org.geosdi.geoplatform.core.model.temporal.extent.GPTemporalExtent;
import org.geosdi.geoplatform.gui.configuration.map.client.geometry.BBoxClientInfo;
import org.geosdi.geoplatform.gui.model.server.GPLayerGrid;
import org.geosdi.geoplatform.gui.model.server.GPRasterLayerGrid;
import org.geosdi.geoplatform.gui.model.server.GPServerBeanModel;
import org.geosdi.geoplatform.gui.model.temporal.dimension.GPTemporalDimensionBean;
import org.geosdi.geoplatform.gui.model.temporal.extent.GPTemporalExtentBean;
import org.geosdi.geoplatform.gui.model.tree.GPStyleStringBeanModel;
import org.geosdi.geoplatform.gui.shared.GPLayerType;
import org.geosdi.geoplatform.response.RasterLayerDTO;
import org.geosdi.geoplatform.response.ServerDTO;
import org.geosdi.geoplatform.response.ShortLayerDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@Component(value = "dtoServerConverter")
public class DTOServerConverter {

    private final Logger logger = LoggerFactory.getLogger(
            this.getClass().getName());

    /**
     * @param serversWS
     * @return
     */
    public ArrayList<GPServerBeanModel> convertServer(
            Collection<ServerDTO> serversWS) {
        ArrayList<GPServerBeanModel> serversDTO = new ArrayList<GPServerBeanModel>();
        if (serversWS != null) {
            for (ServerDTO serverDTO : serversWS) {
                GPServerBeanModel server = new GPServerBeanModel();
                server.setId(serverDTO.getId());
                server.setAlias(serverDTO.getAlias());
                server.setUrlServer(serverDTO.getServerUrl());
                server.setName(serverDTO.getName());
                server.setOrganization(serverDTO.getOrganization());
                server.setServerProtected(serverDTO.isServerProtected());
                server.setUsername(serverDTO.getUsername());
                server.setPassword(serverDTO.getPassword());
                server.setProxy(serverDTO.isProxy());
                serversDTO.add(server);
            }
        }

        return serversDTO;
    }

    /**
     * @param server
     * @return
     */
    public GPServerBeanModel getServerDetail(GeoPlatformServer server) {
        GPServerBeanModel serverDTO = new GPServerBeanModel();
        serverDTO.setId(server.getId());
        serverDTO.setName(server.getName());
        serverDTO.setUrlServer(server.getServerUrl());
        serverDTO.setTitle(server.getTitle());
        serverDTO.setAlias(server.getAliasName());
        if (server.getOrganization() != null) {
            serverDTO.setOrganization(server.getOrganization().getName());
        }
        return serverDTO;
    }

    /**
     * @param layers
     * @return ArrayList<? extends GPLayerBeanModel>
     */
    public ArrayList<? extends GPLayerGrid> createRasterLayerList(List<? extends ShortLayerDTO> layers) {
        return this.createRasterLayerList(layers, new ArrayList<GPLayerGrid>());
    }

    private ArrayList<? extends GPLayerGrid> createRasterLayerList(List<? extends ShortLayerDTO> layers, ArrayList<GPLayerGrid> list) {
        if (layers != null) {
            for (ShortLayerDTO layer : layers) {
                if (((RasterLayerDTO) layer).getSubLayerList().size() > 0) {
                    this.createRasterLayerList(((RasterLayerDTO) layer).getSubLayerList(), list);
                } else {
                    GPRasterLayerGrid raster = this.convertToRasterLayerGrid((RasterLayerDTO) layer);
                    list.add(raster);
                }
            }
        }

        return list;
    }

    /**
     * @param serverDTO
     * @return GPServerBeanModel
     */
    public GPServerBeanModel convertServerWS(ServerDTO serverDTO) {
        GPServerBeanModel server = new GPServerBeanModel();
        server.setId(serverDTO.getId());
        server.setAlias(serverDTO.getAlias());
        server.setName(serverDTO.getName());
        server.setUrlServer(serverDTO.getServerUrl());
        server.setLayers(serverDTO.getLayerList() != null
                ? this.createRasterLayerList(serverDTO.getLayerList()) : null);
        server.setOrganization(serverDTO.getOrganization());
        server.setProxy(serverDTO.isProxy());
        server.setPassword(serverDTO.getPassword());
        server.setUsername(serverDTO.getUsername());
        server.setServerProtected(serverDTO.isServerProtected());
        return server;
    }

    private GPRasterLayerGrid convertToRasterLayerGrid(RasterLayerDTO layer) {
        GPRasterLayerGrid raster = new GPRasterLayerGrid();
        raster.setLabel(layer.getTitle());
        raster.setTitle(layer.getTitle());
        raster.setAlias(layer.getAlias());
        raster.setName(layer.getName());
        raster.setAbstractText(layer.getAbstractText());
        raster.setLayerType(GPLayerType.WMS);
        raster.setDataSource(layer.getUrlServer());
        if (layer.getBbox() != null) {
            raster.setBbox(new BBoxClientInfo(layer.getBbox().getMinX(), layer.getBbox().getMinY(), layer.getBbox().getMaxX(),
                    layer.getBbox().getMaxY()));
            raster.setCrs(layer.getSrs());
        }
        raster.setMaxScale(layer.getMaxScale());
        raster.setMinScale(layer.getMinScale());
        if(layer.isTemporalLayer()) {
            logger.debug("##############Trying to read Temporal Information.");
            GPTemporalDimension dimension = layer.getTemporalLayer().getDimension();
            if (dimension != null) {
                GPTemporalDimensionBean dimensionBean = new GPTemporalDimensionBean();
                dimensionBean.setName(dimension.getName());
                dimensionBean.setUnits(dimension.getUnits());
                raster.setDimension(dimensionBean);
            }
            GPTemporalExtent extent = layer.getTemporalLayer().getExtent();
            if (extent != null) {
                GPTemporalExtentBean extentBean = new GPTemporalExtentBean();
                extentBean.setName(extent.getName());
                extentBean.setDefaultExtent(extent.getDefaultExtent());
                extentBean.setValue(extent.getValue());
                raster.setExtent(extentBean);
            }
        }
        ArrayList<GPStyleStringBeanModel> styles = new ArrayList<GPStyleStringBeanModel>();
        for (String styleString : layer.getStyleList()) {
            GPStyleStringBeanModel style = new GPStyleStringBeanModel();
            style.setStyleString(styleString);
            styles.add(style);
        }
        raster.setStyles(styles);
        return raster;
    }
}