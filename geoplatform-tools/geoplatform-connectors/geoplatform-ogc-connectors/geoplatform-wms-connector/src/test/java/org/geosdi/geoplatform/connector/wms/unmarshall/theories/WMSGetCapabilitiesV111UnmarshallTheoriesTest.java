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
package org.geosdi.geoplatform.connector.wms.unmarshall.theories;

import org.geosdi.geoplatform.connector.jaxb.context.WMSJAXBContext;
import org.geosdi.geoplatform.connector.jaxb.repository.JAXBContextConnectorRepository;
import org.geosdi.geoplatform.jaxb.GPBaseJAXBContext;
import org.geosdi.geoplatform.wms.v111.WMTMSCapabilities;
import org.junit.BeforeClass;
import org.junit.experimental.theories.DataPoints;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;

import javax.xml.parsers.SAXParserFactory;
import javax.xml.transform.sax.SAXSource;
import java.io.File;
import java.io.FileReader;
import java.io.StringWriter;

import static com.google.common.base.Preconditions.checkArgument;
import static java.io.File.separator;
import static java.lang.Boolean.FALSE;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Stream.of;
import static org.geosdi.geoplatform.connector.WMSVersion.V111;
import static org.geosdi.geoplatform.connector.jaxb.repository.WMSConnectorJAXBContextV111.WMS_CONTEXT_KEY_V111;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@RunWith(Theories.class)
public class WMSGetCapabilitiesV111UnmarshallTheoriesTest {

    private static final Logger logger = LoggerFactory.getLogger(WMSGetCapabilitiesV111UnmarshallTheoriesTest.class);

    static {
        wmsContext = JAXBContextConnectorRepository.getProvider(WMS_CONTEXT_KEY_V111);
    }

    private static String dirFiles;
    private static final SAXParserFactory spf = SAXParserFactory.newInstance();
    private static final GPBaseJAXBContext wmsContext;

    @BeforeClass
    public static void beforeClass() throws Exception {
        assertNotNull(wmsContext);
        assertTrue(wmsContext instanceof WMSJAXBContext);
        assertTrue(((WMSJAXBContext) wmsContext).getVersion() == V111);
        dirFiles = of(new File(".").getCanonicalPath(), "src", "test", "resources")
                .collect(joining(separator, "", separator));
        spf.setFeature("http://apache.org/xml/features/nonvalidating/load-external-dtd", FALSE);
        spf.setFeature("http://xml.org/sax/features/validation", FALSE);
    }

    @DataPoints
    public static String[] data() {
        return new String[]{"getcapabilities_1.1.1.xml", "ogc_v1.1.1.xml", "ogc.xml", "cartaFitoclimatica.xml",
                "cartaGeolitologica.xml", "catalogoFrane.xml", "classificazioneSismica2012.xml", "WMSIspraCoste.xml",
                "WMSIspraGetCapabilities.xml", "WMSIspraNatura.xml", "serviziProtezioneCivileGetCapV1.1.1.xml",
                "WMSPrositGetCapabilities.xml"};
    }

    @Theory
    public void wmsGetCapabilitiesV111UnmarshallTest(String fileName) throws Exception {
        checkArgument((fileName != null) && !(fileName.trim().isEmpty()), "The Parameter fileName must not be null or an empty string.");
        XMLReader xmlReader = spf.newSAXParser().getXMLReader();
        InputSource inputSource = new InputSource(new FileReader(dirFiles.concat(fileName)));
        SAXSource source = new SAXSource(xmlReader, inputSource);
        WMTMSCapabilities wmsCapabilities = (WMTMSCapabilities) wmsContext.acquireUnmarshaller().unmarshal(source);
        logger.info("#######################WMSGetCapabilitiesV111 : {}\n, for File : {}\n", wmsCapabilities, fileName);
        StringWriter writer = new StringWriter();
        wmsContext.acquireMarshaller().marshal(wmsCapabilities, writer);
        logger.debug("######################WMSGetCapabilitiesV111-String : \n{}\n, for File : {}\n", writer, fileName);
    }
}