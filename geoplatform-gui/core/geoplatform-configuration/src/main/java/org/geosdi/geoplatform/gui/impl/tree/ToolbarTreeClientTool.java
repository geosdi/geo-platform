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

    public static final String TOOLBAR_ADD_FOLDER = "addFolder";
    public static final String TOOLBAR_ADD_RASTER = "addRasterLayer";
    public static final String TOOLBAR_ADD_VECTOR = "addVectorLayer";
    public static final String TOOLBAR_REMOVE_ELEMENT = "removeElement";
    public static final String TOOLBAR_SAVE_TREE_STATE = "saveTreeState";
    public static final String TOOLBAR_PRINT_TREE_LAYERS = "printTreeLayers";
    public static final String TOOLBAR_UPLOAD_SHAPE = "uploadShape";
    public static final String TOOLBAR_UPLOAD_KML = "uploadKml";
    public static final String TOOLBAR_LOAD_WMS_GETMAP_FROM_URL = "loadWmsGetMapFromUrl";
    public static final String TOOLBAR_LOAD_KML_FROM_URL = "loadKmlFromUrl";
    public static final String TOOLBAR_PREVIEW_KML_FROM_URL = "previewKmlFromUrl";
    //
    private List<GenericClientTool> clientTools = new ArrayList<GenericClientTool>();

    public ToolbarTreeClientTool() {
        this.buildActionTools();
    }

    private void buildActionTools() {
        ActionClientTool addFolder = new ActionClientTool();
        addFolder.setId(TOOLBAR_ADD_FOLDER);
        addFolder.setType(ActionClientTool.BUTTON);
        addFolder.setEnabled(false);
        addFolder.setOrder(1);

        ActionClientTool addRasterLayer = new ActionClientTool();
        addRasterLayer.setId(TOOLBAR_ADD_RASTER);
        addRasterLayer.setType(ActionClientTool.BUTTON);
        addRasterLayer.setEnabled(false);
        addRasterLayer.setOrder(2);

        ActionClientTool addVectorLayer = new ActionClientTool();
        addVectorLayer.setId(TOOLBAR_ADD_VECTOR);
        addVectorLayer.setType(ActionClientTool.BUTTON);
        addVectorLayer.setEnabled(false);
        addVectorLayer.setOrder(3);

        GenericClientTool toolbarSeparator = new GenericClientTool();
        toolbarSeparator.setId("ToolbarSeparator");
        toolbarSeparator.setOrder(4);

        ActionClientTool removeElement = new ActionClientTool();
        removeElement.setId(TOOLBAR_REMOVE_ELEMENT);
        removeElement.setType(ActionClientTool.BUTTON);
        removeElement.setEnabled(false);
        removeElement.setOrder(5);

        ActionClientTool saveTreeState = new ActionClientTool();
        saveTreeState.setId(TOOLBAR_SAVE_TREE_STATE);
        saveTreeState.setType(ActionClientTool.BUTTON);
        saveTreeState.setEnabled(false);
        saveTreeState.setOrder(6);

        ActionClientTool printClientTool = new ActionClientTool();
        printClientTool.setId(TOOLBAR_PRINT_TREE_LAYERS);
        printClientTool.setType(ActionClientTool.BUTTON);
        printClientTool.setEnabled(false);
        printClientTool.setOrder(7);

        GenericClientTool toolbarSeparator2 = new GenericClientTool();
        toolbarSeparator2.setId("ToolbarSeparator");
        toolbarSeparator2.setOrder(8);

        ActionClientTool uploaderClientTool = new ActionClientTool();
        uploaderClientTool.setId(TOOLBAR_UPLOAD_SHAPE);
        uploaderClientTool.setType(ActionClientTool.BUTTON);
        uploaderClientTool.setEnabled(true);
        uploaderClientTool.setOrder(9);

        ActionClientTool loadWmsGetMapFromURLClientTool = new ActionClientTool();
        loadWmsGetMapFromURLClientTool.setId(TOOLBAR_LOAD_WMS_GETMAP_FROM_URL);
        loadWmsGetMapFromURLClientTool.setType(ActionClientTool.BUTTON);
        loadWmsGetMapFromURLClientTool.setEnabled(false);
        loadWmsGetMapFromURLClientTool.setOrder(10);

//        ActionClientTool loadKmlFromUrlClientTool = new ActionClientTool();
//        loadKmlFromUrlClientTool.setId(TOOLBAR_LOAD_KML_FROM_URL);
//        loadKmlFromUrlClientTool.setType(ActionClientTool.BUTTON);
//        loadKmlFromUrlClientTool.setEnabled(false);
//        loadKmlFromUrlClientTool.setOrder(11);
//
//        ActionClientTool uploadKmlClientTool = new ActionClientTool();
//        uploadKmlClientTool.setId(TOOLBAR_UPLOAD_KML);
//        uploadKmlClientTool.setType(ActionClientTool.BUTTON);
//        uploadKmlClientTool.setEnabled(false);
//        uploadKmlClientTool.setOrder(12);

        ActionClientTool previewKmlClientTool = new ActionClientTool();
        previewKmlClientTool.setId(TOOLBAR_PREVIEW_KML_FROM_URL);
        previewKmlClientTool.setType(ActionClientTool.BUTTON);
        previewKmlClientTool.setEnabled(true);
        previewKmlClientTool.setOrder(11);

        this.clientTools.add(previewKmlClientTool);
//        this.clientTools.add(uploadKmlClientTool);
//        this.clientTools.add(loadKmlFromUrlClientTool);
        this.clientTools.add(loadWmsGetMapFromURLClientTool);
        this.clientTools.add(uploaderClientTool);
        this.clientTools.add(toolbarSeparator2);
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
