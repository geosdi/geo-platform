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
package org.geosdi.geoplatform.gui.client.widget.wfs;

import com.extjs.gxt.ui.client.Style;
import com.extjs.gxt.ui.client.data.ModelData;
import com.extjs.gxt.ui.client.data.ModelKeyProvider;
import com.extjs.gxt.ui.client.event.*;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.store.Record;
import com.extjs.gxt.ui.client.store.StoreEvent;
import com.extjs.gxt.ui.client.store.StoreListener;
import com.extjs.gxt.ui.client.widget.grid.CellEditor;
import com.extjs.gxt.ui.client.widget.grid.ColumnConfig;
import com.extjs.gxt.ui.client.widget.grid.ColumnModel;
import com.extjs.gxt.ui.client.widget.grid.EditorGrid;
import com.google.common.collect.Lists;
import com.google.gwt.event.dom.client.FocusEvent;
import com.google.gwt.event.dom.client.FocusHandler;
import com.google.gwt.user.client.Timer;
import org.geosdi.geoplatform.connector.wfs.response.AttributeDTO;
import org.geosdi.geoplatform.gui.client.model.binder.ILayerSchemaBinder;
import org.geosdi.geoplatform.gui.client.model.wfs.FeatureDetail;
import org.geosdi.geoplatform.gui.client.puregwt.map.event.FeatureMapHeightEvent;
import org.geosdi.geoplatform.gui.client.puregwt.map.event.IncreaseHeightEvent;
import org.geosdi.geoplatform.gui.client.puregwt.wfs.event.FeatureStatusBarEvent;
import org.geosdi.geoplatform.gui.client.puregwt.wfs.event.ResetStatusBarEvent;
import org.geosdi.geoplatform.gui.client.puregwt.wfs.handler.FeatureAttributesHandler;
import org.geosdi.geoplatform.gui.client.puregwt.wfs.handler.IDateSelectedHandler;
import org.geosdi.geoplatform.gui.client.puregwt.wfs.handler.WFSFeatureBindingHandler.WFSFeatureBindingEvent;
import org.geosdi.geoplatform.gui.client.widget.GeoPlatformContentPanel;
import org.geosdi.geoplatform.gui.client.widget.wfs.builder.AttributeCustomFieldsMap;
import org.geosdi.geoplatform.gui.client.widget.wfs.builder.GetFeatureControlBuilder;
import org.geosdi.geoplatform.gui.client.widget.wfs.statusbar.FeatureStatusBar.FeatureStatusBarType;
import org.geosdi.geoplatform.gui.client.widget.wfs.time.TimeInputWidget;
import org.geosdi.geoplatform.gui.configuration.GPSecureStringTextField;
import org.geosdi.geoplatform.gui.configuration.action.event.ActionEnableEvent;
import org.geosdi.geoplatform.gui.puregwt.GPEventBus;
import org.gwtopenmaps.openlayers.client.feature.VectorFeature;
import org.gwtopenmaps.openlayers.client.protocol.WFSProtocolCRUDOptions;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

import static com.google.common.base.Preconditions.checkArgument;
import static java.lang.Boolean.TRUE;
import static org.geosdi.geoplatform.gui.client.widget.wfs.statusbar.FeatureStatusBar.FeatureStatusBarType.STATUS_OK;

/**
 * @author Vincenzo Monteverde <vincenzo.monteverde@geosdi.org>
 * @author Giuseppe La Scaleia <giuseppe.lascaleia@geosdi.org>
 */
public class FeatureAttributesWidget extends GeoPlatformContentPanel implements FeatureAttributesHandler, IDateSelectedHandler {

    public static final String ID = WFSWidgetNames.FEATURE_ATTRIBUTES.name();
    private static final ColumnModel mockColumnModel;

    static {
        mockColumnModel = new ColumnModel(new ArrayList<ColumnConfig>());
    }

    private final GPEventBus bus;
    private final TimeInputWidget timeInputWidget;
    //
    private final FeatureMapHeightEvent increaseHeightEvent = new IncreaseHeightEvent();
    //
    private final GetFeatureControlBuilder featureControlBuilder;
    private final WFSProtocolCRUDOptions featureCRUDProtocol;
    private final FeatureStatusBarEvent successStatusBarEvent = new FeatureStatusBarEvent("", STATUS_OK);
    ResetStatusBarEvent resetStatusBarEvent = new ResetStatusBarEvent();
    //
    @Inject
    private ILayerSchemaBinder layerSchemaBinder;
    private ListStore<FeatureDetail> store;
    private EditorGrid<FeatureDetail> grid;
    //
    private String dataAttributeName;

