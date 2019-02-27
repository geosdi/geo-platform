package org.geosdi.geoplatform.jaxb.validation.configuration.store.predicate.logic;

import org.geosdi.geoplatform.jaxb.validation.configuration.ValidationMessage;
import org.geosdi.geoplatform.jaxb.validation.configuration.store.predicate.ValidationMessagePredicate;

import java.util.function.Predicate;

import static org.geosdi.geoplatform.jaxb.validation.configuration.ISeverityType.SeverityType.ERROR;
import static org.geosdi.geoplatform.jaxb.validation.configuration.ISeverityType.SeverityType.FATAL_ERROR;
import static org.geosdi.geoplatform.jaxb.validation.configuration.ValidationMessage.ValidationType.LOGIC;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class ErrorWarningPredicate implements ValidationMessagePredicate {

    protected ErrorWarningPredicate() {
    }

    /**
     * @param validationMessage
     * @return {@link Boolean}
     */
    @Override
    public boolean test(ValidationMessage validationMessage) {
        return ((validationMessage.getValidationType() == LOGIC)
                && ((validationMessage.getSeverityMessage().getType() == FATAL_ERROR)
                || (validationMessage.getSeverityMessage().getType() == ERROR)));
    }

    /**
     * @return {@link java.util.function.Predicate<ValidationMessage>}
     */
    public static Predicate<ValidationMessage> toLogicErrorPredicate() {
        return new ErrorWarningPredicate();
    }
}
