package org.geosdi.geoplatform.connector.wms.stax;

import org.geosdi.geoplatform.connector.reader.stax.GPWMSGetFeatureInfoStaxReader;
import org.geosdi.geoplatform.connector.wms.WMSGetFeatureInfoReaderFileLoaderTest;
import org.junit.BeforeClass;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

import static java.io.File.separator;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Stream.of;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public abstract class WMSGetFeatureInfoStaxReaderTest extends WMSGetFeatureInfoReaderFileLoaderTest {

    protected final Logger logger = LoggerFactory.getLogger(this.getClass());
    //
    protected static final GPWMSGetFeatureInfoStaxReader wmsGetFeatureInfoStaxReader = new GPWMSGetFeatureInfoStaxReader();
}