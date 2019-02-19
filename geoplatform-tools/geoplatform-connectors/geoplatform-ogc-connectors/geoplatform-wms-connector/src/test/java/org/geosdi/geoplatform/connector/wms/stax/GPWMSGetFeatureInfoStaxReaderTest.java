package org.geosdi.geoplatform.connector.wms.stax;

import org.geosdi.geoplatform.connector.reader.stax.GPWMSGetFeatureInfoStaxReader;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.File;

import static java.io.File.separator;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Stream.of;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class GPWMSGetFeatureInfoStaxReaderTest {

    private static final GPWMSGetFeatureInfoStaxReader wmsGetFeatureInfoStaxReader = new GPWMSGetFeatureInfoStaxReader();
    private static File file;

    @BeforeClass
    public static void beforeClass() throws Exception {
        String basePath = new File(".").getCanonicalPath();
        String fileName = of(basePath, "src", "test", "resources", "stax", "geoserver-GetFeatureInfo.xml")
                .collect(joining(separator));
        file = new File(fileName);
    }

    @Test
    public void wmsGetFeatureInfoStaxReaderTest() throws Exception {
        wmsGetFeatureInfoStaxReader.read(file);
    }
}
