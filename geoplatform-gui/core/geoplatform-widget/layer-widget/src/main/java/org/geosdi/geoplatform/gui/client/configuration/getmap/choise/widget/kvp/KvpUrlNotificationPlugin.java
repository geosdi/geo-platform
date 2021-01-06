/**
 *
 *    geo-platform
 *    Rich webgis framework
 *    http://geo-platform.org
 *   ====================================================================
 *
 *   Copyright (C) 2008-2021 geoSDI Group (CNR IMAA - Potenza - ITALY).
 *
 *   This program is free software: you can redistribute it and/or modify it
 *   under the terms of the GNU General Public License as published by
 *   the Free Software Foundation, either version 3 of the License, or
 *   (at your option) any later version. This program is distributed in the
 *   hope that it will be useful, but WITHOUT ANY WARRANTY; without
 *   even the implied warranty of MERCHANTABILITY or FITNESS FOR
 *   A PARTICULAR PURPOSE. See the GNU General Public License
 *   for more details. You should have received a copy of the GNU General
 *   Public License along with this program. If not, see http://www.gnu.org/licenses/
 *
 *   ====================================================================
 *
 *   Linking this library statically or dynamically with other modules is
 *   making a combined work based on this library. Thus, the terms and
 *   conditions of the GNU General Public License cover the whole combination.
 *
 *   As a special exception, the copyright holders of this library give you permission
 *   to link this library with independent modules to produce an executable, regardless
 *   of the license terms of these independent modules, and to copy and distribute
 *   the resulting executable under terms of your choice, provided that you also meet,
 *   for each linked independent module, the terms and conditions of the license of
 *   that module. An independent module is a module which is not derived from or
 *   based on this library. If you modify this library, you may extend this exception
 *   to your version of the library, but you are not obligated to do so. If you do not
 *   wish to do so, delete this exception statement from your version.
 */
package org.geosdi.geoplatform.gui.client.configuration.getmap.choise.widget.kvp;

import com.extjs.gxt.ui.client.core.El;
import com.extjs.gxt.ui.client.core.XDOM;
import com.extjs.gxt.ui.client.event.ComponentEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.widget.Component;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.Element;
import org.geosdi.geoplatform.gui.puregwt.GPEventBus;
import org.geosdi.geoplatform.gui.puregwt.GPEventBusImpl;

/**
 *
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class KvpUrlNotificationPlugin implements NotificationPlugin {

    private static final GPEventBus bus = new GPEventBusImpl();
    //
    private Element notificationElement;
    private El elem;

    public KvpUrlNotificationPlugin() {
        addComponentPluginHandler();
    }

    @Override
    public void init(final Component component) {
        component.addListener(Events.Render, new Listener<ComponentEvent>() {

            @Override
            public void handleEvent(ComponentEvent be) {
                elem = component.el().findParent(".x-form-element", 3);
            }

        });
    }

    @Override
    public void notifyMessage(String message) {
        if (notificationElement != null) {
            elem.removeChild(notificationElement);
        }

        notificationElement = XDOM.create(
                "<div style='color: #615f5f;padding: 1 0 2 0px;'>" + message + "</div>");
        // should style in external CSS  rather than directly  
        elem.appendChild(notificationElement);
    }

    @Override
    public void resetPlugin() {
        if (notificationElement != null) {
            elem.removeChild(notificationElement);
            notificationElement = null;
        }
    }

    @Override
    public final HandlerRegistration addComponentPluginHandler() {
        return bus.addHandler(TYPE, this);
    }

    public static void fireComponentPluginEvent(ComponentPluginEvent event) {
        bus.fireEvent(event);
    }

}
