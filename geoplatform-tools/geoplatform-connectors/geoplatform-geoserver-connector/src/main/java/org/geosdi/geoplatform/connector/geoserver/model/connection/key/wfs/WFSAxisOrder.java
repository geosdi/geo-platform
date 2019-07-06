package org.geosdi.geoplatform.connector.geoserver.model.connection.key.wfs;

import javax.annotation.Nonnull;

import static com.google.common.base.Preconditions.checkArgument;
import static javax.annotation.meta.When.NEVER;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public enum WFSAxisOrder {

    COMPLIANT("Compliant"),
    EAST_NORTH("East / North"),
    NORTH_EAST("North / East");

    private final String order;

    /**
     * @param theOrder
     */
    WFSAxisOrder(@Nonnull(when = NEVER) String theOrder) {
        checkArgument(((theOrder != null) && !(theOrder.trim().isEmpty())), "The Parameter order must not be null or an empty string.");
        this.order = theOrder;
    }

    /**
     * @return {@link String}
     */
    @Override
    public String toString() {
        return this.order;
    }
}