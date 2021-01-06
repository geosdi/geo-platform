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

/**
 * @author Nazzareno Sileno - CNR IMAA geoSDI Group
 * @email nazzareno.sileno@geosdi.org
 */
public interface LayerModuleConstants extends Constants {

    LayerModuleConstants INSTANCE = GWT.create(LayerModuleConstants.class);

    @DefaultStringValue("Save Delete Operation Error")
    String errorSaveDeleteOperationTitleText();

    @DefaultStringValue("Delete Selected Elements")
    String deleteSelectedElementsText();

    @DefaultStringValue("Saving Operations On Service")
    String savingOperationsText();

    @DefaultStringValue("Ever Enabled")
    String pluginEnabledEverMessageText();

    @DefaultStringValue("URL")
    String urlLabelText();

    @DefaultStringValue("Error checking URL")
    String errorCheckingURLTitleText();

    @DefaultStringValue("Error loading")
    String errorLoadingTitleText();

    @DefaultStringValue("Error checking the WMS URL.")
    String statusErrorCheckingURLText();

    @DefaultStringValue("Delete Project")
    String deleteProjectTitleText();

    @DefaultStringValue("Tree elements loaded successfully.")
    String statusSuccessLoadingTreeElementsText();

    @DefaultStringValue("Error loading tree elements.")
    String statusErrorLoadingTreeElementsText();

    @DefaultStringValue("Loading tree elements: please, wait untill contents fully loads.")
    String statusLoadingTreeElementsText();

    /**
     * start LayersPropertiesWidget
     */
    @DefaultStringValue("GP Layers Properties Widget")
    String LayersPropertiesWidget_headingText();

    /**
     * start LayerManagementWidget
     */
    @DefaultStringValue("GeoPlatform - Layer Widget")
    String LayerManagementWidget_headingText();

    /**
     * start GPLegendPanel
     */
    @DefaultStringValue("GeoPlatform - Legend Widget")
    String GPLegendPanel_headingText();

    /**
     * start GPCheckListener
     */
    @DefaultStringValue("Save Check Operation on Folder Error")
    String GPCheckListener_saveCheckErrorTitleText();

    @DefaultStringValue("Problems on saving the new tree state after checking folder")
    String GPCheckListener_saveCheckErrorBodyText();

    @DefaultStringValue("Save Check Folder Operation completed successfully.")
    String GPCheckListener_statusSaveCheckSuccessText();

    /**
     * start FolderPropertiesWidget
     */
    @DefaultStringValue("GP Folder Properties Widget")
    String FolderPropertiesWidget_headingText();

    @DefaultStringValue("Project Properties Widget")
    String ProjectPropertiesWidget_headingText();

    @DefaultStringValue("Clone Project Widget")
    String CloneProjectWidget_headingText();

    /**
     * start LayerTimeFilterWidget
     */
    @DefaultStringValue("Time Filter Message")
    String LayerTimeFilterWidget_timeFilterMessageTitleText();

    @DefaultStringValue("Time Filter Warning")
    String LayerTimeFilterWidget_timeFilterWarningTitleText();

    @DefaultStringValue("Error Loading Time Filter")
    String LayerTimeFilterWidget_statusTimeFilterErrorLoadingText();

    @DefaultStringValue("Time Filter Error")
    String LayerTimeFilterWidget_timeFilterErrorTitleText();

    @DefaultStringValue("Incorrect Position time, you must specify a valid position in size range")
    String LayerTimeFilterWidget_timeFilterErrorBodyText();

    @DefaultStringValue("Impossible to show time sequence, the T1 time must be set")
    String LayerTimeFilterWidget_timeFilterWarningBodyText();

    @DefaultStringValue("The T2 time must be lesser than T1 time")
    String LayerTimeFilterWidget_dimensionWarningBodyText();

    @DefaultStringValue("Dimension Warning")
    String LayerTimeFilterWidget_dimensionWarningTitleText();

    @DefaultStringValue("No Limits")
    String LayerTimeFilterWidget_timeNoLimitsTitleText();

