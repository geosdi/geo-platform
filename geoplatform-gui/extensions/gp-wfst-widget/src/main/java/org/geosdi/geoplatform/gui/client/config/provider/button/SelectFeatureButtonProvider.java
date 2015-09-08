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
import org.geosdi.geoplatform.gui.client.puregwt.wfs.event.EnableQueryButtonEvent;
import org.geosdi.geoplatform.gui.client.puregwt.wfs.event.FeatureInstancesEvent;
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
    private final EnableQueryButtonEvent enableQueryButtonEvent = new EnableQueryButtonEvent(Boolean.TRUE);
    private final EnableQueryButtonEvent disableQueryButtonEvent = new EnableQueryButtonEvent(Boolean.FALSE);
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
                bus.fireEvent(disableQueryButtonEvent);

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
                                    errorMessage + " for " + layerSchemaBinder.getLayerSchemaDTO().getTypeName() + " layer.",
                                    SearchStatus.EnumSearchStatus.STATUS_SEARCH_ERROR.toString());
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
                        bus.fireEvent(enableQueryButtonEvent);
                    }

                    @Override
                    public void onCommandFailure(Throwable exception) {
                        String errorMessage = "Error on WFS GetFeature request";

                        GeoPlatformMessage.errorMessage("GetFeture Service Error",
                                errorMessage + " - " + exception.getMessage());

                        LayoutManager.getInstance().getStatusMap().setStatus(
                                errorMessage + " for " + layerSchemaBinder.getLayerSchemaDTO().getTypeName() + " layer.",
                                SearchStatus.EnumSearchStatus.STATUS_SEARCH_ERROR.toString());
                    }

                });
            }

        });
    }
}
