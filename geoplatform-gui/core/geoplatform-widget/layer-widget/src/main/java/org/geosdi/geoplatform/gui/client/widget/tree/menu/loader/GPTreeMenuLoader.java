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
package org.geosdi.geoplatform.gui.client.widget.tree.menu.loader;

import com.extjs.gxt.ui.client.event.BaseEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.Listener;
import org.geosdi.geoplatform.gui.client.command.GetCompositeMenuRequest;
import org.geosdi.geoplatform.gui.client.command.GetCompositeMenuResponse;
import org.geosdi.geoplatform.gui.client.widget.tree.GPTreePanel;
import org.geosdi.geoplatform.gui.client.widget.tree.menu.activator.puregwt.event.GPTreeMenuActivatorEvent;
import org.geosdi.geoplatform.gui.client.widget.tree.panel.GinTreePanel;
import org.geosdi.geoplatform.gui.command.api.ClientCommandDispatcher;
import org.geosdi.geoplatform.gui.command.api.GPClientCommand;
import org.geosdi.geoplatform.gui.impl.tree.menu.store.TreeMenuStoreRepository;
import org.geosdi.geoplatform.gui.puregwt.GPEventBus;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@Singleton
public class GPTreeMenuLoader implements TreeMenuLoader {

    private static final Logger logger = Logger.getLogger("GPTreeMenuLoader");
    //
    @Inject
    private TreeMenuStoreRepository storeRepository;
    @Inject
    private GPEventBus bus;
    private final GPTreePanel tree;

    /**
     * @param ginTreePanel
     */
    @Inject
    public GPTreeMenuLoader(GinTreePanel ginTreePanel) {
        this.tree = ginTreePanel.get();
    }

    @Override
    public void loadMenu() {
        this.tree.addListener(Events.Render, new Listener() {

            @Override
            public void handleEvent(BaseEvent be) {
                ClientCommandDispatcher.getInstance().execute(new GPClientCommand<GetCompositeMenuResponse>() {

                    private static final long serialVersionUID = 2887781730841655720L;

                    {
                        super.setCommandRequest(new GetCompositeMenuRequest());
                    }

                    @Override
                    public void onCommandSuccess(GetCompositeMenuResponse response) {
                        storeRepository.setMenuCompositeStore(response.getResult());
                        bus.fireEvent(new GPTreeMenuActivatorEvent());
                    }

                    @Override
                    public void onCommandFailure(Throwable exception) {
                        /**
                         * For now a sout to print the exception. We
                         * don't notify to user the error, the
                         * Application must use the base TreeMenu
                         * prepared with Menu Item for GeoPlatform Info.
                         *
                         */
                        logger.log(Level.WARNING, "TreeLayer Dynamic Menu Loading Error : " + exception);
                    }
                });
            }
        });
    }
}
