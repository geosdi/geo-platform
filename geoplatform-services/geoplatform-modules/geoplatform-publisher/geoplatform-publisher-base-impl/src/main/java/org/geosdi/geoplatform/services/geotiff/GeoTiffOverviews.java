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

import it.geosolutions.imageio.maskband.DatasetLayout;
import it.geosolutions.imageio.plugins.tiff.TIFFField;
import it.geosolutions.imageio.plugins.tiff.TIFFImageWriteParam;
import it.geosolutions.imageioimpl.plugins.tiff.TIFFImageMetadata;
import it.geosolutions.imageioimpl.plugins.tiff.TIFFImageReader;
import it.geosolutions.imageioimpl.plugins.tiff.TIFFImageReaderSpi;
import it.geosolutions.imageioimpl.plugins.tiff.TIFFImageWriter;
import it.geosolutions.imageioimpl.plugins.tiff.TIFFImageWriterSpi;
import it.geosolutions.imageioimpl.plugins.tiff.TiffDatasetLayoutImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Nonnull;
import javax.imageio.IIOImage;
import javax.imageio.ImageReadParam;
import javax.imageio.ImageTypeSpecifier;
import javax.imageio.ImageWriteParam;
import javax.imageio.metadata.IIOMetadataNode;
import javax.imageio.stream.FileImageInputStream;
import javax.imageio.stream.FileImageOutputStream;
import java.awt.image.IndexColorModel;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.AtomicMoveNotSupportedException;
import java.nio.file.Files;
import java.nio.file.Path;

import static com.google.common.base.Preconditions.checkArgument;
import static it.geosolutions.imageio.plugins.tiff.BaselineTIFFTagSet.*;
import static java.nio.file.StandardCopyOption.ATOMIC_MOVE;
import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;
import static javax.annotation.meta.When.NEVER;
import static org.geosdi.geoplatform.services.geotiff.GeoTiffOverviewsScaleAlgorithm.NEAREST;

/**
 * Embeds internal overviews in a GeoTIFF, in the layout GeoTools and GeoServer read: every overview is an extra
 * TIFF page flagged as reduced resolution ({@code NewSubfileType = 1}).
 * <p>
 * The overviews are appended to a copy of the file, which then replaces the original, so a failure never leaves a
 * half written GeoTIFF behind.
 *
 * @author Nazzareno Sileno - CNR IMAA geoSDI Group
 * @email nazzareno.sileno@geosdi.org
 */
public class GeoTiffOverviews {

    private static final Logger logger = LoggerFactory.getLogger(GeoTiffOverviews.class);

    private GeoTiffOverviews() {
    }

