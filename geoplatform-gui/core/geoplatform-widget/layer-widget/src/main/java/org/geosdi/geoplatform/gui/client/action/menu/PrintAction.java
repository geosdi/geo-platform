/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.geosdi.geoplatform.gui.client.action.menu;

import com.extjs.gxt.ui.client.event.MenuEvent;
import com.extjs.gxt.ui.client.mvc.Dispatcher;
import org.geosdi.geoplatform.gui.action.menu.MenuBaseAction;
import org.geosdi.geoplatform.gui.view.event.GeoPlatformEvents;

/**
 *
 * @author Francesco Izzi - CNR IMAA geoSDI Group
 * @mail francesco.izzi@geosdi.org
 */
public class PrintAction extends MenuBaseAction {

    public PrintAction() {
        super("Print", null);
    }

    @Override
    public void componentSelected(MenuEvent ce) {
        Dispatcher.forwardEvent(GeoPlatformEvents.SHOW_PRINTING_WIDGET);
    }
}
