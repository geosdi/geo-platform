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
package org.geosdi.geoplatform.gui.client.widget.cql;

import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.SelectionChangedEvent;
import com.extjs.gxt.ui.client.event.SelectionChangedListener;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.ComboBox;
import com.extjs.gxt.ui.client.widget.form.TextArea;
import com.extjs.gxt.ui.client.widget.layout.FormData;
import org.geosdi.geoplatform.gui.client.widget.cql.button.BBOXCQLButton;
import org.geosdi.geoplatform.gui.client.widget.cql.button.BetweenCQLButton;
import org.geosdi.geoplatform.gui.client.widget.cql.button.INCQLButton;
import org.geosdi.geoplatform.gui.client.widget.cql.button.TimeCQLButton;
import org.geosdi.geoplatform.gui.client.widget.cql.combobox.CQLLayerAttributesComboBox;
import org.geosdi.geoplatform.gui.client.widget.cql.combobox.CQLLogicalOperatorComboBox;
import org.geosdi.geoplatform.gui.client.widget.cql.combobox.LogicalOperator;
import org.geosdi.geoplatform.gui.client.widget.tab.GeoPlatformTabItem;
import org.geosdi.geoplatform.gui.client.widget.tree.GPTreePanel;
import org.geosdi.geoplatform.gui.model.tree.GPBeanTreeModel;
import org.geosdi.geoplatform.gui.model.tree.GPLayerAttributes;
import org.geosdi.geoplatform.gui.model.tree.GPLayerAttributes.GPAttributeKey;

/**
 * @author Nazzareno Sileno - CNR IMAA geoSDI Group
 * @email nazzareno.sileno@geosdi.org
 */
public class CQLFilterAdvancedTab extends GeoPlatformTabItem implements ICQLFilterTab {

    private TextArea filterTextArea;
    private GPTreePanel<GPBeanTreeModel> treePanel;

    public CQLFilterAdvancedTab(String title, GPTreePanel<GPBeanTreeModel> treePanel) {
        super(title);
        this.treePanel = treePanel;
        this.subclassCallToInit();
    }

    @Override
    public final void subclassCallToInit() {
        super.init();
    }

