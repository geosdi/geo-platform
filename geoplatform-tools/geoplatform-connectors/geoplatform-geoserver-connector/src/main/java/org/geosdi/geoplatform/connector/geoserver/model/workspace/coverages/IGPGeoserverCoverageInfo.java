package org.geosdi.geoplatform.connector.geoserver.model.workspace.coverages;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.geosdi.geoplatform.connector.geoserver.model.IGPGeoserverResourceInfo;
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
public interface IGPGeoserverCoverageInfo extends IGPGeoserverResourceInfo {

    /**
     * @return {@link String}
     */
    String getDefaultInterpolationMethod();

    /**
     * @return {@link IGPCoverageDimensions}
     */
    IGPCoverageDimensions getDimensions();

    /**
     * @return {@link IGPCoverageGrid}
     */
    IGPCoverageGrid getGrid();

    /**
     * @return {@link IGPGeoserverCRS}
     */
    IGPGeoserverCRS getNativeCRS();

    /**
     * @return {@link IGPCoverageInterpolationMethod}
     */
    IGPCoverageInterpolationMethod getInterpolationMethod();

    /**
     * @return {@link String}
     */
    String getNativeFormat();

    /**
     * @return {@link GPGeoserverRequestSRS}
     */
    GPGeoserverRequestSRS getRequestSRS();

    /**
     * @return {@link IGPGeoserverSupportedFormat}
     */
    IGPGeoserverSupportedFormat getSupportedFormats();
}