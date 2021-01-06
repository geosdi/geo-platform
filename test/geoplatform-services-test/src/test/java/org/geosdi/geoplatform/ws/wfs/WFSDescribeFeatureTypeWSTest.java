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

import org.apache.cxf.binding.soap.SoapFault;
import org.geosdi.geoplatform.connector.wfs.response.AttributeDTO;
import org.geosdi.geoplatform.connector.wfs.response.LayerSchemaDTO;
import org.junit.Assert;
import org.junit.Test;

import javax.xml.namespace.QName;
import java.util.List;

import static java.util.Collections.EMPTY_MAP;

/**
 * @author Vincenzo Monteverde <vincenzo.monteverde@geosdi.org>
 */
public class WFSDescribeFeatureTypeWSTest extends WFSAbstractTest {

    private final static QName TOPP_STATES = new QName(
            "http://www.openplans.org/topp", "topp:states");
    private final static QName SF_COMUNI = new QName(
            "http://www.openplans.org/spearfish", "sf:comuni2001");

    @Test
    public void statesFeatureV110() throws Exception {
        LayerSchemaDTO layerSchema = wfsService.describeFeatureType(addressDatastore,
                TOPP_STATES.getLocalPart(), EMPTY_MAP);
        logger.info("\n\n\n@@@ {}", layerSchema);

        Assert.assertNotNull(layerSchema);
        Assert.assertEquals(TOPP_STATES.getLocalPart(),
                layerSchema.getTypeName());
        Assert.assertEquals(TOPP_STATES.getNamespaceURI(),
                layerSchema.getTargetNamespace());
        Assert.assertEquals(addressDatastore, layerSchema.getScope());

        AttributeDTO geometry = layerSchema.getGeometry();
        Assert.assertNotNull(geometry);
        Assert.assertEquals("the_geom", geometry.getName());
        Assert.assertEquals("MultiPolygon", geometry.getType());

        List<AttributeDTO> attributes = layerSchema.getAttributes();
        Assert.assertNotNull(attributes);
        Assert.assertEquals(22, attributes.size());
        for (AttributeDTO attribute : attributes) {
            Assert.assertNotNull(attribute);
            Assert.assertNotNull(attribute.getName());
            Assert.assertNotNull(attribute.getType());
            Assert.assertTrue(attribute.isNillable());
            Assert.assertEquals(0, attribute.getMinOccurs());
            Assert.assertEquals(1, attribute.getMaxOccurs());
        }
    }

    @Test(expected = SoapFault.class)
    public void comuniFeatureV110() throws Exception {
        LayerSchemaDTO layerSchema = wfsService.describeFeatureType(addressDatastore, SF_COMUNI.getLocalPart(), EMPTY_MAP);
        logger.info("\n\n\n@@@ {}", layerSchema);

        Assert.assertNotNull(layerSchema);
        Assert.assertEquals(SF_COMUNI.getLocalPart(), layerSchema.getTypeName());
        Assert.assertEquals(SF_COMUNI.getNamespaceURI(),
                layerSchema.getTargetNamespace());
        Assert.assertEquals(addressDatastore, layerSchema.getScope());

        AttributeDTO geometry = layerSchema.getGeometry();
        Assert.assertNotNull(geometry);
        Assert.assertEquals("the_geom", geometry.getName());
        Assert.assertEquals("MultiPolygon", geometry.getType());

        List<AttributeDTO> attributes = layerSchema.getAttributes();
        Assert.assertNotNull(attributes);
        Assert.assertEquals(10, attributes.size());
        for (AttributeDTO attribute : attributes) {
            Assert.assertNotNull(attribute);
            Assert.assertNotNull(attribute.getName());
            Assert.assertNotNull(attribute.getType());
        }
    }

    @Test
    public void incorrectFeatureV110() {
        LayerSchemaDTO layerSchema = null;
        try {
            layerSchema = wfsService.describeFeatureType(addressDatastore, "sf:sfdem", EMPTY_MAP);
        } catch (Exception ex) {
            logger.error("###############Exception ############### {}",
                    ex.getMessage());
        }

        Assert.assertNull(layerSchema);
    }

    @Test
    public void absentFeatureV110() {
        LayerSchemaDTO layerSchema = null;
        try {
            layerSchema = wfsService.describeFeatureType(addressDatastore, "none:foo", EMPTY_MAP);
        } catch (Exception ex) {
            logger.error("###############Exception ############### {}",
                    ex.getMessage());
        }
        Assert.assertNull(layerSchema);
    }

    @Test(expected = SoapFault.class)
    public void errorFeatureV110() throws Exception {
        LayerSchemaDTO layerSchema = // typeName must contain the char ":"
                wfsService.describeFeatureType(addressDatastore, "error", EMPTY_MAP);
    }
}
