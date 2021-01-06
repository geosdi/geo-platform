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
package org.geosdi.geoplatform.support.xmpp.configuration.properties;

import com.google.common.base.Preconditions;
import org.geosdi.geoplatform.support.xmpp.configuration.auth.XMPPAuth;
import org.jivesoftware.smack.ConnectionConfiguration;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class GPXMPPProperties implements XMPPBaseProperties {

    private String xmppHost;
    private Integer xmppPort;
    private XMPPAuth xmppAuth;
    private Boolean compressionEnabled;
    private ConnectionConfiguration.SecurityMode securityMode;
    private String serviceName;
    private Boolean debuggerEnabled;

    @Override
    public void setXMPPHost(String theXMPPHost) {
        this.xmppHost = theXMPPHost;
    }

    @Override
    public String getXMPPHost() {
        return this.xmppHost;
    }

    @Override
    public void setXMPPPort(Integer theXMPPPort) {
        this.xmppPort = theXMPPPort;
    }

    @Override
    public Integer getXMPPPort() {
        return this.xmppPort;
    }

    @Override
    public void setXMPPAuth(XMPPAuth theXMPPAuth) {
        this.xmppAuth = theXMPPAuth;
    }

    @Override
    public XMPPAuth getXMPPAuth() {
        return this.xmppAuth;
    }

    @Override
    public void setCompressionEnabled(Boolean compressionEnabled) {
        this.compressionEnabled = compressionEnabled;
    }

    @Override
    public void setSecurityMode(ConnectionConfiguration.SecurityMode securityMode) {
        this.securityMode = securityMode;
    }

    @Override
    public Boolean isCompressionEnabled() {
        return this.compressionEnabled;
    }

    @Override
    public ConnectionConfiguration.SecurityMode getSecurityMode() {
        return this.securityMode;
    }

    @Override
    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    @Override
    public String getServiceName() {
        return this.serviceName;
    }

    @Override
    public void setDebuggerEnabled(Boolean debuggerEnabled) {
        this.debuggerEnabled = debuggerEnabled;
    }

    @Override
    public Boolean isDebuggerEnabled() {
        return this.debuggerEnabled;
    }

    @Override
    public final void afterPropertiesSet() throws Exception {
        Preconditions.checkNotNull(this.xmppHost,
                "XMPP Host must not be null.");
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + " {" + "xmppHost = " + xmppHost
                + ", xmppPort = " + xmppPort
                + ", xmppAuth = " + xmppAuth
                + ", compressionEnabled = " + compressionEnabled
                + ", securityMode = " + securityMode
                + ", serviceName = " + serviceName
                + ", debuggerEnabled = " + debuggerEnabled + '}';
    }

}
