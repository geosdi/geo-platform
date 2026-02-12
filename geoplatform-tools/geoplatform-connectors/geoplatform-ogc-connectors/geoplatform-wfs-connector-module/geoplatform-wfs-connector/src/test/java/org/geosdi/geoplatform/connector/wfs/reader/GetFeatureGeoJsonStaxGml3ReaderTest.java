/**
 *
 * geo-platform
 * Rich webgis framework
 * http://geo-platform.org
 * ====================================================================
 * <p>
 * Copyright (C) 2008-2025 geoSDI Group (CNR IMAA - Potenza - ITALY).
 * <p>
 * This program is free software: you can redistribute it and/or modify it
 * under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version. This program is distributed in the
 * hope that it will be useful, but WITHOUT ANY WARRANTY; without
 * even the implied warranty of MERCHANTABILITY or FITNESS FOR
 * A PARTICULAR PURPOSE. See the GNU General Public License
 * for more details. You should have received a copy of the GNU General
 * Public License along with this program. If not, see http://www.gnu.org/licenses/
 * <p>
 * ====================================================================
 * <p>
 * Linking this library statically or dynamically with other modules is
 * making a combined work based on this library. Thus, the terms and
 * conditions of the GNU General Public License cover the whole combination.
 * <p>
 * As a special exception, the copyright holders of this library give you permission
 * to link this library with independent modules to produce an executable, regardless
 * of the license terms of these independent modules, and to copy and distribute
 * the resulting executable under terms of your choice, provided that you also meet,
 * for each linked independent module, the terms and conditions of the license of
 * that module. An independent module is a module which is not derived from or
 * based on this library. If you modify this library, you may extend this exception
 * to your version of the library, but you are not obligated to do so. If you do not
 * wish to do so, delete this exception statement from your version.
 */
package org.geosdi.geoplatform.connector.wfs.reader;

import org.geosdi.geoplatform.connector.reader.stax.GetFeatureGeoJsonStaxGml3Reader;
import org.geosdi.geoplatform.support.jackson.JacksonSupport;
import org.junit.BeforeClass;
import org.junit.experimental.theories.DataPoints;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tools.jackson.databind.json.JsonMapper;

import javax.annotation.Nonnull;
import java.io.File;
import java.io.FileInputStream;

import static com.google.common.base.Preconditions.checkArgument;
import static java.io.File.separator;
import static java.lang.Boolean.FALSE;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Stream.of;
import static javax.annotation.meta.When.NEVER;
import static org.geosdi.geoplatform.support.jackson.builder.JacksonSupportBuilder.GPJacksonSupportBuilder.builder;
import static org.geosdi.geoplatform.support.jackson.property.GPJacksonSupportEnum.*;
import static org.geosdi.geoplatform.support.jackson.property.GPJsonIncludeFeature.NON_NULL;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@RunWith(Theories.class)
public class GetFeatureGeoJsonStaxGml3ReaderTest {

    private static final Logger logger = LoggerFactory.getLogger(GetFeatureGeoJsonStaxGml3ReaderTest.class);
    //
    private static final JacksonSupport<JsonMapper> JACKSON_SUPPORT =  builder(FALSE)
            .configure(UNWRAP_ROOT_VALUE_DISABLE,
                    FAIL_ON_UNKNOW_PROPERTIES_DISABLE,
                    ACCEPT_SINGLE_VALUE_AS_ARRAY_ENABLE,
                    WRAP_ROOT_VALUE_DISABLE,
                    INDENT_OUTPUT_ENABLE, NON_NULL)
            .build();
    private static final GetFeatureGeoJsonStaxGml3Reader getFeatureGeoJsonStaxGml3Reader = new GetFeatureGeoJsonStaxGml3Reader();
    private static String dirFiles;

    /**
     * @throws Exception
     */
    @BeforeClass
    public static void loadFile() throws Exception {
        dirFiles = of(new File(".").getCanonicalPath(), "src", "test", "resources", "unmarshall", "stax")
                .collect(joining(separator, "", separator));
    }

    /**
     * @return {@link String[]}
     */
    @DataPoints
    public static String[] data() {
        return new String[] {"extra_rt_webgis_strade_comunali.xml", "extra_rt_webgis:Cippi_SR_SP_ettometriche_v1.xml",
                "RTWebGIS:SINS_4069_4365_pr_lu.xml", "RTWebGIS:SINS_4068_1454677_punti_tappa_francigena.xml",
                "RTWebGIS:SINS_4068_9363_totem_asl7.xml", "extra_rt_webgis:Cippi_km_v2_4.xml",
                "extra_rt_webgis:strade_private.xml"};
    }

    /**
     * @param fileName
     * @throws Exception
     */
    @Theory
    public void readFeatureCollectionWithStaxTest(@Nonnull(when = NEVER) String fileName) throws Exception {
        checkArgument((fileName != null) && !(fileName.trim().isEmpty()), "The Parameter fileName must not be null or an empty string.");
        File file = new File(dirFiles.concat(fileName));
        logger.info("@@@@@@@@@@@@@@@@@@@@@FEATURE_COLLECTION  : \n{}\n, for file : {}\n", JACKSON_SUPPORT.getDefaultMapper().writeValueAsString(getFeatureGeoJsonStaxGml3Reader.read(new FileInputStream(file))), fileName);
    }
}