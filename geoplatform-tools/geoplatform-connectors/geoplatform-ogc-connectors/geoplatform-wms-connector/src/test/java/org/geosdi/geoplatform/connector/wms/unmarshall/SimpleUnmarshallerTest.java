package org.geosdi.geoplatform.connector.wms.unmarshall;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.geosdi.geoplatform.jaxb.GPJAXBContextBuilder;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.File;
import java.io.Serializable;

import static java.io.File.separator;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Stream.of;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class SimpleUnmarshallerTest {

    private static final Logger logger = LoggerFactory.getLogger(SimpleUnmarshallerTest.class);
    //
    private static final GPJAXBContextBuilder jaxbContextBuilder = GPJAXBContextBuilder.newInstance();

    @Test
    public void simpleTest() throws Exception {
        String basePath = of(new File(".").getCanonicalPath(), "src", "test", "resources")
                .collect(joining(separator, "", separator));
        File file = new File(basePath.concat("test.xml"));
        SimpleBean simpleBean = jaxbContextBuilder.unmarshal(file, SimpleBean.class);
        logger.info("#######################SimpleBean : {}\n", simpleBean);
    }

    @Getter
    @Setter
    @ToString
    @XmlRootElement(name = "root")
    @XmlAccessorType(XmlAccessType.FIELD)
    private static class SimpleBean implements Serializable {

        private static final long serialVersionUID = -6760414143134662079L;
        //
        private String name;
        @XmlElement(name = "sur.name")
        private String surname;
    }
}
