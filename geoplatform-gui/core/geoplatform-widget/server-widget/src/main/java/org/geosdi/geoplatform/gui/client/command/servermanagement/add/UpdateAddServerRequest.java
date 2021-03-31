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
package org.geosdi.geoplatform.gui.client.command.servermanagement.add;

import org.geosdi.geoplatform.gui.command.api.GPCommandRequest;

/**
 *
 * @author Vito Salvia - CNR IMAA geoSDI Group
 * @email vito.salvia@gmail.com
 */
public class UpdateAddServerRequest implements GPCommandRequest {

    private static final long serialVersionUID = -9033692443947602480L;
    //
    private  Long serverID;
    private  String alias;
    private  String url;
    private  String organitation;
    private  String username;
    private  String password;
    private  boolean proxy;

    public UpdateAddServerRequest() { }

    /**
     *
     * @return {@link Long}
     */
    public Long getServerID() {
        return serverID;
    }

    /**
     *
     * @param serverID
     */
    public void setServerID(Long serverID) {
        this.serverID = serverID;
    }

    /**
     *
     * @return {@link String}
     */
    public String getAlias() {
        return alias;
    }

    /**
     *
     * @param alias
     */
    public void setAlias(String alias) {
        this.alias = alias;
    }

    /**
     *
     * @return {@link String}
     */
    public String getUrl() {
        return url;
    }

    /**
     *
     * @param url
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     *
     * @return {@link String}
     */
    public String getOrganitation() {
        return organitation;
    }

    /**
     *
     * @return {@link String}
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
     * @return {@link String}
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

    /**
     *
     * @param organitation
     */
    public void setOrganitation(String organitation) {
        this.organitation = organitation;
    }

    @Override
    public String toString() {
        return "UpdateAddServerRequest{" + "serverID=" + serverID + ", alias='" + alias + '\'' + ", url='" + url + '\'' + ", organitation='" + organitation + '\'' + ", username='" + username + '\'' + ", password='" + password + '\'' + ", proxy=" + proxy + '}';
    }

    @Override
    public String getCommandName() {
        return "command.servermanagement.UpdateAddServerCommand";
    }

}
