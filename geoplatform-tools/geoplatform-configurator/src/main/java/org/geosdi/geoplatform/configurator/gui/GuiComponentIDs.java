/*
 *  geo-platform
 *  Rich webgis framework
 *  http://geo-platform.org
 * ====================================================================
 *
 * Copyright (C) 2008-2012 geoSDI Group (CNR IMAA - Potenza - ITALY).
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
package org.geosdi.geoplatform.configurator.gui;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * TODO Load IDs from menubar.properties and toolbar.properties
 *
 * @author Vincenzo Monteverde <vincenzo.monteverde@geosdi.org>
 */
public class GuiComponentIDs {

    /**
     * Menubar
     */
    public static final String LOAD_PROJECTS = "loadProjects";
    public static final String INGEST_DATA = "ingestData";
    public static final String MANAGE_USERS = "manageUsers";
    public static final String ABOUT_GEOPLATFORM = "aboutGeoPlatform";
    //
    public static final String SEARCH_AOE = "searchAOE";
    public static final String CLEAR_AOE = "clearAOE";
    //
    public static final String GEOCODING = "geocoding";
    public static final String ROUTING = "routing";
    public static final String LAYER_MENU = "layerMenu";
    //
    public static final String GRATICULA = "graticula";
    /** SIGV-LAYER MENU **/
    public static final String LAYER_MENU_SIGV = "layerMenu-sigv";
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
    public static final String DRAW_FEATURE = "drawFeature";
    public static final String DRAW_LINE_FEATURE = "drawLineFeature";
    public static final String DRAW_POINT_FEATURE = "drawPointFeature";
    public static final String RESHAPE_FEATURE = "reshapeFeature";
    public static final String ROTATE_FEATURE = "rotateFeature";
    public static final String DRAG_FEATURE = "dragFeature";
    public static final String RESIZE_FEATURE = "resizeFeature";
    public static final String DELETE_FEATURE = "deleteFeature";
    //
    public static final String CLEAR_MAP = "clearMap";
    public static final String REVERSE_GEOCODING = "reverseGeocoding";
    public static final String REVERSE_GEOCODING_WPS = "reverseGeocodingWPS";
    // SIGV COMMIT BUTTON
    public static final String COMMIT_ACTION = "Commit";
    // via MenuUtility
    public static final String GOOGLE_ICON = "GOOGLE_ICON";
    public static final String USER_MENU = "USER_MENU";
    public static final String GOOGLE_SIGN_ON_BUTTON = "googleSignOnButton";
    public static final String USER_OPTIONS_BUTTON = "userOptionsButton";
    public static final String USER_LOGOUT = "userLogout";
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
        List<String> all = new ArrayList<String>();
        // Menubar
        all.add(LOAD_PROJECTS);
        all.add(INGEST_DATA);
        all.add(MANAGE_USERS);
        all.add(ABOUT_GEOPLATFORM);
        all.add(SEARCH_AOE);
        all.add(CLEAR_AOE);
        all.add(GEOCODING);
        all.add(ROUTING);
        all.add(LAYER_MENU);
        all.add(GRATICULA);
        all.add(LAYER_MENU_SIGV);
        // Map Toolbar
        all.add(GEO_PLATFORM_INFO_APP);
        all.add(ZOOM_IN);
        all.add(ZOOM_OUT);
        all.add(ZOOM_PREVIOUS);
        all.add(ZOOM_NEXT);
        all.add(GET_FEATURE_INFO);
        all.add(MEASURE);
        all.add(MEASURE_AREA);
        all.add(DRAW_FEATURE);
        all.add(DRAW_LINE_FEATURE);
        all.add(DRAW_POINT_FEATURE);
        all.add(RESHAPE_FEATURE);
        all.add(ROTATE_FEATURE);
        all.add(DRAG_FEATURE);
        all.add(RESIZE_FEATURE);
        all.add(DELETE_FEATURE);
        all.add(CLEAR_MAP);
        all.add(REVERSE_GEOCODING);
        all.add(REVERSE_GEOCODING_WPS);
        all.add(COMMIT_ACTION);
        all.add(GOOGLE_ICON);
        all.add(USER_MENU);
        all.add(GOOGLE_SIGN_ON_BUTTON);
        all.add(USER_OPTIONS_BUTTON);
        all.add(USER_LOGOUT);
        //
        LIST_ALL = Collections.unmodifiableList(all);
        /**
         * USER
         */
        Map<String, Boolean> mapUser = new HashMap<String, Boolean>();
        // Menubar
        mapUser.put(LOAD_PROJECTS, true);
        mapUser.put(INGEST_DATA, true);
        mapUser.put(ABOUT_GEOPLATFORM, true);
        mapUser.put(SEARCH_AOE, true);
        mapUser.put(CLEAR_AOE, true);
        mapUser.put(GEOCODING, true);
        mapUser.put(ROUTING, true);
        mapUser.put(LAYER_MENU, true);
        mapUser.put(GRATICULA, true);
        // Map Toolbar
        mapUser.put(GEO_PLATFORM_INFO_APP, true);
        mapUser.put(ZOOM_IN, true);
        mapUser.put(ZOOM_OUT, true);
        mapUser.put(ZOOM_PREVIOUS, true);
        mapUser.put(ZOOM_NEXT, true);
        mapUser.put(GET_FEATURE_INFO, true);
        mapUser.put(MEASURE, true);
        mapUser.put(MEASURE_AREA, true);
        mapUser.put(DRAW_FEATURE, true);
        mapUser.put(DRAW_LINE_FEATURE, true);
        mapUser.put(DRAW_POINT_FEATURE, true);
        mapUser.put(RESHAPE_FEATURE, true);
        mapUser.put(ROTATE_FEATURE, true);
        mapUser.put(DRAG_FEATURE, true);
        mapUser.put(RESIZE_FEATURE, true);
        mapUser.put(DELETE_FEATURE, true);
        mapUser.put(CLEAR_MAP, true);
        mapUser.put(REVERSE_GEOCODING, true);
        mapUser.put(REVERSE_GEOCODING_WPS, true);
        mapUser.put(GOOGLE_ICON, true);
        mapUser.put(USER_MENU, true);
        mapUser.put(GOOGLE_SIGN_ON_BUTTON, true);
        mapUser.put(USER_OPTIONS_BUTTON, true);
        mapUser.put(USER_LOGOUT, true);
        //
        MAP_USER = Collections.unmodifiableMap(mapUser);
        /**
         * VIEWER
         */
        Map<String, Boolean> mapViewer = new HashMap<String, Boolean>();
        // Menubar
        mapViewer.put(LOAD_PROJECTS, false);
        mapViewer.put(ABOUT_GEOPLATFORM, true);
        mapViewer.put(SEARCH_AOE, true);
        mapViewer.put(CLEAR_AOE, true);
        mapViewer.put(GEOCODING, true);
        mapViewer.put(ROUTING, true);
        mapViewer.put(LAYER_MENU, true);
        mapViewer.put(GRATICULA, true);
        // Toolbar
        mapViewer.put(GEO_PLATFORM_INFO_APP, true);
        mapViewer.put(ZOOM_IN, true);
        mapViewer.put(ZOOM_OUT, true);
        mapViewer.put(ZOOM_PREVIOUS, true);
        mapViewer.put(ZOOM_NEXT, true);
        mapViewer.put(GET_FEATURE_INFO, true);
        mapViewer.put(MEASURE, true);
        mapViewer.put(MEASURE_AREA, true);
        mapViewer.put(DRAW_FEATURE, true);
        mapViewer.put(DRAW_LINE_FEATURE, true);
        mapViewer.put(DRAW_POINT_FEATURE, true);
        mapViewer.put(RESHAPE_FEATURE, true);
        mapViewer.put(ROTATE_FEATURE, true);
        mapViewer.put(DRAG_FEATURE, true);
        mapViewer.put(RESIZE_FEATURE, true);
        mapViewer.put(DELETE_FEATURE, true);
        mapViewer.put(CLEAR_MAP, true);
        mapViewer.put(REVERSE_GEOCODING, true);
        mapViewer.put(REVERSE_GEOCODING_WPS, true);
        mapViewer.put(GOOGLE_ICON, true);
        mapViewer.put(USER_MENU, true);
        mapViewer.put(GOOGLE_SIGN_ON_BUTTON, true);
        mapViewer.put(USER_OPTIONS_BUTTON, true);
        mapViewer.put(USER_LOGOUT, true);
        //
        MAP_VIEWER = Collections.unmodifiableMap(mapViewer);
    }
}
