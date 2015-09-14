package org.geosdi.geoplatform.gui.client.config.provider.button;

import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.SimpleComboBox;
import com.google.common.collect.Lists;
import com.google.gwt.core.client.GWT;
import org.geosdi.geoplatform.connector.wfs.response.FeatureDTO;
import org.geosdi.geoplatform.connector.wfs.response.QueryDTO;
import org.geosdi.geoplatform.connector.wfs.response.QueryRestrictionDTO;
import org.geosdi.geoplatform.gui.client.command.wfst.basic.QueryFeatureRequest;
import org.geosdi.geoplatform.gui.client.command.wfst.basic.QueryFeatureResponse;
import org.geosdi.geoplatform.gui.client.config.annotation.FeatureAttributeConditionFieldList;
import org.geosdi.geoplatform.gui.client.config.annotation.MatchComboField;
import org.geosdi.geoplatform.gui.client.model.binder.ILayerSchemaBinder;
import org.geosdi.geoplatform.gui.client.model.wfs.FeatureDetail;
import org.geosdi.geoplatform.gui.client.puregwt.wfs.event.FeatureInstancesEvent;
import org.geosdi.geoplatform.gui.client.puregwt.wfs.event.FeatureStatusBarEvent;
import org.geosdi.geoplatform.gui.client.puregwt.wfs.handler.FeatureSelectionWidgetHandler;
import org.geosdi.geoplatform.gui.client.widget.SearchStatus;
import org.geosdi.geoplatform.gui.client.widget.wfs.FeatureAttributeConditionField;
import org.geosdi.geoplatform.gui.client.widget.wfs.statusbar.FeatureStatusBar;
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
public class QueryFeatureButtonProvider implements Provider<Button> {

    private final QueryFeatureRequest queryFeatureRequest = GWT.<QueryFeatureRequest>create(QueryFeatureRequest.class);
    private final GPEventBus bus;
    private final ILayerSchemaBinder layerSchemaBinder;
    private final SimpleComboBox<String> matchComboField;
    private final List<FeatureAttributeConditionField> attributeConditions;

    @Inject
    public QueryFeatureButtonProvider(GPEventBus theBus, ILayerSchemaBinder theLayerSchemaBinder,
            @MatchComboField SimpleComboBox matchComboField,
            @FeatureAttributeConditionFieldList List attributeConditions) {
        this.bus = theBus;
        this.layerSchemaBinder = theLayerSchemaBinder;
        this.matchComboField = matchComboField;
        this.attributeConditions = attributeConditions;
    }

    @Override
    public Button get() {
        return new Button("Query", new SelectionListener<ButtonEvent>() {

            @Override
            public void componentSelected(ButtonEvent ce) {
                bus.fireEvent(FeatureSelectionWidgetHandler.DISABLE_QUERY_BUTTON_EVENT);

                queryFeatureRequest.setServerUrl(layerSchemaBinder.getLayerSchemaDTO().getScope());
                queryFeatureRequest.setTypeName(layerSchemaBinder.getLayerSchemaDTO().getTypeName());
                queryFeatureRequest.setMaxFeatures(100);

                QueryDTO queryDTO = new QueryDTO();
                queryDTO.setMatchOperator(matchComboField.getValue().getValue());
                List<QueryRestrictionDTO> queryRestrictions = Lists.<QueryRestrictionDTO>newArrayListWithExpectedSize(
                        attributeConditions.size());
                for (FeatureAttributeConditionField conditionField : GPSharedUtils.safeList(attributeConditions)) {
                    QueryRestrictionDTO queryRestriction = conditionField.getQueryRestriction();
                    if (queryRestriction != null) {
                        queryRestrictions.add(queryRestriction);
                    }
                }
                queryDTO.setQueryRestrictionList(queryRestrictions);
                queryFeatureRequest.setQuery(queryDTO);

                ClientCommandDispatcher.getInstance().execute(new GPClientCommand<QueryFeatureResponse>() {

                    private static final long serialVersionUID = 7052499099859652678L;

                    {
                        super.setCommandRequest(queryFeatureRequest);
                    }

                    @Override
                    public void onCommandSuccess(QueryFeatureResponse response) {
                        if (!response.getResult().isFeaturesLoaded()) {
                            bus.fireEvent(new FeatureStatusBarEvent("No Features Loaded for Query Request",
                                    FeatureStatusBar.FeatureStatusBarType.STATUS_OK));
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
