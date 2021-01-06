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
package org.geosdi.geoplatform.connector.wfs.jaxb.unmarshall;

import org.geosdi.geoplatform.connector.jaxb.context.WFSJAXBContext;
import org.geosdi.geoplatform.xml.wfs.v110.GetFeatureType;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import java.io.File;
import java.io.StringWriter;

import static java.io.File.separator;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Stream.of;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class WFSGetFeatureUnmarshallTest {

    private static final Logger logger = LoggerFactory.getLogger(WFSGetFeatureUnmarshallTest.class);

    static {
        try {
            wfsJAXBContext = new WFSJAXBContext(JAXBContext.newInstance(GetFeatureType.class));
        } catch (JAXBException ex) {
            logger.error("##############FAILED to Look UP JAXBContext for class : {}", WFSGetFeatureUnmarshallTest.class
                    .getSimpleName());
        }
    }

    private static WFSJAXBContext wfsJAXBContext;
    private static File wfsGetFeatureIsEqualToFile;
    private static File wfsMathGetFeatureFile;
    private static File wfsGetFeatureBBOXFile;
    private static File wfsGetFeatureBetweenFile;
    private static File wfsGetFeatureIntersectsFile;

    @BeforeClass
    public static void loadFile() throws Exception {
        String basePath = of(new File(".").getCanonicalPath(), "src", "test", "resources", "unmarshall")
                .collect(joining(separator, "", separator));
        wfsGetFeatureIsEqualToFile = new File(basePath.concat("wfsGetFeatureIsEqualTov110.xml"));
        wfsMathGetFeatureFile = new File(basePath.concat("wfsMathGetFeaturev110.xml"));
        wfsGetFeatureBBOXFile = new File(basePath.concat("wfsGetFeatureBBOXv110.xml"));
        wfsGetFeatureBetweenFile = new File(basePath.concat("wfsGetFeatureBetweenv110.xml"));
        wfsGetFeatureIntersectsFile = new File(basePath.concat("wfsGetFeatureIntersectsv110.xml"));
    }

    @Test
    public void wfsGetFeatureIsEqualTov110Test() throws Exception {
        GetFeatureType getFeatureType = ((JAXBElement<GetFeatureType>) wfsJAXBContext.acquireUnmarshaller()
                .unmarshal(wfsGetFeatureIsEqualToFile)).getValue();
        logger.info("#######################wfsGetFeatureIsEqualTov110Test : {}\n", getFeatureType);
        StringWriter writer = new StringWriter();
        wfsJAXBContext.acquireMarshaller().marshal(getFeatureType, writer);
        logger.debug("######################wfsGetFeatureIsEqualTov110Test-String : \n{}\n", writer);
    }

    @Test
    public void wfsMathGetFeaturev110Test() throws Exception {
        GetFeatureType getFeatureType = ((JAXBElement<GetFeatureType>) wfsJAXBContext.acquireUnmarshaller()
                .unmarshal(wfsMathGetFeatureFile)).getValue();
        logger.info("#######################wfsMathGetFeaturev110Test : {}\n", getFeatureType);
        StringWriter writer = new StringWriter();
        wfsJAXBContext.acquireMarshaller().marshal(getFeatureType, writer);
        logger.debug("######################wfsMathGetFeaturev110Test-String : \n{}\n", writer);
    }

    @Test
    public void wfsGetFeatureBBOXv110Test() throws Exception {
        GetFeatureType getFeatureType = ((JAXBElement<GetFeatureType>) wfsJAXBContext.acquireUnmarshaller()
                .unmarshal(wfsGetFeatureBBOXFile)).getValue();
        logger.info("#######################wfsGetFeatureBBOXv110Test : {}\n", getFeatureType);
        StringWriter writer = new StringWriter();
        wfsJAXBContext.acquireMarshaller().marshal(getFeatureType, writer);
        logger.debug("######################wfsGetFeatureBBOXv110Test-String : \n{}\n", writer);
    }

    @Test
    public void wfsGetFeatureBetweenv110Test() throws Exception {
        GetFeatureType getFeatureType = ((JAXBElement<GetFeatureType>) wfsJAXBContext.acquireUnmarshaller()
                .unmarshal(wfsGetFeatureBetweenFile)).getValue();
        logger.info("#######################wfsGetFeatureBetweenv110Test : {}\n", getFeatureType);
        StringWriter writer = new StringWriter();
        wfsJAXBContext.acquireMarshaller().marshal(getFeatureType, writer);
        logger.debug("######################wfsGetFeatureBetweenv110Test-String : \n{}\n", writer);
    }

    @Test
    public void wfsGetFeatureIntersectsv110Test() throws Exception {
        GetFeatureType getFeatureType = ((JAXBElement<GetFeatureType>) wfsJAXBContext.acquireUnmarshaller()
                .unmarshal(wfsGetFeatureIntersectsFile)).getValue();
        logger.info("#######################wfsGetFeatureIntersectsv110Test : {}\n", getFeatureType);
        StringWriter writer = new StringWriter();
        wfsJAXBContext.acquireMarshaller().marshal(getFeatureType, writer);
        logger.debug("######################wfsGetFeatureIntersectsv110Test-String : \n{}\n", writer);
    }
}