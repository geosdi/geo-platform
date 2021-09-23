/**
 * geo-platform
 * Rich webgis framework
 * http://geo-platform.org
 * ====================================================================
 * <p>
 * Copyright (C) 2008-2020 geoSDI Group (CNR IMAA - Potenza - ITALY).
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
package org.geosdi.geoplatform.connector.jackson;

import org.geosdi.geoplatform.connector.geoserver.model.styles.GPGeoserverStyleBody;
import org.geosdi.geoplatform.connector.geoserver.model.styles.IGPGeoserverStyleBody;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Nonnull;
import java.io.StringReader;

import static com.google.common.base.Preconditions.checkArgument;
import static javax.annotation.meta.When.NEVER;
import static org.geosdi.geoplatform.connector.geoserver.styles.sld.GeoserverStyleSLDV100Request.JACKSON_JAXB_XML_SUPPORT;
import static org.geosdi.geoplatform.connector.jackson.GPGeoserverJacksonTest.jacksonSupport;
import static org.junit.runners.MethodSorters.NAME_ASCENDING;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@FixMethodOrder(value = NAME_ASCENDING)
public class GPGeoserverStyleBodyJacksonTest {

    private static final Logger logger = LoggerFactory.getLogger(GPGeoserverStyleBodyJacksonTest.class);

    @Test
    public void a_marshallGPGeoserverStyleBodyAsJsonStringTest() throws Exception {
        logger.info("####################GP_GEOSERVER_STYLE_BODY_AS_JSON_STRING : \n{}\n", jacksonSupport.getDefaultMapper()
                .writeValueAsString(toGPGeoserverStyleBody()));
    }

    @Test
    public void b_marshallGPGeoserverStyleBodyAsXmlStringTest() throws Exception {
        logger.info("@@@@@@@@@@@@@@@@@@@GP_GEOSERVER_STYLE_BODY_AS_XML_STRING : \n{}\n", JACKSON_JAXB_XML_SUPPORT.getDefaultMapper()
                .writeValueAsString(toGPGeoserverStyleBody()));
    }

    @Test
    public void c_unmarshallGPGeoserverStyleBodyRequestFromJsonStringTest() throws Exception {
        logger.info("###################GP_GEOSERVER_STYLE_BODY_FROM_JSON_STRING : {}\n", jacksonSupport.getDefaultMapper()
                .readValue(new StringReader("{\n"
                        + "  \"style\" : {\n"
                        + "    \"name\" : \"STYLE_TEST\",\n"
                        + "    \"filename\" : \"FILENAME_TEST\"\n"
                        + "  }\n"
                        + "}"), IGPGeoserverStyleBody.class));
    }

    @Test
    public void d_unmarshallGPGeoserverStyleBodyFromXmlStringTest() throws Exception {
        logger.info("@@@@@@@@@@@@@@@@@GP_GEOSERVER_STYLE_BODY_FROM_XML_STRING : {}\n", JACKSON_JAXB_XML_SUPPORT.getDefaultMapper()
                .readValue(new StringReader("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n"
                        + "<style>\n"
                        + "    <name>STYLE_TEST</name>\n"
                        + "    <filename>FILENAME_TEST</filename>\n"
                        + "</style>"), GPGeoserverStyleBody.class));
    }

    /**
     * @return {@link IGPGeoserverStyleBody}
     */
    IGPGeoserverStyleBody toGPGeoserverStyleBody() {
        IGPGeoserverStyleBody geoserverStyleBody = new GPGeoserverStyleBody();
        geoserverStyleBody.setStyleName("STYLE_TEST");
        geoserverStyleBody.setFileName("FILENAME_TEST");
        return geoserverStyleBody;
    }

    /**
     * @param theStyleName
     * @param theFileName
     * @return {@link IGPGeoserverStyleBody}
     */
    public static IGPGeoserverStyleBody toGPGeoserverStyleBody(@Nonnull(when = NEVER) String theStyleName, @Nonnull(when = NEVER) String theFileName) throws Exception {
        checkArgument((theStyleName != null) && !(theStyleName.trim().isEmpty()), "The Parameter styleName must not be null or an empty string.");
        checkArgument((theFileName != null) && !(theFileName.trim().isEmpty()), "The Parameter fileName must not be null or an empty string.");
        IGPGeoserverStyleBody geoserverStyleBody = new GPGeoserverStyleBody();
        geoserverStyleBody.setStyleName(theStyleName);
        geoserverStyleBody.setFileName(theFileName);
        return geoserverStyleBody;
    }
}