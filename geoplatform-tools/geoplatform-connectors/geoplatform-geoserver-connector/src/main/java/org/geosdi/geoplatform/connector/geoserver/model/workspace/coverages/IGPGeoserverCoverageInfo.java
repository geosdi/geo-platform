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
package org.geosdi.geoplatform.connector.geoserver.model.workspace.coverages;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.geosdi.geoplatform.connector.geoserver.model.IGPGeoserverResourceInfo;
import org.geosdi.geoplatform.connector.geoserver.model.bbox.GPGeoserverNativeBoundingBox;
import org.geosdi.geoplatform.connector.geoserver.model.crs.IGPGeoserverCRS;
import org.geosdi.geoplatform.connector.geoserver.model.format.IGPGeoserverSupportedFormat;
import org.geosdi.geoplatform.connector.geoserver.model.srs.GPGeoserverRequestSRS;
import org.geosdi.geoplatform.connector.geoserver.model.workspace.coverages.dimension.IGPCoverageDimensions;
import org.geosdi.geoplatform.connector.geoserver.model.workspace.coverages.grid.IGPCoverageGrid;
import org.geosdi.geoplatform.connector.geoserver.model.workspace.coverages.interpolation.IGPCoverageInterpolationMethod;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@JsonDeserialize(as = GPGeoserverCoverageInfo.class)
public interface IGPGeoserverCoverageInfo extends IGPGeoserverResourceInfo<GPGeoserverNativeBoundingBox> {

    /**
     * @return {@link String}
     */
    String getDefaultInterpolationMethod();

    /**
     * @param theDefaultInterpolationMethod
     */
    void setDefaultInterpolationMethod(String theDefaultInterpolationMethod);

    /**
     * @return {@link IGPCoverageDimensions}
     */
    IGPCoverageDimensions getDimensions();

    /**
     * @param theDimensions
     */
    void setDimensions(IGPCoverageDimensions theDimensions);

    /**
     * @return {@link IGPCoverageGrid}
     */
    IGPCoverageGrid getGrid();

    /**
     * @param theGrid
     */
    void setGrid(IGPCoverageGrid theGrid);

    /**
     * @return {@link IGPGeoserverCRS}
     */
    IGPGeoserverCRS getNativeCRS();

    /**
     * @param theNativeCRS
     */
    void setNativeCRS(IGPGeoserverCRS theNativeCRS);

    /**
     * @return {@link IGPCoverageInterpolationMethod}
     */
    IGPCoverageInterpolationMethod getInterpolationMethod();

    /**
     * @param theInterpolationMethod
     */
    void setInterpolationMethod(IGPCoverageInterpolationMethod theInterpolationMethod);

    /**
     * @return {@link String}
     */
    String getNativeFormat();

    /**
     * @param theNativeFormat
     */
    void setNativeFormat(String theNativeFormat);

    /**
     * @return {@link GPGeoserverRequestSRS}
     */
    GPGeoserverRequestSRS getRequestSRS();

    /**
     * @param theRequestSRS
     */
    void setRequestSRS(GPGeoserverRequestSRS theRequestSRS);

    /**
     * @return {@link IGPGeoserverSupportedFormat}
     */
    IGPGeoserverSupportedFormat getSupportedFormats();

    /**
     * @param theSupportedFormats
     */
    void setSupportedFormats(IGPGeoserverSupportedFormat theSupportedFormats);
}