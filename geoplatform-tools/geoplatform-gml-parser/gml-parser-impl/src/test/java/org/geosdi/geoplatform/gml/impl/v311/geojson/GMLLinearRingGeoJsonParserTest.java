package org.geosdi.geoplatform.gml.impl.v311.geojson;

import org.geosdi.geoplatform.gml.api.parser.base.geometry.linerarring.GMLBaseLinearRingParser;
import org.geosdi.geoplatform.gml.api.parser.base.parameter.GMLBaseParametersRepo;
import org.geosdi.geoplatform.xml.gml.v311.LinearRingType;
import org.junit.Test;

import java.io.File;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class GMLLinearRingGeoJsonParserTest extends GMLBaseGeoJsonParserTest {

    private static final GMLBaseLinearRingParser linearRingParser = GMLBaseParametersRepo.getDefaultLinearRingParser();

    @Test
    public void linearRingGeoJsonParserTest() throws Exception {
        LinearRingType linearRingType = jaxbContextBuilder.unmarshal(new File(dirFiles.concat("LinearRing.xml")), LinearRingType.class);
        logger.info("#######################LINEAR_RING_AS_GEOJSON : \n{}\n", mapper.writeValueAsString(linearRingParser.parseGeometryAsGeoJson(linearRingType)));
    }
}