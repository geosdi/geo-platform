package org.geosdi.geoplatform.experimental.openam.api.connector.request.parameter.mediator;

import org.geosdi.geoplatform.experimental.openam.api.connector.request.parameter.RequestParameter;
import org.springframework.beans.factory.InitializingBean;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public interface IRequestParameterMediator extends InitializingBean {

    /**
     * @param type
     * @param <RP>
     * @return {@link RP}
     * @throws Exception
     */
    <RP extends RequestParameter> RP getRequest(RequestParameter.RequestParameterType type) throws Exception;
}
