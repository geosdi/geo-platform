/*
 *  geo-platform
 *  Rich webgis framework
 *  http://geo-platform.org
 * ====================================================================
 *
 * Copyright (C) 2008-2011 geoSDI Group (CNR IMAA - Potenza - ITALY).
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
import java.net.URL;
import java.util.Map;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.opengis.wps10.ProcessDescriptionType;
import net.opengis.wps10.ProcessDescriptionsType;
import net.opengis.wps10.ProcessOfferingsType;
import net.opengis.wps10.WPSCapabilitiesType;
import org.eclipse.emf.common.util.EList;
import org.geotools.data.wps.WPSFactory;
import org.geotools.data.wps.WebProcessingService;
import org.geotools.data.wps.request.DescribeProcessRequest;
import org.geotools.data.wps.response.DescribeProcessResponse;
import org.geotools.ows.ServiceException;

/**
 * @author Michele Santomauro - CNR IMAA geoSDI Group
 * @email michele.santomauro@geosdi.org
 */
public class GPWPSDecorator {

    // url: "http://localhost:8080/geoserver/ows?service=WPS&request=GetCapabilities"
    public void getWPSCapabilities(String urlString) {
        try {
            URL url = new URL(urlString);
            WebProcessingService wps = new WebProcessingService(url);
            WPSCapabilitiesType capabilities = wps.getCapabilities();

            // view a list of processes offered by the server
            ProcessOfferingsType processOfferings = capabilities.getProcessOfferings();
            EList processes = processOfferings.getProcess();
        } catch (IOException ex) {
            Logger.getLogger(GPWPSDecorator.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ServiceException ex) {
            Logger.getLogger(GPWPSDecorator.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void describeWPSProcess(WebProcessingService wps, String urlString) {
        try {
            URL url = new URL(urlString);

            // create a WebProcessingService as shown above, then do a full describeprocess on my process
            DescribeProcessRequest descRequest = wps.createDescribeProcessRequest();
            descRequest.setIdentifier("DoubleAddition"); // describe the double addition process

            // send the request and get the ProcessDescriptionType bean to create a WPSFactory
            DescribeProcessResponse descResponse = wps.issueRequest(descRequest);
            ProcessDescriptionsType processDesc = descResponse.getProcessDesc();
            ProcessDescriptionType pdt = (ProcessDescriptionType) processDesc.getProcessDescription().get(0);
            WPSFactory wpsfactory = new WPSFactory(pdt, url);

            // create a process
            // create a WebProcessingService, WPSFactory and WPSProcess as shown above and execute it
            org.geotools.process.Process process = wpsfactory.create();


            // Execute WPS


            // setup the inputs
            Map<String, Object> map = new TreeMap<String, Object>();
            Double d1 = 77.5;
            Double d2 = 22.3;
            map.put("input_a", d1);
            map.put("input_b", d2);

            // you could validate your inputs against what the process expected by checking
            // your map against the Parameters in wpsfactory.getParameterInfo(), but
            // to keep this simple let's just try sending the request without validation
            Map<String, Object> results = process.execute(map, null);

            Double result = (Double) results.get("result");
        } catch (IOException ex) {
            Logger.getLogger(GPWPSDecorator.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ServiceException ex) {
            Logger.getLogger(GPWPSDecorator.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
