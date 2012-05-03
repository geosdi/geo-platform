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
package org.geosdi.geoplatform.cswconnector.server;

import java.net.URL;
import org.geosdi.geoplatform.connector.server.GPAbstractServerConnector;
import org.geosdi.geoplatform.connector.server.security.GPSecurityConnector;
import org.geosdi.geoplatform.cswconnector.CatalogVersionException;
import org.geosdi.geoplatform.cswconnector.GPCatalogVersion;
import org.geosdi.geoplatform.cswconnector.server.request.CatalogGetCapabilitiesRequest;
import org.geosdi.geoplatform.cswconnector.server.request.CatalogGetRecordsRequest;
import org.geosdi.geoplatform.cswconnector.server.request.v202.CatalogGetCapabilitiesV202;
import org.geosdi.geoplatform.cswconnector.server.request.v202.CatalogGetRecordsV202;

/**
 *
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email  giuseppe.lascaleia@geosdi.org
 */
public class GPCatalogServerConnector extends GPAbstractServerConnector {

    private GPCatalogVersion version;

    /**
     * Create an Instance of {@link GPCatalogServerConnector} with the server url
     * and the specific version.
     * 
     * @param urlServer the String that represent CSW server Url
     * @param version the value of CSW version. Must be 2.0.2
     */
    public GPCatalogServerConnector(String urlServer, String version) {
        this(urlServer, null, version);
    }

    /**
     * Create an instance of {@link GPCatalogServerConnector} with the server url,
     * {@link GPSecurityConnector} for security and version
     * 
     * @param urlServer the String that represent CSW server Url
     * @param securityConnector {@link GPSecurityConnector}
     * @param version the value of CSW version. Must be 2.0.2
     */
    public GPCatalogServerConnector(String urlServer, GPSecurityConnector securityConnector,
            String version) {
        this(analyzesServerURL(urlServer), securityConnector, toCSWVersion(
                version));
    }

    /**
     * Create an instance of {@link GPCatalogServerConnector} with the {@link URL}
     * server url, {@link GPSecurityConnector} security context and {@link GPCatalogVersion}
     * csw version.
     * 
     * @param server {@link URL} server url
     * @param securityConnector {@link GPSecurityConnector}
     * @param theVersion {@link GPCatalogVersion} CSW version. Must be 2.0.2
     */
    public GPCatalogServerConnector(URL server, GPSecurityConnector securityConnector,
            GPCatalogVersion theVersion) {
        super(server, securityConnector);
        this.version = theVersion;
    }

    /**
     * Create CatalogGetCapabilitiesRequest request
     * 
     * @return {@link CatalogGetCapabilitiesRequest}
     */
    public CatalogGetCapabilitiesRequest createGetCapabilitiesRequest() {
        switch (version) {
            case V202:
                return new CatalogGetCapabilitiesV202(this);

            default:
                throw new CatalogVersionException(
                        "The Version for CSW must be 2.0.2");
        }
    }

    /**
     * Create CatalogGetRecordsRequest request
     * 
     * @return {@link CatalogGetRecordsRequest}
     */
    public CatalogGetRecordsRequest createGetRecordsRequest() {
        switch (version) {
            case V202:
                return new CatalogGetRecordsV202(this);
            default:
                throw new CatalogVersionException(
                        "The Version for CSW must be 2.0.2");
        }
    }

    /**
     * Method that convert String version in {@link GPCatalogVersion} instance 
     * 
     * @param version
     * @return {@link GPCatalogVersion}
     */
    private static GPCatalogVersion toCSWVersion(String version) {
        if (version.equalsIgnoreCase("2.0.2")) {
            return GPCatalogVersion.V202;
        } else {
            throw new CatalogVersionException("CSW Version must be 2.0.2");
        }
    }
}
