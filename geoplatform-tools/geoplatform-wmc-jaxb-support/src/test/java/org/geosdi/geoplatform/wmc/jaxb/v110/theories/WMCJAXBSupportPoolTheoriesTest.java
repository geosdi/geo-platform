package org.geosdi.geoplatform.wmc.jaxb.v110.theories;

import org.geosdi.geoplatform.wmc.support.v110.jaxb.context.pool.WMCJAXBContextPoolV110;
import org.geosdi.geoplatform.wmc.support.v110.jaxb.repository.WMCJAXBContextProviderPoolV110;
import org.geosdi.geoplatform.wmc.support.v110.jaxb.repository.WMCJAXBContextSupport;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;

import javax.xml.bind.JAXBElement;
import java.io.File;
import java.io.StringWriter;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@RunWith(Theories.class)
public class WMCJAXBSupportPoolTheoriesTest extends AbstractWMCJAXBTheoriesTest {

    static {
        wmcJAXBContextPool = WMCJAXBContextSupport
                .getProvider(WMCJAXBContextProviderPoolV110.WMC_CONTEXT_POOL_KEY_V110);
    }

    //
    private final static WMCJAXBContextPoolV110 wmcJAXBContextPool;

    @Theory
    public void wmcJaxbSupportPoolTest(String fileName) throws Exception {
        String wmcFileString = dirFiles + fileName;
        File wmcFile = new File(wmcFileString);

        Object o = wmcJAXBContextPool.unmarshal(wmcFile);

        logger.info("#####################{}\n", (o instanceof JAXBElement) ? ((JAXBElement) o).getValue() : o);
        StringWriter writer = new StringWriter();
        wmcJAXBContextPool.marshal((o instanceof JAXBElement) ? ((JAXBElement) o).getValue() : o, writer);
        logger.info("###########################\n{}\n\n", writer);
    }
}
