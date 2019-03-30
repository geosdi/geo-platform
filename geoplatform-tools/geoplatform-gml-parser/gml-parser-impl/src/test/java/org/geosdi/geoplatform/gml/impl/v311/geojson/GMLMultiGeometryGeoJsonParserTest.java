package org.geosdi.geoplatform.gml.impl.v311.geojson;

import org.geosdi.geoplatform.gml.api.parser.base.DefaultSRSBaseParser;
import org.geosdi.geoplatform.gml.api.parser.base.geometry.multi.geometry.GMLBaseMultiGeometryParser;
import org.geosdi.geoplatform.gml.api.parser.base.parameter.GMLBaseParametersRepo;
import org.geosdi.geoplatform.xml.gml.v311.MultiGeometryType;
import org.junit.Test;

import java.io.File;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class GMLMultiGeometryGeoJsonParserTest extends GMLBaseGeoJsonParserTest {

    private static final GMLBaseMultiGeometryParser multiGeometryParser = GMLBaseParametersRepo.getDefaultMultiGeometryParser();
    private static final DefaultSRSBaseParser srsParser = GMLBaseParametersRepo.getDefaultSRSParser();

    @Test
    public void multiGeometryGeoJsonParserTest() throws Exception {
        MultiGeometryType multiGeometryType = jaxbContextBuilder.unmarshal(new File(dirFiles.concat("GeometryCollection.xml")), MultiGeometryType.class);
        logger.info("#######################LINEAR_RING_AS_GEOJSON : \n{}\n", mapper.writeValueAsString(multiGeometryParser.parseGeometryAsGeoJson(multiGeometryType)));
    }
}
