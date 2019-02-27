package org.geosdi.geoplatform.jaxb.validation.hibernate;

import org.geosdi.geoplatform.hibernate.validator.support.GPBaseValidator;
import org.geosdi.geoplatform.jaxb.validation.configuration.store.GPValidationMessageStore;
import org.geosdi.geoplatform.jaxb.validation.configuration.store.ValidationMessageStore;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Set;

import static com.google.common.base.Preconditions.checkNotNull;
import static java.util.stream.Collectors.toList;
import static javax.validation.Validation.buildDefaultValidatorFactory;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class GPJAXBHibernateValidator extends GPBaseValidator<IGPJAXBHibernateValidator.GPJAXBValidationModel, ValidationMessageStore>
        implements IGPJAXBHibernateValidator<IGPJAXBHibernateValidator.GPJAXBValidationModel> {

    private static final Logger logger = LoggerFactory.getLogger(GPJAXBHibernateValidator.class);
    //
    private final Validator validator;

    public GPJAXBHibernateValidator() {
        this.validator = buildDefaultValidatorFactory().getValidator();
    }

    /**
     * @param thePojo
     * @param groups
     * @return {@link ValidationMessageStore}
     */
    @Override
    public ValidationMessageStore validate(GPJAXBValidationModel thePojo, Class<?>... groups) {
        logger.trace("################################ {} - Validating ----------> {}, for Groups : {}\n",
                getValidatorName(), thePojo, groups);
        return this.buildMessage(this.validator.validate(thePojo, groups));
    }

    /**
     * @param constraintViolations
     * @return {@link ValidationMessageStore}
     */
    @Override
    protected ValidationMessageStore buildMessage(Set<ConstraintViolation<GPJAXBValidationModel>> constraintViolations) {
        checkNotNull(constraintViolations, "The Parameter Set<ConstraintViolation<?>> must not be null");
        return new GPValidationMessageStore(constraintViolations.stream()
                .map(VALIDATION_MESSAGE_FUNCTION)
                .filter(validationMessage -> validationMessage != null)
                .collect(toList()));
    }

    /**
     * @return {@link String}
     */
    @Override
    public String getValidatorName() {
        return "GP_JAXB_HIBERNATE_VALIDATOR";
    }
}
