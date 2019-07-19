package org.geosdi.geoplatform.connector.jackson;

import org.geosdi.geoplatform.connector.geoserver.model.featuretypes.avaialable.GPGeoserverFeatureTypesList;
import org.geosdi.geoplatform.connector.geoserver.model.featuretypes.configured.GPGeoserverFeatureTypes;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.StringReader;

import static org.geosdi.geoplatform.connector.jackson.GPGeoserverJacksonTest.jacksonSupport;
import static org.junit.runners.MethodSorters.NAME_ASCENDING;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@FixMethodOrder(value = NAME_ASCENDING)
public class GPGeoserverFeatureTypesJacksonTest {

    private static final Logger logger = LoggerFactory.getLogger(GPGeoserverFeatureTypesJacksonTest.class);

    @Test
    public void a_unmarshallGeoserverFeatureTypesConfiguredFromStringTest() throws Exception {
        GPGeoserverFeatureTypes featureTypes = jacksonSupport.getDefaultMapper()
                .readValue(new StringReader("{\"featureTypes\": {\n" +
                        "    \"featureType\": [\n" +
                        "      {\n" +
                        "        \"name\": \"PrimitiveGeoFeature\",\n" +
                        "        \"href\": \"http://localhost:8080/geoserver/restng/workspaces/sf/datastores/sf/featuretypes/PrimitiveGeoFeature.json\"\n" +
                        "      },\n" +
                        "      {\n" +
                        "        \"name\": \"archsites\",\n" +
                        "        \"href\": \"http://localhost:8080/geoserver/restng/workspaces/sf/datastores/sf/featuretypes/archsites.json\"\n" +
                        "      }\n" +
                        "    ]\n" +
                        "  }\n" +
                        "}"), GPGeoserverFeatureTypes.class);
        logger.info("######################GEOSERVER_FEATURE_TYPES_CONFIGURED_FROM_STRING : {}\n", featureTypes);
    }

    @Test
    public void b_unmarshallGeoserverFeatureTypeAvaialableFromStringTest() throws Exception {
        GPGeoserverFeatureTypesList featureTypesList = jacksonSupport.getDefaultMapper()
                .readValue(new StringReader("{  \n" +
                        "   \"list\":{  \n" +
                        "      \"string\":[  \n" +
                        "         \"admin_shp_aree_a_gara\",\n" +
                        "         \"admin_shp_cmprov2016_wgs84_g\",\n" +
                        "         \"admin_shp_com2016_wgs84_g\",\n" +
                        "         \"admin_shp_comuni\",\n" +
                        "         \"admin_shp_corine\",\n" +
                        "         \"admin_shp_oliveti_catastali\",\n" +
                        "         \"admin_shp_pozzuoli447so_acque\",\n" +
                        "         \"admin_shp_pozzuoli447so_impianti\",\n" +
                        "         \"admin_shp_reg_2016_wgs84_g\",\n" +
                        "         \"admin_shp_sige_ultimo_evento_imcs_stim\",\n" +
                        "         \"admin_shp_vigneti_analisi\",\n" +
                        "         \"admin_shp_vigneti_catastali\",\n" +
                        "         \"doc150695142966651\",\n" +
                        "         \"donato\",\n" +
                        "         \"tempo\",\n" +
                        "         \"test1234\",\n" +
                        "         \"test12344\",\n" +
                        "         \"test12345\",\n" +
                        "         \"test_multi_linestring\"\n" +
                        "      ]\n" +
                        "   }\n" +
                        "}"), GPGeoserverFeatureTypesList.class);
        logger.info("######################GEOSERVER_FEATURE_TYPES_AVAIALABLE_FROM_STRING : {}\n", featureTypesList);
    }

    @Test
    public void c_unmarshallGeoserverEmptyFeatureTypesAvaialableFromStringTest() throws Exception {
        GPGeoserverFeatureTypesList featureTypesList = jacksonSupport.getDefaultMapper()
                .readValue(new StringReader("{  \n" +
                        "   \"list\":{  \n" +
                        "      \"string\":[  \n" +
                        "\n" +
                        "      ]\n" +
                        "   }\n" +
                        "}"), GPGeoserverFeatureTypesList.class);
        logger.info("######################GEOSERVER_FEATURE_TYPES_AVAIALABLE_FROM_STRING : {}\n", featureTypesList);
    }
}