/*
 *
 *    geo-platform
 *    Rich webgis framework
 *    http://geo-platform.org
 *   ====================================================================
 *
 *   Copyright (C) 2008-2024 geoSDI Group (CNR IMAA - Potenza - ITALY).
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
package org.geosdi.geoplatform.geoserver.featurestype;

import it.geosolutions.geoserver.rest.decoder.RESTFeatureTypeList;
import it.geosolutions.geoserver.rest.decoder.utils.NameLinkElem;
import org.geosdi.geoplatform.connector.geoserver.model.featuretypes.GPGeoserverFeatureTypeInfo;
import org.geosdi.geoplatform.connector.geoserver.model.featuretypes.category.GPGeoserverFeatureTypeCategory;
import org.geosdi.geoplatform.connector.geoserver.model.featuretypes.configured.GPGeoserverFeatureTypes;
import org.geosdi.geoplatform.connector.geoserver.model.featuretypes.wrapper.GPGeoserverFeatureTypeWrapper;
import org.geosdi.geoplatform.connector.geoserver.request.featuretypes.GeoserverLoadFeatureTypeWithUrlRequest;
import org.geosdi.geoplatform.geoserver.GeoserverConnectorTest;
import org.geosdi.geoplatform.responce.LayerAttribute;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.stream.Collectors;

/**
 * @author Vito Salvia - CNR IMAA geoSDI Group
 * @email vito.salvia@gmail.com
 */
public class GeoserverConnectorFeatureTypesTest extends GeoserverConnectorTest {

    static final Logger logger = LoggerFactory.getLogger(GeoserverConnectorFeatureTypesTest.class);
    //

    @Test
    public void a_loadWorkspaceDatastoresRequestTest() throws Exception {
        GPGeoserverFeatureTypeWrapper gpGeoserverFeatureTypeWrapper = this.geoserverConnectorStore.loadWorkspaceFeatureTypesRequest()
                .withFeatureTypeCategory(GPGeoserverFeatureTypeCategory.configured)
                .withWorkspace("topp").getResponse();
        logger.info("############{}\n", ((GPGeoserverFeatureTypes)gpGeoserverFeatureTypeWrapper.toFeatureType()).getFeatureTypes().size());
        SortedSet<String> layerNames = new TreeSet<String>();
        RESTFeatureTypeList featureTypes = this.restReader.getFeatureTypes("topp");
        for (NameLinkElem ft : featureTypes) {
            layerNames.add(ft.getName());
        }
        logger.info("############{}\n", layerNames);
    }

    /*
url: "http://${geoserver_url}/geoserver/rest/workspaces/${workspace_name}/datastores/${store_name}/featuretypes/${layer_name}.json
 */
    @Test()
    public void b_readFeatureType() throws Exception {
        GeoserverLoadFeatureTypeWithUrlRequest geoserverLoadFeatureTypeWithUrlRequest = this.geoserverConnectorStore.loadFeatureTypeWithUrl().
                withUrl("http://150.145.141.180/geoserver/rest/workspaces/tiger/datastores/nyc/featuretypes/poi.json");
        GPGeoserverFeatureTypeInfo gpGeoserverFeatureTypeInfo = geoserverLoadFeatureTypeWithUrlRequest.getResponse();
        logger.info("########################ATTRIBUTES {}\n", gpGeoserverFeatureTypeInfo.getAttributes());
        List<LayerAttribute> result = gpGeoserverFeatureTypeInfo.getAttributes().getValues().stream()
                .map(att -> new LayerAttribute(att.getName(), att.getBinding())).collect(Collectors.toList());
        logger.info("########################RESULT {}\n", result);
    }
}
