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
package org.geosdi.geoplatform.connector.server.request.v202.responsibility;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.xml.bind.JAXBElement;
import org.geosdi.geoplatform.connector.server.request.CatalogGetRecordsRequest;
import org.geosdi.geoplatform.exception.IllegalParameterFault;
import org.geosdi.geoplatform.gui.responce.TextInfo;
import org.geosdi.geoplatform.xml.filter.v110.BinaryComparisonOpType;
import org.geosdi.geoplatform.xml.filter.v110.BinaryLogicOpType;
import org.geosdi.geoplatform.xml.filter.v110.ComparisonOpsType;
import org.geosdi.geoplatform.xml.filter.v110.FilterType;
import org.geosdi.geoplatform.xml.filter.v110.LiteralType;
import org.geosdi.geoplatform.xml.filter.v110.PropertyIsLikeType;
import org.geosdi.geoplatform.xml.filter.v110.PropertyNameType;

/**
 *
 * @author Vincenzo Monteverde <vincenzo.monteverde@geosdi.org>
 */
public class TextSearchRequest extends GetRecordsRequestHandler {

    private final static String ANYTEXT = "AnyText";
    private final static String TITLE = "dc:title";
    private final static String ABSTRACT = "dc:abstract";
    private final static String SUBJECT = "dc:subject";

    @Override
    protected void processGetRecordsRequest(CatalogGetRecordsRequest request, FilterType filterType)
            throws IllegalParameterFault {
        logger.debug("Process...");

        TextInfo textInfo = request.getCatalogFinder().getTextInfo();
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
                switch (request.getConstraintLanguage()) {
                    case FILTER:
                        this.createConstraintFilter(filterType, searchText,
                                searchTitle, searchAbstract, searchSubjects);
                        break;

                    case CQL_TEXT:
                        String constraint = this.createConstraintCQL(searchText,
                                searchTitle, searchAbstract, searchSubjects);

                        logger.trace("\n+++ Text constraint: \"{}\" +++", request.getConstraint());
                        super.addCQLConstraint(request, constraint);
                        break;
                }
            }
        }
    }

    private void createConstraintFilter(FilterType filterType, String searchText,
            boolean searchTitle, boolean searchAbstract, boolean searchSubjects) {

        String searchTextLike = "%" + searchText + "%";

        if (searchTitle & searchAbstract & searchSubjects) {
            PropertyIsLikeType anytextIsLikeType = this.createPropertyIsLikeType(
                    ANYTEXT, searchTextLike);

            filterType.setComparisonOps(filterFactory.createPropertyIsLike(anytextIsLikeType));

        } else {
            List<JAXBElement<? extends ComparisonOpsType>> propertyList =
                    new ArrayList<JAXBElement<? extends ComparisonOpsType>>(3);

            if (searchTitle) {
                PropertyIsLikeType titleIsLikeType = this.createPropertyIsLikeType(
                        TITLE, searchTextLike);

                propertyList.add(filterFactory.createPropertyIsLike(titleIsLikeType));
            }

            if (searchAbstract) {
                PropertyIsLikeType abstractIsLikeType = this.createPropertyIsLikeType(
                        ABSTRACT, searchTextLike);

                propertyList.add(filterFactory.createPropertyIsLike(abstractIsLikeType));
            }

            if (searchSubjects) {
                BinaryComparisonOpType subjectIsEqualTo = this.createBinaryComparisonOpType(
                        SUBJECT, searchText);

                propertyList.add(filterFactory.createPropertyIsEqualTo(subjectIsEqualTo));
            }

            if (propertyList.size() == 1) {
                filterType.setComparisonOps(propertyList.get(0));
            } else {
                BinaryLogicOpType binary = new BinaryLogicOpType();

                binary.setComparisonOpsOrSpatialOpsOrLogicOps(new ArrayList<JAXBElement<?>>(propertyList));
            }
        }

    }

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

    private String createConstraintCQL(String searchText,
            boolean searchTitle, boolean searchAbstract, boolean searchSubjects) {

        StringBuilder constraint = new StringBuilder();

        if (searchTitle & searchAbstract & searchSubjects) {
            constraint.append(ANYTEXT);

        } else {
            if (searchTitle) {
                constraint.append(TITLE);
            }

            if (searchAbstract) {
                if (constraint.length() > 0) {
                    constraint.append(" OR ");
                }
                constraint.append(ABSTRACT);
            }

            if (searchSubjects) {
                if (constraint.length() > 0) {
                    constraint.append(" OR ");
                }
                constraint.append(SUBJECT);
            }
        }

        if (constraint.length() > 0) {
            constraint.append(" LIKE '%").append(searchText).append("%'");
        }

        return constraint.toString();
    }
}
