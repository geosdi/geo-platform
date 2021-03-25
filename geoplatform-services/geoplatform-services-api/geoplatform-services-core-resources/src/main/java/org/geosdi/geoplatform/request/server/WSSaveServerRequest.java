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
package org.geosdi.geoplatform.request.server;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 *
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class WSSaveServerRequest implements Serializable {

    private static final long serialVersionUID = -5546322661654536105L;
    //
    private Long id;
    private String aliasServerName;
    private String serverUrl;
    private String organization;
    private String username;
    private String password;
    private boolean proxy;

    public WSSaveServerRequest() {
    }

    public WSSaveServerRequest(Long theId, String theAliasServerName,
            String theServerUrl, String theOrganization, String theUsername, String thePassword, boolean theProxy) {
        this.id = theId;
        this.aliasServerName = theAliasServerName;
        this.serverUrl = theServerUrl;
        this.organization = theOrganization;
        this.username = theUsername;
        this.password = thePassword;
        this.proxy = theProxy;
    }

    public WSSaveServerRequest(Long theId, String theAliasServerName,
            String theServerUrl, String theOrganization) {
        this(theId, theAliasServerName, theServerUrl, theOrganization, null, null, false);
    }

    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return the aliasServerName
     */
    public String getAliasServerName() {
        return aliasServerName;
    }

    /**
     * @param aliasServerName the aliasServerName to set
     */
    public void setAliasServerName(String aliasServerName) {
        this.aliasServerName = aliasServerName;
    }

    /**
     * @return the serverUrl
     */
    public String getServerUrl() {
        return serverUrl;
    }

    /**
     * @param serverUrl the serverUrl to set
     */
    public void setServerUrl(String serverUrl) {
        this.serverUrl = serverUrl;
    }

    /**
     * @return the organization
     */
    public String getOrganization() {
        return organization;
    }

    /**
     * @param organization the organization to set
     */
    public void setOrganization(String organization) {
        this.organization = organization;
    }

    /**
     *
     * @return
     */
    public String getUsername() {
        return username;
    }

    /**
     *
     * @param username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     *
     * @return
     */
    public String getPassword() {
        return password;
    }

    /**
     *
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     *
     * @return
     */
    public boolean isProxy() {
        return proxy;
    }

    /**
     *
     * @param proxy
     */
    public void setProxy(boolean proxy) {
        this.proxy = proxy;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + " {" + "id = " + id 
                + ", aliasServerName = " + aliasServerName 
                + ", serverUrl = " + serverUrl 
                + ", organization = " + organization
                + ", username = " + username
                + ", password = " + password
                + ", proxy = " + proxy
                + '}';
    }

}
