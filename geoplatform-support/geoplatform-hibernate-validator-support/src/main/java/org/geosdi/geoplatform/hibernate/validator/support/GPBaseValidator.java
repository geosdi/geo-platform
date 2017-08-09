package org.geosdi.geoplatform.hibernate.validator.support;

import org.geosdi.geoplatform.hibernate.validator.support.base.IGPBaseValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public abstract class GPBaseValidator<P extends Object, MESSAGE extends Object>
        implements IGPBaseValidator<P, MESSAGE> {

    protected final Logger logger = LoggerFactory.getLogger(this.getClass());
    //
    private final Validator validator;

    public GPBaseValidator() {
        this.validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    /**
     * @param thePojo
     * @return {@link MESSAGE}
     */
    @Override
    public MESSAGE validate(P thePojo) {
        logger.trace("################################ {} - Validating........ {} \n", getValidatorName(), thePojo);
        Set<ConstraintViolation<P>> constraintViolations = this.validator.validate(thePojo);
        return constraintViolations.isEmpty() ? null : buildMessage(constraintViolations);
    }

    /**
     * @param thePojo
     * @param groups
     * @return {@link MESSAGE}
     */
    @Override
    public MESSAGE validate(P thePojo, Class<?>... groups) {
        logger.trace("################################ {} - Validating........ {}, with Groups : {} \n",
                getValidatorName(), thePojo, groups);
        Set<ConstraintViolation<P>> constraintViolations = this.validator.validate(thePojo, groups);
        return constraintViolations.isEmpty() ? null : buildMessage(constraintViolations);
    }


    /**
     * @param constraintViolations
     * @return {@link MESSAGE}
     */
    protected abstract MESSAGE buildMessage(Set<ConstraintViolation<P>> constraintViolations);
}
