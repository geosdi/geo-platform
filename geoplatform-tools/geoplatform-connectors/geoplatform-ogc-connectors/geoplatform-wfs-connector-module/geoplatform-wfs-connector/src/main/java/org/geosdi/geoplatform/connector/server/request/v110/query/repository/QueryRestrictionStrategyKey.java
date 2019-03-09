package org.geosdi.geoplatform.connector.server.request.v110.query.repository;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.geosdi.geoplatform.gui.shared.wfs.OperatorType;
import org.geosdi.geoplatform.support.bridge.implementor.GPImplementor;

import javax.annotation.Nonnull;

import static com.google.common.base.Preconditions.checkArgument;
import static javax.annotation.meta.When.NEVER;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@Getter
@EqualsAndHashCode(of = {"implementorKey"})
public class QueryRestrictionStrategyKey implements GPImplementor.GPImplementorKey<OperatorType> {

    private final OperatorType implementorKey;

    /**
     * @param theOperatorType
     */
    private QueryRestrictionStrategyKey(OperatorType theOperatorType) {
        this.implementorKey = theOperatorType;
    }

    /**
     * @param queryRestrictionStrategy
     * @return {@link QueryRestrictionStrategyKey}
     */
    public static QueryRestrictionStrategyKey forStrategy(@Nonnull(when = NEVER) QueryRestrictionStrategy queryRestrictionStrategy) {
        checkArgument(queryRestrictionStrategy != null, "The Parameter queryRestrictionStrategy must not be null.");
        return new QueryRestrictionStrategyKey(queryRestrictionStrategy.forOperatorType());
    }
}