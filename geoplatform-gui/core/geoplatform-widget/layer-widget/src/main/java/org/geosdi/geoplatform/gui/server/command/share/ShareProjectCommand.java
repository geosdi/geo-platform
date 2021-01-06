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
package org.geosdi.geoplatform.gui.server.command.share;

import javax.servlet.http.HttpServletRequest;
import org.geosdi.geoplatform.gui.client.command.share.ShareProjectRequest;
import org.geosdi.geoplatform.gui.client.command.share.ShareProjectResponse;
import org.geosdi.geoplatform.gui.command.api.GPCommandResponse;
import org.geosdi.geoplatform.gui.command.server.GPCommand;
import org.geosdi.geoplatform.gui.server.ILayerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

/**
 *
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@Lazy(true)
@Component(value = "command.share.ShareProjectCommand")
public class ShareProjectCommand implements
        GPCommand<ShareProjectRequest, ShareProjectResponse> {

    private static final Logger logger = LoggerFactory.getLogger(
            ShareProjectCommand.class);
    //
    @Autowired
    private ILayerService layerService;

    @Override
    public ShareProjectResponse execute(ShareProjectRequest request,
            HttpServletRequest httpServletRequest) {

        logger.debug("##################### Executing {} Command", this.
                getClass().getSimpleName());

        logger.debug("\n@@@@@@@@@@@ID Project: {}\n", 
                request.getIdSharedProject());
        logger.debug("\n@@@@@@@@@@@Accounts ID: {}\n",
                request.getAccountIDsProject());

        Boolean result = this.layerService.shareProjectToUsers(
                request.getIdSharedProject(),
                request.getAccountIDsProject(), httpServletRequest);

        logger.debug("#################### Found {} ", result);

        return new ShareProjectResponse(result);
    }

}