    /**
     * Adds {@link GeoTiffOverviewsConfiguration#getNumSteps()} overviews, each one
     * {@link GeoTiffOverviewsConfiguration#getDownsampleStep()} times smaller than the previous, stopping earlier if
     * an overview would be smaller than one pixel. A GeoTIFF which already has internal overviews is left untouched.
     * <p>
     * The overviews are an optimisation, so a failure while building them is logged and the original file is kept.
     *
     * @param theConfiguration the overviews configuration
     * @param theFileAbsolutePath the GeoTIFF to update
     * @return the number of overviews added
     * @throws IllegalArgumentException if the configuration is not valid or the file does not exist
     */
    public static int overviewTiff(@Nonnull(when = NEVER) GeoTiffOverviewsConfiguration theConfiguration,
            @Nonnull(when = NEVER) String theFileAbsolutePath) {
        checkArgument(theConfiguration != null, "The Parameter configuration must not be null.");
        checkArgument(theConfiguration.getDownsampleStep() > 1, "Illegal downsampleStep: %s",
                theConfiguration.getDownsampleStep());
        checkArgument(theConfiguration.getNumSteps() > 0, "Illegal numberOfSteps: %s", theConfiguration.getNumSteps());
        checkArgument(theConfiguration.getTileW() > 0 && theConfiguration.getTileH() > 0, "Illegal tile size: %sx%s",
                theConfiguration.getTileW(), theConfiguration.getTileH());
        GeoTiffOverviewsScaleAlgorithm algorithm = GeoTiffOverviewsScaleAlgorithm.fromName(
                theConfiguration.getScaleAlgorithm());
        checkArgument((theFileAbsolutePath != null) && !(theFileAbsolutePath.isBlank()),
                "The Parameter fileAbsolutePath must not be null or an empty string.");
        File tiff = new File(theFileAbsolutePath);
        checkArgument(tiff.isFile(), "The GeoTIFF %s does not exist.", theFileAbsolutePath);
        Path target = tiff.toPath();
        Path copy = target.resolveSibling(tiff.getName() + ".overviews.tmp");
        try {
            Files.copy(target, copy, REPLACE_EXISTING);
            int overviews = embedOverviews(theConfiguration, algorithm, tiff, copy.toFile());
            if (overviews > 0) {
                replace(copy, target);
            }
            logger.debug("@@@@@@@@@@@@@@@@@@@@@@ Added {} overviews to {}", overviews, theFileAbsolutePath);
            return overviews;
        } catch (Exception ex) {
            logger.error("@@@@@@@@@@@@@@@@@@@@@@ Unable to add the overviews to {}, the GeoTIFF is left unchanged",
                    theFileAbsolutePath, ex);
            return 0;
        } finally {
            try {
                Files.deleteIfExists(copy);
            } catch (IOException ex) {
                logger.warn("@@@@@@@@@@@@@@@@@@@@@@ Unable to delete {}", copy, ex);
            }
        }
    }

    /**
     * Reads the source GeoTIFF and appends the overviews to the target, which must be a copy of the source.
     */
    private static int embedOverviews(GeoTiffOverviewsConfiguration theConfiguration,
            GeoTiffOverviewsScaleAlgorithm theAlgorithm, File theSource, File theTarget) throws IOException {
        TIFFImageReader reader = (TIFFImageReader) new TIFFImageReaderSpi().createReaderInstance();
        TIFFImageWriter writer = (TIFFImageWriter) new TIFFImageWriterSpi().createWriterInstance();
        try (FileImageInputStream input = new FileImageInputStream(theSource);
             FileImageOutputStream output = new FileImageOutputStream(theTarget)) {
            reader.setInput(input);
            DatasetLayout layout = TiffDatasetLayoutImpl.parseLayout(reader.getStreamMetadata());
            if ((layout != null) && (layout.getNumInternalOverviews() > 0)) {
                logger.info("@@@@@@@@@@@@@@@@@@@@@@ {} already has {} internal overviews, nothing to do", theSource,
                        layout.getNumInternalOverviews());
                return 0;
            }
            RenderedImage image = reader.readAsRenderedImage(0, reader.getDefaultReadParam());
            GeoTiffOverviewsScaleAlgorithm algorithm = theAlgorithm;
            if ((algorithm != NEAREST) && (image.getColorModel() instanceof IndexColorModel)) {
                logger.info("@@@@@@@@@@@@@@@@@@@@@@ {} is a paletted image, using {} instead of {}", theSource,
                        NEAREST.getName(), algorithm.getName());
                algorithm = NEAREST;
            }
            String compression = compressionOf((TIFFImageMetadata) reader.getImageMetadata(0));
            writer.setOutput(output);
            int width = reader.getWidth(0);
            int height = reader.getHeight(0);
            int step = theConfiguration.getDownsampleStep();
            long factor = 1;
            int overviews = 0;
            RenderedImage overview = image;
            while (overviews < theConfiguration.getNumSteps()) {
                factor *= step;
                if ((factor > width) || (factor > height)) {
                    break;
                }
                overview = (algorithm == NEAREST) ? subsample(reader, (int) factor)
                        : algorithm.downsample(overview, step);
                writeOverview(writer, overview, theConfiguration, compression);
                overviews++;
            }
            return overviews;
        } finally {
            reader.dispose();
            writer.dispose();
        }
    }

