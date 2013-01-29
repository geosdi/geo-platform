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
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.FieldSetEvent;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.ComboBox;
import com.extjs.gxt.ui.client.widget.form.FieldSet;
import com.extjs.gxt.ui.client.widget.form.FormPanel;
import com.extjs.gxt.ui.client.widget.form.LabelField;
import com.extjs.gxt.ui.client.widget.form.MultiField;
import com.extjs.gxt.ui.client.widget.form.SimpleComboBox;
import com.extjs.gxt.ui.client.widget.form.SimpleComboValue;
import com.extjs.gxt.ui.client.widget.layout.FlowLayout;
import com.extjs.gxt.ui.client.widget.layout.VBoxLayoutData;
import com.extjs.gxt.ui.client.widget.tips.ToolTipConfig;
import com.google.common.collect.Lists;
import java.util.List;
import javax.inject.Inject;
import org.geosdi.geoplatform.gui.client.BasicWidgetResources;
import org.geosdi.geoplatform.gui.client.model.wfs.AttributeDetail;
import org.geosdi.geoplatform.gui.client.widget.GeoPlatformContentPanel;
import org.geosdi.geoplatform.gui.client.widget.wfs.handler.DeleteAttributeConditionHandler;
import org.geosdi.geoplatform.gui.puregwt.GPEventBus;

/**
 *
 * @author Vincenzo Monteverde <vincenzo.monteverde@geosdi.org>
 */
public class FeatureSelectionWidget extends GeoPlatformContentPanel
        implements DeleteAttributeConditionHandler {

    private GPEventBus bus;
    //
    private List<AttributeDetail> attributes;
    private List<FeatureAttributeConditionField> attributeConditions;
    //
    private FormPanel formPanel;
    private FieldSet matchResultSet;
    private SimpleComboBox<String> matchComboField;
    private Button addConditionButton;
    private Button resetConditionsButton;
    private Button selectAllButton;
    private Button queryButton;

    @Inject
    public FeatureSelectionWidget(GPEventBus bus) {
        super(true);
        this.bus = bus;
        bus.addHandler(DeleteAttributeConditionHandler.TYPE, this);
        this.attributeConditions = Lists.<FeatureAttributeConditionField>newArrayList();
    }

    @Override
    public void addComponent() {
        this.createFormPanel();
        this.createMatchSelection();
        this.createSelectionButtons();
        this.createQueryButtons();
    }

    @Override
    public void initSize() {
    }

    @Override
    public void setPanelProperties() {
        super.head.setText("Feature Selection");
        super.setBorders(false);
        super.setScrollMode(Style.Scroll.AUTO);
    }

    private void createFormPanel() {
        this.formPanel = new FormPanel();
        formPanel.setHeaderVisible(false);
        formPanel.setBorders(false);
        formPanel.setBodyBorder(false);
        formPanel.setLayout(new FlowLayout());

        super.add(formPanel);
    }

    private void createMatchSelection() {
        matchResultSet = new FieldSet();
        matchResultSet.setHeading("Select by condition");
        matchResultSet.setCheckboxToggle(true);
        matchResultSet.setExpanded(true);

        matchComboField = new SimpleComboBox<String>() {
            @Override
            protected void onSelect(SimpleComboValue<String> model, int index) {
                super.onSelect(model, index);
            }
        };
        matchComboField.setToolTip(new ToolTipConfig("Match selection",
                                                     "Change feature selection"));
        matchComboField.setEditable(false);
        matchComboField.setTypeAhead(true);
        matchComboField.setTriggerAction(ComboBox.TriggerAction.ALL);
        matchComboField.add(MatchType.ALL.name());
        matchComboField.add(MatchType.ANY.name());
        matchComboField.add(MatchType.NONE.name());
        matchComboField.setSimpleValue(MatchType.ALL.name());

        MultiField multiMatchField = new MultiField();
        multiMatchField.add(new LabelField("Match" + "&nbsp;"));
        multiMatchField.add(matchComboField);
        matchResultSet.add(multiMatchField, new VBoxLayoutData(0, 0, 5, 0));

        matchResultSet.addListener(Events.Collapse, new Listener<FieldSetEvent>() {
            @Override
            public void handleEvent(FieldSetEvent be) {
                addConditionButton.setVisible(false);
                queryButton.disable();
            }
        });
        matchResultSet.addListener(Events.Expand, new Listener<FieldSetEvent>() {
            @Override
            public void handleEvent(FieldSetEvent be) {
                addConditionButton.setVisible(true);
                queryButton.enable();
            }
        });

        formPanel.add(matchResultSet);;
    }

    private void createSelectionButtons() {
        formPanel.setButtonAlign(Style.HorizontalAlignment.LEFT);

        this.addConditionButton = new Button("Add Condition",
                                             BasicWidgetResources.ICONS.done(),
                                             new SelectionListener<ButtonEvent>() {
            @Override
            public void componentSelected(ButtonEvent ce) {
                FeatureAttributeConditionField attributeCondition =
                        new FeatureAttributeConditionField(attributes);
                attributeConditions.add(attributeCondition);

                matchResultSet.add(attributeCondition, new VBoxLayoutData(0, 0, 1, 0));
                matchResultSet.layout();

                int vScrollPosition = FeatureSelectionWidget.super.getVScrollPosition();
                FeatureSelectionWidget.super.setVScrollPosition(vScrollPosition + 30);
            }
        });
        formPanel.addButton(addConditionButton);

        this.resetConditionsButton = new Button("Reset Conditions",
                                                BasicWidgetResources.ICONS.delete(),
                                                new SelectionListener<ButtonEvent>() {
            @Override
            public void componentSelected(ButtonEvent ce) {
                for (FeatureAttributeConditionField attCondition : attributeConditions) {
                    matchResultSet.remove(attCondition);
                }
                attributeConditions.clear();
            }
        });
        formPanel.addButton(resetConditionsButton);
    }

    private void createQueryButtons() {
        super.setButtonAlign(Style.HorizontalAlignment.RIGHT);

        this.selectAllButton = new Button("Select All",
                                          new SelectionListener<ButtonEvent>() {
            @Override
            public void componentSelected(ButtonEvent ce) {
            }
        });
        super.addButton(selectAllButton);

        this.queryButton = new Button("Query",
                                      new SelectionListener<ButtonEvent>() {
            @Override
            public void componentSelected(ButtonEvent ce) {
            }
        });
        super.addButton(queryButton);
    }

    public void setAttributes(List<AttributeDetail> attributes) {
        assert (attributes != null) : "Attributes must not bu null.";
        this.attributes = attributes;
    }

    @Override
    public void deleteCondition(FeatureAttributeConditionField field) {
        attributeConditions.remove(field);
        matchResultSet.remove(field);
    }

    private enum MatchType {

        ALL, ANY, NONE;
    }
}
