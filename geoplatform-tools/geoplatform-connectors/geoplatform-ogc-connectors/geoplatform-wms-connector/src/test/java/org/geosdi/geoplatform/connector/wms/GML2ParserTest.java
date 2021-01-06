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
package org.geosdi.geoplatform.connector.wms;

import org.geosdi.geoplatform.jaxb.GPJAXBContextBuilder;
import org.geosdi.geoplatform.xml.gml.v212.*;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.io.gml2.GMLReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileReader;

import static java.io.File.separator;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Stream.of;
import static org.geosdi.geoplatform.jaxb.GPJAXBContextBuilder.newInstance;
import static org.junit.runners.MethodSorters.NAME_ASCENDING;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@FixMethodOrder(value = NAME_ASCENDING)
public class GML2ParserTest {

    private static final Logger logger = LoggerFactory.getLogger(GML2ParserTest.class);
    //
    private static final GPJAXBContextBuilder jaxbContextBuilder = newInstance();
    private static File file;
    private static File file1;
    private static File file2;
    private static File file3;
    private static File file4;
    private static File file5;

    @BeforeClass
    public static void beforeClass() throws Exception {
        String basePath = of(new File(".").getCanonicalPath(), "src", "test", "resources", "stax")
                .collect(joining(separator, "", separator));
        file = new File(basePath.concat("multiPolygon-Vigneti.xml"));
        file1 = new File(basePath.concat("multiPolygon.xml"));
        file2 = new File(basePath.concat("point.xml"));
        file3 = new File(basePath.concat("multyLineString.xml"));
        file4 = new File(basePath.concat("lineString.xml"));
        file5 = new File(basePath.concat("multiPolygon-Corine.xml"));
    }

    @Test
    public void a_readGML2GeometryAsJTSGeometryTest() throws Exception {
        GMLReader gmlReader = new GMLReader();
        logger.info("#########################JTS_GEOMETRY : {}\n", gmlReader.read(new FileReader(file), new GeometryFactory()));
    }

    @Test
    public void b_unmarshallerTest() throws Exception {
        AbstractGeometryType geometryCollectionType = jaxbContextBuilder.unmarshal(new FileReader(file), GeometryCollectionType.class);
        logger.info("######################UNMARSHALL_GML2_GEOMETRY : {}\n", geometryCollectionType.getClass().getSimpleName());
    }

    @Test
    public void c_readGML2GeometryAsJTSGeometryTest() throws Exception {
        GMLReader gmlReader = new GMLReader();
        logger.info("#########################JTS_GEOMETRY : {}\n", gmlReader.read(new FileReader(file1), new GeometryFactory()));
    }

    @Test
    public void d_unmarshallerTest() throws Exception {
        AbstractGeometryType geometryCollectionType = jaxbContextBuilder.unmarshal(new FileReader(file1), GeometryCollectionType.class);
        logger.info("######################UNMARSHALL_GML2_GEOMETRY : {}\n", geometryCollectionType.getClass().getSimpleName());
    }

    @Test
    public void e_readGML2GeometryAsJTSGeometryTest() throws Exception {
        GMLReader gmlReader = new GMLReader();
        logger.info("#########################JTS_GEOMETRY : {}\n", gmlReader.read(new FileReader(file2), new GeometryFactory()));
    }

    @Test
    public void f_unmarshallerTest() throws Exception {
        AbstractGeometryType pointType = jaxbContextBuilder.unmarshal(new FileReader(file2), PointType.class);
        logger.info("######################UNMARSHALL_GML2_GEOMETRY : {}\n", pointType);
    }

    @Test
    public void g_readGML2GeometryAsJTSGeometryTest() throws Exception {
        GMLReader gmlReader = new GMLReader();
        logger.info("#########################JTS_GEOMETRY : {}\n", gmlReader.read(new FileReader(file3), new GeometryFactory()));
    }

    @Test
    public void h_unmarshallerTest() throws Exception {
        AbstractGeometryType multiLineString = jaxbContextBuilder.unmarshal(new FileReader(file3), MultiLineStringType.class);
        logger.info("######################UNMARSHALL_GML2_GEOMETRY : {}\n", multiLineString);
    }

    @Test
    public void i_readGML2GeometryAsJTSGeometryTest() throws Exception {
        GMLReader gmlReader = new GMLReader();
        logger.info("#########################JTS_GEOMETRY : {}\n", gmlReader.read(new FileReader(file4), new GeometryFactory()));
    }

    @Test
    public void l_unmarshallerTest() throws Exception {
        AbstractGeometryType lineString = jaxbContextBuilder.unmarshal(new FileReader(file4), LineStringType.class);
        logger.info("######################UNMARSHALL_GML2_GEOMETRY : {}\n", lineString);
    }

    @Test
    public void m_readGML2GeometryAsJTSGeometryTest() throws Exception {
        GMLReader gmlReader = new GMLReader();
        logger.info("#########################JTS_GEOMETRY : {}\n", gmlReader.read(new FileReader(file5), new GeometryFactory()));
    }

    @Test
    public void n_unmarshallerTest() throws Exception {
        AbstractGeometryType multiPolygon = jaxbContextBuilder.unmarshal(new FileReader(file5), MultiPointType.class);
        logger.info("######################UNMARSHALL_GML2_GEOMETRY : {}\n", multiPolygon);
    }
}