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
package org.geosdi.geoplatform.gui.client.widget.member;

import com.extjs.gxt.ui.client.Registry;
import com.extjs.gxt.ui.client.event.BaseEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.form.CheckBox;
import com.extjs.gxt.ui.client.widget.form.FormPanel;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.HasRpcToken;
import com.google.gwt.user.client.rpc.RpcTokenException;
import com.google.gwt.user.client.rpc.XsrfToken;
import com.google.gwt.user.client.rpc.XsrfTokenServiceAsync;
import org.geosdi.geoplatform.gui.client.i18n.UserModuleConstants;
import org.geosdi.geoplatform.gui.client.i18n.windows.WindowsConstants;
import org.geosdi.geoplatform.gui.client.service.UserRemote;
import org.geosdi.geoplatform.gui.client.service.UserRemoteAsync;
import org.geosdi.geoplatform.gui.client.widget.SearchStatus;
import org.geosdi.geoplatform.gui.client.widget.users.member.UserOptionsMember;
import org.geosdi.geoplatform.gui.configuration.message.GeoPlatformMessage;
import org.geosdi.geoplatform.gui.configuration.users.options.member.UserSessionEnum;
import org.geosdi.geoplatform.gui.impl.map.event.GPLoginEvent;
import org.geosdi.geoplatform.gui.impl.users.options.UserTreeOptions;
import org.geosdi.geoplatform.gui.impl.view.LayoutManager;
import org.geosdi.geoplatform.gui.puregwt.GPHandlerManager;
import org.geosdi.geoplatform.gui.server.gwt.UserRemoteImpl;
import org.geosdi.geoplatform.gui.service.gwt.xsrf.GPXsrfTokenService;
import org.geosdi.geoplatform.gui.utility.GPSessionTimeout;

/**
 *
 * @author Vincenzo Monteverde <vincenzo.monteverde@geosdi.org>
 */
public class UserOptionsMemberView extends UserOptionsMember {

    private static final XsrfTokenServiceAsync xsrf = GPXsrfTokenService.Util.getInstance();
    private static final UserRemoteAsync userRemote = UserRemote.Util.getInstance();
    //
    private CheckBox startupStrategyCheckBox;

    public UserOptionsMemberView() {
        super(UserModuleConstants.INSTANCE.UserOptionsMemberView_titleText());
    }

    @Override
    protected void creteLayoutData(ContentPanel panel) {
//        VBoxLayoutData layoutData = new VBoxLayoutData(new Margins(0, 0, 5, 0));
//        panel.add(new Label("Set GRATICULA"), layoutData);
//        panel.add(new Label("Set SCALE"), layoutData);
        UserTreeOptions userTreeOptions = Registry.get(
                UserSessionEnum.USER_TREE_OPTIONS.name());
        FormPanel formPanel = new FormPanel();
        startupStrategyCheckBox = new CheckBox();
        startupStrategyCheckBox.setFieldLabel(UserModuleConstants.INSTANCE.
                UserOptionsMemberView_startupStrategyLabelText());
        startupStrategyCheckBox.addListener(Events.Change,
                new Listener<BaseEvent>() {

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
        super.saveButton.disable();
        panel.add(formPanel);
    }

    @Override
    public void saveOptions() {
        this.startupStrategyCheckBox.getValue();
        final UserTreeOptions userTreeOptions = Registry.get(
                UserSessionEnum.USER_TREE_OPTIONS.name());
        userTreeOptions.setLoadExpandedFolders(
                startupStrategyCheckBox.getValue());

        xsrf.getNewXsrfToken(new AsyncCallback<XsrfToken>() {

            @Override
            public void onFailure(Throwable caught) {
                try {
                    throw caught;
                } catch (RpcTokenException e) {
                    // Can be thrown for several reasons:
                    //   - duplicate session cookie, which may be a sign of a cookie
                    //     overwrite attack
                    //   - XSRF token cannot be generated because session cookie isn't
                    //     present
                } catch (Throwable e) {
                    // unexpected
                }
            }

            @Override
            public void onSuccess(XsrfToken token) {
                ((HasRpcToken) userRemote).setRpcToken(token);
                userRemote.updateUserTreeOptions(userTreeOptions,
                        new AsyncCallback<Long>() {

                            @Override
                            public void onFailure(Throwable caught) {
                                if (caught.getCause() instanceof GPSessionTimeout) {
                                    GPHandlerManager.fireEvent(new GPLoginEvent(
                                                    null));
                                } else {
                                    GeoPlatformMessage.errorMessage(
                                            WindowsConstants.INSTANCE.
                                            errorSavingTitleText(),
                                            WindowsConstants.INSTANCE.errorMakingConnectionBodyText());
                                    LayoutManager.getInstance().getStatusMap().setStatus(
                                            UserModuleConstants.INSTANCE.
                                            UserOptionsMemberView_statusErrorSavingText(),
                                            SearchStatus.EnumSearchStatus.STATUS_NO_SEARCH.toString());
                                    System.out.println(
                                            "Error saving view options: " + caught.toString()
                                            + " data: " + caught.getMessage());
                                }
                            }

                            @Override
                            public void onSuccess(Long result) {
                                UserOptionsMemberView.super.saveButton.disable();

                                LayoutManager.getInstance().getStatusMap().setStatus(
                                        UserModuleConstants.INSTANCE.
                                        UserOptionsMemberView_statusSaveOptionSuccesfullyText(),
                                        SearchStatus.EnumSearchStatus.STATUS_SEARCH.toString());
                            }
                        });
            }
        });

    }

    @Override
    protected void manageUserData() {
    }
}
