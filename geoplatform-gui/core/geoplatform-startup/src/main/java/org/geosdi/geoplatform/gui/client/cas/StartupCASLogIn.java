/*
 *  geo-platform
 *  Rich webgis framework
 *  http://geo-platform.org
 * ====================================================================
 *
 * Copyright (C) 2008-2013 geoSDI Group (CNR IMAA - Potenza - ITALY).
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
package org.geosdi.geoplatform.gui.client.cas;

import org.geosdi.geoplatform.gui.client.config.BasicGinInjector;
import org.geosdi.geoplatform.gui.client.handler.CASLoginHandler;
import org.geosdi.geoplatform.gui.client.widget.security.ILoginHandler;
import org.geosdi.geoplatform.gui.configuration.startup.IStartupConfigurationStrategy;
import org.geosdi.geoplatform.gui.view.event.GeoPlatformEvents;

/**
 * @author Nazzareno Sileno - CNR IMAA geoSDI Group
 * @email nazzareno.sileno@geosdi.org
 */
public class StartupCASLogIn implements IStartupConfigurationStrategy {

    private static final long serialVersionUID = -4521004364754154783L;

    @Override
    public void initGeoPlatformConfiguration() {
        BasicGinInjector injector = BasicGinInjector.MainInjector.getInstance();
        ILoginHandler cASLoginHandler = new CASLoginHandler();
        injector.getSecurityLoginChainOfResponsibility().setLoginHandler(cASLoginHandler);
        injector.getLoginAccessManager().doLogin(GeoPlatformEvents.INIT_GEO_PLATFORM,
                "CAS login in corso...");

        //Dispatcher.forwardEvent(GeoPlatformEvents.APPLICATION_FIRST_LOGIN);

        //
//        Cookies.getCookie("JSESSIONID");
//        for (String cookie : Cookies.getCookieNames()) {
//            System.out.println("Cookies rilevati: " + cookie);
//        }
//        System.out.println("Query string: " + Window.Location.getParameter("ticket"));
//        GPCASRemoteImpl.Util.getInstance().getCASUser(new AsyncCallback<IGPAccountDetail>() {
//            @Override
//            public void onFailure(Throwable caught) {
//                System.out.println("Result erroress: " + caught);
//            }
//
//            @Override
//            public void onSuccess(IGPAccountDetail result) {
//                System.out.println("Result OK: " + result);
//            }
//        });
//        Dispatcher.get().dispatch(GeoPlatformEvents.INIT_GEO_PLATFORM);
//        Window.open("http://150.146.160.61/cas/login", "_self", ""); 
        //
//        SecurityRemoteImpl.Util.getInstance().ssoLogin(new AsyncCallback<IGPAccountDetail>() {
//            @Override
//            public void onFailure(Throwable caught) {
//                System.out.println("Error login on IVUser: " + caught.getMessage());
//            }
//
//            @Override
//            public void onSuccess(IGPAccountDetail resultDetails) {
//                boolean result = false;
//                if (resultDetails != null) {
//                    executeLoginOperations(resultDetails);
//                    result = true;
//                    //loginXMPPClient(ivUser, password.getValue(), result.getHostXmppServer());
//                }
//                LoginWidget.super.continueLoginProcesFromSSo(result);
//            }
//        });
    }
}
