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
package org.geosdi.geoplatform.connector;

import org.geosdi.geoplatform.connector.api.capabilities.model.csw.CatalogCapabilities;
import org.geosdi.geoplatform.connector.server.request.CatalogGetCapabilitiesRequest;
import org.geosdi.geoplatform.connector.server.security.BasicPreemptiveSecurityConnector;
import org.geosdi.geoplatform.logger.support.annotation.GeoPlatformLog;
import org.geosdi.geoplatform.xml.csw.v202.CapabilitiesType;
import org.geosdi.geoplatform.xml.ows.v100.DomainType;
import org.geosdi.geoplatform.xml.ows.v100.Operation;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import static org.geosdi.geoplatform.connector.GPCSWConnectorBuilder.newConnector;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml",
        "classpath:applicationContext-Logger.xml"})
public class CatalogCapabilitiesTest {

    @GeoPlatformLog
    private Logger logger;
    /**
     * SNIPC Catalog.
     */
    private
    @Value("configurator{snipc_catalog_url}")
    String snipcUrl;
    private
    @Value("configurator{snipc_catalog_username}")
    String snipcUsername;
    private
    @Value("configurator{snipc_catalog_password}")
    String snipcPassword;
    //
    @Autowired
    private CatalogGetCapabilitiesBean catalogCapabilitiesBean;

    @Test
    @Ignore(value = "Server is DOWN")
    public void testCapabilitiesV201WithoutVersionControl() {
        try {
            CatalogCapabilities catalogGetCapabilities = catalogCapabilitiesBean.bindUrlWithoutVersionControl("http://catalogocentrale.nsdi.it/geonetwork/srv/eng/csw?SERVICE");
            logger.info("@@@@@@@@@@@@@@@ CATALOG CAPABILITIES BEAN V_2.0.1 WITHOUT VERSION CONTROL@@@@@@@@@@@@@@@@@@@@@@@ " + catalogGetCapabilities);
        } catch (MalformedURLException ex) {
            logger.error("MalformedURLException @@@@@@@@@@@@@@ " + ex);
        } catch (IOException es) {
            logger.error("IOException @@@@@@@@@@@@@@ " + es);
        }
    }

    @Test
    @Ignore(value = "Server is DOWN")
    public void testCapabilitiesV201() {
        try {
            CatalogCapabilities catalogGetCapabilities = catalogCapabilitiesBean.bindUrl("http://catalogocentrale.nsdi.it/geonetwork/srv/en/csw");
            logger.info("@@@@@@@@@@@@@@@ CATALOG CAPABILITIES BEAN V_2.0.1@@@@@@@@@@@@@@@@@@@@@@@ " + catalogGetCapabilities);
        } catch (MalformedURLException ex) {
            logger.error("MalformedURLException @@@@@@@@@@@@@@ " + ex);
        } catch (IOException es) {
            logger.error("IOException @@@@@@@@@@@@@@ " + es);
        } catch (CatalogVersionException ve) {
            logger.error("CatalogVersionException @@@@@@@@@@@@ " + ve);
        }
    }

    @Test
    @Ignore("SERVER IS DOWN")
    public void testCapabilitiesV202() throws Exception {
        try {

            CatalogCapabilities catalogGetCapabilities = catalogCapabilitiesBean.bindUrl("http://rsdi.regione.basilicata.it/Catalogo/srv/en/csw");
            logger.info("CATALOG CAPABILITIES BEAN V_2.0.2 @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ " + catalogGetCapabilities);
        } catch (MalformedURLException ex) {
            logger.error("MalformedURLException @@@@@@@@@@@@@@ " + ex);
        } catch (IOException es) {
            logger.error("IOException @@@@@@@@@@@@@@ " + es);
        } catch (CatalogVersionException ve) {
            logger.error("CatalogVersionException @@@@@@@@@@@@ " + ve);
        }
    }

    @Test
    @Ignore(value = "There some problems.")
    public void testCSW_ESRI() {
        try {
            CatalogCapabilities catalogGetCapabilities = catalogCapabilitiesBean.bindUrl("https://snipc.protezionecivile.it/geoportal/csw/discovery");
            logger.info("CATALOG CAPABILITIES ESRI @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ " + catalogGetCapabilities);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Test
    public void testGetCapabilitiesWithConnector() throws Exception {
        URL url = new URL("http://catalog.geosdi.org/geonetwork/srv/eng/csw");
        GPCatalogConnectorStore serverConnector = newConnector().
                withServerUrl(url).build();

        CatalogGetCapabilitiesRequest<CapabilitiesType> request = serverConnector.createGetCapabilitiesRequest();
        CapabilitiesType response = request.getResponse();
        logger.info("CSW GET_CAPABILITIES VERSION @@@@@@@@@@@@@@@@@@@@@@@ {}",
                response.getVersion());

        List<Operation> operationList = response.getOperationsMetadata().getOperation();
        for (Operation operation : operationList) {
            String operationName = operation.getName();
            if ("GetRecordById".equals(operationName)) {
                List<DomainType> parameterList = operation.getParameter();
                for (DomainType parameter : parameterList) {
                    String parameterName = parameter.getName();
                    if ("outputSchema".equals(parameterName)) {
                        List<String> valueList = parameter.getValue();
                        logger.info("\n########################### outputSchema");
                        for (String value : valueList) {
                            logger.info("\n*** {}", value);
                        }
                    }
                }
            }
        }
    }

    @Ignore("Require to add the SNIPC certificate into default keystore")
    @Test
    public void testSecureGetCapabilities() throws Exception {
        GPCatalogConnectorStore serverConnector = newConnector()
                .withServerUrl(new URL(snipcUrl))
                .withClientSecurity(new BasicPreemptiveSecurityConnector(snipcUsername, snipcPassword))
                .build();
        CatalogGetCapabilitiesRequest<CapabilitiesType> request = serverConnector.createGetCapabilitiesRequest();
        CapabilitiesType response = request.getResponse();
        logger.info("CSW SECURE GET_CAPABILITIES VERSION @@@@@@@@@@@@@@@@@@@@@@@ {}", response.getVersion());
    }

    @Ignore(value = "Server is Down")
    @Test
    public void testGetCapabilitiesRNDTWithConnector() throws Exception {
        try {
            CatalogCapabilities catalogGetCapabilities = catalogCapabilitiesBean.bindUrlWithoutVersionControl(
                    "http://www.rndt.gov.it/RNDT/CSW?request=GetCapabilities&service=CSW");
            logger.info("@@@@@@@@@@@@@@@ CATALOG CAPABILITIES BEAN V_2.0.1 WITHOUT VERSION CONTROL"
                    + "@@@@@@@@@@@@@@@@@@@@@@@ " + catalogGetCapabilities);
        } catch (MalformedURLException ex) {
            ex.printStackTrace();
        } catch (IOException es) {
            es.printStackTrace();
        }
    }
}