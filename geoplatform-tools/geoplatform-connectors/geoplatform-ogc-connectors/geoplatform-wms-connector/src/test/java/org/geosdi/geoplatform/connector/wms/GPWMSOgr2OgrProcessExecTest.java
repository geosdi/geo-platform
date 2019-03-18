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