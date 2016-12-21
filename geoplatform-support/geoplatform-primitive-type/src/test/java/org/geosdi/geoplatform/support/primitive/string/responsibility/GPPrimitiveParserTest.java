package org.geosdi.geoplatform.support.primitive.string.responsibility;

import org.geosdi.geoplatform.support.primitive.string.responsibility.IGPPrimitiveParserHandlerManager.GPPrimitiveParserHandlerManager;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class GPPrimitiveParserTest {

    private static final Logger logger = LoggerFactory.getLogger(GPPrimitiveParserTest.class);
    //
    private static final IGPPrimitiveParserHandlerManager primitiveParserHandlerManager = new GPPrimitiveParserHandlerManager();

    @Test
    public void parseStringTest() throws Exception {
        logger.info("#######################PARSE_STRING : {}\n",
                primitiveParserHandlerManager.parseValue("This is a Simple Test").getSimpleName());
    }

    @Test
    public void parseIntegerTest() throws Exception {
        logger.info("#######################PARSE_INTEGER : {}\n",
                primitiveParserHandlerManager.parseValue("34").getSimpleName());
    }

    @Test
    public void parseDoubleTest() throws Exception {
        logger.info("#######################PARSE_DOUBLE : {}\n",
                primitiveParserHandlerManager.parseValue("34.89").getSimpleName());
    }

    @Test
    public void parseDateTimeTest() throws Exception {
        logger.info("#######################PARSE_DATE_TIME : {}\n",
                primitiveParserHandlerManager.parseValue("2001-07-04T12:08:56.235-0700").getSimpleName());
    }

    @Test
    public void parseDateTimeAnotherTest() throws Exception {
        logger.info("#######################PARSE_DATE_TIME : {}\n",
                primitiveParserHandlerManager.parseValue("2016-08-28 14:36:15").getSimpleName());
    }
}
