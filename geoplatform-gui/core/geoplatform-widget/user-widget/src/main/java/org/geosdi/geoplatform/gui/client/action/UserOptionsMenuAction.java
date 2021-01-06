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
package org.geosdi.geoplatform.gui.client.action;

import com.extjs.gxt.ui.client.Registry;
import com.extjs.gxt.ui.client.event.MenuEvent;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.HasRpcToken;
import com.google.gwt.user.client.rpc.RpcTokenException;
import com.google.gwt.user.client.rpc.XsrfToken;
import com.google.gwt.user.client.ui.AbstractImagePrototype;
import org.geosdi.geoplatform.gui.action.menu.MenuBaseAction;
import org.geosdi.geoplatform.gui.client.BasicWidgetResources;
import org.geosdi.geoplatform.gui.client.i18n.UserModuleConstants;
import org.geosdi.geoplatform.gui.client.i18n.windows.WindowsConstants;
import org.geosdi.geoplatform.gui.client.service.UserRemote;
import org.geosdi.geoplatform.gui.client.service.UserRemoteAsync;
import org.geosdi.geoplatform.gui.client.widget.UserOptionsWidget;
import org.geosdi.geoplatform.gui.configuration.message.GeoPlatformMessage;
import org.geosdi.geoplatform.gui.configuration.users.options.member.UserSessionEnum;
import org.geosdi.geoplatform.gui.global.security.IGPUserManageDetail;

/**
 *
 * @author Vincenzo Monteverde <vincenzo.monteverde@geosdi.org>
 */
public class UserOptionsMenuAction extends MenuBaseAction {

    private static final UserRemoteAsync userRemote = UserRemote.Util.getInstance();
    //
    private final UserOptionsWidget userOptionsWidget = new UserOptionsWidget(
            true);

    public UserOptionsMenuAction() {
        super(UserModuleConstants.INSTANCE.UserOptionsMenuAction_titleText(),
                AbstractImagePrototype.create(BasicWidgetResources.ICONS.gear()));
    }

    @Override
    public void componentSelected(MenuEvent ce) {
        if (Registry.get(
                UserSessionEnum.USER_MANAGE_DETAIL_IN_SESSION.toString()) != null) {
            userOptionsWidget.show();
        } else {
            retrievesUserFromSession();
        }
    }

    /**
     * Retrieves User from Session
     */
    private void retrievesUserFromSession() {
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
                userRemote.getOwnUser(new AsyncCallback<IGPUserManageDetail>() {

                    @Override
                    public void onFailure(Throwable caught) {
                        GeoPlatformMessage.errorMessage(
                                WindowsConstants.INSTANCE.errorTitleText(),
                                caught.getMessage());
                    }

                    @Override
                    public void onSuccess(IGPUserManageDetail user) {
                        Registry.register(
                                UserSessionEnum.USER_MANAGE_DETAIL_IN_SESSION.toString(),
                                user);
                        userOptionsWidget.show();
                    }
                });
            }
        });
    }
}
