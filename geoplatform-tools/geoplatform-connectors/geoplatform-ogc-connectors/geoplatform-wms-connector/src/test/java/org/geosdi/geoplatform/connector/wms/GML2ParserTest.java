package org.geosdi.geoplatform.connector.wms;

import org.geosdi.geoplatform.jaxb.GPJAXBContextBuilder;
import org.geosdi.geoplatform.xml.gml.v212.AbstractGeometryType;
import org.geosdi.geoplatform.xml.gml.v212.GeometryCollectionType;
import org.junit.BeforeClass;
import org.junit.Test;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.io.gml2.GMLReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileReader;

import static java.io.File.separator;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Stream.of;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class GML2ParserTest {

    private static final Logger logger = LoggerFactory.getLogger(GML2ParserTest.class);

    //
    static {
        jaxbContextBuilder = GPJAXBContextBuilder.newInstance();
    }

    //
    private static final GPJAXBContextBuilder jaxbContextBuilder;
    private static File file;

    @BeforeClass
    public static void beforeClass() throws Exception {
        String basePath = new File(".").getCanonicalPath();
        String fileName = of(basePath, "src", "test", "resources", "stax", "multiPolygon-Vigneti.xml")
                .collect(joining(separator));
        file = new File(fileName);
    }

    @Test
    public void readGML2GeometryAsJTSGeometryTest() throws Exception {
        GMLReader gmlReader = new GMLReader();
        logger.info("#########################ECCOLA : {}\n", gmlReader.read(new FileReader(file), new GeometryFactory()));
    }

    @Test
    public void unmarshallerTest() throws Exception {
        AbstractGeometryType geometryCollectionType = jaxbContextBuilder.unmarshal(new FileReader(file), GeometryCollectionType.class);
        logger.info("######################UNMARSHALL_GML2_GEOMETRY : {}\n", geometryCollectionType.getClass().getSimpleName());
    }
}