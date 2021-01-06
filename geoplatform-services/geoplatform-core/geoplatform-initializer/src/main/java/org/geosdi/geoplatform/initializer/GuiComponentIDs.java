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
package org.geosdi.geoplatform.initializer;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * @TODO Load IDs from menubar.properties, toolbar.properties and
 * contextmenu.properties
 *
 * @author Vincenzo Monteverde <vincenzo.monteverde@geosdi.org>
 */
public class GuiComponentIDs {

    /**
     * Tree Menu
     */
    public static final String EDIT_WSF = "EDIT_FEATURE";
    public static final String EXPORT = "EXPORT";

    /**
     * Menubar
     */
    public static final String MANAGE_PROJECTS = "manageProjects";
    public static final String INGEST_DATA = "ingestData";
    public static final String MANAGE_USERS = "manageUsers";
    public static final String MANAGE_ROLES = "manageRoles";
    public static final String ABOUT_GEOPLATFORM = "aboutGeoPlatform";
    public static final String SDI_MANAGEMENT = "sdiManagement";
    //
    public static final String SEARCH_AOE = "searchAOE";
    public static final String CLEAR_AOE = "clearAOE";
    //
    public static final String GEOCODING = "geocoding";
    public static final String ROUTING = "routing";
    public static final String LAYER_MENU = "layerMenu";
    //
    public static final String VIEWPORT = "viewport";
    public static final String GRATICULA = "graticula";
    //
    public static final String EXTERNAL_LINKS = "externalLinks";
    /**
     * Map Toolbar
     */
    public static final String GEO_PLATFORM_INFO_APP = "GeoPlatformInfoApp";
    //
    public static final String ZOOM_IN = "ZoomIn";
    public static final String ZOOM_OUT = "ZoomOut";
    public static final String ZOOM_PREVIOUS = "ZoomPrevious";
    public static final String ZOOM_NEXT = "ZoomNext";
    public static final String GET_FEATURE_INFO = "GetFeatureInfo";
    public static final String MEASURE = "Measure";
    public static final String MEASURE_AREA = "MeasureArea";
    //
    public static final String EDITOR_AOE = "editorAOE";
    public static final String DRAW_FEATURE = "drawFeature";
    public static final String DRAW_CIRCLE = "drawCircle";
    public static final String DRAW_LINE_FEATURE = "drawLineFeature";
    public static final String DRAW_POINT_FEATURE = "drawPointFeature";
    public static final String RESHAPE_FEATURE = "reshapeFeature";
    public static final String ROTATE_FEATURE = "rotateFeature";
    public static final String DRAG_FEATURE = "dragFeature";
    public static final String RESIZE_FEATURE = "resizeFeature";
    public static final String DELETE_FEATURE = "deleteFeature";
    public static final String GOTO_XY = "gotoXY";
    public static final String CHANGE_BASE_LAYER = "changeBaseLayer";
    public static final String WPS_OPERATIONS_BUTTON = "wpsOperationsButton";
    //
    public static final String CLEAR_MAP = "clearMap";
    public static final String GOOGLE_REVERSE_GEOCODING = "googleReverseGeocoding";
    public static final String YAHOO_REVERSE_GEOCODING = "yahooReverseGeocoding";
    public static final String REVERSE_GEOCODING_WPS = "reverseGeocodingWPS";
    public static final String NOTIFICATION_MENU = "notificationMenu";
    // via MenuUtility
    public static final String GOOGLE_ICON = "GOOGLE_ICON";
    public static final String USER_MENU = "USER_MENU";
    public static final String GOOGLE_SIGN_ON_BUTTON = "googleSignOnButton";
    public static final String USER_OPTIONS_BUTTON = "userOptionsButton";
    public static final String USER_LOGOUT = "userLogout";
    /**
     * Layer Toolbar
     */
    public static final String ADD_FOLDER = "ADD_FOLDER";
    public static final String ADD_LAYERS = "ADD_LAYERS";
    //
    public static final String DELETE_ELEMENT = "DELETE_ELEMENT";
    public static final String SAVE_TREE = "SAVE_TREE";
    public static final String PRINT_LAYERS = "PRINT_LAYERS";
    //
    /**
     * LAYER MENU
     */
    public static final String LAYER_BUILDER = "LAYER_BUILDER";
    public static final String CSV_IMPORTER = "CSV_IMPORTER";
    //
    public static final String EXPORT_PROJECT = "EXPORT_PROJECT";
    public static final String IMPORT_PROJECT = "IMPORT_PROJECT";
    public static final String PREVIEW_KML = "PREVIEW_KML";
    public static final String LAYER_STYLER = "LAYER_STYLER";
    public static final String MAP_LITE_EXPORTER = "EXPORT_MAPLITE_PROJECT";
    public static final String OPEN_CATALOG_FINDER = "OPEN_CATALOG";
    /**
     * Collections of GuiComponent
     */
    public static final List<String> LIST_ALL;
    public static final Map<String, Boolean> MAP_USER;
    public static final Map<String, Boolean> MAP_VIEWER;

