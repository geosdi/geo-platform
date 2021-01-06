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
package org.geosdi.geoplatform.services.geotiff;

import org.geotools.utils.CoverageToolsConstants;
import org.geotools.utils.imageoverviews.OverviewsEmbedder.SubsampleAlgorithm;
import org.springframework.stereotype.Component;

import javax.media.jai.Interpolation;

/**
 * @author Nazzareno Sileno - CNR IMAA geoSDI Group
 * @email nazzareno.sileno@geosdi.org
 */
@Component("overviewConfiguration")
public class GeoTiffOverviewsConfiguration {

    private long JAICapacity;

    public long getJAICapacity() {
        return JAICapacity;
    }

    public void setJAICapacity(long JAICapacity) {
        this.JAICapacity = JAICapacity;
    }
    private double compressionRatio = CoverageToolsConstants.DEFAULT_COMPRESSION_RATIO;
    private String compressionScheme = CoverageToolsConstants.DEFAULT_COMPRESSION_SCHEME;
    /** Downsampling step. */
    private int downsampleStep = 2;
    private int numSteps = 8;
    /** Scale algorithm. */
    private String scaleAlgorithm = SubsampleAlgorithm.Nearest.toString();
    /** Tile height. */
    private int tileH = 256;
    /** Tile width. */
    private int tileW = 256;
    private String wildcardString = "*.*";
    private boolean logNotification = true;
    /**
     * 
     * Interpolation method used througout all the program.
     * 
     * @TODO make the interpolation method customizable from the user perpsective.
     * 
     */
    private int interp = Interpolation.INTERP_NEAREST;
    private String serviceID;

    public final double getCompressionRatio() {
        return compressionRatio;
    }

    public final String getCompressionScheme() {
        return compressionScheme;
    }

    public int getDownsampleStep() {
        return downsampleStep;
    }

    public String getScaleAlgorithm() {
        return scaleAlgorithm;
    }

    public int getTileH() {
        return tileH;
    }

    public int getTileW() {
        return tileW;
    }

    public void setCompressionRatio(double compressionRatio) {
        this.compressionRatio = compressionRatio;
    }

    public void setCompressionScheme(String compressionScheme) {
        this.compressionScheme = compressionScheme;
    }

    public void setDownsampleStep(int downsampleWH) {
        this.downsampleStep = downsampleWH;
    }

    public void setScaleAlgorithm(String scaleAlgorithm) {
        this.scaleAlgorithm = scaleAlgorithm;
    }

    public void setTileH(int tileH) {
        this.tileH = tileH;
    }

    public void setTileW(int tileW) {
        this.tileW = tileW;
    }

    public int getNumSteps() {
        return numSteps;
    }

    public void setNumSteps(int numSteps) {
        this.numSteps = numSteps;
    }

    public String getWildcardString() {
        return wildcardString;
    }

    public void setWildcardString(String wildcardString) {
        this.wildcardString = wildcardString;
    }

    public int getInterp() {
        return interp;
    }

    public void setInterp(int interp) {
        this.interp = interp;
    }

    public boolean isLogNotification() {
        return logNotification;
    }

    public void setLogNotification(boolean logNotification) {
        this.logNotification = logNotification;
    }

    /**
     * @return the serviceID
     */
    public String getServiceID() {
        return serviceID;
    }

    /**
     * @param serviceID
     *            the serviceID to set
     */
    public void setServiceID(String serviceID) {
        this.serviceID = serviceID;
    }
}
