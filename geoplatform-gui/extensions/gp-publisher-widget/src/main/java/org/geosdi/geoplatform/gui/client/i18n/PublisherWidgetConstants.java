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
public interface PublisherWidgetConstants extends Constants {

    public static final PublisherWidgetConstants INSTANCE
            = GWT.create(PublisherWidgetConstants.class);

    @DefaultStringValue("Error Publishing")
    String errorPublishingText();

    @DefaultStringValue("Error Publishing previewed shape.")
    String statusErrorShapePublishingText();

    /**
     * start AddWorkspaceWidget
     */
    @DefaultStringValue("Workspace Creation")
    String AddWorkspaceWidget_fieldSetHeadingText();

    @DefaultStringValue("Workspace Name")
    String AddWorkspaceWidget_workspaceNameText();
    
    @DefaultStringValue("Name")
    String AddWorkspaceWidget_nameText();

    /**
     * start PublisherProgressBar
     */
    @DefaultStringValue("Publisher Progress Bar")
    String PublisherProgressBar_progressBarText();

    /**
     * start UploadKmlWidget
     */
    @DefaultStringValue("Upload KML file")
    String UploadKmlWidget_headingText();

    @DefaultStringValue("Load KML from URL")
    String UploadKmlWidget_fieldSetHeadingText();

    /**
     * start GPPublisherWidget
     */
    @DefaultStringValue("Shape\\s published successfully: remember to save the new tree state.")
    String GPPublisherWidget_statusShapePublishedSuccesfullyText();

    @DefaultStringValue("Realod without result: may be some problems?")
    String GPPublisherWidget_htmlPanelNoResultReloadText();

    @DefaultStringValue("Upload Shape Error")
    String GPPublisherWidget_errorUploadShapeTitleText();

    @DefaultStringValue("File uploader")
    String GPPublisherWidget_southPanelHeadingText();

    @DefaultStringValue("Choose Workspace")
    String GPPublisherWidget_chooseWorkspaceComboBoxText();

    @DefaultStringValue("If enabled: the cluster configuration will be reloaded after layers publishing")
    String GPPublisherWidget_toggleButtonClusterReloadTooltipText();

    @DefaultStringValue("Shape Preview")
    String GPPublisherWidget_shapePreviewTitleText();

    @DefaultStringValue("Error Reloading Cluster")
    String GPPublisherWidget_errorReloadClusterTitleText();

    @DefaultStringValue("Geotiff - Shape Files Uploader")
    String GPPublisherWidget_headingText();

    @DefaultStringValue("Select a file to show in preview")
    String GPPublisherWidget_uploadMessageText();

    @DefaultStringValue("Add workspace")
    String GPPublisherWidget_addWorkspaceButtonTooltip();

    /**
     * Start LayerAttributesComboBox
     */
    @DefaultStringValue("Workspaces")
    String WorkspacesComboBox_fieldLabelText();

    /**
     * start GPKmlWidget
     */
    @DefaultStringValue("Preview KML file from URL")
    String GPKmlWidget_headingText();

    @DefaultStringValue("Error previewed KML file")
    String GPKmlWidget_statusErrorPreviewText();

    @DefaultStringValue("Insert a KML URL for preview")
    String GPKmlWidget_southPanelHeadingText();

    @DefaultStringValue("KML URL")
    String GPKmlWidget_urlLabelText();

    @DefaultStringValue("Error checking KML URL")
    String GPKmlWidget_errorCheckingKMLURLText();

    /**
     * start EPSGTablePanel
     */
    @DefaultStringValue("Processing Data")
    String EPSGTablePanel_processingDataProgressBarText();

    @DefaultStringValue("New Name")
    String EPSGTablePanel_columnNewNameText();

    @DefaultStringValue("Publish Action")
    String EPSGTablePanel_columnPublishActionText();

    @DefaultStringValue("Feature EPSG Code Analisys")
    String EPSGTablePanel_headingText();

    @DefaultStringValue("Fill all the EPSG codes to proceed")
    String EPSGTablePanel_statusEPSGFillRequiredText();

    @DefaultStringValue("Proceed to the feature preview")
    String EPSGTablePanel_processEPSGButtonTooltipText();

    @DefaultStringValue("EPSG:UNKNOWN")
    String EPSGTablePanel_epsgTextFieldEmptyText();

    @DefaultStringValue("EPSG Code")
    String EPSGTablePanel_columnEPSGCodeText();

    @DefaultStringValue("Feature Name")
    String EPSGTablePanel_columnFeatureNameText();

    /**
     * start UploadShapeLayerPlugin
     */
    @DefaultStringValue("Adds one or more layers to the tree from shape or geotiff files")
    String UploadShapeLayerPlugin_tooltipText();

    @DefaultStringValue("Ever enabled")
    String UploadShapeLayerPlugin_messageToEnableText();

    /**
     * start UploadShapeAction
     */
    @DefaultStringValue("Upload Geotiff - Shape Files")
    String UploadShapeAction_tooltipText();

    /**
     * start UploadKmlTreeAction
     */
    @DefaultStringValue("Upload KML")
    String UploadKmlTreeAction_tooltipText();

    /**
     * start PreviewKmlTreeAction
     */
    @DefaultStringValue("Preview KML")
    String PreviewKmlTreeAction_tooltipText();
}
