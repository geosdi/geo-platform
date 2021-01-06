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
package org.geosdi.geoplatform.support.jackson.jts;

import org.geojson.Feature;
import org.geojson.FeatureCollection;
import org.geosdi.geoplatform.support.jackson.GPJacksonSupport;
import org.geosdi.geoplatform.support.jackson.JacksonSupport;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.geom.GeometryCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

import static org.geosdi.geoplatform.support.jackson.property.GPJacksonSupportEnum.*;
import static org.geosdi.geoplatform.support.jackson.property.GPJsonIncludeFeature.NON_NULL;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@FixMethodOrder(value = MethodSorters.NAME_ASCENDING)
public class GPFeatureCollectionReaderTest {

    private static final Logger logger = LoggerFactory.getLogger(GPFeatureCollectionReaderTest.class);
    //
    private static final GPJacksonSupport JTS_JACKSON_SUPPORT = new GPJacksonJTSSupport();
    private static final JacksonSupport JACKSON_SUPPORT = new GPJacksonSupport(UNWRAP_ROOT_VALUE_DISABLE,
            FAIL_ON_UNKNOW_PROPERTIES_DISABLE,
            ACCEPT_SINGLE_VALUE_AS_ARRAY_ENABLE,
            WRAP_ROOT_VALUE_DISABLE,
            INDENT_OUTPUT_ENABLE, NON_NULL);

    @Test
    public void a_readFeatureCollectionTest() throws Exception {
        GeometryCollection geometryCollection = JTS_JACKSON_SUPPORT.getDefaultMapper()
                .readValue(new File("./src/test/resources/geojson/FeatureCollection.json"), GeometryCollection.class);
        JTS_JACKSON_SUPPORT.getDefaultMapper().writeValue(new File("./target/GeometryCollection.json"), geometryCollection);
    }

    @Test
    public void b_parseFeatureTest() throws Exception {
        FeatureCollection featureCollection = JACKSON_SUPPORT.getDefaultMapper()
                .readValue(new File("./src/test/resources/geojson/FeatureCollection.json"), FeatureCollection.class);
        for (Feature feature : featureCollection.getFeatures()) {
            String featureAsString = JACKSON_SUPPORT.getDefaultMapper().writeValueAsString(feature);
            logger.info("##########################JTS_GEOMETRY : {}\n", JTS_JACKSON_SUPPORT
                    .getDefaultMapper().readValue(featureAsString, Geometry.class));
        }
    }
}
