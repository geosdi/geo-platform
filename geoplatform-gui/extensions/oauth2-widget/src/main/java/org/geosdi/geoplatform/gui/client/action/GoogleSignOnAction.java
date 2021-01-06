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

import com.extjs.gxt.ui.client.event.MenuEvent;
import com.google.api.gwt.oauth2.client.Auth;
import com.google.api.gwt.oauth2.client.AuthRequest;
import com.google.api.gwt.oauth2.client.Callback;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AbstractImagePrototype;
import org.geosdi.geoplatform.gui.action.menu.OAuth2MenuBaseAction;
import org.geosdi.geoplatform.gui.client.OAuth2Resources;
import org.geosdi.geoplatform.gui.client.i18n.OAuth2WidgetConstants;
import org.geosdi.geoplatform.gui.client.i18n.windows.WindowsConstants;
import org.geosdi.geoplatform.gui.client.widget.SearchStatus.EnumSearchStatus;
import org.geosdi.geoplatform.gui.configuration.message.GeoPlatformMessage;
import org.geosdi.geoplatform.gui.impl.view.LayoutManager;
import org.geosdi.geoplatform.gui.puregwt.oauth2.IGPOAuth2GEBLoginHandler;
import org.geosdi.geoplatform.gui.puregwt.oauth2.OAuth2HandlerManager;
import org.geosdi.geoplatform.gui.puregwt.oauth2.event.GPOAuth2AddServerEvent;
import org.geosdi.geoplatform.gui.puregwt.oauth2.event.GPOAuth2CapabilitiesEvent;
import org.geosdi.geoplatform.gui.puregwt.properties.WidgetPropertiesHandlerManager;
import org.geosdi.geoplatform.gui.puregwt.oauth2.event.GPToolbarIconGEBLoginWidgetEvent;
import org.geosdi.geoplatform.gui.puregwt.oauth2.event.GPToolbarIconGEBLogoutWidgetEvent;
import org.geosdi.geoplatform.gui.server.gwt.OAuth2RemoteImpl;
import org.geosdi.geoplatform.gui.utility.oauth2.EnumOAuth2;

/**
 * @author Michele Santomauro - CNR IMAA geoSDI Group
 * @email michele.santomauro@geosdi.org
 *
 */
public class GoogleSignOnAction extends OAuth2MenuBaseAction
        implements IGPOAuth2GEBLoginHandler {

    private String type;

    public GoogleSignOnAction() {
        super(OAuth2WidgetConstants.INSTANCE.GoogleSignOnAction_titleText(),
                AbstractImagePrototype.create(OAuth2Resources.ICONS.googleSignOnWhite()));

        OAuth2HandlerManager.addHandler(IGPOAuth2GEBLoginHandler.TYPE, this);
    }

    @Override
    public void componentSelected(MenuEvent ce) {
        doLoginOnGEB(EnumOAuth2.MAKE_LOGIN.getValue());
    }

    @Override
    public void doLoginOnGEB(String theType) {
        this.type = theType;

        AuthRequest request = new AuthRequest(super.getGoogleAuthUrl(),
                super.getGoogleClientId()).withScopes(super.getScope());

        Auth auth = Auth.get();
        auth.login(request, new Callback<String, Throwable>() {
            @Override
            public void onSuccess(String token) {
                googleLoginCallback(token);

                setImage(AbstractImagePrototype.create(OAuth2Resources.ICONS.googleSignOnGreen()));
                setEnabled(false);

                WidgetPropertiesHandlerManager.fireEvent(
                        new GPToolbarIconGEBLoginWidgetEvent(
                        OAuth2WidgetConstants.INSTANCE.GoogleSignOnAction_signedOnGEBText()));
                LayoutManager.getInstance().getStatusMap().setStatus(
                        OAuth2WidgetConstants.INSTANCE.GoogleSignOnAction_signedOnGEBText(),
                        EnumSearchStatus.STATUS_SEARCH.toString());

                if (type.equals(EnumOAuth2.LOAD_CAPABILITIES.getValue())) {
                    OAuth2HandlerManager.fireEvent(new GPOAuth2CapabilitiesEvent());
                } else if (type.equals(EnumOAuth2.ADD_SERVER.getValue())) {
                    OAuth2HandlerManager.fireEvent(new GPOAuth2AddServerEvent());
                }
            }

            @Override
            public void onFailure(Throwable caught) {
                setImage(AbstractImagePrototype.create(OAuth2Resources.ICONS.googleSignOnWhite()));
                setEnabled(true);

                WidgetPropertiesHandlerManager.fireEvent(
                        new GPToolbarIconGEBLogoutWidgetEvent(
                        OAuth2WidgetConstants.INSTANCE.GoogleSignOnAction_notSignedOnGEBText()));

                GeoPlatformMessage.errorMessage(
                        OAuth2WidgetConstants.INSTANCE.GoogleSignOnAction_errorSigningOnGEBText(),
                        caught.getMessage());
                LayoutManager.getInstance().getStatusMap().setStatus(
                        OAuth2WidgetConstants.INSTANCE.GoogleSignOnAction_errorSigningOnGEBText(),
                        EnumSearchStatus.STATUS_SEARCH_ERROR.toString());
            }
        });
    }

    private void googleLoginCallback(String token) {
        OAuth2RemoteImpl.Util.getInstance().googleUserLogin(token, new AsyncCallback<Object>() {
            @Override
            public void onFailure(Throwable caught) {
                GeoPlatformMessage.errorMessage(WindowsConstants.INSTANCE.errorTitleText(),
                        caught.getMessage());
            }

            @Override
            public void onSuccess(Object result) {
            }
        });
    }
}
