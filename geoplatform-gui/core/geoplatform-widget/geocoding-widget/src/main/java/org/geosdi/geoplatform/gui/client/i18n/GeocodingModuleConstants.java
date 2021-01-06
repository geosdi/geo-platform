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
public interface GeocodingModuleConstants extends Constants {

    public GeocodingModuleConstants INSTANCE = GWT.create(GeocodingModuleConstants.class);

    /**
     * start GeocodingGridWidget
     */
    @DefaultStringValue("Please, insert the address and the location")
    String GeocodingGridWidget_searchFieldEmptyText();
    
    @DefaultStringValue("Provider")
    String GeocodingGridWidget_providerText();
    
    @DefaultStringValue("Locations")
    String GeocodingGridWidget_locationsFieldHeadingText();

    @DefaultStringValue("Find")
    String GeocodingGridWidget_searchFieldLabelText();

    @DefaultStringValue("Search")
    String GeocodingGridWidget_searchFieldHeadingText();

    @DefaultStringValue("Location")
    String GeocodingGridWidget_columnConfigHeaderText();

    @DefaultStringValue("Loading Locations")
    String GeocodingGridWidget_gridLoadingMaskText();

    @DefaultStringValue("Geocoding - Service")
    String GeocodingGridWidget_messageTitleText();

    @DefaultStringValue("Geocoding Service is demanding too much time, probably "
                    + "the connection problem, do you want to stop it?")
    String GeocodingGridWidget_checkStatusBodyText();

    @DefaultStringValue("There are no results for your search.")
    String GeocodingGridWidget_noResultBodyText();

    @DefaultStringValue("There is a problem with Geocoding Service Provider")
    String GeocodingGridWidget_failureFindLocationBodyText();
    
    /**
     * start GeocoderManagementWidget
     */
    @DefaultStringValue("GeoPlatfom Geocoding Widget")
    String GeocoderManagementWidget_headingText();
    
    /**
     * start YahooDispatcher
     */
    @DefaultStringValue("An error occurred processing the request")
    String YahooDispatcher_requestFailureText();
    
    /**
     * start ReverseGeoCoderYahooWidget
     */
    @DefaultStringValue("Yahoo Reverse Geocoding")
    String ReverseGeoCoderYahooWidget_infoMessageTitleText();

    @DefaultStringValue("Click on the map to have Information.")
    String ReverseGeoCoderYahooWidget_registerChildBodyText();

    @DefaultStringValue("Reverse Geocoding Control Deactivated.")
    String ReverseGeoCoderYahooWidget_unregisterChildBodyText();

    @DefaultStringValue("Server busy.")
    String ReverseGeoCoderYahooWidget_errorBodyText();

    /**
     * start UserOptionsMemberGeocoding
     */
    @DefaultStringValue("Geocoding")
    String UserOptionsMemberGeocoding_memberNameText();

    @DefaultStringValue("Set TYPE: Google or WPS")
    String UserOptionsMemberGeocoding_labelPanelText();

    /**
     * start ReverseGeoCoderGoogleWidget
     */
    @DefaultStringValue("Google Reverse Geocoding")
    String ReverseGeoCoderGoogleWidget_infoMessageTitleText();

    @DefaultStringValue("Click on the map to have Information.")
    String ReverseGeoCoderGoogleWidget_registerChildBodyText();

    @DefaultStringValue("Reverse Geocoding Control Deactivated.")
    String ReverseGeoCoderGoogleWidget_unregisterChildBodyText();

    @DefaultStringValue("Server busy.")
    String ReverseGeoCoderGoogleWidget_errorBodyText();

    /**
     * start GoogleDispatcher
     */
    @DefaultStringValue("An error occurred processing the request")
    String GoogleDispatcher_requestFailureText();

    /**
     * start GPGoogleGeocoderPlugin
     */
    @DefaultStringValue("Geoplatform geocoding")
    String GPGoogleGeocoderPlugin_tabTitleText();

    /**
     * start YahooReverseGeocodingAction
     */
    @DefaultStringValue("Yahoo Reverse Geocoding")
    String YahooReverseGeocodingAction_tooltipText();

    /**
     * start GoogleReverseGeocodingAction
     */
    @DefaultStringValue("Google Reverse Geocoding")
    String GoogleReverseGeocodingAction_tooltipText();

    /**
     * start GeocodingMenuAction
     */
    @DefaultStringValue("Geocoding")
    String GeocodingMenuAction_titleText();
}
