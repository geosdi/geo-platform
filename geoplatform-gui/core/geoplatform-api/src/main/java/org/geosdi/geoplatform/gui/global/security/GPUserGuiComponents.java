/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.geosdi.geoplatform.gui.global.security;

/**
 *
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email  giuseppe.lascaleia@geosdi.org
 */
public class GPUserGuiComponents {

    private static GPUserGuiComponents INSTANCE = new GPUserGuiComponents();
    private IGPUserDetail userDetail;

    private GPUserGuiComponents() {
    }

    public static GPUserGuiComponents getInstance() {
        return INSTANCE;
    }

    /**
     * @return the userDetail
     */
    public IGPUserDetail getUserDetail() {
        return userDetail;
    }

    /**
     * @param userDetail the userDetail to set
     */
    public void setUserDetail(IGPUserDetail userDetail) {
        this.userDetail = userDetail;
    }

    /**
     * 
     * @return UserName
     */
    public String getUserName() {
        return this.userDetail.getUsername();
    }

    /**
     * @see IGPUserDetail#hasComponentPermission(java.lang.String)
     */
    public Boolean hasComponentPermission(String componentID) {
        return userDetail.hasComponentPermission(componentID);
    }
}
