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
package org.geosdi.geoplatform.connector.wfs;

import java.util.Arrays;
import javax.xml.namespace.QName;
import org.geosdi.geoplatform.connector.server.request.WFSTransactionRequest;
import org.geosdi.geoplatform.gui.responce.AttributeDTO;
import org.geosdi.geoplatform.gui.shared.wfs.TransactionOperation;
import org.geosdi.geoplatform.xml.wfs.v110.TransactionResponseType;
import org.geosdi.geoplatform.xml.wfs.v110.TransactionSummaryType;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

/**
 *
 * @author Vincenzo Monteverde <vincenzo.monteverde@geosdi.org>
 */
//@Category(WFSTest.class)
public class WFSTransactionInsertTest extends WFSTestConfigurator {

    private final static QName TASMANIA_ROADS = new QName("http://www.openplans.org/topp",
                                                          "topp:tasmania_roads");

    @Test
    @Ignore
    public void tasmaniaRoads() throws Exception {
        WFSTransactionRequest<TransactionResponseType> request =
                super.serverConnector.createTransactionRequest();

        request.setOperation(TransactionOperation.INSERT);

        QName name = new QName(TASMANIA_ROADS.getLocalPart());
        request.setTypeName(name);

        AttributeDTO att = new AttributeDTO();
//        att.setMaxOccurs(1);
//        att.setMinOccurs(0);
//        att.setNillable(true);
//        att.setType("string");
        att.setName("TYPE");
        att.setValue("NEW attribute value TYPE");

        // TODO Geometry attribute

        request.setAttributes(Arrays.asList(att));

        TransactionResponseType response = request.getResponse();
        logger.info("\n*** {}", response.getTransactionResults());

        TransactionSummaryType transactionSummary = response.getTransactionSummary();
        Assert.assertEquals(0, transactionSummary.getTotalDeleted().intValue());
        Assert.assertEquals(0, transactionSummary.getTotalUpdated().intValue());
        Assert.assertEquals(1, transactionSummary.getTotalInserted().intValue());
        Assert.assertEquals("1.1.0", response.getVersion());
    }
}
