package org.geosdi.geoplatform.connector.server.request.v202.cql;

import org.geosdi.geoplatform.connector.server.request.CatalogGetRecordsRequest;
import org.geosdi.geoplatform.connector.server.request.v202.responsibility.handler.GPGetRecordsHandlerType;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import static javax.annotation.meta.When.NEVER;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public interface GPGetRecordsRequestHandlerCQL {

    /**
     * @param theSuccessor
     */
    void setSuccessor(@Nullable GetRecordsRequestHandlerCQL theSuccessor);

    /**
     * @param request
     * @throws Exception
     */
    void forwardGetRecordsRequest(@Nonnull(when = NEVER) CatalogGetRecordsRequest request) throws Exception;

    /**
     * @param <HandlerType>
     * @return {@link HandlerType}
     */
    <HandlerType extends GPGetRecordsHandlerType> HandlerType getType();
}