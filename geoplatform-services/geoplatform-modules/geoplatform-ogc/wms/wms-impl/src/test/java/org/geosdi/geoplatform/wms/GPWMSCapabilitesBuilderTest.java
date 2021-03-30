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
package org.geosdi.geoplatform.wms;

import org.geosdi.geoplatform.response.RasterLayerDTO;
import org.geosdi.geoplatform.services.builder.GPWMSCapabilitesBuilder;
import org.geosdi.geoplatform.services.builder.IGPWMSCapabilitesBuilder;
import org.junit.Ignore;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

import static com.google.common.base.Preconditions.checkArgument;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class GPWMSCapabilitesBuilderTest {

    private static final Logger logger = LoggerFactory.getLogger(GPWMSCapabilitesBuilderTest.class);
    //
    private static final IGPWMSCapabilitesBuilder wmsCapabilitiesBuilder = new GPWMSCapabilitesBuilder();
    
    @Test
    public void a_loadWMSCapabilitiesTest() throws Exception {
        List<RasterLayerDTO> rasterLayers = wmsCapabilitiesBuilder.loadWMSCapabilities("http://150.145.141.180/geoserver/wms", null, null, null, null);
        checkArgument(rasterLayers.size() > 0);
        for (RasterLayerDTO rasterLayerDTO : rasterLayers.get(0).getSubLayerList()) {
            logger.info("######################LAYER : {} - STYLE_LIST : {}\n", rasterLayerDTO, rasterLayerDTO.getStyleList());
        }
    }

    @Test
    @Ignore
    public void b_loadWMSCapabilitiesTest() throws Exception {
        List<RasterLayerDTO> rasterLayers = wmsCapabilitiesBuilder.loadWMSCapabilitiesAuth("https://servizi.protezionecivile.it/geoserver/wms", null, null, null, null, null);
        checkArgument(rasterLayers.size() > 0);
        for (RasterLayerDTO rasterLayerDTO : rasterLayers.get(0).getSubLayerList()) {
            logger.info("######################LAYER : {} - STYLE_LIST : {}\n", rasterLayerDTO, rasterLayerDTO.getStyleList());
        }
    }

    @Test
    public void c_loadWMSCapabilitiesTest() throws Exception {
        List<RasterLayerDTO> rasterLayers = wmsCapabilitiesBuilder.loadWMSCapabilitiesAuth("https://prosit.geosdi.org/geoserver/wms", null, null, null, null, null);
        checkArgument(rasterLayers.size() > 0);
        for (RasterLayerDTO rasterLayerDTO : rasterLayers.get(0).getSubLayerList()) {
            logger.info("######################LAYER : {} \n", rasterLayerDTO);
        }
    }

    @Test
    public void d_loadWMSCapabilitiesTest() throws Exception {
        List<RasterLayerDTO> rasterLayers = wmsCapabilitiesBuilder.loadWMSCapabilities("https://insar.irea.cnr.it/geoserver/geonode/wms", null, null, "dpc", "4WzL06EA");
        checkArgument(rasterLayers.size() > 0);
        for (RasterLayerDTO rasterLayerDTO : rasterLayers.get(0).getSubLayerList()) {
            logger.info("######################LAYER : {} \n", rasterLayerDTO);
        }
    }
}
