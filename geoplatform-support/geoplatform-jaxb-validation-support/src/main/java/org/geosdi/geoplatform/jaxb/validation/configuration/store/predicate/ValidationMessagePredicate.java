package org.geosdi.geoplatform.jaxb.validation.configuration.store.predicate;

import org.geosdi.geoplatform.jaxb.validation.configuration.ValidationMessage;

import java.util.function.Predicate;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public interface ValidationMessagePredicate extends Predicate<ValidationMessage> {
}
