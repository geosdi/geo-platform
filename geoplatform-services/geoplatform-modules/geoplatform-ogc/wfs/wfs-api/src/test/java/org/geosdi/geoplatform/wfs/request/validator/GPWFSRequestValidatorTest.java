package org.geosdi.geoplatform.wfs.request.validator;

import org.geosdi.geoplatform.hibernate.validator.support.GPI18NValidator;
import org.geosdi.geoplatform.hibernate.validator.support.interpoletor.GPI18NMessageInterpoletor;
import org.geosdi.geoplatform.hibernate.validator.support.request.GPI18NRequestValidator;
import org.geosdi.geoplatform.services.request.validator.GPWFSRequestfValidator;
import org.hibernate.validator.resourceloading.PlatformResourceBundleLocator;
import org.junit.BeforeClass;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class GPWFSRequestValidatorTest {

    protected final Logger logger = LoggerFactory.getLogger(this.getClass());
    //
    protected static GPI18NValidator<GPI18NRequestValidator, String> wfsRequestValidator;

    @BeforeClass
    public static void beforeClass() throws Exception {
        wfsRequestValidator = new GPWFSRequestfValidator(new GPI18NMessageInterpoletor(new PlatformResourceBundleLocator("GPWFSMessages")));
    }
}