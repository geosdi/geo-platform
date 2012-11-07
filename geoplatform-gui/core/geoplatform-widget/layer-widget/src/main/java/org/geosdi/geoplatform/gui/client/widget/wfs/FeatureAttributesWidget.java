/*
 *  geo-platform
 *  Rich webgis framework
 *  http://geo-platform.org
 * ====================================================================
 *
 * Copyright (C) 2008-2012 geoSDI Group (CNR IMAA - Potenza - ITALY).
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
import com.extjs.gxt.ui.client.data.ModelData;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.store.Record;
import com.extjs.gxt.ui.client.store.StoreEvent;
import com.extjs.gxt.ui.client.store.StoreListener;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.Field;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.extjs.gxt.ui.client.widget.form.Validator;
import com.extjs.gxt.ui.client.widget.grid.CellEditor;
import com.extjs.gxt.ui.client.widget.grid.ColumnConfig;
import com.extjs.gxt.ui.client.widget.grid.ColumnModel;
import com.extjs.gxt.ui.client.widget.grid.EditorGrid;
import com.google.common.collect.Lists;
import com.google.gwt.user.client.Timer;
import java.util.List;
import java.util.Map;
import javax.inject.Inject;
import org.geosdi.geoplatform.gui.client.BasicWidgetResources;
import org.geosdi.geoplatform.gui.client.model.wfs.AttributeDetail;
import org.geosdi.geoplatform.gui.client.model.wfs.AttributeDetail.AttributeDetailKeyValue;
import org.geosdi.geoplatform.gui.client.widget.GeoPlatformContentPanel;
import org.geosdi.geoplatform.gui.client.widget.validator.TypeValidator;
import org.geosdi.geoplatform.gui.client.widget.validator.TypeValidatorController;
import org.geosdi.geoplatform.gui.client.widget.wfs.builder.GetFeatureControlBuilder;
import org.geosdi.geoplatform.gui.client.widget.wfs.event.FeatureStatusBarEvent;
import org.geosdi.geoplatform.gui.client.widget.wfs.handler.FeatureAttributeValuesHandler;
import org.geosdi.geoplatform.gui.client.widget.wfs.statusbar.FeatureStatusBar.FeatureStatusBarType;
import org.geosdi.geoplatform.gui.puregwt.GPEventBus;
import org.gwtopenmaps.openlayers.client.feature.VectorFeature;
import org.gwtopenmaps.openlayers.client.protocol.WFSProtocolCRUDOptions;

/**
 *
 * @author Vincenzo Monteverde <vincenzo.monteverde@geosdi.org>
 */
