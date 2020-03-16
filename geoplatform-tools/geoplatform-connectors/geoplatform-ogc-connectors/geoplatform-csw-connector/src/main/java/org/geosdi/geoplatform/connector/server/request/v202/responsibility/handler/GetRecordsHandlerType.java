package org.geosdi.geoplatform.connector.server.request.v202.responsibility.handler;

import javax.annotation.Nonnull;

import static com.google.common.base.Preconditions.checkArgument;
import static javax.annotation.meta.When.NEVER;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public enum GetRecordsHandlerType implements GPGetRecordsHandlerType {

    TEXT("TEXT_SEARCH_HANDLER"),
    TIME("TIME_SEARCH_HANDLER"),
    SPATIAL("AREA_SEARCH_HANDLER");

    private final String handlerType;

    /**
     * @param theHandlerType
     */
    GetRecordsHandlerType(@Nonnull(when = NEVER) String theHandlerType) {
        checkArgument(((theHandlerType != null) && !(theHandlerType.trim().isEmpty())), "The Parameter handlerType must not be null");
        this.handlerType = theHandlerType;
    }

    /**
     * @return {@link String}
     */
    @Override
    public String getHandlerType() {
        return this.handlerType;
    }

    @Override
    public String toString() {
        return this.handlerType;
    }
}