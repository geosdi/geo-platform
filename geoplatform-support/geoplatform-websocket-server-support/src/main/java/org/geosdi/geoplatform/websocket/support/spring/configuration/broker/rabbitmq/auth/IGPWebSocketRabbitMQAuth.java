package org.geosdi.geoplatform.websocket.support.spring.configuration.broker.rabbitmq.auth;

import org.springframework.beans.factory.InitializingBean;

import java.io.Serializable;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public interface IGPWebSocketRabbitMQAuth extends InitializingBean, Serializable {

    /**
     * @return {@link String}
     */
    String getClientLogin();

    /**
     * @return {@link String}
     */
    String getClientPasscode();
}
