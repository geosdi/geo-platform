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
package org.geosdi.geoplatform.gui.client.widget.member;

import com.extjs.gxt.ui.client.Registry;
import com.extjs.gxt.ui.client.event.BaseEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.form.CheckBox;
import com.extjs.gxt.ui.client.widget.form.FormPanel;
import com.google.gwt.user.client.rpc.AsyncCallback;
import org.geosdi.geoplatform.gui.client.widget.SearchStatus;
import org.geosdi.geoplatform.gui.client.widget.users.member.UserOptionsMember;
import org.geosdi.geoplatform.gui.configuration.message.GeoPlatformMessage;
import org.geosdi.geoplatform.gui.configuration.users.options.member.UserSessionEnum;
import org.geosdi.geoplatform.gui.impl.map.event.GPLoginEvent;
import org.geosdi.geoplatform.gui.impl.users.options.UserTreeOptions;
import org.geosdi.geoplatform.gui.impl.view.LayoutManager;
import org.geosdi.geoplatform.gui.puregwt.GPHandlerManager;
import org.geosdi.geoplatform.gui.server.gwt.UserRemoteImpl;
import org.geosdi.geoplatform.gui.utility.GPSessionTimeout;

/**
 *
 * @author Vincenzo Monteverde <vincenzo.monteverde@geosdi.org>
 */
public class UserOptionsMemberView extends UserOptionsMember {

    private CheckBox startupStrategyCheckBox;

    public UserOptionsMemberView() {
        super("View");
    }

    @Override
    protected void creteLayoutData(ContentPanel panel) {
//        VBoxLayoutData layoutData = new VBoxLayoutData(new Margins(0, 0, 5, 0));
//        panel.add(new Label("Set GRATICULA"), layoutData);
//        panel.add(new Label("Set SCALE"), layoutData);
        UserTreeOptions userTreeOptions = Registry.get(UserSessionEnum.USER_TREE_OPTIONS.name());
        FormPanel formPanel = new FormPanel();
        startupStrategyCheckBox = new CheckBox();
        startupStrategyCheckBox.setFieldLabel("Load expanded folders at start-up");
        startupStrategyCheckBox.addListener(Events.Change, new Listener<BaseEvent>() {
            @Override
            public void handleEvent(BaseEvent be) {
                UserOptionsMemberView.super.saveButton.enable();
            }
        });
        startupStrategyCheckBox.setValue(userTreeOptions.isLoadExpandedFolders());
        formPanel.add(startupStrategyCheckBox);
        formPanel.setHeaderVisible(Boolean.FALSE);
        formPanel.setBodyBorder(Boolean.FALSE);
        formPanel.setBorders(Boolean.FALSE);
        formPanel.setWidth(400);
        panel.add(formPanel);
    }

    @Override
    public void saveOptions() {
        this.startupStrategyCheckBox.getValue();
        UserTreeOptions userTreeOptions = Registry.get(UserSessionEnum.USER_TREE_OPTIONS.name());
        userTreeOptions.setLoadExpandedFolders(startupStrategyCheckBox.getValue());
        UserRemoteImpl.Util.getInstance().updateUserTreeOptions(userTreeOptions, new AsyncCallback<Long>() {
            @Override
            public void onFailure(Throwable caught) {
                if (caught.getCause() instanceof GPSessionTimeout) {
                    GPHandlerManager.fireEvent(new GPLoginEvent(null));
                } else {
                    GeoPlatformMessage.errorMessage("Error saving",
                            "An error occurred while making the requested connection.\n"
                            + "Verify network connections and try again."
                            + "\nIf the problem persists contact your system administrator.");
                    LayoutManager.getInstance().getStatusMap().setStatus(
                            "Error saving view options.",
                            SearchStatus.EnumSearchStatus.STATUS_NO_SEARCH.toString());
                    System.out.println("Error saving view options: " + caught.toString()
                            + " data: " + caught.getMessage());
                }
            }

            @Override
            public void onSuccess(Long result) {
                LayoutManager.getInstance().getStatusMap().setStatus(
                        "View options saved succesfully.",
                        SearchStatus.EnumSearchStatus.STATUS_SEARCH.toString());
            }
        });


    }

    @Override
    protected void manageUserData() {
    }
}
