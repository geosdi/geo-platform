package org.geosdi.geoplatform.wms.i18N.validator;

import org.geosdi.geoplatform.hibernate.validator.support.GPI18NValidator;
import org.geosdi.geoplatform.hibernate.validator.support.request.GPI18NRequestValidator;
import org.geosdi.geoplatform.services.request.GPWMSGetFeatureInfoRequest;
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

import static java.util.stream.Collectors.toSet;
import static java.util.stream.Stream.of;
import static org.geosdi.geoplatform.wms.request.validator.GPWMSRequestValidatorTest.createWMSGetFeatureRequest;
import static org.junit.runners.MethodSorters.NAME_ASCENDING;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@RunWith(value = SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext-Request-Validator-Test.xml"})
@FixMethodOrder(value = NAME_ASCENDING)
public class GPWMSRequestValidatorConfigTest {

    private static final Logger logger = LoggerFactory.getLogger(GPWMSRequestValidatorConfigTest.class);
    //
    @Resource(name = "wmsRequestValidator")
    private GPI18NValidator<GPI18NRequestValidator, String> wmsRequestValidator;

    @Before
    public void setUp() throws Exception {
        Assert.assertNotNull("GPWMSRequestValidator must not be null.", this.wmsRequestValidator);
    }

    @Test
    public void a_wmsGetFeatureInfoElementsIsNullTest() throws Exception {
        GPWMSGetFeatureInfoRequest request = createWMSGetFeatureRequest();
        request.setWmsFeatureInfoElements(null);

        String itMessage = wmsRequestValidator.validate(request, Locale.ITALIAN);
        Assert.assertNotNull(itMessage);
        logger.info("#########################WMS_GET_FEATURE_INFO_ELEMENTS_IT_IS_NULL_MESSAGE : {}\n", itMessage);

        String enMessage = wmsRequestValidator.validate(request, Locale.ENGLISH);
        Assert.assertNotNull(enMessage);
        logger.info("#########################WMS_GET_FEATURE_INFO_ELEMENTS_EN_IS_NULL_MESSAGE : {}\n", enMessage);
    }

    @Test
    public void b_wmsGetFeatureInfoElementCrsIsNullTest() throws Exception {
        GPWMSGetFeatureInfoRequest request = createWMSGetFeatureRequest();
        request.setCrs(null);
        String itMessage = wmsRequestValidator.validate(request, Locale.ITALIAN);
        Assert.assertNotNull(itMessage);
        logger.info("#########################WMS_GET_FEATURE_INFO_ELEMENT_CRS_IT_IS_NULL_MESSAGE : {}\n", itMessage);

        String enMessage = wmsRequestValidator.validate(request, Locale.ENGLISH);
        Assert.assertNotNull(enMessage);
        logger.info("#########################WMS_GET_FEATURE_INFO_ELEMENT_CRS_EN_IS_NULL_MESSAGE : {}\n", enMessage);
    }

    @Test
    public void c_wmsGetFeatureInfoElementCrsIsEmptyTest() throws Exception {
        GPWMSGetFeatureInfoRequest request = createWMSGetFeatureRequest();
        request.setCrs("");
        String itMessage = wmsRequestValidator.validate(request, Locale.ITALIAN);
        Assert.assertNotNull(itMessage);
        logger.info("#########################WMS_GET_FEATURE_INFO_ELEMENT_CRS_IT_IS_EMPTY_MESSAGE : {}\n", itMessage);

        String enMessage = wmsRequestValidator.validate(request, Locale.ENGLISH);
        Assert.assertNotNull(enMessage);
        logger.info("#########################WMS_GET_FEATURE_INFO_ELEMENT_CRS_EN_IS_EMPTY_MESSAGE : {}\n", enMessage);
    }

    @Test
    public void d_wmsGetFeatureInfoElementBboxIsNullTest() {
        GPWMSGetFeatureInfoRequest request = createWMSGetFeatureRequest();
        request.setBoundingBox(null);
        String itMessage = wmsRequestValidator.validate(request, Locale.ITALIAN);
        Assert.assertNotNull(itMessage);
        logger.info("#########################WMS_GET_FEATURE_INFO_ELEMENT_BBOX_IT_IS_NULL_MESSAGE : {}\n", itMessage);

        String enMessage = wmsRequestValidator.validate(request, Locale.ENGLISH);
        Assert.assertNotNull(enMessage);
        logger.info("#########################WMS_GET_FEATURE_INFO_ELEMENT_BBOX_EN_IS_NULL_MESSAGE : {}\n", enMessage);
    }

    @Test
    public void e_wmsGetFeatureInfoElementBboxMinxIsNullTest() {
        GPWMSGetFeatureInfoRequest request = createWMSGetFeatureRequest();
        request.getBoundingBox().setMinx(null);
        String itMessage = wmsRequestValidator.validate(request, Locale.ITALIAN);
        Assert.assertNotNull(itMessage);
        logger.info("#########################WMS_GET_FEATURE_INFO_ELEMENT_BBOX_MINX_IT_IS_NULL_MESSAGE : {}\n", itMessage);

        String enMessage = wmsRequestValidator.validate(request, Locale.ENGLISH);
        Assert.assertNotNull(enMessage);
        logger.info("#########################WMS_GET_FEATURE_INFO_ELEMENT_BBOX_MINX_EN_IS_NULL_MESSAGE : {}\n", enMessage);
    }

    @Test
    public void f_wmsGetFeatureInfoElementBboxMinyIsNullTest() {
        GPWMSGetFeatureInfoRequest request = createWMSGetFeatureRequest();
        request.getBoundingBox().setMiny(null);
        String itMessage = wmsRequestValidator.validate(request, Locale.ITALIAN);
        Assert.assertNotNull(itMessage);
        logger.info("#########################WMS_GET_FEATURE_INFO_ELEMENT_BBOX_MINY_IT_IS_NULL_MESSAGE : {}\n", itMessage);

        String enMessage = wmsRequestValidator.validate(request, Locale.ENGLISH);
        Assert.assertNotNull(enMessage);
        logger.info("#########################WMS_GET_FEATURE_INFO_ELEMENT_BBOX_MINY_EN_IS_NULL_MESSAGE : {}\n", enMessage);
    }

    @Test
    public void g_wmsGetFeatureInfoElementBboxMaxxIsNullTest() {
        GPWMSGetFeatureInfoRequest request = createWMSGetFeatureRequest();
        request.getBoundingBox().setMaxx(null);
        String itMessage = wmsRequestValidator.validate(request, Locale.ITALIAN);
        Assert.assertNotNull(itMessage);
        logger.info("#########################WMS_GET_FEATURE_INFO_ELEMENT_BBOX_MAXX_IT_IS_NULL_MESSAGE : {}\n", itMessage);

        String enMessage = wmsRequestValidator.validate(request, Locale.ENGLISH);
        Assert.assertNotNull(enMessage);
        logger.info("#########################WMS_GET_FEATURE_INFO_ELEMENT_BBOX_MAXX_EN_IS_NULL_MESSAGE : {}\n", enMessage);
    }

    @Test
    public void h_wmsGetFeatureInfoElementBboxMaxyIsNullTest() {
        GPWMSGetFeatureInfoRequest request = createWMSGetFeatureRequest();
        request.getBoundingBox().setMaxy(null);
        String itMessage = wmsRequestValidator.validate(request, Locale.ITALIAN);
        Assert.assertNotNull(itMessage);
        logger.info("#########################WMS_GET_FEATURE_INFO_ELEMENT_BBOX_MAXY_IT_IS_NULL_MESSAGE : {}\n", itMessage);

        String enMessage = wmsRequestValidator.validate(request, Locale.ENGLISH);
        Assert.assertNotNull(enMessage);
        logger.info("#########################WMS_GET_FEATURE_INFO_ELEMENT_BBOX_MAXY_EN_IS_NULL_MESSAGE : {}\n", enMessage);
    }

    @Test
    public void i_wmsGetFeatureInfoElementWidthIsNullTest() throws Exception {
        GPWMSGetFeatureInfoRequest request = createWMSGetFeatureRequest();
        request.setWidth(null);
        String itMessage = wmsRequestValidator.validate(request, Locale.ITALIAN);
        Assert.assertNotNull(itMessage);
        logger.info("#########################WMS_GET_FEATURE_INFO_ELEMENT_WIDTH_IT_IS_NULL_MESSAGE : {}\n", itMessage);

        String enMessage = wmsRequestValidator.validate(request, Locale.ENGLISH);
        Assert.assertNotNull(enMessage);
        logger.info("#########################WMS_GET_FEATURE_INFO_ELEMENT_WIDTH_EN_IS_NULL_MESSAGE : {}\n", enMessage);
    }

    @Test
    public void l_wmsGetFeatureInfoElementWidthIsEmptyTest() throws Exception {
        GPWMSGetFeatureInfoRequest request = createWMSGetFeatureRequest();
        request.setWidth("    ");
        String itMessage = wmsRequestValidator.validate(request, Locale.ITALIAN);
        Assert.assertNotNull(itMessage);
        logger.info("#########################WMS_GET_FEATURE_INFO_ELEMENT_WIDTH_IT_IS_EMPTY_MESSAGE : {}\n", itMessage);

        String enMessage = wmsRequestValidator.validate(request, Locale.ENGLISH);
        Assert.assertNotNull(enMessage);
        logger.info("#########################WMS_GET_FEATURE_INFO_ELEMENT_WIDTH_EN_IS_EMPTY_MESSAGE : {}\n", enMessage);
    }

    @Test
    public void m_wmsGetFeatureInfoElementHeightIsNullTest() throws Exception {
        GPWMSGetFeatureInfoRequest request = createWMSGetFeatureRequest();
        request.setHeight(null);
        String itMessage = wmsRequestValidator.validate(request, Locale.ITALIAN);
        Assert.assertNotNull(itMessage);
        logger.info("#########################WMS_GET_FEATURE_INFO_ELEMENT_HEIGHT_IT_IS_NULL_MESSAGE : {}\n", itMessage);

        String enMessage = wmsRequestValidator.validate(request, Locale.ENGLISH);
        Assert.assertNotNull(enMessage);
        logger.info("#########################WMS_GET_FEATURE_INFO_ELEMENT_HEIGHT_EN_IS_NULL_MESSAGE : {}\n", enMessage);
    }

    @Test
    public void n_wmsGetFeatureInfoElementHeightIsEmptyTest() throws Exception {
        GPWMSGetFeatureInfoRequest request = createWMSGetFeatureRequest();
        request.setHeight("    ");
        String itMessage = wmsRequestValidator.validate(request, Locale.ITALIAN);
        Assert.assertNotNull(itMessage);
        logger.info("#########################WMS_GET_FEATURE_INFO_ELEMENT_HEIGHT_IT_IS_EMPTY_MESSAGE : {}\n", itMessage);

        String enMessage = wmsRequestValidator.validate(request, Locale.ENGLISH);
        Assert.assertNotNull(enMessage);
        logger.info("#########################WMS_GET_FEATURE_INFO_ELEMENT_HEIGHT_EN_IS_EMPTY_MESSAGE : {}\n", enMessage);
    }

    @Test
    public void o_wmsGetFeatureInfoElementLayersIsNullTest() throws Exception {
        GPWMSGetFeatureInfoRequest request = createWMSGetFeatureRequest();
        request.getWmsFeatureInfoElements().get(0).setLayers(null);
        request.getWmsFeatureInfoElements().get(1).setLayers(null);
        String itMessage = wmsRequestValidator.validate(request, Locale.ITALIAN);
        Assert.assertNotNull(itMessage);
        logger.info("#########################WMS_GET_FEATURE_INFO_ELEMENT_LAYERS_IT_IS_NULL_MESSAGE : {}\n", itMessage);

        String enMessage = wmsRequestValidator.validate(request, Locale.ENGLISH);
        Assert.assertNotNull(enMessage);
        logger.info("#########################WMS_GET_FEATURE_INFO_ELEMENT_LAYERS_EN_IS_NULL_MESSAGE : {}\n", enMessage);
    }

    @Test
    public void p_wmsGetFeatureInfoElementOneLayerIsNullTest() throws Exception {
        GPWMSGetFeatureInfoRequest request = createWMSGetFeatureRequest();
        request.getWmsFeatureInfoElements().get(0).setLayers(of("", null, "retenatura:zsc").collect(toSet()));
        String itMessage = wmsRequestValidator.validate(request, Locale.ITALIAN);
        Assert.assertNotNull(itMessage);
        logger.info("#########################WMS_GET_FEATURE_INFO_ELEMENT_LAYERS_IT_IS_NULL_MESSAGE : {}\n", itMessage);

        String enMessage = wmsRequestValidator.validate(request, Locale.ENGLISH);
        Assert.assertNotNull(enMessage);
        logger.info("#########################WMS_GET_FEATURE_INFO_ELEMENT_LAYERS_EN_IS_NULL_MESSAGE : {}\n", enMessage);
    }

    @Test
    public void q_wmsGetFeatureInfoElementServerURLIsNullTest() throws Exception {
        GPWMSGetFeatureInfoRequest request = createWMSGetFeatureRequest();
        request.getWmsFeatureInfoElements().get(0).setWmsServerURL(null);
        request.getWmsFeatureInfoElements().get(1).setWmsServerURL(null);
        String itMessage = wmsRequestValidator.validate(request, Locale.ITALIAN);
        Assert.assertNotNull(itMessage);
        logger.info("#########################WMS_GET_FEATURE_INFO_ELEMENT_SERVER_URL_IT_IS_NULL_MESSAGE : {}\n", itMessage);

        String enMessage = wmsRequestValidator.validate(request, Locale.ENGLISH);
        Assert.assertNotNull(enMessage);
        logger.info("#########################WMS_GET_FEATURE_INFO_ELEMENT_SERVER_URL_EN_IS_NULL_MESSAGE : {}\n", enMessage);
    }

    @Test
    public void r_wmsGetFeatureInfoElementServerURLIsEmptyTest() throws Exception {
        GPWMSGetFeatureInfoRequest request = createWMSGetFeatureRequest();
        request.getWmsFeatureInfoElements().get(0).setWmsServerURL("");
        String itMessage = wmsRequestValidator.validate(request, Locale.ITALIAN);
        Assert.assertNotNull(itMessage);
        logger.info("#########################WMS_GET_FEATURE_INFO_ELEMENT_SERVER_URL_IT_IS_EMPTY_MESSAGE : {}\n", itMessage);

        String enMessage = wmsRequestValidator.validate(request, Locale.ENGLISH);
        Assert.assertNotNull(enMessage);
        logger.info("#########################WMS_GET_FEATURE_INFO_ELEMENT_SERVER_URL_EN_IS_EMPTY_MESSAGE : {}\n", enMessage);
    }

    @Test
    public void s_wmsGetFeatureInfoElementPointIsNullTest() throws Exception {
        GPWMSGetFeatureInfoRequest request = createWMSGetFeatureRequest();
        request.setPoint(null);
        String itMessage = wmsRequestValidator.validate(request, Locale.ITALIAN);
        Assert.assertNotNull(itMessage);
        logger.info("#########################WMS_GET_FEATURE_INFO_ELEMENT_POINT_IT_IS_EMPTY_MESSAGE : {}\n", itMessage);

        String enMessage = wmsRequestValidator.validate(request, Locale.ENGLISH);
        Assert.assertNotNull(enMessage);
        logger.info("#########################WMS_GET_FEATURE_INFO_ELEMENT_POINT_EN_IS_EMPTY_MESSAGE : {}\n", enMessage);
    }

    @Test
    public void t_wmsGetFeatureInfoElementPointXIsNullTest() throws Exception {
        GPWMSGetFeatureInfoRequest request = createWMSGetFeatureRequest();
        request.getPoint().setX(null);
        String itMessage = wmsRequestValidator.validate(request, Locale.ITALIAN);
        Assert.assertNotNull(itMessage);
        logger.info("#########################WMS_GET_FEATURE_INFO_ELEMENT_POINT_X_IT_IS_EMPTY_MESSAGE : {}\n", itMessage);

        String enMessage = wmsRequestValidator.validate(request, Locale.ENGLISH);
        Assert.assertNotNull(enMessage);
        logger.info("#########################WMS_GET_FEATURE_INFO_ELEMENT_POINT_X_EN_IS_EMPTY_MESSAGE : {}\n", enMessage);
    }

    @Test
    public void u_wmsGetFeatureInfoElementPointYIsNullTest() throws Exception {
        GPWMSGetFeatureInfoRequest request = createWMSGetFeatureRequest();
        request.getPoint().setY(null);
        String itMessage = wmsRequestValidator.validate(request, Locale.ITALIAN);
        Assert.assertNotNull(itMessage);
        logger.info("#########################WMS_GET_FEATURE_INFO_ELEMENT_POINT_Y_IT_IS_EMPTY_MESSAGE : {}\n", itMessage);

        String enMessage = wmsRequestValidator.validate(request, Locale.ENGLISH);
        Assert.assertNotNull(enMessage);
        logger.info("#########################WMS_GET_FEATURE_INFO_ELEMENT_POINT_Y_EN_IS_EMPTY_MESSAGE : {}\n", enMessage);
    }

    @Test
    public void v_wmsGetFeatureInfoElementPointBothValuesAreNullTest() throws Exception {
        GPWMSGetFeatureInfoRequest request = createWMSGetFeatureRequest();
        request.getPoint().setX(null);
        String itMessage = wmsRequestValidator.validate(request, Locale.ITALIAN);
        Assert.assertNotNull(itMessage);
        logger.info("#########################WMS_GET_FEATURE_INFO_ELEMENT_POINT_BOTH_VALUES_IT_ARE_EMPTY_MESSAGE : {}\n", itMessage);

        String enMessage = wmsRequestValidator.validate(request, Locale.ENGLISH);
        Assert.assertNotNull(enMessage);
        logger.info("#########################WMS_GET_FEATURE_INFO_ELEMENT_POINT_BOTH_VALUES_EN_ARE_EMPTY_MESSAGE : {}\n", enMessage);
    }
}