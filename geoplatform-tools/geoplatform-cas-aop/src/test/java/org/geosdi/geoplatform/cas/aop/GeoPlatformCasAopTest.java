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
package org.geosdi.geoplatform.cas.aop;

import it.geosolutions.geoserver.rest.GeoServerRESTPublisher;
import it.geosolutions.geoserver.rest.cas.GeoServerCASRESTPublisher;
import it.geosolutions.geoserver.rest.cas.GeoServerCASRESTReader;
import it.geosolutions.geoserver.rest.sldservice.Ramp;
import org.apache.commons.httpclient.NameValuePair;
import org.geosdi.geoplatform.services.IGPPublisherService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.io.File;
import java.lang.reflect.Method;
import java.net.URI;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:applicationContext.xml"})
@ActiveProfiles(value = {"gs_cas"})
public class GeoPlatformCasAopTest {

    private static final Logger logger = LoggerFactory.getLogger(GeoPlatformCasAopTest.class);
    //
    @Resource(name = "casPublisherService")
    private IGPPublisherService casPublisherService;
    @Resource(name = "sharedRestPublisher")
    private GeoServerRESTPublisher restPublisher;

    @Before
    public void setUp() throws Exception {
        Assert.assertNotNull("The CasPublisherService must not be null", this.casPublisherService);
        Assert.assertNotNull("The GeoServerRESTPublisher must not be null.", this.restPublisher);
    }

    @Test(expected = IllegalStateException.class)
    public void casAopExistsStyleTest() throws Exception {
        logger.info("####################################EXISTS_STYLE_RESULT : {}\n",
                this.casPublisherService.existsStyle("polygon"));
    }

    @Test(expected = IllegalStateException.class)
    public void casAopUpdateStyleTest() throws Exception {
        logger.info("############################UPDATE_STYLE_RESULT : {}\n",
                this.casPublisherService.updateStyle("PIPPO", "pippo", Boolean.FALSE));
    }

    @Test(expected = IllegalStateException.class)
    public void casPublishStyleTest() throws Exception {
        logger.info("############################UPDATE_STYLE_RESULT : {}\n",
                this.casPublisherService.publishStyle("PIPPO", "pippo", Boolean.FALSE));
    }

    @Test(expected = IllegalStateException.class)
    public void casPublishSHPTest() throws Exception {
        NameValuePair[] params = new NameValuePair[1];
        NameValuePair nameValuePair = new NameValuePair("charset", "UTF-8");
        params[0] = nameValuePair;
        File tempFile = new File("./src/test/resources/logback-test.xml");
        logger.info("########################################PUBLISH_SHP_RESULT : {}\n",
                this.restPublisher.publishShp("TEST",
                        "TEST", params, "", GeoServerRESTPublisher.UploadMethod.FILE,
                        tempFile.toURI(), "EPSG:4326", "TEST"));
    }

    @Test
    public void findGeoServerCASRESTPublisherMethodTest() throws Exception {
        Method method = GeoServerCASRESTPublisher.class.getMethod("publishShp", String.class, String.class,
                NameValuePair[].class, String.class, GeoServerRESTPublisher.UploadMethod.class,
                URI.class, String.class, String.class);
        Assert.assertNotNull(method);
    }

    @Test
    public void findGeoServerCASRESTReaderMethodTest() throws Exception {
        Method method = GeoServerCASRESTReader.class.getMethod("classifyVectorData", String.class, String.class,
                Ramp.class, Integer.class, it.geosolutions.geoserver.rest.sldservice.Method.class, Boolean.class,
                Boolean.class, Boolean.class, String.class, String.class, String.class, Boolean.class, String.class);
        Assert.assertNotNull(method);
    }
}
