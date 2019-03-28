package org.geosdi.geoplatform.gml.impl.v311.geojson;

import org.geosdi.geoplatform.gml.api.parser.base.geometry.multi.curve.GMLBaseMultiCurveParser;
import org.geosdi.geoplatform.gml.api.parser.base.parameter.GMLBaseParametersRepo;
import org.geosdi.geoplatform.xml.gml.v311.MultiCurveType;
import org.junit.Test;

import java.io.File;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class GMLMultiCurveGeoJsonParserTest extends GMLBaseGeoJsonParserTest {

    private static final GMLBaseMultiCurveParser multiCurveParser = GMLBaseParametersRepo.getDefaultMultiCurveParser();

    @Test
    public void multiCurveGeoJsonParserTest() throws Exception {
        MultiCurveType multiCurveType = jaxbContextBuilder.unmarshal(new File(dirFiles.concat("MultiCurve.xml")), MultiCurveType.class);
        logger.info("##########################MULTI_LINE_STRING_GEO_JSON : {}\n", mapper.writeValueAsString(multiCurveParser.parseGeometryAsGeoJson(multiCurveType)));
    }
}