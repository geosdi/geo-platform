package org.geosdi.geoplatform.gui.client;

import com.google.gwt.core.client.EntryPoint;
import org.geosdi.geoplatform.configurator.gui.GuiComponentIDs;
import org.geosdi.geoplatform.gui.action.ToolbarAction;
import org.geosdi.geoplatform.gui.action.ToolbarActionCreator;
import org.geosdi.geoplatform.gui.action.ToolbarActionRegistar;
import org.geosdi.geoplatform.gui.client.action.NotificationCenterMenuAction;
import org.geosdi.geoplatform.gui.impl.map.GeoPlatformMap;

/**
 * @author Nazzareno Sileno - CNR IMAA geoSDI Group
 * @email nazzareno.sileno@geosdi.org
 */
public class NotificationWidgetUI implements EntryPoint {

    @Override
    public void onModuleLoad() {
        ToolbarActionRegistar.put(GuiComponentIDs.NOTIFICATION_MENU,
                new ToolbarActionCreator() {
                    @Override
                    public ToolbarAction createActionTool(GeoPlatformMap map) {
                        System.out.println("Notification create Menu action");
                        return new NotificationCenterMenuAction();
                    }
                });
    }
}
