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
public interface BasicWidgetConstants extends Constants {

    public BasicWidgetConstants INSTANCE = GWT.create(BasicWidgetConstants.class);

    @DefaultStringValue("About Geo-Platform")
    String GPAboutWidget_headingText();

    /**
     * Start GPTreeExpanderNotifier
     */
    @DefaultStringValue("Expand Folder")
    String GPTreeExpanderNotifier_confirmMessageTitleText();

    @DefaultStringValue("The folder you are trying to put elements "
            + "must be expanded before the adding operation. "
            + "Do you want to expand it?")
    String GPTreeExpanderNotifier_confirmMessageBodyText();

    /**
     * Start GPSecurityWidget
     */
    @DefaultStringValue("Login Service")
    String GPSecurityWidget_headingText();

    @DefaultStringValue("Username")
    String GPSecurityWidget_usernameText();

    @DefaultStringValue("Password")
    String GPSecurityWidget_passwordText();

    /**
     * Start GPAdvancedSecurityWidget
     */
    @DefaultStringValue("Entering into geoSDI")
    String GPAdvancedSecurityWidget_geoSDIAccessText();

    @DefaultStringValue("Sign in")
    String GPAdvancedSecurityWidget_signInText();

    @DefaultStringValue("Download")
    String GPAdvancedSecurityWidget_downloadText();

    @DefaultStringValue("the Android application for alerts.")
    String GPAdvancedSecurityWidget_androidAppText();

    /**
     * Start ComboSearchWindow
     */
    @DefaultStringValue("Reload Results after Combo Collapse")
    String ComboSearchWindow_displayResultText();

    /**
     * Start ProgressBar
     */
    @DefaultStringValue("Starting up...")//Avvio in corso...
    String ProgressBar_startingUpText();

    /**
     * Start MenuUtility
     */
    @DefaultStringValue("Choose a Date")
    String MenuUtility_chooseADateText();

    /**
     * Start GPEPSGContentPanel
     */
    @DefaultStringValue("EPSG")
    String GPEPSGContentPanel_EPSGTextFieldLabelText();

    @DefaultStringValue("Common EPSG")
    String GPEPSGContentPanel_EPSGComboFieldLabelText();

    /**
     * Start EPSGUtility
     */
    @DefaultStringValue("Choose EPSG...")
    String EPSGUtility_chooseEPSGText();

    /**
     * Start GPRoutingManagerWidget
     */
    @DefaultStringValue("GeoPlatform Routing Module")
    String GPRoutingManagerWidget_infoMessageTitleText();

    @DefaultStringValue("Red square represents possible Routing Requests Area.")
    String GPRoutingManagerWidget_infoActivateMessageBodyText();

    @DefaultStringValue("Routing Module Deactivated.")
    String GPRoutingManagerWidget_infoDeactivateMessageBodyText();

    /**
     * Start GPRoutingStartPoint
     */
    @DefaultStringValue("GeoPlatform Routing")
    String GPRoutingStartPoint_errorMessageTitleText();

    @DefaultStringValue("The chosen location is out of Range.")
    String GPRoutingStartPoint_errorMessageBodyText();

    /**
     * Start GPRoutingEndPoint
     */
    @DefaultStringValue("GeoPlatform Routing")
    String GPRoutingEndPoint_errorMessageTitleText();

    @DefaultStringValue("The chosen location is out of Range.")
    String GPRoutingEndPoint_errorMessageBodyText();

    /**
     * Start GeoPlatformSearchWindow
     */
    @DefaultStringValue("Search")
    String GeoPlatformSearchWindow_headingText();

    @DefaultStringValue("Find")
    String GeoPlatformSearchWindow_searchFieldLabelText();

    @DefaultStringValue("Connection to the Server")
    String GeoPlatformSearchWindow_connectionBusyStatusText();

    /**
     * Start GeoPlatformSearchPanel
     */
    @DefaultStringValue("Search")
    String GeoPlatformSearchPanel_headingText();

    @DefaultStringValue("Find")
    String GeoPlatformSearchPanel_searchFieldLabelText();

    @DefaultStringValue("Connection to the Server")
    String GeoPlatformSearchPanel_connectionBusyStatusText();

    /**
     * Start GPFileUploader
     */
    @DefaultStringValue("Uploader Progress Bar")
    String GPFileUploader_progressBarText();
    
    @DefaultStringValue("Upload in progress...")
    String GPFileUploader_uploadInProgressText();

    @DefaultStringValue("Failed to Upload File")
    String GPFileUploader_failedStatusText();

    @DefaultStringValue("Upload Error")
    String GPFileUploader_failedErrorMessageTitleText();

    @DefaultStringValue("This kind of file isn't allowed!")
    String GPFileUploader_failedErrorKindFileBodyText();

    @DefaultStringValue("Error on file upload")
    String GPFileUploader_failedErrorGenericBodyText();

    @DefaultStringValue("Uploaded File Succesfully")
    String GPFileUploader_successStatusText();
    
    /**
     * start GotoXYWidget
     */
    @DefaultStringValue("X")
    String GotoXYWidget_xFieldLabelText();

    @DefaultStringValue("Y")
    String GotoXYWidget_yFieldLabelText();

    @DefaultStringValue("Find Position")
    String GotoXYWidget_headingText();

    @DefaultStringValue("Goto X - Y")
    String GotoXYWidget_fieldSetHeadingText();
    
    @DefaultStringValue("Goto X - Y")
    String GotoXYAction_tooltipText();
}
