package org.geosdi.geoplatform.jaxb.validation.xsd.builder;

import org.geosdi.geoplatform.jaxb.validation.configuration.GPSeverityMessage;
import org.geosdi.geoplatform.jaxb.validation.configuration.ValidationMessage;
import org.geosdi.geoplatform.jaxb.validation.xsd.message.XSDValidationMessage;

import javax.xml.bind.ValidationEvent;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public interface ValidationMessageBuilder {

    /**
     * @param theValidationEvent
     * @return {@link ValidationMessageBuilder}
     */
    ValidationMessageBuilder withValidationEvent(ValidationEvent theValidationEvent);

    /**
     * @return {@link org.geosdi.geoplatform.jaxb.validation.configuration.ValidationMessage}
     * @throws Exception
     */
    ValidationMessage build() throws Exception;

    /**
     *
     */
    class GPValidationMessageBuilder implements ValidationMessageBuilder {

        private ValidationEvent validationEvent;

        private GPValidationMessageBuilder() {
        }

        public static ValidationMessageBuilder validationMessageBuilder() {
            return new GPValidationMessageBuilder();
        }

        /**
         * @param theValidationEvent
         * @return {@link ValidationMessageBuilder}
         */
        @Override
        public ValidationMessageBuilder withValidationEvent(ValidationEvent theValidationEvent) {
            this.validationEvent = theValidationEvent;
            return this;
        }

        /**
         * @return {@link ValidationMessage}
         * @throws Exception
         */
        @Override
        public ValidationMessage build() throws Exception {
            checkNotNull(this.validationEvent, "Validation Event must not be null");
            return new XSDValidationMessage(validationEvent.getMessage(),
                    GPSeverityMessage.fromValue(validationEvent.getSeverity()),
                    validationEvent.getLocator().getColumnNumber(), validationEvent.getLocator().getLineNumber());
        }
    }
}