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
package org.geosdi.geoplatform.gui.client.config.provider.text;

import com.extjs.gxt.ui.client.event.ComponentEvent;
import com.extjs.gxt.ui.client.event.KeyListener;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.i18n.client.LocaleInfo;
import org.geosdi.geoplatform.gui.client.delegate.IWFSGeocodingDelegate;
import org.geosdi.geoplatform.gui.client.i18n.WFSTWidgetConstants;
import org.geosdi.geoplatform.gui.client.puregwt.geocoding.GeocodingHandlerManager;
import org.geosdi.geoplatform.gui.client.puregwt.geocoding.event.ClearGeocodingGridEvent;
import org.geosdi.geoplatform.gui.client.puregwt.geocoding.event.MaskGeocodingGridEvent;
import org.geosdi.geoplatform.gui.client.puregwt.geocoding.event.RemoveMarkerEvent;
import org.geosdi.geoplatform.gui.client.puregwt.wfs.event.WFSZoomEvent;
import org.geosdi.geoplatform.gui.client.service.request.GPGeocodingAddressRequestDTO;
import org.geosdi.geoplatform.gui.client.service.response.FeatureCollectionResponse;
import org.geosdi.geoplatform.gui.configuration.GPSecureStringTextField;
import org.geosdi.geoplatform.gui.puregwt.GPEventBus;

import javax.inject.Inject;
import javax.inject.Provider;

/**
 * @author Vito Salvia - CNR IMAA geoSDI Group
 * @email vito.salvia@gmail.com
 */
public class GeocodingTextFieldProvider implements Provider<GPSecureStringTextField> {

    private static final MaskGeocodingGridEvent MASK_GEOCODING_GRID_EVENT = new MaskGeocodingGridEvent();
    private static final RemoveMarkerEvent REMOVE_MARKER_EVENT = new RemoveMarkerEvent();
    private static final ClearGeocodingGridEvent CLEAR_GEOCODING_GRID_EVENT = new ClearGeocodingGridEvent();
    private static final WFSZoomEvent WFS_ZOOM_EVENT = new WFSZoomEvent();
    //
    private final IWFSGeocodingDelegate geocodingDelegate;
    private final WFSTWidgetConstants wfstWidgetConstants;
    @Inject
    private GPEventBus eventBus;

    @Inject
    public GeocodingTextFieldProvider(IWFSGeocodingDelegate geocodingDelegate, WFSTWidgetConstants wfstWidgetConstants) {
        this.geocodingDelegate = geocodingDelegate;
        this.wfstWidgetConstants = wfstWidgetConstants;
    }

    @Override
    public GPSecureStringTextField get() {
        final GPSecureStringTextField geocoding = new GPSecureStringTextField();
        geocoding.setFieldLabel("Find");
        geocoding.setEmptyText(this.wfstWidgetConstants.searchAddressLabel());

        geocoding.addKeyListener(new KeyListener() {

            @Override
            public void componentKeyUp(ComponentEvent event) {
                if (((event.getKeyCode() == KeyCodes.KEY_BACKSPACE)
                        || (event.getKeyCode() == KeyCodes.KEY_DELETE))
                        && (geocoding.getValue() == null)) {
                    GeocodingHandlerManager.fireEvent(REMOVE_MARKER_EVENT);
                    GeocodingHandlerManager.fireEvent(CLEAR_GEOCODING_GRID_EVENT);
                    eventBus.fireEvent(WFS_ZOOM_EVENT);
                }
            }

            @Override
            public void componentKeyPress(ComponentEvent event) {
                if ((event.getKeyCode() == KeyCodes.KEY_ENTER)
                        && (!geocoding.getValue().equals(""))) {
                    GeocodingHandlerManager.fireEvent(MASK_GEOCODING_GRID_EVENT);
                    geocodingDelegate.searchAddress(new GPGeocodingAddressRequestDTO(LocaleInfo.getCurrentLocale().getLocaleName(),geocoding.getValue()));
                }
            }

        });
        return geocoding;
    }

}
