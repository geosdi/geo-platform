package org.geosdi.geoplatform.gml.impl.v311.gml;

import org.geosdi.geoplatform.gml.api.parser.base.geometry.multi.surface.GMLBaseMultiSurfaceParser;
import org.geosdi.geoplatform.gml.api.parser.base.parameter.GMLBaseParametersRepo;
import org.geosdi.geoplatform.jaxb.GPJAXBContextBuilder;
import org.geosdi.geoplatform.xml.gml.v311.MultiSurfaceType;
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
public class GMLMultiSurfaceParserTest {

    private static final Logger logger = LoggerFactory.getLogger(GMLMultiSurfaceParserTest.class);
    //
    private static final GMLBaseMultiSurfaceParser multiSurfaceParser = GMLBaseParametersRepo.getDefaultMultiSurfaceParser();
    private static File file;

    @BeforeClass
    public static void beforeClass() throws Exception {
        String basePath = of(new File(".").getCanonicalPath(), "src", "test", "resources", "MultiSurface.xml")
                .collect(joining(File.separator));
        file = new File(basePath);
    }

    @Test
    public void multiSurfaceJTSParserTest() throws Exception {
        MultiSurfaceType multiSurfaceType = GPJAXBContextBuilder.newInstance().unmarshal(file, MultiSurfaceType.class);
        logger.info("##########################MULTI_POLYGON_JTS : {}\n", multiSurfaceParser.parseGeometry(multiSurfaceType));
    }
}