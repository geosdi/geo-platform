package org.geosdi.geoplatform.services.request.validator;

import org.geosdi.geoplatform.hibernate.validator.support.GPI18NValidator;
import org.geosdi.geoplatform.hibernate.validator.support.interpoletor.IGPI18NMessageInterpolator;
import org.geosdi.geoplatform.hibernate.validator.support.request.GPI18NRequestValidator;

import javax.validation.ConstraintViolation;
import java.util.Set;

import static java.util.stream.Collectors.joining;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class GPWFSRequestfValidator extends GPI18NValidator<GPI18NRequestValidator, String> {

    /**
     * @param theMessageInterpolator
     */
    public GPWFSRequestfValidator(IGPI18NMessageInterpolator theMessageInterpolator) {
        super(theMessageInterpolator);
    }

    /**
     * @param constraintViolations
     * @return {@link String}
     */
    @Override
    protected String buildMessage(Set<ConstraintViolation<GPI18NRequestValidator>> constraintViolations) {
        return constraintViolations.stream()
                .map(ConstraintViolation::getMessage)
                .filter(message -> ((message != null) && !(message.isEmpty())))
                .distinct()
                .collect(joining("\n"));
    }

    /**
     * @return {@link String}
     */
    @Override
    public String getValidatorName() {
        return "GP_WFS_REQUEST_VALIDATOR";
    }
}