    /**
     * Reads the full resolution image taking one pixel every theFactor along both axes.
     */
    private static RenderedImage subsample(TIFFImageReader theReader, int theFactor) throws IOException {
        ImageReadParam param = theReader.getDefaultReadParam();
        param.setSourceSubsampling(theFactor, theFactor, 0, 0);
        return theReader.readAsRenderedImage(0, param);
    }

    private static void writeOverview(TIFFImageWriter theWriter, RenderedImage theOverview,
            GeoTiffOverviewsConfiguration theConfiguration, String theCompression) throws IOException {
        TIFFImageWriteParam param = (TIFFImageWriteParam) theWriter.getDefaultWriteParam();
        param.setTilingMode(ImageWriteParam.MODE_EXPLICIT);
        param.setTiling(theConfiguration.getTileW(), theConfiguration.getTileH(), 0, 0);
        if (theCompression != null) {
            param.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
            param.setCompressionType(theCompression);
        } else {
            param.setCompressionMode(ImageWriteParam.MODE_DISABLED);
        }
        TIFFImageMetadata metadata = (TIFFImageMetadata) theWriter.getDefaultImageMetadata(
                ImageTypeSpecifier.createFromRenderedImage(theOverview), param);
        markAsReducedResolution(metadata);
        theWriter.writeInsert(-1, new IIOImage(theOverview, null, metadata), param);
    }

    /**
     * Sets NewSubfileType to reduced resolution. It must be stored as a LONG, as the TIFF specification says: the
     * imageio-ext reader, and so GeoTools, only counts the overviews whose NewSubfileType is a LONG, while
     * {@link TIFFImageMetadata#addShortOrLongField(int, int)} would store such a small value as a SHORT.
     */
    private static void markAsReducedResolution(TIFFImageMetadata theMetadata) throws IOException {
        IIOMetadataNode value = new IIOMetadataNode("TIFFLong");
        value.setAttribute("value", Integer.toString(NEW_SUBFILE_TYPE_REDUCED_RESOLUTION));
        IIOMetadataNode values = new IIOMetadataNode("TIFFLongs");
        values.appendChild(value);
        IIOMetadataNode field = new IIOMetadataNode("TIFFField");
        field.setAttribute("number", Integer.toString(TAG_NEW_SUBFILE_TYPE));
        field.appendChild(values);
        IIOMetadataNode ifd = new IIOMetadataNode("TIFFIFD");
        ifd.appendChild(field);
        IIOMetadataNode root = new IIOMetadataNode(theMetadata.getNativeMetadataFormatName());
        root.appendChild(ifd);
        theMetadata.mergeTree(theMetadata.getNativeMetadataFormatName(), root);
    }

    /**
     * @return the writer compression type matching the one of the full resolution image, or null for none. JPEG is
     * not reused because it only fits 8 bit images, so those overviews get Deflate.
     */
    private static String compressionOf(TIFFImageMetadata theMetadata) {
        TIFFField field = theMetadata.getTIFFField(TAG_COMPRESSION);
        int compression = (field != null) ? field.getAsInt(0) : COMPRESSION_NONE;
        return switch (compression) {
            case COMPRESSION_NONE -> null;
            case COMPRESSION_LZW -> "LZW";
            case COMPRESSION_PACKBITS -> "PackBits";
            case COMPRESSION_ZLIB -> "ZLib";
            default -> "Deflate";
        };
    }

    private static void replace(Path theSource, Path theTarget) throws IOException {
        try {
            Files.move(theSource, theTarget, REPLACE_EXISTING, ATOMIC_MOVE);
        } catch (AtomicMoveNotSupportedException ex) {
            Files.move(theSource, theTarget, REPLACE_EXISTING);
        }
    }
}
