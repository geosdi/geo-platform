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
package org.geosdi.geoplatform.gui.client.config;

import com.google.gwt.core.client.GWT;
import com.google.gwt.inject.client.GinModules;
import com.google.gwt.inject.client.Ginjector;
import org.geosdi.geoplatform.gui.action.ToolbarActionRegistar;
import org.geosdi.geoplatform.gui.action.menu.MenuActionRegistar;
import org.geosdi.geoplatform.gui.client.i18n.BasicWidgetMessages;
import org.geosdi.geoplatform.gui.client.widget.security.GPLoginAccessManager;
import org.geosdi.geoplatform.gui.client.widget.security.SecurityLoginChainOfResponsibility;
import org.geosdi.geoplatform.gui.googleanalytics.config.GPGoogleAnalyticsGinConfigurator;
import org.geosdi.geoplatform.gui.model.message.CommandActionMediator;

/**
 * @author Nazzareno Sileno - CNR IMAA geoSDI Group
 * @email nazzareno.sileno@geosdi.org
 */
@GinModules(value = {BasicGinConfigurator.class,
    GPGoogleAnalyticsGinConfigurator.class})
public interface BasicGinInjector extends Ginjector {

    public static class MainInjector {

        private static BasicGinInjector instance;

        private MainInjector() {
        }

        public static BasicGinInjector getInstance() {
            if (instance == null) {
                instance = GWT.create(BasicGinInjector.class);
            }

            return instance;
        }

    }

    public ToolbarActionRegistar getToolbarActionRegistar();

    public MenuActionRegistar getMenuActionRegistar();

    public CommandActionMediator getCommandActionMediator();

    public SecurityLoginChainOfResponsibility getSecurityLoginChainOfResponsibility();

    public GPLoginAccessManager getLoginAccessManager();

    public BasicWidgetMessages getBasicWidgetMessages();

}
