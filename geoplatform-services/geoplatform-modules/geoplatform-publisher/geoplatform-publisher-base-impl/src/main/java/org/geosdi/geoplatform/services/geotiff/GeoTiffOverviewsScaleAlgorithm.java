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

import org.eclipse.imagen.BorderExtender;
import org.eclipse.imagen.ImageN;
import org.eclipse.imagen.Interpolation;
import org.eclipse.imagen.media.scale.ScaleDescriptor;

import javax.annotation.Nonnull;
import java.awt.RenderingHints;
import java.awt.image.RenderedImage;

import static com.google.common.base.Preconditions.checkArgument;
import static javax.annotation.meta.When.NEVER;

/**
 * Algorithms used to build each overview level from the previous one.
 * <p>
 * {@link #NEAREST} is handled by the TIFF reader through source subsampling, so it never goes through
 * {@link #downsample(RenderedImage, int)}. The "Average" and "Filtered" algorithms of the former gt-coveragetools
 * implementation are not supported: ImageN 0.9.2 SubsampleAverage averages blocks shifted by one pixel and
 * FilteredSubsample fails on the image borders.
 *
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public enum GeoTiffOverviewsScaleAlgorithm {

    NEAREST("Nearest") {
        @Override
        RenderedImage downsample(RenderedImage theImage, int theStep) {
            return scale(theImage, theStep, Interpolation.INTERP_NEAREST);
        }
    },
    BILINEAR("Bilinear") {
        @Override
        RenderedImage downsample(RenderedImage theImage, int theStep) {
            return scale(theImage, theStep, Interpolation.INTERP_BILINEAR);
        }
    },
    BICUBIC("Bicubic") {
        @Override
        RenderedImage downsample(RenderedImage theImage, int theStep) {
            return scale(theImage, theStep, Interpolation.INTERP_BICUBIC);
        }
    };

    /** Without it bilinear and bicubic read zeros beyond the image borders. */
    private static final RenderingHints COPY_BORDERS = new RenderingHints(ImageN.KEY_BORDER_EXTENDER,
            BorderExtender.createInstance(BorderExtender.BORDER_COPY));
    //
    private final String name;

    GeoTiffOverviewsScaleAlgorithm(String theName) {
        this.name = theName;
    }

    /**
     * @return the name used in {@link GeoTiffOverviewsConfiguration#getScaleAlgorithm()}
     */
    public String getName() {
        return this.name;
    }

    /**
     * @param theImage the image to reduce
     * @param theStep the reduction factor along both axes
     * @return {@link RenderedImage} the reduced image
     */
    abstract RenderedImage downsample(RenderedImage theImage, int theStep);

    /**
     * @param theName the algorithm name, case insensitive (e.g. "Nearest", "Bilinear")
     * @return {@link GeoTiffOverviewsScaleAlgorithm}
     * @throws IllegalArgumentException if the name does not match any algorithm
     */
    public static GeoTiffOverviewsScaleAlgorithm fromName(@Nonnull(when = NEVER) String theName) {
        checkArgument((theName != null) && !(theName.isBlank()), "The Parameter name must not be null or an empty string.");
        for (GeoTiffOverviewsScaleAlgorithm algorithm : values()) {
            if (algorithm.name.equalsIgnoreCase(theName.trim())) {
                return algorithm;
            }
        }
        throw new IllegalArgumentException("Unsupported scale algorithm : " + theName
                + ", use one of Nearest, Bilinear, Bicubic.");
    }

    private static RenderedImage scale(RenderedImage theImage, int theStep, int theInterpolation) {
        float factor = 1f / theStep;
        return ScaleDescriptor.create(theImage, factor, factor, 0f, 0f, Interpolation.getInstance(theInterpolation),
                COPY_BORDERS);
    }
}
