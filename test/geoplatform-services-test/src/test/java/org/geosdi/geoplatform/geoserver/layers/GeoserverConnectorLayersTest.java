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
package org.geosdi.geoplatform.geoserver.layers;

import it.geosolutions.geoserver.rest.decoder.RESTLayer;
import it.geosolutions.geoserver.rest.decoder.RESTServiceUniqueValues;
import org.geosdi.geoplatform.connector.geoserver.model.featuretypes.GPGeoserverFeatureTypeInfo;
import org.geosdi.geoplatform.connector.geoserver.model.layers.GeoserverLayer;
import org.geosdi.geoplatform.connector.geoserver.request.featuretypes.GeoserverLoadFeatureTypeWithUrlRequest;
import org.geosdi.geoplatform.connector.geoserver.request.layers.GeoserverLoadLayerRequest;
import org.geosdi.geoplatform.connector.geoserver.request.layers.GeoserverLoadWorkspaceLayerRequest;
import org.geosdi.geoplatform.geoserver.GeoserverConnectorTest;
import org.geosdi.geoplatform.responce.LayerAttribute;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.stream.Collectors;

import static java.lang.Boolean.FALSE;

/**
 * @author Vito Salvia - CNR IMAA geoSDI Group
 * @email vito.salvia@gmail.com
 */
public class GeoserverConnectorLayersTest extends GeoserverConnectorTest {

    static final Logger logger = LoggerFactory.getLogger(GeoserverConnectorLayersTest.class);

    @Test
    public void a_existLayer() throws Exception {
        Assert.assertTrue("####################", this.restReader.existsLayer("tiger", "poi", FALSE) ==
                this.geoserverConnectorStore.loadLayerRequest().withName("poi").exsist());
    }

    @Test
    public void b_getLayer() throws Exception {
        RESTLayer restLayer = this.restReader.getLayer("poi");
        GeoserverLoadLayerRequest geoserverLoadLayerRequest = this.geoserverConnectorStore.loadLayerRequest().withName("poi");
        GeoserverLayer geoserverLayer = geoserverLoadLayerRequest.getResponse();
        logger.info("##########################RESOURCE_URL_REST_READER {}\n", restLayer.getResourceUrl());
        logger.info("##########################RESOURCE_URL_GEOSERVER_CONNECTOR {}\n", geoserverLayer.getLayerResource().getHref());
        logger.info("#######################GEOSERVER_LAYER: {}\n", geoserverLayer);
        List<String> styleNames = geoserverLoadLayerRequest.getResponse().getLayerStyle().getStyles().stream().map(s -> s.getName()).collect(Collectors.toList());
        Assert.assertTrue("###################STYLES_LENGTH", styleNames.size() == restLayer.getStyles().getNames().size());
        if(restLayer.getStyles() != null) {
            for (String styleName : restLayer.getStyles().getNames()) {
                logger.info("#######################STYLE_NAME: {}\n", styleName);
                Assert.assertTrue("###################STYLE NAME:",  styleNames.contains(styleName));
            }
        }
    }

    /*
    url: "http://${geoserver_url}/geoserver/rest/workspaces/${workspace_name}/datastores/${store_name}/featuretypes/${layer_name}.json
     */
    @Test()
    public void c_readFeatureType() throws Exception {
        GeoserverLoadFeatureTypeWithUrlRequest geoserverLoadFeatureTypeWithUrlRequest = this.geoserverConnectorStore.loadFeatureTypeWithUrl().
                withUrl("http://150.145.141.180/geoserver/rest/workspaces/tiger/datastores/nyc/featuretypes/poi.json");
        GPGeoserverFeatureTypeInfo gpGeoserverFeatureTypeInfo = geoserverLoadFeatureTypeWithUrlRequest.getResponse();
        logger.info("########################ATTRIBUTES {}\n", gpGeoserverFeatureTypeInfo.getAttributes());
        List<LayerAttribute> result = gpGeoserverFeatureTypeInfo.getAttributes().getValues().stream()
                .map(att -> new LayerAttribute(att.getName(), att.getBinding())).collect(Collectors.toList());
        logger.info("########################RESULT {}\n", result);
    }

    @Test
    public void d_getLayerWithWorkspace() throws Exception {
        RESTLayer restLayer = this.restReader.getLayer("tiger","poi");
        GeoserverLoadWorkspaceLayerRequest geoserverLoadLayerRequest = this.geoserverConnectorStore.loadWorkspaceLayerRequest()
                .withLayerName("poi").withWorkspaceName("tiger");
        GeoserverLayer geoserverLayer = geoserverLoadLayerRequest.getResponse();
        logger.info("##########################STYLES_REST_READER {}\n", restLayer.getStyles().getNames());
        logger.info("#######################GEOSERVER_LAYER: {}\n", geoserverLayer);
        List<String> styleNames = geoserverLoadLayerRequest.getResponse().getLayerStyle().getStyles().stream().map(s -> s.getName()).collect(Collectors.toList());
        logger.info("#######################GEOSERVER_STYLE_NAMES: {}\n", styleNames);
        Assert.assertTrue("###################STYLES_LENGTH", styleNames.size() == restLayer.getStyles().getNames().size());
        if(restLayer.getStyles() != null) {
            for (String styleName : restLayer.getStyles().getNames()) {
                logger.info("#######################STYLE_NAME: {}\n", styleName);
                Assert.assertTrue("###################STYLE NAME:",  styleNames.contains(styleName));
            }
        }
    }

    @Test
    public void e_getLayerWithWorkspace() throws Exception {
        RESTLayer restLayer = this.restReader.getLayer("nurc","mosaic");
        GeoserverLoadWorkspaceLayerRequest geoserverLoadLayerRequest = this.geoserverConnectorStore.loadWorkspaceLayerRequest()
                .withLayerName("mosaic").withWorkspaceName("nurc");
        GeoserverLayer geoserverLayer = geoserverLoadLayerRequest.getResponse();
        logger.info("##########################RESOURCE_URL_REST_READER {}\n", restLayer.getResourceUrl());
        logger.info("##########################RESOURCE_URL_GEOSERVER_CONNECTOR {}\n", geoserverLayer.getLayerResource().getHref());
        logger.info("#######################GEOSERVER_LAYER: {}\n", geoserverLayer);
        logger.info("#######################REST_READER_DEFAULT_STYLE: {}\n", restLayer.getDefaultStyle());
        Assert.assertTrue("###################STYLE NAME:",   restLayer.getDefaultStyle().equals(geoserverLayer.getDefaultStyle().getName()));
    }

    @Test
    public void f_getUniqueValues() throws Exception {
        RESTServiceUniqueValues uniqueValues = this.restReader.uniqueValues("comuni2001", "maschi");
        logger.info("####################UNIQUE_VALUES: {}\n", uniqueValues.getNames());
    }

    @Test
    public void g_existLayer() throws Exception {
        Assert.assertTrue("####################", this.restReader.existsLayer("tiger", "poi", FALSE) ==
                this.geoserverConnectorStore.loadWorkspaceLayerRequest().withLayerName("poi").withWorkspaceName("tiger").exsist());

        Assert.assertTrue("####################", this.restReader.existsLayer("tigerr", "poi", FALSE) ==
                this.geoserverConnectorStore.loadWorkspaceLayerRequest().withLayerName("poi").withWorkspaceName("tigerr").exsist());
    }

}