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