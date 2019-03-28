package org.geosdi.geoplatform.gml.impl.v311.geojson;

import org.geosdi.geoplatform.gml.api.parser.base.geometry.point.GMLBasePointParser;
import org.geosdi.geoplatform.gml.api.parser.base.parameter.GMLBaseParametersRepo;
import org.geosdi.geoplatform.xml.gml.v311.PointType;
import org.junit.Test;

import java.io.File;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class GMLPointGeoJsonParserTest extends GMLBaseGeoJsonParserTest {

    private static final GMLBasePointParser pointParser = GMLBaseParametersRepo.getDefaultPointParser();

    @Test
    public void pointGeoJsonParserTest() throws Exception {
        PointType pointType = jaxbContextBuilder.unmarshal(new File(dirFiles.concat("Point.xml")), PointType.class);
        logger.info("#######################POINT_AS_GEOJSON : \n{}\n", mapper.writeValueAsString(pointParser.parseGeometryAsGeoJson(pointType)));
    }
}