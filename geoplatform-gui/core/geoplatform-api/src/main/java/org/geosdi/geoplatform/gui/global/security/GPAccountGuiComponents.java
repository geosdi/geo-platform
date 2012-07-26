/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.geosdi.geoplatform.gui.global.security;

/**
 *
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public final class GPAccountGuiComponents {

    private static GPAccountGuiComponents INSTANCE = new GPAccountGuiComponents();
    private IGPAccountDetail accountDetail;

    private GPAccountGuiComponents() {
    }

    public static GPAccountGuiComponents getInstance() {
        return INSTANCE;
    }

    /**
     * @param accountDetail the accountDetail to set
     */
    public void setAccountDetail(IGPAccountDetail accountDetail) {
        this.accountDetail = accountDetail;
    }

    /**
     *
     * @return StringID
     */
    public String getStringID() {
        return this.accountDetail.getStringID();
    }

    /**
     *
     * @return Organization
     */
    public String getOrganization() {
        return this.accountDetail.getOrganization();
    }

    /**
     * @see IGPAccountDetail#hasComponentPermission(java.lang.String)
     */
    public Boolean hasComponentPermission(String componentID) {
        return accountDetail.hasComponentPermission(componentID);
    }
}
