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

import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.ComboBox.TriggerAction;
import com.extjs.gxt.ui.client.widget.form.Field;
import com.extjs.gxt.ui.client.widget.form.FormButtonBinding;
import com.extjs.gxt.ui.client.widget.form.FormPanel;
import com.extjs.gxt.ui.client.widget.form.SimpleComboBox;
import com.extjs.gxt.ui.client.widget.form.SimpleComboValue;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.extjs.gxt.ui.client.widget.form.Validator;
import org.geosdi.geoplatform.gui.client.model.GPUserManageDetail;
import org.geosdi.geoplatform.gui.client.model.GPUserManageDetailKeyValue;
import org.geosdi.geoplatform.gui.client.widget.binding.GeoPlatformBindingWidget;
import org.geosdi.geoplatform.gui.client.widget.form.binding.GPFieldBinding;
import org.geosdi.geoplatform.gui.global.security.GPRole;
import org.geosdi.geoplatform.gui.regex.GPRegEx;

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
    private FormButtonBinding formButtonBinding; // Monitors the valid state of a form and enabled / disabled all buttons.

    @Override
    public FormPanel createFormPanel() {
        FormPanel fp = new FormPanel();
        fp.setHeaderVisible(false);

        this.nameField = new TextField<String>();
        this.nameField.setId(GPUserManageDetailKeyValue.NAME.toString());
        this.nameField.setName(GPUserManageDetailKeyValue.NAME.toString());
        this.nameField.setFieldLabel("Name");
        this.nameField.setEmptyText("Enter a complete name (required)");
        this.nameField.setToolTip("Complete name of the user");

        this.emailField = new TextField<String>();
        this.emailField.setId(GPUserManageDetailKeyValue.EMAIL.toString());
        this.emailField.setName(GPUserManageDetailKeyValue.EMAIL.toString());
        this.emailField.setFieldLabel("Email");
        this.emailField.setEmptyText("Enter a email (required)");
        this.emailField.setToolTip("Email of the user");

        this.usernameField = new TextField<String>();
        this.usernameField.setId(GPUserManageDetailKeyValue.USERNAME.toString());
        this.usernameField.setName(GPUserManageDetailKeyValue.USERNAME.toString());
        this.usernameField.setFieldLabel("Username");
        this.usernameField.setEmptyText("Enter a username (required)");
        this.usernameField.setToolTip("Username of the user");
        this.usernameField.setAllowBlank(false);
        this.usernameField.setAutoValidate(true);
        this.usernameField.setMinLength(4);

        this.passwordField = new TextField<String>();
        this.passwordField.setPassword(true);

        this.passwordRepeatField = new TextField<String>();
        this.passwordRepeatField.setPassword(true);
        this.passwordRepeatField.setFieldLabel("Confirm password");
        this.passwordRepeatField.setToolTip("Confirm the password of the user");

        this.userRoleComboBox = new SimpleComboBox<GPRole>() {

            @Override
            protected void onSelect(SimpleComboValue<GPRole> model, int index) {
                super.onSelect(model, index);
                roleComboBinding.updateModel();
            }
        };
        this.userRoleComboBox.setId(GPUserManageDetailKeyValue.AUTORITHY.toString());
        this.userRoleComboBox.setName(GPUserManageDetailKeyValue.AUTORITHY.toString());
        this.userRoleComboBox.setFieldLabel("Role");
        this.userRoleComboBox.setEmptyText("Select a role... (required)");
        this.userRoleComboBox.setToolTip("Role of the user");
        this.userRoleComboBox.setEditable(false);
        this.userRoleComboBox.setTypeAhead(true);
        this.userRoleComboBox.setAllowBlank(false);
        this.userRoleComboBox.setTriggerAction(TriggerAction.ALL);
//        this.userRoleComboBox.setDisplayField(GPUserManageDetailKeyValue.AUTORITHY.toString());
        this.userRoleComboBox.add(GPRole.getAllRoles());

        fp.add(this.nameField);
        fp.add(this.emailField);
        fp.add(this.usernameField);
        fp.add(this.passwordField);
        fp.add(this.passwordRepeatField);
        fp.add(this.userRoleComboBox);

        this.formButtonBinding = new FormButtonBinding(fp);

        return fp;
    }

    public void startMonitoring(Button saveButton) {
        formButtonBinding.addButton(saveButton);
        formButtonBinding.startMonitoring();
    }

    public void stopMonitoring() {
        formButtonBinding.stopMonitoring();
    }

    public String getPassword() {
        return this.passwordField.getValue();
    }

    public void resetFields() {
        nameField.reset();
        emailField.reset();
        usernameField.reset();
        passwordField.reset();
        passwordRepeatField.reset();
        userRoleComboBox.reset();
    }

    @Override
    public void addFieldsBinding() {
        this.formBinding.addFieldBinding(new NameFieldBinding(this.nameField,
                GPUserManageDetailKeyValue.NAME.toString()));
        this.formBinding.addFieldBinding(new EmailFieldBinding(this.emailField,
                GPUserManageDetailKeyValue.EMAIL.toString()));
        this.formBinding.addFieldBinding(new UsernameFieldBinding(this.usernameField,
                GPUserManageDetailKeyValue.USERNAME.toString()));
//        this.formBinding.addFieldBinding(new PasswordFieldBinding(this.passwordField,
//                GPUserManageDetailKeyValue.PASSWORD.toString()));

        this.roleComboBinding = new RoleComboBinding(this.userRoleComboBox,
                GPUserManageDetailKeyValue.AUTORITHY.toString());
        this.formBinding.addFieldBinding(this.roleComboBinding);
    }

    @Override
    public void bindModel(GPUserManageDetail user) {
        super.bindModel(user);

        if (user.getId() == null) {
            this.handleFiledsInsertUser();
        } else {
            this.handleFieldsUpdateUser();
        }

        if (user.getAuthority() != null) {
            this.userRoleComboBox.setValue(userRoleComboBox.findModel(user.getAuthority()));
        }
    }

    private void handleFiledsInsertUser() {
        this.nameField.setValidator(this.validatorInsertName());
        this.nameField.setAllowBlank(false);
        this.nameField.setAutoValidate(true);

        this.emailField.setValidator(this.validatorInsertEmail());
        this.emailField.setAllowBlank(false);
        this.emailField.setAutoValidate(true);

        this.usernameField.setValidator(this.validatorInsertUsername());
        this.usernameField.setEnabled(true);

        this.passwordField.setFieldLabel("Password");
        this.passwordField.setToolTip("Password of the user");
        this.passwordField.setAllowBlank(false);
        this.passwordField.setAutoValidate(true);
        this.passwordField.setMinLength(6);

        this.passwordRepeatField.setValidator(this.validatorInsertPassword());
        this.passwordRepeatField.setAllowBlank(false);
        this.passwordRepeatField.setAutoValidate(true);

    }

    private void handleFieldsUpdateUser() {
        this.nameField.setValidator(this.validatorUpdateName());
        this.nameField.setAllowBlank(true);
        this.nameField.setAutoValidate(false);

        this.usernameField.setEnabled(false);

        this.emailField.setValidator(this.validatorUpdateEmail());
        this.emailField.setAllowBlank(true);
        this.emailField.setAutoValidate(false);

        // TODO Validator?
        this.passwordField.setFieldLabel("Reset password");
        this.passwordField.setToolTip("Reset password of the user");
        this.passwordField.setAllowBlank(true);
        this.passwordField.setAutoValidate(false);
        this.passwordField.setMinLength(0);

        this.passwordRepeatField.setValidator(this.validatorUpdatePassword());
        this.passwordRepeatField.setAllowBlank(true);
        this.passwordRepeatField.setAutoValidate(false);
    }

    /**
     * Validators for Update a user
     */
    private Validator validatorUpdateName() {
        return new Validator() {

            @Override
            public String validate(Field<?> field, String value) {
                if (!value.equals(getModel().getName())
                        && !GPRegEx.RE_COMPLETE_NAME.test(value)) {
                    return "Enter a complete name (example: John Steam)";
                }
                return null;
            }
        };
    }

    private Validator validatorUpdateEmail() {
        return new Validator() {

            @Override
            public String validate(Field<?> field, String value) {
                if (!value.equals(getModel().getEmail())
                        && !GPRegEx.RE_EMAIL.test(value)) {
                    return "Enter a valid email (example: any@foo.org)";
                }
                return null;
            }
        };
    }

    private Validator validatorUpdatePassword() {
        return new Validator() {

            @Override
            public String validate(Field<?> field, String value) {
                if (!passwordField.getValue().trim().isEmpty()
                        && !value.equals(passwordField.getValue())) {
                    return "Retyped password don't match";
                }
                return null;
            }
        };
    }

    /**
     * Validators for Insert a user
     */
    private Validator validatorInsertName() {
        return new Validator() {

            @Override
            public String validate(Field<?> field, String value) {
                if (!GPRegEx.RE_COMPLETE_NAME.test(value)) {
                    return "Enter a complete name (example: John Steam)";
                }
                return null;
            }
        };
    }

    private Validator validatorInsertEmail() {
        return new Validator() {

            @Override
            public String validate(Field<?> field, String value) {
                if (!GPRegEx.RE_EMAIL.test(value)) {
                    return "Enter a valid email (example: any@foo.org)";
                }
                return null;
            }
        };
    }

    private Validator validatorInsertUsername() {
        return new Validator() {

            @Override
            public String validate(Field<?> field, String value) {
                if (!GPRegEx.RE_USERNAME.test(value)) {
                    return "Enter a valid username (example: foo.3_BE-1)";
                }
                return null;
            }
        };
    }

    private Validator validatorInsertPassword() {
        return new Validator() {

            @Override
            public String validate(Field<?> field, String value) {
                if (!value.equals(passwordField.getValue())) {
                    return "Retyped password don't match";
                }
                return null;
            }
        };
    }

    /**
     * @author Nazzareno Sileno - CNR IMAA geoSDI Group
     * @email nazzareno.sileno@geosdi.org
     * 
     * Internal Class to map bi-directional Binding
     * 
     * model-view binding: method updateField
     * view-model binding: method setModelProperty
     * 
     */
    private class RoleComboBinding extends GPFieldBinding {

        public RoleComboBinding(Field field, String property) {
            super(field, property);
        }

        @Override
        public void updateField(boolean updateOriginalValue) {
            GPUserManageDetail userDetail = ((GPUserManageDetail) model);
            if (userDetail.getAuthority() != null) {
                userRoleComboBox.setValue(userRoleComboBox.findModel(userDetail.getAuthority()));
            }
        }

        @Override
        public void setModelProperty(Object val) {
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
            ((GPUserManageDetail) model).setUsername(val != null ? (String) val : "");
        }

        @Override
        public void updateField(boolean updateOriginalValue) {
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
            ((GPUserManageDetail) model).setName(val != null ? (String) val : "");
        }

        @Override
        public void updateField(boolean updateOriginalValue) {
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
            ((GPUserManageDetail) model).setEmail(val != null ? (String) val : "");
        }

        @Override
        public void updateField(boolean updateOriginalValue) {
            GPUserManageDetail userDetail = (GPUserManageDetail) model;
            emailField.setValue(userDetail.getEmail());
        }
    }
}
