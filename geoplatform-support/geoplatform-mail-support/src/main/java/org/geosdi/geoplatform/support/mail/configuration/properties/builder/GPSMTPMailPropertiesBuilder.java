/**
 *
 *    geo-platform
 *    Rich webgis framework
 *    http://geo-platform.org
 *   ====================================================================
 *
 *   Copyright (C) 2008-2018 geoSDI Group (CNR IMAA - Potenza - ITALY).
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
package org.geosdi.geoplatform.support.mail.configuration.properties.builder;

import com.google.common.base.Preconditions;
import java.util.Properties;
import org.geosdi.geoplatform.support.mail.configuration.properties.JavaMailProp;
import org.geosdi.geoplatform.support.mail.configuration.properties.SMTPMailProperties;

/**
 *
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class GPSMTPMailPropertiesBuilder {

    private SMTPMailProperties smtpMailProp;

    private GPSMTPMailPropertiesBuilder() {
    }

    public static GPSMTPMailPropertiesBuilder newInstance() {
        return new GPSMTPMailPropertiesBuilder();
    }

    public GPSMTPMailPropertiesBuilder withSMTPMailProperties(
            SMTPMailProperties theSMTPMailProp) {
        this.smtpMailProp = theSMTPMailProp;
        return this;
    }

    public Properties buildJavaMailProperties() {
        Preconditions.checkNotNull(smtpMailProp, "SMTPMailProperties reference "
                + "must not be null");

        return new Properties() {

            private static final long serialVersionUID = 3109256773218160485L;

            {
                super.setProperty(JavaMailProp.MAIL_SOCKET_FACTORY_CLASS.toString(),
                        smtpMailProp.getSocketFactoryClass());
                super.setProperty(JavaMailProp.MAIL_HOST.toString(),
                        smtpMailProp.getHost());
                super.setProperty(JavaMailProp.MAIL_PORT.toString(),
                        smtpMailProp.getPort().toString());
                super.setProperty(JavaMailProp.MAIL_SMTP_SSL_ENABLE.toString(),
                        smtpMailProp.isSmtpSslEnable().toString());
                super.setProperty(JavaMailProp.MAIL_DEBUG.toString(),
                        smtpMailProp.isDebugEnable().toString());
                super.setProperty(JavaMailProp.MAIL_SMTP_STARTTLS_ENABLE.toString(),
                        smtpMailProp.isSmtpStarttlsEnable().toString());
                super.setProperty(JavaMailProp.MAIL_TRANSPORT_PROTOCOL.toString(),
                        smtpMailProp.getTransportProtocol());
                super.setProperty(JavaMailProp.MAIL_SMTP_AUTH.toString(),
                        smtpMailProp.isSmtpAuth().toString());
                super.setProperty(JavaMailProp.MAIL_USER.toString(),
                        smtpMailProp.getUser());
                super.setProperty(JavaMailProp.MAIL_PASSWORD.toString(),
                        smtpMailProp.getPassword());
            }

        };
    }

}
