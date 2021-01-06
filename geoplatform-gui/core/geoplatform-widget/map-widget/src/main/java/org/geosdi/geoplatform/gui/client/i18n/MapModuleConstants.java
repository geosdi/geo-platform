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
package org.geosdi.geoplatform.gui.client.i18n;

import com.google.gwt.core.client.GWT;
import com.google.gwt.i18n.client.Constants;
import com.google.gwt.i18n.client.Constants.DefaultStringValue;

/**
 * @author Nazzareno Sileno - CNR IMAA geoSDI Group
 * @email nazzareno.sileno@geosdi.org
 */
public interface MapModuleConstants extends Constants {

    public static final MapModuleConstants INSTANCE =
            GWT.create(MapModuleConstants.class);

    /**
     * start ViewportWidget
     */
    @DefaultStringValue("Error loading the viewport elements.")
    String ViewportWidget_statusErrorLoadingText();
    
    @DefaultStringValue("Loading Viewport...")
    String ViewportWidget_loadingMaskText();

    @DefaultStringValue("Viewport Widget")
    String ViewportWidget_headingText();

    /**
     * start ViewportUtility
     */
    @DefaultStringValue("New Viewport")
    String ViewportUtility_newViewportBeanNameText();

    @DefaultStringValue("Insert Description")
    String ViewportUtility_newViewportDescriptionText();

    /**
     * start ViewportGridFieldSet
     */
    @DefaultStringValue("Zoom Level")
    String ViewportGridFieldSet_zoomLevelColumnText();

    @DefaultStringValue("Min X")
    String ViewportGridFieldSet_minXColumnText();

    @DefaultStringValue("Min Y")
    String ViewportGridFieldSet_minYColumnText();

    @DefaultStringValue("Max X")
    String ViewportGridFieldSet_maxXColumnText();

    @DefaultStringValue("Max Y")
    String ViewportGridFieldSet_maxYColumnText();

    @DefaultStringValue("Description")
    String ViewportGridFieldSet_descriptionColumnText();

    @DefaultStringValue("Name")
    String ViewportGridFieldSet_nameColumnText();

    @DefaultStringValue("Default")
    String ViewportGridFieldSet_defaultViewportText();

    @DefaultStringValue("Filter")
    String ViewportGridFieldSet_viewportFilterLabelText();

    @DefaultStringValue("Type the viewport to search")
    String ViewportGridFieldSet_viewportFilterEmptyTextText();

    @DefaultStringValue("Set Default Viewport Alert")
    String ViewportGridFieldSet_setDefaultViewportAlertTitleText();

    @DefaultStringValue("GoTo Viewport Allert")
    String ViewportGridFieldSet_singleSelectionAlertTitleText();

    @DefaultStringValue("You must select only one viewport at time.")
    String ViewportGridFieldSet_singleSelectionAlertBodyText();

    @DefaultStringValue("Delete Viewport")
    String ViewportGridFieldSet_confirmDeleteViewportTitleText();

    @DefaultStringValue("Are you sure you want to delete the selected Viewport?")
    String ViewportGridFieldSet_confirmDeleteViewportBodyText();

    @DefaultStringValue("GoTo Viewport")
    String ViewportGridFieldSet_buttonGoToViewportText();

    @DefaultStringValue("Add Viewport")
    String ViewportGridFieldSet_buttonAddViewportText();

    @DefaultStringValue("Delete Viewport")
    String ViewportGridFieldSet_buttonDeleteViewportText();

    /**
     * start GPScaleWidget
     */
    @DefaultStringValue("Select a scale...")
    String GPScaleWidget_comboScaleEmptyText();

    /**
     * start MapLayoutWidget
     */
    @DefaultStringValue("Base Layer")
    String MapLayoutWidget_baseLayerAlreadyDisplayedTitleText();

    @DefaultStringValue("The selected base layer is already displayed")
    String MapLayoutWidget_baseLayerAlreadyDisplayedBodyText();

    @DefaultStringValue("Scale")
    String MapLayoutWidget_scaleText();

    @DefaultStringValue("Area is")
    String MapLayoutWidget_infoAreaText();

    @DefaultStringValue("Distance is")
    String MapLayoutWidget_infoDiscanceText();

    /**
     * start PolygonRequestHandler
     */
    @DefaultStringValue("Polygon Feature Status")
    String PolygonRequestHandler_confirmFeatureChangesTitleText();

    @DefaultStringValue("The Geometry Polygon Feature is changed. Do you want to apply the changes?")
    String PolygonRequestHandler_confirmFeatureChangesBodyText();

    /**
     * start PointRequestHandler
     */
    @DefaultStringValue("Point Feature Status")
    String PointRequestHandler_confirmFeatureChangesTitleText();

