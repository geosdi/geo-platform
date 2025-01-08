/*
 *
 *    geo-platform
 *    Rich webgis framework
 *    http://geo-platform.org
 *   ====================================================================
 *
 *   Copyright (C) 2008-2025 geoSDI Group (CNR IMAA - Potenza - ITALY).
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
package org.geosdi.geoplatform.gui.client.action;

import com.extjs.gxt.ui.client.Registry;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.google.gwt.user.client.ui.AbstractImagePrototype;
import org.geosdi.geoplatform.gui.action.ToolbarMapAction;
import org.geosdi.geoplatform.gui.client.BasicWidgetResources;
import org.geosdi.geoplatform.gui.client.command.MarkMessagesAsReadRequest;
import org.geosdi.geoplatform.gui.client.command.MarkMessagesAsReadResponse;
import org.geosdi.geoplatform.gui.client.i18n.NotificationModuleConstants;
import org.geosdi.geoplatform.gui.client.i18n.windows.WindowsConstants;
import org.geosdi.geoplatform.gui.client.widget.NotificationPopupPanel;
import org.geosdi.geoplatform.gui.client.widget.SearchStatus;
import org.geosdi.geoplatform.gui.command.api.ClientCommandDispatcher;
import org.geosdi.geoplatform.gui.command.api.GPClientCommand;
import org.geosdi.geoplatform.gui.configuration.message.GeoPlatformMessage;
import org.geosdi.geoplatform.gui.configuration.users.options.member.UserSessionEnum;
import org.geosdi.geoplatform.gui.global.security.IGPAccountDetail;
import org.geosdi.geoplatform.gui.impl.map.event.GPLoginEvent;
import org.geosdi.geoplatform.gui.impl.view.LayoutManager;
import org.geosdi.geoplatform.gui.model.message.IGPClientMessage;
import org.geosdi.geoplatform.gui.puregwt.GPHandlerManager;
import org.geosdi.geoplatform.gui.utility.GPSessionTimeout;

import java.util.List;

/**
 * @author Nazzareno Sileno - CNR IMAA geoSDI Group
 * @email nazzareno.sileno@geosdi.org
 */
public class NotificationCenterAction extends ToolbarMapAction {

    private final NotificationPopupPanel notificationPopupPanel;

    /**
     * @param notificationPopupPanel
     */
    public NotificationCenterAction(NotificationPopupPanel notificationPopupPanel) {
        super(AbstractImagePrototype.create(BasicWidgetResources.ICONS.info()), NotificationModuleConstants.INSTANCE.NotificationCenterAction_tooltipText());
        this.notificationPopupPanel = notificationPopupPanel;
    }

    @Override
    public void componentSelected(ButtonEvent e) {
        notificationPopupPanel.setPopupPosition(e.getTarget().getAbsoluteLeft() - (170), e.getTarget().getAbsoluteTop() + (20));
        notificationPopupPanel.show();
        IGPAccountDetail accountDetail = Registry.get(UserSessionEnum.ACCOUNT_DETAIL_IN_SESSION.name());
        List<IGPClientMessage> messageList = accountDetail.getUnreadMessages();
        if (messageList != null && !messageList.isEmpty()) {
            final MarkMessagesAsReadRequest markMessagesAsReadRequest = new MarkMessagesAsReadRequest();
            ClientCommandDispatcher.getInstance().execute(new GPClientCommand<MarkMessagesAsReadResponse>() {

                {
                    super.setCommandRequest(markMessagesAsReadRequest);
                }

                /**
                 * @param response
                 */
                @Override
                public void onCommandSuccess(MarkMessagesAsReadResponse response) {
                }

                /**
                 * @param exception
                 */
                @Override
                public void onCommandFailure(Throwable exception) {
                    if (exception.getCause() instanceof GPSessionTimeout) {
                        GPHandlerManager.fireEvent(new GPLoginEvent(null));
                    } else {
                        GeoPlatformMessage.errorMessage(NotificationModuleConstants.INSTANCE.NotificationCenterAction_errorSavingReadMessagesText(),
                                WindowsConstants.INSTANCE.errorMakingConnectionBodyText());
                        LayoutManager.getInstance().getStatusMap().setStatus(NotificationModuleConstants.INSTANCE.NotificationCenterAction_errorSavingReadMessagesText(),
                                SearchStatus.EnumSearchStatus.STATUS_NO_SEARCH.toString());
                        System.out.println("Error Saving the read messages: " + exception.toString() + " data: " + exception.getMessage());
                    }
                }
            });
        }
    }
}