public class FeatureAttributesWidget extends GeoPlatformContentPanel
        implements FeatureAttributeValuesHandler {

    private GPEventBus bus;
    //
    private String featureID;
    private List<AttributeDetail> attributes;
    //
    private ListStore<AttributeDetail> store;
    private EditorGrid<AttributeDetail> grid;
    //
    private Button saveButton;
    private Button resetButton;
    private VectorFeature feature;
    private GetFeatureControlBuilder featureControlBuilder;
    private WFSProtocolCRUDOptions featureCRUDProtocol;

    @Inject
    public FeatureAttributesWidget(GPEventBus bus,
            GetFeatureControlBuilder theFeatureControlBuilder,
            WFSProtocolCRUDOptions theFeatureCRUDProtocol) {
        super(true);
        this.bus = bus;
        this.featureControlBuilder = theFeatureControlBuilder;
        this.featureCRUDProtocol = theFeatureCRUDProtocol;

        this.bus.addHandler(FeatureAttributeValuesHandler.TYPE, this);
    }

    public void setFeatureID(String featureID) {
        assert (featureID != null) : "FeatureID must not be null.";
        this.featureID = featureID;
    }

    public void setAttributes(List<AttributeDetail> attributes) {
        assert (attributes != null) : "Attributes must not bu null.";
        this.attributes = attributes;
    }

    @Override
    public void addComponent() {
        this.createStore();
        this.createEditorGrid();
        this.createButtons();
    }

    @Override
    public void initSize() {
    }

    @Override
    public void setPanelProperties() {
        super.head.setText("Feature Attributes");
        super.head.setStyleAttribute("textAlign", "center");
    }

    @Override
    public void reset() {
        grid.stopEditing(true);
        store.removeAll();
        this.feature = null;
        disableButtons();
    }

    private void createStore() {
        store = new ListStore<AttributeDetail>();
        store.addStoreListener(new StoreListener<AttributeDetail>() {
            @Override
            public void storeClear(StoreEvent<AttributeDetail> se) {
                disableButtons();
            }

            @Override
            public void storeUpdate(StoreEvent<AttributeDetail> se) {
                enableButtons();
            }
        });
    }

    private void createEditorGrid() {
        grid = new EditorGrid<AttributeDetail>(store, this.prepareColumnModel());
        grid.setAutoExpandColumn(AttributeDetailKeyValue.NAME.name());
        grid.setBorders(true);
        grid.setStripeRows(true);
        grid.setColumnLines(true);
        grid.setColumnResize(false);
        grid.setHeight(600);

        super.add(grid);
    }

    private ColumnModel prepareColumnModel() {
        List<ColumnConfig> configs = Lists.newArrayListWithCapacity(2);

        ColumnConfig nameColumn = new ColumnConfig();
        nameColumn.setId(AttributeDetailKeyValue.NAME.name());
        nameColumn.setHeader("Name");
        nameColumn.setWidth(150);
        nameColumn.setFixed(true);
        configs.add(nameColumn);

        TextField<String> valueTextField = new TextField<String>();
        valueTextField.setValidator(this.attributeValuesValidator());
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

        ColumnConfig valueColumn = new ColumnConfig();
        valueColumn.setId(AttributeDetailKeyValue.VALUE.name());
        valueColumn.setHeader("Value");
        valueColumn.setWidth(150);
        valueColumn.setFixed(true);
        valueColumn.setEditor(valueEditor);
        configs.add(valueColumn);

        return new ColumnModel(configs);
    }

    private void createButtons() {
        super.setButtonAlign(Style.HorizontalAlignment.CENTER);

        resetButton = new Button("Reset", BasicWidgetResources.ICONS.delete(),
                new SelectionListener<ButtonEvent>() {
                    @Override
                    public void componentSelected(ButtonEvent ce) {
                        grid.stopEditing(true);
                        store.rejectChanges();
                        disableButtons();
                    }
                });
        super.addButton(resetButton);

        this.saveButton = new Button("Save", BasicWidgetResources.ICONS.done(),
                new SelectionListener<ButtonEvent>() {
                    @Override
                    public void componentSelected(ButtonEvent ce) {
                        saveAttributes();
                    }
                });
        super.addButton(saveButton);
        this.disableButtons();
    }

    private void disableButtons() {
        resetButton.disable();
        saveButton.disable();
    }

    private void enableButtons() {
        resetButton.enable();
        saveButton.enable();
    }

    @Override
    public void successfulTransaction() {
        this.bus.fireEvent(new FeatureStatusBarEvent("Successful Transaction",
                FeatureStatusBarType.STATUS_OK));

        store.commitChanges();
        disableButtons();
    }

    private void saveAttributes() {
        for (Record record : store.getModifiedRecords()) {
            ModelData model = record.getModel();
            AttributeDetail attribute = (AttributeDetail) model;
            feature.getAttributes().setAttribute(attribute.getName(),
                    attribute.getValue());
        }

        this.feature.toState(VectorFeature.State.Update);

        this.bus.fireEvent(new FeatureStatusBarEvent("Transaction in Progress",
                FeatureStatusBarType.STATUS_LOADING));

        Timer t = new Timer() {
            @Override
            public void run() {
                featureControlBuilder.getWfsProtocol().commit(feature,
                        featureCRUDProtocol);
            }
        };

        t.schedule(2000);
    }

    private void populateStore() {
        assert (attributes != null) : "Attributes must not be null.";
        store.removeAll(); // TODO It is executed into reset -> notifyHide
        store.add(this.attributes);
    }

    @Override
    public void setValues(Map<String, String> attributeValues,
            VectorFeature feature) {
        assert (attributeValues != null) : "Attribute values must not be null.";
        assert (attributes != null) : "Attributes must not be null.";

        this.feature = feature;

        grid.mask("Retrieve feature attributes");

        for (AttributeDetail attribute : this.attributes) {
            String value = attributeValues.get(attribute.getName());
            attribute.setValue(value);
        }

        this.populateStore();

        grid.unmask();
    }

    @Override
    public void resetAttributeValues() {
        this.reset();
    }

    private Validator attributeValuesValidator() {
        return new Validator() {
            @Override
            public String validate(Field<?> field,
                    String value) {
                AttributeDetail selectedItem = grid.getSelectionModel().getSelectedItem();
                String type = selectedItem.getType();
                String typeName = type.substring(type.lastIndexOf(".") + 1);
//                System.out.println("*** " + typeName + " - value: " + value);
                TypeValidator validator = TypeValidatorController.MAP_VALIDATOR.get(type);
                if (!validator.validateType(value)) {
                    String errorValidation = "The value must be of " + typeName + " type";
                    bus.fireEvent(new FeatureStatusBarEvent(
                            errorValidation, FeatureStatusBarType.STATUS_ERROR));
                    return errorValidation;
                }
                return null;
            }
        };
    }
}
