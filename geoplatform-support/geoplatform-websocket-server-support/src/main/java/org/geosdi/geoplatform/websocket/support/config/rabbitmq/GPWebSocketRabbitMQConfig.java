package org.geosdi.geoplatform.websocket.support.config.rabbitmq;

import org.geosdi.geoplatform.configurator.bootstrap.Production;
import org.geosdi.geoplatform.websocket.support.spring.configuration.broker.rabbitmq.IGPWebSocketRabbiMQBroker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.server.HandshakeInterceptor;

import static com.google.common.base.Preconditions.checkArgument;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@Configuration
@Production
@EnableWebSocketMessageBroker
class GPWebSocketRabbitMQConfig implements WebSocketMessageBrokerConfigurer {

    private static final Logger logger = LoggerFactory.getLogger(GPWebSocketRabbitMQConfig.class);
    //
    private final IGPWebSocketRabbiMQBroker geoPlatformWebSocketRabbitMQBroker;
    private final HandshakeInterceptor geoPlatformSessionIdHandshakeInterceptor;

    /**
     * @param theGeoPlatformWebSocketRabbitMQBroker
     * @param theGeoPlatformSessionIdHandshakeInterceptor
     */
    GPWebSocketRabbitMQConfig(@Qualifier(value = "geoPlatformWebSocketRabbitMQBroker") IGPWebSocketRabbiMQBroker theGeoPlatformWebSocketRabbitMQBroker,
            @Qualifier(value = "geoPlatformSessionIdHandshakeInterceptor") HandshakeInterceptor theGeoPlatformSessionIdHandshakeInterceptor) {
        checkArgument(theGeoPlatformWebSocketRabbitMQBroker != null, "The Parameter theGeoPlatformWebSocketRabbitMQBroker must not be null.");
        checkArgument(theGeoPlatformSessionIdHandshakeInterceptor != null, "The Parameter theGeoPlatformSessionIdHandshakeInterceptor must not be null.");
        this.geoPlatformWebSocketRabbitMQBroker = theGeoPlatformWebSocketRabbitMQBroker;
        this.geoPlatformSessionIdHandshakeInterceptor = theGeoPlatformSessionIdHandshakeInterceptor;
    }

    /**
     * Configure message broker options.
     *
     * @param registry
     */
    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        logger.debug("##########################Configuring MessageBrokerRegistry @Prod with : {}\n", this.geoPlatformWebSocketRabbitMQBroker);
        registry.setApplicationDestinationPrefixes(this.geoPlatformWebSocketRabbitMQBroker.getDestinationApplicationPrefix())
                .enableStompBrokerRelay(this.geoPlatformWebSocketRabbitMQBroker.getStompBrokers())
                .setRelayHost(this.geoPlatformWebSocketRabbitMQBroker.getRelayHost())
                .setRelayPort(this.geoPlatformWebSocketRabbitMQBroker.getRelayPort())
                .setSystemLogin(this.geoPlatformWebSocketRabbitMQBroker.getRabbitMQAuth().getClientLogin())
                .setSystemPasscode(this.geoPlatformWebSocketRabbitMQBroker.getRabbitMQAuth().getClientPasscode())
                .setClientLogin(this.geoPlatformWebSocketRabbitMQBroker.getRabbitMQAuth().getClientLogin())
                .setClientPasscode(this.geoPlatformWebSocketRabbitMQBroker.getRabbitMQAuth().getClientPasscode());
    }

    /**
     * Register STOMP endpoints mapping each to a specific URL and (optionally)
     * enabling and configuring SockJS fallback options.
     *
     * @param registry
     */
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint(this.geoPlatformWebSocketRabbitMQBroker.getEndpoints())
                .setAllowedOrigins("*")
                .addInterceptors(this.geoPlatformSessionIdHandshakeInterceptor);
    }
}