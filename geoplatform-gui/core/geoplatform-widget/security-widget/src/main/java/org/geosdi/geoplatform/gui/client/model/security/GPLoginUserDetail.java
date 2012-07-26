/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.geosdi.geoplatform.gui.client.model.security;

import java.util.Map;
import org.geosdi.geoplatform.gui.configuration.map.client.GPClientViewport;
import org.geosdi.geoplatform.gui.global.security.IGPAccountDetail;
import org.geosdi.geoplatform.gui.global.security.IGPUserSimpleDetail;

/**
 *
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class GPLoginUserDetail implements IGPUserSimpleDetail, IGPAccountDetail {

    private static final long serialVersionUID = -7265573728577702116L;
    //
    private String username;
    private String name;
    private String email;
    private String authkey;
    private String hostXmppServer;
    private String baseLayer;
    private GPClientViewport viewport;
    private Map<String, Boolean> componentPermission;

    public GPLoginUserDetail() {
    }

    @Override
    public String getStringID() {
        return this.getUsername();
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
    public String getAuthkey() {
        return authkey;
    }

    public void setAuthkey(String authkey) {
        this.authkey = authkey;
    }

    /**
     * @return the name
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    @Override
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the email
     */
    @Override
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
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
}
