package org.geosdi.geoplatform.services.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.geosdi.geoplatform.gui.shared.bean.BBox;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessorType;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@Getter
@Setter
@ToString(callSuper = true)
@NoArgsConstructor
@XmlAccessorType
public class GPWFSSearchFeaturesByBboxRequest extends WFSBaseSearchFeaturesRequest {

    private static final long serialVersionUID = 602981407339605893L;
    //
    @NotNull(message = "{wfs_bbox_not_null}")
    private BBox bBox;
}