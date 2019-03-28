package org.geosdi.geoplatform.gml.impl.v311.gml;

import org.geosdi.geoplatform.gml.api.parser.base.geometry.multi.point.GMLBaseMultiPointParser;
import org.geosdi.geoplatform.gml.api.parser.base.parameter.GMLBaseParametersRepo;
import org.geosdi.geoplatform.jaxb.GPJAXBContextBuilder;
import org.geosdi.geoplatform.xml.gml.v311.MultiPointType;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

import static java.util.stream.Collectors.joining;
import static java.util.stream.Stream.of;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class GMLMultiPointParserTest {

    private static final Logger logger = LoggerFactory.getLogger(GMLMultiPointParserTest.class);
    //
    private static final GMLBaseMultiPointParser multiPointParser = GMLBaseParametersRepo.getDefaultMultiPointParser();
    private static File file;

    @BeforeClass
    public static void beforeClass() throws Exception {
        String basePath = of(new File(".").getCanonicalPath(), "src", "test", "resources", "MultiPoint.xml")
                .collect(joining(File.separator));
        file = new File(basePath);
    }

    @Test
    public void multiPointJTSParserTest() throws Exception {
        MultiPointType multiPointType = GPJAXBContextBuilder.newInstance().unmarshal(file, MultiPointType.class);
        logger.info("##########################MULTI_POINT_JTS : {}\n", multiPointParser.parseGeometry(multiPointType));
    }
}