    @Override
    public void addComponents() {
        setSize(CQLFilterTabWidget.TAB_WIDGET_WIDTH,
                CQLFilterTabWidget.TAB_WIDGET_HEIGHT);
        this.filterTextArea = new TextArea();
        HorizontalPanel functionPanel = new HorizontalPanel();
        functionPanel.setSpacing(2);
        final CQLLayerAttributesComboBox attributesComboBox = new CQLLayerAttributesComboBox(this.treePanel);
        attributesComboBox.addSelectionChangedListener(new SelectionChangedListener<GPLayerAttributes>() {
            @Override
            public void selectionChanged(SelectionChangedEvent<GPLayerAttributes> se) {
                GPLayerAttributes layerAttribute = se.getSelectedItem();
                if (layerAttribute != null) {
                    insertTextIntoFilterArea(layerAttribute.get(
                            GPAttributeKey.ATTRIBUTE_VALUE.toString()).toString());
                    attributesComboBox.reset();
                }
            }
        });
        attributesComboBox.setEmptyText("Select Layer Attribute");
        functionPanel.add(attributesComboBox);
        final CQLLogicalOperatorComboBox logicalOperatorComboBox = new CQLLogicalOperatorComboBox();
        logicalOperatorComboBox.setEmptyText("Select Logical Operator");
        logicalOperatorComboBox.addSelectionChangedListener(new SelectionChangedListener<LogicalOperator>() {
            @Override
            public void selectionChanged(SelectionChangedEvent<LogicalOperator> se) {
                LogicalOperator logicalOperator = se.getSelectedItem();
                if (logicalOperator != null) {
                    insertTextIntoFilterArea(logicalOperator.getKeyValue());
                    logicalOperatorComboBox.reset();
                }
            }
        });
        functionPanel.add(logicalOperatorComboBox);

        final ListStore<CQLOperatorValue> operatorListStore = new ListStore<CQLOperatorValue>();
        operatorListStore.add(CQLOperatorValue.getOperatorList());
        final ComboBox<CQLOperatorValue> operatorComboBox = new ComboBox<CQLOperatorValue>();
        operatorComboBox.setStore(operatorListStore);
        operatorComboBox.setDisplayField(CQLOperatorValue.LimitConditionEnum.OPERATOR.toString());
        operatorComboBox.setEditable(Boolean.FALSE);
        operatorComboBox.setForceSelection(Boolean.TRUE);
        operatorComboBox.setEmptyText("Select Operator");
        operatorComboBox.addSelectionChangedListener(new SelectionChangedListener<CQLOperatorValue>() {
            @Override
            public void selectionChanged(SelectionChangedEvent<CQLOperatorValue> se) {
                CQLOperatorValue cQLOperatorValue = se.getSelectedItem();
                if (cQLOperatorValue != null) {
                    insertTextIntoFilterArea(cQLOperatorValue.get(
                            CQLOperatorValue.LimitConditionEnum.OPERATOR.toString()).toString());
                    operatorComboBox.reset();
                }
            }
        });
        functionPanel.add(operatorComboBox);
        HorizontalPanel symbolPanel = new HorizontalPanel();
        symbolPanel.setSpacing(2);
        Button openBracket = new Button("(", new SelectionListener<ButtonEvent>() {
            @Override
            public void componentSelected(ButtonEvent ce) {
                insertTextIntoFilterArea("(");
            }
        });
        symbolPanel.add(openBracket);
        Button openSquareBracket = new Button("[", new SelectionListener<ButtonEvent>() {
            @Override
            public void componentSelected(ButtonEvent ce) {
                insertTextIntoFilterArea("]");
            }
        });
        symbolPanel.add(openSquareBracket);
        Button closedBracket = new Button(")", new SelectionListener<ButtonEvent>() {
            @Override
            public void componentSelected(ButtonEvent ce) {
                insertTextIntoFilterArea(")");
            }
        });
        symbolPanel.add(closedBracket);
        Button closedSquareBracket = new Button("]", new SelectionListener<ButtonEvent>() {
            @Override
            public void componentSelected(ButtonEvent ce) {
                insertTextIntoFilterArea("]");
            }
        });
        symbolPanel.add(closedSquareBracket);
        Button plusOperator = new Button("+", new SelectionListener<ButtonEvent>() {
            @Override
            public void componentSelected(ButtonEvent ce) {
                insertTextIntoFilterArea("+");
            }
        });
        symbolPanel.add(plusOperator);
        Button minusOperator = new Button("-", new SelectionListener<ButtonEvent>() {
            @Override
            public void componentSelected(ButtonEvent ce) {
                insertTextIntoFilterArea("-");
            }
        });
        symbolPanel.add(minusOperator);
        Button timesOperator = new Button("*", new SelectionListener<ButtonEvent>() {
            @Override
            public void componentSelected(ButtonEvent ce) {
                insertTextIntoFilterArea("*");
            }
        });
        symbolPanel.add(timesOperator);
        Button divisionOperator = new Button("/", new SelectionListener<ButtonEvent>() {
            @Override
            public void componentSelected(ButtonEvent ce) {
                insertTextIntoFilterArea("/");
            }
        });
        symbolPanel.add(divisionOperator);
        Button apexElement = new Button("'", new SelectionListener<ButtonEvent>() {
            @Override
            public void componentSelected(ButtonEvent ce) {
                insertTextIntoFilterArea("'");
            }
        });
        apexElement.setToolTip("Use apex before a string element");
        symbolPanel.add(apexElement);
        Button jollyCharacter = new Button("%", new SelectionListener<ButtonEvent>() {
            @Override
            public void componentSelected(ButtonEvent ce) {
                insertTextIntoFilterArea("%");
            }
        });
        jollyCharacter.setToolTip("The % character is a wild-card for any number of characters");
        symbolPanel.add(jollyCharacter);
        Button notOperator = new Button("NOT", new SelectionListener<ButtonEvent>() {
            @Override
            public void componentSelected(ButtonEvent ce) {
                insertTextIntoFilterArea("NOT");
            }
        });
        notOperator.setTitle("Negation of a condition");
        symbolPanel.add(notOperator);
        Button isNullOperator = new Button("IS NULL", new SelectionListener<ButtonEvent>() {
            @Override
            public void componentSelected(ButtonEvent ce) {
                insertTextIntoFilterArea("IS NULL");
            }
        });
        isNullOperator.setTitle("Tests whether a value is (non-)null, can be joined whit NOT operator");
        symbolPanel.add(isNullOperator);
        Button existOperator = new Button("EXIST", new SelectionListener<ButtonEvent>() {
            @Override
            public void componentSelected(ButtonEvent ce) {
                insertTextIntoFilterArea("EXIST");
            }
        });
        existOperator.setTitle("Applied to attributes: Tests whether a featuretype does have a given attribute");
        symbolPanel.add(existOperator);
        Button doesNotExistOperator = new Button("DOES-NOT-EXIST", new SelectionListener<ButtonEvent>() {
            @Override
            public void componentSelected(ButtonEvent ce) {
                insertTextIntoFilterArea("DOES-NOT-EXIST");
            }
        });
        doesNotExistOperator.setTitle("Applied to attributes: "
                + "Tests whether a featuretype does not have a given attribute");
        symbolPanel.add(doesNotExistOperator);


        HorizontalPanel spatialPanel = new HorizontalPanel();
        spatialPanel.setSpacing(2);
        BetweenCQLButton betweenCQLButton = new BetweenCQLButton(filterTextArea);
        spatialPanel.add(betweenCQLButton);
        INCQLButton incqlb = new INCQLButton(filterTextArea, this.treePanel);
        spatialPanel.add(incqlb);
        BBOXCQLButton bboxcqlb = new BBOXCQLButton(filterTextArea, this.treePanel);
        spatialPanel.add(bboxcqlb);
        Button includeOperator = new Button("INCLUDE", new SelectionListener<ButtonEvent>() {
            @Override
            public void componentSelected(ButtonEvent ce) {
                insertTextIntoFilterArea("INCLUDE");
            }
        });
        includeOperator.setTitle("Always include features to which this filter is applied");
        spatialPanel.add(includeOperator);
        Button excludeOperator = new Button("EXCLUDE", new SelectionListener<ButtonEvent>() {
            @Override
            public void componentSelected(ButtonEvent ce) {
                insertTextIntoFilterArea("EXCLUDE");
            }
        });
        excludeOperator.setTitle("Always exclude features to which this filter is applied");
        spatialPanel.add(excludeOperator);
        TimeCQLButton timeCQLButton = new TimeCQLButton(filterTextArea);
        spatialPanel.add(timeCQLButton);

        super.add(symbolPanel);
        super.add(spatialPanel);
        super.add(functionPanel);
        this.filterTextArea.setSize(CQLFilterTabWidget.TAB_WIDGET_WIDTH,
                CQLFilterTabWidget.TAB_WIDGET_HEIGHT - 80);
        super.add(this.filterTextArea, new FormData("98%"));
    }

    private void insertTextIntoFilterArea(String text) {
        String oldText = filterTextArea.getValue();
        StringBuilder newText = new StringBuilder();
        if (oldText != null && !oldText.isEmpty()) {
            newText.append(oldText.substring(0, filterTextArea.getCursorPos()));
            newText.append(text);
            newText.append(oldText.substring(filterTextArea.getCursorPos()));
        } else {
            newText.append(text);
        }
        filterTextArea.setValue(newText.toString());
    }

    @Override
    public String getCQLFilterExpression() {
        return this.filterTextArea.getValue();
    }

    @Override
    public void setCQLValue(String cqlFilter) {
        this.filterTextArea.setValue(cqlFilter);
    }

    @Override
    protected void onUnload() {
        super.onUnload();
        this.filterTextArea.clear();
    }
}
