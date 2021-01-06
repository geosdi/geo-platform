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
package org.geosdi.geoplatform.gui.client.handler;

import com.extjs.gxt.ui.client.mvc.Dispatcher;
import org.geosdi.geoplatform.gui.client.GPXMPPClient;
import org.geosdi.geoplatform.gui.client.command.login.sso.SSOLoginRequest;
import org.geosdi.geoplatform.gui.client.command.login.sso.SSOLoginResponse;
import org.geosdi.geoplatform.gui.client.command.login.xmpp.XMPPGetDataLoginFromHeaderRequest;
import org.geosdi.geoplatform.gui.client.command.login.xmpp.XMPPGetDataLoginResponse;
import org.geosdi.geoplatform.gui.client.config.BasicGinInjector;
import org.geosdi.geoplatform.gui.client.config.SecurityGinInjector;
import org.geosdi.geoplatform.gui.client.i18n.SecurityModuleConstants;
import org.geosdi.geoplatform.gui.client.model.security.XMPPLoginDetails;
import org.geosdi.geoplatform.gui.client.widget.security.AbstractLoginHandler;
import org.geosdi.geoplatform.gui.command.api.ClientCommandDispatcher;
import org.geosdi.geoplatform.gui.command.api.GPClientCommand;
import org.geosdi.geoplatform.gui.configuration.message.GeoPlatformMessage;
import org.geosdi.geoplatform.gui.view.event.GeoPlatformEvents;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Nazzareno Sileno - CNR IMAA geoSDI Group
 * @email nazzareno.sileno@geosdi.org
 */
public class SSOLoginHandler extends AbstractLoginHandler {

    private static final Logger logger = Logger.getLogger("");

    @Override
    public void doLogin() {
        ClientCommandDispatcher.getInstance().execute(new GPClientCommand<SSOLoginResponse>() {

                    private static final long serialVersionUID = -1447466115597300972L;

                    {
                        super.setCommandRequest(new SSOLoginRequest());
                    }

                    @Override
                    public void onCommandSuccess(SSOLoginResponse response) {
                        if ((response != null) && (response.getResult() != null)) {
                            executeXMPPLogin();
                            SecurityGinInjector.MainInjector.getInstance().getPostLoginOperations().executeLoginOperations(response.getResult());
                            BasicGinInjector.MainInjector.getInstance().getLoginAccessManager().hideProgressBar(Boolean.TRUE);
                        } else if (SSOLoginHandler.super.nextHandler != null) {
                            SSOLoginHandler.super.nextHandler.doLogin();
                        } else {
                            BasicGinInjector.MainInjector.getInstance().
                            getLoginAccessManager().hideProgressBar(Boolean.FALSE);
                            Dispatcher.forwardEvent(GeoPlatformEvents.APPLICATION_FIRST_LOGIN);
                        }
                    }

                    @Override
                    public void onCommandFailure(Throwable exception) {
                        logger.log(Level.WARNING, "Error login on IVUser: " + exception.
                                getMessage());
                    }

                });
    }

    private void executeXMPPLogin() {
        logger.log(Level.FINEST, "Executing XMPP login");
        XMPPGetDataLoginFromHeaderRequest xMPPCASGetDataLoginRequest = new XMPPGetDataLoginFromHeaderRequest();
        ClientCommandDispatcher.getInstance().execute(
                new GPClientCommand<XMPPGetDataLoginResponse>(xMPPCASGetDataLoginRequest) {

                    private static final long serialVersionUID = -1178797454775088815L;

                    @Override
                    public void onCommandSuccess(XMPPGetDataLoginResponse response) {
                        logger.log(Level.FINEST, "Executing SSO XMPP login: " + response);
                        if (response != null && response.getResult() != null) {
                            XMPPLoginDetails xMPPLoginDetails = response.getResult();
                            GPXMPPClient xMPPClient = new GPXMPPClient();
                            xMPPClient.userXMPPLogin(xMPPLoginDetails.getUsername(),
                                    xMPPLoginDetails.getPassword(), xMPPLoginDetails.getHostXmppServer());
                            logger.log(Level.FINEST, "Executed SSO XMPP login");
                        }
                    }

                    @Override
                    public void onCommandFailure(Throwable exception) {
                        GeoPlatformMessage.infoMessage(SecurityModuleConstants.INSTANCE.XMPPConnectionErrorTitleText(),
                                SecurityModuleConstants.INSTANCE.XMPPConnectionErrorBodyText() + exception.getMessage());
                        logger.log(Level.WARNING, "Error login on XMPP for IVUser: " + exception.getMessage());
                    }
                });
    }
}