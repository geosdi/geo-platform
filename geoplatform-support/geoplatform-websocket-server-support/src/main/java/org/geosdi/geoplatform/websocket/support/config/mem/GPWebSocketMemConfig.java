/**
 *
 *    geo-platform
 *    Rich webgis framework
 *    http://geo-platform.org
 *   ====================================================================
 *
 *   Copyright (C) 2008-2021 geoSDI Group (CNR IMAA - Potenza - ITALY).
 *
 *   This program is free software: you can redistribute it and/or modify it
 *   under the terms of the GNU General Public License as published by
 *   the Free Software Foundation, either version 3 of the License, or
 *   (at your option) any later version. This program is distributed in the
 *   hope that it will be useful, but WITHOUT ANY WARRANTY; without
 *   even the implied warranty of MERCHANTABILITY or FITNESS FOR
 *   A PARTICULAR PURPOSE. See the GNU General Public License
 *   for more details. You should have received a copy of the GNU General
 *   Public License along with this program. If not, see http://www.gnu.org/licenses/
 *
 *   ====================================================================
 *
 *   Linking this library statically or dynamically with other modules is
 *   making a combined work based on this library. Thus, the terms and
 *   conditions of the GNU General Public License cover the whole combination.
 *
 *   As a special exception, the copyright holders of this library give you permission
 *   to link this library with independent modules to produce an executable, regardless
 *   of the license terms of these independent modules, and to copy and distribute
 *   the resulting executable under terms of your choice, provided that you also meet,
 *   for each linked independent module, the terms and conditions of the license of
 *   that module. An independent module is a module which is not derived from or
 *   based on this library. If you modify this library, you may extend this exception
 *   to your version of the library, but you are not obligated to do so. If you do not
 *   wish to do so, delete this exception statement from your version.
 */
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
import org.springframework.web.socket.config.annotation.WebSocketTransportRegistration;
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

    /**
     * Configure options related to the processing of messages received from and
     * sent to WebSocket clients.
     *
     * @param registry
     */
    @Override
    public void configureWebSocketTransport(WebSocketTransportRegistration registry) {
//        registry.setMessageSizeLimit(50 * 1024 * 1024);
//        registry.setSendBufferSizeLimit(1024 * 1024);
    }
}