package org.geosdi.geoplatform.rs.support.linker.mediator;

import org.geosdi.geoplatform.rs.support.request.linker.GPBaseLinkerRequest;
import org.geosdi.geoplatform.rs.support.response.GPStatusResponse;

import javax.annotation.Nonnull;

import static javax.annotation.meta.When.NEVER;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public interface GPLinkerStrategyMediator<Request extends GPBaseLinkerRequest> {

    /**
     * @param theRequest
     * @param <Status>
     * @return {@link Status}
     * @throws Exception
     */
    <Status extends GPStatusResponse> Status link(@Nonnull(when = NEVER) Request theRequest) throws Exception;
}