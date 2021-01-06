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
package org.geosdi.geoplatform.connector.wms.unmarshall;

import org.geosdi.geoplatform.connector.jaxb.context.WMSJAXBContext;
import org.geosdi.geoplatform.connector.jaxb.repository.JAXBContextConnectorRepository;
import org.geosdi.geoplatform.jaxb.GPBaseJAXBContext;
import org.geosdi.geoplatform.wms.v130.WMSCapabilities;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.StringWriter;

import static java.io.File.separator;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Stream.of;
import static org.geosdi.geoplatform.connector.WMSVersion.V130;
import static org.geosdi.geoplatform.connector.jaxb.repository.WMSConnectorJAXBContextV130.WMS_CONTEXT_KEY_V130;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.runners.MethodSorters.NAME_ASCENDING;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@FixMethodOrder(value = NAME_ASCENDING)
public class WMSV130UnmarshallTest {

    private static final Logger logger = LoggerFactory.getLogger(WMSV130UnmarshallTest.class);

    static {
        wmsContext = JAXBContextConnectorRepository.getProvider(WMS_CONTEXT_KEY_V130);
    }

    //
    private static final GPBaseJAXBContext wmsContext;
    private static File wmsGetCapabilitiesFile;
    private static File wmsGetCapabilitiesCartaFitoclimatica;
    private static File wmsGetCapabilitiesCartaGeolitologica;

    @BeforeClass
    public static void beforeClass() throws Exception {
        assertNotNull(wmsContext);
        assertTrue(wmsContext instanceof WMSJAXBContext);
        assertTrue(((WMSJAXBContext) wmsContext).getVersion() == V130);
        String basePath = of(new File(".").getCanonicalPath(), "src", "test", "resources")
                .collect(joining(separator, "", separator));
        wmsGetCapabilitiesFile = new File(basePath.concat("getcapabilities_1.3.0.xml"));
        wmsGetCapabilitiesCartaFitoclimatica = new File(basePath.concat("cartaFitoclimaticaV130.xml"));
        wmsGetCapabilitiesCartaGeolitologica = new File(basePath.concat("cartaGeolitologicaV130.xml"));
    }

    @Test
    public void a_unmarshallWMSGetCapabilitiesV130Test() throws Exception {
        WMSCapabilities wmsCapabilities = (WMSCapabilities) wmsContext.acquireUnmarshaller().unmarshal(wmsGetCapabilitiesFile);
        logger.info("#######################WMSGetCapabilitiesV130 : {}\n", wmsCapabilities);
        StringWriter writer = new StringWriter();
        wmsContext.acquireMarshaller().marshal(wmsCapabilities, new File("./target/WMSGetCapabilitiesV130.xml"));
        logger.debug("######################WMSGetCapabilitiesV130-String : \n{}\n", writer);
    }

    @Test
    public void b_unmarshallWMSGetCapabilitiesV130Test() throws Exception {
        WMSCapabilities wmsCapabilities = (WMSCapabilities) wmsContext.acquireUnmarshaller().unmarshal(wmsGetCapabilitiesCartaFitoclimatica);
        logger.info("#######################WMSGetCapabilitiesCartaFitoclimaticaV130 : {}\n", wmsCapabilities);
        StringWriter writer = new StringWriter();
        wmsContext.acquireMarshaller().marshal(wmsCapabilities, new File("./target/WMSGetCapabilitiesCartaFitoclimaticaV130.xml"));
        logger.debug("######################WMSGetCapabilitiesCartaFitoclimaticaV130-String : \n{}\n", writer);
    }

    @Test
    public void c_unmarshallWMSGetCapabilitiesV130Test() throws Exception {
        WMSCapabilities wmsCapabilities = (WMSCapabilities) wmsContext.acquireUnmarshaller().unmarshal(wmsGetCapabilitiesCartaGeolitologica);
        logger.info("#######################WMSGetCapabilitiesCartaGeologicaV130 : {}\n", wmsCapabilities);
        StringWriter writer = new StringWriter();
        wmsContext.acquireMarshaller().marshal(wmsCapabilities, new File("./target/WMSGetCapabilitiesCartaGeologicaV130.xml"));
        logger.debug("######################WMSGetCapabilitiesCartaGeologicaV130-String : \n{}\n", writer);
    }
}