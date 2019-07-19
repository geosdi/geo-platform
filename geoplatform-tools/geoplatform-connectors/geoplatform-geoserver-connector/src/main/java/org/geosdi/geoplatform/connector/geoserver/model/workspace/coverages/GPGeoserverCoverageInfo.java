package org.geosdi.geoplatform.connector.geoserver.model.workspace.coverages;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.geosdi.geoplatform.connector.geoserver.model.GPGeoserverResourceInfo;
import org.geosdi.geoplatform.connector.geoserver.model.bbox.GPGeoserverNativeBoundingBox;
import org.geosdi.geoplatform.connector.geoserver.model.crs.IGPGeoserverCRS;
import org.geosdi.geoplatform.connector.geoserver.model.format.IGPGeoserverSupportedFormat;
import org.geosdi.geoplatform.connector.geoserver.model.srs.GPGeoserverRequestSRS;
import org.geosdi.geoplatform.connector.geoserver.model.workspace.coverages.dimension.IGPCoverageDimensions;
import org.geosdi.geoplatform.connector.geoserver.model.workspace.coverages.grid.IGPCoverageGrid;
import org.geosdi.geoplatform.connector.geoserver.model.workspace.coverages.interpolation.IGPCoverageInterpolationMethod;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@Setter
@Getter
@ToString(callSuper = true)
@XmlRootElement(name = "coverage")
@XmlAccessorType(value = XmlAccessType.FIELD)
public class GPGeoserverCoverageInfo extends GPGeoserverResourceInfo<GPGeoserverNativeBoundingBox> implements IGPGeoserverCoverageInfo {

    private static final long serialVersionUID = 2534762636718172525L;
    //
    private String defaultInterpolationMethod;
    private IGPCoverageDimensions dimensions;
    private IGPCoverageGrid grid;
    private IGPGeoserverCRS nativeCRS;
    @XmlElement(name = "interpolationMethods")
    private IGPCoverageInterpolationMethod interpolationMethod;
    private String nativeFormat;
    private GPGeoserverRequestSRS requestSRS;
    private IGPGeoserverSupportedFormat supportedFormats;
}