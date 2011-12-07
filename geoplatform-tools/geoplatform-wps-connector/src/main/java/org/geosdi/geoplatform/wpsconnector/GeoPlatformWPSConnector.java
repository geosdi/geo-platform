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
import net.opengis.wps10.ProcessBriefType;
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
import org.geotools.process.Process;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email  giuseppe.lascaleia@geosdi.org
 *
 */
public class GeoPlatformWPSConnector {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    //
    private URL url;
    private WebProcessingService wps;

    public GeoPlatformWPSConnector(String wpsUrlString) {
        try {
            this.url = new URL(wpsUrlString);
            this.wps = new WebProcessingService(url);
        } catch (IOException ex) {
            logger.error("IOException : " + ex);
        } catch (ServiceException ex) {
            logger.error("Service Exception : " + ex);
        }
    }

    /**
     * Get Capabilities WPS
     * 
     * @return EList of Process
     */
    public EList<ProcessBriefType> getWPSCapabilities() {
        WPSCapabilitiesType capabilities = wps.getCapabilities();

        ProcessOfferingsType processOfferings = capabilities.getProcessOfferings();
        return processOfferings.getProcess();
    }

    /**
     * Describe Process Request
     * 
     * @param 
     *      processIdentifier
     * 
     * @return 
     *      ProcessDescriptionType
     * 
     * @throws IllegalArgumentException 
     */
    public ProcessDescriptionType describeProcess(String processIdentifier)
            throws Exception {

        if (processIdentifier == null) {
            throw new Exception("Process Identifier must not be null");
        }

        DescribeProcessRequest descRequest = wps.createDescribeProcessRequest();
        descRequest.setIdentifier(processIdentifier); // describe the double addition process

        DescribeProcessResponse descResponse = null;
        try {
            descResponse = wps.issueRequest(descRequest);
        } catch (IOException ex) {
            logger.error("IOException : " + ex);
            throw new Exception(ex);
        } catch (ServiceException ex) {
            logger.error("Service Exception : " + ex);
            throw new Exception(ex);
        }

        ProcessDescriptionsType processDesc = descResponse.getProcessDesc();
        return (ProcessDescriptionType) processDesc.getProcessDescription().get(0);
    }

    /**
     * Create a Process 
     * 
     * @param 
     *      processIdentifier 
     * 
     * @return
     *        Process
     * 
     * @throws IllegalArgumentException 
     */
    public Process createProcess(String processIdentifier)
            throws Exception {

        WPSFactory wpsfactory = new WPSFactory(describeProcess(processIdentifier),
                url);

        return wpsfactory.create();

    }
}
