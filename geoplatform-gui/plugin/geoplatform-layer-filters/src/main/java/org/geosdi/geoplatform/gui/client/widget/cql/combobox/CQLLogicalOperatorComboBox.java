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
package org.geosdi.geoplatform.gui.client.widget.cql.combobox;

import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.form.ComboBox;
import com.extjs.gxt.ui.client.widget.form.ComboBox.TriggerAction;
import org.geosdi.geoplatform.gui.client.i18n.LayerFiltersModuleConstants;

/**
 * @author Nazzareno Sileno - CNR IMAA geoSDI Group
 * @email nazzareno.sileno@geosdi.org
 */
public class CQLLogicalOperatorComboBox extends ComboBox<LogicalOperator> {

    public CQLLogicalOperatorComboBox() {
        this.init();
    }

    private void init() {
        this.setComboBoxProperties();
        ListStore<LogicalOperator> operatorsStore = new ListStore<LogicalOperator>();
        operatorsStore.add(LogicalOperator.getOperatorsList());
        super.setStore(operatorsStore);
//        super.setValue(super.store.getAt(0));
    }

    private void setComboBoxProperties() {
        super.setFieldLabel(LayerFiltersModuleConstants.INSTANCE.CQLLogicalOperatorComboBox_fieldLabelText());
        super.setForceSelection(true);
        super.setLoadingText(LayerFiltersModuleConstants.INSTANCE.CQLLogicalOperatorComboBox_loadingText());
        super.setTriggerAction(TriggerAction.ALL);
        super.setDisplayField(LogicalOperator.LogicalOperatorKeyValue.KEY_NAME.toString());
        super.setEditable(Boolean.FALSE);
        super.setForceSelection(true);
//        this.setValueField(GPLayerAttributes.GPAttributeKey.ATTRIBUTE_VALUE.toString());
    }
}
