package org.geosdi.geoplatform.support.jackson.jts.deserializer.theories;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vividsolutions.jts.geom.*;
import org.geosdi.geoplatform.support.jackson.jts.GPJacksonJTSSupport;
import org.junit.BeforeClass;
import org.junit.experimental.theories.DataPoints;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@RunWith(Theories.class)
public class JTSGeometryTheoriesDeserializerTest {

    private static final Logger logger = LoggerFactory.getLogger(JTSGeometryTheoriesDeserializerTest.class);
    //
    private static String dirFiles;
    private static final ObjectMapper mapper = new GPJacksonJTSSupport().getDefaultMapper();

    @BeforeClass
    public static void buildDirFiles() throws IOException {
        dirFiles = new File(".").getCanonicalPath() + File.separator
                + "src/test/resources/geojson/";
    }

    @DataPoints
    public static Entry[] data() {
        return new Entry[]{
                new Entry("Point.json", Point.class),
                new Entry("LineString.json", LineString.class),
                new Entry("LinearRing.json", LinearRing.class),
                new Entry("Polygon.json", Polygon.class),
                new Entry("MultiPoint.json", MultiPoint.class),
                new Entry("MultiLineString.json", MultiLineString.class),
                new Entry("MultiPolygon.json", MultiPolygon.class),
                new Entry("GeometryCollection.json", GeometryCollection.class),
                new Entry("GeometryCollectionComplex.json", GeometryCollection.class),
                new Entry("PolygonWithoutHoles.json", Polygon.class)
        };
    }

    @Theory
    public void deserializeJTSGeometry(Entry entry) throws Exception {
        logger.debug(":::::::::::::::::::::::ENTRY : {}\n", entry);
        String geometryFileString = dirFiles + entry.getFileName();
        File geometryFile = new File(geometryFileString);
        logger.info("::::::::::::::::::::::::JTS_GEOMETRY : \n{}\n",
                mapper.readValue(geometryFile, entry.getGeometryClass()));
    }

    static class Entry {
        private final String fileName;
        private final Class geometryClass;

        public Entry(String fileName, Class geometryClass) {
            this.fileName = fileName;
            this.geometryClass = geometryClass;
        }

        public String getFileName() {
            return fileName;
        }

        public Class getGeometryClass() {
            return geometryClass;
        }

        @Override
        public String toString() {
            return getClass().getSimpleName() + " {" +
                    "fileName = '" + fileName + '\'' +
                    ", geometryClass = " + geometryClass +
                    '}';
        }
    }
}
