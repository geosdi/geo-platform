package org.geosdi.geoplatform.gml.impl.v311.geojson;

import org.geosdi.geoplatform.gml.api.parser.base.geometry.line.GMLBaseLineStringParser;
import org.geosdi.geoplatform.gml.api.parser.base.parameter.GMLBaseParametersRepo;
import org.geosdi.geoplatform.xml.gml.v311.LineStringType;
import org.junit.Test;

import java.io.File;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class GMLLineStringGeoJsonParserTest extends GMLBaseGeoJsonParserTest {

    private static final GMLBaseLineStringParser lineParser = GMLBaseParametersRepo.getDefaultLineStringParser();

    @Test
    public void lineStringGeoJsonParserTest() throws Exception {
        LineStringType lineStringType = jaxbContextBuilder.unmarshal(new File(dirFiles.concat("LineString.xml")), LineStringType.class);
        logger.info("#######################LINE_STRING_AS_GEOJSON : \n{}\n", mapper.writeValueAsString(lineParser.parseGeometryAsGeoJson(lineStringType)));
    }
}