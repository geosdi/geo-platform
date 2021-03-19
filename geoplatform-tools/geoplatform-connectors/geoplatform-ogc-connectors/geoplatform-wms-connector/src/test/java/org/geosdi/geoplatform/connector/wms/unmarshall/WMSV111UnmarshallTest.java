/**
 * geo-platform
 * Rich webgis framework
 * http://geo-platform.org
 * ====================================================================
 * <p>
 * Copyright (C) 2008-2021 geoSDI Group (CNR IMAA - Potenza - ITALY).
 * <p>
 * This program is free software: you can redistribute it and/or modify it
 * under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version. This program is distributed in the
 * hope that it will be useful, but WITHOUT ANY WARRANTY; without
 * even the implied warranty of MERCHANTABILITY or FITNESS FOR
 * A PARTICULAR PURPOSE. See the GNU General Public License
 * for more details. You should have received a copy of the GNU General
 * Public License along with this program. If not, see http://www.gnu.org/licenses/
 * <p>
 * ====================================================================
 * <p>
 * Linking this library statically or dynamically with other modules is
 * making a combined work based on this library. Thus, the terms and
 * conditions of the GNU General Public License cover the whole combination.
 * <p>
 * As a special exception, the copyright holders of this library give you permission
 * to link this library with independent modules to produce an executable, regardless
 * of the license terms of these independent modules, and to copy and distribute
 * the resulting executable under terms of your choice, provided that you also meet,
 * for each linked independent module, the terms and conditions of the license of
 * that module. An independent module is a module which is not derived from or
 * based on this library. If you modify this library, you may extend this exception
 * to your version of the library, but you are not obligated to do so. If you do not
 * wish to do so, delete this exception statement from your version.
 */
package org.geosdi.geoplatform.connector.wms.unmarshall;

import org.geosdi.geoplatform.connector.jaxb.context.WMSJAXBContext;
import org.geosdi.geoplatform.connector.jaxb.repository.JAXBContextConnectorRepository;
import org.geosdi.geoplatform.jaxb.GPBaseJAXBContext;
import org.geosdi.geoplatform.wms.v111.WMSDescribeLayerResponse;
import org.geosdi.geoplatform.wms.v111.WMTMSCapabilities;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;

import javax.xml.parsers.SAXParserFactory;
import javax.xml.transform.sax.SAXSource;
import java.io.File;
import java.io.FileReader;
import java.io.StringWriter;

import static java.io.File.separator;
import static java.lang.Boolean.FALSE;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Stream.of;
import static org.geosdi.geoplatform.connector.WMSVersion.V111;
import static org.geosdi.geoplatform.connector.jaxb.repository.WMSConnectorJAXBContextV111.WMS_CONTEXT_KEY_V111;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.runners.MethodSorters.NAME_ASCENDING;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@FixMethodOrder(value = NAME_ASCENDING)
public class WMSV111UnmarshallTest {

    private static final Logger logger = LoggerFactory.getLogger(WMSV111UnmarshallTest.class);

    static {
        wmsContext = JAXBContextConnectorRepository.getProvider(WMS_CONTEXT_KEY_V111);
    }

    //
    private static final SAXParserFactory spf = SAXParserFactory.newInstance();
    private static final GPBaseJAXBContext wmsContext;
    private static File wmsGetCapabilitiesFile;
    private static File wmsDescribeLayerFile;
    private static File wmsGetCapabilitiesMinisteroAmbiente;
    private static File wmsGetCapabilitiesIncendi;
    private static File wmsGetCapabilitiesCartaFitoclimatica;
    private static File wmsGetCapabilitiesCartaGeolitologica;
    private static File wmsGetCapabilitiesCatalogoFrane;
    private static File wmsGetCapabilitiesClassificazioneSismica;
    private static File wmsGetCapabilitiesIspraCoste;
    private static File wmsGetCapabilitiesIspra;
    private static File wmsGetCapabilitiesIspraNatura;
    private static File wmsServiziProtezioneCivile;
    private static File wmsGetCapabilitiesProsit;
    private static File wmsGetCapabilitiesFile1;
    private static File wmsGetCapabilitiesCnrIrea;