    @DefaultStringValue("T1")
    String LayerTimeFilterWidget_startFieldLabelText();

    @DefaultStringValue("T2")
    String LayerTimeFilterWidget_endFieldLabelText();

    @DefaultStringValue("Start Fixed dimension")
    String LayerTimeFilterWidget_startDimensionComboLabelText();

    @DefaultStringValue("End Fixed dimension")
    String LayerTimeFilterWidget_endDimensionComboLabelText();

    @DefaultStringValue("Variable Dimension")
    String LayerTimeFilterWidget_variableDimensionRadioLabelText();

    @DefaultStringValue("Fixed Dimension")
    String LayerTimeFilterWidget_fixedDimensionRadioLabelText();

    @DefaultStringValue("Dimension strategy")
    String LayerTimeFilterWidget_dimensionRadioLabelText();

    @DefaultStringValue("TIME FILTER EDITOR")
    String LayerTimeFilterWidget_timeFilderHeadingText();

    @DefaultStringValue("Time Filter")
    String LayerTimeFilterWidget_timeFilterTabHeadingText();

    @DefaultStringValue("Time Dimension")
    String LayerTimeFilterWidget_timeDimensioneTabHeadingText();

    @DefaultStringValue("Range")
    String LayerTimeFilterWidget_rangeLabelText();

    @DefaultStringValue("Period")
    String LayerTimeFilterWidget_periodLabelText();

    @DefaultStringValue("Dates Availables")
    String LayerTimeFilterWidget_datesAvailablesLabelText();

    @DefaultStringValue("Year/s")
    String LayerTimeFilterWidget_yearTitleText();

    @DefaultStringValue("Month/s")
    String LayerTimeFilterWidget_monthTitleText();

    @DefaultStringValue("Week/s")
    String LayerTimeFilterWidget_weekTitleText();

    @DefaultStringValue("Day/S")
    String LayerTimeFilterWidget_dayTitleText();

    @DefaultStringValue("Hour/s")
    String LayerTimeFilterWidget_hourTitleText();

    @DefaultStringValue("Minute/s")
    String LayerTimeFilterWidget_minuteTitleText();

    @DefaultStringValue("Second/S")
    String LayerTimeFilterWidget_secondTitleText();

    @DefaultStringValue("From&nbsp;")
    String LayerTimeFilterWidget_multidatesFromText();

    @DefaultStringValue("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;To&nbsp;")
    String LayerTimeFilterWidget_multidatesToText();

    @DefaultStringValue("End date")
    String LayerTimeFilterWidget_endDateTooltipText();

    @DefaultStringValue("End hour")
    String LayerTimeFilterWidget_endHourTooltipText();

    @DefaultStringValue("End minute")
    String LayerTimeFilterWidget_endMinuteTooltipText();

    @DefaultStringValue("Start date")
    String LayerTimeFilterWidget_startDateTooltipText();

    @DefaultStringValue("Start hour")
    String LayerTimeFilterWidget_startHourTooltipText();

    @DefaultStringValue("Start minute")
    String LayerTimeFilterWidget_startMinuteTooltipText();

    @DefaultStringValue("Current date")
    String LayerTimeFilterWidget_currentDateTooltipText();

    @DefaultStringValue("Tot. Steps")
    String LayerTimeFilterWidget_stepTooltipText();

    @DefaultStringValue("Refresh (s)")
    String LayerTimeFilterWidget_refreshTooltipText();

    @DefaultStringValue("Enable to date")
    String LayerTimeFilterWidget_enableToDate();

    @DefaultStringValue("Show all")
    String LayerTimeFilterWidget_showAllTimeLayers();

    /**
     * start StylesLayerTabItem
     */
    @DefaultStringValue("Layer Styles")
    String StylesLayerTabItem_titleText();

    /**
     * start LayersInfoTabItem
     */
    @DefaultStringValue("Layer Info")
    String LayersInfoTabItem_titleText();

