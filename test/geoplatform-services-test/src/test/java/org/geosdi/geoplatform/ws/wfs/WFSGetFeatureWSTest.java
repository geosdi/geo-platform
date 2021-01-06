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
package org.geosdi.geoplatform.ws.wfs;

import org.geosdi.geoplatform.connector.wfs.response.FeatureCollectionDTO;
import org.geosdi.geoplatform.connector.wfs.response.FeatureDTO;
import org.geosdi.geoplatform.connector.wfs.response.LayerSchemaDTO;
import org.geosdi.geoplatform.gui.shared.bean.BBox;
import org.junit.Assert;
import org.junit.Test;

import javax.xml.namespace.QName;
import java.util.List;
import java.util.Map;

import static java.util.Collections.EMPTY_MAP;

/**
 *
 * @author Vincenzo Monteverde <vincenzo.monteverde@geosdi.org>
 */
public class WFSGetFeatureWSTest extends WFSAbstractTest {

    private final static QName TOPP_STATES = new QName("http://www.openplans.org/topp", "topp:states");

    @Test
    public void statesFeatureLayerV110() throws Exception {
        String typeName = TOPP_STATES.getLocalPart();
        LayerSchemaDTO layerSchema = wfsService.describeFeatureType(addressDatastore, typeName, EMPTY_MAP);
        logger.debug("\n\n\n@@@ {}", layerSchema);
        BBox bBox = new BBox(-75.102613, 40.212597, -72.361859, 41.512517);

        FeatureCollectionDTO fc = wfsService.getFeatureByBBox(layerSchema, bBox, EMPTY_MAP);

        this.checkFeatureCollection(fc, typeName, 22, 4);
    }

    @Test
    public void statesFeatureV110() throws Exception {
        String typeName = TOPP_STATES.getLocalPart();
        BBox bBox = new BBox(-75.102613, 40.212597, -72.361859, 41.512517);

        FeatureCollectionDTO fc = wfsService.getFeatureByBBoxDirect(addressDatastore, typeName, bBox, EMPTY_MAP);

        this.checkFeatureCollection(fc, typeName, 22, 4);
    }

    @Test
    public void statesAllFeaturesV110() throws Exception {
        String typeName = TOPP_STATES.getLocalPart();
        FeatureCollectionDTO fc = wfsService.getAllFeatureDirect(addressDatastore, typeName, 10, EMPTY_MAP);
        this.checkFeatureCollection(fc, typeName, 22, 10);
    }

    private void checkFeatureCollection(FeatureCollectionDTO fc,
            String typeName, int numAttributes, int numFeatures) {
        Assert.assertNotNull(fc);

        Assert.assertNotNull(fc.getTimeStamp());
        Assert.assertEquals(numFeatures, fc.getNumberOfFeatures());

        String name = typeName.substring(typeName.indexOf(":") + 1);
        List<FeatureDTO> features = fc.getFeatures();
        Assert.assertNotNull(features);
        Assert.assertEquals(numFeatures, features.size());
        for (FeatureDTO feature : features) {
            String fID = feature.getFID();
            Assert.assertNotNull(fID);
            Assert.assertTrue(fID.startsWith(name));

            Assert.assertNotNull(feature.getGeometry());

            Assert.assertNotNull(feature.getAttributes());
            Map<String, String> fMap = feature.getAttributes().getAttributesMap();
            Assert.assertNotNull(fMap);
            Assert.assertEquals(numAttributes, fMap.size());
            for (Map.Entry<String, String> e : fMap.entrySet()) {
                String attName = e.getKey();
                String attValue = e.getValue();
                Assert.assertNotNull(attName);
                Assert.assertNotNull(attValue);
            }
        }
    }
}
