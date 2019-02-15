package org.geosdi.geoplatform.services.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@XmlRootElement(name = "GPWMSGetFeatureInfoRequest")
@XmlAccessorType
public class GPWMSGetFeatureInfoRequest extends GPWMSRequest {

    private static final long serialVersionUID = 5591882782946299016L;
    //
    @NotBlank(message = "{wms_crs_not_blank}")
    @NotNull(message = "{wms_crs_not_null}")
    private String crs;
    @NotBlank(message = "{wms_width_not_blank}")
    @NotNull(message = "{wms_width_not_null}")
    private String width;
    @NotBlank(message = "{wms_height_not_blank}")
    @NotNull(message = "{wms_height_not_null}")
    private String height;
    @NotNull(message = "{wms_bbox_not_null}")
    @Valid
    private WMSGetFeatureInfoBoundingBox boundingBox;
    @NotNull(message = "{wms_point_not_null}")
    @Valid
    private WMSGetFeatureInfoPoint point;
    @NotNull(message = "{wms_get_feature_info_elements_not_null}")
    private List<@Valid GPWMSGetFeatureInfoElement> wmsFeatureInfoElements;
}