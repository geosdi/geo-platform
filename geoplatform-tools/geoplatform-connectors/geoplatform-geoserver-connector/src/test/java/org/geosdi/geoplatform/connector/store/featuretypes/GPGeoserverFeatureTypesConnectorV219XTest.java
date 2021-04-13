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
package org.geosdi.geoplatform.connector.store.featuretypes;

import org.geosdi.geoplatform.connector.geoserver.model.bbox.GPGeoserverLatLonBoundingBox;
import org.geosdi.geoplatform.connector.geoserver.model.bbox.GPGeoserverNativeBoundingBox;
import org.geosdi.geoplatform.connector.geoserver.model.featuretypes.GPGeoserverFeatureTypeInfo;
import org.geosdi.geoplatform.connector.geoserver.model.featuretypes.IGPGeoserverFeatureTypeInfo;
import org.geosdi.geoplatform.connector.geoserver.model.featuretypes.attribute.GPFeatureTypeAttribute;
import org.geosdi.geoplatform.connector.geoserver.model.featuretypes.attribute.GPFeatureTypeAttributes;
import org.geosdi.geoplatform.connector.geoserver.model.featuretypes.attribute.IGPFeatureTypeAttribute;
import org.geosdi.geoplatform.connector.geoserver.model.featuretypes.attribute.IGPFeatureTypeAttributes;
import org.geosdi.geoplatform.connector.geoserver.request.featuretypes.GeoserverCreateFeatureTypeRequest;
import org.geosdi.geoplatform.connector.geoserver.request.featuretypes.GeoserverDeleteFeatureTypeRequest;
import org.geosdi.geoplatform.connector.geoserver.request.featuretypes.GeoserverLoadWorkspaceDatastoreFeatureTypesRequest;
import org.geosdi.geoplatform.connector.geoserver.request.featuretypes.GeoserverLoadWorkspaceFeatureTypesRequest;
import org.geosdi.geoplatform.connector.server.exception.IncorrectResponseException;
import org.geosdi.geoplatform.connector.server.exception.ResourceNotFoundException;
import org.geosdi.geoplatform.connector.store.GPBaseGeoserverConnectorStoreV219xTest;
import org.junit.FixMethodOrder;
import org.junit.Test;

import static java.lang.Boolean.TRUE;
import static java.util.Arrays.asList;
import static org.geosdi.geoplatform.connector.geoserver.model.featuretypes.category.GPGeoserverFeatureTypeCategory.configured;
import static org.junit.runners.MethodSorters.NAME_ASCENDING;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@FixMethodOrder(NAME_ASCENDING)
public class GPGeoserverFeatureTypesConnectorV219XTest extends GPBaseGeoserverConnectorStoreV219xTest {

    @Test(expected = ResourceNotFoundException.class)
    public void a_loadWorkspaceDatastoreFeatureTypesTest() throws Exception {
        GeoserverLoadWorkspaceDatastoreFeatureTypesRequest workspaceDatastoreFeatureTypesRequest = geoserverConnectorStoreV2_17_x
                .loadWorkspaceDatastoreFeatureTypesRequest();
        workspaceDatastoreFeatureTypesRequest.withWorkspace("topp");
        workspaceDatastoreFeatureTypesRequest.withStore("com");
        logger.info("@@@@@@@@@@@@@@@@@@@@@@@@@@@@LOAD_WORKSPACE_DATASTORE_FEATURE_TYPES_RESPONSE : {}\n", workspaceDatastoreFeatureTypesRequest.getResponse().toFeatureType());
    }

    @Test(expected = ResourceNotFoundException.class)
    public void b_loadEmptyWorkspaceDatastoreFeatureTypesTest() throws Exception {
        GeoserverLoadWorkspaceDatastoreFeatureTypesRequest workspaceDatastoreFeatureTypesRequest = geoserverConnectorStoreV2_17_x
                .loadWorkspaceDatastoreFeatureTypesRequest();
        workspaceDatastoreFeatureTypesRequest.withWorkspace("topp");
        workspaceDatastoreFeatureTypesRequest.withStore("Test");
        logger.info("@@@@@@@@@@@@@@@@@@@@@@@@@@@@LOAD_EMPTY_WORKSPACE_DATASTORE_FEATURE_TYPES_RESPONSE : {}\n", workspaceDatastoreFeatureTypesRequest.getResponse().toFeatureType());
    }

    @Test(expected = IncorrectResponseException.class)
    public void c_loadConfiguredWorkspaceDatastoreFeatureTypesTest() throws Exception {
        GeoserverLoadWorkspaceDatastoreFeatureTypesRequest workspaceDatastoreFeatureTypesRequest = geoserverConnectorStoreV2_17_x
                .loadWorkspaceDatastoreFeatureTypesRequest();
        workspaceDatastoreFeatureTypesRequest.withWorkspace("topp");
        workspaceDatastoreFeatureTypesRequest.withStore("com");
        workspaceDatastoreFeatureTypesRequest.withFeatureTypeCategory(configured);
        logger.info("@@@@@@@@@@@@@@@@@@@@@@@@@@@@LOAD_CONFIGURED_WORKSPACE_DATASTORE_FEATURE_TYPES_RESPONSE : {}\n", workspaceDatastoreFeatureTypesRequest.getResponse().toFeatureType());
    }

