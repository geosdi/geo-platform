/*
 *  geo-platform
 *  Rich webgis framework
 *  http://geo-platform.org
 * ====================================================================
 *
 * Copyright (C) 2008-2013 geoSDI Group (CNR IMAA - Potenza - ITALY).
 *
 * This program is free software: you can redistribute it and/or modify it 
 * under the terms of the GNU General Public License as published by 
 * the Free Software Foundation, either version 3 of the License, or 
 * (at your option) any later version. This program is distributed in the 
 * hope that it will be useful, but WITHOUT ANY WARRANTY; without 
 * even the implied warranty of MERCHANTABILITY or FITNESS FOR 
 * A PARTICULAR PURPOSE. See the GNU General Public License 
 * for more details. You should have received a copy of the GNU General 
 * Public License along with this program. If not, see http://www.gnu.org/licenses/ 
 *
 * ====================================================================
 *
 * Linking this library statically or dynamically with other modules is 
 * making a combined work based on this library. Thus, the terms and 
 * conditions of the GNU General Public License cover the whole combination. 
 * 
 * As a special exception, the copyright holders of this library give you permission 
 * to link this library with independent modules to produce an executable, regardless 
 * of the license terms of these independent modules, and to copy and distribute 
 * the resulting executable under terms of your choice, provided that you also meet, 
 * for each linked independent module, the terms and conditions of the license of 
 * that module. An independent module is a module which is not derived from or 
 * based on this library. If you modify this library, you may extend this exception 
 * to your version of the library, but you are not obligated to do so. If you do not 
 * wish to do so, delete this exception statement from your version. 
 *
 */
package org.geosdi.geoplatform.gui.client.widget.wfs;

import com.extjs.gxt.ui.client.Style;
import com.extjs.gxt.ui.client.event.BaseEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.store.StoreEvent;
import com.extjs.gxt.ui.client.store.StoreListener;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.extjs.gxt.ui.client.widget.grid.CellEditor;
import com.extjs.gxt.ui.client.widget.grid.ColumnConfig;
import com.extjs.gxt.ui.client.widget.grid.ColumnModel;
import com.extjs.gxt.ui.client.widget.grid.EditorGrid;
import com.google.common.collect.Lists;
import com.google.gwt.event.dom.client.FocusEvent;
import com.google.gwt.event.dom.client.FocusHandler;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import org.geosdi.geoplatform.gui.client.model.wfs.FeatureAttributeValuesDetail;
import org.geosdi.geoplatform.gui.client.model.wfs.FeatureDetail;
import org.geosdi.geoplatform.gui.client.puregwt.map.event.FeatureMapHeightEvent;
import org.geosdi.geoplatform.gui.client.puregwt.map.event.IncreaseHeightEvent;
import org.geosdi.geoplatform.gui.client.widget.GeoPlatformContentPanel;
import org.geosdi.geoplatform.gui.client.puregwt.wfs.event.FeatureStatusBarEvent;
import org.geosdi.geoplatform.gui.client.puregwt.wfs.handler.FeatureAttributesHandler;
import org.geosdi.geoplatform.gui.client.puregwt.wfs.handler.IDateSelectedHandler;
import org.geosdi.geoplatform.gui.client.widget.wfs.builder.AttributeCustomFieldsMap;
import org.geosdi.geoplatform.gui.client.widget.wfs.statusbar.FeatureStatusBar.FeatureStatusBarType;
import org.geosdi.geoplatform.gui.client.widget.wfs.time.TimeInputWidget;
import org.geosdi.geoplatform.gui.configuration.action.event.ActionEnableEvent;
import org.geosdi.geoplatform.gui.puregwt.GPEventBus;
import org.geosdi.geoplatform.gui.responce.AttributeDTO;
import org.geosdi.geoplatform.gui.responce.LayerSchemaDTO;
import org.gwtopenmaps.openlayers.client.feature.VectorFeature;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 *
 * @author Vincenzo Monteverde <vincenzo.monteverde@geosdi.org>
 */
