package org.geosdi.geoplatform.websocket.support.config.mem;

import org.geosdi.geoplatform.configurator.bootstrap.Develop;
import org.geosdi.geoplatform.websocket.support.spring.configuration.broker.GPWebSocketStompBroker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.server.HandshakeInterceptor;

import javax.annotation.Resource;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@Configuration
@Develop
@EnableWebSocketMessageBroker
class GPWebSocketMemConfig implements WebSocketMessageBrokerConfigurer {

    private static final Logger logger = LoggerFactory.getLogger(GPWebSocketMemConfig.class);
    //
    @Resource(name = "geoPlatformWebSocketMemBroker")
    private GPWebSocketStompBroker geoPlatformWebSocketMemBroker;
    @Resource(name = "geoPlatformSessionIdHandshakeInterceptor")
    private HandshakeInterceptor geoPlatformSessionIdHandshakeInterceptor;
    @Resource(name = "geoPlatformWebSocketServerTaskScheduler")
    private TaskScheduler geoPlatformWebSocketServerTaskScheduler;

    /**
     * Configure message broker options.
     *
     * @param registry
     */
    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        logger.debug("##########################Configuring MessageBrokerRegistry @Dev with : {}\n", this.geoPlatformWebSocketMemBroker);
        registry.setApplicationDestinationPrefixes(this.geoPlatformWebSocketMemBroker.getDestinationApplicationPrefix())
                .enableSimpleBroker(this.geoPlatformWebSocketMemBroker.getStompBrokers())
                .setTaskScheduler(this.geoPlatformWebSocketServerTaskScheduler);
    }

    /**
     * Register STOMP endpoints mapping each to a specific URL and (optionally)
     * enabling and configuring SockJS fallback options.
     *
     * @param registry
     */
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint(this.geoPlatformWebSocketMemBroker.getEndpoints())
                .setAllowedOrigins("*")
                .addInterceptors(this.geoPlatformSessionIdHandshakeInterceptor);
    }
}