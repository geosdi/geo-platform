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
import org.geosdi.geoplatform.connector.geoserver.request.layers.GeoserverLoadLayerRequest;
import org.geosdi.geoplatform.geoserver.GeoserverConnectorTest;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
                this.geoserverConnectorStore.loadLayerRequest().withName("poi").existLayer());
    }

    @Test
    public void a_test() throws Exception {
        RESTLayer restLayer = this.restReader.getLayer("poi");
        logger.info("#######################REST LAYER: {}\n", restLayer.getResourceUrl());
        GeoserverLoadLayerRequest geoserverLoadLayerRequest = this.geoserverConnectorStore.loadLayerRequest().withName("poigggtg");
        logger.info("#######################REST LAYER: {}\n", geoserverLoadLayerRequest.getResponse());
        logger.info("#####################EXSIST LAYER {}\n", geoserverLoadLayerRequest.existLayer());

        geoserverLoadLayerRequest = geoserverLoadLayerRequest.withName("poi");

        logger.info("#####################EXSIST LAYER {} \n", geoserverLoadLayerRequest.existLayer());
        logger.info("#####################LAYER {}\n", geoserverLoadLayerRequest.getResponse());
        //logger.info("###############\n {}\n", jacksonSupport.getDefaultMapper().writeValueAsString(restLayer) );
    }

//    @Test
//    public void b_getLayer() throws Exception {
//        RESTLayer restLayer = this.restReader.getLayer("poi");
//        GeoserverLoadLayerRequest geoserverLoadLayerRequest = this.geoserverConnectorStore.loadLayerRequest().withName("poi");
//        logger.info("#######################GEOSERVER_LAYER: {}\n", geoserverLoadLayerRequest.getResponse());
//        logger.info("#######################REST_LAYER: {}\n", restLayer.getStyles().get(0).getName());
//
//        if(restLayer.getStyles() != null) {
//            for (String styleName : restLayer.getStyles().getNames()) {
//                Assert.assertTrue("###################STYLE NAME:", geoserverLoadLayerRequest.getResponse().getS);
//            }
//        }
//
//    }
}