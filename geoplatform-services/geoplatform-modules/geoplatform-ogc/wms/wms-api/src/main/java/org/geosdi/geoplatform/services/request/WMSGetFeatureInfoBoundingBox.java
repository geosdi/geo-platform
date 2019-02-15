package org.geosdi.geoplatform.services.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.geosdi.geoplatform.connector.server.request.GPWMSBoundingBox;
import org.geosdi.geoplatform.connector.server.request.WMSBoundingBox;

import javax.validation.constraints.NotNull;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@Getter
@Setter
@NoArgsConstructor
@ToString
public class WMSGetFeatureInfoBoundingBox implements GPWMSGetFeatureInfoBoundingBox {

    private static final long serialVersionUID = 7432677934521830936L;
    //
    @NotNull(message = "{wms_bbox_minx_not_null}")
    private Double minx;
    @NotNull(message = "{wms_bbox_miny_not_null}")
    private Double miny;
    @NotNull(message = "{wms_bbox_maxx_not_null}")
    private Double maxx;
    @NotNull(message = "{wms_bbox_maxy_not_null}")
    private Double maxy;

    /**
     * @return {@link GPWMSBoundingBox}
     */
    @Override
    public GPWMSBoundingBox toWMSBoundingBox() {
        return new WMSBoundingBox(this.minx, this.miny, this.maxx, this.maxy);
    }
}