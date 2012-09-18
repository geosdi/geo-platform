/*
 *  geo-platform
 *  Rich webgis framework
 *  http://geo-platform.org
 * ====================================================================
 *
 * Copyright (C) 2008-2012 geoSDI Group (CNR IMAA - Potenza - ITALY).
 *
 * This program is free software: you can redistribute it and/or modify it
 * under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version. This program is distributed in the
 * hope that it will be useful, but WITHOUT ANY WARRANTY; without
 * even the implied warranty of MERCHANTABILITY or FITNESS FOR
 * A PARTICULAR PURPOSE. See the GNU General Public License
 * for more details. You should have received a copy of the GNU General
 * Public License along with this program. If not, see http://www.gnu.org/licenses/
 *
 * ====================================================================
 *
 * Linking this library statically or dynamically with other modules is
 * making a combined work based on this library. Thus, the terms and
 * conditions of the GNU General Public License cover the whole combination.
 *
 * As a special exception, the copyright holders of this library give you permission
 * to link this library with independent modules to produce an executable, regardless
 * of the license terms of these independent modules, and to copy and distribute
 * the resulting executable under terms of your choice, provided that you also meet,
 * for each linked independent module, the terms and conditions of the license of
 * that module. An independent module is a module which is not derived from or
 * based on this library. If you modify this library, you may extend this exception
 * to your version of the library, but you are not obligated to do so. If you do not
 * wish to do so, delete this exception statement from your version.
 *
 */
package org.geosdi.geoplatform.gui.client.action;

import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.DecoratedPopupPanel;
import com.google.gwt.user.client.ui.PopupPanel;
import org.geosdi.geoplatform.gui.action.ToolbarMapAction;
import org.geosdi.geoplatform.gui.client.BasicWidgetResources;
import org.geosdi.geoplatform.gui.client.BinderDeckLayout;
import org.geosdi.geoplatform.gui.client.event.EventShowNotification;
import org.geosdi.geoplatform.gui.client.style.CenterStyle;

/**
 * @author Nazzareno Sileno - CNR IMAA geoSDI Group
 * @email nazzareno.sileno@geosdi.org
 */
public class NotificationCenterMenuAction extends ToolbarMapAction {

    private PopupPanel popupPanel = new DecoratedPopupPanel(true);
    private BinderDeckLayout binderDeckLayout = new BinderDeckLayout();
    @UiField
    private CenterStyle style;

    public NotificationCenterMenuAction() {
        super(BasicWidgetResources.ICONS.info(), "Notification Center");
    }

    @Override
    public void componentSelected(ButtonEvent e) {
        EventShowNotification eventHShowPopUp = new EventShowNotification();
        eventHShowPopUp.setAbsoluteLeft(e.getTarget().getAbsoluteLeft());
        eventHShowPopUp.setAbsoluteTop(e.getTarget().getAbsoluteTop());
        eventHShowPopUp.setOffsetHeight(e.getTarget().getOffsetHeight());
        eventHShowPopUp.setOffsetWidth(e.getTarget().getOffsetWidth());
        eventHShowPopUp.setBooleanShow(Boolean.TRUE);
//        RootLayoutPanel.get().add(new BinderDeckLayout());
//        this.binderDeckLayout.setVisible(Boolean.TRUE);
        
//        popupPanel.setStyleName(style.notifypopup());
        
        popupPanel.setPopupPosition(eventHShowPopUp.getAbsoluteLeft() - (170),
                eventHShowPopUp.getAbsoluteTop() + (20));
        popupPanel.add(new BinderDeckLayout().asWidget());
        popupPanel.setPixelSize(402, 302);
        popupPanel.show();
    }
}
