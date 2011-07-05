/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.geosdi.geoplatform.gui.global.security;

import java.io.Serializable;
import java.util.Map;

/**
 *
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 */
public interface IGPUserDetail extends Serializable {

    public String getUserName();

    public void setUserName(String userName);

    public void setComponentPermission(Map<String, Boolean> componentPermission);

    public Map<String, Boolean> geoComponentPermission();

    public boolean haveComponentPermission(String idComponent);
}
