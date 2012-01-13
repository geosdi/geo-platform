/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.geosdi.geoplatform.gui.global.security;

import java.util.Map;

/**
 *
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email  giuseppe.lascaleia@geosdi.org
 */
public interface IGPUserDetail extends IGPUserSimpleDetail {

    public void setComponentPermission(Map<String, Boolean> componentPermission);

    public Map<String, Boolean> getComponentPermission();

    /**
     * Check the permission of a GuiComponent via String ID
     * 
     * @param componentID
     * 
     * @return true or false if componentID was set, for enable/disable;
     *         otherwise null i.e. the component will not be visible
     */
    public Boolean hasComponentPermission(String componentID);

    public boolean isViewer(); // TODO DEL

    public void setViewer(boolean viewer); // TODO DEL
}
