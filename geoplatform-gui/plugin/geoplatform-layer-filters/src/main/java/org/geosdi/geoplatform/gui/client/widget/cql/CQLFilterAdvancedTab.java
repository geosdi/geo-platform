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
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.AbstractImagePrototype;
import org.geosdi.geoplatform.gui.client.BasicWidgetResources;
import org.geosdi.geoplatform.gui.client.i18n.LayerFiltersModuleConstants;
import org.geosdi.geoplatform.gui.client.model.GPUniqueValues;
import org.geosdi.geoplatform.gui.client.widget.cql.button.BBOXCQLButton;
import org.geosdi.geoplatform.gui.client.widget.cql.button.BetweenCQLButton;
import org.geosdi.geoplatform.gui.client.widget.cql.button.INCQLButton;
import org.geosdi.geoplatform.gui.client.widget.cql.button.TimeCQLButton;
import org.geosdi.geoplatform.gui.client.widget.cql.combobox.CQLLayerAttributesComboBox;
import org.geosdi.geoplatform.gui.client.widget.cql.combobox.CQLLogicalOperatorComboBox;
import org.geosdi.geoplatform.gui.client.widget.cql.combobox.CQLUniqueValuesComboBox;
import org.geosdi.geoplatform.gui.client.widget.cql.combobox.LogicalOperator;
import org.geosdi.geoplatform.gui.client.widget.cql.enumeration.CQLOperatorEnum;
import org.geosdi.geoplatform.gui.client.widget.tab.GeoPlatformTabItem;
import org.geosdi.geoplatform.gui.client.widget.tree.GPTreePanel;
import org.geosdi.geoplatform.gui.model.tree.GPBeanTreeModel;
import org.geosdi.geoplatform.gui.model.tree.GPLayerAttributes;
import org.geosdi.geoplatform.gui.model.tree.GPLayerAttributes.GPAttributeKey;

import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

/**
 * @author Nazzareno Sileno - CNR IMAA geoSDI Group
 * @email nazzareno.sileno@geosdi.org
 */
public class CQLFilterAdvancedTab extends GeoPlatformTabItem implements ICQLFilterTab {

    final static Logger logger = Logger.getLogger("CQLFilterAdvancedTab");

    private TextArea filterTextArea;
    private GPTreePanel<GPBeanTreeModel> treePanel;
    private Boolean isString;
    private GuideFilterWidget guideFilterWidget;

