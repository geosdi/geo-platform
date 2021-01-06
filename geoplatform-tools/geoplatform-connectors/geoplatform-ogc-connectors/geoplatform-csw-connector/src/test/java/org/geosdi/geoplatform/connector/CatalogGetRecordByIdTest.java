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

import org.geosdi.geoplatform.connector.server.request.CatalogGetCapabilitiesRequest;
import org.geosdi.geoplatform.connector.server.request.CatalogGetRecordByIdRequest;
import org.geosdi.geoplatform.connector.server.security.BasicPreemptiveSecurityConnector;
import org.geosdi.geoplatform.connector.server.security.GPSecurityConnector;
import org.geosdi.geoplatform.logger.support.annotation.GeoPlatformLog;
import org.geosdi.geoplatform.xml.csw.OutputSchema;
import org.geosdi.geoplatform.xml.csw.v202.*;
import org.geosdi.geoplatform.xml.gfc.FCFeatureCatalogueType;
import org.geosdi.geoplatform.xml.iso19139.v20070417.gmd.MDMetadataType;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import javax.xml.bind.JAXBElement;
import java.io.FileOutputStream;
import java.net.URL;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 *
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext-geoSDI.xml",
        "classpath:applicationContext-Logger.xml"})
public class CatalogGetRecordByIdTest {

    @GeoPlatformLog
    private static Logger logger;
    //
    @Resource(name = "geoSDIServerConnectorStore")
    private GPCatalogConnectorStore serverConnector;
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
    /**
     * ISPRA Catalog.
     */
    private static final String ISPRA_URL = "http://sgi.isprambiente.it/geoportal/csw/discovery";

    @Ignore(value = "ID REQUEST DOESN'T EXIST")
    @Test
    public void testTypeSummary() throws Exception {
        CatalogGetRecordByIdRequest<GetRecordByIdResponseType> request = serverConnector.createGetRecordByIdRequest();
        request.setId("7e418dac-3764-4290-b8ac-47c9ac2a12af");
        GetRecordByIdResponseType response = request.getResponse();
        assertEquals(true, response.isSetAbstractRecord());
        assertEquals(false, response.isSetAny());
        List<JAXBElement<? extends AbstractRecordType>> abstractRecord = response.getAbstractRecord();
        assertEquals(1, abstractRecord.size());
        SummaryRecordType record = (SummaryRecordType) abstractRecord.get(0).getValue();
        logger.info("SUMMARY RESULT @@@@@@@@@@@@@@@@@@ " + record);
    }

    @Ignore(value = "ID REQUEST DOESN'T EXIST")
    @Test
    public void testTypeFull() throws Exception {
        CatalogGetRecordByIdRequest<GetRecordByIdResponseType> request = serverConnector.createGetRecordByIdRequest();
        request.setId("7e418dac-3764-4290-b8ac-47c9ac2a12af");
        request.setOutputSchema(OutputSchema.GMD);
        request.setElementSetType(ElementSetType.FULL.value());
        GetRecordByIdResponseType response = request.getResponse();
        assertEquals(false, response.isSetAbstractRecord());
        assertEquals(true, response.isSetAny());
        List<Object> any = response.getAny();
        logger.info("FULL METADATA @@@@@@@@@@@@@@@@@@@@@@@@@@@ {}", ((JAXBElement) any.get(0)).getValue());
    }

