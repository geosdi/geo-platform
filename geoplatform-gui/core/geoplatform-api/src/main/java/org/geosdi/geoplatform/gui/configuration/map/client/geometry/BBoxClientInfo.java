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
package org.geosdi.geoplatform.gui.configuration.map.client.geometry;

import java.io.Serializable;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 * 
 */
public class BBoxClientInfo implements Serializable {

    private static final long serialVersionUID = -6733851739584878237L;
    //
    private double lowerLeftX;
    private double lowerLeftY;
    private double upperRightX;
    private double upperRightY;

    public BBoxClientInfo() {
    }

    public BBoxClientInfo(double lowerLeftX, double lowerLeftY, double upperRightX, double upperRightY) {
        this.lowerLeftX = lowerLeftX;
        this.lowerLeftY = lowerLeftY;
        this.upperRightX = upperRightX;
        this.upperRightY = upperRightY;
    }

    /**
     * @return the lowerLeftX
     */
    public double getLowerLeftX() {
        return lowerLeftX;
    }

    /**
     * @param lowerLeftX
     *            the lowerLeftX to set
     */
    public void setLowerLeftX(double lowerLeftX) {
        this.lowerLeftX = lowerLeftX;
    }

    /**
     * @return the lowerLeftY
     */
    public double getLowerLeftY() {
        return lowerLeftY;
    }

    /**
     * @param lowerLeftY
     *            the lowerLeftY to set
     */
    public void setLowerLeftY(double lowerLeftY) {
        this.lowerLeftY = lowerLeftY;
    }

    /**
     * @return the upperRightX
     */
    public double getUpperRightX() {
        return upperRightX;
    }

    /**
     * @param upperRightX
     *            the upperRightX to set
     */
    public void setUpperRightX(double upperRightX) {
        this.upperRightX = upperRightX;
    }

    /**
     * @return the upperRightY
     */
    public double getUpperRightY() {
        return upperRightY;
    }

    /**
     * @param upperRightY
     *            the upperRightY to set
     */
    public void setUpperRightY(double upperRightY) {
        this.upperRightY = upperRightY;
    }

    /**
     * (non-Javadoc)
     *
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "BBoxClientInfo [lowerLeftX=" + lowerLeftX + ", lowerLeftY="
                + lowerLeftY + ", upperRightX=" + upperRightX
                + ", upperRightY=" + upperRightY + "]";
    }
}
