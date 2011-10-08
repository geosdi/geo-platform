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

    public boolean hasComponentPermission(String idComponent);

    public boolean isViewer();

    public void setViewer(boolean viewer);
}