    @Ignore(value = "Server has Problem")
    @Test(expected = IllegalStateException.class)
    public void testTypeFullIspra() throws Exception {
        GPCatalogConnectorStore ispraServerConnector = GPCSWConnectorBuilder
                .newConnector()
                .withServerUrl(new URL("http://www.geoportale.isprambiente.it/"
                        + "geoportale/csw/discovery"))
                .build();
        CatalogGetRecordByIdRequest<GetRecordByIdResponseType> request = ispraServerConnector.createGetRecordByIdRequest();
        request.setId("ispra_rm:20150521:185000");
        request.setOutputSchema(OutputSchema.GMD);
        request.setElementSetType(ElementSetType.FULL.value());
        GetRecordByIdResponseType response = request.getResponse();
        assertEquals(false, response.isSetAbstractRecord());
        assertEquals(true, response.isSetAny());
        List<Object> any = response.getAny();
        logger.info("FULL METADATA @@@@@@@@@@@@@@@@@@@@@@@@@@@ {}", ((JAXBElement) any.get(0)).getValue());
    }

    @Ignore(value = "ID REQUEST DOESN'T EXIST")
    @Test
    public void testDoubleRequest() throws Exception {
        CatalogGetRecordByIdRequest<GetRecordByIdResponseType> request = serverConnector.createGetRecordByIdRequest();
        request.setId("7e418dac-3764-4290-b8ac-47c9ac2a12af", "r_friuli:m8726-cc-i1286");
        GetRecordByIdResponseType response = request.getResponse();
        assertEquals(true, response.isSetAbstractRecord());
        assertEquals(false, response.isSetAny());
        List<JAXBElement<? extends AbstractRecordType>> abstractRecord = response.getAbstractRecord();
        assertEquals(2, abstractRecord.size());
        for (JAXBElement element : abstractRecord) {
            logger.info("SUMMARY RECORD @@@@@@@@@@@@@@@@@@ {}\n", element.getValue());
        }
    }

    @Ignore(value = "ID REQUEST DOESN'T EXIST")
    @Test
    public void testOutputGmd() throws Exception {
        CatalogGetRecordByIdRequest<GetRecordByIdResponseType> request = serverConnector.createGetRecordByIdRequest();
        request.setId("7e418dac-3764-4290-b8ac-47c9ac2a12af");
        request.setElementSetType(ElementSetType.FULL.value());
        request.setOutputSchema(OutputSchema.GMD);
        GetRecordByIdResponseType response = request.getResponse();
        assertEquals(false, response.isSetAbstractRecord());
        assertEquals(true, response.isSetAny());
        List<Object> any = response.getAny();
        assertEquals(1, any.size());
        JAXBElement element = ((JAXBElement) any.get(0));
        MDMetadataType metadata = (MDMetadataType) element.getValue();
        Assert.assertNotNull(metadata);
        logger.info("FULL METADATA @@@@@@@@@@@@@@@@@@@@@@@@@@@ {}", metadata);
    }

    @Ignore(value = "Catalog is DOWN")
    @Test
    public void testOutputGmdIspra() throws Exception {
        URL url = new URL(ISPRA_URL);
        GPCatalogConnectorStore connector = GPCSWConnectorBuilder.newConnector().
                withServerUrl(url).build();
        CatalogGetRecordByIdRequest<GetRecordByIdResponseType> request = connector.createGetRecordByIdRequest();
        request.setId("{D499D5B8-13A5-43B2-B4FA-9FD2AA519F90}");
        request.setElementSetType(ElementSetType.FULL.value());
        request.setOutputSchema(OutputSchema.GMD);
        GetRecordByIdResponseType response = request.getResponse();
        assertEquals(false, response.isSetAbstractRecord());
        assertEquals(true, response.isSetAny());
        List<Object> any = response.getAny();
        assertEquals(1, any.size());
        JAXBElement element = ((JAXBElement) any.get(0));
        MDMetadataType metadata = (MDMetadataType) element.getValue();
        Assert.assertNotNull(metadata);
        logger.info("FULL METADATA @@@@@@@@@@@@@@@@@@@@@@@@@@@ {}", metadata);
    }

