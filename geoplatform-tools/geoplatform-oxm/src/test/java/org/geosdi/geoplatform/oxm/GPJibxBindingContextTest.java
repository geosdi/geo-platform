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