    public CQLFilterAdvancedTab(String title, GPTreePanel<GPBeanTreeModel> treePanel) {
        super(title, Boolean.TRUE);
        this.treePanel = treePanel;
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
        this.guideFilterWidget = new GuideFilterWidget(Boolean.TRUE);
        HorizontalPanel uniqueValuePanel = new HorizontalPanel();
        uniqueValuePanel.setSpacing(2);
        final CQLUniqueValuesComboBox uniqueValueComboBox = new CQLUniqueValuesComboBox(this.treePanel);
        uniqueValueComboBox.setEmptyText(LayerFiltersModuleConstants.INSTANCE.CQLFilterAdvancedTab_uniqueOperatorTooltipText());
        uniqueValueComboBox.setEnabled(false);
        uniqueValueComboBox.addSelectionChangedListener(new SelectionChangedListener<GPUniqueValues>() {
            @Override
            public void selectionChanged(SelectionChangedEvent<GPUniqueValues> se) {
                GPUniqueValues gpUniqueValues = se.getSelectedItem();
                if (gpUniqueValues != null) {
                    String uniqueValue = gpUniqueValues.get(
                            GPUniqueValues.GPUniqueValueKey.UNIQUE_VALUE.toString()).toString();
                    if(isString)
                        uniqueValue =  "'".concat(uniqueValue).concat("'");
                    insertTextIntoFilterArea(uniqueValue);
                    uniqueValueComboBox.reset();
                }
            }
        });
        uniqueValuePanel.add(uniqueValueComboBox);

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
                    uniqueValueComboBox.setEnabled(true);
                    isString = layerAttribute.get(
                            GPAttributeKey.ATTRIBUTE_TYPE.toString()).toString().equals("java.lang.String");
                    uniqueValueComboBox.setLayerAttribute(layerAttribute.get(
                            GPAttributeKey.ATTRIBUTE_VALUE.toString()).toString());
                }
            }
        });
        attributesComboBox.setEmptyText(LayerFiltersModuleConstants.INSTANCE.CQLFilterAdvancedTab_attributeEmptyText());
        functionPanel.add(attributesComboBox);
        final CQLLogicalOperatorComboBox logicalOperatorComboBox = new CQLLogicalOperatorComboBox();
        logicalOperatorComboBox.setEmptyText(LayerFiltersModuleConstants.INSTANCE.CQLFilterAdvancedTab_logicalOperatorEmptyText());
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
        operatorComboBox.setTypeAhead(Boolean.TRUE);
        operatorComboBox.setTriggerAction(ComboBox.TriggerAction.ALL);
        operatorComboBox.setEmptyText(LayerFiltersModuleConstants.INSTANCE.CQLFilterAdvancedTab_operatorEmptyText());
        operatorComboBox.addSelectionChangedListener(new SelectionChangedListener<CQLOperatorValue>() {
            @Override
            public void selectionChanged(SelectionChangedEvent<CQLOperatorValue> se) {
                CQLOperatorValue cQLOperatorValue = se.getSelectedItem();
                if (cQLOperatorValue != null) {
                    insertTextIntoFilterArea(((CQLOperatorEnum)cQLOperatorValue.get(CQLOperatorValue.LimitConditionEnum.OPERATOR.toString())).getValue());
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
        apexElement.setToolTip(LayerFiltersModuleConstants.INSTANCE.CQLFilterAdvancedTab_apexElementTooltipText());
        symbolPanel.add(apexElement);
        Button jollyCharacter = new Button("%", new SelectionListener<ButtonEvent>() {
            @Override
            public void componentSelected(ButtonEvent ce) {
                insertTextIntoFilterArea("%");
            }
        });
        jollyCharacter.setToolTip(LayerFiltersModuleConstants.INSTANCE.CQLFilterAdvancedTab_jollyCharacterTooltipText());
        symbolPanel.add(jollyCharacter);
        Button notOperator = new Button("NOT", new SelectionListener<ButtonEvent>() {
            @Override
            public void componentSelected(ButtonEvent ce) {
                insertTextIntoFilterArea("NOT");
            }
        });
        notOperator.setTitle(LayerFiltersModuleConstants.INSTANCE.CQLFilterAdvancedTab_notOperatorTooltipText());
        symbolPanel.add(notOperator);
        Button isNullOperator = new Button("IS NULL", new SelectionListener<ButtonEvent>() {
            @Override
            public void componentSelected(ButtonEvent ce) {
                insertTextIntoFilterArea("IS NULL");
            }
        });
        isNullOperator.setTitle(LayerFiltersModuleConstants.INSTANCE.CQLFilterAdvancedTab_isNullOperatorTooltipText());
        symbolPanel.add(isNullOperator);
        Button existOperator = new Button("EXIST", new SelectionListener<ButtonEvent>() {
            @Override
            public void componentSelected(ButtonEvent ce) {
                insertTextIntoFilterArea("EXIST");
            }
        });
        existOperator.setTitle(LayerFiltersModuleConstants.INSTANCE.CQLFilterAdvancedTab_existOperatorTooltipText());
        symbolPanel.add(existOperator);
        Button doesNotExistOperator = new Button("DOES-NOT-EXIST", new SelectionListener<ButtonEvent>() {
            @Override
            public void componentSelected(ButtonEvent ce) {
                insertTextIntoFilterArea("DOES-NOT-EXIST");
            }
        });
        doesNotExistOperator.setTitle(LayerFiltersModuleConstants.INSTANCE.CQLFilterAdvancedTab_doesNotExistOperatorTooltipText());
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
        includeOperator.setTitle(LayerFiltersModuleConstants.INSTANCE.CQLFilterAdvancedTab_includeOperatorTooltipText());
        spatialPanel.add(includeOperator);
        Button excludeOperator = new Button("EXCLUDE", new SelectionListener<ButtonEvent>() {
            @Override
            public void componentSelected(ButtonEvent ce) {
                insertTextIntoFilterArea("EXCLUDE");
            }
        });
        excludeOperator.setTitle(LayerFiltersModuleConstants.INSTANCE.CQLFilterAdvancedTab_excludeOperatorTooltipText());
        spatialPanel.add(excludeOperator);
        TimeCQLButton timeCQLButton = new TimeCQLButton(filterTextArea);
        spatialPanel.add(timeCQLButton);

        Button infoButton = new Button("",
                AbstractImagePrototype.create(BasicWidgetResources.ICONS.info()), new SelectionListener<ButtonEvent>() {
            @Override
            public void componentSelected(ButtonEvent ce) {
                guideFilterWidget.show();
            }
        });
        infoButton.setTitle(LayerFiltersModuleConstants.INSTANCE.CQLFilterAdvancedTab_cqlGuideTooltipText());
        spatialPanel.add(infoButton);

        super.add(symbolPanel);
        super.add(spatialPanel);
        super.add(functionPanel);
        super.add(uniqueValuePanel);
        this.filterTextArea.setSize(CQLFilterTabWidget.TAB_WIDGET_WIDTH,
                CQLFilterTabWidget.TAB_WIDGET_HEIGHT - 80);
        super.add(this.filterTextArea, new FormData("98%"));
    }

    private void insertTextIntoFilterArea(String text) {
        GWT.log(text);
        String oldText = filterTextArea.getValue();
        StringBuilder newText = new StringBuilder();
        if (oldText != null && !oldText.isEmpty()) {
            newText.append(oldText.substring(0, filterTextArea.getCursorPos()));
            newText.append(" "+text);
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
