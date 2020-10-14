package org.geosdi.geoplatform.websocket.support.spring.configuration.broker.rabbitmq;

import org.geosdi.geoplatform.websocket.support.spring.configuration.broker.GPWebSocketStompBroker;
import org.geosdi.geoplatform.websocket.support.spring.configuration.broker.rabbitmq.auth.IGPWebSocketRabbitMQAuth;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public interface IGPWebSocketRabbiMQBroker extends GPWebSocketStompBroker {

    /**
     * @return {@link String}
     */
    String getRelayHost();

    /**
     * @return {@link Integer}
     */
    Integer getRelayPort();

    /**
     * @return {@link IGPWebSocketRabbitMQAuth}
     */
    IGPWebSocketRabbitMQAuth getRabbitMQAuth();
}