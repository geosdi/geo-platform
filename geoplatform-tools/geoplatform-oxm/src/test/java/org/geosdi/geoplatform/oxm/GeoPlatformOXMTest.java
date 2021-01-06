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

import org.geosdi.geoplatform.GPGenericMarshaller;
import org.geosdi.geoplatform.mock.ClassToXMLMap;
import org.geosdi.geoplatform.oxm.jaxb.GenericJaxbMarshaller;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.File;
import java.io.IOException;

/**
 *
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext-Test.xml"})
public class GeoPlatformOXMTest {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    //
    @Autowired
    @Qualifier(value = "castor")
    private GPGenericMarshaller castor;
    //
    @Autowired
    private GenericJaxbMarshaller jax;
    //
    @Autowired
    @Qualifier(value = "xtream")
    private GPGenericMarshaller xtream;
    //
    @Autowired
    @Qualifier(value = "jibx")
    private GPGenericMarshaller jibx;
    //
    private ClassToXMLMap message;

    @Before
    public void onSetUp() throws Exception {
        message = new ClassToXMLMap();
        message.setData("I am data");
        message.setHistory("in the past");
    }

    @Test
    public void testCastor() throws Exception {
        String castorFile = "target/castor.xml";
        File f = new File(castorFile);

        castor.marshal(message, f);

        ClassToXMLMap castorMap = (ClassToXMLMap) castor.unmarshal(f);
        Assert.assertNotNull(castorMap);

        logger.info("CASTOR BEAN  ******************** {}", castorMap + "\n");

    }

    @Test
    public void testJaxB() throws Exception {
        String jaxbFile = "target/jaxb.xml";
        File f = new File(jaxbFile);

        jax.marshal(message, f);

        ClassToXMLMap jaxbMap = (ClassToXMLMap) jax.unmarshal(f);
        Assert.assertNotNull(jaxbMap);

        logger.info("JAX BEAN ***************** {}", jaxbMap + "\n");
    }

    @Test
    public void testXStream() throws Exception {
        String xtreamFile = "target/xtream.xml";
        File f = new File(xtreamFile);

        xtream.marshal(message, f);

        ClassToXMLMap xstreamMap = (ClassToXMLMap) xtream.
                unmarshal(f);

        Assert.assertNotNull(xstreamMap);

        logger.info("XSTREAM BEAN *************** {}", xstreamMap + "\n");
    }

    @Test
    public void testJibx() throws IOException {
        String jibxFile = "target/jibx.xml";
        File f = new File(jibxFile);

        jibx.marshal(message, f);

        ClassToXMLMap jibxMap = (ClassToXMLMap) jibx.unmarshal(f);

        Assert.assertNotNull(jibxMap);

        logger.info("JIXB BEAN *************** {}", jibxMap + "\n");
    }

}
