package org.geosdi.geoplatform.services.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.geosdi.geoplatform.gui.shared.bean.BBox;

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
public class GPWFSSearchFeaturesByBboxAndQueryRequest extends GPWFSSearchFeaturesRequest {

    private static final long serialVersionUID = 3181149872128222998L;
    //
    private BBox bBox;
}