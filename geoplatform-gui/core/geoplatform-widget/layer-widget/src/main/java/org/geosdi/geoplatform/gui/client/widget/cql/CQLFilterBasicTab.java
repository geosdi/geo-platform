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
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.widget.VerticalPanel;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.google.common.collect.Lists;
import java.util.List;
import org.geosdi.geoplatform.gui.client.BasicWidgetResources;
import org.geosdi.geoplatform.gui.client.widget.cql.combobox.LogicalOperator.LogicalOperatorKeyValue;
import org.geosdi.geoplatform.gui.client.widget.tab.GeoPlatformTabItem;
import org.geosdi.geoplatform.gui.client.widget.tree.GPTreePanel;
import org.geosdi.geoplatform.gui.model.tree.GPBeanTreeModel;
import org.geosdi.geoplatform.gui.utility.GeoPlatformUtils;

/**
 * @author Nazzareno Sileno - CNR IMAA geoSDI Group
 * @email nazzareno.sileno@geosdi.org
 */
public class CQLFilterBasicTab extends GeoPlatformTabItem implements ICQLFilterTab {

    private List<CQLFilterBasicRow> filterList;
    private GPTreePanel<GPBeanTreeModel> treePanel;
    private VerticalPanel expressionsPanel;
    private Button addExpressionButton;

    public CQLFilterBasicTab(String title, GPTreePanel<GPBeanTreeModel> treePanel) {
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
        this.filterList = Lists.newArrayList();
        this.expressionsPanel = new VerticalPanel();
        this.expressionsPanel.setSpacing(10);
        super.add(this.expressionsPanel);
        CQLFilterBasicRow basicRow = new CQLFilterBasicRow(treePanel);
        this.filterList.add(basicRow);
        this.expressionsPanel.add(basicRow);
        this.addExpressionButton = new Button("Add Expression");
        addExpressionButton.setIcon(BasicWidgetResources.ICONS.done());
        addExpressionButton.setToolTip("Add a new expression line");
        addExpressionButton.addSelectionListener(new SelectionListener<ButtonEvent>() {
            @Override
            public void componentSelected(ButtonEvent ce) {
                CQLFilterBasicTab.this.addCQLBasicRow();
            }
        });
        super.add(this.addExpressionButton);
    }

    private CQLFilterBasicRow addCQLBasicRow() {
        CQLFilterBasicRow basicRow = new CQLFilterBasicRow(treePanel);
        Button deleteRowButton = this.generateDeleteButton(basicRow);
        basicRow.add(deleteRowButton);
        this.filterList.add(basicRow);
        this.expressionsPanel.add(basicRow.getLogicalOperatorComboBox());
        this.expressionsPanel.add(basicRow);
        this.expressionsPanel.layout();
        return basicRow;
    }

    private Button generateDeleteButton(final CQLFilterBasicRow basicRow) {
        Button cancelFilterButton = new Button();
        cancelFilterButton.setIcon(BasicWidgetResources.ICONS.delete());
        cancelFilterButton.setToolTip("Remove this filter line");
        cancelFilterButton.addSelectionListener(new SelectionListener<ButtonEvent>() {
            @Override
            public void componentSelected(ButtonEvent ce) {
                filterList.remove(basicRow);
                expressionsPanel.remove(basicRow.getLogicalOperatorComboBox());
                expressionsPanel.remove(basicRow);
            }
        });
        return cancelFilterButton;
    }

    @Override
    public String getCQLFilterExpression() {
        String filterExpression = "";
        boolean firstIteration = true;
        for (CQLFilterBasicRow basicRow : GeoPlatformUtils.safeList(this.filterList)) {
            if (!firstIteration) {
                filterExpression += " "
                        + basicRow.getLogicalOperatorComboBox().getValue().get(LogicalOperatorKeyValue.KEY_NAME.toString())
                        + " ";
            }
            ICQLBasicOperator basicOperator = basicRow.getSelectedOperator().getOperatorInstance();
            filterExpression += basicOperator.generateExpression(
                    basicRow.getSelectedAttribute(),
                    basicRow.getConditionValueTextfield().getValue());
            firstIteration = false;
        }
        return filterExpression;
    }

    @Override
    public void setCQLValue(String cqlFilter) {
    }
}