    @BeforeClass
    public static void beforeClass() throws Exception {
        assertNotNull(wmsContext);
        assertTrue(wmsContext instanceof WMSJAXBContext);
        assertTrue(((WMSJAXBContext) wmsContext).getVersion() == V111);
        String basePath = of(new File(".").getCanonicalPath(), "src", "test", "resources")
                .collect(joining(separator, "", separator));
        wmsGetCapabilitiesFile = new File(basePath.concat("getcapabilities_1.1.1.xml"));
        wmsDescribeLayerFile = new File(basePath.concat("describeLayer-111.xml"));
        wmsGetCapabilitiesMinisteroAmbiente = new File(basePath.concat("ogc_v1.1.1.xml"));
        wmsGetCapabilitiesIncendi = new File(basePath.concat("ogc.xml"));
        wmsGetCapabilitiesCartaFitoclimatica = new File(basePath.concat("cartaFitoclimatica.xml"));
        wmsGetCapabilitiesCartaGeolitologica = new File(basePath.concat("cartaGeolitologica.xml"));
        wmsGetCapabilitiesCatalogoFrane = new File(basePath.concat("catalogoFrane.xml"));
        wmsGetCapabilitiesClassificazioneSismica = new File(basePath.concat("classificazioneSismica2012.xml"));
        wmsGetCapabilitiesIspraCoste = new File(basePath.concat("WMSIspraCoste.xml"));
        wmsGetCapabilitiesIspra = new File(basePath.concat("WMSIspraGetCapabilities.xml"));
        wmsGetCapabilitiesIspraNatura = new File(basePath.concat("WMSIspraNatura.xml"));
        wmsServiziProtezioneCivile = new File(basePath.concat("serviziProtezioneCivileGetCapV1.1.1.xml"));
        wmsGetCapabilitiesProsit = new File(basePath.concat("WMSPrositGetCapabilities.xml"));
        wmsGetCapabilitiesFile1 = new File(basePath.concat("WMSGetCapabilities_1.1.1.xml"));
        wmsGetCapabilitiesCnrIrea = new File(basePath.concat("getcapabilitiesCnrIrea_1.1.1.xml"));
        spf.setFeature("http://apache.org/xml/features/nonvalidating/load-external-dtd", FALSE);
        spf.setFeature("http://xml.org/sax/features/validation", FALSE);
    }

    @Test
    public void a_unmarshallWMSGetCapabilitiesV111Test() throws Exception {
        XMLReader xmlReader = spf.newSAXParser().getXMLReader();
        InputSource inputSource = new InputSource(new FileReader(wmsGetCapabilitiesFile));
        SAXSource source = new SAXSource(xmlReader, inputSource);
        WMTMSCapabilities wmsCapabilities = (WMTMSCapabilities) wmsContext.acquireUnmarshaller().unmarshal(source);
        logger.info("#######################WMSGetCapabilitiesV111 : {}\n", wmsCapabilities);
        StringWriter writer = new StringWriter();
        wmsContext.acquireMarshaller().marshal(wmsCapabilities, writer);
        logger.debug("######################WMSGetCapabilitiesV111-String : \n{}\n", writer);
    }

    @Test
    public void b_unmarshallWMSDescribeLayerV111Test() throws Exception {
        XMLReader xmlReader = spf.newSAXParser().getXMLReader();
        InputSource inputSource = new InputSource(new FileReader(wmsDescribeLayerFile));
        SAXSource source = new SAXSource(xmlReader, inputSource);
        WMSDescribeLayerResponse wmsDescribeLayerResponse = (WMSDescribeLayerResponse) wmsContext.acquireUnmarshaller().unmarshal(source);
        logger.debug("#######################WMSDescribeLayerResponseV111 : {}\n", wmsDescribeLayerResponse);
        StringWriter writer = new StringWriter();
        wmsContext.acquireMarshaller().marshal(wmsDescribeLayerResponse, writer);
        logger.info("######################WMSDescribeLayerResponseV111-String : \n{}\n", writer);
    }

    @Test
    public void c_unmarshallWMSGetCapabilitiesV111Test() throws Exception {
        XMLReader xmlReader = spf.newSAXParser().getXMLReader();
        InputSource inputSource = new InputSource(new FileReader(wmsGetCapabilitiesMinisteroAmbiente));
        SAXSource source = new SAXSource(xmlReader, inputSource);
        WMTMSCapabilities wmsCapabilities = (WMTMSCapabilities) wmsContext.acquireUnmarshaller().unmarshal(source);
        logger.debug("#######################WMSGetCapabilitiesV111_Ministero_Ambiente : {}\n", wmsCapabilities);
        StringWriter writer = new StringWriter();
        wmsContext.acquireMarshaller().marshal(wmsCapabilities, writer);
        logger.info("######################WMSGetCapabilitiesV111_Ministero_Ambiente-String : \n{}\n", writer);
    }

    @Test
    public void d_unmarshallWMSGetCapabilitiesV111Test() throws Exception {
        XMLReader xmlReader = spf.newSAXParser().getXMLReader();
        InputSource inputSource = new InputSource(new FileReader(wmsGetCapabilitiesIncendi));
        SAXSource source = new SAXSource(xmlReader, inputSource);
        WMTMSCapabilities wmsCapabilities = (WMTMSCapabilities) wmsContext.acquireUnmarshaller().unmarshal(source);
        logger.debug("#######################WMSGetCapabilitiesV111_INCENDI_BOSCHIVI : {}\n", wmsCapabilities);
        StringWriter writer = new StringWriter();
        wmsContext.acquireMarshaller().marshal(wmsCapabilities, writer);
        logger.info("######################WMSGetCapabilitiesV111_INCENDI_BOSCHIVI-String : \n{}\n", writer);
    }

