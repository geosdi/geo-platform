/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.geosdi.geoplatform.gui.global.security;

/**
 *
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 *
 * @author Vincenzo Monteverde <vincenzo.monteverde@geosdi.org>
 */
public final class GPAccountLogged {

    private static GPAccountLogged INSTANCE = new GPAccountLogged();
    private IGPAccountDetail accountDetail;

    private GPAccountLogged() {
    }

    public static GPAccountLogged getInstance() {
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
     * @return name
     */
    public String getName() {
        assert (accountDetail != null) : "accountDetail must not be null.";
        return this.accountDetail.getName();
    }

    /**
     *
     * @return Organization
     */
    public String getOrganization() {
        assert (accountDetail != null) : "accountDetail must not be null.";
        return this.accountDetail.getOrganization();
    }

    /**
     * @see IGPAccountDetail#hasComponentPermission(java.lang.String)
     */
    public Boolean hasComponentPermission(String componentID) {
        assert (accountDetail != null) : "accountDetail must not be null.";
        return accountDetail.hasComponentPermission(componentID);
    }
}
