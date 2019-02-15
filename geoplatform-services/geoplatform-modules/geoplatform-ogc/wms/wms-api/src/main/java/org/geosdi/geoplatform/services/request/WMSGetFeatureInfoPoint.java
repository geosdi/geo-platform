package org.geosdi.geoplatform.services.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotNull;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@Getter
@Setter
@NoArgsConstructor
@ToString
public class WMSGetFeatureInfoPoint implements GPWMSGetFeatureInfoPoint {

    private static final long serialVersionUID = -321217195280223398L;
    //
    @NotNull(message = "{wms_x_not_null}")
    private Integer x;
    @NotNull(message = "{wms_y_not_null}")
    private Integer y;
}