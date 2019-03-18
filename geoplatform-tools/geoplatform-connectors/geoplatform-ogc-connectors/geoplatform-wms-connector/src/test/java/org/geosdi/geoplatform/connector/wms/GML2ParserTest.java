package org.geosdi.geoplatform.connector.wms;

import org.geosdi.geoplatform.jaxb.GPJAXBContextBuilder;
import org.geosdi.geoplatform.xml.gml.v212.*;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
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
import static org.junit.runners.MethodSorters.NAME_ASCENDING;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@FixMethodOrder(value = NAME_ASCENDING)
public class GML2ParserTest {

    private static final Logger logger = LoggerFactory.getLogger(GML2ParserTest.class);

    //
    static {
        jaxbContextBuilder = GPJAXBContextBuilder.newInstance();
    }

    //
    private static final GPJAXBContextBuilder jaxbContextBuilder;
    private static File file;
    private static File file1;
    private static File file2;
    private static File file3;
    private static File file4;
    private static File file5;

    @BeforeClass
    public static void beforeClass() throws Exception {
        String basePath = of(new File(".").getCanonicalPath(), "src", "test", "resources", "stax")
                .collect(joining(separator, "", separator));
        file = new File(basePath.concat("multiPolygon-Vigneti.xml"));
        file1 = new File(basePath.concat("multiPolygon.xml"));
        file2 = new File(basePath.concat("point.xml"));
        file3 = new File(basePath.concat("multyLineString.xml"));
        file4 = new File(basePath.concat("lineString.xml"));
        file5 = new File(basePath.concat("multiPolygon-Corine.xml"));
    }

    @Test
    public void a_readGML2GeometryAsJTSGeometryTest() throws Exception {
        GMLReader gmlReader = new GMLReader();
        logger.info("#########################JTS_GEOMETRY : {}\n", gmlReader.read(new FileReader(file), new GeometryFactory()));
    }

    @Test
    public void b_unmarshallerTest() throws Exception {
        AbstractGeometryType geometryCollectionType = jaxbContextBuilder.unmarshal(new FileReader(file), GeometryCollectionType.class);
        logger.info("######################UNMARSHALL_GML2_GEOMETRY : {}\n", geometryCollectionType.getClass().getSimpleName());
    }

    @Test
    public void c_readGML2GeometryAsJTSGeometryTest() throws Exception {
        GMLReader gmlReader = new GMLReader();
        logger.info("#########################JTS_GEOMETRY : {}\n", gmlReader.read(new FileReader(file1), new GeometryFactory()));
    }

    @Test
    public void d_unmarshallerTest() throws Exception {
        AbstractGeometryType geometryCollectionType = jaxbContextBuilder.unmarshal(new FileReader(file1), GeometryCollectionType.class);
        logger.info("######################UNMARSHALL_GML2_GEOMETRY : {}\n", geometryCollectionType.getClass().getSimpleName());
    }

    @Test
    public void e_readGML2GeometryAsJTSGeometryTest() throws Exception {
        GMLReader gmlReader = new GMLReader();
        logger.info("#########################JTS_GEOMETRY : {}\n", gmlReader.read(new FileReader(file2), new GeometryFactory()));
    }

    @Test
    public void f_unmarshallerTest() throws Exception {
        AbstractGeometryType pointType = jaxbContextBuilder.unmarshal(new FileReader(file2), PointType.class);
        logger.info("######################UNMARSHALL_GML2_GEOMETRY : {}\n", pointType);
    }

    @Test
    public void g_readGML2GeometryAsJTSGeometryTest() throws Exception {
        GMLReader gmlReader = new GMLReader();
        logger.info("#########################JTS_GEOMETRY : {}\n", gmlReader.read(new FileReader(file3), new GeometryFactory()));
    }

    @Test
    public void h_unmarshallerTest() throws Exception {
        AbstractGeometryType multiLineString = jaxbContextBuilder.unmarshal(new FileReader(file3), MultiLineStringType.class);
        logger.info("######################UNMARSHALL_GML2_GEOMETRY : {}\n", multiLineString);
    }

    @Test
    public void i_readGML2GeometryAsJTSGeometryTest() throws Exception {
        GMLReader gmlReader = new GMLReader();
        logger.info("#########################JTS_GEOMETRY : {}\n", gmlReader.read(new FileReader(file4), new GeometryFactory()));
    }

    @Test
    public void l_unmarshallerTest() throws Exception {
        AbstractGeometryType lineString = jaxbContextBuilder.unmarshal(new FileReader(file4), LineStringType.class);
        logger.info("######################UNMARSHALL_GML2_GEOMETRY : {}\n", lineString);
    }

    @Test
    public void m_readGML2GeometryAsJTSGeometryTest() throws Exception {
        GMLReader gmlReader = new GMLReader();
        logger.info("#########################JTS_GEOMETRY : {}\n", gmlReader.read(new FileReader(file5), new GeometryFactory()));
    }

    @Test
    public void n_unmarshallerTest() throws Exception {
        AbstractGeometryType multiPolygon = jaxbContextBuilder.unmarshal(new FileReader(file5), MultiPointType.class);
        logger.info("######################UNMARSHALL_GML2_GEOMETRY : {}\n", multiPolygon);
    }
}