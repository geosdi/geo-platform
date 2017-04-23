package org.geosdi.geoplatform.jaxb.comparison.task;

import org.geosdi.geoplatform.jaxb.GPJAXBContextBuilder;
import org.geosdi.geoplatform.jaxb.IGPJAXBContextBuilder;
import org.geosdi.geoplatform.jaxb.model.AttributeStore;
import org.geosdi.geoplatform.jaxb.model.Car;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.StringWriter;
import java.util.concurrent.Callable;

import static org.geosdi.geoplatform.jaxb.GPJAXBContextBuilderTest.createAttributes;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class GPJAXBContextBuilderTaskSimple implements Callable<Long> {

    private static final Logger logger = LoggerFactory.getLogger(GPJAXBContextBuilderTaskSimple.class);
    //
    private static final IGPJAXBContextBuilder GP_JAXB_CONTEXT_BUILDER = GPJAXBContextBuilder.newInstance();

    /**
     * Computes a result, or throws an exception if unable to do so.
     *
     * @return computed result
     * @throws Exception if unable to compute a result
     */
    @Override
    public Long call() throws Exception {
        long start = System.currentTimeMillis();
        logger.debug("###########################UNMARSHALL_CAR_FROM_FILE_SIMPLE : {}\n", GP_JAXB_CONTEXT_BUILDER
                .unmarshal(new File("./src/test/resources/Car.xml"), Car.class));
        AttributeStore attributeStore = new AttributeStore();
        attributeStore.setAttributes(createAttributes(25));
        StringWriter writer = new StringWriter();
        GP_JAXB_CONTEXT_BUILDER.marshal(attributeStore, writer);
        logger.debug("@@@@@@@@@@@@@@@@@@@@@@@@@@@@MARSHALL_ATTRIBUTE_STORE_AS_STRING_SIMPLE : \n{}\n", writer.toString());
        return System.currentTimeMillis() - start;
    }
}