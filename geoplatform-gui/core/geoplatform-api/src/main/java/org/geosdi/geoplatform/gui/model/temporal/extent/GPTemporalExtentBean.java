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
package org.geosdi.geoplatform.gui.model.temporal.extent;

import org.geosdi.geoplatform.gui.model.GeoPlatformBeanModel;

import static org.geosdi.geoplatform.gui.model.temporal.extent.IGPTemporalExtentBean.GPTemporalExtensionKeyValue.*;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class GPTemporalExtentBean extends GeoPlatformBeanModel implements IGPTemporalExtentBean {

    private static final long serialVersionUID = 5502106936262045758L;
    //
    private Boolean range;

    /**
     * @return {@link String}
     */
    @Override
    public String getName() {
        return super.get(TEMPORAL_EXTENT_NAME.toString(), "");
    }

    /**
     * @param theName
     */
    @Override
    public void setName(String theName) {
        super.set(TEMPORAL_EXTENT_NAME.toString(), theName);
    }

    /**
     * @return {@link String}
     */
    @Override
    public String getDefaultExtent() {
        return super.get(TEMPORAL_DEFAULT_EXTENT.toString(), "");
    }

    /**
     * @param theDefaultExtent
     */
    @Override
    public void setDefaultExtent(String theDefaultExtent) {
        super.set(TEMPORAL_DEFAULT_EXTENT.toString(), theDefaultExtent);
    }

    /**
     * @return {@link String}
     */
    @Override
    public String getValue() {
        return super.get(TEMPORAL_EXTENT_VALUE.toString(), "");
    }

    /**
     * @param theValue
     */
    @Override
    public void setValue(String theValue) {
        super.set(TEMPORAL_EXTENT_VALUE.toString(), theValue);
    }

    /**
     * @return {@link Boolean}
     */
    @Override
    public boolean isTemporal() {
        return ((this.getName() != null) && !(this.getName().trim().isEmpty())) && ((this.getValue() != null) && !(this.getValue().trim().isEmpty()));
    }

    /**
     *
     * @return {@link Boolean}
     */
    public Boolean isRange() {
        return range;
    }

    /**
     *
     * @param range
     */
    @Override
    public void setRange(Boolean range) {
        this.range = range;
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + "{" +
                "extentName = " + this.getName() +
                ", defaultExtent = " + this.getDefaultExtent() +
                ", extentValue = " + this.getValue() +
                ", isRange = " + this.isRange().toString() +
                "}";
    }
}