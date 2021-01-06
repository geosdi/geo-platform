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
package org.geosdi.geoplatform.feature.reader;

import org.geosdi.geoplatform.connector.wfs.response.AttributeDTO;
import org.geosdi.geoplatform.connector.wfs.response.FeatureCollectionDTO;
import org.geosdi.geoplatform.connector.wfs.response.FeatureDTO;
import org.geosdi.geoplatform.connector.wfs.response.LayerSchemaDTO;
import org.geosdi.geoplatform.support.wfs.feature.reader.FeatureSchemaReader;
import org.geosdi.geoplatform.support.wfs.feature.reader.GPFeatureSchemaReader;
import org.geosdi.geoplatform.support.wfs.feature.reader.WFSGetFeatureStaxReader;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * @author Giuseppe La Scaleia <giuseppe.lascaleia@geosdi.org>
 * @author Vincenzo Monteverde <vincenzo.monteverde@geosdi.org>
 */
@RunWith(Parameterized.class)
public class FeatureReaderTest {

    private final static Logger logger = LoggerFactory.getLogger(FeatureReaderTest.class);
    private static String pathFile;
    private final FeatureSchemaReader featureReaderXSD = new GPFeatureSchemaReader();
    //
    private final String fileDFT, fileGF;
    private final int numAttributes, numFeatures;

    static {
        try {
            pathFile = new File(".").getCanonicalPath() + File.separator + "src/test/resources/";
        } catch (IOException ex) {
            logger.error("\n### Error: {}", ex.getMessage());
        }
    }

    public FeatureReaderTest(String file, int numAttributes, int numFeatures) {
        logger.trace("\n@@@ Test '{}' file (#attributes = {} - #features = {}) @@@", file, numAttributes, numFeatures);

        this.fileGF = pathFile + file + "-GetFeature.xml";

        // DescribeFeatureType is the same for all GetFeature request
        int i = file.indexOf("-");
        if (i != -1) {
            file = file.substring(0, i);
        }
        this.fileDFT = pathFile + file + "-DescribeFeatureType.xml";

        this.numAttributes = numAttributes;
        this.numFeatures = numFeatures;
        logger.info("#######################ECCOLO : {}\n", this.fileGF);
    }

    @Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{{"restricted", 1, 4}, {"restricted-null", 1, 4},
                {"states", 22, 49}, {"giant_polygon", 0, 1}, {"tiger_roads", 2, 1000}});
    }

    @Test
    public void testFeature() throws Exception {
        FileInputStream ff = null;
        try {
            File dftFile = new File(fileDFT);
            ff = new FileInputStream(dftFile);

            List<LayerSchemaDTO> schemas = featureReaderXSD.read(ff);
            Assert.assertNotNull(schemas);
            Assert.assertEquals(1, schemas.size());

            LayerSchemaDTO layerSchema = schemas.get(0);
            Assert.assertNotNull(layerSchema.getTypeName());
            String name = layerSchema.getTypeName().substring(layerSchema.getTypeName().indexOf(":") + 1);
            Assert.assertNotNull(layerSchema.getTargetNamespace());
            Assert.assertNotNull(layerSchema.getGeometry());
            List<AttributeDTO> attributes = layerSchema.getAttributes();
            Assert.assertNotNull(attributes);
            Assert.assertEquals(numAttributes, attributes.size());
            for (AttributeDTO att : attributes) {
                Assert.assertTrue(att.getMinOccurs() >= 0);
                Assert.assertTrue(att.getMaxOccurs() > att.getMinOccurs());
                Assert.assertNotNull(att.getName());
                Assert.assertNotNull(att.getType());
            }
            logger.debug("@@@@@@@@@@@@@@@@@@@@LAYER_SCHEMA : {}", layerSchema);

            WFSGetFeatureStaxReader featureReader = new WFSGetFeatureStaxReader(layerSchema);
            FeatureCollectionDTO fc = featureReader.read(new File(fileGF));
            Assert.assertNotNull(fc);
            Assert.assertNotNull(fc.getTimeStamp());
            Assert.assertEquals(numFeatures, fc.getNumberOfFeatures());
            List<FeatureDTO> features = fc.getFeatures();
            Assert.assertNotNull(features);
            Assert.assertEquals(numFeatures, features.size());
            for (FeatureDTO feature : features) {
                Assert.assertNotNull(feature.getFID());
                Assert.assertTrue(feature.getFID().contains(name));
                Assert.assertNotNull(feature.getGeometry());
                if (numAttributes == 0) {
                    Assert.assertTrue(feature.getAttributes().getAttributesMap().isEmpty());
                } else {
                    Assert.assertNotNull(feature.getAttributes());
                    Map<String, String> fMap = feature.getAttributes().getAttributesMap();
                    Assert.assertNotNull(fMap);
                    logger.debug("#################FMAP_SIZE : {}\n", fMap.size());
                    Assert.assertEquals(numAttributes, fMap.size());
                }
                logger.debug("@@@@@@@@@@@@@@@@@@@@ {} - {}", feature.getFID(), feature.getAttributes());
                logger.debug("GEOMETRY @@@@@@@@@@@@@@@@ : {}", feature.getGeometry());
            }

        } finally {
            if (ff != null) {
                ff.close();
            }
        }
    }

}
