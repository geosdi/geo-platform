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
package org.geosdi.geoplatform.oxm;

import java.io.File;
import java.io.IOException;
import org.geosdi.geoplatform.gui.oxm.model.yahoo.GPYahooGeocodeRoot;
import org.geosdi.geoplatform.oxm.jaxb.GPJaxbMarshaller;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 *
 * @author Michele Santomauro - CNR IMAA geoSDI Group
 * @email michele.santomauro@geosdi.org
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
            locations = {"classpath:org/geosdi/geoplatform/gui/applicationContext-TEST.xml"})
    public class YahooModelTest {

        private final Logger logger = LoggerFactory.getLogger(this.getClass());
        //
        @Autowired
        private GPJaxbMarshaller geocoderYahooJaxbMarshaller;

        @Test
        public void testGeocoding() {
            try {
                String sourceFileUrl = new File(".").getCanonicalPath() + File.separator
                        + "src/test/resources/yahooGeocodeExample.xml";

                File source = new File(sourceFileUrl);
                if (!source.canRead()) {
                    throw new IllegalArgumentException(
                            "Source path " + sourceFileUrl + " is not valid");
                }

                GPYahooGeocodeRoot geocode = (GPYahooGeocodeRoot) geocoderYahooJaxbMarshaller.
                        unmarshal(source);
                Assert.assertNotNull("The geocode is null", geocode);

                logger.info(
                        "\n\n\t GeoPlatform Yahoo geocoding OXM parsing : {}\n\n",
                        geocode);
            } catch (IOException ex) {
                Assert.fail(ex.getMessage());
            }
        }

        @Test
        public void testReverseGeocoding() {
            try {
                String sourceFileUrl = new File(".").getCanonicalPath() + File.separator
                        + "src/test/resources/yahooReverseGeocodeExample.xml";

                File source = new File(sourceFileUrl);
                if (!source.canRead()) {
                    throw new IllegalArgumentException(
                            "Source path " + sourceFileUrl + " is not valid");
                }

                GPYahooGeocodeRoot geocode = (GPYahooGeocodeRoot) geocoderYahooJaxbMarshaller.
                        unmarshal(source);
                Assert.assertNotNull("The geocode is null", geocode);

                logger.info(
                        "\n\n\t GeoPlatform Yahoo reverse geocoding OXM parsing : {}\n\n",
                        geocode);
            } catch (IOException ex) {
                Assert.fail(ex.getMessage());
            }
        }

    }
