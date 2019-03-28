package org.geosdi.geoplatform.gml.impl.v311.geojson;

import org.geosdi.geoplatform.gml.api.parser.base.geometry.multi.line.GMLBaseMultiLineStringParser;
import org.geosdi.geoplatform.gml.api.parser.base.parameter.GMLBaseParametersRepo;
import org.geosdi.geoplatform.xml.gml.v311.MultiLineStringType;
import org.junit.FixMethodOrder;
import org.junit.Test;

import java.io.File;

import static org.junit.runners.MethodSorters.NAME_ASCENDING;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@FixMethodOrder(value = NAME_ASCENDING)
public class GMLMultiLineStringGeoJsonParserTest extends GMLBaseGeoJsonParserTest {

    private static final GMLBaseMultiLineStringParser multiLineStringParser = GMLBaseParametersRepo.getDefaultMultiLineStringParser();

    @Test
    public void a_multiLineStringGeoJsonParserTest() throws Exception {
        MultiLineStringType multiLineStringType = jaxbContextBuilder.unmarshal(new File(dirFiles.concat("MultiLineString.xml")), MultiLineStringType.class);
        logger.info("##########################MULTI_LINE_STRING_GEO_JSON : {}\n", mapper.writeValueAsString(multiLineStringParser.parseGeometryAsGeoJson(multiLineStringType)));
    }

    @Test
    public void b_multiLineStringGeoJsonParserTest() throws Exception {
        MultiLineStringType multiLineStringType = jaxbContextBuilder.unmarshal(new File(dirFiles.concat("MultiLineString_srsDimension3.xml")), MultiLineStringType.class);
        logger.info("##########################MULTI_LINE_STRING_GEO_JSON : {}\n", mapper.writeValueAsString(multiLineStringParser.parseGeometryAsGeoJson(multiLineStringType)));
    }
}