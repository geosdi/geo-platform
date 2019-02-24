package org.geosdi.geoplatform.connector.wms;

import org.junit.BeforeClass;
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

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class GPWMSOgr2OgrProcessExecTest {

    private static final Logger logger = LoggerFactory.getLogger(GPWMSOgr2OgrProcessExecTest.class);
    //
    private static final File movedDir = new File("./target/MOVED_DIR");
    private static File file;

    @BeforeClass
    public static void beforeClass() throws Exception {
        String basePath = of(new File(".").getCanonicalPath(), "src", "test", "resources", "stax")
                .collect(joining(separator, "", separator));
        movedDir.mkdirs();
        file = new File(basePath.concat("geoserver-GetFeatureInfo1.xml"));
    }

    @Ignore(value = "Must be Install GDAL on Hudson.")
    @Test
    public void processTest() throws Exception {
        File fileCopied = new File(movedDir, file.getName());
        copy(file.toPath(), fileCopied.toPath(), REPLACE_EXISTING);
        File file = new File(movedDir, "geojson.json");
        ProcessBuilder builder = new ProcessBuilder("ogr2ogr", "-f", "GeoJSON", file.getAbsolutePath(), fileCopied.getAbsolutePath());
        Process process = builder.start();
        int exitCode = process.waitFor();
        checkArgument(exitCode == 0, "The Process has problem.");
        String stringFromFile = Files.lines(file.toPath()).collect(joining());
        logger.info("@@@@@@@@@@@@@@@@@@@@@@@@@@ {}\n", stringFromFile);
    }
}