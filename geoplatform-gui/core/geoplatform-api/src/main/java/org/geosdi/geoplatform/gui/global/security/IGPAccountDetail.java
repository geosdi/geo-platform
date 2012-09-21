/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.geosdi.geoplatform.gui.global.security;

import java.util.Map;
import org.geosdi.geoplatform.gui.configuration.map.client.GPClientViewport;
import org.geosdi.geoplatform.gui.shared.GPTrustedLevel;

/**
 *
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 *
 * @author Vincenzo Monteverde <vincenzo.monteverde@geosdi.org>
 */
public interface IGPAccountDetail {

    String getName();

    String getOrganization();

    String getAuthkey();

    String getHostXmppServer();

    String getBaseLayer();

    GPTrustedLevel getTrustedLevel();

    String getAuthority();

    void setBaseLayer(String baseLayer);

    GPClientViewport getViewport();

    IGPTreeOptions getTreeOptions();

    void setComponentPermission(Map<String, Boolean> componentPermission);

    Map<String, Boolean> getComponentPermission();

    /**
     * Check the permission of a GuiComponent via String ID
     *
     * @param componentID
     *
     * @return true or false if componentID was set, for enable/disable;
     * otherwise null i.e. the component will not be visible
     */
    Boolean hasComponentPermission(String componentID);
}
