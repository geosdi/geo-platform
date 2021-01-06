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
package org.geosdi.geoplatform.support.mail.spring.configuration.properties;

import net.jcip.annotations.Immutable;
import org.geosdi.geoplatform.support.mail.configuration.properties.SMTPMailProperties;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import static com.google.common.base.Preconditions.checkArgument;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@Immutable
@Component(value = "smtpMailSpringProperties")
class SMTPMailSpringProperties implements SMTPMailProperties {

    @Value(value = "gpMailConfigurator{gp.mail.username:@null}")
    private String user;
    @Value(value = "gpMailConfigurator{gp.mail.password:@null}")
    private String password;
    @Value(value = "gpMailConfigurator{gp.mail.host:@null}")
    private String host;
    @Value(value = "gpMailConfigurator{gp.mail.port:@null}")
    private Integer port;
    @Value(value = "gpMailConfigurator{gp.mail.smtp.ssl.enable:@null}")
    private Boolean smtpSsl;
    @Value(value = "gpMailConfigurator{gp.mail.debug:@null}")
    private Boolean debug;
    @Value(value = "gpMailConfigurator{gp.mail.smtp.starttls.enable:@null}")
    private Boolean smtpStarttls;
    @Value(value = "gpMailConfigurator{gp.mail.smtp.starttls.enable:@null}")
    private Boolean smtpAuth;

    @Override
    public String getSocketFactoryClass() {
        return "javax.net.ssl.SSLSocketFactory";
    }

    @Override
    public String getHost() {
        return this.host;
    }

    @Override
    public Integer getPort() {
        return this.port = (this.port != null) ? this.port : -1;
    }

    @Override
    public String getTransportProtocol() {
        return "smtp";
    }

    @Override
    public Boolean isDebugEnable() {
        return this.debug = (this.debug != null) ? this.debug : Boolean.FALSE;
    }

    @Override
    public String getUser() {
        return this.user;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public Boolean isSmtpSslEnable() {
        return this.smtpSsl = (this.smtpSsl != null) ? this.smtpSsl : Boolean.TRUE;
    }

    @Override
    public Boolean isSmtpStarttlsEnable() {
        return this.smtpStarttls = (this.smtpStarttls != null) ? this.smtpStarttls : Boolean.TRUE;
    }

    @Override
    public Boolean isSmtpAuth() {
        return this.smtpAuth = (this.smtpAuth != null) ? this.smtpAuth : Boolean.TRUE;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        checkArgument((host != null) && !(host.trim().isEmpty()), "The Parameter host must not be null or an empty string.");
        checkArgument((user != null) && !(user.trim().isEmpty()), "The Parameter user must not be null or an empty string.");
        checkArgument((password != null) && !(password.trim().isEmpty()), "The Parameter password must not be null or an empty string.");
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "{ "
                + "user = " + user
                + ", password = " + password
                + ", host = " + host + ", port = " + port
                + ", smtpSsl = " + smtpSsl + ", debug = " + debug
                + ", smtpStarttls = " + smtpStarttls
                + ", smtpAuth = " + smtpAuth + '}';
    }
}