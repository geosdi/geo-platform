/*
 *  geo-platform
 *  Rich webgis framework
 *  http://geo-platform.org
 * ====================================================================
 *
 * Copyright (C) 2008-2012 geoSDI Group (CNR IMAA - Potenza - ITALY).
 *
 * This program is free software: you can redistribute it and/or modify it 
 * under the terms of the GNU General Public License as published by 
 * the Free Software Foundation, either version 3 of the License, or 
 * (at your option) any later version. This program is distributed in the 
 * hope that it will be useful, but WITHOUT ANY WARRANTY; without 
 * even the implied warranty of MERCHANTABILITY or FITNESS FOR 
 * A PARTICULAR PURPOSE. See the GNU General Public License 
 * for more details. You should have received a copy of the GNU General 
 * Public License along with this program. If not, see http://www.gnu.org/licenses/ 
 *
 * ====================================================================
 *
 * Linking this library statically or dynamically with other modules is 
 * making a combined work based on this library. Thus, the terms and 
 * conditions of the GNU General Public License cover the whole combination. 
 * 
 * As a special exception, the copyright holders of this library give you permission 
 * to link this library with independent modules to produce an executable, regardless 
 * of the license terms of these independent modules, and to copy and distribute 
 * the resulting executable under terms of your choice, provided that you also meet, 
 * for each linked independent module, the terms and conditions of the license of 
 * that module. An independent module is a module which is not derived from or 
 * based on this library. If you modify this library, you may extend this exception 
 * to your version of the library, but you are not obligated to do so. If you do not 
 * wish to do so, delete this exception statement from your version. 
 *
 */
package org.geosdi.geoplatform.ws.wfs;

import java.util.List;
import junit.framework.Assert;
import org.geosdi.geoplatform.exception.ResourceNotFoundFault;
import org.geosdi.geoplatform.gui.responce.AttributeDTO;
import org.geosdi.geoplatform.gui.responce.LayerSchemaDTO;
import org.junit.Test;

/**
 *
 * @author Vincenzo Monteverde <vincenzo.monteverde@geosdi.org>
 */
public class WFSDescribeFeatureTypeWSTest extends WFSAbstractTest {

    @Test
    public void singleFeatureV110() throws ResourceNotFoundFault {
        Assert.assertNotNull(super.wfsService);

        LayerSchemaDTO layerSchema =
                wfsService.describeFeatureType("http://localhost:8989/geoserver/wfs", "topp:states");
        logger.info("\n\n\n@@@ {}", layerSchema);

        Assert.assertNotNull(layerSchema);
        Assert.assertEquals("topp:states", layerSchema.getTypeName());
        Assert.assertEquals("http://www.openplans.org/topp", layerSchema.getTargetNamespace());

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
        }
    }
}
