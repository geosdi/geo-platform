package org.geosdi.geoplatform.gml.impl.v311.geojson;

import org.geosdi.geoplatform.gml.api.parser.base.geometry.multi.polygon.GMLBaseMultiPolygonParser;
import org.geosdi.geoplatform.gml.api.parser.base.parameter.GMLBaseParametersRepo;
import org.geosdi.geoplatform.xml.gml.v311.MultiPolygonType;
import org.junit.Test;

import java.io.File;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class GMLMultiPolygonGeoJsonParserTest extends GMLBaseGeoJsonParserTest {

    private static final GMLBaseMultiPolygonParser multiPolygonParser = GMLBaseParametersRepo.getDefaultMultiPolygonParser();

    @Test
    public void multiPolygonGeoJsonParserTest() throws Exception {
        MultiPolygonType multiPolygonType = jaxbContextBuilder.unmarshal(new File(dirFiles.concat("MultiPolygon.xml")), MultiPolygonType.class);
        logger.info("##########################MULTI_POLYGON_GEO_JSON : {}\n", mapper.writeValueAsString(multiPolygonParser.parseGeometryAsGeoJson(multiPolygonType)));
    }
}