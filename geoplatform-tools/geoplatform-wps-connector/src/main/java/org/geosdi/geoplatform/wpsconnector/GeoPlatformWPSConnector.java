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

import java.net.MalformedURLException;
import java.net.URL;
import org.geotoolkit.security.ClientSecurity;
import org.geotoolkit.wps.DescribeProcessRequest;
import org.geotoolkit.wps.ExecuteRequest;
import org.geotoolkit.wps.GetCapabilitiesRequest;
import org.geotoolkit.wps.WebProcessingServer;
import org.geotoolkit.wps.xml.v100.WPSCapabilitiesType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group 
 * @email giuseppe.lascaleia@geosdi.org
 *
 */
public class GeoPlatformWPSConnector {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    //
    private WebProcessingServer wpsServer;

    public GeoPlatformWPSConnector(String url) {
        this(url, WPSVersionEnum.WPS_1_0_0);
    }

    /**
     * Create a GeoPlatformWPSConnector Instance with the url to the WPS Service
     * and WPS Version
     *
     * @param url
     * @param version
     */
    public GeoPlatformWPSConnector(String url, WPSVersionEnum version) {
        try {
            this.wpsServer = new WebProcessingServer(new URL(url),
                    version.toString());
        } catch (MalformedURLException ex) {
            logger.error("URL Incorrect : @@@@@@@@@@@@@@ " + ex);
        }
    }

    /**
     *
     * @param url
     * @param security
     * @param version
     */
    public GeoPlatformWPSConnector(String url, ClientSecurity security,
            WPSVersionEnum version) {
        try {
            this.wpsServer = new WebProcessingServer(new URL(url), security,
                    version.toString());
        } catch (MalformedURLException ex) {
            logger.error("URL Incorrect : @@@@@@@@@@@@@@@ " + ex);
        }
    }

    /**
     * Return the GetCapabilities from WPS Server
     *
     * @return WPSCapabilitiesType
     */
    public WPSCapabilitiesType getCapabilities() {
        return this.wpsServer.getCapabilities();
    }

    /**
     * Create Capabilities Request
     *
     * @return GetCapabilitiesRequest
     */
    public GetCapabilitiesRequest createCapabilitiesRequest() {
        return this.wpsServer.createGetCapabilities();
    }

    /**
     * Create Describe Process Request
     *
     * @return DescribeProcessRequest
     */
    public DescribeProcessRequest createDescribeProcess() {
        return this.wpsServer.createDescribeProcess();
    }

    /**
     * Create Execute Request
     *
     * @return ExecuteRequest
     */
    public ExecuteRequest createExecuteRequest() {
        return this.wpsServer.createExecute();
    }
}
