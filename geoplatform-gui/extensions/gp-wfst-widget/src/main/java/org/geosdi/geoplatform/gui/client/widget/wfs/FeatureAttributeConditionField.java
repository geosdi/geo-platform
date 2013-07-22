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

import com.extjs.gxt.ui.client.event.BaseEvent;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.SelectionChangedEvent;
import com.extjs.gxt.ui.client.event.SelectionChangedListener;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.AdapterField;
import com.extjs.gxt.ui.client.widget.form.ComboBox;
import com.extjs.gxt.ui.client.widget.form.LabelField;
import com.extjs.gxt.ui.client.widget.form.MultiField;
import com.extjs.gxt.ui.client.widget.form.SimpleComboBox;
import com.extjs.gxt.ui.client.widget.form.TextField;
import java.util.List;
import org.geosdi.geoplatform.gui.client.BasicWidgetResources;
import org.geosdi.geoplatform.gui.client.config.FeatureInjector;
import org.geosdi.geoplatform.gui.client.model.wfs.AttributeDetail;
import org.geosdi.geoplatform.gui.client.model.wfs.OperatorType;
import org.geosdi.geoplatform.gui.client.puregwt.wfs.event.DeleteAttributeConditionEvent;
import org.geosdi.geoplatform.gui.client.util.FeatureConverter;
import org.geosdi.geoplatform.gui.client.widget.wfs.builder.AttributeCustomFields;
import org.geosdi.geoplatform.gui.client.widget.wfs.builder.AttributeCustomFieldsMap;
import org.geosdi.geoplatform.gui.client.widget.wfs.time.TimeInputWidget;
import org.geosdi.geoplatform.gui.puregwt.GPEventBus;
import org.geosdi.geoplatform.gui.responce.AttributeDTO;
import org.geosdi.geoplatform.gui.responce.QueryRestrictionDTO;

/**
 *
 * @author Vincenzo Monteverde <vincenzo.monteverde@geosdi.org>
 */
public class FeatureAttributeConditionField extends MultiField {

    private List<AttributeDetail> attributes;
    private ComboBox<AttributeDetail> nameAttributeCombo;
    private SimpleComboBox<String> operatorCombo;
    private TextField<String> conditionAttributeField = new TextField<String>();

    public FeatureAttributeConditionField(List<AttributeDetail> attributes) {
        assert (attributes != null) : "attributes must not be null.";
        this.attributes = attributes;
        this.createComponents();
    }

    private void createComponents() {
        final String spacer = "<span class='spacer'>&nbsp;</span>";
        Button deleteButton = this.createDeleteButton();
        super.add(new AdapterField(deleteButton));
//        super.add(new LabelField(spacer));
        super.add(this.createNameAttributeCombo());
        super.add(new LabelField(spacer));
        super.add(this.createConditionsCombo());
        super.add(new LabelField(spacer));
        super.add(this.createConditionAttributeField());
    }

    private ComboBox<AttributeDetail> createNameAttributeCombo() {
        this.nameAttributeCombo = new ComboBox<AttributeDetail>();
        nameAttributeCombo.setEditable(false);
        nameAttributeCombo.setTypeAhead(true);
        nameAttributeCombo.setTriggerAction(ComboBox.TriggerAction.ALL);
        nameAttributeCombo.setWidth(110);

        final TimeInputWidget timeInputWidget = new TimeInputWidget(this.conditionAttributeField);
        final Listener dateFieldListener = new Listener<BaseEvent>() {
            @Override
            public void handleEvent(BaseEvent be) {
                timeInputWidget.show();
            }
        };
        this.nameAttributeCombo.addSelectionChangedListener(new SelectionChangedListener<AttributeDetail>() {
            @Override
            public void selectionChanged(SelectionChangedEvent<AttributeDetail> se) {
                conditionAttributeField.removeListener(Events.OnClick, dateFieldListener);
                AttributeDetail attributeDetail = se.getSelectedItem();
                if (attributeDetail == null) {
                    operatorCombo.disable();
                } else {
                    AttributeCustomFields customFields =
                            AttributeCustomFieldsMap.CUSTOM_FIELD_MAP.get(
                            attributeDetail.getType());
                    operatorCombo.clear();
                    operatorCombo.removeAll();
                    for (OperatorType operatorType : customFields.getOperatorList()) {
                        operatorCombo.add(operatorType.toString());
                    }
                    operatorCombo.enable();
                    conditionAttributeField.clear();
                    conditionAttributeField.setValidator(customFields.getValidator());
                    conditionAttributeField.setToolTip("Datatype: " + attributeDetail.getType());
                    if (attributeDetail.getType().equals("dateTime")) {
                        conditionAttributeField.addListener(Events.OnClick, dateFieldListener);
                    }
                }
            }
        });
        ListStore nameAttributeStore = new ListStore<AttributeDetail>();
        nameAttributeStore.add(attributes);
        nameAttributeCombo.setStore(nameAttributeStore);
        nameAttributeCombo.setDisplayField(AttributeDetail.AttributeDetailKeyValue.NAME.name());
//        nameAttributeCombo.setSimpleValue("XXX");

        return nameAttributeCombo;
    }

    private SimpleComboBox<String> createConditionsCombo() {
        this.operatorCombo = new SimpleComboBox<String>();
        operatorCombo.setEditable(false);
        operatorCombo.setTypeAhead(true);
        operatorCombo.setTriggerAction(ComboBox.TriggerAction.ALL);
        operatorCombo.setWidth(50);

        for (OperatorType operator : OperatorType.values()) {
            operatorCombo.add(operator.toString());
        }
//        conditionsCombo.setSimpleValue(OperatorType.EQUAL);
        operatorCombo.disable();
        return operatorCombo;
    }

    private TextField createConditionAttributeField() {
        conditionAttributeField.setWidth(60);
        return conditionAttributeField;
    }

    private Button createDeleteButton() {
        Button button = new Button("", new SelectionListener<ButtonEvent>() {
            @Override
            public void componentSelected(ButtonEvent ce) {
                FeatureInjector injector = FeatureInjector.MainInjector.getInstance();
                GPEventBus bus = injector.getEventBus();
                bus.fireEvent(new DeleteAttributeConditionEvent(
                        FeatureAttributeConditionField.this));
            }
        });
        button.setToolTip("Delete Condition");
        button.setIcon(BasicWidgetResources.ICONS.delete());
        button.setAutoWidth(true);

        return button;
    }

    public QueryRestrictionDTO getQueryRestriction() {
        QueryRestrictionDTO queryRestriction = null;
        AttributeDetail attributeDetail = this.nameAttributeCombo.getValue();
        String operator = this.operatorCombo.getValue().getValue();
        String restriction = this.conditionAttributeField.getValue();
        if (attributeDetail != null && operator != null && this.conditionAttributeField.isValid()
                && restriction != null) {
            AttributeDTO attributeDTO = FeatureConverter.convert(attributeDetail);
            queryRestriction = new QueryRestrictionDTO(attributeDTO, operator, restriction);
        }
        return queryRestriction;
    }
}
