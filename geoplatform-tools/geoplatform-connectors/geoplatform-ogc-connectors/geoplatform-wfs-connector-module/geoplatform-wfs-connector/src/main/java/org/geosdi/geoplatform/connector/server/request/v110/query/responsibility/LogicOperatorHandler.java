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
package org.geosdi.geoplatform.connector.server.request.v110.query.responsibility;

import org.geosdi.geoplatform.connector.server.request.v110.query.repository.QueryRestrictionStrategy;
import org.geosdi.geoplatform.connector.wfs.response.QueryDTO;
import org.geosdi.geoplatform.connector.wfs.response.QueryRestrictionDTO;
import org.geosdi.geoplatform.gui.shared.wfs.OperatorType;
import org.geosdi.geoplatform.xml.filter.v110.FilterType;
import org.geosdi.geoplatform.xml.filter.v110.ObjectFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Nullable;
import javax.xml.bind.JAXBElement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

import static com.google.common.collect.Lists.newArrayList;
import static java.util.stream.Collectors.toList;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public abstract class LogicOperatorHandler implements ILogicOperatorHandler {

    protected final Logger logger = LoggerFactory.getLogger(this.getClass());
    //
    protected static final ObjectFactory filterFactory = QueryRestrictionStrategy.filterFactory;
    private LogicOperatorHandler successor;

    /**
     * @param queryDTO
     * @param filter
     * @throws IllegalStateException
     */
    protected void forwardBuildLogicFilterOperator(QueryDTO queryDTO, FilterType filter) throws IllegalStateException {
        if (this.successor != null) {
            this.successor.buildLogicFilterOperator(queryDTO, filter);
        }
    }

    /**
     * @param theLogicOperator
     * @return {@link Boolean}
     */
    protected Boolean canBuildLogicOperator(String theLogicOperator) {
        return ((theLogicOperator != null) && (this.getOperatorValue().equalsIgnoreCase(theLogicOperator)));
    }

    /**
     * @param theQueryRestrictions
     * @return {@link List<JAXBElement<?>>}
     */
    protected List<JAXBElement<?>> buildJAXBElementList(List<QueryRestrictionDTO> theQueryRestrictions) {
        Collection<QueryRestrictionDTO> queryRestrictions = cleanUp(theQueryRestrictions);
        List<JAXBElement<?>> elements = new ArrayList<>(queryRestrictions.size());
        for (QueryRestrictionDTO queryRestrictionDTO : queryRestrictions) {
            OperatorType operatorType = queryRestrictionDTO.getOperator();
            QueryRestrictionStrategy<?> queryRestrictionStrategy = QUERY_RESTRICTION_REPOSITORY.getQueryRestrictionStrategy(operatorType);
            if (queryRestrictionStrategy != null) {
                elements.add(queryRestrictionStrategy.create(queryRestrictionDTO));
            } else {
                logger.debug("###############{} doesn't found QueryRestrictionStrategy<?> for OperatorType : {}\n", getFilterName(), operatorType);
            }
        }
        return elements;
    }

    /**
     * @param theQueryRestrictions
     * @return {@link Collection<QueryRestrictionDTO>}
     */
    Collection<QueryRestrictionDTO> cleanUp(@Nullable Collection<QueryRestrictionDTO> theQueryRestrictions) {
        return (theQueryRestrictions != null ? theQueryRestrictions.stream()
                .filter(Objects::nonNull)
                .filter(r -> r.getOperator() != null)
                .filter(r -> ((r.getAttribute() != null) && (r.getAttribute().getName() != null) && !(r.getAttribute().getName().trim().isEmpty())))
                .filter(r -> ((r.getRestriction() != null) && !(r.getRestriction().trim().isEmpty())))
                .collect(toList()) : newArrayList());
    }


    /**
     * @param filter
     * @param queryRestrictionDTOs
     */
    protected abstract void processQueryRestrictions(FilterType filter, List<QueryRestrictionDTO> queryRestrictionDTOs);

    /**
     * <p>We have three Operator type see {@link org.geosdi.geoplatform.gui.shared.wfs.LogicOperatorType}</p>
     *
     * @return the Operator
     */
    protected abstract String getOperatorValue();

    /**
     * @param theSuccessor
     */
    public void setSuccessor(LogicOperatorHandler theSuccessor) {
        this.successor = theSuccessor;
    }

    /**
     * @return {@link String}
     */
    @Override
    public String getFilterName() {
        return getClass().getSimpleName();
    }
}