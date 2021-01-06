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
package org.geosdi.geoplatform.feature.transaction;

import org.geosdi.geoplatform.connector.GPWFSConnectorStore;
import org.geosdi.geoplatform.connector.WFSConnectorBuilder;
import org.geosdi.geoplatform.connector.server.request.WFSTransactionRequest;
import org.geosdi.geoplatform.connector.wfs.response.AttributeDTO;
import org.geosdi.geoplatform.connector.wfs.response.GeometryAttributeDTO;
import org.geosdi.geoplatform.gui.shared.wfs.TransactionOperation;
import org.geosdi.geoplatform.xml.wfs.v110.TransactionResponseType;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.namespace.QName;
import java.net.URL;
import java.util.Arrays;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class WFSTransactionInsertTest {

    private static final Logger logger = LoggerFactory.getLogger(WFSTransactionInsertTest.class);
    //
    private static final String wfsURL = "http://150.145.141.92/geoserver/wfs";
    private static GPWFSConnectorStore serverConnector;
    private final static QName TASMANIA_ROADS = new QName("http://www.openplans.org/topp",
            "topp:tasmania_roads", "topp");

    @BeforeClass
    public static void setUp() throws Exception {
        serverConnector = WFSConnectorBuilder
                .newConnector()
                .withServerUrl(new URL(wfsURL))
                .build();
    }

    @Test
    public void tasmaniaRoads() throws Exception {
        WFSTransactionRequest<TransactionResponseType> request = serverConnector.createTransactionRequest();

        request.setOperation(TransactionOperation.INSERT);
        request.setTypeName(TASMANIA_ROADS);

        AttributeDTO att = new AttributeDTO();
        att.setName("TYPE");
        att.setValue("NEW attribute value TYPE");

        // TODO Geometry attribute
        GeometryAttributeDTO geometry = new GeometryAttributeDTO();
        geometry.setName("the_geom");
        geometry.setValue("MULTILINESTRING ((10 10, 20 20, 10 40), "
                + "(40 40, 30 30, 40 20, 30 10))");

        request.setAttributes(Arrays.asList(att, geometry));

        logger.info("@@@@@@@@@@@@@@@@@@@@@@@@@@@Request TRANSACTION INSERT ***\n{}\n",
                request.showRequestAsString());
    }
}
