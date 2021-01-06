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