package org.geosdi.geoplatform.connector.server.request.v202.filter;

import org.geosdi.geoplatform.connector.server.request.CatalogGetRecordsRequest;
import org.geosdi.geoplatform.connector.server.request.v202.responsibility.handler.GPGetRecordsHandlerType;
import org.geosdi.geoplatform.exception.IllegalParameterFault;
import org.geosdi.geoplatform.xml.filter.v110.FilterType;

import javax.annotation.Nonnull;
import javax.xml.bind.JAXBElement;
import java.util.List;

import static javax.annotation.meta.When.NEVER;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public interface GPGetRecordsRequestHandlerFilter {

    /**
     * @param theRequest
     * @param theFilterType
     * @param theFilterPredicates
     * @throws IllegalParameterFault
     */
    void forwardGetRecordsRequest(@Nonnull(when = NEVER) CatalogGetRecordsRequest theRequest, @Nonnull(when = NEVER) FilterType theFilterType, @Nonnull(when = NEVER) List<JAXBElement<?>> theFilterPredicates) throws IllegalParameterFault;

    /**
     * @param theSuccessor
     */
    void setSuccessor(GetRecordsRequestHandlerFilter theSuccessor);

    /**
     * @param <HandlerType>
     * @return {@link HandlerType}
     */
    <HandlerType extends GPGetRecordsHandlerType> HandlerType getType();
}