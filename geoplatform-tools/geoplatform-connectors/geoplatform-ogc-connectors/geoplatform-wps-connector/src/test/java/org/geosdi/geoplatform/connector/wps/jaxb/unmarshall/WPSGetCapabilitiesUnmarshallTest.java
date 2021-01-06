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
package org.geosdi.geoplatform.connector.wps.jaxb.unmarshall;

import org.geosdi.geoplatform.connector.jaxb.context.WPSJAXBContext;
import org.geosdi.geoplatform.xml.wps.v100.WPSCapabilitiesType;
import org.junit.BeforeClass;
import org.junit.experimental.theories.DataPoints;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.Unmarshaller;
import java.io.File;

import static javax.xml.bind.JAXBContext.newInstance;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@RunWith(Theories.class)
public class WPSGetCapabilitiesUnmarshallTest {

    private static final Logger logger = LoggerFactory.getLogger(WPSGetCapabilitiesUnmarshallTest.class);

    //
    static {
        try {
            wpsJAXBContext = new WPSJAXBContext(newInstance(WPSCapabilitiesType.class));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private static WPSJAXBContext wpsJAXBContext;
    private static String dirFiles;

    @BeforeClass
    public static void buildDirFiles() throws Exception {
        dirFiles = new File(".").getCanonicalPath() + File.separator
                + "src/test/resources/unmarshall/capabilities/";
    }

    @DataPoints
    public static String[] data() {
        return new String[]{"WPSGetCapabilitiesV100.xml", "WPSHttpsGetCapabilitiesV100.xml"};
    }

    @Theory
    public void unmarshallWPSGetCapabilitiesTest(String fileName) throws Exception {
        String getCapabilitiesFile = dirFiles.concat(fileName);
        File file = new File(getCapabilitiesFile);
        Unmarshaller unmarshaller = wpsJAXBContext.acquireUnmarshaller();
        JAXBElement<WPSCapabilitiesType> jaxbElement = (JAXBElement<WPSCapabilitiesType>) unmarshaller.unmarshal(file);
        logger.info("##########################WPS_CAPABILITIES_TYPE : \n{}\n for File : {}\n",
                jaxbElement.getValue(), fileName);
    }
}
