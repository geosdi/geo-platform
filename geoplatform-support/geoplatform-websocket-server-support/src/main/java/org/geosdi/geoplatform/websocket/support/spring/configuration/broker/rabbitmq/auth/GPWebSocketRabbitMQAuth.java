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
package org.geosdi.geoplatform.websocket.support.spring.configuration.broker.rabbitmq.auth;

import lombok.Getter;
import lombok.ToString;
import net.jcip.annotations.Immutable;
import org.geosdi.geoplatform.configurator.bootstrap.Production;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import static com.google.common.base.Preconditions.checkArgument;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@ToString
@Getter
@Immutable
@Production
@Component(value = "geoPlatformWebSocketRabbitMQAuth")
class GPWebSocketRabbitMQAuth implements IGPWebSocketRabbitMQAuth {

    private static final long serialVersionUID = -725477416523701584L;
    //
    private static final String CLIENT_LOGIN_VALUE = "geoPlatformWebSocketServerConfigurator{gp.websocket.rabbitmq.client_login:@null}";
    private static final String CLIENT_PASSCODE_VALUE = "geoPlatformWebSocketServerConfigurator{gp.websocket.rabbitmq.client_passcode:@null}";
    //
    @Value(CLIENT_LOGIN_VALUE)
    private String clientLogin;
    @Value(CLIENT_PASSCODE_VALUE)
    private String clientPasscode;

    /**
     * Invoked by the containing {@code BeanFactory} after it has set all bean properties
     * and satisfied {@link org.springframework.beans.factory.BeanFactoryAware}, {@code ApplicationContextAware} etc.
     * <p>This method allows the bean instance to perform validation of its overall
     * configuration and final initialization when all bean properties have been set.
     *
     * @throws Exception in the event of misconfiguration (such as failure to set an
     *                   essential property) or if initialization fails for any other reason
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        checkArgument((this.clientLogin != null) && !(this.clientLogin.trim().isEmpty()) && !(this.clientLogin.equals(CLIENT_LOGIN_VALUE)), "Parameter ClientLogin can not be Null or an Empty String or " + CLIENT_LOGIN_VALUE);
        checkArgument((this.clientPasscode != null) && !(this.clientPasscode.trim().isEmpty()) && !(this.clientPasscode.equals(CLIENT_PASSCODE_VALUE)), "Parameter ClientPasscode must not be null or an Empty String or " + CLIENT_PASSCODE_VALUE);
    }
}