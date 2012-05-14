/*
 *  geo-platform
 *  Rich webgis framework
 *  http://geo-platform.org
 * ====================================================================
 *
 * Copyright (C) 2008-2012 geoSDI Group (CNR IMAA - Potenza - ITALY).
 *
 * This program is free software: you can redistribute it and/or modify it 
 * under the terms of the GNU General Public License as published by 
 * the Free Software Foundation, either version 3 of the License, or 
 * (at your option) any later version. This program is distributed in the 
 * hope that it will be useful, but WITHOUT ANY WARRANTY; without 
 * even the implied warranty of MERCHANTABILITY or FITNESS FOR 
 * A PARTICULAR PURPOSE. See the GNU General Public License 
 * for more details. You should have received a copy of the GNU General 
 * Public License along with this program. If not, see http://www.gnu.org/licenses/ 
 *
 * ====================================================================
 *
 * Linking this library statically or dynamically with other modules is 
 * making a combined work based on this library. Thus, the terms and 
 * conditions of the GNU General Public License cover the whole combination. 
 * 
 * As a special exception, the copyright holders of this library give you permission 
 * to link this library with independent modules to produce an executable, regardless 
 * of the license terms of these independent modules, and to copy and distribute 
 * the resulting executable under terms of your choice, provided that you also meet, 
 * for each linked independent module, the terms and conditions of the license of 
 * that module. An independent module is a module which is not derived from or 
 * based on this library. If you modify this library, you may extend this exception 
 * to your version of the library, but you are not obligated to do so. If you do not 
 * wish to do so, delete this exception statement from your version. 
 *
 */
package org.geosdi.geoplatform.services.responsibility;

import org.geosdi.geoplatform.connector.server.request.CatalogGetRecordsRequest;
import org.geosdi.geoplatform.exception.IllegalParameterFault;
import org.geosdi.geoplatform.gui.responce.CatalogFinderBean;
import org.geosdi.geoplatform.gui.responce.TextInfo;
import org.geosdi.geoplatform.services.responsibility.TypeSearchRequest.GetRecordsSearchType;

/**
 *
 * @author Vincenzo Monteverde <vincenzo.monteverde@geosdi.org>
 */
public class TextSearchRequest extends GetRecordsRequestHandler {

    @Override
    protected void processGetRecordsRequest(GetRecordsSearchType searchType,
            CatalogFinderBean catalogFinder, CatalogGetRecordsRequest request)
            throws IllegalParameterFault {
        logger.debug("Process...");

        TextInfo textInfo = catalogFinder.getTextInfo();
        if (textInfo != null) {
            String searchText = textInfo.getText();
            boolean searchTitle = textInfo.isSearchTitle();
            boolean searchAbstract = textInfo.isSearchAbstract();
            boolean searchSubjects = textInfo.isSearchSubjects();
            if (searchText != null
                    && !searchTitle && !searchAbstract && !searchSubjects) {
                throw new IllegalParameterFault(
                        "You need to specify where to search \"" + searchText + "\" text");
            }

            logger.debug("\n+++ Search text: \"{}\" +++", searchText);
            if (searchText != null) {
                if (searchTitle & searchAbstract & searchSubjects) {
                    super.addConstraint(request, "AnyText LIKE '%" + searchText + "%'");
                } else {
                    if (searchTitle) {
                        super.addConstraint(request, "dc:title LIKE '%" + searchText + "%'");
                    }
                    if (searchAbstract) {
                        super.addConstraint(request, "dct:abstract LIKE '%" + searchText + "%'");
                    }
                    if (searchSubjects) {
                        super.addConstraint(request, "dc:subject LIKE '%" + searchText + "%'");
                    }
                }
                logger.trace("\n+++ Text constraint: \"{}\" +++", request.getConstraint());
            }
        }
    }
}