    @Test(expected = IncorrectResponseException.class)
    public void d_loadEmptyConfiguredWorkspaceDatastoreFeatureTypesTest() throws Exception {
        GeoserverLoadWorkspaceDatastoreFeatureTypesRequest workspaceDatastoreFeatureTypesRequest = geoserverConnectorStoreV2_17_x
                .loadWorkspaceDatastoreFeatureTypesRequest();
        workspaceDatastoreFeatureTypesRequest.withWorkspace("topp");
        workspaceDatastoreFeatureTypesRequest.withStore("Test");
        workspaceDatastoreFeatureTypesRequest.withFeatureTypeCategory(configured);
        logger.info("@@@@@@@@@@@@@@@@@@@@@@@@@@@@LOAD_EMPTY_CONFIGURED_WORKSPACE_DATASTORE_FEATURE_TYPES_RESPONSE : {}\n", workspaceDatastoreFeatureTypesRequest.getResponse().toFeatureType());
    }

    @Test
    public void e_loadWorkspaceFeatureTypesTest() throws Exception {
        GeoserverLoadWorkspaceFeatureTypesRequest workspaceFeatureTypesRequest = geoserverConnectorStoreV2_17_x.loadWorkspaceFeatureTypesRequest();
        workspaceFeatureTypesRequest.withWorkspace("sf");
        logger.info("@@@@@@@@@@@@@@@@@@@@@@@@@@@@LOAD_WORKSPACE_FEATURE_TYPES_RESPONSE : {}\n", workspaceFeatureTypesRequest.getResponse().toFeatureType());
    }

    @Test(expected = ResourceNotFoundException.class)
    public void f_createFeatureTypeTest() throws Exception {
        GeoserverCreateFeatureTypeRequest createFeatureTypeRequest = geoserverConnectorStoreV2_17_x.createFeatureTypeRequest();
        createFeatureTypeRequest.withWorkspace("topp").withStore("Test");
        IGPGeoserverFeatureTypeInfo featureTypeBody = new GPGeoserverFeatureTypeInfo();
        featureTypeBody.setNativeCRS("GEOGCS[&quot;WGS 84&quot;, \n" +
                "  DATUM[&quot;World Geodetic System 1984&quot;, \n" +
                "    SPHEROID[&quot;WGS 84&quot;, 6378137.0, 298.257223563, AUTHORITY[&quot;EPSG&quot;,&quot;7030&quot;]], \n" +
                "    AUTHORITY[&quot;EPSG&quot;,&quot;6326&quot;]], \n" +
                "  PRIMEM[&quot;Greenwich&quot;, 0.0, AUTHORITY[&quot;EPSG&quot;,&quot;8901&quot;]], \n" +
                "  UNIT[&quot;degree&quot;, 0.017453292519943295], \n" +
                "  AXIS[&quot;Geodetic longitude&quot;, EAST], \n" +
                "  AXIS[&quot;Geodetic latitude&quot;, NORTH], \n" +
                "  AUTHORITY[&quot;EPSG&quot;,&quot;4326&quot;]]");
        featureTypeBody.setSrs("EPSG:4326");
        featureTypeBody.setEnabled(TRUE);
        featureTypeBody.setTitle("layer_test");
        featureTypeBody.setName("test");
        GPGeoserverNativeBoundingBox nativeBoundingBox = new GPGeoserverNativeBoundingBox();
        nativeBoundingBox.setMinx(-74.0118315772888);
        nativeBoundingBox.setMaxx(-74.00153046439813);
        nativeBoundingBox.setMiny(40.70754683896324);
        nativeBoundingBox.setMaxy(40.719885123828675);
        nativeBoundingBox.setCrs("EPSG:4326");
        featureTypeBody.setNativeBoundingBox(nativeBoundingBox);
        GPGeoserverLatLonBoundingBox latLonBoundingBox = new GPGeoserverLatLonBoundingBox();
        latLonBoundingBox.setMinx(-74.0118315772888);
        latLonBoundingBox.setMaxx(-74.00857344353275);
        latLonBoundingBox.setMiny(40.70754683896324);
        latLonBoundingBox.setMaxy(40.711945649065406);
        latLonBoundingBox.setCrs("EPSG:4326");
        featureTypeBody.setLatLonBoundingBox(latLonBoundingBox);
        IGPFeatureTypeAttribute featureTypeAttribute = new GPFeatureTypeAttribute();
        featureTypeAttribute.setName("the_geom");
        featureTypeAttribute.setBinding("org.locationtech.jts.geom.Point");
        featureTypeAttribute.setNillable(TRUE);
        featureTypeAttribute.setMinOccurs(0);
        featureTypeAttribute.setMinOccurs(1);
        IGPFeatureTypeAttributes featureTypeAttributes = new GPFeatureTypeAttributes();
        featureTypeAttributes.setValues(asList(featureTypeAttribute));
        featureTypeBody.setAttributes(featureTypeAttributes);
        createFeatureTypeRequest.withFeatureTypeBody(featureTypeBody);
        logger.info("@@@@@@@@@@@@@@@@@@@@@@@@@@@@CREATE_FEATURE_TYPE_RESPONSE : {}\n", createFeatureTypeRequest.getResponse());
    }

    @Test(expected = ResourceNotFoundException.class)
    public void g_deleteFeatureTypeTest() throws Exception {
        GeoserverDeleteFeatureTypeRequest deleteFeatureTypeRequest = geoserverConnectorStoreV2_17_x.deleteFeatureTypeRequest();
        deleteFeatureTypeRequest.withWorkspace("topp").withStore("Test").withFeatureTypeName("test").withRecurse(TRUE);
        logger.info("@@@@@@@@@@@@@@@@@@@@@@@@@@@DELETE_FEATURE_TYPE_RESPONSE : {}\n", deleteFeatureTypeRequest.getResponse());
    }
}