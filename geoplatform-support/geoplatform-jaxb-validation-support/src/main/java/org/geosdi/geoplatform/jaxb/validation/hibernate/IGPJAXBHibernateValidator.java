package org.geosdi.geoplatform.jaxb.validation.hibernate;

import org.geosdi.geoplatform.hibernate.validator.support.base.IGPBaseValidator;
import org.geosdi.geoplatform.jaxb.validation.configuration.GPSeverityMessage;
import org.geosdi.geoplatform.jaxb.validation.configuration.GPValidationMessage;
import org.geosdi.geoplatform.jaxb.validation.configuration.ISeverityType;
import org.geosdi.geoplatform.jaxb.validation.configuration.ValidationMessage;
import org.geosdi.geoplatform.jaxb.validation.configuration.store.ValidationMessageStore;

import javax.validation.ConstraintViolation;
import java.io.Serializable;
import java.util.function.Function;

import static org.geosdi.geoplatform.jaxb.validation.configuration.GPSeverityMessage.WARNING_MESSAGE;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public interface IGPJAXBHibernateValidator<P extends IGPJAXBHibernateValidator.GPJAXBValidationModel> extends IGPBaseValidator<P, ValidationMessageStore> {

    Function<ConstraintViolation<GPJAXBValidationModel>, ValidationMessage> VALIDATION_MESSAGE_FUNCTION = new ValidationMessageFunction<>();

    /**
     * @param <P>
     */
    class ValidationMessageFunction<P extends GPJAXBValidationModel> implements Function<ConstraintViolation<P>, ValidationMessage> {

        /**
         * Applies this function to the given argument.
         *
         * @param theConstraintViolation the function argument
         * @return the function result
         */
        @Override
        public ValidationMessage apply(ConstraintViolation<P> theConstraintViolation) {
            GPSeverityMessage severityMessage = GPSeverityMessage.fromValue((ISeverityType.SeverityType) theConstraintViolation
                    .getConstraintDescriptor().getAttributes().get("severityType"));
            return new GPValidationMessage(theConstraintViolation.getMessage(), (severityMessage != null) ? severityMessage : WARNING_MESSAGE);
        }
    }

    interface GPJAXBValidationModel extends Serializable {
    }
}