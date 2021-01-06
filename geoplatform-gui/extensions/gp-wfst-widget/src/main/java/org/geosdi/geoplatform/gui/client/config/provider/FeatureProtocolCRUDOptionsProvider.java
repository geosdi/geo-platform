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
package org.geosdi.geoplatform.gui.client.config.provider;

import org.geosdi.geoplatform.gui.client.i18n.WFSTWidgetConstants;
import org.geosdi.geoplatform.gui.client.model.binder.ILayerSchemaBinder;
import org.geosdi.geoplatform.gui.client.puregwt.map.initializer.IFeatureMapInitializerHandler;
import org.geosdi.geoplatform.gui.client.puregwt.wfs.event.FeatureStatusBarEvent;
import org.geosdi.geoplatform.gui.client.puregwt.wfs.event.FeatureTransactionEvent;
import org.geosdi.geoplatform.gui.client.widget.wfs.statusbar.FeatureStatusBar;
import org.geosdi.geoplatform.gui.puregwt.GPEventBus;
import org.geosdi.geoplatform.gui.puregwt.GPHandlerManager;
import org.gwtopenmaps.openlayers.client.protocol.CRUDOptions;
import org.gwtopenmaps.openlayers.client.protocol.Response;
import org.gwtopenmaps.openlayers.client.protocol.WFSProtocolCRUDOptions;

import javax.inject.Inject;
import javax.inject.Provider;

/**
 * <p>
 * This Class is not used. The Transaction will do with WFSConnector Module and
 * not directly with OpenLayers.</p>
 *
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class FeatureProtocolCRUDOptionsProvider implements
        Provider<WFSProtocolCRUDOptions> {

    private final GPEventBus bus;
    private final FeatureTransactionEvent transactionEvent = new FeatureTransactionEvent();
    private final ILayerSchemaBinder layerSchemaBinder;

    @Inject
    public FeatureProtocolCRUDOptionsProvider(GPEventBus theBus,
                                              ILayerSchemaBinder layerSchemaBinder) {
        this.bus = theBus;
        this.layerSchemaBinder = layerSchemaBinder;
    }

    @Override
    public WFSProtocolCRUDOptions get() {
        return new WFSProtocolCRUDOptions(new CRUDOptions.Callback() {

            @Override
            public void computeResponse(Response response) {
                if (response.success()) {
                    bus.fireEvent(transactionEvent);
                    GPHandlerManager.fireEvent(
                            layerSchemaBinder.getReloadLayerMapEvent());
                    bus.fireEvent(IFeatureMapInitializerHandler.REDRAW_EVENT);
                } else {
                    bus.fireEvent(new FeatureStatusBarEvent(
                            WFSTWidgetConstants.INSTANCE.
                                    FeatureProtocolCRUDOptionsProvider_transactionErrorText(),
                            FeatureStatusBar.FeatureStatusBarType.STATUS_NOT_OK));
                }
            }

        });
    }

}