    @Inject
    public FeatureAttributesWidget(GPEventBus theBus, GetFeatureControlBuilder theFeatureControlBuilder,
            WFSProtocolCRUDOptions theFeatureCRUDProtocol) {
        super(TRUE);
        checkArgument(theBus != null, "The Parameter bus must not be null.");
        checkArgument(theFeatureControlBuilder != null, "The Parameter featureControlBuilder must not be null.");
        checkArgument(theFeatureCRUDProtocol != null, "The Parameter featureCRUDProtocol must not be null.");
        this.bus = theBus;
        this.featureControlBuilder = theFeatureControlBuilder;
        this.featureCRUDProtocol = theFeatureCRUDProtocol;
        this.timeInputWidget = new TimeInputWidget(theBus);
        this.bus.addHandlerToSource(IDateSelectedHandler.TYPE, timeInputWidget, this);
        this.bus.addHandler(FeatureAttributesHandler.TYPE, this);
        super.setMonitorWindowResize(TRUE);
        addWidgetListener(new WidgetListener() {

            @Override
            public void widgetResized(ComponentEvent ce) {

                if ((getWidth() > 0)) {
                    grid.setWidth(getWidth());
                }
            }
        });
    }

    public void reconfigureEditorGrid() {
        this.grid.reconfigure(store, this.prepareColumnModel());
    }

    @Override
    protected void beforeRender() {
        super.beforeRender();
        this.grid.reconfigure(store, this.prepareColumnModel());
    }

    @Override
    protected void afterRender() {
        super.afterRender();
        super.setId(ID);
    }

    @Override
    public void addComponent() {
        this.createStore();
        this.createEditorGrid();
    }

    @Override
    public void initSize() {
    }

    protected void manageGridHeight() {
        this.grid.setHeight(super.getHeight() - 25);
    }

    @Override
    public void collapse() {
        this.increaseHeightEvent.setHeight(getHeight());
        this.bus.fireEvent(increaseHeightEvent);
        super.collapse();
    }

    @Override
    public void setPanelProperties() {
    }

    @Override
    public void reset() {
        grid.stopEditing(TRUE);
        store.removeAll();

        bus.fireEvent(new ActionEnableEvent(Boolean.FALSE));
        super.setVScrollPosition(0);
    }

    @Override
    public void dateSelected(String date) {
        FeatureDetail featureAttributeValuesDetail = this.grid.getSelectionModel().getSelectedItem();
        if (featureAttributeValuesDetail != null) {
            featureAttributeValuesDetail.setValue(dataAttributeName, date);
            store.update(featureAttributeValuesDetail);
        }
    }

    private void createStore() {
        store = new ListStore<FeatureDetail>();
        store.setKeyProvider(new ModelKeyProvider<FeatureDetail>() {

            @Override
            public String getKey(FeatureDetail model) {
                return model.getFeatureID();
            }
        });
        store.addStoreListener(new StoreListener<FeatureDetail>() {

            @Override
            public void storeClear(StoreEvent<FeatureDetail> se) {
                bus.fireEvent(new ActionEnableEvent(false));
            }

            @Override
            public void storeUpdate(StoreEvent<FeatureDetail> se) {
                bus.fireEvent(new ActionEnableEvent(true));
            }

        });
    }

    private void createEditorGrid() {
        grid = new EditorGrid<FeatureDetail>(store, mockColumnModel);
        grid.setBorders(TRUE);
        grid.setStripeRows(TRUE);
        grid.setColumnLines(TRUE);
        grid.setColumnResize(TRUE);
        grid.setHeight(125);
        grid.addStyleName("grid-style");
        grid.setClicksToEdit(EditorGrid.ClicksToEdit.TWO);
        grid.getSelectionModel().setSelectionMode(Style.SelectionMode.SIMPLE);
        grid.addListener(Events.CellClick, new Listener<BaseEvent>() {

            @Override
            public void handleEvent(BaseEvent be) {
                FeatureDetail selectedItem = grid.getSelectionModel().getSelectedItem();
                bus.fireEvent(new WFSFeatureBindingEvent(selectedItem.getVectorFeature()));
            }

        });
        super.add(grid);
    }

