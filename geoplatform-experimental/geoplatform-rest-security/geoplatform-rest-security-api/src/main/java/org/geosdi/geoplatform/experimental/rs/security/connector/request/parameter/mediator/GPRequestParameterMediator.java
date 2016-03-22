package org.geosdi.geoplatform.experimental.rs.security.connector.request.parameter.mediator;

import org.geosdi.geoplatform.experimental.rs.security.connector.request.parameter.GPRequestParameter;
import org.springframework.beans.factory.InitializingBean;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public interface GPRequestParameterMediator extends InitializingBean {

    /**
     * @param type
     * @param <RP>
     * @return {@link RP}
     * @throws Exception
     */
    <RP extends GPRequestParameter> RP getRequest(GPRequestParameter.GPRequestParamaterType type) throws Exception;
}
