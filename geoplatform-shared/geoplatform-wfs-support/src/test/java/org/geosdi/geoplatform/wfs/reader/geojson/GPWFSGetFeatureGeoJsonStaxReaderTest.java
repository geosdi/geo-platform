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
package org.geosdi.geoplatform.wfs.reader.geojson;

import org.geojson.FeatureCollection;
import org.geosdi.geoplatform.connector.reader.stax.GetFeatureGeoJsonStaxGml3Reader;
import org.geosdi.geoplatform.support.jackson.GPJacksonSupport;
import org.geosdi.geoplatform.support.jackson.JacksonSupport;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.Map;

import static java.io.File.separator;
import static java.lang.Runtime.getRuntime;
import static java.lang.System.currentTimeMillis;
import static java.util.function.Function.identity;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toMap;
import static java.util.stream.Stream.of;
import static org.geosdi.geoplatform.support.jackson.property.GPJacksonSupportEnum.*;
import static org.geosdi.geoplatform.support.jackson.property.GPJsonIncludeFeature.NON_NULL;
import static org.junit.runners.MethodSorters.NAME_ASCENDING;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@FixMethodOrder(value = NAME_ASCENDING)
public class GPWFSGetFeatureGeoJsonStaxReaderTest {

    private static final Logger logger = LoggerFactory.getLogger(GPWFSGetFeatureGeoJsonStaxReaderTest.class);
    //
    private static final GetFeatureGeoJsonStaxGml3Reader WFS_GET_FEATURE_GEO_JSON_STAX_READER = new GetFeatureGeoJsonStaxGml3Reader();
    private static Map<String, File> repoFiles;
    private static final JacksonSupport JACKSON_SUPPORT = new GPJacksonSupport(UNWRAP_ROOT_VALUE_DISABLE,
            FAIL_ON_UNKNOW_PROPERTIES_DISABLE,
            ACCEPT_SINGLE_VALUE_AS_ARRAY_ENABLE,
            WRAP_ROOT_VALUE_DISABLE,
            INDENT_OUTPUT_ENABLE, NON_NULL);
    static final long startTime = currentTimeMillis();

    @BeforeClass
    public static void beforeClass() throws Exception {
        String basePath = of(new File(".").getCanonicalPath(), "src", "test", "resources", "reader")
                .collect(joining(separator, "", separator));
        repoFiles = of("GetFeaturePeUins.xml", "GetFeatureSFRestricted.xml", "elem_ridotto_2.xml", "StradeStataliSS.xml",
                "Aviafrancigena_escursionismo.xml", "postazioni_traffico.xml", "strade_provinciali.xml",
                "StradePrivate.xml", "cen_abi_a_polygon.xml")
                .collect(toMap(identity(), v -> new File(basePath.concat(v))));
    }

    @AfterClass
    public static void afterClass() throws Exception {
        getRuntime().addShutdownHook(new Thread(() -> {
            logger.info("@@@@@@@@@@@@@@@@@@@@@@@@@ Shutdown Hook is running !");
            final long endTime = currentTimeMillis();
            logger.info("#################### Calculated in : {}", (double) (endTime - startTime) / 1000 + " seconds" );
        }));
    }

    @Test
    public void a_getFeatureGeoJsonStaxReaderTest() throws Exception {
        FeatureCollection featureCollection = WFS_GET_FEATURE_GEO_JSON_STAX_READER.read(repoFiles.get("GetFeaturePeUins.xml"));
        JACKSON_SUPPORT.getDefaultMapper().writeValue(new File(of(new File(".").getCanonicalPath(), "target", "GetFeaturePeUins.json")
                .collect(joining(separator, "", separator))), featureCollection);
    }

    @Test
    public void b_getFeatureGeoJsonStaxReaderTest() throws Exception {
        FeatureCollection featureCollection = WFS_GET_FEATURE_GEO_JSON_STAX_READER.read(repoFiles.get("StradeStataliSS.xml"));
        JACKSON_SUPPORT.getDefaultMapper().writeValue(new File(of(new File(".").getCanonicalPath(), "target", "StadeStataliSS.json")
                        .collect(joining(separator, "", separator))), featureCollection);
    }

    @Test
    public void c_getFeatureGeoJsonStaxReaderTest() throws Exception {
        FeatureCollection featureCollection = WFS_GET_FEATURE_GEO_JSON_STAX_READER.read(repoFiles.get("GetFeatureSFRestricted.xml"));
        JACKSON_SUPPORT.getDefaultMapper().writeValue(new File(of(new File(".").getCanonicalPath(), "target", "GetFeatureSFRestricted.json")
                .collect(joining(separator, "", separator))), featureCollection);
    }

    @Test
    public void d_getFeatureGeoJsonStaxReaderTest() throws Exception {
        FeatureCollection featureCollection = WFS_GET_FEATURE_GEO_JSON_STAX_READER.read(repoFiles.get("elem_ridotto_2.xml"));
        JACKSON_SUPPORT.getDefaultMapper().writeValue(new File(of(new File(".").getCanonicalPath(), "target", "elem_ridotto_2.json")
                .collect(joining(separator, "", separator))), featureCollection);
    }

    @Test
    public void e_getFeatureGeoJsonStaxReaderTest() throws Exception {
        FeatureCollection featureCollection = WFS_GET_FEATURE_GEO_JSON_STAX_READER.read(repoFiles.get("Aviafrancigena_escursionismo.xml"));
        JACKSON_SUPPORT.getDefaultMapper().writeValue(new File(of(new File(".").getCanonicalPath(), "target", "Aviafrancigena_escursionismo.json")
                .collect(joining(separator, "", separator))), featureCollection);
    }

    @Test
    public void f_getFeatureGeoJsonStaxReaderTest() throws Exception {
        FeatureCollection featureCollection = WFS_GET_FEATURE_GEO_JSON_STAX_READER.read(repoFiles.get("postazioni_traffico.xml"));
        JACKSON_SUPPORT.getDefaultMapper().writeValue(new File(of(new File(".").getCanonicalPath(), "target", "postazioni_traffico.json")
                .collect(joining(separator, "", separator))), featureCollection);
    }

    @Test
    public void g_getFeatureGeoJsonStaxReaderTest() throws Exception {
        FeatureCollection featureCollection = WFS_GET_FEATURE_GEO_JSON_STAX_READER.read(repoFiles.get("strade_provinciali.xml"));
        JACKSON_SUPPORT.getDefaultMapper().writeValue(new File(of(new File(".").getCanonicalPath(), "target", "strade_provinciali.json")
                .collect(joining(separator, "", separator))), featureCollection);
    }

    @Test
    public void h_getFeatureGeoJsonStaxReaderTest() throws Exception {
        FeatureCollection featureCollection = WFS_GET_FEATURE_GEO_JSON_STAX_READER.read(repoFiles.get("StradePrivate.xml"));
        JACKSON_SUPPORT.getDefaultMapper().writeValue(new File(of(new File(".").getCanonicalPath(), "target", "StradePrivate.json")
                .collect(joining(separator, "", separator))), featureCollection);
    }

    @Test
    public void i_getFeatureGeoJsonStaxReaderTest() throws Exception {
        FeatureCollection featureCollection = WFS_GET_FEATURE_GEO_JSON_STAX_READER.read(repoFiles.get("cen_abi_a_polygon.xml"));
        JACKSON_SUPPORT.getDefaultMapper().writeValue(new File(of(new File(".").getCanonicalPath(), "target", "cen_abi_a_polygon.json")
                .collect(joining(separator, "", separator))), featureCollection);
    }
}