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
package org.geosdi.geoplatform.gui.client.config.provider.button;

import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.google.common.collect.Lists;
import com.google.gwt.core.client.GWT;
import org.geosdi.geoplatform.connector.wfs.response.FeatureDTO;
import org.geosdi.geoplatform.gui.client.command.wfst.basic.GetAllFeatureRequest;
import org.geosdi.geoplatform.gui.client.command.wfst.basic.GetAllFeatureResponse;
import org.geosdi.geoplatform.gui.client.model.binder.ILayerSchemaBinder;
import org.geosdi.geoplatform.gui.client.model.wfs.FeatureDetail;
import org.geosdi.geoplatform.gui.client.puregwt.wfs.event.FeatureInstancesEvent;
import org.geosdi.geoplatform.gui.client.puregwt.wfs.handler.FeatureSelectionWidgetHandler;
import org.geosdi.geoplatform.gui.client.widget.SearchStatus;
import org.geosdi.geoplatform.gui.command.api.ClientCommandDispatcher;
import org.geosdi.geoplatform.gui.command.api.GPClientCommand;
import org.geosdi.geoplatform.gui.configuration.message.GeoPlatformMessage;
import org.geosdi.geoplatform.gui.impl.view.LayoutManager;
import org.geosdi.geoplatform.gui.puregwt.GPEventBus;
import org.geosdi.geoplatform.gui.shared.util.GPSharedUtils;

import javax.inject.Inject;
import javax.inject.Provider;
import java.util.List;
import java.util.Map;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class SelectFeatureButtonProvider implements Provider<Button> {

    private final GetAllFeatureRequest getAllFeatureRequest = GWT.<GetAllFeatureRequest>create(
            GetAllFeatureRequest.class);
    private final GPEventBus bus;
    private final ILayerSchemaBinder layerSchemaBinder;

    @Inject
    public SelectFeatureButtonProvider(GPEventBus theBus, ILayerSchemaBinder theLayerSchemaBinder) {
        this.bus = theBus;
        this.layerSchemaBinder = theLayerSchemaBinder;
    }

    @Override
    public Button get() {
        return new Button("Get 100 Features", new SelectionListener<ButtonEvent>() {

            @Override
            public void componentSelected(ButtonEvent ce) {
                bus.fireEvent(FeatureSelectionWidgetHandler.DISABLE_QUERY_BUTTON_EVENT);

                getAllFeatureRequest.setServerUrl(layerSchemaBinder.getLayerSchemaDTO().getScope());
                getAllFeatureRequest.setTypeName(layerSchemaBinder.getLayerSchemaDTO().getTypeName());
                getAllFeatureRequest.setMaxFeatures(100);

                ClientCommandDispatcher.getInstance().execute(new GPClientCommand<GetAllFeatureResponse>() {

                    private static final long serialVersionUID = 9028489214099941178L;

                    {
                        super.setCommandRequest(getAllFeatureRequest);
                    }

                    @Override
                    public void onCommandSuccess(GetAllFeatureResponse response) {
                        if (!response.getResult().isFeaturesLoaded()) {
                            String errorMessage = "Error on WFS GetFeature request";

                            GeoPlatformMessage.errorMessage("GetFeture Service Error",
                                    errorMessage + " - " + response.getResult().getErrorMessage());

                            LayoutManager.getInstance().getStatusMap().setStatus(
                                    errorMessage + " for " + layerSchemaBinder.getLayerSchemaDTO().getTypeName() +
                                            " layer.", SearchStatus.EnumSearchStatus.STATUS_SEARCH_ERROR.toString());
                        } else {
                            List<FeatureDetail> instances = Lists.<FeatureDetail>newArrayListWithCapacity(
                                    response.getResult().getFeatures().size());
                            for (FeatureDTO feature : GPSharedUtils.safeList(response.getResult().getFeatures())) {
                                Map<String, String> attributes = feature.getAttributes().getAttributesMap();
                                FeatureDetail featureDetail = new FeatureDetail(attributes, feature);
                                instances.add(featureDetail);
                            }

                            FeatureInstancesEvent e = new FeatureInstancesEvent();
                            e.setInstances(instances);
                            bus.fireEvent(e);
                        }
                        bus.fireEvent(FeatureSelectionWidgetHandler.ENABLE_QUERY_BUTTON_EVENT);
                    }

                    @Override
                    public void onCommandFailure(Throwable exception) {
                        String errorMessage = "Error on WFS GetFeature request";

                        GeoPlatformMessage.errorMessage("GetFeture Service Error",
                                errorMessage + " - " + exception.getMessage());

                        LayoutManager.getInstance().getStatusMap().setStatus(
                                errorMessage + " for " + layerSchemaBinder.getLayerSchemaDTO().getTypeName() +
                                        " layer.", SearchStatus.EnumSearchStatus.STATUS_SEARCH_ERROR.toString());
                    }

                });
            }

        });
    }
}
