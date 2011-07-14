/*
 *  geo-platform
 *  Rich webgis framework
 *  http://geo-platform.org
 * ====================================================================
 *
 * Copyright (C) 2008-2011 geoSDI Group (CNR IMAA - Potenza - ITALY).
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
package org.geosdi.geoplatform.gui.client.action.toolbar;

import org.geosdi.geoplatform.gui.action.MapToggleAction;
import org.geosdi.geoplatform.gui.client.Resources;
import org.geosdi.geoplatform.gui.client.widget.map.MapLayoutWidget;
import org.geosdi.geoplatform.gui.impl.map.GeoPlatformMap;
import org.gwtopenmaps.openlayers.client.control.Control;

import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.widget.button.ToggleButton;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 * 
 */
public class DrawPointAction extends MapToggleAction {

    public DrawPointAction(GeoPlatformMap mapWidget) {
        super("Draw Point", Resources.ICONS.drawPointFeature(), mapWidget);
        // TODO Auto-generated constructor stub
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * com.extjs.gxt.ui.client.event.SelectionListener#componentSelected(com
     * .extjs.gxt.ui.client.event.ComponentEvent)
     */
    @Override
    public void componentSelected(ButtonEvent ce) {
        // TODO Auto-generated method stub
        ToggleButton button = (ToggleButton) ce.getSource();

        super.changeButtonState();

        if (button.isPressed()) {
            mapWidget.getButtonBar().setPressedButton(button);
            this.mapWidget.activateDrawPointFeature();
        } else {
            this.mapWidget.deactivateDrawPointFeature();
        }
    }

    /*
     * (non-Javadoc)
     *
     * @see org.geosdi.geoplatform.gui.action.ToolbarMapAction#getMapControl()
     */
    @Override
    public Control getMapControl() {
        // TODO Auto-generated method stub
        return ((MapLayoutWidget) mapWidget).getDrawPointFeature();
    }

    @Override
    public void disableControl() {
        getMapControl().deactivate();
    }
}
