package org.geosdi.geoplatform.connector.geoserver.model.featuretypes;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.geosdi.geoplatform.connector.geoserver.model.GPGeoserverResourceInfo;
import org.geosdi.geoplatform.connector.geoserver.model.bbox.GPGeoserverNativeBoundingBox;
import org.geosdi.geoplatform.connector.geoserver.model.crs.GPGeoserverCRS;
import org.geosdi.geoplatform.connector.geoserver.model.crs.GPGeoserverCRSDeserializer;
import org.geosdi.geoplatform.connector.geoserver.model.featuretypes.attribute.IGPFeatureTypeAttributes;
import org.geosdi.geoplatform.connector.geoserver.model.metadata.link.IGPGeoserverMetadataLink;
import org.geosdi.geoplatform.connector.geoserver.model.projection.GPProjectionPolicy;

import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.util.List;

import static com.google.common.base.Preconditions.checkArgument;
import static javax.xml.bind.annotation.XmlAccessType.FIELD;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@Setter
@Getter
@ToString(callSuper = true)
@XmlRootElement(name = "featureType")
@XmlAccessorType(value = FIELD)
@XmlType(propOrder = {"name", "nativeName", "namespace", "title", "abstract", "keywords", "metadataLinks", "dataLinks",
        "nativeCRS", "srs", "nativeBoundingBox", "latLonBoundingBox", "projectionPolicy", "enabled", "metadata", "store",
        "cqlFilter", "maxFeatures", "numDecimals", "responseSRS", "overridingServiceSRS", "skipNumberMatched",
        "circularArcPresent", "linearizationTolerance", "attributes"})
public class GPGeoserverFeatureTypeInfo extends GPGeoserverResourceInfo<GPGeoserverNativeBoundingBox> implements IGPGeoserverFeatureTypeInfo {

    private static final long serialVersionUID = 1449200355815165256L;
    //
    private List<IGPGeoserverMetadataLink> metadataLinks;
    private List<IGPGeoserverMetadataLink> dataLinks;
    @JsonDeserialize(using = GPGeoserverCRSDeserializer.class)
    private Object nativeCRS;
    private String cqlFilter;
    private Integer maxFeatures;
    private Integer numDecimals;
    private boolean overridingServiceSRS;
    private boolean skipNumberMatched;
    private boolean circularArcPresent;
    private Integer linearizationTolerance;
    private GPProjectionPolicy projectionPolicy;
    private IGPFeatureTypeAttributes attributes;

    /**
     * @param theNativeCRS
     */
    public void setNativeCRS(Object theNativeCRS) {
        checkArgument(((theNativeCRS instanceof String) || (theNativeCRS instanceof GPGeoserverCRS)), "The Parameter nativeCRS must be an Instance of Stirng or GPGeoserverCRS.");
        this.nativeCRS = theNativeCRS;
    }
}