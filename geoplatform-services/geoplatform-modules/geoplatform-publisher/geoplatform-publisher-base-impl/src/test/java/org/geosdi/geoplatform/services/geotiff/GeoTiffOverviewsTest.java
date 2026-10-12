/*
 *
 *    geo-platform
 *    Rich webgis framework
 *    http://geo-platform.org
 *   ====================================================================
 *
 *   Copyright (C) 2008-2026 geoSDI Group (CNR IMAA - Potenza - ITALY).
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
package org.geosdi.geoplatform.services.geotiff;

import it.geosolutions.imageio.plugins.tiff.TIFFField;
import it.geosolutions.imageioimpl.plugins.tiff.TIFFImageMetadata;
import it.geosolutions.imageioimpl.plugins.tiff.TIFFImageReader;
import it.geosolutions.imageioimpl.plugins.tiff.TIFFImageReaderSpi;
import it.geosolutions.imageioimpl.plugins.tiff.TiffDatasetLayoutImpl;
import org.geotools.api.parameter.GeneralParameterValue;
import org.geotools.api.parameter.ParameterValueGroup;
import org.geotools.coverage.grid.GridCoverage2D;
import org.geotools.coverage.grid.GridCoverageFactory;
import org.geotools.coverage.grid.io.AbstractGridFormat;
import org.geotools.gce.geotiff.GeoTiffFormat;
import org.geotools.gce.geotiff.GeoTiffReader;
import org.geotools.gce.geotiff.GeoTiffWriteParams;
import org.geotools.gce.geotiff.GeoTiffWriter;
import org.geotools.geometry.jts.ReferencedEnvelope;
import org.geotools.referencing.CRS;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Nonnull;
import javax.imageio.ImageWriteParam;
import javax.imageio.stream.FileImageInputStream;
import java.awt.image.BufferedImage;
import java.awt.image.IndexColorModel;
import java.awt.image.Raster;
import java.io.File;
import java.nio.file.Files;
import java.util.Arrays;

import static com.google.common.base.Preconditions.checkArgument;
import static it.geosolutions.imageio.plugins.tiff.BaselineTIFFTagSet.*;
import static javax.annotation.meta.When.NEVER;
import static org.junit.Assert.*;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class GeoTiffOverviewsTest {

    private static final Logger logger = LoggerFactory.getLogger(GeoTiffOverviewsTest.class);
    //
    @Rule
    public TemporaryFolder folder = new TemporaryFolder();

    @Test
    public void nearestOverviewsAreReadByGeoToolsTest() throws Exception {
        BufferedImage image = grayImage(1024, 1024);
        File tiff = writeGeoTiff(image, null);
        assertEquals(3, GeoTiffOverviews.overviewTiff(configuration("Nearest", 3), tiff.getAbsolutePath()));
        GeoTiffReader reader = new GeoTiffReader(tiff);
        try {
            assertEquals(3, reader.getDatasetLayout().getNumInternalOverviews());
            double[][] levels = reader.getResolutionLevels();
            logger.info("##########################RESOLUTION_LEVELS : {}", Arrays.deepToString(levels));
            assertEquals(4, levels.length);
            for (int level = 1; level < levels.length; level++) {
                assertEquals(levels[level - 1][0] * 2, levels[level][0], 1e-9);
                assertEquals(levels[level - 1][1] * 2, levels[level][1], 1e-9);
            }
        } finally {
            reader.dispose();
        }
        try (TiffPages pages = new TiffPages(tiff)) {
            assertEquals(4, pages.count());
            assertEquals(3, pages.overviews());
            assertArrayEquals(pixels(image.getData()), pixels(pages.read(0)));
            for (int level = 1; level <= 3; level++) {
                int factor = 1 << level;
                Raster overview = pages.read(level);
                assertEquals(1024 / factor, overview.getWidth());
                assertEquals(1024 / factor, overview.getHeight());
                assertEquals(NEW_SUBFILE_TYPE_REDUCED_RESOLUTION, pages.field(level, TAG_NEW_SUBFILE_TYPE));
                for (int y = 0; y < overview.getHeight(); y += 37) {
                    for (int x = 0; x < overview.getWidth(); x += 41) {
                        assertEquals(image.getRaster().getSample(x * factor, y * factor, 0), overview.getSample(x, y, 0));
                    }
                }
            }
        }
    }

    @Test
    public void bilinearOverviewsAverageTwoByTwoBlocksTest() throws Exception {
        BufferedImage image = grayImage(512, 512);
        File tiff = writeGeoTiff(image, null);
        assertEquals(2, GeoTiffOverviews.overviewTiff(configuration("Bilinear", 2), tiff.getAbsolutePath()));
        try (TiffPages pages = new TiffPages(tiff)) {
            assertEquals(2, pages.overviews());
            Raster overview = pages.read(1);
            assertEquals(256, overview.getWidth());
            for (int y = 0; y < overview.getHeight(); y += 29) {
                for (int x = 0; x < overview.getWidth(); x += 31) {
                    assertEquals(blockMean(image.getRaster(), x, y), overview.getSample(x, y, 0), 0.5d);
                }
            }
            assertEquals(128, pages.read(2).getWidth());
        }
    }

    @Test
    public void bicubicOverviewsKeepTheBordersTest() throws Exception {
        BufferedImage image = new BufferedImage(256, 256, BufferedImage.TYPE_BYTE_GRAY);
        for (int y = 0; y < image.getHeight(); y++) {
            for (int x = 0; x < image.getWidth(); x++) {
                image.getRaster().setSample(x, y, 0, x / 2 + 50);
            }
        }
        File tiff = writeGeoTiff(image, null);
        assertEquals(1, GeoTiffOverviews.overviewTiff(configuration("Bicubic", 1), tiff.getAbsolutePath()));
        try (TiffPages pages = new TiffPages(tiff)) {
            Raster overview = pages.read(1);
            for (int y = 0; y < overview.getHeight(); y += 17) {
                assertEquals(blockMean(image.getRaster(), 0, y), overview.getSample(0, y, 0), 1d);
                assertEquals(blockMean(image.getRaster(), 127, y), overview.getSample(127, y, 0), 1d);
            }
        }
    }

    @Test
    public void overviewsStopAtOnePixelTest() throws Exception {
        File tiff = writeGeoTiff(grayImage(64, 48), null);
        assertEquals(5, GeoTiffOverviews.overviewTiff(configuration("Nearest", 10), tiff.getAbsolutePath()));
        try (TiffPages pages = new TiffPages(tiff)) {
            assertEquals(6, pages.count());
            assertEquals(2, pages.read(5).getWidth());
            assertEquals(1, pages.read(5).getHeight());
        }
    }

    @Test
    public void geoTiffWithOverviewsIsLeftUntouchedTest() throws Exception {
        File tiff = writeGeoTiff(grayImage(256, 256), null);
        GeoTiffOverviewsConfiguration configuration = configuration("Nearest", 2);
        assertEquals(2, GeoTiffOverviews.overviewTiff(configuration, tiff.getAbsolutePath()));
        byte[] withOverviews = Files.readAllBytes(tiff.toPath());
        assertEquals(0, GeoTiffOverviews.overviewTiff(configuration, tiff.getAbsolutePath()));
        assertArrayEquals(withOverviews, Files.readAllBytes(tiff.toPath()));
        try (TiffPages pages = new TiffPages(tiff)) {
            assertEquals(2, pages.overviews());
        }
    }

    @Test
    public void palettedImageFallsBackToNearestTest() throws Exception {
        byte[] palette = new byte[256];
        for (int i = 0; i < palette.length; i++) {
            palette[i] = (byte) i;
        }
        BufferedImage image = new BufferedImage(256, 256, BufferedImage.TYPE_BYTE_INDEXED,
                new IndexColorModel(8, 256, palette, palette, palette));
        fill(image);
        File tiff = writeGeoTiff(image, null);
        assertEquals(2, GeoTiffOverviews.overviewTiff(configuration("Bilinear", 2), tiff.getAbsolutePath()));
        try (TiffPages pages = new TiffPages(tiff)) {
            Raster overview = pages.read(1);
            assertEquals(image.getRaster().getSample(2 * 17, 2 * 23, 0), overview.getSample(17, 23, 0));
        }
    }

    @Test
    public void overviewsKeepTheCompressionTest() throws Exception {
        File tiff = writeGeoTiff(grayImage(512, 512), "LZW");
        assertEquals(2, GeoTiffOverviews.overviewTiff(configuration("Nearest", 2), tiff.getAbsolutePath()));
        try (TiffPages pages = new TiffPages(tiff)) {
            assertEquals(COMPRESSION_LZW, pages.field(0, TAG_COMPRESSION));
            assertEquals(COMPRESSION_LZW, pages.field(1, TAG_COMPRESSION));
            assertEquals(COMPRESSION_LZW, pages.field(2, TAG_COMPRESSION));
        }
    }

    @Test
    public void invalidGeoTiffIsLeftUnchangedTest() throws Exception {
        File notATiff = this.folder.newFile("broken.tif");
        Files.writeString(notATiff.toPath(), "this is not a tiff");
        assertEquals(0, GeoTiffOverviews.overviewTiff(configuration("Nearest", 2), notATiff.getAbsolutePath()));
        assertEquals("this is not a tiff", Files.readString(notATiff.toPath()));
        assertArrayEquals(new String[]{"broken.tif"}, this.folder.getRoot().list());
    }

    @Test(expected = IllegalArgumentException.class)
    public void downsampleStepMustBeGreaterThanOneTest() throws Exception {
        GeoTiffOverviewsConfiguration configuration = configuration("Nearest", 2);
        configuration.setDownsampleStep(1);
        GeoTiffOverviews.overviewTiff(configuration, writeGeoTiff(grayImage(64, 64), null).getAbsolutePath());
    }

    @Test(expected = IllegalArgumentException.class)
    public void averageIsNotSupportedTest() throws Exception {
        GeoTiffOverviews.overviewTiff(configuration("Average", 2),
                writeGeoTiff(grayImage(64, 64), null).getAbsolutePath());
    }

    @Test(expected = IllegalArgumentException.class)
    public void unknownScaleAlgorithmTest() throws Exception {
        GeoTiffOverviews.overviewTiff(configuration("Lanczos", 2),
                writeGeoTiff(grayImage(64, 64), null).getAbsolutePath());
    }

    @Test(expected = IllegalArgumentException.class)
    public void missingGeoTiffTest() throws Exception {
        GeoTiffOverviews.overviewTiff(configuration("Nearest", 2),
                new File(this.folder.getRoot(), "missing.tif").getAbsolutePath());
    }

    /**
     * @param theScaleAlgorithm
     * @param theNumSteps
     * @return {@link GeoTiffOverviewsConfiguration}
     */
    private static GeoTiffOverviewsConfiguration configuration(@Nonnull(when = NEVER) String theScaleAlgorithm,
            int theNumSteps) {
        checkArgument((theScaleAlgorithm != null) && !(theScaleAlgorithm.isBlank()),
                "The Parameter scaleAlgorithm must not be null or an empty string.");
        GeoTiffOverviewsConfiguration configuration = new GeoTiffOverviewsConfiguration();
        configuration.setScaleAlgorithm(theScaleAlgorithm);
        configuration.setNumSteps(theNumSteps);
        return configuration;
    }

    /**
     * @param theWidth
     * @param theHeight
     * @return {@link BufferedImage} a gray image whose pixels all differ from their neighbours
     */
    private static BufferedImage grayImage(int theWidth, int theHeight) {
        BufferedImage image = new BufferedImage(theWidth, theHeight, BufferedImage.TYPE_BYTE_GRAY);
        fill(image);
        return image;
    }

    private static void fill(BufferedImage theImage) {
        for (int y = 0; y < theImage.getHeight(); y++) {
            for (int x = 0; x < theImage.getWidth(); x++) {
                theImage.getRaster().setSample(x, y, 0, (x * 7 + y * 13) & 0xFF);
            }
        }
    }

    /**
     * @param theImage
     * @param theCompression the TIFF compression, or null for none
     * @return {@link File} a GeoTIFF in EPSG:4326 holding theImage
     * @throws Exception
     */
    private File writeGeoTiff(@Nonnull(when = NEVER) BufferedImage theImage, String theCompression) throws Exception {
        checkArgument(theImage != null, "The Parameter image must not be null.");
        GridCoverage2D coverage = new GridCoverageFactory().create("overviews", theImage,
                new ReferencedEnvelope(15d, 16d, 40d, 41d, CRS.decode("EPSG:4326", true)));
        File tiff = this.folder.newFile();
        GeoTiffWriter writer = new GeoTiffWriter(tiff);
        try {
            ParameterValueGroup parameters = new GeoTiffFormat().getWriteParameters();
            if (theCompression != null) {
                GeoTiffWriteParams writeParams = new GeoTiffWriteParams();
                writeParams.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
                writeParams.setCompressionType(theCompression);
                parameters.parameter(AbstractGridFormat.GEOTOOLS_WRITE_PARAMS.getName().toString()).setValue(writeParams);
            }
            writer.write(coverage, parameters.values().toArray(new GeneralParameterValue[0]));
        } finally {
            writer.dispose();
        }
        return tiff;
    }

    /**
     * @return the mean of the 2x2 block of theRaster that the overview pixel (theX, theY) covers
     */
    private static double blockMean(Raster theRaster, int theX, int theY) {
        return (theRaster.getSample(2 * theX, 2 * theY, 0) + theRaster.getSample(2 * theX + 1, 2 * theY, 0)
                + theRaster.getSample(2 * theX, 2 * theY + 1, 0) + theRaster.getSample(2 * theX + 1, 2 * theY + 1, 0))
                / 4d;
    }

    private static int[] pixels(Raster theRaster) {
        return theRaster.getPixels(theRaster.getMinX(), theRaster.getMinY(), theRaster.getWidth(),
                theRaster.getHeight(), (int[]) null);
    }

    /**
     * Reads the TIFF pages directly, independently of the GeoTIFF layout.
     */
    private static final class TiffPages implements AutoCloseable {

        private final FileImageInputStream input;
        private final TIFFImageReader reader;

        private TiffPages(File theTiff) throws Exception {
            this.input = new FileImageInputStream(theTiff);
            this.reader = (TIFFImageReader) new TIFFImageReaderSpi().createReaderInstance();
            this.reader.setInput(this.input);
        }

        private int count() throws Exception {
            return this.reader.getNumImages(true);
        }

        /**
         * @return the internal overviews, counted as GeoTools does
         */
        private int overviews() throws Exception {
            return TiffDatasetLayoutImpl.parseLayout(this.reader.getStreamMetadata()).getNumInternalOverviews();
        }

        private Raster read(int theIndex) throws Exception {
            return this.reader.read(theIndex).getData();
        }

        private int field(int theIndex, int theTag) throws Exception {
            TIFFField field = ((TIFFImageMetadata) this.reader.getImageMetadata(theIndex)).getTIFFField(theTag);
            assertNotNull(field);
            return field.getAsInt(0);
        }

        @Override
        public void close() throws Exception {
            this.reader.dispose();
            this.input.close();
        }
    }
}
