package org.geosdi.geoplatform.gml.impl.v311.geojson.theories;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.geosdi.geoplatform.gml.api.jaxb.context.GMLJAXBContext;
import org.geosdi.geoplatform.gml.api.parser.base.geometry.sextante.GMLBaseSextanteParser;
import org.geosdi.geoplatform.gml.api.parser.base.parameter.GMLBaseParametersRepo;
import org.geosdi.geoplatform.gml.impl.v311.jaxb.context.factory.GMLContextFactoryV311;
import org.geosdi.geoplatform.gml.impl.v311.jaxb.context.factory.GMLContextType;
import org.geosdi.geoplatform.support.jackson.GPJacksonSupport;
import org.geosdi.geoplatform.xml.gml.v311.AbstractGeometryType;
import org.junit.BeforeClass;
import org.junit.experimental.theories.DataPoints;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.bind.JAXBElement;
import java.io.File;
import java.io.IOException;

import static java.io.File.separator;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Stream.of;
import static org.geosdi.geoplatform.support.jackson.property.GPJacksonSupportEnum.*;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@RunWith(Theories.class)
public class GMLTheoriesGeoJsonSexanteParserTest {

    private static final Logger logger = LoggerFactory.getLogger(GMLTheoriesGeoJsonSexanteParserTest.class);
    //
    private static final GMLJAXBContext gmlJAXBContext = GMLContextFactoryV311.createJAXBContext(GMLContextType.SIMPLE);
    private static final ObjectMapper mapper = new GPJacksonSupport(UNWRAP_ROOT_VALUE_DISABLE,
            FAIL_ON_UNKNOW_PROPERTIES_DISABLE, ACCEPT_SINGLE_VALUE_AS_ARRAY_ENABLE, WRAP_ROOT_VALUE_DISABLE,
            INDENT_OUTPUT_ENABLE).getDefaultMapper();
    private static final GMLBaseSextanteParser sextanteParser = GMLBaseParametersRepo.getDefaultSextanteParser();
    private static String dirFiles;

    @BeforeClass
    public static void buildDirFiles() throws IOException {
        dirFiles = of(new File(".").getCanonicalPath(), "src", "test", "resources")
                .collect(joining(separator, "", separator));
    }

    @DataPoints
    public static String[] data() {
        return new String[]{
                "MultiCurve.xml", "Point.xml", "GeometryCollection.xml", "LineString.xml",
                "LinearRing.xml", "MultiLineString.xml", "MultiPoint.xml", "MultiPolygon.xml",
                "Polygon.xml", "Polygon_1.xml", "MultiSurface.xml", "MultiLineString_srsDimension3.xml", "Curve.xml"
        };
    }

    @Theory
    public void testGMLGeometry(String file) throws Exception {
        JAXBElement<AbstractGeometryType> jaxbElement = (JAXBElement<AbstractGeometryType>) gmlJAXBContext.acquireDefaultUnmarshaller().unmarshal(new File(dirFiles.concat(file)));
        AbstractGeometryType abstractGeometryType = jaxbElement.getValue();
        logger.debug("#######################################GML Geometry : {} -- GeoJson Geometry \n\n{}\n\n for File : {}\n",
                abstractGeometryType.getClass().getSimpleName(), mapper.writeValueAsString(sextanteParser.parseGeometryAsGeoJson(abstractGeometryType)), file);
    }
}