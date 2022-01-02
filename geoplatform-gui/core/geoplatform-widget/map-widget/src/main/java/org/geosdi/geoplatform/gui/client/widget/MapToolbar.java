/*
 *
 *    geo-platform
 *    Rich webgis framework
 *    http://geo-platform.org
 *   ====================================================================
 *
 *   Copyright (C) 2008-2022 geoSDI Group (CNR IMAA - Potenza - ITALY).
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
package org.geosdi.geoplatform.gui.client.widget;

import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.button.ToggleButton;
import java.util.List;
import org.geosdi.geoplatform.gui.action.GeoPlatformButtonObserver;
import org.geosdi.geoplatform.gui.client.widget.map.MapLayoutWidget;
import org.geosdi.geoplatform.gui.client.widget.map.toolbar.GPMapToolbarWidget;
import org.geosdi.geoplatform.gui.configuration.WidgetGenericTool;
import org.geosdi.geoplatform.gui.configuration.toolbar.IGeoPlatformToolbar;
import org.geosdi.geoplatform.gui.impl.map.IGeoPlatofomMapButtonBar;

/**
 * @author giuseppe
 *
 */
public class MapToolbar extends GPMapToolbarWidget
        implements IGeoPlatofomMapButtonBar {

    private GeoPlatformButtonObserver buttonObserver;

    /**
     * Constructor
     *
     * @param mapLayoutWidget
     * @param tools
     */
    public MapToolbar(MapLayoutWidget mapLayoutWidget,
            List<WidgetGenericTool<IGeoPlatformToolbar>> tools) {
        super(mapLayoutWidget, tools);
        ((MapLayoutWidget) this.geoPlatformMap).setButtonBar(this);
        this.buttonObserver = new GeoPlatformButtonObserver();
    }

    /**
     * Checks for a Toggle Button pressed
     *
     * @return boolean
     */
    @Override
    public boolean isTogglePressed() {
        return this.buttonObserver.isButtonPressed();
    }

    /**
     *
     * @param button
     */
    @Override
    public void setPressedButton(ToggleButton button) {
        this.buttonObserver.setButtonPressed(button);
    }

    @Override
    public Button getPressedButton() {
        return this.buttonObserver.getButtonPressed();
    }

    @Override
    public void changeButtonState() {
        this.buttonObserver.changeButtonState();
    }

    /**
     *
     * @return Toolbar Elements Number
     */
    public int getItemsCount() {
        return this.toolBar.getItemCount();
    }
}
