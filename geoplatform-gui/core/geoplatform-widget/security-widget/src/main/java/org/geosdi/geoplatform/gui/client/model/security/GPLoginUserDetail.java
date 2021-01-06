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
package org.geosdi.geoplatform.gui.client.model.security;

import java.util.List;
import java.util.Map;
import org.geosdi.geoplatform.gui.configuration.map.client.GPClientViewport;
import org.geosdi.geoplatform.gui.global.security.IGPAccountDetail;
import org.geosdi.geoplatform.gui.global.security.IGPTreeOptions;
import org.geosdi.geoplatform.gui.global.security.IGPUserSimpleDetail;
import org.geosdi.geoplatform.gui.model.message.IGPClientMessage;
import org.geosdi.geoplatform.gui.shared.GPTrustedLevel;

/**
 *
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 *
 * @author Vincenzo Monteverde <vincenzo.monteverde@geosdi.org>
 */
public class GPLoginUserDetail implements IGPUserSimpleDetail, IGPAccountDetail {

    private static final long serialVersionUID = -7265573728577702116L;
    //
    private long id;
    private String username;
    private String name;
    private String email;
    private String organization;
    private String authkey;
    private String hostXmppServer;
    private String baseLayer;
    private String role;
    private GPTrustedLevel trustedLevel;
    private GPClientViewport viewport;
    private Map<String, Boolean> componentPermission;
    private IGPTreeOptions treeOptions;
    private List<IGPClientMessage> unreadMessages;

    public GPLoginUserDetail() {
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String getOrganization() {
        return this.organization;
    }

    @Override
    public void setOrganization(String organization) {
        this.organization = organization;
    }

    /**
     * @return the authority
     */
    @Override
    public String getAuthority() {
        return this.role;
    }

    /**
     * @param role the authority to set
     */
    @Override
    public void setAuthority(String role) {
        this.role = role;
    }

    @Override
    public String getAuthkey() {
        return authkey;
    }

    public void setAuthkey(String authkey) {
        this.authkey = authkey;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getEmail() {
        return email;
    }

    @Override
    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String getHostXmppServer() {
        return hostXmppServer;
    }

    public void setHostXmppServer(String hostXmppServer) {
        this.hostXmppServer = hostXmppServer;
    }

    @Override
    public void setComponentPermission(Map<String, Boolean> componentPermission) {
        this.componentPermission = componentPermission;
    }

    @Override
    public Map<String, Boolean> getComponentPermission() {
        return componentPermission;
    }

    @Override
    public Boolean hasComponentPermission(String componentID) {
        return componentPermission.get(componentID);
    }

    @Override
    public void setBaseLayer(String baseLayer) {
        this.baseLayer = baseLayer;
    }

    @Override
    public String getBaseLayer() {
        return baseLayer;
    }

    public void setViewport(GPClientViewport viewport) {
        this.viewport = viewport;
    }

    @Override
    public GPClientViewport getViewport() {
        return this.viewport;
    }

    @Override
    public IGPTreeOptions getTreeOptions() {
        return this.treeOptions;
    }

    public void setTreeOptions(IGPTreeOptions treeOptions) {
        this.treeOptions = treeOptions;
    }

    @Override
    public Long getId() {
        return this.id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public GPTrustedLevel getTrustedLevel() {
        return this.trustedLevel;
    }

    @Override
    public void setTrustedLevel(GPTrustedLevel trustedLevel) {
        this.trustedLevel = trustedLevel;
    }

    public void setUnreadMessages(List<IGPClientMessage> unreadMessages) {
        this.unreadMessages = unreadMessages;
    }

    @Override
    public List<IGPClientMessage> getUnreadMessages() {
        return this.unreadMessages;
    }
}
