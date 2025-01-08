/*
 *
 *    geo-platform
 *    Rich webgis framework
 *    http://geo-platform.org
 *   ====================================================================
 *
 *   Copyright (C) 2008-2025 geoSDI Group (CNR IMAA - Potenza - ITALY).
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
package org.geosdi.geoplatform.gui.server.command;

import org.geosdi.geoplatform.gui.client.command.GetRecordByIdRequest;
import org.geosdi.geoplatform.gui.client.command.GetRecordByIdResponse;
import org.geosdi.geoplatform.gui.global.GeoPlatformException;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.text.Normalizer;
import java.util.regex.Pattern;

import static java.lang.System.currentTimeMillis;
import static java.nio.charset.StandardCharsets.UTF_8;
import static java.util.regex.Pattern.compile;
import static org.apache.commons.io.FileUtils.writeStringToFile;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@Lazy
@Component(value = "command.GetRecordByIdCommand")
class GetRecordByIdCommand extends BaseCSWCommand<GetRecordByIdRequest, GetRecordByIdResponse> {

    /**
     * @param request
     * @param httpServletRequest
     * @return
     */
    @Override
    public GetRecordByIdResponse execute(GetRecordByIdRequest request, HttpServletRequest httpServletRequest) {
        logger.debug("#####################Executing {} Command", this.getClass().getSimpleName());
        logger.debug("ServerID : {}", request.getServerID());
        logger.debug("Identifier : {}", request.getIdentifier());
        logger.debug("ModuleName : {}", request.getModuleName());
        try {
            String url = httpServletRequest.getSession().getServletContext().getRealPath("/" + request.getModuleName() + "/csw-template");
            logger.trace("PATH @@@@@@@@@@@@@@@@@@ {}", url);
            String response = geoPlatformCSWClient.getRecordById(request.getServerID(), request.getIdentifier());
            response = this.deAccent(response);
            String fileName = url + "/" + currentTimeMillis() + "-" + request.getIdentifier() + ".xml";
            File file = new File(fileName);
            writeStringToFile(file, response, UTF_8);
            logger.debug("Name FILE Created  @@@@@@@@@@@@@@@@@@@@@@@@@@@ {}", file.getName());
            return new GetRecordByIdResponse(file.getName());
        } catch (Exception ex) {
            logger.error("\n*** IOException ***\n{}", ex.getMessage());
            throw new GeoPlatformException(ex.getMessage());
        }
    }

    /**
     * @param str
     * @return {@link String}
     */
    protected String deAccent(String str) {
        String nfdNormalizedString = Normalizer.normalize(str, Normalizer.Form.NFD);
        Pattern pattern = compile("\\p{InCombiningDiacriticalMarks}+");
        return pattern.matcher(nfdNormalizedString).replaceAll("'");
    }
}