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
public interface ServerModuleConstants extends Constants {

    public static final ServerModuleConstants INSTANCE =
            GWT.create(ServerModuleConstants.class);

    @DefaultStringValue("Server")
    String serverText();

    @DefaultStringValue("Server Service")
    String serverServiceText();

    @DefaultStringValue("Loading Server...")
    String loadingServersText();

    @DefaultStringValue("An Error occured loading Servers.")
    String errorLoadingServerBodyText();

    @DefaultStringValue("Add Server")
    String addServerText();

    @DefaultStringValue("Saving Server")
    String savingServerText();

    @DefaultStringValue("Server Alias")
    String serverAliasText();

    @DefaultStringValue("Username")
    String usernameText();

    @DefaultStringValue("Password")
    String passwordText();

    @DefaultStringValue("URL Server")
    String serverURLText();

    @DefaultStringValue("Secure")
    String secureText();

    @DefaultStringValue("Proxy")
    String proxyText();

    @DefaultStringValue("Google sign on required")
    String googleSignOnRequiredTitleText();

    @DefaultStringValue("Is necessary to sign on Google account for access the Google Earth Builder functionality")
    String googleSignOnRequiredBodyText();

    /**
     * start GridLayersWidget
     */
    @DefaultStringValue("Filter in ignore case")
    String GridLayersWidget_filterTooltipText();

    @DefaultStringValue("Layer Name")
    String GridLayersWidget_layerNameHeaderText();

    @DefaultStringValue("Title")
    String GridLayersWidget_titleHeaderText();

    
    /**
     * start GPCapabilitiesWidget
     */
    @DefaultStringValue("Server Capabilities")
    String GPCapabilitiesWidget_headingText();
    
    /**
     * start DisplayServerWidget
     */
    @DefaultStringValue("Layers have been loaded correctly by the service")
    String DisplayServerWidget_statusLayerLoadedCorrectlyText();

    @DefaultStringValue("There are no Servers.")
    String DisplayServerWidget_alerThereAreNoServerText();

    @DefaultStringValue("Error opening Get Capabilities window.")
    String DisplayServerWidget_statusErrorOpeningWindowText();

    @DefaultStringValue("Select a Server...")
    String DisplayServerWidget_comboServerEmptyText();

    @DefaultStringValue("Manage Servers")
    String DisplayServerWidget_manageServerButtonText();

    /**
     * start ManageServerWidget
     */
    @DefaultStringValue("Server added succesfully")
    String ManageServerWidget_serverAddedSuccesfullyText();

    @DefaultStringValue("Server Deleted Succesfully")
    String ManageServerWidget_statusServerDeletedSuccesfullyText();

    @DefaultStringValue("Error on Deleting Server")
    String ManageServerWidget_errorDeletingTitleText();

    @DefaultStringValue("Error on Saving Server")
    String ManageServerWidget_errorSavingTitleText();

    @DefaultStringValue("Server Manager")
    String ManageServerWidget_headingText();

    @DefaultStringValue("New Server")
    String ManageServerWidget_newServerText();

    @DefaultStringValue("Filter")
    String ManageServerWidget_serverFilterText();

    @DefaultStringValue("Type the server to search")
    String ManageServerWidget_typeServerToSearchText();

    @DefaultStringValue("Delete Server")
    String ManageServerWidget_deleteButtonText();

    /**
     * start AddServerWidget
     */
    @DefaultStringValue("Adding Server")
    String AddServerWidget_statusAddingServerText();

    @DefaultStringValue("Save Server")
    String AddServerWidget_saveServerText();

    @DefaultStringValue("Server Present")
    String AddServerWidget_serverPresentText();

    @DefaultStringValue("Address")
    String AddServerWidget_serverAddressText();

    @DefaultStringValue("Name")
    String AddServerWidget_serverNameText();

    @DefaultStringValue("Insert a valid WMS Url and name for the server.")
    String AddServerWidget_tooltipServerInsertionText();

    /**
     * start GPServerExpander
     */
    @DefaultStringValue("Search Layers")
    String GPServerExpander_progressBarSearchLayersText();

    @DefaultStringValue("Add layer operation cancelled.")
    String GPServerExpander_statusAddLayerOperationCancelledText();
}
