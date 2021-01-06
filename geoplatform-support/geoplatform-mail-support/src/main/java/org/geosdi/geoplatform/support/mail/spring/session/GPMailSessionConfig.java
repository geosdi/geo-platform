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
package org.geosdi.geoplatform.support.mail.spring.session;

import org.geosdi.geoplatform.support.mail.configuration.properties.JavaMailProp;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import java.util.Properties;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@Configuration
class GPMailSessionConfig {

    /**
     * @param gpJavaMailProperties
     * @return {@link Session}
     */
    @Bean(name = "gpMailSpringSession")
    public static Session gpMailSpringSession(@Qualifier(value = "gpJavaMailProperties") Properties gpJavaMailProperties) {
        return Session.getInstance(gpJavaMailProperties, new GPMailAuthenticatorSupport(gpJavaMailProperties
                .getProperty(JavaMailProp.MAIL_USER.toString()), gpJavaMailProperties
                .getProperty(JavaMailProp.MAIL_PASSWORD.toString())));
    }

    /**
     *
     */
    static class GPMailAuthenticatorSupport extends Authenticator {

        private final String userName;
        private final String password;

        public GPMailAuthenticatorSupport(String theUserName, String thePassword) {
            this.userName = theUserName;
            this.password = thePassword;
        }

        /**
         * Called when password authentication is needed.  Subclasses should
         * override the default implementation, which returns null. <p>
         * <p>
         * Note that if this method uses a dialog to prompt the user for this
         * information, the dialog needs to block until the user supplies the
         * information.  This method can not simply return after showing the
         * dialog.
         *
         * @return The PasswordAuthentication collected from the
         * user, or null if none is provided.
         */
        @Override
        protected PasswordAuthentication getPasswordAuthentication() {
            return new PasswordAuthentication(this.userName, this.password);
        }
    }
}
