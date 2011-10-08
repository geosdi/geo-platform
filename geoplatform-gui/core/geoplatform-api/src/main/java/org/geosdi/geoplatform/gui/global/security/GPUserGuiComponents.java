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

    private static GPUserGuiComponents instance = new GPUserGuiComponents();
    private IGPUserDetail userDetail;

    private GPUserGuiComponents() {
    }

    public static GPUserGuiComponents getInstance() {
        return instance;
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
     * 
     * @param idComponent
     * @return 
     */
    public boolean hasComponentPermission(String idComponent) {
        return userDetail.hasComponentPermission(idComponent);
    }

    /**
     * 
     * @param idComponent
     * @return 
     */
    public boolean getPermissionForComponent(String idComponent) {
        return userDetail.getComponentPermission() != null ? userDetail.getComponentPermission().get(
                idComponent) == null ? false : true : false;
    }
}
