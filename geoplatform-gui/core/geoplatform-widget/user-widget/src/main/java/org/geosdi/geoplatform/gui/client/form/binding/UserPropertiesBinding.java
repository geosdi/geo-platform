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

import com.extjs.gxt.ui.client.widget.form.ComboBox.TriggerAction;
import com.extjs.gxt.ui.client.widget.form.Field;
import com.extjs.gxt.ui.client.widget.form.FormButtonBinding;
import com.extjs.gxt.ui.client.widget.form.FormPanel;
import com.extjs.gxt.ui.client.widget.form.HiddenField;
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
    private TextField<String> emailField;
    private TextField<String> usernameField;
    private TextField<String> passwordField;
    private TextField<String> passwordRepeatField;
    private SimpleComboBox<GPRole> userRoleComboBox;
    private RoleComboBinding roleComboBinding;
    //
    private FormButtonBinding buttonBinding; // Monitors the valid state of a form and enabled / disabled all buttons. 

//    TextField<String> text = new TextField<String>();
//     text.setFieldLabel("Name");
//     text.setEmptyText("Enter your full name");
//     text.setAllowBlank(false);
//     text.setMinLength(4);
    @Override
    public FormPanel createFormPanel() {
        FormPanel fp = new FormPanel();
        fp.setHeaderVisible(false);

        this.nameField = new TextField<String>();
        this.nameField.setFieldLabel("Name");
        this.nameField.setId(GPUserManageDetailKeyValue.NAME.toString());
        this.nameField.setName(GPUserManageDetailKeyValue.NAME.toString());

        this.emailField = new TextField<String>();
        this.emailField.setFieldLabel("Email");
        this.emailField.setId(GPUserManageDetailKeyValue.EMAIL.toString());
        this.emailField.setName(GPUserManageDetailKeyValue.EMAIL.toString());

        this.usernameField = new TextField<String>();
        this.usernameField.setFieldLabel("Username");
        this.usernameField.setId(GPUserManageDetailKeyValue.USERNAME.toString());
        this.usernameField.setName(GPUserManageDetailKeyValue.USERNAME.toString());

        this.passwordField = new TextField<String>();
        this.passwordField.setPassword(true);
        this.passwordField.setFieldLabel("Password");        
//        this.passwordField.setName(GPUserManageDetailKeyValue.PASSWORD.toString());

        this.passwordRepeatField = new TextField<String>();
        this.passwordRepeatField.setPassword(true);
        this.passwordRepeatField.setFieldLabel("Retype password");
//        this.passwordRepeatField.setName("Re-" + GPUserManageDetailKeyValue.PASSWORD.toString());

        this.userRoleComboBox = new SimpleComboBox<GPRole>() {

            @Override
            protected void onSelect(SimpleComboValue<GPRole> model, int index) {
                super.onSelect(model, index);
                roleComboBinding.updateModel();
            }
        };
        this.userRoleComboBox.setFieldLabel("Role");
        this.userRoleComboBox.setEmptyText("Select a role...");
        this.userRoleComboBox.setId(GPUserManageDetailKeyValue.AUTORITHY.toString());
        this.userRoleComboBox.setName(GPUserManageDetailKeyValue.AUTORITHY.toString());
        this.userRoleComboBox.setTypeAhead(true);
        this.userRoleComboBox.setTriggerAction(TriggerAction.ALL);
//        this.userRoleComboBox.setDisplayField(GPUserManageDetailKeyValue.AUTORITHY.toString());
        this.userRoleComboBox.add(GPRole.getAllRoles());

        fp.add(this.nameField);
        fp.add(this.emailField);
        fp.add(this.usernameField);
        fp.add(this.passwordField);
        fp.add(this.passwordRepeatField);
        fp.add(this.userRoleComboBox);
        return fp;
    }

    @Override
    public void addFieldsBinding() {
        this.formBinding.addFieldBinding(new NameFieldBinding(this.nameField,
                GPUserManageDetailKeyValue.NAME.toString()));
        this.formBinding.addFieldBinding(new UsernameFieldBinding(this.usernameField,
                GPUserManageDetailKeyValue.USERNAME.toString()));
        this.formBinding.addFieldBinding(new EmailFieldBinding(this.emailField,
                GPUserManageDetailKeyValue.EMAIL.toString()));

        this.roleComboBinding = new RoleComboBinding(this.userRoleComboBox,
                GPUserManageDetailKeyValue.AUTORITHY.toString());
        this.formBinding.addFieldBinding(this.roleComboBinding);
    }

    @Override
    public void bindModel(GPUserManageDetail model) {
        super.bindModel(model);
        if (model.getAuthority() != null) {
            this.userRoleComboBox.setValue(userRoleComboBox.findModel(model.getAuthority()));
        }
    }

    /**
     * @author Nazzareno Sileno - CNR IMAA geoSDI Group
     * @email nazzareno.sileno@geosdi.org
     * 
     * Internal Class to map bi-directional Binding
     * 
     */
    private class RoleComboBinding extends GPFieldBinding {

        public RoleComboBinding(Field field, String property) {
            super(field, property);
        }

        @Override //From model to view
        public void updateField(boolean updateOriginalValue) {
            System.out.println("Combo: Updating view");
            GPUserManageDetail userDetail = ((GPUserManageDetail) model);
            if (userDetail.getAuthority() != null) {
                userRoleComboBox.setValue(userRoleComboBox.findModel(userDetail.getAuthority()));
            }
        }

        @Override //From view to model
        public void setModelProperty(Object val) {
            System.out.println("Combo: Updating model");
            if (val != null && val instanceof SimpleComboValue) {
                SimpleComboValue<GPRole> roleString = (SimpleComboValue) val;
                GPUserManageDetail userDetail = ((GPUserManageDetail) model);
                if (!roleString.getValue().equals(userDetail.getAuthority())) {
                    userDetail.setAuthority(roleString.getValue());
                }
            }
        }
    }

    private class UsernameFieldBinding extends GPFieldBinding {

        public UsernameFieldBinding(Field field, String property) {
            super(field, property);
        }

        @Override
        public void setModelProperty(Object val) {
            System.out.println("Username: Updating model");
            ((GPUserManageDetail) model).setUsername(val != null ? (String) val : "");
        }

        @Override
        public void updateField(boolean updateOriginalValue) {
            System.out.println("Username: Updating view");
            GPUserManageDetail userDetail = (GPUserManageDetail) model;
            usernameField.setValue(userDetail.getUsername());
        }
    }

    private class NameFieldBinding extends GPFieldBinding {

        public NameFieldBinding(Field field, String property) {
            super(field, property);
        }

        @Override
        public void setModelProperty(Object val) {
            System.out.println("User: Updating model");
            ((GPUserManageDetail) model).setName(val != null ? (String) val : "");
        }

        @Override
        public void updateField(boolean updateOriginalValue) {
            System.out.println("User: Updating view");
            GPUserManageDetail userDetail = (GPUserManageDetail) model;
            nameField.setValue(userDetail.getName());
        }
    }

    private class EmailFieldBinding extends GPFieldBinding {

        public EmailFieldBinding(Field field, String property) {
            super(field, property);
        }

        @Override
        public void setModelProperty(Object val) {
            System.out.println("Email: Updating model");
            ((GPUserManageDetail) model).setEmail(val != null ? (String) val : "");
        }

        @Override
        public void updateField(boolean updateOriginalValue) {
            System.out.println("Email: Updating view");
            GPUserManageDetail userDetail = (GPUserManageDetail) model;
            nameField.setValue(userDetail.getEmail());
        }
    }

    private class PasswordFieldBinding extends GPFieldBinding { // TODO

        public PasswordFieldBinding(Field field, String property) {
            super(field, property);
        }

        @Override
        public void setModelProperty(Object val) {
            System.out.println("Password: Updating model");
            ((GPUserManageDetail) model).setPassword(val != null ? (String) val : "");
        }
    }
}