    /**
     * start FolderInfoTabItem
     */
    @DefaultStringValue("Folder Info")
    String FolderInfoTabItem_titleText();

    /**
     * start DisplayLayersTabItem
     */
    @DefaultStringValue("Display")
    String DisplayLayersTabItem_titleText();

    /**
     * start GPLayerStyleBinding
     */
    @DefaultStringValue("Select a style")
    String GPLayerStyleBinding_fieldSetHeadingText();

    @DefaultStringValue("Select a style...")
    String GPLayerStyleBinding_comboEmptyText();

    /**
     * start GPLayerInfoBinding
     */
    @DefaultStringValue("Server")
    String GPLayerInfoBinding_serverLabelText();

    @DefaultStringValue("Alias")
    String GPLayerInfoBinding_aliasLabelText();

    @DefaultStringValue("Title")
    String GPLayerInfoBinding_titleLabelText();

    @DefaultStringValue("Abstract")
    String GPLayerInfoBinding_abstractLabelText();

    /**
     * start GPLayerDisplayBinding
     */
    @DefaultStringValue("Limit by Scale")
    String GPLayerDisplayBinding_limitsByScaleFieldText();

    @DefaultStringValue("Remove Scale Limit")
    String GPLayerDisplayBinding_removeLimitsMessageTitleText();

    @DefaultStringValue("Are you sure you want to remove the scale limits?")
    String GPLayerDisplayBinding_removeLimitsMessageBodyText();

    @DefaultStringValue("Remove Limits")
    String GPLayerDisplayBinding_removeLimitsButtonText();

    @DefaultStringValue("Opacity")
    String GPLayerDisplayBinding_sliderMessageText();

    @DefaultStringValue("Min Scale 1")
    String GPLayerDisplayBinding_minScaleNumberFieldText();

    @DefaultStringValue("Max Scale 1")
    String GPLayerDisplayBinding_maxScaleNumberFieldText();

    @DefaultStringValue("Layer Opacity")
    String GPLayerDisplayBinding_opacityHeadingText();

    @DefaultStringValue("Layer Single Tile Request")
    String GPLayerDisplayBinding_singleTileRequestHeadingText();

    @DefaultStringValue("Choose Opacity Value for Layer")
    String GPLayerDisplayBinding_sliderDataText();

    /**
     * start GPFolderInfoBinding
     */
    @DefaultStringValue("Label")
    String GPFolderInfoBinding_labelFieldText();

    /**
     * start GPTreeStoreWidget
     */
    @DefaultStringValue("Problems on saving the new tree state after layers creation")
    String GPTreeStoreWidget_mementoFailMessageText();

    @DefaultStringValue("Layers saved successfully.")
    String GPTreeStoreWidget_mementoSuccessMessageText();

    @DefaultStringValue("Load Vector Layers in the Store")
    String GPTreeStoreWidget_progressBarMessageText();

    @DefaultStringValue("Copy")
    String GPTreeStoreWidget_copyStringText();

    @DefaultStringValue("Add Layers Notification")
    String GPTreeStoreWidget_renameAlertTitleText();

    /**
     * start GPLayerProgressBar
     */
    @DefaultStringValue("GPLayer ProgressBar")
    String GPLayerProgressBar_titleText();

    @DefaultStringValue("Loading...")
    String GPLayerProgressBar_progressText();

    /**
     * start GPProjectSearchPanel
     */
    @DefaultStringValue("Setting Default Project Error")
    String GPProjectSearchPanel_settingDefaultProjectErrorTitleText();

    @DefaultStringValue("Setting Default Project")
    String GPProjectSearchPanel_statusSettingDefaultProjectText();

    @DefaultStringValue("Default Project has already selected.")
    String GPProjectSearchPanel_alertDefaultProjectSelectedBodyText();

    @DefaultStringValue("GeoPlatform Alert Message")
    String GPProjectSearchPanel_alertDefaultProjectSelectedTitleText();

    @DefaultStringValue("Name")
    String GPProjectSearchPanel_listViewNameText();

