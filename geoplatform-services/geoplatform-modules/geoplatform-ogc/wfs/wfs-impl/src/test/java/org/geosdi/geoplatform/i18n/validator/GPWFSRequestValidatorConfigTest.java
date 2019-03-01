package org.geosdi.geoplatform.i18n.validator;

import org.geosdi.geoplatform.hibernate.validator.support.GPI18NValidator;
import org.geosdi.geoplatform.hibernate.validator.support.request.GPI18NRequestValidator;
import org.geosdi.geoplatform.services.request.GPWFSSearchFeaturesRequest;
import org.junit.Assert;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.Locale;

import static org.geosdi.geoplatform.wfs.request.validator.GPWFSRequestValidatorTest.createWFSGetFeatureRequest;
import static org.junit.runners.MethodSorters.NAME_ASCENDING;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@RunWith(value = SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext-Request-Validator-Test.xml"})
@FixMethodOrder(value = NAME_ASCENDING)
public class GPWFSRequestValidatorConfigTest {

    private static final Logger logger = LoggerFactory.getLogger(GPWFSRequestValidatorConfigTest.class);
    //
    @Resource(name = "wfsRequestValidator")
    private GPI18NValidator<GPI18NRequestValidator, String> wfsRequestValidator;

    @Before
    public void setUp() throws Exception {
        Assert.assertNotNull("GPWFSRequestValidator must not be null.", this.wfsRequestValidator);
    }

    @Test
    public void a_wfsServerURLIsNullTest() throws Exception {
        GPWFSSearchFeaturesRequest request = createWFSGetFeatureRequest();
        request.setServerURL(null);

        String itMessage = wfsRequestValidator.validate(request, Locale.ITALIAN);
        Assert.assertNotNull(itMessage);
        logger.info("#########################WFS_SERVER_URL_IT_IS_NULL_MESSAGE : {}\n", itMessage);

        String enMessage = wfsRequestValidator.validate(request, Locale.ENGLISH);
        Assert.assertNotNull(enMessage);
        logger.info("#########################WFS_SERVER_URL_EN_IS_NULL_MESSAGE : {}\n", enMessage);
    }

    @Test
    public void b_wfsServerURLIsEmptyTest() throws Exception {
        GPWFSSearchFeaturesRequest request = createWFSGetFeatureRequest();
        request.setServerURL("");

        String itMessage = wfsRequestValidator.validate(request, Locale.ITALIAN);
        Assert.assertNotNull(itMessage);
        logger.info("#########################WFS_SERVER_URL_IT_IS_EMPTY_MESSAGE : {}\n", itMessage);

        String enMessage = wfsRequestValidator.validate(request, Locale.ENGLISH);
        Assert.assertNotNull(enMessage);
        logger.info("#########################WFS_SERVER_URL_EN_IS_EMPTY_MESSAGE : {}\n", enMessage);
    }

    @Test
    public void c_wfsTypeNameIsNullTest() throws Exception {
        GPWFSSearchFeaturesRequest request = createWFSGetFeatureRequest();
        request.setTypeName(null);

        String itMessage = wfsRequestValidator.validate(request, Locale.ITALIAN);
        Assert.assertNotNull(itMessage);
        logger.info("#########################WFS_TYPE_NAME_IT_IS_NULL_MESSAGE : {}\n", itMessage);

        String enMessage = wfsRequestValidator.validate(request, Locale.ENGLISH);
        Assert.assertNotNull(enMessage);
        logger.info("#########################WFS_TYPE_NAME_EN_IS_NULL_MESSAGE : {}\n", enMessage);
    }

    @Test
    public void d_wfsTypeNameIsEmptyTest() throws Exception {
        GPWFSSearchFeaturesRequest request = createWFSGetFeatureRequest();
        request.setTypeName("");

        String itMessage = wfsRequestValidator.validate(request, Locale.ITALIAN);
        Assert.assertNotNull(itMessage);
        logger.info("#########################WFS_TYPE_NAME_IT_IS_EMPTY_MESSAGE : {}\n", itMessage);

        String enMessage = wfsRequestValidator.validate(request, Locale.ENGLISH);
        Assert.assertNotNull(enMessage);
        logger.info("#########################WFS_TYPE_NAME_EN_IS_EMPTY_MESSAGE : {}\n", enMessage);
    }

    @Test
    public void e_wfsMaxFeaturesIsNullTest() throws Exception {
        GPWFSSearchFeaturesRequest request = createWFSGetFeatureRequest();
        request.setMaxFeatures(null);

        String itMessage = wfsRequestValidator.validate(request, Locale.ITALIAN);
        Assert.assertNotNull(itMessage);
        logger.info("#########################WFS_MAX_FEATURES_IT_IS_NULL_MESSAGE : {}\n", itMessage);

        String enMessage = wfsRequestValidator.validate(request, Locale.ENGLISH);
        Assert.assertNotNull(enMessage);
        logger.info("#########################WFS_MAX_FEATURES_EN_IS_NULL_MESSAGE : {}\n", enMessage);
    }

    @Test
    public void f_wfsTypeNameIsNotPositiveTest() throws Exception {
        GPWFSSearchFeaturesRequest request = createWFSGetFeatureRequest();
        request.setMaxFeatures(0);

        String itMessage = wfsRequestValidator.validate(request, Locale.ITALIAN);
        Assert.assertNotNull(itMessage);
        logger.info("#########################WFS_MAX_FEATURES_IT_IS_NOT_POSITIVE_MESSAGE : {}\n", itMessage);

        String enMessage = wfsRequestValidator.validate(request, Locale.ENGLISH);
        Assert.assertNotNull(enMessage);
        logger.info("#########################WFS_MAX_FEATURES_EN_IS_NOT_POSITIVE_MESSAGE : {}\n", enMessage);
    }

    @Test
    public void g_wfsTypeNameAllowedTest() throws Exception {
        GPWFSSearchFeaturesRequest request = createWFSGetFeatureRequest();
        request.setTypeName("states");

        String itMessage = wfsRequestValidator.validate(request, Locale.ITALIAN);
        Assert.assertNotNull(itMessage);
        logger.info("#########################WFS_TYPE_NAME_IT_IS_ALLOWED_MESSAGE : {}\n", itMessage);

        String enMessage = wfsRequestValidator.validate(request, Locale.ENGLISH);
        Assert.assertNotNull(enMessage);
        logger.info("#########################WFS_TYPE_NAME_EN_IS_ALLOWED_MESSAGE : {}\n", enMessage);
    }
}