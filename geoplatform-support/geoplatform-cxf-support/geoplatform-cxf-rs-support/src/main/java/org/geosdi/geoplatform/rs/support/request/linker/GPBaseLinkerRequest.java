package org.geosdi.geoplatform.rs.support.request.linker;

import org.geosdi.geoplatform.hibernate.validator.support.request.GPI18NRequestValidator;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.orgu
 */
public interface GPBaseLinkerRequest extends GPI18NRequestValidator {

    /**
     * @return {@link GPLinkerType}
     */
    GPLinkerType getType();

    /**
     *
     * @param theLinkerType
     */
    void setType(GPLinkerType theLinkerType);
}