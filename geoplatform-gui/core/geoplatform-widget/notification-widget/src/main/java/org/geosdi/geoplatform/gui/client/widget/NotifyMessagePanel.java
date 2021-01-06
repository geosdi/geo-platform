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
package org.geosdi.geoplatform.gui.client.widget;

import com.extjs.gxt.ui.client.Registry;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.AbstractImagePrototype;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Hyperlink;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import org.geosdi.geoplatform.gui.client.event.BackNotifyEvent;
import org.geosdi.geoplatform.gui.client.event.NextNotifyEvent;
import org.geosdi.geoplatform.gui.configuration.users.options.member.UserSessionEnum;
import org.geosdi.geoplatform.gui.global.security.IGPAccountDetail;
import org.geosdi.geoplatform.gui.model.message.ICommandAction;
import org.geosdi.geoplatform.gui.puregwt.GPHandlerManager;

/**
 * @author Nazzareno Sileno - CNR IMAA geoSDI Group
 * @email nazzareno.sileno@geosdi.org
 */
public class NotifyMessagePanel extends Composite {

    private static BinderOtherPanelUiBinder uiBinder = GWT.create(BinderOtherPanelUiBinder.class);

    interface BinderOtherPanelUiBinder extends
            UiBinder<Widget, NotifyMessagePanel> {
    }
    @UiField
    VerticalPanel centerPanel;
    @UiField
    Hyperlink homeLabel;
    @UiField
    Hyperlink backLabel;
    SingleNotify singleComponent;

    public NotifyMessagePanel(final SingleNotify singleComponent) {
        this.singleComponent = singleComponent;
        initWidget(uiBinder.createAndBindUi(this));
        VerticalPanel bodyPanel = new VerticalPanel();
        bodyPanel.setPixelSize(400, 250);
        bodyPanel.add(this.singleComponent.getLabelTextNews());
        final ICommandAction commandAction = this.singleComponent.getCommandAction();
        if (commandAction != null) {
            AbstractImagePrototype image = AbstractImagePrototype.create(commandAction.getCommandImageResource());
            Button button = new Button("", image);
            button.setSize(commandAction.getCommandImageResource().getWidth(),
                    commandAction.getCommandImageResource().getHeight());
            button.addSelectionListener(new SelectionListener<ButtonEvent>() {
                @Override
                public void componentSelected(ButtonEvent ce) {
                    commandAction.setCommandProperties(singleComponent.getMessage().getCommandProperties());
                    SelectionListener<ButtonEvent> listener = (SelectionListener<ButtonEvent>) commandAction;
                    listener.componentSelected(ce);
                }
            });
            bodyPanel.add(button);
        } else {
            bodyPanel.add(this.singleComponent.getImageNotify());
        }
        centerPanel.add(bodyPanel);
    }

    @UiHandler("homeLabel")
    void onHomeLabelClick(ClickEvent event) {
        BackNotifyEvent backNotifyEvent = new BackNotifyEvent();
        backNotifyEvent.setIndex(0);
        GPHandlerManager.fireEvent(backNotifyEvent);
    }

    @UiHandler("nextLabel")
    void onNextLabelClick(ClickEvent event) {
        IGPAccountDetail accountDetail = Registry.get(UserSessionEnum.ACCOUNT_DETAIL_IN_SESSION.name());
        if ((singleComponent.getIndex() % accountDetail.getUnreadMessages().size()) != 0) {
            NextNotifyEvent nextNotifyEvent = new NextNotifyEvent();
            nextNotifyEvent.setIndex((this.singleComponent.getIndex() + 1));
            GPHandlerManager.fireEvent(nextNotifyEvent);
        }
    }

    @UiHandler("backLabel")
    void onBackLabelClick(ClickEvent event) {
        BackNotifyEvent backNotifyEvent = new BackNotifyEvent();
        backNotifyEvent.setIndex((this.singleComponent.getIndex() - 1));
        GPHandlerManager.fireEvent(backNotifyEvent);
    }
}
