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
package org.geosdi.geoplatform.connector.server.request.v110.query.repository;

import org.geosdi.geoplatform.gui.shared.wfs.OperatorType;
import org.geosdi.geoplatform.gui.shared.wfs.WFSOperatorType;
import org.geosdi.geoplatform.support.bridge.finder.GPImplementorFinder;
import org.geosdi.geoplatform.support.bridge.implementor.GPImplementor;
import org.geosdi.geoplatform.xml.filter.v110.ComparisonOpsType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Nonnull;
import java.util.*;

import static com.google.common.base.Preconditions.checkArgument;
import static java.util.function.Function.identity;
import static java.util.stream.Collectors.toMap;
import static javax.annotation.meta.When.NEVER;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public final class QueryRestrictionRepository implements WFSQueryRestrictionRepository<ComparisonOpsType> {

    private static final long serialVersionUID = -3162739880052052564L;
    //
    private static final Logger logger = LoggerFactory.getLogger(QueryRestrictionRepository.class);
    private static final GPImplementorFinder<QueryRestrictionStrategy<ComparisonOpsType>> finder = new QueryRestrictionsStrategyFinder<>();
    private static final Map<OperatorType, QueryRestrictionStrategy<ComparisonOpsType>> queryRestrictionStrategies;

    static {
        queryRestrictionStrategies = finder.getValidImplementors()
                .stream()
                .collect(toMap(k -> k.getKey().getImplementorKey(), identity(), (v1, v2) -> v1, () -> new EnumMap<>(OperatorType.class)));
        logger.debug("@@@@@@@@@@@@@@@@@@@@@@{} up with {} values.\n\n", QueryRestrictionRepository.class.getSimpleName(),
                queryRestrictionStrategies.size());
    }

    /**
     * @param operatorType
     * @return {@link QueryRestrictionStrategy<ComparisonOpsType>}
     */
    public QueryRestrictionStrategy<ComparisonOpsType> getQueryRestrictionStrategy(@Nonnull(when = NEVER) WFSOperatorType operatorType) {
        checkArgument(operatorType != null, "The Parameter operatorType must not be null.");
        logger.trace("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@Try to find QueryRestrictionStrategy with Key : {}\n", operatorType);
        return queryRestrictionStrategies.get(operatorType);
    }

    /**
     * @param key
     * @return {@link QueryRestrictionStrategy<ComparisonOpsType>}
     * @throws Exception
     */
    @Override
    public QueryRestrictionStrategy<ComparisonOpsType> getImplementorByKey(GPImplementor.GPImplementorKey key) throws Exception {
        checkArgument((key != null), "The Parameter key must not be null");
        logger.trace("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@Try to find QueryRestrictionStrategy with Key : {}\n", key);
        return queryRestrictionStrategies.get(key);
    }

    /**
     * @return {@link Set<QueryRestrictionStrategy<ComparisonOpsType>>}
     */
    @Override
    public Set<QueryRestrictionStrategy<ComparisonOpsType>> getAllImplementors() {
        return Collections.unmodifiableSet(finder.getAllImplementors());
    }

    /**
     * @return {@link Collection<QueryRestrictionStrategy<ComparisonOpsType>>}
     */
    @Override
    public Collection<QueryRestrictionStrategy<ComparisonOpsType>> getValidImplementors() {
        return Collections.unmodifiableCollection(finder.getValidImplementors());
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "{" +
                " queryRestrictionStrategies = " + queryRestrictionStrategies +
                "}";
    }
}