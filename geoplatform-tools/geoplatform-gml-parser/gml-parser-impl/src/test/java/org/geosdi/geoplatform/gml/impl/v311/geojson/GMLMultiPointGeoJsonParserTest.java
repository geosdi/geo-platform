package org.geosdi.geoplatform.gml.impl.v311.geojson;

import org.geosdi.geoplatform.gml.api.parser.base.geometry.multi.point.GMLBaseMultiPointParser;
import org.geosdi.geoplatform.gml.api.parser.base.parameter.GMLBaseParametersRepo;
import org.geosdi.geoplatform.xml.gml.v311.MultiPointType;
import org.junit.Test;

import java.io.File;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class GMLMultiPointGeoJsonParserTest extends GMLBaseGeoJsonParserTest {

    private static final GMLBaseMultiPointParser multiPointParser = GMLBaseParametersRepo.getDefaultMultiPointParser();

    @Test
    public void multiPointGeoJsonParserTest() throws Exception {
        MultiPointType multiPointType = jaxbContextBuilder.unmarshal(new File(dirFiles.concat("MultiPoint.xml")), MultiPointType.class);
        logger.info("##########################MULTI_POINT_GEO_JSON : {}\n", mapper.writeValueAsString(multiPointParser.parseGeometryAsGeoJson(multiPointType)));
    }
}