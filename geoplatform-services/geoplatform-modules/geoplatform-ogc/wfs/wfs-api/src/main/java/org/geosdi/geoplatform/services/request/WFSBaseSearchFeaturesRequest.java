package org.geosdi.geoplatform.services.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.geosdi.geoplatform.services.request.annotation.GPWFSTypeNameAllowed;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlTransient;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@XmlTransient
abstract class WFSBaseSearchFeaturesRequest extends GPWFSRequest {

    private static final long serialVersionUID = -6804744415618740075L;
    //
    @NotBlank(message = "{wfs_server_url_not_blank}")
    @NotNull(message = "{wfs_server_url_not_null}")
    private String serverURL;
    @NotBlank(message = "{wfs_type_name_not_blank}")
    @NotNull(message = "{wfs_type_name_not_null}")
    @NotBlank(message = "{wfs_type_name_not_blank}")
    @NotNull(message = "{wfs_type_name_not_null}")
    @GPWFSTypeNameAllowed
    private String typeName;
    @NotNull(message = "{wfs_max_features_not_null}")
    @Range(min = 1l, message = "{wfs_max_features_must_be_positive}")
    private Integer maxFeatures;
}