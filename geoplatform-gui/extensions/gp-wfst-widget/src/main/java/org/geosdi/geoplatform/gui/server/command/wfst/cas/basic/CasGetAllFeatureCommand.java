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
package org.geosdi.geoplatform.gui.server.command.wfst.cas.basic;

import org.geosdi.geoplatform.connector.wfs.response.FeatureCollectionDTO;
import org.geosdi.geoplatform.connector.wfs.response.LayerSchemaDTO;
import org.geosdi.geoplatform.gui.client.command.wfst.basic.GetAllFeatureResponse;
import org.geosdi.geoplatform.gui.client.command.wfst.cas.basic.CasGetAllFeatureRequest;
import org.geosdi.geoplatform.gui.command.server.GPCommand;
import org.geosdi.geoplatform.gui.global.GeoPlatformException;
import org.geosdi.geoplatform.gui.server.command.wfst.cas.WFSCasUtility;
import org.geosdi.geoplatform.support.wfs.cas.services.CasDescribeFeatureService;
import org.geosdi.geoplatform.support.wfs.cas.services.CasGetFeatureService;
import org.jasig.cas.client.validation.Assertion;
import org.jasig.cas.client.validation.AssertionImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

import static java.util.Collections.EMPTY_MAP;
import static org.jasig.cas.client.util.AbstractCasFilter.CONST_CAS_ASSERTION;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@Lazy(true)
@Component(value = "command.wfst.cas.basic.CasGetAllFeatureCommand")
@Profile(value = "gs_cas")
public class CasGetAllFeatureCommand implements
        GPCommand<CasGetAllFeatureRequest, GetAllFeatureResponse> {

    private static final Logger logger = LoggerFactory.getLogger(
            CasGetAllFeatureCommand.class);
    //
    @Autowired
    private CasGetFeatureService gpCasGetFeatureService;
    @Autowired
    private CasDescribeFeatureService gpCasDescribeFeatureService;

    @Override
    public GetAllFeatureResponse execute(CasGetAllFeatureRequest request,
                                         HttpServletRequest httpServletRequest) {
        logger.debug("##################### Executing {} Command", this.getClass().getSimpleName());
        Assertion casAssertion = null;
        if (httpServletRequest != null) {
            casAssertion = (AssertionImpl) httpServletRequest.getSession().getAttribute(CONST_CAS_ASSERTION);
        }

        try {
            String url = WFSCasUtility.appendProxyTicketToURL(request.getServerUrl(), casAssertion);
            LayerSchemaDTO layerSchema = this.gpCasDescribeFeatureService.describeFeatureType(url, request.getTypeName(),
                    EMPTY_MAP);
            WFSCasUtility.appendProxyTicketToURL(layerSchema.getScope(), casAssertion);
            FeatureCollectionDTO result = this.gpCasGetFeatureService.getFeature(
                    layerSchema, request.getMaxFeatures(), EMPTY_MAP);
            logger.debug("#################### Found {} ", result);
            return new GetAllFeatureResponse(result);
        } catch (Exception ex) {
            logger.error("####################CasGetAllFeatureCommand "
                    + "error : {}", ex.getMessage());
            throw new GeoPlatformException(ex.getMessage().contains(
                    "unexpected element (uri:\"\", local:\"html\")")
                    ? "Unauthorized Service" : ex.getMessage());
        }
    }

}
