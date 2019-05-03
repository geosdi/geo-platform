package org.geosdi.geoplatform.support.mail.configuration.properties.builder;

import org.geosdi.geoplatform.support.mail.configuration.properties.SMTPMailProperties;

import javax.annotation.Nonnull;
import java.util.Properties;

import static com.google.common.base.Preconditions.checkNotNull;
import static javax.annotation.meta.When.NEVER;
import static org.geosdi.geoplatform.support.mail.configuration.properties.JavaMailProp.*;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public interface IGPSMTPMailPropertiesBuilder {

    /**
     * @param theSMTPMailProp
     * @return {@link IGPSMTPMailPropertiesBuilder}
     */
    IGPSMTPMailPropertiesBuilder withSMTPMailProperties(@Nonnull(when = NEVER) SMTPMailProperties theSMTPMailProp);

    /**
     * @return {@link Properties}
     */
    Properties buildJavaMailProperties();

    class GPSMTPMailPropertiesBuilder implements IGPSMTPMailPropertiesBuilder {

        private SMTPMailProperties smtpMailProp;

        private GPSMTPMailPropertiesBuilder() {
        }

        /**
         * @return {@link IGPSMTPMailPropertiesBuilder}
         */
        public static IGPSMTPMailPropertiesBuilder smtpMailPropertiesBuilder() {
            return new GPSMTPMailPropertiesBuilder();
        }

        /**
         * @param theSMTPMailProp
         * @return {@link IGPSMTPMailPropertiesBuilder}
         */
        @Override
        public IGPSMTPMailPropertiesBuilder withSMTPMailProperties(@Nonnull(when = NEVER) SMTPMailProperties theSMTPMailProp) {
            this.smtpMailProp = theSMTPMailProp;
            return self();
        }

        /**
         * @return {@link Properties}
         */
        @Override
        public Properties buildJavaMailProperties() {
            checkNotNull(smtpMailProp, "SMTPMailProperties reference must not be null");
            return new Properties() {

                private static final long serialVersionUID = 3109256773218160485L;

                {
                    super.setProperty(MAIL_SOCKET_FACTORY_CLASS.toString(), smtpMailProp.getSocketFactoryClass());
                    super.setProperty(MAIL_HOST.toString(), smtpMailProp.getHost());
                    super.setProperty(MAIL_PORT.toString(), smtpMailProp.getPort().toString());
                    super.setProperty(MAIL_SMTP_SSL_ENABLE.toString(), smtpMailProp.isSmtpSslEnable().toString());
                    super.setProperty(MAIL_DEBUG.toString(), smtpMailProp.isDebugEnable().toString());
                    super.setProperty(MAIL_SMTP_STARTTLS_ENABLE.toString(), smtpMailProp.isSmtpStarttlsEnable().toString());
                    super.setProperty(MAIL_TRANSPORT_PROTOCOL.toString(), smtpMailProp.getTransportProtocol());
                    super.setProperty(MAIL_SMTP_AUTH.toString(), smtpMailProp.isSmtpAuth().toString());
                    super.setProperty(MAIL_USER.toString(), smtpMailProp.getUser());
                    super.setProperty(MAIL_PASSWORD.toString(), smtpMailProp.getPassword());
                }
            };
        }

        /**
         * @return {@link IGPSMTPMailPropertiesBuilder}
         */
        protected IGPSMTPMailPropertiesBuilder self() {
            return this;
        }
    }
}