    @Ignore(value = "Catalog is DOWN")
    @Test
    public void testOutputOriginalIspra() throws Exception {
        URL url = new URL(ISPRA_URL);
        GPCatalogConnectorStore connector = GPCSWConnectorBuilder.newConnector().
                withServerUrl(url).build();
        CatalogGetRecordByIdRequest<GetRecordByIdResponseType> request = connector.createGetRecordByIdRequest();
        request.setId("{D499D5B8-13A5-43B2-B4FA-9FD2AA519F90}");
        request.setElementSetType(ElementSetType.FULL.value());
        request.setOutputSchema(OutputSchema.ORIGINAL);
        Object o = request.getResponse();
        MDMetadataType metadata = (MDMetadataType) o;
        Assert.assertNotNull(metadata);
        logger.info("FULL METADATA @@@@@@@@@@@@@@@@@@@@@@@@@@@ {}", metadata);
    }

    @Ignore("Require to add the SNIPC certificate into default keystore")
    @Test
    public void testSecureSnipc() throws Exception {
        URL url = new URL(snipcUrl);
        GPSecurityConnector securityConnector = new BasicPreemptiveSecurityConnector(
                snipcUsername, snipcPassword);
        GPCatalogConnectorStore snipcConnector = GPCSWConnectorBuilder.newConnector().
                withServerUrl(url).
                withClientSecurity(securityConnector).
                build();
        CatalogGetRecordByIdRequest<GetRecordByIdResponseType> request = snipcConnector.createGetRecordByIdRequest();
        request.setId("PCM:901:20101021:112931");
        request.setElementSetType(ElementSetType.FULL.toString());
//        request.setOutputSchema(OutputSchema.CSW_V202);
        GetRecordByIdResponseType response = request.getResponse();
        assertEquals(true, response.isSetAbstractRecord());
        assertEquals(false, response.isSetAny());
        List<JAXBElement<? extends AbstractRecordType>> abstractRecord = response.getAbstractRecord();
        assertEquals(1, abstractRecord.size());
        logger.info("RECORD @@@@@@@@@@@@@@@@@@ {}", abstractRecord.get(0).getValue());
    }

    /**
     * <p>
     * SNIPC catalogue is ambiguous so we can't know the OUTPUT SCHEMA and the
     * returned Object. For ISO - 19110 the Obeject is
     * {@link FCFeatureCatalogueType}. For ISO - 19139 the Object is
     * {@link MDMetadataType} </p>
     *
     */
    @Ignore("Require to add the SNIPC certificate into default keystore")
    @Test
    public void testSecureOutputOriginalSnipc() throws Exception {
        URL url = new URL(snipcUrl);
        GPSecurityConnector securityConnector = new BasicPreemptiveSecurityConnector(
                snipcUsername, snipcPassword);
        GPCatalogConnectorStore snipcConnector = GPCSWConnectorBuilder.newConnector().
                withServerUrl(url).
                withClientSecurity(securityConnector).
                build();

        CatalogGetCapabilitiesRequest<CapabilitiesType> requestGetCap = snipcConnector.createGetCapabilitiesRequest();
        logger.info("GetCapabilities SNIPC @@@@@@@@@@@@@@@@@@@@@@@@ {}\n", requestGetCap.getResponse());

        CatalogGetRecordByIdRequest<GetRecordByIdResponseType> request = snipcConnector.createGetRecordByIdRequest();

        request.setId("{3DEE88CB-A0DB-4794-941A-FD8119621A2F}");
        request.setElementSetType(ElementSetType.FULL.toString());
        request.setOutputSchema(OutputSchema.ORIGINAL);

        Object o = request.getResponse();
//        MDMetadataType metadata = (MDMetadataType) o;
//        Assert.assertNotNull(metadata);
        logger.info("FULL METADATA @@@@@@@@@@@@@@@@@@@@@@@@@@@ {}", request.getResponseAsString());
        String snipcGetRecordById = "target/snipcGetRecordById.xml";
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(snipcGetRecordById);
            request.getMarshaller().marshal(o, fos);
        } finally {
            if (fos != null) {
                fos.close();
            }
        }
    }
}