public class FeatureAttributesWidget extends GeoPlatformContentPanel
        implements FeatureAttributesHandler, IDateSelectedHandler {

    static {
        mockColumnModel = new ColumnModel(new ArrayList<ColumnConfig>());
    }

    public static final String ID = WFSWidgetNames.FEATURE_ATTRIBUTES.name();
    private static final ColumnModel mockColumnModel;
    //
    private GPEventBus bus;
    private TimeInputWidget timeInputWidget;
    //
    private LayerSchemaDTO schemaDTO;
    //
    private ListStore<FeatureAttributeValuesDetail> store;
    private EditorGrid<FeatureAttributeValuesDetail> grid;
    //
    private List<VectorFeature> vectors;
    private FeatureMapHeightEvent increaseHeightEvent = new IncreaseHeightEvent();
    //
    private String dataAttributeName;

    @Inject
    public FeatureAttributesWidget(GPEventBus bus,
            TimeInputWidget timeInputWidget) {
        super(true);
        this.bus = bus;
        this.timeInputWidget = timeInputWidget;
        this.bus.addHandlerToSource(IDateSelectedHandler.TYPE, timeInputWidget,
                this);
        this.bus.addHandler(FeatureAttributesHandler.TYPE, this);
    }

    public void bind(LayerSchemaDTO theSchemaDTO) {
        assert (theSchemaDTO.getAttributes() != null) : "Attributes must not be null.";
        this.schemaDTO = theSchemaDTO;
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

    protected void manageGridSize() {
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
        super.setScrollMode(Style.Scroll.AUTOX);
    }

    @Override
    public void reset() {
        grid.stopEditing(true);
        store.removeAll();

        vectors = null;
        bus.fireEvent(new ActionEnableEvent(false));
        super.setVScrollPosition(0);
    }

    @Override
    public void dateSelected(String date) {
        FeatureAttributeValuesDetail featureAttributeValuesDetail = this.grid.
                getSelectionModel().getSelectedItem();
        if (featureAttributeValuesDetail != null) {
            featureAttributeValuesDetail.setValue(dataAttributeName, date);
            store.update(featureAttributeValuesDetail);
        }
    }

    private void createStore() {
        store = new ListStore<FeatureAttributeValuesDetail>();
        store.addStoreListener(
                new StoreListener<FeatureAttributeValuesDetail>() {

            @Override
            public void storeClear(StoreEvent<FeatureAttributeValuesDetail> se) {
                bus.fireEvent(new ActionEnableEvent(false));
            }

            @Override
            public void storeUpdate(StoreEvent<FeatureAttributeValuesDetail> se) {
                bus.fireEvent(new ActionEnableEvent(true));
            }

        });
    }

    private void createEditorGrid() {
        grid = new EditorGrid<FeatureAttributeValuesDetail>(store,
                mockColumnModel);

        grid.setBorders(true);
        grid.setStripeRows(true);
        grid.setColumnLines(true);
        grid.setColumnResize(true);
        grid.setHeight(125);
        grid.setAutoWidth(true);

        grid.setClicksToEdit(EditorGrid.ClicksToEdit.TWO);
        grid.getSelectionModel().setSelectionMode(Style.SelectionMode.SIMPLE);

        grid.addListener(Events.CellClick, new Listener<BaseEvent>() {

            @Override
            public void handleEvent(BaseEvent be) {
                System.out.println("SELECTED @@@@@@@@@@ "
                        + grid.getSelectionModel().getSelectedItem());
            }

        });

        super.add(grid);
    }

    private ColumnModel prepareColumnModel() {
        List<AttributeDTO> attributesDTO = this.schemaDTO.getAttributes();
        List<ColumnConfig> configs = Lists.<ColumnConfig>newArrayListWithCapacity(
                attributesDTO.size());

        for (final AttributeDTO att : attributesDTO) {
            TextField<String> valueTextField = new TextField<String>();

            valueTextField.setValidator(
                    AttributeCustomFieldsMap.getValidatorForAttributeType(
                    att.getType()));

            valueTextField.setAutoValidate(true);
            CellEditor valueEditor = new CellEditor(valueTextField) {

                @Override
                public Object postProcessValue(Object value) {
                    if (value == null) {
                        return value;
                    }
                    bus.fireEvent(new FeatureStatusBarEvent(
                            "The value \"" + value + "\" is correct",
                            FeatureStatusBarType.STATUS_OK));
                    return value;
                }

            };
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
            valueColumn.setEditor(valueEditor);
            valueColumn.setWidth(100);

            configs.add(valueColumn);
        }

        return new ColumnModel(configs);
    }

    @Override
    public void saveAttributes() {
//        for (Record record : store.getModifiedRecords()) {
//            ModelData model = record.getModel();
//            AttributeDetail attribute = (AttributeDetail) model;
//            features.getAttributes().setAttribute(
//                    attribute.getName(), attribute.getValue());
//        }
//
//        this.features.toState(VectorFeature.State.Update);
//
//        this.bus.fireEvent(new FeatureStatusBarEvent("Transaction in Progress",
//                                                     FeatureStatusBarType.STATUS_LOADING));
//
//        Timer t = new Timer() {
//            @Override
//            public void run() {
//                featureControlBuilder.getWfsProtocol().commit(features,
//                                                              featureCRUDProtocol);
//            }
//        };
//
//        t.schedule(2000);
    }

    @Override
    public void resetAttributes() {
        grid.stopEditing(true);
        store.rejectChanges();
        bus.fireEvent(new ActionEnableEvent(false));
    }

    @Override
    public void postInstances(List<FeatureDetail> instaces) {
        assert (instaces != null) : "Feature instances must not be null.";
        int numFeature = instaces.size();
        if (numFeature == 0) {
            System.out.println("*** NO FEATURE"); // TODO
            return;
        }


        grid.mask("Retrieve " + numFeature + " feature instance attributes");

        this.vectors = Lists.<VectorFeature>newArrayListWithCapacity(numFeature);
        List<FeatureAttributeValuesDetail> attValues = Lists.<FeatureAttributeValuesDetail>newArrayListWithCapacity(
                numFeature);

        for (FeatureDetail instace : instaces) {
            vectors.add(instace.getVector());
            attValues.add(new FeatureAttributeValuesDetail(
                    instace.getAttributes()));
        }

        this.populateStore(attValues);

        grid.unmask();
    }

    private void populateStore(List<FeatureAttributeValuesDetail> attValues) {
        store.removeAll();
        store.add(attValues);
    }

    @Override
    public void resetInstances() {
        this.reset();
    }

    @Override
    public void successfulTransaction() {
        this.bus.fireEvent(new FeatureStatusBarEvent("Successful Transaction",
                FeatureStatusBarType.STATUS_OK));

        store.commitChanges();
        bus.fireEvent(new ActionEnableEvent(false));
    }

    @Override
    public void maskAttributes(boolean mask) {
        if (mask) {
            grid.mask("Retrieving feature attributes");
        } else {
            grid.unmask();
        }
    }

}
