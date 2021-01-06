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
public interface CatalogFinderConstants extends Constants {

    public CatalogFinderConstants INSTANCE = GWT.create(CatalogFinderConstants.class);

    /**
     * start CatalogFinderWidget
     */
    @DefaultStringValue("GeoPlatform Catalog Finder UI")
    String CatalogFinderWidget_headingText();

    /**
     * start GPCatalogExpander
     */
    @DefaultStringValue("Search Layers")
    String GPCatalogExpander_progressBarText();

    @DefaultStringValue("Add layer operation cancelled.")
    String GPCatalogExpander_statusOperationCancelledText();

    @DefaultStringValue("Catalog Finder")
    String GPCatalogExpander_alertMessageTitleText();

    @DefaultStringValue("You can put layers into folders only."
            + "\n Please close window and select the correct node.")
    String GPCatalogExpander_alertMessageBodyText();

    /**
     * start SearchTabItem
     */
    @DefaultStringValue("Search")
    String SearchTabItem_tabItemTitleText();

    /**
     * start MetadataTabItem
     */
    @DefaultStringValue("Full Metadata")
    String MetadataTabItem_tabItemTitleText();

    /**
     * start CatalogTreeLayerWidgetSupport
     */
    @DefaultStringValue("Operations with selected")
    String CatalogTreeLayerWidgetSupport_operationLabelText();

    /**
     * start CatalogSearchWidget
     */
    @DefaultStringValue("Search Text")
    String CatalogSearchWidget_searchLabelText();

    @DefaultStringValue("Error Search")
    String CatalogSearchWidget_errorSearchTitleText();

    @DefaultStringValue("Search Options")
    String CatalogSearchWidget_optionsLabelText();

    @DefaultStringValue("Select at least one option")
    String CatalogSearchWidget_optionsCheckboxValidateText();

    @DefaultStringValue("Title")
    String CatalogSearchWidget_titleLabelText();

    @DefaultStringValue("Abstract")
    String CatalogSearchWidget_abstractLabelText();

    @DefaultStringValue("Keywords")
    String CatalogSearchWidget_keywordsLabelText();

    @DefaultStringValue("Select/Deselect all")
    String CatalogSearchWidget_allSelectLabelText();

    /**
     * start CatalogSearchResultWidget
     */
    @DefaultStringValue("Search Result")
    String CatalogSearchResultWidget_resultLabelText();

    /**
     * start CatalogRecordsToolTip
     */
    @DefaultStringValue("The Following Metadata")
    String CatalogRecordsToolTip_buildTooltipHeaderText();

    @DefaultStringValue("Can't be added on LayerTree, because it")
    String CatalogRecordsToolTip_metadataNotAddedText();

    @DefaultStringValue("hasn't the GetMap or WMSGetCapabilities Protocol"
            + " defined.")
    String CatalogRecordsToolTip_metadataNotGetMapProtocolText();

    @DefaultStringValue("Catalog Metadata ToolTip")
    String CatalogRecordsToolTip_tooltipTitleText();

    /**
     * Start RecordsContainer
     */
    @DefaultStringValue("Abstract")
    String RecordsContainer_xTemplateAbstractText();

    @DefaultStringValue("Keywords")
    String RecordsContainer_xTemplateKeywordsText();

    @DefaultStringValue("Title")
    String RecordsContainer_titleColumnHeaderText();

    @DefaultStringValue("Loading Records")
    String RecordsContainer_gridLoadingMaskText();

    @DefaultStringValue("Records correctly loaded")
    String RecordsContainer_eventRecordsCorrectlyLoaderText();

    @DefaultStringValue("There are no records")
    String RecordsContainer_eventNoRecordsLoaderText();

    @DefaultStringValue("The services are down, report to the administator.")
    String RecordsContainer_errorServiceDownBodyText();

    @DefaultStringValue("Connection error")
    String RecordsContainer_errorServiceDownTitleText();

    /**
     * Start CSWServerFormWidget
     */
    @DefaultStringValue("Server")
    String CSWServerFormWidget_fieldSetHeadingText();

    @DefaultStringValue("Alias")
    String CSWServerFormWidget_aliasFieldLabelText();

    @DefaultStringValue("Enter a CSW server alias")
    String CSWServerFormWidget_aliasEmptyText();

    @DefaultStringValue("Insert a alias for the CSW server")
    String CSWServerFormWidget_aliasTooltipText();

    @DefaultStringValue("Address")
    String CSWServerFormWidget_urlFieldLabelText();

    @DefaultStringValue("Enter a CSW server URL")
    String CSWServerFormWidget_urlEmptyText();

    @DefaultStringValue("Insert a URL for the CSW server")
    String CSWServerFormWidget_urlTooltipText();

    @DefaultStringValue("URL must be start with \"http://\" or \"https://\"")
    String CSWServerFormWidget_urlValidateMessageText();

    @DefaultStringValue("Insert CSW Server")
    String CSWServerFormWidget_headingText();

    @DefaultStringValue("Adding CSW Server")
    String CSWServerFormWidget_saveStatusBusyText();

    @DefaultStringValue("Error on saving server")
    String CSWServerFormWidget_eventErrorSavingServerText();

    @DefaultStringValue("Server correctly saved")
    String CSWServerFormWidget_eventCorrectlySavedServerText();

    /**
     * Start CatalogComboBoxComponent
     */
    @DefaultStringValue("Type:")
    String CatalogComboBoxComponent_typeLabelText();

    /**
     * Start CatalogCheckBoxComponent
     */
    @DefaultStringValue("Activate Spatial Filter")
    String CatalogCheckBoxComponent_activateFilterText();

