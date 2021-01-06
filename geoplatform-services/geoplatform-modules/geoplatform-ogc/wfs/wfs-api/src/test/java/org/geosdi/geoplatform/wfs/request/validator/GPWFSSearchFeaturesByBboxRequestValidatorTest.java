/**
 *
 *    geo-platform
 *    Rich webgis framework
 *    http://geo-platform.org
 *   ====================================================================
 *
 *   Copyright (C) 2008-2021 geoSDI Group (CNR IMAA - Potenza - ITALY).
 *
 *   This program is free software: you can redistribute it and/or modify it
 *   under the terms of the GNU General Public License as published by
 *   the Free Software Foundation, either version 3 of the License, or
 *   (at your option) any later version. This program is distributed in the
 *   hope that it will be useful, but WITHOUT ANY WARRANTY; without
 *   even the implied warranty of MERCHANTABILITY or FITNESS FOR
 *   A PARTICULAR PURPOSE. See the GNU General Public License
 *   for more details. You should have received a copy of the GNU General
 *   Public License along with this program. If not, see http://www.gnu.org/licenses/
 *
 *   ====================================================================
 *
 *   Linking this library statically or dynamically with other modules is
 *   making a combined work based on this library. Thus, the terms and
 *   conditions of the GNU General Public License cover the whole combination.
 *
 *   As a special exception, the copyright holders of this library give you permission
 *   to link this library with independent modules to produce an executable, regardless
 *   of the license terms of these independent modules, and to copy and distribute
 *   the resulting executable under terms of your choice, provided that you also meet,
 *   for each linked independent module, the terms and conditions of the license of
 *   that module. An independent module is a module which is not derived from or
 *   based on this library. If you modify this library, you may extend this exception
 *   to your version of the library, but you are not obligated to do so. If you do not
 *   wish to do so, delete this exception statement from your version.
 */
package org.geosdi.geoplatform.wfs.request.validator;

import org.geosdi.geoplatform.connector.wfs.response.QueryDTO;
import org.geosdi.geoplatform.gui.shared.bean.BBox;
import org.geosdi.geoplatform.jaxb.GPJAXBContextBuilder;
import org.geosdi.geoplatform.services.request.GPWFSSearchFeaturesByBboxAndQueryRequest;
import org.geosdi.geoplatform.services.request.GPWFSSearchFeaturesByBboxRequest;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.io.StringReader;
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

    /**
     * @return {@link GPWFSSearchFeaturesByBboxRequest}
     */
    public static GPWFSSearchFeaturesByBboxAndQueryRequest createWFSSearchFeaturesByBboxAndQueryRequest() {
        return new GPWFSSearchFeaturesByBboxAndQueryRequest() {

            {
                super.setServerURL("http://150.145.141.92/geoserver/wfs");
                super.setTypeName("topp:states");
                super.setMaxFeatures(50);
                super.setQueryDTO(GPJAXBContextBuilder.newInstance()
                        .unmarshal(new StringReader(
                                "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n" +
                                        "<QueryDTO>\n" +
                                        "    <matchOperator>ALL</matchOperator>\n" +
                                        "    <queryRestrictionList>\n" +
                                        "        <queryRestriction>\n" +
                                        "            <attribute>\n" +
                                        "                <maxOccurs>1</maxOccurs>\n" +
                                        "                <minOccurs>0</minOccurs>\n" +
                                        "                <name>WORKERS</name>\n" +
                                        "                <nillable>true</nillable>\n" +
                                        "                <type>double</type>\n" +
                                        "                <value></value>\n" +
                                        "            </attribute>\n" +
                                        "            <operator>LESS_OR_EQUAL</operator>\n" +
                                        "            <restriction>0.25</restriction>\n" +
                                        "        </queryRestriction>\n" +
                                        "    </queryRestrictionList>\n" +
                                        "</QueryDTO>"), QueryDTO.class));
                super.setBBox(new BBox(-75.102613, 40.212597, -72.361859, 41.512517));
            }
        };
    }
}