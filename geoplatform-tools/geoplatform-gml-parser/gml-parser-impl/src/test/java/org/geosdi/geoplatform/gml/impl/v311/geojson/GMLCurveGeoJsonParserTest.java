package org.geosdi.geoplatform.gml.impl.v311.geojson;

import org.geosdi.geoplatform.gml.api.AbstractCurve;
import org.geosdi.geoplatform.gml.api.parser.base.geometry.curve.GMLBaseCurveParser;
import org.geosdi.geoplatform.gml.api.parser.base.parameter.GMLBaseParametersRepo;
import org.geosdi.geoplatform.xml.gml.v311.CurveType;
import org.junit.Test;

import java.io.File;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class GMLCurveGeoJsonParserTest extends GMLBaseGeoJsonParserTest {

    private static final GMLBaseCurveParser curveParser = GMLBaseParametersRepo.getDefaultCurveParser();

    @Test
    public void curveGeoJsonParserTest() throws Exception {
        CurveType curveType = jaxbContextBuilder.unmarshal(new File(dirFiles.concat("Curve.xml")), CurveType.class);
        logger.info("##########################MULTI_LINE_STRING_GEO_JSON : {}\n", mapper.writeValueAsString(curveParser.parseGeometryAsGeoJson((AbstractCurve) curveType)));
    }
}