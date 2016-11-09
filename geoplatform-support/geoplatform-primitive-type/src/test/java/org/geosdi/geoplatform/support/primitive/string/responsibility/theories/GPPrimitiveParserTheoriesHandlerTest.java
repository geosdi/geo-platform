package org.geosdi.geoplatform.support.primitive.string.responsibility.theories;

import org.geosdi.geoplatform.support.primitive.string.responsibility.IGPPrimitiveParserHandlerManager;
import org.geosdi.geoplatform.support.primitive.string.responsibility.IGPPrimitiveParserHandlerManager.GPPrimitiveParserHandlerManager;
import org.junit.experimental.theories.DataPoints;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@RunWith(Theories.class)
public class GPPrimitiveParserTheoriesHandlerTest {

    private static final Logger logger = LoggerFactory.getLogger(GPPrimitiveParserTheoriesHandlerTest.class);
    //
    private static final IGPPrimitiveParserHandlerManager primitiveParserHandlerManager = new GPPrimitiveParserHandlerManager();

    @DataPoints
    public static String[] data() {
        return new String[]{"This is a test", "120", "130.89", "1200", "1450", "This is another Test",
                "2001.07.04 AD at 12:08:56 PDT", "Wed, Jul 4, '01", "12:08 PM",
                "12 o'clock PM, Pacific Daylight Time", "0:08 PM, PDT",
                "02001.July.04 AD 12:08 PM", "Wed, 4 Jul 2001 12:08:56 -0700",
                "010704120856-0700", "2001-07-04T12:08:56.235-0700",
                "16/08/1977", "This is an important Date.", "12898.89873482", "21-05-15", "Test1",
                "Test2", "1998"};
    }

    @Theory
    public void parseValueTest(String value) throws Exception {
        logger.info("##################################FOUND Classe : {} , for Value : {}\n",
                primitiveParserHandlerManager.parseValue(value), value);
    }
}