    @Test
    public void e_unmarshallWMSGetCapabilitiesV111Test() throws Exception {
        XMLReader xmlReader = spf.newSAXParser().getXMLReader();
        InputSource inputSource = new InputSource(new FileReader(wmsGetCapabilitiesCartaFitoclimatica));
        SAXSource source = new SAXSource(xmlReader, inputSource);
        WMTMSCapabilities wmsCapabilities = (WMTMSCapabilities) wmsContext.acquireUnmarshaller().unmarshal(source);
        logger.debug("#######################WMSGetCapabilitiesV111_CARTA_FITOCLIMATICA : {}\n", wmsCapabilities);
        StringWriter writer = new StringWriter();
        wmsContext.acquireMarshaller().marshal(wmsCapabilities, writer);
        logger.info("######################WMSGetCapabilitiesV111_CARTA_FITOCLIMATICA-String : \n{}\n", writer);
    }

    @Test
    public void f_unmarshallWMSGetCapabilitiesV111Test() throws Exception {
        XMLReader xmlReader = spf.newSAXParser().getXMLReader();
        InputSource inputSource = new InputSource(new FileReader(wmsGetCapabilitiesCartaGeolitologica));
        SAXSource source = new SAXSource(xmlReader, inputSource);
        WMTMSCapabilities wmsCapabilities = (WMTMSCapabilities) wmsContext.acquireUnmarshaller().unmarshal(source);
        logger.debug("#######################WMSGetCapabilitiesV111_CARTA_GEOLOGICA : {}\n", wmsCapabilities);
        StringWriter writer = new StringWriter();
        wmsContext.acquireMarshaller().marshal(wmsCapabilities, writer);
        logger.info("######################WMSGetCapabilitiesV111_CARTA_GEOLOGICA-String : \n{}\n", writer);
    }

    @Test
    public void g_unmarshallWMSGetCapabilitiesV111Test() throws Exception {
        XMLReader xmlReader = spf.newSAXParser().getXMLReader();
        InputSource inputSource = new InputSource(new FileReader(wmsGetCapabilitiesCatalogoFrane));
        SAXSource source = new SAXSource(xmlReader, inputSource);
        WMTMSCapabilities wmsCapabilities = (WMTMSCapabilities) wmsContext.acquireUnmarshaller().unmarshal(source);
        logger.debug("#######################WMSGetCapabilitiesV111_CATALOGO_FRANE : {}\n", wmsCapabilities);
        StringWriter writer = new StringWriter();
        wmsContext.acquireMarshaller().marshal(wmsCapabilities, writer);
        logger.info("######################WMSGetCapabilitiesV111_CATALOGO_FRANE-String : \n{}\n", writer);
    }

    @Test
    public void h_unmarshallWMSGetCapabilitiesV111Test() throws Exception {
        XMLReader xmlReader = spf.newSAXParser().getXMLReader();
        InputSource inputSource = new InputSource(new FileReader(wmsGetCapabilitiesClassificazioneSismica));
        SAXSource source = new SAXSource(xmlReader, inputSource);
        WMTMSCapabilities wmsCapabilities = (WMTMSCapabilities) wmsContext.acquireUnmarshaller().unmarshal(source);
        logger.debug("#######################WMSGetCapabilitiesV111_CLASSIFICAZIONE_SISMICA : {}\n", wmsCapabilities);
        StringWriter writer = new StringWriter();
        wmsContext.acquireMarshaller().marshal(wmsCapabilities, writer);
        logger.info("######################WMSGetCapabilitiesV111_CLASSIFICAZIONE_SISMICA-String : \n{}\n", writer);
    }

    @Test
    public void i_unmarshallWMSGetCapabilitiesV111Test() throws Exception {
        XMLReader xmlReader = spf.newSAXParser().getXMLReader();
        InputSource inputSource = new InputSource(new FileReader(wmsGetCapabilitiesIspraCoste));
        SAXSource source = new SAXSource(xmlReader, inputSource);
        WMTMSCapabilities wmsCapabilities = (WMTMSCapabilities) wmsContext.acquireUnmarshaller().unmarshal(source);
        logger.debug("#######################WMSGetCapabilitiesV111_ISPRA_COSTE : {}\n", wmsCapabilities);
        StringWriter writer = new StringWriter();
        wmsContext.acquireMarshaller().marshal(wmsCapabilities, writer);
        logger.info("######################WMSGetCapabilitiesV111_ISPRA_COSTE-String : \n{}\n", writer);
    }

