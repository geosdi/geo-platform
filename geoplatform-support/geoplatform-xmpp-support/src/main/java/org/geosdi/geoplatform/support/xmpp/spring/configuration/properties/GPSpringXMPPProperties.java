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
package org.geosdi.geoplatform.support.xmpp.spring.configuration.properties;

import com.google.common.base.Preconditions;
import net.jcip.annotations.Immutable;
import org.geosdi.geoplatform.support.xmpp.configuration.auth.XMPPAuth;
import org.geosdi.geoplatform.support.xmpp.configuration.properties.XMPPProperties;
import org.geosdi.geoplatform.support.xmpp.spring.annotation.GPXMPPProp;
import org.jivesoftware.smack.ConnectionConfiguration;
import org.springframework.beans.factory.annotation.Value;

import javax.annotation.Resource;
import javax.inject.Named;

/**
 *
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@Immutable
@GPXMPPProp
@Named(value = "gpSpringXMPPProp")
public class GPSpringXMPPProperties implements XMPPProperties {

    @Value("gpXmppConfigurator{gp.xmpp.host:@null}")
    private String xmppHost;
    @Value("gpXmppConfigurator{gp.xmpp.port:@null}")
    private Integer xmppPort;
    @Resource(name = "gpSpringXMPPAuth")
    private XMPPAuth xmppAuth;
    @Value("gpXmppConfigurator{gp.xmpp.compression.enabled:@null}")
    private Boolean compressionEnabled;
    @Value("gpXmppConfigurator{gp.xmpp.security.mode:@null}")
    private ConnectionConfiguration.SecurityMode securityMode;
    @Value("gpXmppConfigurator{gp.xmpp.service:@null}")
    private String serviceName;
    @Value("gpXmppConfigurator{gp.xmpp.debug.enabled:@null}")
    private Boolean debuggerEnabled;

    @Override
    public String getXMPPHost() {
        return this.xmppHost;
    }

    @Override
    public Integer getXMPPPort() {
        return this.xmppPort = ((this.xmppPort != null) ? this.xmppPort : 5222);
    }

    @Override
    public XMPPAuth getXMPPAuth() {
        return this.xmppAuth;
    }

    @Override
    public Boolean isCompressionEnabled() {
        return this.compressionEnabled = ((this.compressionEnabled != null)
                ? this.compressionEnabled : Boolean.FALSE);
    }

    @Override
    public ConnectionConfiguration.SecurityMode getSecurityMode() {
        return this.securityMode = ((this.securityMode != null)
                ? this.securityMode : ConnectionConfiguration.SecurityMode.ifpossible);
    }

    @Override
    public String getServiceName() {
        return this.serviceName = ((this.serviceName != null)
                ? this.serviceName : "");
    }

    @Override
    public Boolean isDebuggerEnabled() {
        return this.debuggerEnabled = ((this.debuggerEnabled != null)
                ? this.debuggerEnabled : Boolean.FALSE);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        Preconditions.checkNotNull(this.xmppHost, "XMPP HOST must not be null.");
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + " {" + "xmppHost = " + xmppHost
                + ", xmppPort = " + getXMPPPort()
                + ", xmppAuth = " + xmppAuth
                + ", compressionEnabled = " + isCompressionEnabled()
                + ", securityMode = " + getSecurityMode()
                + ", debuggerEnabled = " + isDebuggerEnabled() + '}';
    }

}
