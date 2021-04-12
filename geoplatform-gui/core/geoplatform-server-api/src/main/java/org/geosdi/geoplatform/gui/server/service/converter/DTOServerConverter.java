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

import com.google.common.collect.Lists;
import org.geosdi.geoplatform.configurator.crypt.GPPooledPBEStringEncryptorDecorator;
import org.geosdi.geoplatform.core.model.GeoPlatformServer;
import org.geosdi.geoplatform.core.model.temporal.dimension.GPTemporalDimension;
import org.geosdi.geoplatform.core.model.temporal.extent.GPTemporalExtent;
import org.geosdi.geoplatform.gui.configuration.map.client.geometry.BBoxClientInfo;
import org.geosdi.geoplatform.gui.model.logo.GPAttributionLogoURLBean;
import org.geosdi.geoplatform.gui.model.server.GPLayerGrid;
import org.geosdi.geoplatform.gui.model.server.GPRasterLayerGrid;
import org.geosdi.geoplatform.gui.model.server.GPServerBeanModel;
import org.geosdi.geoplatform.gui.model.temporal.dimension.GPTemporalDimensionBean;
import org.geosdi.geoplatform.gui.model.temporal.extent.GPTemporalExtentBean;
import org.geosdi.geoplatform.gui.model.tree.GPStyleStringBeanModel;
import org.geosdi.geoplatform.response.RasterLayerDTO;
import org.geosdi.geoplatform.response.ServerDTO;
import org.geosdi.geoplatform.response.ShortLayerDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

import static com.google.common.base.Preconditions.checkArgument;
import static java.util.stream.Collectors.toCollection;
import static javax.annotation.meta.When.NEVER;
import static org.geosdi.geoplatform.gui.shared.GPLayerType.WMS;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@Component(value = "dtoServerConverter")
class DTOServerConverter implements GPDTOServerConverter {

    private static final Logger logger = LoggerFactory.getLogger(DTOServerConverter.class);
    //
    @Autowired
    private GPPooledPBEStringEncryptorDecorator pooledPBEStringEncryptorDecorator;

    /**
     * @param serversWS
     * @return
     */
    @Override
    public ArrayList<GPServerBeanModel> convertServer(@Nullable Collection<ServerDTO> serversWS) {
        return ((serversWS != null) ? serversWS.stream()
                .filter(Objects::nonNull)
                .map(this::fromServerDTO)
                .collect(toCollection(ArrayList::new)) : Lists.newArrayList());
    }

    /**
     * @param server
     * @return {@link GPServerBeanModel}
     */
    @Override
    public GPServerBeanModel getServerDetail(@Nonnull(when = NEVER) GeoPlatformServer server) throws Exception {
        checkArgument(server != null, "The Parameter server must not be null.");
        GPServerBeanModel serverDTO = new GPServerBeanModel();
        serverDTO.setId(server.getId());
        serverDTO.setName(server.getName());
        serverDTO.setUrlServer(server.getServerUrl());
        serverDTO.setTitle(server.getTitle());
        serverDTO.setAlias(server.getAliasName());
        if (server.getOrganization() != null) {
            serverDTO.setOrganization(server.getOrganization().getName());
        }
        if (server.isProtected()) {
            serverDTO.setUsername(server.getAuthServer().getUsername());
            serverDTO.setPassword(server.getAuthServer().getPassword());
        }
        serverDTO.setProxy(server.isProxy());
        return serverDTO;
    }

    /**
     * @param layers
     * @return ArrayList<? extends GPLayerBeanModel>
     */
    @Override
    public ArrayList<? extends GPLayerGrid> createRasterLayerList(@Nullable List<? extends ShortLayerDTO> layers) {
        return this.createRasterLayerList(layers, new ArrayList<GPLayerGrid>());
    }