    private GuiComponentIDs() {
    }

    static {
        /**
         * ALL
         */
        List<String> all = Lists.<String>newArrayList();
        // Menubar
        all.add(MANAGE_PROJECTS);
        all.add(INGEST_DATA);
        all.add(MANAGE_USERS);
        all.add(MANAGE_ROLES);
        all.add(ABOUT_GEOPLATFORM);
        all.add(SEARCH_AOE);
        all.add(CLEAR_AOE);
        all.add(GEOCODING);
        all.add(ROUTING);
        all.add(LAYER_MENU);
        all.add(GRATICULA);
        all.add(VIEWPORT);
        // Map Toolbar
        all.add(GEO_PLATFORM_INFO_APP);
        all.add(EXTERNAL_LINKS);
        all.add(SDI_MANAGEMENT);
        all.add(ZOOM_IN);
        all.add(ZOOM_OUT);
        all.add(ZOOM_PREVIOUS);
        all.add(ZOOM_NEXT);
        all.add(GET_FEATURE_INFO);
        all.add(MEASURE);
        all.add(MEASURE_AREA);
        all.add(EDITOR_AOE);
        all.add(DRAW_FEATURE);
        all.add(DRAW_CIRCLE);
        all.add(DRAW_LINE_FEATURE);
        all.add(DRAW_POINT_FEATURE);
        all.add(RESHAPE_FEATURE);
        all.add(ROTATE_FEATURE);
        all.add(DRAG_FEATURE);
        all.add(RESIZE_FEATURE);
        all.add(DELETE_FEATURE);
        all.add(GOTO_XY);
        all.add(CHANGE_BASE_LAYER);
        all.add(WPS_OPERATIONS_BUTTON);
        all.add(CLEAR_MAP);
        all.add(GOOGLE_REVERSE_GEOCODING);
        all.add(YAHOO_REVERSE_GEOCODING);
        all.add(REVERSE_GEOCODING_WPS);
        all.add(NOTIFICATION_MENU);
        all.add(GOOGLE_ICON);
        all.add(USER_MENU);
        all.add(GOOGLE_SIGN_ON_BUTTON);
        all.add(USER_OPTIONS_BUTTON);
        all.add(USER_LOGOUT);
        //
        all.add(ADD_FOLDER);
        all.add(ADD_LAYERS);
        all.add(DELETE_ELEMENT);
        all.add(SAVE_TREE);
        all.add(PRINT_LAYERS);
        all.add(EXPORT_PROJECT);
        all.add(IMPORT_PROJECT);
        all.add(PREVIEW_KML);
        all.add(LAYER_STYLER);
        all.add(MAP_LITE_EXPORTER);
        all.add(OPEN_CATALOG_FINDER);
        all.add(EDIT_WSF);
        all.add(EXPORT);
        all.add(LAYER_BUILDER);
        all.add(CSV_IMPORTER);
        //
        LIST_ALL = Collections.<String>unmodifiableList(all);
        /**
         * USER
         */
        Map<String, Boolean> mapUser = Maps.<String, Boolean>newHashMap();
        // Menubar
        mapUser.put(MANAGE_PROJECTS, Boolean.TRUE);
        mapUser.put(INGEST_DATA, Boolean.TRUE);
        mapUser.put(ABOUT_GEOPLATFORM, Boolean.TRUE);
        mapUser.put(SEARCH_AOE, Boolean.TRUE);
        mapUser.put(CLEAR_AOE, Boolean.TRUE);
        mapUser.put(GEOCODING, Boolean.TRUE);
        mapUser.put(ROUTING, Boolean.TRUE);
        mapUser.put(LAYER_MENU, Boolean.TRUE);
        mapUser.put(GRATICULA, Boolean.TRUE);
        mapUser.put(VIEWPORT, Boolean.TRUE);
        // Map Toolbar
        mapUser.put(GEO_PLATFORM_INFO_APP, Boolean.TRUE);
        mapUser.put(ZOOM_IN, Boolean.TRUE);
        mapUser.put(ZOOM_OUT, Boolean.TRUE);
        mapUser.put(ZOOM_PREVIOUS, Boolean.TRUE);
        mapUser.put(ZOOM_NEXT, Boolean.TRUE);
        mapUser.put(GET_FEATURE_INFO, Boolean.TRUE);
        mapUser.put(MEASURE, Boolean.TRUE);
        mapUser.put(MEASURE_AREA, Boolean.TRUE);
        mapUser.put(EDITOR_AOE, Boolean.TRUE);
        mapUser.put(DRAW_FEATURE, Boolean.TRUE);
        mapUser.put(DRAW_CIRCLE, Boolean.TRUE);
        mapUser.put(DRAW_LINE_FEATURE, Boolean.TRUE);
        mapUser.put(DRAW_POINT_FEATURE, Boolean.TRUE);
        mapUser.put(RESHAPE_FEATURE, Boolean.TRUE);
        mapUser.put(ROTATE_FEATURE, Boolean.TRUE);
        mapUser.put(DRAG_FEATURE, Boolean.TRUE);
        mapUser.put(RESIZE_FEATURE, Boolean.TRUE);
        mapUser.put(DELETE_FEATURE, Boolean.TRUE);
        mapUser.put(GOTO_XY, Boolean.TRUE);
        mapUser.put(CHANGE_BASE_LAYER, Boolean.TRUE);
        mapUser.put(CLEAR_MAP, Boolean.TRUE);
        mapUser.put(GOOGLE_REVERSE_GEOCODING, Boolean.TRUE);
        mapUser.put(YAHOO_REVERSE_GEOCODING, Boolean.TRUE);
        mapUser.put(REVERSE_GEOCODING_WPS, Boolean.TRUE);
        mapUser.put(NOTIFICATION_MENU, Boolean.TRUE);
        mapUser.put(GOOGLE_ICON, Boolean.TRUE);
        mapUser.put(USER_MENU, Boolean.TRUE);
        mapUser.put(GOOGLE_SIGN_ON_BUTTON, Boolean.TRUE);
        mapUser.put(USER_OPTIONS_BUTTON, Boolean.TRUE);
        mapUser.put(USER_LOGOUT, Boolean.TRUE);
        //
        mapUser.put(ADD_FOLDER, Boolean.TRUE);
        mapUser.put(ADD_LAYERS, Boolean.TRUE);
        mapUser.put(DELETE_ELEMENT, Boolean.TRUE);
        mapUser.put(SAVE_TREE, Boolean.TRUE);
        mapUser.put(PRINT_LAYERS, Boolean.TRUE);
        mapUser.put(EXPORT_PROJECT, Boolean.TRUE);
        mapUser.put(IMPORT_PROJECT, Boolean.TRUE);
        mapUser.put(PREVIEW_KML, Boolean.TRUE);
        mapUser.put(LAYER_STYLER, Boolean.FALSE);
        mapUser.put(MAP_LITE_EXPORTER, Boolean.TRUE);
        mapUser.put(OPEN_CATALOG_FINDER, Boolean.TRUE);
        //
        MAP_USER = Collections.<String, Boolean>unmodifiableMap(mapUser);
        /**
         * VIEWER
         */
        Map<String, Boolean> mapViewer = Maps.<String, Boolean>newHashMap();
        // Menubar
        mapViewer.put(MANAGE_PROJECTS, Boolean.TRUE);
        mapViewer.put(ABOUT_GEOPLATFORM, Boolean.TRUE);
        mapViewer.put(SEARCH_AOE, Boolean.TRUE);
        mapViewer.put(CLEAR_AOE, Boolean.TRUE);
        mapViewer.put(GEOCODING, Boolean.TRUE);
        mapViewer.put(ROUTING, Boolean.TRUE);
        mapViewer.put(LAYER_MENU, Boolean.TRUE);
        mapViewer.put(GRATICULA, Boolean.TRUE);
        mapViewer.put(VIEWPORT, Boolean.TRUE);
        // Toolbar
        mapViewer.put(GEO_PLATFORM_INFO_APP, Boolean.TRUE);
        mapViewer.put(ZOOM_IN, Boolean.TRUE);
        mapViewer.put(ZOOM_OUT, Boolean.TRUE);
        mapViewer.put(ZOOM_PREVIOUS, Boolean.TRUE);
        mapViewer.put(ZOOM_NEXT, Boolean.TRUE);
        mapViewer.put(GET_FEATURE_INFO, Boolean.TRUE);
        mapViewer.put(MEASURE, Boolean.TRUE);
        mapViewer.put(MEASURE_AREA, Boolean.TRUE);
        mapViewer.put(EDITOR_AOE, Boolean.TRUE);
        mapViewer.put(DRAW_FEATURE, Boolean.TRUE);
        mapViewer.put(DRAW_CIRCLE, Boolean.TRUE);
        mapViewer.put(DRAW_LINE_FEATURE, Boolean.TRUE);
        mapViewer.put(DRAW_POINT_FEATURE, Boolean.TRUE);
        mapViewer.put(RESHAPE_FEATURE, Boolean.TRUE);
        mapViewer.put(ROTATE_FEATURE, Boolean.TRUE);
        mapViewer.put(DRAG_FEATURE, Boolean.TRUE);
        mapViewer.put(RESIZE_FEATURE, Boolean.TRUE);
        mapViewer.put(DELETE_FEATURE, Boolean.TRUE);
        mapViewer.put(GOTO_XY, Boolean.TRUE);
        mapViewer.put(CHANGE_BASE_LAYER, Boolean.TRUE);
        mapViewer.put(CLEAR_MAP, Boolean.TRUE);
        mapViewer.put(GOOGLE_REVERSE_GEOCODING, Boolean.TRUE);
        mapViewer.put(YAHOO_REVERSE_GEOCODING, Boolean.TRUE);
        mapViewer.put(REVERSE_GEOCODING_WPS, Boolean.TRUE);
        mapViewer.put(NOTIFICATION_MENU, Boolean.TRUE);
        mapViewer.put(GOOGLE_ICON, Boolean.TRUE);
        mapViewer.put(USER_MENU, Boolean.TRUE);
        mapViewer.put(GOOGLE_SIGN_ON_BUTTON, Boolean.TRUE);
        mapViewer.put(USER_OPTIONS_BUTTON, Boolean.TRUE);
        mapViewer.put(USER_LOGOUT, Boolean.TRUE);
        //
        mapViewer.put(ADD_FOLDER, Boolean.TRUE);
        mapViewer.put(ADD_LAYERS, Boolean.TRUE);
        mapViewer.put(DELETE_ELEMENT, Boolean.TRUE);
        mapViewer.put(SAVE_TREE, Boolean.FALSE);
        mapViewer.put(PRINT_LAYERS, Boolean.TRUE);
        // Import/Export because Viewer must not save projects
        mapViewer.put(EXPORT_PROJECT, Boolean.FALSE);
        mapViewer.put(IMPORT_PROJECT, Boolean.FALSE);
        mapViewer.put(PREVIEW_KML, Boolean.TRUE);
        mapViewer.put(LAYER_STYLER, Boolean.FALSE);
        mapViewer.put(MAP_LITE_EXPORTER, Boolean.TRUE);
        mapViewer.put(OPEN_CATALOG_FINDER, Boolean.TRUE);
        //
        MAP_VIEWER = Collections.<String, Boolean>unmodifiableMap(mapViewer);
    }

}