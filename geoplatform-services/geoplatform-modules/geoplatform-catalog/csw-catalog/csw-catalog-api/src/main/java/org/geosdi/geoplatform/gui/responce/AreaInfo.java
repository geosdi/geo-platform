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
package org.geosdi.geoplatform.gui.responce;

import org.geosdi.geoplatform.gui.shared.bean.BBox;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

/**
 * @author Vincenzo Monteverde <vincenzo.monteverde@geosdi.org>
 * @author Giuseppe La Scaleia <giuseppe.lascaleia@geosdi.org>
 */
public class AreaInfo implements Serializable {

    private static final long serialVersionUID = -8000840803980598023L;
    //
    private boolean active;
    private BBox bBox;
    private AreaSearchType areaSearchType;

    public enum AreaSearchType {

        /**
         * Stored geometry fully contains the input geometry. Spatial operator:
         * Contains
         */
        ENCLOSES,
        /**
         * Stored geometry are equal the input geometry. Spatial operator:
         * Equals
         */
        IS,
        /**
         * Stored geometry is fully outside the input geometry. Spatial
         * operator: Disjoint
         */
        OUTSIDE,
        /**
         * Stored geometry intersect but are not equal the input geometry.
         * Spatial operator: Intersects
         */
        OVERLAP;

        public static List<AreaSearchType> valuesAsList() {
            return Arrays.asList(AreaSearchType.values());
        }

        public static AreaSearchType fromString(String text) {
            if (text != null) {
                for (AreaSearchType t : AreaSearchType.values()) {
                    if (text.trim().equalsIgnoreCase(t.toString())) {
                        return t;
                    }
                }
            }
            return null;
        }

        @Override
        public String toString() {
            return super.toString();
        }
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public BBox getBBox() {
        return bBox;
    }

    public void setBBox(BBox bBox) {
        this.bBox = bBox;
    }

    public AreaSearchType getAreaSearchType() {
        return areaSearchType;
    }

    public void setAreaSearchType(AreaSearchType areaSearchType) {
        this.areaSearchType = areaSearchType;
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder("AreaInfo {");
        str.append("active = ").append(active);
        str.append(", bBox = ").append(bBox);
        str.append(", areaSearchType = ").append(areaSearchType);
        return str.append('}').toString();
    }
}
