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
package org.geosdi.geoplatform.gui.client.widget.tab;

import org.geosdi.geoplatform.gui.client.widget.IGPSubClassesInitialization;
import com.extjs.gxt.ui.client.event.ComponentEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.widget.TabItem;
import java.util.logging.Logger;
import org.geosdi.geoplatform.gui.client.widget.binding.GeoPlatformBindingWidget;
import org.geosdi.geoplatform.gui.puregwt.properties.WidgetPropertiesHandlerManager;
import org.geosdi.geoplatform.gui.puregwt.properties.event.GPWidgetSizeEvent;

/**
 *
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public abstract class GeoPlatformTabItem extends TabItem
        implements IGPSubClassesInitialization {

    protected final static Logger logger = Logger.getLogger("");
    protected GeoPlatformBindingWidget bindingWidget;
    protected GPWidgetSizeEvent event = new GPWidgetSizeEvent();
    private boolean initialized;

    public GeoPlatformTabItem(String text) {
        this(text, Boolean.FALSE);
    }

    public GeoPlatformTabItem(String title, boolean lazy) {
        super(title);
        if (!lazy) {
            this.init();
        }
    }

    @Override
    protected void beforeRender() {
        super.beforeRender();
    }

    @Override
    public final void init() {
        if (!initialized) {
            addComponents();
            setWidgetProperties();
            this.initialized = true;
        }
    }

    @Override
    protected void onAttach() {
        super.onAttach();
        this.init();
    }

    @Override
    public abstract void subclassCallToInit();

    public abstract void addComponents();

    protected void setWidgetProperties() {
        super.addListener(Events.Select, new Listener<ComponentEvent>() {

            @Override
            public void handleEvent(ComponentEvent be) {
                event.setSize(getTabPanel().getHeight());
                WidgetPropertiesHandlerManager.fireEventFromSource(event,
                        getTabPanel().getParent());
            }
        });
    }

    public void reset() {
    }

    /**
     *
     * @return Component State for Initialization of All Components
     */
    public boolean isInitialized() {
        return initialized;
    }
}
