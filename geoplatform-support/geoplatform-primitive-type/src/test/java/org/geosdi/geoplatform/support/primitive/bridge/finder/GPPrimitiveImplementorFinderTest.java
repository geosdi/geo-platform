package org.geosdi.geoplatform.support.primitive.bridge.finder;

import org.geosdi.geoplatform.support.bridge.finder.GPImplementorFinder;
import org.geosdi.geoplatform.support.primitive.bridge.implementor.PrimitiveImplementor;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Set;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class GPPrimitiveImplementorFinderTest {

    private static final Logger logger = LoggerFactory.getLogger(GPPrimitiveImplementorFinderTest.class);
    //
    private static final GPImplementorFinder<PrimitiveImplementor<?>> primitiveImplementorFinder = new GPPrimitiveImplementorFinder<>();

    @Test
    public void a_getAllImplementorsTest() throws Exception {
        Set<PrimitiveImplementor<?>> primitiveImplementors = primitiveImplementorFinder.getAllImplementors();
        logger.info("##########################PRIMITIVE_IMPLEMENTORS : {}\n", primitiveImplementors.size());
    }

    @Test
    public void b_firstReload() throws Exception {
        primitiveImplementorFinder.reload();
        logger.info("###########################{}\n", primitiveImplementorFinder.getValidImplementors().size());
    }

    @Test
    public void c_getAllImplementors1Test() throws Exception {
        Set<PrimitiveImplementor<?>> primitiveImplementors = primitiveImplementorFinder.getAllImplementors();
        logger.info("##########################PRIMITIVE_IMPLEMENTORS : {}\n", primitiveImplementors.size());

    }

    @Test
    public void d_secondReload() throws Exception {
        primitiveImplementorFinder.reload();
        logger.info("###########################{}\n", primitiveImplementorFinder.getValidImplementors().size());
    }

    @Test
    public void getAllImplementors2Test() throws Exception {
        Set<PrimitiveImplementor<?>> primitiveImplementors = primitiveImplementorFinder.getAllImplementors();
        logger.info("##########################PRIMITIVE_IMPLEMENTORS : {}\n", primitiveImplementors.size());
    }
}
