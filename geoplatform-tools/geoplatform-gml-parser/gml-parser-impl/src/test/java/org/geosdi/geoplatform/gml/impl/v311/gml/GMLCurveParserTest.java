package org.geosdi.geoplatform.gml.impl.v311.gml;

import org.geosdi.geoplatform.gml.api.parser.base.geometry.curve.GMLBaseCurveParser;
import org.geosdi.geoplatform.gml.api.parser.base.parameter.GMLBaseParametersRepo;
import org.geosdi.geoplatform.jaxb.GPJAXBContextBuilder;
import org.geosdi.geoplatform.xml.gml.v311.CurveType;
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
public class GMLCurveParserTest {

    private static final Logger logger = LoggerFactory.getLogger(GMLCurveParserTest.class);
    //
    private static final GMLBaseCurveParser curveParser = GMLBaseParametersRepo.getDefaultCurveParser();
    private static File file;

    @BeforeClass
    public static void beforeClass() throws Exception {
        String basePath = of(new File(".").getCanonicalPath(), "src", "test", "resources", "Curve.xml")
                .collect(joining(File.separator));
        file = new File(basePath);
    }

    @Test
    public void curveJTSParserTest() throws Exception {
        CurveType curveType = GPJAXBContextBuilder.newInstance().unmarshal(file, CurveType.class);
        logger.info("##########################MULTI_LINE_STRING_JTS : {}\n", curveParser.parseGeometry(curveType));
    }
}