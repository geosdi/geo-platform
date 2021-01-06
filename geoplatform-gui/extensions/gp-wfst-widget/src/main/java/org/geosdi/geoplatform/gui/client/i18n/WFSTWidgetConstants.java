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
import com.google.gwt.i18n.client.ConstantsWithLookup;

/**
 * @author Nazzareno Sileno - CNR IMAA geoSDI Group
 * @email nazzareno.sileno@geosdi.org
 */
public interface WFSTWidgetConstants extends ConstantsWithLookup {

    WFSTWidgetConstants INSTANCE = GWT.create(WFSTWidgetConstants.class);

    @DefaultStringValue("DescribeFeatureType Service")
    String describeFeatureTypeServiceText();

    /**
     * start GPDescribeFeatureDispatcher
     */
    @DefaultStringValue("DescribeFeatureType Service Error")
    String GPDescribeFeatureDispatcher_errorDescribeFeatureTypeTitleText();

    @DefaultStringValue("Error on WFS DescribeFeatureType request")
    String GPDescribeFeatureDispatcher_errorDescribeFeatureTypeRequestText();

    /**
     * start VectorLayerProvider
     */
    @DefaultStringValue("Basic GeoPlatform WFS")
    String VectorLayerProvider_basicVectorNameText();

    /**
     * start FeatureProtocolCRUDOptionsProvider
     */
    @DefaultStringValue("Transaction Error")
    String FeatureProtocolCRUDOptionsProvider_transactionErrorText();

    /**
     * start RotateFeatureToggleButtonProvider
     */
    @DefaultStringValue("Rotate")
    String RotateFeatureToggleButtonProvider_titleText();

    /**
     * start ResizeFeatureToggleButtonProvider
     */
    @DefaultStringValue("Resize")
    String ResizeFeatureToggleButtonProvider_titleText();

    /**
     * start ReshapeFeatureToggleButtonProvider
     */
    @DefaultStringValue("Reshape")
    String ReshapeFeatureToggleButtonProvider_titleText();

    /**
     * start EditFeatureToggleButtonProvider
     */
    @DefaultStringValue("Create New Feature")
    String EditFeatureToggleButtonProvider_titleText();

    @DefaultStringValue("Erase Feature")
    String EraseFeatureToggleButtonProvider_titleText();

    /**
     * start DragFeatureToggleButtonProvider
     */
    @DefaultStringValue("Drag")
    String DragFeatureToggleButtonProvider_titleText();

    /**
     * start EditWFSAction
     */
    @DefaultStringValue("Edit WFS Mode")
    String EditWFSAction_titleText();

    /**
     * start FeatureAttributesWindow
     */
    @DefaultStringValue("Feature Attribute Window")
    String FeatureAttributesWindow_headingText();

    @DefaultStringValue("Please, verify all the attribute before save")
    String FeatureAttributesWindow_infoVerifyAttribute();

    /**
     * start ShowFeaturesWFSAction
     */
    @DefaultStringValue("Show Features")
    String showFeaturesTitleText();

    @DefaultStringValue("Get all Features")
    String getAllFeatures();

    @DefaultStringValue("Insert the address")
    String searchAddressLabel();

    @DefaultStringValue("Search")
    String searchLabel();

    @DefaultStringValue("Location")
    String locationLabel();

    @DefaultStringValue("Loading")
    String loadingLabel();
}
