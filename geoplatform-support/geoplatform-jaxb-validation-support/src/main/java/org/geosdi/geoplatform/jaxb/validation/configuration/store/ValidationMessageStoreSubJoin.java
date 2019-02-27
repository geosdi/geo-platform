package org.geosdi.geoplatform.jaxb.validation.configuration.store;

import org.geosdi.geoplatform.jaxb.validation.configuration.ValidationMessage;

import java.io.Serializable;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@FunctionalInterface
public interface ValidationMessageStoreSubJoin extends Serializable {

    /**
     * @param validationMessage
     */
    void addValidationMessage(ValidationMessage validationMessage);
}
