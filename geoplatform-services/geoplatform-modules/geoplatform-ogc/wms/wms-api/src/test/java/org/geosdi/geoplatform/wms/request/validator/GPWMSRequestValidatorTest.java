package org.geosdi.geoplatform.wms.request.validator;

import org.geosdi.geoplatform.hibernate.validator.support.GPI18NValidator;
import org.geosdi.geoplatform.hibernate.validator.support.interpoletor.GPI18NMessageInterpoletor;
import org.geosdi.geoplatform.hibernate.validator.support.request.GPI18NRequestValidator;
import org.geosdi.geoplatform.services.request.GPWMSGetFeatureInfoElement;
import org.geosdi.geoplatform.services.request.GPWMSGetFeatureInfoRequest;
import org.geosdi.geoplatform.services.request.WMSGetFeatureInfoBoundingBox;
import org.geosdi.geoplatform.services.request.WMSGetFeatureInfoPoint;
import org.geosdi.geoplatform.services.request.validator.GPWMSRequestfValidator;
import org.hibernate.validator.resourceloading.PlatformResourceBundleLocator;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.Locale;

import static java.util.stream.Collectors.toList;
import static java.util.stream.Stream.of;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@FixMethodOrder(value = MethodSorters.NAME_ASCENDING)
public class GPWMSRequestValidatorTest {

    private static final Logger logger = LoggerFactory.getLogger(GPWMSRequestValidatorTest.class);
    //
    private static GPI18NValidator<GPI18NRequestValidator, String> wmsRequestValidator;

    @BeforeClass
    public static void beforeClass() throws Exception {
        wmsRequestValidator = new GPWMSRequestfValidator(new GPI18NMessageInterpoletor(new PlatformResourceBundleLocator("GPWMSMessages")));
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
        request.getWmsFeatureInfoElements().get(0).setLayers(of("", null, "retenatura:zsc").collect(toList()));
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
        logger.info("{}\n", request.getWmsFeatureInfoElements());
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

    /**
     * @return {@link GPWMSGetFeatureInfoRequest}
     */
    public static GPWMSGetFeatureInfoRequest createWMSGetFeatureRequest() {
        return new GPWMSGetFeatureInfoRequest() {
            {
                super.setCrs("EPSG:4326");
                super.setHeight("256");
                super.setWidth("256");
                super.setPoint(new WMSGetFeatureInfoPoint() {
                    {
                        super.setX(98);
                        super.setY(34);
                    }
                });
                super.setBoundingBox(new WMSGetFeatureInfoBoundingBox() {
                    {
                        super.setMinx(-82.06792594360869);
                        super.setMiny(35.02655390844236);
                        super.setMaxx(-82.06781624389134);
                        super.setMaxy(35.02664373997006);
                    }
                });
                super.setWmsFeatureInfoElements(Arrays.asList(createWMSGetFeatureInfoElement(), createWMSGetFeatureInfoElement()));
            }
        };
    }

    /**
     * @return {@link GPWMSGetFeatureInfoElement}
     */
    public static GPWMSGetFeatureInfoElement createWMSGetFeatureInfoElement() {
        return new GPWMSGetFeatureInfoElement() {
            {
                super.setWmsServerURL("http://150.145.141.180/geoserver/wms");
                super.setLayers(of("topp:states", "topp:states", "siti_protetti:zsc", "retenatura:zsc", "retenatura:zsc").collect(toList()));
            }
        };
    }
}