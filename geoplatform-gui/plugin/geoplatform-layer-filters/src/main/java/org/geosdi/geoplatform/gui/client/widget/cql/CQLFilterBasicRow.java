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

import com.extjs.gxt.ui.client.event.*;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.form.ComboBox;
import com.extjs.gxt.ui.client.widget.form.MultiField;
import org.geosdi.geoplatform.gui.client.widget.cql.combobox.CQLLayerAttributesComboBox;
import org.geosdi.geoplatform.gui.client.widget.cql.combobox.CQLLogicalOperatorComboBox;
import org.geosdi.geoplatform.gui.client.widget.cql.enumeration.CQLOperatorEnum;
import org.geosdi.geoplatform.gui.client.widget.tree.GPTreePanel;
import org.geosdi.geoplatform.gui.configuration.GPSecureStringTextField;
import org.geosdi.geoplatform.gui.model.tree.GPBeanTreeModel;
import org.geosdi.geoplatform.gui.model.tree.GPLayerAttributes;
import org.geosdi.geoplatform.gui.model.tree.GPLayerAttributes.GPAttributeKey;

/**
 * @author Nazzareno Sileno - CNR IMAA geoSDI Group
 * @email nazzareno.sileno@geosdi.org
 */
public class CQLFilterBasicRow extends MultiField<String> {

    private CQLLayerAttributesComboBox attributesComboBox;
    private CQLLogicalOperatorComboBox logicalOperatorComboBox;
    private ComboBox<CQLOperatorValue> operatorComboBox;
    private GPSecureStringTextField conditionValueTextfield;
    private GPTreePanel<GPBeanTreeModel> treePanel;

    public CQLFilterBasicRow(GPTreePanel<GPBeanTreeModel> treePanel) {
        this.treePanel = treePanel;
        this.initializeRow();
    }

    private void initializeRow() {
        attributesComboBox = new CQLLayerAttributesComboBox(this.treePanel);
        attributesComboBox.addSelectionChangedListener(new SelectionChangedListener<GPLayerAttributes>() {
            @Override
            public void selectionChanged(SelectionChangedEvent<GPLayerAttributes> se) {
                GPLayerAttributes layerAttribute = se.getSelectedItem();
                if (layerAttribute != null) {
                    verifyAndProcessFieldValuesNotNull();
                }
            }
        });
        super.add(attributesComboBox);
        this.logicalOperatorComboBox = new CQLLogicalOperatorComboBox();
        this.logicalOperatorComboBox.setValue(this.logicalOperatorComboBox.getStore().getAt(0));

        final ListStore<CQLOperatorValue> operatorListStore = new ListStore<CQLOperatorValue>();
        operatorListStore.add(CQLOperatorValue.getOperatorList());
        operatorComboBox = new ComboBox<CQLOperatorValue>();
        operatorComboBox.setStore(operatorListStore);
        operatorComboBox.setDisplayField(CQLOperatorValue.LimitConditionEnum.OPERATOR.toString());
        operatorComboBox.setEditable(Boolean.FALSE);
        operatorComboBox.setForceSelection(Boolean.TRUE);
        operatorComboBox.setTypeAhead(Boolean.TRUE);
        operatorComboBox.setTriggerAction(ComboBox.TriggerAction.ALL);
        this.operatorComboBox.setValue(operatorListStore.getAt(0));
        operatorComboBox.addSelectionChangedListener(new SelectionChangedListener<CQLOperatorValue>() {
            @Override
            public void selectionChanged(SelectionChangedEvent<CQLOperatorValue> se) {
                CQLOperatorValue limitConditionValue = se.getSelectedItem();
                if (limitConditionValue != null) {
                    verifyAndProcessFieldValuesNotNull();
                }
            }
        });
        operatorComboBox.setWidth("80");
        super.add(operatorComboBox);
        this.conditionValueTextfield = new GPSecureStringTextField();
        this.conditionValueTextfield.addListener(Events.Change, new Listener<FieldEvent>() {
            @Override
            public void handleEvent(FieldEvent be) {
                verifyAndProcessFieldValuesNotNull();
            }
        });
        super.add(conditionValueTextfield);
    }

    private void verifyAndProcessFieldValuesNotNull() {
        if (this.conditionValueTextfield.getValue() != null
                && this.getSelectedOperator() != null
                && this.getSelectedAttribute() != null) {
//            RestrictionsBuilderUtility.saveConditionChanges(this);
        }
    }

    public CQLOperatorEnum getSelectedOperator() {
        CQLOperatorEnum limitOperatorEnum = null;
        CQLOperatorValue limitConditionValue = this.operatorComboBox.getValue();
        if (limitConditionValue != null) {
            limitOperatorEnum = limitConditionValue.get(CQLOperatorValue.LimitConditionEnum.OPERATOR.toString());
        }
        return limitOperatorEnum;
    }

    public String getSelectedAttribute() {
        String attribute = null;
        GPLayerAttributes layerAttribute = this.attributesComboBox.getValue();
        if (layerAttribute != null) {
            attribute = layerAttribute.get(GPAttributeKey.ATTRIBUTE_VALUE.toString());
        }
        return attribute;
    }

    public GPSecureStringTextField getConditionValueTextfield() {
        return conditionValueTextfield;
    }

    public CQLLogicalOperatorComboBox getLogicalOperatorComboBox() {
        return logicalOperatorComboBox;
    }
}
