package org.geosdi.geoplatform.hibernate.validator.support;

import com.google.common.base.Preconditions;
import org.geosdi.geoplatform.hibernate.validator.support.i18n.IGPI18NValidator;
import org.geosdi.geoplatform.hibernate.validator.support.interpoletor.IGPI18NMessageInterpolator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.Configuration;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import java.util.Locale;
import java.util.Set;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public abstract class GPI18NValidator<P extends Object, MESSAGE extends Object>
        implements IGPI18NValidator<P, MESSAGE> {

    protected final Logger logger = LoggerFactory.getLogger(this.getClass());
    //
    private final IGPI18NMessageInterpolator messageInterpolator;
    private final javax.validation.Validator validator;

    /**
     * @param theMessageInterpolator
     */
    public GPI18NValidator(IGPI18NMessageInterpolator theMessageInterpolator) {
        Preconditions.checkArgument(theMessageInterpolator != null, "The Parameter MessageInterpoletor must " +
                "not be null.");
        this.messageInterpolator = theMessageInterpolator;
        Configuration<?> configuration = Validation.byDefaultProvider().configure();
        this.validator = configuration.messageInterpolator(this.messageInterpolator)
                .buildValidatorFactory().getValidator();
    }

    /**
     * <p>Return Null, if there are no errors.</p>
     *
     * @param thePojo
     * @param locale
     * @return {@link MESSAGE}
     */
    @Override
    public MESSAGE validate(P thePojo, Locale locale) {
        logger.trace("################################ {} - Validating........ {} , with Locale : {}\n",
                getValidatorName(), thePojo, locale);
        this.messageInterpolator.setDefaultLocale(locale);
        Set<ConstraintViolation<P>> constraintViolations = this.validator.validate(thePojo);
        return constraintViolations.isEmpty() ? null : buildMessage(constraintViolations);
    }

    /**
     * @param constraintViolations
     * @return {@link MESSAGE}
     */
    protected abstract MESSAGE buildMessage(Set<ConstraintViolation<P>> constraintViolations);
}
