/*
 *  geo-platform
 *  Rich webgis framework
 *  http://geo-platform.org
 * ====================================================================
 *
 * Copyright (C) 2008-2013 geoSDI Group (CNR IMAA - Potenza - ITALY).
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
import org.junit.Test;

/**
 *
 * @author Vincenzo Monteverde <vincenzo.monteverde@geosdi.org>
 */
public class WFSTransactionUpdateTest extends WFSTestConfigurator {

    private final static QName POLY_LANDMARKS = new QName(
            "http://www.census.gov", "tiger:poly_landmarks");
    //
    private final static QName FIRESAT = new QName(
            "http://www.openplans.org/spearfish", "sf:firesat");

    @Test
    public void polyLandmarks() throws Exception {
        WFSTransactionRequest<TransactionResponseType> request =
                super.serverConnector.createTransactionRequest();

        request.setOperation(TransactionOperation.UPDATE);

        QName name = new QName(POLY_LANDMARKS.getLocalPart());
        request.setTypeName(name);

        request.setFID("poly_landmarks.1");

        AttributeDTO att = new AttributeDTO();
        att.setMaxOccurs(1);
        att.setMinOccurs(0);
        att.setName("LANAME");
        att.setNillable(true);
        att.setType("string");
        att.setValue("Washington Square Park MOD");
        request.setAttributes(Arrays.asList(att));

        // We must avoid transactions on the server,
        // but show only the requests generated

        logger.info("HERE THE REQUEST ########################## \n {}",
                request.showRequestAsString());

//        TransactionResponseType response = request.getResponse();
//        logger.info("\n*** {}", response.getTransactionResults());
//
//        TransactionSummaryType transactionSummary = response.getTransactionSummary();
//        Assert.assertEquals(0, transactionSummary.getTotalDeleted().intValue());
//        Assert.assertEquals(0, transactionSummary.getTotalInserted().intValue());
//        Assert.assertEquals(1, transactionSummary.getTotalUpdated().intValue());
//        Assert.assertEquals("1.1.0", response.getVersion());
    }

//    @Test
//    public void testFiresat() throws Exception {
//        WFSTransactionRequest<TransactionResponseType> request =
//                super.serverConnector.createTransactionRequest();
//
//        request.setOperation(TransactionOperation.UPDATE);
//
//        QName name = new QName(FIRESAT.getLocalPart());
//        request.setTypeName(name);
//
//        request.setFID("firesat.10234");
//
//        AttributeDTO att = new AttributeDTO();
//        att.setName("AREA");
//        
//        att.setValue("33");
//        request.setAttributes(Arrays.asList(att));
//
//        // We must avoid transactions on the server,
//        // but show only the requests generated
//
//        logger.info("HERE THE REQUEST ########################## \n {}",
//                request.showRequestAsString());
//        
//        TransactionResponseType response = request.getResponse();
//        logger.info("\n*** {}", response.getTransactionResults());
//
//        TransactionSummaryType transactionSummary = response.getTransactionSummary();
//        Assert.assertEquals(0, transactionSummary.getTotalDeleted().intValue());
//        Assert.assertEquals(0, transactionSummary.getTotalInserted().intValue());
//        Assert.assertEquals(1, transactionSummary.getTotalUpdated().intValue());
//        Assert.assertEquals("1.1.0", response.getVersion());
//    }

}
