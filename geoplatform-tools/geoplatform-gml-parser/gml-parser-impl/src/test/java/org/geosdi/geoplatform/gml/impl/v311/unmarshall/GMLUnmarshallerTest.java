/*
 *
 *    geo-platform
 *    Rich webgis framework
 *    http://geo-platform.org
 *   ====================================================================
 *
 *   Copyright (C) 2008-2024 geoSDI Group (CNR IMAA - Potenza - ITALY).
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
package org.geosdi.geoplatform.gml.impl.v311.unmarshall;

import org.geosdi.geoplatform.jaxb.pool.GPJAXBContextBuilderPool;
import org.geosdi.geoplatform.xml.gml.v311.*;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.StringReader;

import static org.geosdi.geoplatform.jaxb.pool.GPJAXBContextBuilderPool.jaxbContextBuilderPool;
import static org.junit.runners.MethodSorters.NAME_ASCENDING;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@FixMethodOrder(value = NAME_ASCENDING)
public class GMLUnmarshallerTest {

    private static final Logger logger = LoggerFactory.getLogger(GMLUnmarshallerTest.class);
    //
    private static final GPJAXBContextBuilderPool GPJAXB_CONTEXT_BUILDER_POOL = jaxbContextBuilderPool();

    @Test
    public void a_unmarshallerTest() throws Exception {
        CircleByCenterPointType circle = GPJAXB_CONTEXT_BUILDER_POOL.unmarshal(new StringReader("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n"
                + "<gml:CircleByCenterPoint xmlns:gml=\"http://www.opengis.net/gml\">\n"
                + "   <gml:pos srsName=\"urn:ogc:def:crs:EPSG::4326\">11.979491114616 43.847170472145</gml:pos>\n"
                + "   <gml:radius uom=\"km\">25</gml:radius>\n"
                + "</gml:CircleByCenterPoint>"), CircleByCenterPointType.class);
        logger.info("###################GML_CIRCLE : {}\n", circle);
    }

    @Test
    public void b_unmashallerTest() throws Exception {
        MultiLineStringType multiLineStringType = GPJAXB_CONTEXT_BUILDER_POOL.unmarshal(new StringReader("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n"
                + "<gml:MultiLineString xmlns:gml=\"http://www.opengis.net/gml\" srsName=\"http://www.opengis.net/gml/srs/epsg.xml#3003\">\n"
                + "   <gml:lineStringMember>\n"
                + "      <gml:LineString>\n"
                + "         <gml:coordinates decimal=\".\" cs=\",\" ts=\" \">1531819.973,5041805.098,140.68800354 1531821.303,5041802.773,140.68800354\n"
                + "                                1531822.297,5041801.215,140.68800354 1531823.387,5041799.93,140.68800354\n"
                + "                                1531824.359,5041798.844,140.68800354 1531825.51,5041797.664,140.68800354\n"
                + "                                1531826.211,5041796.125,140.68800354 1531826.832,5041794.773,140.68800354\n"
                + "                                1531827.453,5041793.504,140.68800354 1531828.063,5041792.781,140.68800354\n"
                + "                                1531828.947,5041791.695,140.68800354 1531829.92,5041790.07,140.68800354\n"
                + "                                1531830.979,5041787.445,140.68800354 1531832.393,5041785.816,140.68800354\n"
                + "                                1531834.158,5041783.734,140.68800354 1531835.486,5041781.656,140.68800354\n"
                + "                                1531836.193,5041779.934,140.68800354 1531837.43,5041777.855,140.68800354\n"
                + "                                1531839.018,5041775.773,140.68800354 1531840.783,5041773.879,140.68800354\n"
                + "                                1531842.375,5041771.887,140.68800354 1531843.643,5041769.508,140.68800354" +
                "</gml:coordinates>\n"
                + "      </gml:LineString>\n"
                + "   </gml:lineStringMember>\n"
                + "</gml:MultiLineString>\n"), MultiLineStringType.class);
        logger.info("@@@@@@@@@@@@@@@@@@GML_MULTI_LINE_STRING : {}\n", multiLineStringType);
    }

    @Test
    public void c_unmarshallerTest() throws Exception {
        CurveType curve = GPJAXB_CONTEXT_BUILDER_POOL.unmarshal(new StringReader("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n"
                + "<gml:Curve xmlns:gml=\"http://www.opengis.net/gml\" gml:id=\"ID\" srsName=\"urn:x-ogc:def:crs:EPSG:4326\">\n"
                + "   <gml:metaDataProperty>\n"
                + "      <gml:GenericMetaData>\n"
                + "         Any text, intermingled with:\n"
                + "         <!--any element-->\n"
                + "      </gml:GenericMetaData>\n"
                + "   </gml:metaDataProperty>\n"
                + "   <gml:description>string</gml:description>\n"
                + "   <gml:descriptionReference />\n"
                + "   <gml:identifier codeSpace=\"http://www.example.com/\">string</gml:identifier>\n"
                + "   <gml:name>string</gml:name>\n"
                + "   <gml:segments>\n"
                + "      <gml:LineStringSegment>\n"
                + "         <gml:pos>1.0 1.0</gml:pos>\n"
                + "      </gml:LineStringSegment>\n"
                + "   </gml:segments>\n"
                + "</gml:Curve>"), CurveType.class);
        logger.info("#################GML_CURVE : {}\n", curve);
    }

    @Test
    public void d_unmarshallerTest() throws Exception {
        CurveType curve = GPJAXB_CONTEXT_BUILDER_POOL.unmarshal(new StringReader("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n"
                + "<gml:Curve xmlns:gml=\"http://www.opengis.net/gml\" srsName=\"urn:x-ogc:def:crs:EPSG:4326\">\n"
                + "   <gml:segments>\n"
                + "      <gml:CircleByCenterPoint numArc=\"1\">\n"
                + "         <gml:pos>1.0 1.0</gml:pos>\n"
                + "         <gml:radius uom=\"http://www.example.com/\">1.0</gml:radius>\n"
                + "      </gml:CircleByCenterPoint>\n"
                + "   </gml:segments>\n" + "</gml:Curve>"), CurveType.class);
        logger.info("#################GML_CURVE : {}\n", curve);
    }

    @Test
    public void e_unmarshallerTest() throws Exception {
        PolygonType polygon = GPJAXB_CONTEXT_BUILDER_POOL.unmarshal(new StringReader("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n"
                + "<gml:Polygon xmlns:gml=\"http://www.opengis.net/gml\" srsName=\"EPSG:4326\">\n"
                + "   <gml:exterior>\n"
                + "      <gml:LinearRing srsName=\"EPSG:4326\">\n"
                + "         <gml:coordinates decimal=\".\" cs=\",\" ts=\"\">119.593002319336,-31.6695003509522 119.595306396484,\n"
                + "                    31.6650276184082 119.600944519043,-31.6658897399902 119.603385925293,-31.669527053833     " +
                "       \n" + "                    119.60050201416,-31.6739158630371 119.595664978027,-31.6728610992432 119.593002319336,\n"
                + "                    31.6695003509522</gml:coordinates>\n"
                + "      </gml:LinearRing>\n"
                + "   </gml:exterior>\n"
                + "</gml:Polygon>"), PolygonType.class);
        logger.info("@@@@@@@@@@@@@@@@@GML_POLYGON : {}\n", polygon);
    }

    @Test
    public void f_unmarshallerTest() throws Exception {
        PolygonType polygon = GPJAXB_CONTEXT_BUILDER_POOL.unmarshal(new StringReader("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n"
                + "<gml:Polygon xmlns:gml=\"http://www.opengis.net/gml\">\n"
                + "   <gml:outerBoundaryIs>\n"
                + "      <gml:LinearRing>\n"
                + "         <gml:coordinates>0,0 100,0 100,100 0,100 0,0</gml:coordinates>\n"
                + "      </gml:LinearRing>\n"
                + "   </gml:outerBoundaryIs>\n" + "</gml:Polygon>"), PolygonType.class);
        logger.info("################GML_POLYGON : {}\n", polygon);
    }

    @Test
    public void g_unmarshallerTest() throws Exception {
        PolygonType polygon = GPJAXB_CONTEXT_BUILDER_POOL.unmarshal(new StringReader("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n"
                + "<gml:Polygon xmlns:gml=\"http://www.opengis.net/gml\" srsName=\"urn:ogc:def:crs:EPSG::4979\">\n"
                + "   <gml:exterior>\n"
                + "      <gml:LinearRing>\n"
                + "         <gml:posList>42.556844 -73.248157 36.6\n"
                + "                      42.549631 -73.237283 36.6\n"
                + "                      42.539087 -73.240328 36.6\n"
                + "                      42.535756 -73.254242 36.6\n"
                + "                      42.542969 -73.265115 36.6\n"
                + "                      42.553513 -73.262075 36.6\n"
                + "                      42.556844 -73.248157 36.6</gml:posList>\n"
                + "      </gml:LinearRing>\n"
                + "   </gml:exterior>\n"
                + "</gml:Polygon>"), PolygonType.class);
        logger.info("@@@@@@@@@@@@@@@GML_POLYGON : {}\n", polygon);
    }

    @Test
    public void h_unmarshallerTest() throws Exception {
        PointType point = GPJAXB_CONTEXT_BUILDER_POOL.unmarshal(new StringReader("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n"
                + "<gml:Point xmlns:gml=\"http://www.opengis.net/gml\">\n"
                + "   <gml:coordinates>100,200</gml:coordinates>\n"
                + "</gml:Point>"), PointType.class);
        logger.info("##############GML_POINT : {}\n", point);
    }

    @Test
    public void i_unmarshallerTest() throws Exception {
        LineStringType lineString = GPJAXB_CONTEXT_BUILDER_POOL.unmarshal(new StringReader("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n"
                + "<gml:LineString xmlns:gml=\"http://www.opengis.net/gml\" srsName=\"EPSG:4269\">\n"
                + "   <gml:coordinates>-71.16028,42.258729 -71.160837,42.259112 -71.161143,42.25932</gml:coordinates>\n"
                + "</gml:LineString>"), LineStringType.class);
        logger.info("@@@@@@@@@@@@@GML_LINESTRING : {}\n", lineString);
    }

    @Test
    public void l_unmarshallerTest() throws Exception {
        ArcStringType arc = GPJAXB_CONTEXT_BUILDER_POOL.unmarshal(new StringReader("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n"
                + "<gml:ArcString xmlns:gml=\"http://www.opengis.net/gml\" numArc=\"1\">\n"
                + "   <gml:pos>1.0 1.0</gml:pos>\n"
                + "</gml:ArcString>"), ArcStringType.class);
        logger.info("############GML_ARC_STRING : {}\n", arc);
    }
}