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
package org.geosdi.geoplatform.wpsconnector;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import junit.framework.Assert;
import org.geotoolkit.wps.*;
import org.geotoolkit.wps.xml.WPSMarshallerPool;
import org.geotoolkit.wps.xml.v100.ExecuteResponse;
import org.geotoolkit.wps.xml.v100.OutputDataType;
import org.geotoolkit.wps.xml.v100.ProcessBriefType;
import org.geotoolkit.wps.xml.v100.ProcessDescriptionType;
import org.geotoolkit.wps.xml.v100.ProcessDescriptions;
import org.geotoolkit.wps.xml.v100.WPSCapabilitiesType;
import org.geotoolkit.xml.MarshallerPool;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 *
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group @email
 * giuseppe.lascaleia@geosdi.org
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"applicationContext-TEST.xml"})
public class GeoPlatformWPSConnectorTest {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    //
    @Autowired
    private GeoPlatformWPSConnector gpWPSConnector;
    //
    private MarshallerPool pool = WPSMarshallerPool.getInstance();
    private Unmarshaller um;

    @Test
    public void testGetCapabilities() throws JAXBException, IOException {
        try {
            this.um = pool.acquireUnmarshaller();

            Assert.assertNotNull(um);

            GetCapabilitiesRequest request = this.gpWPSConnector.createCapabilitiesRequest();

            InputStream is = request.getResponseStream();

            WPSCapabilitiesType capabilities = ((JAXBElement<WPSCapabilitiesType>) um.unmarshal(
                    is)).getValue();

            logger.info(
                    "Service : @@@@@@@@@@@@@@@@@@@@ " + capabilities.getService()
                    + " - Version : @@@@@@@@@@@@@ " + capabilities.getVersion());

            logger.info("Number of Process : @@@@@@@@@@@@@@@@@@@@@@@@@ "
                    + capabilities.getProcessOfferings().getProcess().size());

            for (ProcessBriefType pbf : capabilities.getProcessOfferings().getProcess()) {
                logger.info(
                        "Process Identifier @@@@@@@@@@@@@@ "
                        + pbf.getIdentifier().getValue());
                logger.info("Process Description @@@@@@@@@@@@@@@@@@@ "
                        + pbf.getTitle().getValue());
            }

        } finally {
            pool.release(um);
        }
    }

    @Test
    public void testDoubleAddition() throws JAXBException, IOException {
        try {
            this.um = pool.acquireUnmarshaller();

            ExecuteRequest request = this.gpWPSConnector.createExecuteRequest();
            request.setIdentifier("gt:DoubleAddition");

            List<AbstractWPSInput> inputs = new ArrayList<AbstractWPSInput>();
            inputs.add(new WPSInputLiteral("input_a", "10"));
            inputs.add(new WPSInputLiteral("input_b", "10"));

            List<WPSOutput> outputs = new ArrayList<WPSOutput>();
            outputs.add(new WPSOutput("result"));

            request.setInputs(inputs);
            request.setOutputs(outputs);

            InputStream is = request.getResponseStream();

            ExecuteResponse response = (ExecuteResponse) um.unmarshal(
                    is);

            for (OutputDataType out : response.getProcessOutputs().getOutput()) {
                logger.info("RESULT @@@@@@@@@@@@@ " + out.getData().getLiteralData().getValue());
            }

            logger.info("\n");
        } finally {
            pool.release(um);
        }
    }
    
    /**
     * This Code running only modifing a Binding WPS jar
     * 
     * @throws JAXBException
     * @throws IOException 
     */

    @Test
    public void describeProcess() throws JAXBException, IOException {
        try {
            this.um = pool.acquireUnmarshaller();

            DescribeProcessRequest dsp = this.gpWPSConnector.createDescribeProcess();
            List<String> identifiers = new ArrayList<String>();
            identifiers.add("sitdpc:FilterByField");

            dsp.setIdentifiers(identifiers);

            InputStream is = dsp.getResponseStream();

            ProcessDescriptions pdt = (ProcessDescriptions) this.um.unmarshal(is);

            ProcessDescriptionType type = pdt.getProcessDescription().get(0);

            logger.info("Process Input @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ "
                    + type.getDataInputs().getInput().size());

            logger.info("Process Output @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ "
                    + type.getProcessOutputs().getOutput().size());


        } finally {
            pool.release(um);
        }
    }
}