    private ColumnModel prepareColumnModel() {
        List<AttributeDTO> attributesDTO = this.layerSchemaBinder.getLayerSchemaDTO().getAttributes();
        List<ColumnConfig> configs = Lists.<ColumnConfig>newArrayListWithCapacity(attributesDTO.size());
        for (final AttributeDTO att : attributesDTO) {
            final GPSecureStringTextField valueTextField = new GPSecureStringTextField();
            valueTextField.setValidator(AttributeCustomFieldsMap.getValidatorForAttributeType(att.getType()));
            valueTextField.setAutoValidate(true);
            if (att.isDateType()) {
                FocusHandler focusHandler = new FocusHandler() {

                    @Override
                    public void onFocus(FocusEvent event) {
                        dataAttributeName = att.getName();
                        timeInputWidget.show();
                    }

                };
                valueTextField.addHandler(focusHandler, FocusEvent.getType());
            }
            ColumnConfig valueColumn = new ColumnConfig();
            String name = att.getName();
            valueColumn.setId(name);
            valueColumn.setHeaderHtml(name);
            valueColumn.setEditor(buildCellEditor(valueTextField));
            valueColumn.setWidth((name.length() + 1) * 10);
            valueColumn.setToolTip("Datatype: " + att.getType());
            configs.add(valueColumn);
        }
        return new ColumnModel(configs);
    }

    @Override
    public void saveAttributes() {
        final List<VectorFeature> features = new ArrayList<VectorFeature>();
        for (Record record : store.getModifiedRecords()) {
            ModelData model = record.getModel();
            FeatureDetail attribute = (FeatureDetail) model;
            for (String name : attribute.getProperties().keySet()) {
                if ((name != null) && !(name.isEmpty())) {
                    attribute.getVectorFeature().getAttributes().setAttribute(name, attribute.getValue(name));
                }
            }
            attribute.getVectorFeature().toState(VectorFeature.State.Update);
            features.add(attribute.getVectorFeature());
        }
        this.bus.fireEvent(new FeatureStatusBarEvent("Transaction in Progress", FeatureStatusBarType.STATUS_LOADING));
        Timer t = new Timer() {

            @Override
            public void run() {

                featureControlBuilder.getWfsProtocol().commit(features.toArray(new VectorFeature[features.size()]),
                        featureCRUDProtocol);
            }
        };
        t.schedule(2000);
    }

    @Override
    public void resetAttributes() {
        grid.stopEditing(true);
        store.rejectChanges();
        bus.fireEvent(new ActionEnableEvent(false));
    }

    @Override
    public void postInstances(List<FeatureDetail> instaces) {
        checkArgument(instaces != null, "The Parameter instances must not be null.");
        int numFeature = instaces.size();
        for (FeatureDetail featureDetail : instaces) {
            logger.fine("##################################FID : " + featureDetail.getFeatureID());
        }
        grid.mask("Retrieve " + numFeature + " feature instance attributes");
        this.populateStore(instaces);
        grid.unmask();
        bus.fireEvent(new FeatureStatusBarEvent("Features Loaded " + numFeature, STATUS_OK));
    }

    @Override
    public void resetInstances() {
        this.reset();
    }

    @Override
    public void removeFeatureDetail(String featureID) {
        FeatureDetail featureDetail = this.store.findModel(featureID);
        if (featureDetail != null) {
            store.remove(featureDetail);
            grid.stopEditing(TRUE);
        }
        this.bus.fireEvent(new FeatureStatusBarEvent("Feature Successfully Deleted", STATUS_OK));
        this.bus.fireEvent(new ActionEnableEvent(Boolean.FALSE));
    }

    @Override
    public void successfulTransaction() {
        this.bus.fireEvent(new FeatureStatusBarEvent("Successful Transaction", STATUS_OK));
        store.commitChanges();
        bus.fireEvent(new ActionEnableEvent(Boolean.FALSE));
    }

    @Override
    public void maskAttributes(boolean mask) {
        if (mask) {
            grid.mask("Retrieving feature attributes");
        } else {
            grid.unmask();
        }
    }

    final CellEditor buildCellEditor(GPSecureStringTextField textField) {
        CellEditor valueEditor = new CellEditor(textField) {

            @Override
            public Object postProcessValue(Object value) {

                if (value == null) {
                    return value;
                }
                successStatusBarEvent.setText("The value \"" + value + "\" is correct");
                bus.fireEvent(successStatusBarEvent);
                return value;
            }

        };

        valueEditor.addListener(Events.CancelEdit, new Listener<EditorEvent>() {

            @Override
            public void handleEvent(EditorEvent be) {

                bus.fireEvent(resetStatusBarEvent);
            }

        });

        return valueEditor;
    }

    private void populateStore(List<FeatureDetail> attValues) {
        store.removeAll();
        store.add(attValues);
    }
}