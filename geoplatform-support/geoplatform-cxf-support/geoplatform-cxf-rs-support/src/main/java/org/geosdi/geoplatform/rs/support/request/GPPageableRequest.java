package org.geosdi.geoplatform.rs.support.request;

import lombok.*;
import org.geosdi.geoplatform.rs.support.request.annotation.GPPageableFrom;
import org.geosdi.geoplatform.rs.support.request.annotation.GPPageableSize;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
@XmlAccessorType(XmlAccessType.FIELD)
public class GPPageableRequest extends GPI18NRequest {

    private static final long serialVersionUID = -635571665699739878L;
    //
    @NotNull(message = "{gp.pageable_from_not_null.message}")
    @GPPageableFrom
    private Integer from;
    @NotNull(message = "{gp.pageable_size_not_null.message}")
    @GPPageableSize
    private Integer size;
}