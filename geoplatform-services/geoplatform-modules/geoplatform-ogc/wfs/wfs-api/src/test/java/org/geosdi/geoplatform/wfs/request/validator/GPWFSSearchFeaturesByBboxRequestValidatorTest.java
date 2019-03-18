package org.geosdi.geoplatform.wfs.request.validator;

import org.geosdi.geoplatform.gui.shared.bean.BBox;
import org.geosdi.geoplatform.services.request.GPWFSSearchFeaturesByBboxRequest;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.util.Locale;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@FixMethodOrder(value = MethodSorters.NAME_ASCENDING)
public class GPWFSSearchFeaturesByBboxRequestValidatorTest extends GPWFSRequestValidatorTest {

    @Test
    public void a_wfsServerURLIsNullTest() throws Exception {
        GPWFSSearchFeaturesByBboxRequest request = createWFSSearchFeaturesByBboxRequest();
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
        GPWFSSearchFeaturesByBboxRequest request = createWFSSearchFeaturesByBboxRequest();
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
        GPWFSSearchFeaturesByBboxRequest request = createWFSSearchFeaturesByBboxRequest();
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
        GPWFSSearchFeaturesByBboxRequest request = createWFSSearchFeaturesByBboxRequest();
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
        GPWFSSearchFeaturesByBboxRequest request = createWFSSearchFeaturesByBboxRequest();
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
        GPWFSSearchFeaturesByBboxRequest request = createWFSSearchFeaturesByBboxRequest();
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
        GPWFSSearchFeaturesByBboxRequest request = createWFSSearchFeaturesByBboxRequest();
        request.setTypeName("states");

        String itMessage = wfsRequestValidator.validate(request, Locale.ITALIAN);
        Assert.assertNotNull(itMessage);
        logger.info("#########################WFS_TYPE_NAME_IT_IS_ALLOWED_MESSAGE : {}\n", itMessage);

        String enMessage = wfsRequestValidator.validate(request, Locale.ENGLISH);
        Assert.assertNotNull(enMessage);
        logger.info("#########################WFS_TYPE_NAME_EN_IS_ALLOWED_MESSAGE : {}\n", enMessage);
    }

    @Test
    public void h_wfsBboxIsNullTest() throws Exception {
        GPWFSSearchFeaturesByBboxRequest request = createWFSSearchFeaturesByBboxRequest();
        request.setBBox(null);

        String itMessage = wfsRequestValidator.validate(request, Locale.ITALIAN);
        Assert.assertNotNull(itMessage);
        logger.info("#########################WFS_BBOX_IT_IS_NULL_MESSAGE : {}\n", itMessage);

        String enMessage = wfsRequestValidator.validate(request, Locale.ENGLISH);
        Assert.assertNotNull(enMessage);
        logger.info("#########################WFS_BBOX_EN_IS_NULL_MESSAGE : {}\n", enMessage);
    }

    /**
     * @return {@link GPWFSSearchFeaturesByBboxRequest}
     */
    public static GPWFSSearchFeaturesByBboxRequest createWFSSearchFeaturesByBboxRequest() {
        return new GPWFSSearchFeaturesByBboxRequest() {

            {
                super.setServerURL("http://150.145.141.92/geoserver/wfs");
                super.setTypeName("topp:states");
                super.setMaxFeatures(50);
                super.setBBox(new BBox(-75.102613, 40.212597, -72.361859, 41.512517));
            }
        };
    }
}