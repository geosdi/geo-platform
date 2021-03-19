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
package org.geosdi.geoplatform.services.builder;

import com.google.common.collect.Lists;
import org.geosdi.geoplatform.core.model.GPBBox;
import org.geosdi.geoplatform.core.model.GPLayerInfo;
import org.geosdi.geoplatform.core.model.temporal.GPTemporalLayer;
import org.geosdi.geoplatform.core.model.temporal.dimension.GPTemporalDimension;
import org.geosdi.geoplatform.core.model.temporal.extent.GPTemporalExtent;
import org.geosdi.geoplatform.response.RasterLayerDTO;
import org.geotools.data.ows.CRSEnvelope;
import org.geotools.data.ows.Layer;
import org.geotools.data.ows.StyleImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Nonnull;
import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import static com.google.common.base.Preconditions.checkArgument;
import static java.lang.Double.isNaN;
import static java.lang.Double.valueOf;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Stream.of;
import static javax.annotation.meta.When.NEVER;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public interface GPRasterLayerDTOBuilder extends Serializable {

    /**
     * @param layer
     * @param urlServer
     * @return {@link List<RasterLayerDTO>}
     * @throws Exception
     */
    List<RasterLayerDTO> convertToLayerList(@Nonnull(when = NEVER) Layer layer, @Nonnull(when = NEVER) String urlServer) throws Exception;

    class RasterLayerDTOBuilder implements GPRasterLayerDTOBuilder {

        private static final Logger logger = LoggerFactory.getLogger(GPRasterLayerDTOBuilder.class);
        //
        private final static String EPSG_4326 = "EPSG:4326";
        private final static String EPSG_3857 = "EPSG:3857";
        private final static String EPSG_GOOGLE = "EPSG:900913";
        protected static final String GEB = "earthbuilder.google.com";

        /**
         * @param layer
         * @param urlServer
         * @return {@link List<RasterLayerDTO>}
         * @throws Exception
         */
        @Override
        public List<RasterLayerDTO> convertToLayerList(@Nonnull(when = NEVER) Layer layer, @Nonnull(when = NEVER) String urlServer) throws Exception {
            checkArgument(layer != null, "The Parameter layer must not be null.");
            checkArgument((urlServer != null) && !(urlServer.trim().isEmpty()), "The Parameter urlServer must not be null or an empty string.");
            List<RasterLayerDTO> shortLayers = Lists.newArrayList();
            shortLayers.add(this.getRasterAndSubRaster(layer, layer, urlServer));
            return shortLayers;
        }

        /**
         * @param ancestorLayer
         * @param layer
         * @param urlServer
         * @return {@link RasterLayerDTO}
         */
        private RasterLayerDTO getRasterAndSubRaster(Layer ancestorLayer, Layer layer, String urlServer) {
            RasterLayerDTO raster = this.convertLayerToRaster(ancestorLayer, layer, urlServer);
            raster.setSubLayerList(layer.getLayerChildren().stream()
                    .filter(Objects::nonNull)
                    .map(l -> this.getRasterAndSubRaster(ancestorLayer, l, urlServer))
                    .collect(toList()));
            return raster;
        }

        /**
         * @param ancestorLayer
         * @param layer
         * @param urlServer
         * @return {@link RasterLayerDTO}
         */
        private RasterLayerDTO convertLayerToRaster(Layer ancestorLayer, Layer layer, String urlServer) {
            RasterLayerDTO raster = new RasterLayerDTO();
            raster.setUrlServer(this.getUrlServer(urlServer));
            raster.setName(layer.getName());
            raster.setAbstractText(layer.get_abstract());
            raster.setTitle(((layer.getTitle() == null) || layer.getTitle().trim().equals("")) ? layer.getName() : layer.getTitle());
            Map<String, CRSEnvelope> additionalBounds = layer.getBoundingBoxes();
            logger.debug("ADDITIONAL BOUNDS ############################### {}", additionalBounds.toString());
            if (!additionalBounds.isEmpty()) {
                if (additionalBounds.containsKey(EPSG_GOOGLE) || additionalBounds.containsKey(EPSG_3857)) {
                    CRSEnvelope env = additionalBounds.get(EPSG_GOOGLE);
                    if (env == null) {
                        env = additionalBounds.get(EPSG_3857);
                    }
                    raster.setBbox(this.createBbox(env));
                    raster.setSrs(env.getEPSGCode());
                } else {
                    raster.setBbox(this.createBbox(layer.getLatLonBoundingBox()));
                    raster.setSrs(EPSG_4326);
                }
            } else {
                additionalBounds = ancestorLayer.getBoundingBoxes();
                if (additionalBounds.containsKey(EPSG_GOOGLE) || additionalBounds.containsKey(EPSG_3857)) {
                    CRSEnvelope env = additionalBounds.get(EPSG_GOOGLE);
                    if (env == null) {
                        env = additionalBounds.get(EPSG_3857);
                    }
                    raster.setBbox(this.createBbox(env));
                    raster.setSrs(env.getEPSGCode());
                } else {
                    raster.setBbox(this.createBbox(ancestorLayer.getLatLonBoundingBox()));
                    raster.setSrs(EPSG_4326);
                }
            }
            logger.debug("############Raster Name: {}", raster.getName());
            logger.debug("############Raster BBOX: {}", raster.getBbox());
            logger.debug("############Raster SRS: {}", raster.getSrs());
            if (urlServer.contains(GEB)) {
                if (layer.getLatLonBoundingBox() != null) {
                    raster.setBbox(this.createBbox(layer.getLatLonBoundingBox()));
                }
                raster.setSrs(EPSG_4326);
            }

            // Set LayerInfo of Raster Ith
            GPLayerInfo layerInfo = new GPLayerInfo();
            layerInfo.setQueryable(layer.isQueryable());
            if (layer.getKeywords() != null) {
                List<String> keywordList = Arrays.asList(layer.getKeywords());
                if (keywordList.size() > 0) {
                    layerInfo.setKeywords(keywordList);
                }
            }
            raster.setLayerInfo(layerInfo);
            logger.debug("@@@@@@@@@@@@@@@@@@@@@@@@@@MAX_SCALE : {} - MIN_SCAKE : {}\n\n", layer.getScaleDenominatorMax(), layer.getScaleDenominatorMin());
            raster.setMaxScale((isNaN(layer.getScaleDenominatorMin())) ? null : valueOf(layer.getScaleDenominatorMin()).floatValue());
            raster.setMinScale((isNaN(layer.getScaleDenominatorMax())) ? null : (valueOf(layer.getScaleDenominatorMax()).floatValue()));
            logger.debug("@@@@@@@@@@@@@@@@@@@@@@@@@@Trying to determinate if Layer : {} is Temporal.", raster.getName());
            if (((layer.getLayerDimensions() != null) && !(layer.getLayerDimensions().isEmpty())) && ((layer.getExtents() != null) && !(layer.getExtents().isEmpty()))) {
                GPTemporalDimension dimension = layer.getLayerDimensions().stream()
                        .filter(Objects::nonNull)
                        .filter(d -> d.getUnits().equals("ISO8601"))
                        .findFirst()
                        .filter(Objects::nonNull)
                        .map(d -> new GPTemporalDimension(d.getName(), d.getUnits()))
                        .get();
                GPTemporalExtent extent = of(layer.getExtent(dimension.getName()))
                        .filter(Objects::nonNull)
                        .map(e -> new GPTemporalExtent(e.getName(), e.getDefaultValue(), e.getValue()))
                        .findFirst().get();
                GPTemporalLayer temporalLayer = new GPTemporalLayer(dimension, extent);
                logger.debug("########################Build GPTemporalLayer : {}\n", temporalLayer);
                raster.setTemporalLayer(temporalLayer);
            }
            // Set Styles of Raster Ith
            List<StyleImpl> stylesImpl = layer.getStyles();
            logger.debug("@@@@@@@@@@@@@@@ Layer \"{}\" has {} SubLayers and {} StyleImpl @@@@@@@@", layer.getTitle(),
                    layer.getLayerChildren().size(), stylesImpl.size());
            raster.setStyleList(this.createStyleList(stylesImpl));
            return raster;
        }

        /**
         * @param stylesImpl
         * @return {@link List<String>}
         */
        private List<String> createStyleList(List<StyleImpl> stylesImpl) {
            return stylesImpl.stream()
                    .filter(Objects::nonNull)
                    .map(StyleImpl::getName)
                    .filter(Objects::nonNull)
                    .filter(n -> !n.trim().isEmpty())
                    .collect(toList());
        }

        /**
         * @param env
         * @return {@link GPBBox}
         */
        private GPBBox createBbox(CRSEnvelope env) {
            return ((env != null) ? new GPBBox(env.getMinX(), env.getMinY(), env.getMaxX(), env.getMaxY()) :
                    new GPBBox(-179, -89, 179, 89));
        }

        /**
         * @param urlServer
         * @return {@link String}
         */
        private String getUrlServer(String urlServer) {
            int index = -1;
            if (urlServer.contains(".map")) {
                index = urlServer.indexOf(".map") + 4;
            } else if (urlServer.contains("mapserv.exe") || urlServer.contains("mapserver") || urlServer
                    .contains("mapserv") || urlServer.contains("usertoken") || urlServer.contains("map")) {
                index = urlServer.indexOf("&");
            } else {
                index = urlServer.indexOf("?");
                // index += 1;
            }
            if (index != -1) {
                String newUrl = urlServer.substring(0, index);
                return newUrl;
            }
            return urlServer;
        }
    }
}
