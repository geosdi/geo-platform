package org.geosdi.geoplatform.gml.impl.v311.geojson;

import org.geosdi.geoplatform.gml.api.parser.base.geometry.multi.surface.GMLBaseMultiSurfaceParser;
import org.geosdi.geoplatform.gml.api.parser.base.parameter.GMLBaseParametersRepo;
import org.geosdi.geoplatform.xml.gml.v311.MultiSurfaceType;
import org.junit.Test;

import java.io.File;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class GMLMultiSurfaceGeoJsonParserTest extends GMLBaseGeoJsonParserTest {

    private static final GMLBaseMultiSurfaceParser multiSurfaceParser = GMLBaseParametersRepo.getDefaultMultiSurfaceParser();

    @Test
    public void multiSurfaceGeoJsonParserTest() throws Exception {
        MultiSurfaceType multiSurfaceType = jaxbContextBuilder.unmarshal(new File(dirFiles.concat("MultiSurface.xml")), MultiSurfaceType.class);
        logger.info("##########################MULTI_POLYGON_GEO_JSON : {}\n", mapper.writeValueAsString(multiSurfaceParser.parseGeometryAsGeoJson(multiSurfaceType)));
    }
}