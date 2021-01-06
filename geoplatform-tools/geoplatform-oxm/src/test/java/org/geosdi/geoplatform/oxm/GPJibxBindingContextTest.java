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

import org.geosdi.geoplatform.mock.ClassToXMLMap;
import org.geosdi.geoplatform.oxm.jibx.GPJibxBindingContext;
import org.geosdi.geoplatform.oxm.jibx.GPJibxBindingContextImpl;
import org.jibx.runtime.IMarshallingContext;
import org.jibx.runtime.IUnmarshallingContext;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class GPJibxBindingContextTest {

    private static final Logger logger = LoggerFactory.getLogger(GPJibxBindingContextTest.class);
    //
    private static final GPJibxBindingContext jibxBindingContext = new GPJibxBindingContextImpl(ClassToXMLMap.class);
    //
    private ClassToXMLMap message;

    @Before
    public void onSetUp() throws Exception {
        message = new ClassToXMLMap();
        message.setData("I am data for Binding Context JIBX");
        message.setHistory("in the past or in the future");
    }

    @Test
    public void jibxBindingTest() throws Exception {
        String jibxFile = "target/jibx-binding.xml";
        File f = new File(jibxFile);
        IMarshallingContext marshallingContext = jibxBindingContext.createMarshallingContext();
        marshallingContext.setIndent(2);
        marshallingContext.setOutput(new FileOutputStream(f), "UTF-8");
        marshallingContext.marshalDocument(message);
        IUnmarshallingContext unmarshallingContext = jibxBindingContext.createUnmarshallingContext();
        ClassToXMLMap jibxMap = (ClassToXMLMap) unmarshallingContext.unmarshalDocument(new DataInputStream(new FileInputStream(f)), "UTF-8");
        Assert.assertNotNull(jibxMap);
        logger.info("JIBX BINDING CONTEXT BEAN *************** {}", jibxMap + "\n");
    }
}