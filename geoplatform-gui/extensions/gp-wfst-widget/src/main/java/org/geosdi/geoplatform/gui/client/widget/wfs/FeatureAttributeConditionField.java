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

import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.AdapterField;
import com.extjs.gxt.ui.client.widget.form.ComboBox;
import com.extjs.gxt.ui.client.widget.form.Field;
import com.extjs.gxt.ui.client.widget.form.LabelField;
import com.extjs.gxt.ui.client.widget.form.MultiField;
import com.extjs.gxt.ui.client.widget.form.SimpleComboBox;
import com.extjs.gxt.ui.client.widget.form.TextField;
import java.util.List;
import org.geosdi.geoplatform.gui.client.BasicWidgetResources;
import org.geosdi.geoplatform.gui.client.config.FeatureInjector;
import org.geosdi.geoplatform.gui.client.model.wfs.AttributeDetail;
import org.geosdi.geoplatform.gui.client.widget.wfs.event.DeleteAttributeConditionEvent;
import org.geosdi.geoplatform.gui.puregwt.GPEventBus;

/**
 *
 * @author Vincenzo Monteverde <vincenzo.monteverde@geosdi.org>
 */
public class FeatureAttributeConditionField extends MultiField {

    private List<AttributeDetail> attributes;

    public FeatureAttributeConditionField(List<AttributeDetail> attributes) {
        assert (attributes != null) : "attributes must not be null.";
        this.attributes = attributes;
        this.createComponents();
    }

    public FeatureAttributeConditionField() {
        throw new NullPointerException("Use constructor with List<AttributeDTO> argument.");
    }

    public FeatureAttributeConditionField(String fieldLabel, Field... fields) {
        throw new NullPointerException("Use constructor with List<AttributeDTO> argument.");
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

    private SimpleComboBox<String> createNameAttributeCombo() {
        SimpleComboBox<String> nameAttributeCombo = new SimpleComboBox<String>();
        nameAttributeCombo.setEditable(false);
        nameAttributeCombo.setTypeAhead(true);
        nameAttributeCombo.setTriggerAction(ComboBox.TriggerAction.ALL);
        nameAttributeCombo.setWidth(110);

        for (AttributeDetail attribute : attributes) {
            nameAttributeCombo.add(attribute.getName());
        }
//        nameAttributeCombo.setSimpleValue("XXX");

        return nameAttributeCombo;
    }

    private SimpleComboBox<String> createConditionsCombo() {
        SimpleComboBox<String> conditionsCombo = new SimpleComboBox<String>();
        conditionsCombo.setEditable(false);
        conditionsCombo.setTypeAhead(true);
        conditionsCombo.setTriggerAction(ComboBox.TriggerAction.ALL);
        conditionsCombo.setWidth(50);

        for (OperatorType operator : OperatorType.values()) {
            conditionsCombo.add(operator.toString());
        }
//        conditionsCombo.setSimpleValue(OperatorType.EQUAL);

        return conditionsCombo;
    }

    private TextField createConditionAttributeField() {
        TextField conditionAttributeField = new TextField();
        conditionAttributeField.setWidth(80);
        // TODO Validation
        return conditionAttributeField;
    }

    private Button createDeleteButton() {
        Button button = new Button("", new SelectionListener<ButtonEvent>() {
            @Override
            public void componentSelected(ButtonEvent ce) {
                FeatureInjector injector = FeatureInjector.MainInjector.getInstance();
                GPEventBus bus = injector.getEventBus();
                bus.fireEvent(new DeleteAttributeConditionEvent(FeatureAttributeConditionField.this));
            }
        });
        button.setToolTip("Delete Condition");
        button.setIcon(BasicWidgetResources.ICONS.delete());
        button.setAutoWidth(true);

        return button;
    }

    private enum OperatorType {

        EQUAL("="),
        NOT_EQUAL("<>"),
        LESS("<"),
        GREATER(">"),
        LESS_OR_EQUAL("<="),
        GREATER_OR_EQUAL(">=");
        //
        private String symbol;

        private OperatorType(String symbol) {
            this.symbol = symbol;
        }

        @Override
        public String toString() {
            return this.symbol;
        }
    }
}
