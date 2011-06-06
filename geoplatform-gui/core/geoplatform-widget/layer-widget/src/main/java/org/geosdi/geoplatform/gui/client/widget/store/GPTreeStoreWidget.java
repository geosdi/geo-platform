/*
 *  geo-platform
 *  Rich webgis framework
 *  http://geo-plartform.org
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
package org.geosdi.geoplatform.gui.client.widget.store;

import com.google.gwt.user.client.Timer;
import java.util.List;
import org.geosdi.geoplatform.gui.client.widget.tree.GPTreePanel;
import org.geosdi.geoplatform.gui.client.widget.tree.store.GenericTreeStoreWidget;
import org.geosdi.geoplatform.gui.model.GPLayerBean;
import org.geosdi.geoplatform.gui.model.tree.GPBeanTreeModel;
import org.geosdi.geoplatform.gui.puregwt.grid.event.DeselectGridElementEvent;
import org.geosdi.geoplatform.gui.puregwt.layers.LayerHandlerManager;
import org.geosdi.geoplatform.gui.puregwt.progressbar.layers.event.LayersProgressTextEvent;

/**
 *
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email  giuseppe.lascaleia@geosdi.org
 */
public class GPTreeStoreWidget extends GenericTreeStoreWidget {

    private LayersProgressTextEvent layersTextEvent = new LayersProgressTextEvent();
    private DeselectGridElementEvent deselectEvent = new DeselectGridElementEvent();

    /**********************************************************/
    /**HERE THE MEMENTO AND VISITOR PROPERTIES TO ADD LAYERS **/
    /**********************************************************
     * @param theTree 
     */
    public GPTreeStoreWidget(GPTreePanel<? extends GPBeanTreeModel> theTree) {
        super(theTree);
    }

    @Override
    public void addRasterLayers(List<? extends GPLayerBean> layers) {
        this.changeProgressBarMessage("Load Raster Layers in the Store");
        System.out.println("ADD RASTER ******************* " + layers);

        //TODO :
        //     THIS CODE MUST BE CHANGED AND WILL BE SEND AN EVENT
        //     THAT DELESECT LAYERS IN THE GRID AND CLOSE PROGRESS BAR
        Timer t = new Timer() {

            @Override
            public void run() {
                LayerHandlerManager.fireEvent(deselectEvent);
            }
        };
        t.schedule(5000);
    }

    @Override
    public void addVectorLayers(List<? extends GPLayerBean> layers) {
        this.changeProgressBarMessage("Load Vector Layers in the Store");
        System.out.println("ADD VECTOR *********************** " + layers);
    }

    private void changeProgressBarMessage(String message) {
        layersTextEvent.setMessage(message);
        LayerHandlerManager.fireEvent(layersTextEvent);
    }
}
