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
package org.geosdi.geoplatform.support.jackson.jts.deserializer.theories;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.ToString;
import org.geosdi.geoplatform.support.jackson.jts.GPJacksonJTSSupport;
import org.junit.BeforeClass;
import org.junit.experimental.theories.DataPoints;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;
import org.locationtech.jts.geom.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Nonnull;
import java.io.File;
import java.io.IOException;

import static com.google.common.base.Preconditions.checkArgument;
import static java.io.File.separator;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Stream.of;
import static javax.annotation.meta.When.NEVER;

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
        dirFiles = of(new File(".").getCanonicalPath(), "src", "test", "resources", "geojson")
                .collect(joining(separator, "", separator));
    }

    /**
     * @return {@link Entry[]}
     */
    @DataPoints
    public static Entry[] data() {
        return of(new Entry("Point.json", Point.class),
                new Entry("LineString.json", LineString.class),
                new Entry("LinearRing.json", LinearRing.class),
                new Entry("Polygon.json", Polygon.class),
                new Entry("MultiPoint.json", MultiPoint.class),
                new Entry("MultiLineString.json", MultiLineString.class),
                new Entry("MultiPolygon.json", MultiPolygon.class),
                new Entry("GeometryCollection.json", GeometryCollection.class),
                new Entry("GeometryCollectionComplex.json", GeometryCollection.class),
                new Entry("PolygonWithoutHoles.json", Polygon.class))
                .toArray(Entry[]::new);
    }

    @Theory
    public void deserializeJTSGeometry(Entry entry) throws Exception {
        logger.debug(":::::::::::::::::::::::ENTRY : {}\n", entry);
        String geometryFileString = dirFiles + entry.getFileName();
        File geometryFile = new File(geometryFileString);
        logger.info("::::::::::::::::::::::::JTS_GEOMETRY : \n{}\n", mapper.readValue(geometryFile, entry.getGeometryClass()));
    }

    @Getter
    @ToString
    static class Entry {

        private final String fileName;
        private final Class geometryClass;

        /**
         * @param theFileName
         * @param theGeometryClass
         */
        Entry(@Nonnull(when = NEVER) String theFileName, @Nonnull(when = NEVER) Class theGeometryClass) {
            checkArgument((theFileName != null) && !(theFileName.trim().isEmpty()), "The Parameter fileName must not be null or an empty string.");
            checkArgument(theGeometryClass != null, "The Parameter geometryClass must not be null.");
            this.fileName = theFileName;
            this.geometryClass = theGeometryClass;
        }
    }
}