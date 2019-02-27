package org.geosdi.geoplatform.jaxb.validation;

import org.geosdi.geoplatform.jaxb.validation.configuration.store.ValidationMessageStore;
import org.geosdi.geoplatform.jaxb.validation.hibernate.GPJAXBHibernateValidator;
import org.geosdi.geoplatform.jaxb.validation.hibernate.IGPJAXBHibernateValidator;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class GPJAXBHibernateValidatorTest {

    private static final Logger logger = LoggerFactory.getLogger(GPJAXBHibernateValidatorTest.class);
    //
    private static final IGPJAXBHibernateValidator<IGPJAXBHibernateValidator.GPJAXBValidationModel> jaxbHibernateValidator = new GPJAXBHibernateValidator();

    @Test
    public void validateTest() throws Exception {
        GPGenericObject genericObject = new GPGenericObject();
        ValidationMessageStore validationMessageStore = jaxbHibernateValidator.validate(genericObject);
        logger.info("##############################VALIDATION_MESSAGE_STORE : {}\n", validationMessageStore);
    }
}
