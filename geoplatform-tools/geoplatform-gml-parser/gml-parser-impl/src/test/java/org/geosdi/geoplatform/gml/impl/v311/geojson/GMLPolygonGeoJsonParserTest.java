/**
 *
 *    geo-platform
 *    Rich webgis framework
 *    http://geo-platform.org
 *   ====================================================================
 *
 *   Copyright (C) 2008-2021 geoSDI Group (CNR IMAA - Potenza - ITALY).
 *
 *   This program is free software: you can redistribute it and/or modify it
 *   under the terms of the GNU General Public License as published by
 *   the Free Software Foundation, either version 3 of the License, or
 *   (at your option) any later version. This program is distributed in the
 *   hope that it will be useful, but WITHOUT ANY WARRANTY; without
 *   even the implied warranty of MERCHANTABILITY or FITNESS FOR
 *   A PARTICULAR PURPOSE. See the GNU General Public License
 *   for more details. You should have received a copy of the GNU General
 *   Public License along with this program. If not, see http://www.gnu.org/licenses/
 *
 *   ====================================================================
 *
 *   Linking this library statically or dynamically with other modules is
 *   making a combined work based on this library. Thus, the terms and
 *   conditions of the GNU General Public License cover the whole combination.
 *
 *   As a special exception, the copyright holders of this library give you permission
 *   to link this library with independent modules to produce an executable, regardless
 *   of the license terms of these independent modules, and to copy and distribute
 *   the resulting executable under terms of your choice, provided that you also meet,
 *   for each linked independent module, the terms and conditions of the license of
 *   that module. An independent module is a module which is not derived from or
 *   based on this library. If you modify this library, you may extend this exception
 *   to your version of the library, but you are not obligated to do so. If you do not
 *   wish to do so, delete this exception statement from your version.
 */
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