    @DefaultStringValue("Properties")
    String GPProjectSearchPanel_listViewPropertiesText();

    @DefaultStringValue("Version")
    String GPProjectSearchPanel_listViewVersionText();

    @DefaultStringValue("Load on Tree")
    String GPProjectSearchPanel_selectButtonText();

    @DefaultStringValue("Find Project")
    String GPProjectSearchPanel_searchlabelText();

    @DefaultStringValue("Load Project Save Operations")
    String GPProjectSearchPanel_loadProjectVerifySaveOperationsTitleText();

    @DefaultStringValue("It is not possible to load the selected project without first saving the layer tree operations")
    String GPProjectSearchPanel_loadProjectVerifySaveOperationsMessageText();

    /**
     * start ProjectProperties
     */
    @DefaultStringValue("Creation Date")
    String GPProjectSearchPanel_listViewCreationDateText();

    @DefaultStringValue("Message")
    String GPProjectSearchPanel_listViewMessageText();

    /**
     * start EnumProjectMessage
     */
    @DefaultStringValue("Default Project set correctly")
    String EnumProjectMessage_defaultProjectText();

    /**
     * start ShareProjectPanel
     */
    @DefaultStringValue("Type the user to filter")
    String ShareProjectPanel_storeFilterEmptyText();

    @DefaultStringValue("Sharing User List")
    String ShareProjectPanel_toFilterLabelText();

    @DefaultStringValue("Organization Users")
    String ShareProjectPanel_fromFilterLabelText();

    @DefaultStringValue("GoTo Search Project")
    String ShareProjectPanel_cancelButtonText();

    @DefaultStringValue("Project Shared Users")
    String ShareProjectPanel_projectSharedUserLabelText();

    @DefaultStringValue("Organization Users")
    String ShareProjectPanel_organizationUserLabelText();

    @DefaultStringValue("Share Project to Users")
    String ShareProjectPanel_fieldSetHeadingText();

    @DefaultStringValue("Project")
    String ShareProjectPanel_projectNameLabelText();

    @DefaultStringValue("Owner")
    String ShareProjectPanel_ownerLabelText();

    @DefaultStringValue("Organization")
    String ShareProjectPanel_organizationLabelText();

    @DefaultStringValue("Share Save Operations")
    String ShareProjectPanel_shareVerifySaveOperationsTitleText();

    @DefaultStringValue("It is not possible to share the project without first saving the layer tree operations")
    String ShareProjectPanel_shareVerifySaveOperationsMessageText();

    /**
     * start LoadWmsGetMapFromUrlWidget
     */
    @DefaultStringValue("WMS URL is Syntactically Correct")
    String LoadWmsGetMapFromUrlWidget_suggestionURLOKText();

    @DefaultStringValue("URL must be start with \"http://\"")
    String LoadWmsGetMapFromUrlWidget_suggestionURLStartText();

    @DefaultStringValue("Problems on saving the new tree state after WMS creation")
    String LoadWmsGetMapFromUrlWidget_mementoFailMessageText();

    @DefaultStringValue("WMS saved successfully.")
    String LoadWmsGetMapFromUrlWidget_mementoSuccessMessageText();

    @DefaultStringValue("Added WMS on tree succesfully.")
    String LoadWmsGetMapFromUrlWidget_statusAddedWMSSuccessText();

    @DefaultStringValue("Adding WMS from URL")
    String LoadWmsGetMapFromUrlWidget_statusAddingWMSText();

    @DefaultStringValue("Add WMS from GetMap direct URL")
    String LoadWmsGetMapFromUrlWidget_headingText();

    /**
     * start GetMapKvpFieldSet
     */
    @DefaultStringValue("WMS GetMap from Kvp URL")
    String GetMapKvpFieldSet_fieldSetHeadingText();

    /**
     * start SimpleUrlTextFields
     */
    @DefaultStringValue("Layer Name")
    String SimpleUrlTextFields_layerNameText();

