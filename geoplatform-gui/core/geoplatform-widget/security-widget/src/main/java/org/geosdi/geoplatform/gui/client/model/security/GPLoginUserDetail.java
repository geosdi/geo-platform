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
    private Map<String, Boolean> componentPermission;

    @Override
    public String getUserName() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void setUserName(String userName) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void setComponentPermission(Map<String, Boolean> componentPermission) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Map<String, Boolean> geoComponentPermission() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean haveComponentPermission(String idComponent) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
