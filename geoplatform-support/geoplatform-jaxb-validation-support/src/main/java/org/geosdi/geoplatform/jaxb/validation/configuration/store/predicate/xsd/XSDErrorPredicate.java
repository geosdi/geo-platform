package org.geosdi.geoplatform.jaxb.validation.configuration.store.predicate.xsd;

import org.geosdi.geoplatform.jaxb.validation.configuration.ValidationMessage;
import org.geosdi.geoplatform.jaxb.validation.configuration.store.predicate.ValidationMessagePredicate;

import java.util.function.Predicate;

import static org.geosdi.geoplatform.jaxb.validation.configuration.ISeverityType.SeverityType.ERROR;
import static org.geosdi.geoplatform.jaxb.validation.configuration.ISeverityType.SeverityType.FATAL_ERROR;
import static org.geosdi.geoplatform.jaxb.validation.configuration.ValidationMessage.ValidationType.XSD;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class XSDErrorPredicate implements ValidationMessagePredicate {

    protected XSDErrorPredicate() {
    }

    /**
     * @param validationMessage
     * @return {@link Boolean}
     */
    @Override
    public boolean test(ValidationMessage validationMessage) {
        return ((validationMessage.getValidationType() == XSD) && ((validationMessage.getSeverityMessage().getType() == FATAL_ERROR)
                || (validationMessage.getSeverityMessage().getType() == ERROR)));
    }

    /**
     * @return {@link java.util.function.Predicate<ValidationMessage>}
     */
    public static Predicate<ValidationMessage> toXSDErrorPredicate() {
        return new XSDErrorPredicate();
    }
}