    @DefaultStringValue("The URL is empty")
    String SimpleUrlTextFields_checkURLEmptyText();

    @DefaultStringValue("The URL does not start with http or https")
    String SimpleUrlTextFields_checkURLProtocolText();

    @DefaultStringValue("The Layer Name is empty")
    String SimpleUrlTextFields_checkLayerNameText();

    /**
     * start GetMapSimpleFieldSet
     */
    @DefaultStringValue("WMS GetMap from simple URL")
    String GetMapSimpleFieldSet_fieldSetHeadingText();

    /**
     * start LoadKmlFromUrlWidget
     */
    @DefaultStringValue("Problems on saving the new tree state after KML creation")
    String LoadKmlFromUrlWidget_mementoFailMessageText();

    @DefaultStringValue("KML saved successfully.")
    String LoadKmlFromUrlWidget_mementoSuccessMessageText();

    @DefaultStringValue("Added KML on tree succesfully.")
    String LoadKmlFromUrlWidget_statusAddedKMLSuccessText();

    @DefaultStringValue("Adding KML from URL")
    String LoadKmlFromUrlWidget_statusAddingKMLText();

    @DefaultStringValue("Add KML from direct URL")
    String LoadKmlFromUrlWidget_headingText();

    @DefaultStringValue("Load KML from URL")
    String LoadKmlFromUrlWidget_fieldSetHeadingText();

    /**
     * start GPProjectManagementWidget
     */
    @DefaultStringValue("GeoPlatform Project Management")
    String GPProjectManagementWidget_headingText();

    /**
     * start AddLayerWidget
     */
    @DefaultStringValue("Function Disabled")
    String AddLayerWidget_errorDisabledFunctionText();

    @DefaultStringValue("Add Layers Window")
    String AddLayerWidget_headingText();

    /**
     * start AddFolderWidget
     */
    @DefaultStringValue("Problems on saving the new tree state after folder creation")
    String AddFolderWidget_saveFolderErrorBodyText();

    @DefaultStringValue("Save Folder Error")
    String AddFolderWidget_saveFolderErrorTitleText();

    @DefaultStringValue("Folders saved successfully.")
    String AddFolderWidget_statusSaveFolderSuccessText();

    @DefaultStringValue("Added folder on tree succesfully.")
    String AddFolderWidget_statusAddedFolderSuccessText();

    @DefaultStringValue("Adding Folder")
    String AddFolderWidget_statusAddingFolderText();

    @DefaultStringValue("Add Folder")
    String AddFolderWidget_headingText();

    @DefaultStringValue("Folder Name")
    String AddFolderWidget_fieldSetHeadingText();

    @DefaultStringValue("Folder")
    String AddFolderWidget_folderLabelText();

    /**
     * start ProjectBindingWidget
     */
    @DefaultStringValue("Project successfully Updated")
    String ProjectBindingWidget_updateProjectSuccessText();

    @DefaultStringValue("Project successfully added")
    String ProjectBindingWidget_addProjectSuccessText();

    @DefaultStringValue("Update Project Error")
    String ProjectBindingWidget_updateProjectErrorText();

    @DefaultStringValue("Add Project Error")
    String ProjectBindingWidget_addProjectErrorText();

    @DefaultStringValue("Add Project Widget")
    String ProjectBindingWidget_headingText();

    @DefaultStringValue("Project State")
    String ProjectBindingWidget_checkBoxGroupLabelText();

    @DefaultStringValue("Is Default")
    String ProjectBindingWidget_projectDefaultCheckLabelText();

    @DefaultStringValue("Project Name")
    String ProjectBindingWidget_projectFieldNameLabelText();

    @DefaultStringValue("Insert Name for Project (required)")
    String ProjectBindingWidget_projectFieldNameEmptyText();

    @DefaultStringValue("Project Description")
    String ProjectBindingWidget_projectFielDescriptionLabelText();

    @DefaultStringValue("Insert Description for Project (required)")
    String ProjectBindingWidget_projectFieldDescriptionEmptyText();

