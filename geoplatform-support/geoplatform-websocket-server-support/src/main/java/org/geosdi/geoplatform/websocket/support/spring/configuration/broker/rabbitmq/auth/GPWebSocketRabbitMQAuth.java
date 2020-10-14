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