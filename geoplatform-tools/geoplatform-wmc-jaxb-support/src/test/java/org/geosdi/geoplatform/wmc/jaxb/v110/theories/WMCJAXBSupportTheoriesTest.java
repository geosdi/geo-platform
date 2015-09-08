package org.geosdi.geoplatform.wmc.jaxb.v110.theories;

import org.geosdi.geoplatform.wmc.support.v110.jaxb.context.WMCJAXBContextV110;
import org.geosdi.geoplatform.wmc.support.v110.jaxb.repository.WMCJAXBContextProviderV110;
import org.geosdi.geoplatform.wmc.support.v110.jaxb.repository.WMCJAXBContextSupport;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.StringWriter;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@RunWith(Theories.class)
public class WMCJAXBSupportTheoriesTest extends AbstractWMCJAXBTheoriesTest {

    static {
        wmcJAXBContext = WMCJAXBContextSupport
                .getProvider(WMCJAXBContextProviderV110.WMC_CONTEXT_KEY_V110);
    }

    //
    private final static WMCJAXBContextV110 wmcJAXBContext;

    @Theory
    public void wmcJaxbSupportTest(String fileName) throws Exception {
        String wmcFileString = dirFiles + fileName;
        File wmcFile = new File(wmcFileString);

        Unmarshaller unmarshaller = wmcJAXBContext.acquireUnmarshaller();
        Object o = unmarshaller.unmarshal(wmcFile);

        logger.info("#####################{}\n", (o instanceof JAXBElement) ? ((JAXBElement) o).getValue() : o);
        Marshaller marshaller = wmcJAXBContext.acquireMarshaller();
        StringWriter writer = new StringWriter();
        marshaller.marshal((o instanceof JAXBElement) ? ((JAXBElement) o).getValue() : o, writer);
        logger.info("###########################\n{}\n\n", writer);
    }
}
