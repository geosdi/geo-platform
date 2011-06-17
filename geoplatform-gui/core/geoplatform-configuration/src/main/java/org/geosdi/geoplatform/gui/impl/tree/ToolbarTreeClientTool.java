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
package org.geosdi.geoplatform.gui.impl.tree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.geosdi.geoplatform.gui.configuration.ActionClientTool;
import org.geosdi.geoplatform.gui.configuration.GenericClientTool;

/**
 *
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email  giuseppe.lascaleia@geosdi.org
 */
public class ToolbarTreeClientTool {

    private List<GenericClientTool> clientTools = new ArrayList<GenericClientTool>();

    public ToolbarTreeClientTool() {
        this.buildActionTools();
    }

    private void buildActionTools() {
        ActionClientTool addFolder = new ActionClientTool();
        addFolder.setId("addFolder");
        addFolder.setType("button");
        addFolder.setEnabled(false);
        addFolder.setOrder(1);

        ActionClientTool addRasterLayer = new ActionClientTool();
        addRasterLayer.setId("addRasterLayer");
        addRasterLayer.setType("button");
        addRasterLayer.setEnabled(false);
        addRasterLayer.setOrder(2);

        ActionClientTool addVectorLayer = new ActionClientTool();
        addVectorLayer.setId("addVectorLayer");
        addVectorLayer.setType("button");
        addVectorLayer.setEnabled(false);
        addVectorLayer.setOrder(3);

        GenericClientTool toolbarSeparator = new GenericClientTool();
        toolbarSeparator.setId("ToolbarSeparator");
        toolbarSeparator.setOrder(4);


        ActionClientTool removeElement = new ActionClientTool();
        removeElement.setId("removeElement");
        removeElement.setType("button");
        removeElement.setEnabled(false);
        removeElement.setOrder(5);


        ActionClientTool saveTreeState = new ActionClientTool();
        saveTreeState.setId("saveTreeState");
        saveTreeState.setType("button");
        saveTreeState.setEnabled(false);
        saveTreeState.setOrder(6);
        
        ActionClientTool printClientTool = new ActionClientTool();
        printClientTool.setId("printTreeLayers");
        printClientTool.setType("button");
        printClientTool.setEnabled(false);
        printClientTool.setOrder(7);


        this.clientTools.add(printClientTool);
        this.clientTools.add(saveTreeState);
        this.clientTools.add(removeElement);
        this.clientTools.add(toolbarSeparator);
        this.clientTools.add(addFolder);
        this.clientTools.add(addVectorLayer);
        this.clientTools.add(addRasterLayer);

        Collections.sort(clientTools);

    }

    /**
     * @return the clientTools
     */
    public List<GenericClientTool> getClientTools() {
        return clientTools;
    }
}
