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
package org.geosdi.geoplatform.gui.command.api;

import com.google.gwt.user.client.Cookies;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.HasRpcToken;
import com.google.gwt.user.client.rpc.RpcTokenException;
import com.google.gwt.user.client.rpc.XsrfToken;
import com.google.gwt.user.client.rpc.XsrfTokenServiceAsync;
import java.util.Random;
import org.geosdi.geoplatform.gui.service.GeoPlatformService;
import org.geosdi.geoplatform.gui.service.GeoPlatformServiceAsync;
import org.geosdi.geoplatform.gui.service.gwt.xsrf.GPXsrfTokenService;

/**
 *
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 *
 * @since 1.5.1
 */
public final class ClientCommandDispatcher {

    static {
        Cookies.setCookie("GEOSDI_COOKIE", Long.toString(new Random().nextLong()));
    }

    private static final ClientCommandDispatcher instance = new ClientCommandDispatcher();
    //
    private final GeoPlatformServiceAsync service;
    private final XsrfTokenServiceAsync xsrf;

    private ClientCommandDispatcher() {
        this.service = GeoPlatformService.Util.getInstance();
        this.xsrf = GPXsrfTokenService.Util.getInstance();
    }

    public static ClientCommandDispatcher getInstance() {
        return instance;
    }

    /**
     * <p>
     * Execute the {@link ClientCommand} command on the Server</p>
     *
     * @param command
     */
    public void execute(final ClientCommand command) {
        this.xsrf.getNewXsrfToken(new AsyncCallback<XsrfToken>() {

            @Override
            public void onFailure(Throwable caught) {
                command.onCommandFailure(caught);
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
                ((HasRpcToken) service).setRpcToken(token);
                service.execute(command.getCommandRequest(),
                        new AsyncCallback<GPCommandResponse>() {

                            @Override
                            public void onFailure(Throwable caught) {
                                command.onCommandFailure(caught);
                            }

                            @Override
                            public void onSuccess(GPCommandResponse result) {
                                command.onCommandSuccess(result);
                            }
                        });
            }
        });
    }
}
