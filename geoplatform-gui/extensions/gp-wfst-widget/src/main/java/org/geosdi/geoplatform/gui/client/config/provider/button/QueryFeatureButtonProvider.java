package org.geosdi.geoplatform.gui.client.config.provider.button;

import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.SimpleComboBox;
import com.google.common.collect.Lists;
import com.google.gwt.core.client.GWT;
import org.geosdi.geoplatform.connector.wfs.response.QueryDTO;
import org.geosdi.geoplatform.connector.wfs.response.QueryRestrictionDTO;
import org.geosdi.geoplatform.gui.client.command.wfst.basic.QueryFeatureRequest;
import org.geosdi.geoplatform.gui.client.command.wfst.basic.QueryFeatureResponse;
import org.geosdi.geoplatform.gui.client.config.annotation.FeatureAttributeConditionFieldList;
import org.geosdi.geoplatform.gui.client.config.annotation.MatchComboField;
import org.geosdi.geoplatform.gui.client.model.binder.ILayerSchemaBinder;
import org.geosdi.geoplatform.gui.client.widget.wfs.FeatureAttributeConditionField;
import org.geosdi.geoplatform.gui.command.api.ClientCommandDispatcher;
import org.geosdi.geoplatform.gui.command.api.GPClientCommand;
import org.geosdi.geoplatform.gui.configuration.message.GeoPlatformMessage;
import org.geosdi.geoplatform.gui.puregwt.GPEventBus;
import org.geosdi.geoplatform.gui.shared.util.GPSharedUtils;

import javax.inject.Inject;
import javax.inject.Provider;
import java.util.List;

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
                        //TODO: Show response result
                        System.out.println("On success");
                    }

                    @Override
                    public void onCommandFailure(Throwable exception) {
                        GeoPlatformMessage.errorMessage("Query Error", exception.getMessage());
                    }

                });
            }

        });
    }
}
