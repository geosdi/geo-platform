package org.geosdi.geoplatform.rs.support.request.update;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.geosdi.geoplatform.hibernate.validator.support.request.GPI18NRequestValidator;

import javax.annotation.Nonnull;

import static javax.annotation.meta.When.NEVER;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public interface GPUpdateRequestBinder<B extends Object> extends GPI18NRequestValidator {

    /**
     * @param theValue
     * @return {@link B}
     * @throws Exception
     */
    @JsonIgnore
    B update(@Nonnull(when = NEVER) B theValue) throws Exception;
}