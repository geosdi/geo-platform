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

import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.nio.file.Files;

import static com.google.common.base.Preconditions.checkArgument;
import static java.io.File.separator;
import static java.nio.file.Files.copy;
import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Stream.of;
import static org.junit.runners.MethodSorters.NAME_ASCENDING;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@FixMethodOrder(value = NAME_ASCENDING)
public class GPWMSOgr2OgrProcessExecTest {

    private static final Logger logger = LoggerFactory.getLogger(GPWMSOgr2OgrProcessExecTest.class);
    //
    private static final File movedDir = new File("./target/MOVED_DIR");
    private static File file;
    private static File file1;
    private static File file2;
    private static File file3;

    @BeforeClass
    public static void beforeClass() throws Exception {
        String basePath = of(new File(".").getCanonicalPath(), "src", "test", "resources", "stax")
                .collect(joining(separator, "", separator));
        movedDir.mkdirs();
        file = new File(basePath.concat("geoserver-GetFeatureInfo1.xml"));
        file1 = new File(basePath.concat("corine-GetFeatureInfo.xml"));
        file2 = new File(basePath.concat("sfdem-GetFeatureInfo.xml"));
        file3 = new File(basePath.concat("tiger_ny-GetFeatureInfo.xml"));
    }

    @Ignore(value = "Must be Install GDAL on Hudson.")
    @Test
    public void a_processTest() throws Exception {
        File fileCopied = new File(movedDir, file.getName());
        copy(file.toPath(), fileCopied.toPath(), REPLACE_EXISTING);
        File file = new File(movedDir, "file-geojson.json");
        ProcessBuilder builder = new ProcessBuilder("ogr2ogr", "-f", "GeoJSON", file.getAbsolutePath(), fileCopied.getAbsolutePath());
        Process process = builder.start();
        int exitCode = process.waitFor();
        checkArgument(exitCode == 0, "The Process has problem.");
        String stringFromFile = Files.lines(file.toPath()).collect(joining());
        logger.info("@@@@@@@@@@@@@@@@@@@@@@@@@@ {}\n", stringFromFile);
    }

    @Ignore(value = "Must be Install GDAL on Hudson.")
    @Test
    public void b_processTest() throws Exception {
        File fileCopied = new File(movedDir, file1.getName());
        copy(file1.toPath(), fileCopied.toPath(), REPLACE_EXISTING);
        File file = new File(movedDir, "file1-geojson.json");
        ProcessBuilder builder = new ProcessBuilder("ogr2ogr", "-f", "GeoJSON", file.getAbsolutePath(), fileCopied.getAbsolutePath());
        Process process = builder.start();
        int exitCode = process.waitFor();
        checkArgument(exitCode == 0, "The Process has problem.");
        String stringFromFile = Files.lines(file.toPath()).collect(joining());
        logger.info("@@@@@@@@@@@@@@@@@@@@@@@@@@ {}\n", stringFromFile);
    }

    @Ignore(value = "Must be Install GDAL on Hudson.")
    @Test
    public void c_processTest() throws Exception {
        File fileCopied = new File(movedDir, file2.getName());
        copy(file2.toPath(), fileCopied.toPath(), REPLACE_EXISTING);
        File file = new File(movedDir, "sfdem-geojson.json");
        ProcessBuilder builder = new ProcessBuilder("ogr2ogr", "-f", "GeoJSON", file.getAbsolutePath(), fileCopied.getAbsolutePath());
        Process process = builder.start();
        int exitCode = process.waitFor();
        checkArgument(exitCode == 0, "The Process has problem.");
        String stringFromFile = Files.lines(file.toPath()).collect(joining());
        logger.info("@@@@@@@@@@@@@@@@@@@@@@@@@@ {}\n", stringFromFile);
    }

    @Ignore(value = "In this case GDAL Fails to generate the GeoJson.")
    @Test
    public void d_processTest() throws Exception {
        File fileCopied = new File(movedDir, file3.getName());
        copy(file3.toPath(), fileCopied.toPath(), REPLACE_EXISTING);
        File file = new File(movedDir, "tygerny-geojson.json");
        ProcessBuilder builder = new ProcessBuilder("ogr2ogr", "-f", "GeoJSON", file.getAbsolutePath(), fileCopied.getAbsolutePath());
        Process process = builder.start();
        int exitCode = process.waitFor();
        checkArgument(exitCode == 0, "The Process has problem.");
        String stringFromFile = Files.lines(file.toPath()).collect(joining());
        logger.info("@@@@@@@@@@@@@@@@@@@@@@@@@@ {}\n", stringFromFile);
    }
}