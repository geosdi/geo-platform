package org.geosdi.geoplatform.gml.impl.v311.gml;

import org.geosdi.geoplatform.gml.api.parser.base.geometry.multi.line.GMLBaseMultiLineStringParser;
import org.geosdi.geoplatform.gml.api.parser.base.parameter.GMLBaseParametersRepo;
import org.geosdi.geoplatform.jaxb.GPJAXBContextBuilder;
import org.geosdi.geoplatform.xml.gml.v311.MultiLineStringType;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

import static java.io.File.separator;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Stream.of;
import static org.junit.runners.MethodSorters.NAME_ASCENDING;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@FixMethodOrder(value = NAME_ASCENDING)
public class GMLMultiLineStringParserTest {

    private static final Logger logger = LoggerFactory.getLogger(GMLMultiLineStringParserTest.class);
    //
    private static final GMLBaseMultiLineStringParser multiLineStringParser = GMLBaseParametersRepo.getDefaultMultiLineStringParser();
    private static File file;
    private static File file1;

    @BeforeClass
    public static void beforeClass() throws Exception {
        String basePath = of(new File(".").getCanonicalPath(), "src", "test", "resources")
                .collect(joining(separator, "", separator));
        file = new File(basePath.concat("MultiLineString.xml"));
        file1 = new File(basePath.concat("MultiLineString_srsDimension3.xml"));
    }

    @Test
    public void a_multiLineStringJTSParserTest() throws Exception {
        MultiLineStringType multiLineStringType = GPJAXBContextBuilder.newInstance().unmarshal(file, MultiLineStringType.class);
        logger.info("##########################MULTI_LINE_STRING_JTS : {}\n", multiLineStringParser.parseGeometry(multiLineStringType));
    }

    @Test
    public void b_multiLineStringJTSParserTest() throws Exception {
        MultiLineStringType multiLineStringType = GPJAXBContextBuilder.newInstance().unmarshal(file1, MultiLineStringType.class);
        logger.info("##########################MULTI_LINE_STRING_JTS : {}\n", multiLineStringParser.parseGeometry(multiLineStringType));
    }
}
