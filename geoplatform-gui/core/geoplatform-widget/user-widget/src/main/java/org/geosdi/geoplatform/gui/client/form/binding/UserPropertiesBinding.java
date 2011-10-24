/*
 *  geo-platform
 *  Rich webgis framework
 *  http://geo-platform.org
 * ====================================================================
 *
 * Copyright (C) 2008-2011 geoSDI Group (CNR IMAA - Potenza - ITALY).
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
package org.geosdi.geoplatform.gui.client.form.binding;

import com.extjs.gxt.ui.client.store.Record;
import com.extjs.gxt.ui.client.widget.form.ComboBox.TriggerAction;
import com.extjs.gxt.ui.client.widget.form.Field;
import com.extjs.gxt.ui.client.widget.form.FormPanel;
import com.extjs.gxt.ui.client.widget.form.SimpleComboBox;
import com.extjs.gxt.ui.client.widget.form.SimpleComboValue;
import com.extjs.gxt.ui.client.widget.form.TextField;
import org.geosdi.geoplatform.gui.client.model.GPUserManageDetail;
import org.geosdi.geoplatform.gui.client.model.GPUserManageDetailKeyValue;
import org.geosdi.geoplatform.gui.client.widget.binding.GeoPlatformBindingWidget;
import org.geosdi.geoplatform.gui.client.widget.form.binding.GPFieldBinding;
import org.geosdi.geoplatform.gui.global.security.GPRole;

/**
 * @author Nazzareno Sileno - CNR IMAA geoSDI Group
 * @email nazzareno.sileno@geosdi.org
 */
public class UserPropertiesBinding extends GeoPlatformBindingWidget<GPUserManageDetail> {

    private TextField<String> nameField;
    private TextField<String> userNameField;
    private SimpleComboBox<GPRole> userRoleComboBox;
    private GPRoleComboBinding roleComboBinding;

//    private GPTreeLabelEvent labelEvent = new TreeChangeLabelEvent();
    @Override
    public FormPanel createFormPanel() {
        FormPanel fp = new FormPanel();
        fp.setHeaderVisible(false);

        this.nameField = new TextField<String>();
        this.nameField.setFieldLabel("Name");
        this.nameField.setId(GPUserManageDetailKeyValue.NAME.toString());
        this.nameField.setName(GPUserManageDetailKeyValue.NAME.toString());
        this.userNameField = new TextField<String>();
        this.userNameField.setFieldLabel("User Name");
        this.userNameField.setId(GPUserManageDetailKeyValue.USERNAME.toString());
        this.userNameField.setName(GPUserManageDetailKeyValue.USERNAME.toString());
        this.userRoleComboBox = new SimpleComboBox<GPRole>() {

            @Override
            protected void onSelect(SimpleComboValue<GPRole> model, int index) {
                super.onSelect(model, index);
                roleComboBinding.updateModel();
            }
        };
        this.userRoleComboBox.setFieldLabel("User Role");
        this.userRoleComboBox.setEmptyText("Select a role...");
        this.userRoleComboBox.setTypeAhead(true);
        this.userRoleComboBox.setTriggerAction(TriggerAction.ALL);
//        this.userRoleComboBox.setDisplayField(GPUserManageDetailKeyValue.AUTORITHY.toString());
        this.userRoleComboBox.add(GPRole.getAllRoles());
        this.userRoleComboBox.setId(GPUserManageDetailKeyValue.AUTORITHY.toString());
        this.userRoleComboBox.setName(GPUserManageDetailKeyValue.AUTORITHY.toString());


//        labelField = new TextField<String>();
//        labelField.setName(GPFolderKeyValue.LABEL.toString());
//        labelField.setFieldLabel("Label");
//        labelField.setFireChangeEventOnSetValue(true);
//        labelField.addKeyListener(new KeyListener() {
//
//            @Override
//            public void componentKeyDown(ComponentEvent event) {
//                super.componentKeyDown(event);
//                if (event.getKeyCode() == KeyCodes.KEY_ENTER
//                        && !labelField.getValue().isEmpty()) {
//                    getModel().setLabel(labelField.getValue());
//                }
//            }
//        });
        this.roleComboBinding = new GPRoleComboBinding(this.userRoleComboBox,
                GPUserManageDetailKeyValue.AUTORITHY.toString());
        fp.add(this.nameField);
        fp.add(this.userNameField);
        fp.add(this.userRoleComboBox);
        return fp;
    }

    @Override
    public void bindModel(GPUserManageDetail model) {
        super.bindModel(model);
        if (model.getAuthority() != null) {
            this.userRoleComboBox.setValue(userRoleComboBox.findModel(model.getAuthority()));
        }
    }

    @Override
    public void addFieldsBinding() {
        this.formBinding.addFieldBinding(new GPUserNameFieldBinding(this.userNameField,
                GPUserManageDetailKeyValue.USERNAME.toString()));
        this.formBinding.addFieldBinding(new GPUserFieldBinding(this.nameField,
                GPUserManageDetailKeyValue.NAME.toString()));
        this.formBinding.addFieldBinding(this.roleComboBinding);
    }

    /**
     * @author Nazzareno Sileno - CNR IMAA geoSDI Group
     * @email nazzareno.sileno@geosdi.org
     * 
     * Internal Class to map bi-directional Binding
     * 
     */
    private class GPRoleComboBinding extends GPFieldBinding {

        public GPRoleComboBinding(Field field, String property) {
            super(field, property);
        }

        @Override //From model to view
        public void updateField(boolean updateOriginalValue) {
//            System.out.println("Combo: Updating view");
            GPUserManageDetail userDetail = ((GPUserManageDetail) model);
            if (userDetail.getAuthority() != null) {
                userRoleComboBox.setValue(userRoleComboBox.findModel(userDetail.getAuthority()));
            }
        }

        @Override //From view to model
        public void setModelProperty(Object val) {
//            System.out.println("Combo: Updating model");
            if (val != null && val instanceof SimpleComboValue) {
                SimpleComboValue<GPRole> roleString = (SimpleComboValue) val;
                GPUserManageDetail userDetail = ((GPUserManageDetail) model);
                if (!roleString.getValue().equals(userDetail.getAuthority())) {
                    userDetail.setAuthority(roleString.getValue());
                }
            }
        }
    }

    private class GPUserNameFieldBinding extends GPFieldBinding {

        public GPUserNameFieldBinding(Field field, String property) {
            super(field, property);
        }

        @Override
        public void setModelProperty(Object val) {
            ((GPUserManageDetail) model).setUsername(val != null ? (String) val : "");
        }

        @Override
        public void updateField(boolean updateOriginalValue) {
            GPUserManageDetail userDetail = (GPUserManageDetail) model;
            userNameField.setValue(userDetail.getUsername());
        }
    }

    private class GPUserFieldBinding extends GPFieldBinding {

        public GPUserFieldBinding(Field field, String property) {
            super(field, property);
        }

        @Override
        public void setModelProperty(Object val) {
            ((GPUserManageDetail) model).setName(val != null ? (String) val : "");
        }

        @Override
        public void updateField(boolean updateOriginalValue) {
            GPUserManageDetail userDetail = (GPUserManageDetail) model;
            nameField.setValue(userDetail.getName());
        }
    }
}
