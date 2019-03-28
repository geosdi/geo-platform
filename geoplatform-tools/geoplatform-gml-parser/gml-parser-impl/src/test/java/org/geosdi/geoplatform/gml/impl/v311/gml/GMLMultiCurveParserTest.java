package org.geosdi.geoplatform.gml.impl.v311.gml;

import org.geosdi.geoplatform.gml.api.parser.base.geometry.multi.curve.GMLBaseMultiCurveParser;
import org.geosdi.geoplatform.gml.api.parser.base.parameter.GMLBaseParametersRepo;
import org.geosdi.geoplatform.jaxb.GPJAXBContextBuilder;
import org.geosdi.geoplatform.xml.gml.v311.MultiCurveType;
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
public class GMLMultiCurveParserTest {

    private static final Logger logger = LoggerFactory.getLogger(GMLMultiCurveParserTest.class);
    //
    private static final GMLBaseMultiCurveParser multiCurveParser = GMLBaseParametersRepo.getDefaultMultiCurveParser();
    private static File file;

    @BeforeClass
    public static void beforeClass() throws Exception {
        String basePath = of(new File(".").getCanonicalPath(), "src", "test", "resources", "MultiCurve.xml")
                .collect(joining(File.separator));
        file = new File(basePath);
    }

    @Test
    public void multiCurveJTSParserTest() throws Exception {
        MultiCurveType multiCurveType = GPJAXBContextBuilder.newInstance().unmarshal(file, MultiCurveType.class);
        logger.info("##########################MULTI_LINE_STRING_JTS : {}\n", multiCurveParser.parseGeometry(multiCurveType));
    }
}