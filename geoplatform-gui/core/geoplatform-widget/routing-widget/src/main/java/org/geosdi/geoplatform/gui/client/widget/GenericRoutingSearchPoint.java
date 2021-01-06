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
package org.geosdi.geoplatform.gui.client.widget;

import com.extjs.gxt.ui.client.event.ComponentEvent;
import com.extjs.gxt.ui.client.event.KeyListener;
import com.google.gwt.event.dom.client.KeyCodes;
import org.geosdi.geoplatform.gui.client.command.FindLocationsByAddressRequest;
import org.geosdi.geoplatform.gui.client.command.FindLocationsResponse;
import org.geosdi.geoplatform.gui.client.i18n.RoutingModuleConstants;
import org.geosdi.geoplatform.gui.client.i18n.windows.WindowsConstants;
import org.geosdi.geoplatform.gui.client.model.GeocodingBean;
import org.geosdi.geoplatform.gui.client.model.GeocodingKeyValue;
import org.geosdi.geoplatform.gui.client.widget.search.ComboSearchWidget;
import org.geosdi.geoplatform.gui.client.widget.search.routing.GPComboBox;
import org.geosdi.geoplatform.gui.command.api.GPClientCommand;
import org.geosdi.geoplatform.gui.command.api.GPClientCommandExecutor;
import org.geosdi.geoplatform.gui.configuration.message.GeoPlatformMessage;
import org.geosdi.geoplatform.gui.puregwt.routing.event.CleanComboEventHandler;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 *
 */
public abstract class GenericRoutingSearchPoint extends ComboSearchWidget<GeocodingBean>
        implements CleanComboEventHandler {

    private final FindLocationsByAddressRequest request = new FindLocationsByAddressRequest();

    /**
     * (non-Javadoc)
     *
     * @see
     * org.geosdi.geoplatform.gui.client.widget.search.ComboSearchWidget#setWidgetProperties()
     */
    @Override
    public void setWidgetProperties() {
        this.combo.setDisplayField(GeocodingKeyValue.DESCRIPTION.getValue());
        this.combo.setHideTrigger(true);

        this.combo.setUseQueryCache(false);

        this.combo.setWidth(200);

        this.combo.addKeyListener(new KeyListener() {

            @Override
            public void componentKeyPress(ComponentEvent event) {
                if ((event.getKeyCode() == KeyCodes.KEY_ENTER)
                        && (combo.getRawValue().length() >= 4)
                        && (combo.getSelection().isEmpty())) {
                    dispatchRequest();
                }
            }

        });
    }

    /**
     * Send Request to Geocoding Module to fill the Combo with Results
     *
     */
    public void dispatchRequest() {
        loadImage(TypeImage.IMAGE_LOADING, true);
        this.clearStore();

        this.request.setAddress(combo.getRawValue());

        GPClientCommandExecutor.executeCommand(
                new GPClientCommand<FindLocationsResponse>() {

            private static final long serialVersionUID = -7919006248337641101L;

            {
                super.setCommandRequest(request);
            }

            @Override
            public void onCommandSuccess(FindLocationsResponse response) {
                if (response.getResult().size() > 0) {
                    GeoPlatformMessage.infoMessage(
                            RoutingModuleConstants.INSTANCE.geocodingServiceText(),
                            WindowsConstants.INSTANCE.resultsLoadedWithSuccessText());
                    loadImage(TypeImage.IMAGE_RESULT_FOUND, true);
                    fillStore(response.getResult());
                    expand();
                } else {
                    GeoPlatformMessage.alertMessage(
                            RoutingModuleConstants.INSTANCE.geocodingServiceText(),
                            WindowsConstants.INSTANCE.noResultsFoundText());
                    loadImage(TypeImage.IMAGE_RESULT_NOT_FOUND, true);
                    clearStore();
                }
            }

            @Override
            public void onCommandFailure(Throwable exception) {
                GeoPlatformMessage.errorMessage(
                        RoutingModuleConstants.INSTANCE.geocodingServiceText(),
                        WindowsConstants.INSTANCE.errorDispatchingRequestBodyText());
                loadImage(TypeImage.IMAGE_SERVICE_ERROR, true);
                clearStore();
            }

        });
    }

    /**
     * Clear Component Status for Widget :
     * <ul>
     * <li>Clear Store</li>
     * <li>Collapse ComboBox</li>
     * <li>Remove Marker on the Map</li>
     * </ul>
     */
    @Override
    public void clearStatus() {
        super.clearWidget();
        cleanGeoPlatformMap();
    }

    /**
     * Remove Marker on the Map
     *
     */
    public abstract void cleanGeoPlatformMap();

    /**
     *
     * @return
     */
    public GPComboBox<GeocodingBean> getComboBox() {
        return combo;
    }

}
