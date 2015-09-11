package org.geosdi.geoplatform.connector.server.request.v110.query.factory;

import com.google.common.collect.Maps;
import org.geosdi.geoplatform.gui.shared.wfs.OperatorType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.EnumMap;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public final class QueryRestrictionFactory {

    private static final Logger logger = LoggerFactory.getLogger(QueryRestrictionFactory.class);
    //
    private static final EnumMap<OperatorType, QueryRestrictionStrategy<?>> parameters = Maps.newEnumMap(
            OperatorType.class);

    static {
        for (QueryRestrictionStrategy<?> queryRestrictionStrategy : QueryRestrictionsStrategyFinder.getValidStrategies()) {
            parameters.put(queryRestrictionStrategy.forOperatorType(), queryRestrictionStrategy);
        }
        logger.debug("\n\n@@@@@@@@@@@@@@@@@@@@@@@@@@{} parameters map up with {} values.\n\n",
                QueryRestrictionFactory.class.getSimpleName(), parameters.size());
    }

    QueryRestrictionFactory() {
    }

    public static QueryRestrictionStrategy<?> getQueryRestrictionStrategy(OperatorType operatorType) {
        return parameters.get(operatorType);
    }
}
