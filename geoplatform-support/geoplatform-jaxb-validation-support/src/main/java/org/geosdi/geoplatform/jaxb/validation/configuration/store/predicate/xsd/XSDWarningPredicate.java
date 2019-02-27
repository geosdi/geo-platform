package org.geosdi.geoplatform.jaxb.validation.configuration.store.predicate.xsd;

import org.geosdi.geoplatform.jaxb.validation.configuration.ValidationMessage;
import org.geosdi.geoplatform.jaxb.validation.configuration.store.predicate.ValidationMessagePredicate;

import java.util.function.Predicate;

import static org.geosdi.geoplatform.jaxb.validation.configuration.ISeverityType.SeverityType.WARNING;
import static org.geosdi.geoplatform.jaxb.validation.configuration.ValidationMessage.ValidationType.XSD;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class XSDWarningPredicate implements ValidationMessagePredicate {

    protected XSDWarningPredicate() {
    }

    @Override
    public boolean test(ValidationMessage validationMessage) {
        return ((validationMessage.getValidationType() == XSD)
                && (validationMessage.getSeverityMessage().getType() == WARNING));
    }

    /**
     * @return {@link java.util.function.Predicate<ValidationMessage>}
     */
    public static Predicate<ValidationMessage> toXSDWarningPredicate() {
        return new XSDWarningPredicate();
    }
}