    /**
     * @param serverDTO
     * @return GPServerBeanModel
     */
    @Override
    public GPServerBeanModel convertServerWS(@Nonnull(when = NEVER) ServerDTO serverDTO) throws Exception {
        checkArgument(serverDTO != null, "The Parameter serverDTO must not be null.");
        GPServerBeanModel server = new GPServerBeanModel();
        server.setId(serverDTO.getId());
        server.setAlias(serverDTO.getAlias());
        server.setName(serverDTO.getName());
        server.setUrlServer(serverDTO.getServerUrl());
        server.setLayers(serverDTO.getLayerList() != null ? this.createRasterLayerList(serverDTO.getLayerList()) : null);
        server.setOrganization(serverDTO.getOrganization());
        server.setProxy(serverDTO.isProxy());
        if (serverDTO.isServerProtected()) {
            server.setPassword(this.pooledPBEStringEncryptorDecorator.decrypt(serverDTO.getPassword()));
        }
        server.setUsername(serverDTO.getUsername());
        server.setServerProtected(serverDTO.isServerProtected());
        return server;
    }

    /**
     * Invoked by the containing {@code BeanFactory} after it has set all bean properties
     * and satisfied {@link org.springframework.beans.factory.BeanFactoryAware}, {@code ApplicationContextAware} etc.
     * <p>This method allows the bean instance to perform validation of its overall
     * configuration and final initialization when all bean properties have been set.
     *
     * @throws Exception in the event of misconfiguration (such as failure to set an
     *                   essential property) or if initialization fails for any other reason
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        checkArgument(this.pooledPBEStringEncryptorDecorator != null, "The Parameter pooledPBEStringEncryptorDecorator must not be null.");
    }

    /**
     * @param layers
     * @param list
     * @return {@link ArrayList<GPLayerGrid>}
     */
    private ArrayList<? extends GPLayerGrid> createRasterLayerList(@Nullable List<? extends ShortLayerDTO> layers, ArrayList<GPLayerGrid> list) {
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
     * @param layer
     * @return {@link GPRasterLayerGrid}
     */
    private GPRasterLayerGrid convertToRasterLayerGrid(RasterLayerDTO layer) {
        GPRasterLayerGrid raster = new GPRasterLayerGrid();
        raster.setLabel(layer.getTitle());
        raster.setTitle(layer.getTitle());
        raster.setAlias(layer.getAlias());
        raster.setName(layer.getName());
        raster.setAbstractText(layer.getAbstractText());
        raster.setLayerType(WMS);
        raster.setDataSource(layer.getUrlServer());
        if (layer.getBbox() != null) {
            raster.setBbox(new BBoxClientInfo(layer.getBbox().getMinX(), layer.getBbox().getMinY(), layer.getBbox().getMaxX(),
                    layer.getBbox().getMaxY()));
            raster.setCrs(layer.getSrs());
        }
        raster.setMaxScale(layer.getMaxScale());
        raster.setMinScale(layer.getMinScale());
        if (layer.isTemporalLayer()) {
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
        if(layer.isSetAttribution()) {
            logger.info("##############Trying to read Logo Information.");
            GPAttributionLogoURLBean logoURLBean = new GPAttributionLogoURLBean();
            logoURLBean.setOnlineResource(layer.getLayerAttribution().getLogoUrl().getOnlineResource());
            logoURLBean.setHeight(layer.getLayerAttribution().getLogoUrl().getHeight());
            logoURLBean.setWidth(layer.getLayerAttribution().getLogoUrl().getWidth());
            logoURLBean.setFormat(layer.getLayerAttribution().getLogoUrl().getFormat());
            raster.setLogoURLBean(logoURLBean);
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

    /**
     * @param theServerDTO
     * @return {@link GPServerBeanModel}
     */
    private GPServerBeanModel fromServerDTO(ServerDTO theServerDTO) {
        GPServerBeanModel server = new GPServerBeanModel();
        server.setId(theServerDTO.getId());
        server.setAlias(theServerDTO.getAlias());
        server.setUrlServer(theServerDTO.getServerUrl());
        server.setName(theServerDTO.getName());
        server.setOrganization(theServerDTO.getOrganization());
        server.setServerProtected(theServerDTO.isServerProtected());
        if (theServerDTO.isServerProtected()) {
            server.setUsername(theServerDTO.getUsername());
            server.setPassword(this.pooledPBEStringEncryptorDecorator.decrypt(theServerDTO.getPassword()));
        }
        server.setProxy(theServerDTO.isProxy());
        return server;
    }
}
