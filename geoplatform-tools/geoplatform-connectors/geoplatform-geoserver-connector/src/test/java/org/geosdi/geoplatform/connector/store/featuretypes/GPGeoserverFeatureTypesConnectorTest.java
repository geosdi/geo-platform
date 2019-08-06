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
import org.geosdi.geoplatform.connector.store.GPBaseGeoserverConnectorStoreTest;
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
public class GPGeoserverFeatureTypesConnectorTest extends GPBaseGeoserverConnectorStoreTest {

    @Test
    public void a_loadWorkspaceDatastoreFeatureTypesTest() throws Exception {
        GeoserverLoadWorkspaceDatastoreFeatureTypesRequest workspaceDatastoreFeatureTypesRequest = geoserverConnectorStore.loadWorkspaceDatastoreFeatureTypesRequest();
        workspaceDatastoreFeatureTypesRequest.withWorkspace("topp");
        workspaceDatastoreFeatureTypesRequest.withStore("com");
        logger.info("@@@@@@@@@@@@@@@@@@@@@@@@@@@@LOAD_WORKSPACE_DATASTORE_FEATURE_TYPES_RESPONSE : {}\n", workspaceDatastoreFeatureTypesRequest.getResponse().toFeatureType());
    }

    @Test
    public void b_loadEmptyWorkspaceDatastoreFeatureTypesTest() throws Exception {
        GeoserverLoadWorkspaceDatastoreFeatureTypesRequest workspaceDatastoreFeatureTypesRequest = geoserverConnectorStore.loadWorkspaceDatastoreFeatureTypesRequest();
        workspaceDatastoreFeatureTypesRequest.withWorkspace("topp");
        workspaceDatastoreFeatureTypesRequest.withStore("Test");
        logger.info("@@@@@@@@@@@@@@@@@@@@@@@@@@@@LOAD_EMPTY_WORKSPACE_DATASTORE_FEATURE_TYPES_RESPONSE : {}\n", workspaceDatastoreFeatureTypesRequest.getResponse().toFeatureType());
    }

    @Test
    public void c_loadConfiguredWorkspaceDatastoreFeatureTypesTest() throws Exception {
        GeoserverLoadWorkspaceDatastoreFeatureTypesRequest workspaceDatastoreFeatureTypesRequest = geoserverConnectorStore.loadWorkspaceDatastoreFeatureTypesRequest();
        workspaceDatastoreFeatureTypesRequest.withWorkspace("topp");
        workspaceDatastoreFeatureTypesRequest.withStore("com");
        workspaceDatastoreFeatureTypesRequest.withFeatureTypeCategory(configured);
        logger.info("@@@@@@@@@@@@@@@@@@@@@@@@@@@@LOAD_CONFIGURED_WORKSPACE_DATASTORE_FEATURE_TYPES_RESPONSE : {}\n", workspaceDatastoreFeatureTypesRequest.getResponse().toFeatureType());
    }

    @Test
    public void d_loadEmptyConfiguredWorkspaceDatastoreFeatureTypesTest() throws Exception {
        GeoserverLoadWorkspaceDatastoreFeatureTypesRequest workspaceDatastoreFeatureTypesRequest = geoserverConnectorStore.loadWorkspaceDatastoreFeatureTypesRequest();
        workspaceDatastoreFeatureTypesRequest.withWorkspace("topp");
        workspaceDatastoreFeatureTypesRequest.withStore("Test");
        workspaceDatastoreFeatureTypesRequest.withFeatureTypeCategory(configured);
        logger.info("@@@@@@@@@@@@@@@@@@@@@@@@@@@@LOAD_EMPTY_CONFIGURED_WORKSPACE_DATASTORE_FEATURE_TYPES_RESPONSE : {}\n", workspaceDatastoreFeatureTypesRequest.getResponse().toFeatureType());
    }

    @Test
    public void e_loadWorkspaceFeatureTypesTest() throws Exception {
        GeoserverLoadWorkspaceFeatureTypesRequest workspaceFeatureTypesRequest = geoserverConnectorStore.loadWorkspaceFeatureTypesRequest();
        workspaceFeatureTypesRequest.withWorkspace("sf");
        logger.info("@@@@@@@@@@@@@@@@@@@@@@@@@@@@LOAD_WORKSPACE_FEATURE_TYPES_RESPONSE : {}\n", workspaceFeatureTypesRequest.getResponse().toFeatureType());
    }

    @Test
    public void f_createFeatureTypeTest() throws Exception {
        GeoserverCreateFeatureTypeRequest createFeatureTypeRequest = geoserverConnectorStore.createFeatureTypeRequest();
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

    @Test
    public void g_deleteFeatureTypeTest() throws Exception {
        GeoserverDeleteFeatureTypeRequest deleteFeatureTypeRequest = geoserverConnectorStore.deleteFeatureTypeRequest();
        deleteFeatureTypeRequest.withWorkspace("topp").withStore("Test").withFeatureTypeName("test").withRecurse(TRUE);
        logger.info("@@@@@@@@@@@@@@@@@@@@@@@@@@@DELETE_FEATURE_TYPE_RESPONSE : {}\n", deleteFeatureTypeRequest.getResponse());
    }
}