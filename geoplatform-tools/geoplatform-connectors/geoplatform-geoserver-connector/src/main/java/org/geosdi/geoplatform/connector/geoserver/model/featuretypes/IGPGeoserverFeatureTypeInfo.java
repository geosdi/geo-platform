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
package org.geosdi.geoplatform.connector.geoserver.model.featuretypes;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.geosdi.geoplatform.connector.geoserver.model.IGPGeoserverResourceInfo;
import org.geosdi.geoplatform.connector.geoserver.model.bbox.GPGeoserverNativeBoundingBox;
import org.geosdi.geoplatform.connector.geoserver.model.featuretypes.attribute.IGPFeatureTypeAttributes;
import org.geosdi.geoplatform.connector.geoserver.model.metadata.link.IGPGeoserverMetadataLink;
import org.geosdi.geoplatform.connector.geoserver.model.projection.GPProjectionPolicy;

import java.util.List;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@JsonDeserialize(as = GPGeoserverFeatureTypeInfo.class)
public interface IGPGeoserverFeatureTypeInfo extends IGPGeoserverResourceInfo<GPGeoserverNativeBoundingBox> {

    /**
     * @return {@link List<IGPGeoserverMetadataLink>}
     */
    List<IGPGeoserverMetadataLink> getMetadataLinks();

    /**
     * @param theMetadataLinks
     */
    void setMetadataLinks(List<IGPGeoserverMetadataLink> theMetadataLinks);

    /**
     * @return {@link List<IGPGeoserverMetadataLink>}
     */
    List<IGPGeoserverMetadataLink> getDataLinks();

    /**
     * @param theDataLinks
     */
    void setDataLinks(List<IGPGeoserverMetadataLink> theDataLinks);

    /**
     * @return {@link Object}
     */
    Object getNativeCRS();

    /**
     * <p>
     *     Possible values are :
     *     <ul>
     *         <li>String as : EPSG:4326</li>
     *         <li>{@link org.geosdi.geoplatform.connector.geoserver.model.crs.IGPGeoserverCRS}</li>
     *     </ul>
     * </p>
     * @param theNativeCRS
     */
    void setNativeCRS(Object theNativeCRS);

    /**
     * @return {@link String}
     */
    String getCqlFilter();

    /**
     * @param theCqlFilter
     */
    void setCqlFilter(String theCqlFilter);

    /**
     * @return {@link Integer}
     */
    Integer getMaxFeatures();

    /**
     * @param theMaxFeatures
     */
    void setMaxFeatures(Integer theMaxFeatures);

    /**
     * @return {@link Integer}
     */
    Integer getNumDecimals();

    /**
     * @param theNumDecimals
     */
    void setNumDecimals(Integer theNumDecimals);

    /**
     * @return {@link Boolean}
     */
    boolean isOverridingServiceSRS();

    /**
     * @param theOverridingServiceSRS
     */
    void setOverridingServiceSRS(boolean theOverridingServiceSRS);

    /**
     * @return {@link Boolean}
     */
    boolean isSkipNumberMatched();

    /**
     * @param theSkipNumberMatched
     */
    void setSkipNumberMatched(boolean theSkipNumberMatched);

    /**
     * @return {@link Boolean}
     */
    boolean isCircularArcPresent();

    /**
     * @param theCircularArcPresent
     */
    void setCircularArcPresent(boolean theCircularArcPresent);

    /**
     * @return {@link Integer}
     */
    Integer getLinearizationTolerance();

    /**
     * @param theLinearizationTolerance
     */
    void setLinearizationTolerance(Integer theLinearizationTolerance);

    /**
     * @return {@link GPProjectionPolicy}
     */
    GPProjectionPolicy getProjectionPolicy();

    /**
     * <p>
     * Possible Values are :
     * <ul>
     * <li>{@link GPProjectionPolicy#FORCE_DECLARED} Use the declared CRS (ignore native).</li>
     * <li>{@link GPProjectionPolicy#REPROJECT_TO_DECLARED} Reproject from native to declared CRS</li>
     * <li>{@link GPProjectionPolicy#NONE} No reprojection (use native CRS)</li>
     * </ul>
     * </p>
     *
     * @param theProjectionPolicy
     */
    void setProjectionPolicy(GPProjectionPolicy theProjectionPolicy);

    /**
     * @return {@link IGPFeatureTypeAttributes}
     */
    IGPFeatureTypeAttributes getAttributes();

    /**
     * @param theAttributes
     */
    void setAttributes(IGPFeatureTypeAttributes theAttributes);
}