    /**
     * Start CatalogBBoxComponent
     */
    @DefaultStringValue("Max Lat")
    String CatalogBBoxComponent_maxLatLabelText();

    @DefaultStringValue("Min Lat")
    String CatalogBBoxComponent_minLatLabelText();

    @DefaultStringValue("Min Lon")
    String CatalogBBoxComponent_minLonLabelText();

    @DefaultStringValue("Max Lon")
    String CatalogBBoxComponent_maxLonLabelText();

    /**
     * Start CSWServerPaginationContainer
     */
    @DefaultStringValue("Find Server")
    String CSWServerPaginationContainer_searchFieltLabelText();

    @DefaultStringValue("New Server")
    String CSWServerPaginationContainer_newServerButtonText();

    @DefaultStringValue("Create a new CSW Server")
    String CSWServerPaginationContainer_newServerButtonTooltipText();

    @DefaultStringValue("Delete Server")
    String CSWServerPaginationContainer_deleteServerButtonText();

    @DefaultStringValue("Delete the selected CSW Server")
    String CSWServerPaginationContainer_deleteServerButtonTooltipText();

    @DefaultStringValue("Alias")
    String CSWServerPaginationContainer_aliasColumnHeaderText();

    @DefaultStringValue("Title")
    String CSWServerPaginationContainer_titleColumnHeaderText();

    @DefaultStringValue("Loading CSW servers")
    String CSWServerPaginationContainer_gridLoadingMaskText();

    @DefaultStringValue("Deleting server")
    String CSWServerPaginationContainer_gridDeletingMaskText();

    @DefaultStringValue("There are no catalogs")
    String CSWServerPaginationContainer_eventNoCatalogsText();

    @DefaultStringValue("Catalogs correctly loaded")
    String CSWServerPaginationContainer_eventLoadedCatalogsText();

    @DefaultStringValue("Error on deleting server")
    String CSWServerPaginationContainer_eventErrorDeletingServerText();

    @DefaultStringValue("Server correctly deleted")
    String CSWServerPaginationContainer_eventCorrectlyDeletedServerText();

    @DefaultStringValue("Connection error")
    String CSWServerPaginationContainer_errorLoaderMessageTitleText();

    @DefaultStringValue("The services are down, report to the administator.")
    String CSWServerPaginationContainer_errorLoaderMessageBodyText();

    /**
     * Start TimeAccordionWidget
     */
    @DefaultStringValue("Filter by Time")
    String TimeAccordionWidget_headingText();

    @DefaultStringValue("From:&nbsp;")
    String TimeAccordionWidget_multidatesFromText();

    @DefaultStringValue("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;To:&nbsp;")
    String TimeAccordionWidget_multidatesToText();

    @DefaultStringValue("End date")
    String TimeAccordionWidget_endDateTooltipText();

    @DefaultStringValue("Start date")
    String TimeAccordionWidget_startDateTooltipText();

    @DefaultStringValue("Temporal Extent")
    String TimeAccordionWidget_temporalExtentBoxLabelText();

    @DefaultStringValue("Anytime")
    String TimeAccordionWidget_anyTimeBoxLabelText();

    /**
     * Start SpatialAreaAccordionWidget
     */
    @DefaultStringValue("Filter by Area")
    String SpatialAreaAccordionWidget_headingText();

    /**
     * Start CatalogAccordionWidget
     */
    @DefaultStringValue("Filter by Catalogue")
    String CatalogAccordionWidget_headingText();

    /**
     * Start AddCatalogFinderLayerPlugin
     */
    @DefaultStringValue("Adds one or more WMS layers to the tree from Metadata")
    String AddCatalogFinderLayerPlugin_tooltipText();

    /**
     * Start WmsFromMetadataTreeAction
     */
    @DefaultStringValue("Add WMS from Metadata")
    String WmsFromMetadataTreeAction_tooltipText();

    /**
     * Start ShowFullMetadataAction
     */
    @DefaultStringValue("View Full Metadata")
    String ShowFullMetadataAction_titleActionText();

    @DefaultStringValue("GetRecordById Request error")
    String ShowFullMetadataAction_errorRecordRequestText();

    @DefaultStringValue("GetRecordById Request executed")
    String ShowFullMetadataAction_recordRequestExecutedText();

    @DefaultStringValue("Full Metadata")//Metadata completo
    String ShowFullMetadataAction_windowText();

    /**
     * start CatalogGridLayersWidget
     */
    @DefaultStringValue("Filter in ignore case")
    String CatalogGridLayersWidget_filterTooltipText();

    @DefaultStringValue("Layer Name")
    String CatalogGridLayersWidget_layerNameHeaderText();

    @DefaultStringValue("Title")
    String CatalogGridLayersWidget_titleHeaderText();

    /**
     * start CatalogWMSCapabilitiesExpander
     */
    @DefaultStringValue("Search Layers")
    String CatalogWMSCapabilitiesExpander_progressBarSearchLayersText();

    @DefaultStringValue("Add layer operation cancelled.")
    String CatalogWMSCapabilitiesExpander_statusAddLayerOperationCancelledText();

    /**
     * start CatalogWMSCapabilitiesWidget
     */
    @DefaultStringValue("Catalog WMS Capabilities")
    String CatalogWMSCapabilitiesWidget_headingText();

    @DefaultStringValue("Layers have been loaded correctly by the service")
    String CatalogWMSCapabilitiesWidget_statusLayerLoadedCorrectlyText();

    @DefaultStringValue("Google sign on required")
    String googleSignOnRequiredTitleText();

    @DefaultStringValue(
            "Is necessary to sign on Google account for access the Google Earth Builder functionality")
    String googleSignOnRequiredBodyText();

}