    @DefaultStringValue("Project Path Image")
    String ProjectBindingWidget_projectFielPathImageLabelText();

    @DefaultStringValue("Insert Path Image for Project (required)")
    String ProjectBindingWidget_projectFieldPathImageEmptyText();

    @DefaultStringValue("Project Settings")
    String ProjectBindingWidget_fieldSetHeadingText();

    /**
     * start GPLayerExpander
     */
    @DefaultStringValue("Add folder operation cancelled.")
    String GPLayerExpander_statusOperationCancelledText();

    /**
     * start GetMapLayerPlugin
     */
    @DefaultStringValue("Adds one or more WMS layers to the tree from Get Map direct URL")
    String GetMapLayerPlugin_tooltipText();

    @DefaultStringValue("Enabled only on Folder Selected")
    String GetMapLayerPlugin_pluginMessageToEnableText();

    /**
     * start AddRasterLayerPlugin
     */
    @DefaultStringValue("Adds one or more WFS layers to the tree from the get capabilities")
    String AddVectorLayerPlugin_tooltipText();

    /**
     * start AddRasterLayerPlugin
     */
    @DefaultStringValue("Adds one or more WMS layers to the tree from the get capabilities")
    String AddRasterLayerPlugin_tooltipText();

    /**
     * start RasterTreeNode
     */
    @DefaultStringValue("opacity")
    String RasterTreeNode_opacityText();

    @DefaultStringValue("max scale")
    String RasterTreeNode_maxScaleText();

    @DefaultStringValue("min scale")
    String RasterTreeNode_minScaleText();

    @DefaultStringValue("single tile request")
    String RasterTreeNode_singleTileRequestText();

    /**
     * start GPRootTreeNode
     */
    @DefaultStringValue("Geo-Platform Tree")
    String GPRootTreeNode_labelText();

    /**
     * start GPClientProject
     */
    @DefaultStringValue("DEFAULT PROJECT")
    String GPClientProject_defaultProjectLabelText();

    @DefaultStringValue("Is Shared")
    String GPClientProject_sharedLabelText();

    @DefaultStringValue("Project Visibility")
    String GPClientProject_checkGroupVisibilityLabelText();

    @DefaultStringValue("Is Internal")
    String GPClientProject_internalVisibilityLabelText();

    @DefaultStringValue("Is External")
    String GPClientProject_externalVisibilityLabelText();

    /**
     * start RefreshTimeComboBuilder
     */
    @DefaultStringValue("Refresh Time")
    String RefreshTimeComboBuilder_comboEmptyText();

    /**
     * start SaveTreeAction
     */
    @DefaultStringValue("Save Tree State")
    String SaveTreeAction_tooltipText();

    /**
     * start LoadKmlFromUrlTreeAction
     */
    @DefaultStringValue("Add WMS from URL")
    String LoadWmsGetMapFromUrlTreeAction_tooltipText();

    /**
     * start LoadKmlFromUrlTreeAction
     */
    @DefaultStringValue("Load KML from URL")
    String LoadKmlFromUrlTreeAction_tooltipText();

    /**
     * start AddVectorTreeAction
     */
    @DefaultStringValue("Add WFS")
    String AddVectorTreeAction_tooltipText();

    /**
     * start AddRasterTreeAction
     */
    @DefaultStringValue("Add WMS")
    String AddRasterTreeAction_tooltipText();

    /**
     * start AddLayersTreeAction
     */
    @DefaultStringValue("Add Layers")
    String AddLayersTreeAction_tooltipText();

    /**
     * start AddFolderTreeAction
     */
    @DefaultStringValue("Add Folder")
    String AddFolderTreeAction_tooltipText();

    /**
     * start DeleteRequestManager
     */
    @DefaultStringValue("Delete Layer")
    String DeleteRequestManager_confirmDeleteTitleText();

    @DefaultStringValue("Are you sure you want to delete the selected element(s)?")
    String DeleteRequestManager_confirmDeleteBodyText();

