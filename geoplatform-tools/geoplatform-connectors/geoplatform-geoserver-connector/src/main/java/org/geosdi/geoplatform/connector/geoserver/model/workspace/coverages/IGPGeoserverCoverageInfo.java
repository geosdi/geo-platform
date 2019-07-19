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