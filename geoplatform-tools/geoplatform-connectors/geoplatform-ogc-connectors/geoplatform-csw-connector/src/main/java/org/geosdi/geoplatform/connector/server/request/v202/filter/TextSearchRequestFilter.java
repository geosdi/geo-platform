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
package org.geosdi.geoplatform.connector.server.request.v202.filter;

import org.geosdi.geoplatform.connector.server.request.CatalogGetRecordsRequest;
import org.geosdi.geoplatform.connector.server.request.v202.responsibility.handler.GPGetRecordsHandlerType;
import org.geosdi.geoplatform.exception.IllegalParameterFault;
import org.geosdi.geoplatform.gui.responce.TextInfo;
import org.geosdi.geoplatform.xml.filter.v110.*;

import javax.xml.bind.JAXBElement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.geosdi.geoplatform.connector.server.request.v202.responsibility.handler.GetRecordsHandlerType.TEXT;

/**
 * @author Giuseppe La Scaleia <giuseppe.lascaleia@geosdi.org>
 * @author Vincenzo Monteverde <vincenzo.monteverde@geosdi.org>
 */
public class TextSearchRequestFilter extends GetRecordsRequestHandlerFilter {

    private final static String ANYTEXT = "AnyText";
    private final static String TITLE = "dc:title";
    private final static String ABSTRACT = "dc:abstract";
    private final static String SUBJECT = "dc:subject";

    /**
     * @param theRequest
     * @param theFilterType
     * @param theFilterPredicates
     * @throws IllegalParameterFault
     */
    @Override
    protected void processGetRecordsRequest(CatalogGetRecordsRequest theRequest, FilterType theFilterType, List<JAXBElement<?>> theFilterPredicates) throws IllegalParameterFault {
        logger.debug("#####################Called {}#processGetRecordsRequest", this);
        TextInfo textInfo = theRequest.getCatalogFinder().getTextInfo();
        if (textInfo != null) {
            String searchText = textInfo.getText();
            boolean searchTitle = textInfo.isSearchTitle();
            boolean searchAbstract = textInfo.isSearchAbstract();
            boolean searchSubjects = textInfo.isSearchSubjects();
            if (searchText != null) {
                if (!searchTitle && !searchAbstract && !searchSubjects) {
                    throw new IllegalParameterFault("You need to specify where to search \"" + searchText + "\" text");
                }
                logger.debug("######################## Search text: \"{}\"\n", searchText);
                List<JAXBElement<?>> textPredicate = this.createFilterTextPredicate(searchText, searchTitle, searchAbstract, searchSubjects);
                logger.trace("########################## Text filter: \"{}\"\n", textPredicate);
                theFilterPredicates.addAll(textPredicate);
            }
        }
    }

    /**
     * @return {@link GPGetRecordsHandlerType}
     */
    @Override
    public GPGetRecordsHandlerType getType() {
        return TEXT;
    }

    /**
     * @param searchText
     * @param searchTitle
     * @param searchAbstract
     * @param searchSubjects
     * @return {@link List<JAXBElement<?>}
     */
    private List<JAXBElement<?>> createFilterTextPredicate(String searchText, boolean searchTitle, boolean searchAbstract, boolean searchSubjects) {
        List<JAXBElement<?>> textPredicate = new ArrayList<>(3);
        String searchTextLike = "%" + searchText + "%";
        if (searchTitle & searchAbstract & searchSubjects) {
            PropertyIsLikeType anytextIsLikeType = this.createPropertyIsLikeType(ANYTEXT, searchTextLike);
            textPredicate.add(filterFactory.createPropertyIsLike(anytextIsLikeType));
        } else {
            if (searchTitle) {
                PropertyIsLikeType titleIsLikeType = this.createPropertyIsLikeType(TITLE, searchTextLike);
                textPredicate.add(filterFactory.createPropertyIsLike(titleIsLikeType));
            }
            if (searchAbstract) {
                PropertyIsLikeType abstractIsLikeType = this.createPropertyIsLikeType(ABSTRACT, searchTextLike);
                textPredicate.add(filterFactory.createPropertyIsLike(abstractIsLikeType));
            }
            if (searchSubjects) {
                BinaryComparisonOpType subjectIsEqualTo = this.createBinaryComparisonOpType(SUBJECT, searchText);
                textPredicate.add(filterFactory.createPropertyIsEqualTo(subjectIsEqualTo));
            }
        }
        return textPredicate;
    }

    /**
     * @param propertyName
     * @param literal
     * @return {@link PropertyIsLikeType}
     */
    private PropertyIsLikeType createPropertyIsLikeType(String propertyName, String literal) {
        PropertyIsLikeType propertyIsLikeType = new PropertyIsLikeType();
        propertyIsLikeType.setWildCard("%");
        propertyIsLikeType.setSingleChar(".");
        propertyIsLikeType.setEscapeChar("\\");
        PropertyNameType propertyNameType = new PropertyNameType();
        propertyNameType.setContent(Arrays.<Object>asList(propertyName));
        propertyIsLikeType.setPropertyName(propertyNameType);
        LiteralType literalType = new LiteralType();
        literalType.setContent(Arrays.<Object>asList(literal));
        propertyIsLikeType.setLiteral(literalType);
        return propertyIsLikeType;
    }
}