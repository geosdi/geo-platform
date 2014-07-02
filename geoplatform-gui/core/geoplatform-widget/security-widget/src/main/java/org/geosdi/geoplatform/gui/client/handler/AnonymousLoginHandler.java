/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.geosdi.geoplatform.gui.client.handler;

import com.google.gwt.user.client.Window;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.geosdi.geoplatform.gui.client.command.login.basic.BasicLoginRequest;
import org.geosdi.geoplatform.gui.client.command.login.basic.BasicLoginResponse;
import org.geosdi.geoplatform.gui.client.config.BasicGinInjector;
import org.geosdi.geoplatform.gui.client.config.SecurityGinInjector;
import org.geosdi.geoplatform.gui.client.widget.security.AbstractLoginHandler;
import org.geosdi.geoplatform.gui.command.api.ClientCommandDispatcher;
import org.geosdi.geoplatform.gui.command.api.GPClientCommand;
import org.geosdi.geoplatform.gui.configuration.action.event.ToggleEnableEvent;
import org.geosdi.geoplatform.gui.configuration.message.GeoPlatformMessage;
import org.geosdi.geoplatform.gui.puregwt.togglebutton.GPToggleButtonHandlerManager;
import org.geosdi.geoplatform.gui.shared.util.GPSharedUtils;

/**
 * @author Nazzareno Sileno - CNR IMAA geoSDI Group
 * @email nazzareno.sileno@geosdi.org
 */
public class AnonymousLoginHandler extends AbstractLoginHandler {

    private static final Logger logger = Logger.getLogger("");

    @Override
    public void doLogin() {
        String userName = Window.Location.getParameter("project");
        String password = Window.Location.getParameter("key");
        if (GPSharedUtils.isNotEmpty(userName) && GPSharedUtils.isNotEmpty(password)) {
            ClientCommandDispatcher.getInstance().execute(
                    new GPClientCommand<BasicLoginResponse>(new BasicLoginRequest(
                                    userName, password)) {

                                        private static final long serialVersionUID = -1178797454775088815L;

                                        @Override
                                        public void onCommandSuccess(BasicLoginResponse response) {
                                            logger.log(Level.FINE, "Autorithy retrieved: " + response.getResult().getAuthority());
                                            if (response.getResult().getAuthority().equalsIgnoreCase("Viewer")) {
                                                SecurityGinInjector.MainInjector.getInstance().
                                                getPostLoginOperations().
                                                executeLoginOperations(response.getResult());

                                                BasicGinInjector.MainInjector.getInstance().
                                                getLoginAccessManager().
                                                hideProgressBar(Boolean.TRUE);

                                                GPToggleButtonHandlerManager.fireEventFromSource(
                                                        new ToggleEnableEvent(true),
                                                        GPToggleButtonHandlerManager.GPToggleButtonKeySource.GET_FEATURE_INFO);
                                            } else {
                                                printFailure();
                                            }
                                        }

                                        @Override
                                        public void onCommandFailure(Throwable exception) {
                                            printFailure();
                                            logger.log(Level.WARNING, "Error login using userName and pwd query string parameters: "
                                                    + exception.getMessage());
                                        }

                                    });
        } else {
            this.printFailure();
        }
    }

    private void printFailure() {
        GeoPlatformMessage.errorMessage("Login error",
                "Please verify project and key passed on query string");
    }

}
