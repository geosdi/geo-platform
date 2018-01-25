/**
 *
 *    geo-platform
 *    Rich webgis framework
 *    http://geo-platform.org
 *   ====================================================================
 *
 *   Copyright (C) 2008-2018 geoSDI Group (CNR IMAA - Potenza - ITALY).
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
package org.geosdi.geoplatform.wfs.reader;

import org.geosdi.geoplatform.connector.wfs.response.FeatureCollectionDTO;
import org.geosdi.geoplatform.connector.wfs.response.FeatureDTO;
import org.geosdi.geoplatform.connector.wfs.response.LayerSchemaDTO;
import org.geosdi.geoplatform.jaxb.GPJAXBContextBuilder;
import org.geosdi.geoplatform.support.wfs.feature.reader.WFSGetFeatureStaxReader;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.nio.file.Paths;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class WFSGetFeatureToppStatesStaxReaderTest {

    private static final Logger logger = LoggerFactory.getLogger(WFSGetFeatureToppStatesStaxReaderTest.class);
    //
    private static LayerSchemaDTO toppStatesLayerSchema;
    private static GPJAXBContextBuilder jaxbContextBuilder = GPJAXBContextBuilder.newInstance();
    private static File getFeatureToppStates;

    @BeforeClass
    public static void beforeClass() throws Exception {
        toppStatesLayerSchema = jaxbContextBuilder.unmarshal(new File("./src/test/resources/reader/LayerSchemaToppStates.xml"),
                LayerSchemaDTO.class);
        getFeatureToppStates = Paths.get("./src/test/resources/reader/GetFeatureToppStates.xml").toFile();
        Assert.assertNotNull("The LayerSchemaDTO for topp:states must not be null.", toppStatesLayerSchema);
        Assert.assertNotNull("The File getFeatureToppStates must not be null.", getFeatureToppStates);
    }

    @Test
    public void toppStatesStaxReaderTest() throws Exception {
        WFSGetFeatureStaxReader featureReaderStAX = new WFSGetFeatureStaxReader(toppStatesLayerSchema);
        FeatureCollectionDTO featureCollectionDTO = featureReaderStAX.read(getFeatureToppStates);
        for (FeatureDTO featureDTO : featureCollectionDTO.getFeatures()) {
            logger.info("###############################FEATURE : {}\n", featureDTO);
        }
    }
}
