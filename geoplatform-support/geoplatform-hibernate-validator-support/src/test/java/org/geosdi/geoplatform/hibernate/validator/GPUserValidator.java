package org.geosdi.geoplatform.hibernate.validator;

import org.geosdi.geoplatform.hibernate.validator.model.GPUser;
import org.geosdi.geoplatform.hibernate.validator.support.GPI18NValidator;
import org.geosdi.geoplatform.hibernate.validator.support.interpoletor.GPI18NMessageInterpoletor;
import org.hibernate.validator.resourceloading.PlatformResourceBundleLocator;

import javax.validation.ConstraintViolation;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class GPUserValidator extends GPI18NValidator<GPUser, String> {

    public GPUserValidator() {
        super(new GPI18NMessageInterpoletor(new PlatformResourceBundleLocator("GPBaseMessages")));
    }

    /**
     * @return {@link String}
     */
    @Override
    public String getValidatorName() {
        return "GP_USER_VALIDATOR_TEST";
    }

    /**
     * @param constraintViolations
     * @return {@link String}
     */
    @Override
    protected String buildMessage(Set<ConstraintViolation<GPUser>> constraintViolations) {
        return constraintViolations.stream()
                .map(ConstraintViolation::getMessage)
                .filter(message -> ((message != null) && !(message.isEmpty())))
                .collect(Collectors.joining("\n"));
    }
}
