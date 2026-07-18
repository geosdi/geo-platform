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
package org.geosdi.geoplatform.connector.wfs.transaction;

import org.geosdi.geoplatform.connector.server.request.v110.transaction.stax.TransactionParameters;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.geosdi.geoplatform.connector.server.request.v110.transaction.stax.WFSTransactionParam.*;
import static org.junit.Assert.assertEquals;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class TransactionParametersTest {

    private static final Logger logger = LoggerFactory.getLogger(TransactionParametersTest.class);

    @Test
    public void a_transactionParameterTest() throws Exception {
        String param = TransactionParameters.getParam(TRANSACTION);
        logger.info("#####################TRANSANCTION_PARAM : {}\n", param);
        assertEquals("Transaction", param);
    }

    @Test
    public void b_transactionParameterTest() throws Exception {
        String param = TransactionParameters.getParam(TRANSACTION_INSERT);
        logger.info("#####################TRANSANCTION_PARAM : {}\n", param);
        assertEquals("Insert", param);
    }

    @Test
    public void c_transactionParameterTest() throws Exception {
        String param = TransactionParameters.getParam(ID_GEN);
        logger.info("#####################TRANSANCTION_PARAM : {}\n", param);
        assertEquals("idgen", param);
    }

    @Test
    public void d_transactionParameterTest() throws Exception {
        String param = TransactionParameters.getParam(INPUT_FORMAT);
        logger.info("#####################TRANSANCTION_PARAM : {}\n", param);
        assertEquals("inputFormat", param);
    }

    @Test
    public void e_transactionParameterTest() throws Exception {
        String param = TransactionParameters.getParam(SRS_NAME);
        logger.info("#####################TRANSANCTION_PARAM : {}\n", param);
        assertEquals("srsName", param);
    }

    @Test
    public void f_transactionParameterTest() throws Exception {
        String param = TransactionParameters.getParam(LOCKID);
        logger.info("#####################TRANSANCTION_PARAM : {}\n", param);
        assertEquals("LockId", param);
    }

    @Test
    public void g_transactionParameterTest() throws Exception {
        String param = TransactionParameters.getParam(RELEASE_ACTION);
        logger.info("#####################TRANSANCTION_PARAM : {}\n", param);
        assertEquals("releaseAction", param);
    }
}