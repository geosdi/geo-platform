package org.geosdi.geoplatform.services.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.geosdi.geoplatform.services.request.annotation.GPWMSGetFeatureInfoLayersAllowed;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@Getter
@Setter
@NoArgsConstructor
@ToString
public class GPWMSGetFeatureInfoElement implements Serializable {

    private static final long serialVersionUID = -1833248069836672682L;
    //
    @NotBlank(message = "{wms_server_url_not_blank}")
    @NotNull(message = "{wms_server_url_not_null}")
    private String wmsServerURL;
    @GPWMSGetFeatureInfoLayersAllowed
    private List<String> layers;

    /**
     * @return {@link String[]}
     */
    public String[] toLayers() {
        return (this.layers != null) ? this.layers.stream().distinct().toArray(s -> new String[s]) : new String[0];
    }
}