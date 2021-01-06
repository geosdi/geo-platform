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
package org.geosdi.geoplatform.support.wfs.services;

import org.geosdi.geoplatform.connector.GPWFSConnectorStore;
import org.geosdi.geoplatform.connector.server.security.GPHeaderSecurityConnector;
import org.geosdi.geoplatform.exception.IllegalParameterFault;
import org.geosdi.geoplatform.support.wfs.configurator.GPWFSConfigurator;
import org.geosdi.geoplatform.support.wfs.feature.reader.FeatureSchemaReader;
import org.geosdi.geoplatform.support.wfs.feature.reader.GPFeatureSchemaReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;

import static org.geosdi.geoplatform.connector.WFSConnectorBuilder.newConnector;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public abstract class AbstractFeatureService {

    protected final Logger logger = LoggerFactory.getLogger(this.getClass());
    //
    protected static final FeatureSchemaReader GP_FEATURE_SCHEMA_READER = new GPFeatureSchemaReader();
    //
    @Autowired
    protected GPWFSConfigurator wfsConfigurator;

    /**
     * @param serverUrl
     * @return {@link GPWFSConnectorStore}
     * @throws Exception
     */
    protected GPWFSConnectorStore createWFSConnector(String serverUrl) throws Exception {
        try {
            return newConnector()
                    .withServerUrl(new URL(serverUrl))
                    .build();
        } catch (MalformedURLException ex) {
            ex.printStackTrace();
            throw new IllegalParameterFault("Malformed URL");
        }
    }

    /**
     * @param serverUrl
     * @param headerParams
     * @return {@link GPWFSConnectorStore}
     * @throws Exception
     */
    protected GPWFSConnectorStore createWFSConnector(String serverUrl, Map<String, String> headerParams) throws Exception {
        try {
            return newConnector()
                    .withServerUrl(new URL(serverUrl))
                    .withClientSecurity(new GPHeaderSecurityConnector(headerParams))
                    .build();
        } catch (MalformedURLException ex) {
            ex.printStackTrace();
            throw new IllegalParameterFault("Malformed URL");
        }
    }
}