    /**
     * start DeleteLayerHandler
     */
    @DefaultStringValue("The selected layer was deleted succesfully")
    String DeleteLayerHandler_statusLayerDeletedText();

    @DefaultStringValue("Problems on saving the new tree state after deleting layer")
    String DeleteLayerHandler_errorSaveDeleteBodyText();

    @DefaultStringValue("Layer deleted successfully.")
    String DeleteLayerHandler_statusSaveDeleteSuccessText();

    /**
     * start DeleteFolderHandler
     */
    @DefaultStringValue("Elements deleted successfully.")
    String DeleteFolderHandler_statusSaveDeleteSuccessText();

    @DefaultStringValue("Problems on saving the new tree state after deleting elements")
    String DeleteFolderHandler_errorSaveDeleteBodyText();

    @DefaultStringValue("The selected folder was deleted succesfully")
    String DeleteFolderHandler_statusFolderDeletedText();

    /**
     * start ShareProjectCommandAction
     */
    @DefaultStringValue("Error Loading Project")
    String ShareProjectCommandAction_errorLoadingProjectText();

    /**
     * start DeleteProjectAction
     */
    @DefaultStringValue("Are you sure you want to delete the Selected Project ?")
    String DeleteProjectAction_confirmDeletionBodyText();

    @DefaultStringValue("Warning: you could not delete Default Project.")
    String DeleteProjectAction_warningDeletionBodyText();

    /**
     * start ShareProjectMenuAction
     */
    @DefaultStringValue("Error sharing the layer tree project")
    String ShareProjectMenuAction_statusErrorSharingText();

    @DefaultStringValue("Error Sharing")
    String ShareProjectMenuAction_errorSharingTitleText();

    @DefaultStringValue("Share Project")
    String ShareProjectMenuAction_titleText();

    @DefaultStringValue("Project Properties")
    String ShareProjectPropertiesMenuAction_titleText();

    @DefaultStringValue("Clone Project")
    String ShareCloneProjectMenuAction_titleText();

    /**
     * start RefreshLayerAction
     */
    @DefaultStringValue("Error setting the reload time")
    String RefreshLayerAction_statusReloadTimeErrorText();

    @DefaultStringValue("The Layer will not be reloaded any more")
    String RefreshLayerAction_statusStopReloadTimeText();

    /**
     * start LayerMenuAction
     */
    @DefaultStringValue("Layers")
    String LayerMenuAction_titleText();

    /**
     * start CreateFolderViewportAction
     */
    @DefaultStringValue("Expand Folder")
    String CreateFolderViewportAction_confirmExpandTitleText();

    @DefaultStringValue("The folder you are trying to put elements "
            + "must be expanded before the adding operation. "
            + "Do you want to expand it?")
    String CreateFolderViewportAction_confirmExpandBodyText();

    @DefaultStringValue("No Layer(s)")
    String CreateFolderViewportAction_errorNoLayerTitleText();

    @DefaultStringValue("The selected folder does not contains layer(s).\n"
            + "Impossible to create a viewport without layers.")
    String CreateFolderViewportAction_errorNoLayerBodyText();

    /**
     * start AddFolderMenuAction
     */
    @DefaultStringValue("Add Folder")
    String AddFolderMenuAction_titleText();

    /**
     * start LoadMenuProjects
     */
    @DefaultStringValue("Open Project")
    String LoadMenuProjects_titleText();

    /**
     * start ExportoToShpZip
     */
    @DefaultStringValue("The selected layer was not a vectorial layer")
    String ExportoToShpZip_warningBodyText();

    @DefaultStringValue("Clone Project Error")
    String CloneProjectWidget_cloneProjectErrorTitleText();

    @DefaultStringValue("Project Cloned")
    String CloneProjectWidget_cloneProjectSuccessTitleText();

    @DefaultStringValue("Success")
    String CloneProjectWidget_cloneSuccessTitleText();

    @DefaultStringValue("Reload from UI")
    String ShowProjectPropertiesWidget_checkBoxLabel();
}
