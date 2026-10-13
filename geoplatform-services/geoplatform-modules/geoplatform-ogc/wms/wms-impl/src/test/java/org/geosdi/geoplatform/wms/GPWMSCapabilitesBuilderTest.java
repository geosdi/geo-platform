/*
 *
 *    geo-platform
 *    Rich webgis framework
 *    http://geo-platform.org
 *   ====================================================================
 *
 *   Copyright (C) 2008-2026 geoSDI Group (CNR IMAA - Potenza - ITALY).
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

import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.geosdi.geoplatform.response.RasterLayerDTO;
import org.geosdi.geoplatform.services.builder.GPWMSCapabilitesBuilder;
import org.geosdi.geoplatform.services.builder.GeoSDIHttpClient5;
import org.geosdi.geoplatform.services.builder.IGPWMSCapabilitesBuilder;
import org.geotools.ows.wms.CRSEnvelope;
import org.geotools.ows.wms.Layer;
import org.geotools.ows.wms.StyleImpl;
import org.geotools.ows.wms.WMSCapabilities;
import org.geotools.ows.wms.WebMapServer;
import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.mockito.MockedConstruction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import static com.google.common.base.Preconditions.checkArgument;
import static java.util.Arrays.asList;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Stream.of;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mockConstruction;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@FixMethodOrder(value = MethodSorters.NAME_ASCENDING)
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
        WMSCapabilities wmsCapabilities = createPrositWMSCapabilities();
        try (MockedConstruction<WebMapServer> mockedWebMapServer = mockConstruction(WebMapServer.class, (mock, context) -> {
            assertEquals(new URL("https://prosit.geosdi.org/geoserver/wms?request=GetCapabilities"), context.arguments().get(0));
            assertTrue(context.arguments().get(1) instanceof GeoSDIHttpClient5);
            when(mock.getCapabilities()).thenReturn(wmsCapabilities);
        })) {
            List<RasterLayerDTO> rasterLayers = wmsCapabilitiesBuilder.loadWMSCapabilitiesAuth("https://prosit.geosdi.org/geoserver/wms", null, null, null, null, null);
            checkArgument(rasterLayers.size() > 0);
            for (RasterLayerDTO rasterLayerDTO : rasterLayers.get(0).getSubLayerList()) {
                logger.info("######################LAYER : {} \n", rasterLayerDTO);
            }
            assertEquals(1, mockedWebMapServer.constructed().size());
            verify(mockedWebMapServer.constructed().get(0)).getCapabilities();
            assertEquals(1, rasterLayers.size());
            RasterLayerDTO rootLayer = rasterLayers.get(0);
            assertEquals("GeoServer Web Map Service", rootLayer.getTitle());
            assertEquals("https://prosit.geosdi.org/geoserver/wms", rootLayer.getUrlServer());
            List<RasterLayerDTO> subLayers = rootLayer.getSubLayerList();
            assertEquals(3, subLayers.size());
            assertEquals(asList("admin:admin_shp_comuni", "admin:admin_shp_oliveti_catastali", "admin:admin_shp_vigneti_catastali"),
                    subLayers.stream().map(RasterLayerDTO::getName).collect(toList()));
            RasterLayerDTO comuni = subLayers.get(0);
            assertEquals("EPSG:3857", comuni.getSrs());
            assertEquals(asList("polygon", "admin_comuni"), comuni.getStyleList());
            assertTrue(comuni.getLayerInfo().isQueryable());
            RasterLayerDTO oliveti = subLayers.get(1);
            assertEquals("EPSG:4326", oliveti.getSrs());
            assertEquals(15.5, oliveti.getBbox().getMinX(), 0d);
            assertEquals(asList("polygon"), oliveti.getStyleList());
            RasterLayerDTO vigneti = subLayers.get(2);
            assertEquals("Vigneti Catastali", vigneti.getTitle());
            assertEquals("EPSG:4326", vigneti.getSrs());
            assertEquals(13.9, vigneti.getBbox().getMinX(), 0d);
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

    /**
     * @return the {@link WMSCapabilities} published by the prosit GeoServer, used in place of the remote GetCapabilities response.
     */
    private static WMSCapabilities createPrositWMSCapabilities() {
        Layer rootLayer = new Layer("GeoServer Web Map Service");
        rootLayer.setLatLonBoundingBox(new CRSEnvelope("CRS:84", 13.9, 39.8, 16.9, 41.2));
        rootLayer.setBoundingBoxes(new HashMap<>());
        Layer comuni = createLayer("admin:admin_shp_comuni", "Comuni", new CRSEnvelope("CRS:84", 15.3, 39.9, 16.9, 41.2),
                "polygon", "admin_comuni");
        Map<String, CRSEnvelope> comuniBoundingBoxes = new HashMap<>();
        comuniBoundingBoxes.put("EPSG:4326", new CRSEnvelope("EPSG:4326", 15.3, 39.9, 16.9, 41.2));
        comuniBoundingBoxes.put("EPSG:3857", new CRSEnvelope("EPSG:3857", 1703201.1, 4850570.9, 1881314.8, 5040013.8));
        comuni.setBoundingBoxes(comuniBoundingBoxes);
        Layer oliveti = createLayer("admin:admin_shp_oliveti_catastali", "Oliveti Catastali",
                new CRSEnvelope("CRS:84", 15.5, 40.1, 16.6, 41.1), "polygon");
        Map<String, CRSEnvelope> olivetiBoundingBoxes = new HashMap<>();
        olivetiBoundingBoxes.put("EPSG:4326", new CRSEnvelope("EPSG:4326", 15.5, 40.1, 16.6, 41.1));
        oliveti.setBoundingBoxes(olivetiBoundingBoxes);
        // no BoundingBox : the root layer bounds are used
        Layer vigneti = createLayer("admin:admin_shp_vigneti_catastali", "Vigneti Catastali",
                new CRSEnvelope("CRS:84", 15.6, 40.2, 16.5, 41.0), "polygon");
        vigneti.setBoundingBoxes(new HashMap<>());
        rootLayer.addChildren(comuni);
        rootLayer.addChildren(oliveti);
        rootLayer.addChildren(vigneti);
        WMSCapabilities wmsCapabilities = new WMSCapabilities();
        wmsCapabilities.setLayer(rootLayer);
        return wmsCapabilities;
    }

    /**
     * @param name
     * @param title
     * @param latLonBoundingBox
     * @param styles
     * @return {@link Layer}
     */
    private static Layer createLayer(String name, String title, CRSEnvelope latLonBoundingBox, String... styles) {
        Layer layer = new Layer(title);
        layer.setName(name);
        layer.setQueryable(true);
        layer.setLatLonBoundingBox(latLonBoundingBox);
        layer.setStyles(of(styles).map(StyleImpl::new).collect(toList()));
        return layer;
    }
}
