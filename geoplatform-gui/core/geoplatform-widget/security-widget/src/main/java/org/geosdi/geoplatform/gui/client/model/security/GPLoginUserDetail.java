/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.geosdi.geoplatform.gui.client.model.security;

import java.util.Map;
import org.geosdi.geoplatform.gui.global.security.IGPUserDetail;

/**
 *
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email  giuseppe.lascaleia@geosdi.org
 */
public class GPLoginUserDetail implements IGPUserDetail {

    private static final long serialVersionUID = -7265573728577702116L;
    private String username;
    private String name;
    private String email;
    private Map<String, Boolean> componentPermission;
    private boolean viewer;

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
    public void setComponentPermission(Map<String, Boolean> componentPermission) {
        this.componentPermission = componentPermission;
    }

    @Override
    public Map<String, Boolean> getComponentPermission() {
        return componentPermission;
    }

    @Override
    public boolean hasComponentPermission(String idComponent) {
        return this.componentPermission != null ? this.componentPermission.containsKey(
                idComponent) : true;
    }

    /**
     * @return the viewer
     */
    @Override
    public boolean isViewer() {
        return viewer;
    }

    /**
     * @param viewer the viewer to set
     */
    @Override
    public void setViewer(boolean viewer) {
        this.viewer = viewer;
    }
}
