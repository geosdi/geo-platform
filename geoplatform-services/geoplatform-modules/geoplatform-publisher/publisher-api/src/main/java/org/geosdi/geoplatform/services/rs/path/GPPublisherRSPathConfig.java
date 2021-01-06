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
package org.geosdi.geoplatform.services.rs.path;

/**
 *
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public final class GPPublisherRSPathConfig {

    public static final String DEFAULT_PUBLISHER_RS_SERVICE_PATH = "/";

    public static final String GP_PUBLISHER_SERVICE_RS_PATH = "/jsonPublisher";

    /**
     * PREVIEW PATH
     */
    private static final String PREVIEW_BASE_PATH = "/preview/";
    public static final String ANALAYZE_ZIP_EPSG_PATH = PREVIEW_BASE_PATH 
            + "analyzeZIPEPSG";
    public static final String PROCESS_EPSG_RESULT_PATH = PREVIEW_BASE_PATH +
            "processEPSGResult";
    public static final String LOAD_STYLE_PATH = PREVIEW_BASE_PATH +
            "loadStyle";
    public static final String DESCRIBE_FEATURE_TYPE_PATH = PREVIEW_BASE_PATH +
            "describeFeatureType";
    public static final String PUBLISH_STYLE_PATH = PREVIEW_BASE_PATH +
            "publishStyle";
    public static final String UPDATE_STYLE_PATH = PREVIEW_BASE_PATH + "updateStyle";
    public static final String EXISTS_STYLE_PATH = PREVIEW_BASE_PATH +
            "existsStyle";
    public static final String GET_WORKSPACE_NAMES_PATH = PREVIEW_BASE_PATH +
            "getWorkspaceNames";
    public static final String CREATE_WORKSPACE_PATH = PREVIEW_BASE_PATH +
            "createWorkspace";
    public static final String ANALYZE_TIF_IN_PREVIEW_PATH = PREVIEW_BASE_PATH
            + "analyzeTIFInPreview";
    public static final String GET_PREVIEW_DATA_STORES_PATH = PREVIEW_BASE_PATH 
            + "getPreviewDataStores";
    public static final String PUBLISH_PATH = PREVIEW_BASE_PATH + "publish";
    public static final String PUBLISH_ALL_PATH = PREVIEW_BASE_PATH +
            "publishAll";
    public static final String PUBLISH_ALL_OF_PREVIEW_PATH = PREVIEW_BASE_PATH +
            "publishAllofPreview";
    public static final String UNIQUE_VALUES_PATH = PREVIEW_BASE_PATH +
            "uniqueValue";

    private GPPublisherRSPathConfig() {
    }

}
