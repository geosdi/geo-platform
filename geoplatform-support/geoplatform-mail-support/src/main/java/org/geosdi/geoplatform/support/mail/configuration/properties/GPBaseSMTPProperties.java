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
package org.geosdi.geoplatform.support.mail.configuration.properties;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 *
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class GPBaseSMTPProperties implements SMTPMailProperties {

    private String user;
    private String password;
    private String host;
    private Integer port;
    private Boolean smtpSsl;
    private Boolean debug;
    private Boolean smtpStarttls;
    private Boolean smtpAuth;

    /**
     * @param user the user to set
     */
    public void setUser(String user) {
        this.user = user;
    }

    @Override
    public String getUser() {
        return this.user;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    /**
     * @param host the host to set
     */
    public void setHost(String host) {
        this.host = host;
    }

    @Override
    public String getHost() {
        return this.host;
    }

    /**
     * @param port the port to set
     */
    public void setPort(Integer port) {
        this.port = port;
    }

    @Override
    public Integer getPort() {
        return (this.port != null) ? this.port : -1;
    }

    /**
     * @param smtpSsl the smtpSsl to set
     */
    public void setSmtpSsl(Boolean smtpSsl) {
        this.smtpSsl = smtpSsl;
    }

    @Override
    public Boolean isSmtpSslEnable() {
        return this.smtpSsl;
    }

    /**
     * @param debug the debug to set
     */
    public void setDebug(Boolean debug) {
        this.debug = debug;
    }

    @Override
    public Boolean isDebugEnable() {
        return this.debug;
    }

    /**
     * @param smtpStarttls the smtpStarttls to set
     */
    public void setSmtpStarttls(Boolean smtpStarttls) {
        this.smtpStarttls = smtpStarttls;
    }

    @Override
    public Boolean isSmtpStarttlsEnable() {
        return this.smtpStarttls;
    }

    /**
     * @param smtpAuth the smtpAuth to set
     */
    public void setSmtpAuth(Boolean smtpAuth) {
        this.smtpAuth = smtpAuth;
    }

    @Override
    public Boolean isSmtpAuth() {
        return this.smtpAuth;
    }

    @Override
    public String getSocketFactoryClass() {
        return "javax.net.ssl.SSLSocketFactory";
    }

    @Override
    public String getTransportProtocol() {
        return "smtp";
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        checkNotNull(host, "Host must not be bull");
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 13 * hash + (this.user != null ? this.user.hashCode() : 0);
        hash = 13 * hash + (this.host != null ? this.host.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final GPBaseSMTPProperties other = (GPBaseSMTPProperties) obj;
        if ((this.user == null) ? (other.user != null) : !this.user.equals(other.user)) {
            return false;
        }
        return !((this.host == null) ? (other.host != null) : !this.host.equals(other.host));
    }

    @Override
    public String toString() {
        return "GPBaseSMTPProperties {" + "user = " + user
                + ", password = " + password + ", host = " + host
                + ", port = " + port + ", smtpSsl = " + smtpSsl
                + ", debug = " + debug + ", smtpStarttls = " + smtpStarttls
                + ", smtpAuth = " + smtpAuth
                + ", socketFactoryClass = " + getSocketFactoryClass()
                + ", transportProtocol = " + getTransportProtocol() + '}';
    }

}
