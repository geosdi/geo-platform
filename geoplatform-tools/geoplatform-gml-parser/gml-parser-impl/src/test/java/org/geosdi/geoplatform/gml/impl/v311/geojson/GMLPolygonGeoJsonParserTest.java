package org.geosdi.geoplatform.gml.impl.v311.geojson;

import org.geojson.LngLatAlt;
import org.geojson.Polygon;
import org.geosdi.geoplatform.gml.api.parser.base.geometry.polygon.GMLBasePolygonParser;
import org.geosdi.geoplatform.gml.api.parser.base.parameter.GMLBaseParametersRepo;
import org.geosdi.geoplatform.xml.gml.v311.PolygonType;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;

import java.io.File;
import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.runners.MethodSorters.NAME_ASCENDING;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@FixMethodOrder(value = NAME_ASCENDING)
public class GMLPolygonGeoJsonParserTest extends GMLBaseGeoJsonParserTest {

    private static final GMLBasePolygonParser polygonParser = GMLBaseParametersRepo.getDefaultPolygonParser();

    @Test
    public void a_polygonGeoJsonParserTest() throws Exception {
        PolygonType polygonType = jaxbContextBuilder.unmarshal(new File(dirFiles.concat("Polygon.xml")), PolygonType.class);
        Polygon polygon = polygonParser.parseGeometryAsGeoJson(polygonType);
        List<List<LngLatAlt>> interiorRings = polygon.getInteriorRings();
        assertNotNull(interiorRings);
        Assert.assertTrue(interiorRings.size() == 2);
        logger.info("#######################POLYGON_AS_GEOJSON : \n{}\n", mapper.writeValueAsString(polygon));
    }

    @Test
    public void b_polygonGeoJsonParserTest() throws Exception {
        PolygonType polygonType = jaxbContextBuilder.unmarshal(new File(dirFiles.concat("Polygon_1.xml")), PolygonType.class);
        Polygon polygon = polygonParser.parseGeometryAsGeoJson(polygonType);
        Assert.assertTrue(polygon.getInteriorRings().size() == 0);
        logger.info("#######################POLYGON1_AS_GEOJSON : \n{}\n", mapper.writeValueAsString(polygon));
    }
}