    @DefaultStringValue("The Geometry Point Feature is changed. Do you want to apply the changes?")
    String PointRequestHandler_confirmFeatureChangesBodyText();

    /**
     * start LineRequestHandler
     */
    @DefaultStringValue("Line Feature Status")
    String LineRequestHandler_confirmFeatureChangesTitleText();

    @DefaultStringValue("The Geometry Line Feature is changed. Do you want to apply the changes?")
    String LineRequestHandler_confirmFeatureChangesBodyText();

    /**
     * start GenericFeatureOperation
     */
    @DefaultStringValue("Delete Feature")
    String GenericFeatureOperation_confirmDeletionTitleText();

    @DefaultStringValue("Are you sure you want to delete the selected feature?")
    String GenericFeatureOperation_confirmDeletionBodyText();

    /**
     * start LegendWindow
     */
    @DefaultStringValue("Legend Window")
    String LegendWindow_headingText();

    /**
     * start BaseLayerWidget
     */
    @DefaultStringValue("Base Layer Selection")
    String BaseLayerWidget_headingText();

    /**
     * start SaveViewportAction
     */
    @DefaultStringValue("Viewport Incorrect")
    String SaveViewportAction_errorViewportTitleText();

    @DefaultStringValue("Before to save it is necessary to correct or fill "
            + "the: Name, zoom or BBOX values")
    String SaveViewportAction_errorViewportBodyText();

    @DefaultStringValue("Error saving the viewport list.")
    String SaveViewportAction_statusErrorSavingText();

    @DefaultStringValue("Succesfully saved the viewport list.")
    String SaveViewportAction_statusSaveSuccesfullText();

    /**
     * start ZoomPreviousAction
     */
    @DefaultStringValue("Zoom Previous")
    String ZoomPreviousAction_tooltipText();

    /**
     * start ZoomOutAction
     */
    @DefaultStringValue("Zoom Out")
    String ZoomOutAction_tooltipText();

    /**
     * start ZoomNextAction
     */
    @DefaultStringValue("Zoom Next")
    String ZoomNextAction_tooltipText();

    /**
     * start ZoomInAction
     */
    @DefaultStringValue("Zoom In")
    String ZoomInAction_tooltipText();

    /**
     * start OpenEditorMapAction
     */
    @DefaultStringValue("Open Basic Map Editor")
    String OpenEditorMapAction_tooltipText();

    /**
     * start MeasureAreaAction
     */
    @DefaultStringValue("Measure Area")
    String MeasureAreaAction_tooltipText();

    /**
     * start MeasureAction
     */
    @DefaultStringValue("Measure")
    String MeasureAction_tooltipText();

    /**
     * start ChangeBaseLayerAction
     */
    @DefaultStringValue("Select Base Layer")
    String ChangeBaseLayerAction_tooltipText();

    /**
     * start ViewportAction
     */
    @DefaultStringValue("Viewport")
    String ViewportAction_titleText();

    /**
     * start RotateAction
     */
    @DefaultStringValue("Rotate")
    String RotateAction_tooltipText();

    /**
     * start ResizeAction
     */
    @DefaultStringValue("Resize")
    String ResizeAction_tooltipText();

    /**
     * start ReshapeAction
     */
    @DefaultStringValue("Reshape")
    String ReshapeAction_tooltipText();

    /**
     * start DrawPolygonAction
     */
    @DefaultStringValue("Draw Polygon")
    String DrawPolygonAction_tooltipText();
    
    /**
     * start DrawCircleAction
     */
    @DefaultStringValue("Draw Polygon")
    String DrawCircleAction_tooltipText();

    /**
     * start DrawPointAction
     */
    @DefaultStringValue("Draw Point")
    String DrawPointAction_tooltipText();

    /**
     * start DrawLineAction
     */
    @DefaultStringValue("Draw Line")
    String DrawLineAction_tooltipText();

    /**
     * start DragAction
     */
    @DefaultStringValue("Drag")
    String DragAction_tooltipText();

    /**
     * start DeleteFeatureAction
     */
    @DefaultStringValue("Delete Feature")
    String DeleteFeatureAction_tooltipText();

    @DefaultStringValue("Feaures Service")
    String DeleteFeatureAction_alertNoFeatureTitleText();

    @DefaultStringValue("There are no Features to erase.")
    String DeleteFeatureAction_alertNoFeatureBodyText();

    /**
     * start ClearMapAction
     */
    @DefaultStringValue("Clear Map")
    String ClearMapAction_tooltipText();

    /**
     * start SaveBaseLayerAction
     */
    @DefaultStringValue("Error saving the new base layer.")
    String SaveBaseLayerAction_statusErrorSavingText();

    @DefaultStringValue("Base Layer successfully saved.")
    String SaveBaseLayerAction_statusSaveSuccesfullText();
}