    @Test
    public void l_unmarshallWMSGetCapabilitiesV111Test() throws Exception {
        XMLReader xmlReader = spf.newSAXParser().getXMLReader();
        InputSource inputSource = new InputSource(new FileReader(wmsGetCapabilitiesIspra));
        SAXSource source = new SAXSource(xmlReader, inputSource);
        WMTMSCapabilities wmsCapabilities = (WMTMSCapabilities) wmsContext.acquireUnmarshaller().unmarshal(source);
        logger.debug("#######################WMSGetCapabilitiesV111_ISPRA : {}\n", wmsCapabilities);
        StringWriter writer = new StringWriter();
        wmsContext.acquireMarshaller().marshal(wmsCapabilities, writer);
        logger.info("######################WMSGetCapabilitiesV111_ISPRA-String : \n{}\n", writer);
    }

    @Test
    public void m_unmarshallWMSGetCapabilitiesV111Test() throws Exception {
        XMLReader xmlReader = spf.newSAXParser().getXMLReader();
        InputSource inputSource = new InputSource(new FileReader(wmsGetCapabilitiesIspraNatura));
        SAXSource source = new SAXSource(xmlReader, inputSource);
        WMTMSCapabilities wmsCapabilities = (WMTMSCapabilities) wmsContext.acquireUnmarshaller().unmarshal(source);
        logger.debug("#######################WMSGetCapabilitiesV111_ISPRA_NATURA : {}\n", wmsCapabilities);
        StringWriter writer = new StringWriter();
        wmsContext.acquireMarshaller().marshal(wmsCapabilities, writer);
        logger.info("######################WMSGetCapabilitiesV111_ISPRA_NATURA-String : \n{}\n", writer);
    }

    @Test
    public void n_unmarshallWMSGetCapabilitiesV111Test() throws Exception {
        XMLReader xmlReader = spf.newSAXParser().getXMLReader();
        InputSource inputSource = new InputSource(new FileReader(wmsServiziProtezioneCivile));
        SAXSource source = new SAXSource(xmlReader, inputSource);
        WMTMSCapabilities wmsCapabilities = (WMTMSCapabilities) wmsContext.acquireUnmarshaller().unmarshal(source);
        logger.debug("#######################WMSGetCapabilitiesV111_SERVIZI_PROTEZIONE_CIVILE : {}\n", wmsCapabilities);
        StringWriter writer = new StringWriter();
        wmsContext.acquireMarshaller().marshal(wmsCapabilities, writer);
        logger.info("######################WMSGetCapabilitiesV111_PROTEZIONE_CIVILE-String : \n{}\n", writer);
    }

    @Test
    public void o_unmarshallWMSGetCapabilitiesV111Test() throws Exception {
        XMLReader xmlReader = spf.newSAXParser().getXMLReader();
        InputSource inputSource = new InputSource(new FileReader(wmsGetCapabilitiesProsit));
        SAXSource source = new SAXSource(xmlReader, inputSource);
        WMTMSCapabilities wmsCapabilities = (WMTMSCapabilities) wmsContext.acquireUnmarshaller().unmarshal(source);
        logger.debug("#######################WMSGetCapabilitiesV111_PROSIT : {}\n", wmsCapabilities);
        StringWriter writer = new StringWriter();
        wmsContext.acquireMarshaller().marshal(wmsCapabilities, writer);
        logger.info("######################WMSGetCapabilitiesV111_PROSIT-String : \n{}\n", writer);
    }

    @Test
    public void p_unmarshallWMSGetCapabilitiesV111Test() throws Exception {
        XMLReader xmlReader = spf.newSAXParser().getXMLReader();
        InputSource inputSource = new InputSource(new FileReader(wmsGetCapabilitiesFile1));
        SAXSource source = new SAXSource(xmlReader, inputSource);
        WMTMSCapabilities wmsCapabilities = (WMTMSCapabilities) wmsContext.acquireUnmarshaller().unmarshal(source);
        StringWriter writer = new StringWriter();
        wmsContext.acquireMarshaller().marshal(wmsCapabilities, writer);
        logger.info("######################WMSGetCapabilitiesV111-String : \n{}\n", writer);
    }

    @Test
    public void q_unmarshallWMSGetCapabilitiesV111Test() throws Exception {
        XMLReader xmlReader = spf.newSAXParser().getXMLReader();
        InputSource inputSource = new InputSource(new FileReader(wmsGetCapabilitiesCnrIrea));
        SAXSource source = new SAXSource(xmlReader, inputSource);
        WMTMSCapabilities wmsCapabilities = (WMTMSCapabilities) wmsContext.acquireUnmarshaller().unmarshal(source);
        StringWriter writer = new StringWriter();
        wmsContext.acquireMarshaller().marshal(wmsCapabilities, writer);
        logger.info("######################WMSGetCapabilitiesV111-String : \n{}\n", writer);
    }
}