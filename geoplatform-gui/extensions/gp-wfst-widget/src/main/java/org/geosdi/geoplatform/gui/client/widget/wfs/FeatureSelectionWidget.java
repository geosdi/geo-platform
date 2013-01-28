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
import com.extjs.gxt.ui.client.widget.form.SimpleComboBox;
import com.extjs.gxt.ui.client.widget.form.SimpleComboValue;
import com.extjs.gxt.ui.client.widget.layout.FlowLayout;
import com.extjs.gxt.ui.client.widget.layout.FormLayout;
import com.extjs.gxt.ui.client.widget.layout.VBoxLayoutData;
import com.extjs.gxt.ui.client.widget.tips.ToolTipConfig;
import com.google.common.collect.Lists;
import java.util.List;
import javax.inject.Inject;
import org.geosdi.geoplatform.gui.client.BasicWidgetResources;
import org.geosdi.geoplatform.gui.client.model.wfs.AttributeDetail;
import org.geosdi.geoplatform.gui.client.widget.GeoPlatformContentPanel;
import org.geosdi.geoplatform.gui.puregwt.GPEventBus;

/**
 *
 * @author Vincenzo Monteverde <vincenzo.monteverde@geosdi.org>
 */
public class FeatureSelectionWidget extends GeoPlatformContentPanel {

    private GPEventBus bus;
    //
    private List<AttributeDetail> attributes;
    //
    private FormPanel formPanel;
    private SimpleComboBox<String> matchField;
    private List<FeatureAttributeConditionField> attributeConditions;
    //
    private Button selectAllButton;
    private Button queryButton;

    @Inject
    public FeatureSelectionWidget(GPEventBus bus) {
        super(true);
        this.bus = bus;
        this.attributeConditions = Lists.<FeatureAttributeConditionField>newArrayList();
    }

    @Override
    public void addComponent() {
        this.createFormPanel();
        this.createButtons();
    }

    @Override
    public void initSize() {
    }

    @Override
    public void setPanelProperties() {
        super.head.setText("Feature Selection");
        super.setBorders(false);
    }

    private void createFormPanel() {
        this.formPanel = new FormPanel();
        formPanel.setHeaderVisible(false);
        formPanel.setBorders(false);
        formPanel.setLayout(new FlowLayout());

//        formPanel.setLayout(new FitLayout());
//        formPanel.setLayout(new CardLayout());
//        formPanel.setLayoutData(null);
//        formPanel.setHeight(300);

//        formPanel.setLabelSeparator("");
//        formPanel.setHideLabels(true);

        formPanel.add(this.createMatchSelection(), new VBoxLayoutData());

        /***********************************************************************/
        formPanel.add(new FeatureAttributeConditionField(attributes));
//        final ContentPanel panelConditions = new ContentPanel();
//        panelConditions.setWidth(250);
//        panelConditions.setHeaderVisible(false);
////        panelConditions.setBorders(false);
//        formPanel.add(panelConditions);

//        final MultiField<FeatureAttributeConditionField> multi = new MultiField<FeatureAttributeConditionField>();
//        multi.setResizeFields(true); //
//        formPanel.add(multi);

//        for (int i = 1; i <= 9; i++) {
//            FeatureAttributeConditionField ex =
//                    new FeatureAttributeConditionField(attributes);
////            ex.setVisible(false);
////            ex.setReadOnly(true);
//            ex.setName(i + "");
//            attributeConditions.add(ex); //
//            formPanel.add(ex);
//        }

        formPanel.setButtonAlign(Style.HorizontalAlignment.LEFT);
        formPanel.addButton(new Button("Add Condition", BasicWidgetResources.ICONS.done(),
                                       new SelectionListener<ButtonEvent>() {
//            private int count = 0;

            @Override
            public void componentSelected(ButtonEvent ce) {

//                if (count == 9) {
//                    System.out.println("*** MAX 9 conditions");
//                    return;
//                }
//                FeatureAttributeConditionField attributeCondition = attributeConditions.get(count++);
//                attributeCondition.setVisible(true);

//                attributeConditions.add(attributeCondition);

//                FeatureAttributeConditionField attributeCondition =
//                        new FeatureAttributeConditionField(attributes);

//                formPanel.add(attributeCondition);
//                panelConditions.add(attributeCondition);
//                multi.add(attributeCondition);
            }
        }));
        /***********************************************************************/

        super.add(formPanel);
    }

    private FieldSet createMatchSelection() {
        FieldSet matchResultSet = new FieldSet();
        matchResultSet.setHeading("Select by condition");
        matchResultSet.setCheckboxToggle(true);
        matchResultSet.setExpanded(true);

        FormLayout layout = new FormLayout();
        layout.setLabelWidth(60);
        layout.setDefaultWidth(160);
        matchResultSet.setLayout(layout);

        matchField = new SimpleComboBox<String>() {
            @Override
            protected void onSelect(SimpleComboValue<String> model, int index) {
                super.onSelect(model, index);
            }
        };
        matchField.setToolTip(new ToolTipConfig("Match selection",
                                                "Change feature selection"));
        matchField.setFieldLabel("Match");
        matchField.setEditable(false);
        matchField.setTypeAhead(true);
        matchField.setTriggerAction(ComboBox.TriggerAction.ALL);
        matchField.add(MatchType.ALL.name());
        matchField.add(MatchType.ANY.name());
        matchField.add(MatchType.NONE.name());
        matchField.setSimpleValue(MatchType.ALL.name());

        matchResultSet.add(matchField);

        matchResultSet.addListener(Events.Collapse, new Listener<FieldSetEvent>() {
            @Override
            public void handleEvent(FieldSetEvent be) {
                queryButton.disable();
            }
        });
        matchResultSet.addListener(Events.Expand, new Listener<FieldSetEvent>() {
            @Override
            public void handleEvent(FieldSetEvent be) {
                queryButton.enable();
            }
        });

        return matchResultSet;
    }

    private void createButtons() {
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

    private enum MatchType {

        ALL, ANY, NONE;
    }
}
