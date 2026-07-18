/*
 *
 *    geo-platform
 *    Rich webgis framework
 *    http://geo-platform.org
 *   ====================================================================
 *
 *   Copyright (C) 2008-2026 geoSDI Group (CNR IMAA - Potenza - ITALY).
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
package org.geosdi.geoplatform.connector.wfs;

import org.geosdi.geoplatform.connector.server.request.WFSTransactionRequest;
import org.geosdi.geoplatform.connector.wfs.response.AttributeDTO;
import org.geosdi.geoplatform.connector.wfs.response.GeometryAttributeDTO;
import org.geosdi.geoplatform.gui.shared.wfs.TransactionOperation;
import org.geosdi.geoplatform.xml.wfs.v110.TransactionResponseType;
import org.junit.Test;

import javax.xml.namespace.QName;
import java.util.Arrays;

import static org.junit.Assert.assertTrue;

/**
 * @author Vincenzo Monteverde <vincenzo.monteverde@geosdi.org>
 */
public class WFSTransactionInsertTest extends WFSTestConfigurator {

    private final static QName TASMANIA_ROADS = new QName("http://www.openplans.org/topp",
            "topp:tasmania_roads", "topp");

    @Test
    public void tasmaniaRoads() throws Exception {
        WFSTransactionRequest<TransactionResponseType> request = serverConnector.createTransactionRequest();
        request.withOperation(TransactionOperation.INSERT);
        request.withTypeName(TASMANIA_ROADS);
        AttributeDTO att = new AttributeDTO();
        att.setName("TYPE");
        att.setValue("NEW attribute");
        // TODO Geometry attribute
        GeometryAttributeDTO geometry = new GeometryAttributeDTO();
        geometry.setName("the_geom");
        geometry.setValue("MULTILINESTRING ((10 10, 20 20, 10 40), "
                + "(40 40, 30 30, 40 20, 30 10))");
        request.withAttributes(Arrays.asList(att, geometry));
        String requestAsString = request.showRequestAsString();
        logger.info("\n*** Request TRANSACTION INSERT ***\n{}\n\n", requestAsString);
        // Non-destructive verification : assert the INSERT transaction is serialized correctly from the
        // per-thread ThreadLocal state, without mutating the live GeoServer.
        assertTrue("must serialize a wfs:Insert element", requestAsString.contains("<wfs:Insert"));
        assertTrue("must carry the target typeName", requestAsString.contains("tasmania_roads"));
        assertTrue("must carry the TYPE attribute value", requestAsString.contains("NEW attribute"));
        assertTrue("must carry the geometry attribute", requestAsString.contains("the_geom"));
    }
}
