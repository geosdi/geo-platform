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

import org.geosdi.geoplatform.connector.wfs.response.AttributeDTO;
import org.geosdi.geoplatform.connector.wfs.response.FeatureCollectionDTO;
import org.geosdi.geoplatform.connector.wfs.response.FeatureDTO;
import org.geosdi.geoplatform.connector.wfs.response.LayerSchemaDTO;
import org.geosdi.geoplatform.gui.shared.bean.BBox;
import org.junit.Assert;
import org.junit.Test;

import javax.xml.namespace.QName;
import java.util.Arrays;
import java.util.Map;

import static java.util.Collections.EMPTY_MAP;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * @author Vincenzo Monteverde <vincenzo.monteverde@geosdi.org>
 */
public class WFSTransactionUpdateWSTest extends WFSAbstractTest {

    private final static QName TOPP_STATES = new QName("http://www.openplans.org/topp", "topp:states");

    @Test
    public void multiUpdate() throws Exception {
        String typeName = TOPP_STATES.getLocalPart();
        LayerSchemaDTO describeFeatureType = wfsService.describeFeatureType(addressDatastore, typeName, EMPTY_MAP);
        AttributeDTO att1 = null, att2 = null;
        for (AttributeDTO attribute : describeFeatureType.getAttributes()) {
            if ("LAND_KM".equals(attribute.getName())) {
                att1 = attribute;
            } else if ("WATER_KM".equals(attribute.getName())) {
                att2 = attribute;
            }
        }
        assertNotNull(att1);
        assertNotNull(att2);

        BBox bbox = new BBox(-75.102613, 40.212597, -75.361859, 40.512517);
        FeatureCollectionDTO featureCollection = wfsService.getFeatureByBBoxDirect(addressDatastore, typeName, bbox, EMPTY_MAP);
        assertEquals(1, featureCollection.getNumberOfFeatures());
        assertNotNull(featureCollection.getFeatures());

        FeatureDTO feature = featureCollection.getFeatures().get(0);
        assertNotNull(feature);
        assertNotNull(feature.getFID());

        Map<String, String> attributesMap = feature.getAttributes().getAttributesMap();
        String val1 = attributesMap.get(att1.getName());
        double d1 = Double.valueOf(val1) + 0.001;
        att1.setValue(Double.toString(d1));
        String val2 = attributesMap.get(att2.getName());
        double d2 = Double.valueOf(val2) + 0.001;
        att2.setValue(Double.toString(d2));
        logger.info("\n\n*** val1: from {} to {}\n*** val2: from {} to {}\n\n",
                val1, att1.getValue(), val2, att2.getValue());

        boolean updated = wfsService.transactionUpdate(addressDatastore, typeName, feature.getFID(),
                Arrays.asList(att1, att2), EMPTY_MAP);
        Assert.assertTrue(updated);

        feature = wfsService.getFeatureByFIDDirect(addressDatastore, typeName, feature.getFID(), EMPTY_MAP);
        assertNotNull(feature);
        assertNotNull(feature.getFID());
        attributesMap = feature.getAttributes().getAttributesMap();
        val1 = attributesMap.get(att1.getName());
        assertEquals(Double.toString(d1), val1);
        val2 = attributesMap.get(att2.getName());
        assertEquals(Double.toString(d2), val2);
    }
}
