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
import com.google.gwt.i18n.client.Constants.DefaultStringValue;
import com.google.gwt.i18n.client.ConstantsWithLookup;

/**
 * @author Nazzareno Sileno - CNR IMAA geoSDI Group
 * @email nazzareno.sileno@geosdi.org
 */
public interface BaseMenuGenericToolConstants extends ConstantsWithLookup {

    public final static BaseMenuGenericToolConstants INSTANCE = GWT.create(BaseMenuGenericToolConstants.class);

    //Menu voices
    @DefaultStringValue("About")
    String aboutGeoPlatformText();
    
    @DefaultStringValue("Geocoding")
    String geocodingText();
    
    @DefaultStringValue("Viewport")
    String viewportText();
    
    @DefaultStringValue("Routing")
    String routingText();
    
    @DefaultStringValue("Manage Users")
    String manageUsersText();
    
    @DefaultStringValue("Manage Roles")
    String manageRolesText();
    
    @DefaultStringValue("Options")
    String userOptionsButtonText();
    
    @DefaultStringValue("Edit WFS Mode")
    String editFeatureText();
   
    @DefaultStringValue("Google Earth Builder")
    String googleSignOnButtonText();
    
    @DefaultStringValue("Logout")
    String userLogoutText();

    @DefaultStringValue("Layers")
    String layerMenuText();
    
    @DefaultStringValue("Ingest Data")
    String ingestDataText();

    @DefaultStringValue("Manage Projects")
    String manageProjectsText();

    @DefaultStringValue("Layer Properties")
    String layerPropertiesText();

//  Layer Tree Menu i18n
    @DefaultStringValue("Add Folder")
    String treeMenuAddFolderText();

    @DefaultStringValue("Share Project")
    String treeMenuShareProjectText();

    @DefaultStringValue("Paste Menu")
    String treeMenuPasteText();

    @DefaultStringValue("Rename Folder")
    String treeMenuRenameFolderText();

    @DefaultStringValue("Create Viewport")
    String treeMenuCreateViewportText();

    @DefaultStringValue("Zoom to layer extend")
    String treeMenuZoomToLayerExtendText();

    @DefaultStringValue("Get Legend")
    String treeMenuGetLegendText();

    @DefaultStringValue("Export")
    String treeMenuExportText();

    @DefaultStringValue("Export To KML")
    String treeMenuExportToKMLText();

    @DefaultStringValue("Export To PDF")
    String treeMenuExportToPDFText();

    @DefaultStringValue("Export To TIFF")
    String treeMenuExportToTIFFText();

    @DefaultStringValue("Export To Shp-Zip")
    String treeMenuExportToShpZipText();

    @DefaultStringValue("Export GML")
    String treeMenuExportGMLText();

    @DefaultStringValue("Export To GML 2")
    String treeMenuExportToGML2Text();

    @DefaultStringValue("Export To GML 3.1")
    String treeMenuExportToGML3_1Text();

    @DefaultStringValue("Export To GML 3.2")
    String treeMenuExportToGML3_2Text();

    @DefaultStringValue("Export To CSV")
    String treeMenuExportToCSVText();

    @DefaultStringValue("Export To GeoJSON")
    String treeMenuExportToGeoJSONText();

    @DefaultStringValue("Export To RSS")
    String treeMenuExportToRSSText();

    @DefaultStringValue("CQL Filter")
    String treeMenuCQLFilterText();

    @DefaultStringValue("SLD")
    String sldGroupMenuText();

    @DefaultStringValue("Add/Modify CQL Filter")
    String treeMenuCQLAddModifyFilterText();

    @DefaultStringValue("Remove CQL Filter")
    String treeMenuCQLRemoveFilterText();

    @DefaultStringValue("TIME Filter")
    String treeMenuTimeFilterText();

    @DefaultStringValue("Add/Modify Time Filter")
    String treeMenuTimeAddModifyFilterText();

    @DefaultStringValue("Remove Time Filter")
    String treeMenuTimeRemoveFilterText();

    @DefaultStringValue("Copy Layer")
    String treeMenuCopyLayerText();

    @DefaultStringValue("Import Style")
    String importLayerStyleText();

    @DefaultStringValue("Classify Layer Style")
    String classifyLayerStyleText();

    @DefaultStringValue("Rasterize Layer Style")
    String rasterizeLayerStyleText();

    @DefaultStringValue("Edit WFS Mode")
    String treeMenuEditWFSModeText();

    @DefaultStringValue("Layer Properties")
    String treeMenuLayerPropertiesText();

    @DefaultStringValue("Copy Layers")
    String treeMenuCopyLayersText();

    @DefaultStringValue("Delete Selected Elements")
    String treeMenuDeleteSelectedElementsText();
}
