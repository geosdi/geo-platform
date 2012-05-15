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
package org.geosdi.geoplatform.connector;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import javax.xml.bind.JAXBElement;
import junit.framework.Assert;
import org.geosdi.geoplatform.connector.server.request.CatalogGetRecordByIdRequest;
import org.geosdi.geoplatform.xml.csw.OutputSchema;
import org.geosdi.geoplatform.xml.csw.v202.AbstractRecordType;
import org.geosdi.geoplatform.xml.csw.v202.GetRecordByIdResponseType;
import org.geosdi.geoplatform.xml.csw.v202.SummaryRecordType;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 *
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class CatalogGetRecordByIdTest {
    
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    //
    private GPCSWServerConnector serverConnector;
    
    @Before
    public void setUp() throws MalformedURLException {
        URL url = new URL("http://150.146.160.152/geonetwork/srv/en/csw");
        this.serverConnector = GPCSWConnectorBuilder.newConnector().
                withServerUrl(url).build();
    }
    
    @Test
    public void testSingleGetRecordById_SUMMARY() throws Exception {
        CatalogGetRecordByIdRequest<GetRecordByIdResponseType> request =
                this.serverConnector.createGetRecordByIdRequest();
        
        request.setId("ab687944-e8e4-41b4-9b62-4097e264b465");
        
        GetRecordByIdResponseType response = request.getResponse();
        
        Assert.assertEquals(true, response.isSetAbstractRecord());
        Assert.assertEquals(false, response.isSetAny());
        
        List<JAXBElement<? extends AbstractRecordType>> abstractRecord = response.getAbstractRecord();
        
        Assert.assertEquals(1, abstractRecord.size());
        
        SummaryRecordType record = (SummaryRecordType) abstractRecord.get(0).getValue();
        
        logger.info(
                "SUMMARY RESULT @@@@@@@@@@@@@@@@@@ " + record);
    }
    
    @Test
    public void testSingleGetRecordById_FULL() throws Exception {
        CatalogGetRecordByIdRequest<GetRecordByIdResponseType> request =
                this.serverConnector.createGetRecordByIdRequest();
        
        request.setId("ab687944-e8e4-41b4-9b62-4097e264b465");
        
        request.setOutputSchema(OutputSchema.GMD);
        request.setElementSetType("full");
        
        GetRecordByIdResponseType response = request.getResponse();
        
        Assert.assertEquals(false, response.isSetAbstractRecord());
        Assert.assertEquals(true, response.isSetAny());
        
        List<Object> any = response.getAny();
        
        logger.info(
                "FULL METADATA @@@@@@@@@@@@@@@@@@@@@@@@@@@ " + ((JAXBElement) any.get(
                                                                0)).getValue());
    }
    
    @Test
    public void testDoubleGetRecordById_SUMMARY() throws Exception {
        CatalogGetRecordByIdRequest<GetRecordByIdResponseType> request =
                this.serverConnector.createGetRecordByIdRequest();
        
        request.setId("ab687944-e8e4-41b4-9b62-4097e264b465",
                "906cc1a1-6b7d-4840-ac2a-f2c34dd423dd");
        
        GetRecordByIdResponseType response = request.getResponse();
        
        Assert.assertEquals(true, response.isSetAbstractRecord());
        Assert.assertEquals(false, response.isSetAny());
        
        List<JAXBElement<? extends AbstractRecordType>> abstractRecord = response.getAbstractRecord();
        
        Assert.assertEquals(2, abstractRecord.size());
        
        for (JAXBElement element : abstractRecord) {
            logger.info(
                    "SUMMARY RECORD @@@@@@@@@@@@@@@@@@ " + element.getValue() + "\n");
        }
    }
}
