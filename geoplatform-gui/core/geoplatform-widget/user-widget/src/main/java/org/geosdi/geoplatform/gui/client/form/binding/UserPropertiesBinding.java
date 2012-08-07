/*
 *  geo-platform
 *  Rich webgis framework
 *  http://geo-platform.org
 * ====================================================================
 *
 * Copyright (C) 2008-2012 geoSDI Group (CNR IMAA - Potenza - ITALY).
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

import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.FieldEvent;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.store.Record;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.CheckBox;
import com.extjs.gxt.ui.client.widget.form.ComboBox.TriggerAction;
import com.extjs.gxt.ui.client.widget.form.Field;
import com.extjs.gxt.ui.client.widget.form.FormButtonBinding;
import com.extjs.gxt.ui.client.widget.form.FormPanel;
import com.extjs.gxt.ui.client.widget.form.LabelField;
import com.extjs.gxt.ui.client.widget.form.MultiField;
import com.extjs.gxt.ui.client.widget.form.SimpleComboBox;
import com.extjs.gxt.ui.client.widget.form.SimpleComboValue;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.extjs.gxt.ui.client.widget.form.Validator;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.datepicker.client.CalendarUtil;
import java.util.Date;
import java.util.List;
import org.geosdi.geoplatform.gui.client.model.GPUserManageDetail;
import org.geosdi.geoplatform.gui.client.model.GPUserManageDetail.GPUserManageDetailKeyValue;
import org.geosdi.geoplatform.gui.client.widget.binding.GeoPlatformBindingWidget;
import org.geosdi.geoplatform.gui.client.widget.form.binding.GPFieldBinding;
import org.geosdi.geoplatform.gui.regex.GPRegEx;

/**
 * @author Nazzareno Sileno - CNR IMAA geoSDI Group
 * @email nazzareno.sileno@geosdi.org
 *
 * @author Vincenzo Monteverde <vincenzo.monteverde@geosdi.org>
 *
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class UserPropertiesBinding extends GeoPlatformBindingWidget<GPUserManageDetail> {

    private TextField<String> nameField;
    private TextField<String> emailField;
    private TextField<String> usernameField;
    private TextField<String> passwordField;
    private TextField<String> passwordRepeatField;
    private CheckBox enabledField;
    private LabelField creationDateLabelField;
    private CheckBox temporaryField;
    private LabelField expiredLabelField;
    private SimpleComboBox<String> userRoleComboBox;
    private RoleComboBinding roleComboBinding;
    //
    private FormButtonBinding formButtonBinding; // Monitors the valid state of a form and enabled / disabled all buttons.
    private Button buttonBinding;
    //
    private GPUserManageDetail userOriginal = new GPUserManageDetail();
    private boolean updateName;
    private boolean updateEmail;
    private boolean updatePassword;
    private boolean updateEnabled;
    private boolean updateTemporary;
    private boolean updateRole;

    public UserPropertiesBinding(ListStore<GPUserManageDetail> store,
            Button buttonBinding) {
        super();
        super.formBinding.setStore(store);

        this.formButtonBinding = new FormButtonBinding(super.getWidget());
        this.formButtonBinding.addButton(this.buttonBinding = buttonBinding);
    }

    @Override
    public FormPanel createFormPanel() {
        FormPanel fp = new FormPanel();
        fp.setHeaderVisible(false);

        this.nameField = new TextField<String>();
        this.nameField.setId(GPUserManageDetailKeyValue.NAME.toString());
        this.nameField.setFieldLabel("Name");
        this.nameField.setEmptyText("Enter a complete name (required)");
        this.nameField.setToolTip("Complete name of the user");
        this.nameField.setAutoValidate(true);

        this.emailField = new TextField<String>();
        this.emailField.setId(GPUserManageDetailKeyValue.EMAIL.toString());
        this.emailField.setFieldLabel("Email");
        this.emailField.setEmptyText("Enter a email (required)");
        this.emailField.setToolTip("Email of the user");
        this.emailField.setAutoValidate(true);

        this.usernameField = new TextField<String>();
        this.usernameField.setId(GPUserManageDetailKeyValue.USERNAME.toString());
        this.usernameField.setFieldLabel("Username");
        this.usernameField.setEmptyText("Enter a username (required)");
        this.usernameField.setToolTip("Username of the user");
        this.usernameField.setAllowBlank(false);
        this.usernameField.setAutoValidate(true);
        this.usernameField.setMinLength(4);

        this.passwordField = new TextField<String>();
        this.passwordField.setPassword(true);
        this.passwordField.setValidator(this.validatorPassword());

        this.passwordRepeatField = new TextField<String>();
        this.passwordRepeatField.setPassword(true);
        this.passwordRepeatField.setFieldLabel("Confirm password");
        this.passwordRepeatField.setToolTip("Confirm the password of the user");

        this.enabledField = new CheckBox();
        this.enabledField.setId(GPUserManageDetailKeyValue.ENABLED.toString());
        this.enabledField.setToolTip("Check for enable the user");
        this.enabledField.addListener(Events.Change, new Listener<FieldEvent>() {
            @Override
            public void handleEvent(FieldEvent be) {
                Boolean check = (Boolean) be.getValue();
                if (check.booleanValue() != userOriginal.isEnabled()) {
                    updateEnabled(true);
                } else {
                    updateEnabled(false);
                }
            }
        });

        this.creationDateLabelField = new LabelField();

        MultiField enabledAndCreationFields = new MultiField();
        enabledAndCreationFields.setFieldLabel("Enabled");
        enabledAndCreationFields.add(enabledField);
        enabledAndCreationFields.add(new LabelField("<span class='spacer'>&nbsp;</span>"));
        enabledAndCreationFields.add(creationDateLabelField);

        this.temporaryField = new CheckBox();
        this.temporaryField.setId(GPUserManageDetailKeyValue.TEMPORARY.toString());
        this.temporaryField.addListener(Events.Change, new Listener<FieldEvent>() {
            @Override
            public void handleEvent(FieldEvent be) {
                Boolean temporary = (Boolean) be.getValue();
                if (temporary.booleanValue() != userOriginal.isTemporary()) {
                    updateTemporary(true);
                } else {
                    updateTemporary(false);
                }
            }
        });

        this.expiredLabelField = new LabelField();

        MultiField tempAndExpiredFields = new MultiField();
        tempAndExpiredFields.setFieldLabel("Temporary");
        tempAndExpiredFields.add(temporaryField);
        tempAndExpiredFields.add(new LabelField("<span class='spacer'>&nbsp;</span>"));
        tempAndExpiredFields.add(expiredLabelField);

        this.userRoleComboBox = new SimpleComboBox<String>() {
            @Override
            protected void onSelect(SimpleComboValue<String> model, int index) {
                super.onSelect(model, index);
                roleComboBinding.updateModel();
            }
        };
        this.userRoleComboBox.setId(GPUserManageDetailKeyValue.AUTORITHY.toString());
        this.userRoleComboBox.setFieldLabel("Role");
        this.userRoleComboBox.setEmptyText("Select a role... (required)");
        this.userRoleComboBox.setToolTip("Role of the user");
        this.userRoleComboBox.setEditable(false);
        this.userRoleComboBox.setTypeAhead(true);
        this.userRoleComboBox.setAllowBlank(false);
        this.userRoleComboBox.setMaxHeight(150);
        this.userRoleComboBox.setTriggerAction(TriggerAction.ALL);

        fp.add(this.nameField);
        fp.add(this.emailField);
        fp.add(this.usernameField);
        fp.add(this.passwordField);
        fp.add(this.passwordRepeatField);
        fp.add(enabledAndCreationFields);
        fp.add(tempAndExpiredFields);
        fp.add(this.userRoleComboBox);

        return fp;
    }

    public void resetFields() {
        this.nameField.reset();
        this.emailField.reset();
        this.usernameField.reset();
        this.passwordField.reset();
        this.passwordRepeatField.reset();
        this.enabledField.reset();
        this.creationDateLabelField.reset();
        this.temporaryField.reset();
        this.expiredLabelField.reset();
        this.userRoleComboBox.reset();
    }

    @Override
    public void addFieldsBinding() {
        this.roleComboBinding = new RoleComboBinding();
        this.formBinding.addFieldBinding(this.roleComboBinding);

        this.formBinding.addFieldBinding(new NameFieldBinding());
        this.formBinding.addFieldBinding(new EmailFieldBinding());
        this.formBinding.addFieldBinding(new UsernameFieldBinding());
        this.formBinding.addFieldBinding(new EnabledFieldBinding());
        this.formBinding.addFieldBinding(new TemporaryFieldBinding());
    }

    @Override
    public void bindModel(GPUserManageDetail user) {
        super.bindModel(user);

        this.buttonBinding.disable();
        if (user.getId() == null) { // INSERT USER
            this.handleFiledsInsertUser();
        } else { // UPDATE USER
            // Copy the changeable fields
            userOriginal.setName(user.getName());
            userOriginal.setEmail(user.getEmail());
            userOriginal.setEnabled(user.isEnabled());
            userOriginal.setTemporary(user.isTemporary());
            userOriginal.setAuthority(user.getAuthority());
            //
            this.formButtonBinding.stopMonitoring(); // NOTE FormButtonBinding is always start auto-magically
            this.handleFieldsUpdateUser();
        }
    }

    private void handleFiledsInsertUser() {
        this.nameField.setValidator(this.validatorInsertName());
        this.nameField.setAllowBlank(false);

        this.emailField.setValidator(this.validatorInsertEmail());
        this.emailField.setAllowBlank(false);

        this.usernameField.setValidator(this.validatorInsertUsername());
        this.usernameField.enable();

        this.passwordField.setFieldLabel("Password");
        this.passwordField.setToolTip("Password of the user");
        this.passwordField.setAllowBlank(false);

        this.passwordRepeatField.disable();
        this.passwordRepeatField.setValidator(this.validatorInsertConfirmPassword());
        this.passwordRepeatField.setAllowBlank(false);

        // TODO Check default enabled check box
//        getModel().setEnabled(true);
//        this.enabledField.setValue(true);
//        this.enabledField.setValueAttribute("true");
//        this.enabledField.setRawValue("true");

        this.creationDateLabelField.setText("");

        this.temporaryField.setToolTip("Check if the user is temporary (will be disabled in 10 days)");
        this.temporaryField.setReadOnly(false);

        this.expiredLabelField.setText("Will be disabled in 10 days");

        this.userRoleComboBox.setValidator(null);
    }

    private void handleFieldsUpdateUser() {
        GPUserManageDetail user = super.getModel();

        this.nameField.setValidator(this.validatorUpdateName());
        this.nameField.setAllowBlank(true);

        this.emailField.setValidator(this.validatorUpdateEmail());
        this.emailField.setAllowBlank(true);

        this.usernameField.disable();

        this.passwordField.setFieldLabel("Reset password");
        this.passwordField.setToolTip("Reset password of the user");
        this.passwordField.setAllowBlank(true);

        this.passwordRepeatField.disable();
        this.passwordRepeatField.setValidator(this.validatorUpdateConfirmPassword());
        this.passwordRepeatField.setAllowBlank(true);

        this.creationDateLabelField.setText("Created the " + DateTimeFormat.getFormat(
                DateTimeFormat.PredefinedFormat.DATE_LONG).format(user.getCreationDate()));

        if (!user.isTemporary()) {
            this.temporaryField.setReadOnly(true);
            this.temporaryField.setToolTip("Checked if the user is temporary");
        } else {
            this.temporaryField.setReadOnly(false);
            this.temporaryField.setToolTip("Dechecked and the user will not be temporary");

            if (user.isExpired()) {
                this.expiredLabelField.setText("<span style='color:red'>User expired</span>");
            } else {
                Date today = new Date();
                CalendarUtil.addDaysToDate(today, -10);
                this.expiredLabelField.setText("User not expired (remain "
                        + CalendarUtil.getDaysBetween(today, user.getCreationDate()) + " day/s)");
            }
        }

        this.userRoleComboBox.setValidator(this.validatorUpdateRole());
    }

    /**
     * Validators for Update a user.
     */
    private Validator validatorUpdateName() {
        return new Validator() {
            @Override
            public String validate(Field<?> field, String value) {
                if (value.equals(userOriginal.getName())) {
                    updateName(false);
                    return null; // Pseudo-valid
                }
                if (!GPRegEx.RE_COMPLETE_NAME.test(value)) {
                    updateName(false);
                    return "Complete name is not valid (example: John Steam)";
                }
                updateName(true);
                return null;
            }
        };
    }

    private Validator validatorUpdateEmail() {
        return new Validator() {
            @Override
            public String validate(Field<?> field, String value) {
                if (value.equals(userOriginal.getEmail())) {
                    updateEmail(false);
                    return null; // Pseudo-valid
                }
                if (!GPRegEx.RE_EMAIL.test(value)) {
                    updateEmail(false);
                    return "Email is not valid (example: any@foo.org)";
                }
                updateEmail(true);
                return null;
            }
        };
    }

    private Validator validatorPassword() {
        return new Validator() {
            @Override
            public String validate(Field<?> field, String value) {
                if (value.length() < 4) {
                    passwordRepeatField.disable();
                    return "The minimun lenght for password is 4";
                }
                passwordRepeatField.enable();
                return null;
            }
        };
    }

    private Validator validatorUpdateConfirmPassword() {
        return new Validator() {
            @Override
            public String validate(Field<?> field, String value) {
                if (value.equals(passwordField.getValue())) {
                    updatePassword(true);
                    return null;
                }
                updatePassword(false);
                return "Retyped reset password don't match";
            }
        };
    }

    private Validator validatorUpdateRole() {
        return new Validator() {
            @Override
            public String validate(Field<?> field, String value) {
                String role = userOriginal.getAuthority();
                if (role != null && value.equals(role)) {
                    updateRole(false);
                    return null; // Pseudo-valid
                }
                updateRole(true);
                return null;
            }
        };
    }

    private void updateName(boolean updateName) {
        this.updateName = updateName;
        this.updateUser();
    }

    private void updateEmail(boolean updateEmail) {
        this.updateEmail = updateEmail;
        this.updateUser();
    }

    private void updatePassword(boolean updatePassword) {
        this.updatePassword = updatePassword;
        this.updateUser();
    }

    private void updateEnabled(boolean updateEnabled) {
        this.updateEnabled = updateEnabled;
        this.updateUser();
    }

    private void updateTemporary(boolean updateTemporary) {
        this.updateTemporary = updateTemporary;
        this.updateUser();
    }

    private void updateRole(boolean updateRole) {
        this.updateRole = updateRole;
        this.updateUser();
    }

    private void updateUser() {
        if (updateName || updateEmail || updatePassword
                || updateEnabled || updateTemporary || updateRole) {
            this.buttonBinding.enable();
        } else {
            this.buttonBinding.disable();
        }
    }

    /**
     * Validators for Insert a user.
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

    private Validator validatorInsertConfirmPassword() {
        return new Validator() {
            @Override
            public String validate(Field<?> field, String value) {
                if (value.equals(passwordField.getValue())) {
                    return null;
                }
                return "Retyped password don't match";
            }
        };
    }

    /**
     * @author Nazzareno Sileno - CNR IMAA geoSDI Group
     * @email nazzareno.sileno@geosdi.org
     *
     * Internal Class to map bi-directional Binding
     *
     * model-view binding: method updateField view-model binding: method
     * setModelProperty
     *
     */
    private class RoleComboBinding extends GPFieldBinding {

        public RoleComboBinding() {
            super(userRoleComboBox, GPUserManageDetailKeyValue.AUTORITHY.toString());
        }

        @Override
        public void updateField(boolean updateOriginalValue) {
            GPUserManageDetail userDetail = (GPUserManageDetail) super.model;
            if (userDetail.getAuthority() != null) {
                userRoleComboBox.setValue(userRoleComboBox.findModel(userDetail.getAuthority()));
            }
        }

        @Override
        public void setModelProperty(Object val) {
            if (val != null && val instanceof SimpleComboValue) {
                SimpleComboValue<String> roleString = (SimpleComboValue) val;
                GPUserManageDetail userDetail = (GPUserManageDetail) super.model;
                if (!roleString.getValue().equals(userDetail.getAuthority())) {
                    userDetail.setAuthority(roleString.getValue());
                }
            }
        }

        @Override
        public void setRecordProperty(Record r, Object val) {
            r.set(super.property, ((SimpleComboValue<String>) val).getValue());
        }
    }

    private class UsernameFieldBinding extends GPFieldBinding {

        public UsernameFieldBinding() {
            super(usernameField, GPUserManageDetailKeyValue.USERNAME.toString());
        }

        @Override
        public void setModelProperty(Object val) {
            GPUserManageDetail userDetail = (GPUserManageDetail) super.model;
            userDetail.setUsername(val != null ? (String) val : "");
        }

        @Override
        public void updateField(boolean updateOriginalValue) {
            GPUserManageDetail userDetail = (GPUserManageDetail) super.model;
            usernameField.setValue(userDetail.getUsername());
        }

        @Override
        public void setRecordProperty(Record r, Object val) {
            r.set(super.property, val);
        }
    }

    private class NameFieldBinding extends GPFieldBinding {

        public NameFieldBinding() {
            super(nameField, GPUserManageDetailKeyValue.NAME.toString());
        }

        @Override
        public void setModelProperty(Object val) {
            GPUserManageDetail userDetail = (GPUserManageDetail) super.model;
            userDetail.setName(val != null ? (String) val : "");
        }

        @Override
        public void updateField(boolean updateOriginalValue) {
            GPUserManageDetail userDetail = (GPUserManageDetail) super.model;
            nameField.setValue(userDetail.getName());
        }

        @Override
        public void setRecordProperty(Record r, Object val) {
            r.set(super.property, val);
        }
    }

    private class EmailFieldBinding extends GPFieldBinding {

        public EmailFieldBinding() {
            super(emailField, GPUserManageDetailKeyValue.EMAIL.toString());
        }

        @Override
        public void setModelProperty(Object val) {
            GPUserManageDetail userDetail = (GPUserManageDetail) super.model;
            userDetail.setEmail(val != null ? (String) val : "");
        }

        @Override
        public void updateField(boolean updateOriginalValue) {
            GPUserManageDetail userDetail = (GPUserManageDetail) super.model;
            emailField.setValue(userDetail.getEmail());
        }

        @Override
        public void setRecordProperty(Record r, Object val) {
            r.set(super.property, val);
        }
    }

    private class EnabledFieldBinding extends GPFieldBinding {

        public EnabledFieldBinding() {
            super(enabledField, GPUserManageDetailKeyValue.ENABLED.toString());
        }

        @Override
        public void setModelProperty(Object val) {
            GPUserManageDetail userDetail = (GPUserManageDetail) super.model;
            userDetail.setEnabled(val != null ? (Boolean) val : false);
        }

        @Override
        public void updateField(boolean updateOriginalValue) {
            GPUserManageDetail userDetail = (GPUserManageDetail) super.model;
            enabledField.setValue(userDetail.isEnabled());
        }

        @Override
        public void setRecordProperty(Record r, Object val) {
            r.set(super.property, val);
        }
    }

    private class TemporaryFieldBinding extends GPFieldBinding {

        public TemporaryFieldBinding() {
            super(temporaryField, GPUserManageDetailKeyValue.TEMPORARY.toString());
        }

        @Override
        public void setModelProperty(Object val) {
            GPUserManageDetail userDetail = (GPUserManageDetail) super.model;
            userDetail.setTemporary(val != null ? (Boolean) val : false);
        }

        @Override
        public void updateField(boolean updateOriginalValue) {
            GPUserManageDetail userDetail = (GPUserManageDetail) super.model;
            temporaryField.setValue(userDetail.isTemporary());
        }

        @Override
        public void setRecordProperty(Record r, Object val) {
            r.set(super.property, val);
        }
    }

    public String getPassword() {
        return this.passwordField.getValue();
    }

    public void populateRoles(List<String> roles) {
        this.userRoleComboBox.removeAll();
        this.userRoleComboBox.add(roles);
    }
}
