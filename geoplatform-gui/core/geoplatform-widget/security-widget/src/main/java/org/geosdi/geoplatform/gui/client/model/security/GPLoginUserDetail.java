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
    private String userName;
    private String name;
    private String email;
    private Map<String, Boolean> componentPermission;

    public GPLoginUserDetail() {
    }

    @Override
    public String getUserName() {
        return this.userName;
    }

    @Override
    public void setUserName(String userName) {
        this.userName = userName;
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
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
