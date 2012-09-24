package org.geosdi.geoplatform.gui.client;

import com.google.gwt.core.client.EntryPoint;
import org.geosdi.geoplatform.gui.client.config.NotificationGinInjector;

/**
 * @author Nazzareno Sileno - CNR IMAA geoSDI Group
 * @email nazzareno.sileno@geosdi.org
 */
public class NotificationWidgetUI implements EntryPoint {

    @Override
    public void onModuleLoad() {
        NotificationGinInjector.MainInjector.getInstance().getNotificationCenterMenuAction();
//        ToolbarActionRegistar registar = BasicGinInjector.MainInjector.getInstance().getToolbarActionRegistar();
//        registar.put(GuiComponentIDs.NOTIFICATION_MENU,
//                new ToolbarActionCreator() {
//                    @Override
//                    public ToolbarAction createActionTool(GeoPlatformMap map) {
//                        return NotificationGinInjector.MainInjector.getInstance().
//                                getNotificationCenterMenuAction();
//                    }
//                });
    }
}
