package org.geosdi.geoplatform.jaxb.validation.configuration.store.predicate.logic;

import org.geosdi.geoplatform.jaxb.validation.configuration.ValidationMessage;
import org.geosdi.geoplatform.jaxb.validation.configuration.store.predicate.ValidationMessagePredicate;

import java.util.function.Predicate;

import static org.geosdi.geoplatform.jaxb.validation.configuration.ISeverityType.SeverityType.WARNING;
import static org.geosdi.geoplatform.jaxb.validation.configuration.ValidationMessage.ValidationType.LOGIC;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class LogicWarningPredicate implements ValidationMessagePredicate {

    protected LogicWarningPredicate() {
    }

    /**
     * @param validationMessage
     * @return
     */
    @Override
    public boolean test(ValidationMessage validationMessage) {
        return ((validationMessage.getValidationType() == LOGIC)
                && (validationMessage.getSeverityMessage().getType() == WARNING));
    }

    /**
     * @return {@link java.util.function.Predicate<ValidationMessage>}
     */
    public static Predicate<ValidationMessage> toLogicWarningPredicate() {
        return new LogicWarningPredicate();
    }
}
